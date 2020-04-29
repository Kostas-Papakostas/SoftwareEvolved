package gui.actionListeners;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.antlr.v4.runtime.RecognitionException;

import gui.mainEngine.Gui;

public class FileController {

    public void loadProjectAction(Gui gui, Boolean isApproved, File file) {
        String fileName;
        String project;
        
        if (isApproved) {
            System.out.println(file.toString());
            project = file.getName();
            fileName = file.toString();
            System.out.println("!!" + project);

        } else {
            return;
        }
        
        try {
            gui.importData(fileName);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Something seems wrong with this file");
            return;
        } catch (RecognitionException e1) {
            JOptionPane.showMessageDialog(null, "Something seems wrong with this file");
            return;
        }
    }
    
    public void createProjectAction(Gui gui, File file) {
        loadProjectAction(gui, true, file);
    }
    
    public void editProjectAction(Gui gui, File file) {
        loadProjectAction(gui, true, file);
    }
    
}
