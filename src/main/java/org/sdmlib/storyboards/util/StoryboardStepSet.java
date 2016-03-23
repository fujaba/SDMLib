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
   
package org.sdmlib.storyboards.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardStep;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.storyboards.util.StoryboardSet;

public class StoryboardStepSet extends SimpleSet<StoryboardStep> implements org.sdmlib.models.modelsets.ModelSet
{
   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (StoryboardStep elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.storyboards.StoryboardStep";
   }


   public StoryboardStepSet with(StoryboardStep value)
   {
      this.add(value);
      return this;
   }
   
   public StoryboardStepSet without(StoryboardStep value)
   {
      this.remove(value);
      return this;
   }
   public StringList getText()
   {
      StringList result = new StringList();
      
      for (StoryboardStep obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public StoryboardStepSet withText(String value)
   {
      for (StoryboardStep obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

   public StoryboardSet getStoryboard()
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardStep obj : this)
      {
         result.with(obj.getStoryboard());
      }
      
      return result;
   }

   public StoryboardStepSet withStoryboard(Storyboard value)
   {
      for (StoryboardStep obj : this)
      {
         obj.withStoryboard(value);
      }
      
      return this;
   }


   public StoryboardStep getFirst()
   {
      for (StoryboardStep obj : this)
      {
         return obj;
      }
      
      return null;
   }


   public StoryboardStep getLast()
   {
      Object[] array = this.toArray();
      if (array.length > 0)
      {
         return (StoryboardStep) array[array.length - 1];
      }
      return null;
   }



   public StoryboardStepPO startModelPattern()
   {
      return new StoryboardStepPO(this.toArray(new StoryboardStep[this.size()]));
   }


   public StoryboardStepSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((StoryboardStep) value);
      }
      
      return this;
   }
   



   public StoryboardStepPO hasStoryboardStepPO()
   {
      return new StoryboardStepPO (this.toArray(new StoryboardStep[this.size()]));
   }

   public static final StoryboardStepSet EMPTY_SET = new StoryboardStepSet().withFlag(StoryboardStepSet.READONLY);
   public StoryboardStepSet hasText(String value)
   {
      StoryboardStepSet result = new StoryboardStepSet();
      
      for (StoryboardStep obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StoryboardStepSet hasText(String lower, String upper)
   {
      StoryboardStepSet result = new StoryboardStepSet();
      
      for (StoryboardStep obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public StoryboardStepPO filterStoryboardStepPO()
   {
      return new StoryboardStepPO(this.toArray(new StoryboardStep[this.size()]));
   }

   /**
    * Loop through the current set of StoryboardStep objects and collect those StoryboardStep objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of StoryboardStep objects that match the parameter
    */
   public StoryboardStepSet filterText(String value)
   {
      StoryboardStepSet result = new StoryboardStepSet();
      
      for (StoryboardStep obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of StoryboardStep objects and collect those StoryboardStep objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of StoryboardStep objects that match the parameter
    */
   public StoryboardStepSet filterText(String lower, String upper)
   {
      StoryboardStepSet result = new StoryboardStepSet();
      
      for (StoryboardStep obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}




