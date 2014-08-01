package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.classes.EnumerationValue;

public class EnumerationValuePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new EnumerationValuePO(new EnumerationValue[]{});
      } else {
          return new EnumerationValuePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.models.classes.util.CreatorCreator.createIdMap(sessionID);
   }
}
