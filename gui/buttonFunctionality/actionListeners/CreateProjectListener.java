package gui.buttonFunctionality.actionListeners;

import gui.buttonFunctionality.listenerTemplates.ProjectTemplateListener;

public class CreateProjectListener extends ProjectTemplateListener {
    
    @Override
    protected void openFile() {
        isApproved = projectConfig.createProject("","","","","","");
    }
    
    @Override
    protected void setWholeCol() {
        
    }
}
