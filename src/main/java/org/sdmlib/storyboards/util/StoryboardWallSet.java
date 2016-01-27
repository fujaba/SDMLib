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
   
package org.sdmlib.storyboards.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardWall;

import de.uniks.networkparser.list.SimpleSet;

public class StoryboardWallSet extends SimpleSet<StoryboardWall>
{
   public StoryboardWallPO hasStoryboardWallPO()
   {
      return new StoryboardWallPO (this.toArray(new StoryboardWall[this.size()]));
   }

   public StoryboardWallSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((StoryboardWall) value);
      }
      
      return this;
   }
   
   public StoryboardWallSet without(StoryboardWall value)
   {
      this.remove(value);
      return this;
   }

   public StoryboardSet getStoryboard()
   {
      StoryboardSet result = new StoryboardSet();
      
      for (StoryboardWall obj : this)
      {
         result.with(obj.getStoryboard());
      }
      
      return result;
   }

   public StoryboardWallSet hasStoryboard(Object value)
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
      
      StoryboardWallSet answer = new StoryboardWallSet();
      
      for (StoryboardWall obj : this)
      {
         if (neighbors.contains(obj.getStoryboard()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public StoryboardWallSet withStoryboard(Storyboard value)
   {
      for (StoryboardWall obj : this)
      {
         obj.withStoryboard(value);
      }
      
      return this;
   }


   public static final StoryboardWallSet EMPTY_SET = new StoryboardWallSet().withFlag(StoryboardWallSet.READONLY);
}




