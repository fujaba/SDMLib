package org.sdmlib.models.transformations.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.transformations.AttributeOp;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.transformations.OperationObject;

public class AttributeOpSet extends LinkedHashSet<AttributeOp>
{
   public StringList getText()
   {
      StringList result = new StringList();
      
      for (AttributeOp obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public AttributeOpSet withText(String value)
   {
      for (AttributeOp obj : this)
      {
         obj.withText(value);
      }
      
      return this;
   }

   public OperationObjectSet getOperationObject()
   {
      OperationObjectSet result = new OperationObjectSet();
      
      for (AttributeOp obj : this)
      {
         result.add(obj.getOperationObject());
      }
      
      return result;
   }
   public AttributeOpSet withOperationObject(OperationObject value)
   {
      for (AttributeOp obj : this)
      {
         obj.withOperationObject(value);
      }
      
      return this;
   }

}

