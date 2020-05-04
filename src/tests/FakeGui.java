package tests;

import gui.actionListeners.TableController;
import gui.mainEngine.Gui;
import gui.tableElements.commons.MyTableModel;

import java.io.File;


public class FakeGui extends Gui{
    private static final long serialVersionUID = 1L;
    private File fileToReturn;
    private MyTableModel detailedModel;
    
    public FakeGui(){
        loadProject();
        getInfoFromFileController();
        fillTable();
        fillTree();
        constructDetailedModel();
        createProject();
    }

    public File createProject(){
        FakeCreateProjectJDialog createProjectDialog = new FakeCreateProjectJDialog("lala", "filesHandler/datasets/Atlas.txt", "filesHandler/input/atlas.csv", "filesHandler/output/atlas_Assessment1.txt", "filesHandler/output/atlas_Assessment1.txt", "filesHandler/transitions/atlasTransitions.xml");
        createProjectDialog.setModal(true);
        createProjectDialog.triggerOkButton();
        
        File file;
        if (true) {
            //createProjectDialog.setVisible(true);
            file = createProjectDialog.getFile();
            System.out.println(file.toString());
            //TODO some kind of test? It the same with load project
            project = fileController.createAndEditProjectAction(FakeGui.this, file);

        }
        fileToReturn=file;
        return file;
    }
    
    private void loadProject() {
        File file = new File("filesHandler/inis/Atlas.ini");
        fileController.loadProjectAction(true, file);
    }

    public File getFileToReturn() {
        return fileToReturn;
    }
    
    private void constructDetailedModel() {
        TableController tableController = TableController.getInstance();
        tableController.createFullDetailedLifeTable(selectedColumn);
        detailedModel = tableController.getDetailedModel();
    }

    public MyTableModel getDetailedModel() {
        return detailedModel;
    }
    
}
