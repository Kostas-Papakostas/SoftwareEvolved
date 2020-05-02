package tests;

import java.io.File;
import gui.actionListeners.TableController;
import gui.mainEngine.Gui;
import gui.tableElements.commons.MyTableModel;


public class FakeGui extends Gui{
    private static final long serialVersionUID = 1L;
    
    private MyTableModel detailedModel;
    
    public FakeGui(){
        loadProject();
        getInfoFromFileController();
        fillTable();
        fillTree();
        constructDetailedModel();  
    }
    
    
    private void loadProject() {
        //String fileName;
        File file = new File("filesHandler/inis/Atlas.ini");
        //fileName = file.toString();
        fileController.loadProjectAction(true, file);
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
