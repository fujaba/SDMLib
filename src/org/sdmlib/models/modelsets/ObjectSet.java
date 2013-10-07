package org.sdmlib.models.modelsets;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class ObjectSet extends LinkedHashSet<Object>
{

   public ObjectSet with(Object... elems)
   {
      // TODO Auto-generated method stub
      this.addAll(Arrays.asList(elems));
      return this;
   }

}
