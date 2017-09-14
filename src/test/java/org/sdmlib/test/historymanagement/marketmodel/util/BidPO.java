package org.sdmlib.test.historymanagement.marketmodel.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.historymanagement.marketmodel.Actor;
import org.sdmlib.test.historymanagement.marketmodel.Bid;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

public class BidPO extends PatternObject<BidPO, Bid>
{

    public BidSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BidSet matches = new BidSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Bid) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BidPO(){
      newInstance(null);
   }

   public BidPO(Bid... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public BidPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public BidPO createAmountCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Bid.PROPERTY_AMOUNT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BidPO createAmountCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Bid.PROPERTY_AMOUNT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BidPO createAmountAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Bid.PROPERTY_AMOUNT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getAmount()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bid) getCurrentMatch()).getAmount();
      }
      return 0;
   }
   
   public BidPO withAmount(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Bid) getCurrentMatch()).setAmount(value);
      }
      return this;
   }
   
   public ActorPO createBidderPO()
   {
      ActorPO result = new ActorPO(new Actor[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bid.PROPERTY_BIDDER, result);
      
      return result;
   }

   public ActorPO createBidderPO(String modifier)
   {
      ActorPO result = new ActorPO(new Actor[]{});
      
      result.setModifier(modifier);
      super.hasLink(Bid.PROPERTY_BIDDER, result);
      
      return result;
   }

   public BidPO createBidderLink(ActorPO tgt)
   {
      return hasLinkConstraint(tgt, Bid.PROPERTY_BIDDER);
   }

   public BidPO createBidderLink(ActorPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Bid.PROPERTY_BIDDER, modifier);
   }

   public Actor getBidder()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bid) this.getCurrentMatch()).getBidder();
      }
      return null;
   }

   public OfferPO createOfferPO()
   {
      OfferPO result = new OfferPO(new Offer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Bid.PROPERTY_OFFER, result);
      
      return result;
   }

   public OfferPO createOfferPO(String modifier)
   {
      OfferPO result = new OfferPO(new Offer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Bid.PROPERTY_OFFER, result);
      
      return result;
   }

   public BidPO createOfferLink(OfferPO tgt)
   {
      return hasLinkConstraint(tgt, Bid.PROPERTY_OFFER);
   }

   public BidPO createOfferLink(OfferPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Bid.PROPERTY_OFFER, modifier);
   }

   public Offer getOffer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Bid) this.getCurrentMatch()).getOffer();
      }
      return null;
   }

}
