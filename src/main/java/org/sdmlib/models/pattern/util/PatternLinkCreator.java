package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.IdMap;

public class PatternLinkCreator extends PatternElementCreator
{
   private final String[] properties = new String[]
   {
      PatternLink.PROPERTY_SRCROLENAME,
      PatternLink.PROPERTY_TGTROLENAME,
      PatternLink.PROPERTY_TGT,
      PatternLink.PROPERTY_SRC,
      PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT,
      PatternElement.PROPERTY_PATTERN, 
      PatternLink.PROPERTY_MODIFIER,
      PatternLink.PROPERTY_HASMATCH,
      PatternLink.PROPERTY_PATTERNOBJECTNAME,
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
      return new PatternLink();
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

      if (PatternLink.PROPERTY_SRCROLENAME.equalsIgnoreCase(attribute))
      {
         return ((PatternLink)target).getSrcRoleName();
      }

      if (PatternLink.PROPERTY_TGTROLENAME.equalsIgnoreCase(attribute))
      {
         return ((PatternLink)target).getTgtRoleName();
      }

      if (PatternLink.PROPERTY_TGT.equalsIgnoreCase(attribute))
      {
         return ((PatternLink)target).getTgt();
      }

      if (PatternLink.PROPERTY_SRC.equalsIgnoreCase(attribute))
      {
         return ((PatternLink)target).getSrc();
      }

      if (PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attribute))
      {
         return ((PatternLink)target).getHostGraphSrcObject();
      }

      if (PatternLink.PROPERTY_PATTERN.equalsIgnoreCase(attribute))
      {
         return ((PatternLink) target).getPattern();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (PatternLink.PROPERTY_SRCROLENAME.equalsIgnoreCase(attrName))
      {
         ((PatternLink)target).setSrcRoleName((String) value);
         return true;
      }

      if (PatternLink.PROPERTY_TGTROLENAME.equalsIgnoreCase(attrName))
      {
         ((PatternLink)target).setTgtRoleName((String) value);
         return true;
      }

      if (PatternLink.PROPERTY_TGT.equalsIgnoreCase(attrName))
      {
         ((PatternLink)target).setTgt((PatternObject<?, ?>) value);
         return true;
      }

      if (PatternLink.PROPERTY_SRC.equalsIgnoreCase(attrName))
      {
         ((PatternLink)target).setSrc((PatternObject<?, ?>) value);
         return true;
      }

      if (PatternLink.PROPERTY_HOSTGRAPHSRCOBJECT.equalsIgnoreCase(attrName))
      {
         ((PatternLink)target).setHostGraphSrcObject((Object) value);
         return true;
      }

      if (PatternLink.PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         ((PatternLink) target).setPattern((Pattern) value);
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
      ((PatternLink) entity).removeYou();
   }
}
