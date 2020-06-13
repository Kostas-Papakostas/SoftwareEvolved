package gui.treeElements;

import data.dataKeeper.GlobalDataKeeper;
import data.dataPPL.pplSQLSchema.PPLSchema;
import data.dataPPL.pplSQLSchema.PPLTable;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Map;
import java.util.TreeMap;

public class TreeConstructionGeneral {

	private GlobalDataKeeper dataKeeper=null;
	
	public TreeConstructionGeneral(GlobalDataKeeper dataKeeper){
		this.dataKeeper=dataKeeper;
	}
	
	public JTree constructTree(){
		
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Versions");
		TreeMap<String, PPLSchema> schemas = dataKeeper.getAllPPLSchemas();
		
		for (Map.Entry<String,PPLSchema> pplSc : schemas.entrySet()) {
			
			DefaultMutableTreeNode mutableTreeNode = new DefaultMutableTreeNode(pplSc.getKey());
		    top.add(mutableTreeNode);
		    TreeMap<String, PPLTable> tables = pplSc.getValue().getTables();
		    
			for (Map.Entry<String,PPLTable> pplT : tables.entrySet()) {
				DefaultMutableTreeNode nestedMutableTreeNode = new DefaultMutableTreeNode(pplT.getKey());
				mutableTreeNode.add(nestedMutableTreeNode);
			}
		    
		}
		
		JTree treeToConstruct = new JTree(top);
		
		return treeToConstruct;
		
	}

	public GlobalDataKeeper getDataKeeper(){
		return this.dataKeeper;
	}
	
}
