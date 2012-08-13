package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.DestroyObjectElem;

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
   public PatternObjectSet withCurrentMatch(Object value)
   {
      for (PatternObject obj : this)
      {
         obj.withCurrentMatch(value);
      }
      
      return this;
   }

   public PatternObjectSet withCandidates(Object value)
   {
      for (PatternObject obj : this)
      {
         obj.withCandidates(value);
      }
      
      return this;
   }

   public PatternObjectSet withIncomming(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withIncomming(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutIncomming(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutIncomming(value);
      }
      
      return this;
   }

   public PatternObjectSet withOutgoing(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withOutgoing(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutOutgoing(PatternLink value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutOutgoing(value);
      }
      
      return this;
   }

   public PatternObjectSet withAttrConstraints(AttributeConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withAttrConstraints(value);
      }
      
      return this;
   }

   public PatternObjectSet withoutAttrConstraints(AttributeConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutAttrConstraints(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public PatternObjectSet withModifier(String value)
   {
      for (PatternObject obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public PatternObjectSet withHasMatch(boolean value)
   {
      for (PatternObject obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public PatternObjectSet withName(String value)
   {
      for (PatternObject obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public DestroyObjectElemSet getDestroyElem()
   {
      DestroyObjectElemSet result = new DestroyObjectElemSet();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getDestroyElem());
      }
      
      return result;
   }
   public PatternObjectSet withDestroyElem(DestroyObjectElem value)
   {
      for (PatternObject obj : this)
      {
         obj.withDestroyElem(value);
      }
      
      return this;
   }

}










