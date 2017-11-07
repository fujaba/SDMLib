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

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import java.util.Collections;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.BoxSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Box;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.KarliSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Karli;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.MazeSet;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TileSet;

public class TileSet extends SimpleSet<Tile>
{
	public Class<?> getTypClass() {
		return Tile.class;
	}

   public TileSet()
   {
      // empty
   }

   public TileSet(Tile... objects)
   {
      for (Tile obj : objects)
      {
         this.add(obj);
      }
   }

   public TileSet(Collection<Tile> objects)
   {
      this.addAll(objects);
   }

   public static final TileSet EMPTY_SET = new TileSet().withFlag(TileSet.READONLY);


   public TilePO createTilePO()
   {
      return new TilePO(this.toArray(new Tile[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile";
   }


   @Override
   public TileSet getNewList(boolean keyValue)
   {
      return new TileSet();
   }


   public TileSet filter(Condition<Tile> condition) {
      TileSet filterList = new TileSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public TileSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Tile>)value);
      }
      else if (value != null)
      {
         this.add((Tile) value);
      }
      
      return this;
   }
   
   public TileSet without(Tile value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Tile objects and collect a list of the goal attribute values. 
    * 
    * @return List of boolean objects reachable via goal attribute
    */
   public BooleanList getGoal()
   {
      BooleanList result = new BooleanList();
      
      for (Tile obj : this)
      {
         result.add(obj.isGoal());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and collect those Tile objects where the goal attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Tile objects that match the parameter
    */
   public TileSet createGoalCondition(boolean value)
   {
      TileSet result = new TileSet();
      
      for (Tile obj : this)
      {
         if (value == obj.isGoal())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and assign value to the goal attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Tile objects now with new attribute values.
    */
   public TileSet withGoal(boolean value)
   {
      for (Tile obj : this)
      {
         obj.setGoal(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Tile objects and collect a list of the wall attribute values. 
    * 
    * @return List of boolean objects reachable via wall attribute
    */
   public BooleanList getWall()
   {
      BooleanList result = new BooleanList();
      
      for (Tile obj : this)
      {
         result.add(obj.isWall());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and collect those Tile objects where the wall attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Tile objects that match the parameter
    */
   public TileSet createWallCondition(boolean value)
   {
      TileSet result = new TileSet();
      
      for (Tile obj : this)
      {
         if (value == obj.isWall())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and assign value to the wall attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Tile objects now with new attribute values.
    */
   public TileSet withWall(boolean value)
   {
      for (Tile obj : this)
      {
         obj.setWall(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Tile objects and collect a list of the x attribute values. 
    * 
    * @return List of int objects reachable via x attribute
    */
   public NumberList getX()
   {
      NumberList result = new NumberList();
      
      for (Tile obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and collect those Tile objects where the x attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Tile objects that match the parameter
    */
   public TileSet createXCondition(int value)
   {
      TileSet result = new TileSet();
      
      for (Tile obj : this)
      {
         if (value == obj.getX())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and collect those Tile objects where the x attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Tile objects that match the parameter
    */
   public TileSet createXCondition(int lower, int upper)
   {
      TileSet result = new TileSet();
      
      for (Tile obj : this)
      {
         if (lower <= obj.getX() && obj.getX() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and assign value to the x attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Tile objects now with new attribute values.
    */
   public TileSet withX(int value)
   {
      for (Tile obj : this)
      {
         obj.setX(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Tile objects and collect a list of the y attribute values. 
    * 
    * @return List of int objects reachable via y attribute
    */
   public NumberList getY()
   {
      NumberList result = new NumberList();
      
      for (Tile obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and collect those Tile objects where the y attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Tile objects that match the parameter
    */
   public TileSet createYCondition(int value)
   {
      TileSet result = new TileSet();
      
      for (Tile obj : this)
      {
         if (value == obj.getY())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and collect those Tile objects where the y attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Tile objects that match the parameter
    */
   public TileSet createYCondition(int lower, int upper)
   {
      TileSet result = new TileSet();
      
      for (Tile obj : this)
      {
         if (lower <= obj.getY() && obj.getY() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Tile objects and assign value to the y attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Tile objects now with new attribute values.
    */
   public TileSet withY(int value)
   {
      for (Tile obj : this)
      {
         obj.setY(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Tile objects and collect a set of the Box objects reached via boxes. 
    * 
    * @return Set of Box objects reachable via boxes
    */
   public BoxSet getBoxes()
   {
      BoxSet result = new BoxSet();
      
      for (Tile obj : this)
      {
         result.with(obj.getBoxes());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Tile objects and collect all contained objects with reference boxes pointing to the object passed as parameter. 
    * 
    * @param value The object required as boxes neighbor of the collected results. 
    * 
    * @return Set of Box objects referring to value via boxes
    */
   public TileSet filterBoxes(Object value)
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
      
      TileSet answer = new TileSet();
      
      for (Tile obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getBoxes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Tile object passed as parameter to the Boxes attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Boxes attributes.
    */
   public TileSet withBoxes(Box value)
   {
      for (Tile obj : this)
      {
         obj.withBoxes(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Tile object passed as parameter from the Boxes attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TileSet withoutBoxes(Box value)
   {
      for (Tile obj : this)
      {
         obj.withoutBoxes(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Tile objects and collect a set of the Karli objects reached via karlis. 
    * 
    * @return Set of Karli objects reachable via karlis
    */
   public KarliSet getKarlis()
   {
      KarliSet result = new KarliSet();
      
      for (Tile obj : this)
      {
         result.with(obj.getKarlis());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Tile objects and collect all contained objects with reference karlis pointing to the object passed as parameter. 
    * 
    * @param value The object required as karlis neighbor of the collected results. 
    * 
    * @return Set of Karli objects referring to value via karlis
    */
   public TileSet filterKarlis(Object value)
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
      
      TileSet answer = new TileSet();
      
      for (Tile obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getKarlis()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Tile object passed as parameter to the Karlis attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Karlis attributes.
    */
   public TileSet withKarlis(Karli value)
   {
      for (Tile obj : this)
      {
         obj.withKarlis(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Tile object passed as parameter from the Karlis attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TileSet withoutKarlis(Karli value)
   {
      for (Tile obj : this)
      {
         obj.withoutKarlis(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Tile objects and collect a set of the Maze objects reached via maze. 
    * 
    * @return Set of Maze objects reachable via maze
    */
   public MazeSet getMaze()
   {
      MazeSet result = new MazeSet();
      
      for (Tile obj : this)
      {
         result.with(obj.getMaze());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Tile objects and collect all contained objects with reference maze pointing to the object passed as parameter. 
    * 
    * @param value The object required as maze neighbor of the collected results. 
    * 
    * @return Set of Maze objects referring to value via maze
    */
   public TileSet filterMaze(Object value)
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
      
      TileSet answer = new TileSet();
      
      for (Tile obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMaze()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Tile object passed as parameter to the Maze attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Maze attributes.
    */
   public TileSet withMaze(Maze value)
   {
      for (Tile obj : this)
      {
         obj.withMaze(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Tile object passed as parameter from the Maze attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TileSet withoutMaze(Maze value)
   {
      for (Tile obj : this)
      {
         obj.withoutMaze(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Tile objects and collect a set of the Tile objects reached via neighbors. 
    * 
    * @return Set of Tile objects reachable via neighbors
    */
   public TileSet getNeighbors()
   {
      TileSet result = new TileSet();
      
      for (Tile obj : this)
      {
         result.with(obj.getNeighbors());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Tile objects and collect all contained objects with reference neighbors pointing to the object passed as parameter. 
    * 
    * @param value The object required as neighbors neighbor of the collected results. 
    * 
    * @return Set of Tile objects referring to value via neighbors
    */
   public TileSet filterNeighbors(Object value)
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
      
      TileSet answer = new TileSet();
      
      for (Tile obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getNeighbors()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow neighbors reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Tile objects reachable via neighbors transitively (including the start set)
    */
   public TileSet getNeighborsTransitive()
   {
      TileSet todo = new TileSet().with(this);
      
      TileSet result = new TileSet();
      
      while ( ! todo.isEmpty())
      {
         Tile current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getNeighbors()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Tile object passed as parameter to the Neighbors attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Neighbors attributes.
    */
   public TileSet withNeighbors(Tile value)
   {
      for (Tile obj : this)
      {
         obj.withNeighbors(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Tile object passed as parameter from the Neighbors attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TileSet withoutNeighbors(Tile value)
   {
      for (Tile obj : this)
      {
         obj.withoutNeighbors(value);
      }
      
      return this;
   }

}
