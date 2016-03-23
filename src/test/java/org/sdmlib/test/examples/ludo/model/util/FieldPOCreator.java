package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.ludo.model.Field;

import de.uniks.networkparser.IdMap;

public class FieldPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new FieldPO(new Field[]{});
      } else {
          return new FieldPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
