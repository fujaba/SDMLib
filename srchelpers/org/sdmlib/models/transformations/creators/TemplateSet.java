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
import java.lang.Object;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionSet;
import java.util.Collection;
import java.util.Collections;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.creators.ChoiceTemplateSet;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.creators.MatchSet;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.transformations.Match;

public class TemplateSet extends LinkedHashSet<Template> implements org.sdmlib.models.modelsets.ModelSet
{
   public Template first()
   {
      for (Template obj : this)
      {
         return obj;
      }
      
      return null;
   }


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

   public TemplateSet hasTemplateText(String value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value.equals(obj.getTemplateText()))
         {
            result.add(obj);
         }
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

   public StringList getExpandedText()
   {
      StringList result = new StringList();
      
      for (Template obj : this)
      {
         result.add(obj.getExpandedText());
      }
      
      return result;
   }

   public TemplateSet hasExpandedText(String value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value.equals(obj.getExpandedText()))
         {
            result.add(obj);
         }
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

   public ObjectSet getModelObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (Template obj : this)
      {
         result.add(obj.getModelObject());
      }
      
      return result;
   }

   public TemplateSet hasModelObject(java.lang.Object value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value == obj.getModelObject())
         {
            result.add(obj);
         }
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

   public TemplateSet hasModelClassName(String value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value.equals(obj.getModelClassName()))
         {
            result.add(obj);
         }
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

   public StringList getListStart()
   {
      StringList result = new StringList();
      
      for (Template obj : this)
      {
         result.add(obj.getListStart());
      }
      
      return result;
   }

   public TemplateSet hasListStart(String value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value.equals(obj.getListStart()))
         {
            result.add(obj);
         }
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

   public TemplateSet hasListSeparator(String value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value.equals(obj.getListSeparator()))
         {
            result.add(obj);
         }
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

   public TemplateSet hasListEnd(String value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value.equals(obj.getListEnd()))
         {
            result.add(obj);
         }
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

   public PlaceHolderDescriptionSet getPlaceholders()
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (Template obj : this)
      {
         result.addAll(obj.getPlaceholders());
      }
      
      return result;
   }

   public TemplateSet hasPlaceholders(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      TemplateSet answer = new TemplateSet();
      
      for (Template obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPlaceholders()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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

   public ChoiceTemplateSet getChooser()
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (Template obj : this)
      {
         result.add(obj.getChooser());
      }
      
      return result;
   }

   public TemplateSet hasChooser(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      TemplateSet answer = new TemplateSet();
      
      for (Template obj : this)
      {
         if (neighbors.contains(obj.getChooser()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TemplateSet withChooser(ChoiceTemplate value)
   {
      for (Template obj : this)
      {
         obj.withChooser(value);
      }
      
      return this;
   }

   public PlaceHolderDescriptionSet getParent()
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (Template obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public TemplateSet hasParent(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      TemplateSet answer = new TemplateSet();
      
      for (Template obj : this)
      {
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TemplateSet withParent(PlaceHolderDescription value)
   {
      for (Template obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public MatchSet getMatches()
   {
      MatchSet result = new MatchSet();
      
      for (Template obj : this)
      {
         result.addAll(obj.getMatches());
      }
      
      return result;
   }

   public TemplateSet hasMatches(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      TemplateSet answer = new TemplateSet();
      
      for (Template obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMatches()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TemplateSet withMatches(Match value)
   {
      for (Template obj : this)
      {
         obj.withMatches(value);
      }
      
      return this;
   }

   public TemplateSet withoutMatches(Match value)
   {
      for (Template obj : this)
      {
         obj.withoutMatches(value);
      }
      
      return this;
   }

}


