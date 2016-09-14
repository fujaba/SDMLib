/*
   Copyright (c) 2014 christian 
   
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
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.util.ReplicationRootSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
* @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
* @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/replication/ReplicationModel.java'>ReplicationModel.java</a>
 */
   public class ReplicationRoot implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_APPLICATIONOBJECT.equalsIgnoreCase(attrName))
      {
         return getApplicationObject();
      }

      if (PROPERTY_KIDS.equalsIgnoreCase(attrName))
      {
         return getKids();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
         return true;
      }

      if (PROPERTY_APPLICATIONOBJECT.equalsIgnoreCase(attrName))
      {
         setApplicationObject((Object) value);
         return true;
      }

      if (PROPERTY_KIDS.equalsIgnoreCase(attrName))
      {
         addToKids((ReplicationRoot) value);
         return true;
      }
      
      if ((PROPERTY_KIDS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromKids((ReplicationRoot) value);
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((ReplicationRoot) value);
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
      removeAllFromKids();
      setParent(null);
      withoutKids(this.getKids().toArray(new ReplicationRoot[this.getKids().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public ReplicationRoot withName(String value)
   {
      setName(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getName());
      return s.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_APPLICATIONOBJECT = "applicationObject";
   
   private Object applicationObject;

   public Object getApplicationObject()
   {
      return this.applicationObject;
   }
   
   public void setApplicationObject(Object value)
   {
      if (this.applicationObject != value)
      {
         Object oldValue = this.applicationObject;
         this.applicationObject = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_APPLICATIONOBJECT, oldValue, value);
      }
   }
   
   public ReplicationRoot withApplicationObject(Object value)
   {
      setApplicationObject(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * ReplicationRoot ----------------------------------- ReplicationRoot
    *              parent                   kids
    * </pre>
    */
   
   public static final String PROPERTY_KIDS = "kids";

   private ReplicationRootSet kids = null;
   
   public ReplicationRootSet getKids()
   {
      if (this.kids == null)
      {
         return ReplicationRoot.EMPTY_SET;
      }
   
      return this.kids;
   }
   public ReplicationRootSet getKidsTransitive()
   {
      ReplicationRootSet result = new ReplicationRootSet().with(this);
      return result.getKidsTransitive();
   }


   public boolean addToKids(ReplicationRoot value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.kids == null)
         {
            this.kids = new ReplicationRootSet();
         }
         
         changed = this.kids.add (value);
         
         if (changed)
         {
            value.withParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromKids(ReplicationRoot value)
   {
      boolean changed = false;
      
      if ((this.kids != null) && (value != null))
      {
         changed = this.kids.remove (value);
         
         if (changed)
         {
            value.setParent(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDS, value, null);
         }
      }
         
      return changed;   
   }

   public ReplicationRoot withKids(ReplicationRoot... value)
   {
      for (ReplicationRoot item : value)
      {
         addToKids(item);
      }
      return this;
   } 

   public ReplicationRoot withoutKids(ReplicationRoot... value)
   {
      for (ReplicationRoot item : value)
      {
         removeFromKids(item);
      }
      return this;
   }

   public void removeAllFromKids()
   {
      LinkedHashSet<ReplicationRoot> tmpSet = new LinkedHashSet<ReplicationRoot>(this.getKids());
   
      for (ReplicationRoot value : tmpSet)
      {
         this.removeFromKids(value);
      }
   }

   public ReplicationRoot createKids()
   {
      ReplicationRoot value = new ReplicationRoot();
      withKids(value);
      return value;
   } 

   
   public static final ReplicationRootSet EMPTY_SET = new ReplicationRootSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * ReplicationRoot ----------------------------------- ReplicationRoot
    *              kids                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";

   private ReplicationRoot parent = null;

   public ReplicationRoot getParent()
   {
      return this.parent;
   }
   public ReplicationRootSet getParentTransitive()
   {
      ReplicationRootSet result = new ReplicationRootSet().with(this);
      return result.getParentTransitive();
   }


   public boolean setParent(ReplicationRoot value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         ReplicationRoot oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.withoutKids(this);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withKids(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public ReplicationRoot withParent(ReplicationRoot value)
   {
      setParent(value);
      return this;
   } 

   public ReplicationRoot createParent()
   {
      ReplicationRoot value = new ReplicationRoot();
      withParent(value);
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

