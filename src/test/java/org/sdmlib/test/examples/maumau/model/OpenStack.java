/*
   Copyright (c) 2015 Stefan
   
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
   
package org.sdmlib.test.examples.maumau.model;

/**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/maumau/GenModel.java'>GenModel.java</a>
* @see org.sdmlib.test.examples.maumau.GenModel#genModel
 */
   public  class OpenStack extends Holder
{

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      withoutCards(this.getCards().toArray(new Card[this.getCards().size()]));
      setDeckOwner(null);
      setStackOwner(null);
      setGame(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * OpenStack ----------------------------------- MauMau
    *              openStack                   game
    * </pre>
    */
   
   public static final String PROPERTY_GAME = "game";

   private MauMau game = null;

   public MauMau getGame()
   {
      return this.game;
   }

   public boolean setGame(MauMau value)
   {
      boolean changed = false;
      
      if (this.game != value)
      {
         MauMau oldValue = this.game;
         
         if (this.game != null)
         {
            this.game = null;
            oldValue.setOpenStack(null);
         }
         
         this.game = value;
         
         if (value != null)
         {
            value.withOpenStack(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GAME, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public OpenStack withGame(MauMau value)
   {
      setGame(value);
      return this;
   } 

   public MauMau createGame()
   {
      MauMau value = new MauMau();
      withGame(value);
      return value;
   } 
}
