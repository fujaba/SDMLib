package org.sdmlib.test.examples.SimpleModelWithSet.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.SimpleModelWithSet.model.Child;

import de.uniks.networkparser.IdMap;

public class ChildPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ChildPO(new Child[]{});
      } else {
          return new ChildPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.SimpleModelWithSet.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
