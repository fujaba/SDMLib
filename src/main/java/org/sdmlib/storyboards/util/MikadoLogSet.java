/*
   Copyright (c) 2018 zuendorf
   
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

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.storyboards.MikadoLog;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.storyboards.util.LogEntrySet;
import org.sdmlib.storyboards.LogEntry;
import org.sdmlib.storyboards.util.GoalSet;
import org.sdmlib.storyboards.Goal;

public class MikadoLogSet extends SimpleSet<MikadoLog>
{
	public Class<?> getTypClass() {
		return MikadoLog.class;
	}

   public MikadoLogSet()
   {
      // empty
   }

   public MikadoLogSet(MikadoLog... objects)
   {
      for (MikadoLog obj : objects)
      {
         this.add(obj);
      }
   }

   public MikadoLogSet(Collection<MikadoLog> objects)
   {
      this.addAll(objects);
   }

   public static final MikadoLogSet EMPTY_SET = new MikadoLogSet().withFlag(MikadoLogSet.READONLY);


   public MikadoLogPO createMikadoLogPO()
   {
      return new MikadoLogPO(this.toArray(new MikadoLog[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.storyboards.MikadoLog";
   }


   @Override
   public MikadoLogSet getNewList(boolean keyValue)
   {
      return new MikadoLogSet();
   }


   @SuppressWarnings("unchecked")
   public MikadoLogSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MikadoLog>)value);
      }
      else if (value != null)
      {
         this.add((MikadoLog) value);
      }
      
      return this;
   }
   
   public MikadoLogSet without(MikadoLog value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of MikadoLog objects and collect a set of the LogEntry objects reached via entries. 
    * 
    * @return Set of LogEntry objects reachable via entries
    */
   public LogEntrySet getEntries()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (MikadoLog obj : this)
      {
         result.with(obj.getEntries());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MikadoLog objects and collect all contained objects with reference entries pointing to the object passed as parameter. 
    * 
    * @param value The object required as entries neighbor of the collected results. 
    * 
    * @return Set of LogEntry objects referring to value via entries
    */
   public MikadoLogSet filterEntries(Object value)
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
      
      MikadoLogSet answer = new MikadoLogSet();
      
      for (MikadoLog obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getEntries()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MikadoLog object passed as parameter to the Entries attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their Entries attributes.
    */
   public MikadoLogSet withEntries(LogEntry value)
   {
      for (MikadoLog obj : this)
      {
         obj.withEntries(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the MikadoLog object passed as parameter from the Entries attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MikadoLogSet withoutEntries(LogEntry value)
   {
      for (MikadoLog obj : this)
      {
         obj.withoutEntries(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of MikadoLog objects and collect a set of the Goal objects reached via mainGoal. 
    * 
    * @return Set of Goal objects reachable via mainGoal
    */
   public GoalSet getMainGoal()
   {
      GoalSet result = new GoalSet();
      
      for (MikadoLog obj : this)
      {
         result.with(obj.getMainGoal());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MikadoLog objects and collect all contained objects with reference mainGoal pointing to the object passed as parameter. 
    * 
    * @param value The object required as mainGoal neighbor of the collected results. 
    * 
    * @return Set of Goal objects referring to value via mainGoal
    */
   public MikadoLogSet filterMainGoal(Object value)
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
      
      MikadoLogSet answer = new MikadoLogSet();
      
      for (MikadoLog obj : this)
      {
         if (neighbors.contains(obj.getMainGoal()) || (neighbors.isEmpty() && obj.getMainGoal() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MikadoLog object passed as parameter to the MainGoal attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their MainGoal attributes.
    */
   public MikadoLogSet withMainGoal(Goal value)
   {
      for (MikadoLog obj : this)
      {
         obj.withMainGoal(value);
      }
      
      return this;
   }

}
