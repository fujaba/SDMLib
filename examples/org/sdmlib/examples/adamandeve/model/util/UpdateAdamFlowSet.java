/*
   Copyright (c) 2014 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.adamandeve.model.UpdateAdamFlow;
import java.util.Collection;
import java.util.LinkedHashSet;
import org.sdmlib.examples.adamandeve.model.util.PeerProxySet;
import org.sdmlib.logger.PeerProxy;
import org.sdmlib.examples.adamandeve.model.util.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.models.modelsets.intList;

public class UpdateAdamFlowSet extends SDMSet<UpdateAdamFlow>
{


   public UpdateAdamFlowPO hasUpdateAdamFlowPO()
   {
      return new UpdateAdamFlowPO(this.toArray(new UpdateAdamFlow[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.adamandeve.model.UpdateAdamFlow";
   }


   @SuppressWarnings("unchecked")
   public UpdateAdamFlowSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<UpdateAdamFlow>)value);
      }
      else if (value != null)
      {
         this.add((UpdateAdamFlow) value);
      }
      
      return this;
   }
   
   public UpdateAdamFlowSet without(UpdateAdamFlow value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public UpdateAdamFlowSet run()
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   
   //==========================================================================
   
   public LinkedHashSet<Object> getTaskNames()
   {
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getTaskNames());
      }
      return result;
   }

   public PeerProxySet getAdam()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getAdam());
      }
      
      return result;
   }

   public UpdateAdamFlowSet hasAdam(org.sdmlib.logger.PeerProxy value)
   {
      UpdateAdamFlowSet result = new UpdateAdamFlowSet();
      
      for (UpdateAdamFlow obj : this)
      {
         if (value == obj.getAdam())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UpdateAdamFlowSet withAdam(PeerProxy value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.setAdam(value);
      }
      
      return this;
   }

   public PeerProxySet getEve()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getEve());
      }
      
      return result;
   }

   public UpdateAdamFlowSet hasEve(org.sdmlib.logger.PeerProxy value)
   {
      UpdateAdamFlowSet result = new UpdateAdamFlowSet();
      
      for (UpdateAdamFlow obj : this)
      {
         if (value == obj.getEve())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UpdateAdamFlowSet withEve(PeerProxy value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.setEve(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public UpdateAdamFlowSet hasIdMap(org.sdmlib.serialization.SDMLibJsonIdMap value)
   {
      UpdateAdamFlowSet result = new UpdateAdamFlowSet();
      
      for (UpdateAdamFlow obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UpdateAdamFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.setIdMap(value);
      }
      
      return this;
   }

   public longList getAdamJarAtEveSiteLastModified()
   {
      longList result = new longList();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getAdamJarAtEveSiteLastModified());
      }
      
      return result;
   }

   public UpdateAdamFlowSet hasAdamJarAtEveSiteLastModified(long value)
   {
      UpdateAdamFlowSet result = new UpdateAdamFlowSet();
      
      for (UpdateAdamFlow obj : this)
      {
         if (value == obj.getAdamJarAtEveSiteLastModified())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UpdateAdamFlowSet withAdamJarAtEveSiteLastModified(long value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.setAdamJarAtEveSiteLastModified(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public UpdateAdamFlowSet hasTaskNo(int value)
   {
      UpdateAdamFlowSet result = new UpdateAdamFlowSet();
      
      for (UpdateAdamFlow obj : this)
      {
         if (value == obj.getTaskNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public UpdateAdamFlowSet withTaskNo(int value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.setTaskNo(value);
      }
      
      return this;
   }

}
