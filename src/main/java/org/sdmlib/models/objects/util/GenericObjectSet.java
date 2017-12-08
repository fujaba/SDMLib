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
   
package org.sdmlib.models.objects.util;

import java.util.Collection;

import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class GenericObjectSet extends SimpleSet<GenericObject>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (GenericObject obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public GenericObjectSet withName(String value)
   {
      for (GenericObject obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public StringList getType()
   {
      StringList result = new StringList();
      
      for (GenericObject obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public GenericObjectSet withType(String value)
   {
      for (GenericObject obj : this)
      {
         obj.withType(value);
      }
      
      return this;
   }

   public GenericGraphSet getGraph()
   {
      GenericGraphSet result = new GenericGraphSet();
      
      for (GenericObject obj : this)
      {
         result.add(obj.getGraph());
      }
      
      return result;
   }
   public GenericObjectSet withGraph(GenericGraph value)
   {
      for (GenericObject obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

   public GenericAttributeSet getAttrs()
   {
      GenericAttributeSet result = new GenericAttributeSet();
      
      for (GenericObject obj : this)
      {
         result.addAll(obj.getAttrs());
      }
      
      return result;
   }
   public GenericObjectSet withAttrs(GenericAttribute value)
   {
      for (GenericObject obj : this)
      {
         obj.withAttrs(value);
      }
      
      return this;
   }

   public GenericObjectSet withoutAttrs(GenericAttribute value)
   {
      for (GenericObject obj : this)
      {
         obj.withoutAttrs(value);
      }
      
      return this;
   }

   public GenericLinkSet getOutgoingLinks()
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericObject obj : this)
      {
         result.addAll(obj.getOutgoingLinks());
      }
      
      return result;
   }
   public GenericObjectSet withOutgoingLinks(GenericLink value)
   {
      for (GenericObject obj : this)
      {
         obj.withOutgoingLinks(value);
      }
      
      return this;
   }

   public GenericObjectSet withoutOutgoingLinks(GenericLink value)
   {
      for (GenericObject obj : this)
      {
         obj.withoutOutgoingLinks(value);
      }
      
      return this;
   }

   public GenericLinkSet getIncommingLinks()
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericObject obj : this)
      {
         result.addAll(obj.getIncommingLinks());
      }
      
      return result;
   }
   public GenericObjectSet withIncommingLinks(GenericLink value)
   {
      for (GenericObject obj : this)
      {
         obj.withIncommingLinks(value);
      }
      
      return this;
   }

   public GenericObjectSet withoutIncommingLinks(GenericLink value)
   {
      for (GenericObject obj : this)
      {
         obj.withoutIncommingLinks(value);
      }
      
      return this;
   }

   public StringList getIcon()
   {
      StringList result = new StringList();
      
      for (GenericObject obj : this)
      {
         result.add(obj.getIcon());
      }
      
      return result;
   }

   public GenericObjectSet withIcon(String value)
   {
      for (GenericObject obj : this)
      {
         obj.withIcon(value);
      }
      
      return this;
   }

   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (GenericObject elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }

   public GenericObjectPO startModelPattern()
   {
      return new GenericObjectPO(this.toArray(new GenericObject[this.size()]));
   }


   public GenericObjectSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((GenericObject) value);
      }
      
      return this;
   }
   
   public GenericObjectSet without(GenericObject value)
   {
      this.remove(value);
      return this;
   }



   public GenericObjectPO hasGenericObjectPO()
   {
      return new GenericObjectPO(this.toArray(new GenericObject[this.size()]));
   }

   public static final GenericObjectSet EMPTY_SET = new GenericObjectSet().withFlag(GenericObjectSet.READONLY);
   public GenericObjectSet hasName(String value)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericObjectSet hasName(String lower, String upper)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericObjectSet hasType(String value)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (value.equals(obj.getType()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericObjectSet hasType(String lower, String upper)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (lower.compareTo(obj.getType()) <= 0 && obj.getType().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericObjectSet hasIcon(String value)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (value.equals(obj.getIcon()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericObjectSet hasIcon(String lower, String upper)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (lower.compareTo(obj.getIcon()) <= 0 && obj.getIcon().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public GenericObjectPO filterGenericObjectPO()
   {
      return new GenericObjectPO(this.toArray(new GenericObject[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.objects.GenericObject";
   }

   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet filterName(String value)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet filterName(String lower, String upper)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet filterType(String value)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (value.equals(obj.getType()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the type attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet filterType(String lower, String upper)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (lower.compareTo(obj.getType()) <= 0 && obj.getType().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the icon attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet filterIcon(String value)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (value.equals(obj.getIcon()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the icon attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet filterIcon(String lower, String upper)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (lower.compareTo(obj.getIcon()) <= 0 && obj.getIcon().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public GenericObjectSet()
   {
      // empty
   }

   public GenericObjectSet(GenericObject... objects)
   {
      for (GenericObject obj : objects)
      {
         this.add(obj);
      }
   }

   public GenericObjectSet(Collection<GenericObject> objects)
   {
      this.addAll(objects);
   }


   public GenericObjectPO createGenericObjectPO()
   {
      return new GenericObjectPO(this.toArray(new GenericObject[this.size()]));
   }

   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet createNameCondition(String value)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet createNameCondition(String lower, String upper)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the type attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet createTypeCondition(String value)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (value.equals(obj.getType()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the type attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet createTypeCondition(String lower, String upper)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (lower.compareTo(obj.getType()) <= 0 && obj.getType().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the icon attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet createIconCondition(String value)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (value.equals(obj.getIcon()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericObject objects and collect those GenericObject objects where the icon attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericObject objects that match the parameter
    */
   public GenericObjectSet createIconCondition(String lower, String upper)
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericObject obj : this)
      {
         if (lower.compareTo(obj.getIcon()) <= 0 && obj.getIcon().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   @Override
   public GenericObjectSet getNewList(boolean keyValue)
   {
      return new GenericObjectSet();
   }
}