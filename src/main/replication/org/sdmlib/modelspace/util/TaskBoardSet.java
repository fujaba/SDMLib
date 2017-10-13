/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.modelspace.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.modelspace.TaskBoard;
import org.sdmlib.modelspace.TaskLane;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.modelspace.util.TaskLaneSet;

public class TaskBoardSet extends SimpleSet<TaskBoard>
{

   public static final TaskBoardSet EMPTY_SET = new TaskBoardSet().withFlag(TaskBoardSet.READONLY);


   public TaskBoardPO hasTaskBoardPO()
   {
      return new TaskBoardPO(this.toArray(new TaskBoard[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.modelspace.TaskBoard";
   }


   @SuppressWarnings("unchecked")
   public TaskBoardSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TaskBoard>)value);
      }
      else if (value != null)
      {
         this.add((TaskBoard) value);
      }
      
      return this;
   }
   
   public TaskBoardSet without(TaskBoard value)
   {
      this.remove(value);
      return this;
   }

   public TaskLaneSet getLanes()
   {
      TaskLaneSet result = new TaskLaneSet();
      
      for (TaskBoard obj : this)
      {
         result.addAll(obj.getLanes());
      }
      
      return result;
   }

   public TaskBoardSet hasLanes(Object value)
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
      
      TaskBoardSet answer = new TaskBoardSet();
      
      for (TaskBoard obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getLanes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TaskBoardSet withLanes(TaskLane value)
   {
      for (TaskBoard obj : this)
      {
         obj.withLanes(value);
      }
      
      return this;
   }

   public TaskBoardSet withoutLanes(TaskLane value)
   {
      for (TaskBoard obj : this)
      {
         obj.withoutLanes(value);
      }
      
      return this;
   }



   public TaskBoardPO filterTaskBoardPO()
   {
      return new TaskBoardPO(this.toArray(new TaskBoard[this.size()]));
   }

   public TaskBoardSet()
   {
      // empty
   }

   public TaskBoardSet(TaskBoard... objects)
   {
      for (TaskBoard obj : objects)
      {
         this.add(obj);
      }
   }

   public TaskBoardSet(Collection<TaskBoard> objects)
   {
      this.addAll(objects);
   }


   public TaskBoardPO createTaskBoardPO()
   {
      return new TaskBoardPO(this.toArray(new TaskBoard[this.size()]));
   }


   @Override
   public TaskBoardSet getNewList(boolean keyValue)
   {
      return new TaskBoardSet();
   }


   public TaskBoardSet filter(Condition<TaskBoard> condition) {
      TaskBoardSet filterList = new TaskBoardSet();
      filterItems(filterList, condition);
      return filterList;
   }}
