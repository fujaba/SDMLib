package de.uniks.networkparser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.sdmlib.replication.ChangeEvent;

import de.uniks.networkparser.buffer.CharacterBuffer;
import de.uniks.networkparser.interfaces.ObjectCondition;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.json.JsonTokener;
import de.uniks.networkparser.list.SimpleList;
import de.uniks.networkparser.list.SimpleSet;

public class HistoryIdMap extends IdMap
{
   private static final String LIFE = "life";

   public static final String POINTER = "pointer";

   public static final String ADD = "add";

   public static final String PLAIN = "plain";

   private ObjectCondition updateListenerFromUser;

   public long decodeChanges(String value)
   {
      long noOfChangesApplied = 0;
      // read all changes into single json array
      JsonArray result = new JsonArray();
      result.clear();
      JsonTokener tokener = new JsonTokener();
      CharacterBuffer buffer = CharacterBuffer.create(value);
      while (!buffer.isEnd())
      {
         tokener.parseToEntity(result, buffer);
      }

      // apply changes if valid
      for (int i = 0; i < result.size(); i++)
      {
         JsonObject jsonChange = (JsonObject) result.get(i);

         noOfChangesApplied += applyJsonChange(jsonChange);
      }

      // System.out.println(result.toString(3));
      return noOfChangesApplied;
   }

   public static class SimpleAttrMap extends LinkedHashMap<String, AttrTimeStampMap>
   {
      public AttrTimeStampMap getOrCreate(String id)
      {
         AttrTimeStampMap attrMap = this.get(id);
         if (attrMap == null)
         {
            attrMap = new AttrTimeStampMap();
            this.put(id, attrMap);
         }
         return attrMap;
      }
   }

   public static class AttrTimeStampMap extends LinkedHashMap<String, Long>
   {

      public void dumpHistory(StringBuffer buf)
      {
         for (java.util.Map.Entry<String, Long> e : this.entrySet())
         {
            String format = String.format("   %12s %d\n", e.getKey(), e.getValue());
            buf.append(format);
         }

      }
   }

   public class ToManyRefsMap extends LinkedHashMap<String, RefTimeStampsMap>
   {

      public RefTimeStampsMap getOrCreate(String id)
      {
         RefTimeStampsMap refTimeStampsMap = this.get(id);
         if (refTimeStampsMap == null)
         {
            refTimeStampsMap = new RefTimeStampsMap();
            this.put(id, refTimeStampsMap);
         }
         return refTimeStampsMap;
      }

   }

   public class RefTimeStampsMap extends LinkedHashMap<String, TimeStampMap>
   {

      public TimeStampMap getOrCreate(String prop)
      {
         TimeStampMap timeStampMap = this.get(prop);
         if (timeStampMap == null)
         {
            timeStampMap = new TimeStampMap();
            this.put(prop, timeStampMap);
         }
         return timeStampMap;
      }


      public RefTime getById(String id)
      {
         String owner = ownerId(id);
         TimeStampMap timeStampMap = this.get(owner);
         if (timeStampMap == null)
         {
            return null;
         }
         String baseId = baseId(id);
         return timeStampMap.get(baseId);
      }


      public void putById(String id, RefTime refTime)
      {
         String owner = ownerId(id);
         TimeStampMap timeStampMap = removedObjects.getOrCreate(owner);
         String baseId = baseId(id);
         timeStampMap.put(baseId, refTime);
      }


      public void dumpHistory(StringBuffer buf)
      {
         for (java.util.Map.Entry<String, TimeStampMap> e : this.entrySet())
         {
            buf.append(String.format("   %12s\n", e.getKey()));

            TimeStampMap timeStampMap = e.getValue();

            for (java.util.Map.Entry<String, RefTime> t : timeStampMap.entrySet())
            {
               buf.append(String.format("   %12s %s %d\n", " ",
                  smallId(t.getKey()),
                  t.getValue().timeStamp));

            }
            buf.append(String.format("   %12s\n", "rem"));
            for (java.util.Map.Entry<String, RefTime> r : timeStampMap.removedTimeStamps.entrySet())
            {
               String key = r.getKey().substring(r.getKey().lastIndexOf('.') + 1);
               buf.append(String.format("   %12s %s %d\n", " ",
                  key,
                  r.getValue().timeStamp));
            }
         }

      }

   }

