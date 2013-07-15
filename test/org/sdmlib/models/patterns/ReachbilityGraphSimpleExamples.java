package org.sdmlib.models.patterns;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.patterns.example.Node;
import org.sdmlib.models.patterns.example.SimpleState;
import org.sdmlib.models.patterns.example.creators.CreatorCreator;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;

public class ReachbilityGraphSimpleExamples
{
   @Test
   public void SimpleReachabilityGraphIsomorphismTest()
   {
      Scenario scenario = new Scenario("test");
      
      scenario.add("create two rings of three nodes with a mark at one node");
      
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
      
      JsonIdMap map = CreatorCreator.createIdMap("s");
      JsonIdMap map2 = CreatorCreator.createIdMap("t");
      
      // mark them at different places
      n21.withNum(42);
      
      n13.withNum(42);
      
      scenario.addObjectDiag(s11);
      
      scenario.addObjectDiag(s21);
      
      scenario.add("compute certificates");
      
      ReachableState rs1 = new ReachableState().withGraphRoot(s11);
      ReachableState rs2 = new ReachableState().withGraphRoot(s21);
      
      String s1cert = rs1.computeCertificate(map);
      String s2cert = rs2.computeCertificate(map2);
      
      scenario.add("Both certificates are equal: " + (s1cert.equals(s2cert)));
      
      scenario.add(s1cert);
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.setMasterMap(CreatorCreator.createIdMap("s"));
      
      LinkedHashMap<String, String> match = reachabilityGraph.match(rs1, rs2);
      
      scenario.add("Graphs are isomorphic:");
      scenario.add(match.toString());
      
      
      scenario.add("removing the num");
      
      n21.withNum(0);
      
      n13.withNum(0);
      
      scenario.addObjectDiag(s11);
      
      scenario.addObjectDiag(s21);
      
      s1cert = rs1.computeCertificate(map);
      s2cert = rs2.computeCertificate(map2);
      
      scenario.add("Both certificates are again equal: " + (s1cert.equals(s2cert)));
      
      scenario.add(s1cert);

      match = reachabilityGraph.match(rs1, rs2);
      
      scenario.add("Graphs are isomorphic:");
      scenario.add(match.toString());
      
      scenario.dumpHTML();
   }

   @Test
   public void ReachabilitGraphSameCertificatesNonIsomorphic()
   {
      Scenario scenario = new Scenario("test");
      
      scenario.add("graph 1 two rings of two nodes");
      scenario.add("graph 2 one ring of four nodes");
      
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
      
      JsonIdMap map = CreatorCreator.createIdMap("s");
      JsonIdMap map2 = CreatorCreator.createIdMap("t");
      
      scenario.addObjectDiag(s11);
      
      scenario.addObjectDiag(s21);
      
      scenario.add("compute certificates");
      
      ReachableState rs1 = new ReachableState().withGraphRoot(s11);
      ReachableState rs2 = new ReachableState().withGraphRoot(s21);
      
      String s1cert = rs1.computeCertificate(map);
      String s2cert = rs2.computeCertificate(map2);
      
      scenario.add("Both certificates are equal: " + (s1cert.equals(s2cert)));
      
      scenario.add(s1cert);
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.setMasterMap(CreatorCreator.createIdMap("s"));
      
      LinkedHashMap<String, String> match = reachabilityGraph.match(rs1, rs2);
      
      scenario.add("Graphs are not isomorphic:");
      scenario.add("" + match);
            
      scenario.dumpHTML();
   }

}
