package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.AttributeConstraint;

public class PatternObjectSet extends LinkedHashSet<PatternObject>
{
   public LinkedHashSet<Object> getCurrentMatch()
   {
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getCurrentMatch());
      }
      
      return result;
   }

   public PatternLinkSet getIncomming()
   {
      PatternLinkSet result = new PatternLinkSet();
      
      for (PatternObject obj : this)
      {
         result.addAll(obj.getIncomming());
      }
      
      return result;
   }
   public PatternLinkSet getOutgoing()
   {
      PatternLinkSet result = new PatternLinkSet();
      
      for (PatternObject obj : this)
      {
         result.addAll(obj.getOutgoing());
      }
      
      return result;
   }
   public LinkedHashSet<Object> getCandidates()
   {
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getCandidates());
      }
      
      return result;
   }

   public AttributeConstraintSet getAttrConstraints()
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (PatternObject obj : this)
      {
         result.addAll(obj.getAttrConstraints());
      }
      
      return result;
   }
}





