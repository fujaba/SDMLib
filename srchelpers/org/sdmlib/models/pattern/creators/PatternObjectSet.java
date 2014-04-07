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

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.creators.PatternSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.pattern.creators.AttributeConstraintSet;
import org.sdmlib.models.pattern.creators.DestroyObjectElemSet;
import org.sdmlib.models.pattern.creators.CardinalityConstraintSet;
import org.sdmlib.models.pattern.creators.MatchOtherThenSet;

public class PatternObjectSet extends LinkedHashSet<PatternObject>
{
   public LinkedHashSet<Object> getCurrentMatch()
   {
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getCurrentMatch());
      }
      
      return result;
   }

//   public PatternLinkSet getIncomming()
//   {
//      PatternLinkSet result = new PatternLinkSet();
//      
//      for (PatternObject obj : this)
//      {
//         result.addAll(obj.getIncomming());
//      }
//      
//      return result;
//   }
//   public PatternLinkSet getOutgoing()
//   {
//      PatternLinkSet result = new PatternLinkSet();
//      
//      for (PatternObject obj : this)
//      {
//         result.addAll(obj.getOutgoing());
//      }
//      
//      return result;
//   }
   public LinkedHashSet<Object> getCandidates()
   {
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getCandidates());
      }
      
      return result;
   }

   public AttributeConstraintSet getAttrConstraints()
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (PatternObject obj : this)
      {
         result.addAll(obj.getAttrConstraints());
      }
      
      return result;
   }
   public PatternObjectSet withCurrentMatch(Object value)
   {
      for (PatternObject obj : this)
      {
         obj.withCurrentMatch(value);
      }
      
      return this;
   }

   public PatternObjectSet withCandidates(Object value)
   {
      for (PatternObject obj : this)
      {
         obj.withCandidates(value);
      }
      
      return this;
   }

   public PatternObjectSet withIncomming(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withIncomming(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutIncomming(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutIncomming(value);
      }
      
      return this;
   }

   public PatternObjectSet withOutgoing(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withOutgoing(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutOutgoing(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutOutgoing(value);
      }
      
      return this;
   }

   public PatternObjectSet withAttrConstraints(AttributeConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withAttrConstraints(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutAttrConstraints(AttributeConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutAttrConstraints(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public PatternObjectSet withModifier(String value)
   {
      for (PatternObject obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public PatternObjectSet withHasMatch(boolean value)
   {
      for (PatternObject obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public DestroyObjectElemSet getDestroyElem()
   {
      DestroyObjectElemSet result = new DestroyObjectElemSet();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getDestroyElem());
      }
      
      return result;
   }
   public PatternObjectSet withDestroyElem(DestroyObjectElem value)
   {
      for (PatternObject obj : this)
      {
         obj.withDestroyElem(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public PatternObjectSet withDoAllMatches(boolean value)
   {
      for (PatternObject obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public PatternObjectSet withPatternObjectName(String value)
   {
      for (PatternObject obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (PatternObject elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.PatternObject";
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public PatternObjectSet withPattern(Pattern value)
   {
      for (PatternObject obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   public StringList getPoName()
   {
      StringList result = new StringList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getPoName());
      }
      
      return result;
   }

   public PatternObjectSet withPoName(String value)
   {
      for (PatternObject obj : this)
      {
         obj.setPoName(value);
      }
      
      return this;
   }

   public CardinalityConstraintSet getCardConstraints()
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (PatternObject obj : this)
      {
         result.addAll(obj.getCardConstraints());
      }
      
      return result;
   }

   public PatternObjectSet withCardConstraints(CardinalityConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withCardConstraints(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutCardConstraints(CardinalityConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutCardConstraints(value);
      }
      
      return this;
   }

   public MatchOtherThenSet getMatchOtherThen()
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (PatternObject obj : this)
      {
         result.addAll(obj.getMatchOtherThen());
      }
      
      return result;
   }

   public PatternObjectSet withMatchOtherThen(MatchOtherThen value)
   {
      for (PatternObject obj : this)
      {
         obj.withMatchOtherThen(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutMatchOtherThen(MatchOtherThen value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutMatchOtherThen(value);
      }
      
      return this;
   }

   public MatchOtherThenSet getExcluders()
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (PatternObject obj : this)
      {
         result.addAll(obj.getExcluders());
      }
      
      return result;
   }

   public PatternObjectSet withExcluders(MatchOtherThen value)
   {
      for (PatternObject obj : this)
      {
         obj.withExcluders(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutExcluders(MatchOtherThen value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutExcluders(value);
      }
      
      return this;
   }

//   public PatternLinkSet getOutgoing()
//   {
//      PatternLinkSet result = new PatternLinkSet();
//      
//      for (PatternObject obj : this)
//      {
//         result.addAll(obj.getOutgoing());
//      }
//      
//      return result;
//   }

//   public PatternLinkSet getIncomming()
//   {
//      PatternLinkSet result = new PatternLinkSet();
//      
//      for (PatternObject obj : this)
//      {
//         result.addAll(obj.getIncomming());
//      }
//      
//      return result;
//   }
//
//   public PatternLinkSet getOutgoing()
//   {
//      PatternLinkSet result = new PatternLinkSet();
//      
//      for (PatternObject obj : this)
//      {
//         result.addAll(obj.getOutgoing());
//      }
//      
//      return result;
//   }




   public PatternObjectPO startModelPattern()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      PatternObjectPO patternObject = pattern.hasElementPatternObjectPO();
      
      patternObject.withCandidates(this);
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public PatternObjectSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PatternObject>)value);
      }
      else if (value != null)
      {
         this.add((PatternObject) value);
      }
      
      return this;
   }
   
   public PatternObjectSet without(PatternObject value)
   {
      this.remove(value);
      return this;
   }



   public PatternObjectPO hasPatternObjectPO()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      PatternObjectPO patternObject = pattern.hasElementPatternObjectPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}






























