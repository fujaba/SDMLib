package org.sdmlib.models.classes.creators;

import org.sdmlib.models.pattern.creators.PatternObjectCreator;

public class AssociationPOCreator extends PatternObjectCreator
{
   public Object getSendableInstance(boolean reference)
   {
      return new AssociationPO();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((AssociationPO) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((AssociationPO) target).set(attrName, value);
   }
}
