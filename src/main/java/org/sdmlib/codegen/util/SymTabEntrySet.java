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
   
package org.sdmlib.codegen.util;

import java.util.Collection;
import java.util.Iterator;

import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

public class SymTabEntrySet extends SDMSet<SymTabEntry>
{
   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }

   public SymTabEntrySet withKind(String value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withKind(value);
      }
      
      return this;
   }

   public StringList getMemberName()
   {
      StringList result = new StringList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getMemberName());
      }
      
      return result;
   }

   public SymTabEntrySet withMemberName(String value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withMemberName(value);
      }
      
      return this;
   }

   public StringList getType()
   {
      StringList result = new StringList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public SymTabEntrySet withType(String value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withType(value);
      }
      
      return this;
   }

   public intList getStartPos()
   {
      intList result = new intList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getStartPos());
      }
      
      return result;
   }

   public SymTabEntrySet withStartPos(int value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withStartPos(value);
      }
      
      return this;
   }

   public intList getBodyStartPos()
   {
      intList result = new intList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getBodyStartPos());
      }
      
      return result;
   }

   public SymTabEntrySet withBodyStartPos(int value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withBodyStartPos(value);
      }
      
      return this;
   }

   public intList getEndPos()
   {
      intList result = new intList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getEndPos());
      }
      
      return result;
   }

   public SymTabEntrySet withEndPos(int value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withEndPos(value);
      }
      
      return this;
   }

   public StringList getModifiers()
   {
      StringList result = new StringList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getModifiers());
      }
      
      return result;
   }

   public SymTabEntrySet withModifiers(String value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withModifiers(value);
      }
      
      return this;
   }

   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (SymTabEntry elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.codegen.SymTabEntry";
   }


   public SymTabEntrySet with(SymTabEntry value)
   {
      this.add(value);
      return this;
   }
   
   public SymTabEntrySet without(SymTabEntry value)
   {
      this.remove(value);
      return this;
   }


   public SymTabEntryPO startModelPattern()
   {
      return new SymTabEntryPO(this.toArray(new SymTabEntry[this.size()]));
   }


   public SymTabEntrySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         for(Iterator<?> i = ((Collection<?>)value).iterator();i.hasNext();){
            this.add((SymTabEntry) i.next());
         }
      }
      else if (value != null)
      {
         this.add((SymTabEntry) value);
      }
      
      return this;
   }
   
   public SymTabEntryPO hasSymTabEntryPO()
   {
      return new SymTabEntryPO(this.toArray(new SymTabEntry[this.size()]));

   }

   public static final SymTabEntrySet EMPTY_SET = new SymTabEntrySet().withReadOnly(true);
   public StringList getAnnotations()
   {
      StringList result = new StringList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getAnnotations());
      }
      
      return result;
   }

   public SymTabEntrySet hasAnnotations(String value)
   {
      SymTabEntrySet result = new SymTabEntrySet();
      
      for (SymTabEntry obj : this)
      {
         if (value.equals(obj.getAnnotations()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SymTabEntrySet hasAnnotations(String lower, String upper)
   {
      SymTabEntrySet result = new SymTabEntrySet();
      
      for (SymTabEntry obj : this)
      {
         if (lower.compareTo(obj.getAnnotations()) <= 0 && obj.getAnnotations().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SymTabEntrySet withAnnotations(String value)
   {
      for (SymTabEntry obj : this)
      {
         obj.setAnnotations(value);
      }
      
      return this;
   }

}
