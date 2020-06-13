package tests;

import data.dataPPL.pplSQLSchema.PPLSchema;
import data.dataPPL.pplSQLSchema.PPLTable;

import java.util.TreeMap;

/**
 * Created by Kostakis on 20/4/2020.
 */
public class FakePPLSchema extends PPLSchema {

    FakePPLSchema(TreeMap<String,PPLTable> t){
        super(t);
    }
}
