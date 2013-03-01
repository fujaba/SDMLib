package org.sdmlib.models.objects.creators;

import org.sdmlib.models.pattern.PatternObject;

public class GenericObjectsTestPO extends PatternObject
{
   public GenericObjectsTestPO startNAC()
   {
      return (GenericObjectsTestPO) super.startNAC();
   }
   
   public GenericObjectsTestPO endNAC()
   {
      return (GenericObjectsTestPO) super.endNAC();
   }
   
}

