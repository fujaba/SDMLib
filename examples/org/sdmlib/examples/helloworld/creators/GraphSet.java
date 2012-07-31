package org.sdmlib.examples.helloworld.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.examples.helloworld.Edge;

public class GraphSet extends LinkedHashSet<Graph>
{
   public NodeSet getNodes()
   {
      NodeSet result = new NodeSet();
      
      for (Graph obj : this)
      {
         result.addAll(obj.getNodes());
      }
      
      return result;
   }
   public GraphSet withNodes(Node value)
   {
      for (Graph obj : this)
      {
         obj.withNodes(value);
      }
      
      return this;
   }

   public GraphSet withoutNodes(Node value)
   {
      for (Graph obj : this)
      {
         obj.withoutNodes(value);
      }
      
      return this;
   }

   public EdgeSet getEdges()
   {
      EdgeSet result = new EdgeSet();
      
      for (Graph obj : this)
      {
         result.addAll(obj.getEdges());
      }
      
      return result;
   }
   public GraphSet withEdges(Edge value)
   {
      for (Graph obj : this)
      {
         obj.withEdges(value);
      }
      
      return this;
   }

   public GraphSet withoutEdges(Edge value)
   {
      for (Graph obj : this)
      {
         obj.withoutEdges(value);
      }
      
      return this;
   }

}

