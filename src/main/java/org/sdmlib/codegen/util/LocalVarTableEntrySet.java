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

import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

import de.uniks.networkparser.list.SimpleSet;

public class LocalVarTableEntrySet extends SimpleSet<LocalVarTableEntry>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (LocalVarTableEntry obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public LocalVarTableEntrySet withName(String value)
   {
      for (LocalVarTableEntry obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public StringList getType()
   {
      StringList result = new StringList();
      
      for (LocalVarTableEntry obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public LocalVarTableEntrySet withType(String value)
   {
      for (LocalVarTableEntry obj : this)
      {
         obj.withType(value);
      }
      
      return this;
   }

   public intList getStartPos()
   {
      intList result = new intList();
      
      for (LocalVarTableEntry obj : this)
      {
         result.add(obj.getStartPos());
      }
      
      return result;
   }

   public LocalVarTableEntrySet withStartPos(int value)
   {
      for (LocalVarTableEntry obj : this)
      {
         obj.withStartPos(value);
      }
      
      return this;
   }

   public intList getEndPos()
   {
      intList result = new intList();
      
      for (LocalVarTableEntry obj : this)
      {
         result.add(obj.getEndPos());
      }
      
      return result;
   }

   public LocalVarTableEntrySet withEndPos(int value)
   {
      for (LocalVarTableEntry obj : this)
      {
         obj.withEndPos(value);
      }
      
      return this;
   }

   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (LocalVarTableEntry elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.codegen.LocalVarTableEntry";
   }



   public LocalVarTableEntryPO startModelPattern()
   {
      return new LocalVarTableEntryPO(this.toArray(new LocalVarTableEntry[this.size()]));
   }


   public LocalVarTableEntrySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         for(Iterator<?> i = ((Collection<?>)value).iterator();i.hasNext();){
            this.add((LocalVarTableEntry) i.next());
         }
      }
      else if (value != null)
      {
         this.add((LocalVarTableEntry) value);
      }
      
      return this;
   }
   
   public LocalVarTableEntrySet without(LocalVarTableEntry value)
   {
      this.remove(value);
      return this;
   }



   public LocalVarTableEntryPO hasLocalVarTableEntryPO()
   {
      return new LocalVarTableEntryPO(this.toArray(new LocalVarTableEntry[this.size()]));

   }

   public static final LocalVarTableEntrySet EMPTY_SET = new LocalVarTableEntrySet().withFlag(LocalVarTableEntrySet.READONLY);
   public LocalVarTableEntrySet hasName(String value)
   {
      LocalVarTableEntrySet result = new LocalVarTableEntrySet();
      
      for (LocalVarTableEntry obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LocalVarTableEntrySet hasName(String lower, String upper)
   {
      LocalVarTableEntrySet result = new LocalVarTableEntrySet();
      
      for (LocalVarTableEntry obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LocalVarTableEntrySet hasType(String value)
   {
      LocalVarTableEntrySet result = new LocalVarTableEntrySet();
      
      for (LocalVarTableEntry obj : this)
      {
         if (value.equals(obj.getType()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LocalVarTableEntrySet hasType(String lower, String upper)
   {
      LocalVarTableEntrySet result = new LocalVarTableEntrySet();
      
      for (LocalVarTableEntry obj : this)
      {
         if (lower.compareTo(obj.getType()) <= 0 && obj.getType().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LocalVarTableEntrySet hasStartPos(int value)
   {
      LocalVarTableEntrySet result = new LocalVarTableEntrySet();
      
      for (LocalVarTableEntry obj : this)
      {
         if (value == obj.getStartPos())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LocalVarTableEntrySet hasStartPos(int lower, int upper)
   {
      LocalVarTableEntrySet result = new LocalVarTableEntrySet();
      
      for (LocalVarTableEntry obj : this)
      {
         if (lower <= obj.getStartPos() && obj.getStartPos() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LocalVarTableEntrySet hasEndPos(int value)
   {
      LocalVarTableEntrySet result = new LocalVarTableEntrySet();
      
      for (LocalVarTableEntry obj : this)
      {
         if (value == obj.getEndPos())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LocalVarTableEntrySet hasEndPos(int lower, int upper)
   {
      LocalVarTableEntrySet result = new LocalVarTableEntrySet();
      
      for (LocalVarTableEntry obj : this)
      {
         if (lower <= obj.getEndPos() && obj.getEndPos() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
