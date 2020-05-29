package gui.actionListeners;

import gui.controllers.ProjectConfig;
import gui.controllers.TableController;
import gui.mainEngine.Gui;
import gui.tableComputations.GeneralTableGraphicComputation;
import gui.tableElements.tableConstructors.TableConstructionIDU;


import javax.swing.*;
import data.dataKeeper.GlobalDataKeeper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPLDListener implements ActionListener {

    private Gui gui;
    private GlobalDataKeeper globalDataKeeper;
    private ProjectConfig projectConfig = ProjectConfig.getInstance();
    
    
    public void listenToGUI(Gui gui_p){
        this.gui=gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentProject = projectConfig.getCurrentProject();
        
        if (!(currentProject == null)) {
            globalDataKeeper = projectConfig.getGlobalDataKeeper();

            TableConstructionIDU table = new TableConstructionIDU(globalDataKeeper);
            final String[] columns = table.constructColumns();
            final String[][] rows = table.constructRows();
            gui.setSegmentSizeZoomArea(table.getSegmentSize());
            System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
            System.out.println("C: " + columns.length + " R: " + rows.length);

            
            gui.setFinalColumnsZoomArea(columns);
            gui.setFinalRowsZoomArea(rows);
            gui.getTabbedPane().setSelectedIndex(0);

            // Part of generaleableIDU
            gui.defineButtonsVisibillity(true);
            
            TableController tableController = TableController.getInstance();
            gui.setZoomModel(tableController.createZoomTableModel(rows, columns));

            gui.paintGeneralTableIDU(gui.getZoomModel());
            gui.setRowHeight(GeneralTableGraphicComputation.getInstance().getRowHeight());
            gui.setColumnWidth(GeneralTableGraphicComputation.getInstance().getColumnWidth());

            gui.setFinalRowsZoomArea(tableController.getFinalRowsZoomArea());
            gui.setFinalColumnsZoomArea(tableController.getFinalColumnsZoomArea());
            
            gui.fillTree();
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }


}
