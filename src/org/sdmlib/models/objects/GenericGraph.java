/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.models.objects;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.models.objects.creators.GenericObjectSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.objects.creators.GenericLinkSet;

public class GenericGraph implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_OBJECTS.equalsIgnoreCase(attrName))
      {
         return getObjects();
      }

      if (PROPERTY_LINKS.equalsIgnoreCase(attrName))
      {
         return getLinks();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_OBJECTS.equalsIgnoreCase(attrName))
      {
         addToObjects((GenericObject) value);
         return true;
      }
      
      if ((PROPERTY_OBJECTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromObjects((GenericObject) value);
         return true;
      }

      if (PROPERTY_LINKS.equalsIgnoreCase(attrName))
      {
         addToLinks((GenericLink) value);
         return true;
      }
      
      if ((PROPERTY_LINKS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromLinks((GenericLink) value);
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
      removeAllFromObjects();
      removeAllFromLinks();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * GenericGraph ----------------------------------- GenericObject
    *              graph                   objects
    * </pre>
    */
   
   public static final String PROPERTY_OBJECTS = "objects";
   
   private GenericObjectSet objects = null;
   
   public GenericObjectSet getObjects()
   {
      if (this.objects == null)
      {
         return GenericObject.EMPTY_SET;
      }
   
      return this.objects;
   }
   
   public boolean addToObjects(GenericObject value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.objects == null)
         {
            this.objects = new GenericObjectSet();
         }
         
         changed = this.objects.add (value);
         
         if (changed)
         {
            value.withGraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OBJECTS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromObjects(GenericObject value)
   {
      boolean changed = false;
      
      if ((this.objects != null) && (value != null))
      {
         changed = this.objects.remove (value);
         
         if (changed)
         {
            value.setGraph(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OBJECTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public GenericGraph withObjects(GenericObject value)
   {
      addToObjects(value);
      return this;
   } 
   
   public GenericGraph withoutObjects(GenericObject value)
   {
      removeFromObjects(value);
      return this;
   } 
   
   public void removeAllFromObjects()
   {
      LinkedHashSet<GenericObject> tmpSet = new LinkedHashSet<GenericObject>(this.getObjects());
   
      for (GenericObject value : tmpSet)
      {
         this.removeFromObjects(value);
      }
   }
   
   public GenericObject createObjects()
   {
      GenericObject value = new GenericObject();
      withObjects(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * GenericGraph ----------------------------------- GenericLink
    *              graph                   links
    * </pre>
    */
   
   public static final String PROPERTY_LINKS = "links";
   
   private GenericLinkSet links = null;
   
   public GenericLinkSet getLinks()
   {
      if (this.links == null)
      {
         return GenericLink.EMPTY_SET;
      }
   
      return this.links;
   }
   
   public boolean addToLinks(GenericLink value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.links == null)
         {
            this.links = new GenericLinkSet();
         }
         
         changed = this.links.add (value);
         
         if (changed)
         {
            value.withGraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LINKS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromLinks(GenericLink value)
   {
      boolean changed = false;
      
      if ((this.links != null) && (value != null))
      {
         changed = this.links.remove (value);
         
         if (changed)
         {
            value.setGraph(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_LINKS, value, null);
         }
      }
         
      return changed;   
   }
   
   public GenericGraph withLinks(GenericLink value)
   {
      addToLinks(value);
      return this;
   } 
   
   public GenericGraph withoutLinks(GenericLink value)
   {
      removeFromLinks(value);
      return this;
   } 
   
   public void removeAllFromLinks()
   {
      LinkedHashSet<GenericLink> tmpSet = new LinkedHashSet<GenericLink>(this.getLinks());
   
      for (GenericLink value : tmpSet)
      {
         this.removeFromLinks(value);
      }
   }
   
   public GenericLink createLinks()
   {
      GenericLink value = new GenericLink();
      withLinks(value);
      return value;
   } 
}

