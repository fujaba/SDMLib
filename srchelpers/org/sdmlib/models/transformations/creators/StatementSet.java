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

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.Statement;
import org.sdmlib.models.transformations.TransformOp;

public class StatementSet extends LinkedHashSet<Statement>
{
   public StringList getText()
   {
      StringList result = new StringList();
      
      for (Statement obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public StatementSet withText(String value)
   {
      for (Statement obj : this)
      {
         obj.withText(value);
      }
      
      return this;
   }

   public StatementSet getNext()
   {
      StatementSet result = new StatementSet();
      
      for (Statement obj : this)
      {
         result.add(obj.getNext());
      }
      
      return result;
   }
   public StatementSet withNext(Statement value)
   {
      for (Statement obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public StatementSet getPrev()
   {
      StatementSet result = new StatementSet();
      
      for (Statement obj : this)
      {
         result.add(obj.getPrev());
      }
      
      return result;
   }
   public StatementSet withPrev(Statement value)
   {
      for (Statement obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public OperationObjectSet getOperationObjects()
   {
      OperationObjectSet result = new OperationObjectSet();
      
      for (Statement obj : this)
      {
         result.addAll(obj.getOperationObjects());
      }
      
      return result;
   }
   public StatementSet withOperationObjects(OperationObject value)
   {
      for (Statement obj : this)
      {
         obj.withOperationObjects(value);
      }
      
      return this;
   }

   public StatementSet withoutOperationObjects(OperationObject value)
   {
      for (Statement obj : this)
      {
         obj.withoutOperationObjects(value);
      }
      
      return this;
   }

   public TransformOpSet getTransformOp()
   {
      TransformOpSet result = new TransformOpSet();
      
      for (Statement obj : this)
      {
         result.add(obj.getTransformOp());
      }
      
      return result;
   }
   public StatementSet withTransformOp(TransformOp value)
   {
      for (Statement obj : this)
      {
         obj.withTransformOp(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Statement elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.transformations.Statement";
   }


   public StatementSet with(Statement value)
   {
      this.add(value);
      return this;
   }
   
   public StatementSet without(Statement value)
   {
      this.remove(value);
      return this;
   }
}


