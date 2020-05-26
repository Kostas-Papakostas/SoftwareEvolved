package gui.actionListeners;

import gui.controllers.ProjectConfig;
import gui.dialogs.ParametersJDialog;
import gui.mainEngine.Gui;
import gui.tableElements.tableConstructors.TableConstructionWithClusters;
import phaseAnalyzer.engine.PhaseAnalyzerMainEngine;
import tableClustering.clusterExtractor.engine.TableClusteringMainEngine;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowPhasesWithClustersPLDListener implements ActionListener {
    private Gui gui;

    public void listenToGUI(Gui gui_p){
        gui=gui_p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.setWholeCol(-1);
        if (!(ProjectConfig.getProject() == null)) {

            ParametersJDialog jD = new ParametersJDialog(true);

            jD.setModal(true);

            jD.setVisible(true);

            if (jD.getConfirmation()) {

                float timeWeight = jD.getTimeWeight();
                float changeWeight = jD.getChangeWeight();
                boolean preProcessingTime = jD.getPreProcessingTime();
                boolean preProcessingChange = jD.getPreProcessingChange();
                int numberOfPhases = jD.getNumberOfPhases();
                int numberOfClusters = jD.getNumberOfClusters();
                Double birthWeight = jD.geBirthWeight();
                Double deathWeight = jD.getDeathWeight();
                Double changeWeightCl = jD.getChangeWeightCluster();

                System.out.println(timeWeight + " " + changeWeight);

                PhaseAnalyzerMainEngine mainEngine = new PhaseAnalyzerMainEngine(gui.getProjectConfig(), timeWeight, changeWeight, preProcessingTime, preProcessingChange);

                mainEngine.parseInput();
                System.out.println("\n\n\n");
                mainEngine.extractPhases(numberOfPhases);
                /*
                 * try { mainEngine.extractReportAssessment1(); } catch (IOException e) {
                 * e.printStackTrace(); } try { mainEngine.extractReportAssessment2(); } catch
                 * (IOException e) { e.printStackTrace(); }
                 */

                mainEngine.connectTransitionsWithPhases(ProjectConfig.getGlobalDataKeeper());
                ProjectConfig.getGlobalDataKeeper().setPhaseCollectors(mainEngine.getPhaseCollectors());
                TableClusteringMainEngine mainEngine2 = new TableClusteringMainEngine(ProjectConfig.getGlobalDataKeeper(),
                        birthWeight, deathWeight, changeWeightCl);
                mainEngine2.extractClusters(numberOfClusters);
                ProjectConfig.getGlobalDataKeeper().setClusterCollectors(mainEngine2.getClusterCollectors());
                mainEngine2.print();

                if (ProjectConfig.getGlobalDataKeeper().getPhaseCollectors().size() != 0) {
                    TableConstructionWithClusters table = new TableConstructionWithClusters(ProjectConfig.getGlobalDataKeeper());
                    final String[] columns = table.constructColumns();
                    final String[][] rows = table.constructRows();
                    gui.setSegmentSize(table.getSegmentSize());
                    System.out.println("Schemas: " + ProjectConfig.getGlobalDataKeeper().getAllPPLSchemas().size());
                    System.out.println("C: " + columns.length + " R: " + rows.length);

                    gui.setFinalColumns(columns);
                    gui.setFinalRows(rows);
                    gui.getTabbedPane().setSelectedIndex(0);
                    gui.makeGeneralTablePhases();
                    gui.fillClustersTree();

                } else {
                    JOptionPane.showMessageDialog(null, "Extract Phases first");
                }
            }
        } else {

            JOptionPane.showMessageDialog(null, "Please select a project first!");

        }

    }
}
