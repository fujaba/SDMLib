package org.sdmlib.models.classes.creators;

import java.util.ArrayList;

import org.sdmlib.models.pattern.PatternObject;

public class ArrayListPO extends PatternObject<ArrayListPO, ArrayList<?>>
{
   public ArrayListSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ArrayListSet matches = new ArrayListSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ArrayList<?>) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

