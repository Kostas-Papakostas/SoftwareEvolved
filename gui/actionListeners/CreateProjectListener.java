package gui.actionListeners;

public class CreateProjectListener extends ProjectTemplateListener {
    
    @Override
    protected void openFile() {
        isApproved = projectConfig.createProject("","","","","","");
    }
    
    @Override
    protected void setWholeCol() {
        
    }
}
