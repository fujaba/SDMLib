package org.sdmlib.models.transformations.creators;

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
         creatorSet.add(new org.sdmlib.models.transformations.creators.TransformOpCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.TransformOpPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.OperationObjectCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.OperationObjectPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.AttributeOpCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.AttributeOpPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.LinkOpCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.LinkOpPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.StatementCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.StatementPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.TemplateCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.TemplatePOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.PlaceHolderDescriptionCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.PlaceHolderDescriptionPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.ObjectCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.ObjectPOCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.ChoiceTemplateCreator());
         creatorSet.add(new org.sdmlib.models.transformations.creators.ChoiceTemplatePOCreator());
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



