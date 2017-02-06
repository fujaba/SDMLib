package org.sdmlib.test.historymanagement.marketmodel.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.historymanagement.marketmodel.Offer;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.historymanagement.marketmodel.util.BidPO;
import org.sdmlib.test.historymanagement.marketmodel.Bid;
import org.sdmlib.test.historymanagement.marketmodel.util.OfferPO;
import org.sdmlib.test.historymanagement.marketmodel.util.BidSet;
import org.sdmlib.test.historymanagement.marketmodel.util.ActorPO;
import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.util.MarketPO;
import org.sdmlib.test.historymanagement.marketmodel.Market;

public class OfferPO extends PatternObject<OfferPO, Offer>
{

    public OfferSet allMatches()
   {
      this.setDoAllMatches(true);
      
      OfferSet matches = new OfferSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Offer) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public OfferPO(){
      newInstance(null);
   }

   public OfferPO(Offer... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public OfferPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public OfferPO createDescriptionCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Offer.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OfferPO createDescriptionCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Offer.PROPERTY_DESCRIPTION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OfferPO createDescriptionAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Offer.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getDescription()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Offer) getCurrentMatch()).getDescription();
      }
      return null;
   }
   
   public OfferPO withDescription(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Offer) getCurrentMatch()).setDescription(value);
      }
      return this;
   }
   
   public OfferPO createTimeLimitCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Offer.PROPERTY_TIMELIMIT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OfferPO createTimeLimitCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Offer.PROPERTY_TIMELIMIT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public OfferPO createTimeLimitAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Offer.PROPERTY_TIMELIMIT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getTimeLimit()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Offer) getCurrentMatch()).getTimeLimit();
      }
      return null;
   }
   
   public OfferPO withTimeLimit(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Offer) getCurrentMatch()).setTimeLimit(value);
      }
      return this;
   }
   
   public BidPO createBidsPO()
   {
      BidPO result = new BidPO(new Bid[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Offer.PROPERTY_BIDS, result);
      
      return result;
   }

   public BidPO createBidsPO(String modifier)
   {
      BidPO result = new BidPO(new Bid[]{});
      
      result.setModifier(modifier);
      super.hasLink(Offer.PROPERTY_BIDS, result);
      
      return result;
   }

   public OfferPO createBidsLink(BidPO tgt)
   {
      return hasLinkConstraint(tgt, Offer.PROPERTY_BIDS);
   }

   public OfferPO createBidsLink(BidPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Offer.PROPERTY_BIDS, modifier);
   }

   public BidSet getBids()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Offer) this.getCurrentMatch()).getBids();
      }
      return null;
   }

   public ActorPO createOwnerPO()
   {
      ActorPO result = new ActorPO(new Actor[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Offer.PROPERTY_OWNER, result);
      
      return result;
   }

   public ActorPO createOwnerPO(String modifier)
   {
      ActorPO result = new ActorPO(new Actor[]{});
      
      result.setModifier(modifier);
      super.hasLink(Offer.PROPERTY_OWNER, result);
      
      return result;
   }

   public OfferPO createOwnerLink(ActorPO tgt)
   {
      return hasLinkConstraint(tgt, Offer.PROPERTY_OWNER);
   }

   public OfferPO createOwnerLink(ActorPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Offer.PROPERTY_OWNER, modifier);
   }

   public Actor getOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Offer) this.getCurrentMatch()).getOwner();
      }
      return null;
   }

   public MarketPO createMarketPO()
   {
      MarketPO result = new MarketPO(new Market[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Offer.PROPERTY_MARKET, result);
      
      return result;
   }

   public MarketPO createMarketPO(String modifier)
   {
      MarketPO result = new MarketPO(new Market[]{});
      
      result.setModifier(modifier);
      super.hasLink(Offer.PROPERTY_MARKET, result);
      
      return result;
   }

   public OfferPO createMarketLink(MarketPO tgt)
   {
      return hasLinkConstraint(tgt, Offer.PROPERTY_MARKET);
   }

   public OfferPO createMarketLink(MarketPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Offer.PROPERTY_MARKET, modifier);
   }

   public Market getMarket()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Offer) this.getCurrentMatch()).getMarket();
      }
      return null;
   }

}
