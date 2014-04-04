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

package org.sdmlib.examples.m2m.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.examples.m2m.Graph;
import org.sdmlib.examples.m2m.GraphComponent;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.examples.m2m.creators.GraphSet;
import java.util.Collections;

public class GraphComponentSet extends LinkedHashSet<GraphComponent> implements
      org.sdmlib.models.modelsets.ModelSet
{
   public GraphComponent first()
   {
      for (GraphComponent obj : this)
      {
         return obj;
      }

      return null;
   }

   public String toString()
   {
      StringList stringList = new StringList();

      for (GraphComponent elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.examples.m2m.GraphComponent";
   }

   public StringList getText()
   {
      StringList result = new StringList();

      for (GraphComponent obj : this)
      {
         result.add(obj.getText());
      }

      return result;
   }

   public GraphComponentSet hasText(String value)
   {
      GraphComponentSet result = new GraphComponentSet();

      for (GraphComponent obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public GraphComponentSet withText(String value)
   {
      for (GraphComponent obj : this)
      {
         obj.setText(value);
      }

      return this;
   }

   public GraphSet getParent()
   {
      GraphSet result = new GraphSet();

      for (GraphComponent obj : this)
      {
         result.add(obj.getParent());
      }

      return result;
   }

   public GraphComponentSet hasParent(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
      }
      else
      {
         neighbors.add(value);
      }

      GraphComponentSet answer = new GraphComponentSet();

      for (GraphComponent obj : this)
      {
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public GraphComponentSet withParent(Graph value)
   {
      for (GraphComponent obj : this)
      {
         obj.withParent(value);
      }

      return this;
   }

   public GraphComponentPO startModelPattern()
   {
      org.sdmlib.examples.m2m.creators.ModelPattern pattern = new org.sdmlib.examples.m2m.creators.ModelPattern();

      GraphComponentPO patternObject = pattern.hasElementGraphComponentPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public GraphComponentSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<GraphComponent>) value);
      }
      else if (value != null)
      {
         this.add((GraphComponent) value);
      }

      return this;
   }

   public GraphComponentSet without(GraphComponent value)
   {
      this.remove(value);
      return this;
   }

   public GraphComponentPO hasGraphComponentPO()
   {
      org.sdmlib.examples.m2m.creators.ModelPattern pattern = new org.sdmlib.examples.m2m.creators.ModelPattern();

      GraphComponentPO patternObject = pattern.hasElementGraphComponentPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}
