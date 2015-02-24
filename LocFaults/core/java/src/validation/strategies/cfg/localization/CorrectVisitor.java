package validation.strategies.cfg.localization;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

import validation.Validation;
import validation.strategies.cfg.localization.DFSdevieVisitor.CtrInfo;
import validation.system.cfg.CfgCsp;
import validation.util.Type;
import expression.Expression;
import expression.logical.ArrayAssignment;
import expression.logical.Assignment;
import expression.logical.LogicalExpression;
import expression.logical.LogicalLiteral;
import expression.logical.NotExpression;
import expression.numeric.IntegerLiteral;
import expression.variables.ArrayVariable;
import expression.variables.Variable;
import ilog.concert.IloException;
import CFG.AssertEndWhile;
import CFG.AssertNode;
import CFG.BlockNode;
import CFG.CFG;
import CFG.CFGNode;
import CFG.CFGVisitException;
import CFG.CFGVisitor; 
import CFG.ConditionalNode;
import CFG.EnsuresNode;
import CFG.FunctionCallNode;
import CFG.IfNode;
import CFG.OnTheFlyWhileNode;
import CFG.RequiresNode;
import CFG.RootNode;
import CFG.WhileNode;

public class CorrectVisitor extends RhsExpressionComputer implements CFGVisitor {

	/**
	 * Values coming from the counterexample (the values ​​of the propagation of the counterexample).
	 */
	private HashMap<String, String> ceValues;
		
	private boolean Correcte;
	
	public int nbAssignments;
	
	public int nbNodes;
	
	public CorrectVisitor(HashMap<String, String> inputs) {		
		this.ceValues = inputs;	
		nbAssignments = 0;
		nbNodes = 0;
		knownValues = new HashMap<String, Expression>();
	}
	
	/**
	 * Explore the path.
	 * @param dfSdevieVisitor 
	 * @param method 
	 * 
	 * @param method
	 * @param globalInitializations
	 * @param inputs
	 * @throws IOException 
	 * @throws CFGVisitException 
	 */
	public boolean visitPath(CFGNode n) 
	{		
		try {
			n.getLeft().accept(this);
		} catch (Exception e) {
			System.err.println("Error (PathVisitor): CFG visit terminated abnormally!");
			e.printStackTrace();
			System.exit(-1);
		}
		
		return this.Correcte;
	}
		
	
	/**
	 * Adds an assignment constraint for an input variable. The variable's value comes from the counterexample.
	 *  
	 * @param v
	 * @param startLine
	 */
	private void addCEAssignment(Variable v, int startLine) {
		Expression value = Type.getLiteralFromString(ceValues.get(v.name()), v.type());
		if (value != null) {
			knownValues.put(v.name(), value);
		}
		else {
			System.err.println("Error (PathVisitor): incomplete counterexample, variable " + v + " is missing!");
			System.err.println(Thread.currentThread().getStackTrace());
			System.exit(-1);
		}
	}
	
	@Override
	public void visit(RootNode n) throws CFGVisitException, IloException {
		CFGNode firstNode = n.getLeft();
		if (firstNode != null)
			firstNode.accept(this);
		else
			System.err.println("Error (ConstantPropagator): empty CFG!");
	}

	@Override
	public void visit(EnsuresNode n) throws CFGVisitException, IloException {
		nbNodes++;
		LogicalLiteral cond = (LogicalLiteral)n.getCondition().structAccept(this);
		Correcte=cond.constantBoolean();
	}

	@Override
	public void visit(RequiresNode n) throws CFGVisitException, IloException {
		nbNodes++;
		
		// Cannot be a junction node
		n.getLeft().accept(this);
	}

	@Override
	public void visit(AssertNode n) throws CFGVisitException, IloException {
		LogicalLiteral cond = (LogicalLiteral)n.getCondition().structAccept(this);
		if (!cond.constantBoolean()) {  // this is the first failing assertion on this path
			// TODO ...
		}
		else {  // Ignore the assertion and go on visiting the faulty path
			n.getLeft().accept(this);
		}
	}

