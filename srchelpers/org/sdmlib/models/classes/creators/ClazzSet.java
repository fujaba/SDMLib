package org.sdmlib.models.classes.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanSet;

import java.util.List;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.modelsets.booleanList;

public class ClazzSet extends LinkedHashSet<Clazz>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Clazz obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public ClazzSet withName(String value)
   {
      for (Clazz obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public booleanSet getInterfaze()
   {
      booleanSet result = new booleanSet();
      
      for (Clazz obj : this)
      {
         result.add(obj.isInterfaze());
      }
      
      return result;
   }

   public ClazzSet withInterfaze(Boolean value)
   {
      for (Clazz obj : this)
      {
         obj.withInterfaze(value);
      }
      
      return this;
   }

   public ClassModelSet getClassModel()
   {
      ClassModelSet result = new ClassModelSet();
      
      for (Clazz obj : this)
      {
         result.add(obj.getClassModel());
      }
      
      return result;
   }
   public ClazzSet withClassModel(ClassModel value)
   {
      for (Clazz obj : this)
      {
         obj.withClassModel(value);
      }
      
      return this;
   }

   public ClazzSet getKindClasses()
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getKindClasses());
      }
      
      return result;
   }
   public ClazzSet withKindClasses(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withKindClasses(value);
      }
      
      return this;
   }

   public ClazzSet withoutKindClasses(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withoutKindClasses(value);
      }
      
      return this;
   }

   public ClazzSet getSuperClass()
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         result.add(obj.getSuperClass());
      }
      
      return result;
   }
   public ClazzSet withSuperClass(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withSuperClass(value);
      }
      
      return this;
   }

   public ClazzSet getKindClassesAsInterface()
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getKindClassesAsInterface());
      }
      
      return result;
   }
   public ClazzSet withKindClassesAsInterface(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withKindClassesAsInterface(value);
      }
      
      return this;
   }

   public ClazzSet withoutKindClassesAsInterface(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withoutKindClassesAsInterface(value);
      }
      
      return this;
   }

   public ClazzSet getInterfaces()
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getInterfaces());
      }
      
      return result;
   }
   public ClazzSet withInterfaces(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withInterfaces(value);
      }
      
      return this;
   }

   public ClazzSet withoutInterfaces(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withoutInterfaces(value);
      }
      
      return this;
   }

   public AttributeSet getAttributes()
   {
      AttributeSet result = new AttributeSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getAttributes());
      }
      
      return result;
   }
   public ClazzSet withAttributes(Attribute value)
   {
      for (Clazz obj : this)
      {
         obj.withAttributes(value);
      }
      
      return this;
   }

   public ClazzSet withoutAttributes(Attribute value)
   {
      for (Clazz obj : this)
      {
         obj.withoutAttributes(value);
      }
      
      return this;
   }

   public MethodSet getMethods()
   {
      MethodSet result = new MethodSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getMethods());
      }
      
      return result;
   }
   public ClazzSet withMethods(Method value)
   {
      for (Clazz obj : this)
      {
         obj.withMethods(value);
      }
      
      return this;
   }

   public ClazzSet withoutMethods(Method value)
   {
      for (Clazz obj : this)
      {
         obj.withoutMethods(value);
      }
      
      return this;
   }

   public RoleSet getSourceRoles()
   {
      RoleSet result = new RoleSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getSourceRoles());
      }
      
      return result;
   }
   public ClazzSet withSourceRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.withSourceRoles(value);
      }
      
      return this;
   }

   public ClazzSet withoutSourceRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.withoutSourceRoles(value);
      }
      
      return this;
   }

   public RoleSet getTargetRoles()
   {
      RoleSet result = new RoleSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getTargetRoles());
      }
      
      return result;
   }
   public ClazzSet withTargetRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.withTargetRoles(value);
      }
      
      return this;
   }

   public ClazzSet withoutTargetRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.withoutTargetRoles(value);
      }
      
      return this;
   }

   public booleanSet getExternal()
   {
      booleanSet result = new booleanSet();
      
      for (Clazz obj : this)
      {
         result.add(obj.isExternal());
      }
      
      return result;
   }

   public ClazzSet withExternal(Boolean value)
   {
      for (Clazz obj : this)
      {
         obj.withExternal(value);
      }
      
      return this;
   }

   public ClazzSet withInterfaze(boolean value)
   {
      for (Clazz obj : this)
      {
         obj.withInterfaze(value);
      }
      
      return this;
   }

   public ClazzSet withExternal(boolean value)
   {
      for (Clazz obj : this)
      {
         obj.withExternal(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Clazz elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Clazz";
   }


   public ClazzSet with(Clazz value)
   {
      this.add(value);
      return this;
   }
   
   public ClazzSet without(Clazz value)
   {
      this.remove(value);
      return this;
   }
   public booleanList getWrapped()
   {
      booleanList result = new booleanList();
      
      for (Clazz obj : this)
      {
         result.add(obj.getWrapped());
      }
      
      return result;
   }

   public ClazzSet withWrapped(boolean value)
   {
      for (Clazz obj : this)
      {
         obj.withWrapped(value);
      }
      
      return this;
   }

}




