/*
   Copyright (c) 2014 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.test.examples.simpleModel.model.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.simpleModel.model.BigBrother;
import org.sdmlib.test.examples.simpleModel.model.Person;

import de.uniks.networkparser.IdMap;

public class BigBrotherCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      BigBrother.PROPERTY_KIDS,
      BigBrother.PROPERTY_NOONE,
      BigBrother.PROPERTY_SUSPECTS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new BigBrother();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (BigBrother.PROPERTY_KIDS.equalsIgnoreCase(attribute))
      {
         return ((BigBrother) target).getKids();
      }

      if (BigBrother.PROPERTY_NOONE.equalsIgnoreCase(attribute))
      {
         return ((BigBrother) target).getNoOne();
      }

      if (BigBrother.PROPERTY_SUSPECTS.equalsIgnoreCase(attribute))
      {
         return ((BigBrother) target).getSuspects();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (BigBrother.PROPERTY_KIDS.equalsIgnoreCase(attrName))
      {
         ((BigBrother) target).withKids((Object) value);
         return true;
      }
      
      if ((BigBrother.PROPERTY_KIDS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((BigBrother) target).withoutKids((Object) value);
         return true;
      }

      if (BigBrother.PROPERTY_NOONE.equalsIgnoreCase(attrName))
      {
         ((BigBrother) target).setNoOne((Person) value);
         return true;
      }

      if (BigBrother.PROPERTY_SUSPECTS.equalsIgnoreCase(attrName))
      {
         ((BigBrother) target).withSuspects((Person) value);
         return true;
      }
      
      if ((BigBrother.PROPERTY_SUSPECTS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((BigBrother) target).withoutSuspects((Person) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.simpleModel.model.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((BigBrother) entity).removeYou();
   }
}
