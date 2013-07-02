package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

public class NewPlayerHandler extends ChangeHandler
{
   
   
   private MultiMauMauControler maumauControler;

   public NewPlayerHandler(MultiMauMauControler maumauControler)
   {
      this.maumauControler = maumauControler;
   }

   @Override
   public boolean propertyChange(PropertyChangeEvent evt)
   {
      if (evt != null && MauMau.PROPERTY_PLAYERS.equals(evt.getPropertyName()))
      {
         
         Player activePlayer = maumauControler.getActivePlayer();
         if (activePlayer  == null || activePlayer.getName() == null)
         {
            // to early, wait for next event.
            return true;
         }
         
         // create a label for each player, place the active player at the bottom
         // build a list of players starting with the active player
         LinkedList<Player> meFirstPlayerList = new LinkedList<Player>();
         meFirstPlayerList.add(activePlayer);

         // add later players
         boolean foundMe = false;
         for (Player player : maumauControler.getMauMau().getPlayers())
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
         for (Player player : maumauControler.getMauMau().getPlayers())
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
            Label lblPlayername = maumauControler.getPlayerLabels().get(player);

            if (lblPlayername == null)
            {
               lblPlayername = new Label(maumauControler.getGui().getShell(), SWT.NONE);
               lblPlayername.setAlignment(SWT.CENTER);
               lblPlayername.setText(player.getName());

               maumauControler.getPlayerLabels().put(player, lblPlayername);

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

            int width = 92;

            x = (int) (midX + xOffset) - width / 2;
            y = (int) (midY + yOffset);

            lblPlayername.setBounds(x, y, width, 15);

            // rotate by delta
            clockPos += delta;
         }
         return true;
      }

      return false;
   }
}
