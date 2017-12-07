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

import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Karli;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class KarliSet extends SimpleSet<Karli>
{
	public Class<?> getTypClass() {
		return Karli.class;
	}

   public KarliSet()
   {
      // empty
   }

   public KarliSet(Karli... objects)
   {
      for (Karli obj : objects)
      {
         this.add(obj);
      }
   }

   public KarliSet(Collection<Karli> objects)
   {
      this.addAll(objects);
   }

   public static final KarliSet EMPTY_SET = new KarliSet().withFlag(KarliSet.READONLY);


   public KarliPO createKarliPO()
   {
      return new KarliPO(this.toArray(new Karli[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.sokoban.Karli";
   }


   @Override
   public KarliSet getNewList(boolean keyValue)
   {
      return new KarliSet();
   }

   @SuppressWarnings("unchecked")
   public KarliSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Karli>)value);
      }
      else if (value != null)
      {
         this.add((Karli) value);
      }
      
      return this;
   }
   
   public KarliSet without(Karli value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Karli objects and collect a set of the Sokoban objects reached via sokoban. 
    * 
    * @return Set of Sokoban objects reachable via sokoban
    */
   public SokobanSet getSokoban()
   {
      SokobanSet result = new SokobanSet();
      
      for (Karli obj : this)
      {
         result.with(obj.getSokoban());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Karli objects and collect all contained objects with reference sokoban pointing to the object passed as parameter. 
    * 
    * @param value The object required as sokoban neighbor of the collected results. 
    * 
    * @return Set of Sokoban objects referring to value via sokoban
    */
   public KarliSet filterSokoban(Object value)
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
      
      KarliSet answer = new KarliSet();
      
      for (Karli obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSokoban()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Karli object passed as parameter to the Sokoban attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Sokoban attributes.
    */
   public KarliSet withSokoban(Sokoban value)
   {
      for (Karli obj : this)
      {
         obj.withSokoban(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Karli object passed as parameter from the Sokoban attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public KarliSet withoutSokoban(Sokoban value)
   {
      for (Karli obj : this)
      {
         obj.withoutSokoban(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Karli objects and collect a set of the Tile objects reached via tile. 
    * 
    * @return Set of Tile objects reachable via tile
    */
   public TileSet getTile()
   {
      TileSet result = new TileSet();
      
      for (Karli obj : this)
      {
         result.with(obj.getTile());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Karli objects and collect all contained objects with reference tile pointing to the object passed as parameter. 
    * 
    * @param value The object required as tile neighbor of the collected results. 
    * 
    * @return Set of Tile objects referring to value via tile
    */
   public KarliSet filterTile(Object value)
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
      
      KarliSet answer = new KarliSet();
      
      for (Karli obj : this)
      {
         if (neighbors.contains(obj.getTile()) || (neighbors.isEmpty() && obj.getTile() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Karli object passed as parameter to the Tile attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Tile attributes.
    */
   public KarliSet withTile(Tile value)
   {
      for (Karli obj : this)
      {
         obj.withTile(value);
      }
      
      return this;
   }

}
