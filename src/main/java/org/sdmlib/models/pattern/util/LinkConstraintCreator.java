package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;

import de.uniks.networkparser.IdMap;

public class LinkConstraintCreator extends PatternLinkCreator
{
   private final String[] properties = new String[]
   {
         PatternLink.PROPERTY_SRCROLENAME,
         PatternLink.PROPERTY_TGTROLENAME,
         PatternLink.PROPERTY_TGT,
         PatternLink.PROPERTY_SRC,
         PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT,
         PatternElement.PROPERTY_PATTERN, 
         PatternElement.PROPERTY_MODIFIER,
      LinkConstraint.PROPERTY_HASMATCH,
      LinkConstraint.PROPERTY_PATTERNOBJECTNAME,
      PatternElement.PROPERTY_DOALLMATCHES,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new LinkConstraint();
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((LinkConstraint) entity).removeYou();
   }
}
