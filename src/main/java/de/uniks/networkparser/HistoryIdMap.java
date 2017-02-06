package de.uniks.networkparser;

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import org.sdmlib.replication.ChangeEvent;

import de.uniks.networkparser.interfaces.Entity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.interfaces.UpdateListener;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;

public class HistoryIdMap extends IdMap
{
   private UpdateListener updateListenerFromUser;
   
   private static class ObjectMap extends LinkedHashMap<String, AttrMap>{} 
   private static class AttrMap extends LinkedHashMap<String, Long>{} 
   
   private ObjectMap history;


   public HistoryIdMap(String owner)
   {
      this.withCounter(new NanoCounter());
      this.withSessionId(owner);
   }
   
   @Override
   public IdMap withListener(UpdateListener updateListener)
   {
      updateListenerFromUser = updateListener;
      
      return super.withListener((UpdateListener) update -> wrapUpdate(update));
   }
   
   
   private boolean wrapUpdate(Object update)
   {
      // create history json changes
      SimpleEvent evt = (SimpleEvent) update;
      JsonArray jsonArray = new JsonArray();
      JsonObject oldJson = (JsonObject) evt.getEntity();
      
      recodeChanges(jsonArray, oldJson, ""+System.nanoTime());
      
      boolean result = updateListenerFromUser.update(jsonArray);
      return result;
   }

   private JsonObject createJsonChange(String id, String opCode, String prop, Object newValue, Object oldValue, String timeStamp)
   {
      JsonObject change = new JsonObject();
      change.put("id", id);
      change.put("opCode", opCode);
      change.put("prop", prop);
      change.put("newValue", newValue);
      change.put("oldValue", oldValue);
      change.put("timeStamp", timeStamp);
      
      // fill history tables
      history.get(id);
      
      return change;
   }


   private void recodeChanges(JsonArray jsonArray, JsonObject oldJson, String timeStamp)
   {
   // {"id":"testerProxy",
      //  "class":"org.sdmlib.replication.SeppelSpaceProxy",
      //  "upd":{"scopes":{"class":"org.sdmlib.replication.SeppelScope",
      //                   "id":"tester.S1",
      //                   "prop":{"scopeName":"commands",
      //                           "spaces":[{"id":"testerProxy"}]}}}}

      if (oldJson == null)
      {
         return;
      }
      String oldText = oldJson.toString(3);
      String opCode = IdMap.UPDATE;
      
      String oldId = (String) oldJson.get(IdMap.ID);
      Object attributes = oldJson.get(IdMap.UPDATE);

      if (attributes == null)
      {
         attributes = oldJson.get(IdMap.REMOVE);
         opCode = IdMap.REMOVE;

         if (attributes == null)
         {
            attributes = oldJson.get("prop");
            opCode = IdMap.UPDATE;
         }
      }

      JsonObject valueJsonObject = null;
      JsonObject attributesJson = null;
      String prop = null;
      Object newValue = null;
      Object oldValue = null;

      if (attributes != null)
      {
         attributesJson = (JsonObject) attributes;

         Iterator<String> iter = attributesJson.keyIterator();
         while ( iter.hasNext())
         {
            prop = iter.next();

            Object attrValue = attributesJson.get(prop);

            if (attrValue instanceof JsonObject)
            {
               JsonArray valueJsonArray = new JsonArray();
               valueJsonArray.add(attrValue);
               attrValue = valueJsonArray;
            }

            if (attrValue instanceof JsonArray)
            {
               JsonArray valueJsonArray = (JsonArray) attrValue;

               for (Object arrayElem : valueJsonArray)
               {
                  valueJsonObject = (JsonObject) arrayElem;

                  String valueObjectId = (String) valueJsonObject.get(IdMap.ID);

                  Object valueObject = this.getObject(valueObjectId);


                  // toOne or toMany
                  String card = ChangeEvent.TO_ONE;

                  Object targetObject = this.getObject(oldId);

                  SendableEntityCreator creator = this.getCreatorClass(targetObject);

                  Object value = creator.getValue(targetObject, prop);

                  if (value != null && value instanceof Collection)
                  {
                     card = ChangeEvent.TO_MANY;
                     if ( ! opCode.equals(IdMap.REMOVE))
                     {
                        opCode = "add";
                     }
                  }

                  // newValue or oldValue?
                  if (opCode.equals(IdMap.REMOVE))
                  {
                     oldValue = valueObjectId;
                  }
                  else
                  {
                     newValue = valueObjectId;
                  }

                  if (card.equals(ChangeEvent.TO_ONE))
                  {
                     opCode = "pointer";
                  }

                  // store it
                  JsonObject createJsonChange = createJsonChange(oldId, opCode, prop, newValue, oldValue, timeStamp);
                  jsonArray.add(createJsonChange);

                  // does the value have properties?
                  if (valueJsonObject.get("prop") != null)
                  {
                     // call recursive
                     recodeChanges(jsonArray, valueJsonObject, timeStamp);
                  }
               }
            }
            else
            {
               JsonObject remJson = (JsonObject) oldJson.get(IdMap.REMOVE);
               if (remJson != null)
               {
                  oldValue = remJson.get(prop);
               }
               JsonObject createJsonChange = createJsonChange(oldId, "plain", prop, attrValue, oldValue, timeStamp);
               jsonArray.add(createJsonChange);

               // Logger.getGlobal().severe("Unhandled case");
            }
         }
      }
      
   }


   private static class NanoCounter extends SimpleIdCounter
   {
      
      private long sessionStartTime;

      public NanoCounter()
      {
         sessionStartTime = System.currentTimeMillis();
      }

      /**
       * Get a new Id
       */
      @Override
      public String getId(Object obj) {
         String key;

         // new object generate key and add to tables
         // <session id>.<first char><running number>
         if (obj == null) {
            return "";
         }
         String className = obj.getClass().getName();
         char firstChar = className.charAt(className.lastIndexOf(".") + 1);
         if (this.prefixId != null) {
            key = this.prefixId 
                  + this.getSplitter() + sessionStartTime 
                  + this.getSplitter() + firstChar + this.number
                  + this.getSplitter() + "1"
                  + ":" + className;
         } else {
            key = "" + firstChar + this.number;
         }
         this.number++;
         return key;
      }



   }
}
