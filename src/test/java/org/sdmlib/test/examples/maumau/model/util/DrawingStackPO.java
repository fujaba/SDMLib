package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.DrawingStack;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.MauMau;

public class DrawingStackPO extends PatternObject<DrawingStackPO, DrawingStack>
{

    public DrawingStackSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DrawingStackSet matches = new DrawingStackSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((DrawingStack) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public DrawingStackPO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public DrawingStackPO(DrawingStack... hostGraphObject) {
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

   public DrawingStackPO hasCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS);
   }

   public DrawingStackPO createCards(CardPO tgt)
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

   public DrawingStackPO hasDeckOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_DECKOWNER);
   }

   public DrawingStackPO createDeckOwner(MauMauPO tgt)
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

   public DrawingStackPO hasStackOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_STACKOWNER);
   }

   public DrawingStackPO createStackOwner(MauMauPO tgt)
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
      super.hasLink(DrawingStack.PROPERTY_GAME, result);
      
      return result;
   }

   public MauMauPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public DrawingStackPO hasGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, DrawingStack.PROPERTY_GAME);
   }

   public DrawingStackPO createGame(MauMauPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

   public MauMau getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((DrawingStack) this.getCurrentMatch()).getGame();
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

   public DrawingStackPO filterDeckOwner(MauMauPO tgt)
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

   public DrawingStackPO filterStackOwner(MauMauPO tgt)
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

   public DrawingStackPO filterCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS);
   }

   public MauMauPO filterGame()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(DrawingStack.PROPERTY_GAME, result);
      
      return result;
   }

   public DrawingStackPO filterGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, DrawingStack.PROPERTY_GAME);
   }

}
