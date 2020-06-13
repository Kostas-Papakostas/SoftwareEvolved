package gui.treeElements;

import java.util.ArrayList;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import tableClustering.clusterExtractor.commons.Cluster;
import data.dataKeeper.GlobalDataKeeper;

public class TreeConstructionPhasesWithClusters implements TreeConstruction {
	
	private GlobalDataKeeper dataKeeper=null;

	public TreeConstructionPhasesWithClusters(GlobalDataKeeper dataKeeper) {
		this.dataKeeper=dataKeeper;
	}
	
	@Override
	public JTree constructTree() {
		
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Clusters");		
				
		ArrayList<Cluster> clusters = dataKeeper.getClusterCollectors().get(0).getClusters();
		
		for(int i=0; i<clusters.size(); i++){
			
			DefaultMutableTreeNode mutableTreeNode=new DefaultMutableTreeNode("Cluster "+i);
			top.add(mutableTreeNode);
			ArrayList<String> tables=clusters.get(i).getNamesOfTables();
			
			for(String tableName:tables){
				DefaultMutableTreeNode nestedMutableTreeNode=new DefaultMutableTreeNode(tableName);
				mutableTreeNode.add(nestedMutableTreeNode);
				
			}
			
		}
		
		JTree treeToConstruct = new JTree(top);
		
		return treeToConstruct;
	}

}
