package org.sdmlib.models.pattern;

import de.uniks.networkparser.IdMap;

public class IsomorphismComputation
{

   public static boolean calculateMatch(Object graph1, Object graph2, IdMap creators)
   {
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.withMasterMap ( new IdMap().withSession("s").with(creators));

      ReachableState rs1 = new ReachableState().withGraphRoot(graph1).withParent(reachabilityGraph);
      ReachableState rs2 = new ReachableState().withGraphRoot(graph2).withParent(reachabilityGraph);

      Object s1cert = rs1.dynComputeCertificate();
      Object s2cert = rs2.dynComputeCertificate();

      
      return reachabilityGraph.lazyMatch(rs1, rs2) != null;
   }

   public static boolean isIsomorphic(Object graph1, Object graph2, IdMap creators)
   {
      return calculateMatch(graph1, graph2, creators);
   }
}
