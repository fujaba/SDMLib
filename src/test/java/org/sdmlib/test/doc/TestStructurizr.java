package org.sdmlib.test.doc;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.api.StructurizrClientException;
import com.structurizr.io.dot.DotWriter;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;
import com.structurizr.io.WorkspaceReader;
import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

public class TestStructurizr
{
   /**
    *
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/doc/TestStructurizr.java' type='text/x-java'>StructurizrDiagrams</a></p>
    * <p>Capture some architecture information with structurizr</p>
    */
   @Test
   public void testStructurizrDiagrams()
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.add("Capture some architecture information with structurizr");

      Workspace workspace = new Workspace("Getting Started", "This is a model of my software system.");
      Model model = workspace.getModel();

      Person alice = model.addPerson("Alice", "Alice uses GroupAccount.");
      Person bob = model.addPerson("Bob", "Alice uses GroupAccount.");
      SoftwareSystem softwareSystem = model.addSoftwareSystem("GroupAccount", "Doing accounting for groups.");
      alice.uses(softwareSystem, "uses");
      bob.uses(softwareSystem, "uses");

      ViewSet views = workspace.getViews();
      SystemContextView contextView = views.createSystemContextView(softwareSystem, "SystemContext", "A System Context diagram.");
      contextView.addAllSoftwareSystems();
      contextView.addAllPeople();

//      StructurizrClient structurizrClient = new StructurizrClient("11066b8b-930b-4de8-a5b8-164a6da0d2c9 ", "aeb9d278-a826-43b9-96f5-2c71d414b758");
//      try
//      {
//         structurizrClient.putWorkspace( 38847, workspace);
//      } catch (StructurizrClientException e)
//      {
//         e.printStackTrace();
//      }

      StringWriter stringWriter = new StringWriter();
      DotWriter dotWriter = new DotWriter();
      dotWriter.write(workspace, stringWriter);
      System.out.println(stringWriter);

      String dotText = stringWriter.toString();
      int pos = dotText.indexOf("digraph");
      dotText = dotText.substring(pos);

      try
      {
         MutableGraph g = Parser.read(dotText);
         Graphviz.fromGraph(g).width(600).render(Format.PNG).toFile(new File("doc/ex4-1.png"));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      story.dumpHTML();
   }
}
