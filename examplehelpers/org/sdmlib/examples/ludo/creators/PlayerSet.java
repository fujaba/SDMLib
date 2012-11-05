/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.ludo.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.examples.ludo.Ludo;
import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.Pawn;
import java.util.ArrayList;
import org.sdmlib.examples.ludo.LudoModel.LudoColor;

public class PlayerSet extends LinkedHashSet<Player>
{
   public StringList getColor()
   {
      StringList result = new StringList();
      
      for (Player obj : this)
      {
         result.add(obj.getColor());
      }
      
      return result;
   }

   public PlayerSet withColor(String value)
   {
      for (Player obj : this)
      {
         obj.withColor(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Player obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public PlayerSet withName(String value)
   {
      for (Player obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (Player obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public PlayerSet withX(int value)
   {
      for (Player obj : this)
      {
         obj.withX(value);
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (Player obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public PlayerSet withY(int value)
   {
      for (Player obj : this)
      {
         obj.withY(value);
      }
      
      return this;
   }

   public LudoSet getGame()
   {
      LudoSet result = new LudoSet();
      
      for (Player obj : this)
      {
         result.add(obj.getGame());
      }
      
      return result;
   }
   public PlayerSet withGame(Ludo value)
   {
      for (Player obj : this)
      {
         obj.withGame(value);
      }
      
      return this;
   }

   public PlayerSet getNext()
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }
   public PlayerSet withNext(Player value)
   {
      for (Player obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public PlayerSet getPrev()
   {
      PlayerSet result = new PlayerSet();
      
      for (Player obj : this)
      {
         result.add(obj.getPrev());
      }
      
      return result;
   }
   public PlayerSet withPrev(Player value)
   {
      for (Player obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public DiceSet getDice()
   {
      DiceSet result = new DiceSet();
      
      for (Player obj : this)
      {
         result.add(obj.getDice());
      }
      
      return result;
   }
   public PlayerSet withDice(Dice value)
   {
      for (Player obj : this)
      {
         obj.withDice(value);
      }
      
      return this;
   }

   public FieldSet getStart()
   {
      FieldSet result = new FieldSet();
      
      for (Player obj : this)
      {
         result.add(obj.getStart());
      }
      
      return result;
   }
   public PlayerSet withStart(Field value)
   {
      for (Player obj : this)
      {
         obj.withStart(value);
      }
      
      return this;
   }

   public FieldSet getBase()
   {
      FieldSet result = new FieldSet();
      
      for (Player obj : this)
      {
         result.add(obj.getBase());
      }
      
      return result;
   }
   public PlayerSet withBase(Field value)
   {
      for (Player obj : this)
      {
         obj.withBase(value);
      }
      
      return this;
   }

   public FieldSet getLanding()
   {
      FieldSet result = new FieldSet();
      
      for (Player obj : this)
      {
         result.add(obj.getLanding());
      }
      
      return result;
   }
   public PlayerSet withLanding(Field value)
   {
      for (Player obj : this)
      {
         obj.withLanding(value);
      }
      
      return this;
   }

   public PawnSet getPawns()
   {
      PawnSet result = new PawnSet();
      
      for (Player obj : this)
      {
         result.addAll(obj.getPawns());
      }
      
      return result;
   }
   public PlayerSet withPawns(Pawn value)
   {
      for (Player obj : this)
      {
         obj.withPawns(value);
      }
      
      return this;
   }

   public PlayerSet withoutPawns(Pawn value)
   {
      for (Player obj : this)
      {
         obj.withoutPawns(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Player elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public PlayerSet with(Player value)
   {
      this.add(value);
      return this;
   }
   
   public PlayerSet without(Player value)
   {
      this.remove(value);
      return this;
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.ludo.Player";
   }

   public ArrayList<LudoColor> getEnumColor()
   {
      ArrayList<LudoColor> result = new ArrayList<LudoColor>();
      
      for (Player obj : this)
      {
         result.add(obj.getEnumColor());
      }
      
      return result;
   }

   public PlayerSet withEnumColor(LudoColor value)
   {
      for (Player obj : this)
      {
         obj.withEnumColor(value);
      }
      
      return this;
   }

}








