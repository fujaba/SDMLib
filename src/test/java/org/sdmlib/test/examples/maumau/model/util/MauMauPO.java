package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.DrawingStack;
import org.sdmlib.test.examples.maumau.model.Holder;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.OpenStack;
import org.sdmlib.test.examples.maumau.model.Player;
import org.sdmlib.test.examples.maumau.model.Suit;

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


   public MauMauPO(){
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MauMauPO(MauMau... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MauMauPO hasCurrentPlayer(Player value)
   {
      new AttributeConstraint()
      .withAttrName(MauMau.PROPERTY_CURRENTPLAYER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MauMauPO createCurrentPlayer(Player value)
   {
      this.startCreate().hasCurrentPlayer(value).endCreate();
      return this;
   }
   
   public Player getCurrentPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) getCurrentMatch()).getCurrentPlayer();
      }
      return null;
   }
   
   public MauMauPO withCurrentPlayer(Player value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MauMau) getCurrentMatch()).setCurrentPlayer(value);
      }
      return this;
   }
   
   public MauMauPO hasCurrentSuit(Suit value)
   {
      new AttributeConstraint()
      .withAttrName(MauMau.PROPERTY_CURRENTSUIT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MauMauPO createCurrentSuit(Suit value)
   {
      this.startCreate().hasCurrentSuit(value).endCreate();
      return this;
   }
   
   public Suit getCurrentSuit()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) getCurrentMatch()).getCurrentSuit();
      }
      return null;
   }
   
   public MauMauPO withCurrentSuit(Suit value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MauMau) getCurrentMatch()).setCurrentSuit(value);
      }
      return this;
   }
   
   public CardPO hasCards()
   {
      CardPO result = new CardPO(new Card[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_CARDS, result);
      
      return result;
   }

   public CardPO createCards()
   {
      return this.startCreate().hasCards().endCreate();
   }

   public MauMauPO hasCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_CARDS);
   }

   public MauMauPO createCards(CardPO tgt)
   {
      return this.startCreate().hasCards(tgt).endCreate();
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
      HolderPO result = new HolderPO(new Holder[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_DECK, result);
      
      return result;
   }

   public HolderPO createDeck()
   {
      return this.startCreate().hasDeck().endCreate();
   }

   public MauMauPO hasDeck(HolderPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_DECK);
   }

   public MauMauPO createDeck(HolderPO tgt)
   {
      return this.startCreate().hasDeck(tgt).endCreate();
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
      HolderPO result = new HolderPO(new Holder[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_STACK, result);
      
      return result;
   }

   public HolderPO createStack()
   {
      return this.startCreate().hasStack().endCreate();
   }

   public MauMauPO hasStack(HolderPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_STACK);
   }

   public MauMauPO createStack(HolderPO tgt)
   {
      return this.startCreate().hasStack(tgt).endCreate();
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
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public PlayerPO createPlayers()
   {
      return this.startCreate().hasPlayers().endCreate();
   }

   public MauMauPO hasPlayers(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_PLAYERS);
   }

   public MauMauPO createPlayers(PlayerPO tgt)
   {
      return this.startCreate().hasPlayers(tgt).endCreate();
   }

   public PlayerSet getPlayers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getPlayers();
      }
      return null;
   }

   public PlayerPO hasWinner()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_WINNER, result);
      
      return result;
   }

   public PlayerPO createWinner()
   {
      return this.startCreate().hasWinner().endCreate();
   }

   public MauMauPO hasWinner(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_WINNER);
   }

   public MauMauPO createWinner(PlayerPO tgt)
   {
      return this.startCreate().hasWinner(tgt).endCreate();
   }

   public Player getWinner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getWinner();
      }
      return null;
   }

   public PlayerPO hasLosers()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_LOSERS, result);
      
      return result;
   }

   public PlayerPO createLosers()
   {
      return this.startCreate().hasLosers().endCreate();
   }

   public MauMauPO hasLosers(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_LOSERS);
   }

   public MauMauPO createLosers(PlayerPO tgt)
   {
      return this.startCreate().hasLosers(tgt).endCreate();
   }

   public PlayerSet getLosers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getLosers();
      }
      return null;
   }

   public DrawingStackPO hasDrawingStack()
   {
      DrawingStackPO result = new DrawingStackPO(new DrawingStack[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_DRAWINGSTACK, result);
      
      return result;
   }

   public DrawingStackPO createDrawingStack()
   {
      return this.startCreate().hasDrawingStack().endCreate();
   }

   public MauMauPO hasDrawingStack(DrawingStackPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_DRAWINGSTACK);
   }

   public MauMauPO createDrawingStack(DrawingStackPO tgt)
   {
      return this.startCreate().hasDrawingStack(tgt).endCreate();
   }

   public DrawingStack getDrawingStack()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getDrawingStack();
      }
      return null;
   }

   public OpenStackPO hasOpenStack()
   {
      OpenStackPO result = new OpenStackPO(new OpenStack[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_OPENSTACK, result);
      
      return result;
   }

   public OpenStackPO createOpenStack()
   {
      return this.startCreate().hasOpenStack().endCreate();
   }

   public MauMauPO hasOpenStack(OpenStackPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_OPENSTACK);
   }

   public MauMauPO createOpenStack(OpenStackPO tgt)
   {
      return this.startCreate().hasOpenStack(tgt).endCreate();
   }

   public OpenStack getOpenStack()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MauMau) this.getCurrentMatch()).getOpenStack();
      }
      return null;
   }

   public MauMauPO filterCurrentPlayer(Player value)
   {
      new AttributeConstraint()
      .withAttrName(MauMau.PROPERTY_CURRENTPLAYER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MauMauPO filterCurrentSuit(Suit value)
   {
      new AttributeConstraint()
      .withAttrName(MauMau.PROPERTY_CURRENTSUIT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CardPO filterCards()
   {
      CardPO result = new CardPO(new Card[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_CARDS, result);
      
      return result;
   }

   public MauMauPO filterCards(CardPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_CARDS);
   }

   public HolderPO filterDeck()
   {
      HolderPO result = new HolderPO(new Holder[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_DECK, result);
      
      return result;
   }

   public MauMauPO filterDeck(HolderPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_DECK);
   }

   public HolderPO filterStack()
   {
      HolderPO result = new HolderPO(new Holder[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_STACK, result);
      
      return result;
   }

   public MauMauPO filterStack(HolderPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_STACK);
   }

   public PlayerPO filterPlayers()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_PLAYERS, result);
      
      return result;
   }

   public MauMauPO filterPlayers(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_PLAYERS);
   }

   public PlayerPO filterWinner()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_WINNER, result);
      
      return result;
   }

   public MauMauPO filterWinner(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_WINNER);
   }

   public PlayerPO filterLosers()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_LOSERS, result);
      
      return result;
   }

   public MauMauPO filterLosers(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_LOSERS);
   }

   public DrawingStackPO filterDrawingStack()
   {
      DrawingStackPO result = new DrawingStackPO(new DrawingStack[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_DRAWINGSTACK, result);
      
      return result;
   }

   public MauMauPO filterDrawingStack(DrawingStackPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_DRAWINGSTACK);
   }

   public OpenStackPO filterOpenStack()
   {
      OpenStackPO result = new OpenStackPO(new OpenStack[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MauMau.PROPERTY_OPENSTACK, result);
      
      return result;
   }

   public MauMauPO filterOpenStack(OpenStackPO tgt)
   {
      return hasLinkConstraint(tgt, MauMau.PROPERTY_OPENSTACK);
   }

}
