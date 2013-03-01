package org.sdmlib.models.transformations.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.Statement;
import org.sdmlib.models.transformations.TransformOp;

public class TransformOpSet extends LinkedHashSet<TransformOp>
{
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
         obj.withName(value);
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

}

