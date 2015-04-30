package org.sdmlib.models.classes;

import java.util.Iterator;

import de.uniks.networkparser.gui.javafx.window.DiagramEditor;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;

public class SDMDiagramEditor extends DiagramEditor{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void generate(JsonObject model) {
//		super.generate(model);
		if(!model.has("nodes")) {
			System.err.println("no Nodes");
			System.out.println("no Nodes");
			return;
		}
    	JsonObject nodes = model.getJsonObject("nodes");
    	ClassModel classModel=new ClassModel(model.getString("package"));
    	for(int i=0;i<nodes.size();i++) {
    		 Object item = nodes.getValueByIndex(i);
    		 if(item instanceof JsonObject) {
    			 JsonObject node = (JsonObject) item;
            	 classModel.createClazz(node.getString("id"));
            	 JsonArray attributes = node.getJsonArray("attributes");
            	 
    		 }
    	 }
    	 classModel.generate("gen");
	}
}
