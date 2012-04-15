package org.sdmlib.codegen.creators;

import org.sdmlib.models.classes.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.codegen.StatementEntry;

public class StatementEntryCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      StatementEntry.PROPERTY_KIND,
      StatementEntry.PROPERTY_TOKENLIST,
      StatementEntry.PROPERTY_ASSIGNTARGETVARNAME,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new StatementEntry();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((StatementEntry) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((StatementEntry) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

