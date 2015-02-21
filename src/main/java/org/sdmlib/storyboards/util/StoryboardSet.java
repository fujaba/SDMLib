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
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardStep;
import org.sdmlib.storyboards.StoryboardWall;
import org.sdmlib.storyboards.util.StoryboardWallSet;
import org.sdmlib.storyboards.util.StoryboardStepSet;

public class StoryboardSet extends SDMSet<Storyboard> implements org.sdmlib.models.modelsets.ModelSet
{
   private static final long serialVersionUID = 1L;

   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Storyboard elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.storyboards.Storyboard";
   }


   public StoryboardSet with(Storyboard value)
   {
      this.add(value);
      return this;
   }
   
   public StoryboardSet without(Storyboard value)
   {
      this.remove(value);
      return this;
   }
   
   public StoryboardStepSet getStoryboardSteps()
   {
      StoryboardStepSet result = new StoryboardStepSet();
      
      for (Storyboard obj : this)
      {
         result.with(obj.getStoryboardSteps());
      }
      
      return result;
   }

   public StoryboardSet withStoryboardSteps(StoryboardStep value)
   {
      for (Storyboard obj : this)
      {
         obj.withStoryboardSteps(value);
      }
      
      return this;
   }

   public StoryboardSet withoutStoryboardSteps(StoryboardStep value)
   {
      for (Storyboard obj : this)
      {
         obj.withoutStoryboardSteps(value);
      }
      
      return this;
   }

//   public StoryboardWallSet getWall()
//   {
//      StoryboardWallSet result = new StoryboardWallSet();
//      
//      for (Storyboard obj : this)
//      {
//         result.with(obj.getWall());
//      }
//      
//      return result;
//   }
//
//   public StoryboardSet withWall(StoryboardWall value)
//   {
//      for (Storyboard obj : this)
//      {
//         obj.withWall(value);
//      }
//      
//      return this;
//   }

   public StringList getRootDir()
   {
      StringList result = new StringList();
      
      for (Storyboard obj : this)
      {
         result.add(obj.getRootDir());
      }
      
      return result;
   }

   public StoryboardSet withRootDir(String value)
   {
      for (Storyboard obj : this)
      {
         obj.setRootDir(value);
      }
      
      return this;
   }

   public intList getStepCounter()
   {
      intList result = new intList();
      
      for (Storyboard obj : this)
      {
         result.add(obj.getStepCounter());
      }
      
      return result;
   }

   public StoryboardSet withStepCounter(int value)
   {
      for (Storyboard obj : this)
      {
         obj.setStepCounter(value);
      }
      
      return this;
   }

   public intList getStepDoneCounter()
   {
      intList result = new intList();
      
      for (Storyboard obj : this)
      {
         result.add(obj.getStepDoneCounter());
      }
      
      return result;
   }

   public StoryboardSet withStepDoneCounter(int value)
   {
      for (Storyboard obj : this)
      {
         obj.setStepDoneCounter(value);
      }
      
      return this;
   }



   public StoryboardPO startModelPattern()
   {
      return new StoryboardPO(this.toArray(new Storyboard[this.size()]));
   }


   public StoryboardSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Storyboard>)value);
      }
      else if (value != null)
      {
         this.add((Storyboard) value);
      }
      
      return this;
   }

   public StoryboardPO hasStoryboardPO()
   {
      return new StoryboardPO (this.toArray(new Storyboard[this.size()]));
   }

   public static final StoryboardSet EMPTY_SET = new StoryboardSet().withReadonly(true);
}




