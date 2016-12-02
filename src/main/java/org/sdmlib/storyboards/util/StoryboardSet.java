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

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.storyboards.StoryboardImpl;
import org.sdmlib.storyboards.StoryboardStep;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class StoryboardSet extends SimpleSet<StoryboardImpl> implements org.sdmlib.models.modelsets.ModelSet
{
   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (StoryboardImpl elem : this)
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


   public StoryboardSet with(StoryboardImpl value)
   {
      this.add(value);
      return this;
   }
   
   public StoryboardSet without(StoryboardImpl value)
   {
      this.remove(value);
      return this;
   }
   
   public StoryboardStepSet getStoryboardSteps()
   {
      StoryboardStepSet result = new StoryboardStepSet();
      
      for (StoryboardImpl obj : this)
      {
         result.with(obj.getStoryboardSteps());
      }
      
      return result;
   }

   public StoryboardSet withStoryboardSteps(StoryboardStep value)
   {
      for (StoryboardImpl obj : this)
      {
         obj.withStoryboardSteps(value);
      }
      
      return this;
   }

   public StoryboardSet withoutStoryboardSteps(StoryboardStep value)
   {
      for (StoryboardImpl obj : this)
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
      
      for (StoryboardImpl obj : this)
      {
         result.add(obj.getRootDir());
      }
      
      return result;
   }

   public StoryboardSet withRootDir(String value)
   {
      for (StoryboardImpl obj : this)
      {
         obj.setRootDir(value);
      }
      
      return this;
   }

   public intList getStepCounter()
   {
      intList result = new intList();
      
      for (StoryboardImpl obj : this)
      {
         result.add(obj.getStepCounter());
      }
      
      return result;
   }

   public StoryboardSet withStepCounter(int value)
   {
      for (StoryboardImpl obj : this)
      {
         obj.setStepCounter(value);
      }
      
      return this;
   }

   public intList getStepDoneCounter()
   {
      intList result = new intList();
      
      for (StoryboardImpl obj : this)
      {
         result.add(obj.getStepDoneCounter());
      }
      
      return result;
   }

   public StoryboardSet withStepDoneCounter(int value)
   {
      for (StoryboardImpl obj : this)
      {
         obj.setStepDoneCounter(value);
      }
      
      return this;
   }



   public StoryboardPO startModelPattern()
   {
      return new StoryboardPO(this.toArray(new StoryboardImpl[this.size()]));
   }


   public StoryboardSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((StoryboardImpl) value);
      }
      
      return this;
   }

   public StoryboardPO hasStoryboardPO()
   {
      return new StoryboardPO (this.toArray(new StoryboardImpl[this.size()]));
   }

   public static final StoryboardSet EMPTY_SET = new StoryboardSet().withFlag(StoryboardSet.READONLY);
   
   public StoryboardSet hasRootDir(String value)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (value.equals(obj.getRootDir()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StoryboardSet hasRootDir(String lower, String upper)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (lower.compareTo(obj.getRootDir()) <= 0 && obj.getRootDir().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StoryboardSet hasStepCounter(int value)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (value == obj.getStepCounter())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StoryboardSet hasStepCounter(int lower, int upper)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (lower <= obj.getStepCounter() && obj.getStepCounter() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StoryboardSet hasStepDoneCounter(int value)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (value == obj.getStepDoneCounter())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StoryboardSet hasStepDoneCounter(int lower, int upper)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (lower <= obj.getStepDoneCounter() && obj.getStepDoneCounter() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public StoryboardPO filterStoryboardPO()
   {
      return new StoryboardPO(this.toArray(new StoryboardImpl[this.size()]));
   }

   /**
    * Loop through the current set of Storyboard objects and collect those Storyboard objects where the rootDir attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Storyboard objects that match the parameter
    */
   public StoryboardSet filterRootDir(String value)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (value.equals(obj.getRootDir()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Storyboard objects and collect those Storyboard objects where the rootDir attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Storyboard objects that match the parameter
    */
   public StoryboardSet filterRootDir(String lower, String upper)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (lower.compareTo(obj.getRootDir()) <= 0 && obj.getRootDir().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Storyboard objects and collect those Storyboard objects where the stepCounter attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Storyboard objects that match the parameter
    */
   public StoryboardSet filterStepCounter(int value)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (value == obj.getStepCounter())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Storyboard objects and collect those Storyboard objects where the stepCounter attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Storyboard objects that match the parameter
    */
   public StoryboardSet filterStepCounter(int lower, int upper)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (lower <= obj.getStepCounter() && obj.getStepCounter() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Storyboard objects and collect those Storyboard objects where the stepDoneCounter attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Storyboard objects that match the parameter
    */
   public StoryboardSet filterStepDoneCounter(int value)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (value == obj.getStepDoneCounter())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Storyboard objects and collect those Storyboard objects where the stepDoneCounter attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Storyboard objects that match the parameter
    */
   public StoryboardSet filterStepDoneCounter(int lower, int upper)
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardImpl obj : this)
      {
         if (lower <= obj.getStepDoneCounter() && obj.getStepDoneCounter() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}




