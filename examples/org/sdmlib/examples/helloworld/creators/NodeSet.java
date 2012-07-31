package org.sdmlib.examples.helloworld.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.examples.helloworld.Edge;

public class NodeSet extends LinkedHashSet<Node>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Node obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public NodeSet withName(String value)
   {
      for (Node obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public GraphSet getGraph()
   {
      GraphSet result = new GraphSet();
      
      for (Node obj : this)
      {
         result.add(obj.getGraph());
      }
      
      return result;
   }
   public NodeSet withGraph(Graph value)
   {
      for (Node obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

   public EdgeSet getOutEdges()
   {
      EdgeSet result = new EdgeSet();
      
      for (Node obj : this)
      {
         result.addAll(obj.getOutEdges());
      }
      
      return result;
   }
   public NodeSet withOutEdges(Edge value)
   {
      for (Node obj : this)
      {
         obj.withOutEdges(value);
      }
      
      return this;
   }

   public NodeSet withoutOutEdges(Edge value)
   {
      for (Node obj : this)
      {
         obj.withoutOutEdges(value);
      }
      
      return this;
   }

   public EdgeSet getInEdges()
   {
      EdgeSet result = new EdgeSet();
      
      for (Node obj : this)
      {
         result.addAll(obj.getInEdges());
      }
      
      return result;
   }
   public NodeSet withInEdges(Edge value)
   {
      for (Node obj : this)
      {
         obj.withInEdges(value);
      }
      
      return this;
   }

   public NodeSet withoutInEdges(Edge value)
   {
      for (Node obj : this)
      {
         obj.withoutInEdges(value);
      }
      
      return this;
   }

}

