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

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;

public class GenericGraphSet extends LinkedHashSet<GenericGraph>
{
   public GenericObjectSet getObjects()
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericGraph obj : this)
      {
         result.addAll(obj.getObjects());
      }
      
      return result;
   }
   public GenericGraphSet withObjects(GenericObject value)
   {
      for (GenericGraph obj : this)
      {
         obj.withObjects(value);
      }
      
      return this;
   }

   public GenericGraphSet withoutObjects(GenericObject value)
   {
      for (GenericGraph obj : this)
      {
         obj.withoutObjects(value);
      }
      
      return this;
   }

   public GenericLinkSet getLinks()
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericGraph obj : this)
      {
         result.addAll(obj.getLinks());
      }
      
      return result;
   }
   public GenericGraphSet withLinks(GenericLink value)
   {
      for (GenericGraph obj : this)
      {
         obj.withLinks(value);
      }
      
      return this;
   }

   public GenericGraphSet withoutLinks(GenericLink value)
   {
      for (GenericGraph obj : this)
      {
         obj.withoutLinks(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (GenericGraph elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.objects.GenericGraph";
   }


   public GenericGraphPO startModelPattern()
   {
      org.sdmlib.models.objects.util.ModelPattern pattern = new org.sdmlib.models.objects.util.ModelPattern();
      
      GenericGraphPO patternObject = pattern.hasElementGenericGraphPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public GenericGraphSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<GenericGraph>)value);
      }
      else if (value != null)
      {
         this.add((GenericGraph) value);
      }
      
      return this;
   }
   
   public GenericGraphSet without(GenericGraph value)
   {
      this.remove(value);
      return this;
   }



   public GenericGraphPO hasGenericGraphPO()
   {
      org.sdmlib.models.objects.util.ModelPattern pattern = new org.sdmlib.models.objects.util.ModelPattern();
      
      GenericGraphPO patternObject = pattern.hasElementGenericGraphPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}












