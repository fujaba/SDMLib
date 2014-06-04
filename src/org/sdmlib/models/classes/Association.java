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

import org.sdmlib.CGUtil;

public class Association extends SDMLibClass
{
   
   // ADDED NAME TO Assoc
   
   /********************************************************************
    * <pre>
    *              one                       one
    * Association ----------------------------------- Role
    *              assoc                   source
    * </pre>
    */
   public static final String PROPERTY_SOURCE = "source";
   public static final String PROPERTY_TARGET = "target";
      
   private Role source = null;
   private Role target;
   
   @Override
   public String toString()
   {
      String text = "role is missing";
      try
      {
         text = CGUtil.shortClassName(source.getClazz().getName()) + "|" + source.getName() + " -- " + target.getName() + "|" + CGUtil.shortClassName(target.getClazz().getName());
      }
      catch (Exception e)
      {
         // intentionally empty
      }
      return  text;
   }
   
   public Association withSource(Clazz sourceClass, String roleName, Card card)
   {
      withSource(sourceClass, roleName, card, Role.VANILLA);
      return this;
   }
   
   public Association withSource(Clazz sourceClass, String roleName, Card card,
      String kind)
   {
      setSource(new Role()
      .withName(roleName)
      .withClazz(sourceClass)
      .withCard(card.toString())
      .withKind(kind));
      
      if (sourceClass.getClassModel() != null && sourceClass.getClassModel().getGenerator() != null)
      {
         sourceClass.getClassModel().getGenerator().addToAssociations(this);
      }
      
      return this;
   }

   public Association withTarget(Clazz targetClass, String roleName, Card card)
   {
      withTarget(targetClass, roleName, card, Role.VANILLA);
      return this;
   }
   
   public Association withTarget(Clazz targetClass, String roleName, Card card,
      String kind)
   {
      setTarget(new Role()
      .withName(roleName)
      .withClazz(targetClass)
      .withCard(card.toString())
      .withKind(kind));
      
      if (targetClass.getClassModel() != null && targetClass.getClassModel().getGenerator() != null)
      {
         targetClass.getClassModel().getGenerator().addToAssociations(this);
      }
      
      return this;
   }

 
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
            value.withAssoc(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGET, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Association withTarget(Role value)
   {
      setTarget(value);
      return this;
   } 
   
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
            value.withAssoc(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SOURCE, oldValue, value);
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
   
   @Override
   public void removeYou()
   {
      Clazz targetClazz=null;
      if(getSource()!=null){
         targetClazz = getSource().getClazz();
      }else if(getTarget()!=null){
         targetClazz = getTarget().getClazz();
      }
      if(targetClazz!=null){
         targetClazz.getClassModel().getGenerator().removeFromAssociations(this);
      }
      setSource(null);
      setTarget(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   @Override
   public Association withName(String value)
   {
      setName(value);
      return this;
   }

   public Role createSource()
   {
      Role value = new Role();
      withSource(value);
      return value;
   } 

   public Role createTarget()
   {
      Role value = new Role();
      withTarget(value);
      return value;
   } 
}
