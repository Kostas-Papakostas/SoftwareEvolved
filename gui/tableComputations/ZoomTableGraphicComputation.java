package gui.tableComputations;

import gui.tableElements.commons.JvTable;
import gui.tableElements.commons.MyTableModel;

import javax.swing.*;
import java.awt.*;

public class ZoomTableGraphicComputation extends TableComputation{

    private static ZoomTableGraphicComputation singleInstance = null;

    public static ZoomTableGraphicComputation getInstance() {
        if(singleInstance == null) {
            singleInstance = new ZoomTableGraphicComputation();
        }
        return singleInstance;
    }

    public final JvTable makeZoomTable(MyTableModel zoomModel, int minWidth, int maxWidth){
        final JvTable zoomTable = new JvTable(zoomModel);

        zoomTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        zoomTable.setShowGrid(false);
        zoomTable.setIntercellSpacing(new Dimension(0, 0));

        for (int i = 0; i < zoomTable.getColumnCount(); i++) {
            if (i == 0) {
                zoomTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            } else {
                zoomTable.getColumnModel().getColumn(i).setPreferredWidth(maxWidth);
                zoomTable.getColumnModel().getColumn(i).setMaxWidth(maxWidth);
                zoomTable.getColumnModel().getColumn(i).setMinWidth(minWidth);
            }
        }

        return zoomTable;
    }
}
