package org.sdmlib.examples.replication.maumau;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Label;

public class CardListener implements MouseListener, MouseMoveListener
{

   private MauMauControler mauMauControler;
   private Card card;
   private Label label;

   private boolean mouseDown = false;
   private int deltaX = 0;
   private int deltaY = 0;
   private MauMauClientGui gui;
  
   public CardListener(Card newCard, MauMauControler mauMauControler, Label label)
   {
      this.card = newCard;
      this.mauMauControler = mauMauControler;
      this.gui = mauMauControler.getGui();
      this.label = label;
   }

   @Override
   public void mouseDoubleClick(MouseEvent e)
   {
      // blank
   }

   @Override
   public void mouseDown(MouseEvent e)
   {
      label.moveAbove(null);
      mouseDown = true;
      deltaX = e.x;
      deltaY = e.y;
   }

   @Override
   public void mouseUp(MouseEvent e)
   {
      mouseDown = false;
      
      // drop zone reached?
      Rectangle cardBounds = label.getBounds();
      
      boolean inX = cardBounds.x <= gui.STACK_X + gui.CARD_WIDTH && cardBounds.x + gui.CARD_WIDTH >= gui.STACK_X;
      boolean inY = cardBounds.y <= gui.STACK_Y + gui.CARD_HEIGHT && cardBounds.y + gui.CARD_HEIGHT >= gui.STACK_Y;

      if (inX && inY)
      {
         boolean flag = card.playToStack();
         
         if (flag == false)
         {
            // forbidden, move back
            
            Label playerLabel = mauMauControler.getPlayerLabels().get(card.getHolder());
            
            if (playerLabel != null)
            {
               label.setLocation(label.getLocation().x, playerLabel.getLocation().y - 40);
            }
         }
         
         return;
      }

      if (card.getHolder() == card.getGame().getDeck() // card from deck
            && (cardBounds.y <= 200  || cardBounds.y >= 400))
      {
         boolean flag; 
         
         Player targetPlayer = null;
         
         for (Player p : mauMauControler.getPlayerLabels().keySet())
         {
            int playerPos = mauMauControler.getPlayerLabels().get(p).getLocation().y;
            
            if (cardBounds.y <= 200 && playerPos <= 200)
            {
               targetPlayer = p;
               break;
            }
            else if (cardBounds.y >= 400 && playerPos >= 400)
            {
               targetPlayer = p;
               break;
            }
         }
         
         flag = card.draw(targetPlayer);
         
         if (flag == false)
         {
            // forbidden, move back
            label.setLocation(155, gui.STACK_Y - 4);
         }
         
         return;
      }
         
   }

   @Override
   public void mouseMove(MouseEvent e)
   {
      if (mouseDown)
      {
         Rectangle oldPos = label.getBounds();
         label.setBounds(oldPos.x + e.x -deltaX, oldPos.y + e.y - deltaY, 
            MauMauClientGui.CARD_WIDTH, MauMauClientGui.CARD_HEIGHT);
      }
   }

}
