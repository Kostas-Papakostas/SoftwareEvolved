package gui.actionListeners;

import gui.actionListeners.listenerTemplates.ProjectTemplateListener;

import javax.swing.*;
import java.io.File;

public class EditProjectListener extends ProjectTemplateListener {
    
    @Override
    protected void openFile() {
        File file = new File("filesHandler/inis");
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(file);
        int returnValue = fileChooser.showDialog(gui, "Open");
        projectConfig.editProject(returnValue == JFileChooser.APPROVE_OPTION, fileChooser.getSelectedFile());
        isApproved = returnValue == JFileChooser.APPROVE_OPTION;
    }

    @Override
    protected void setWholeCol() {
        
    }
}
