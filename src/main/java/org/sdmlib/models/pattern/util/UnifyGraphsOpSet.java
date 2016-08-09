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
import org.sdmlib.models.pattern.UnifyGraphsOp;

import de.uniks.networkparser.list.SimpleSet;

public class UnifyGraphsOpSet extends SimpleSet<UnifyGraphsOp> implements org.sdmlib.models.modelsets.ModelSet
{
   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (UnifyGraphsOp elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.UnifyGraphsOp";
   }


   public UnifyGraphsOpSet with(UnifyGraphsOp value)
   {
      this.add(value);
      return this;
   }
   
   public UnifyGraphsOpSet without(UnifyGraphsOp value)
   {
      this.remove(value);
      return this;
   }
   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (UnifyGraphsOp obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public UnifyGraphsOpSet withModifier(String value)
   {
      for (UnifyGraphsOp obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (UnifyGraphsOp obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public UnifyGraphsOpSet withHasMatch(boolean value)
   {
      for (UnifyGraphsOp obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (UnifyGraphsOp obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public UnifyGraphsOpSet withPatternObjectName(String value)
   {
      for (UnifyGraphsOp obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (UnifyGraphsOp obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public UnifyGraphsOpSet withDoAllMatches(boolean value)
   {
      for (UnifyGraphsOp obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (UnifyGraphsOp obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public UnifyGraphsOpSet withPattern(Pattern value)
   {
      for (UnifyGraphsOp obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }



   public UnifyGraphsOpPO startModelPattern()
   {
      return new UnifyGraphsOpPO(this.toArray(new UnifyGraphsOp[this.size()]));
   }


   public UnifyGraphsOpSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<UnifyGraphsOp>)value);
      }
      else if (value != null)
      {
         this.add((UnifyGraphsOp) value);
      }
      
      return this;
   }
   
   public UnifyGraphsOpPO hasUnifyGraphsOpPO()
   {
      return new UnifyGraphsOpPO(this.toArray(new UnifyGraphsOp[this.size()]));
   }

   public static final UnifyGraphsOpSet EMPTY_SET = new UnifyGraphsOpSet().withFlag(UnifyGraphsOpSet.READONLY);
   public UnifyGraphsOpSet hasModifier(String value)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UnifyGraphsOpSet hasModifier(String lower, String upper)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UnifyGraphsOpSet hasHasMatch(boolean value)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UnifyGraphsOpSet hasPatternObjectName(String value)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UnifyGraphsOpSet hasPatternObjectName(String lower, String upper)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UnifyGraphsOpSet hasDoAllMatches(boolean value)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public UnifyGraphsOpPO filterUnifyGraphsOpPO()
   {
      return new UnifyGraphsOpPO(this.toArray(new UnifyGraphsOp[this.size()]));
   }

   /**
    * Loop through the current set of UnifyGraphsOp objects and collect those UnifyGraphsOp objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of UnifyGraphsOp objects that match the parameter
    */
   public UnifyGraphsOpSet filterModifier(String value)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UnifyGraphsOp objects and collect those UnifyGraphsOp objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of UnifyGraphsOp objects that match the parameter
    */
   public UnifyGraphsOpSet filterModifier(String lower, String upper)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UnifyGraphsOp objects and collect those UnifyGraphsOp objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of UnifyGraphsOp objects that match the parameter
    */
   public UnifyGraphsOpSet filterHasMatch(boolean value)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UnifyGraphsOp objects and collect those UnifyGraphsOp objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of UnifyGraphsOp objects that match the parameter
    */
   public UnifyGraphsOpSet filterPatternObjectName(String value)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UnifyGraphsOp objects and collect those UnifyGraphsOp objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of UnifyGraphsOp objects that match the parameter
    */
   public UnifyGraphsOpSet filterPatternObjectName(String lower, String upper)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of UnifyGraphsOp objects and collect those UnifyGraphsOp objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of UnifyGraphsOp objects that match the parameter
    */
   public UnifyGraphsOpSet filterDoAllMatches(boolean value)
   {
      UnifyGraphsOpSet result = new UnifyGraphsOpSet();
      
      for (UnifyGraphsOp obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
