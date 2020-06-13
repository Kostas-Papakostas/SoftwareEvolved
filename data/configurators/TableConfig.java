package data.configurators;

import data.dataKeeper.GlobalDataKeeper;
import data.dataSorters.PldRowSorter;
import gui.tableElements.commons.MyTableModel;
import gui.tableElements.tableConstructors.TableConstructionAllSquaresIncluded;

import javax.swing.*;

public class TableConfig{
    private static TableConfig singleInstance = null;
    private ProjectConfig projectConfig = ProjectConfig.getInstance();
    private GlobalDataKeeper globalDataKeeper;
    private String[] finalColumnsZoomArea;
    private String[][] finalRowsZoomArea;
    private MyTableModel generalModel;
    private boolean isLevelised = false;
    
    protected Integer[] segmentSizeDetailedTable = new Integer[3];
    protected MyTableModel detailedModel = null;
    protected MyTableModel zoomModel = null;

    //Needed for Singleton Pattern
    private TableConfig() {
    
    }

    public static TableConfig getInstance() {
        if(singleInstance == null) {
            singleInstance = new TableConfig();
        }
        return singleInstance;
    }

    public MyTableModel createFullDetailedLifeTableModel() {
        if (!(projectConfig.getCurrentProject() == null)) {
            globalDataKeeper = ProjectConfig.getGlobalDataKeeper();
            TableConstructionAllSquaresIncluded table = new TableConstructionAllSquaresIncluded(globalDataKeeper);
            final String[] columns = table.constructColumns();
            final String[][] rows = table.constructRows();
            segmentSizeDetailedTable = table.getSegmentSize();
            detailedModel = new MyTableModel(columns, rows);
            isLevelised=true;
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            isLevelised = false;
            return null;
        }

        return detailedModel;
    }

    public MyTableModel createZoomTableModel(String[][] finalRowsZoomArea, String[] finalColumnsZoomArea){
        PldRowSorter sorter = new PldRowSorter(finalRowsZoomArea, ProjectConfig.getGlobalDataKeeper());
        finalRowsZoomArea = sorter.sortRows();
        int numberOfColumns = finalRowsZoomArea[0].length;
        int numberOfRows = finalRowsZoomArea.length;
        String[][] tempRows = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            tempRows[i][0] = finalRowsZoomArea[i][0];
        }

        zoomModel = new MyTableModel(finalColumnsZoomArea, tempRows);
        return zoomModel;
    }

    public MyTableModel createGeneralTableModel(String[][] finalRowsGeneral, String[] finalColumnsGeneral){
        int numberOfColumns = finalRowsGeneral[0].length;
        int numberOfRows = finalRowsGeneral.length;
        String[][] rows = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            rows[i][0] = finalRowsGeneral[i][0];
        }

        generalModel = new MyTableModel(finalColumnsGeneral, rows);
        return generalModel;
    }

    public MyTableModel getZoomModel() {
        return zoomModel;
    }

    public String[] getFinalColumnsZoomArea() {
        return finalColumnsZoomArea;
    }

    public Integer[] getSegmentSizeDetailedTable() {
        return segmentSizeDetailedTable;
    }
    
    public String[][] getFinalRowsZoomArea(){
        return finalRowsZoomArea;
    }

    public boolean getIsLevelised() {
        return isLevelised;
    }
}