   public static class TimeStampMap extends LinkedHashMap<String, RefTime>
   {
      private ArrayList<RefTime> sortedTimeStamps = new ArrayList<>();

      private LinkedHashMap<String, RefTime> removedTimeStamps = null;


      public ArrayList<RefTime> getSortedTimeStamps()
      {
         return sortedTimeStamps;
      }


      public LinkedHashMap<String, RefTime> getRemovedTimeStamps()
      {
         if (removedTimeStamps == null)
         {
            removedTimeStamps = new LinkedHashMap<>();
         }
         return removedTimeStamps;
      }


      public void put(String key, Long value)
      {
         String baseId = baseId(key);
         RefTime removalTime = getRemovedTimeStamps().remove(baseId);
         if (removalTime != null)
         {
            if (removalTime.timeStamp >= value)
            {
               throw new UnsupportedOperationException();
            }
         }

         RefTime refTime = this.get(key);
         if (refTime != null)
         {
            sortedTimeStamps.remove(refTime);
         }
         else
         {
            refTime = new RefTime(key, value);
            this.put(key, refTime);
         }

         // add time stamp to sortedTimeStamps
         Long lastValue = 0L;
         if (sortedTimeStamps.size() > 0)
         {
            lastValue = sortedTimeStamps.get(sortedTimeStamps.size() - 1).timeStamp;
         }
         sortedTimeStamps.add(refTime);
         if (value < lastValue)
         {
            sortedTimeStamps.sort(null);
         }

         return;
      }


      @Override
      public boolean remove(Object key, Object value)
      {
         RefTime oldRefTime = this.remove(key);

         if (oldRefTime == null)
         {
            oldRefTime = new RefTime((String)key, (Long)value);
         }

         sortedTimeStamps.remove(oldRefTime);
         getRemovedTimeStamps().put(baseId((String) key), oldRefTime);

         return true;
      }
   }

   public static class RefTime implements Comparable<RefTime>
   {
      public String objId;

      public Long timeStamp;


      public RefTime(String key, Long value)
      {
         this.objId = key;
         this.timeStamp = value;
      }


      @Override
      public int compareTo(RefTime o)
      {
         int compareTo = timeStamp.compareTo(o.timeStamp);
         if (compareTo == 0)
         {
            compareTo = this.objId.compareTo(o.objId);
         }
         return compareTo;
      }


      @Override
      public String toString()
      {
         return this.objId + "#" + this.timeStamp;
      }
   }

   public SimpleAttrMap simpleAttrChanges = new SimpleAttrMap();

   public ToManyRefsMap toManyChanges = new ToManyRefsMap();

   private LinkedHashMap<String, Long> rebirthCounters = new LinkedHashMap<>();


   public LinkedHashMap<String, Long> getRebirthCounters()
   {
      return rebirthCounters;
   }

   private RefTimeStampsMap removedObjects = new RefTimeStampsMap();


   public RefTimeStampsMap getRemovedObjects()
   {
      return removedObjects;
   }


   public HistoryIdMap(String owner)
   {
      this.withSession(owner);
   }


   @Override
   public IdMap withListener(ObjectCondition updateListener)
   {
      updateListenerFromUser = updateListener;

      return super.withListener((ObjectCondition) update -> wrapUpdate(update));
   }

   private long changeTime = 0;


   public long getChangeTime()
   {
      long newChangeTime = System.nanoTime();
      if (newChangeTime > changeTime)
      {
         changeTime = newChangeTime;
      }
      else
      {
         changeTime++;
      }
      return changeTime;
   }


