package org.sdmlib.examples.replication.maumau.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.replication.maumau.MauMau;
import org.sdmlib.examples.replication.maumau.creators.MauMauSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.replication.maumau.creators.CardPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.replication.maumau.creators.MauMauPO;
import org.sdmlib.examples.replication.maumau.Card;
import org.sdmlib.examples.replication.maumau.creators.CardSet;
import org.sdmlib.examples.replication.maumau.creators.HolderPO;
import org.sdmlib.examples.replication.maumau.Holder;
import org.sdmlib.examples.replication.maumau.creators.PlayerPO;
import org.sdmlib.examples.replication.maumau.Player;
import org.sdmlib.examples.replication.maumau.creators.PlayerSet;

public class MauMauPO extends PatternObject<MauMauPO, MauMau>
{
   public MauMauSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MauMauSet matches = new MauMauSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MauMau) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public CardPO hasCards()
   {
      CardPO result = new CardPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(MauMau.PROPERTY_CARDS, result);
      
      return result;
   }

   public MauMauPO hasCards(CardPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(MauMau.PROPERTY_CARDS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public CardSet getCards()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getCards();
      }
      return null;
   }

   public HolderPO hasDeck()
   {
      HolderPO result = new HolderPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(MauMau.PROPERTY_DECK, result);
      
      return result;
   }

   public MauMauPO hasDeck(HolderPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(MauMau.PROPERTY_DECK)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Holder getDeck()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getDeck();
      }
      return null;
   }

   public HolderPO hasStack()
   {
      HolderPO result = new HolderPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(MauMau.PROPERTY_STACK, result);
      
      return result;
   }

   public MauMauPO hasStack(HolderPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(MauMau.PROPERTY_STACK)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Holder getStack()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getStack();
      }
      return null;
   }

   public PlayerPO hasPlayers()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(MauMau.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public MauMauPO hasPlayers(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(MauMau.PROPERTY_PLAYERS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PlayerSet getPlayers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getPlayers();
      }
      return null;
   }

   public PlayerPO hasCurrentMove()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(MauMau.PROPERTY_CURRENTMOVE, result);
      
      return result;
   }

   public MauMauPO hasCurrentMove(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(MauMau.PROPERTY_CURRENTMOVE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Player getCurrentMove()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getCurrentMove();
      }
      return null;
   }

}

