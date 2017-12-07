/*
   Copyright (c) 2014 zuendorf 
   
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
import org.sdmlib.test.examples.helloworld.model.GraphComponent;
import org.sdmlib.test.examples.helloworld.model.Node;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.helloworld.model.util.NodeSet;

public class GraphSet extends SimpleSet<Graph>
{
        private static final long serialVersionUID = 1L;


   public GraphPO hasGraphPO()
   {
      return new GraphPO (this.toArray(new Graph[this.size()]));
   }

   public GraphSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
           Collection<?> collection = (Collection<?>) value;
           for(Object item : collection){
               this.add((Graph) item);
           }
      }
      else if (value != null)
      {
         this.add((Graph) value);
      }
      
      return this;
   }
   
   public GraphSet without(Graph value)
   {
      this.remove(value);
      return this;
   }

   public NodeSet getNodes()
   {
      NodeSet result = new NodeSet();
      
      for (Graph obj : this)
      {
         result.with(obj.getNodes());
      }
      
      return result;
   }

   public GraphSet hasNodes(Object value)
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
      
      GraphSet answer = new GraphSet();
      
      for (Graph obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getNodes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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
         result.with(obj.getEdges());
      }
      
      return result;
   }

   public GraphSet hasEdges(Object value)
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
      
      GraphSet answer = new GraphSet();
      
      for (Graph obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getEdges()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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

   public GraphComponentSet getGcs()
   {
      GraphComponentSet result = new GraphComponentSet();
      
      for (Graph obj : this)
      {
         result.with(obj.getGcs());
      }
      
      return result;
   }

   public GraphSet hasGcs(Object value)
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
      
      GraphSet answer = new GraphSet();
      
      for (Graph obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getGcs()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public GraphSet withGcs(GraphComponent value)
   {
      for (Graph obj : this)
      {
         obj.withGcs(value);
      }
      
      return this;
   }

   public GraphSet withoutGcs(GraphComponent value)
   {
      for (Graph obj : this)
      {
         obj.withoutGcs(value);
      }
      
      return this;
   }


   public static final GraphSet EMPTY_SET = new GraphSet().withFlag(GraphSet.READONLY);


   public GraphPO filterGraphPO()
   {
      return new GraphPO(this.toArray(new Graph[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.helloworld.model.Graph";
   }

   public GraphSet()
   {
      // empty
   }

   public GraphSet(Graph... objects)
   {
      for (Graph obj : objects)
      {
         this.add(obj);
      }
   }

   public GraphSet(Collection<Graph> objects)
   {
      this.addAll(objects);
   }


   public GraphPO createGraphPO()
   {
      return new GraphPO(this.toArray(new Graph[this.size()]));
   }


   @Override
   public GraphSet getNewList(boolean keyValue)
   {
      return new GraphSet();
   }
}