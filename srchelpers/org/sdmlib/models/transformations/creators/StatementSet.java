package org.sdmlib.models.transformations.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.transformations.Statement;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.transformations.OperationObject;
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

}

