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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.util.BoardTaskSet;
import org.sdmlib.replication.util.ObjectSet;
import org.sdmlib.replication.util.SeppelScopeSet;
import org.sdmlib.replication.util.SeppelSpaceProxySet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.BoardTask;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/replication/ReplicationModel.java'>ReplicationModel.java</a>
 * @see org.sdmlib.test.replication.ReplicationModel#testSeppelModel
 */
   public class SeppelSpaceProxy implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutObservedObjects(this.getObservedObjects().toArray(new Object[this.getObservedObjects().size()]));
      withoutPartners(this.getPartners().toArray(new SeppelSpaceProxy[this.getPartners().size()]));
      setChannel(null);
      withoutScopes(this.getScopes().toArray(new SeppelScope[this.getScopes().size()]));
      withoutTasks(this.getTasks().toArray(new BoardTask[this.getTasks().size()]));
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
   
   public SeppelSpaceProxy withSpaceId(String value)
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
      result.append(" ").append(this.getPortNo());
      result.append(" ").append(this.getLoginName());
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
   
   public SeppelSpaceProxy withAcceptsConnectionRequests(boolean value)
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
   
   public SeppelSpaceProxy withHostName(String value)
   {
      setHostName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PORTNO = "portNo";
   
   private int portNo;

   public int getPortNo()
   {
      return this.portNo;
   }
   
   public void setPortNo(int value)
   {
      if (this.portNo != value)
      {
         int oldValue = this.portNo;
         this.portNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PORTNO, oldValue, value);
      }
   }
   
   public SeppelSpaceProxy withPortNo(int value)
   {
      setPortNo(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * SeppelSpaceProxy ----------------------------------- Object
    *                                 observedObjects
    * </pre>
    */
   
   public static final String PROPERTY_OBSERVEDOBJECTS = "observedObjects";

   private ObjectSet observedObjects = null;
   
   public ObjectSet getObservedObjects()
   {
      if (this.observedObjects == null)
      {
         return ObjectSet.EMPTY_SET;
      }
   
      return this.observedObjects;
   }

   public SeppelSpaceProxy withObservedObjects(Object... value)
   {
      if(value==null){
         return this;
      }
      for (Object item : value)
      {
         if (item != null)
         {
            if (this.observedObjects == null)
            {
               this.observedObjects = new ObjectSet();
            }
            
            boolean changed = this.observedObjects.add (item);

            if (changed)
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_OBSERVEDOBJECTS, null, item);
            }
         }
      }
      return this;
   } 

   public SeppelSpaceProxy withoutObservedObjects(Object... value)
   {
      for (Object item : value)
      {
         if ((this.observedObjects != null) && (item != null))
         {
            if (this.observedObjects.remove(item))
            {
               getPropertyChangeSupport().firePropertyChange(PROPERTY_OBSERVEDOBJECTS, item, null);
            }
         }
      }
      return this;
   }

   public Object createObservedObjects()
   {
      Object value = new Object();
      withObservedObjects(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * SeppelSpaceProxy ----------------------------------- SeppelSpaceProxy
    *              partners                   partners
    * </pre>
    */
   
   public static final String PROPERTY_PARTNERS = "partners";

   private SeppelSpaceProxySet partners = null;
   
   public SeppelSpaceProxySet getPartners()
   {
      if (this.partners == null)
      {
         return SeppelSpaceProxySet.EMPTY_SET;
      }
   
      return this.partners;
   }
   
   public SeppelSpaceProxySet getPartnersTransitive()
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet().with(this);
      return result.getPartnersTransitive();
   }

   public SeppelSpaceProxy getOrCreatePartners(String partnerName)
   {
      for (SeppelSpaceProxy proxy : this.getPartners())
      {
         if (proxy.getSpaceId().equals(partnerName))
         {
            return proxy;
         }
      }
      
      SeppelSpaceProxy proxy = new SeppelSpaceProxy().withSpaceId(partnerName).withPartners(this);
      
      return proxy;
   } 


   public SeppelSpaceProxy withPartners(SeppelSpaceProxy... value)
   {
      if(value==null){
         return this;
      }
      for (SeppelSpaceProxy item : value)
      {
         if (item != null)
         {
            if (this.partners == null)
            {
               this.partners = new SeppelSpaceProxySet();
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

   public SeppelSpaceProxy withoutPartners(SeppelSpaceProxy... value)
   {
      for (SeppelSpaceProxy item : value)
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

   public SeppelSpaceProxy createPartners()
   {
      SeppelSpaceProxy value = new SeppelSpaceProxy();
      withPartners(value);
      return value;
   } 

   

   
   /********************************************************************
    * <pre>
    *              one                       one
    * SeppelSpaceProxy ----------------------------------- SeppelChannel
    *              seppelSpaceProxy                   channel
    * </pre>
    */
   
   public static final String PROPERTY_CHANNEL = "channel";

   private SeppelChannel channel = null;

   public SeppelChannel getChannel()
   {
      return this.channel;
   }

   public SeppelChannel getOrCreateChannel()
   {
      if (this.channel == null)
      {
         SeppelChannel newChannel = new SeppelChannel(this.hostName, this.portNo);
         this.withChannel(newChannel);
      }
      return this.channel;
   }

   public boolean setChannel(SeppelChannel value)
   {
      boolean changed = false;
      
      if (this.channel != value)
      {
         SeppelChannel oldValue = this.channel;
         
         if (this.channel != null)
         {
            this.channel = null;
            oldValue.setSeppelSpaceProxy(null);
         }
         
         this.channel = value;
         
         if (value != null)
         {
            value.withSeppelSpaceProxy(this);
         }
         
         // do not send an event in order not to be part of the replication changes
         // getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNEL, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public SeppelSpaceProxy withChannel(SeppelChannel value)
   {
      setChannel(value);
      return this;
   } 

   public SeppelChannel createChannel()
   {
      SeppelChannel value = new SeppelChannel(this.getHostName(), this.getPortNo());
      this.withChannel(value);
      return value;
   }


   
   /********************************************************************
    * <pre>
    *              many                       many
    * SeppelSpaceProxy ----------------------------------- SeppelScope
    *              spaces                   scopes
    * </pre>
    */
   
   public static final String PROPERTY_SCOPES = "scopes";

   private SeppelScopeSet scopes = null;
   
   public SeppelScopeSet getScopes()
   {
      if (this.scopes == null)
      {
         return SeppelScopeSet.EMPTY_SET;
      }
   
      return this.scopes;
   }

   public SeppelSpaceProxy withScopes(SeppelScope... value)
   {
      if(value==null){
         return this;
      }
      for (SeppelScope item : value)
      {
         if (item != null)
         {
            if (this.scopes == null)
            {
               this.scopes = new SeppelScopeSet();
            }
            
            boolean changed = this.scopes.add (item);

            if (changed)
            {
               item.withSpaces(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SCOPES, null, item);
            }
         }
      }
      return this;
   } 

   public SeppelSpaceProxy withoutScopes(SeppelScope... value)
   {
      for (SeppelScope item : value)
      {
         if ((this.scopes != null) && (item != null))
         {
            if (this.scopes.remove(item))
            {
               item.withoutSpaces(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SCOPES, item, null);
            }
         }
      }
      return this;
   }

   public SeppelScope createScopes()
   {
      SeppelScope value = new SeppelScope();
      withScopes(value);
      return value;
   } 


   
   //==========================================================================
   
   public static final String PROPERTY_LOGINNAME = "loginName";
   
   private String loginName;

   public String getLoginName()
   {
      return this.loginName;
   }
   
   public void setLoginName(String value)
   {
      if ( ! StrUtil.stringEquals(this.loginName, value))
      {
         String oldValue = this.loginName;
         this.loginName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGINNAME, oldValue, value);
      }
   }
   
   public SeppelSpaceProxy withLoginName(String value)
   {
      setLoginName(value);
      return this;
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
   
   public SeppelSpaceProxy withPassword(String value)
   {
      setPassword(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * SeppelSpaceProxy ----------------------------------- BoardTask
    *              proxy                   tasks
    * </pre>
    */
   
   public static final String PROPERTY_TASKS = "tasks";

   private BoardTaskSet tasks = null;
   
   public BoardTaskSet getTasks()
   {
      if (this.tasks == null)
      {
         return BoardTaskSet.EMPTY_SET;
      }
   
      return this.tasks;
   }

   public SeppelSpaceProxy withTasks(BoardTask... value)
   {
      if(value==null){
         return this;
      }
      for (BoardTask item : value)
      {
         if (item != null)
         {
            if (this.tasks == null)
            {
               this.tasks = new BoardTaskSet();
            }
            
            boolean changed = this.tasks.add (item);

            if (changed)
            {
               item.withProxy(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKS, null, item);
            }
         }
      }
      return this;
   } 

   public SeppelSpaceProxy withoutTasks(BoardTask... value)
   {
      for (BoardTask item : value)
      {
         if ((this.tasks != null) && (item != null))
         {
            if (this.tasks.remove(item))
            {
               item.setProxy(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKS, item, null);
            }
         }
      }
      return this;
   }

   public BoardTask createTasks()
   {
      BoardTask value = new BoardTask();
      withTasks(value);
      return value;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
