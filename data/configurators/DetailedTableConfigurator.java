package data.configurators;

import data.dataKeeper.GlobalDataKeeper;
import gui.tableElements.commons.MyTableModel;
import gui.tableElements.tableConstructors.TableConstructionAllSquaresIncluded;

import javax.swing.*;

public class DetailedTableConfigurator {
    private static DetailedTableConfigurator singleInstance = null;
    private ProjectConfigurator projectConfig = ProjectConfigurator.getInstance();
    private GlobalDataKeeper globalDataKeeper;
    private boolean isLevelised = false;
    protected Integer[] segmentSizeDetailedTable = new Integer[3];
    protected MyTableModel detailedModel = null;
    

    private DetailedTableConfigurator() {

    }

    public static DetailedTableConfigurator getInstance() {
        if(singleInstance == null) {
            singleInstance = new DetailedTableConfigurator();
        }
        return singleInstance;
    }

    public MyTableModel createFullDetailedLifeTableModel() {
        if (!(projectConfig.getCurrentProject() == null)) {
            globalDataKeeper = ProjectConfigurator.getGlobalDataKeeper();
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

    public Integer[] getSegmentSizeDetailedTable() {
        return segmentSizeDetailedTable;
    }

    public boolean getIsLevelised() {
        return isLevelised;
    }

}
