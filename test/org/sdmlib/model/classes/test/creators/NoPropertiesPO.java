package org.sdmlib.model.classes.test.creators;

import org.sdmlib.model.classes.test.NoProperties;
import org.sdmlib.models.pattern.PatternObject;

public class NoPropertiesPO extends PatternObject<NoPropertiesPO, NoProperties>
{
   public NoPropertiesSet allMatches()
   {
      this.setDoAllMatches(true);
      
      NoPropertiesSet matches = new NoPropertiesSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((NoProperties) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

