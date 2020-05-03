package tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

<<<<<<< Updated upstream
import org.junit.Test;
=======
//import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

//import static org.junit.Assert.assertEquals;

public class CreateUseCaseTestTest {

    FakeGui fakeGui = new FakeGui();


    @Test
    public void testCreatedFile(){
        String expectedContenrts = "Project-name:lala\n"+
                "Dataset-txt:filesHandler/datasets/Atlas.txt\n"+
                "Input-csv:filesHandler/input/atlas.csv\n"+
                "Assessement1-output:filesHandler/output/atlas_Assessment1.txt\n"+
                "Assessement2-output:filesHandler/output/atlas_Assessment1.txt\n"+
                "Transition-xml:filesHandler/transitions/atlasTransitions.xml";

        File file = fakeGui.getFileToReturn();
        String file1str=null;

        try {
            file1str = new String(Files.readAllBytes(Paths.get("./filesHandler/inis/lala.ini")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(expectedContenrts,file1str);
    }
>>>>>>> Stashed changes

public class CreateUseCaseTestTest {

    @Test
    public void testDumpData_generalModel(){


        String file1str = null;
        try {
            file1str = new String(Files.readAllBytes(Paths.get("./Dump Files Original/generalModel_Atlas.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String file2str = null;
        try {
            file2str = new String(Files.readAllBytes(Paths.get("./Dump Files Refactored/generalModel_Atlas.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(file1str, file2str);
    }

}
