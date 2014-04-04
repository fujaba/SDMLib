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

public class BooleanCondition implements Condition, SendableEntityCreator
{
   public static final String VALUE = "value";
   private boolean value;

   @Override
   public boolean matches(ValuesSimple values)
   {
      return this.value;
   }

   public BooleanCondition withValue(boolean value)
   {
      this.value = value;
      return this;
   }

   public boolean getValue()
   {
      return value;
   }

   public static BooleanCondition value(boolean value)
   {
      return new BooleanCondition().withValue(value);
   }

   @Override
   public String[] getProperties()
   {
      return new String[]
      { VALUE };
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new BooleanCondition();
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      if (VALUE.equalsIgnoreCase(attribute))
      {
         return ((BooleanCondition) entity).getValue();
      }
      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value,
         String type)
   {
      if (VALUE.equalsIgnoreCase(attribute))
      {
         ((BooleanCondition) entity).withValue((Boolean) value);
         return true;
      }
      return false;
   }
}
