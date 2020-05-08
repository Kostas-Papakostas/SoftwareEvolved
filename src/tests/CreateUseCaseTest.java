package tests;

<<<<<<< HEAD
import gui.actionListeners.FileController;
import gui.mainEngine.Gui;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

//import org.junit.Test;

//import static org.junit.Assert.assertEquals;

public class CreateUseCaseTest {

    Gui testGui = new Gui();
    private FileController fileController = FileController.getInstance();



    @Test
    public void testCreatedFile(){
        fileController = FileController.getInstance();
=======
import gui.tableElements.commons.MyTableModel;
//import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.junit.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertEquals;

public class CreateUseCaseTest {
    FakeGui fakeGui = new FakeGui();

    @Test
    public void testCreatedFile(){
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        String expectedContenrts = "Project-name:lala\n"+
                "Dataset-txt:filesHandler/datasets/Atlas.txt\n"+
                "Input-csv:filesHandler/input/atlas.csv\n"+
                "Assessement1-output:filesHandler/output/atlas_Assessment1.txt\n"+
<<<<<<< HEAD
                "Assessement2-output:filesHandler/output/atlas_Assessment2.txt\n"+
                "Transition-xml:filesHandler/transitions/atlasTransitions.xml";

        fileController.createProject("lala","filesHandler/datasets/Atlas.txt","filesHandler/input/atlas.csv","filesHandler/output/atlas_Assessment1.txt","filesHandler/output/atlas_Assessment2.txt","filesHandler/transitions/atlasTransitions.xml");
        testGui.getInfoFromFileController();
        testGui.fillTable();
        testGui.fillTree();

=======
                "Assessement2-output:filesHandler/output/atlas_Assessment1.txt\n"+
                "Transition-xml:filesHandler/transitions/atlasTransitions.xml";

>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        String file1str=null;

        try {
            file1str = new String(Files.readAllBytes(Paths.get("./filesHandler/inis/lala.ini")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(expectedContenrts,file1str);
    }
<<<<<<< HEAD
=======


    @Test
    public void testDumpData_generalModel(){
        String file1str = null;
        String file2str = null;
        String pathToOriginalFile = "./Dump Files Original/Load Use Case/generalModel_Atlas.txt";
        String pathToRefactoredFile = "./Dump Files Refactored/Create Use Case/generalModel_Atlas.txt";

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

>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
}
