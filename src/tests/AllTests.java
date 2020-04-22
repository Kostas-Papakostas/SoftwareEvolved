package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllPPLTransitionTest.class, MyTableModelTest.class, TreeConstructionGeneralTest.class })
public class AllTests {

}
