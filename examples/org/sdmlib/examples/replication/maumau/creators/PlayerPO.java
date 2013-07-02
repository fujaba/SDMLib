package org.sdmlib.examples.replication.maumau.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.replication.maumau.Player;
import org.sdmlib.examples.replication.maumau.creators.PlayerSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.replication.maumau.creators.MauMauPO;
import org.sdmlib.examples.replication.maumau.Holder;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.replication.maumau.creators.PlayerPO;
import org.sdmlib.examples.replication.maumau.MauMau;
import org.sdmlib.examples.replication.maumau.creators.CardPO;
import org.sdmlib.examples.replication.maumau.Card;
import org.sdmlib.examples.replication.maumau.creators.CardSet;
import org.sdmlib.examples.replication.maumau.creators.MauMauSet;

public class PlayerPO extends PatternObject<PlayerPO, Player>
{
   public PlayerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PlayerSet matches = new PlayerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Player) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public PlayerPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PlayerPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public MauMauPO hasDeckOwner()
   {
      MauMauPO result = new MauMauPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Holder.PROPERTY_DECKOWNER, result);
      
      return result;
   }

   public PlayerPO hasDeckOwner(MauMauPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Holder.PROPERTY_DECKOWNER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public MauMau getDeckOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Holder) this.getCurrentMatch()).getDeckOwner();
      }
      return null;
   }

   public CardPO hasCards()
   {
      CardPO result = new CardPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Holder.PROPERTY_CARDS, result);
      
      return result;
   }

   public PlayerPO hasCards(CardPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Holder.PROPERTY_CARDS)
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
         return ((Holder) this.getCurrentMatch()).getCards();
      }
      return null;
   }

   public MauMauPO hasStackOwner()
   {
      MauMauPO result = new MauMauPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Holder.PROPERTY_STACKOWNER, result);
      
      return result;
   }

   public PlayerPO hasStackOwner(MauMauPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Holder.PROPERTY_STACKOWNER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public MauMauSet getStackOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Holder) this.getCurrentMatch()).getStackOwner();
      }
      return null;
   }

   public MauMauPO hasGame()
   {
      MauMauPO result = new MauMauPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public PlayerPO hasGame(MauMauPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_GAME)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public MauMau getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public PlayerPO hasNext()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_NEXT, result);
      
      return result;
   }

   public PlayerPO hasNext(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_NEXT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Player getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public PlayerPO hasPrev()
   {
      PlayerPO result = new PlayerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_PREV, result);
      
      return result;
   }

   public PlayerPO hasPrev(PlayerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_PREV)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Player getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public MauMauPO hasAssignment()
   {
      MauMauPO result = new MauMauPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Player.PROPERTY_ASSIGNMENT, result);
      
      return result;
   }

   public PlayerPO hasAssignment(MauMauPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Player.PROPERTY_ASSIGNMENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public MauMau getAssignment()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getAssignment();
      }
      return null;
   }

}

