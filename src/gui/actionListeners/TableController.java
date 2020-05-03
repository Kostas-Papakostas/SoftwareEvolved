package gui.actionListeners;

import data.dataKeeper.GlobalDataKeeper;
import data.dataSorters.PldRowSorter;
import gui.tableElements.commons.JvTable;
import gui.tableElements.commons.MyTableModel;
import gui.tableElements.tableConstructors.TableConstructionAllSquaresIncluded;
import gui.tableElements.tableConstructors.TableConstructionIDU;
import gui.tableElements.tableRenderers.IDUHeaderTableRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableController {
    private static TableController singleInstance = null;
    private FileController fileController = FileController.getInstance();
    private GlobalDataKeeper globalDataKeeper;
    private  int rowHeight;
    private  int columnWidth;
    private Integer[] segmentSizeZoomArea = new Integer[4];
    protected Integer[] segmentSizeDetailedTable = new Integer[3];
    protected MyTableModel detailedModel = null;
    private MyTableModel zoomModel = null;
    
    
    private JvTable tmpLifeTimeTable;
    private String[] finalColumnsZoomArea;
    private String[][] finalRowsZoomArea;
    
    //Needed for Singleton Pattern
    private TableController() {
    
    }
    
    
    public static TableController getInstance() {
        if(singleInstance == null) {
            singleInstance = new TableController();
        }
        return singleInstance;
    }
    
    
    public void createFullDetailedLifeTable(final int selectedColumn) {
        if (!(fileController.getCurrentProject() == null)) {
            globalDataKeeper = fileController.getGlobalDataKeeper();
            TableConstructionAllSquaresIncluded table = new TableConstructionAllSquaresIncluded(globalDataKeeper);
            final String[] columns = table.constructColumns();
            final String[][] rows = table.constructRows();
            segmentSizeDetailedTable = table.getSegmentSize();
            makeDetailedTable(columns, rows, true, selectedColumn);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }
    
   
    private JvTable setTableWidth(JvTable tmpLifeTimeTable, int colIndex, int preferedWidth) {
        tmpLifeTimeTable.getColumnModel().getColumn(colIndex).setPreferredWidth(preferedWidth);
        tmpLifeTimeTable.getColumnModel().getColumn(colIndex).setMaxWidth(preferedWidth);
        tmpLifeTimeTable.getColumnModel().getColumn(colIndex).setMinWidth(preferedWidth);
        return tmpLifeTimeTable;
    }
    
    
    private void makeDetailedTable(String[] columns, String[][] rows, final boolean levelized, final int selectedColumn) {
        
        detailedModel = new MyTableModel(columns, rows);

        tmpLifeTimeTable = new JvTable(detailedModel);

        tmpLifeTimeTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        if (levelized == true) {
            for (int i = 0; i < tmpLifeTimeTable.getColumnCount(); i++) {
                if (i == 0) {
                    setTableWidth(tmpLifeTimeTable,0,150);
                } else {
                    if (tmpLifeTimeTable.getColumnName(i).contains("v")) {
                        setTableWidth(tmpLifeTimeTable,i,100);
                    } else {
                        setTableWidth(tmpLifeTimeTable,i,25);
                    }
                }
            }
        } else {
            for (int i = 0; i < tmpLifeTimeTable.getColumnCount(); i++) {
                if (i == 0) {
                    setTableWidth(tmpLifeTimeTable,0,150);
                } else {
                    setTableWidth(tmpLifeTimeTable,i,20);
                }
            }
        }

        tmpLifeTimeTable.setName("LifeTimeTable");

        tmpLifeTimeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);

                String tmpValue = (String) table.getValueAt(row, column);
                String columnName = table.getColumnName(column);
                Color fr = new Color(0, 0, 0);
                c.setForeground(fr);

                if (selectedColumn == 0) {
                    if (isSelected) {
                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);

                        return c;
                    }
                } else {
                    if (isSelected && hasFocus) {

                        c.setBackground(Color.YELLOW);
                        return c;
                    }

                }

                try {
                    int numericValue = Integer.parseInt(tmpValue);
                    Color insersionColor = null;

                    if (columnName.equals("I")) {
                        if (numericValue == 0) {
                            insersionColor = new Color(255, 231, 186);
                        } else if (numericValue > 0 && numericValue <= segmentSizeDetailedTable[0]) {

                            insersionColor = new Color(193, 255, 193);
                        } else if (numericValue > segmentSizeDetailedTable[0]
                                && numericValue <= 2 * segmentSizeDetailedTable[0]) {
                            insersionColor = new Color(84, 255, 159);
                        } else if (numericValue > 2 * segmentSizeDetailedTable[0]
                                && numericValue <= 3 * segmentSizeDetailedTable[0]) {

                            insersionColor = new Color(0, 201, 87);
                        } else {
                            insersionColor = new Color(0, 100, 0);
                        }
                        c.setBackground(insersionColor);
                    }

                    if (columnName.equals("U")) {
                        if (numericValue == 0) {
                            insersionColor = new Color(255, 231, 186);
                        } else if (numericValue > 0 && numericValue <= segmentSizeDetailedTable[1]) {

                            insersionColor = new Color(176, 226, 255);
                        } else if (numericValue > segmentSizeDetailedTable[1]
                                && numericValue <= 2 * segmentSizeDetailedTable[1]) {
                            insersionColor = new Color(92, 172, 238);
                        } else if (numericValue > 2 * segmentSizeDetailedTable[1]
                                && numericValue <= 3 * segmentSizeDetailedTable[1]) {

                            insersionColor = new Color(28, 134, 238);
                        } else {
                            insersionColor = new Color(16, 78, 139);
                        }
                        c.setBackground(insersionColor);
                    }

                    if (columnName.equals("D")) {
                        if (numericValue == 0) {
                            insersionColor = new Color(255, 231, 186);
                        } else if (numericValue > 0 && numericValue <= segmentSizeDetailedTable[2]) {

                            insersionColor = new Color(255, 106, 106);
                        } else if (numericValue > segmentSizeDetailedTable[2]
                                && numericValue <= 2 * segmentSizeDetailedTable[2]) {
                            insersionColor = new Color(255, 0, 0);
                        } else if (numericValue > 2 * segmentSizeDetailedTable[2]
                                && numericValue <= 3 * segmentSizeDetailedTable[2]) {

                            insersionColor = new Color(205, 0, 0);
                        } else {
                            insersionColor = new Color(139, 0, 0);
                        }
                        c.setBackground(insersionColor);
                    }

                    return c;
                } catch (Exception e) {

                    if (tmpValue.equals("")) {
                        c.setBackground(Color.black);
                        return c;
                    } else {
                        if (columnName.contains("v")) {
                            c.setBackground(Color.lightGray);
                            if (levelized == false) {
                                setToolTipText(columnName);
                            }
                        } else {
                            Color tableNameColor = new Color(205, 175, 149);
                            c.setBackground(tableNameColor);
                        }
                        return c;
                    }
                }
            }
        });
        
    }

    
    //TODO
    
    public void createPLD() {
        globalDataKeeper = fileController.getGlobalDataKeeper();
        TableConstructionIDU table = new TableConstructionIDU(globalDataKeeper);
        final String[] columns = table.constructColumns();
        final String[][] rows = table.constructRows();
        segmentSizeZoomArea = table.getSegmentSize();
        System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
        System.out.println("C: " + columns.length + " R: " + rows.length);

        finalColumnsZoomArea = columns;
        finalRowsZoomArea = rows;
        
        //makeGeneralTableIDU(rowHeight,columnWidth,);
    }


    public MyTableModel getZoomModel() {
        return zoomModel;
    }

    public JvTable makeGeneralTableIDU(String[][] finalRowsZoomArea_p, String[] finalColumnsZoomArea_p, int rowHeight, int columnWidth, int wholeCol) {
        finalRowsZoomArea=finalRowsZoomArea_p;
        finalColumnsZoomArea=finalColumnsZoomArea_p;

        PldRowSorter sorter = new PldRowSorter(finalRowsZoomArea, fileController.getGlobalDataKeeper());

        finalRowsZoomArea = sorter.sortRows();

        int numberOfColumns = finalRowsZoomArea[0].length;
        int numberOfRows = finalRowsZoomArea.length;

        String[][] rows = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {

            rows[i][0] = finalRowsZoomArea[i][0];

        }

        zoomModel = new MyTableModel(finalColumnsZoomArea, rows);

        final JvTable generalTable = new JvTable(zoomModel);

        if (rowHeight < 1) {
            rowHeight = 1;
        }
        if (columnWidth < 1) {
            columnWidth = 1;
        }

        for (int i = 0; i < generalTable.getRowCount(); i++) {
            generalTable.setRowHeight(i, rowHeight);

        }

        generalTable.setShowGrid(false);
        generalTable.setIntercellSpacing(new Dimension(0, 0));

        for (int i = 0; i < generalTable.getColumnCount(); i++) {
            if (i == 0) {
                generalTable.getColumnModel().getColumn(0).setPreferredWidth(columnWidth);

            } else {
                generalTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidth);

            }
        }

        int start = -1;
        int end = -1;
        if (fileController.getGlobalDataKeeper().getPhaseCollectors() != null && wholeCol != -1 && wholeCol != 0) {
            start = fileController.getGlobalDataKeeper().getPhaseCollectors().get(0).getPhases().get(wholeCol - 1).getStartPos();
            end = fileController.getGlobalDataKeeper().getPhaseCollectors().get(0).getPhases().get(wholeCol - 1).getEndPos();
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

        // generalTable.setDefaultRenderer(Object.class, renderer);

        return generalTable;
    }
    
    
    public JvTable getTmpLifeTimeTable() {
        return tmpLifeTimeTable;
    }
    
    
    public MyTableModel getDetailedModel() {
        return detailedModel;
    }

    
    public String[] getFinalColumnsZoomArea() {
        return finalColumnsZoomArea;
    }
    
    
    public String[][] getFinalRowsZoomArea(){
        return finalRowsZoomArea;
    }
}
