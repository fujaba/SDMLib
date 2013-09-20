package org.sdmlib.storyboards;

import java.lang.reflect.Method;
import java.util.LinkedHashSet;

import org.sdmlib.models.patterns.example.ferrmansproblem.creators.ModelPattern;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.utils.StrUtil;

public class GenericCreator extends EntityFactory
{
   private String className = "";
   
   public String getClassName()
   {
      return className;
   }
   
   public void setClassName(String className)
   {
      this.className = className;
   }
   
   public GenericCreator withClassName(String className)
   {
      setClassName(className);
      return this;
   }
   
   @Override
   public void removeObject(Object entity)
   {
      // TODO Auto-generated method stub
      super.removeObject(entity);
   }

   @Override
   public Object call(Object entity, String method, Object... args)
   {
      // TODO Auto-generated method stub
      return super.call(entity, method, args);
   }
   
   private String[] properties = null;

   @Override
   public String[] getProperties()
   {
      if (properties != null)
      {
         return properties;
      }
      
      try
      {
         Class<?> clazz = Class.forName(className);
         
         Method[] methods = clazz.getMethods();
         
         LinkedHashSet<String> fieldNames = new LinkedHashSet<String>();
         for (Method method : methods)
         {
            String methodName = method.getName();
            
            if (methodName.startsWith("get") 
                  && ! methodName.equals("getClass")
                  && ! methodName.equals("getPropertyChangeSupport"))
            {
               methodName = methodName.substring(3);

               methodName = StrUtil.downFirstChar(methodName);
               
               if ( ! "".equals(methodName.trim()))
               {
                  fieldNames.add(methodName);
               }
            }
            
         }
         
         properties = fieldNames.toArray(new String[]{});
         
         return properties;
      }
      catch (ClassNotFoundException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      // TODO Auto-generated method stub
      return super.getProperties();
   }

   private Object protoType = null;
   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      if (protoType == null)
      {
         try
         {
            Class<?> clazz = Class.forName(className);
            protoType = clazz.newInstance();
         }
         catch (Exception e)
         {
            // TODO Auto-generated catch block
            // e.printStackTrace();
         }
      }
      
      return protoType;
   }


   @Override
   public Object getValue(Object entity, String attribute)
   {
      if (entity == null)
      {
         return null;
      }
      try
      {
         Class<?> clazz = Class.forName(className);
         
         Method method = clazz.getMethod("get" + StrUtil.upFirstChar(attribute));
         
         Object invoke = method.invoke(entity);
         
         return invoke;
      }
      catch (Exception e)
      {
         // TODO Auto-generated catch block
         // e.printStackTrace();
      }
      
      return super.getValue(entity, attribute);
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value,
         String type)
   {
      // TODO Auto-generated method stub
      return super.setValue(entity, attribute, value, type);
   }
   
}
