package org.sdmlib.models.modelsets;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class ObjectSet extends LinkedHashSet<Object>
{
   private static final long serialVersionUID = 1L;

   public ObjectSet with(Object... elems)
   {
      // TODO Auto-generated method stub
      this.addAll(Arrays.asList(elems));
      return this;
   }

}
