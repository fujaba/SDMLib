package org.sdmlib.test.historymanagement.marketmodel.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.Bid;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

public class ActorPO extends PatternObject<ActorPO, Actor>
{

    public ActorSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ActorSet matches = new ActorSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Actor) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ActorPO(){
      newInstance(null);
   }

   public ActorPO(Actor... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public ActorPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ActorPO createActorNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Actor.PROPERTY_ACTORNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ActorPO createActorNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Actor.PROPERTY_ACTORNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ActorPO createActorNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Actor.PROPERTY_ACTORNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getActorName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Actor) getCurrentMatch()).getActorName();
      }
      return null;
   }
   
   public ActorPO withActorName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Actor) getCurrentMatch()).setActorName(value);
      }
      return this;
   }
   
   public BidPO createBidsPO()
   {
      BidPO result = new BidPO(new Bid[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Actor.PROPERTY_BIDS, result);
      
      return result;
   }

   public BidPO createBidsPO(String modifier)
   {
      BidPO result = new BidPO(new Bid[]{});
      
      result.setModifier(modifier);
      super.hasLink(Actor.PROPERTY_BIDS, result);
      
      return result;
   }

   public ActorPO createBidsLink(BidPO tgt)
   {
      return hasLinkConstraint(tgt, Actor.PROPERTY_BIDS);
   }

   public ActorPO createBidsLink(BidPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Actor.PROPERTY_BIDS, modifier);
   }

   public BidSet getBids()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Actor) this.getCurrentMatch()).getBids();
      }
      return null;
   }

   public OfferPO createOffersPO()
   {
      OfferPO result = new OfferPO(new Offer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Actor.PROPERTY_OFFERS, result);
      
      return result;
   }

   public OfferPO createOffersPO(String modifier)
   {
      OfferPO result = new OfferPO(new Offer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Actor.PROPERTY_OFFERS, result);
      
      return result;
   }

   public ActorPO createOffersLink(OfferPO tgt)
   {
      return hasLinkConstraint(tgt, Actor.PROPERTY_OFFERS);
   }

   public ActorPO createOffersLink(OfferPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Actor.PROPERTY_OFFERS, modifier);
   }

   public OfferSet getOffers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Actor) this.getCurrentMatch()).getOffers();
      }
      return null;
   }

}
