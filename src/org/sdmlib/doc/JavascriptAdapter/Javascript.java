package org.sdmlib.doc.JavascriptAdapter;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.doc.interfaze.Drawer.GuiFileDrawer;
import org.sdmlib.model.taskflows.util.LogEntrySet;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.util.ClassModelCreator;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.util.GenericObjectSet;

import de.uniks.networkparser.graph.GraphConverter;
import de.uniks.networkparser.graph.GraphIdMap;
import de.uniks.networkparser.graph.GraphNode;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;

public class Javascript implements GuiAdapter
{
   public static final String NAME="Javascript";
   private String rootDir = "src";
   private JsonIdMap lastIdMap = null;
   private LinkedHashMap<String, String> iconMap;

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
            jsonObject.put("headimage", entry.getValue());
         }
      }

      // new diagram
      GraphConverter graphConverter = new GraphConverter();
      JsonObject objectModel = graphConverter.convertToJson(GraphIdMap.OBJECT, jsonArray, true);

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
      return null;
   }

   @Override
   public String dumpSwimlanes(String name, LogEntrySet entries)
   {
      // TODO Auto-generated method stub
      return null;
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
      return null;
   }

   @Override
   public String dumpClassDiagram(String diagName, ClassModel model)
   {
      JsonObject json = new JsonObject();
      
      json.put("typ", "class");
      
      JsonObject jsonGraph = new JsonObject();
      
      JsonArray jsonNodes = new JsonArray();
      JsonArray jsonEdges = new JsonArray();
      
      for (Clazz clazz : model.getClasses())
      {
         JsonObject jsonClazz = new JsonObject();
         jsonClazz.put("typ", "node");
         jsonClazz.put("id", clazz.getName());
         
         JsonArray jsonAttrs = new JsonArray();
         for (Attribute attr : clazz.getAttributes())
         {
            jsonAttrs.add("" + attr.getName() + " : " + attr.getType().getValue());
         }
         
         jsonClazz.put("attributes", jsonAttrs);
         
         jsonNodes.add(jsonClazz);
      }
      
      jsonGraph.put("nodes", jsonNodes);

      for (Association assoc : model.getClasses().getRoles().getAssoc())
      {
         JsonObject jsonAssoc = new JsonObject();
         jsonAssoc.put("typ","edge");
         Role source = assoc.getSource();
         Role target = assoc.getTarget();
         jsonAssoc.put("sourcecardinality", source.getCard());
         jsonAssoc.put("targetcardinality", target.getCard());
         jsonAssoc.put("sourceproperty", source.getName());
         jsonAssoc.put("targetproperty",target.getName());
         jsonAssoc.put("source",source.getClazz().getName());
         jsonAssoc.put("target",target.getClazz().getName());

         jsonEdges.add(jsonAssoc);
      }
      
      
      
      jsonGraph.put("edges", jsonEdges);
      
      json.put("value", jsonGraph);
      
      String text =
            "<script>\n" +
               "   var json = " +
               json.toString(3) +
               "   ;\n" +
               "   json[\"options\"]={\"canvasid\":\"canvas" + diagName + "\", "
               + "\"display\":\"html\", "
               + "\"fontsize\":10,"
               + "\"bar\":true};" +
               "   var g = new Graph(json);\n" +
               "   g.layout(100,100);\n" +
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
