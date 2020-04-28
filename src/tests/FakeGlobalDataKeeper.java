package tests;

import data.dataKeeper.GlobalDataKeeper;
import data.dataPPL.pplSQLSchema.PPLSchema;
import data.dataPPL.pplSQLSchema.PPLTable;
import data.dataPPL.pplTransition.AtomicChange;
import data.dataPPL.pplTransition.PPLTransition;
import data.dataPPL.pplTransition.TableChange;

import java.util.ArrayList;
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
        allPPLTransitions = new TreeMap<Integer,PPLTransition>();
    }

    public TreeMap<Integer,PPLTransition> getAllPPLTransitions(){

        return allPPLTransitions;

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


        PPLTransition ppLTransition = new PPLTransition("oldschema0", "newschema0", 001);


        AtomicChange atomicChange = new AtomicChange("t0", "birth", "integer", "oldschema0", "newschema0", 001);


        ArrayList<AtomicChange> atomicChangeList = new ArrayList<AtomicChange>();
        atomicChangeList.add(atomicChange);

        TreeMap<Integer, ArrayList<AtomicChange>> atomicChangesTree = new TreeMap<Integer, ArrayList<AtomicChange>>();
        atomicChangesTree.put(0, atomicChangeList);

        TableChange tableChanges = new TableChange("t0", atomicChangesTree);

        ArrayList<TableChange> tableChangesList = new ArrayList<>();
        tableChangesList.add(tableChanges);

        ppLTransition.setTableChanges(tableChangesList);

        allPPLTransitions.put(0, ppLTransition);
        allTables=fakePPLTable;
    }
}
