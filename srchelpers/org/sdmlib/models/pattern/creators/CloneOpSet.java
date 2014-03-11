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
import org.sdmlib.models.pattern.CloneOp;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.creators.PatternSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class CloneOpSet extends LinkedHashSet<CloneOp> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CloneOp elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


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
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      CloneOpPO patternObject = pattern.hasElementCloneOpPO();
      
      patternObject.withCandidates(this);
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public CloneOpSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<CloneOp>)value);
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
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      CloneOpPO patternObject = pattern.hasElementCloneOpPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}








