package gui.controllers;

import data.dataKeeper.GlobalDataKeeper;
import gui.dialogs.CreateProjectJDialog;
import org.antlr.v4.runtime.RecognitionException;

import javax.swing.*;
import java.io.*;

public class FileController {
    private static FileController singleInstance = null;
    private static GlobalDataKeeper globalDataKeeper;
    private static String project;
    private String projectName;
    private String datasetTxt;
    private String inputCsv;
    private String outputAssessment1;
    private String outputAssessment2;
    private String transitionsFile;
    private String currentProject;

    //Needed for Singleton Pattern
    private FileController() {
    
    }

    public static FileController getInstance() {
        if(singleInstance == null) {
            singleInstance = new FileController();
        }
        return singleInstance;
    }

    private void readProject(String fileName) throws IOException {
        BufferedReader br;
        br = new BufferedReader(new FileReader(fileName));
        String line;

        while (true) {
            line = br.readLine();
            if (line == null)
                break;
            if (line.contains("Project-name")) {
                String[] projectNameTable = line.split(":");
                projectName = projectNameTable[1];
            } else if (line.contains("Dataset-txt")) {
                String[] datasetTxtTable = line.split(":");
                datasetTxt = datasetTxtTable[1];
            } else if (line.contains("Input-csv")) {
                String[] inputCsvTable = line.split(":");
                inputCsv = inputCsvTable[1];
            } else if (line.contains("Assessement1-output")) {
                String[] outputAss1 = line.split(":");
                outputAssessment1 = outputAss1[1];
            } else if (line.contains("Assessement2-output")) {
                String[] outputAss2 = line.split(":");
                outputAssessment2 = outputAss2[1];
            } else if (line.contains("Transition-xml")) {
                String[] transitionXmlTable = line.split(":");
                transitionsFile = transitionXmlTable[1];
            }

        };

        br.close();
    }

    public String loadProjectAction(boolean isApproved, File file) {
        String fileName;
        String internalProject;

        if (isApproved) {
            System.out.println(file.toString());
            project = file.getName();
            fileName = file.toString();
            System.out.println("!!" + project);
        } else {
            return null;
        }

        try {
            importData(fileName);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Something seems wrong with this file");
            return null;
        } catch (RecognitionException e1) {
            JOptionPane.showMessageDialog(null, "Something seems wrong with this file");
            return null;
        }
        internalProject= project;

        return internalProject;
    }
    
    public boolean createProject(String projectName,String datasetTxt,String inputCsv,String ass1,String ass2,String transXml) {
        CreateProjectJDialog createProjectDialog = new CreateProjectJDialog(projectName, datasetTxt, inputCsv, ass1, ass2, transXml);
        createProjectDialog.setModal(true);
        createProjectDialog.setVisible(true);

        if (createProjectDialog.getConfirmation()) {

            createProjectDialog.setVisible(false);
            File file = createProjectDialog.getFile();
            System.out.println(file.toString());
            //TODO some kind of test? It the same with load project
            loadProjectAction(true, file);
            return true;
        }
        return false;
    }
    
    public void editProject(boolean isApproved, File inputFile) {
        String fileName = null;

        if (isApproved) {
            File file = inputFile;
            System.out.println(file.toString());
            project = file.getName();
            fileName = file.toString();
            System.out.println("!!" + project);

            try {
                readProject(fileName);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();

            }

            System.out.println(projectName);
            CreateProjectJDialog createProjectDialog = new CreateProjectJDialog(projectName, datasetTxt,
                    inputCsv, outputAssessment1, outputAssessment2, transitionsFile);

            createProjectDialog.setModal(true);
            createProjectDialog.setVisible(true);

            if (createProjectDialog.getConfirmation()) {
                createProjectDialog.setVisible(false);
                file = createProjectDialog.getFile();
                System.out.println(file.toString());
                //TODO check if is correct
                loadProjectAction(isApproved, file);
            }

        } else {
            return;
        }
    }
    
    public void importData(String fileName) throws IOException, RecognitionException {
        readProject(fileName);
        
        System.out.println("Project Name:" + projectName);
        System.out.println("Dataset txt:" + datasetTxt);
        System.out.println("Input Csv:" + inputCsv);
        System.out.println("Output Assessment1:" + outputAssessment1);
        System.out.println("Output Assessment2:" + outputAssessment2);
        System.out.println("Transitions File:" + transitionsFile);
        
        globalDataKeeper = new GlobalDataKeeper(datasetTxt, transitionsFile);
        globalDataKeeper.setData();
        System.out.println(globalDataKeeper.getAllPPLTables().size());

        System.out.println(fileName);
        currentProject = fileName;
    }

    public static GlobalDataKeeper getGlobalDataKeeper() {
        return globalDataKeeper;
    }

    public static String getProject() {
        return project;
    }
    
    public String getProjectName() {
        return projectName;
    }

    public String getDatasetTxt() {
        return datasetTxt;
    }

    public String getInputCsv() {
        return inputCsv;
    }

    public String getOutputAssessment1() {
        return outputAssessment1;
    }
    
    public String getOutputAssessment2() {
        return outputAssessment2;
    }

    public String getTransitionsFile() {
        return transitionsFile;
    }
    
    public String getCurrentProject() {
        return currentProject;
    }

}
