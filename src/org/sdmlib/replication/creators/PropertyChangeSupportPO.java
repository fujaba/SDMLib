package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import java.beans.PropertyChangeSupport;
import org.sdmlib.replication.creators.PropertyChangeSupportSet;

public class PropertyChangeSupportPO extends PatternObject<PropertyChangeSupportPO, PropertyChangeSupport>
{
   public PropertyChangeSupportSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PropertyChangeSupportSet matches = new PropertyChangeSupportSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PropertyChangeSupport) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

