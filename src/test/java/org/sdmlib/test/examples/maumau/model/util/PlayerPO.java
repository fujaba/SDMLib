package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.replication.Lane;
import org.sdmlib.test.examples.maumau.model.util.CardPO;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.util.PlayerPO;
import org.sdmlib.test.examples.maumau.model.util.CardSet;
import org.sdmlib.test.examples.maumau.model.util.MauMauPO;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.util.DutyPO;
import org.sdmlib.test.examples.maumau.model.Duty;
import org.sdmlib.test.examples.maumau.model.util.DutySet;

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


   public PlayerPO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PlayerPO(Player... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PlayerPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
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
   
   public PlayerPO hasLane(Lane value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_LANE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createLane(Lane value)
   {
      this.startCreate().hasLane(value).endCreate();
      return this;
   }
   
   public Lane getLane()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getLane();
      }
      return null;
   }
   
   public PlayerPO withLane(Lane value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setLane(value);
      }
      return this;
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

   public PlayerPO hasCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS);
   }

   public PlayerPO createCards(CardPO tgt)
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

   public PlayerPO hasDeckOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_DECKOWNER);
   }

   public PlayerPO createDeckOwner(MauMauPO tgt)
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

   public PlayerPO hasStackOwner(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_STACKOWNER);
   }

   public PlayerPO createStackOwner(MauMauPO tgt)
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
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public MauMauPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public PlayerPO hasGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public PlayerPO createGame(MauMauPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

   public MauMau getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public MauMauPO hasWonGame()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_WONGAME, result);
      
      return result;
   }

   public MauMauPO createWonGame()
   {
      return this.startCreate().hasWonGame().endCreate();
   }

   public PlayerPO hasWonGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_WONGAME);
   }

   public PlayerPO createWonGame(MauMauPO tgt)
   {
      return this.startCreate().hasWonGame(tgt).endCreate();
   }

   public MauMau getWonGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getWonGame();
      }
      return null;
   }

   public MauMauPO hasLostGame()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_LOSTGAME, result);
      
      return result;
   }

   public MauMauPO createLostGame()
   {
      return this.startCreate().hasLostGame().endCreate();
   }

   public PlayerPO hasLostGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_LOSTGAME);
   }

   public PlayerPO createLostGame(MauMauPO tgt)
   {
      return this.startCreate().hasLostGame(tgt).endCreate();
   }

   public MauMau getLostGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getLostGame();
      }
      return null;
   }

   public PlayerPO hasNext()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_NEXT, result);
      
      return result;
   }

   public PlayerPO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public PlayerPO hasNext(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_NEXT);
   }

   public PlayerPO createNext(PlayerPO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
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
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PREV, result);
      
      return result;
   }

   public PlayerPO createPrev()
   {
      return this.startCreate().hasPrev().endCreate();
   }

   public PlayerPO hasPrev(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PREV);
   }

   public PlayerPO createPrev(PlayerPO tgt)
   {
      return this.startCreate().hasPrev(tgt).endCreate();
   }

   public Player getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public DutyPO hasDuty()
   {
      DutyPO result = new DutyPO(new Duty[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_DUTY, result);
      
      return result;
   }

   public DutyPO createDuty()
   {
      return this.startCreate().hasDuty().endCreate();
   }

   public PlayerPO hasDuty(DutyPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_DUTY);
   }

   public PlayerPO createDuty(DutyPO tgt)
   {
      return this.startCreate().hasDuty(tgt).endCreate();
   }

   public DutySet getDuty()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getDuty();
      }
      return null;
   }

   public PlayerPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterLane(Lane value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_LANE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MauMauPO filterDeckOwner()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Holder.PROPERTY_DECKOWNER, result);
      
      return result;
   }

   public PlayerPO filterDeckOwner(MauMauPO tgt)
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

   public PlayerPO filterStackOwner(MauMauPO tgt)
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

   public PlayerPO filterCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, Holder.PROPERTY_CARDS);
   }

   public MauMauPO filterGame()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public PlayerPO filterGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public MauMauPO filterWonGame()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_WONGAME, result);
      
      return result;
   }

   public PlayerPO filterWonGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_WONGAME);
   }

   public MauMauPO filterLostGame()
   {
      MauMauPO result = new MauMauPO(new MauMau[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_LOSTGAME, result);
      
      return result;
   }

   public PlayerPO filterLostGame(MauMauPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_LOSTGAME);
   }

   public PlayerPO filterPrev()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PREV, result);
      
      return result;
   }

   public PlayerPO filterPrev(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PREV);
   }

   public DutyPO filterDuty()
   {
      DutyPO result = new DutyPO(new Duty[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_DUTY, result);
      
      return result;
   }

   public PlayerPO filterDuty(DutyPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_DUTY);
   }

   public PlayerPO filterNext()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_NEXT, result);
      
      return result;
   }

   public PlayerPO filterNext(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_NEXT);
   }

}
