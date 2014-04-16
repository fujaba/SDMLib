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
   
package org.sdmlib.models.classes.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.modelsets.StringList;

public class MethodSet extends LinkedHashSet<Method> implements org.sdmlib.models.modelsets.ModelSet
{
   public StringList getSignature()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getSignature());
      }
      
      return result;
   }

   public MethodSet withSignature(String value)
   {
      for (Method obj : this)
      {
         obj.withSignature(value);
      }
      
      return this;
   }

   public StringList getReturnType()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getReturnType());
      }
      
      return result;
   }

   public MethodSet withReturnType(String value)
   {
      for (Method obj : this)
      {
         obj.withReturnType(value);
      }
      
      return this;
   }

   public ClazzSet getClazz()
   {
      ClazzSet result = new ClazzSet();
      
      for (Method obj : this)
      {
         result.add(obj.getClazz());
      }
      
      return result;
   }
   public MethodSet withClazz(Clazz value)
   {
      for (Method obj : this)
      {
         obj.withClazz(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Method elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Method";
   }


   public StringList getBody()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getBody());
      }
      
      return result;
   }

   public MethodSet withBody(String value)
   {
      for (Method obj : this)
      {
         obj.setBody(value);
      }
      
      return this;
   }



   public MethodPO startModelPattern()
   {
      org.sdmlib.models.classes.creators.ModelPattern pattern = new org.sdmlib.models.classes.creators.ModelPattern();
      
      MethodPO patternObject = pattern.hasElementMethodPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public MethodSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Method>)value);
      }
      else if (value != null)
      {
         this.add((Method) value);
      }
      
      return this;
   }
   
   public MethodSet without(Method value)
   {
      this.remove(value);
      return this;
   }



   public MethodPO hasMethodPO()
   {
      org.sdmlib.models.classes.creators.ModelPattern pattern = new org.sdmlib.models.classes.creators.ModelPattern();
      
      MethodPO patternObject = pattern.hasElementMethodPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}










