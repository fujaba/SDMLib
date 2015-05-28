/*
   Copyright (c) 2015 zasch 
   
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
   
package org.sdmlib.examples.gofpattern.strategy.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.gofpattern.strategy.BombermanPlayer;
import java.util.Collection;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.modelsets.charList;
import org.sdmlib.models.modelsets.shortList;

public class BombermanPlayerSet extends SDMSet<BombermanPlayer>
{

   public static final BombermanPlayerSet EMPTY_SET = new BombermanPlayerSet().withReadOnly(true);


   public BombermanPlayerPO hasBombermanPlayerPO()
   {
      return new BombermanPlayerPO(this.toArray(new BombermanPlayer[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.gofpattern.strategy.BombermanPlayer";
   }


   @SuppressWarnings("unchecked")
   public BombermanPlayerSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<BombermanPlayer>)value);
      }
      else if (value != null)
      {
         this.add((BombermanPlayer) value);
      }
      
      return this;
   }
   
   public BombermanPlayerSet without(BombermanPlayer value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public BombermanPlayerSet keyPress(String key)
   {
      for (BombermanPlayer obj : this)
      {
         obj.keyPress(key);
      }
      return this;
   }

   public intList getXPosition()
   {
      intList result = new intList();
      
      for (BombermanPlayer obj : this)
      {
         result.add(obj.getXPosition());
      }
      
      return result;
   }

   public BombermanPlayerSet hasXPosition(int value)
   {
      BombermanPlayerSet result = new BombermanPlayerSet();
      
      for (BombermanPlayer obj : this)
      {
         if (value == obj.getXPosition())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BombermanPlayerSet hasXPosition(int lower, int upper)
   {
      BombermanPlayerSet result = new BombermanPlayerSet();
      
      for (BombermanPlayer obj : this)
      {
         if (lower <= obj.getXPosition() && obj.getXPosition() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BombermanPlayerSet withXPosition(int value)
   {
      for (BombermanPlayer obj : this)
      {
         obj.setXPosition(value);
      }
      
      return this;
   }

   public intList getYPosition()
   {
      intList result = new intList();
      
      for (BombermanPlayer obj : this)
      {
         result.add(obj.getYPosition());
      }
      
      return result;
   }

   public BombermanPlayerSet hasYPosition(int value)
   {
      BombermanPlayerSet result = new BombermanPlayerSet();
      
      for (BombermanPlayer obj : this)
      {
         if (value == obj.getYPosition())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BombermanPlayerSet hasYPosition(int lower, int upper)
   {
      BombermanPlayerSet result = new BombermanPlayerSet();
      
      for (BombermanPlayer obj : this)
      {
         if (lower <= obj.getYPosition() && obj.getYPosition() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BombermanPlayerSet withYPosition(int value)
   {
      for (BombermanPlayer obj : this)
      {
         obj.setYPosition(value);
      }
      
      return this;
   }

   public intList getNumberOfBombs()
   {
      intList result = new intList();
      
      for (BombermanPlayer obj : this)
      {
         result.add(obj.getNumberOfBombs());
      }
      
      return result;
   }

   public BombermanPlayerSet hasNumberOfBombs(int value)
   {
      BombermanPlayerSet result = new BombermanPlayerSet();
      
      for (BombermanPlayer obj : this)
      {
         if (value == obj.getNumberOfBombs())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BombermanPlayerSet hasNumberOfBombs(int lower, int upper)
   {
      BombermanPlayerSet result = new BombermanPlayerSet();
      
      for (BombermanPlayer obj : this)
      {
         if (lower <= obj.getNumberOfBombs() && obj.getNumberOfBombs() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BombermanPlayerSet withNumberOfBombs(int value)
   {
      for (BombermanPlayer obj : this)
      {
         obj.setNumberOfBombs(value);
      }
      
      return this;
   }

   public charList getLastKey()
   {
      charList result = new charList();
      
      for (BombermanPlayer obj : this)
      {
         result.add(obj.getLastKey());
      }
      
      return result;
   }

   public BombermanPlayerSet hasLastKey(char value)
   {
      BombermanPlayerSet result = new BombermanPlayerSet();
      
      for (BombermanPlayer obj : this)
      {
         if (value == obj.getLastKey())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BombermanPlayerSet withLastKey(char value)
   {
      for (BombermanPlayer obj : this)
      {
         obj.setLastKey(value);
      }
      
      return this;
   }

   public shortList getShortTest()
   {
      shortList result = new shortList();
      
      for (BombermanPlayer obj : this)
      {
         result.add(obj.getShortTest());
      }
      
      return result;
   }

   public BombermanPlayerSet hasShortTest(short value)
   {
      BombermanPlayerSet result = new BombermanPlayerSet();
      
      for (BombermanPlayer obj : this)
      {
         if (value == obj.getShortTest())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BombermanPlayerSet withShortTest(short value)
   {
      for (BombermanPlayer obj : this)
      {
         obj.setShortTest(value);
      }
      
      return this;
   }

}