   private boolean wrapUpdate(Object update)
   {
      // create history json changes
      SimpleEvent evt = (SimpleEvent) update;
      JsonArray jsonArray = new JsonArray();
      JsonObject oldJson = (JsonObject) evt.getEntity();

      recodeChanges(jsonArray, oldJson);

      boolean result = updateListenerFromUser.update(jsonArray);
      return result;
   }


   public Object getOrCreateObject(String id)
   {
      Object obj = this.getObject(id);
      if (obj == null)
      {
         // create a new one
         String className = classNameFromId(id);
         SimpleList<SendableEntityCreator> candidates = new SimpleList<SendableEntityCreator>();
         SendableEntityCreator creator = this.getCreator(className, false, candidates);
         if(creator == null && candidates.size()>0) {
        	 creator = candidates.first();
        	 for(SendableEntityCreator candidate : candidates) {
        		 String name = candidate.getClass().getName();
        		 if(name.startsWith("de.uniks.networkparser.")) {
        			 continue;
        		 }
        		 creator = candidate;
        	 }
         }
         obj = creator.getSendableInstance(false);
         this.put(id, obj);
         // add to re-birth, if necessary
         long lifeCountFromId = lifeCountFromId(id);
         if (lifeCountFromId > 1)
         {
            String baseId = baseId(id);
            rebirthCounters.put(baseId, lifeCountFromId);
         }
      }
      return obj;
   }


   private String classNameFromId(String id)
   {
      int pos = id.indexOf(':');
      String className = id.substring(pos + 1);
      return className;
   }


