/*
   Copyright (c) 2015 philipp-pc
   
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
   
package org.sdmlib.test.model.refactoring;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.test.model.refactoring.Ludo;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/test/model/ModelRefactoring.java'>ModelRefactoring.java</a>/n */
   public  class Player implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      setGame(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Player ----------------------------------- Ludo
    *              players                   game
    * </pre>
    */
   
   public static final String PROPERTY_GAME = "game";

   private Ludo game = null;

   public Ludo getGame()
   {
      return this.game;
   }

   public boolean setGame(Ludo value)
   {
      boolean changed = false;
      
      if (this.game != value)
      {
         Ludo oldValue = this.game;
         
         if (this.game != null)
         {
            this.game = null;
            oldValue.withoutPlayers(this);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withPlayers(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Player withGame(Ludo value)
   {
      setGame(value);
      return this;
   } 

   public Ludo createGame()
   {
      Ludo value = new Ludo();
      withGame(value);
      return value;
   } 
}
