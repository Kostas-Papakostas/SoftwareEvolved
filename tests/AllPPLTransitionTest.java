package tests;

import data.dataPPL.pplTransition.PPLTransition;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
//import org.junit.jupiter.api.Test;

//import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by Kostakis on 21/4/2020.
 */
public class AllPPLTransitionTest {
    @Test
    public void testAllPPLTransition(){
        FakeGlobalDataKeeper fakeKeeper = new FakeGlobalDataKeeper();
        fakeKeeper.setData();

        PPLTransition pplTransition = (PPLTransition) fakeKeeper.getAllPPLTransitions().get(0);

        assertEquals(pplTransition.getOldVersionName(),"oldschema0");
        assertEquals(pplTransition.getNewVersionName(),"newschema0");
        assertEquals(pplTransition.getPPLTransitionID(), 001);

    }

    //@Test
    public void testTransitionTableChanges(){
        String expectedResult="Table Change \n" + "[AtomicChange: Table: t0\tAttribute: birth\tType: integer\toldSchema: oldschema0\tnewSchema: newschema0]\n";

        FakeGlobalDataKeeper fakeKeeper = new FakeGlobalDataKeeper();
        fakeKeeper.setData();

        PPLTransition pplTransition = (PPLTransition) fakeKeeper.getAllPPLTransitions().get(0);


        System.out.println(pplTransition.getTableChanges().get(0).toString());
        assertEquals(pplTransition.getTableChanges().get(0).toString(), expectedResult);

    }
}