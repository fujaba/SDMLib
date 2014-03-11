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
import org.sdmlib.models.pattern.creators.PatternSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

import java.util.List;
import java.util.Collections;
import org.sdmlib.models.pattern.creators.PatternObjectSet;

public class AttributeConstraintSet extends LinkedHashSet<AttributeConstraint>
{
   public StringList getAttrName()
   {
      StringList result = new StringList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getAttrName());
      }
      
      return result;
   }
   
   public AttributeConstraintSet hasAttrName(String value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint attributeConstraint : this)
      {
         if (attributeConstraint.getAttrName().equals(value))
         {
            result.add(attributeConstraint);
         }
      }
      return result;
   }
   
   public AttributeConstraint first()
   {
      for (AttributeConstraint obj : this)
      {
         return obj;
      }
      
      return null;
   }

   public LinkedHashSet<Object> getTgtValue()
   {
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getTgtValue());
      }
      
      return result;
   }

   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }

   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }
   public AttributeConstraintSet withAttrName(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withAttrName(value);
      }
      
      return this;
   }

   public AttributeConstraintSet withTgtValue(Object value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withTgtValue(value);
      }
      
      return this;
   }

   public AttributeConstraintSet withHostGraphSrcObject(Object value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withHostGraphSrcObject(value);
      }
      
      return this;
   }

   public AttributeConstraintSet withSrc(PatternObject value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public AttributeConstraintSet withModifier(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public AttributeConstraintSet withHasMatch(boolean value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public AttributeConstraintSet withDoAllMatches(boolean value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public AttributeConstraintSet withPatternObjectName(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (AttributeConstraint elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.AttributeConstraint";
   }


   public AttributeConstraintSet with(AttributeConstraint value)
   {
      this.add(value);
      return this;
   }
   
   public AttributeConstraintSet without(AttributeConstraint value)
   {
      this.remove(value);
      return this;
   }
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public AttributeConstraintSet withPattern(Pattern value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   public StringList getCmpOp()
   {
      StringList result = new StringList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getCmpOp());
      }
      
      return result;
   }

   public AttributeConstraintSet withCmpOp(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setCmpOp(value);
      }
      
      return this;
   }



   public AttributeConstraintPO startModelPattern()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      AttributeConstraintPO patternObject = pattern.hasElementAttributeConstraintPO();
      
      patternObject.withCandidates(this);
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public AttributeConstraintSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<AttributeConstraint>)value);
      }
      else if (value != null)
      {
         this.add((AttributeConstraint) value);
      }
      
      return this;
   }
   

   public ObjectSet getUpperTgtValue()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getUpperTgtValue());
      }
      
      return result;
   }

   public AttributeConstraintSet hasUpperTgtValue(Object value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value == obj.getUpperTgtValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AttributeConstraintSet withUpperTgtValue(Object value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setUpperTgtValue(value);
      }
      
      return this;
   }



   public AttributeConstraintPO hasAttributeConstraintPO()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      AttributeConstraintPO patternObject = pattern.hasElementAttributeConstraintPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}














