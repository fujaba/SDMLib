package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Suit;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.test.examples.maumau.model.Value;
import org.sdmlib.test.examples.maumau.model.util.MauMauPO;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.util.CardPO;
import org.sdmlib.test.examples.maumau.model.util.HolderPO;
import org.sdmlib.test.examples.maumau.model.Holder;

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


   public CardPO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public CardPO(Cardinality... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public CardPO hasSuit(Suit value)
   {
      new AttributeConstraint()
      .withAttrName(Cardinality.PROPERTY_SUIT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public CardPO createSuit(Suit value)
   {
      this.startCreate().hasSuit(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(Cardinality.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public CardPO createValue(Value value)
   {
      this.startCreate().hasValue(value).endCreate();
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
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cardinality.PROPERTY_GAME, result);
      
      return result;
   }

   public MauMauPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public CardPO hasGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Cardinality.PROPERTY_GAME);
   }

   public CardPO createGame(MauMauPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
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
      HolderPO result = new HolderPO(new Holder[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cardinality.PROPERTY_HOLDER, result);
      
      return result;
   }

   public HolderPO createHolder()
   {
      return this.startCreate().hasHolder().endCreate();
   }

   public CardPO hasHolder(HolderPO tgt)
   {
      return hasLinkConstraint(tgt, Cardinality.PROPERTY_HOLDER);
   }

   public CardPO createHolder(HolderPO tgt)
   {
      return this.startCreate().hasHolder(tgt).endCreate();
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
