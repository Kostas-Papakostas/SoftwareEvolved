package gui.actionListeners;

import gui.controllers.FileController;
import gui.mainEngine.Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateProjectListener implements ActionListener {

    private FileController fileController = FileController.getInstance();
    private Gui gui;

    public void listenToGui(Gui gui_p){
        this.gui = gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean okPressed = fileController.createProject("","","","","","");

        if (okPressed){
            gui.getInfoFromFileController();
            gui.fillTable();
            gui.fillTree();
            gui.defineButtonsVisibillity(true);
        }
    }
}
