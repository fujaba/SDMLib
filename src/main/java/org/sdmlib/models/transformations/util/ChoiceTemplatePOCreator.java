package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.transformations.ChoiceTemplate;

import de.uniks.networkparser.json.JsonIdMap;

public class ChoiceTemplatePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ChoiceTemplatePO(new ChoiceTemplate[]{});
      } else {
          return new ChoiceTemplatePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
