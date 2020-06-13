<<<<<<< HEAD
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
=======
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
>>>>>>> 1b15a71283cd26ef5125c42bcd399b76927e116a
