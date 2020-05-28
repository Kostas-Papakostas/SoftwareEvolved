package gui.actionListeners;

import gui.dialogs.ParametersJDialog;
import gui.tableElements.tableConstructors.TableConstructionWithClusters;
import phaseAnalyzer.engine.PhaseAnalyzerMainEngine;
import tableClustering.clusterExtractor.engine.TableClusteringMainEngine;
import java.awt.event.ActionListener;

public class ShowPhasesWithClustersPLDListener extends PhasesTemplateListener implements ActionListener  {
    private TableConstructionWithClusters table;
    
    @Override
    protected ParametersJDialog getParametersJDialog() {
        return new ParametersJDialog(true);
    }

    @Override
    protected PhaseAnalyzerMainEngine getMainEngine(ParametersJDialog jD) {
        return new PhaseAnalyzerMainEngine(gui.getProjectConfig(), jD.getTimeWeight(), 
                jD.getChangeWeight(), jD.getPreProcessingTime(), jD.getPreProcessingChange());
    }

    @Override
    protected void extractClusters(ParametersJDialog jD) {
        TableClusteringMainEngine mainEngine2 = new TableClusteringMainEngine(globalDataKeeper,
                jD.geBirthWeight(), jD.getDeathWeight(), jD.getChangeWeightCluster());
        mainEngine2.extractClusters(jD.getNumberOfClusters());
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
