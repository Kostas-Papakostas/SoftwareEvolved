package tests;

//import org.junit.runner.RunWith;
//import org.junit.runners.Suite;
//import org.junit.runners.Suite.SuiteClasses;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ AllPPLTransitionTest.class, LoadUseCaseTest.class, TreeConstructionGeneralTest.class })
public class AllTests {

}