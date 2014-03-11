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
import org.sdmlib.examples.helloworld.Node;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.helloworld.creators.GraphSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.examples.helloworld.creators.NodeSet;

public class EdgeSet extends LinkedHashSet<Edge> implements org.sdmlib.models.modelsets.ModelSet
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



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Edge elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.helloworld.Edge";
   }


   public GraphSet getParent()
   {
      GraphSet result = new GraphSet();
      
      for (Edge obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public EdgeSet withParent(Graph value)
   {
      for (Edge obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }



   public EdgePO startModelPattern()
   {
      org.sdmlib.examples.helloworld.creators.ModelPattern pattern = new org.sdmlib.examples.helloworld.creators.ModelPattern();
      
      EdgePO patternObject = pattern.hasElementEdgePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public EdgeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Edge>)value);
      }
      else if (value != null)
      {
         this.add((Edge) value);
      }
      
      return this;
   }
   
   public EdgeSet without(Edge value)
   {
      this.remove(value);
      return this;
   }



   public EdgePO hasEdgePO()
   {
      org.sdmlib.examples.helloworld.creators.ModelPattern pattern = new org.sdmlib.examples.helloworld.creators.ModelPattern();
      
      EdgePO patternObject = pattern.hasElementEdgePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}



























