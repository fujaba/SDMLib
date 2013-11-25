package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.LudoStoryboard;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class LudoStoryboardCreator extends EntityFactory
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
      return new LudoStoryboard();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((LudoStoryboard) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((LudoStoryboard) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LudoStoryboard) entity).removeYou();
   }
}

