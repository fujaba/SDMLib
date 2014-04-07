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
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.models.pattern.creators.ReachableStateSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class RuleApplicationSet extends LinkedHashSet<RuleApplication> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (RuleApplication elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.RuleApplication";
   }


   public StringList getDescription()
   {
      StringList result = new StringList();
      
      for (RuleApplication obj : this)
      {
         result.add(obj.getDescription());
      }
      
      return result;
   }

   public RuleApplicationSet withDescription(String value)
   {
      for (RuleApplication obj : this)
      {
         obj.setDescription(value);
      }
      
      return this;
   }

   public ReachableStateSet getSrc()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (RuleApplication obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }

   public RuleApplicationSet withSrc(ReachableState value)
   {
      for (RuleApplication obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public ReachableStateSet getTgt()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (RuleApplication obj : this)
      {
         result.add(obj.getTgt());
      }
      
      return result;
   }

   public RuleApplicationSet withTgt(ReachableState value)
   {
      for (RuleApplication obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }



   public RuleApplicationPO startModelPattern()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      RuleApplicationPO patternObject = pattern.hasElementRuleApplicationPO();
      
      patternObject.withCandidates(this);
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public RuleApplicationSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<RuleApplication>)value);
      }
      else if (value != null)
      {
         this.add((RuleApplication) value);
      }
      
      return this;
   }
   
   public RuleApplicationSet without(RuleApplication value)
   {
      this.remove(value);
      return this;
   }



   public RuleApplicationPO hasRuleApplicationPO()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      RuleApplicationPO patternObject = pattern.hasElementRuleApplicationPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}









