package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.SDMLibClass;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class SDMLibClassPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SDMLibClassPO(new SDMLibClass[]{});
      } else {
          return new SDMLibClassPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
