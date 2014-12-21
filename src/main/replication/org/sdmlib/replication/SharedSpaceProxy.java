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
   
package org.sdmlib.replication;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import java.util.LinkedHashSet;
import org.sdmlib.replication.util.SharedSpaceProxySet;

public class SharedSpaceProxy implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setChannel(null);
      withoutPartners(this.getPartners().toArray(new SharedSpaceProxy[this.getPartners().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_SPACEID = "spaceId";
   
   private String spaceId;

   public String getSpaceId()
   {
      return this.spaceId;
   }
   
   public void setSpaceId(String value)
   {
      if ( ! StrUtil.stringEquals(this.spaceId, value))
      {
         String oldValue = this.spaceId;
         this.spaceId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SPACEID, oldValue, value);
      }
   }
   
   public SharedSpaceProxy withSpaceId(String value)
   {
      setSpaceId(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getSpaceId());
      result.append(" ").append(this.getHostName());
      result.append(" ").append(this.getPassword());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_ACCEPTSCONNECTIONREQUESTS = "acceptsConnectionRequests";
   
   private boolean acceptsConnectionRequests;

   public boolean isAcceptsConnectionRequests()
   {
      return this.acceptsConnectionRequests;
   }
   
   public void setAcceptsConnectionRequests(boolean value)
   {
      if (this.acceptsConnectionRequests != value)
      {
         boolean oldValue = this.acceptsConnectionRequests;
         this.acceptsConnectionRequests = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ACCEPTSCONNECTIONREQUESTS, oldValue, value);
      }
   }
   
   public SharedSpaceProxy withAcceptsConnectionRequests(boolean value)
   {
      setAcceptsConnectionRequests(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_HOSTNAME = "hostName";
   
   private String hostName;

   public String getHostName()
   {
      return this.hostName;
   }
   
   public void setHostName(String value)
   {
      if ( ! StrUtil.stringEquals(this.hostName, value))
      {
         String oldValue = this.hostName;
         this.hostName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOSTNAME, oldValue, value);
      }
   }
   
   public SharedSpaceProxy withHostName(String value)
   {
      setHostName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PORTNO = "portNo";
   
   private long portNo;

   public long getPortNo()
   {
      return this.portNo;
   }
   
   public void setPortNo(long value)
   {
      if (this.portNo != value)
      {
         long oldValue = this.portNo;
         this.portNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PORTNO, oldValue, value);
      }
   }
   
   public SharedSpaceProxy withPortNo(long value)
   {
      setPortNo(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * SharedSpaceProxy ----------------------------------- ReplicationChannel
    *              sharedSpaceProxy                   channel
    * </pre>
    */
   
   public static final String PROPERTY_CHANNEL = "channel";

   private ReplicationChannel channel = null;

   public ReplicationChannel getChannel()
   {
      return this.channel;
   }

   public boolean setChannel(ReplicationChannel value)
   {
      boolean changed = false;
      
      if (this.channel != value)
      {
         ReplicationChannel oldValue = this.channel;
         
         if (this.channel != null)
         {
            this.channel = null;
            oldValue.setSharedSpaceProxy(null);
         }
         
         this.channel = value;
         
         if (value != null)
         {
            value.withSharedSpaceProxy(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNEL, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public SharedSpaceProxy withChannel(ReplicationChannel value)
   {
      setChannel(value);
      return this;
   } 

   public ReplicationChannel createChannel()
   {
      ReplicationChannel value = new ReplicationChannel();
      withChannel(value);
      return value;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PASSWORD = "password";
   
   private String password;

   public String getPassword()
   {
      return this.password;
   }
   
   public void setPassword(String value)
   {
      if ( ! StrUtil.stringEquals(this.password, value))
      {
         String oldValue = this.password;
         this.password = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PASSWORD, oldValue, value);
      }
   }
   
   public SharedSpaceProxy withPassword(String value)
   {
      setPassword(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * SharedSpaceProxy ----------------------------------- SharedSpaceProxy
    *              partners                   partners
    * </pre>
    */
   
   public static final String PROPERTY_PARTNERS = "partners";

   private SharedSpaceProxySet partners = null;
   
   public SharedSpaceProxySet getPartners()
   {
      if (this.partners == null)
      {
         return SharedSpaceProxy.EMPTY_SET;
      }
   
      return this.partners;
   }
   public SharedSpaceProxySet getPartnersTransitive()
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet().with(this);
      return result.getPartnersTransitive();
   }


   public SharedSpaceProxy withPartners(SharedSpaceProxy... value)
   {
      if(value==null){
         return this;
      }
      for (SharedSpaceProxy item : value)
      {
         if (item != null)
         {
            if (this.partners == null)
            {
               this.partners = new SharedSpaceProxySet();
            }
            
            boolean changed = this.partners.add (item);

            if (changed)
            {
               item.withPartners(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PARTNERS, null, item);
            }
         }
      }
      return this;
   } 

   public SharedSpaceProxy withoutPartners(SharedSpaceProxy... value)
   {
      for (SharedSpaceProxy item : value)
      {
         if ((this.partners != null) && (item != null))
         {
            if (this.partners.remove(item))
            {
               item.withoutPartners(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PARTNERS, item, null);
            }
         }
      }
      return this;
   }

   public SharedSpaceProxy createPartners()
   {
      SharedSpaceProxy value = new SharedSpaceProxy();
      withPartners(value);
      return value;
   } 

   
   public static final SharedSpaceProxySet EMPTY_SET = new SharedSpaceProxySet().withReadonly(true);
}
