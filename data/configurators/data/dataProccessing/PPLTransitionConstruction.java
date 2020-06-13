package data.dataProccessing;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import data.dataPPL.pplSQLSchema.PPLSchema;
import data.dataPPL.pplTransition.AtomicChange;
import data.dataPPL.pplTransition.PPLTransition;
import data.dataPPL.pplTransition.TableChange;

public class PPLTransitionConstruction {
	
	private static TreeMap<Integer,PPLTransition> allPPLTransitions = null;
	private static TreeMap<String,PPLSchema> allPPLSchemas = new TreeMap<String,PPLSchema>();
	private static TreeMap<String,TableChange> allTableChanges = new  TreeMap<String,TableChange>();

	public PPLTransitionConstruction(TreeMap<String,PPLSchema> tmpSc, TreeMap<String,TableChange> tmpTbs){
		allPPLTransitions = new TreeMap<Integer,PPLTransition>();
		allPPLSchemas=tmpSc;
		allTableChanges = tmpTbs;
		
	}
	
	public void makePPLTransitions(){
		
		allPPLTransitions  = new TreeMap<Integer,PPLTransition>();
		
		ArrayList<TableChange> tmpTableChanges = new ArrayList<TableChange>();
		
		Set<String> tmpKeys = allPPLSchemas.keySet();
		ArrayList<String> assistantKeys = new ArrayList<String>();
		
		for(String key: tmpKeys){
			assistantKeys.add(key);
		}
		
		for(int i=0; i<assistantKeys.size()-1; i++){
			
			PPLTransition tmpPPLTransition = new PPLTransition(assistantKeys.get(i),assistantKeys.get(i+1),i);
			
			allPPLTransitions.put(i,tmpPPLTransition);
			
		}
		
		
		for (Map.Entry<Integer,PPLTransition> transition : allPPLTransitions.entrySet()) {

			for (Map.Entry<String, TableChange> tableChange : allTableChanges.entrySet()) {
				
				TableChange tmpTableChange = tableChange.getValue();
				
				TreeMap<Integer,ArrayList<AtomicChange>> tmpAtomicChanges = tmpTableChange.getTableAtomicChanges();
				
				for (Map.Entry<Integer,ArrayList<AtomicChange>> at : tmpAtomicChanges.entrySet()) {
	
					if(at.getKey().equals(transition.getKey())){
					
						TableChange tmpTableChange1 = new TableChange(tableChange.getKey(),tmpTableChange.getTableAtChForOneTransition(transition.getKey()));
						
						tmpTableChanges.add(tmpTableChange1);
						
					}
				
				}
					
			}
		
			transition.getValue().setTableChanges(tmpTableChanges);
			tmpTableChanges = new ArrayList<TableChange>();
			
		}

	}
	
	public  TreeMap<Integer,PPLTransition> getAllPPLTransitions(){
		
		return allPPLTransitions;
	}
}
