/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.test.examples.gofpattern.strategy.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.charList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.modelsets.shortList;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanPlayer;

import de.uniks.networkparser.list.SimpleSet;

public class BombermanPlayerSet extends SimpleSet<BombermanPlayer>
{

   public static final BombermanPlayerSet EMPTY_SET = new BombermanPlayerSet().withFlag(BombermanPlayerSet.READONLY);


   public BombermanPlayerPO hasBombermanPlayerPO()
   {
      return new BombermanPlayerPO(this.toArray(new BombermanPlayer[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.gofpattern.strategy.BombermanPlayer";
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



   public BombermanPlayerPO filterBombermanPlayerPO()
   {
      return new BombermanPlayerPO(this.toArray(new BombermanPlayer[this.size()]));
   }

   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the xPosition attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet filterXPosition(int value)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the xPosition attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet filterXPosition(int lower, int upper)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the yPosition attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet filterYPosition(int value)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the yPosition attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet filterYPosition(int lower, int upper)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the numberOfBombs attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet filterNumberOfBombs(int value)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the numberOfBombs attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet filterNumberOfBombs(int lower, int upper)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the lastKey attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet filterLastKey(char value)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the shortTest attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet filterShortTest(short value)
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


   public BombermanPlayerSet()
   {
      // empty
   }

   public BombermanPlayerSet(BombermanPlayer... objects)
   {
      for (BombermanPlayer obj : objects)
      {
         this.add(obj);
      }
   }

   public BombermanPlayerSet(Collection<BombermanPlayer> objects)
   {
      this.addAll(objects);
   }


   public BombermanPlayerPO createBombermanPlayerPO()
   {
      return new BombermanPlayerPO(this.toArray(new BombermanPlayer[this.size()]));
   }


   @Override
   public BombermanPlayerSet getNewList(boolean keyValue)
   {
      return new BombermanPlayerSet();
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the lastKey attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet createLastKeyCondition(char value)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the numberOfBombs attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet createNumberOfBombsCondition(int value)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the numberOfBombs attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet createNumberOfBombsCondition(int lower, int upper)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the shortTest attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet createShortTestCondition(short value)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the xPosition attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet createXPositionCondition(int value)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the xPosition attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet createXPositionCondition(int lower, int upper)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the yPosition attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet createYPositionCondition(int value)
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


   /**
    * Loop through the current set of BombermanPlayer objects and collect those BombermanPlayer objects where the yPosition attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of BombermanPlayer objects that match the parameter
    */
   public BombermanPlayerSet createYPositionCondition(int lower, int upper)
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

}
