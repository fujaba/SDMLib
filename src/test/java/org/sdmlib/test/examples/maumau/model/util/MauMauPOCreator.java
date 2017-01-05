package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.maumau.model.MauMau;

import de.uniks.networkparser.IdMap;

public class MauMauPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MauMauPO(new MauMau[]{});
      } else {
          return new MauMauPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
