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
   
package org.sdmlib.simple.model.abstract_C.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.simple.model.abstract_C.Ground;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.simple.model.abstract_C.Grass;
import org.sdmlib.simple.model.abstract_C.util.GrassSet;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.simple.model.abstract_C.util.GameSet;
import org.sdmlib.simple.model.abstract_C.Game;

public class GroundSet extends SimpleSet<Ground>
{
	public Class<?> getTypClass() {
		return Ground.class;
	}

   public GroundSet()
   {
      // empty
   }

   public GroundSet(Ground... objects)
   {
      for (Ground obj : objects)
      {
         this.add(obj);
      }
   }

   public GroundSet(Collection<Ground> objects)
   {
      this.addAll(objects);
   }

   public static final GroundSet EMPTY_SET = new GroundSet().withFlag(GroundSet.READONLY);


   public GroundPO createGroundPO()
   {
      return new GroundPO(this.toArray(new Ground[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.abstract_C.Ground";
   }


   @Override
   public GroundSet getNewList(boolean keyValue)
   {
      return new GroundSet();
   }


   public GroundSet filter(Condition<Ground> condition) {
      GroundSet filterList = new GroundSet();
      filterItems(filterList, condition);
      return filterList;
   }

   public GrassSet instanceOfGrass()
   {
      GrassSet result = new GrassSet();
      
      for(Object obj : this)
      {
         if (obj instanceof Grass)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   @SuppressWarnings("unchecked")
   public GroundSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Ground>)value);
      }
      else if (value != null)
      {
         this.add((Ground) value);
      }
      
      return this;
   }
   
   public GroundSet without(Ground value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Ground objects and collect a set of the Game objects reached via game. 
    * 
    * @return Set of Game objects reachable via game
    */
   public GameSet getGame()
   {
      GameSet result = new GameSet();
      
      for (Ground obj : this)
      {
         result.with(obj.getGame());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Ground objects and collect all contained objects with reference game pointing to the object passed as parameter. 
    * 
    * @param value The object required as game neighbor of the collected results. 
    * 
    * @return Set of Game objects referring to value via game
    */
   public GroundSet filterGame(Object value)
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
      
      GroundSet answer = new GroundSet();
      
      for (Ground obj : this)
      {
         if (neighbors.contains(obj.getGame()) || (neighbors.isEmpty() && obj.getGame() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Ground object passed as parameter to the Game attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Game attributes.
    */
   public GroundSet withGame(Game value)
   {
      for (Ground obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

}
