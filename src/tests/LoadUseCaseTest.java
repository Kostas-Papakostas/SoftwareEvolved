package tests;



import org.junit.Test;

import gui.tableElements.commons.MyTableModel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kostakis on 20/4/2020.
 */
public class LoadUseCaseTest {
    FakeGui fakeGui = new FakeGui();
    
    @Test
    public void testDumpData_detailedModel(){
        String file1str = null;
        String file2str = null;
        String pathToOriginalFile = "./Dump Files Original/Load Use Case/detailedModel_Atlas.txt";
        String pathToRefactoredFile = "./Dump Files Refactored/Load Use Case/detailedModel_Atlas.txt";
        MyTableModel fakeDetailedModel = fakeGui.getDetailedModel();
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
        
        MyTableModel fakeGeneralModel = fakeGui.getGeneralModel();
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
        
        MyTableModel fakeZoomModel = fakeGui.getZoomModel();
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