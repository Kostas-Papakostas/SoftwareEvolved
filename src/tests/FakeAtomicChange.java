package tests;

import data.dataPPL.pplTransition.AtomicChange;

/**
 * Created by Kostakis on 21/4/2020.
 */
public class FakeAtomicChange extends AtomicChange {
    public FakeAtomicChange(String tmpAffectedTable, String tmpAffectedAttribute, String tmpType, String tmpOldSchema, String tmpNewSchema, Integer transitionID){
        super(tmpAffectedTable,tmpAffectedAttribute,tmpType,tmpOldSchema,tmpNewSchema,transitionID);
    }
}
