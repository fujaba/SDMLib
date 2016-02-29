package org.sdmlib.doc.JavascriptAdapter;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.sdmlib.CGUtil;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.doc.interfaze.Drawer.GuiFileDrawer;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.util.GenericObjectSet;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.converter.GraphConverter;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.AssociationTypes;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.GraphList;
import de.uniks.networkparser.graph.GraphTokener;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Parameter;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.list.SimpleSet;

public class Javascript implements GuiAdapter
{
   public static final String NAME="Javascript";
   private String rootDir = "src";
   private IdMap lastIdMap = null;
   private LinkedHashMap<String, String> iconMap = new LinkedHashMap<>();

   @Override
   public Javascript withRootDir(String rootDir) {
      this.rootDir = rootDir;
      return this;
   }

   @Override
   public Javascript withIconMap(LinkedHashMap<String, String> iconMap) {
      this.iconMap = iconMap;
      return this;
   }


   @Override
   public String getName()
   {
      return NAME;
   }
   
   @Override
   public String toImg(String imgName, JsonArray jsonArray)
   {
      // add icons
      for (Entry<String, String> entry : iconMap.entrySet())
      {
         JsonObject jsonObject = jsonArray.get(entry.getKey());

         if (jsonObject != null)
         {
            jsonObject.put("head", new JsonObject().withKeyValue("src", entry.getValue()));
         }
      }

      // new diagram
      GraphConverter graphConverter = new GraphConverter();
      JsonObject objectModel = graphConverter.convertToJson(GraphTokener.OBJECT, jsonArray, true);

      String text =
            "<script>\n" +
               "   var json = " +
               objectModel.toString(3) +
               "   ;\n" +
               "   json[\"options\"]={\"canvasid\":\"canvas" + imgName + "\", "
               + "\"display\":\"html\", "
               + "\"fontsize\":10,"
               + "\"bar\":true};" +
               "   var g = new Graph(json);\n" +
               "   g.layout(100,100);\n" +
               "</script>\n";      
      return text;
   }

   @Override
   public String addGenericObjectDiag(String diagramName, GenericGraph graph, GenericObjectSet hiddenObjects)
   {
      // TODO Auto-generated method stub
      return "";
   }

   @Override
   public void fillNodeAndEdgeBuilders(String imgName, JsonArray objects, StringBuilder nodeBuilder,
         StringBuilder edgeBuilder, boolean omitRoot, String... aggregationRoles)
   {
      // TODO Auto-generated method stub
      
   }

   @Override
   public String dumpDiagram(String diagramName, String fileText)
   {
      // TODO Auto-generated method stub
      return "";
   }
   
   private SimpleSet<Association> getAllAssoc(ClassModel model) {
	   SimpleSet<Association> colleciton = new SimpleSet<Association>();
	   for(Clazz clazz : model.getClazzes()) {
		   colleciton.addAll(clazz.getAssociations());
	   }
	   return colleciton;
   }
   
   public GraphList convertModelToGraphList(ClassModel model) {
	   GraphList list = new GraphList().withTyp(IdMap.CLASS);
	   HashMap<String, Clazz> nodes=new HashMap<String, Clazz>();
	      
      for (Clazz clazz : model.getClazzes())
      {
    	  Clazz node = new Clazz().with(CGUtil.shortClassName(clazz.getName()));
         
         // Attributes
         for (Attribute attr : clazz.getAttributes())
         {
        	 node.with(new Attribute(attr.getName(), attr.getType()));
         }
         // Methods
         for (Method method : clazz.getMethods())
         {
        	 Method newMethod = new Method(method.getName());
        	 for(Parameter param : method.getParameter()){
        		 newMethod.withParameter(param.getName(), param.getType());
        	 }
        	 node.with(newMethod);
         }
         list.with(node);
         nodes.put(node.getId(), node);
      }
      for (Association assoc : getAllAssoc(model))
      {
//    	 Role source = assoc.getSource();
//         Role target = assoc.getTarget();
         
         Association sourceEdge = new Association(nodes.get(assoc.getClazz().getName(true))).with(assoc.getCardinality());
         Association targetEdge = new Association(nodes.get(assoc.getOtherClazz().getName(true))).with(assoc.getOther().getCardinality());
         sourceEdge.with(targetEdge);
         
         sourceEdge.with(assoc.getInfo());
         targetEdge.with(assoc.getInfo());
         list.with(sourceEdge);
      }
      
      for (Clazz kidClazz : model.getClazzes())
      {
         for (Clazz superClazz : kidClazz.getSuperClazzes(false))
         {
        	 Clazz clazz = nodes.get(CGUtil.shortClassName(kidClazz.getName()));
        	 Association generationEdge = new Association(clazz).with(AssociationTypes.GENERALISATION);
        	 Clazz kidClazzes = nodes.get(CGUtil.shortClassName(superClazz.getName()));
        	 Association kidEdge = new Association(kidClazzes).with(AssociationTypes.EDGE);
        	generationEdge.with(kidEdge);
        	
        	list.with(generationEdge);
         }
      }
      return list;
   }
   
