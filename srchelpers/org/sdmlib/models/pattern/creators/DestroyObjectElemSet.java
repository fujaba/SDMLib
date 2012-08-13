package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.PatternObject;

public class DestroyObjectElemSet extends LinkedHashSet<DestroyObjectElem>
{
   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public DestroyObjectElemSet withModifier(String value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public DestroyObjectElemSet withHasMatch(boolean value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public DestroyObjectElemSet withName(String value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public PatternObjectSet getPatternObject()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getPatternObject());
      }
      
      return result;
   }
   public DestroyObjectElemSet withPatternObject(PatternObject value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.withPatternObject(value);
      }
      
      return this;
   }

}

