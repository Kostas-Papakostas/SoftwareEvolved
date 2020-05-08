
package tests;

<<<<<<< HEAD
import gui.actionListeners.FileController;
import gui.mainEngine.Gui;
import gui.tableElements.commons.MyTableModel;
import org.junit.jupiter.api.Test;

import java.io.File;
=======
import gui.tableElements.commons.MyTableModel;

import static org.junit.Assert.assertEquals;

>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

<<<<<<< HEAD
/**
 * used on Idea
 */import static org.junit.jupiter.api.Assertions.assertEquals;
=======
import org.junit.Test;

>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1

/**used on idea*/

public class EditUseCaseTest {

<<<<<<< HEAD
    Gui testGui = new Gui();
    private FileController fileController = FileController.getInstance();

    @Test
    public void testEdit_generalModel(){

=======
    FakeGui fakeGui = new FakeGui();

    @Test
    public void testDumpData_generalModel(){
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        String file1str = null;
        String file2str = null;
        String pathToOriginalFile = "./Dump Files Original/Load Use Case/generalModel_Atlas.txt";
        String pathToRefactoredFile = "./Dump Files Refactored/Load Use Case/generalModel_Atlas.txt";

<<<<<<< HEAD
        File file = new File("filesHandler/inis/Atlas.ini");
        fileController.editProject(true, file);

        testGui.getInfoFromFileController();
        testGui.fillTable();
        testGui.fillTree();

        MyTableModel fakeGeneralModel = testGui.getGeneralModel();
=======
        MyTableModel fakeGeneralModel = fakeGui.getGeneralModel();
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
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
<<<<<<< HEAD

=======
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
    }
}
