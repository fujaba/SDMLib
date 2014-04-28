package org.sdmlib.doc;

import java.util.LinkedHashMap;

import org.sdmlib.logger.util.LogEntrySet;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.util.GenericObjectSet;
import org.sdmlib.serialization.json.JsonArray;

public interface GuiAdapter
{
   public GuiAdapter withRootDir(String rootDir);

   public GuiAdapter withIconMap(LinkedHashMap<String, String> iconMap);

   public String toImg(String imgName, org.sdmlib.serialization.json.JsonArray objects);

   public String toImg(String imgName, org.sdmlib.serialization.json.JsonArray objects, boolean omitRoot,
         String[] aggregationRoles);

   public String addGenericObjectDiag(String diagramName, GenericGraph graph, GenericObjectSet hiddenObjects);

   public String dumpSwimlanes(String name, LogEntrySet entries);

   public void fillNodeAndEdgeBuilders(String imgName, JsonArray objects,
         StringBuilder nodeBuilder, StringBuilder edgeBuilder,
         boolean omitRoot, String... aggregationRoles);

   public String dumpDiagram(String diagramName, String fileText);

   public String dumpClassDiagram(String diagName, ClassModel model);
}
