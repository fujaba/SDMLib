package org.sdmlib.test.doc;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

import java.io.File;
import java.io.IOException;

public class TestStructurizr
{
   /**
    * PNG Image:
    * <p>Storyboard StructurizrDiagrams</p>
    * <p>Capture some architecture information as diagram</p>
    * <img src='doc-files/diag.png' alt='diag'>
    */
   @Test
   public void testStructurizrDiagrams()
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.add("Capture some architecture information as diagram");

      String dotText = "digraph d {\n" +
              "   node1 [shape=plaintext label=<\n" +
              "      <table>\n" +
              "         <tr><td>IMG SRC=\"karli.png\"</td></tr>\n" +
              "      </table>\n" +
              ">]; \n" +
              "}\n";

      try
      {
         Graphviz.fromString(dotText).render(Format.SVG).toFile(new File("src/test/java/org/sdmlib/test/doc/doc-files/diag.svg"));
      } catch (IOException e)
      {
         e.printStackTrace();
      }

      story.add("<img src='doc-files/diag.png' alt='diag'>");

      story.dumpHTML();
   }
}
