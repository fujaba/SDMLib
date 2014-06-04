/*
   Copyright (c) 2012 Albert Zündorf

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

import org.sdmlib.models.classes.util.AttributeSet;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.classes.util.MethodSet;
import org.sdmlib.models.classes.util.RoleSet;

public class Clazz extends SDMLibClass
{
   public static final String PROPERTY_ATTRIBUTES = "attributes";
   public static final String PROPERTY_CLASSMODEL = "classModel";
   public static final String PROPERTY_SUPERCLASS = "superclasses";
   public static final String PROPERTY_KIDCLASSES = "kidclasses";
   
   public static final String PROPERTY_METHODS = "methods";
   public static final String PROPERTY_ROLES = "roles";
   
   public static final String PROPERTY_INTERFACE = "interface";
   public static final String PROPERTY_EXTERNAL = "external";
   public static final ClazzSet EMPTY_SET = new ClazzSet();

   private AttributeSet attributes = null;
   private ClassModel classModel = null;  
   private ClazzSet superClazzes = null;
   private ClazzSet kidClazzes = null;
   
   private MethodSet methods = null;
   private RoleSet roles = null;
   
   private boolean interfaze = false;
   private boolean external;
   
   public Clazz(){
      
   }
   public Clazz(String name){
      setName(name);
   }
   
   public Clazz withAssoc(Clazz tgtClass, String tgtRoleName, Card tgtCard, String srcRoleName, Card srcCard)
   {      
      Association assoc = new Association()
      .withTarget(tgtClass, tgtRoleName, tgtCard)
      .withSource(this, srcRoleName, srcCard);

      if (this.getClassModel() != null && this.getClassModel().getGenerator() != null)
      {
         this.getClassModel().getGenerator().addToAssociations(assoc);
      }
      
      return this;
   }
   
   public Clazz withReference(Clazz tgtClass, String tgtRoleName, Card tgtCard){
      //FIXME ALEX
      return this;
   }

   public String getFullName()
   {
      
      if(name.indexOf('.') < 0 && getClassModel()!= null && getClassModel().getName() != null)
      {
         return getClassModel().getName()  + "." + name.replace("$", ".");
      }
      return name.replace("$", ".");
   }

   @Override
   public Clazz withName(String name)
   {
      setName(name);
      return this;
   }


   public Clazz getSuperClass()
   {
      if(superClazzes==null){
         return null;
      }
      for(Clazz clazz : superClazzes){
         if(!clazz.isInterface()){
            return clazz;
         }
      }
      return null;
   }
   
   public ClazzSet getInterfaces()
   {
      ClazzSet interfaces =new ClazzSet();
      if(superClazzes==null){
         return interfaces;
      }
      for(Clazz clazz : superClazzes){
         if(clazz.isInterface()){
            interfaces.add(clazz);
         }
      }
      return interfaces;
   }
   
   public ClazzSet getSuperClasses()
   {
      if (this.superClazzes == null)
      {
         superClazzes =  new ClazzSet();
      }
      return superClazzes;
   }

   public Clazz withSuperClass(Clazz... newSuperClass)
   {
      for(Clazz superClazz : newSuperClass){
         if(getSuperClasses().add(superClazz)){
            superClazz.withKidClass(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERCLASS, null, newSuperClass);
         }
      }
      return this;
   }
   
   public boolean removeFromSuperClass(Clazz... value)
   {
      boolean changed = false;
      for(Clazz superClazz : value){
         if(this.superClazzes.remove(superClazz)){
            superClazz.removeFromKidClass(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERCLASS, superClazz, null);
            changed = true;
         }
      }
      return changed;   
   }
   

   public ClazzSet getKidClasses()
   {
      if (this.kidClazzes == null)
      {
         kidClazzes =  new ClazzSet();
      }
      return kidClazzes;
   }
   
   public ClazzSet getKidClassesTransitive()
   {
      ClazzSet result = new ClazzSet().with(this);
      return result.getKidClassesTransitive();
   }

   Clazz withKidClass(Clazz... newSuperClass)
   {
      for(Clazz superClazz : newSuperClass){
         if(getKidClasses().add(superClazz)){
            superClazz.withSuperClass(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERCLASS, null, newSuperClass);
         }
      }
      return this;
   }
   
   boolean removeFromKidClass(Clazz... value)
   {
      boolean changed = false;
      for(Clazz superClazz : value){
         if(this.superClazzes.remove(superClazz)){
            superClazz.removeFromSuperClass(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERCLASS, superClazz, null);
            changed = true;
         }
      }
      return changed;   
   }

   public ClassModel getClassModel()
   {
      return classModel;
   }

   public Clazz withClassModel(ClassModel value)
   {
      if (this.classModel != value)
      {
         ClassModel oldValue = this.classModel;
         if (this.classModel != null)
         {
            this.classModel = null;
            oldValue.removeFromClasses(this);
         }
         this.classModel = value;
       if (value != null)
         {
            value.addToClasses(this);
         }
      }
      return this;
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Attribute
    *              clazz                   attributes
    * </pre>
    */
   public AttributeSet getAttributes()
   {
      if (this.attributes == null)
      {
         return new AttributeSet();
      }

      return this.attributes;
   }

   boolean addToAttributes(Attribute value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.attributes == null)
         {
            this.attributes = new AttributeSet();
         }

         changed = this.attributes.add (value);

         if (changed)
         {
            value.setClazz(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRIBUTES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromAttributes(Attribute value)
   {
      boolean changed = false;

      if ((this.attributes != null) && (value != null))
      {
         changed = this.attributes.remove (value);

         if (changed)
         {
            value.setClazz(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRIBUTES, value, null);
         }
      }

      return changed;   
   }
 

   public void removeAllFromAttributes()
   {
      LinkedHashSet<Attribute> tmpSet = new LinkedHashSet<Attribute>(this.getAttributes());

      for (Attribute value : tmpSet)
      {
         this.removeFromAttributes(value);
      }
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Role
    *              clazz                   sourceRoles
    * </pre>
    */
   public RoleSet getRoles()
   {
      if (this.roles == null)
      {
         return Role.EMPTY_SET;
      }

      return this.roles;
   }
   
   public Clazz with(Attribute value){
      return withAttributes(value);
   }
   
   public Clazz with(Method value){
         return withMethods(value);
   }

   public boolean addToRoles(Role value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.roles == null)
         {
            this.roles = new RoleSet();
         }

         changed = this.roles.add (value);

         if (changed)
         {
            value.withClazz(this);
         }
      }

      return changed;   
   }

   public boolean removeFromRoles(Role value)
   {
      boolean changed = false;

      if ((this.roles != null) && (value != null))
      {
         changed = this.roles.remove (value);

         if (changed)
         {
            value.setClazz(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ROLES, null, value);
         }
      }

      return changed;   
   }

   public Clazz withRoles(Role value)
   {
      addToRoles(value);
      return this;
   } 

   public Clazz withoutRoles(Role value)
   {
      removeFromRoles(value);
      return this;
   } 

   public void removeAllFromRoles()
   {
      LinkedHashSet<Role> tmpSet = new LinkedHashSet<Role>(this.getRoles());

      for (Role value : tmpSet)
      {
         this.removeFromRoles(value);
      }
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Method
    *              clazz                   methods
    * </pre>
    */
   public MethodSet getMethods()
   {
      if (this.methods == null)
      {
         return new MethodSet();
      }

      return this.methods;
   }

   public boolean addToMethods(Method value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.methods == null)
         {
            this.methods = new MethodSet();
         }

         changed = this.methods.add (value);

         if (changed)
         {
            value.withClazz(this);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromMethods(Method value)
   {
      boolean changed = false;

      if ((this.methods != null) && (value != null))
      {
         changed = this.methods.remove (value);

         if (changed)
         {
            value.setClazz(null);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, null, value);
         }
      }

      return changed;   
   }
   
   public Clazz withMethod(String name)
   {
      return with(new Method(name));
   }

   public Clazz withMethod(String name, DataType returnType, Parameter... parameters)
   {
      return with(new Method(name, returnType, parameters));
   } 

   public Clazz withoutMethods(Method value)
   {
      removeFromMethods(value);
      return this;
   } 

   public void removeAllFromMethods()
   {
      LinkedHashSet<Method> tmpSet = new LinkedHashSet<Method>(this.getMethods());

      for (Method value : tmpSet)
      {
         this.removeFromMethods(value);
      }
   }


   public Clazz withAttribute(String name, DataType type)
   {      
      this.withAttributes(new Attribute().withName(name).withType(type));
      return this;
   }

   public Clazz withAttribute(String name, DataType type, String initialization)
   {      
      this.withAttributes(new Attribute().withName(name).withType(type).withInitialization(initialization));
      return this;
   }

   //==========================================================================
   @Override
   public void removeYou()
   {
      withClassModel(null);
      removeAllFromAttributes();
      removeAllFromMethods();
      removeAllFromRoles();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   //==========================================================================
   public Boolean isInterface()
   {
      return this.interfaze;
   }

   public void setInterface(Boolean value)
   {
      if (this.interfaze != value)
      {
         Boolean oldValue = this.interfaze;
         this.interfaze = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFACE, oldValue, value);
      }
   }

   public Attribute getOrCreateAttribute(String attrName, DataType attrType)
   {
      for (Attribute attrDecl : getAttributes())
      {
         if (attrDecl.getName().equals(attrName))
         {
            return attrDecl;
         }
      }

      return new Attribute().withName(attrName).withType(attrType).withClazz(this);

   }

   //==========================================================================
   public boolean isExternal()
   {
      return this.external;
   }

   public void setExternal(boolean value)
   {
      if (this.external != value)
      {
         boolean oldValue = this.external;
         this.external = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_EXTERNAL, oldValue, value);
      }
   }

   public Clazz withExternal(boolean value)
   {
      setExternal(value);
      return this;
   }


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getName());
      // _.append(" ").append(this.getFilePath());
      return _.substring(1);
   }
   
   //==========================================================================
   
   public boolean getInterfaze()
   {
      return this.interfaze;
   }
   
   public void setInterfaze(boolean value)
   {
      if (this.interfaze != value)
      {
         boolean oldValue = this.interfaze;
         this.interfaze = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFACE, oldValue, value);
      }
   }
   
   public Clazz withInterfaze(boolean value)
   {
      setInterfaze(value);
      return this;
   } 

   
   //==========================================================================
   
   public boolean getExternal()
   {
      return this.external;
   }
   
   public Clazz withAttributes(Attribute... value)
   {
      for (Attribute item : value)
      {
         addToAttributes(item);
      }
      return this;
   } 

   public Clazz withoutAttributes(Attribute... value)
   {
      for (Attribute item : value)
      {
         removeFromAttributes(item);
      }
      return this;
   }

  public Clazz withMethods(Method... value)
   {
      for (Method item : value)
      {
         addToMethods(item);
      }
      return this;
   } 

   public Clazz withoutMethods(Method... value)
   {
      for (Method item : value)
      {
         removeFromMethods(item);
      }
      return this;
   }

   public Clazz withRoles(Role... value)
   {
      for (Role item : value)
      {
         addToRoles(item);
      }
      return this;
   } 

   public Clazz withoutRoles(Role... value)
   {
      for (Role item : value)
      {
         removeFromRoles(item);
      }
      return this;
   }

   public Role createSourceRoles()
   {
      Role value = new Role();
      withRoles(value);
      return value;
   } 

 
   public ClazzSet getSuperClassTransitive()
   {
      ClazzSet result = new ClazzSet().with(this);
      return result.getSuperClassTransitive();
   }

   public ClazzSet getInterfacesTransitive()
   {
      ClazzSet result = new ClazzSet().with(this);
      return result.getInterfacesTransitive();
   }
   
   public Method createMethod(String name, Parameter... parameters)
   {
      return new Method().withName(name).withParameter(parameters).withClazz(this);     
   }
   
   public Method createMethod(String name, DataType returnValue, Parameter... parameters)
   {
      return new Method().withName(name).withParameter(parameters).withReturnType(returnValue).withClazz(this);     
   }

   public Attribute createAttribute(String name, DataType type)
   {
      Attribute attribute = new Attribute(name, type);
      with(attribute);
      return attribute;
   }
}
