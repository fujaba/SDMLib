package org.sdmlib.models.objects.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.modelsets.StringList;
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

   public StringList getValue()
   {
      StringList result = new StringList();
      
      for (GenericAttribute obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
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
}

