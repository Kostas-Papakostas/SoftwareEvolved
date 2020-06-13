package gui.actionListeners;

import data.configurators.ProjectConfig;
import gui.mainEngine.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ProjectTemplateListener implements ActionListener{
    protected ProjectConfig projectConfig = ProjectConfig.getInstance();
    protected Gui gui;
    protected boolean isApproved;
    
    protected abstract void openFile();
    protected abstract void setWholeCol();
    
    public void listenToGui(Gui gui){
        this.gui = gui;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        openFile();
        
        if(isApproved) {
            setWholeCol();
            gui.getDataKeeper();
            gui.fillTable();
            gui.fillTree();
            gui.defineButtonsVisibillity(true);
        }
    }

}
