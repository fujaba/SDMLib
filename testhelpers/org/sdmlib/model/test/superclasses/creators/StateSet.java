package org.sdmlib.model.test.superclasses.creators;

import java.util.LinkedHashSet;

import org.sdmlib.model.test.superclasses.State;
import org.sdmlib.models.modelsets.StringList;

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

   public StateSet withTest(String value)
   {
      for (State obj : this)
      {
         obj.withTest(value);
      }
      
      return this;
   }

}



