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
   
package org.sdmlib.models.transformations.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.transformations.creators.TemplateSet;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.models.modelsets.booleanList;

public class PlaceHolderDescriptionSet extends LinkedHashSet<PlaceHolderDescription> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (PlaceHolderDescription elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.PlaceHolderDescription";
   }


   public PlaceHolderDescriptionSet with(PlaceHolderDescription value)
   {
      this.add(value);
      return this;
   }
   
   public PlaceHolderDescriptionSet without(PlaceHolderDescription value)
   {
      this.remove(value);
      return this;
   }
   public StringList getTextFragment()
   {
      StringList result = new StringList();
      
      for (PlaceHolderDescription obj : this)
      {
         result.add(obj.getTextFragment());
      }
      
      return result;
   }

   public PlaceHolderDescriptionSet withTextFragment(String value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.setTextFragment(value);
      }
      
      return this;
   }

   public StringList getValue()
   {
      StringList result = new StringList();
      
      for (PlaceHolderDescription obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public PlaceHolderDescriptionSet withValue(String value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.setValue(value);
      }
      
      return this;
   }

   public StringList getAttrName()
   {
      StringList result = new StringList();
      
      for (PlaceHolderDescription obj : this)
      {
         result.add(obj.getAttrName());
      }
      
      return result;
   }

   public PlaceHolderDescriptionSet withAttrName(String value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.setAttrName(value);
      }
      
      return this;
   }

   public TemplateSet getTemplate()
   {
      TemplateSet result = new TemplateSet();
      
      for (PlaceHolderDescription obj : this)
      {
         result.add(obj.getTemplate());
      }
      
      return result;
   }

   public PlaceHolderDescriptionSet withTemplate(Template value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.withTemplate(value);
      }
      
      return this;
   }

   public TemplateSet getSubTemplate()
   {
      TemplateSet result = new TemplateSet();
      
      for (PlaceHolderDescription obj : this)
      {
         result.add(obj.getSubTemplate());
      }
      
      return result;
   }

   public PlaceHolderDescriptionSet withSubTemplate(Template value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.withSubTemplate(value);
      }
      
      return this;
   }

   public booleanList getIsKeyAttribute()
   {
      booleanList result = new booleanList();
      
      for (PlaceHolderDescription obj : this)
      {
         result.add(obj.getIsKeyAttribute());
      }
      
      return result;
   }

   public PlaceHolderDescriptionSet withIsKeyAttribute(boolean value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.setIsKeyAttribute(value);
      }
      
      return this;
   }

}


