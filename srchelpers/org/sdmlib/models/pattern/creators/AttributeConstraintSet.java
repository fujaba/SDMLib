package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.pattern.PatternObject;

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
}

