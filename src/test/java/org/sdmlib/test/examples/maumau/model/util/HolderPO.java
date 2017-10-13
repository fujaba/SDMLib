package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.util.CardPO;
import org.sdmlib.test.examples.maumau.model.util.HolderPO;
import org.sdmlib.test.examples.maumau.model.util.MauMauPO;

public class HolderPO extends PatternObject<HolderPO, Holder>
{

    public HolderSet allMatches()
   {
      this.setDoAllMatches(true);
      
      HolderSet matches = new HolderSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Holder) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public HolderPO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public HolderPO(Holder... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public CardPO hasCards()
   {
      CardPO result = new CardPO(new Card[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_CARDS, result);
      
      return result;
   }

   public CardPO createCards()
   {
      return this.startCreate().hasCards().endCreate();
   }

   public HolderPO hasCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS);
   }

   public HolderPO createCards(CardPO tgt)
   {
      return this.startCreate().hasCards(tgt).endCreate();
   }

   public CardSet getCards()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Holder) this.getCurrentMatch()).getCards();
      }
      return null;
   }

   public MauMauPO hasDeckOwner()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_DECKOWNER, result);
      
      return result;
   }

   public MauMauPO createDeckOwner()
   {
      return this.startCreate().hasDeckOwner().endCreate();
   }

   public HolderPO hasDeckOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_DECKOWNER);
   }

   public HolderPO createDeckOwner(MauMauPO tgt)
   {
      return this.startCreate().hasDeckOwner(tgt).endCreate();
   }

   public MauMau getDeckOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Holder) this.getCurrentMatch()).getDeckOwner();
      }
      return null;
   }

   public MauMauPO hasStackOwner()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_STACKOWNER, result);
      
      return result;
   }

   public MauMauPO createStackOwner()
   {
      return this.startCreate().hasStackOwner().endCreate();
   }

   public HolderPO hasStackOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_STACKOWNER);
   }

   public HolderPO createStackOwner(MauMauPO tgt)
   {
      return this.startCreate().hasStackOwner(tgt).endCreate();
   }

   public MauMau getStackOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Holder) this.getCurrentMatch()).getStackOwner();
      }
      return null;
   }

   public CardPO filterCards()
   {
      CardPO result = new CardPO(new Card[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_CARDS, result);
      
      return result;
   }

   public HolderPO filterCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS);
   }

   public MauMauPO filterDeckOwner()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_DECKOWNER, result);
      
      return result;
   }

   public HolderPO filterDeckOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_DECKOWNER);
   }

   public MauMauPO filterStackOwner()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_STACKOWNER, result);
      
      return result;
   }

   public HolderPO filterStackOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_STACKOWNER);
   }


   public HolderPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public CardPO createCardsPO()
   {
      CardPO result = new CardPO(new Card[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_CARDS, result);
      
      return result;
   }

   public CardPO createCardsPO(String modifier)
   {
      CardPO result = new CardPO(new Card[]{});
      
      result.setModifier(modifier);
      super.hasLink(Holder.PROPERTY_CARDS, result);
      
      return result;
   }

   public HolderPO createCardsLink(CardPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS);
   }

   public HolderPO createCardsLink(CardPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS, modifier);
   }

   public MauMauPO createDeckOwnerPO()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_DECKOWNER, result);
      
      return result;
   }

   public MauMauPO createDeckOwnerPO(String modifier)
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(modifier);
      super.hasLink(Holder.PROPERTY_DECKOWNER, result);
      
      return result;
   }

   public HolderPO createDeckOwnerLink(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_DECKOWNER);
   }

   public HolderPO createDeckOwnerLink(MauMauPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_DECKOWNER, modifier);
   }

   public MauMauPO createStackOwnerPO()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_STACKOWNER, result);
      
      return result;
   }

   public MauMauPO createStackOwnerPO(String modifier)
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(modifier);
      super.hasLink(Holder.PROPERTY_STACKOWNER, result);
      
      return result;
   }

   public HolderPO createStackOwnerLink(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_STACKOWNER);
   }

   public HolderPO createStackOwnerLink(MauMauPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_STACKOWNER, modifier);
   }

}
