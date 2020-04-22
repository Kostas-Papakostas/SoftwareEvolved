package tests;

import data.dataPPL.pplTransition.AtomicChange;
import data.dataPPL.pplTransition.TableChange;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Kostakis on 21/4/2020.
 */
public class FakeTableChanges extends TableChange {
    public FakeTableChanges(String tmpAffectedTable, TreeMap<Integer, ArrayList<AtomicChange>> tmpAtomicChanges){
        super(tmpAffectedTable,tmpAtomicChanges);
    }
}
