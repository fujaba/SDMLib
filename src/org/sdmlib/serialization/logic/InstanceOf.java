package org.sdmlib.serialization.logic;

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
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreatorByte;

public class InstanceOf extends ConditionMap implements SendableEntityCreator
{
   public static final String CLAZZNAME = "clazzname";
   public static final String VALUE = "value";

   protected SendableEntityCreatorByte value;
   private String clazzName;

   public InstanceOf withCreator(SendableEntityCreatorByte creator)
   {
      this.value = creator;
      return this;
   }

   public SendableEntityCreatorByte getValue()
   {
      return value;
   }

   public String getClazzName()
   {
      return clazzName;
   }

   public InstanceOf withClass(Class<?> className)
   {
      this.clazzName = className.getName();
      return this;
   }

   public InstanceOf withClass(String className)
   {
      this.clazzName = className;
      return this;
   }

   @Override
   public boolean matches(ValuesMap values)
   {
      return values.entity.getClass().getName().equals(clazzName);
   }

   @Override
   public String[] getProperties()
   {
      return new String[]
      { CLAZZNAME, VALUE };
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new InstanceOf();
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      if (CLAZZNAME.equalsIgnoreCase(attribute))
      {
         return ((InstanceOf) entity).getClazzName();
      }
      if (VALUE.equalsIgnoreCase(attribute))
      {
         return ((InstanceOf) entity).getValue();
      }
      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value,
         String type)
   {
      if (CLAZZNAME.equalsIgnoreCase(attribute))
      {
         ((InstanceOf) entity).withClass(String.valueOf(value));
         return true;
      }
      if (VALUE.equalsIgnoreCase(attribute))
      {
         ((InstanceOf) entity).withCreator((SendableEntityCreatorByte) value);
         return true;
      }
      return false;
   }
}
