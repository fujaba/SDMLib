package org.sdmlib.models.classes.creators;

import java.util.LinkedHashSet;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static LinkedHashSet<SendableEntityCreator> creatorSet = null;
   
   public static LinkedHashSet<SendableEntityCreator> getCreatorSet()
   {
      if (creatorSet == null)
      {
         creatorSet = new LinkedHashSet<SendableEntityCreator>();
         creatorSet.add(new org.sdmlib.models.classes.creators.ClassModelCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.ClassModelPOCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.ClazzCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.ClazzPOCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.AttributeCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.AttributePOCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.MethodCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.MethodPOCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.AssociationCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.AssociationPOCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.RoleCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.RolePOCreator());
         creatorSet.add(new org.sdmlib.codegen.creators.SymTabEntryCreator());
         creatorSet.add(new org.sdmlib.codegen.creators.SymTabEntryPOCreator());
         creatorSet.add(new org.sdmlib.codegen.creators.LocalVarTableEntryCreator());
         creatorSet.add(new org.sdmlib.codegen.creators.LocalVarTableEntryPOCreator());
         creatorSet.add(new org.sdmlib.codegen.creators.StatementEntryCreator());
         creatorSet.add(new org.sdmlib.codegen.creators.StatementEntryPOCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.ArrayListCreator());
         creatorSet.add(new org.sdmlib.models.classes.creators.ArrayListPOCreator());
         creatorSet.addAll(org.sdmlib.models.pattern.creators.CreatorCreator.getCreatorSet());
      }
      
      return creatorSet;
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(getCreatorSet());

      return jsonIdMap;
   }
}


