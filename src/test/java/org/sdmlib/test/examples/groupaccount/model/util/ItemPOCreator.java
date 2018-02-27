package org.sdmlib.test.examples.groupaccount.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.groupaccount.model.Item;

public class ItemPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ItemPO(new Item[]{});
      } else {
          return new ItemPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.groupaccount.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
