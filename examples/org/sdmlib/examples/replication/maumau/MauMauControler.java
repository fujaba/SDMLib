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
   private SharedSpace sharedSpace;

   public MauMauClientGui getGui()
   {
      return gui;
   }

   public MauMauControler(MauMau mauMau, MauMauClientGui gui, SharedSpace sharedSpace)
   {
      this.mauMau = mauMau;
      this.gui = gui;
      this.sharedSpace = sharedSpace;
   }

   private LinkedHashSet<ChangeHandler> handlerlist = new LinkedHashSet<ChangeHandler>();
   
   public MauMauControler init()
   {
      return this;
   }
   
   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      boolean oldState = sharedSpace.isApplyingChangeMsg();
      if (evt != null && MauMau.PROPERTY_PLAYERS.equals(evt.getPropertyName()))
      {
         if (activePlayer == null || activePlayer.getName() == null)
         {
            // to early, wait for next event.
            return;
         }
         
         // create a label for each player, place the active player at the bottom
         // build a list of players starting with the active player
         LinkedList<Player> meFirstPlayerList = new LinkedList<Player>();
         meFirstPlayerList.add(activePlayer);

         // add later players
         boolean foundMe = false;
         for (Player player : mauMau.getPlayers())
         {
            if (foundMe)
            {
               meFirstPlayerList.add(player);
            }

            if (player == activePlayer)
            {
               foundMe = true;
            }
         }

         // add prior players
         for (Player player : mauMau.getPlayers())
         {
            if (player == activePlayer)
            {
               break;
            }

            meFirstPlayerList.add(player);
         }

         double delta = 2 * Math.PI / meFirstPlayerList.size();
         double clockPos = Math.PI / 2;

         int midX = 350;
         int midY = 350;
         int radius = 260;

         int x = midX;
         int y = midY + radius;

         for (Player player : meFirstPlayerList)
         {
            Label lblPlayername = playerLabels.get(player);

            if (lblPlayername == null)
            {
               lblPlayername = new Label(gui.getShell(), SWT.NONE);
               lblPlayername.setAlignment(SWT.CENTER);
               lblPlayername.setText(player.getName());

               playerLabels.put(player, lblPlayername);

               player.getPropertyChangeSupport().addPropertyChangeListener(Player.PROPERTY_ASSIGNMENT, new PlayerListener(player, lblPlayername));
            }

            // put them clock wise start at 6 o'clock
            int xOffset = radius;
            if (Math.PI * 1 / 4 < clockPos && clockPos <= Math.PI * 3 / 4)
            {
               xOffset = (int) (radius / Math.tan(clockPos));
            }
            else if (Math.PI * 3 / 4 < clockPos && clockPos <= Math.PI * 5 / 4)
            {
               xOffset = -radius;
            }
            else if (Math.PI * 5 / 4 < clockPos && clockPos <= Math.PI * 7 / 4)
            {
               xOffset = (int) (radius / Math.tan(clockPos));
            }

            // put them clock wise start at 6 o'clock
            int yOffset = radius;
            if (Math.PI * 3 / 4 < clockPos && clockPos <= Math.PI * 5 / 4)
            {
               yOffset = (int) (-radius * Math.tan(clockPos));
            }
            else if (Math.PI * 5 / 4 < clockPos && clockPos <= Math.PI * 7 / 4)
            {
               yOffset = -radius;
            }
            else if (Math.PI * 7 / 4 < clockPos && clockPos <= Math.PI * 9 / 4)
            {
               yOffset = (int) (radius * Math.tan(clockPos));
            }

            x = (int) (midX + xOffset);
            y = (int) (midY + yOffset);

            lblPlayername.setBounds(x, y, 92, 15);

            // rotate by delta
            clockPos += delta;
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
      System.out.println("Active player is: " + player.getName());
   }

}
