package org.sdmlib.models.pattern;

import java.util.LinkedHashMap;

import de.uniks.networkparser.IdMap;

public class IsomorphismComputation
{

   public static LinkedHashMap<String, String> calculateMatch(Object graph1, Object graph2, IdMap creators)
   {
      IdMap map1 = new IdMap().withSessionId("s").with(creators);
      IdMap map2 = new IdMap().withSessionId("s").with(creators);

      ReachableState rs1 = new ReachableState().withGraphRoot(graph1);
      ReachableState rs2 = new ReachableState().withGraphRoot(graph2);

      String s1cert = rs1.computeCertificate(map1);
      String s2cert = rs2.computeCertificate(map2);

      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.setMasterMap( new IdMap().withSessionId("s").with(creators));

      return reachabilityGraph.match(rs1, rs2);
   }

   public static boolean isIsomorphic(Object graph1, Object graph2, IdMap creators)
   {
      return calculateMatch(graph1, graph2, creators) != null;
   }
}
