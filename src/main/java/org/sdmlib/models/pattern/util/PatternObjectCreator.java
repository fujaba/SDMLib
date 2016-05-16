package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.MatchOtherThan;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.IdMap;

public class PatternObjectCreator extends PatternElementCreator
{
   private final String[] properties = new String[]
   {
      PatternElement.PROPERTY_PATTERN,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,

      PatternObject.PROPERTY_CURRENTMATCH,
      PatternObject.PROPERTY_INCOMING,
      PatternObject.PROPERTY_OUTGOING,
      PatternObject.PROPERTY_CANDIDATES,
      PatternObject.PROPERTY_ATTRCONSTRAINTS,
      PatternObject.PROPERTY_DESTROYELEM,
      PatternObject.PROPERTY_CARDCONSTRAINTS,
      PatternObject.PROPERTY_MATCHOTHERTHAN,
      PatternObject.PROPERTY_EXCLUDERS,
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

      if (PatternObject.PROPERTY_INCOMING.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getIncoming();
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

      if (PatternObject.PROPERTY_CARDCONSTRAINTS.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getCardConstraints();
      }

      if (PatternObject.PROPERTY_MATCHOTHERTHAN.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getMatchOtherThan();
      }

      if (PatternObject.PROPERTY_EXCLUDERS.equalsIgnoreCase(attribute))
      {
         return ((PatternObject<?, ?>)target).getExcluders();
      }

      if (PatternObject.PROPERTY_PATTERN.equalsIgnoreCase(attribute))
      {
         return ((PatternObject) target).getPattern();
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
      if (PatternObject.PROPERTY_INCOMING.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToIncoming((PatternLink) value);
         return true;
      }

      if ((PatternObject.PROPERTY_INCOMING + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromIncoming((PatternLink) value);
         return true;
      }

      if (PatternObject.PROPERTY_OUTGOING.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToOutgoing((PatternLink) value);
         return true;
      }

      if ((PatternObject.PROPERTY_OUTGOING + IdMap.REMOVE).equalsIgnoreCase(attrName))
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

      if ((PatternObject.PROPERTY_ATTRCONSTRAINTS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromAttrConstraints((AttributeConstraint) value);
         return true;
      }

      if (PatternObject.PROPERTY_DESTROYELEM.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).setDestroyElem((DestroyObjectElem) value);
         return true;
      }

      if (PatternObject.PROPERTY_CARDCONSTRAINTS.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToCardConstraints((CardinalityConstraint) value);
         return true;
      }

      if ((PatternObject.PROPERTY_CARDCONSTRAINTS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromCardConstraints((CardinalityConstraint) value);
         return true;
      }

      if (PatternObject.PROPERTY_MATCHOTHERTHAN.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToMatchOtherThan((MatchOtherThan) value);
         return true;
      }

      if ((PatternObject.PROPERTY_MATCHOTHERTHAN + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromMatchOtherThan((MatchOtherThan) value);
         return true;
      }

      if (PatternObject.PROPERTY_EXCLUDERS.equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).addToExcluders((MatchOtherThan) value);
         return true;
      }

      if ((PatternObject.PROPERTY_EXCLUDERS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((PatternObject<?, ?>)target).removeFromExcluders((MatchOtherThan) value);
         return true;
      }

      if (PatternObject.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((PatternObject) target).setPattern((Pattern) value);
         return true;
      }

      return super.setValue(target, attrName, value, type);
   }
   
   // ==========================================================================
   
   public static IdMap createIdMap(String sessionID)
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
