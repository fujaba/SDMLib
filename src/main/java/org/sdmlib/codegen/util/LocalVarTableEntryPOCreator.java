package org.sdmlib.codegen.util;

import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class LocalVarTableEntryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new LocalVarTableEntryPO(new LocalVarTableEntry[]{});
     } else {
         return new LocalVarTableEntryPO();
     }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}
