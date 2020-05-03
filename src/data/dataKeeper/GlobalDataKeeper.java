package data.dataKeeper;

import data.dataPPL.pplSQLSchema.PPLSchema;
import data.dataPPL.pplSQLSchema.PPLTable;
import data.dataPPL.pplTransition.AtomicChange;
import data.dataPPL.pplTransition.PPLTransition;
import data.dataPPL.pplTransition.TableChange;
import data.dataProccessing.Worker;
import phaseAnalyzer.commons.PhaseCollector;
import tableClustering.clusterExtractor.commons.ClusterCollector;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JTable;

/* Refactor! Problem: Data Class */

public class GlobalDataKeeper {
    //Refactor: Implemented Singleton Pattern
    protected TreeMap<String, PPLSchema> allPPLSchemas = null; // ATTENTION::HAS BEEN CHANGED TO PROTECTED
    private TreeMap<String, PPLTable> allTables = null;
    private ArrayList<AtomicChange> atomicChanges = null;
    private TreeMap<String, TableChange> tableChanges = null;
    private TreeMap<String, TableChange> tableChangesForTwo = null;
    protected TreeMap<Integer, PPLTransition> allPPLTransitions = null;
    private ArrayList<PhaseCollector> phaseCollectors = null;
    private ArrayList<ClusterCollector> clusterCollectors = null;

    private String projectDataFolder = null;
    private String filename = null;
    private String transitionsFile = "";

