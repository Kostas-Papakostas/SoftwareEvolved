package data.configurators;

import data.dataSorters.PldRowSorter;
import gui.tableElements.commons.MyTableModel;

public class ZoomTableConfig implements TablesInterface{
    private static ZoomTableConfig singleInstance = null;
    private boolean isLevelised = false;
    private String[] finalColumnsZoomArea;
    private String[][] finalRowsZoomArea;
    protected MyTableModel zoomModel = null;

    //Needed for Singleton Pattern
    private ZoomTableConfig() {

    }

    public static ZoomTableConfig getInstance() {
        if(singleInstance == null) {
            singleInstance = new ZoomTableConfig();
        }
        return singleInstance;
    }

    public MyTableModel createTableModel(String[][] finalRowsZoomArea_p, String[] finalColumnsZoomArea_p){
        finalRowsZoomArea = finalRowsZoomArea_p;
        finalColumnsZoomArea = finalColumnsZoomArea_p;
        PldRowSorter sorter = new PldRowSorter(finalRowsZoomArea, ProjectConfig.getGlobalDataKeeper());
        finalRowsZoomArea = sorter.sortRows();
        int numberOfColumns = finalRowsZoomArea[0].length;
        int numberOfRows = finalRowsZoomArea.length;
        String[][] tempRows = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            tempRows[i][0] = finalRowsZoomArea[i][0];
        }

        zoomModel = new MyTableModel(finalColumnsZoomArea, tempRows);
        return zoomModel;
    }

    public MyTableModel getZoomModel() {
        return zoomModel;
    }

    public String[] getFinalColumnsZoomArea() {
        return finalColumnsZoomArea;
    }

    public String[][] getFinalRowsZoomArea(){
        return finalRowsZoomArea;
    }

    public boolean getIsLevelised() {
        return isLevelised;
    }
}
