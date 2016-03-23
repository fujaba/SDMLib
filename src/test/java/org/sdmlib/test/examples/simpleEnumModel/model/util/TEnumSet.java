/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.test.examples.simpleEnumModel.model.util;

import java.util.Collection;

import org.sdmlib.test.examples.simpleEnumModel.model.TEnum;

import de.uniks.networkparser.list.SimpleSet;

public class TEnumSet extends SimpleSet<TEnum>
{

   public static final TEnumSet EMPTY_SET = new TEnumSet().withFlag(TEnumSet.READONLY);


   public TEnumPO hasTEnumPO()
   {
      return new TEnumPO(this.toArray(new TEnum[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.simpleEnumModel.model.TEnum";
   }


   @SuppressWarnings("unchecked")
   public TEnumSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TEnum>)value);
      }
      else if (value != null)
      {
         this.add((TEnum) value);
      }
      
      return this;
   }
   
   public TEnumSet without(TEnum value)
   {
      this.remove(value);
      return this;
   }

}
