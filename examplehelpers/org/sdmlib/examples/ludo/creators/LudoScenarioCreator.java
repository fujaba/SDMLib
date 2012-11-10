package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.ludo.LudoScenario;

public class LudoScenarioCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new LudoScenario();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LudoScenario) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LudoScenario) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LudoScenario) entity).removeYou();
   }
}

