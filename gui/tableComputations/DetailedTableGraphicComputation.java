package gui.tableComputations;

import data.configurators.ProjectConfig;
import gui.tableElements.commons.JvTable;
import gui.tableElements.commons.MyTableModel;

import javax.swing.*;

public class DetailedTableGraphicComputation extends TableComputation{

    private static DetailedTableGraphicComputation singleInstance;
    private ProjectConfig projectConfig = ProjectConfig.getInstance();

    private JvTable tmpLifeTimeTable;

    public static DetailedTableGraphicComputation getInstance() {
        if(singleInstance == null) {
            singleInstance = new DetailedTableGraphicComputation();
        }
        return singleInstance;
    }

    public JvTable makeDetailedGraphicTable(MyTableModel detailedModel, final boolean levelized) {

        tmpLifeTimeTable = new JvTable(detailedModel);

        tmpLifeTimeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        if (levelized == true) {
            for (int i = 0; i < tmpLifeTimeTable.getColumnCount(); i++) {
                if (i == 0) {
                    setTableWidth(tmpLifeTimeTable, 0, 150);
                } else {
                    if (tmpLifeTimeTable.getColumnName(i).contains("v")) {
                        setTableWidth(tmpLifeTimeTable, i, 100);
                    } else {
                        setTableWidth(tmpLifeTimeTable, i, 25);
                    }
                }
            }
        } else {
            for (int i = 0; i < tmpLifeTimeTable.getColumnCount(); i++) {
                if (i == 0) {
                    setTableWidth(tmpLifeTimeTable, 0, 150);
                } else {
                    setTableWidth(tmpLifeTimeTable, i, 20);
                }
            }
        }

        return tmpLifeTimeTable;
    }


    public JvTable getTmpLifeTimeTable() {
        return tmpLifeTimeTable;
    }

}