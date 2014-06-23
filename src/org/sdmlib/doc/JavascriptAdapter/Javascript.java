package org.sdmlib.doc.JavascriptAdapter;

import java.util.LinkedHashMap;

import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.doc.interfaze.Drawer.GuiFileDrawer;
import org.sdmlib.model.taskflows.util.LogEntrySet;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.util.GenericObjectSet;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;

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
   public String toImg(String imgName, JsonArray objects)
   {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String toImg(String imgName, JsonArray objects, boolean omitRoot, String[] aggregationRoles)
   {
      // TODO Auto-generated method stub
      return null;
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
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public Javascript withDrawer(GuiFileDrawer drawer)
   {
      // TODO Auto-generated method stub
      return null;
   }
}
