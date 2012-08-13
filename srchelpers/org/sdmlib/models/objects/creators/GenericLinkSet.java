package org.sdmlib.models.objects.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.GenericGraph;

public class GenericLinkSet extends LinkedHashSet<GenericLink>
{
   public StringList getTgtLabel()
   {
      StringList result = new StringList();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getTgtLabel());
      }
      
      return result;
   }

   public GenericLinkSet withTgtLabel(String value)
   {
      for (GenericLink obj : this)
      {
         obj.withTgtLabel(value);
      }
      
      return this;
   }

   public StringList getSrcLabel()
   {
      StringList result = new StringList();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getSrcLabel());
      }
      
      return result;
   }

   public GenericLinkSet withSrcLabel(String value)
   {
      for (GenericLink obj : this)
      {
         obj.withSrcLabel(value);
      }
      
      return this;
   }

   public GenericObjectSet getSrc()
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }
   public GenericLinkSet withSrc(GenericObject value)
   {
      for (GenericLink obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public GenericObjectSet getTgt()
   {
      GenericObjectSet result = new GenericObjectSet();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getTgt());
      }
      
      return result;
   }
   public GenericLinkSet withTgt(GenericObject value)
   {
      for (GenericLink obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

   public GenericGraphSet getGraph()
   {
      GenericGraphSet result = new GenericGraphSet();
      
      for (GenericLink obj : this)
      {
         result.add(obj.getGraph());
      }
      
      return result;
   }
   public GenericLinkSet withGraph(GenericGraph value)
   {
      for (GenericLink obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

}

