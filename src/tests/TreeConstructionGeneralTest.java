package tests;

import gui.treeElements.TreeConstructionGeneral;

import static org.junit.Assert.assertEquals;

import javax.swing.*;
import javax.swing.tree.TreeModel;

import org.junit.Test;

//import org.junit.Test;


//import static org.junit.Assert.assertEquals;

/**
 * Created by Kostakis on 20/4/2020.
 */
public class TreeConstructionGeneralTest {
    @Test
    public void testConstructionGeneralTree(){
        String expectedResult="VersionsFake Version 0t0t1t2t3Fake Version 1t0t1t2t3Fake Version 2t0t1t2t3Fake Version 3t0t1t2t3";
        String descriptionExpectedResult = "Transition ID:1\n"+
                "Old Version Name:oldschema0\n" +
                "New Version Name:newschema0\n" +
                "Transition Changes:0\n" +
                "Additions:0\n" +
                "Deletions:0\n" +
                "Updates:0\n";
        FakeGlobalDataKeeper fakeKeeper = new FakeGlobalDataKeeper();
        fakeKeeper.setData();

        TreeConstructionGeneral testTreeCostructionManual = new TreeConstructionGeneral(fakeKeeper);

        JTree result = testTreeCostructionManual.constructTree();
        TreeModel model = result.getModel();

        String [][]temp= {{"t0"}};

        assertEquals(descriptionExpectedResult,fakeKeeper.constructDescriptionZoomAreaPhasesColumn(temp,0));
        assertEquals(expectedResult, getTreeText(model, model.getRoot(), ""));
    }

    private String getTreeText(TreeModel model, Object object,String indent){
        String myRow = indent+object+"";
        for(int i=0;i<model.getChildCount(object);i++){
            myRow+=getTreeText(model,model.getChild(object,i),indent);
        }
        return myRow;
    }

}
