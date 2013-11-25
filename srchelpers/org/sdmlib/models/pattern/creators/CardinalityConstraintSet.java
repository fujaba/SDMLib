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
   
package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.creators.PatternSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.creators.PatternObjectSet;
import org.sdmlib.models.pattern.PatternObject;

public class CardinalityConstraintSet extends LinkedHashSet<CardinalityConstraint> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CardinalityConstraint elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.CardinalityConstraint";
   }


   public CardinalityConstraintSet with(CardinalityConstraint value)
   {
      this.add(value);
      return this;
   }
   
   public CardinalityConstraintSet without(CardinalityConstraint value)
   {
      this.remove(value);
      return this;
   }
   public StringList getTgtRoleName()
   {
      StringList result = new StringList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getTgtRoleName());
      }
      
      return result;
   }

   public CardinalityConstraintSet withTgtRoleName(String value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setTgtRoleName(value);
      }
      
      return this;
   }

   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }

   public CardinalityConstraintSet withHostGraphSrcObject(Object value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setHostGraphSrcObject(value);
      }
      
      return this;
   }

   public longList getMinCard()
   {
      longList result = new longList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getMinCard());
      }
      
      return result;
   }

   public CardinalityConstraintSet withMinCard(long value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setMinCard(value);
      }
      
      return this;
   }

   public longList getMaxCard()
   {
      longList result = new longList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getMaxCard());
      }
      
      return result;
   }

   public CardinalityConstraintSet withMaxCard(long value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setMaxCard(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public CardinalityConstraintSet withModifier(String value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public CardinalityConstraintSet withHasMatch(boolean value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public CardinalityConstraintSet withPatternObjectName(String value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public CardinalityConstraintSet withDoAllMatches(boolean value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public CardinalityConstraintSet withPattern(Pattern value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }

   public CardinalityConstraintSet withSrc(PatternObject value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

}

