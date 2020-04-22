package tests;

import data.dataPPL.pplTransition.PPLTransition;

/**
 * Created by Kostakis on 21/4/2020.
 */
public class FakePPLTransition extends PPLTransition {
    FakePPLTransition(String tmpOldSchema, String tmpNewSchema, int pplTransitionID){
        super(tmpOldSchema,tmpNewSchema,pplTransitionID);
    }

}
