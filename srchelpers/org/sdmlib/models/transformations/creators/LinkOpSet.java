package org.sdmlib.models.transformations.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.transformations.LinkOp;
import org.sdmlib.models.transformations.OperationObject;
import org.sdmlib.models.transformations.TransformOp;

public class LinkOpSet extends LinkedHashSet<LinkOp>
{
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
         obj.withSrcText(value);
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
         obj.withTgtText(value);
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

}

