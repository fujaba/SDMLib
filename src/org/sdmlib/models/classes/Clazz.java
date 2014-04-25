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

import org.sdmlib.CGUtil;
import org.sdmlib.models.classes.creators.RoleSet;
import org.sdmlib.models.classes.logic.GenClass;
import org.sdmlib.models.classes.util.AttributeSet;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.classes.util.MethodSet;

public class Clazz extends SDMLibClass
{
   public static final String PROPERTY_NAME = "name";
   public static final String PROPERTY_SUPERCLASS = "superClass";
   public static final String PROPERTY_ATTRIBUTES = "attributes";
   public static final String PROPERTY_CLASSMODEL = "classModel";
   public static final String PROPERTY_KIDCLASSES = "kidClasses";
   public static final String PROPERTY_EXTERNAL = "external";
   public static final String PROPERTY_INTERFACES = "interfaces";
   public static final String PROPERTY_INTERFAZE = "interfaze";
   public static final String PROPERTY_METHODS = "methods";
   public static final String PROPERTY_KIDCLASSESASINTERFACE = "kidClassesAsInterface";
   public static final String PROPERTY_SOURCEROLES = "sourceRoles";
   public static final String PROPERTY_TARGETROLES = "targetRoles";
   public static final String PROPERTY_WRAPPED = "wrapped";

   private ClassModel classModel = null;  
   private boolean external;
   private MethodSet methods = null;
   private ClazzSet kidClasses = null;
   private ClazzSet kidClassesAsInterface = null;
   private ClazzSet interfaces = null;
   private Boolean interfaze = false;
   private Clazz superClass = null; 
   private RoleSet sourceRoles = null;
   private RoleSet targetRoles = null;
   private boolean wrapped;
   private String name = null; 
   private AttributeSet attributes = null;
   private GenClass generator;
   
   public Clazz(){
      
   }
   public Clazz(String name){
      setName(name);
   }

   public GenClass getGenerator(){
      if(generator==null){
         this.setGenerator(new GenClass());
      }
      return generator;
   }
   
   public void setGenerator(GenClass value)
   {
      if (this.generator != value)
      {
         GenClass oldValue = this.generator;
         if (this.generator != null)
         {
            this.generator = null;
            oldValue.setModel(null);
         }
         this.generator = value;
         if (value != null)
         {
            value.setModel(this);
         }
      }
   }
   
   public Clazz withAssoc(Clazz tgtClass, String tgtRoleName, R tgtCard, String srcRoleName, R srcCard)
   {      
      new Association()
      .withTarget(tgtRoleName, tgtClass, tgtCard)
      .withSource(srcRoleName, this, srcCard);

      return this;
   }



   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      if (getClassModel() != null)
      {
         if(name.indexOf('.') < 0 && getClassModel().getPackageName() != null)
         {
            name = "" + getClassModel().getPackageName()  + "." + name;
         }
         else if (getClassModel().getPackageName() == null)
         {
            getClassModel().setPackageName(CGUtil.packageName(name));
         }
      }
      
