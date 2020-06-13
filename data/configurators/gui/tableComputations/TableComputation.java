package gui.tableComputations;

import gui.tableElements.commons.JvTable;

abstract class TableComputation{
    protected int rowHeight;
    protected int columnWidth;

    protected JvTable setTableWidth(JvTable tmpLifeTimeTable, int colIndex, int preferedWidth){
        tmpLifeTimeTable.getColumnModel().getColumn(colIndex).setPreferredWidth(preferedWidth);
        tmpLifeTimeTable.getColumnModel().getColumn(colIndex).setMaxWidth(preferedWidth);
        tmpLifeTimeTable.getColumnModel().getColumn(colIndex).setMinWidth(preferedWidth);
        return tmpLifeTimeTable;
    }

    public int getRowHeight() {
        return rowHeight;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

}
