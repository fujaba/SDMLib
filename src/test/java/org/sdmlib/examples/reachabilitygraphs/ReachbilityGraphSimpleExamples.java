package org.sdmlib.examples.reachabilitygraphs;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.sdmlib.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.examples.reachabilitygraphs.simplestates.SimpleState;
import org.sdmlib.examples.reachabilitygraphs.simplestates.util.SimpleStateCreator;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.json.JsonIdMap;

public class ReachbilityGraphSimpleExamples
{
   @Test
   public void ReachabilityGraphSimpleIsomorphismTest()
   {
      Storyboard storyboard = new Storyboard("test");
      
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
      
      JsonIdMap map = SimpleStateCreator.createIdMap("s");
      JsonIdMap map2 = SimpleStateCreator.createIdMap("s");
      
      // mark them at different places
      n21.withNum(42);
      
      n13.withNum(42);
      
      storyboard.addObjectDiagram(s11);
      
      storyboard.addObjectDiagram(s21);
      
      storyboard.add("compute certificates");
      
      ReachableState rs1 = new ReachableState().withGraphRoot(s11);
      ReachableState rs2 = new ReachableState().withGraphRoot(s21);
      
      String s1cert = rs1.computeCertificate(map);
      String s2cert = rs2.computeCertificate(map2);
      
      storyboard.assertTrue("Both certificates are equal. ", s1cert.equals(s2cert));
      
      storyboard.add(s1cert);
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.setMasterMap(SimpleStateCreator.createIdMap("s"));
      
      LinkedHashMap<String, String> match = reachabilityGraph.match(rs1, rs2);
      
      storyboard.add("Graphs are isomorphic:");
      storyboard.add(match.toString());
      
      
      storyboard.add("removing the num");
      
      n21.withNum(0);
      
      n13.withNum(0);
      
      storyboard.addObjectDiagram(s11);
      
      storyboard.addObjectDiagram(s21);
      
      s1cert = rs1.computeCertificate(map);
      s2cert = rs2.computeCertificate(map2);
      
      storyboard.assertTrue("Both certificates are again equal. ", s1cert.equals(s2cert));
      
      storyboard.add(s1cert);

      match = reachabilityGraph.match(rs1, rs2);
      
      storyboard.add("Graphs are isomorphic:");
      storyboard.add(match.toString());
      
      storyboard.dumpHTML();
   }

   @Test
   public void ReachabilitGraphSameCertificatesNonIsomorphic()
   {
      Storyboard storyboard = new Storyboard("test");
      
      storyboard.add("graph 1 two rings of two nodes");
      storyboard.add("graph 2 one ring of four nodes");
      
      // graph 1
      SimpleState s11 = new SimpleState();
      Node n11 = s11.createNodes();
      Node n12 = s11.createNodes().withPrev(n11).withNext(n11);
      Node n13 = s11.createNodes();
      Node n14 = s11.createNodes().withPrev(n13).withNext(n13);
      
      
      // graph2
      SimpleState s21 = new SimpleState();
      Node n21 = s21.createNodes();
      Node n22 = s21.createNodes().withPrev(n21);
      Node n23 = s21.createNodes().withPrev(n22);
      Node n24 = s21.createNodes().withPrev(n23).withNext(n21);
      
      JsonIdMap map = SimpleStateCreator.createIdMap("s");
      JsonIdMap map2 = SimpleStateCreator.createIdMap("t");
      
      storyboard.addObjectDiagram(s11);
      
      storyboard.addObjectDiagram(s21);
      
      storyboard.add("compute certificates");
      
      ReachableState rs1 = new ReachableState().withGraphRoot(s11);
      ReachableState rs2 = new ReachableState().withGraphRoot(s21);
      
      String s1cert = rs1.computeCertificate(map);
      String s2cert = rs2.computeCertificate(map2);
      
      storyboard.add("Both certificates are equal: " + (s1cert.equals(s2cert)));
      
      storyboard.add(s1cert);
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.setMasterMap(SimpleStateCreator.createIdMap("s"));
      
      LinkedHashMap<String, String> match = reachabilityGraph.match(rs1, rs2);
      
      storyboard.add("Graphs are not isomorphic:");
      storyboard.add("" + match);
            
      storyboard.dumpHTML();
   }

}
