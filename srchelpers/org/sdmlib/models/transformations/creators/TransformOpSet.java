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
import org.sdmlib.models.transformations.Statement;
import org.sdmlib.models.transformations.TransformOp;

public class TransformOpSet extends LinkedHashSet<TransformOp> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (TransformOp elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.TransformOp";
   }


   public TransformOpSet with(TransformOp value)
   {
      this.add(value);
      return this;
   }
   
   public TransformOpSet without(TransformOp value)
   {
      this.remove(value);
      return this;
   }
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (TransformOp obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public TransformOpSet withName(String value)
   {
      for (TransformOp obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public OperationObjectSet getOpObjects()
   {
      OperationObjectSet result = new OperationObjectSet();
      
      for (TransformOp obj : this)
      {
         result.addAll(obj.getOpObjects());
      }
      
      return result;
   }

   public TransformOpSet withOpObjects(OperationObject value)
   {
      for (TransformOp obj : this)
      {
         obj.withOpObjects(value);
      }
      
      return this;
   }

   public TransformOpSet withoutOpObjects(OperationObject value)
   {
      for (TransformOp obj : this)
      {
         obj.withoutOpObjects(value);
      }
      
      return this;
   }

   public LinkOpSet getLinkOps()
   {
      LinkOpSet result = new LinkOpSet();
      
      for (TransformOp obj : this)
      {
         result.addAll(obj.getLinkOps());
      }
      
      return result;
   }

   public TransformOpSet withLinkOps(LinkOp value)
   {
      for (TransformOp obj : this)
      {
         obj.withLinkOps(value);
      }
      
      return this;
   }

   public TransformOpSet withoutLinkOps(LinkOp value)
   {
      for (TransformOp obj : this)
      {
         obj.withoutLinkOps(value);
      }
      
      return this;
   }

   public StatementSet getStatements()
   {
      StatementSet result = new StatementSet();
      
      for (TransformOp obj : this)
      {
         result.addAll(obj.getStatements());
      }
      
      return result;
   }

   public TransformOpSet withStatements(Statement value)
   {
      for (TransformOp obj : this)
      {
         obj.withStatements(value);
      }
      
      return this;
   }

   public TransformOpSet withoutStatements(Statement value)
   {
      for (TransformOp obj : this)
      {
         obj.withoutStatements(value);
      }
      
      return this;
   }



   public TransformOpPO startModelPattern()
   {
      org.sdmlib.models.transformations.creators.ModelPattern pattern = new org.sdmlib.models.transformations.creators.ModelPattern();
      
      TransformOpPO patternObject = pattern.hasElementTransformOpPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public TransformOpSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TransformOp>)value);
      }
      else if (value != null)
      {
         this.add((TransformOp) value);
      }
      
      return this;
   }
   

}


