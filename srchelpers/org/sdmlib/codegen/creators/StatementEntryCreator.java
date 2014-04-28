package org.sdmlib.codegen.creators;

import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.models.classes.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class StatementEntryCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { StatementEntry.PROPERTY_KIND, StatementEntry.PROPERTY_TOKENLIST,
         StatementEntry.PROPERTY_ASSIGNTARGETVARNAME,
         StatementEntry.PROPERTY_BODYSTATS, StatementEntry.PROPERTY_PARENT,
         StatementEntry.PROPERTY_STARTPOS, StatementEntry.PROPERTY_ENDPOS, };

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

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((StatementEntry) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   // ==========================================================================

   @Override
   public void removeObject(Object entity)
   {
      ((StatementEntry) entity).removeYou();
   }
}
