package org.sdmlib;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class SerializeToChanges
{

   public static final String PLAIN = "plain";

   public static final String TO_ONE = "toOne";

   public static final String TO_MANY = "toMany";

   private ObjectIdManager objectIdManager;

   private int changeno;

   private ModelChangeHandler changeHandler;


   public SerializeToChanges(ObjectIdManager objectIdManager, ModelChangeHandler changeHandler)
   {
      this.objectIdManager = objectIdManager;
      this.changeHandler = changeHandler;
   }


   public SerializeToChanges()
   {
      objectIdManager = new ObjectIdManager("a");
      this.changeHandler = new ModelChangeHandler(objectIdManager);
   }


   public Object clone(Object object)
   {
      List<ChangeEvent> serialize = serialize(object);

      ObjectIdManager cloneIDManager = new ObjectIdManager("a");
      ModelChangeHandler changeHandler = new ModelChangeHandler(cloneIDManager);

      for (ChangeEvent changeEvent : serialize)
      {
         try
         {
            changeHandler.applyChange(changeEvent);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }

      return cloneIDManager.getObject(objectIdManager.getId(object));

   }


   public List<ChangeEvent> serialize(Object game)
   {
      ArrayList<ChangeEvent> changes = new ArrayList<>();

      synchronized (changeHandler.lock)
      {
         try
         {
            changes.addAll(serialize(game, new HashSet<Object>()));
         }
         catch (IllegalArgumentException | IllegalAccessException e)
         {
            e.printStackTrace();
         }
      }
      return changes;
   }


   private List<ChangeEvent> serialize(Object obj, HashSet<Object> touched)
         throws IllegalArgumentException, IllegalAccessException
   {
      touched.add(obj);

      ArrayList<ChangeEvent> changes = new ArrayList<>();

      Field[] fields = obj.getClass().getFields();

      for (Field namefield : fields)
      {

         if (!namefield.getName().startsWith("PROPERTY_"))
         {

            continue;
         }

         String property = (String) namefield.get(obj);
         Field field = getFieldbyName(obj.getClass(), property);
         field.setAccessible(true);
         Object value = field.get(obj);

         if (value == null)
         {
            continue;
         }
         else if (value.getClass().isPrimitive() || value instanceof String || value instanceof Number)
         {
            // is primitive
            ChangeEvent changeEvent = new ChangeEvent();
            changeEvent.setNewValue(value.toString());
            changeEvent.setValueType(value.getClass().getName());
            changeEvent.setObjectId(objectIdManager.getId(obj));
            changeEvent.setObjectType(obj.getClass().getName());
            changeEvent.setProperty(property);
            changeEvent.setSessionId(ModelChangeHandler.SESSION_ID);
            changeEvent.setPropertyKind(PLAIN);
            changeEvent.setChangeNo(String.valueOf(changeHandler.getNewHistoryIdNumber()));
            changes.add(changeEvent);
         }
         else if (value instanceof Collection)
         {
            Collection collection = (Collection) value;
            Iterator iterator = collection.iterator();

            while (iterator.hasNext())
            {
               Object object = (Object) iterator.next();

               if (!touched.contains(object))
               {
                  changes.addAll(serialize(object, touched));
               }
               ChangeEvent changeEvent = new ChangeEvent();
               changeEvent.setNewValue(objectIdManager.getId(object));
               changeEvent.setValueType(object.getClass().getName());
               changeEvent.setObjectId(objectIdManager.getId(obj));
               changeEvent.setObjectType(obj.getClass().getName());
               changeEvent.setProperty(property);
               changeEvent.setSessionId(ModelChangeHandler.SESSION_ID);
               changeEvent.setPropertyKind(TO_MANY);
               changeEvent.setChangeNo(String.valueOf(changeHandler.getNewHistoryIdNumber()));
               changes.add(changeEvent);
            }

         }
         else
         {
            // is an object

            if (!touched.contains(value))
            {
               changes.addAll(serialize(value, touched));
            }

            ChangeEvent changeEvent = new ChangeEvent();
            changeEvent.setNewValue(objectIdManager.getId(value));
            changeEvent.setValueType(value.getClass().getName());
            changeEvent.setObjectId(objectIdManager.getId(obj));
            changeEvent.setObjectType(obj.getClass().getName());
            changeEvent.setProperty(property);
            changeEvent.setSessionId(ModelChangeHandler.SESSION_ID);
            changeEvent.setPropertyKind(TO_ONE);
            changeEvent.setChangeNo(String.valueOf(changeHandler.getNewHistoryIdNumber()));
            changes.add(changeEvent);

         }

      }

      return changes;
   }


   private Field getFieldbyName(Class clazz, String property)
   {
      if (clazz == null)
      {
         return null;
      }

      try
      {
         try
         {
            return clazz.getDeclaredField(property);
         }
         catch (NoSuchFieldException e)
         {
            return getFieldbyName(clazz.getSuperclass(), property);
         }
      }
      catch (SecurityException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return null;
   }

}