   private long applyJsonChange(JsonObject jsonChange)
   {
      String id = jsonChange.getString("id");
      String opCode = jsonChange.getString("opCode");
      String prop = jsonChange.getString("prop");
      Object newValue = jsonChange.get("newValue");
      Object oldValue = jsonChange.get("oldValue");
      long newChangeNanos = jsonChange.getLong("timeStamp");
      
      Logger.getGlobal().setLevel(Level.SEVERE);
      Logger.getGlobal().info(dumpHistory());

      // the object might have been removed meanwhile
      if (idIsDead(id))
      {
         return 0;
      }
      // look up old timestamps
      if (opCode.equals(PLAIN) || opCode.equals(POINTER))
      {
         AttrTimeStampMap attrTimeStampMap = simpleAttrChanges.getOrCreate(id);
         Long oldTimeStamp = attrTimeStampMap.get(prop);
         if (oldTimeStamp != null && oldTimeStamp >= newChangeNanos)
         {
            // reject new change
            return 0;
         }

         // its new, apply and record it
         Object object = this.getOrCreateObject(id);
         SendableEntityCreator creator = this.getCreatorClass(object);
         if (opCode.equals(POINTER))
         {
            if (newValue != null)
            {
               newValue = this.getOrCreateObject((String) newValue);
            }
            else
            {
               newValue = null;
            }
         }
         creator.setValue(object, prop, newValue, SendableEntityCreator.UPDATE);
         attrTimeStampMap.put(prop, newChangeNanos);
         Logger.getGlobal().info(dumpHistory() + "");
      }
      else if (opCode.equals(ADD))
      {
         String addId = (String) newValue;
         if (idIsDead(addId))
         {
            return 0;
         }

         // check for removal time stamp
         RefTimeStampsMap refTimeStampsMap = toManyChanges.getOrCreate(id);
         TimeStampMap timeStampMap = refTimeStampsMap.getOrCreate(prop);
         RefTime refTime = timeStampMap.get(addId);
         if (refTime != null && refTime.timeStamp >= newChangeNanos)
         {
            return 0;
         }
         if (timeStampMap.removedTimeStamps != null)
         {
            refTime = timeStampMap.removedTimeStamps.get(addId);
            if (refTime != null && refTime.timeStamp >= newChangeNanos)
            {
               return 0;
            }
         }
         timeStampMap.put((String) newValue, newChangeNanos);

         // apply change
         Object object = this.getOrCreateObject(id);
         SendableEntityCreator creator = this.getCreatorClass(object);
         Object addObject = this.getOrCreateObject(addId);
         creator.setValue(object, prop, addObject, ADD);
         adjustPosition(id, prop, addId, object, addObject, creator, timeStampMap, newChangeNanos);
         Logger.getGlobal().info(dumpHistory() + "");
      }
      else if (opCode.equals(SendableEntityCreator.REMOVE))
      {
         String remId = (String) oldValue;
         
         RefTimeStampsMap refTimeStampsMap = toManyChanges.getOrCreate(id);
         TimeStampMap timeStampMap = refTimeStampsMap.getOrCreate(prop);
         RefTime refTime = timeStampMap.get(remId);
         if (refTime != null && refTime.timeStamp >= newChangeNanos)
         {
            return 0;
         }
         if (timeStampMap.removedTimeStamps != null)
         {
            refTime = timeStampMap.removedTimeStamps.get(remId);
            if (refTime != null && refTime.timeStamp >= newChangeNanos)
            {
               return 0;
            }
         }
         timeStampMap.remove((String) oldValue, newChangeNanos);

         //apply change
         Object object = this.getObject(id);
         SendableEntityCreator creator = this.getCreatorClass(object);
         Object remObject = this.getObject(remId);
         if (remObject == null)
         {
            return 0;
         }
         creator.setValue(object, prop, remObject, SendableEntityCreator.REMOVE);
         Logger.getGlobal().info(dumpHistory() + "");
      }
      else if (opCode.equals(SendableEntityCreator.REMOVE_YOU))
      {
         // store removed id
         // split object number without life counter from id
         String baseId = baseId(id);
         RefTime refTime = new RefTime(id, newChangeNanos);
         String owner = ownerId(id);
         TimeStampMap timeStampMap = removedObjects.getOrCreate(owner);
         timeStampMap.put(baseId, refTime);

         // remove old timestamps
         simpleAttrChanges.remove(id);
         toManyChanges.remove(id);

         // actually remove the object
         Object object = this.getObject(id);
         if (object != null)
         {
//        	 this.removeObj(object, true);
//            SendableEntityCreator creator = this.getCreatorClass(object);
//            creator.removeObject(object);
            SendableEntityCreator creator = this.getCreatorClass(object);
            creator.setValue(object, "this", null, SendableEntityCreator.REMOVE_YOU);
         }

         Logger.getGlobal().info(dumpHistory() + "");
      }
      else
      {
         throw new NotYetImplementedException();
      }

      return 1;
   }


   public String smallId(String id)
   {
      int pos = id.indexOf('.');
      pos = id.indexOf('.', pos + 1);
      int colonPos = id.indexOf(':');
      if(colonPos<0) {
    	  return id;
      }
      String result = id.substring(pos + 1, colonPos);
      return result;
   }


