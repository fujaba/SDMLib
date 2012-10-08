package org.sdmlib.models.objects.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.objects.GenericObjectsTest;
import org.sdmlib.models.modelsets.StringList;

public class GenericObjectsTestSet extends LinkedHashSet<GenericObjectsTest>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (GenericObjectsTest elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }
}





