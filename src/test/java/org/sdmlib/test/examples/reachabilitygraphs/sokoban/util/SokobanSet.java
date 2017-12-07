/*
   Copyright (c) 2017 zuendorf
   
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
   
package org.sdmlib.test.examples.reachabilitygraphs.sokoban.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Box;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Karli;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class SokobanSet extends SimpleSet<Sokoban>
{
	public Class<?> getTypClass() {
		return Sokoban.class;
	}

   public SokobanSet()
   {
      // empty
   }

   public SokobanSet(Sokoban... objects)
   {
      for (Sokoban obj : objects)
      {
         this.add(obj);
      }
   }

   public SokobanSet(Collection<Sokoban> objects)
   {
      this.addAll(objects);
   }

   public static final SokobanSet EMPTY_SET = new SokobanSet().withFlag(SokobanSet.READONLY);


   public SokobanPO createSokobanPO()
   {
      return new SokobanPO(this.toArray(new Sokoban[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban";
   }


   @Override
   public SokobanSet getNewList(boolean keyValue)
   {
      return new SokobanSet();
   }

   @SuppressWarnings("unchecked")
   public SokobanSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Sokoban>)value);
      }
      else if (value != null)
      {
         this.add((Sokoban) value);
      }
      
      return this;
   }
   
   public SokobanSet without(Sokoban value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Sokoban objects and collect a set of the Maze objects reached via maze. 
    * 
    * @return Set of Maze objects reachable via maze
    */
   public MazeSet getMaze()
   {
      MazeSet result = new MazeSet();
      
      for (Sokoban obj : this)
      {
         result.with(obj.getMaze());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Sokoban objects and collect all contained objects with reference maze pointing to the object passed as parameter. 
    * 
    * @param value The object required as maze neighbor of the collected results. 
    * 
    * @return Set of Maze objects referring to value via maze
    */
   public SokobanSet filterMaze(Object value)
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
      
      SokobanSet answer = new SokobanSet();
      
      for (Sokoban obj : this)
      {
         if (neighbors.contains(obj.getMaze()) || (neighbors.isEmpty() && obj.getMaze() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Sokoban object passed as parameter to the Maze attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Maze attributes.
    */
   public SokobanSet withMaze(Maze value)
   {
      for (Sokoban obj : this)
      {
         obj.withMaze(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Sokoban objects and collect a set of the Box objects reached via boxes. 
    * 
    * @return Set of Box objects reachable via boxes
    */
   public BoxSet getBoxes()
   {
      BoxSet result = new BoxSet();
      
      for (Sokoban obj : this)
      {
         result.with(obj.getBoxes());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Sokoban objects and collect all contained objects with reference boxes pointing to the object passed as parameter. 
    * 
    * @param value The object required as boxes neighbor of the collected results. 
    * 
    * @return Set of Box objects referring to value via boxes
    */
   public SokobanSet filterBoxes(Object value)
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
      
      SokobanSet answer = new SokobanSet();
      
      for (Sokoban obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBoxes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Sokoban object passed as parameter to the Boxes attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Boxes attributes.
    */
   public SokobanSet withBoxes(Box value)
   {
      for (Sokoban obj : this)
      {
         obj.withBoxes(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Sokoban object passed as parameter from the Boxes attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public SokobanSet withoutBoxes(Box value)
   {
      for (Sokoban obj : this)
      {
         obj.withoutBoxes(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Sokoban objects and collect a set of the Karli objects reached via karli. 
    * 
    * @return Set of Karli objects reachable via karli
    */
   public KarliSet getKarli()
   {
      KarliSet result = new KarliSet();
      
      for (Sokoban obj : this)
      {
         result.with(obj.getKarli());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Sokoban objects and collect all contained objects with reference karli pointing to the object passed as parameter. 
    * 
    * @param value The object required as karli neighbor of the collected results. 
    * 
    * @return Set of Karli objects referring to value via karli
    */
   public SokobanSet filterKarli(Object value)
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
      
      SokobanSet answer = new SokobanSet();
      
      for (Sokoban obj : this)
      {
         if (neighbors.contains(obj.getKarli()) || (neighbors.isEmpty() && obj.getKarli() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Sokoban object passed as parameter to the Karli attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Karli attributes.
    */
   public SokobanSet withKarli(Karli value)
   {
      for (Sokoban obj : this)
      {
         obj.withKarli(value);
      }
      
      return this;
   }

}
