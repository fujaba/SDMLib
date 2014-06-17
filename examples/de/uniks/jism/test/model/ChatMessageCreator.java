package de.uniks.jism.test.model;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class ChatMessageCreator implements SendableEntityCreator
{
   public String[] getProperties()
   {
      return new String[]
      { ChatMessage.PROPERTY_SENDER, ChatMessage.PROPERTY_TIME,
            ChatMessage.PROPERTY_TEXT, ChatMessage.PROPERTY_COUNT,
            ChatMessage.PROPERTY_ACTIV };
   }

   public Object getSendableInstance(boolean reference)
   {
      return new ChatMessage();
   }

   public Object getValue(Object entity, String attribute)
   {
      return ((ChatMessage) entity).get(attribute);
   }

   public boolean setValue(Object entity, String attribute, Object value,
         String typ)
   {
      return ((ChatMessage) entity).set(attribute, value);
   }
}