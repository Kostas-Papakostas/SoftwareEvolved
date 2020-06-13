package data.configurators;

import gui.tableElements.commons.MyTableModel;

public class GeneralTableConfigurator implements TablesInterface{
    private static GeneralTableConfigurator singleInstance = null;
    private MyTableModel generalModel;

    private GeneralTableConfigurator() {

    }

    public static GeneralTableConfigurator getInstance() {
        if(singleInstance == null) {
            singleInstance = new GeneralTableConfigurator();
        }
        return singleInstance;
    }


    public MyTableModel createTableModel(String[][] finalRowsGeneral, String[] finalColumnsGeneral){
        int numberOfColumns = finalRowsGeneral[0].length;
        int numberOfRows = finalRowsGeneral.length;
        String[][] rows = new String[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            rows[i][0] = finalRowsGeneral[i][0];
        }

        generalModel = new MyTableModel(finalColumnsGeneral, rows);
        return generalModel;
    }

}
