package gui.actionListeners;

import gui.mainEngine.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPLDListener implements ActionListener {

    private Gui gui;

    public void listenToGUI(Gui gui_p){
        this.gui=gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(gui.getProjectConfig().getCurrentProject() == null)) {
            gui.createPLD();
            gui.fillTree();
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }
}
