package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class MatchIsomorphicConstraintCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MatchIsomorphicConstraint.PROPERTY_MODIFIER,
      MatchIsomorphicConstraint.PROPERTY_HASMATCH,
      MatchIsomorphicConstraint.PROPERTY_PATTERNOBJECTNAME,
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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((MatchIsomorphicConstraint) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((MatchIsomorphicConstraint) entity).removeYou();
   }
}


