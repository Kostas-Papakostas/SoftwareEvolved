package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AllPPLTransitionTest.class, LoadUseCaseTest.class, TreeConstructionGeneralTest.class })
public class AllTests {

}
