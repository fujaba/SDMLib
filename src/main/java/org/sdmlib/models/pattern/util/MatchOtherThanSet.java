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

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.MatchOtherThan;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import java.lang.Object;
import org.sdmlib.models.pattern.util.PatternSet;

import de.uniks.networkparser.list.SimpleSet;

import org.sdmlib.models.pattern.util.PatternObjectSet;

public class MatchOtherThanSet extends SimpleSet<MatchOtherThan> implements org.sdmlib.models.modelsets.ModelSet
{
   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (MatchOtherThan elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.MatchOtherThan";
   }


   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (MatchOtherThan obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }

   public MatchOtherThanSet withHostGraphSrcObject(Object value)
   {
      for (MatchOtherThan obj : this)
      {
         obj.setHostGraphSrcObject(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (MatchOtherThan obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public MatchOtherThanSet withModifier(String value)
   {
      for (MatchOtherThan obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (MatchOtherThan obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public MatchOtherThanSet withHasMatch(boolean value)
   {
      for (MatchOtherThan obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (MatchOtherThan obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public MatchOtherThanSet withPatternObjectName(String value)
   {
      for (MatchOtherThan obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (MatchOtherThan obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public MatchOtherThanSet withDoAllMatches(boolean value)
   {
      for (MatchOtherThan obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (MatchOtherThan obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public MatchOtherThanSet withPattern(Pattern value)
   {
      for (MatchOtherThan obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (MatchOtherThan obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }

   public MatchOtherThanSet withSrc(PatternObject value)
   {
      for (MatchOtherThan obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public PatternObjectSet getForbidden()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (MatchOtherThan obj : this)
      {
         result.add(obj.getForbidden());
      }
      
      return result;
   }

   public MatchOtherThanSet withForbidden(PatternObject value)
   {
      for (MatchOtherThan obj : this)
      {
         obj.withForbidden(value);
      }
      
      return this;
   }



   public MatchOtherThanPO startModelPattern()
   {
      return new MatchOtherThanPO(this.toArray(new MatchOtherThan[this.size()]));
   }


   public MatchOtherThanSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((MatchOtherThan) value);
      }
      
      return this;
   }
   
   public MatchOtherThanSet without(MatchOtherThan value)
   {
      this.remove(value);
      return this;
   }



   public MatchOtherThanPO hasMatchOtherThanPO()
   {
      return new MatchOtherThanPO(this.toArray(new MatchOtherThan[this.size()]));
   }

   public static final MatchOtherThanSet EMPTY_SET = new MatchOtherThanSet().withFlag(MatchOtherThanSet.READONLY);
   public MatchOtherThanSet hasHostGraphSrcObject(Object value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value == obj.getHostGraphSrcObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThanSet hasModifier(String value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThanSet hasModifier(String lower, String upper)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThanSet hasHasMatch(boolean value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThanSet hasPatternObjectName(String value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThanSet hasPatternObjectName(String lower, String upper)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchOtherThanSet hasDoAllMatches(boolean value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public MatchOtherThanPO filterMatchOtherThanPO()
   {
      return new MatchOtherThanPO(this.toArray(new MatchOtherThan[this.size()]));
   }

   /**
    * Loop through the current set of MatchOtherThan objects and collect those MatchOtherThan objects where the hostGraphSrcObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThan objects that match the parameter
    */
   public MatchOtherThanSet filterHostGraphSrcObject(Object value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value == obj.getHostGraphSrcObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThan objects and collect those MatchOtherThan objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThan objects that match the parameter
    */
   public MatchOtherThanSet filterModifier(String value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThan objects and collect those MatchOtherThan objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MatchOtherThan objects that match the parameter
    */
   public MatchOtherThanSet filterModifier(String lower, String upper)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThan objects and collect those MatchOtherThan objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThan objects that match the parameter
    */
   public MatchOtherThanSet filterHasMatch(boolean value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThan objects and collect those MatchOtherThan objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThan objects that match the parameter
    */
   public MatchOtherThanSet filterPatternObjectName(String value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThan objects and collect those MatchOtherThan objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MatchOtherThan objects that match the parameter
    */
   public MatchOtherThanSet filterPatternObjectName(String lower, String upper)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThan objects and collect those MatchOtherThan objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThan objects that match the parameter
    */
   public MatchOtherThanSet filterDoAllMatches(boolean value)
   {
      MatchOtherThanSet result = new MatchOtherThanSet();
      
      for (MatchOtherThan obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
