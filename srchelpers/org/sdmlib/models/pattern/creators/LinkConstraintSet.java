package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.booleanList;

public class LinkConstraintSet extends LinkedHashSet<LinkConstraint>
{
   public StringList getTgtRoleName()
   {
      StringList result = new StringList();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.getTgtRoleName());
      }
      
      return result;
   }

   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }

   public LinkConstraintSet withTgtRoleName(String value)
   {
      for (LinkConstraint obj : this)
      {
         obj.withTgtRoleName(value);
      }
      
      return this;
   }

   public LinkConstraintSet withHostGraphSrcObject(Object value)
   {
      for (LinkConstraint obj : this)
      {
         obj.withHostGraphSrcObject(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public LinkConstraintSet withModifier(String value)
   {
      for (LinkConstraint obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public LinkConstraintSet withHasMatch(boolean value)
   {
      for (LinkConstraint obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public LinkConstraintSet withName(String value)
   {
      for (LinkConstraint obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

}





