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
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.models.transformations.util.TemplateSet;
import org.sdmlib.models.transformations.util.PlaceHolderDescriptionSet;

public class MatchSet extends SDMSet<Match>
{


   public MatchPO hasMatchPO()
   {
      return new MatchPO(this.toArray(new Match[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.Match";
   }


   @SuppressWarnings("unchecked")
   public MatchSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Match>)value);
      }
      else if (value != null)
      {
         this.add((Match) value);
      }
      
      return this;
   }
   
   public MatchSet without(Match value)
   {
      this.remove(value);
      return this;
   }

   public intList getStartPos()
   {
      intList result = new intList();
      
      for (Match obj : this)
      {
         result.add(obj.getStartPos());
      }
      
      return result;
   }

   public MatchSet hasStartPos(int value)
   {
      MatchSet result = new MatchSet();
      
      for (Match obj : this)
      {
         if (value == obj.getStartPos())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchSet withStartPos(int value)
   {
      for (Match obj : this)
      {
         obj.setStartPos(value);
      }
      
      return this;
   }

   public intList getEndPos()
   {
      intList result = new intList();
      
      for (Match obj : this)
      {
         result.add(obj.getEndPos());
      }
      
      return result;
   }

   public MatchSet hasEndPos(int value)
   {
      MatchSet result = new MatchSet();
      
      for (Match obj : this)
      {
         if (value == obj.getEndPos())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchSet withEndPos(int value)
   {
      for (Match obj : this)
      {
         obj.setEndPos(value);
      }
      
      return this;
   }

   public StringList getFullText()
   {
      StringList result = new StringList();
      
      for (Match obj : this)
      {
         result.add(obj.getFullText());
      }
      
      return result;
   }

   public MatchSet hasFullText(String value)
   {
      MatchSet result = new MatchSet();
      
      for (Match obj : this)
      {
         if (value.equals(obj.getFullText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchSet withFullText(String value)
   {
      for (Match obj : this)
      {
         obj.setFullText(value);
      }
      
      return this;
   }

   public StringList getMatchText()
   {
      StringList result = new StringList();
      
      for (Match obj : this)
      {
         result.add(obj.getMatchText());
      }
      
      return result;
   }

   public MatchSet hasMatchText(String value)
   {
      MatchSet result = new MatchSet();
      
      for (Match obj : this)
      {
         if (value.equals(obj.getMatchText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchSet withMatchText(String value)
   {
      for (Match obj : this)
      {
         obj.setMatchText(value);
      }
      
      return this;
   }

   public ObjectSet getModelObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (Match obj : this)
      {
         result.add(obj.getModelObject());
      }
      
      return result;
   }

   public MatchSet hasModelObject(Object value)
   {
      MatchSet result = new MatchSet();
      
      for (Match obj : this)
      {
         if (value == obj.getModelObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchSet withModelObject(Object value)
   {
      for (Match obj : this)
      {
         obj.setModelObject(value);
      }
      
      return this;
   }

   public TemplateSet getTemplate()
   {
      TemplateSet result = new TemplateSet();
      
      for (Match obj : this)
      {
         result.add(obj.getTemplate());
      }
      
      return result;
   }

   public MatchSet hasTemplate(Object value)
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
      
      MatchSet answer = new MatchSet();
      
      for (Match obj : this)
      {
         if (neighbors.contains(obj.getTemplate()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MatchSet withTemplate(Template value)
   {
      for (Match obj : this)
      {
         obj.withTemplate(value);
      }
      
      return this;
   }

   public PlaceHolderDescriptionSet getPlaceholder()
   {
      PlaceHolderDescriptionSet result = new PlaceHolderDescriptionSet();
      
      for (Match obj : this)
      {
         result.add(obj.getPlaceholder());
      }
      
      return result;
   }

   public MatchSet hasPlaceholder(Object value)
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
      
      MatchSet answer = new MatchSet();
      
      for (Match obj : this)
      {
         if (neighbors.contains(obj.getPlaceholder()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public MatchSet withPlaceholder(PlaceHolderDescription value)
   {
      for (Match obj : this)
      {
         obj.withPlaceholder(value);
      }
      
      return this;
   }

   public MatchSet getSubMatches()
   {
      MatchSet result = new MatchSet();
      
      for (Match obj : this)
      {
         result.addAll(obj.getSubMatches());
      }
      
      return result;
   }

   public MatchSet hasSubMatches(Object value)
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
      
      MatchSet answer = new MatchSet();
      
      for (Match obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSubMatches()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public MatchSet getSubMatchesTransitive()
   {
      MatchSet todo = new MatchSet().with(this);
      
      MatchSet result = new MatchSet();
      
      while ( ! todo.isEmpty())
      {
         Match current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getSubMatches().minus(result));
         }
      }
      
      return result;
   }

   public MatchSet withSubMatches(Match value)
   {
      for (Match obj : this)
      {
         obj.withSubMatches(value);
      }
      
      return this;
   }

   public MatchSet withoutSubMatches(Match value)
   {
      for (Match obj : this)
      {
         obj.withoutSubMatches(value);
      }
      
      return this;
   }

   public MatchSet getParentMatch()
   {
      MatchSet result = new MatchSet();
      
      for (Match obj : this)
      {
         result.add(obj.getParentMatch());
      }
      
      return result;
   }

   public MatchSet hasParentMatch(Object value)
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
      
      MatchSet answer = new MatchSet();
      
      for (Match obj : this)
      {
         if (neighbors.contains(obj.getParentMatch()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public MatchSet getParentMatchTransitive()
   {
      MatchSet todo = new MatchSet().with(this);
      
      MatchSet result = new MatchSet();
      
      while ( ! todo.isEmpty())
      {
         Match current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getParentMatch()))
            {
               todo.with(current.getParentMatch());
            }
         }
      }
      
      return result;
   }

   public MatchSet withParentMatch(Match value)
   {
      for (Match obj : this)
      {
         obj.withParentMatch(value);
      }
      
      return this;
   }


   public static final MatchSet EMPTY_SET = new MatchSet().withReadOnly(true);
}




