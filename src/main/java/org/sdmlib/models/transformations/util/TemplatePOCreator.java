package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.transformations.Template;

import de.uniks.networkparser.json.JsonIdMap;

public class TemplatePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TemplatePO(new Template[]{});
      } else {
          return new TemplatePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
