package org.sdmlib.serialization.event.creator;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
 */
import org.sdmlib.serialization.event.ByteMessage;
import org.sdmlib.serialization.interfaces.SendableEntityCreatorByte;

/**
 * The Class ByteMessageCreator.
 */

public class ByteMessageCreator implements SendableEntityCreatorByte
{
   /** The properties. */
   private final String[] properties = new String[]
   { ByteMessage.PROPERTY_VALUE };

   /*
    * return the Properties
    */
   @Override
   public String[] getProperties()
   {
      return properties;
   }

   /*
    * Create new Instance of ByteMessage
    */
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ByteMessage();
   }

   /*
    * Get the EventTyp of BasicMessage (0x01)
    */
   @Override
   public byte getEventTyp()
   {
      return 0x01;
   }

   /*
    * Getter for ByteMessage
    */
   @Override
   public Object getValue(Object entity, String attribute)
   {
      return ((ByteMessage) entity).get(attribute);
   }

   /*
    * Setter for ByteMessage
    */
   @Override
   public boolean setValue(Object entity, String attribute, Object value,
         String typ)
   {
      return ((ByteMessage) entity).set(attribute, value);
   }
}
