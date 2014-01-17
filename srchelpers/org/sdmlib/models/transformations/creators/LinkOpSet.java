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
   
package org.sdmlib.models.transformations.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.TransformOp;

public class LinkOpSet extends LinkedHashSet<LinkOp> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (LinkOp elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.LinkOp";
   }

   public StringList getSrcText()
   {
      StringList result = new StringList();
      
      for (LinkOp obj : this)
      {
         result.add(obj.getSrcText());
      }
      
      return result;
   }

   public LinkOpSet withSrcText(String value)
   {
      for (LinkOp obj : this)
      {
         obj.setSrcText(value);
      }
      
      return this;
   }

   public StringList getTgtText()
   {
      StringList result = new StringList();
      
      for (LinkOp obj : this)
      {
         result.add(obj.getTgtText());
      }
      
      return result;
   }

   public LinkOpSet withTgtText(String value)
   {
      for (LinkOp obj : this)
      {
         obj.setTgtText(value);
      }
      
      return this;
   }

   public OperationObjectSet getSrc()
   {
      OperationObjectSet result = new OperationObjectSet();
      
      for (LinkOp obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }

   public LinkOpSet withSrc(OperationObject value)
   {
      for (LinkOp obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public OperationObjectSet getTgt()
   {
      OperationObjectSet result = new OperationObjectSet();
      
      for (LinkOp obj : this)
      {
         result.add(obj.getTgt());
      }
      
      return result;
   }

   public LinkOpSet withTgt(OperationObject value)
   {
      for (LinkOp obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

   public TransformOpSet getTransformOp()
   {
      TransformOpSet result = new TransformOpSet();
      
      for (LinkOp obj : this)
      {
         result.add(obj.getTransformOp());
      }
      
      return result;
   }

   public LinkOpSet withTransformOp(TransformOp value)
   {
      for (LinkOp obj : this)
      {
         obj.withTransformOp(value);
      }
      
      return this;
   }



   public LinkOpPO startModelPattern()
   {
      org.sdmlib.models.transformations.creators.ModelPattern pattern = new org.sdmlib.models.transformations.creators.ModelPattern();
      
      LinkOpPO patternObject = pattern.hasElementLinkOpPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public LinkOpSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<LinkOp>)value);
      }
      else if (value != null)
      {
         this.add((LinkOp) value);
      }
      
      return this;
   }
   
   public LinkOpSet without(LinkOp value)
   {
      this.remove(value);
      return this;
   }

}


