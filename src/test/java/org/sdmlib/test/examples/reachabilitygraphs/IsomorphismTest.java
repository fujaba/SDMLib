package org.sdmlib.test.examples.reachabilitygraphs;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.sdmlib.models.pattern.IsomorphismComputation;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStateCreator;

public class IsomorphismTest
{
   @Test
   public void testIsomorphismCalculation()
   {
      Storyboard storyboard = new Storyboard();

      storyboard.add("Create two rings of three nodes with a mark at one node.");

      // graph 1
      SimpleState s11 = new SimpleState();
      Node n11 = s11.createNodes();
      Node n12 = s11.createNodes().withPrev(n11);
      Node n13 = s11.createNodes().withPrev(n12).withNext(n11);

      // graph2
      SimpleState s21 = new SimpleState();
      Node n21 = s21.createNodes();
      Node n22 = s21.createNodes().withPrev(n21);
      Node n23 = s21.createNodes().withPrev(n22).withNext(n21);

      // mark them at different places
      n21.withNum(42);
      n13.withNum(42);

      storyboard.addObjectDiagram(s11);

      storyboard.addObjectDiagram(s21);

      LinkedHashMap<Object, Object> match = IsomorphismComputation.calculateMatch(s11, s21,
         SimpleStateCreator.createIdMap("s"));

      storyboard.add("Graphs are isomorphic:");
      storyboard.add(match.toString());
      storyboard.assertTrue("Graphs are isomorphic", match != null);

      storyboard.add("removing the num");

      n21.withNum(0);

      n13.withNum(0);

      storyboard.addObjectDiagram(s11);

      storyboard.addObjectDiagram(s21);

      match = IsomorphismComputation.calculateMatch(s11, s21, SimpleStateCreator.createIdMap("s"));

      storyboard.add("Graphs are isomorphic:");
      storyboard.add(match.toString());
      storyboard.assertTrue("Graphs are isomorphic", match != null);
   }

}
