package org.sdmlib.models.objects.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.objects.GenericGraph;
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

   public GenericObjectSet withName(String value)
   {
      for (GenericObject obj : this)
      {
         obj.withName(value);
      }
      
      return this;
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

   public GenericObjectSet withType(String value)
   {
      for (GenericObject obj : this)
      {
         obj.withType(value);
      }
      
      return this;
   }

   public GenericGraphSet getGraph()
   {
      GenericGraphSet result = new GenericGraphSet();
      
      for (GenericObject obj : this)
      {
         result.add(obj.getGraph());
      }
      
      return result;
   }
   public GenericObjectSet withGraph(GenericGraph value)
   {
      for (GenericObject obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
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

   public GenericLinkSet getOutgoingLinks()
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericObject obj : this)
      {
         result.addAll(obj.getOutgoingLinks());
      }
      
      return result;
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

   public GenericLinkSet getIncommingLinks()
   {
      GenericLinkSet result = new GenericLinkSet();
      
      for (GenericObject obj : this)
      {
         result.addAll(obj.getIncommingLinks());
      }
      
      return result;
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

   public StringList getIcon()
   {
      StringList result = new StringList();
      
      for (GenericObject obj : this)
      {
         result.add(obj.getIcon());
      }
      
      return result;
   }

   public GenericObjectSet withIcon(String value)
   {
      for (GenericObject obj : this)
      {
         obj.withIcon(value);
      }
      
      return this;
   }

   public GenericObjectSet with(GenericObject... genObjects)
   {
      for (GenericObject genObj : genObjects)
      {
         this.add(genObj);
      }
      
      return this;
   }


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (GenericObject elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }




   public String getEntryType()
   {
      return "org.sdmlib.models.objects.GenericObject";
   }


   public GenericObjectSet with(GenericObject value)
   {
      this.add(value);
      return this;
   }
   
   public GenericObjectSet without(GenericObject value)
   {
      this.remove(value);
      return this;
   }
}








