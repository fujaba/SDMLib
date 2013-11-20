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
import org.sdmlib.models.transformations.Template;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.transformations.creators.ObjectSet;
import java.lang.Object;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionSet;
import org.sdmlib.models.transformations.PlaceHolderDescription;

public class TemplateSet extends LinkedHashSet<Template> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Template elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.Template";
   }


   public TemplateSet with(Template value)
   {
      this.add(value);
      return this;
   }
   
   public TemplateSet without(Template value)
   {
      this.remove(value);
      return this;
   }
   public StringList getTemplateText()
   {
      StringList result = new StringList();
      
      for (Template obj : this)
      {
         result.add(obj.getTemplateText());
      }
      
      return result;
   }

   public TemplateSet withTemplateText(String value)
   {
      for (Template obj : this)
      {
         obj.setTemplateText(value);
      }
      
      return this;
   }

   public ObjectSet getModelObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (Template obj : this)
      {
         result.add(obj.getModelObject());
      }
      
      return result;
   }

   public TemplateSet withModelObject(Object value)
   {
      for (Template obj : this)
      {
         obj.setModelObject(value);
      }
      
      return this;
   }

   public StringList getModelClassName()
   {
      StringList result = new StringList();
      
      for (Template obj : this)
      {
         result.add(obj.getModelClassName());
      }
      
      return result;
   }

   public TemplateSet withModelClassName(String value)
   {
      for (Template obj : this)
      {
         obj.setModelClassName(value);
      }
      
      return this;
   }

   public PlaceHolderDescriptionSet getPlaceholders()
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (Template obj : this)
      {
         result.addAll(obj.getPlaceholders());
      }
      
      return result;
   }

   public TemplateSet withPlaceholders(PlaceHolderDescription value)
   {
      for (Template obj : this)
      {
         obj.withPlaceholders(value);
      }
      
      return this;
   }

   public TemplateSet withoutPlaceholders(PlaceHolderDescription value)
   {
      for (Template obj : this)
      {
         obj.withoutPlaceholders(value);
      }
      
      return this;
   }

   public PlaceHolderDescriptionSet getPlaceholderDescription()
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (Template obj : this)
      {
         result.add(obj.getPlaceholderDescription());
      }
      
      return result;
   }

   public TemplateSet withPlaceholderDescription(PlaceHolderDescription value)
   {
      for (Template obj : this)
      {
         obj.withPlaceholderDescription(value);
      }
      
      return this;
   }

   public StringList getExpandedText()
   {
      StringList result = new StringList();
      
      for (Template obj : this)
      {
         result.add(obj.getExpandedText());
      }
      
      return result;
   }

   public TemplateSet withExpandedText(String value)
   {
      for (Template obj : this)
      {
         obj.setExpandedText(value);
      }
      
      return this;
   }

   public StringList getListStart()
   {
      StringList result = new StringList();
      
      for (Template obj : this)
      {
         result.add(obj.getListStart());
      }
      
      return result;
   }

   public TemplateSet withListStart(String value)
   {
      for (Template obj : this)
      {
         obj.setListStart(value);
      }
      
      return this;
   }

   public StringList getListSeparator()
   {
      StringList result = new StringList();
      
      for (Template obj : this)
      {
         result.add(obj.getListSeparator());
      }
      
      return result;
   }

   public TemplateSet withListSeparator(String value)
   {
      for (Template obj : this)
      {
         obj.setListSeparator(value);
      }
      
      return this;
   }

   public StringList getListEnd()
   {
      StringList result = new StringList();
      
      for (Template obj : this)
      {
         result.add(obj.getListEnd());
      }
      
      return result;
   }

   public TemplateSet withListEnd(String value)
   {
      for (Template obj : this)
      {
         obj.setListEnd(value);
      }
      
      return this;
   }

}



