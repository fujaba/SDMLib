/*
   Copyright (c) 2014 Stefan 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.test.examples.helloworld.model.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.test.examples.helloworld.model.Edge;
import org.sdmlib.test.examples.helloworld.model.Graph;
import org.sdmlib.test.examples.helloworld.model.Node;
import org.sdmlib.test.examples.helloworld.model.util.EdgeSet;
import org.sdmlib.test.examples.helloworld.model.util.GraphSet;

public class NodeSet extends SDMSet<Node>
{


   public NodePO hasNodePO()
   {
      return new NodePO (this.toArray(new Node[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.test.examples.helloworld.model.Node";
   }


   public NodeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Node>)value);
      }
      else if (value != null)
      {
         this.add((Node) value);
      }
      
      return this;
   }
   
   public NodeSet without(Node value)
   {
      this.remove(value);
      return this;
   }

   public NodeSet getCopy()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.with(obj.getCopy());
      }
      
      return result;
   }

   public NodeSet hasCopy(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if (neighbors.contains(obj.getCopy()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public NodeSet getCopyTransitive()
   {
      NodeSet todo = new NodeSet().with(this);
      
      NodeSet result = new NodeSet();
      
      while ( ! todo.isEmpty())
      {
         Node current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getCopy()))
            {
               todo.with(current.getCopy());
            }
         }
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

   public NodeSet getOrig()
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         result.with(obj.getOrig());
      }
      
      return result;
   }

   public NodeSet hasOrig(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if (neighbors.contains(obj.getOrig()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public NodeSet getOrigTransitive()
   {
      NodeSet todo = new NodeSet().with(this);
      
      NodeSet result = new NodeSet();
      
      while ( ! todo.isEmpty())
      {
         Node current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getOrig()))
            {
               todo.with(current.getOrig());
            }
         }
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

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Node obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public NodeSet hasName(String value)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public NodeSet withName(String value)
   {
      for (Node obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public GraphSet getGraph()
   {
      GraphSet result = new GraphSet();
      
      for (Node obj : this)
      {
         result.with(obj.getGraph());
      }
      
      return result;
   }

   public NodeSet hasGraph(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if (neighbors.contains(obj.getGraph()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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
         result.with(obj.getOutEdges());
      }
      
      return result;
   }

   public NodeSet hasOutEdges(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getOutEdges()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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
         result.with(obj.getInEdges());
      }
      
      return result;
   }

   public NodeSet hasInEdges(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getInEdges()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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

   public NodeSet hasText(String value)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public NodeSet withText(String value)
   {
      for (Node obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

   public GraphSet getParent()
   {
      GraphSet result = new GraphSet();
      
      for (Node obj : this)
      {
         result.with(obj.getParent());
      }
      
      return result;
   }

   public NodeSet hasParent(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public NodeSet withParent(Graph value)
   {
      for (Node obj : this)
      {
         obj.withParent(value);
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

   public NodeSet hasLinksTo(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getLinksTo()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public NodeSet getLinksToTransitive()
   {
      NodeSet todo = new NodeSet().with(this);
      
      NodeSet result = new NodeSet();
      
      while ( ! todo.isEmpty())
      {
         Node current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getLinksTo().minus(result));
         }
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

   public NodeSet hasLinksFrom(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      NodeSet answer = new NodeSet();
      
      for (Node obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getLinksFrom()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public NodeSet getLinksFromTransitive()
   {
      NodeSet todo = new NodeSet().with(this);
      
      NodeSet result = new NodeSet();
      
      while ( ! todo.isEmpty())
      {
         Node current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getLinksFrom().minus(result));
         }
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


   public static final NodeSet EMPTY_SET = new NodeSet().withReadOnly(true);
}








































































