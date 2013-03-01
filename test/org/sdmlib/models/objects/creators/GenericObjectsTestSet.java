package org.sdmlib.models.objects.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.objects.GenericObjectsTest;

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


   public String getEntryType()
   {
      return "org.sdmlib.models.objects.GenericObjectsTest";
   }


   public GenericObjectsTestSet with(GenericObjectsTest value)
   {
      this.add(value);
      return this;
   }
   
   public GenericObjectsTestSet without(GenericObjectsTest value)
   {
      this.remove(value);
      return this;
   }
}






