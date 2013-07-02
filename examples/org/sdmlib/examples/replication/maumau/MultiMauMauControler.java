package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.TaskHandler;

public class MultiMauMauControler implements PropertyChangeListener
{

   private Player activePlayer;
   private MauMau mauMau;
   private MauMauClientGui gui;

   public Player getActivePlayer()
   {
      return activePlayer;
   }

   private LinkedHashMap<Player, Label> playerLabels = new LinkedHashMap<Player, Label>();

   public LinkedHashMap<Player, Label> getPlayerLabels()
   {
      return playerLabels;
   }

   private LinkedHashMap<Label, Card> labelToCards = new LinkedHashMap<Label, Card>();
   private SharedSpace sharedSpace;

   public MauMauClientGui getGui()
   {
      return gui;
   }

   public MultiMauMauControler(MauMau mauMau, MauMauClientGui gui, SharedSpace sharedSpace)
   {
      this.mauMau = mauMau;
      this.gui = gui;
      this.sharedSpace = sharedSpace;
   }

   private LinkedHashSet<ChangeHandler> handlerlist = new LinkedHashSet<ChangeHandler>();
   
   public MultiMauMauControler init()
   {
      handlerlist.add(new NewPlayerHandler(this));
      handlerlist.add(new NewCardHandler(this));
      return this;
   }
   
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      boolean oldState = sharedSpace.isApplyingChangeMsg();
    
      try
      {
         sharedSpace.setApplyingChangeMsg(false);
         
         for (ChangeHandler handler : handlerlist)
         {
            boolean done = handler.propertyChange(evt);
            
            if (done)
            {
               break;
            }
         }
         
      }
      finally
      {
         sharedSpace.setApplyingChangeMsg(oldState);
      }
      
      if (evt != null && evt.getPropertyName().equals(MauMau.PROPERTY_CARDS))
      {
         // new Card, add CardController to it
         Card newCard = (Card) evt.getNewValue();

//         CardControler listener = new CardControler(this, newCard);
//         newCard.getPropertyChangeSupport().addPropertyChangeListener(listener);
//         listener.propertyChange(null);
      }


   }

   public void setActivePlayer(Player player)
   {
      activePlayer = player;
      System.out.println("Active player is: " + player.getName());
   }

   public MauMau getMauMau()
   {
      return mauMau;
   }

}
