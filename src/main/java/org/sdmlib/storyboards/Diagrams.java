package org.sdmlib.storyboards;

import java.io.File;
import java.io.IOException;

import org.sdmlib.CGUtil;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;

public class Diagrams
{
   public static String classDiag(ClassModel model)
   {
      return new Diagrams().doClassDiag(model);
   }

   public String doClassDiag(ClassModel model)
   {
      try
      {
         StringBuilder dotString = new StringBuilder();
         dotString.append("" +
               "digraph H {\n" +
               "nodes \n" +
               "edges \n" +
               "}\n");


         String nodesString = makeNodes(model);
         String edgesString = makeEdges(model);

         CGUtil.replaceAll(dotString,
               "nodes", nodesString,
               "edges", edgesString);
         String imageFileName = "doc/doc-files/" + model.getName() + ".png";
         // System.out.println(dotString.toString());
         System.out.println(new File(".").getAbsolutePath());
       	 Graphviz.fromString(dotString.toString()).render(Format.PNG).toFile(new File(imageFileName));

         return imageFileName;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

      return null;
   }

   private String makeEdges(ClassModel model)
   {
      StringBuilder buf = new StringBuilder();


      for (Association assoc : model.getAssociations())
      {
         String sourceId = assoc.getClazz().getName();
         String targetId = assoc.getOther().getClazz().getName();

         String sourceLabel = assoc.getName();
         if (assoc.getCardinality() == Association.MANY)
         {
            sourceLabel += " *";
         }

         String targetLabel = assoc.getOther().getName();
         if (assoc.getOther().getCardinality() == Association.MANY)
         {
            targetLabel += " *";
         }

         buf.append(sourceId).append(" -> ").append(targetId)
               .append(" [arrowhead=none fontsize=\"10\" " +
                     "taillabel=\"" + sourceLabel + "\" " +
                     "headlabel=\"" + targetLabel + "\"];\n");
      }

      return buf.toString();
   }

   private String makeNodes(ClassModel model)
   {
      StringBuilder buf = new StringBuilder();

      for (Clazz clazz : model.getClazzes())
      {
         String objId = clazz.getName();

         buf.append(objId).append(" " +
               "[\n" +
               "   shape=plaintext\n" +
               "   fontsize=\"10\"\n" +
               "   label=<\n"  +
               "     <table border='0' cellborder='1' cellspacing='0'>\n" +
               "       <tr><td>")
               .append(objId)
               .append("</td></tr>\n"  +
                     "       <tr><td>");

         for (Attribute key : clazz.getAttributes())
         {
            buf.append(key).append("<br  align='left'/>");
         }

         buf.append("</td></tr>\n" +
               "     </table>\n" +
               "  >];\n");
      }

      return buf.toString();
   }
}
