package gui.actionListeners;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import data.dataKeeper.GlobalDataKeeper;
import data.dataSorters.PldRowSorter;
import gui.mainEngine.Gui;
import gui.tableElements.commons.JvTable;
import gui.tableElements.commons.MyTableModel;
import gui.tableElements.tableConstructors.TableConstructionAllSquaresIncluded;
import gui.tableElements.tableConstructors.TableConstructionIDU;
import gui.tableElements.tableRenderers.IDUHeaderTableRenderer;
import gui.tableElements.tableRenderers.IDUTableRenderer;

public class TableController {
    private static TableController singleInstance = null;
    private FileController fileController = FileController.getInstance();
    //moved
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
            GlobalDataKeeper globalDataKeeper = fileController.getGlobalDataKeeper();
            TableConstructionAllSquaresIncluded table = new TableConstructionAllSquaresIncluded(globalDataKeeper);
            final String[] columns = table.constructColumns();
            final String[][] rows = table.constructRows();
            segmentSizeDetailedTable = table.getSegmentSize();
            
            //This is not needed here
            //tabbedPane.setSelectedIndex(0);
            
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

    
    
    /*TODO move it
    public void createPLD() {
        GlobalDataKeeper globalDataKeeper = fileController.getGlobalDataKeeper();
        TableConstructionIDU table = new TableConstructionIDU(globalDataKeeper);
        final String[] columns = table.constructColumns();
        final String[][] rows = table.constructRows();
        segmentSizeZoomArea = table.getSegmentSize();
        System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
        System.out.println("C: " + columns.length + " R: " + rows.length);

        finalColumnsZoomArea = columns;
        finalRowsZoomArea = rows;
        
        makeGeneralTableIDU(globalDataKeeper);
    }
    
    
    private void makeGeneralTableIDU(final GlobalDataKeeper globalDataKeeper) {

        PldRowSorter sorter = new PldRowSorter(finalRowsZoomArea, globalDataKeeper);

        finalRowsZoomArea = sorter.sortRows();
        
        //this shit too
        showingPld = true;
        zoomInButton.setVisible(true);
        zoomOutButton.setVisible(true);
        showThisToPopup.setVisible(true);
        selectedRows = new ArrayList<Integer>();

        int numberOfColumns = finalRowsZoomArea[0].length;
        int numberOfRows = finalRowsZoomArea.length;

        

        String[][] rows = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {

            rows[i][0] = finalRowsZoomArea[i][0];

        }

        zoomModel = new MyTableModel(finalColumnsZoomArea, rows);

        final JvTable generalTable = new JvTable(zoomModel);

        generalTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
        if (globalDataKeeper.getPhaseCollectors() != null && wholeCol != -1 && wholeCol != 0) {
            start = globalDataKeeper.getPhaseCollectors().get(0).getPhases().get(wholeCol - 1).getStartPos();
            end = globalDataKeeper.getPhaseCollectors().get(0).getPhases().get(wholeCol - 1).getEndPos();
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

        final IDUTableRenderer renderer = new IDUTableRenderer(Gui.this, finalRowsZoomArea, globalDataKeeper,
                segmentSize);
        // generalTable.setDefaultRenderer(Object.class, renderer);

        generalTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                        column);

                String tmpValue = finalRowsZoomArea[row][column];
                String columnName = table.getColumnName(column);
                Color fr = new Color(0, 0, 0);

                c.setForeground(fr);
                setOpaque(true);

                if (column == wholeColZoomArea && wholeColZoomArea != 0) {

                    // Refactored: EXTRACT METHOD and MOVE METHOD
                    String description = globalDataKeeper.constructDescriptionZoomAreaColumn(table, column);

                    descriptionText.setText(description);

                    Color cl = new Color(255, 69, 0, 100);

                    c.setBackground(cl);
                    return c;
                }
                
                else if (selectedColumnZoomArea == 0) {

                    if (isSelected) {
                        Color cl = new Color(255, 69, 0, 100);
                        c.setBackground(cl);

                        // Refactored: EXTRACT METHOD and MOVE METHOD
                        String description = globalDataKeeper.constructDescriptionZoomAreaRow(row,
                                finalRowsZoomArea[row][0]);

                        descriptionText.setText(description);

                        return c;

                    }
                } else {

                    if (selectedFromTree.contains(finalRowsZoomArea[row][0])) {

                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);

                        return c;
                    }

                    if (isSelected && hasFocus) {

                        String description = "";
                        if (!table.getColumnName(column).contains("Table name")) {
                            
                            // Refactored: EXTRACT METHOD and MOVE METHOD
                            description = globalDataKeeper.constructDescriptionZoomAreaCell(table, column, finalRowsZoomArea[row][0]);
                            descriptionText.setText(description);
                        }
                        Color cl = new Color(255, 69, 0, 100);

                        c.setBackground(cl);

                        return c;
                    }

                }

                try {
                    int numericValue = Integer.parseInt(tmpValue);
                    Color insersionColor = null;
                    setToolTipText(Integer.toString(numericValue));

                    if (numericValue == 0) {
                        insersionColor = new Color(154, 205, 50, 200);
                    } else if (numericValue > 0 && numericValue <= segmentSizeZoomArea[3]) {

                        insersionColor = new Color(176, 226, 255);
                    } else if (numericValue > segmentSizeZoomArea[3] && numericValue <= 2 * segmentSizeZoomArea[3]) {
                        insersionColor = new Color(92, 172, 238);
                    } else if (numericValue > 2 * segmentSizeZoomArea[3]
                            && numericValue <= 3 * segmentSizeZoomArea[3]) {

                        insersionColor = new Color(28, 134, 238);
                    } else {
                        insersionColor = new Color(16, 78, 139);
                    }
                    c.setBackground(insersionColor);

                    return c;
                } catch (Exception e) {

                    if (tmpValue.equals("")) {
                        c.setBackground(Color.GRAY);
                        return c;
                    } else {
                        if (columnName.contains("v")) {
                            c.setBackground(Color.lightGray);
                            setToolTipText(columnName);
                        } else {
                            Color tableNameColor = new Color(205, 175, 149);
                            c.setBackground(tableNameColor);
                        }
                        return c;
                    }

                }
            }

        });

        generalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();

                    selectedRowsFromMouse = target.getSelectedRows();
                    selectedColumnZoomArea = target.getSelectedColumn();
                    renderer.setSelCol(selectedColumnZoomArea);
                    target.getSelectedColumns();

                    zoomAreaTable.repaint();
                }

            }
        });

        generalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {

                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click");

                    JTable target1 = (JTable) e.getSource();
                    target1.getSelectedColumns();
                    selectedRowsFromMouse = target1.getSelectedRows();
                    System.out.println(target1.getSelectedColumns().length);
                    System.out.println(target1.getSelectedRow());
                    for (int rowsSelected = 0; rowsSelected < selectedRowsFromMouse.length; rowsSelected++) {
                        System.out.println(generalTable.getValueAt(selectedRowsFromMouse[rowsSelected], 0));
                    }
                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Clear Selection");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            selectedFromTree = new ArrayList<String>();
                            zoomAreaTable.repaint();
                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(generalTable, e.getX(), e.getY());

                }

            }
        });

        generalTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                wholeColZoomArea = generalTable.columnAtPoint(e.getPoint());
                renderer.setWholeCol(generalTable.columnAtPoint(e.getPoint()));
                // String name = generalTable.getColumnName(wholeColZoomArea);
                // System.out.println("Column index selected " + wholeColZoomArea + " " + name);
                generalTable.repaint();
            }
        });

        generalTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    System.out.println("Right Click");

                    final JPopupMenu popupMenu = new JPopupMenu();
                    JMenuItem showDetailsItem = new JMenuItem("Clear Column Selection");
                    showDetailsItem.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            wholeColZoomArea = -1;
                            renderer.setWholeCol(wholeColZoomArea);

                            generalTable.repaint();
                        }
                    });
                    popupMenu.add(showDetailsItem);
                    popupMenu.show(generalTable, e.getX(), e.getY());

                }

            }

        });
        
        //stays back
        zoomAreaTable = generalTable;
        tmpScrollPaneZoomArea.setViewportView(zoomAreaTable);
        tmpScrollPaneZoomArea.setAlignmentX(0);
        tmpScrollPaneZoomArea.setAlignmentY(0);
        tmpScrollPaneZoomArea.setBounds(300, 300, 950, 250);
        tmpScrollPaneZoomArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        tmpScrollPaneZoomArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        lifeTimePanel.setCursor(getCursor());
        lifeTimePanel.add(tmpScrollPaneZoomArea);

    }
    */
    
    
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
