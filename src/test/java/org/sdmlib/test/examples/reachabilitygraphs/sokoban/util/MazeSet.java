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

import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;

import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class MazeSet extends SimpleSet<Maze>
{
	public Class<?> getTypClass() {
		return Maze.class;
	}

   public MazeSet()
   {
      // empty
   }

   public MazeSet(Maze... objects)
   {
      for (Maze obj : objects)
      {
         this.add(obj);
      }
   }

   public MazeSet(Collection<Maze> objects)
   {
      this.addAll(objects);
   }

   public static final MazeSet EMPTY_SET = new MazeSet().withFlag(MazeSet.READONLY);


   public MazePO createMazePO()
   {
      return new MazePO(this.toArray(new Maze[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze";
   }


   @Override
   public MazeSet getNewList(boolean keyValue)
   {
      return new MazeSet();
   }

   @SuppressWarnings("unchecked")
   public MazeSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Maze>)value);
      }
      else if (value != null)
      {
         this.add((Maze) value);
      }
      
      return this;
   }
   
   public MazeSet without(Maze value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Maze objects and collect a set of the Sokoban objects reached via sokoban. 
    * 
    * @return Set of Sokoban objects reachable via sokoban
    */
   public SokobanSet getSokoban()
   {
      SokobanSet result = new SokobanSet();
      
      for (Maze obj : this)
      {
         result.with(obj.getSokoban());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Maze objects and collect all contained objects with reference sokoban pointing to the object passed as parameter. 
    * 
    * @param value The object required as sokoban neighbor of the collected results. 
    * 
    * @return Set of Sokoban objects referring to value via sokoban
    */
   public MazeSet filterSokoban(Object value)
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
      
      MazeSet answer = new MazeSet();
      
      for (Maze obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSokoban()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Maze object passed as parameter to the Sokoban attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Sokoban attributes.
    */
   public MazeSet withSokoban(Sokoban value)
   {
      for (Maze obj : this)
      {
         obj.withSokoban(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Maze object passed as parameter from the Sokoban attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MazeSet withoutSokoban(Sokoban value)
   {
      for (Maze obj : this)
      {
         obj.withoutSokoban(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Maze objects and collect a set of the Tile objects reached via tiles. 
    * 
    * @return Set of Tile objects reachable via tiles
    */
   public TileSet getTiles()
   {
      TileSet result = new TileSet();
      
      for (Maze obj : this)
      {
         result.with(obj.getTiles());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Maze objects and collect all contained objects with reference tiles pointing to the object passed as parameter. 
    * 
    * @param value The object required as tiles neighbor of the collected results. 
    * 
    * @return Set of Tile objects referring to value via tiles
    */
   public MazeSet filterTiles(Object value)
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
      
      MazeSet answer = new MazeSet();
      
      for (Maze obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTiles()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Maze object passed as parameter to the Tiles attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Tiles attributes.
    */
   public MazeSet withTiles(Tile value)
   {
      for (Maze obj : this)
      {
         obj.withTiles(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Maze object passed as parameter from the Tiles attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public MazeSet withoutTiles(Tile value)
   {
      for (Maze obj : this)
      {
         obj.withoutTiles(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Maze objects and collect a list of the height attribute values. 
    * 
    * @return List of int objects reachable via height attribute
    */
   public NumberList getHeight()
   {
      NumberList result = new NumberList();
      
      for (Maze obj : this)
      {
         result.add(obj.getHeight());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Maze objects and collect those Maze objects where the height attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Maze objects that match the parameter
    */
   public MazeSet createHeightCondition(int value)
   {
      MazeSet result = new MazeSet();
      
      for (Maze obj : this)
      {
         if (value == obj.getHeight())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Maze objects and collect those Maze objects where the height attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Maze objects that match the parameter
    */
   public MazeSet createHeightCondition(int lower, int upper)
   {
      MazeSet result = new MazeSet();
      
      for (Maze obj : this)
      {
         if (lower <= obj.getHeight() && obj.getHeight() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Maze objects and assign value to the height attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Maze objects now with new attribute values.
    */
   public MazeSet withHeight(int value)
   {
      for (Maze obj : this)
      {
         obj.setHeight(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Maze objects and collect a list of the width attribute values. 
    * 
    * @return List of int objects reachable via width attribute
    */
   public NumberList getWidth()
   {
      NumberList result = new NumberList();
      
      for (Maze obj : this)
      {
         result.add(obj.getWidth());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Maze objects and collect those Maze objects where the width attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Maze objects that match the parameter
    */
   public MazeSet createWidthCondition(int value)
   {
      MazeSet result = new MazeSet();
      
      for (Maze obj : this)
      {
         if (value == obj.getWidth())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Maze objects and collect those Maze objects where the width attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Maze objects that match the parameter
    */
   public MazeSet createWidthCondition(int lower, int upper)
   {
      MazeSet result = new MazeSet();
      
      for (Maze obj : this)
      {
         if (lower <= obj.getWidth() && obj.getWidth() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Maze objects and assign value to the width attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Maze objects now with new attribute values.
    */
   public MazeSet withWidth(int value)
   {
      for (Maze obj : this)
      {
         obj.setWidth(value);
      }
      
      return this;
   }

}
