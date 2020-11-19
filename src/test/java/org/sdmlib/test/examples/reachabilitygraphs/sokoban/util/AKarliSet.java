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

import org.sdmlib.test.examples.reachabilitygraphs.sokoban.AKarli;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class AKarliSet extends SimpleSet<AKarli>
{
	public Class<?> getTypClass() {
		return AKarli.class;
	}

   public AKarliSet()
   {
      // empty
   }

   public AKarliSet(AKarli... objects)
   {
      for (AKarli obj : objects)
      {
         this.add(obj);
      }
   }

   public AKarliSet(Collection<AKarli> objects)
   {
      this.addAll(objects);
   }

   public static final AKarliSet EMPTY_SET = new AKarliSet().withFlag(AKarliSet.READONLY);


   public AKarliPO createAKarliPO()
   {
      return new AKarliPO(this.toArray(new AKarli[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.reachabilitygraphs.sokoban.AKarli";
   }


   @Override
   public AKarliSet getNewList(boolean keyValue)
   {
      return new AKarliSet();
   }


   @SuppressWarnings("unchecked")
   public AKarliSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<AKarli>)value);
      }
      else if (value != null)
      {
         this.add((AKarli) value);
      }
      
      return this;
   }
   
   public AKarliSet without(AKarli value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of AKarli objects and collect a set of the Tile objects reached via tiles. 
    * 
    * @return Set of Tile objects reachable via tiles
    */
   public TileSet getTiles()
   {
      TileSet result = new TileSet();
      
      for (AKarli obj : this)
      {
         result.with(obj.getTiles());
      }
      
      return result;
   }

   /**
    * Loop through the current set of AKarli objects and collect all contained objects with reference tiles pointing to the object passed as parameter. 
    * 
    * @param value The object required as tiles neighbor of the collected results. 
    * 
    * @return Set of Tile objects referring to value via tiles
    */
   public AKarliSet filterTiles(Object value)
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
      
      AKarliSet answer = new AKarliSet();
      
      for (AKarli obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTiles()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the AKarli object passed as parameter to the Tiles attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Tiles attributes.
    */
   public AKarliSet withTiles(Tile value)
   {
      for (AKarli obj : this)
      {
         obj.withTiles(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the AKarli object passed as parameter from the Tiles attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public AKarliSet withoutTiles(Tile value)
   {
      for (AKarli obj : this)
      {
         obj.withoutTiles(value);
      }
      
      return this;
   }

}
