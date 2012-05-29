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
import org.sdmlib.utils.StrUtil;
import org.sdmlib.models.objects.creators.GenericAttributeSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.objects.creators.GenericLinkSet;

public class GenericObject implements PropertyChangeInterface
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

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         return getType();
      }

      if (PROPERTY_ATTRS.equalsIgnoreCase(attrName))
      {
         return getAttrs();
      }

      if (PROPERTY_OUTGOINGLINKS.equalsIgnoreCase(attrName))
      {
         return getOutgoingLinks();
      }

      if (PROPERTY_INCOMMINGLINKS.equalsIgnoreCase(attrName))
      {
         return getIncommingLinks();
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

      if (PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         setType((String) value);
         return true;
      }

      if (PROPERTY_ATTRS.equalsIgnoreCase(attrName))
      {
         addToAttrs((GenericAttribute) value);
         return true;
      }
      
      if ((PROPERTY_ATTRS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromAttrs((GenericAttribute) value);
         return true;
      }

      if (PROPERTY_OUTGOINGLINKS.equalsIgnoreCase(attrName))
      {
         addToOutgoingLinks((GenericLink) value);
         return true;
      }
      
      if ((PROPERTY_OUTGOINGLINKS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromOutgoingLinks((GenericLink) value);
         return true;
      }

      if (PROPERTY_INCOMMINGLINKS.equalsIgnoreCase(attrName))
      {
         addToIncommingLinks((GenericLink) value);
         return true;
      }
      
      if ((PROPERTY_INCOMMINGLINKS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromIncommingLinks((GenericLink) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      removeAllFromAttrs();
      removeAllFromOutgoingLinks();
      removeAllFromIncommingLinks();
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
   
   public GenericObject withName(String value)
   {
      setName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TYPE = "type";
   
   private String type;
   
   public String getType()
   {
      return this.type;
   }
   
   public void setType(String value)
   {
      if ( ! StrUtil.stringEquals(this.type, value))
      {
         String oldValue = this.type;
         this.type = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TYPE, oldValue, value);
      }
   }
   
   public GenericObject withType(String value)
   {
      setType(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * GenericObject ----------------------------------- GenericAttribute
    *              owner                   attrs
    * </pre>
    */
   
   public static final String PROPERTY_ATTRS = "attrs";
   
   private GenericAttributeSet attrs = null;
   
   public GenericAttributeSet getAttrs()
   {
      if (this.attrs == null)
      {
         return GenericAttribute.EMPTY_SET;
      }
   
      return this.attrs;
   }
   
   public boolean addToAttrs(GenericAttribute value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.attrs == null)
         {
            this.attrs = new GenericAttributeSet();
         }
         
         changed = this.attrs.add (value);
         
         if (changed)
         {
            value.withOwner(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromAttrs(GenericAttribute value)
   {
      boolean changed = false;
      
      if ((this.attrs != null) && (value != null))
      {
         changed = this.attrs.remove (value);
         
         if (changed)
         {
            value.setOwner(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRS, value, null);
         }
      }
         
      return changed;   
   }
   
   public GenericObject withAttrs(GenericAttribute value)
   {
      addToAttrs(value);
      return this;
   } 
   
   public GenericObject withoutAttrs(GenericAttribute value)
   {
      removeFromAttrs(value);
      return this;
   } 
   
   public void removeAllFromAttrs()
   {
      LinkedHashSet<GenericAttribute> tmpSet = new LinkedHashSet<GenericAttribute>(this.getAttrs());
   
      for (GenericAttribute value : tmpSet)
      {
         this.removeFromAttrs(value);
      }
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * GenericObject ----------------------------------- GenericLink
    *              src                   outgoingLinks
    * </pre>
    */
   
   public static final String PROPERTY_OUTGOINGLINKS = "outgoingLinks";
   
   private GenericLinkSet outgoingLinks = null;
   
   public GenericLinkSet getOutgoingLinks()
   {
      if (this.outgoingLinks == null)
      {
         return GenericLink.EMPTY_SET;
      }
   
      return this.outgoingLinks;
   }
   
   public boolean addToOutgoingLinks(GenericLink value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.outgoingLinks == null)
         {
            this.outgoingLinks = new GenericLinkSet();
         }
         
         changed = this.outgoingLinks.add (value);
         
         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTGOINGLINKS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromOutgoingLinks(GenericLink value)
   {
      boolean changed = false;
      
      if ((this.outgoingLinks != null) && (value != null))
      {
         changed = this.outgoingLinks.remove (value);
         
         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTGOINGLINKS, value, null);
         }
      }
         
      return changed;   
   }
   
   public GenericObject withOutgoingLinks(GenericLink value)
   {
      addToOutgoingLinks(value);
      return this;
   } 
   
   public GenericObject withoutOutgoingLinks(GenericLink value)
   {
      removeFromOutgoingLinks(value);
      return this;
   } 
   
   public void removeAllFromOutgoingLinks()
   {
      LinkedHashSet<GenericLink> tmpSet = new LinkedHashSet<GenericLink>(this.getOutgoingLinks());
   
      for (GenericLink value : tmpSet)
      {
         this.removeFromOutgoingLinks(value);
      }
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * GenericObject ----------------------------------- GenericLink
    *              tgt                   incommingLinks
    * </pre>
    */
   
   public static final String PROPERTY_INCOMMINGLINKS = "incommingLinks";
   
   private GenericLinkSet incommingLinks = null;
   
   public GenericLinkSet getIncommingLinks()
   {
      if (this.incommingLinks == null)
      {
         return GenericLink.EMPTY_SET;
      }
   
      return this.incommingLinks;
   }
   
   public boolean addToIncommingLinks(GenericLink value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.incommingLinks == null)
         {
            this.incommingLinks = new GenericLinkSet();
         }
         
         changed = this.incommingLinks.add (value);
         
         if (changed)
         {
            value.withTgt(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INCOMMINGLINKS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromIncommingLinks(GenericLink value)
   {
      boolean changed = false;
      
      if ((this.incommingLinks != null) && (value != null))
      {
         changed = this.incommingLinks.remove (value);
         
         if (changed)
         {
            value.setTgt(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INCOMMINGLINKS, value, null);
         }
      }
         
      return changed;   
   }
   
   public GenericObject withIncommingLinks(GenericLink value)
   {
      addToIncommingLinks(value);
      return this;
   } 
   
   public GenericObject withoutIncommingLinks(GenericLink value)
   {
      removeFromIncommingLinks(value);
      return this;
   } 
   
   public void removeAllFromIncommingLinks()
   {
      LinkedHashSet<GenericLink> tmpSet = new LinkedHashSet<GenericLink>(this.getIncommingLinks());
   
      for (GenericLink value : tmpSet)
      {
         this.removeFromIncommingLinks(value);
      }
   }


   public GenericObject with(String attrName, String attrValue)
   {
      new GenericAttribute()
      .withName(attrName)
      .withValue(attrValue)
      .withOwner(this);
      
      return this;
   }
}

