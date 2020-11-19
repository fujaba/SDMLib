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

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;

import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class GenericLinkSet extends SimpleSet<GenericLink>
{
   public StringList getTgtLabel()
   {
      StringList result = new StringList();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getTgtLabel());
      }
      
      return result;
   }

   public GenericLinkSet withTgtLabel(String value)
   {
      for (GenericLink obj : this)
      {
         obj.withTgtLabel(value);
      }
      
      return this;
   }

   public StringList getSrcLabel()
   {
      StringList result = new StringList();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getSrcLabel());
      }
      
      return result;
   }

   public GenericLinkSet withSrcLabel(String value)
   {
      for (GenericLink obj : this)
      {
         obj.withSrcLabel(value);
      }
      
      return this;
   }

   public GenericObjectSet getSrc()
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }
   public GenericLinkSet withSrc(GenericObject value)
   {
      for (GenericLink obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public GenericObjectSet getTgt()
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getTgt());
      }
      
      return result;
   }
   public GenericLinkSet withTgt(GenericObject value)
   {
      for (GenericLink obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

   public GenericGraphSet getGraph()
   {
      GenericGraphSet result = new GenericGraphSet();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getGraph());
      }
      
      return result;
   }
   public GenericLinkSet withGraph(GenericGraph value)
   {
      for (GenericLink obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (GenericLink elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }




   public String getEntryType()
   {
      return "org.sdmlib.models.objects.GenericLink";
   }


   public GenericLinkPO startModelPattern()
   {
      return new GenericLinkPO(this.toArray(new GenericLink[this.size()]));
   }


   public GenericLinkSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((GenericLink) value);
      }
      
      return this;
   }
   
   public GenericLinkSet without(GenericLink value)
   {
      this.remove(value);
      return this;
   }



   public GenericLinkPO hasGenericLinkPO()
   {
      return new GenericLinkPO(this.toArray(new GenericLink[this.size()]));
   }

   public static final GenericLinkSet EMPTY_SET = new GenericLinkSet().withFlag(GenericLinkSet.READONLY);
   public GenericLinkSet hasTgtLabel(String value)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (value.equals(obj.getTgtLabel()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericLinkSet hasTgtLabel(String lower, String upper)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (lower.compareTo(obj.getTgtLabel()) <= 0 && obj.getTgtLabel().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericLinkSet hasSrcLabel(String value)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (value.equals(obj.getSrcLabel()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericLinkSet hasSrcLabel(String lower, String upper)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (lower.compareTo(obj.getSrcLabel()) <= 0 && obj.getSrcLabel().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public GenericLinkPO filterGenericLinkPO()
   {
      return new GenericLinkPO(this.toArray(new GenericLink[this.size()]));
   }

   /**
    * Loop through the current set of GenericLink objects and collect those GenericLink objects where the tgtLabel attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericLink objects that match the parameter
    */
   public GenericLinkSet filterTgtLabel(String value)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (value.equals(obj.getTgtLabel()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericLink objects and collect those GenericLink objects where the tgtLabel attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericLink objects that match the parameter
    */
   public GenericLinkSet filterTgtLabel(String lower, String upper)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (lower.compareTo(obj.getTgtLabel()) <= 0 && obj.getTgtLabel().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericLink objects and collect those GenericLink objects where the srcLabel attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericLink objects that match the parameter
    */
   public GenericLinkSet filterSrcLabel(String value)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (value.equals(obj.getSrcLabel()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericLink objects and collect those GenericLink objects where the srcLabel attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericLink objects that match the parameter
    */
   public GenericLinkSet filterSrcLabel(String lower, String upper)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (lower.compareTo(obj.getSrcLabel()) <= 0 && obj.getSrcLabel().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public GenericLinkSet()
   {
      // empty
   }

   public GenericLinkSet(GenericLink... objects)
   {
      for (GenericLink obj : objects)
      {
         this.add(obj);
      }
   }

   public GenericLinkSet(Collection<GenericLink> objects)
   {
      this.addAll(objects);
   }


   public GenericLinkPO createGenericLinkPO()
   {
      return new GenericLinkPO(this.toArray(new GenericLink[this.size()]));
   }

   /**
    * Loop through the current set of GenericLink objects and collect those GenericLink objects where the tgtLabel attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericLink objects that match the parameter
    */
   public GenericLinkSet createTgtLabelCondition(String value)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (value.equals(obj.getTgtLabel()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericLink objects and collect those GenericLink objects where the tgtLabel attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericLink objects that match the parameter
    */
   public GenericLinkSet createTgtLabelCondition(String lower, String upper)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (lower.compareTo(obj.getTgtLabel()) <= 0 && obj.getTgtLabel().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericLink objects and collect those GenericLink objects where the srcLabel attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericLink objects that match the parameter
    */
   public GenericLinkSet createSrcLabelCondition(String value)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (value.equals(obj.getSrcLabel()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericLink objects and collect those GenericLink objects where the srcLabel attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericLink objects that match the parameter
    */
   public GenericLinkSet createSrcLabelCondition(String lower, String upper)
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericLink obj : this)
      {
         if (lower.compareTo(obj.getSrcLabel()) <= 0 && obj.getSrcLabel().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   @Override
   public GenericLinkSet getNewList(boolean keyValue)
   {
      return new GenericLinkSet();
   }
}
