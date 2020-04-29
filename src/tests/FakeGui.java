package tests;

import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

import org.antlr.v4.runtime.RecognitionException;

import gui.mainEngine.Gui;
import gui.tableElements.commons.MyTableModel;
import gui.tableElements.tableConstructors.TableConstructionAllSquaresIncluded;

public class FakeGui extends Gui{

    public FakeGui(){
        loadProject();
        constructDetailedModel();
    }
    
    
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
    
    

    
}
