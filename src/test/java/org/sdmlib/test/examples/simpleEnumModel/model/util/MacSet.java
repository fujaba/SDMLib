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
   
package org.sdmlib.test.examples.simpleEnumModel.model.util;

import java.util.Collection;

import de.uniks.networkparser.list.StringList;
import org.sdmlib.test.examples.simpleEnumModel.model.Alex;
import org.sdmlib.test.examples.simpleEnumModel.model.Mac;
import org.sdmlib.test.examples.simpleEnumModel.model.TEnum;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.ObjectSet;

public class MacSet extends SimpleSet<Mac>
{
   public MacPO hasMacPO()
   {
      return new MacPO(this.toArray(new Mac[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public MacSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Mac>)value);
      }
      else if (value != null)
      {
         this.add((Mac) value);
      }
      
      return this;
   }
   
   public MacSet without(Mac value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Mac obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public MacSet hasName(String value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MacSet hasName(String lower, String upper)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MacSet withName(String value)
   {
      for (Mac obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   public static final MacSet EMPTY_SET = new MacSet().withFlag(MacSet.READONLY);

   /**
    * Loop through the current set of Mac objects and collect a list of the type attribute values. 
    * 
    * @return List of org.sdmlib.test.examples.simpleEnumModel.model.TEnum objects reachable via type attribute
    */
   public TEnumSet getType()
   {
      TEnumSet result = new TEnumSet();
      
      for (Mac obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet hasType(TEnum value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and assign value to the type attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Mac objects now with new attribute values.
    */
   public MacSet withType(TEnum value)
   {
      for (Mac obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Mac objects and collect a list of the owner attribute values. 
    * 
    * @return List of Alex objects reachable via owner attribute
    */
   public AlexSet getOwner()
   {
      AlexSet result = new AlexSet();
      
      for (Mac obj : this)
      {
         result.add(obj.getOwner());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the owner attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet hasOwner(Alex value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value == obj.getOwner())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and assign value to the owner attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Mac objects now with new attribute values.
    */
   public MacSet withOwner(Alex value)
   {
      for (Mac obj : this)
      {
         obj.setOwner(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public StringList concat(int p1)
   {
      StringList result = new StringList();
      for (Mac obj : this)
      {
         result.add(obj.concat(p1));
      }
      return result;
   }

   
   //==========================================================================
   
   public TEnumSet select(int p1)
   {
      TEnumSet result = new TEnumSet();
      for (Mac obj : this)
      {
         result.add(obj.select(p1));
      }
      return result;
   }



   public MacPO filterMacPO()
   {
      return new MacPO(this.toArray(new Mac[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.simpleEnumModel.model.Mac";
   }

   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the Name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet filterName(String value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the Name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet filterName(String lower, String upper)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet filterType(TEnum value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the owner attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet filterOwner(Alex value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value == obj.getOwner())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public MacSet()
   {
      // empty
   }

   public MacSet(Mac... objects)
   {
      for (Mac obj : objects)
      {
         this.add(obj);
      }
   }

   public MacSet(Collection<Mac> objects)
   {
      this.addAll(objects);
   }


   public MacPO createMacPO()
   {
      return new MacPO(this.toArray(new Mac[this.size()]));
   }

   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the Name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet createNameCondition(String value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the Name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet createNameCondition(String lower, String upper)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet createTypeCondition(TEnum value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Mac objects and collect those Mac objects where the owner attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Mac objects that match the parameter
    */
   public MacSet createOwnerCondition(Alex value)
   {
      MacSet result = new MacSet();
      
      for (Mac obj : this)
      {
         if (value == obj.getOwner())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
