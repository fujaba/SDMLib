package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.pattern.LinkConstraint;

public class LinkConstraintPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new LinkConstraintPO(new LinkConstraint[]{});
      } else {
          return new LinkConstraintPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
}
