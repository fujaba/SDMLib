package org.sdmlib.serialization.interfaces;

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
/**
 * The Interface SendableEntityCreator. This is the Basic Interface for the
 * Creator for Serialization
 */

public interface SendableEntityCreator
{
   /**
    * Gets the properties.
    * 
    * @return the properties
    */
   public String[] getProperties();

   /**
    * Gets the sendable instance.
    * 
    * @param prototyp
    *           the prototyp
    * @return the sendable instance
    */
   public Object getSendableInstance(boolean prototyp);

   /**
    * Gets the value.
    * 
    * @param entity
    *           the entity
    * @param attribute
    *           the attribute
    * @return the value
    */
   public Object getValue(Object entity, String attribute);

   /**
    * Sets the value.
    * 
    * @param entity
    *           the entity
    * @param attribute
    *           the attribute
    * @param value
    *           the value
    * @param type
    *           edit, update or remove operation
    * @return true, if successful
    */
   public boolean setValue(Object entity, String attribute, Object value,
         String type);
}
