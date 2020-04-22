package tests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Kostakis on 21/4/2020.
 */
public class AllPPLTransitionTest {
    @Test
    public void testAllPPLTransition(){
        String expectedResult="Transition ID: 001 Old Version Name: oldshema0 New Version Name: newschema0";

        FakeGlobalDataKeeper fakeKeeper = new FakeGlobalDataKeeper();
        fakeKeeper.setData();

        FakePPLTransition fakeTransition = (FakePPLTransition) fakeKeeper.getAllPPLTransitions().get(0);

        assertEquals(fakeTransition.getOldVersionName(),"oldschema0");
        assertEquals(fakeTransition.getNewVersionName(),"newschema0");
        assertEquals(fakeTransition.getPPLTransitionID(),001);

    }

    @Test
    public void testTransitionTableChanges(){
        String expectedResult="Table Change \n" + "[AtomicChange: Table: t0\tAttribute: birth\tType: integer\toldSchema: oldschema0\tnewSchema: newschema0]\n";

        FakeGlobalDataKeeper fakeKeeper = new FakeGlobalDataKeeper();
        fakeKeeper.setData();

        FakePPLTransition fakeTransition = (FakePPLTransition) fakeKeeper.getAllPPLTransitions().get(0);


        System.out.println(fakeTransition.getTableChanges().get(0).toString());
        assertEquals(fakeTransition.getTableChanges().get(0).toString(),expectedResult);

    }
}