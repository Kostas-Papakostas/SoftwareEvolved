package gui.actionListeners.listenerTemplates;

import data.configurators.ProjectConfig;
import data.dataKeeper.GlobalDataKeeper;
import gui.dialogs.ParametersJDialog;
import gui.mainEngine.Gui;
import phaseAnalyzer.engine.PhaseAnalyzerMainEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PhasesTemplateListener implements ActionListener {
    protected Gui gui;
    protected GlobalDataKeeper globalDataKeeper;
    protected ProjectConfig projectConfig = ProjectConfig.getInstance();
    
    protected abstract ParametersJDialog getParametersJDialog();
    protected abstract PhaseAnalyzerMainEngine getMainEngine(ParametersJDialog jDialog);
    protected abstract void extractClusters(ParametersJDialog jDialog);
    protected abstract void constructTable();
    protected abstract String[] getTableColumns();
    protected abstract String[][] getTableRows();
    protected abstract void fillTree();
    
    public void listenToGui(Gui gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String project = ProjectConfig.getProject();

        if (!(project == null)) {
            gui.setWholeCol(-1);
            ParametersJDialog jDialog = getParametersJDialog();

            jDialog.setModal(true);

            jDialog.setVisible(true);

            if (jDialog.getConfirmation()) {
                System.out.println(jDialog.getTimeWeight() + " " + jDialog.getChangeWeight());

                PhaseAnalyzerMainEngine mainEngine = getMainEngine(jDialog);

                mainEngine.parseInput();
                System.out.println("\n\n\n");
                mainEngine.extractPhases(jDialog.getNumberOfPhases());

                globalDataKeeper = ProjectConfig.getGlobalDataKeeper();
                mainEngine.connectTransitionsWithPhases(globalDataKeeper);

                globalDataKeeper.setPhaseCollectors(mainEngine.getPhaseCollectors());
                
                extractClusters(jDialog);
                
                if (globalDataKeeper.getPhaseCollectors().size() != 0) {
                    constructTable();
                    String[] columns = getTableColumns();
                    String[][] rows = getTableRows();
                    
                    gui.setFinalColumns(columns);
                    gui.setFinalRows(rows);
                    gui.getTabbedPane().setSelectedIndex(0);
                    gui.makeGeneralTablePhases();
                    fillTree();
                } else {
                    JOptionPane.showMessageDialog(null, "Extract Phases first");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select a project first!");
        }
    }

}
