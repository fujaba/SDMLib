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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.models.objects.util.GenericAttributeSet;
import org.sdmlib.models.objects.util.GenericLinkSet;
import org.sdmlib.models.objects.util.GenericObjectSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;


   public class GenericObject implements PropertyChangeInterface, SendableEntity
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
      setGraph(null);
      withoutAttrs(this.getAttrs().toArray(new GenericAttribute[this.getAttrs().size()]));
      withoutOutgoingLinks(this.getOutgoingLinks().toArray(new GenericLink[this.getOutgoingLinks().size()]));
      withoutIncommingLinks(this.getIncommingLinks().toArray(new GenericLink[this.getIncommingLinks().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
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

   
   public static final GenericObjectSet EMPTY_SET = new GenericObjectSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * GenericObject ----------------------------------- GenericGraph
    *              objects                   graph
    * </pre>
    */
   
   public static final String PROPERTY_GRAPH = "graph";
   
   private GenericGraph graph = null;
   
   public GenericGraph getGraph()
   {
      return this.graph;
   }
   
   public boolean setGraph(GenericGraph value)
   {
      boolean changed = false;
      
      if (this.graph != value)
      {
         GenericGraph oldValue = this.graph;
         
         if (this.graph != null)
         {
            this.graph = null;
            oldValue.withoutObjects(this);
         }
         
         this.graph = value;
         
         if (value != null)
         {
            value.withObjects(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GRAPH, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public GenericObject withGraph(GenericGraph value)
   {
      setGraph(value);
      return this;
   } 
   
   public GenericGraph createGraph()
   {
      GenericGraph value = new GenericGraph();
      withGraph(value);
      return value;
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
   
   public GenericAttribute createAttrs()
   {
      GenericAttribute value = new GenericAttribute();
      withAttrs(value);
      return value;
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
   
   public GenericLink createOutgoingLinks()
   {
      GenericLink value = new GenericLink()
      .withGraph(this.getGraph());
      
      this.withOutgoingLinks(value);
      
      return value;
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
   
   public GenericLink createIncommingLinks()
   {
      GenericLink value = new GenericLink()
      .withGraph(this.getGraph());
      
      this.withIncommingLinks(value);
      
      return value;
   }


   public GenericObject with(String... params)
   {
      for (int i = 0; i <= params.length - 2; i += 2)
      {
         this.with(params[i], params[i + 1]);
      }
      
      return this;
   }
   
   public GenericObject with(String name, String value)
   {
      this.createAttrs()
      .withName(name)
      .withValue(value);
      
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ICON = "icon";
   
   private String icon;

   public String getIcon()
   {
      return this.icon;
   }
   
   public void setIcon(String value)
   {
      if ( ! StrUtil.stringEquals(this.icon, value))
      {
         String oldValue = this.icon;
         this.icon = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ICON, oldValue, value);
      }
   }
   
   public GenericObject withIcon(String value)
   {
      setIcon(value);
      return this;
   }


   public GenericObject createSubElem(String string)
   {
      return this.createOutgoingLinks().withTgtLabel(string)
            .createTgt();
      
   }


   public GenericLink createOutgoingLinks(String tgtLabel, GenericObject tgt)
   {
      return this.createOutgoingLinks().withTgtLabel(tgtLabel).withTgt(tgt);
   }


   public GenericLink createOutgoingLinks(String srcLabel, String tgtLabel, GenericObject tgt)
   {
      return this.createOutgoingLinks().withSrcLabel(srcLabel).withTgtLabel(tgtLabel).withTgt(tgt);
   } 

   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getName());
      s.append(" ").append(this.getType());
      s.append(" ").append(this.getIcon());
      return s.substring(1);
   }


   public GenericObject withAttrs(GenericAttribute... value)
   {
      for (GenericAttribute item : value)
      {
         addToAttrs(item);
      }
      return this;
   } 

   public GenericObject withoutAttrs(GenericAttribute... value)
   {
      for (GenericAttribute item : value)
      {
         removeFromAttrs(item);
      }
      return this;
   }

   public GenericObject withOutgoingLinks(GenericLink... value)
   {
      for (GenericLink item : value)
      {
         addToOutgoingLinks(item);
      }
      return this;
   } 

   public GenericObject withoutOutgoingLinks(GenericLink... value)
   {
      for (GenericLink item : value)
      {
         removeFromOutgoingLinks(item);
      }
      return this;
   }

   public GenericObject withIncommingLinks(GenericLink... value)
   {
      for (GenericLink item : value)
      {
         addToIncommingLinks(item);
      }
      return this;
   } 

   public GenericObject withoutIncommingLinks(GenericLink... value)
   {
      for (GenericLink item : value)
      {
         removeFromIncommingLinks(item);
      }
      return this;
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

