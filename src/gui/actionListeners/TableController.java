package gui.actionListeners;

import data.dataKeeper.GlobalDataKeeper;
import data.dataSorters.PldRowSorter;
import gui.tableElements.commons.JvTable;
import gui.tableElements.commons.MyTableModel;
import gui.tableElements.tableConstructors.TableConstructionAllSquaresIncluded;
<<<<<<< HEAD
import gui.tableElements.tableRenderers.IDUHeaderTableRenderer;

import javax.swing.*;
=======
import gui.tableElements.tableConstructors.TableConstructionIDU;
import gui.tableElements.tableRenderers.IDUHeaderTableRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
import java.awt.*;

public class TableController {
    private static TableController singleInstance = null;
    private FileController fileController = FileController.getInstance();
    private GlobalDataKeeper globalDataKeeper;

<<<<<<< HEAD
    private int rowHeight;
    private int columnWidth;

    private Integer[] segmentSizeZoomArea = new Integer[4];


=======
   

    private int rowHeight;
    private int columnWidth;
    private Integer[] segmentSizeZoomArea = new Integer[4];
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
    protected Integer[] segmentSizeDetailedTable = new Integer[3];
    protected MyTableModel detailedModel = null;
    private MyTableModel zoomModel = null;
    
    
    private JvTable tmpLifeTimeTable;
    private String[] finalColumnsZoomArea;
    private String[][] finalRowsZoomArea;
    private JvTable generalTable;
<<<<<<< HEAD
=======
    //private MyTableModel generalModel;
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1

    //Needed for Singleton Pattern
    private TableController() {
    
    }
<<<<<<< HEAD

=======
    
    
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
    public static TableController getInstance() {
        if(singleInstance == null) {
            singleInstance = new TableController();
        }
        return singleInstance;
    }
<<<<<<< HEAD



    
    public MyTableModel createFullDetailedLifeTableModel() {
=======
    
    
    public void createFullDetailedLifeTable(final int selectedColumn) {
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        if (!(fileController.getCurrentProject() == null)) {
            globalDataKeeper = fileController.getGlobalDataKeeper();
            TableConstructionAllSquaresIncluded table = new TableConstructionAllSquaresIncluded(globalDataKeeper);
            final String[] columns = table.constructColumns();
            final String[][] rows = table.constructRows();
            segmentSizeDetailedTable = table.getSegmentSize();
<<<<<<< HEAD
            makeDetailedTable(columns, rows, true);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return null;
        }

        return detailedModel;
    }

=======
            makeDetailedTable(columns, rows, true, selectedColumn);
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }
    
   
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
    private JvTable setTableWidth(JvTable tmpLifeTimeTable, int colIndex, int preferedWidth) {
        tmpLifeTimeTable.getColumnModel().getColumn(colIndex).setPreferredWidth(preferedWidth);
        tmpLifeTimeTable.getColumnModel().getColumn(colIndex).setMaxWidth(preferedWidth);
        tmpLifeTimeTable.getColumnModel().getColumn(colIndex).setMinWidth(preferedWidth);
        return tmpLifeTimeTable;
    }
<<<<<<< HEAD

    private JvTable makeDetailedTable(String[] columns, String[][] rows, final boolean levelized) {
=======
    
    
    private void makeDetailedTable(String[] columns, String[][] rows, final boolean levelized, final int selectedColumn) {
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        
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

<<<<<<< HEAD
        return tmpLifeTimeTable;

        /**tmpLifeTimeTable.setName("LifeTimeTable");
=======
        tmpLifeTimeTable.setName("LifeTimeTable");
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1

        tmpLifeTimeTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
<<<<<<< HEAD
                                                           boolean hasFocus, int row, int column) {
=======
                    boolean hasFocus, int row, int column) {
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
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
<<<<<<< HEAD
        });**/

    }

    public final JvTable makeGeneralTableIDU(String[][] finalRowsZoomArea_p, String[] finalColumnsZoomArea_p, int rowHeight, int columnWidth, int wholeCol) {
        finalRowsZoomArea=finalRowsZoomArea_p;
        finalColumnsZoomArea=finalColumnsZoomArea_p;

        PldRowSorter sorter = new PldRowSorter(finalRowsZoomArea_p, fileController.getGlobalDataKeeper());
=======
        });
        
    }

    
    public void createPLD(int wholeCol) {
        globalDataKeeper = fileController.getGlobalDataKeeper();
        TableConstructionIDU table = new TableConstructionIDU(globalDataKeeper);
        final String[] columns = table.constructColumns();
        final String[][] rows = table.constructRows();
        segmentSizeZoomArea = table.getSegmentSize();
        System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
        System.out.println("C: " + columns.length + " R: " + rows.length);

        finalColumnsZoomArea = columns;
        finalRowsZoomArea = rows;
        
        makeGeneralTableIDU(finalRowsZoomArea, finalColumnsZoomArea, rowHeight, columnWidth, wholeCol);
    }


    public JvTable makeGeneralTableIDU(String[][] finalRowsZoomArea, String[] finalColumnsZoomArea, int rowHeight, int columnWidth, int wholeCol) {
        PldRowSorter sorter = new PldRowSorter(finalRowsZoomArea, fileController.getGlobalDataKeeper());
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1

        finalRowsZoomArea = sorter.sortRows();

        int numberOfColumns = finalRowsZoomArea[0].length;
        int numberOfRows = finalRowsZoomArea.length;

        this.rowHeight=rowHeight;
        this.columnWidth=columnWidth;

        String[][] aelrows = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {

            aelrows[i][0] = finalRowsZoomArea[i][0];

        }

        zoomModel = new MyTableModel(finalColumnsZoomArea, aelrows);

        generalTable = new JvTable(zoomModel);
        generalTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
                generalTable.getColumnModel().getColumn(0).setPreferredWidth(columnWidth);

            } else {
                generalTable.getColumnModel().getColumn(i).setPreferredWidth(this.columnWidth);

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
<<<<<<< HEAD


        return generalTable;
    }





    public MyTableModel getZoomModel() {
        return zoomModel;
    }
=======
        this.finalRowsZoomArea = finalRowsZoomArea;
        this.finalColumnsZoomArea = finalColumnsZoomArea;
        return generalTable;
    }
    
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
    
    public JvTable getTmpLifeTimeTable() {
        return tmpLifeTimeTable;
    }
<<<<<<< HEAD
=======
    
    
    public MyTableModel getDetailedModel() {
        return detailedModel;
    }
    
    public MyTableModel getZoomModel() {
        return zoomModel;
    }
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1

    public String[] getFinalColumnsZoomArea() {
        return finalColumnsZoomArea;
    }
<<<<<<< HEAD

    public int getRowHeight() {
        return rowHeight;
    }

    public int getColumnWidth() {
        return columnWidth;
    }

    public Integer[] getSegmentSizeDetailedTable() {
        return segmentSizeDetailedTable;
    }
    
    public String[][] getFinalRowsZoomArea(){
        return finalRowsZoomArea;
    }
=======
    
    public String[][] getFinalRowsZoomArea(){
        return finalRowsZoomArea;
    }
    
    public Integer[] getSegmentSizeZoomArea() {
        return segmentSizeZoomArea;
    }
    
    public JvTable getGeneralTable() {
        return generalTable;
    }
    
    public int getRowHeight() {
        return rowHeight;
    }
    
    public int getColumnWidth() {
        return columnWidth;
    }
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
}
