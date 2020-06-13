package gui.actionListeners;

import gui.actionListeners.listenerTemplates.PhasesTemplateListener;
import gui.dialogs.ParametersJDialog;
import gui.tableElements.tableConstructors.TableConstructionWithClusters;
import phaseAnalyzer.engine.PhaseAnalyzerMainEngine;
import tableClustering.clusterExtractor.engine.TableClusteringMainEngine;

public class ShowPhasesWithClustersPLDListener extends PhasesTemplateListener {
    private TableConstructionWithClusters table;
    
    @Override
    protected ParametersJDialog getParametersJDialog() {
        return new ParametersJDialog(true);
    }

    @Override
    protected PhaseAnalyzerMainEngine getMainEngine(ParametersJDialog jDialog) {
        return new PhaseAnalyzerMainEngine(gui.getProjectConfig(), jDialog.getTimeWeight(), 
                jDialog.getChangeWeight(), jDialog.getPreProcessingTime(), jDialog.getPreProcessingChange());
    }

    @Override
    protected void extractClusters(ParametersJDialog jDialog) {
        TableClusteringMainEngine mainEngine2 = new TableClusteringMainEngine(globalDataKeeper,
                jDialog.geBirthWeight(), jDialog.getDeathWeight(), jDialog.getChangeWeightCluster());
        mainEngine2.extractClusters(jDialog.getNumberOfClusters());
        globalDataKeeper.setClusterCollectors(mainEngine2.getClusterCollectors());
        mainEngine2.print();
    }

    @Override
    protected void fillTree() {
        gui.fillClustersTree();
    }

    @Override
    protected void constructTable() {
        table = new TableConstructionWithClusters(globalDataKeeper);
        final String[] columns = table.constructColumns();
        final String[][] rows = table.constructRows();
        gui.setSegmentSize(table.getSegmentSize());
        System.out.println("Schemas: " + globalDataKeeper.getAllPPLSchemas().size());
        System.out.println("C: " + columns.length + " R: " + rows.length);
    }

    @Override
    protected String[] getTableColumns() {
        return table.constructColumns();
    }

    @Override
    protected String[][] getTableRows() {
        return table.constructRows();
    }
}
