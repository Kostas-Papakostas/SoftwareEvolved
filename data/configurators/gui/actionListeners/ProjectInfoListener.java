package gui.actionListeners;

import data.configurators.ProjectConfigurator;
import gui.dialogs.ProjectInfoDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjectInfoListener implements ActionListener {
    private ProjectConfigurator projectConfig = ProjectConfigurator.getInstance();

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
            System.out.println("Schemas:" + ProjectConfigurator.getGlobalDataKeeper().getAllPPLSchemas().size());
            System.out.println("Transitions:" + ProjectConfigurator.getGlobalDataKeeper().getAllPPLTransitions().size());
            System.out.println("Tables:" + ProjectConfigurator.getGlobalDataKeeper().getAllPPLTables().size());
            ProjectInfoDialog infoDialog = new ProjectInfoDialog(projectConfig.getProjectName(), projectConfig.getDatasetTxt(), projectConfig.getInputCsv(),
                    projectConfig.getTransitionsFile(), ProjectConfigurator.getGlobalDataKeeper().getAllPPLSchemas().size(),
                    ProjectConfigurator.getGlobalDataKeeper().getAllPPLTransitions().size(), ProjectConfigurator.getGlobalDataKeeper().getAllPPLTables().size());
            infoDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }
}
