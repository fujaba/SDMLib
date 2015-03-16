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

import java.util.LinkedHashSet;

import org.sdmlib.models.transformations.util.TemplateSet;
import org.sdmlib.serialization.PropertyChangeInterface;

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
   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      setChooser(null);
      withoutPlaceholders(this.getPlaceholders().toArray(new PlaceHolderDescription[this.getPlaceholders().size()]));
      withoutChoices(this.getChoices().toArray(new Template[this.getChoices().size()]));
      withoutMatches(this.getMatches().toArray(new Match[this.getMatches().size()]));
      withoutParents(this.getParents().toArray(new PlaceHolderDescription[this.getParents().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getTemplateText());
      s.append(" ").append(this.getExpandedText());
      s.append(" ").append(this.getModelClassName());
      s.append(" ").append(this.getListStart());
      s.append(" ").append(this.getListSeparator());
      s.append(" ").append(this.getListEnd());
      s.append(" ").append(this.getName());
      return s.substring(1);
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

   public Template createChoicesChoiceTemplate()
   {
      Template value = new ChoiceTemplate();
      withChoices(value);
      return value;
   } 

   public ChoiceTemplate createChoiceTemplate()
   {
      ChoiceTemplate value = new ChoiceTemplate();
      withChoices(value);
      return value;
   } 
}

