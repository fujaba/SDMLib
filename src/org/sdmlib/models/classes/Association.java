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
   
package org.sdmlib.models.classes;

import java.util.LinkedHashSet;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

public class Association implements PropertyChangeInterface
{
   public Association()
   {
      setModel(ClassModel.classModel);
   }
   
   public Association withSource(String roleName, Clazz sourceClass, String card)
   {
      withSource(roleName, sourceClass, card, Role.VANILLA);
      return this;
   }


   public Association withSource(String roleName, Clazz sourceClass, String card,
      String kind)
   {
      setSource(new Role()
      .withName(roleName)
      .withClazz(sourceClass)
      .withCard(card)
      .withKind(kind));
      
      return this;
   }

 
   public Association withTarget(String roleName, Clazz targetClass, String card)
   {
      withTarget(roleName, targetClass, card, Role.VANILLA);
      return this;
   }


   public Association withTarget(String roleName, Clazz targetClass, String card,
      String kind)
   {
      setTarget(new Role()
      .withName(roleName)
      .withClazz(targetClass)
      .withCard(card)
      .withKind(kind));
      
      return this;
   }

 
   public Association generate(String rootDir)
   {
      generate(rootDir, true);
      
      return this;
   }
   
   public Association generate(String rootDir, boolean doGenerate)
   {
      // open source class and get or insert role implementation
      // Parser sourceClassParser = getSourceClass().getOrCreateParser(rootDir);
      getSource().generate(rootDir, getTarget(), doGenerate);
      
      // open target class and get or insert role implementation
      getTarget().generate(rootDir, getSource(), doGenerate);
      
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TARGET = "target";
   
   private Role target;
   
   public Role getTarget()
   {
      return this.target;
   }
   
   public boolean setTarget(Role value)
   {
      
      boolean changed = false;
      
      if (this.target != value)
      {
         Role oldValue = this.target;
         
         if (this.target != null)
         {
            this.target = null;
            oldValue.setAssoc(null);
         }
         
         this.target = value;
         
         if (value != null)
         {
            value.setAssoc(this);
         }
         
         // getPropertyChangeSupport().firePropertyChange(PROPERTY_SOURCE, null, value);
         changed = true;
      }
      
      return changed;
      
   }
   
   public Association withTarget(Role value)
   {
      setTarget(value);
      return this;
   } 

   
   public static final LinkedHashSet<Association> EMPTY_SET = new LinkedHashSet<Association>();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Association ----------------------------------- ClassModel
    *              associations                   model
    * </pre>
    */
   
   public static final String PROPERTY_MODEL = "model";
   
   private ClassModel model = null;
   
   public ClassModel getModel()
   {
      return this.model;
   }
   
   public boolean setModel(ClassModel value)
   {
      boolean changed = false;
      
      if (this.model != value)
      {
         ClassModel oldValue = this.model;
         
         if (this.model != null)
         {
            this.model = null;
            oldValue.withoutAssociations(this);
         }
         
         this.model = value;
         
         if (value != null)
         {
            value.withAssociations(this);
         }
         
         // getPropertyChangeSupport().firePropertyChange(PROPERTY_MODEL, null, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Association withModel(ClassModel value)
   {
      setModel(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Association ----------------------------------- Role
    *              assoc                   source
    * </pre>
    */
   
   public static final String PROPERTY_SOURCE = "source";
   
   private Role source = null;
   
   public Role getSource()
   {
      return this.source;
   }
   
   public boolean setSource(Role value)
   {
      boolean changed = false;
      
      if (this.source != value)
      {
         Role oldValue = this.source;
         
         if (this.source != null)
         {
            this.source = null;
            oldValue.setAssoc(null);
         }
         
         this.source = value;
         
         if (value != null)
         {
            value.setAssoc(this);
         }
         
         // getPropertyChangeSupport().firePropertyChange(PROPERTY_SOURCE, null, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Association withSource(Role value)
   {
      setSource(value);
      return this;
   } 

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_MODEL.equalsIgnoreCase(attrName))
      {
         return getModel();
      }

      if (PROPERTY_SOURCE.equalsIgnoreCase(attrName))
      {
         return getSource();
      }

      if (PROPERTY_TARGET.equalsIgnoreCase(attrName))
      {
         return getTarget();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_MODEL.equalsIgnoreCase(attrName))
      {
         setModel((ClassModel) value);
         return true;
      }

      if (PROPERTY_SOURCE.equalsIgnoreCase(attrName))
      {
         setSource((Role) value);
         return true;
      }

      if (PROPERTY_TARGET.equalsIgnoreCase(attrName))
      {
         setTarget((Role) value);
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
      setModel(null);
      setSource(null);
      setTarget(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
}

