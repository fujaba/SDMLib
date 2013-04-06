package org.sdmlib.examples.replication.creators;

import org.sdmlib.examples.replication.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.replication.ChatRoot;

public class ChatRootCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ChatRoot.PROPERTY_MSGS,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new ChatRoot();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((ChatRoot) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((ChatRoot) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ChatRoot) entity).removeYou();
   }
}

