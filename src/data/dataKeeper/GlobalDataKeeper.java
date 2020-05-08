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


    public GlobalDataKeeper() {
        
    }
    
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
<<<<<<< HEAD
    public String constructDescriptionZoomAreaColumn(String tableName) {
        String description = "Transition ID:" + tableName + "\n";
        description = description + "Old Version Name:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getNewVersionName() + "\n";
        description = description + "Transition Changes:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getNumberOfChangesForOneTr()
                + "\n";
        description = description + "Additions:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getNumberOfAdditionsForOneTr()
                + "\n";
        description = description + "Deletions:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getNumberOfDeletionsForOneTr()
                + "\n";
        description = description + "Updates:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getNumberOfUpdatesForOneTr()
=======
    public String constructDescriptionZoomAreaColumn(String columnName) {
        String description = "Transition ID:" + columnName + "\n";
        description = description + "Old Version Name:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getNewVersionName() + "\n";
        description = description + "Transition Changes:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getNumberOfChangesForOneTr()
                + "\n";
        description = description + "Additions:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getNumberOfAdditionsForOneTr()
                + "\n";
        description = description + "Deletions:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getNumberOfDeletionsForOneTr()
                + "\n";
        description = description + "Updates:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getNumberOfUpdatesForOneTr()
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
                + "\n";
        return description;
    }

<<<<<<< HEAD
    public String constructDescriptionZoomAreaRow( String finalRowsZoomAreaRowZero) {
=======
    public String constructDescriptionZoomAreaRow(String finalRowsZoomAreaRowZero) {
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
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

<<<<<<< HEAD
    public String constructDescriptionZoomAreaCell(String tableName, String finalRowsZoomAreaRowZero) {
=======
    public String constructDescriptionZoomAreaCell(String columnName, String finalRowsZoomAreaRowZero) {
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        String description;
        description = "Table:" + finalRowsZoomAreaRowZero + "\n";

        description = description + "Old Version Name:"
<<<<<<< HEAD
                + allPPLTransitions.get(Integer.parseInt(tableName)).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getNewVersionName() + "\n";

        if (allTables.get(finalRowsZoomAreaRowZero).getTableChanges()
                .getTableAtChForOneTransition(Integer.parseInt(tableName)) != null) {

            description = description + "Transition Changes:"
                    + allTables.get(finalRowsZoomAreaRowZero).getTableChanges()
                            .getTableAtChForOneTransition(Integer.parseInt(tableName)).size()
                    + "\n";
            description = description + "Additions:" + allTables.get(finalRowsZoomAreaRowZero)
                    .getNumberOfAdditionsForOneTr(Integer.parseInt(tableName)) + "\n";
            description = description + "Deletions:" + allTables.get(finalRowsZoomAreaRowZero)
                    .getNumberOfDeletionsForOneTr(Integer.parseInt(tableName)) + "\n";
            description = description + "Updates:" + allTables.get(finalRowsZoomAreaRowZero)
                    .getNumberOfUpdatesForOneTr(Integer.parseInt(tableName)) + "\n";
=======
                + allPPLTransitions.get(Integer.parseInt(columnName)).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getNewVersionName() + "\n";

        if (allTables.get(finalRowsZoomAreaRowZero).getTableChanges()
                .getTableAtChForOneTransition(Integer.parseInt(columnName)) != null) {

            description = description + "Transition Changes:"
                    + allTables.get(finalRowsZoomAreaRowZero).getTableChanges()
                            .getTableAtChForOneTransition(Integer.parseInt(columnName)).size()
                    + "\n";
            description = description + "Additions:" + allTables.get(finalRowsZoomAreaRowZero)
                    .getNumberOfAdditionsForOneTr(Integer.parseInt(columnName)) + "\n";
            description = description + "Deletions:" + allTables.get(finalRowsZoomAreaRowZero)
                    .getNumberOfDeletionsForOneTr(Integer.parseInt(columnName)) + "\n";
            description = description + "Updates:" + allTables.get(finalRowsZoomAreaRowZero)
                    .getNumberOfUpdatesForOneTr(Integer.parseInt(columnName)) + "\n";
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1

        } else {
            description = description + "Transition Changes:0" + "\n";
            description = description + "Additions:0" + "\n";
            description = description + "Deletions:0" + "\n";
            description = description + "Updates:0" + "\n";

        }
        return description;
    }

<<<<<<< HEAD
    public String constructDescriptionPLDColumns(String tableName, int column) {
        String description = tableName + "\n";
=======
    public String constructDescriptionPLDColumns(String columnName, int column) {
        String description = columnName + "\n";
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
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

    public String constructDescriptionPLDPhases(String tableName) {
        String description = "Table:" + tableName + "\n";
        description = description + "Birth Version Name:" + allTables.get(tableName).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(tableName).getBirthVersionID() + "\n";
        description = description + "Death Version Name:" + allTables.get(tableName).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(tableName).getDeathVersionID() + "\n";
        description = description + "Total Changes:" + allTables.get(tableName).getTotalChanges() + "\n";
        return description;
    }

<<<<<<< HEAD
    public String constructDescriptionPLDCell(String tableName, int row, int column, String tmpValue, String clusterID) {
=======
    public String constructDescriptionPLDCell(String columnName, int row, int column, String tmpValue, String clusterID) {
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        String description;
        description = clusterID + "\n";
        description = description + "Tables:"
                + clusterCollectors.get(0).getClusters().get(row).getNamesOfTables().size() + "\n\n";

<<<<<<< HEAD
        description = description + tableName + "\n";
=======
        description = description + columnName + "\n";
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        description = description + "First Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getStartPos() + "\n";
        description = description + "Last Transition ID:"
                + phaseCollectors.get(0).getPhases().get(column - 1).getEndPos() + "\n\n";
        description = description + "Total Changes For This Phase:" + tmpValue + "\n";
        return description;
    }

<<<<<<< HEAD
    public String constructDescriptionPLDPhasesCell(String tableName, int column, String tmpValue) {
        String description;
        description = tableName + "\n";
=======
    public String constructDescriptionPLDPhasesCell(String columnName, int row, int column, String tmpValue,
            String tableName) {
        String description;
        description = columnName + "\n";
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
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

<<<<<<< HEAD
    public String constructDescriptionZoomAreaPhasesColumn(final String[][] rowsZoom, String tableName, int column) {
        String description = "Transition ID:" + tableName + "\n";
        description = description + "Old Version Name:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getNewVersionName() + "\n";

        description = description + "Transition Changes:" + allPPLTransitions
                .get(Integer.parseInt(tableName)).getNumberOfClusterChangesForOneTr(rowsZoom) + "\n";
        description = description + "Additions:" + allPPLTransitions.get(Integer.parseInt(tableName))
                .getNumberOfClusterAdditionsForOneTr(rowsZoom) + "\n";
        description = description + "Deletions:" + allPPLTransitions.get(Integer.parseInt(tableName))
                .getNumberOfClusterDeletionsForOneTr(rowsZoom) + "\n";
        description = description + "Updates:" + allPPLTransitions.get(Integer.parseInt(tableName))
=======
    public String constructDescriptionZoomAreaPhasesColumn(final String[][] rowsZoom, String columnName) {
        String description = "Transition ID:" + columnName + "\n";
        description = description + "Old Version Name:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getNewVersionName() + "\n";

        description = description + "Transition Changes:" + allPPLTransitions
                .get(Integer.parseInt(columnName)).getNumberOfClusterChangesForOneTr(rowsZoom) + "\n";
        description = description + "Additions:" + allPPLTransitions.get(Integer.parseInt(columnName))
                .getNumberOfClusterAdditionsForOneTr(rowsZoom) + "\n";
        description = description + "Deletions:" + allPPLTransitions.get(Integer.parseInt(columnName))
                .getNumberOfClusterDeletionsForOneTr(rowsZoom) + "\n";
        description = description + "Updates:" + allPPLTransitions.get(Integer.parseInt(columnName))
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
                .getNumberOfClusterUpdatesForOneTr(rowsZoom) + "\n";
        return description;
    }

<<<<<<< HEAD
    public String constructDescriptionZoomAreaPhasesRow(String tablaName, String totalChanges, String changes) {
=======
    public String constructDescriptionZoomAreaPhasesRow(String columnName0, String columnName1, int row, String tablaName) {
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        String description = "Table:" + tablaName + "\n";
        description = description + "Birth Version Name:" + allTables.get(tablaName).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(tablaName).getBirthVersionID() + "\n";
        description = description + "Death Version Name:" + allTables.get(tablaName).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(tablaName).getDeathVersionID() + "\n";
        description = description + "Total Changes:"
<<<<<<< HEAD
                + allTables.get(tablaName).getTotalChangesForOnePhase(Integer.parseInt(totalChanges), //columnname 1
                        Integer.parseInt(changes)) //column count -1
=======
                + allTables.get(tablaName).getTotalChangesForOnePhase(Integer.parseInt(columnName0),
                        Integer.parseInt(columnName1))
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
                + "\n";
        return description;
    }

<<<<<<< HEAD
    public String constructDescriptionZoomAreaPhasesRowCell(String tableName) {
=======
    public String constructDescriptionZoomAreaPhasesRowCell(String columnName, int row, String tableName) {
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
        String description;
        description = "Table:" + tableName + "\n";

        description = description + "Old Version Name:"
<<<<<<< HEAD
                + allPPLTransitions.get(Integer.parseInt(tableName)).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(tableName)).getNewVersionName() + "\n";
        if (allTables.get(tableName).getTableChanges()
                .getTableAtChForOneTransition(Integer.parseInt(tableName)) != null) {
            description = description + "Transition Changes:" + allTables.get(tableName).getTableChanges()
                    .getTableAtChForOneTransition(Integer.parseInt(tableName)).size() + "\n";
            description = description + "Additions:" + allTables.get(tableName)
                    .getNumberOfAdditionsForOneTr(Integer.parseInt(tableName)) + "\n";
            description = description + "Deletions:" + allTables.get(tableName)
                    .getNumberOfDeletionsForOneTr(Integer.parseInt(tableName)) + "\n";
            description = description + "Updates:"
                    + allTables.get(tableName).getNumberOfUpdatesForOneTr(Integer.parseInt(tableName))
=======
                + allPPLTransitions.get(Integer.parseInt(columnName)).getOldVersionName() + "\n";
        description = description + "New Version Name:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getNewVersionName() + "\n";
        if (allTables.get(tableName).getTableChanges()
                .getTableAtChForOneTransition(Integer.parseInt(columnName)) != null) {
            description = description + "Transition Changes:" + allTables.get(tableName).getTableChanges()
                    .getTableAtChForOneTransition(Integer.parseInt(columnName)).size() + "\n";
            description = description + "Additions:" + allTables.get(tableName)
                    .getNumberOfAdditionsForOneTr(Integer.parseInt(columnName)) + "\n";
            description = description + "Deletions:" + allTables.get(tableName)
                    .getNumberOfDeletionsForOneTr(Integer.parseInt(columnName)) + "\n";
            description = description + "Updates:"
                    + allTables.get(tableName).getNumberOfUpdatesForOneTr(Integer.parseInt(columnName))
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
                    + "\n";

        } else {
            description = description + "Transition Changes:0" + "\n";
            description = description + "Additions:0" + "\n";
            description = description + "Deletions:0" + "\n";
            description = description + "Updates:0" + "\n";

        }
        return description;
    }

<<<<<<< HEAD
    public String constructDescriptionZoomAreaClusterColumn(String tableName, int column) {
        String description = tableName + "\n";
=======
    public String constructDescriptionZoomAreaClusterColumn(String columnName, int column) {
        String description = columnName + "\n";
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1
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

    public String constructDescriptionZoomAreaClusterRow(String tableName) {
        String description = "Table:" + tableName + "\n";
        description = description + "Birth Version Name:" + allTables.get(tableName).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(tableName).getBirthVersionID() + "\n";
        description = description + "Death Version Name:" + allTables.get(tableName).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(tableName).getDeathVersionID() + "\n";
        description = description + "Total Changes:" + allTables.get(tableName).getTotalChanges() + "\n";
        return description;
    }

<<<<<<< HEAD
    public String constructDescriptionZoomAreaClusterCell(String transitionName, String tmpValue, String tableName) {
        String description;
        description = "Transition " + transitionName + "\n";

        description = description + "Old Version:"
                + allPPLTransitions.get(Integer.parseInt(transitionName)).getOldVersionName() + "\n";
        description = description + "New Version:"
                + allPPLTransitions.get(Integer.parseInt(transitionName)).getNewVersionName() + "\n\n";
=======
    public String constructDescriptionZoomAreaClusterCell(String columnName, int row, String tmpValue, String tableName) {
        String description;
        description = "Transition " + columnName + "\n";

        description = description + "Old Version:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getOldVersionName() + "\n";
        description = description + "New Version:"
                + allPPLTransitions.get(Integer.parseInt(columnName)).getNewVersionName() + "\n\n";
>>>>>>> ece5d8ff50f0f1a475864c155ce217e095eca3d1

        description = description + "Table:" + tableName + "\n";
        description = description + "Birth Version Name:" + allTables.get(tableName).getBirth() + "\n";
        description = description + "Birth Version ID:" + allTables.get(tableName).getBirthVersionID() + "\n";
        description = description + "Death Version Name:" + allTables.get(tableName).getDeath() + "\n";
        description = description + "Death Version ID:" + allTables.get(tableName).getDeathVersionID() + "\n";
        description = description + "Total Changes For This Phase:" + tmpValue + "\n";
        return description;
    }
}
