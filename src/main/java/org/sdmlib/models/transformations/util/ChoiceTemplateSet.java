/*
   Copyright (c) 2015 christoph 
   
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

import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class ChoiceTemplateSet extends SimpleSet<ChoiceTemplate>
{

   public static final ChoiceTemplateSet EMPTY_SET = new ChoiceTemplateSet().withFlag(ChoiceTemplateSet.READONLY);

   public ChoiceTemplatePO hasChoiceTemplatePO()
   {
      return new ChoiceTemplatePO(this.toArray(new ChoiceTemplate[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ChoiceTemplateSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ChoiceTemplate>) value);
      }
      else if (value != null)
      {
         this.add((ChoiceTemplate) value);
      }

      return this;
   }

   public ChoiceTemplateSet without(ChoiceTemplate value)
   {
      this.remove(value);
      return this;
   }

   public StringList getTemplateText()
   {
      StringList result = new StringList();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getTemplateText());
      }

      return result;
   }

   public ChoiceTemplateSet hasTemplateText(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getTemplateText()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet hasTemplateText(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getTemplateText()) <= 0 && obj.getTemplateText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet withTemplateText(String value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.setTemplateText(value);
      }

      return this;
   }

   public StringList getExpandedText()
   {
      StringList result = new StringList();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getExpandedText());
      }

      return result;
   }

   public ChoiceTemplateSet hasExpandedText(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getExpandedText()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet hasExpandedText(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getExpandedText()) <= 0 && obj.getExpandedText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet withExpandedText(String value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.setExpandedText(value);
      }

      return this;
   }

   public ObjectSet getModelObject()
   {
      ObjectSet result = new ObjectSet();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getModelObject());
      }

      return result;
   }

   public ChoiceTemplateSet hasModelObject(Object value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (value == obj.getModelObject())
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet withModelObject(Object value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.setModelObject(value);
      }

      return this;
   }

   public StringList getModelClassName()
   {
      StringList result = new StringList();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getModelClassName());
      }

      return result;
   }

   public ChoiceTemplateSet hasModelClassName(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getModelClassName()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet hasModelClassName(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getModelClassName()) <= 0 && obj.getModelClassName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet withModelClassName(String value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.setModelClassName(value);
      }

      return this;
   }

   public StringList getListStart()
   {
      StringList result = new StringList();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getListStart());
      }

      return result;
   }

   public ChoiceTemplateSet hasListStart(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getListStart()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet hasListStart(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getListStart()) <= 0 && obj.getListStart().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet withListStart(String value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.setListStart(value);
      }

      return this;
   }

   public StringList getListSeparator()
   {
      StringList result = new StringList();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getListSeparator());
      }

      return result;
   }

   public ChoiceTemplateSet hasListSeparator(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getListSeparator()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet hasListSeparator(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getListSeparator()) <= 0 && obj.getListSeparator().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet withListSeparator(String value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.setListSeparator(value);
      }

      return this;
   }

   public StringList getListEnd()
   {
      StringList result = new StringList();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getListEnd());
      }

      return result;
   }

   public ChoiceTemplateSet hasListEnd(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getListEnd()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet hasListEnd(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getListEnd()) <= 0 && obj.getListEnd().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet withListEnd(String value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.setListEnd(value);
      }

      return this;
   }

   public booleanList getReferenceLookup()
   {
      booleanList result = new booleanList();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getReferenceLookup());
      }

      return result;
   }

   public ChoiceTemplateSet hasReferenceLookup(boolean value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (value == obj.getReferenceLookup())
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet withReferenceLookup(boolean value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.setReferenceLookup(value);
      }

      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getName());
      }

      return result;
   }

   public ChoiceTemplateSet hasName(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet hasName(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }

      return result;
   }

   public ChoiceTemplateSet withName(String value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.setName(value);
      }

      return this;
   }

   public PlaceHolderDescriptionSet getPlaceholders()
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (ChoiceTemplate obj : this)
      {
         result.addAll(obj.getPlaceholders());
      }

      return result;
   }

   public ChoiceTemplateSet hasPlaceholders(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }

      ChoiceTemplateSet answer = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getPlaceholders()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public ChoiceTemplateSet withPlaceholders(PlaceHolderDescription value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.withPlaceholders(value);
      }

      return this;
   }

   public ChoiceTemplateSet withoutPlaceholders(PlaceHolderDescription value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.withoutPlaceholders(value);
      }

      return this;
   }

   public TemplateSet getChoices()
   {
      TemplateSet result = new TemplateSet();

      for (ChoiceTemplate obj : this)
      {
         result.addAll(obj.getChoices());
      }

      return result;
   }

   public ChoiceTemplateSet hasChoices(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }

      ChoiceTemplateSet answer = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getChoices()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public ChoiceTemplateSet withChoices(Template value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.withChoices(value);
      }

      return this;
   }

   public ChoiceTemplateSet withoutChoices(Template value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.withoutChoices(value);
      }

      return this;
   }

   public ChoiceTemplateSet getChooser()
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         result.add(obj.getChooser());
      }

      return result;
   }

   public ChoiceTemplateSet hasChooser(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }

      ChoiceTemplateSet answer = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (neighbors.contains(obj.getChooser()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public ChoiceTemplateSet withChooser(ChoiceTemplate value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.withChooser(value);
      }

      return this;
   }

   public MatchSet getMatches()
   {
      MatchSet result = new MatchSet();

      for (ChoiceTemplate obj : this)
      {
         result.addAll(obj.getMatches());
      }

      return result;
   }

   public ChoiceTemplateSet hasMatches(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }

      ChoiceTemplateSet answer = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getMatches()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public ChoiceTemplateSet withMatches(Match value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.withMatches(value);
      }

      return this;
   }

   public ChoiceTemplateSet withoutMatches(Match value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.withoutMatches(value);
      }

      return this;
   }

   public PlaceHolderDescriptionSet getParents()
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (ChoiceTemplate obj : this)
      {
         result.addAll(obj.getParents());
      }

      return result;
   }

   public ChoiceTemplateSet hasParents(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }

      ChoiceTemplateSet answer = new ChoiceTemplateSet();

      for (ChoiceTemplate obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getParents()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public ChoiceTemplateSet withParents(PlaceHolderDescription value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.withParents(value);
      }

      return this;
   }

   public ChoiceTemplateSet withoutParents(PlaceHolderDescription value)
   {
      for (ChoiceTemplate obj : this)
      {
         obj.withoutParents(value);
      }

      return this;
   }



   public ChoiceTemplatePO filterChoiceTemplatePO()
   {
      return new ChoiceTemplatePO(this.toArray(new ChoiceTemplate[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.ChoiceTemplate";
   }

   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the templateText attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterTemplateText(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getTemplateText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the templateText attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterTemplateText(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getTemplateText()) <= 0 && obj.getTemplateText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the expandedText attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterExpandedText(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getExpandedText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the expandedText attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterExpandedText(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getExpandedText()) <= 0 && obj.getExpandedText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the modelObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterModelObject(Object value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value == obj.getModelObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the modelClassName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterModelClassName(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getModelClassName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the modelClassName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterModelClassName(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getModelClassName()) <= 0 && obj.getModelClassName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listStart attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterListStart(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getListStart()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listStart attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterListStart(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getListStart()) <= 0 && obj.getListStart().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listSeparator attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterListSeparator(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getListSeparator()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listSeparator attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterListSeparator(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getListSeparator()) <= 0 && obj.getListSeparator().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listEnd attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterListEnd(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getListEnd()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listEnd attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterListEnd(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getListEnd()) <= 0 && obj.getListEnd().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the referenceLookup attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterReferenceLookup(boolean value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value == obj.isReferenceLookup())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterName(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet filterName(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public ChoiceTemplateSet()
   {
      // empty
   }

   public ChoiceTemplateSet(ChoiceTemplate... objects)
   {
      for (ChoiceTemplate obj : objects)
      {
         this.add(obj);
      }
   }

   public ChoiceTemplateSet(Collection<ChoiceTemplate> objects)
   {
      this.addAll(objects);
   }


   public ChoiceTemplatePO createChoiceTemplatePO()
   {
      return new ChoiceTemplatePO(this.toArray(new ChoiceTemplate[this.size()]));
   }


   @Override
   public ChoiceTemplateSet getNewList(boolean keyValue)
   {
      return new ChoiceTemplateSet();
   }

   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the expandedText attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createExpandedTextCondition(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getExpandedText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the expandedText attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createExpandedTextCondition(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getExpandedText()) <= 0 && obj.getExpandedText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listEnd attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createListEndCondition(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getListEnd()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listEnd attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createListEndCondition(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getListEnd()) <= 0 && obj.getListEnd().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listSeparator attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createListSeparatorCondition(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getListSeparator()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listSeparator attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createListSeparatorCondition(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getListSeparator()) <= 0 && obj.getListSeparator().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listStart attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createListStartCondition(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getListStart()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the listStart attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createListStartCondition(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getListStart()) <= 0 && obj.getListStart().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the modelClassName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createModelClassNameCondition(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getModelClassName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the modelClassName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createModelClassNameCondition(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getModelClassName()) <= 0 && obj.getModelClassName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the modelObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createModelObjectCondition(Object value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value == obj.getModelObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createNameCondition(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createNameCondition(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the referenceLookup attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createReferenceLookupCondition(boolean value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value == obj.isReferenceLookup())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the templateText attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createTemplateTextCondition(String value)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (value.equals(obj.getTemplateText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChoiceTemplate objects and collect those ChoiceTemplate objects where the templateText attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChoiceTemplate objects that match the parameter
    */
   public ChoiceTemplateSet createTemplateTextCondition(String lower, String upper)
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for (ChoiceTemplate obj : this)
      {
         if (lower.compareTo(obj.getTemplateText()) <= 0 && obj.getTemplateText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
