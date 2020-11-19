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
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class PlaceHolderDescriptionSet extends SimpleSet<PlaceHolderDescription>
{

   public static final PlaceHolderDescriptionSet EMPTY_SET = new PlaceHolderDescriptionSet().withFlag(PlaceHolderDescriptionSet.READONLY);

   public PlaceHolderDescriptionPO hasPlaceHolderDescriptionPO()
   {
      return new PlaceHolderDescriptionPO(this.toArray(new PlaceHolderDescription[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public PlaceHolderDescriptionSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PlaceHolderDescription>) value);
      }
      else if (value != null)
      {
         this.add((PlaceHolderDescription) value);
      }

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

   public PlaceHolderDescriptionSet hasTextFragment(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getTextFragment()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public PlaceHolderDescriptionSet hasTextFragment(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getTextFragment()) <= 0 && obj.getTextFragment().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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

   public PlaceHolderDescriptionSet hasValue(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getValue()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public PlaceHolderDescriptionSet hasValue(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getValue()) <= 0 && obj.getValue().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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

   public PlaceHolderDescriptionSet hasAttrName(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getAttrName()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public PlaceHolderDescriptionSet hasAttrName(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getAttrName()) <= 0 && obj.getAttrName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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

   public booleanList getIsKeyAttribute()
   {
      booleanList result = new booleanList();

      for (PlaceHolderDescription obj : this)
      {
         result.add(obj.getIsKeyAttribute());
      }

      return result;
   }

   public PlaceHolderDescriptionSet hasIsKeyAttribute(boolean value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (value == obj.getIsKeyAttribute())
         {
            result.add(obj);
         }
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

   public StringList getPrefix()
   {
      StringList result = new StringList();

      for (PlaceHolderDescription obj : this)
      {
         result.add(obj.getPrefix());
      }

      return result;
   }

   public PlaceHolderDescriptionSet hasPrefix(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getPrefix()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public PlaceHolderDescriptionSet hasPrefix(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getPrefix()) <= 0 && obj.getPrefix().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }

      return result;
   }

   public PlaceHolderDescriptionSet withPrefix(String value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.setPrefix(value);
      }

      return this;
   }

   public TemplateSet getOwners()
   {
      TemplateSet result = new TemplateSet();

      for (PlaceHolderDescription obj : this)
      {
         result.addAll(obj.getOwners());
      }

      return result;
   }

   public PlaceHolderDescriptionSet hasOwners(Object value)
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

      PlaceHolderDescriptionSet answer = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getOwners()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public PlaceHolderDescriptionSet withOwners(Template value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.withOwners(value);
      }

      return this;
   }

   public PlaceHolderDescriptionSet withoutOwners(Template value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.withoutOwners(value);
      }

      return this;
   }

   public MatchSet getMatches()
   {
      MatchSet result = new MatchSet();

      for (PlaceHolderDescription obj : this)
      {
         result.addAll(obj.getMatches());
      }

      return result;
   }

   public PlaceHolderDescriptionSet hasMatches(Object value)
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

      PlaceHolderDescriptionSet answer = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (!Collections.disjoint(neighbors, obj.getMatches()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public PlaceHolderDescriptionSet withMatches(Match value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.withMatches(value);
      }

      return this;
   }

   public PlaceHolderDescriptionSet withoutMatches(Match value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.withoutMatches(value);
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

   public PlaceHolderDescriptionSet hasSubTemplate(Object value)
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

      PlaceHolderDescriptionSet answer = new PlaceHolderDescriptionSet();

      for (PlaceHolderDescription obj : this)
      {
         if (neighbors.contains(obj.getSubTemplate()))
         {
            answer.add(obj);
         }
      }

      return answer;
   }

   public PlaceHolderDescriptionSet withSubTemplate(Template value)
   {
      for (PlaceHolderDescription obj : this)
      {
         obj.withSubTemplate(value);
      }

      return this;
   }



   public PlaceHolderDescriptionPO filterPlaceHolderDescriptionPO()
   {
      return new PlaceHolderDescriptionPO(this.toArray(new PlaceHolderDescription[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.PlaceHolderDescription";
   }

   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the textFragment attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet filterTextFragment(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getTextFragment()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the textFragment attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet filterTextFragment(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getTextFragment()) <= 0 && obj.getTextFragment().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the value attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet filterValue(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getValue()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the value attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet filterValue(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getValue()) <= 0 && obj.getValue().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the attrName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet filterAttrName(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getAttrName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the attrName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet filterAttrName(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getAttrName()) <= 0 && obj.getAttrName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the isKeyAttribute attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet filterIsKeyAttribute(boolean value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value == obj.isIsKeyAttribute())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the prefix attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet filterPrefix(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getPrefix()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the prefix attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet filterPrefix(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getPrefix()) <= 0 && obj.getPrefix().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public PlaceHolderDescriptionSet()
   {
      // empty
   }

   public PlaceHolderDescriptionSet(PlaceHolderDescription... objects)
   {
      for (PlaceHolderDescription obj : objects)
      {
         this.add(obj);
      }
   }

   public PlaceHolderDescriptionSet(Collection<PlaceHolderDescription> objects)
   {
      this.addAll(objects);
   }


   public PlaceHolderDescriptionPO createPlaceHolderDescriptionPO()
   {
      return new PlaceHolderDescriptionPO(this.toArray(new PlaceHolderDescription[this.size()]));
   }


   @Override
   public PlaceHolderDescriptionSet getNewList(boolean keyValue)
   {
      return new PlaceHolderDescriptionSet();
   }

   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the attrName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet createAttrNameCondition(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getAttrName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the attrName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet createAttrNameCondition(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getAttrName()) <= 0 && obj.getAttrName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the isKeyAttribute attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet createIsKeyAttributeCondition(boolean value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value == obj.isIsKeyAttribute())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the prefix attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet createPrefixCondition(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getPrefix()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the prefix attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet createPrefixCondition(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getPrefix()) <= 0 && obj.getPrefix().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the textFragment attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet createTextFragmentCondition(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getTextFragment()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the textFragment attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet createTextFragmentCondition(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getTextFragment()) <= 0 && obj.getTextFragment().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the value attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet createValueCondition(String value)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (value.equals(obj.getValue()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PlaceHolderDescription objects and collect those PlaceHolderDescription objects where the value attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PlaceHolderDescription objects that match the parameter
    */
   public PlaceHolderDescriptionSet createValueCondition(String lower, String upper)
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (PlaceHolderDescription obj : this)
      {
         if (lower.compareTo(obj.getValue()) <= 0 && obj.getValue().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
