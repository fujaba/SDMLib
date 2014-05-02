/*
   Copyright (c) 2014 christian 
   
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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.replication.Lane;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import java.util.List;
import org.sdmlib.replication.creators.RemoteTaskBoardSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.replication.RemoteTaskBoard;
import org.sdmlib.replication.creators.BoardTaskSet;
import org.sdmlib.replication.BoardTask;

public class LaneSet extends SDMSet<Lane>
{

   public LanePO hasLanePO()
   {
      org.sdmlib.replication.creators.ModelPattern pattern = new org.sdmlib.replication.creators.ModelPattern();

      LanePO patternObject = pattern.hasElementLanePO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.Lane";
   }

   public LaneSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Lane>) value);
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

   public StringList getName()
   {
      StringList result = new StringList();

      for (Lane obj : this)
      {
         result.add(obj.getName());
      }

      return result;
   }

   public LaneSet hasName(String value)
   {
      LaneSet result = new LaneSet();

      for (Lane obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public LaneSet hasName(String lower, String upper)
   {
      LaneSet result = new LaneSet();

      for (Lane obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0
            && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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

   public RemoteTaskBoardSet getBoard()
   {
      RemoteTaskBoardSet result = new RemoteTaskBoardSet();

      for (Lane obj : this)
      {
         result.with(obj.getBoard());
      }

      return result;
   }

   public LaneSet hasBoard(Object value)
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

      LaneSet answer = new LaneSet();

      for (Lane obj : this)
      {
         if (neighbors.contains(obj.getBoard()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public LaneSet withBoard(RemoteTaskBoard value)
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
         result.with(obj.getTasks());
      }

      return result;
   }

   public LaneSet hasTasks(Object value)
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

      LaneSet answer = new LaneSet();

      for (Lane obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getTasks()))
         {
            answer.add(obj);
         }
      }

      return answer;
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

}





