package org.sdmlib.models.pattern.creators;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.Pattern;

public class PatternElementSet extends ArrayList<PatternElement>
{
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }
}

