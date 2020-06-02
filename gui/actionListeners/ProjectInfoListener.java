package gui.actionListeners;

import data.configurators.ProjectConfig;
import gui.dialogs.ProjectInfoDialog;
import gui.mainEngine.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectInfoListener implements ActionListener {
    private Gui gui;
    private ProjectConfig projectConfig = ProjectConfig.getInstance();

    public void listenToGUI(Gui gui_p){
        gui=gui_p;
    }

    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(projectConfig.getCurrentProject() == null)) {

            System.out.println("Project Name:" + projectConfig.getProjectName());
            System.out.println("Dataset txt:" + projectConfig.getDatasetTxt());
            System.out.println("Input Csv:" + projectConfig.getInputCsv());
            System.out.println("Output Assessment1:" + projectConfig.getOutputAssessment1());
            System.out.println("Output Assessment2:" + projectConfig.getOutputAssessment2());
            System.out.println("Transitions File:" + projectConfig.getTransitionsFile());

            System.out.println("Schemas:" + projectConfig.getGlobalDataKeeper().getAllPPLSchemas().size());
            System.out.println("Transitions:" + projectConfig.getGlobalDataKeeper().getAllPPLTransitions().size());
            System.out.println("Tables:" + projectConfig.getGlobalDataKeeper().getAllPPLTables().size());

            ProjectInfoDialog infoDialog = new ProjectInfoDialog(projectConfig.getProjectName(), projectConfig.getDatasetTxt(), projectConfig.getInputCsv(),
                    projectConfig.getTransitionsFile(), projectConfig.getGlobalDataKeeper().getAllPPLSchemas().size(),
                    projectConfig.getGlobalDataKeeper().getAllPPLTransitions().size(), projectConfig.getGlobalDataKeeper().getAllPPLTables().size());

            infoDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }
}
