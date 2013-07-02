package org.sdmlib.examples.replication.maumau;

import java.beans.PropertyChangeEvent;
import java.util.Collections;

import org.sdmlib.examples.replication.maumau.creators.CardSet;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.TaskHandler;

public class ServerStartGameHandler extends TaskHandler
{

   private SharedSpace sharedSpace;

   public ServerStartGameHandler(SharedSpace sharedSpace)
   {
      this.sharedSpace = sharedSpace;
   }

   @Override
   public boolean handle(BoardTask oldTask, BoardTask newTask)
   {
      if (newTask != null && MultiMauMau.START_GAME.equals(newTask.getName()))
      {
         MauMau mauMau = (MauMau) sharedSpace.getMap().getObject(sharedSpace.getSpaceId() + "_root");
         
         // deal the cards and ask the first player to do its turn
         mauMau.createDeck();
         mauMau.createStack();
               
         int suitCount = 0;
         for (Suit s : Suit.values())
         {
            for (Value v : Value.values())
            {
               Card card = new Card().withSuit(s).withValue(v);
               mauMau.addToCards(card);
            }
            suitCount++;
            if (suitCount >= 2)
            {
               break;
            }
         }
         
         // deal cards
         Player[] players = mauMau.getPlayers().toArray(new Player[] {});
         
         int i = 0;
         int p = 0;

         CardSet cards = new CardSet();
         cards.addAll(mauMau.getCards());
         
         Collections.shuffle(cards);
         
         for (Card c : cards)
         {
            if (p < players.length)
            {
               players[p].addToCards(c);
               
               i++;
               
               if ( ! (i < 5))
               {
                  i = 0;
                  p++;
               }
            }
            else if (p == players.length)
            {
               // put one card on the stack
               mauMau.getStack().addToCards(c);
               p++;
            }
            else
            { 
               // all other cards put on the deck
               mauMau.getDeck().addToCards(c);
            }
         }
         
         return true;
      }
      
      return false;
   }

}