   public JsonArray getCurrentChanges()
   {
      Logger.getGlobal().setLevel(Level.SEVERE);
      Logger.getGlobal().info(dumpHistory());
      JsonArray result = new JsonArray();

      // plain and pointer
      for (Entry<String, AttrTimeStampMap> ac : simpleAttrChanges.entrySet())
      {
         String id = ac.getKey();
         String opCode = null;
         AttrTimeStampMap timeStampMap = ac.getValue();
         for (Entry<String, Long> ts : timeStampMap.entrySet())
         {
            String prop = ts.getKey();
            long nanoTime = ts.getValue();
            Object object = this.getObject(id);
            SendableEntityCreator creator = this.getCreatorClass(object);
            Object newValue = creator.getValue(object, prop);
            if (newValue == null)
            {
               opCode = POINTER;
            }
            else if (this.getCreatorClass(newValue) != null)
            {
               opCode = POINTER;
               newValue = this.getId(newValue);
            }
            else
            {
               opCode = PLAIN;
            }
            JsonObject jsonChange = plainCreateJsonChange(id, opCode, prop, newValue, null, nanoTime);
            result.add(jsonChange);
         }
      }
      
      // add
      for (Entry<String, RefTimeStampsMap> ac : toManyChanges.entrySet())
      {
         String id = ac.getKey();
         RefTimeStampsMap refTimeStampsMap = ac.getValue();
         for ( Entry<String, TimeStampMap> rt : refTimeStampsMap.entrySet())
         {
            String prop = rt.getKey();
            TimeStampMap timeStampMap = rt.getValue();
            for ( Entry<String, RefTime> rc : timeStampMap.entrySet())
            {
               String otherId = rc.getKey();
               RefTime refTime = rc.getValue();
               long timeStamp = refTime.timeStamp;
               JsonObject jsonChange = plainCreateJsonChange(id, ADD, prop, otherId, null, timeStamp);
               result.add(jsonChange);
            }
            
            // remove
            for ( Entry<String, RefTime> rr : timeStampMap.removedTimeStamps.entrySet())
            {
               RefTime refTime = rr.getValue();
               String otherId = refTime.objId;
               long timeStamp = refTime.timeStamp;
               JsonObject jsonChange = plainCreateJsonChange(id, SendableEntityCreator.REMOVE, prop, null, otherId, timeStamp);
               result.add(jsonChange);
            }
         }
      }
      
      // removeYou
      for ( Entry<String, TimeStampMap> rts : removedObjects.entrySet())
      {
         String owner = rts.getKey();
         TimeStampMap timeStampMap = rts.getValue();
         for ( Entry<String, RefTime> ro : timeStampMap.entrySet())
         {
            RefTime refTime = ro.getValue();
            String id = refTime.objId;
            long timeStamp = refTime.timeStamp;
            JsonObject jsonChange = plainCreateJsonChange(id, SendableEntityCreator.REMOVE_YOU, LIFE, -1, 1, timeStamp);
            result.add(jsonChange);
         }
      }

      return result;
   }


   public String dumpHistory()
   {
      StringBuffer buf = new StringBuffer("\n");

      for (Entry<String, AttrTimeStampMap> ac : simpleAttrChanges.entrySet())
      {
         buf.append(smallId(ac.getKey())).append("\n");

         // add attrs
         AttrTimeStampMap attrMap = ac.getValue();
         attrMap.dumpHistory(buf);
      }

      buf.append("\n");

      for (Entry<String, RefTimeStampsMap> rc : toManyChanges.entrySet())
      {
         buf.append(smallId(rc.getKey())).append("\n");

         // add refs
         RefTimeStampsMap refMap = rc.getValue();
         refMap.dumpHistory(buf);
      }

      buf.append("re-birth\n");
      for (Entry<String, Long> bo : rebirthCounters.entrySet())
      {
         String string = String.format("   %12s %d\n", bo.getKey(), bo.getValue());
         buf.append(string);
      }

      buf.append("removeYou\n");
      for (Entry<String, TimeStampMap> ro : removedObjects.entrySet())
      {
         TimeStampMap timeStampMap = ro.getValue();
         for (Entry<String, RefTime> te : timeStampMap.entrySet())
         {
            RefTime refTime = te.getValue();
            String string = String.format("   %12s %d\n", ro.getKey() + "." + smallId(refTime.objId), refTime.timeStamp);
            buf.append(string);
         }
      }
      return buf.toString();
   }


   private void adjustPosition(String id, String prop, String addId, Object object, Object addObject, SendableEntityCreator creator, TimeStampMap timeStampMap, long newChangeNanos)
   {
      // find pos
      int pos = -1;
      for (int i = timeStampMap.sortedTimeStamps.size() - 1; i >= 0; i--)
      {
         RefTime refTime = timeStampMap.sortedTimeStamps.get(i);
         if (refTime.objId.equals(addId) && refTime.timeStamp == newChangeNanos)
         {
            // found the position; check position in SimpleSet
            Object container = creator.getValue(object, prop);
            if(container == null) {
            	return;
            }
            SimpleSet<Object> toManySet = (SimpleSet<Object>) container;
            int indexOf = toManySet.indexOf(addObject);
            if (indexOf != i)
            {
               toManySet.remove(addObject);
               toManySet.add(i, addObject);
            }
            return;
         }
      }

   }


