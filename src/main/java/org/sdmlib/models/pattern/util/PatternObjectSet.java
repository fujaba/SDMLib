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
   
package org.sdmlib.models.pattern.util;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.MatchOtherThan;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.pattern.util.CardinalityConstraintSet;
import org.sdmlib.models.pattern.util.MatchOtherThanSet;

public class PatternObjectSet extends SimpleSet<PatternObject>
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

//   public PatternLinkSet getIncoming()
//   {
//      PatternLinkSet result = new PatternLinkSet();
//      
//      for (PatternObject obj : this)
//      {
//         result.addAll(obj.getIncoming());
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

   public PatternObjectSet withIncoming(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withIncoming(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutIncoming(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutIncoming(value);
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

   public MatchOtherThanSet getMatchOtherThan()
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (PatternObject obj : this)
      {
         result.addAll(obj.getMatchOtherThan());
      }
      
      return result;
   }

   public PatternObjectSet withMatchOtherThan(MatchOtherThan value)
   {
      for (PatternObject obj : this)
      {
         obj.withMatchOtherThan(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutMatchOtherThan(MatchOtherThan value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutMatchOtherThan(value);
      }
      
      return this;
   }

   public MatchOtherThanSet getExcluders()
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (PatternObject obj : this)
      {
         result.addAll(obj.getExcluders());
      }
      
      return result;
   }

   public PatternObjectSet withExcluders(MatchOtherThan value)
   {
      for (PatternObject obj : this)
      {
         obj.withExcluders(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutExcluders(MatchOtherThan value)
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

//   public PatternLinkSet getIncoming()
//   {
//      PatternLinkSet result = new PatternLinkSet();
//      
//      for (PatternObject obj : this)
//      {
//         result.addAll(obj.getIncoming());
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
      return new PatternObjectPO(this.toArray(new PatternObject[this.size()]));
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
      return new PatternObjectPO(this.toArray(new PatternObject[this.size()]));
   }

   public static final PatternObjectSet EMPTY_SET = new PatternObjectSet().withFlag(PatternObjectSet.READONLY);
   public PatternObjectSet hasCurrentMatch(Object value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.getCurrentMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternObjectSet hasCandidates(Object value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.getCandidates())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternObjectSet hasModifier(String value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternObjectSet hasModifier(String lower, String upper)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternObjectSet hasHasMatch(boolean value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternObjectSet hasPatternObjectName(String value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternObjectSet hasPatternObjectName(String lower, String upper)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternObjectSet hasDoAllMatches(boolean value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public PatternObjectPO filterPatternObjectPO()
   {
      return new PatternObjectPO(this.toArray(new PatternObject[this.size()]));
   }

   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the currentMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet filterCurrentMatch(Object value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.getCurrentMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the candidates attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet filterCandidates(Object value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.getCandidates())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet filterModifier(String value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet filterModifier(String lower, String upper)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet filterHasMatch(boolean value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet filterPatternObjectName(String value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet filterPatternObjectName(String lower, String upper)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet filterDoAllMatches(boolean value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
