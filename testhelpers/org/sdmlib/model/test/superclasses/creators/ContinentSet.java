package org.sdmlib.model.test.superclasses.creators;

import java.util.LinkedHashSet;

import org.sdmlib.model.test.superclasses.Continent;
import org.sdmlib.models.modelsets.StringList;

public class ContinentSet extends LinkedHashSet<Continent>
{
   public StringList getTest()
   {
      StringList result = new StringList();
      
      for (Continent obj : this)
      {
         result.add(obj.getTest());
      }
      
      return result;
   }

   public ContinentSet withTest(String value)
   {
      for (Continent obj : this)
      {
         obj.withTest(value);
      }
      
      return this;
   }

}



