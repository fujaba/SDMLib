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

import de.uniks.networkparser.list.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;

import de.uniks.networkparser.list.SimpleSet;

public class PatternElementSet extends SimpleSet<PatternElement<?>>
{
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }
   public PatternElementSet withPattern(Pattern value)
   {
      for (PatternElement obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public PatternElementSet withModifier(String value)
   {
      for (PatternElement obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public PatternElementSet withHasMatch(boolean value)
   {
      for (PatternElement obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public PatternElementSet withDoAllMatches(boolean value)
   {
      for (PatternElement obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public PatternElementSet withPatternObjectName(String value)
   {
      for (PatternElement obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }
   
   public PatternSet getContentOfTypePattern()
   {
      PatternSet result = new PatternSet();
      
      for (PatternElement elem : this)
      {
         if (elem instanceof Pattern)
         {
            result.add((Pattern) elem);
         }
      }
      
      return result;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (PatternElement elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.PatternElement";
   }

   public PatternElement first()
   {
      for (PatternElement pe : this)
      {
         return pe;
      }
      
      return null;
   }


   public PatternElementPO startModelPattern()
   {
      return new PatternElementPO(this.toArray(new PatternElement[this.size()]));
   }


   public PatternElementSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PatternElement<?>>)value);
      }
      else if (value != null)
      {
         this.add((PatternElement) value);
      }
      
      return this;
   }
   
   public PatternElementSet without(PatternElement value)
   {
      this.remove(value);
      return this;
   }



   public PatternElementPO hasPatternElementPO()
   {
      return new PatternElementPO(this.toArray(new PatternElement[this.size()]));
   }

   public static final PatternElementSet EMPTY_SET = new PatternElementSet().withFlag(PatternElementSet.READONLY);
   public PatternElementSet hasModifier(String value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternElementSet hasModifier(String lower, String upper)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternElementSet hasHasMatch(boolean value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternElementSet hasPatternObjectName(String value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternElementSet hasPatternObjectName(String lower, String upper)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public PatternElementSet hasDoAllMatches(boolean value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public PatternElementPO filterPatternElementPO()
   {
      return new PatternElementPO(this.toArray(new PatternElement[this.size()]));
   }

   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet filterModifier(String value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet filterModifier(String lower, String upper)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet filterHasMatch(boolean value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet filterPatternObjectName(String value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet filterPatternObjectName(String lower, String upper)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet filterDoAllMatches(boolean value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
