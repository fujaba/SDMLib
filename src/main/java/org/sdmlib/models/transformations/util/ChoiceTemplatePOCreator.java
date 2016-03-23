package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.transformations.ChoiceTemplate;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.transformations.util.CreatorCreator.createIdMap(sessionID);
   }
}
