/*
   Copyright (c) 2016 zuendorf
   
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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.classes.Role;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;

public class RoleSet extends SDMSet<Role>
{

   public static final RoleSet EMPTY_SET = new RoleSet().withFlag(RoleSet.READONLY);


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Role";
   }


   @SuppressWarnings("unchecked")
   public RoleSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Role>)value);
      }
      else if (value != null)
      {
         this.add((Role) value);
      }
      
      return this;
   }
   
   public RoleSet without(Role value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public RoleSet filter(Condition<Role> newValue) {
      RoleSet filterList = new RoleSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Role objects and collect a list of the card attribute values. 
    * 
    * @return List of String objects reachable via card attribute
    */
   public StringList getCard()
   {
      StringList result = new StringList();
      
      for (Role obj : this)
      {
         result.add(obj.getCard());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Role objects and collect those Role objects where the card attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Role objects that match the parameter
    */
   public RoleSet filterCard(String value)
   {
      RoleSet result = new RoleSet();
      
      for (Role obj : this)
      {
         if (value.equals(obj.getCard()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Role objects and collect those Role objects where the card attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Role objects that match the parameter
    */
   public RoleSet filterCard(String lower, String upper)
   {
      RoleSet result = new RoleSet();
      
      for (Role obj : this)
      {
         if (lower.compareTo(obj.getCard()) <= 0 && obj.getCard().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Role objects and assign value to the card attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Role objects now with new attribute values.
    */
   public RoleSet withCard(String value)
   {
      for (Role obj : this)
      {
         obj.setCard(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Role objects and collect a list of the kind attribute values. 
    * 
    * @return List of String objects reachable via kind attribute
    */
   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (Role obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Role objects and collect those Role objects where the kind attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Role objects that match the parameter
    */
   public RoleSet filterKind(String value)
   {
      RoleSet result = new RoleSet();
      
      for (Role obj : this)
      {
         if (value.equals(obj.getKind()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Role objects and collect those Role objects where the kind attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Role objects that match the parameter
    */
   public RoleSet filterKind(String lower, String upper)
   {
      RoleSet result = new RoleSet();
      
      for (Role obj : this)
      {
         if (lower.compareTo(obj.getKind()) <= 0 && obj.getKind().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Role objects and assign value to the kind attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Role objects now with new attribute values.
    */
   public RoleSet withKind(String value)
   {
      for (Role obj : this)
      {
         obj.setKind(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Role objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Role obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Role objects and collect those Role objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Role objects that match the parameter
    */
   public RoleSet filterName(String value)
   {
      RoleSet result = new RoleSet();
      
      for (Role obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Role objects and collect those Role objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Role objects that match the parameter
    */
   public RoleSet filterName(String lower, String upper)
   {
      RoleSet result = new RoleSet();
      
      for (Role obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Role objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Role objects now with new attribute values.
    */
   public RoleSet withName(String value)
   {
      for (Role obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

}
