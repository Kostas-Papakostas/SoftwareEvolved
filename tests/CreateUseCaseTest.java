package tests;

import data.configurators.ProjectConfig;
import gui.mainEngine.Gui;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateUseCaseTest {

    Gui testGui = new Gui();
    private ProjectConfig projectConfig = ProjectConfig.getInstance();



    //@Test
    public void testCreatedFile(){
        projectConfig = ProjectConfig.getInstance();
        String expectedContenrts = "Project-name:lala\n"+
                "Dataset-txt:filesHandler/datasets/Atlas.txt\n"+
                "Input-csv:filesHandler/input/atlas.csv\n"+
                "Assessement1-output:filesHandler/output/atlas_Assessment1.txt\n"+
                "Assessement2-output:filesHandler/output/atlas_Assessment2.txt\n"+
                "Transition-xml:filesHandler/transitions/atlasTransitions.xml";

        projectConfig.createProject("lala","filesHandler/datasets/Atlas.txt","filesHandler/input/atlas.csv","filesHandler/output/atlas_Assessment1.txt","filesHandler/output/atlas_Assessment2.txt","filesHandler/transitions/atlasTransitions.xml");
        testGui.getDataKeeper();
        testGui.fillTable();
        testGui.fillTree();

        String file1str=null;

        try {
            file1str = new String(Files.readAllBytes(Paths.get("./filesHandler/inis/lala.ini")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(expectedContenrts,file1str);
    }
}
