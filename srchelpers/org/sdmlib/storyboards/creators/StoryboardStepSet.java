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
   
package org.sdmlib.storyboards.creators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

import org.sdmlib.storyboards.StoryboardStep;
import org.sdmlib.models.modelsets.StringList;

import java.util.List;

import org.sdmlib.storyboards.creators.StoryboardSet;
import org.sdmlib.storyboards.Storyboard;

public class StoryboardStepSet extends LinkedHashSet<StoryboardStep> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (StoryboardStep elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


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
         result.add(obj.getStoryboard());
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

}

