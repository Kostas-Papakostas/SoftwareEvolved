<<<<<<< HEAD
package gui.actionListeners;

import data.configurators.ProjectConfigurator;
import data.configurators.ZoomTableConfigurator;
import data.dataKeeper.GlobalDataKeeper;
import gui.mainEngine.Gui;
import gui.tableComputations.GeneralTableGraphicComputation;
import gui.tableElements.tableConstructors.TableConstructionIDU;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPLDListener implements ActionListener {
    private Gui gui;
    private GlobalDataKeeper globalDataKeeper;
    private ProjectConfigurator projectConfig = ProjectConfigurator.getInstance();
    
    public void listenToGUI(Gui gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentProject = projectConfig.getCurrentProject();
        if (!(currentProject == null)) {
            globalDataKeeper = ProjectConfigurator.getGlobalDataKeeper();
            TableConstructionIDU table = new TableConstructionIDU(globalDataKeeper);
            final String[] columns = table.constructColumns();
            final String[][] rows = table.constructRows();
            gui.setSegmentSizeZoomArea(table.getSegmentSize());
            System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
            System.out.println("C: " + columns.length + " R: " + rows.length);

            gui.setFinalColumnsZoomArea(columns);
            gui.setFinalRowsZoomArea(rows);
            gui.getTabbedPane().setSelectedIndex(0);
            gui.defineButtonsVisibillity(true);
            ZoomTableConfigurator zoomTableConfig = ZoomTableConfigurator.getInstance();
            gui.setZoomModel(zoomTableConfig.createTableModel(rows, columns));
            gui.paintGeneralTableIDU(gui.getZoomModel());
            gui.setRowHeight(GeneralTableGraphicComputation.getInstance().getRowHeight());
            gui.setColumnWidth(GeneralTableGraphicComputation.getInstance().getColumnWidth());
            gui.setFinalRowsZoomArea(zoomTableConfig.getFinalRowsZoomArea());
            gui.setFinalColumnsZoomArea(zoomTableConfig.getFinalColumnsZoomArea());
            gui.fillTree();
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }


}
=======
package gui.actionListeners;

import data.configurators.ProjectConfigurator;
import data.configurators.ZoomTableConfigurator;
import data.dataKeeper.GlobalDataKeeper;
import gui.mainEngine.Gui;
import gui.tableComputations.GeneralTableGraphicComputation;
import gui.tableElements.tableConstructors.TableConstructionIDU;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPLDListener implements ActionListener {
    private Gui gui;
    private GlobalDataKeeper globalDataKeeper;
    private ProjectConfigurator projectConfig = ProjectConfigurator.getInstance();
    
    public void listenToGUI(Gui gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String currentProject = projectConfig.getCurrentProject();
        if (!(currentProject == null)) {
            globalDataKeeper = ProjectConfigurator.getGlobalDataKeeper();
            TableConstructionIDU table = new TableConstructionIDU(globalDataKeeper);
            final String[] columns = table.constructColumns();
            final String[][] rows = table.constructRows();
            gui.setSegmentSizeZoomArea(table.getSegmentSize());
            System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
            System.out.println("C: " + columns.length + " R: " + rows.length);

            gui.setFinalColumnsZoomArea(columns);
            gui.setFinalRowsZoomArea(rows);
            gui.getTabbedPane().setSelectedIndex(0);
            gui.defineButtonsVisibillity(true);
            ZoomTableConfigurator zoomTableConfig = ZoomTableConfigurator.getInstance();
            gui.setZoomModel(zoomTableConfig.createTableModel(rows, columns));
            gui.paintGeneralTableIDU(gui.getZoomModel());
            gui.setRowHeight(GeneralTableGraphicComputation.getInstance().getRowHeight());
            gui.setColumnWidth(GeneralTableGraphicComputation.getInstance().getColumnWidth());
            gui.setFinalRowsZoomArea(zoomTableConfig.getFinalRowsZoomArea());
            gui.setFinalColumnsZoomArea(zoomTableConfig.getFinalColumnsZoomArea());
            gui.fillTree();
        } else {
            JOptionPane.showMessageDialog(null, "Select a Project first");
            return;
        }
    }


}
>>>>>>> 1b15a71283cd26ef5125c42bcd399b76927e116a
