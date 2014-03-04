package org.sdmlib.serialization.graph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;

public class GraphConverter implements Converter {
	public static final String TYP="typ";
	public static final String VALUE="value";
	public static final String NODE="node";
	public static final String EDGE = "edge";
	public static final String ID="id";
	public static final String ATTRIBUTES = "attributes";
	public static final String NODES = "nodes";
	public static final String EDGES = "edges";
	public static final String SOURCE= "source";
	public static final String TARGET= "target";
	public static final String SOURCECARDINALITY= "sourcecardinality";
	public static final String TARGETCARDINALITY= "targetcardinality";
	public static final String SOURCEPROPERTY= "sourceproperty";
	public static final String TARGETPROPERTY= "targetproperty";
	
	@Override
	public String convert(GraphList root, boolean removePackage) {
		return convertToJson(root, removePackage).toString();

	}
	public JsonObject convertToJson(JsonArray list, boolean removePackage){
		return convertToJson(GraphIdMap.OBJECT, list, removePackage);
	}
	public JsonObject convertToJson(String typ, JsonArray list, boolean removePackage){
		GraphList root = new GraphList().withTyp(typ);
		for(Object item  : list){
			if(item instanceof JsonObject){
				parseJsonObject(root, (JsonObject) item);
			}
		}
		return convertToJson(root, removePackage);
	}
	
	public GraphNode parseJsonObject(GraphList root, JsonObject node){
		String id = node.getString(JsonIdMap.ID);
		GraphNode graphNode = root.getById(id);
		if(graphNode==null){
			graphNode = new GraphNode().withId(id);
			root.add(graphNode);
		}
		if(node.has(JsonIdMap.CLASS)){
			graphNode.withClassName(node.getString(JsonIdMap.CLASS));
		}
		if(node.has(JsonIdMap.JSON_PROPS)){
			JsonObject props = node.getJsonObject(JsonIdMap.JSON_PROPS);
			for(Iterator<String> keys = props.keys();keys.hasNext();){
				String key = keys.next();
				Object value = props.get(key);
				if(value instanceof JsonObject) {
					// Must be a Link to 1
					GraphNode newNode = parseJsonObject(root, (JsonObject)value);
					root.addEdge(new GraphEdge().withSource(graphNode).withTarget(newNode, GraphIdMap.ONE, key));
				}else if(value instanceof JsonArray) {
					// Must be a Link to n
					JsonArray array = (JsonArray) value;
					StringBuilder sb = new StringBuilder();
					for(Object item : array){
						if(item instanceof JsonObject){
							GraphNode newNode = parseJsonObject(root, (JsonObject)item);
							root.addEdge(new GraphEdge().withSource(graphNode).withTarget(newNode, GraphIdMap.MANY, key));							
						}else{
							if(sb.length()>0){
								sb.append(","+item.toString());
							}else{
								sb.append(item.toString());
							}
						}
					}
				}else{
					graphNode.addValue(key, value.getClass().getName(), value.toString());
				}
				
			}
		}
		return graphNode;
	}
	
	public JsonObject convertToJson(GraphList root, boolean removePackage){
		String typ = root.getTyp();
		JsonObject jsonRoot=new JsonObject().withValue(TYP, typ);
		JsonObject value=new JsonObject();
		value.put(NODES, parseEntities(typ, root.getChildren(), removePackage));
		value.put(EDGES, parseEdges(typ, root.getEdges(), removePackage));
		jsonRoot.put("value", value);
		return jsonRoot;
	}
	
	private Collection<?> parseEdges(String typ, ArrayList<GraphEdge> edges, boolean shortName) {
		JsonArray result=new JsonArray();
		HashSet<String> ids=new HashSet<String>();

		for(GraphEdge edge : edges){
			for(GraphNode source : edge.getSource().getItems()){
				for(GraphNode target : edge.getTarget().getItems()){
					JsonObject child = new JsonObject().withValue(TYP, EDGE);
					child.put(SOURCECARDINALITY,  edge.getSource().getCardinality());
					child.put(TARGETCARDINALITY,  edge.getTarget().getCardinality());
					child.put(SOURCEPROPERTY,  edge.getSource().getProperty());
					child.put(TARGETPROPERTY,  edge.getTarget().getProperty());
					if(typ.equals(GraphIdMap.OBJECT)) {
						child.put(SOURCE, source.getId() + " : " + source.getClassName(shortName));
						child.put(TARGET, target.getId() + " : " + target.getClassName(shortName));
						result.add(child);
					}else{
						String id = source.getClassName(false)+":"+edge.getSource().getProperty()+target.getClassName(false)+":"+edge.getTarget().getProperty();
						if(!ids.contains(id)){
							child.put(SOURCE, source.getClassName(shortName));
							child.put(TARGET, target.getClassName(shortName));
							result.add(child);
							ids.add(id);
						}
					}
				}
			}
		}
		return result;
	}
	
	public JsonArray parseEntities(String typ, Collection<GraphNode> nodes, boolean shortName) {
		JsonArray result=new JsonArray();
		HashSet<String> ids=new HashSet<String>();
		for(GraphNode entity : nodes){
			JsonObject item = parseEntity(typ, entity, shortName);
			if(item!=null){
				if (typ == GraphIdMap.CLASS) {
					String key = item.getString(ID);
					if(ids.contains(key)){
						continue;
					}
					ids.add(key);
				}
				result.add(item);
			}
		}
		return result;
	}
	
	public JsonObject parseEntity(String typ, GraphNode entity, boolean shortName) {
		if(typ==null){
			typ = GraphIdMap.OBJECT;
			if(entity.getId()==null){
				typ = GraphIdMap.CLASS;
			}
		}
		if (!entity.isVisible()) {
			return null;
		}
		JsonObject item = new JsonObject().withValue(TYP, NODE);
		if (typ == GraphIdMap.OBJECT) {
			item.put(ID, entity.getId() + " : " + entity.getClassName(shortName));
		}else{
			item.put(ID, entity.getClassName(shortName));
		}
		item.put(ATTRIBUTES, parseAttributes(typ, entity.getAttributes(), shortName));
		return item;
	}
	private JsonArray parseAttributes(String typ, ArrayList<Attribute> attributes, boolean shortName) {
		JsonArray result=new JsonArray();
		String splitter = "";
		if (typ.equals(GraphIdMap.OBJECT)) {
			splitter = "=";
		} else if (typ.equals(GraphIdMap.CLASS)) {
			splitter = ":";
		}
		for(Attribute attribute : attributes){
			result.put(attribute.getKey() + splitter + attribute.getValue(typ, shortName));
		}
		return result;
	}
}
