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
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.MutableGraph;
import guru.nidi.graphviz.parse.Parser;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class TestStructurizr
{
   /**
    * PNG Image:
    * <img src="doc-files/alicebob.png" alt="JavaDocStoriesMikadoPlanStep0.png" usemap="#planetmap">
    * <map name="planetmap">
    *   <area shape="rect" coords="0,0,182,126" href="sun.htm" alt="Sun">
    *   <area shape="circle" coords="90,58,3" href="mercur.htm" alt="Mercury">
    *   <area shape="circle" coords="124,58,8" href="venus.htm" alt="Venus">
    * </map>
    */
   @Test
   public void testStructurizrDiagrams()
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.add("Capture some architecture information with structurizr");

      Workspace workspace = new Workspace("Getting Started", "This is a model of my software system.");
      Model model = workspace.getModel();

      Person alice = model.addPerson("Alice", "Alice enters the data.");
      Person bob = model.addPerson("Bob", "Bob pays his share.");
      SoftwareSystem softwareSystem = model.addSoftwareSystem("GroupAccount", "Doing accounting for groups.");
      alice.uses(softwareSystem, "uses");
      bob.uses(softwareSystem, "uses");

      ViewSet views = workspace.getViews();
      SystemContextView contextView = views.createSystemContextView(softwareSystem, "SystemContext", "A System Context diagram.");
      contextView.addAllSoftwareSystems();
      contextView.addAllPeople();


      try
      {
         Graph g = graph("example1").directed().with(node("a").link(node("b")));
         Graphviz.fromGraph(g).width(200).render(Format.PNG).toFile(new File("doc/doc-files/ex1.png"));      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      // story.dumpHTML();
   }
}
