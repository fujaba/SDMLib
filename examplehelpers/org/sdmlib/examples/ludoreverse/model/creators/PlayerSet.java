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
   
package org.sdmlib.examples.ludoreverse.model.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.ludoreverse.model.Ludo;
import org.sdmlib.examples.ludoreverse.model.Player;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.modelsets.StringList;

public class PlayerSet extends LinkedHashSet<Player> implements ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Player elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.ludoreverse.model.Player";
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

}