      this.name = name;
   }

   public Clazz withName(String name)
   {
      setName(name);
      return this;
   }


   public Clazz getSuperClass()
   {
      return superClass;
   }

   public void setSuperClass(Clazz newSuperClass)
   {
      if (this.superClass != newSuperClass)
      {
         Clazz oldSuperClass = this.superClass;
         if (this.superClass != null)
         {
            this.superClass = null;
            oldSuperClass.removeFromKidClasses(oldSuperClass);
         }
         
         this.superClass = newSuperClass;
         
         if (newSuperClass != null)
         {
            newSuperClass.addToKidClasses(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERCLASS, oldSuperClass, newSuperClass);
      }
   }

   public Clazz withSuperClass(Clazz superClass)
   {
      setSuperClass(superClass);
      return this;
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
            if(name.startsWith(this.classModel.getPackageName())){
               name = name.substring(this.classModel.getPackageName().length()+1);
            }
            this.classModel = null;
            oldValue.removeFromClasses(this);
         }
         this.classModel = value;
         if(name.indexOf('.') < 0){
            name = "" + getClassModel().getPackageName()  + "." + name;
         }  
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

   public boolean addToAttributes(Attribute value)
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

   public Clazz withAttributes(Attribute value)
   {
      addToAttributes(value);
      return this;
   } 

   public Clazz withoutAttributes(Attribute value)
   {
      removeFromAttributes(value);
      return this;
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
   public RoleSet getSourceRoles()
   {
      if (this.sourceRoles == null)
      {
         return Role.EMPTY_SET;
      }

      return this.sourceRoles;
   }

   public boolean addToSourceRoles(Role value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.sourceRoles == null)
         {
            this.sourceRoles = new RoleSet();
         }

         changed = this.sourceRoles.add (value);

         if (changed)
         {
            value.withClazz(this);
         }
      }

      return changed;   
   }

   public boolean removeFromSourceRoles(Role value)
   {
      boolean changed = false;

      if ((this.sourceRoles != null) && (value != null))
      {
         changed = this.sourceRoles.remove (value);

         if (changed)
         {
            value.setClazz(null);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_SOURCEROLES, null, value);
         }
      }

      return changed;   
   }

   public Clazz withSourceRoles(Role value)
   {
      addToSourceRoles(value);
      return this;
   } 

   public Clazz withoutSourceRoles(Role value)
   {
      removeFromSourceRoles(value);
      return this;
   } 

   public void removeAllFromSourceRoles()
   {
      LinkedHashSet<Role> tmpSet = new LinkedHashSet<Role>(this.getSourceRoles());

      for (Role value : tmpSet)
      {
         this.removeFromSourceRoles(value);
      }
   }


   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Role
    *              clazz                   targetRoles
    * </pre>
    */
   public RoleSet getTargetRoles()
   {
      if (this.targetRoles == null)
      {
         return Role.EMPTY_SET;
      }

      return this.targetRoles;
   }

   public boolean addToTargetRoles(Role value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.targetRoles == null)
         {
            this.targetRoles = new RoleSet();
         }

         changed = this.targetRoles.add (value);

         if (changed)
         {
            value.withClazz(this);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGETROLES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromTargetRoles(Role value)
   {
      boolean changed = false;

      if ((this.targetRoles != null) && (value != null))
      {
         changed = this.targetRoles.remove (value);

         if (changed)
         {
            value.setClazz(null);
            // getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGETROLES, null, value);
         }
      }

      return changed;   
   }

   public Clazz withTargetRoles(Role value)
   {
      addToTargetRoles(value);
      return this;
   } 

   public Clazz withoutTargetRoles(Role value)
   {
      removeFromTargetRoles(value);
      return this;
   } 

   public void removeAllFromTargetRoles()
   {
      LinkedHashSet<Role> tmpSet = new LinkedHashSet<Role>(this.getTargetRoles());

      for (Role value : tmpSet)
      {
         this.removeFromTargetRoles(value);
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
         return Method.EMPTY_SET;
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

   public Clazz withMethods(DataType returnType, Attribute parameters)
   {
      return withMethods(new Method(returnType, parameters));
   } 

   public Clazz withMethods(Method value)
   {
      addToMethods(value);
      return this;
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
   public void removeYou()
   {
      withClassModel(null);
      removeAllFromAttributes();
      removeAllFromMethods();
      removeAllFromSourceRoles();
      removeAllFromTargetRoles();
      removeAllFromKidClasses();
      setSuperClass(null);
      removeAllFromKidClassesAsInterface();
      removeAllFromInterfaces();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }



   /********************************************************************
    * <pre>
    *              one                       many
    * Clazz ----------------------------------- Clazz
    *              superClass                   kidClasses
    * </pre>
    */

   public ClazzSet getKidClasses()
   {
      if (this.kidClasses == null)
      {
         return new ClazzSet();
      }

      return this.kidClasses;
   }

   public boolean addToKidClasses(Clazz value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.kidClasses == null)
         {
            this.kidClasses = new ClazzSet();
         }

         changed = this.kidClasses.add (value);

         if (changed)
         {
            value.withSuperClass(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDCLASSES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromKidClasses(Clazz value)
   {
      boolean changed = false;

      if ((this.kidClasses != null) && (value != null))
      {
         changed = this.kidClasses.remove (value);

         if (changed)
         {
            value.setSuperClass(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDCLASSES, value, null);
         }
      }

      return changed;   
   }

   public Clazz withKidClasses(Clazz value)
   {
      addToKidClasses(value);
      return this;
   } 

   public Clazz withoutKidClasses(Clazz value)
   {
      removeFromKidClasses(value);
      return this;
   } 

   public void removeAllFromKidClasses()
   {
      LinkedHashSet<Clazz> tmpSet = new LinkedHashSet<Clazz>(this.getKidClasses());

      for (Clazz value : tmpSet)
      {
         this.removeFromKidClasses(value);
      }
   }


   //==========================================================================
   public Boolean isInterfaze()
   {
      return this.interfaze;
   }

   public void setInterfaze(Boolean value)
   {
      if (this.interfaze != value)
      {
         Boolean oldValue = this.interfaze;
         this.interfaze = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFAZE, oldValue, value);
      }
   }

   public Clazz withInterfaze(Boolean value)
   {
      setInterfaze(value);
      return this;
   } 


   /********************************************************************
    * <pre>
    *              many                       many
    * Clazz ----------------------------------- Clazz
    *              interfaces                   kidClassesAsInterface
    * </pre>
    */
   public ClazzSet getKidClassesAsInterface()
   {
      if (this.kidClassesAsInterface == null)
      {
         return new ClazzSet();
      }

      return this.kidClassesAsInterface;
   }

   public boolean addToKidClassesAsInterface(Clazz value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.kidClassesAsInterface == null)
         {
            this.kidClassesAsInterface = new ClazzSet();
         }

         changed = this.kidClassesAsInterface.add (value);

         if (changed)
         {
            value.withInterfaces(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDCLASSESASINTERFACE, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromKidClassesAsInterface(Clazz value)
   {
      boolean changed = false;

      if ((this.kidClassesAsInterface != null) && (value != null))
      {
         changed = this.kidClassesAsInterface.remove (value);

         if (changed)
         {
            value.withoutInterfaces(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDCLASSESASINTERFACE, value, null);
         }
      }

      return changed;   
   }

   public Clazz withKidClassesAsInterface(Clazz value)
   {
      addToKidClassesAsInterface(value);
      return this;
   } 

   public Clazz withoutKidClassesAsInterface(Clazz value)
   {
      removeFromKidClassesAsInterface(value);
      return this;
   } 

   public void removeAllFromKidClassesAsInterface()
   {
      ClazzSet tmpSet = (ClazzSet) this.getInterfaces().clone();

      for (Clazz value : tmpSet)
      {
         this.removeFromKidClassesAsInterface(value);
      }
   }

   /********************************************************************
    * <pre>
    *              many                       many
    * Clazz ----------------------------------- Clazz
    *              kindClassesAsInterface                   interfaces
    * </pre>
    */
   public ClazzSet getInterfaces()
   {
      if (this.interfaces == null)
      {
         return new ClazzSet();
      }

      return this.interfaces;
   }

   public boolean addToInterfaces(Clazz value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.interfaces == null)
         {
            this.interfaces = new ClazzSet();
         }

         changed = this.interfaces.add (value);

         if (changed)
         {
            value.withKidClassesAsInterface(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFACES, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromInterfaces(Clazz value)
   {
      boolean changed = false;

      if ((this.interfaces != null) && (value != null))
      {
         changed = this.interfaces.remove (value);

         if (changed)
         {
            value.withoutKidClassesAsInterface(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFACES, value, null);
         }
      }

      return changed;   
   }

   public Clazz withInterfaces(Clazz value)
   {
      addToInterfaces(value);
      value.addToKidClassesAsInterface(this);
      return this;
   } 

   public Clazz withoutInterfaces(Clazz value)
   {
      removeFromInterfaces(value);
      return this;
   } 

   public void removeAllFromInterfaces()
   {
      LinkedHashSet<Clazz> tmpSet = new LinkedHashSet<Clazz>(this.getInterfaces());

      for (Clazz value : tmpSet)
      {
         this.removeFromInterfaces(value);
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

   public Method createMethods(DataType returnType, Attribute... parameters)
   {
      return new Method(returnType, parameters).withClazz(this);     
   } 


   //==========================================================================
   public boolean getWrapped()
   {
      return this.wrapped;
   }

   public void setWrapped(boolean value)
   {
      if (this.wrapped != value)
      {
         boolean oldValue = this.wrapped;
         this.wrapped = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_WRAPPED, oldValue, value);
      }
   }

   public Clazz withWrapped(boolean value)
   {
      setWrapped(value);
      return this;
   }

   public ClazzSet getKidClassesClosure()
   {
      ClazzSet result = this.getKidClasses().union(this.getKidClassesAsInterface());

      int oldSize = 0;

      int newSize = result.size();

      while (newSize > oldSize)
      {
         result.addAll(result.getKidClasses().union(result.getKidClassesAsInterface()));
         oldSize = newSize;
         newSize = result.size();
      }

      return result;
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
         getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFAZE, oldValue, value);
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
   
   public Clazz withKidClasses(Clazz... value)
   {
      for (Clazz item : value)
      {
         addToKidClasses(item);
      }
      return this;
   } 

   public Clazz withoutKidClasses(Clazz... value)
   {
      for (Clazz item : value)
      {
         removeFromKidClasses(item);
      }
      return this;
   }

   public Clazz withKidClassesAsInterface(Clazz... value)
   {
      for (Clazz item : value)
      {
         addToKidClassesAsInterface(item);
      }
      return this;
   } 

   public Clazz withoutKidClassesAsInterface(Clazz... value)
   {
      for (Clazz item : value)
      {
         removeFromKidClassesAsInterface(item);
      }
      return this;
   }

   public Clazz createKidClassesAsInterface()
   {
      Clazz value = new Clazz();
      withKidClassesAsInterface(value);
      return value;
   } 

   public Clazz withInterfaces(Clazz... value)
   {
      for (Clazz item : value)
      {
         addToInterfaces(item);
      }
      return this;
   } 

   public Clazz withoutInterfaces(Clazz... value)
   {
      for (Clazz item : value)
      {
         removeFromInterfaces(item);
      }
      return this;
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

   public Clazz withSourceRoles(Role... value)
   {
      for (Role item : value)
      {
         addToSourceRoles(item);
      }
      return this;
   } 

   public Clazz withoutSourceRoles(Role... value)
   {
      for (Role item : value)
      {
         removeFromSourceRoles(item);
      }
      return this;
   }

   public Role createSourceRoles()
   {
      Role value = new Role();
      withSourceRoles(value);
      return value;
   } 

   public Clazz withTargetRoles(Role... value)
   {
      for (Role item : value)
      {
         addToTargetRoles(item);
      }
      return this;
   } 

   public Clazz withoutTargetRoles(Role... value)
   {
      for (Role item : value)
      {
         removeFromTargetRoles(item);
      }
      return this;
   }

   public Role createTargetRoles()
   {
      Role value = new Role();
      withTargetRoles(value);
      return value;
   } 
   public ClazzSet getKidClassesTransitive()
   {
      ClazzSet result = new ClazzSet().with(this);
      return result.getKidClassesTransitive();
   }

   public ClazzSet getSuperClassTransitive()
   {
      ClazzSet result = new ClazzSet().with(this);
      return result.getSuperClassTransitive();
   }

   public ClazzSet getKidClassesAsInterfaceTransitive()
   {
      ClazzSet result = new ClazzSet().with(this);
      return result.getKidClassesAsInterfaceTransitive();
   }

   public ClazzSet getInterfacesTransitive()
   {
      ClazzSet result = new ClazzSet().with(this);
      return result.getInterfacesTransitive();
   }
}
