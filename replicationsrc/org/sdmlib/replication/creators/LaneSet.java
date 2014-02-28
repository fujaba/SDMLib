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
import org.sdmlib.replication.Lane;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.replication.creators.TaskFlowBoardSet;
import org.sdmlib.replication.TaskFlowBoard;
import org.sdmlib.replication.creators.BoardTaskSet;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.creators.ObjectSet;
import java.lang.Object;
import java.beans.PropertyChangeSupport;
import java.util.Collection;

public class LaneSet extends LinkedHashSet<Lane> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Lane elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.Lane";
   }


   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Lane obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public LaneSet withName(String value)
   {
      for (Lane obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public TaskFlowBoardSet getBoard()
   {
      TaskFlowBoardSet result = new TaskFlowBoardSet();
      
      for (Lane obj : this)
      {
         result.add(obj.getBoard());
      }
      
      return result;
   }

   public LaneSet withBoard(TaskFlowBoard value)
   {
      for (Lane obj : this)
      {
         obj.withBoard(value);
      }
      
      return this;
   }

   public BoardTaskSet getTasks()
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (Lane obj : this)
      {
         result.addAll(obj.getTasks());
      }
      
      return result;
   }

   public LaneSet withTasks(BoardTask value)
   {
      for (Lane obj : this)
      {
         obj.withTasks(value);
      }
      
      return this;
   }

   public LaneSet withoutTasks(BoardTask value)
   {
      for (Lane obj : this)
      {
         obj.withoutTasks(value);
      }
      
      return this;
   }



   public LanePO hasLanePO()
   {
      org.sdmlib.replication.creators.ModelPattern pattern = new org.sdmlib.replication.creators.ModelPattern();
      
      LanePO patternObject = pattern.hasElementLanePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public LaneSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Lane>)value);
      }
      else if (value != null)
      {
         this.add((Lane) value);
      }
      
      return this;
   }
   
   public LaneSet without(Lane value)
   {
      this.remove(value);
      return this;
   }

}




