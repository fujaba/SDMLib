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
   
package de.uniks.se1.ss18.teamb.UoM.model.util;

import java.util.Collection;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.se1.ss18.teamb.UoM.model.Game;
import de.uniks.se1.ss18.teamb.UoM.model.GameState;

public class GameSet extends SimpleSet<Game>
{
	public Class<?> getTypClass() {
		return Game.class;
	}

   public GameSet()
   {
      // empty
   }

   public GameSet(Game... objects)
   {
      for (Game obj : objects)
      {
         this.add(obj);
      }
   }

   public GameSet(Collection<Game> objects)
   {
      this.addAll(objects);
   }

   public static final GameSet EMPTY_SET = new GameSet().withFlag(GameSet.READONLY);


   public GamePO createGamePO()
   {
      return new GamePO(this.toArray(new Game[this.size()]));
   }


   public String getEntryType()
   {
      return "de.uniks.se1.ss18.teamb.UoM.model.Game";
   }


   @Override
   public GameSet getNewList(boolean keyValue)
   {
      return new GameSet();
   }


   @SuppressWarnings("unchecked")
   public GameSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Game>)value);
      }
      else if (value != null)
      {
         this.add((Game) value);
      }
      
      return this;
   }
   
   public GameSet without(Game value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of Game objects and collect a set of the GameState objects reached via gameState. 
    * 
    * @return Set of GameState objects reachable via gameState
    */
   public GameStateSet getGameState()
   {
      GameStateSet result = new GameStateSet();
      
      for (Game obj : this)
      {
         result.with(obj.getGameState());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Game objects and collect all contained objects with reference gameState pointing to the object passed as parameter. 
    * 
    * @param value The object required as gameState neighbor of the collected results. 
    * 
    * @return Set of GameState objects referring to value via gameState
    */
   public GameSet filterGameState(Object value)
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
      
      GameSet answer = new GameSet();
      
      for (Game obj : this)
      {
         if (neighbors.contains(obj.getGameState()) || (neighbors.isEmpty() && obj.getGameState() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Game object passed as parameter to the GameState attribute of each of it. 
    * 
    * @param value value    * @return The original set of ModelType objects now with the new neighbor attached to their GameState attributes.
    */
   public GameSet withGameState(GameState value)
   {
      for (Game obj : this)
      {
         obj.withGameState(value);
      }
      
      return this;
   }

}
