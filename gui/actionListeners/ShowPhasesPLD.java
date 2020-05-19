package gui.actionListeners;

import data.dataKeeper.GlobalDataKeeper;
import gui.controllers.FileController;
import gui.dialogs.ParametersJDialog;
import gui.mainEngine.Gui;
import gui.tableElements.tableConstructors.TableConstructionPhases;
import phaseAnalyzer.engine.PhaseAnalyzerMainEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPhasesPLD implements ActionListener {
    private Gui gui;
    private GlobalDataKeeper globalDataKeeper;

    public void listenToGui(Gui gui_p){
        this.gui = gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String project = FileController.getProject();

        System.out.println("rrrrrank1 "+ project);
        if (!(project == null)) {
            gui.setWholeCol(-1);
            ParametersJDialog jD = new ParametersJDialog(false);

            jD.setModal(true);

            jD.setVisible(true);

            if (jD.getConfirmation()) {
                float timeWeight = jD.getTimeWeight();
                float changeWeight = jD.getChangeWeight();
                boolean preProcessingTime = jD.getPreProcessingTime();
                boolean preProcessingChange = jD.getPreProcessingChange();
                int numberOfPhases = jD.getNumberOfPhases();

                System.out.println(jD.getTimeWeight() + " " + jD.getChangeWeight());

                PhaseAnalyzerMainEngine mainEngine = new PhaseAnalyzerMainEngine(gui.getInputCsv(), gui.getOutputAssessment1(),
                        gui.getOutputAssessment2(), jD.getTimeWeight(), jD.getChangeWeight(), jD.getPreProcessingTime(), jD.getPreProcessingChange());

                mainEngine.parseInput();

                System.out.println("\n\n\n");
                mainEngine.extractPhases(jD.getNumberOfPhases());

                globalDataKeeper = FileController.getGlobalDataKeeper();
                mainEngine.connectTransitionsWithPhases(globalDataKeeper);

                globalDataKeeper.setPhaseCollectors(mainEngine.getPhaseCollectors());

                if (globalDataKeeper.getPhaseCollectors().size() != 0) {
                    TableConstructionPhases table = new TableConstructionPhases(globalDataKeeper);
                    final String[] columns = table.constructColumns();
                    final String[][] rows = table.constructRows();
                    gui.setSegmentSize(table.getSegmentSize());
                    System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
                    System.out.println("C: " + columns.length + " R: " + rows.length);

                    gui.setFinalColumns(columns);
                    gui.setFinalRows(rows);

                    gui.getTabbedPane().setSelectedIndex(0);
                    gui.makeGeneralTablePhases();
                    gui.fillPhasesTree();
                } else {
                    JOptionPane.showMessageDialog(null, "Extract Phases first");
                }
            }
        } else {

            JOptionPane.showMessageDialog(null, "Please select a project first!");

        }

    }
}
