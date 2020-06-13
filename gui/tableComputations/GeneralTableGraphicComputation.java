package gui.tableComputations;

import data.configurators.ProjectConfig;
import gui.tableElements.commons.JvTable;
import gui.tableElements.commons.MyTableModel;
import gui.tableElements.tableRenderers.IDUHeaderTableRenderer;

import javax.swing.*;
import java.awt.*;

public class GeneralTableGraphicComputation extends TableComputation{
    private static GeneralTableGraphicComputation singleInstance;

    public static GeneralTableGraphicComputation getInstance() {
        if(singleInstance == null) {
            singleInstance = new GeneralTableGraphicComputation();
        }
        return singleInstance;
    }

    public final JvTable makeGeneralGraphicTable(MyTableModel zoomModel_p, int rowHeight, int columnWidth, int wholeCol) {
        JvTable generalTable = new JvTable(zoomModel_p);
        generalTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        this.rowHeight = rowHeight;
        this.columnWidth = columnWidth;

        if (rowHeight < 1) {
            this.rowHeight = 1;
        }
        if (columnWidth < 1) {
            this.columnWidth = 1;
        }

        for (int i = 0; i < generalTable.getRowCount(); i++) {
            generalTable.setRowHeight(i, this.rowHeight);

        }

        generalTable.setShowGrid(false);
        generalTable.setIntercellSpacing(new Dimension(0, 0));

        for (int i = 0; i < generalTable.getColumnCount(); i++) {
            if (i == 0) {
                generalTable.getColumnModel().getColumn(0).setPreferredWidth(this.columnWidth);

            } else {
                generalTable.getColumnModel().getColumn(i).setPreferredWidth(this.columnWidth);

            }
        }

        int start = -1;
        int end = -1;
        if (ProjectConfig.getGlobalDataKeeper().getPhaseCollectors() != null && wholeCol != -1 && wholeCol != 0) {
            start = ProjectConfig.getGlobalDataKeeper().getPhaseCollectors().get(0).getPhases().get(wholeCol - 1).getStartPos();
            end = ProjectConfig.getGlobalDataKeeper().getPhaseCollectors().get(0).getPhases().get(wholeCol - 1).getEndPos();
        }

        if (wholeCol != -1) {
            for (int i = 0; i < generalTable.getColumnCount(); i++) {
                if (!(generalTable.getColumnName(i).equals("Table name"))) {
                    if (Integer.parseInt(generalTable.getColumnName(i)) >= start
                            && Integer.parseInt(generalTable.getColumnName(i)) <= end) {

                        generalTable.getColumnModel().getColumn(i).setHeaderRenderer(new IDUHeaderTableRenderer());

                    }
                }
            }
        }

        return generalTable;
    }

}
