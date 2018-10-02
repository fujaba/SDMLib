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
import org.sdmlib.replication.util.ObjectSet;
import org.sdmlib.replication.util.SeppelScopeSet;
import org.sdmlib.replication.util.SeppelSpaceProxySet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;

   public class SeppelScope implements PropertyChangeInterface, SendableEntity
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
      withoutSubScopes(this.getSubScopes().toArray(new SeppelScope[this.getSubScopes().size()]));
      withoutSuperScopes(this.getSuperScopes().toArray(new SeppelScope[this.getSuperScopes().size()]));
      withoutSpaces(this.getSpaces().toArray(new SeppelSpaceProxy[this.getSpaces().size()]));
      withoutObservedObjects(this.getObservedObjects().toArray(new Object[this.getObservedObjects().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_SCOPENAME = "scopeName";
   
   private String scopeName;

   public String getScopeName()
   {
      return this.scopeName;
   }
   
   public void setScopeName(String value)
   {
      if ( ! StrUtil.stringEquals(this.scopeName, value))
      {
         String oldValue = this.scopeName;
         this.scopeName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SCOPENAME, oldValue, value);
      }
   }
   
   public SeppelScope withScopeName(String value)
   {
      setScopeName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getScopeName());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       many
    * SeppelScope ----------------------------------- SeppelScope
    *              superScopes                   subScopes
    * </pre>
    */
   
   public static final String PROPERTY_SUBSCOPES = "subScopes";

   private SeppelScopeSet subScopes = null;
   
   public SeppelScopeSet getSubScopes()
   {
      if (this.subScopes == null)
      {
         return SeppelScopeSet.EMPTY_SET;
      }
   
      return this.subScopes;
   }
   public SeppelScopeSet getSubScopesTransitive()
   {
      SeppelScopeSet result = new SeppelScopeSet().with(this);
      return result.getSubScopesTransitive();
   }


   public SeppelScope withSubScopes(SeppelScope... value)
   {
      if(value==null){
         return this;
      }
      for (SeppelScope item : value)
      {
         if (item != null)
         {
            if (this.subScopes == null)
            {
               this.subScopes = new SeppelScopeSet();
            }
            
            boolean changed = this.subScopes.add (item);

            if (changed)
            {
               item.withSuperScopes(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBSCOPES, null, item);
            }
         }
      }
      return this;
   } 

   public SeppelScope withoutSubScopes(SeppelScope... value)
   {
      for (SeppelScope item : value)
      {
         if ((this.subScopes != null) && (item != null))
         {
            if (this.subScopes.remove(item))
            {
               item.withoutSuperScopes(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBSCOPES, item, null);
            }
         }
      }
      return this;
   }

   public SeppelScope createSubScopes()
   {
      SeppelScope value = new SeppelScope();
      withSubScopes(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * SeppelScope ----------------------------------- SeppelScope
    *              subScopes                   superScopes
    * </pre>
    */
   
   public static final String PROPERTY_SUPERSCOPES = "superScopes";

   private SeppelScopeSet superScopes = null;
   
   public SeppelScopeSet getSuperScopes()
   {
      if (this.superScopes == null)
      {
         return SeppelScopeSet.EMPTY_SET;
      }
   
      return this.superScopes;
   }
   public SeppelScopeSet getSuperScopesTransitive()
   {
      SeppelScopeSet result = new SeppelScopeSet().with(this);
      return result.getSuperScopesTransitive();
   }


   public SeppelScope withSuperScopes(SeppelScope... value)
   {
      if(value==null){
         return this;
      }
      for (SeppelScope item : value)
      {
         if (item != null)
         {
            if (this.superScopes == null)
            {
               this.superScopes = new SeppelScopeSet();
            }
            
            boolean changed = this.superScopes.add (item);

            if (changed)
            {
               item.withSubScopes(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERSCOPES, null, item);
            }
         }
      }
      return this;
   } 

   public SeppelScope withoutSuperScopes(SeppelScope... value)
   {
      for (SeppelScope item : value)
      {
         if ((this.superScopes != null) && (item != null))
         {
            if (this.superScopes.remove(item))
            {
               item.withoutSubScopes(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERSCOPES, item, null);
            }
         }
      }
      return this;
   }

   public SeppelScope createSuperScopes()
   {
      SeppelScope value = new SeppelScope();
      withSuperScopes(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * SeppelScope ----------------------------------- SeppelSpaceProxy
    *              scopes                   spaces
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

   public SeppelScope withSpaces(SeppelSpaceProxy... value)
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
               item.withScopes(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SPACES, null, item);
            }
         }
      }
      return this;
   } 

   public SeppelScope withoutSpaces(SeppelSpaceProxy... value)
   {
      for (SeppelSpaceProxy item : value)
      {
         if ((this.spaces != null) && (item != null))
         {
            if (this.spaces.remove(item))
            {
               item.withoutScopes(this);
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
    *              one                       many
    * SeppelScope ----------------------------------- Object
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

   public SeppelScope withObservedObjects(Object... value)
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

   public SeppelScope withoutObservedObjects(Object... value)
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

   public <T> T add(T newObject)
   {
      this.withObservedObjects(newObject);
      return newObject;
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
