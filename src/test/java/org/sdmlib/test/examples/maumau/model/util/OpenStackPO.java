package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.OpenStack;

public class OpenStackPO extends PatternObject<OpenStackPO, OpenStack>
{

    public OpenStackSet allMatches()
   {
      this.setDoAllMatches(true);
      
      OpenStackSet matches = new OpenStackSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((OpenStack) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public OpenStackPO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public OpenStackPO(OpenStack... hostGraphObject) {
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

   public OpenStackPO hasCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS);
   }

   public OpenStackPO createCards(CardPO tgt)
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

   public OpenStackPO hasDeckOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_DECKOWNER);
   }

   public OpenStackPO createDeckOwner(MauMauPO tgt)
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

   public OpenStackPO hasStackOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_STACKOWNER);
   }

   public OpenStackPO createStackOwner(MauMauPO tgt)
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

   public MauMauPO hasGame()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(OpenStack.PROPERTY_GAME, result);
      
      return result;
   }

   public MauMauPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public OpenStackPO hasGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_GAME);
   }

   public OpenStackPO createGame(MauMauPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

   public MauMau getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((OpenStack) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public MauMauPO filterDeckOwner()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_DECKOWNER, result);
      
      return result;
   }

   public OpenStackPO filterDeckOwner(MauMauPO tgt)
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

   public OpenStackPO filterStackOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_STACKOWNER);
   }

   public CardPO filterCards()
   {
      CardPO result = new CardPO(new Card[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_CARDS, result);
      
      return result;
   }

   public OpenStackPO filterCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS);
   }

   public MauMauPO filterGame()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(OpenStack.PROPERTY_GAME, result);
      
      return result;
   }

   public OpenStackPO filterGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_GAME);
   }


   public OpenStackPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public CardPO createCardsPO()
   {
      CardPO result = new CardPO(new Card[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(OpenStack.PROPERTY_CARDS, result);
      
      return result;
   }

   public CardPO createCardsPO(String modifier)
   {
      CardPO result = new CardPO(new Card[]{});
      
      result.setModifier(modifier);
      super.hasLink(OpenStack.PROPERTY_CARDS, result);
      
      return result;
   }

   public OpenStackPO createCardsLink(CardPO tgt)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_CARDS);
   }

   public OpenStackPO createCardsLink(CardPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_CARDS, modifier);
   }

   public MauMauPO createDeckOwnerPO()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(OpenStack.PROPERTY_DECKOWNER, result);
      
      return result;
   }

   public MauMauPO createDeckOwnerPO(String modifier)
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(modifier);
      super.hasLink(OpenStack.PROPERTY_DECKOWNER, result);
      
      return result;
   }

   public OpenStackPO createDeckOwnerLink(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_DECKOWNER);
   }

   public OpenStackPO createDeckOwnerLink(MauMauPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_DECKOWNER, modifier);
   }

   public MauMauPO createGamePO()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(OpenStack.PROPERTY_GAME, result);
      
      return result;
   }

   public MauMauPO createGamePO(String modifier)
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(modifier);
      super.hasLink(OpenStack.PROPERTY_GAME, result);
      
      return result;
   }

   public OpenStackPO createGameLink(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_GAME);
   }

   public OpenStackPO createGameLink(MauMauPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_GAME, modifier);
   }

   public MauMauPO createStackOwnerPO()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(OpenStack.PROPERTY_STACKOWNER, result);
      
      return result;
   }

   public MauMauPO createStackOwnerPO(String modifier)
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(modifier);
      super.hasLink(OpenStack.PROPERTY_STACKOWNER, result);
      
      return result;
   }

   public OpenStackPO createStackOwnerLink(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_STACKOWNER);
   }

   public OpenStackPO createStackOwnerLink(MauMauPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, OpenStack.PROPERTY_STACKOWNER, modifier);
   }

}
