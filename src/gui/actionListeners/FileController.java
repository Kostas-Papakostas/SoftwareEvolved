package gui.actionListeners;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

<<<<<<< Updated upstream
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
    
=======
public class FileController {
    private static FileController singleInstance = null;
    private GlobalDataKeeper globalDataKeeper;
    private String project;
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
    
    private void readProject(String fileName) throws FileNotFoundException, IOException {
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
    
    public void loadProjectAction(Boolean isApproved, File file) {
        String fileName;
        
        if (isApproved) {
            System.out.println(file.toString());
            project = file.getName();
            fileName = file.toString();
            System.out.println("!!" + project);

        } else {
            return;
        }
        
        try {
            importData(fileName);
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Something seems wrong with this file");
            return;
        } catch (RecognitionException e1) {
            JOptionPane.showMessageDialog(null, "Something seems wrong with this file");
            return;
        }
    }
    
    public void createProjectAction(File file) {
        loadProjectAction(true, file);
    }
    
    public void editProjectAction(File file) {
        loadProjectAction(true, file);
    }
    
    public void loadProject(Gui gui) {
        File dir = new File("filesHandler/inis");
        JFileChooser fcOpen1 = new JFileChooser();
        fcOpen1.setCurrentDirectory(dir);
        int returnVal = fcOpen1.showDialog(gui, "Open");
        loadProjectAction(returnVal == JFileChooser.APPROVE_OPTION, fcOpen1.getSelectedFile());
    }
    
    public void createProject(Gui gui) {
        CreateProjectJDialog createProjectDialog = new CreateProjectJDialog("", "", "", "", "", "");
        createProjectDialog.setModal(true);
        createProjectDialog.setVisible(true);

        if (createProjectDialog.getConfirmation()) {

            createProjectDialog.setVisible(false);
            File file = createProjectDialog.getFile();
            System.out.println(file.toString());
            //TODO some kind of test? It the same with load project
            createProjectAction(file);   
        }
    }
    
    public void editProject(Gui gui) {
        String fileName = null;
        File dir = new File("filesHandler/inis");
        JFileChooser fcOpen1 = new JFileChooser();
        fcOpen1.setCurrentDirectory(dir);
        int returnVal = fcOpen1.showDialog(gui, "Open");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fcOpen1.getSelectedFile();
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
                editProjectAction(file);
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
    
    public GlobalDataKeeper getGlobalDataKeeper() {
        return globalDataKeeper;
    }
    
    public String getProject() {
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

    /**for testing purposes**/
    public String loadProjectAction(Gui gui, boolean isApproved, File file) {
        String fileName;
        String project;

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

        return project;
    }

    public String createAndEditProjectAction(Gui gui, File file) {
        return loadProjectAction(gui, true, file);
    }

    public File createAndEditProject(String projectName, String datasetTxt, String inputCsv, String ass1, String ass2, String transXml){
        File project;

        CreateProjectJDialog createProjectDialog = new CreateProjectJDialog(projectName, datasetTxt, inputCsv, ass1, ass2, transXml);

        createProjectDialog.setModal(true);

        createProjectDialog.setVisible(true);


        if (createProjectDialog.getConfirmation()) {

            createProjectDialog.setVisible(false);

            File file = createProjectDialog.getFile();
            project=file;
            System.out.println(file.toString());
            //TODO some kind of test? It the same with load project
            //project = loadProjectAction(gui, returnVal == JFileChooser.APPROVE_OPTION, fcOpen1.getSelectedFile(), file);

        }else{
            return null;
        }
        return project;
    }
>>>>>>> Stashed changes
}
