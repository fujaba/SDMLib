package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class AnnotationPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AnnotationPO(new Annotation[]{});
      } else {
          return new AnnotationPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.models.classes.util.CreatorCreator.createIdMap(sessionID);
   }
}
