package gui.actionListeners;

import gui.controllers.TableController;
import gui.mainEngine.Gui;
import gui.tableElements.commons.JvTable;
import gui.tableElements.commons.MyTableModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowFullDetailedLifetimeTableListener extends JFrame implements ActionListener {
    private Gui gui;
    private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    private TableController tableController = TableController.getInstance();

    public void listenToGui(Gui gui_p){
        this.gui=gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTableModel detailedModel = tableController.createFullDetailedLifeTableModel();
        JvTable tmpLifeTimeTable = gui.getDetailedTableToConstruct().makeDetailedTable(tableController.getIsLevelised(),detailedModel);
        gui.getTabbedPane().setSelectedIndex(0);

        gui.paintDetailedTable(true);
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
    
}
