package tests;



//import org.junit.Test;

import data.configurators.ProjectConfigurator;
import gui.mainEngine.Gui;
import gui.tableElements.commons.MyTableModel;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kostakis on 20/4/2020.
 */
public class LoadUseCaseTest {
    Gui testGui = new Gui();
    private ProjectConfigurator projectConfig = ProjectConfigurator.getInstance();

    @Test
    public void testDumpData_detailedModel(){
        String file1str = null;
        String file2str = null;
        final String pathToOriginalFile = "./Dump Files Original/Load Use Case/detailedModel_Atlas.txt";
        final String pathToRefactoredFile = "./Dump Files Refactored/Load Use Case/detailedModel_Atlas.txt";

        File file = new File("filesHandler/inis/Atlas.ini");
        String project = projectConfig.loadProjectAction(true, file);
        testGui.getDataKeeper();
        System.out.println("rataata"+project);
        testGui.fillTable();
        testGui.fillTree();
        MyTableModel fakeDetailedModel = testGui.getDetailedTableConfig().createFullDetailedLifeTableModel();
        fakeDetailedModel.dumpData(pathToRefactoredFile);
        
        try {
            file1str = new String(Files.readAllBytes(Paths.get(pathToOriginalFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            file2str = new String(Files.readAllBytes(Paths.get(pathToRefactoredFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(file1str, file2str);
    }

    @Test
    public void testDumpData_generalModel(){
        String file1str = null;
        String file2str = null;
        String pathToOriginalFile = "./Dump Files Original/Load Use Case/generalModel_Atlas.txt";
        String pathToRefactoredFile = "./Dump Files Refactored/Load Use Case/generalModel_Atlas.txt";
        File file = new File("filesHandler/inis/Atlas.ini");
        String project = projectConfig.loadProjectAction(true, file);
        testGui.getDataKeeper();
        System.out.println("rataata"+project);
        testGui.fillTable();
        testGui.fillTree();
        MyTableModel fakeGeneralModel = testGui.getGeneralModel();
        fakeGeneralModel.dumpData(pathToRefactoredFile);
        
        try {
            file1str = new String(Files.readAllBytes(Paths.get(pathToOriginalFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            file2str = new String(Files.readAllBytes(Paths.get(pathToRefactoredFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        assertEquals(file1str, file2str);
    }

    @Test
    public void testDumpData_zoomModel(){
        String file1str = null;
        String file2str = null;
        String pathToOriginalFile = "./Dump Files Original/Load Use Case/zoomModel_Atlas.txt";
        String pathToRefactoredFile = "./Dump Files Refactored/Load Use Case/zoomModel_Atlas.txt";
        File file = new File("filesHandler/inis/Atlas.ini");
        String project = projectConfig.loadProjectAction(true, file);

        testGui.getDataKeeper();
        testGui.fillTable();
        testGui.fillTree();
        MyTableModel fakeZoomModel = testGui.getZoomModel();
        fakeZoomModel.dumpData(pathToRefactoredFile);
        
        try {
            file1str = new String(Files.readAllBytes(Paths.get(pathToOriginalFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try {
            file2str = new String(Files.readAllBytes(Paths.get(pathToRefactoredFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(file1str, file2str);
    }
}