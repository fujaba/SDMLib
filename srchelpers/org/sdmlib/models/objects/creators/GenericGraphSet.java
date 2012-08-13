package org.sdmlib.models.objects.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.GenericLink;

public class GenericGraphSet extends LinkedHashSet<GenericGraph>
{
   public GenericObjectSet getObjects()
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericGraph obj : this)
      {
         result.addAll(obj.getObjects());
      }
      
      return result;
   }
   public GenericGraphSet withObjects(GenericObject value)
   {
      for (GenericGraph obj : this)
      {
         obj.withObjects(value);
      }
      
      return this;
   }

   public GenericGraphSet withoutObjects(GenericObject value)
   {
      for (GenericGraph obj : this)
      {
         obj.withoutObjects(value);
      }
      
      return this;
   }

   public GenericLinkSet getLinks()
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericGraph obj : this)
      {
         result.addAll(obj.getLinks());
      }
      
      return result;
   }
   public GenericGraphSet withLinks(GenericLink value)
   {
      for (GenericGraph obj : this)
      {
         obj.withLinks(value);
      }
      
      return this;
   }

   public GenericGraphSet withoutLinks(GenericLink value)
   {
      for (GenericGraph obj : this)
      {
         obj.withoutLinks(value);
      }
      
      return this;
   }

}

