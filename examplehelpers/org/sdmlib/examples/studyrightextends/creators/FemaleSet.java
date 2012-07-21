package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Female;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;

public class FemaleSet extends LinkedHashSet<Female>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Female obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

}

