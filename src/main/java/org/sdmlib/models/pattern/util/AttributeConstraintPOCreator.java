package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;

import de.uniks.networkparser.IdMap;

public class AttributeConstraintPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AttributeConstraintPO(new AttributeConstraint[]{});
      } else {
          return new AttributeConstraintPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
}
