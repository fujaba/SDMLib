package org.sdmlib.test.examples.reachabilitygraphs;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.sdmlib.models.pattern.LazyCloneOp;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.storyboards.StoryPage;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodePO;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodeSet;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStateCreator;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStatePO;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStateSet;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.list.SimpleKeyValueList;

public class ReachbilityGraphSimpleExamples
{
   
   @Test
   public void LazyReachabilityGraphAttrsAndNodes() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");
      
      SimpleState root = new SimpleState();
      
      Node kid1 = root.createNodes().withNum(42);

      Node kid2 = root.createNodes().withNum(4);

      kid1.withNext(kid2);
      
      story.add("Start graph: ");
      story.addObjectDiagram(root);
      
      // nodes rule
      SimpleStatePO rootPO1 = new SimpleStatePO();
      
      NodePO kidPO = rootPO1.createNodesPO()
            .createNumCondition(4);
      
      kidPO.destroy();
      
      NodePO newKid = rootPO1.createNodesPO(Pattern.CREATE)
            .createNumAssignment(23);
      
      
      // attr rule
      SimpleStatePO rootPO2 = new SimpleStatePO();
      
      kidPO = rootPO2.createNodesPO()
            .createNumCondition(23)
            .createNumAssignment(42);
      
      story.add("Rewrite rule: ");
      story.addPattern(rootPO2, true);
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
            .withMasterMap(SimpleStateCreator.createIdMap("s"))
            .withLazyCloning()
            .withRules(rootPO1, rootPO2);
      
      ReachableState startState = new ReachableState().withGraphRoot(root);
      
      reachabilityGraph.withStart(startState);
      
      reachabilityGraph.explore();
      
      story.addObjectDiagram(reachabilityGraph);
      
      story.assertEquals("number of reachable states", 3, reachabilityGraph.getStates().size());
      
      NodeSet nodes = reachabilityGraph.getStates().getGraphRoot().instanceOf(new SimpleStateSet()).getNodes();
      story.assertEquals("number of nodes building the two graphs", 4, nodes.size());
      
      story.dumpHTML();
   }
   /**
    * @see <a href='../../../../../../../../doc/internal/ReachabilityGraphSimpleIsomorphismTest.html'>ReachabilityGraphSimpleIsomorphismTest.html</a>
    */
   @Test
   public void ReachabilityGraphSimpleIsomorphismTest()
   {
      Storyboard storyboard = new Storyboard().withDocDirName("doc/internal");
      
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
      
      storyboard.add("compute certificates");
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.setMasterMap(SimpleStateCreator.createIdMap("s"));
      
      ReachableState rs1 = new ReachableState().withGraphRoot(s11).withParent(reachabilityGraph);
      ReachableState rs2 = new ReachableState().withGraphRoot(s21).withParent(reachabilityGraph);
      
      Object s1cert = rs1.lazyComputeCertificate();
      Object s2cert = rs2.lazyComputeCertificate();
      
      storyboard.assertTrue("Both certificates are equal. ", s1cert.equals(s2cert));
      
      storyboard.add(s1cert.toString());
      
      SimpleKeyValueList<Object, Object> match = reachabilityGraph.lazyMatch(rs1, rs2);
      
      storyboard.assertNotNull("Graphs are isomorphic:", match);
      storyboard.add(match.toString());
      
      
      storyboard.add("removing the num");
      
      n21.withNum(0);
      
      n13.withNum(0);
      
      storyboard.addObjectDiagram(s11);
      
      storyboard.addObjectDiagram(s21);
      
      s1cert = rs1.lazyComputeCertificate();
      s2cert = rs2.lazyComputeCertificate();
      
      storyboard.assertTrue("Both certificates are again equal. ", s1cert.equals(s2cert));
      
      storyboard.add(s1cert.toString());

      match = reachabilityGraph.lazyMatch(rs1, rs2);
      
      storyboard.assertNotNull("Graphs are isomorphic:", match);
      
      storyboard.dumpHTML();
   }

    /**
    * 
    * @see <a href='../../../../../../../../doc/internal/ReachabilitGraphSameCertificatesNonIsomorphic.html'>ReachabilitGraphSameCertificatesNonIsomorphic.html</a>
 */
   @Test
   public void ReachabilitGraphSameCertificatesNonIsomorphic()
   {
      Storyboard storyboard = new Storyboard().withDocDirName("doc/internal");
      
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
      
      
      storyboard.addObjectDiagram(s11);
      
      storyboard.addObjectDiagram(s21);
      
      storyboard.add("compute certificates");
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.setMasterMap(SimpleStateCreator.createIdMap("s"));
      
      ReachableState rs1 = new ReachableState().withGraphRoot(s11).withParent(reachabilityGraph);
      ReachableState rs2 = new ReachableState().withGraphRoot(s21).withParent(reachabilityGraph);
      
      Object s1cert = rs1.lazyComputeCertificate();
      Object s2cert = rs2.lazyComputeCertificate();
      
      storyboard.add("Both certificates are equal: " + (s1cert.equals(s2cert)));
      
      storyboard.add(s1cert.toString());
      
      SimpleKeyValueList<Object, Object> match = reachabilityGraph.lazyMatch(rs1, rs2);
      
      storyboard.assertFalse("Graphs are not isomorphic:", false);
            
      storyboard.dumpHTML();
   }

}
