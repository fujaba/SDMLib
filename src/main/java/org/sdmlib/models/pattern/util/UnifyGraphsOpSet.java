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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.UnifyGraphsOp;

public class UnifyGraphsOpSet extends SDMSet<UnifyGraphsOp> implements org.sdmlib.models.modelsets.ModelSet
{
   private static final long serialVersionUID = 1L;

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

   public static final UnifyGraphsOpSet EMPTY_SET = new UnifyGraphsOpSet().withReadOnly(true);
}
