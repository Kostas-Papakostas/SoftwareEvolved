package gui.actionListeners;

import gui.controllers.ProjectConfig;
import gui.mainEngine.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProjectListener implements ActionListener {

    private ProjectConfig projectConfig = ProjectConfig.getInstance();
    private Gui gui;

    public void listenToGui(Gui gui_p){
        this.gui = gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean okPressed = projectConfig.createProject("","","","","","");

        if (okPressed){
            gui.getDataKeeper();
            gui.fillTable();
            gui.fillTree();
            gui.defineButtonsVisibillity(true);
        }
    }
}
