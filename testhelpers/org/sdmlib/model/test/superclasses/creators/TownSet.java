package org.sdmlib.model.test.superclasses.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.test.superclasses.Town;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;

public class TownSet extends LinkedHashSet<Town>
{
   public StringList getTest()
   {
      StringList result = new StringList();
      
      for (Town obj : this)
      {
         result.add(obj.getTest());
      }
      
      return result;
   }

   public TownSet withTest(String value)
   {
      for (Town obj : this)
      {
         obj.withTest(value);
      }
      
      return this;
   }

}



