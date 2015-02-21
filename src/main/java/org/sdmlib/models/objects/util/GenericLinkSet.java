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
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.util.GenericObjectSet;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.objects.util.GenericGraphSet;

public class GenericLinkSet extends SDMSet<GenericLink>
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
         this.addAll((Collection<GenericLink>)value);
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

   public static final GenericLinkSet EMPTY_SET = new GenericLinkSet().withReadonly(true);
}

