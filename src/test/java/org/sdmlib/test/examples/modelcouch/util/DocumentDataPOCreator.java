package org.sdmlib.test.examples.modelcouch.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.modelcouch.DocumentData;

import de.uniks.networkparser.IdMap;

public class DocumentDataPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new DocumentDataPO(new DocumentData[]{});
      } else {
          return new DocumentDataPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.modelcouch.util.CreatorCreator.createIdMap(sessionID);
   }
}
