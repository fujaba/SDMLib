package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;

public class MatchIsomorphicConstraintCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      MatchIsomorphicConstraint.PROPERTY_MODIFIER,
      MatchIsomorphicConstraint.PROPERTY_HASMATCH,
      MatchIsomorphicConstraint.PROPERTY_NAME,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new MatchIsomorphicConstraint();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((MatchIsomorphicConstraint) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((MatchIsomorphicConstraint) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