   private boolean idIsDead(String id)
   {
      RefTime refTime = removedObjects.getById(id);
      if (refTime != null)
      {
         return true;
      }
      String baseId = baseId(id);
      Long lifeCount = rebirthCounters.get(baseId);
      if (lifeCount != null)
      {
         long objLifeCount = lifeCountFromId(id);
         if (objLifeCount < lifeCount)
         {
            return true;
         }
      }

      return false;
   }


   private long lifeCountFromId(String id)
   {
      int pos = id.indexOf('#');
      int colonPos = id.indexOf(':', pos);
      if(colonPos<0) {
    	  return 1;
      }
      String lifeNumString = id.substring(pos + 1, colonPos);
      long lifeNum = Long.valueOf(lifeNumString);
      return lifeNum;
   }


   private String ownerId(String id)
   {
      int pos = id.indexOf('.');
      if(pos<0) {
    	  return id;
      }
      String owner = id.substring(0, pos);
      return owner;
   }


   private JsonObject createJsonChange(String id, String opCode, String prop, Object newValue, Object oldValue)
   {
      long nanoTime = getChangeTime();
      JsonObject change = plainCreateJsonChange(id, opCode, prop, newValue, oldValue, nanoTime);

      // fill history tables
      if (opCode.equals(PLAIN) || opCode.equals(POINTER))
      {
         AttrTimeStampMap attrTimeStampMap = simpleAttrChanges.getOrCreate(id);
         attrTimeStampMap.put(prop, nanoTime);
      }
      else if (opCode.equals(ADD))
      {
         RefTimeStampsMap refTimeStampsMap = toManyChanges.getOrCreate(id);
         TimeStampMap timeStampMap = refTimeStampsMap.getOrCreate(prop);
         timeStampMap.put((String) newValue, nanoTime);
      }
      else if (opCode.equals(SendableEntityCreator.REMOVE))
      {
         // remove from toMany timestamps
         // store in removed toMany timestamps
         RefTimeStampsMap refTimeStampsMap = toManyChanges.getOrCreate(id);
         TimeStampMap timeStampMap = refTimeStampsMap.getOrCreate(prop);
         timeStampMap.remove((String) oldValue, nanoTime);
      }
      else if (opCode.equals(SendableEntityCreator.REMOVE_YOU))
      {
         // store removed id
         // split object number without life counter from id
         String baseId = baseId(id);
         RefTime refTime = new RefTime(id, nanoTime);
         TimeStampMap timeStampMap = removedObjects.getOrCreate(this.getSession());
         timeStampMap.put(baseId, refTime);

         // remove old timestamps
         simpleAttrChanges.remove(id);
         toManyChanges.remove(id);
      }
      else
      {
         throw new NotYetImplementedException();
      }

      return change;
   }


   private JsonObject plainCreateJsonChange(String id, String opCode, String prop, Object newValue, Object oldValue, long nanoTime)
   {
      JsonObject change = new JsonObject();
      change.put("id", id);
      change.put("opCode", opCode);
      change.put("prop", prop);
      change.put("newValue", newValue);
      change.put("oldValue", oldValue);
      change.put("timeStamp", nanoTime);
      return change;
   }


   public static String baseId(String id)
   {
      int lifePos = id.indexOf('#');
      if(lifePos<0) {
    	  return id;
      }
      String baseId = id.substring(0, lifePos);
      return baseId;
   }

   public static class NotYetImplementedException extends RuntimeException
   {

   }


