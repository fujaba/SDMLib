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

public class Not implements Condition, SendableEntityCreator
{
   public final String ITEM = "item";
   private Condition item;

   @Override
   public boolean matches(ValuesSimple values)
   {
      return !item.matches(values);
   }

   public Condition getItem()
   {
      return item;
   }

   public Not withItem(Condition item)
   {
      this.item = item;
      return this;
   }

   @Override
   public String[] getProperties()
   {
      return new String[]
      { ITEM };
   }

   @Override
   public Object getSendableInstance(boolean prototyp)
   {
      return new Not();
   }

   @Override
   public Object getValue(Object entity, String attribute)
   {
      if (ITEM.equalsIgnoreCase(attribute))
      {
         ((Not) entity).getItem();
      }
      return null;
   }

   @Override
   public boolean setValue(Object entity, String attribute, Object value,
         String type)
   {
      if (ITEM.equalsIgnoreCase(attribute))
      {
         ((Not) entity).withItem((Condition) value);
      }
      return false;
   }
}
