package data.configurators;

import gui.tableElements.commons.MyTableModel;

public interface TablesInterface {
    public MyTableModel createTableModel(String[][] finalRowsZoomArea_p, String[] finalColumnsZoomArea_p);
}
