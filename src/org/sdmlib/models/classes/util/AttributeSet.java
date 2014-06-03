/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.models.classes.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.modelsets.DataTypeList;
import org.sdmlib.models.modelsets.StringList;

public class AttributeSet extends LinkedHashSet<Attribute>  implements org.sdmlib.models.modelsets.ModelSet
{
   private static final long serialVersionUID = 1L;

   public StringList getInitialization()
   {
      StringList result = new StringList();
      
      for (Attribute obj : this)
      {
         result.add(obj.getInitialization());
      }
      
      return result;
   }

   public AttributeSet withInitialization(String value)
   {
      for (Attribute obj : this)
      {
         obj.withInitialization(value);
      }
      
      return this;
   }

   public ClazzSet getClazz()
   {
      ClazzSet result = new ClazzSet();
      
      for (Attribute obj : this)
      {
         result.add(obj.getClazz());
      }
      
      return result;
   }
   
   public AttributeSet withClazz(Clazz value)
   {
      for (Attribute obj : this)
      {
         obj.withClazz(value);
      }
      
      return this;
   }



   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Attribute elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Attribute";
   }


   public AttributeSet with(Attribute value)
   {
      this.add(value);
      return this;
   }
   
   public AttributeSet without(Attribute value)
   {
      this.remove(value);
      return this;
   }
   
   public DataTypeList getType()
   {
      DataTypeList result = new DataTypeList();
      
      for (Attribute obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public AttributeSet withType(DataType value)
   {
      for (Attribute obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Attribute obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public AttributeSet withName(String value)
   {
      for (Attribute obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }



   public AttributePO startModelPattern()
   {
      return new AttributePO(this.toArray(new Attribute[this.size()]));
   }


   public AttributeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         for(Iterator<?> i = ((Collection<?>)value).iterator();i.hasNext();){
            this.add((Attribute) i.next());
         }
      }
      else if (value != null)
      {
         this.add((Attribute) value);
      }
      
      return this;
   }

   public AttributePO hasAttributePO()
   {
      return new AttributePO(this.toArray(new Attribute[this.size()]));
   }
}
