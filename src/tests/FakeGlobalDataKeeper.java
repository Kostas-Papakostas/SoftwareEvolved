package tests;

import data.dataKeeper.GlobalDataKeeper;
import data.dataPPL.pplSQLSchema.PPLSchema;
import data.dataPPL.pplSQLSchema.PPLTable;

import java.util.TreeMap;

/**
 * Created by Kostakis on 20/4/2020.
 */
public class FakeGlobalDataKeeper extends GlobalDataKeeper{


    FakeGlobalDataKeeper(String fl, String transitionFile){
        super(fl,transitionFile);
    }

    FakeGlobalDataKeeper(){
        allPPLSchemas = new TreeMap<String,PPLSchema>();
    }

    public void setData(){

        TreeMap<String,PPLTable> fakePPLTable = new TreeMap<String,PPLTable>();
        fakePPLTable.put("t0", new FakePPLTable());
        fakePPLTable.put("t1", new FakePPLTable());
        fakePPLTable.put("t2", new FakePPLTable());
        fakePPLTable.put("t3", new FakePPLTable());

        FakePPLSchema fakeSchema0 = new FakePPLSchema(fakePPLTable);
        FakePPLSchema fakeSchema1 = new FakePPLSchema(fakePPLTable);
        FakePPLSchema fakeSchema2 = new FakePPLSchema(fakePPLTable);
        FakePPLSchema fakeSchema3 = new FakePPLSchema(fakePPLTable);

        allPPLSchemas.put("Fake Version 0", fakeSchema0);
        allPPLSchemas.put("Fake Version 1", fakeSchema1);
        allPPLSchemas.put("Fake Version 2", fakeSchema2);
        allPPLSchemas.put("Fake Version 3", fakeSchema3);
    }
}
