package org.sdmlib.examples.clickcounter.creators;

import org.sdmlib.models.pattern.PatternObject;
import javafx.beans.property.IntegerProperty;
import org.sdmlib.examples.clickcounter.creators.IntegerPropertySet;

public class IntegerPropertyPO extends
      PatternObject<IntegerPropertyPO, IntegerProperty>
{
   public IntegerPropertySet allMatches()
   {
      this.setDoAllMatches(true);

      IntegerPropertySet matches = new IntegerPropertySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((IntegerProperty) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

}
