package gui.actionListeners;

import data.configurators.ProjectConfig;
import gui.dialogs.ProjectInfoDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectInfoListener implements ActionListener {
    private ProjectConfig projectConfig = ProjectConfig.getInstance();

    public void listenToGUI(){
        
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
            System.out.println("Schemas:" + ProjectConfig.getGlobalDataKeeper().getAllPPLSchemas().size());
            System.out.println("Transitions:" + ProjectConfig.getGlobalDataKeeper().getAllPPLTransitions().size());
            System.out.println("Tables:" + ProjectConfig.getGlobalDataKeeper().getAllPPLTables().size());
            ProjectInfoDialog infoDialog = new ProjectInfoDialog(projectConfig.getProjectName(), projectConfig.getDatasetTxt(), projectConfig.getInputCsv(),
                    projectConfig.getTransitionsFile(), ProjectConfig.getGlobalDataKeeper().getAllPPLSchemas().size(),
                    ProjectConfig.getGlobalDataKeeper().getAllPPLTransitions().size(), ProjectConfig.getGlobalDataKeeper().getAllPPLTables().size());
            infoDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }
}
