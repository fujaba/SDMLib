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
   
package org.sdmlib.models.transformations;

import org.sdmlib.models.transformations.Template;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.transformations.creators.TemplateSet;
import java.util.LinkedHashSet;

public class ChoiceTemplate extends Template implements PropertyChangeInterface
{
   
   
   //==========================================================================
   
   @Override
   public Match parseOnce()
   {
      // loop through choices and pick first that works
      for (Template altTemplate : this.getChoices())
      {
         Match match = altTemplate
               .withExpandedText(this.getExpandedText()).withValueStartPos(currentPosInExpandedText)
               .withIdMap(idMap).withConstFragmentFollowingAfterList(constFragmentFollowingAfterList)
               .withList(this.getListStart(), this.getListSeparator(), this.getListEnd())
               .parseOnce();
         
         if (match != null)
         {
            this.currentPosInExpandedText = altTemplate.currentPosInExpandedText;
            this.setModelObject(altTemplate.getModelObject());
            return match;
         }
      }
      
      return null;
   }


   public Object get(String attrName)
   {
      if (PROPERTY_TEMPLATETEXT.equalsIgnoreCase(attrName))
      {
         return getTemplateText();
      }

      if (PROPERTY_EXPANDEDTEXT.equalsIgnoreCase(attrName))
      {
         return getExpandedText();
      }

      if (PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         return getModelObject();
      }

      if (PROPERTY_MODELCLASSNAME.equalsIgnoreCase(attrName))
      {
         return getModelClassName();
      }

      if (PROPERTY_LISTSTART.equalsIgnoreCase(attrName))
      {
         return getListStart();
      }

      if (PROPERTY_LISTSEPARATOR.equalsIgnoreCase(attrName))
      {
         return getListSeparator();
      }

      if (PROPERTY_LISTEND.equalsIgnoreCase(attrName))
      {
         return getListEnd();
      }

      if (PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         return getPlaceholders();
      }

      if (PROPERTY_CHOICES.equalsIgnoreCase(attrName))
      {
         return getChoices();
      }

      if (PROPERTY_CHOOSER.equalsIgnoreCase(attrName))
      {
         return getChooser();
      }

      if (PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         return getMatches();
      }

      if (PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         return getParents();
      }

      if (PROPERTY_REFERENCELOOKUP.equalsIgnoreCase(attrName))
      {
         return getReferenceLookup();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TEMPLATETEXT.equalsIgnoreCase(attrName))
      {
         setTemplateText((String) value);
         return true;
      }

      if (PROPERTY_EXPANDEDTEXT.equalsIgnoreCase(attrName))
      {
         setExpandedText((String) value);
         return true;
      }

      if (PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         setModelObject((java.lang.Object) value);
         return true;
      }

      if (PROPERTY_MODELCLASSNAME.equalsIgnoreCase(attrName))
      {
         setModelClassName((String) value);
         return true;
      }

      if (PROPERTY_LISTSTART.equalsIgnoreCase(attrName))
      {
         setListStart((String) value);
         return true;
      }

      if (PROPERTY_LISTSEPARATOR.equalsIgnoreCase(attrName))
      {
         setListSeparator((String) value);
         return true;
      }

      if (PROPERTY_LISTEND.equalsIgnoreCase(attrName))
      {
         setListEnd((String) value);
         return true;
      }

      if (PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         addToPlaceholders((PlaceHolderDescription) value);
         return true;
      }
      
      if ((PROPERTY_PLACEHOLDERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromPlaceholders((PlaceHolderDescription) value);
         return true;
      }

      if (PROPERTY_CHOICES.equalsIgnoreCase(attrName))
      {
         addToChoices((Template) value);
         return true;
      }
      
      if ((PROPERTY_CHOICES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromChoices((Template) value);
         return true;
      }

      if (PROPERTY_CHOOSER.equalsIgnoreCase(attrName))
      {
         setChooser((ChoiceTemplate) value);
         return true;
      }

      if (PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         addToMatches((Match) value);
         return true;
      }
      
      if ((PROPERTY_MATCHES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromMatches((Match) value);
         return true;
      }

      if (PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         addToParents((PlaceHolderDescription) value);
         return true;
      }
      
      if ((PROPERTY_PARENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromParents((PlaceHolderDescription) value);
         return true;
      }

      if (PROPERTY_REFERENCELOOKUP.equalsIgnoreCase(attrName))
      {
         setReferenceLookup((Boolean) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
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
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      removeAllFromPlaceholders();
      removeAllFromChoices();
      setChooser(null);
      removeAllFromMatches();
      removeAllFromParents();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTemplateText());
      _.append(" ").append(this.getExpandedText());
      _.append(" ").append(this.getModelClassName());
      _.append(" ").append(this.getListStart());
      _.append(" ").append(this.getListSeparator());
      _.append(" ").append(this.getListEnd());
      _.append(" ").append(this.getName());
      return _.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * ChoiceTemplate ----------------------------------- Template
    *              chooser                   choices
    * </pre>
    */
   
   public static final String PROPERTY_CHOICES = "choices";
   
   private TemplateSet choices = null;
   
   public TemplateSet getChoices()
   {
      if (this.choices == null)
      {
         return Template.EMPTY_SET;
      }
   
      return this.choices;
   }
   
   public boolean addToChoices(Template value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.choices == null)
         {
            this.choices = new TemplateSet();
         }
         
         changed = this.choices.add (value);
         
         if (changed)
         {
            value.withChooser(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CHOICES, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromChoices(Template value)
   {
      boolean changed = false;
      
      if ((this.choices != null) && (value != null))
      {
         changed = this.choices.remove (value);
         
         if (changed)
         {
            value.setChooser(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CHOICES, value, null);
         }
      }
         
      return changed;   
   }
   
   public ChoiceTemplate withChoices(Template... value)
   {
      for (Template item : value)
      {
         addToChoices(item);
      }
      return this;
   } 
   
   public ChoiceTemplate withoutChoices(Template... value)
   {
      for (Template item : value)
      {
         removeFromChoices(item);
      }
      return this;
   }
   
   public void removeAllFromChoices()
   {
      LinkedHashSet<Template> tmpSet = new LinkedHashSet<Template>(this.getChoices());
   
      for (Template value : tmpSet)
      {
         this.removeFromChoices(value);
      }
   }
   
   public Template createChoices()
   {
      Template value = new Template();
      withChoices(value);
      return value;
   } 
}

