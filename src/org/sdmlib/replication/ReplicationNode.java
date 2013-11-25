/*
   Copyright (c) 2013 zuendorf 
   
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

import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.replication.creators.SharedSpaceSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class ReplicationNode implements PropertyChangeInterface
{
   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_SHAREDSPACES.equalsIgnoreCase(attrName))
      {
         return getSharedSpaces();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_SHAREDSPACES.equalsIgnoreCase(attrName))
      {
         addToSharedSpaces((SharedSpace) value);
         return true;
      }
      
      if ((PROPERTY_SHAREDSPACES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromSharedSpaces((SharedSpace) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      removeAllFromSharedSpaces();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * ReplicationNode ----------------------------------- SharedSpace
    *              node                   sharedSpaces
    * </pre>
    */
   
   public static final String PROPERTY_SHAREDSPACES = "sharedSpaces";
   
   private SharedSpaceSet sharedSpaces = null;
   
   public synchronized SharedSpace getOrCreateSharedSpace(String spaceId)
   {
      SharedSpace sharedSpace = this.getSharedSpaces().get(spaceId);
      
      if (sharedSpace == null)
      {
         sharedSpace = new SharedSpace().withSpaceId(spaceId);
         this.addToSharedSpaces(sharedSpace);
         sharedSpace.setName("SharedSpace"+getSharedSpaces().size());
         sharedSpace.start();
      }
      
      return sharedSpace;
   }
   
   public SharedSpaceSet getSharedSpaces()
   {
      if (this.sharedSpaces == null)
      {
         return SharedSpace.EMPTY_SET;
      }
   
      return this.sharedSpaces;
   }
   
   public boolean addToSharedSpaces(SharedSpace value)
   {
      SharedSpace oldContent = null;
      
      if (value != null)
      {
         if (this.sharedSpaces == null)
         {
            this.sharedSpaces = new SharedSpaceSet();
         }
         
         oldContent = this.sharedSpaces.put ("" + value.getSpaceId(), value);
         
         if (oldContent != value)
         {
            value.withNode(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SHAREDSPACES, null, value);
         }
      }
         
      return oldContent != value;   
   }
   
   public boolean removeFromSharedSpaces(SharedSpace value)
   {
      SharedSpace oldContent = null;
      
      if ((this.sharedSpaces != null) && (value != null))
      {
         oldContent = this.sharedSpaces.remove ("" + value.getSpaceId());
         
         if (oldContent == value)
         {
            value.setNode(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SHAREDSPACES, value, null);
         }
      }
         
      return oldContent == value;   
   }
   
   public ReplicationNode withSharedSpaces(SharedSpace value)
   {
      addToSharedSpaces(value);
      return this;
   } 
   
   public ReplicationNode withoutSharedSpaces(SharedSpace value)
   {
      removeFromSharedSpaces(value);
      return this;
   } 
   
   public void removeAllFromSharedSpaces()
   {
      LinkedHashSet<SharedSpace> tmpSet = new LinkedHashSet<SharedSpace>(this.getSharedSpaces().values());
   
      for (SharedSpace value : tmpSet)
      {
         this.removeFromSharedSpaces(value);
      }
   }
   
   public SharedSpace createSharedSpaces()
   {
      SharedSpace value = new SharedSpace();
      withSharedSpaces(value);
      return value;
   } 
}

