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
   
package org.sdmlib.models.transformations.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;

public class TemplateSet extends SDMSet<Template>
{


   public TemplatePO startModelPattern()
   {
      org.sdmlib.models.transformations.util.ModelPattern pattern = new org.sdmlib.models.transformations.util.ModelPattern();
      
      TemplatePO patternObject = pattern.hasElementTemplatePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.Template";
   }


   public TemplateSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Template>)value);
      }
      else if (value != null)
      {
         this.add((Template) value);
      }
      
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

   public TemplateSet hasTemplateText(String lower, String upper)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (lower.compareTo(obj.getTemplateText()) <= 0 && obj.getTemplateText().compareTo(upper) <= 0)
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

   public TemplateSet hasExpandedText(String lower, String upper)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (lower.compareTo(obj.getExpandedText()) <= 0 && obj.getExpandedText().compareTo(upper) <= 0)
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

   public TemplateSet hasModelObject(Object value)
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

   public TemplateSet hasModelClassName(String lower, String upper)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (lower.compareTo(obj.getModelClassName()) <= 0 && obj.getModelClassName().compareTo(upper) <= 0)
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

   public TemplateSet hasListStart(String lower, String upper)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (lower.compareTo(obj.getListStart()) <= 0 && obj.getListStart().compareTo(upper) <= 0)
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

   public TemplateSet hasListSeparator(String lower, String upper)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (lower.compareTo(obj.getListSeparator()) <= 0 && obj.getListSeparator().compareTo(upper) <= 0)
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

   public TemplateSet hasListEnd(String lower, String upper)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (lower.compareTo(obj.getListEnd()) <= 0 && obj.getListEnd().compareTo(upper) <= 0)
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
         result.with(obj.getPlaceholders());
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
         result.with(obj.getChooser());
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

   public MatchSet getMatches()
   {
      MatchSet result = new MatchSet();
      
      for (Template obj : this)
      {
         result.with(obj.getMatches());
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

   public PlaceHolderDescriptionSet getParents()
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (Template obj : this)
      {
         result.with(obj.getParents());
      }
      
      return result;
   }

   public TemplateSet hasParents(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getParents()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TemplateSet withParents(PlaceHolderDescription value)
   {
      for (Template obj : this)
      {
         obj.withParents(value);
      }
      
      return this;
   }

   public TemplateSet withoutParents(PlaceHolderDescription value)
   {
      for (Template obj : this)
      {
         obj.withoutParents(value);
      }
      
      return this;
   }

   public booleanList getReferenceLookup()
   {
      booleanList result = new booleanList();
      
      for (Template obj : this)
      {
         result.add(obj.getReferenceLookup());
      }
      
      return result;
   }

   public TemplateSet hasReferenceLookup(boolean value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value == obj.getReferenceLookup())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TemplateSet withReferenceLookup(boolean value)
   {
      for (Template obj : this)
      {
         obj.setReferenceLookup(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Template obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public TemplateSet hasName(String value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TemplateSet hasName(String lower, String upper)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TemplateSet withName(String value)
   {
      for (Template obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }



   public TemplatePO hasTemplatePO()
   {
      org.sdmlib.models.transformations.util.ModelPattern pattern = new org.sdmlib.models.transformations.util.ModelPattern();
      
      TemplatePO patternObject = pattern.hasElementTemplatePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}










