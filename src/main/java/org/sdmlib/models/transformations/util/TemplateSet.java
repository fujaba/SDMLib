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

public class TemplateSet extends SimpleSet<Template>
{

   public static final TemplateSet EMPTY_SET = new TemplateSet().withFlag(TemplateSet.READONLY);

   public TemplatePO hasTemplatePO()
   {
      return new TemplatePO(this.toArray(new Template[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public TemplateSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Template>) value);
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
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }

      TemplateSet answer = new TemplateSet();

      for (Template obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getPlaceholders()))
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
         neighbors.addAll((Collection<?>) value);
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
         result.addAll(obj.getMatches());
      }

      return result;
   }

   public TemplateSet hasMatches(Object value)
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

      TemplateSet answer = new TemplateSet();

      for (Template obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getMatches()))
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
         result.addAll(obj.getParents());
      }

      return result;
   }

   public TemplateSet hasParents(Object value)
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

      TemplateSet answer = new TemplateSet();

      for (Template obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getParents()))
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



   public TemplatePO filterTemplatePO()
   {
      return new TemplatePO(this.toArray(new Template[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.Template";
   }

   /**
    * Loop through the current set of Template objects and collect those Template objects where the templateText attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterTemplateText(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the templateText attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterTemplateText(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the expandedText attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterExpandedText(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the expandedText attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterExpandedText(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the modelObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterModelObject(Object value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the modelClassName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterModelClassName(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the modelClassName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterModelClassName(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listStart attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterListStart(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listStart attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterListStart(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listSeparator attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterListSeparator(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listSeparator attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterListSeparator(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listEnd attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterListEnd(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listEnd attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterListEnd(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the referenceLookup attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterReferenceLookup(boolean value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value == obj.isReferenceLookup())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Template objects and collect those Template objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterName(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet filterName(String lower, String upper)
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


   public TemplateSet()
   {
      // empty
   }

   public TemplateSet(Template... objects)
   {
      for (Template obj : objects)
      {
         this.add(obj);
      }
   }

   public TemplateSet(Collection<Template> objects)
   {
      this.addAll(objects);
   }


   public TemplatePO createTemplatePO()
   {
      return new TemplatePO(this.toArray(new Template[this.size()]));
   }


   @Override
   public TemplateSet getNewList(boolean keyValue)
   {
      return new TemplateSet();
   }

   public ChoiceTemplateSet instanceOfChoiceTemplate()
   {
      ChoiceTemplateSet result = new ChoiceTemplateSet();
      
      for(Object obj : this)
      {
         if (obj instanceof ChoiceTemplate)
         {
            result.with(obj);
         }
      }
      
      return result;
   }
   /**
    * Loop through the current set of Template objects and collect those Template objects where the expandedText attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createExpandedTextCondition(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the expandedText attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createExpandedTextCondition(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listEnd attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createListEndCondition(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listEnd attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createListEndCondition(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listSeparator attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createListSeparatorCondition(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listSeparator attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createListSeparatorCondition(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listStart attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createListStartCondition(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the listStart attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createListStartCondition(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the modelClassName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createModelClassNameCondition(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the modelClassName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createModelClassNameCondition(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the modelObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createModelObjectCondition(Object value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createNameCondition(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createNameCondition(String lower, String upper)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the referenceLookup attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createReferenceLookupCondition(boolean value)
   {
      TemplateSet result = new TemplateSet();
      
      for (Template obj : this)
      {
         if (value == obj.isReferenceLookup())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Template objects and collect those Template objects where the templateText attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createTemplateTextCondition(String value)
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


   /**
    * Loop through the current set of Template objects and collect those Template objects where the templateText attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Template objects that match the parameter
    */
   public TemplateSet createTemplateTextCondition(String lower, String upper)
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

}
