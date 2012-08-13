package org.sdmlib.models.objects.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.objects.GenericObject;

public class GenericAttributeSet extends LinkedHashSet<GenericAttribute>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (GenericAttribute obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public GenericAttributeSet withName(String value)
   {
      for (GenericAttribute obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public StringList getValue()
   {
      StringList result = new StringList();
      
      for (GenericAttribute obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public GenericAttributeSet withValue(String value)
   {
      for (GenericAttribute obj : this)
      {
         obj.withValue(value);
      }
      
      return this;
   }

   public GenericObjectSet getOwner()
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericAttribute obj : this)
      {
         result.add(obj.getOwner());
      }
      
      return result;
   }
   public GenericAttributeSet withOwner(GenericObject value)
   {
      for (GenericAttribute obj : this)
      {
         obj.withOwner(value);
      }
      
      return this;
   }

}

