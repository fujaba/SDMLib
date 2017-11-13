package org.sdmlib.models.pattern;

import java.util.LinkedHashMap;

import de.uniks.networkparser.IdMap;

public class IsomorphismComputation
{

   public static LinkedHashMap<Object, Object> calculateMatch(Object graph1, Object graph2, IdMap creators)
   {
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.withMasterMap ( new IdMap().withSession("s").with(creators));

      ReachableState rs1 = new ReachableState().withGraphRoot(graph1).withParent(reachabilityGraph);
      ReachableState rs2 = new ReachableState().withGraphRoot(graph2).withParent(reachabilityGraph);

      String s1cert = rs1.lazyComputeCertificate();
      String s2cert = rs2.lazyComputeCertificate();

      
      return reachabilityGraph.lazyMatch(rs1, rs2);
   }

   public static boolean isIsomorphic(Object graph1, Object graph2, IdMap creators)
   {
      return calculateMatch(graph1, graph2, creators) != null;
   }
}
