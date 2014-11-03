package org.sdmlib.doc.JavascriptAdapter;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.sdmlib.CGUtil;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.doc.interfaze.Drawer.GuiFileDrawer;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.util.GenericObjectSet;
import org.sdmlib.models.taskflows.util.LogEntrySet;

import de.uniks.networkparser.graph.GraphConverter;
import de.uniks.networkparser.graph.GraphIdMap;
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
      return "";
   }

   @Override
   public String dumpSwimlanes(String name, LogEntrySet entries)
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

   @Override
   public String dumpClassDiagram(String diagName, ClassModel model)
   {
      JsonObject json = new JsonObject();
      
      json.put("typ", "class");
      
      JsonArray jsonNodes = new JsonArray();
      JsonArray jsonEdges = new JsonArray();
      
      for (Clazz clazz : model.getClasses())
      {
         JsonObject jsonClazz = new JsonObject();
         jsonClazz.put("typ", "node");
         jsonClazz.put("id", CGUtil.shortClassName(clazz.getName()));
         
         JsonArray jsonAttrs = new JsonArray();
         for (Attribute attr : clazz.getAttributes())
         {
            jsonAttrs.add("" + attr.getName() + " : " + attr.getType().getValue());
         }
         
         jsonClazz.put("attributes", jsonAttrs);
         
         jsonNodes.add(jsonClazz);
      }
      
      json.put("nodes", jsonNodes);

      for (Association assoc : model.getClasses().getRoles().getAssoc())
      {
         JsonObject jsonAssoc = new JsonObject();
         jsonAssoc.put("typ","edge");
         
         JsonObject jsonRole = new JsonObject();
         
         jsonAssoc.put("source", jsonRole);
         
         Role source = assoc.getSource();
         Role target = assoc.getTarget();
         
         jsonRole.put("cardinality", source.getCard());
         jsonRole.put("property", source.getName());
         jsonRole.put("id",CGUtil.shortClassName(source.getClazz().getName()));

         jsonRole = new JsonObject();
         jsonAssoc.put("target", jsonRole);
         
         jsonRole.put("id",CGUtil.shortClassName(target.getClazz().getName()));
         jsonRole.put("cardinality", target.getCard());
         jsonRole.put("property",target.getName());
         
         jsonEdges.add(jsonAssoc);
      }
      
      for (Clazz kidClazz : model.getClasses())
      {
         for (Clazz superClazz : kidClazz.getSuperClazzes())
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