   private void recodeChanges(JsonArray jsonArray, JsonObject oldJson)
   {
      // {"id":"testerProxy",
      // "class":"org.sdmlib.replication.SeppelSpaceProxy",
      // "upd":{"scopes":{"class":"org.sdmlib.replication.SeppelScope",
      // "id":"tester.S1",
      // "prop":{"scopeName":"commands",
      // "spaces":[{"id":"testerProxy"}]}}}}

      if (oldJson == null)
      {
         return;
      }
      String oldText = oldJson.toString(3);
      String opCode = SendableEntityCreator.UPDATE;

      String oldId = (String) oldJson.get(IdMap.ID);
      Object attributes = oldJson.get(SendableEntityCreator.UPDATE);

      if (attributes == null)
      {
         attributes = oldJson.get(SendableEntityCreator.REMOVE);
         opCode = SendableEntityCreator.REMOVE;

         if (attributes == null)
         {
            attributes = oldJson.get("prop");
            opCode = SendableEntityCreator.UPDATE;
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

         if (!iter.hasNext() && opCode == SendableEntityCreator.REMOVE)
         {
            // object removal
            JsonObject jsonChange = createJsonChange(oldId, SendableEntityCreator.REMOVE_YOU, LIFE, -1, 1);
            jsonArray.add(jsonChange);
         }
         while (iter.hasNext())
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
                     if (!opCode.equals(SendableEntityCreator.REMOVE))
                     {
                        opCode = ADD;
                     }
                  }

                  // newValue or oldValue?
                  if (opCode.equals(SendableEntityCreator.REMOVE))
                  {
                     oldValue = valueObjectId;
                  }
                  else
                  {
                     newValue = valueObjectId;
                  }

                  if (card.equals(ChangeEvent.TO_ONE))
                  {
                     opCode = POINTER;
                  }

                  // store it
                  JsonObject createJsonChange = createJsonChange(oldId, opCode, prop, newValue, oldValue);
                  jsonArray.add(createJsonChange);

                  // does the value have properties?
                  if (valueJsonObject.get("prop") != null)
                  {
                     // call recursive
                     recodeChanges(jsonArray, valueJsonObject);
                  }
               }
            }
            else
            {
               JsonObject remJson = (JsonObject) oldJson.get(SendableEntityCreator.REMOVE);
               if (remJson != null)
               {
                  oldValue = remJson.get(prop);
               }
               JsonObject createJsonChange = createJsonChange(oldId, PLAIN, prop, attrValue, oldValue);
               jsonArray.add(createJsonChange);

               // Logger.getGlobal().severe("Unhandled case");
            }
         }
      }

   }
   
   private long sessionStartTime = System.currentTimeMillis();
   private long number = 1;
   
   @Override
   public String createId(Object obj, boolean notification) {
      String key;
      
      if (obj == null)
      {
         return "";
      }

      String className = obj.getClass().getName();
      char firstChar = className.charAt(className.lastIndexOf(".") + 1);

      if (this.session != null)
      {
         // try to reuse removed id
         TimeStampMap timeStampMap = getRemovedObjects().get(this.session);
         if (timeStampMap != null && timeStampMap.size() > 0)
         {
            String oldKey = timeStampMap.keySet().iterator().next();
            RefTime refTime = timeStampMap.get(oldKey);
            String fullOldKey = refTime.objId;
            String baseId = baseId(fullOldKey);
            long lifeNum = lifeCountFromId(fullOldKey);
            lifeNum++;
            key = baseId + "#" + lifeNum + ":" + className;
            timeStampMap.remove(oldKey);
            rebirthCounters.put(baseId, lifeNum);
         }
         else
         {
            // generate new first life key
            key = this.session
               + "." + sessionStartTime
               + "." + firstChar + this.number
               + "#1"
               + ":" + className;
         }
      }
      else
      {
         key = "" + firstChar + this.number;
      }
      
      this.number++;
      put(key, obj, notification);
      return key;
   }
}
