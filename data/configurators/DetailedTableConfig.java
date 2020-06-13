package data.configurators;

import data.dataKeeper.GlobalDataKeeper;
import gui.tableElements.commons.MyTableModel;
import gui.tableElements.tableConstructors.TableConstructionAllSquaresIncluded;

import javax.swing.*;

public class DetailedTableConfig {

    private static DetailedTableConfig singleInstance = null;
    private ProjectConfig projectConfig = ProjectConfig.getInstance();
    private GlobalDataKeeper globalDataKeeper;

    private Integer[] segmentSizeZoomArea = new Integer[4];

    protected Integer[] segmentSizeDetailedTable = new Integer[3];

    protected MyTableModel detailedModel = null;
    private boolean isLevelised = false;

    private DetailedTableConfig() {

    }

    public static DetailedTableConfig getInstance() {
        if(singleInstance == null) {
            singleInstance = new DetailedTableConfig();
        }
        return singleInstance;
    }

    public MyTableModel createFullDetailedLifeTableModel() {
        if (!(projectConfig.getCurrentProject() == null)) {
            globalDataKeeper = projectConfig.getGlobalDataKeeper();
            TableConstructionAllSquaresIncluded table = new TableConstructionAllSquaresIncluded(globalDataKeeper);
            final String[] columns = table.constructColumns();
            final String[][] rows = table.constructRows();
            segmentSizeDetailedTable = table.getSegmentSize();
            detailedModel = new MyTableModel(columns, rows);
            isLevelised=true;
            //makeDetailedTable(columns, rows, true);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            isLevelised = false;
            return null;
        }

        return detailedModel;
    }

    public Integer[] getSegmentSizeDetailedTable() {
        return segmentSizeDetailedTable;
    }

    public boolean getIsLevelised() {
        return isLevelised;
    }

}
