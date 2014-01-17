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
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.transformations.AttributeOp;
import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.Statement;
import org.sdmlib.models.transformations.TransformOp;

public class OperationObjectSet extends LinkedHashSet<OperationObject> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (OperationObject elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.OperationObject";
   }


   public StringList getName()
   {
      StringList result = new StringList();
      
      for (OperationObject obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public OperationObjectSet withName(String value)
   {
      for (OperationObject obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public StringList getType()
   {
      StringList result = new StringList();
      
      for (OperationObject obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public OperationObjectSet withType(String value)
   {
      for (OperationObject obj : this)
      {
         obj.setType(value);
      }
      
      return this;
   }

   public booleanList getSet()
   {
      booleanList result = new booleanList();
      
      for (OperationObject obj : this)
      {
         result.add(obj.getSet());
      }
      
      return result;
   }

   public OperationObjectSet withSet(boolean value)
   {
      for (OperationObject obj : this)
      {
         obj.setSet(value);
      }
      
      return this;
   }

   public TransformOpSet getTransformOp()
   {
      TransformOpSet result = new TransformOpSet();
      
      for (OperationObject obj : this)
      {
         result.add(obj.getTransformOp());
      }
      
      return result;
   }

   public OperationObjectSet withTransformOp(TransformOp value)
   {
      for (OperationObject obj : this)
      {
         obj.withTransformOp(value);
      }
      
      return this;
   }

   public AttributeOpSet getAttributeOps()
   {
      AttributeOpSet result = new AttributeOpSet();
      
      for (OperationObject obj : this)
      {
         result.addAll(obj.getAttributeOps());
      }
      
      return result;
   }

   public OperationObjectSet withAttributeOps(AttributeOp value)
   {
      for (OperationObject obj : this)
      {
         obj.withAttributeOps(value);
      }
      
      return this;
   }

   public OperationObjectSet withoutAttributeOps(AttributeOp value)
   {
      for (OperationObject obj : this)
      {
         obj.withoutAttributeOps(value);
      }
      
      return this;
   }

   public LinkOpSet getOutgoings()
   {
      LinkOpSet result = new LinkOpSet();
      
      for (OperationObject obj : this)
      {
         result.addAll(obj.getOutgoings());
      }
      
      return result;
   }

   public OperationObjectSet withOutgoings(LinkOp value)
   {
      for (OperationObject obj : this)
      {
         obj.withOutgoings(value);
      }
      
      return this;
   }

   public OperationObjectSet withoutOutgoings(LinkOp value)
   {
      for (OperationObject obj : this)
      {
         obj.withoutOutgoings(value);
      }
      
      return this;
   }

   public LinkOpSet getIncomings()
   {
      LinkOpSet result = new LinkOpSet();
      
      for (OperationObject obj : this)
      {
         result.addAll(obj.getIncomings());
      }
      
      return result;
   }

   public OperationObjectSet withIncomings(LinkOp value)
   {
      for (OperationObject obj : this)
      {
         obj.withIncomings(value);
      }
      
      return this;
   }

   public OperationObjectSet withoutIncomings(LinkOp value)
   {
      for (OperationObject obj : this)
      {
         obj.withoutIncomings(value);
      }
      
      return this;
   }

   public StatementSet getStatements()
   {
      StatementSet result = new StatementSet();
      
      for (OperationObject obj : this)
      {
         result.addAll(obj.getStatements());
      }
      
      return result;
   }

   public OperationObjectSet withStatements(Statement value)
   {
      for (OperationObject obj : this)
      {
         obj.withStatements(value);
      }
      
      return this;
   }

   public OperationObjectSet withoutStatements(Statement value)
   {
      for (OperationObject obj : this)
      {
         obj.withoutStatements(value);
      }
      
      return this;
   }



   public OperationObjectPO startModelPattern()
   {
      org.sdmlib.models.transformations.creators.ModelPattern pattern = new org.sdmlib.models.transformations.creators.ModelPattern();
      
      OperationObjectPO patternObject = pattern.hasElementOperationObjectPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public OperationObjectSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<OperationObject>)value);
      }
      else if (value != null)
      {
         this.add((OperationObject) value);
      }
      
      return this;
   }
   
   public OperationObjectSet without(OperationObject value)
   {
      this.remove(value);
      return this;
   }

}


