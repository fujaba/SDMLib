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

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.booleanSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class PatternLinkSet extends LinkedHashSet<PatternLink>
{
   public StringList getSrcRoleName()
   {
      StringList result = new StringList();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getSrcRoleName());
      }
      
      return result;
   }

   public StringList getTgtRoleName()
   {
      StringList result = new StringList();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getTgtRoleName());
      }
      
      return result;
   }

   public PatternObjectSet getTgt()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getTgt());
      }
      
      return result;
   }
   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }
   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }

   public PatternLinkSet withTgtRoleName(String value)
   {
      for (PatternLink obj : this)
      {
         obj.withTgtRoleName(value);
      }
      
      return this;
   }

   public PatternLinkSet withHostGraphSrcObject(Object value)
   {
      for (PatternLink obj : this)
      {
         obj.withHostGraphSrcObject(value);
      }
      
      return this;
   }

   public PatternLinkSet withTgt(PatternObject value)
   {
      for (PatternLink obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

   public PatternLinkSet withSrc(PatternObject value)
   {
      for (PatternLink obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public PatternLinkSet withModifier(String value)
   {
      for (PatternLink obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanSet getHasMatch()
   {
      booleanSet result = new booleanSet();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public PatternLinkSet withHasMatch(boolean value)
   {
      for (PatternLink obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public PatternLinkSet withDoAllMatches(boolean value)
   {
      for (PatternLink obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public PatternLinkSet withPatternObjectName(String value)
   {
      for (PatternLink obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (PatternLink elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.PatternLink";
   }


   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public PatternLinkSet withPattern(Pattern value)
   {
      for (PatternLink obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }



   public PatternLinkPO startModelPattern()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      PatternLinkPO patternObject = pattern.hasElementPatternLinkPO();
      
      patternObject.withCandidates(this);
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public PatternLinkSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PatternLink>)value);
      }
      else if (value != null)
      {
         this.add((PatternLink) value);
      }
      
      return this;
   }
   
   public PatternLinkSet without(PatternLink value)
   {
      this.remove(value);
      return this;
   }



   public PatternLinkPO hasPatternLinkPO()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      PatternLinkPO patternObject = pattern.hasElementPatternLinkPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}











