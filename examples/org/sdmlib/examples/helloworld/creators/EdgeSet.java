package org.sdmlib.examples.helloworld.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.helloworld.Edge;
import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.models.modelsets.StringList;

public class EdgeSet extends LinkedHashSet<Edge>
{
   public GraphSet getGraph()
   {
      GraphSet result = new GraphSet();
      
      for (Edge obj : this)
      {
         result.add(obj.getGraph());
      }
      
      return result;
   }
   public EdgeSet withGraph(Graph value)
   {
      for (Edge obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

   public NodeSet getSrc()
   {
      NodeSet result = new NodeSet();
      
      for (Edge obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }
   public EdgeSet withSrc(Node value)
   {
      for (Edge obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public NodeSet getTgt()
   {
      NodeSet result = new NodeSet();
      
      for (Edge obj : this)
      {
         result.add(obj.getTgt());
      }
      
      return result;
   }
   public EdgeSet withTgt(Node value)
   {
      for (Edge obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Edge obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public EdgeSet withName(String value)
   {
      for (Edge obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (Edge obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public EdgeSet withText(String value)
   {
      for (Edge obj : this)
      {
         obj.withText(value);
      }
      
      return this;
   }

}



