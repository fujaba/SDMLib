package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class PatternObjectCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      PatternElement.PROPERTY_PATTERN,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,

      PatternObject.PROPERTY_CURRENTMATCH,
      PatternObject.PROPERTY_INCOMMING,
      PatternObject.PROPERTY_OUTGOING,
      PatternObject.PROPERTY_CANDIDATES,
      PatternObject.PROPERTY_ATTRCONSTRAINTS,
      PatternObject.PROPERTY_DESTROYELEM,
      PatternObject.PROPERTY_PONAME,
      PatternObject.PROPERTY_CARDCONSTRAINTS,
      PatternObject.PROPERTY_MATCHOTHERTHEN,
      PatternObject.PROPERTY_EXCLUDERS
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new PatternObject<Object, Object>();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PatternObject.PROPERTY_CURRENTMATCH.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getCurrentMatch();
      }

      if (PatternObject.PROPERTY_INCOMMING.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getIncomming();
      }
      if (PatternObject.PROPERTY_OUTGOING.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getOutgoing();
      }

      if (PatternObject.PROPERTY_CANDIDATES.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getCandidates();
      }

      if (PatternObject.PROPERTY_ATTRCONSTRAINTS.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getAttrConstraints();
      }

      if (PatternObject.PROPERTY_DESTROYELEM.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getDestroyElem();
      }

      if (PatternObject.PROPERTY_PONAME.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getPoName();
      }

      if (PatternObject.PROPERTY_CARDCONSTRAINTS.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getCardConstraints();
      }

      if (PatternObject.PROPERTY_MATCHOTHERTHEN.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getMatchOtherThen();
      }

      if (PatternObject.PROPERTY_EXCLUDERS.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getExcluders();
      }

      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (PatternObject.PROPERTY_CURRENTMATCH.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).setCurrentMatch((Object) value);
         return true;
      }
      if (PatternObject.PROPERTY_INCOMMING.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToIncomming((PatternLink) value);
         return true;
      }

      if ((PatternObject.PROPERTY_INCOMMING + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromIncomming((PatternLink) value);
         return true;
      }

      if (PatternObject.PROPERTY_OUTGOING.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToOutgoing((PatternLink) value);
         return true;
      }

      if ((PatternObject.PROPERTY_OUTGOING + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromOutgoing((PatternLink) value);
         return true;
      }

      if (PatternObject.PROPERTY_CANDIDATES.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).setCandidates(value);
         return true;
      }

      if (PatternObject.PROPERTY_ATTRCONSTRAINTS.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToAttrConstraints((AttributeConstraint) value);
         return true;
      }

      if ((PatternObject.PROPERTY_ATTRCONSTRAINTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromAttrConstraints((AttributeConstraint) value);
         return true;
      }

      if (PatternObject.PROPERTY_DESTROYELEM.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).setDestroyElem((DestroyObjectElem) value);
         return true;
      }

      if (PatternObject.PROPERTY_PONAME.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).setPoName((String) value);
         return true;
      }

      if (PatternObject.PROPERTY_CARDCONSTRAINTS.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToCardConstraints((CardinalityConstraint) value);
         return true;
      }

      if ((PatternObject.PROPERTY_CARDCONSTRAINTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromCardConstraints((CardinalityConstraint) value);
         return true;
      }

      if (PatternObject.PROPERTY_MATCHOTHERTHEN.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToMatchOtherThen((MatchOtherThen) value);
         return true;
      }

      if ((PatternObject.PROPERTY_MATCHOTHERTHEN + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromMatchOtherThen((MatchOtherThen) value);
         return true;
      }

      if (PatternObject.PROPERTY_EXCLUDERS.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToExcluders((MatchOtherThen) value);
         return true;
      }

      if ((PatternObject.PROPERTY_EXCLUDERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromExcluders((MatchOtherThen) value);
         return true;
      }

      return super.setValue(target, attrName, value, type);
   }
   
   // ==========================================================================
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
 
   //==========================================================================   
   @Override
   public void removeObject(Object entity)
   {
      ((PatternObject<?,?>) entity).removeYou();
   }
}
