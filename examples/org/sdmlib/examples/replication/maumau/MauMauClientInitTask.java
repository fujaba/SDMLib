package org.sdmlib.examples.replication.maumau;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Vector;

import org.sdmlib.examples.replication.maumau.creators.CardSet;


public class MauMauClientInitTask implements Runnable
{

   private MauMauClientGui gui;

   public MauMauClientInitTask(MauMauClientGui gui)
   {
      this.gui = gui;
   }

   @Override
   public void run()
   {
      // create game, players, some cards and deal first cards
      MauMau mauMau = new MauMau();
      
      MauMauControler gameControler = new MauMauControler(mauMau, gui, null).init();
      mauMau.getPropertyChangeSupport().addPropertyChangeListener(gameControler);
      
      Player tom = mauMau.createPlayers().withName("Tom");
      Player albert = mauMau.createPlayers().withName("Albert");
      
      tom.withNext(albert);
      albert.withNext(tom);
      
      gameControler.setActivePlayer(albert);
      
      mauMau.setCurrentMove(albert);
      
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
      
   }

}
