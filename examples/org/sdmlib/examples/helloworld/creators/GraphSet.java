/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.examples.helloworld.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.examples.helloworld.Edge;
import org.sdmlib.examples.helloworld.Graph;
import org.sdmlib.examples.helloworld.GraphComponent;
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.models.modelsets.StringList;

public class GraphSet extends LinkedHashSet<Graph> implements org.sdmlib.models.modelsets.ModelSet
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

   public GraphComponentSet getGcs()
   {
      GraphComponentSet result = new GraphComponentSet();
      
      for (Graph obj : this)
      {
         result.addAll(obj.getGcs());
      }
      
      return result;
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



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Graph elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.helloworld.Graph";
   }


   public GraphPO startModelPattern()
   {
      org.sdmlib.examples.helloworld.creators.ModelPattern pattern = new org.sdmlib.examples.helloworld.creators.ModelPattern();
      
      GraphPO patternObject = pattern.hasElementGraphPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public GraphSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Graph>)value);
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

}