    public GlobalDataKeeper(String fl, String transitionsFile) {
        allPPLSchemas = new TreeMap<String, PPLSchema>();
        allTables = new TreeMap<String, PPLTable>();
        atomicChanges = new ArrayList<AtomicChange>();
        tableChanges = new TreeMap<String, TableChange>();
        tableChangesForTwo = new TreeMap<String, TableChange>();
        allPPLTransitions = new TreeMap<Integer, PPLTransition>();
        phaseCollectors = new ArrayList<PhaseCollector>();
        clusterCollectors = new ArrayList<ClusterCollector>();
        filename = fl;
        this.transitionsFile = transitionsFile;
    }


<<<<<<< Updated upstream
=======
    public GlobalDataKeeper() {
        
    }
    
>>>>>>> Stashed changes
    public void setData() {

        Worker w = new Worker(filename, transitionsFile);
        try {
            w.work();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setAllPPLSchemas(w.getAllPPLSchemas());
        setAllPPLTables(w.getAllPPLTables());
        setAllPPLTransitions(w.getAllPPLTransitions());
        setAllTableChanges(w.getAllTableChanges());
        setAtomicChanges(w.getAtomicChanges());
        setDataFolder(w.getDataFolder());

    }

    public void setPhaseCollectors(ArrayList<PhaseCollector> phaseCollectors) {
        this.phaseCollectors = phaseCollectors;
    }

    public void setClusterCollectors(ArrayList<ClusterCollector> clusterCollectors) {
        this.clusterCollectors = clusterCollectors;
    }

    private void setAllPPLSchemas(TreeMap<String, PPLSchema> tmpAllPPLSchemas) {

        allPPLSchemas = tmpAllPPLSchemas;

    }

    private void setAllPPLTables(TreeMap<String, PPLTable> tmpAllTables) {
        allTables = tmpAllTables;

    }

    private void setAtomicChanges(ArrayList<AtomicChange> tmpAtomicChanges) {

        atomicChanges = tmpAtomicChanges;

    }

    private void setAllTableChanges(TreeMap<String, TableChange> tmpTableChanges) {

        tableChanges = tmpTableChanges;

    }

    private void setAllPPLTransitions(TreeMap<Integer, PPLTransition> tmpAllPPLTransitions) {

        allPPLTransitions = tmpAllPPLTransitions;

    }

    private void setDataFolder(String tmpProjectDataFolder) {
        projectDataFolder = tmpProjectDataFolder;
    }

    public TreeMap<String, PPLSchema> getAllPPLSchemas() {

        return allPPLSchemas;

    }

    public TreeMap<String, PPLTable> getAllPPLTables() {

        return allTables;

    }

    public ArrayList<AtomicChange> getAtomicChanges() {

        return atomicChanges;

    }

    public TreeMap<String, TableChange> getAllTableChanges() {

        return tableChanges;

    }

    public TreeMap<String, TableChange> getTmpTableChanges() {

        return tableChangesForTwo;

    }

    public TreeMap<Integer, PPLTransition> getAllPPLTransitions() {

        return allPPLTransitions;

    }

    public String getDataFolder() {
        return projectDataFolder;
    }

    public ArrayList<PhaseCollector> getPhaseCollectors() {
        return this.phaseCollectors;
    }

    public ArrayList<ClusterCollector> getClusterCollectors() {
        return this.clusterCollectors;
    }

    // Extracted methods area
    public String constructDescriptionZoomAreaColumn(JTable table, int column) {
        String description = "Transition ID:" + table.getColumnName(column) + "\n";
        description = description + "Old Version Name:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getNewVersionName() + "\n";
        description = description + "Transition Changes:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getNumberOfChangesForOneTr()
                + "\n";
        description = description + "Additions:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getNumberOfAdditionsForOneTr()
                + "\n";
        description = description + "Deletions:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getNumberOfDeletionsForOneTr()
                + "\n";
        description = description + "Updates:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getNumberOfUpdatesForOneTr()
                + "\n";
        return description;
    }

    public String constructDescriptionZoomAreaRow(int row, String finalRowsZoomAreaRowZero) {
        String description = "Table:" + finalRowsZoomAreaRowZero + "\n";
        description = description + "Birth Version Name:" + allTables.get(finalRowsZoomAreaRowZero).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(finalRowsZoomAreaRowZero).getBirthVersionID()
                + "\n";
        description = description + "Death Version Name:" + allTables.get(finalRowsZoomAreaRowZero).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(finalRowsZoomAreaRowZero).getDeathVersionID()
                + "\n";
        description = description + "Total Changes:" + allTables.get(finalRowsZoomAreaRowZero).getTotalChanges() + "\n";
        return description;
    }

    public String constructDescriptionZoomAreaCell(JTable table, int column, String finalRowsZoomAreaRowZero) {
        String description;
        description = "Table:" + finalRowsZoomAreaRowZero + "\n";

        description = description + "Old Version Name:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getNewVersionName() + "\n";

        if (allTables.get(finalRowsZoomAreaRowZero).getTableChanges()
                .getTableAtChForOneTransition(Integer.parseInt(table.getColumnName(column))) != null) {

            description = description + "Transition Changes:"
                    + allTables.get(finalRowsZoomAreaRowZero).getTableChanges()
                            .getTableAtChForOneTransition(Integer.parseInt(table.getColumnName(column))).size()
                    + "\n";
            description = description + "Additions:" + allTables.get(finalRowsZoomAreaRowZero)
                    .getNumberOfAdditionsForOneTr(Integer.parseInt(table.getColumnName(column))) + "\n";
            description = description + "Deletions:" + allTables.get(finalRowsZoomAreaRowZero)
                    .getNumberOfDeletionsForOneTr(Integer.parseInt(table.getColumnName(column))) + "\n";
            description = description + "Updates:" + allTables.get(finalRowsZoomAreaRowZero)
                    .getNumberOfUpdatesForOneTr(Integer.parseInt(table.getColumnName(column))) + "\n";

        } else {
            description = description + "Transition Changes:0" + "\n";
            description = description + "Additions:0" + "\n";
            description = description + "Deletions:0" + "\n";
            description = description + "Updates:0" + "\n";

        }
        return description;
    }

    public String constructDescriptionPLDColumns(JTable table, int column) {
        String description = table.getColumnName(column) + "\n";
        description = description + "First Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getStartPos() + "\n";
        description = description + "Last Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getEndPos() + "\n";
        description = description + "Total Changes For This Phase:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getTotalUpdates() + "\n";
        description = description + "Additions For This Phase:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getTotalAdditionsOfPhase() + "\n";
        description = description + "Deletions For This Phase:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getTotalDeletionsOfPhase() + "\n";
        description = description + "Updates For This Phase:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getTotalUpdatesOfPhase() + "\n";
        return description;
    }

    public String constructDescriptionPLDCluster(int row, String clusterName) {
        String description = "Cluster:" + clusterName + "\n";
        description = description + "Birth Version Name:"
                + clusterCollectors.get(0).getClusters().get(row).getBirthSqlFile() + "\n";
        description = description + "Birth Version ID:" + clusterCollectors.get(0).getClusters().get(row).getBirth()
                + "\n";
        description = description + "Death Version Name:"
                + clusterCollectors.get(0).getClusters().get(row).getDeathSqlFile() + "\n";
        description = description + "Death Version ID:" + clusterCollectors.get(0).getClusters().get(row).getDeath()
                + "\n";
        description = description + "Tables:"
                + clusterCollectors.get(0).getClusters().get(row).getNamesOfTables().size() + "\n";
        description = description + "Total Changes:" + clusterCollectors.get(0).getClusters().get(row).getTotalChanges()
                + "\n";
        return description;
    }

    public String constructDescriptionPLDPhases(int row, String tableName) {
        String description = "Table:" + tableName + "\n";
        description = description + "Birth Version Name:" + allTables.get(tableName).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(tableName).getBirthVersionID() + "\n";
        description = description + "Death Version Name:" + allTables.get(tableName).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(tableName).getDeathVersionID() + "\n";
        description = description + "Total Changes:" + allTables.get(tableName).getTotalChanges() + "\n";
        return description;
    }

    public String constructDescriptionPLDCell(JTable table, int row, int column, String tmpValue, String clusterID) {
        String description;
        description = clusterID + "\n";
        description = description + "Tables:"
                + clusterCollectors.get(0).getClusters().get(row).getNamesOfTables().size() + "\n\n";

        description = description + table.getColumnName(column) + "\n";
        description = description + "First Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getStartPos() + "\n";
        description = description + "Last Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getEndPos() + "\n\n";
        description = description + "Total Changes For This Phase:" + tmpValue + "\n";
        return description;
    }

    public String constructDescriptionPLDPhasesCell(JTable table, int row, int column, String tmpValue,
            String tableName) {
        String description;
        description = table.getColumnName(column) + "\n";
        description = description + "First Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getStartPos() + "\n";
        description = description + "Last Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getEndPos() + "\n\n";
        description = description + "Table:" + tableName + "\n";
        description = description + "Birth Version Name:" + allTables.get(tableName).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(tableName).getBirthVersionID() + "\n";
        description = description + "Death Version Name:" + allTables.get(tableName).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(tableName).getDeathVersionID() + "\n";
        description = description + "Total Changes For This Phase:" + tmpValue + "\n";
        return description;
    }

    public String constructDescriptionZoomAreaPhasesColumn(final String[][] rowsZoom, JTable table, int column) {
        String description = "Transition ID:" + table.getColumnName(column) + "\n";
        description = description + "Old Version Name:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getNewVersionName() + "\n";

        description = description + "Transition Changes:" + allPPLTransitions
                .get(Integer.parseInt(table.getColumnName(column))).getNumberOfClusterChangesForOneTr(rowsZoom) + "\n";
        description = description + "Additions:" + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column)))
                .getNumberOfClusterAdditionsForOneTr(rowsZoom) + "\n";
        description = description + "Deletions:" + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column)))
                .getNumberOfClusterDeletionsForOneTr(rowsZoom) + "\n";
        description = description + "Updates:" + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column)))
                .getNumberOfClusterUpdatesForOneTr(rowsZoom) + "\n";
        return description;
    }

    public String constructDescriptionZoomAreaPhasesRow(JTable table, int row, String tablaName) {
        String description = "Table:" + tablaName + "\n";
        description = description + "Birth Version Name:" + allTables.get(tablaName).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(tablaName).getBirthVersionID() + "\n";
        description = description + "Death Version Name:" + allTables.get(tablaName).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(tablaName).getDeathVersionID() + "\n";
        description = description + "Total Changes:"
                + allTables.get(tablaName).getTotalChangesForOnePhase(Integer.parseInt(table.getColumnName(1)),
                        Integer.parseInt(table.getColumnName(table.getColumnCount() - 1)))
                + "\n";
        return description;
    }

    public String constructDescriptionZoomAreaPhasesRowCell(JTable table, int row, int column, String tableName) {
        String description;
        description = "Table:" + tableName + "\n";

        description = description + "Old Version Name:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getNewVersionName() + "\n";
        if (allTables.get(tableName).getTableChanges()
                .getTableAtChForOneTransition(Integer.parseInt(table.getColumnName(column))) != null) {
            description = description + "Transition Changes:" + allTables.get(tableName).getTableChanges()
                    .getTableAtChForOneTransition(Integer.parseInt(table.getColumnName(column))).size() + "\n";
            description = description + "Additions:" + allTables.get(tableName)
                    .getNumberOfAdditionsForOneTr(Integer.parseInt(table.getColumnName(column))) + "\n";
            description = description + "Deletions:" + allTables.get(tableName)
                    .getNumberOfDeletionsForOneTr(Integer.parseInt(table.getColumnName(column))) + "\n";
            description = description + "Updates:"
                    + allTables.get(tableName).getNumberOfUpdatesForOneTr(Integer.parseInt(table.getColumnName(column)))
                    + "\n";

        } else {
            description = description + "Transition Changes:0" + "\n";
            description = description + "Additions:0" + "\n";
            description = description + "Deletions:0" + "\n";
            description = description + "Updates:0" + "\n";

        }
        return description;
    }

    public String constructDescriptionZoomAreaClusterColumn(JTable table, int column) {
        String description = table.getColumnName(column) + "\n";
        description = description + "First Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getStartPos() + "\n";
        description = description + "Last Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getEndPos() + "\n";
        description = description + "Total Changes For This Phase:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getTotalUpdates() + "\n";
        description = description + "Additions For This Phase:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getTotalAdditionsOfPhase() + "\n";
        description = description + "Deletions For This Phase:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getTotalDeletionsOfPhase() + "\n";
        description = description + "Updates For This Phase:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getTotalUpdatesOfPhase() + "\n";
        return description;
    }

    public String constructDescriptionZoomAreaClusterRow(int row, String tableName) {
        String description = "Table:" + tableName + "\n";
        description = description + "Birth Version Name:" + allTables.get(tableName).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(tableName).getBirthVersionID() + "\n";
        description = description + "Death Version Name:" + allTables.get(tableName).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(tableName).getDeathVersionID() + "\n";
        description = description + "Total Changes:" + allTables.get(tableName).getTotalChanges() + "\n";
        return description;
    }

    public String constructDescriptionZoomAreaClusterCell(JTable table, int row, int column, String tmpValue, String tableName) {
        String description;
        description = "Transition " + table.getColumnName(column) + "\n";

        description = description + "Old Version:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getOldVersionName() + "\n";
        description = description + "New Version:"
                + allPPLTransitions.get(Integer.parseInt(table.getColumnName(column))).getNewVersionName() + "\n\n";

        description = description + "Table:" + tableName + "\n";
        description = description + "Birth Version Name:" + allTables.get(tableName).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(tableName).getBirthVersionID() + "\n";
        description = description + "Death Version Name:" + allTables.get(tableName).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(tableName).getDeathVersionID() + "\n";
        description = description + "Total Changes For This Phase:" + tmpValue + "\n";
        return description;
    }
}
