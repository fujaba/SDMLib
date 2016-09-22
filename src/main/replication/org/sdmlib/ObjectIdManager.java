package org.sdmlib;

import java.util.HashMap;

public class ObjectIdManager
{

   private final String idPrefix;

   private long idIndex = 1;

   private HashMap<String, Object> idObjectMap = new HashMap<>();
   private HashMap<Object, String> objectIdMap = new HashMap<>();

   private Object root;

   public ObjectIdManager(String idPrefix)
   {
      this.idPrefix = idPrefix;
   }

   public String addObject(String id, Object object)
   {
      idObjectMap.put(id, object);
      objectIdMap.put(object, id);
      return id;
   }

   public String addObject(Object object)
   {
      String id = generateId(object);
      return addObject(id, object);
   }

   public Object getObject(String id)
   {
      return idObjectMap.get(id);
   }

   public boolean remove(Object object)
   {
      String removedId = objectIdMap.remove(object);
      if (removedId != null)
      {
         Object removedObject = idObjectMap.remove(removedId);
         if (removedObject == object)
         {
            return true;
         }
         else
         {
            throw new RuntimeException("Somthing went wrong while removing ");
         }
      }
      else
      {
         Object removedObject = idObjectMap.remove((String) object);
         if (removedObject != null)
         {
            removedId = objectIdMap.remove(removedObject);
            if (removedId == (String) object)
            {
               return true;
            }
            else
            {
               throw new RuntimeException("Somthing went wrong while removing ");
            }
         }
         else
         {
            throw new RuntimeException("Somthing went wrong while removing ");
         }

      }
   }

   private String generateId(Object object)
   {

      return idPrefix + '.' + object.getClass().getSimpleName().charAt(0) + (idIndex++);
   }

   public String getId(Object object)
   {
      String result = objectIdMap.get(object);

      if (result == null)
      {
         return addObject(object);
      }

      return result;
   }

   public void setRootObject(Object root)
   {
      this.root = root;
      addObject(root);
   }

   public Object getRoot()
   {
      return this.root;
   }

}
