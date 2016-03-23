package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.IdMap;

public class CardinalityConstraintCreator extends PatternElementCreator
{
   private final String[] properties = new String[]
   {
      CardinalityConstraint.PROPERTY_TGTROLENAME,
      CardinalityConstraint.PROPERTY_HOSTGRAPHSRCOBJECT,
      CardinalityConstraint.PROPERTY_MINCARD,
      CardinalityConstraint.PROPERTY_MAXCARD,
      PatternElement.PROPERTY_MODIFIER,
      PatternElement.PROPERTY_HASMATCH,
      PatternElement.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
      PatternElement.PROPERTY_PATTERN,
      CardinalityConstraint.PROPERTY_SRC,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new CardinalityConstraint();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (CardinalityConstraint.PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         return ((CardinalityConstraint)target).getTgtRoleName();
      }

      if (CardinalityConstraint.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         return ((CardinalityConstraint)target).getHostGraphSrcObject();
      }

      if (CardinalityConstraint.PROPERTY_MINCARD.equalsIgnoreCase(attrName))
      {
         return ((CardinalityConstraint)target).getMinCard();
      }

      if (CardinalityConstraint.PROPERTY_MAXCARD.equalsIgnoreCase(attrName))
      {
         return ((CardinalityConstraint)target).getMaxCard();
      }

      if (CardinalityConstraint.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         return ((CardinalityConstraint)target).getSrc();
      }

      if (CardinalityConstraint.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return ((CardinalityConstraint) target).getPattern();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (CardinalityConstraint.PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         ((CardinalityConstraint)target).setTgtRoleName((String) value);
         return true;
      }

      if (CardinalityConstraint.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         ((CardinalityConstraint)target).setHostGraphSrcObject((Object) value);
         return true;
      }

      if (CardinalityConstraint.PROPERTY_MINCARD.equalsIgnoreCase(attrName))
      {
         ((CardinalityConstraint)target).setMinCard(Long.parseLong(value.toString()));
         return true;
      }

      if (CardinalityConstraint.PROPERTY_MAXCARD.equalsIgnoreCase(attrName))
      {
         ((CardinalityConstraint)target).setMaxCard(Long.parseLong(value.toString()));
         return true;
      }

      if (CardinalityConstraint.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((CardinalityConstraint)target).setSrc((PatternObject<?,?>) value);
         return true;
      }

      if (CardinalityConstraint.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((CardinalityConstraint) target).setPattern((Pattern<?>) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((CardinalityConstraint) entity).removeYou();
   }
}

