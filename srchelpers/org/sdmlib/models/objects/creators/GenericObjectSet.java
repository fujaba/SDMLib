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
   
package org.sdmlib.models.objects.creators;

import java.util.Collection;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;

public class GenericObjectSet extends SDMSet<GenericObject>
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

   public GenericObjectSet with(GenericObject... genObjects)
   {
      for (GenericObject genObj : genObjects)
      {
         this.add(genObj);
      }
      
      return this;
   }


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (GenericObject elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }




   public String getEntryType()
   {
      return "org.sdmlib.models.objects.GenericObject";
   }


   public GenericObjectPO startModelPattern()
   {
      org.sdmlib.models.objects.creators.ModelPattern pattern = new org.sdmlib.models.objects.creators.ModelPattern();
      
      GenericObjectPO patternObject = pattern.hasElementGenericObjectPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public GenericObjectSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<GenericObject>)value);
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
      org.sdmlib.models.objects.creators.ModelPattern pattern = new org.sdmlib.models.objects.creators.ModelPattern();
      
      GenericObjectPO patternObject = pattern.hasElementGenericObjectPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


}













