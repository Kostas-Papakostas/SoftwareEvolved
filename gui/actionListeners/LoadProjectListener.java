package gui.actionListeners;

import gui.controllers.ProjectConfig;
import gui.mainEngine.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class LoadProjectListener implements ActionListener {

    private ProjectConfig projectConfig = ProjectConfig.getInstance();
    private String project;
    private Gui gui;

    public void listenToGui(Gui gui_p){
        this.gui = gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File dir = new File("filesHandler/inis");
        JFileChooser fcOpen1 = new JFileChooser();
        fcOpen1.setCurrentDirectory(dir);
        int returnVal = fcOpen1.showDialog(gui, "Open");

        project = projectConfig.loadProjectAction(returnVal==JFileChooser.APPROVE_OPTION, fcOpen1.getSelectedFile());

        if(returnVal==JFileChooser.APPROVE_OPTION) {
            gui.setWholeCol(-1);
            gui.getDataKeeper();
            gui.fillTable();
            gui.fillTree();
            gui.defineButtonsVisibillity(true);
        }
    }
}
