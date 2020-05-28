package gui.actionListeners;

import gui.dialogs.ParametersJDialog;
import gui.tableElements.tableConstructors.TableConstructionPhases;
import phaseAnalyzer.engine.PhaseAnalyzerMainEngine;
import java.awt.event.ActionListener;

public class ShowPhasesPLD extends PhasesTemplateListener implements ActionListener {
    private TableConstructionPhases table;

    @Override
    protected ParametersJDialog getParametersJDialog() {
        return new ParametersJDialog(false);
    }

    @Override
    protected PhaseAnalyzerMainEngine getMainEngine(ParametersJDialog jD) {
        return new PhaseAnalyzerMainEngine(projectConfig.getInputCsv(), 
                projectConfig.getOutputAssessment1(), projectConfig.getOutputAssessment2(),
                jD.getTimeWeight(), jD.getChangeWeight(), 
                jD.getPreProcessingTime(), jD.getPreProcessingChange());
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




