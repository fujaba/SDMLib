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
import org.sdmlib.models.pattern.CloneOp;
import org.sdmlib.models.pattern.Pattern;

import de.uniks.networkparser.list.SimpleSet;

public class CloneOpSet extends SimpleSet<CloneOp> implements org.sdmlib.models.modelsets.ModelSet
{
   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CloneOp elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.CloneOp";
   }


   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (CloneOp obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public CloneOpSet withModifier(String value)
   {
      for (CloneOp obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (CloneOp obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public CloneOpSet withHasMatch(boolean value)
   {
      for (CloneOp obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (CloneOp obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public CloneOpSet withPatternObjectName(String value)
   {
      for (CloneOp obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (CloneOp obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public CloneOpSet withDoAllMatches(boolean value)
   {
      for (CloneOp obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (CloneOp obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public CloneOpSet withPattern(Pattern value)
   {
      for (CloneOp obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }



   public CloneOpPO startModelPattern()
   {
      return new CloneOpPO(this.toArray(new CloneOp[this.size()]));
   }


   public CloneOpSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((CloneOp) value);
      }
      
      return this;
   }
   
   public CloneOpSet without(CloneOp value)
   {
      this.remove(value);
      return this;
   }



   public CloneOpPO hasCloneOpPO()
   {
      return new CloneOpPO(this.toArray(new CloneOp[this.size()]));
   }

   public static final CloneOpSet EMPTY_SET = new CloneOpSet().withFlag(CloneOpSet.READONLY);
   public CloneOpSet hasModifier(String value)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CloneOpSet hasModifier(String lower, String upper)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CloneOpSet hasHasMatch(boolean value)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CloneOpSet hasPatternObjectName(String value)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CloneOpSet hasPatternObjectName(String lower, String upper)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public CloneOpSet hasDoAllMatches(boolean value)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public CloneOpPO filterCloneOpPO()
   {
      return new CloneOpPO(this.toArray(new CloneOp[this.size()]));
   }

   /**
    * Loop through the current set of CloneOp objects and collect those CloneOp objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CloneOp objects that match the parameter
    */
   public CloneOpSet filterModifier(String value)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloneOp objects and collect those CloneOp objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CloneOp objects that match the parameter
    */
   public CloneOpSet filterModifier(String lower, String upper)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloneOp objects and collect those CloneOp objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CloneOp objects that match the parameter
    */
   public CloneOpSet filterHasMatch(boolean value)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloneOp objects and collect those CloneOp objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CloneOp objects that match the parameter
    */
   public CloneOpSet filterPatternObjectName(String value)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloneOp objects and collect those CloneOp objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CloneOp objects that match the parameter
    */
   public CloneOpSet filterPatternObjectName(String lower, String upper)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CloneOp objects and collect those CloneOp objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CloneOp objects that match the parameter
    */
   public CloneOpSet filterDoAllMatches(boolean value)
   {
      CloneOpSet result = new CloneOpSet();
      
      for (CloneOp obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
