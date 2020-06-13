package data.configurators;

import gui.tableElements.commons.MyTableModel;

public class GeneralTableConfig implements TablesInterface{
    private static GeneralTableConfig singleInstance = null;
    private ProjectConfig projectConfig = ProjectConfig.getInstance();

    private Integer[] segmentSizeZoomArea = new Integer[4];

    private boolean isLevelised = false;

    private String[] finalColumnsGeneral;
    private String[][] finalRowsGeneral;

    private MyTableModel generalModel;

    private GeneralTableConfig() {

    }

    public static GeneralTableConfig getInstance() {
        if(singleInstance == null) {
            singleInstance = new GeneralTableConfig();
        }
        return singleInstance;
    }


    public MyTableModel createTableModel(String[][] finalRowsGeneral_p, String[] finalColumnsGeneral_p){
        finalRowsGeneral=finalRowsGeneral_p;
        finalColumnsGeneral=finalColumnsGeneral_p;

        int numberOfColumns = finalRowsGeneral[0].length;
        int numberOfRows = finalRowsGeneral.length;

        /***selectedRows = new ArrayList<Integer>();****/  //don't need this here

        String[][] rows = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {

            rows[i][0] = finalRowsGeneral[i][0];

        }

        generalModel = new MyTableModel(finalColumnsGeneral, rows);
        return generalModel;
    }

}