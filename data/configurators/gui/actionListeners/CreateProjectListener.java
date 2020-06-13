package gui.actionListeners;

import gui.actionListeners.listenerTemplates.ProjectTemplateListener;

public class CreateProjectListener extends ProjectTemplateListener {
    
    @Override
    protected void openFile() {
        isApproved = projectConfig.createProject("","","","","","");
    }
    
    @Override
    protected void setWholeCol() {
        
    }
}
