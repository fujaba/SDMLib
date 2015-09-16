package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.OpenStack;
import org.sdmlib.test.examples.maumau.model.util.CardPO;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.util.OpenStackPO;
import org.sdmlib.test.examples.maumau.model.util.CardSet;
import org.sdmlib.test.examples.maumau.model.util.MauMauPO;
import org.sdmlib.test.examples.maumau.model.MauMau;

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

}
