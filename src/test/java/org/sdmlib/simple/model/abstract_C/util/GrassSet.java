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
import org.sdmlib.simple.model.abstract_C.Grass;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.simple.model.abstract_C.util.GameSet;
import org.sdmlib.simple.model.abstract_C.Game;

public class GrassSet extends SimpleSet<Grass>
{
	public Class<?> getTypClass() {
		return Grass.class;
	}

   public GrassSet()
   {
      // empty
   }

   public GrassSet(Grass... objects)
   {
      for (Grass obj : objects)
      {
         this.add(obj);
      }
   }

   public GrassSet(Collection<Grass> objects)
   {
      this.addAll(objects);
   }

   public static final GrassSet EMPTY_SET = new GrassSet().withFlag(GrassSet.READONLY);


   public GrassPO createGrassPO()
   {
      return new GrassPO(this.toArray(new Grass[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.abstract_C.Grass";
   }


   @Override
   public GrassSet getNewList(boolean keyValue)
   {
      return new GrassSet();
   }


   public GrassSet filter(Condition<Grass> condition) {
      GrassSet filterList = new GrassSet();
      filterItems(filterList, condition);
      return filterList;
   }

   @SuppressWarnings("unchecked")
   public GrassSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Grass>)value);
      }
      else if (value != null)
      {
         this.add((Grass) value);
      }
      
      return this;
   }
   
   public GrassSet without(Grass value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Grass objects and collect a set of the Game objects reached via game. 
    * 
    * @return Set of Game objects reachable via game
    */
   public GameSet getGame()
   {
      GameSet result = new GameSet();
      
      for (Grass obj : this)
      {
         result.with(obj.getGame());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Grass objects and collect all contained objects with reference game pointing to the object passed as parameter. 
    * 
    * @param value The object required as game neighbor of the collected results. 
    * 
    * @return Set of Game objects referring to value via game
    */
   public GrassSet filterGame(Object value)
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
      
      GrassSet answer = new GrassSet();
      
      for (Grass obj : this)
      {
         if (neighbors.contains(obj.getGame()) || (neighbors.isEmpty() && obj.getGame() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Grass object passed as parameter to the Game attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Game attributes.
    */
   public GrassSet withGame(Game value)
   {
      for (Grass obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

}
