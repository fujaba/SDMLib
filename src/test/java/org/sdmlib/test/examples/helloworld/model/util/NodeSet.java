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

import org.sdmlib.test.examples.helloworld.model.Edge;
import org.sdmlib.test.examples.helloworld.model.Graph;
import org.sdmlib.test.examples.helloworld.model.Node;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class NodeSet extends SimpleSet<Node>
{
   public NodePO hasNodePO()
   {
      return new NodePO (this.toArray(new Node[this.size()]));
   }

   public NodeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
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


   public static final NodeSet EMPTY_SET = new NodeSet().withFlag(NodeSet.READONLY);
   public NodeSet hasName(String lower, String upper)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public NodeSet hasText(String lower, String upper)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public NodePO filterNodePO()
   {
      return new NodePO(this.toArray(new Node[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.helloworld.model.Node";
   }

   /**
    * Loop through the current set of Node objects and collect those Node objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet filterName(String value)
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


   /**
    * Loop through the current set of Node objects and collect those Node objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet filterName(String lower, String upper)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Node objects and collect those Node objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet filterText(String value)
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


   /**
    * Loop through the current set of Node objects and collect those Node objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet filterText(String lower, String upper)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public NodeSet()
   {
      // empty
   }

   public NodeSet(Node... objects)
   {
      for (Node obj : objects)
      {
         this.add(obj);
      }
   }

   public NodeSet(Collection<Node> objects)
   {
      this.addAll(objects);
   }


   public NodePO createNodePO()
   {
      return new NodePO(this.toArray(new Node[this.size()]));
   }


   @Override
   public NodeSet getNewList(boolean keyValue)
   {
      return new NodeSet();
   }

   /**
    * Loop through the current set of Node objects and collect those Node objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet createNameCondition(String value)
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


   /**
    * Loop through the current set of Node objects and collect those Node objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet createNameCondition(String lower, String upper)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Node objects and collect those Node objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet createTextCondition(String value)
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


   /**
    * Loop through the current set of Node objects and collect those Node objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Node objects that match the parameter
    */
   public NodeSet createTextCondition(String lower, String upper)
   {
      NodeSet result = new NodeSet();
      
      for (Node obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
