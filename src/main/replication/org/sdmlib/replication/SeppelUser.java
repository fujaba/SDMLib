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
import org.sdmlib.replication.util.SeppelSpaceProxySet;
import org.sdmlib.replication.util.SeppelScopeSet;

public class SeppelUser implements PropertyChangeInterface
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
      withoutSpaces(this.getSpaces().toArray(new SeppelSpaceProxy[this.getSpaces().size()]));
      withoutScopes(this.getScopes().toArray(new SeppelScope[this.getScopes().size()]));
      setMasterSpace(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
   
   public SeppelUser withLoginName(String value)
   {
      setLoginName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getLoginName());
      result.append(" ").append(this.getPassword());
      return result.substring(1);
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
   
   public SeppelUser withPassword(String value)
   {
      setPassword(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * SeppelUser ----------------------------------- SeppelSpaceProxy
    *              user                   spaces
    * </pre>
    */
   
   public static final String PROPERTY_SPACES = "spaces";

   private SeppelSpaceProxySet spaces = null;
   
   public SeppelSpaceProxySet getSpaces()
   {
      if (this.spaces == null)
      {
         return SeppelSpaceProxySet.EMPTY_SET;
      }
   
      return this.spaces;
   }
   
   public SeppelSpaceProxy getOrCreateSpaces(String spaceId)
   {
      SeppelSpaceProxy theSpace = this.getSpaces().hasSpaceId(spaceId).first();
      
      if (theSpace == null)
      {
         theSpace = this.createSpaces().withSpaceId(spaceId);
      }
      
      return theSpace;
   } 


   public SeppelUser withSpaces(SeppelSpaceProxy... value)
   {
      if(value==null){
         return this;
      }
      for (SeppelSpaceProxy item : value)
      {
         if (item != null)
         {
            if (this.spaces == null)
            {
               this.spaces = new SeppelSpaceProxySet();
            }
            
            boolean changed = this.spaces.add (item);

            if (changed)
            {
               item.withUser(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SPACES, null, item);
            }
         }
      }
      return this;
   } 

   public SeppelUser withoutSpaces(SeppelSpaceProxy... value)
   {
      for (SeppelSpaceProxy item : value)
      {
         if ((this.spaces != null) && (item != null))
         {
            if (this.spaces.remove(item))
            {
               item.setUser(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SPACES, item, null);
            }
         }
      }
      return this;
   }

   public SeppelSpaceProxy createSpaces()
   {
      SeppelSpaceProxy value = new SeppelSpaceProxy();
      withSpaces(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * SeppelUser ----------------------------------- SeppelScope
    *              users                   scopes
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

   public SeppelUser withScopes(SeppelScope... value)
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
               item.withUsers(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SCOPES, null, item);
            }
         }
      }
      return this;
   } 

   public SeppelUser withoutScopes(SeppelScope... value)
   {
      for (SeppelScope item : value)
      {
         if ((this.scopes != null) && (item != null))
         {
            if (this.scopes.remove(item))
            {
               item.withoutUsers(this);
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

   
   /********************************************************************
    * <pre>
    *              many                       one
    * SeppelUser ----------------------------------- SeppelSpaceProxy
    *              knownUsers                   masterSpace
    * </pre>
    */
   
   public static final String PROPERTY_MASTERSPACE = "masterSpace";

   private SeppelSpaceProxy masterSpace = null;

   public SeppelSpaceProxy getMasterSpace()
   {
      return this.masterSpace;
   }

   public boolean setMasterSpace(SeppelSpaceProxy value)
   {
      boolean changed = false;
      
      if (this.masterSpace != value)
      {
         SeppelSpaceProxy oldValue = this.masterSpace;
         
         if (this.masterSpace != null)
         {
            this.masterSpace = null;
            oldValue.withoutKnownUsers(this);
         }
         
         this.masterSpace = value;
         
         if (value != null)
         {
            value.withKnownUsers(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MASTERSPACE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public SeppelUser withMasterSpace(SeppelSpaceProxy value)
   {
      setMasterSpace(value);
      return this;
   } 

   public SeppelSpaceProxy createMasterSpace()
   {
      SeppelSpaceProxy value = new SeppelSpaceProxy();
      withMasterSpace(value);
      return value;
   }

}
