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
   
package org.sdmlib.test.examples.m2m.model.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.test.examples.m2m.model.Graph;
import org.sdmlib.test.examples.m2m.model.GraphComponent;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.m2m.model.util.GraphSet;

public class GraphComponentSet extends SimpleSet<GraphComponent>
{


   public GraphComponentPO hasGraphComponentPO()
   {
      return new GraphComponentPO(this.toArray(new GraphComponent[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public GraphComponentSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<GraphComponent>)value);
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
         neighbors.addAll((Collection<?>) value);
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


   public static final GraphComponentSet EMPTY_SET = new GraphComponentSet().withFlag(GraphComponentSet.READONLY);
   public GraphComponentSet hasText(String lower, String upper)
   {
      GraphComponentSet result = new GraphComponentSet();
      
      for (GraphComponent obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public GraphComponentPO filterGraphComponentPO()
   {
      return new GraphComponentPO(this.toArray(new GraphComponent[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.m2m.model.GraphComponent";
   }

   /**
    * Loop through the current set of GraphComponent objects and collect those GraphComponent objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GraphComponent objects that match the parameter
    */
   public GraphComponentSet filterText(String value)
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


   /**
    * Loop through the current set of GraphComponent objects and collect those GraphComponent objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GraphComponent objects that match the parameter
    */
   public GraphComponentSet filterText(String lower, String upper)
   {
      GraphComponentSet result = new GraphComponentSet();
      
      for (GraphComponent obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
