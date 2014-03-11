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
   
package org.sdmlib.replication.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.TaskFlowBoard;
import java.util.Collection;
import org.sdmlib.replication.creators.LaneSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class TaskFlowBoardSet extends LinkedHashSet<TaskFlowBoard> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (TaskFlowBoard elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.TaskFlowBoard";
   }


   public LaneSet getLanes()
   {
      LaneSet result = new LaneSet();
      
      for (TaskFlowBoard obj : this)
      {
         result.addAll(obj.getLanes());
      }
      
      return result;
   }

   public TaskFlowBoardSet withLanes(Lane value)
   {
      for (TaskFlowBoard obj : this)
      {
         obj.withLanes(value);
      }
      
      return this;
   }

   public TaskFlowBoardSet withoutLanes(Lane value)
   {
      for (TaskFlowBoard obj : this)
      {
         obj.withoutLanes(value);
      }
      
      return this;
   }


   public TaskFlowBoardPO hasTaskFlowBoardPO()
   {
      org.sdmlib.replication.creators.ModelPattern pattern = new org.sdmlib.replication.creators.ModelPattern();
      
      TaskFlowBoardPO patternObject = pattern.hasElementTaskFlowBoardPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public TaskFlowBoardSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TaskFlowBoard>)value);
      }
      else if (value != null)
      {
         this.add((TaskFlowBoard) value);
      }
      
      return this;
   }
   
   public TaskFlowBoardSet without(TaskFlowBoard value)
   {
      this.remove(value);
      return this;
   }

}



