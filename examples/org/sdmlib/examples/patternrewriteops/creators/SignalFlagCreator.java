package org.sdmlib.examples.patternrewriteops.creators;

import org.sdmlib.examples.patternrewriteops.SignalFlag;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class SignalFlagCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { SignalFlag.PROPERTY_STATION, };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new SignalFlag();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((SignalFlag) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((SignalFlag) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((SignalFlag) entity).removeYou();
   }
}
