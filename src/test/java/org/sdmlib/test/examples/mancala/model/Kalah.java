/*
   Copyright (c) 2014 NeTH 
   
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
   
package org.sdmlib.test.examples.mancala.model;

import org.sdmlib.test.examples.mancala.model.Pit;
import org.sdmlib.test.examples.mancala.model.Mancala;
import org.sdmlib.test.examples.mancala.model.Player;

/**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/mancala/MancalaModel.java'>MancalaModel.java</a>
* @see org.sdmlib.test.examples.mancala.MancalaModel#MancalaModelCreation
 */
   public class Kalah extends Pit
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      setGame(null);
      setPlayer(null);
      setNext(null);
      setPrevious(null);
      setCounterpart(null);
      setKalahPlayer(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getNr());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       one
    * Kalah ----------------------------------- Player
    *              kalah                   kalahPlayer
    * </pre>
    */
   
   public static final String PROPERTY_KALAHPLAYER = "kalahPlayer";

   private Player kalahPlayer = null;

   public Player getKalahPlayer()
   {
      return this.kalahPlayer;
   }

   public boolean setKalahPlayer(Player value)
   {
      boolean changed = false;
      
      if (this.kalahPlayer != value)
      {
         Player oldValue = this.kalahPlayer;
         
         if (this.kalahPlayer != null)
         {
            this.kalahPlayer = null;
            oldValue.setKalah(null);
         }
         
         this.kalahPlayer = value;
         
         if (value != null)
         {
            value.withKalah(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_KALAHPLAYER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Kalah withKalahPlayer(Player value)
   {
      setKalahPlayer(value);
      return this;
   } 

   public Player createKalahPlayer()
   {
      Player value = new Player();
      withKalahPlayer(value);
      return value;
   } 
}