   public JsonObject convertModel(ClassModel model) {
	   JsonObject json = new JsonObject();
	      
	      json.put("typ", IdMap.CLASS);
	      
	      JsonArray jsonNodes = new JsonArray();
	      JsonArray jsonEdges = new JsonArray();
	      
	      for (Clazz clazz : model.getClazzes())
	      {
	         JsonObject jsonClazz = new JsonObject();
	         jsonClazz.put("typ", "node");
	         jsonClazz.put("id", CGUtil.shortClassName(clazz.getName()));
	         
	         // Attributes
	         JsonArray jsonAttrs = new JsonArray();
	         for (Attribute attr : clazz.getAttributes())
	         {
	            jsonAttrs.add("" + attr.getName() + " : " + attr.getType().getName(true));
	         }
	         if(jsonAttrs.size()>0){
	        	 jsonClazz.put("attributes", jsonAttrs);
	         }
	         // Methods
	         JsonArray jsonMethods = new JsonArray();
	         for (Method method : clazz.getMethods())
	         {
	        	 jsonMethods.add("" + method.getName(false));
	         }
	         if(jsonMethods.size()>0){
	        	 jsonClazz.put("methods", jsonMethods);
	         }
	         jsonNodes.add(jsonClazz);
	      }
	      
	      json.put("nodes", jsonNodes);

	      for (Association assoc : getAllAssoc(model))
	      {
	         JsonObject jsonAssoc = new JsonObject();
	         jsonAssoc.put("typ","edge");
	         
	         JsonObject jsonRole = new JsonObject();
	         
	         jsonAssoc.put("source", jsonRole);
	         
//	         Role source = assoc.getSource();
//	         Role target = assoc.getTarget();
	         
	         jsonRole.put("id", assoc.getClazz().getName(true));
	         if(assoc.getCardinality() != null) {
	        	 jsonRole.put("cardinality", assoc.getCardinality());
	        	 jsonRole.put("property", assoc.getName());
	         }

	         jsonRole = new JsonObject();
	         jsonAssoc.put("target", jsonRole);
	         
	         jsonRole.put("id", assoc.getOtherClazz().getName(true));
	         jsonRole.put("cardinality", assoc.getOther().getCardinality());
	         jsonRole.put("property",assoc.getOther().getName());
	         
	         jsonEdges.add(jsonAssoc);
	      }
	      
	      for (Clazz kidClazz : model.getClazzes())
	      {
	         for (Clazz superClazz : kidClazz.getSuperClazzes(false))
	         {
	            JsonObject jsonAssoc = new JsonObject();
	            jsonAssoc.put("typ","generalisation");
	            
	            JsonObject jsonRole = new JsonObject();
	            jsonRole.put("id",CGUtil.shortClassName(kidClazz.getName()));
	            jsonAssoc.put("source", jsonRole);
	            
	            jsonRole = new JsonObject();
	            jsonRole.put("id",CGUtil.shortClassName(superClazz.getName()));
	            jsonAssoc.put("target", jsonRole);

	            jsonEdges.add(jsonAssoc);
	         }
	      }
	      
	      
	      json.put("edges", jsonEdges);

	      return json;
   }

   @Override
   public String dumpClassDiagram(String diagName, ClassModel model)
   {
      JsonObject json = this.convertModel(model);
      
      String text =
            "<script>\n" +
               "   var json = " +
               json.toString(3) +
               "   ;\n" +
               "   new Graph(json, {\"canvasid\":\"canvas" + diagName + "\", "
               + "\"display\":\"html\", "
               + "\"fontsize\":10,"
               + "\"bar\":true}).layout(100,100);\n" +
               "</script>\n";      
      return text;
   }

   @Override
   public Javascript withDrawer(GuiFileDrawer drawer)
   {
      // TODO Auto-generated method stub
      return null;
   }
}
