package org.sdmlib.codegen.util;

import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class SymTabEntryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new SymTabEntryPO(new SymTabEntry[]{});
      } else {
         return new SymTabEntryPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

