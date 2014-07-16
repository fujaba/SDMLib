package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.storyboards.KanbanEntry;

import de.uniks.networkparser.json.JsonIdMap;

public class KanbanEntryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) 
      {
         return new KanbanEntryPO(new KanbanEntry[]{});
      } else {
         return new KanbanEntryPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