	@Override
	public void visit(AssertEndWhile n) throws CFGVisitException, IloException {
		n.getLeft().accept(this);
	}

	private void visitBranchingNode(ConditionalNode branchingNode) throws CFGVisitException, IloException {
		nbNodes++;
		LogicalLiteral cond = (LogicalLiteral)branchingNode.getCondition().structAccept(this);
		if (cond.constantBoolean()) {
			// Follow 'Then' branch
			branchingNode.getLeft().accept(this);
		}
		else {
			// Follow 'Else' branch
			branchingNode.getRight().accept(this);
		}
	}
	
	@Override
	public void visit(IfNode n) throws CFGVisitException, IloException {
		visitBranchingNode(n);
	}

	@Override
	public void visit(WhileNode n) throws CFGVisitException, IloException {
		visitBranchingNode(n);
	}
	
	private void visitAssignments(ArrayList<Assignment> block) {
		String ceValue;
		Expression rhs;
		IntegerLiteral index;
		Assignment ass;
		ArrayAssignment arrayAss;
		// the set of assignments is treated from the first one to the last one
		// to correctly handle SSA renaming
		ListIterator<Assignment> blockItr = block.listIterator();
		while (blockItr.hasNext()) {
			ass = blockItr.next();
			rhs = ass.rhs();
			if (rhs == null) { // non-det assignment are in the counterexample
				addCEAssignment(ass.lhs(), ass.startLine());
			}
			else {
				if (ass.rhs() instanceof ArrayVariable) { // array copy
					ArrayVariable arrayDest = (ArrayVariable)ass.lhs();
					for (int i=0; i<arrayDest.length(); i++) {
						knownValues.put(arrayDest.name() + '[' + i + ']', 
								        knownValues.get(((ArrayVariable)ass.rhs()).name() + '[' + i + ']'));
					}
				}
				else {
					// We first try to see if the value of this variable is part of the counterexample.
					// This is necessarily the case for non-det assignments, but we may also have
					// all the program variable values in the counterexample (and not just the inputs)
					ceValue = ceValues.get(ass.lhs().name());
					//if (ceValue != null) {  
						//rhs = Type.getLiteralFromString(ceValue, ass.lhs().type());
					//}
					//else {  // This non-input variable is not in the counterexample
						// Propagate constants and simplify rhs if possible
						rhs = (Expression)rhs.structAccept(this);
					//}

					if (ass instanceof ArrayAssignment) {
						arrayAss = (ArrayAssignment)ass;
						index = (IntegerLiteral)arrayAss.index().structAccept(this);
						for (int i=0; i<arrayAss.array().length(); i++) {
							if (i == index.constantNumber().intValue())
								knownValues.put(arrayAss.array().name() + '[' + i + ']', rhs);
							else
								knownValues.put(arrayAss.array().name() + '[' + i + ']', 
										        knownValues.get(arrayAss.previousArray().name() + '[' + i + ']'));
						}
					}
					else {  // not an array assignment
						knownValues.put(ass.lhs().name(), rhs);
					}
				}
			}
		}
		nbAssignments += block.size();
	}

	
	@Override
	public void visit(BlockNode n) throws CFGVisitException, IloException {
		nbNodes++;
		visitAssignments(n.getBlock());
		n.getLeft().accept(this);
	}

	@Override
	public void visit(FunctionCallNode n) throws CFGVisitException, IloException {
		nbNodes++;
		// add variables in parameter passing assignments 
		visitAssignments(n.getParameterPassing().getBlock());
		// add global variables which are used in the function
		visitAssignments(n.getLocalFromGlobal().getBlock());

		// visit the callee
		n.getCFG().firstNode().accept(this);
		
		// go on with the visit of the caller
		n.getLeft().accept(this);
	}

	@Override
	public void visit(OnTheFlyWhileNode whileNode) throws CFGVisitException {
		
	}
	
	
	

}
