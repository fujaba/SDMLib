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

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import java.lang.Object;
import org.sdmlib.models.pattern.util.PatternSet;

import de.uniks.networkparser.list.SimpleSet;

import org.sdmlib.models.pattern.util.PatternObjectSet;

public class MatchOtherThenSet extends SimpleSet<MatchOtherThen> implements org.sdmlib.models.modelsets.ModelSet
{
   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (MatchOtherThen elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.MatchOtherThen";
   }


   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }

   public MatchOtherThenSet withHostGraphSrcObject(Object value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setHostGraphSrcObject(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public MatchOtherThenSet withModifier(String value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public MatchOtherThenSet withHasMatch(boolean value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public MatchOtherThenSet withPatternObjectName(String value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public MatchOtherThenSet withDoAllMatches(boolean value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public MatchOtherThenSet withPattern(Pattern value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }

   public MatchOtherThenSet withSrc(PatternObject value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public PatternObjectSet getForbidden()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getForbidden());
      }
      
      return result;
   }

   public MatchOtherThenSet withForbidden(PatternObject value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.withForbidden(value);
      }
      
      return this;
   }



   public MatchOtherThenPO startModelPattern()
   {
      return new MatchOtherThenPO(this.toArray(new MatchOtherThen[this.size()]));
   }


   public MatchOtherThenSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((MatchOtherThen) value);
      }
      
      return this;
   }
   
   public MatchOtherThenSet without(MatchOtherThen value)
   {
      this.remove(value);
      return this;
   }



   public MatchOtherThenPO hasMatchOtherThenPO()
   {
      return new MatchOtherThenPO(this.toArray(new MatchOtherThen[this.size()]));
   }

   public static final MatchOtherThenSet EMPTY_SET = new MatchOtherThenSet().withFlag(MatchOtherThenSet.READONLY);
   public MatchOtherThenSet hasHostGraphSrcObject(Object value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value == obj.getHostGraphSrcObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThenSet hasModifier(String value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThenSet hasModifier(String lower, String upper)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThenSet hasHasMatch(boolean value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThenSet hasPatternObjectName(String value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThenSet hasPatternObjectName(String lower, String upper)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThenSet hasDoAllMatches(boolean value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public MatchOtherThenPO filterMatchOtherThenPO()
   {
      return new MatchOtherThenPO(this.toArray(new MatchOtherThen[this.size()]));
   }

   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the hostGraphSrcObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet filterHostGraphSrcObject(Object value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value == obj.getHostGraphSrcObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet filterModifier(String value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet filterModifier(String lower, String upper)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet filterHasMatch(boolean value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet filterPatternObjectName(String value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet filterPatternObjectName(String lower, String upper)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet filterDoAllMatches(boolean value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
