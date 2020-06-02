package gui.actionListeners;

import data.configurators.ProjectConfig;
import data.configurators.TableConfig;
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
            
            TableConfig tableConfig = TableConfig.getInstance();
            gui.setZoomModel(tableConfig.createZoomTableModel(rows, columns));

            gui.paintGeneralTableIDU(gui.getZoomModel());
            gui.setRowHeight(GeneralTableGraphicComputation.getInstance().getRowHeight());
            gui.setColumnWidth(GeneralTableGraphicComputation.getInstance().getColumnWidth());

            gui.setFinalRowsZoomArea(tableConfig.getFinalRowsZoomArea());
            gui.setFinalColumnsZoomArea(tableConfig.getFinalColumnsZoomArea());
            
            gui.fillTree();
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }


}
