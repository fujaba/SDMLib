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

import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.modelsets.DataTypeList;
import org.sdmlib.models.modelsets.StringList;

public class ParameterSet extends LinkedHashSet<Parameter>  implements org.sdmlib.models.modelsets.ModelSet
{
   private static final long serialVersionUID = 1L;



   public StringList getInitialization()
   {
      StringList result = new StringList();
      
      for (Parameter obj : this)
      {
         result.add(obj.getInitialization());
      }
      
      return result;
   }

   public ParameterSet withInitialization(String value)
   {
      for (Parameter obj : this)
      {
         obj.withInitialization(value);
      }
      
      return this;
   }

   public MethodSet getMethod()
   {
      MethodSet result = new MethodSet();
      
      for (Parameter obj : this)
      {
         result.add(obj.getMethod());
      }
      
      return result;
   }
   
   public ParameterSet withMethod(Method value)
   {
      for (Parameter obj : this)
      {
         obj.withClazz(value);
      }
      
      return this;
   }



   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Parameter elem : this)
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


   public ParameterSet with(Parameter value)
   {
      this.add(value);
      return this;
   }
   
   public ParameterSet without(Parameter value)
   {
      this.remove(value);
      return this;
   }
   
   public DataTypeList getType()
   {
      DataTypeList result = new DataTypeList();
      
      for (Parameter obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public ParameterSet withType(DataType value)
   {
      for (Parameter obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Parameter obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public ParameterSet withName(String value)
   {
      for (Parameter obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }



   public AttributePO startModelPattern()
   {
      org.sdmlib.models.classes.util.ModelPattern pattern = new org.sdmlib.models.classes.util.ModelPattern();
      
      AttributePO patternObject = pattern.hasElementAttributePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public ParameterSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         for(Iterator<?> i = ((Collection<?>) value).iterator();i.hasNext();){
            this.add((Parameter) i.next());
         }
      }
      else if (value != null)
      {
         this.add((Parameter) value);
      }
      
      return this;
   }



   public AttributePO hasAttributePO()
   {
      org.sdmlib.models.classes.util.ModelPattern pattern = new org.sdmlib.models.classes.util.ModelPattern();
      
      AttributePO patternObject = pattern.hasElementAttributePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}
