package org.sdmlib.models.objects.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericLink;

public class GenericObjectSet extends LinkedHashSet<GenericObject>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (GenericObject obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public StringList getType()
   {
      StringList result = new StringList();
      
      for (GenericObject obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public GenericAttributeSet getAttrs()
   {
      GenericAttributeSet result = new GenericAttributeSet();
      
      for (GenericObject obj : this)
      {
         result.addAll(obj.getAttrs());
      }
      
      return result;
   }
   public GenericLinkSet getOutgoingLinks()
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericObject obj : this)
      {
         result.addAll(obj.getOutgoingLinks());
      }
      
      return result;
   }
   public GenericLinkSet getIncommingLinks()
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericObject obj : this)
      {
         result.addAll(obj.getIncommingLinks());
      }
      
      return result;
   }
   public GenericObjectSet withName(String value)
   {
      for (GenericObject obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public GenericObjectSet withType(String value)
   {
      for (GenericObject obj : this)
      {
         obj.withType(value);
      }
      
      return this;
   }

   public GenericObjectSet withAttrs(GenericAttribute value)
   {
      for (GenericObject obj : this)
      {
         obj.withAttrs(value);
      }
      
      return this;
   }

   public GenericObjectSet withoutAttrs(GenericAttribute value)
   {
      for (GenericObject obj : this)
      {
         obj.withoutAttrs(value);
      }
      
      return this;
   }

   public GenericObjectSet withOutgoingLinks(GenericLink value)
   {
      for (GenericObject obj : this)
      {
         obj.withOutgoingLinks(value);
      }
      
      return this;
   }

   public GenericObjectSet withoutOutgoingLinks(GenericLink value)
   {
      for (GenericObject obj : this)
      {
         obj.withoutOutgoingLinks(value);
      }
      
      return this;
   }

   public GenericObjectSet withIncommingLinks(GenericLink value)
   {
      for (GenericObject obj : this)
      {
         obj.withIncommingLinks(value);
      }
      
      return this;
   }

   public GenericObjectSet withoutIncommingLinks(GenericLink value)
   {
      for (GenericObject obj : this)
      {
         obj.withoutIncommingLinks(value);
      }
      
      return this;
   }

}


