package line_highlighting;

import java.io.FileNotFoundException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.*;

public class Highlighting {
	
	public void doJob(){
		IWorkbenchPart workbenchPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart(); 
		IFile file = (IFile) workbenchPart.getSite().getPage().getActiveEditor().getEditorInput().getAdapter(IFile.class);
		if (file == null)
			try {
				throw new FileNotFoundException();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else{
			try {
				
				
				//we fetch the current open file open in the Eclipse workbench
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IEditorPart editor = page.getActiveEditor();
				ITextEditor textEditor = (ITextEditor) editor;
				IDocumentProvider docProvider = textEditor.getDocumentProvider();
				IDocument doc = docProvider.getDocument(editor.getEditorInput());
				
				//we create the maker
				IMarker marker = file.createMarker("myMarker");
				marker.setAttribute(IMarker.LINE_NUMBER, 3);
			    marker.setAttribute(IMarker.CHAR_START, 1033);
			    marker.setAttribute(IMarker.CHAR_END, 1065);
				marker.setAttribute(IMarker.TEXT, "ceci est un marker");
				
				//create the annotation
				IAnnotationModel annotationModel = docProvider.getAnnotationModel(editor.getEditorInput());
				SimpleMarkerAnnotation ma = new SimpleMarkerAnnotation("com.ibm.example.myannotation",marker);
				
				
				int start = (int) marker.getAttribute(IMarker.CHAR_START);
				int length = (int) marker.getAttribute(IMarker.CHAR_END) - (int) marker.getAttribute(IMarker.CHAR_START);
				
				//add the annotation in the file
				annotationModel.connect(doc);
				annotationModel.addAnnotation(ma, new Position(start,length));
				annotationModel.disconnect(doc);
				
				
			
				
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		/*
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorPart editor = page.getActiveEditor();
		ITextEditor textEditor = (ITextEditor) editor;
		textEditor.
		textEditor.selectAndReveal(0, 10);
		
		*/
		
		/*
		IWorkspaceRoot workspace = ResourcesPlugin.getWorkspace().getRoot(); 
		IProject p = workspace.getProject("lineHighlighting");
		IFolder folder = p.getFolder("src");
		IFile f = folder.getFile("Toto.java");
		
		
		try {
			IMarker marker = f.createMarker("myMarker");
			marker.setAttribute(IMarker.LINE_NUMBER, 3);
			
		} catch (CoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
	}
}
