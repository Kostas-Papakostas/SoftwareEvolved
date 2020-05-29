package gui.actionListeners;

import gui.controllers.TableController;
import gui.mainEngine.Gui;
import gui.tableElements.commons.JvTable;
import gui.tableElements.commons.MyTableModel;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowFullDetailedLifetimeTableListener extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private Gui gui;
    private TableController tableController = TableController.getInstance();

    public void listenToGui(Gui gui_p){
        this.gui=gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTableModel detailedModel = tableController.createFullDetailedLifeTableModel();
        JvTable tmpLifeTimeTable = gui.getDetailedTableToConstruct().makeDetailedTable(tableController.getIsLevelised(),detailedModel);
        gui.getTabbedPane().setSelectedIndex(0);

        paintDetailedTable(true);
        tmpLifeTimeTable.setOpaque(true);

        tmpLifeTimeTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tmpLifeTimeTable.getSelectionModel().addListSelectionListener(new Gui.RowListener()); /*****make rowlistener static*****/
        tmpLifeTimeTable.getColumnModel().getSelectionModel().addListSelectionListener(new Gui.ColumnListener());  /*****make columnlistener static*****/

        JScrollPane detailedScrollPane = new JScrollPane();
        detailedScrollPane.setViewportView(tmpLifeTimeTable);
        detailedScrollPane.setAlignmentX(0);
        detailedScrollPane.setAlignmentY(0);
        detailedScrollPane.setBounds(0, 0, 1280, 650);
        detailedScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        detailedScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        detailedScrollPane.setCursor(getCursor());

        JDialog detailedDialog = new JDialog();
        detailedDialog.setBounds(100, 100, 1300, 700);

        JPanel panelToAdd = new JPanel();

        GroupLayout gl_panel = new GroupLayout(panelToAdd);
        gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING));
        gl_panel.setVerticalGroup(gl_panel.createParallelGroup(GroupLayout.Alignment.LEADING));
        panelToAdd.setLayout(gl_panel);

        panelToAdd.add(detailedScrollPane);
        detailedDialog.getContentPane().add(panelToAdd);
        detailedDialog.setVisible(true);
    }
    
    private void paintDetailedTable(final boolean levelized) {
        JvTable tmpLifeTimeTable = gui.detailedTableToConstruct.getTmpLifeTimeTable();
        tmpLifeTimeTable.setName("LifeTimeTable");
        gui.setSegmentSizeDetailedTable(tableController.getSegmentSizeDetailedTable());
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

                if (gui.getSelectedColumn() == 0) {
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
                    Integer[] segmentSizeDetailedTable = gui.getSegmentSizeDetailedTable();
                    
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
        gui.setTmpLifeTimeTable(tmpLifeTimeTable);
    }

}
