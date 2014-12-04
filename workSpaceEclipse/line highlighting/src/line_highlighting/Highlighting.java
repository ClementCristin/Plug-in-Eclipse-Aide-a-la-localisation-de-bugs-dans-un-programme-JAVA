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
				
				IMarker marker = file.createMarker("myMarker");
				marker.setAttribute(IMarker.LINE_NUMBER, 3);
			    marker.setAttribute(IMarker.CHAR_START, 0);
			    marker.setAttribute(IMarker.CHAR_END, 10);
				marker.setAttribute(IMarker.TEXT, "ceci est un marker");
				
				System.out.println("\n\n" + file.getName() + "\n" + marker.getAttribute(IMarker.LINE_NUMBER));
				
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IEditorPart editor = page.getActiveEditor();
				ITextEditor textEditor = (ITextEditor) editor;
				IDocumentProvider docProvider = textEditor.getDocumentProvider();
				IDocument doc = docProvider.getDocument(editor.getEditorInput());
				
				IAnnotationModel annotationModel = docProvider.getAnnotationModel(editor.getEditorInput());
				SimpleMarkerAnnotation ma = new SimpleMarkerAnnotation("com.ibm.example.myannotation",marker);
				
				
				annotationModel.connect(doc);
				annotationModel.addAnnotation(ma, new Position(200,100));
				annotationModel.disconnect(doc);
				
				
			/*
				IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				IEditorPart editor = page.getActiveEditor();
				ITextEditor textEditor = (ITextEditor) editor;
				textEditor.
				textEditor.selectAndReveal(0, 10);
				
				*/
				
			} catch (CoreException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
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
