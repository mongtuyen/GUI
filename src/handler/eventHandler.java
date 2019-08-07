package handler;

import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MInputPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class eventHandler {
	@Execute
	public void execute(EModelService modelService, MApplication app, EPartService partService) {


	    MPartStack stack = (MPartStack) modelService.find("test.partstack.editors", app);
	    
	    MInputPart part = MBasicFactory.INSTANCE.createInputPart();

	    part.setInputURI("bundleclass://Test/test.parts.ModelViewer");
	    part.setCloseable(true);

         
	    part.getObject();
	    stack.getChildren().add(part);
	    partService.showPart(part, PartState.ACTIVATE);
	
		}

}
