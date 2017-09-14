package org.sdmlib.test.historymanagement.marketmodel.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.historymanagement.marketmodel.Market;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

public class MarketPO extends PatternObject<MarketPO, Market>
{

    public MarketSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MarketSet matches = new MarketSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Market) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MarketPO(){
      newInstance(null);
   }

   public MarketPO(Market... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public MarketPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public MarketPO createMarketTimeCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Market.PROPERTY_MARKETTIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MarketPO createMarketTimeCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Market.PROPERTY_MARKETTIME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MarketPO createMarketTimeAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Market.PROPERTY_MARKETTIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getMarketTime()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Market) getCurrentMatch()).getMarketTime();
      }
      return null;
   }
   
   public MarketPO withMarketTime(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Market) getCurrentMatch()).setMarketTime(value);
      }
      return this;
   }
   
   public OfferPO createOffersPO()
   {
      OfferPO result = new OfferPO(new Offer[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Market.PROPERTY_OFFERS, result);
      
      return result;
   }

   public OfferPO createOffersPO(String modifier)
   {
      OfferPO result = new OfferPO(new Offer[]{});
      
      result.setModifier(modifier);
      super.hasLink(Market.PROPERTY_OFFERS, result);
      
      return result;
   }

   public MarketPO createOffersLink(OfferPO tgt)
   {
      return hasLinkConstraint(tgt, Market.PROPERTY_OFFERS);
   }

   public MarketPO createOffersLink(OfferPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Market.PROPERTY_OFFERS, modifier);
   }

   public OfferSet getOffers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Market) this.getCurrentMatch()).getOffers();
      }
      return null;
   }

}
