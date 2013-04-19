package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

public class MauMauControler implements PropertyChangeListener
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
   
   public MauMauClientGui getGui()
   {
      return gui;
   }

   public MauMauControler(MauMau mauMau, MauMauClientGui gui)
   {
      this.mauMau = mauMau;
      this.gui = gui;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt == null && activePlayer != null)
      {
         // create a label for each player, place the active player at the bottom
         for (Player player : mauMau.getPlayers())
         {
            Label lblPlayername;
            if (player == activePlayer)
            {
               lblPlayername = new Label(gui.getShell(), SWT.NONE);
               lblPlayername.setBounds(26, 614, 92, 15);
               lblPlayername.setAlignment(SWT.CENTER);
               lblPlayername.setText(player.getName());
               
               playerLabels.put(player, lblPlayername);
               
               
            }
            else
            {
               lblPlayername = new Label(gui.getShell(), SWT.NONE);
               lblPlayername.setBounds(26, 86, 92, 15);
               lblPlayername.setAlignment(SWT.CENTER);
               lblPlayername.setText(player.getName());

               playerLabels.put(player, lblPlayername);
            }
            
            player.getPropertyChangeSupport().addPropertyChangeListener(Player.PROPERTY_ASSIGNMENT, new PlayerListener(player, lblPlayername));
         }
      }
      else if (evt != null && evt.getPropertyName().equals(MauMau.PROPERTY_CARDS))
      {
         // new Card, add CardController to it
         Card newCard = (Card) evt.getNewValue();
         
         CardControler listener = new CardControler(this, newCard);
         newCard.getPropertyChangeSupport().addPropertyChangeListener(listener);
         listener.propertyChange(null);
      }
         

   }

   public void setActivePlayer(Player player)
   {
      activePlayer = player;
      propertyChange(null);
   }

}
