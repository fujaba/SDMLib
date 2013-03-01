package org.sdmlib.examples.helloworld.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.helloworld.Edge;
import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.models.modelsets.StringList;

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

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (Node obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public NodeSet withText(String value)
   {
      for (Node obj : this)
      {
         obj.withText(value);
      }
      
      return this;
   }

   public NodeSet getOrig()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.add(obj.getOrig());
      }
      
      return result;
   }
   public NodeSet withOrig(Node value)
   {
      for (Node obj : this)
      {
         obj.withOrig(value);
      }
      
      return this;
   }

   public NodeSet getCopy()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.add(obj.getCopy());
      }
      
      return result;
   }
   public NodeSet withCopy(Node value)
   {
      for (Node obj : this)
      {
         obj.withCopy(value);
      }
      
      return this;
   }

   public NodeSet getLinksTo()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.addAll(obj.getLinksTo());
      }
      
      return result;
   }
   public NodeSet withLinksTo(Node value)
   {
      for (Node obj : this)
      {
         obj.withLinksTo(value);
      }
      
      return this;
   }

   public NodeSet withoutLinksTo(Node value)
   {
      for (Node obj : this)
      {
         obj.withoutLinksTo(value);
      }
      
      return this;
   }

   public NodeSet getLinksFrom()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.addAll(obj.getLinksFrom());
      }
      
      return result;
   }
   public NodeSet withLinksFrom(Node value)
   {
      for (Node obj : this)
      {
         obj.withLinksFrom(value);
      }
      
      return this;
   }

   public NodeSet withoutLinksFrom(Node value)
   {
      for (Node obj : this)
      {
         obj.withoutLinksFrom(value);
      }
      
      return this;
   }

}





