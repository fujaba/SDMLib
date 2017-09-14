package org.sdmlib.test.historymanagement.marketmodel.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.historymanagement.marketmodel.Offer;

import de.uniks.networkparser.IdMap;

public class OfferPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new OfferPO(new Offer[]{});
      } else {
          return new OfferPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.historymanagement.marketmodel.util.CreatorCreator.createIdMap(sessionID);
   }
}
