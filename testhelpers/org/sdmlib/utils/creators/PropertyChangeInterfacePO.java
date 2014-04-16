package org.sdmlib.utils.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.util.PropertyChangeInterface;
import org.sdmlib.utils.creators.PropertyChangeInterfaceSet;

public class PropertyChangeInterfacePO extends PatternObject<PropertyChangeInterfacePO, PropertyChangeInterface>
{
   public PropertyChangeInterfaceSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PropertyChangeInterfaceSet matches = new PropertyChangeInterfaceSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PropertyChangeInterface) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

