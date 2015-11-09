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
import org.sdmlib.models.classes.logic.ClassModelAdapter;
import org.sdmlib.models.classes.logic.GenRole;

/**
 * 
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
 */
public class Association extends SDMLibClass
{
   /**
    * @deprecated use Clazz.withAssoc(...) to construct associations. 
    */
   @Deprecated
   public Association()
   {
      
      // Auto-generated constructor stub
   }
   
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
	   Role role = new Role()
	      .withName(roleName)
	      .with(sourceClass);
	   if(card != null) {
		   role.withCard(card.toString());
	   }
      setSource(role);
      if (sourceClass.getClassModel() != null && sourceClass.getClassModel().getGenerator() != null)
      {
         sourceClass.getClassModel().getGenerator().addToAssociations(this);
      }
      
      return this;
   }

   public Association withTarget(Clazz targetClass, String roleName, Card card)
   {
      setTarget(new Role()
      .withName(roleName)
      .with(targetClass)
      .withCard(card.toString()));
      
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
            value.with(this);
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
            value.with(this);
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
      getSource().removeYou();
//      setSource(null);
//      setTarget(null);
      getTarget().removeYou();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   @Override
   public Association withName(String value)
   {
      setName(value);
      return this;
   }

   Role createSource()
   {
      Role value = new Role();
      withSource(value);
      return value;
   } 

   Role createTarget()
   {
      Role value = new Role();
      withTarget(value);
      return value;
   }

   /**
    * Removes this association from the current model and deletes
    * the generated code from the model and util classes.<br> 
    * This includes the set, creator and pattern object classes, that are associated with this association.
    * 
    * 
    * @param rootDir root directory, where the code of the association is located
    */
   public void removeFromModelAndCode(String rootDir) {
	   
	   ClassModelAdapter genModel = this.getSource().getClazz().getClassModel().getGenerator();
	   
	   GenRole sourceGenRole = genModel.getOrCreate(this.getSource());
	   
	   GenRole targetGenRole = genModel.getOrCreate(this.getTarget());
	   
	   sourceGenRole.removeGeneratedCode(rootDir);
	   
	   targetGenRole.removeGeneratedCode(rootDir);
	   
	   this.removeYou();
	   
   } 
}

