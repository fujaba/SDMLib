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
import org.sdmlib.serialization.json.JsonObject;

/**
 * The listener interface for receiving mapUpdate events. The class that is
 * interested in processing a mapUpdate event implements this interface, and the
 * object created with that class is registered with a component using the
 * component's <code>addMapUpdateListener<code> method. When
 * the mapUpdate event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see MapUpdateEvent
 */

public interface MapUpdateListener
{
   /**
    * Send update msg.
    * 
    * @param jsonObject
    *           the json object
    * @return true, if successful
    */
   public boolean sendUpdateMsg(Object target, String property, Object oldObj,
         Object newObject, JsonObject jsonObject);

   public boolean isReadMessages(String key, Object element, JsonObject props,
         String type);

   public boolean readMessages(String key, Object element, Object value,
         JsonObject props, String type);

   public boolean skipCollision(Object masterObj, String key, Object value,
         JsonObject removeJson, JsonObject updateJson);
}
