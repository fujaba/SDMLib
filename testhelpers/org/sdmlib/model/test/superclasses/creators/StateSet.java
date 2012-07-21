package org.sdmlib.model.test.superclasses.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.test.superclasses.State;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;

public class StateSet extends LinkedHashSet<State>
{
   public StringList getTest()
   {
      StringList result = new StringList();
      
      for (State obj : this)
      {
         result.add(obj.getTest());
      }
      
      return result;
   }

}


