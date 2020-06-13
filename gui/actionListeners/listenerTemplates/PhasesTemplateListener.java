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
    protected abstract PhaseAnalyzerMainEngine getMainEngine(ParametersJDialog jD);
    protected abstract void extractClusters(ParametersJDialog jD);
    protected abstract void constructTable();
    protected abstract String[] getTableColumns();
    protected abstract String[][] getTableRows();
    protected abstract void fillTree();
    
    public void listenToGui(Gui gui_p){
        gui=gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String project = ProjectConfig.getProject();

        if (!(project == null)) {
            gui.setWholeCol(-1);
            ParametersJDialog jD = getParametersJDialog();

            jD.setModal(true);

            jD.setVisible(true);

            if (jD.getConfirmation()) {
                System.out.println(jD.getTimeWeight() + " " + jD.getChangeWeight());

                PhaseAnalyzerMainEngine mainEngine = getMainEngine(jD);

                mainEngine.parseInput();
                System.out.println("\n\n\n");
                mainEngine.extractPhases(jD.getNumberOfPhases());

                globalDataKeeper = ProjectConfig.getGlobalDataKeeper();
                mainEngine.connectTransitionsWithPhases(globalDataKeeper);

                globalDataKeeper.setPhaseCollectors(mainEngine.getPhaseCollectors());
                
                extractClusters(jD);
                
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
