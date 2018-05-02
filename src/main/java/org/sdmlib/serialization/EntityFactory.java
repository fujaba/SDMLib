package org.sdmlib.serialization;

import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class EntityFactory implements SendableEntityCreator
{
   /**
    * Calls entity.removeYou().
    *
    * @param entity
    *           the entity to be deleted
    */
   public void removeObject(Object entity)
   {

   }
   
   protected String[] assocs = null;
   
   public String getOtherRole(String myRole)
   {
      if (assocs == null)
      {
         return null;
      }
      
      for (String a : assocs)
      {
         if (a.startsWith(myRole))
         {
            String[] split = a.split(" ");
            
            if (split.length >= 2)
            {
               return split[1];
            }
            else
            {
               return null;
            }
         }
      }
      return null;
   }

   public Object call(Object entity, String method, Object... args)
   {
      return null;
   }

   @Override
   public String[] getProperties()
   {
      return new String[]
      {};
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return null;
   }

   public Object create()
   {
      return getSendableInstance(false);
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value,
         String type)
   {
      return false;
   }
}
