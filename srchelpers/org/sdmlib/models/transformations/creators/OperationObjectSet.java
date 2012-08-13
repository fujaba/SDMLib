package org.sdmlib.models.transformations.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.transformations.TransformOp;
import org.sdmlib.models.transformations.AttributeOp;
import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.models.transformations.Statement;

public class OperationObjectSet extends LinkedHashSet<OperationObject>
{
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
         obj.withName(value);
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
         obj.withType(value);
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
         obj.withSet(value);
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

}

