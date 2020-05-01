package gui.actionListeners;

import javax.swing.JOptionPane;

import gui.tableElements.tableConstructors.TableConstructionAllSquaresIncluded;

public class TableController {
    private FileController fileController = FileController.getInstance();
    
   /* private void createFullDetailedLifeTable() {
        if (!(fileController.getCurrentProject() == null)) {
            TableConstructionAllSquaresIncluded table = new TableConstructionAllSquaresIncluded(globalDataKeeper);
            final String[] columns = table.constructColumns();
            final String[][] rows = table.constructRows();
            segmentSizeDetailedTable = table.getSegmentSize();
            tabbedPane.setSelectedIndex(0);
            makeDetailedTable(columns, rows, true);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }*/
}
