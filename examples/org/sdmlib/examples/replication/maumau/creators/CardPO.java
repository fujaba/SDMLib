package org.sdmlib.examples.replication.maumau.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.replication.maumau.Card;
import org.sdmlib.examples.replication.maumau.Suit;
import org.sdmlib.examples.replication.maumau.creators.CardSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.examples.replication.maumau.Value;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.replication.maumau.creators.MauMauPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.replication.maumau.creators.CardPO;
import org.sdmlib.examples.replication.maumau.MauMau;
import org.sdmlib.examples.replication.maumau.creators.HolderPO;
import org.sdmlib.examples.replication.maumau.Holder;

public class CardPO extends PatternObject<CardPO, Card>
{
   public CardSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CardSet matches = new CardSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Card) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public CardPO hasSuit(Suit value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Card.PROPERTY_SUIT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Suit getSuit()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Card) getCurrentMatch()).getSuit();
      }
      return null;
   }
   
   public CardPO withSuit(Suit value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Card) getCurrentMatch()).setSuit(value);
      }
      return this;
   }
   
   public CardPO hasValue(Value value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Card.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Value getValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Card) getCurrentMatch()).getValue();
      }
      return null;
   }
   
   public CardPO withValue(Value value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Card) getCurrentMatch()).setValue(value);
      }
      return this;
   }
   
   public MauMauPO hasGame()
   {
      MauMauPO result = new MauMauPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Card.PROPERTY_GAME, result);
      
      return result;
   }

   public CardPO hasGame(MauMauPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Card.PROPERTY_GAME)
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
         return ((Card) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public HolderPO hasHolder()
   {
      HolderPO result = new HolderPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Card.PROPERTY_HOLDER, result);
      
      return result;
   }

   public CardPO hasHolder(HolderPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Card.PROPERTY_HOLDER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Holder getHolder()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Card) this.getCurrentMatch()).getHolder();
      }
      return null;
   }

}

