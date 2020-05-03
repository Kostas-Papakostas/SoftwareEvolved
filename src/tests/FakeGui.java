package tests;

<<<<<<< Updated upstream
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import org.antlr.v4.runtime.RecognitionException;

=======
import gui.actionListeners.TableController;
>>>>>>> Stashed changes
import gui.mainEngine.Gui;
import gui.tableElements.commons.MyTableModel;

public class FakeGui extends Gui{
    private static final long serialVersionUID = 1L;


<<<<<<< Updated upstream
=======

    private File fileToReturn;
    private MyTableModel detailedModel;
    
>>>>>>> Stashed changes
    public FakeGui(){
        loadProject();
        getInfoFromFileController();
        fillTable();
        fillTree();
        constructDetailedModel();
    }
<<<<<<< Updated upstream
    
    
    private void makeDetailedTable(String[] columns, String[][] rows) {
        detailedModel = new MyTableModel(columns, rows); 
    }
    
    
    private void loadProject() {
        String fileName;
        File file = new File("filesHandler/inis/Atlas.ini");
        fileName = file.toString();
        fileController.loadProjectAction(FakeGui.this, true, file);
    }
    
    
    private void constructDetailedModel() {
        TableConstructionAllSquaresIncluded table = new TableConstructionAllSquaresIncluded(globalDataKeeper);
        final String[] columns = table.constructColumns();
        final String[][] rows = table.constructRows();
        segmentSizeDetailedTable = table.getSegmentSize();
        makeDetailedTable(columns, rows);
    }
    
    
=======

    public File createProject(){

        FakeCreateProjectJDialog createProjectDialog = new FakeCreateProjectJDialog("lala", "filesHandler/datasets/Atlas.txt", "filesHandler/input/atlas.csv", "filesHandler/output/atlas_Assessment1.txt", "filesHandler/output/atlas_Assessment1.txt", "filesHandler/transitions/atlasTransitions.xml");

        createProjectDialog.setModal(true);

        createProjectDialog.setVisible(true);
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
        //String fileName;
        File file = new File("filesHandler/inis/Atlas.ini");
        //fileName = file.toString();
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
>>>>>>> Stashed changes

    public MyTableModel getDetailedModel() {
        return detailedModel;
    }
    
}
