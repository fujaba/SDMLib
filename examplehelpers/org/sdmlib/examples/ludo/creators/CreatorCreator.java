package org.sdmlib.examples.ludo.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.examples.ludo.creators.LudoCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.ludo.creators.PlayerCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.ludo.creators.DiceCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.ludo.creators.FieldCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.ludo.creators.PawnCreator());

      jsonIdMap.addCreator(new org.sdmlib.examples.ludo.creators.PawnPOCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.ludo.creators.PlayerPOCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.ludo.creators.DicePOCreator());
      jsonIdMap.addCreator(new org.sdmlib.examples.ludo.creators.FieldPOCreator());
      
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.PatternCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.PatternLinkCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.AttributeConstraintCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.LinkConstraintCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.NegativeApplicationConditionCreator());
      

      return jsonIdMap;
   }
   
   private static JsonIdMap staticMap = null;
   
   public static JsonIdMap getIdMap() 
   {
      if (staticMap == null)
      {
         staticMap = createIdMap("p");
      }
      
      return staticMap;
   }
}

