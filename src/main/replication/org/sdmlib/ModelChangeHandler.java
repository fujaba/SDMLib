package org.sdmlib;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ModelChangeHandler implements PropertyChangeListener
{

   public static final String PLAIN = "plain";

   public static final String TO_ONE = "toOne";

   public static final String TO_MANY = "toMany";

   public static final String SESSION_ID = "server";

   private ObjectIdManager objectIdManager;

   private long lastChangeId = Long.MIN_VALUE;

   private int historyPos;

   private static HashSet<String> filter = new HashSet<>();

   private Gson gson = new GsonBuilder().serializeNulls().create();

   public final static Object lock = new Object();


   public ModelChangeHandler(ObjectIdManager objectIdManager)
   {
      this.setIdMap(objectIdManager);

   }


   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {

      // {"id":"testerProxy",
      // "class":"org.sdmlib.replication.SeppelSpaceProxy",
      // "upd":{"scopes":{"class":"org.sdmlib.replication.SeppelScope",
      // "id":"tester.S1",
      // "prop":{"scopeName":"commands",
      // "spaces":[{"id":"testerProxy"}]}}}}

      if (evt.getPropertyName().equals("REMOVE_YOU"))
      {
         return;
      }

      Object source = evt.getSource();
      Object newValue = evt.getNewValue();
      Object oldValue = evt.getOldValue();

      synchronized (lock)
      {

         // is primitive
         ChangeEvent changeEvent = new ChangeEvent();
         changeEvent.setObjectId(objectIdManager.getId(source));
         changeEvent.setObjectType(source.getClass().getName());
         changeEvent.setProperty(evt.getPropertyName());
         changeEvent.setSessionId(ModelChangeHandler.SESSION_ID);
         changeEvent.setChangeNo(String.valueOf(getNewHistoryIdNumber()));

         Field field = getFieldbyName(source.getClass(), evt.getPropertyName());

         if (field.getType().isPrimitive() || field.getType().equals(String.class))
         {

            changeEvent.setPropertyKind(PLAIN);

            if (newValue != null)
            {
               changeEvent.setNewValue(newValue.toString());
            }
            else
            {
               changeEvent.setNewValue(null);
            }

            if (oldValue != null)
            {
               changeEvent.setOldValue(oldValue.toString());
            }
            else
            {
               changeEvent.setOldValue(null);

            }
         }
         else
         {

            if (field.getType().isAssignableFrom(ArrayList.class))
            {
               changeEvent.setPropertyKind(TO_MANY);
            }
            else
            {
               changeEvent.setPropertyKind(TO_ONE);
            }

            if (oldValue != null)
            {
               changeEvent.setOldValue(objectIdManager.getId(oldValue));
               changeEvent.setValueType(oldValue.getClass().getName());
            }
            else
            {
               changeEvent.setOldValue(null);

            }

            if (newValue != null)
            {
               changeEvent.setNewValue(objectIdManager.getId(newValue));
               changeEvent.setValueType(newValue.getClass().getName());
            }
            else
            {
               changeEvent.setNewValue(null);
            }

         }

         getPropertyChangeSupport().firePropertyChange("", null, changeEvent);
      }
   }


   public long getNewHistoryIdNumber()
   {
      long result = System.currentTimeMillis();

      if (result <= lastChangeId)
      {
         result = lastChangeId + 1;
      }

      lastChangeId = result;

      return result;
   }


   // public ChangeEventList getHistory()
   // {
   // return history;
   // }
   //
   // public void setHistory(ChangeEventList history)
   // {
   // this.history = history;
   // }

   public void handleChange(String line)
   {

      JsonParser jsonParser = new JsonParser();
      JsonObject jsonObject = jsonParser.parse(line).getAsJsonObject();

      if (jsonObject.has("timestamp"))
      {
         String timeString = jsonObject.get("timestamp").getAsString();

         Long valueOf = Long.valueOf(timeString);
         timediff = System.currentTimeMillis() - valueOf;

      }
      synchronized (lock)
      {
         // line is a json string describing a change

         try
         {
            ChangeEvent change = gson.fromJson(jsonObject, ChangeEvent.class);

            if (change.getNewValue() != null && change.getNewValue().equals("null"))
            {
               change.setNewValue(null);
            }
            if (change.getOldValue() != null && change.getOldValue().equals("null"))
            {
               change.setOldValue(null);
            }

            // try to apply change
            applyChange(change);
            Iterator<ChangeEvent> iterator = deleteLater.iterator();
            while (iterator.hasNext())
            {
               ChangeEvent next = iterator.next();
               if (objectIdManager.getObject(next.getOldValue()) != null)
               {
                  // we can now apply the delete change event correctly
                  applyChange(next);
                  iterator.remove();
               }
            }

         }
         catch (Exception e)
         {
            System.err.println(line);
            e.printStackTrace();
         }
         finally
         {
         }
      }
   }


   private String firstToUpper(String string)
   {
      return string.substring(0, 1).toUpperCase() + string.substring(1);
   }


   public void applyChange(ChangeEvent change) throws Exception
   {
      String changeNo = change.getChangeNo();
      String newValueID = change.getNewValue();
      Object newValue = getObjectIdManager().getObject(newValueID);
      String valueType = change.getValueType();
      Class<?> valueClass;
      String objectId = change.getObjectId();
      Object object = getObjectIdManager().getObject(objectId);
      String objectType = change.getObjectType();
      Class<?> objectClass = Class.forName(objectType);
      String oldValueID = change.getOldValue();
      Object oldValue = getObjectIdManager().getObject(oldValueID);
      String property = change.getProperty();
      String propertyKind = change.getPropertyKind();
      Method methodToInvoke = null;
      Object methodParameter = null;

      try
      {
         if (object == null)
         {

            {
               // new object, create it
               System.out.println("Creating object of type " + objectClass.getSimpleName() + ": " + change.toString());
               object = objectClass.newInstance();
            }

            getObjectIdManager().addObject(objectId, object);
         }

         // check property kind
         switch (propertyKind)
         {
         case PLAIN:
            // simple attribute just do assignment

            methodToInvoke = getMethodToInvoke("set", objectClass, property);

            Class<?> paramType = getParamType(methodToInvoke);

            Object newInstance = paramType.getConstructor(String.class).newInstance(newValueID);

            methodParameter = newInstance;

            break;

         case TO_ONE:
            valueClass = Class.forName(valueType);

            methodToInvoke = getMethodToInvoke("set", objectClass, property);

            if (newValueID == null)
            {
               // deletion
               // System.out.println("Set " + valueClass.getSimpleName() + " to
               // null on object " + object + ": " + change.toString());
               methodParameter = newValueID;
            }
            else
            {
               // insertion
               if (newValue == null)
               {
                  // not yet known target, build it.
                  // System.out.println("Creating value of type " +
                  // valueClass.getSimpleName() + ": " + change.toString());
                  newValue = valueClass.newInstance();
                  getObjectIdManager().addObject(newValueID, newValue);
               }

               methodParameter = newValue;
            }
            break;

         case TO_MANY:
            valueClass = Class.forName(valueType);

            if (newValueID == null)
            {
               // deletion
               // System.out.println("Remove " + oldValue + " from " + object +
               // ": " + change.toString());

               methodToInvoke = getMethodToInvoke("without", objectClass, property);

               if (oldValue != null)
               {
                  // without method needs array
                  Object array = Array.newInstance(valueClass, 1);
                  Array.set(array, 0, oldValue);
                  methodParameter = array;
               }
               else
               {
                  // haven't received the object we need to delete yet, so we
                  // need to store for later deletion
                  deleteLater.add(change);
                  return;
               }
            }
            else
            {
               // insertion
               methodToInvoke = getMethodToInvoke("with", objectClass, property);

               if (newValue == null)
               {
                  // not yet known target, build it.
                  // System.out.println("Creating value of type " +
                  // valueClass.getSimpleName() + ": " + change.toString());
                  newValue = valueClass.newInstance();
                  getObjectIdManager().addObject(newValueID, newValue);
               }

               // with method needs array
               Object array = Array.newInstance(valueClass, 1);
               Array.set(array, 0, newValue);
               methodParameter = array;
            }
            break;

         default:
            throw new RuntimeException("Can not apply change " + change);
         }

         // invoke selected method with corresponding parameter
         methodToInvoke.invoke(object, methodParameter);

      }
      catch (InvocationTargetException e)
      {
         // System.out.println("Invoking " + methodToInvoke + " on object " +
         // object + " with paramater " + methodParameter + " failed.");
         throw e;
      }
      catch (Exception e)
      {
         throw e;
      }
   }


   private Method getMethodToInvoke(String modifier, Class<?> objectClass, String property)
   {
      Method[] methods = objectClass.getMethods();

      for (Method method : methods)
      {
         if (method.getName().equals(modifier + firstToUpper(property)))
         {
            return method;
         }
      }
      return null;
   }


   private Class<?> getParamType(Method method)
   {
      Class<?> paramType = method.getParameterTypes()[0];

      switch (paramType.getSimpleName())
      {
      case "double":
         return Double.class;
      case "int":
         return Integer.class;
      case "float":
         return Float.class;
      case "long":
         return Long.class;
      case "boolean":
         return Boolean.class;

      default:
         return paramType;
      }
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

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   private long timediff;

   private ArrayList<ChangeEvent> deleteLater = new ArrayList<>();


   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }


   public ObjectIdManager getObjectIdManager()
   {
      return objectIdManager;
   }


   public void setIdMap(ObjectIdManager idMap)
   {
      this.objectIdManager = idMap;
   }


   public static void addToFilter(Class clazz, String property)
   {
      filter.add(clazz.getName() + ":" + property);

   }


   public boolean isInFilter(ChangeEvent changeEvent)
   {
      boolean contains = filter.contains(changeEvent.getObjectType() + ":" + changeEvent.getProperty());
      // if (contains)
      // {
      // System.out.println("is in filter: " + changeEvent.toJson().toString());
      // }
      // else
      // {
      // System.out.println("not in filter: " +
      // changeEvent.toJson().toString());
      // }
      return contains;
   }


   public long getTimediff()
   {
      return timediff;
   }


   public void setTimediff(long timediff)
   {
      this.timediff = timediff;
   }

}
