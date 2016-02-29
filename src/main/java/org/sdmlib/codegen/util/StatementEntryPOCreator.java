package org.sdmlib.codegen.util;

import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.IdMap;

public class StatementEntryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
     if(reference) {
         return new StatementEntryPO(new StatementEntry[]{});
     } else {
         return new StatementEntryPO();
     }
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

