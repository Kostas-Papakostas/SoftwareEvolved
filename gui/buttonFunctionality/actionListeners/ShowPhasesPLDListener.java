package gui.buttonFunctionality.actionListeners;

import gui.buttonFunctionality.listenerTemplates.PhasesTemplateListener;
import gui.dialogs.ParametersJDialog;
import gui.tableElements.tableConstructors.TableConstructionPhases;
import phaseAnalyzer.engine.PhaseAnalyzerMainEngine;

public class ShowPhasesPLDListener extends PhasesTemplateListener {
    private TableConstructionPhases table;

    @Override
    protected ParametersJDialog getParametersJDialog() {
        return new ParametersJDialog(false);
    }

    @Override
    protected PhaseAnalyzerMainEngine getMainEngine(ParametersJDialog jDialog) {
        return new PhaseAnalyzerMainEngine(projectConfig,
                jDialog.getTimeWeight(), jDialog.getChangeWeight(), 
                jDialog.getPreProcessingTime(), jDialog.getPreProcessingChange());
    }

    @Override
    protected void extractClusters(ParametersJDialog jD) {
        
    }

    @Override
    protected void fillTree() {
        gui.fillPhasesTree();
    }

    @Override
    protected void constructTable() {
        table = new TableConstructionPhases(globalDataKeeper);
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