
package tests;

import gui.tableElements.commons.MyTableModel;
import org.junit.jupiter.api.Test;

/**
 * used on Idea
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**used on idea*/

public class EditUseCaseTest {

    FakeGui fakeGui = new FakeGui();

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
}
