package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class CardControler implements PropertyChangeListener
{

   private Card card;
   private MauMauClientGui gui;
   private Label label = null;
   private MauMauControler mauMauControler;

   public CardControler(MauMauControler mauMauControler, Card newCard)
   {
      this.mauMauControler = mauMauControler;
      this.gui = mauMauControler.getGui();
      this.card = newCard;
   }

   private static int cardNo = 1;

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (card.getValue() == null || card.getSuit() == null || card.getGame() == null)
      {
         // not yet initialized 
         return;
      }

      MauMau game = card.getGame();

      if (label == null)
      {
         // create inital label
         label = new Label(gui.getShell(), SWT.BORDER | SWT.SHADOW_NONE);
         label.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
         label.setAlignment(SWT.CENTER);

         label.setBounds(42, 42, gui.CARD_WIDTH, gui.CARD_HEIGHT);

         label.moveAbove(null);
         String txt = "" + card.getValue() + " " + card.getSuit();
         label.setText(txt);

         CardListener listener = new CardListener(card, mauMauControler, label);
         label.addMouseListener(listener);
         label.addMouseMoveListener(listener);
      }

      if (card.getHolder() != null && card.getHolder() instanceof Player)
      {
         Label playerLabel = mauMauControler.getPlayerLabels().get(card.getHolder());
         int xpos = playerLabel.getBounds().x + 100 + card.getHolder().getCards().size() * 40;
         int ypos = playerLabel.getBounds().y - 40 - card.getHolder().getCards().size() * 2;
         label.setBounds(xpos, ypos, gui.CARD_WIDTH, gui.CARD_HEIGHT);
         label.moveAbove(null);
      }

      if (card.getHolder() == mauMauControler.getActivePlayer())
      {
         label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
         label.moveAbove(null);

         String txt = "" + card.getValue() + " " + card.getSuit();
         label.setText(txt);
      }

      if (card.getHolder() == game.getStack())
      {
         // show card
         label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
         label.moveAbove(null);
         String txt = "" + card.getValue() + " " + card.getSuit();
         label.setText(txt);

         if (game.getStack().getCards().size() == 1)
         {
            // first card on stack, move to middle show card

            label.setBounds(350 - gui.CARD_WIDTH / 2, 350 - gui.CARD_HEIGHT / 2, gui.CARD_WIDTH, gui.CARD_HEIGHT);
         }
      }

      if (card.getHolder() == game.getDeck())
      {
         label.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
         label.moveAbove(null);

         String txt = "" + card.getValue() + " " + card.getSuit();
         label.setText(txt);

         int cardNo = game.getDeck().getCards().size();
         int xpos = 150 + (cardNo - 10) * 1;
         int ypos = 350 - gui.CARD_HEIGHT / 2 - (cardNo - 10) * 1;
         label.setBounds(xpos, ypos, gui.CARD_WIDTH, gui.CARD_HEIGHT);
      }

      if (game.getDeck().getCards().size() == 0 && game.getStack().getCards().size() >= 2
            && evt.getPropertyName().equals(Card.PROPERTY_HOLDER)
            && evt.getNewValue() instanceof Player)
      {
         Holder holder = (Holder) evt.getOldValue();
         if (holder.getDeckOwner() != null)
         {
            // last card from deck has been drawn. 
            game.shuffleStackToDeck();
         }
      }
   }

}
