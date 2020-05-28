package gui.actionListeners;

import javax.swing.*;
import java.io.File;

public class LoadProjectListener extends ProjectTemplateListener{
   
    @Override
    protected void openFile() {
        File dir = new File("filesHandler/inis");
        JFileChooser fcOpen1 = new JFileChooser();
        fcOpen1.setCurrentDirectory(dir);
        int returnVal = fcOpen1.showDialog(gui, "Open");
        projectConfig.loadProjectAction(returnVal == JFileChooser.APPROVE_OPTION, fcOpen1.getSelectedFile());
        isApproved = returnVal == JFileChooser.APPROVE_OPTION;
    }

    @Override
    protected void setWholeCol() {
        gui.setWholeCol(-1);
    }
}
