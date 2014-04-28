package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class PlaceHolderDescriptionPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new PlaceHolderDescriptionPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((PlaceHolderDescriptionPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((PlaceHolderDescriptionPO) target).set(attrName, value);
   }
}
