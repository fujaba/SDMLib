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
   
package org.sdmlib.replication.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.RemoteTaskBoard;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.replication.util.RemoteTaskBoardSet;
import org.sdmlib.replication.util.BoardTaskSet;

public class LaneSet extends SimpleSet<Lane>
{


   public LanePO hasLanePO()
   {
      return new LanePO(this.toArray(new Lane[this.size()]));
   }

   @SuppressWarnings("unchecked")
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
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
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
         result.add(obj.getBoard());
      }
      
      return result;
   }

   public LaneSet hasBoard(Object value)
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
         result.addAll(obj.getTasks());
      }
      
      return result;
   }

   public LaneSet hasTasks(Object value)
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
      
      LaneSet answer = new LaneSet();
      
      for (Lane obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTasks()))
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


   public static final LaneSet EMPTY_SET = new LaneSet().withFlag(LaneSet.READONLY);


   public LanePO filterLanePO()
   {
      return new LanePO(this.toArray(new Lane[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.Lane";
   }

   /**
    * Loop through the current set of Lane objects and collect those Lane objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Lane objects that match the parameter
    */
   public LaneSet filterName(String value)
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


   /**
    * Loop through the current set of Lane objects and collect those Lane objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Lane objects that match the parameter
    */
   public LaneSet filterName(String lower, String upper)
   {
      LaneSet result = new LaneSet();
      
      for (Lane obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public LaneSet()
   {
      // empty
   }

   public LaneSet(Lane... objects)
   {
      for (Lane obj : objects)
      {
         this.add(obj);
      }
   }

   public LaneSet(Collection<Lane> objects)
   {
      this.addAll(objects);
   }


   public LanePO createLanePO()
   {
      return new LanePO(this.toArray(new Lane[this.size()]));
   }


   @Override
   public LaneSet getNewList(boolean keyValue)
   {
      return new LaneSet();
   }


   public LaneSet filter(Condition<Lane> condition) {
      LaneSet filterList = new LaneSet();
      filterItems(filterList, condition);
      return filterList;
   }
   /**
    * Loop through the current set of Lane objects and collect those Lane objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Lane objects that match the parameter
    */
   public LaneSet createNameCondition(String value)
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


   /**
    * Loop through the current set of Lane objects and collect those Lane objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Lane objects that match the parameter
    */
   public LaneSet createNameCondition(String lower, String upper)
   {
      LaneSet result = new LaneSet();
      
      for (Lane obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
