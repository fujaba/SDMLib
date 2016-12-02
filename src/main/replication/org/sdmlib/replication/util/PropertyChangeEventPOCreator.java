package org.sdmlib.replication.util;

import java.beans.PropertyChangeEvent;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.IdMap;

public class PropertyChangeEventPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PropertyChangeEventPO(new PropertyChangeEvent[]{});
      } else {
          return new PropertyChangeEventPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.replication.util.CreatorCreator.createIdMap(sessionID);
   }
}
