package tests;



import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Created by Kostakis on 20/4/2020.
 */
public class MyTableModelTest {
    @Test
    public void testDumpData_detailedModel(){


        String file1str = null;
        try {
            file1str = new String(Files.readAllBytes(Paths.get("./Dump Files Original/detailedModel_Atlas.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String file2str = null;
        try {
            file2str = new String(Files.readAllBytes(Paths.get("./Dump Files Refactored/detailedModel_Atlas.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(file1str, file2str);
    }

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

    @Test
    public void testDumpData_zoomModel(){


        String file1str = null;
        try {
            file1str = new String(Files.readAllBytes(Paths.get("./Dump Files Original/zoomModel_Atlas.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String file2str = null;
        try {
            file2str = new String(Files.readAllBytes(Paths.get("./Dump Files Refactored/zoomModel_Atlas.txt")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(file1str, file2str);
    }
}