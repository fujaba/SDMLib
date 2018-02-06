package org.sdmlib.models.classes.gui;

import org.sdmlib.StrUtil;

import de.uniks.networkparser.ext.generic.ReflectionLoader;
import de.uniks.networkparser.ext.javafx.DiagramEditor;
import javafx.application.Application;
import javafx.stage.Stage;

public class SDMDiagramEditor extends Application {
	private DiagramEditor editor;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ReflectionLoader.logger = System.err;
		editor = DiagramEditor.create(primaryStage);
		editor.withIcon(StrUtil.class.getResource("sdmlib.png").toString());
//		editor.withListener(this);
		editor.show();
	}
//	@Override
//	public boolean generate(JsonObject model) {
////		super.generate(model);
//		if(!model.has("nodes")) {
//			System.err.println("no Nodes");
//			System.out.println("no Nodes");
//			return false;
//		}
//    	JsonObject nodes = model.getJsonObject("nodes");
//    	ClassModel classModel=new ClassModel(model.getString("package"));
//		for (int i = 0; i < nodes.size(); i++) {
//			Object item = nodes.getValueByIndex(i);
//			if (item instanceof JsonObject) {
//				JsonObject node = (JsonObject) item;
//				Clazz clazz = classModel.createClazz(node.getString("id"));
//				if(node.has("attributes")) {
//					JsonArray attributes = node.getJsonArray("attributes");
//					for (Object entity : attributes) {
//						if (entity instanceof String) {
//							String attribute = (String) entity;
//							int pos = attribute.indexOf(":");
//							if (pos > 0) {
//								clazz.createAttribute(attribute.substring(0, pos),
//										DataType.create(attribute.substring(pos + 1)));
//							}
//						}
//					}
//				}
//			}
//		}
//		if(model.has("edges")){
//			JsonArray edges = model.getJsonArray("edges");
//			for(Object entity : edges) {
//				if(entity instanceof JsonObject) {
//					JsonObject edge = (JsonObject) entity;
//					JsonObject source = (JsonObject) edge.getJsonObject("source");
//					JsonObject target = (JsonObject) edge.getJsonObject("target");
//					if(edge.getString("typ").equalsIgnoreCase("edge")) {
//						Clazz fromClazz = classModel.getClazz(source.getString("id"));
//						Clazz toClazz = classModel.getClazz(target.getString("id"));
//						
//						fromClazz.withBidirectional(toClazz, target.getString("property"), Cardinality.ONE, source.getString("property"), Cardinality.ONE);
//					}
//				}
//			}
//		}
//		
//		
// 	   	String genModel = classModel.getName()  + ".genModel";
//    	 classModel.getGenerator().insertModelCreationCodeHere("gen", genModel, "testGenModel");
//    	 classModel.generate("gen");
//		return false;
//	}
//	
//	@Override
//	public void open(Object logic, String... args) {
//		try {
//			Class<?> clazz = Class.forName(Editor.URL);
//			Object editor = clazz.newInstance();
//			if (editor instanceof Editor) {
//				((Editor) editor).open(this, args);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
}