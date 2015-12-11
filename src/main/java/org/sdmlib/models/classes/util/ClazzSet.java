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
   
package org.sdmlib.models.classes.util;

import java.util.Collection;
import java.util.Collections;

import javax.management.relation.Role;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.booleanSet;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Method;

public class ClazzSet extends SDMSet<Clazz> 
{
   public static final ClazzSet EMPTY_SET = new ClazzSet().withReadOnly(true);
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Clazz obj : this)
      {
         result.add(obj.getName(false));
      }
      
      return result;
   }

   public ClazzSet withName(String value)
   {
      for (Clazz obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }
   
   public ClazzSet hasName(String value)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if (value.indexOf(obj.getName(false)) >= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public booleanSet isInterface()
   {
      booleanSet result = new booleanSet();
      
      for (Clazz obj : this)
      {
         result.add(obj.isInterface());
      }
      
      return result;
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
         obj.with(value);
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
         obj.withSuperClazz(value);
      }
      
      return this;
   }
   
   public ClazzSet getKidClazzesTransitive()
   {
      ClazzSet todo = new ClazzSet().with(this);
      
      ClazzSet result = new ClazzSet();
      
      while ( ! todo.isEmpty())
      {
         Clazz current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getKidClazzes().minus(result));
         }
      }
      
      return result;

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
         obj.with(value);
      }
      
      return this;
   }

   public ClazzSet withoutAttributes(Attribute value)
   {
      for (Clazz obj : this)
      {
         obj.without(value);
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
         obj.with(value);
      }
      
      return this;
   }

   public ClazzSet withoutMethods(Method value)
   {
      for (Clazz obj : this)
      {
         obj.without(value);
      }
      
      return this;
   }

   public RoleSet getSourceRoles()
   {
      RoleSet result = new RoleSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getRoles());
      }
      
      return result;
   }
   public ClazzSet withSourceRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }

   public ClazzSet withoutSourceRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.without(value);
      }
      
      return this;
   }

   public booleanSet isExternal()
   {
      booleanSet result = new booleanSet();
      
      for (Clazz obj : this)
      {
         result.add(obj.isExternal());
      }
      
      return result;
   }

   public ClazzSet withInterfaze(boolean value)
   {
      for (Clazz obj : this)
      {
         obj.withInterface(value);
      }
      
      return this;
   }

   public ClazzSet withExternal(boolean value)
   {
      for (Clazz obj : this)
      {
         obj.setExternal(value);
      }
      
      return this;
   }

   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Clazz elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Clazz";
   }


   public booleanList getWrapped()
   {
      booleanList result = new booleanList();
      
      for (Clazz obj : this)
      {
         result.add(obj.isExternal());
      }
      
      return result;
   }

   public ClazzSet withWrapped(boolean value)
   {
      for (Clazz obj : this)
      {
         obj.withExternal(value);
      }
      
      return this;
   }

   @SuppressWarnings("unchecked")
   public ClazzSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Clazz>)value);
      }
      else if (value != null)
      {
         this.add((Clazz) value);
      }
      
      return this;
   }
   
   public ClazzSet without(Clazz value)
   {
      this.remove(value);
      return this;
   }

   public ClazzSet getInterfacesTransitive()
   {
      ClazzSet todo = new ClazzSet().with(this);
      
      ClazzSet result = new ClazzSet();
      
      while ( ! todo.isEmpty())
      {
         Clazz current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getInterfaces().minus(result));
         }
      }
      
      return result;
   }


   public ClazzSet getSuperClassTransitive()
   {
      ClazzSet todo = new ClazzSet().with(this);
      
      ClazzSet result = new ClazzSet();
      
      while ( ! todo.isEmpty())
      {
         Clazz current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getSuperClass()))
            {
               todo.with(current.getSuperClass());
            }
         }
      }
      
      return result;
   }

   public ClazzSet hasInterface(boolean value)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if (value == obj.isInterface())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ClazzSet withInterface(boolean value)
   {
      for (Clazz obj : this)
      {
         obj.setInterface(value);
      }
      
      return this;
   }

   public RoleSet getRoles()
   {
      RoleSet result = new RoleSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getRoles());
      }
      
      return result;
   }

   public ClazzSet hasRoles(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ClazzSet answer = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getRoles()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ClazzSet withRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }

   public ClazzSet withoutRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.without(value);
      }
      
      return this;
   }

   public ClazzSet getKidClazzes()
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getKidClazzes());
      }
      
      return result;
   }

   public ClazzSet hasKidClazzes(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ClazzSet answer = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getKidClazzes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ClazzSet withKidClazzes(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withKidClazzes(value);
      }
      
      return this;
   }

   public ClazzSet withoutKidClazzes(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withoutKidClazz(value);
      }
      
      return this;
   }

   public ClazzSet getSuperClazzes()
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getSuperClazzes());
      }
      
      return result;
   }

   public ClazzSet hasSuperClazzes(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ClazzSet answer = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSuperClazzes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public ClazzSet getSuperClazzesTransitive()
   {
      ClazzSet todo = new ClazzSet().with(this);
      
      ClazzSet result = new ClazzSet();
      
      while ( ! todo.isEmpty())
      {
         Clazz current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getSuperClazzes().minus(result));
         }
      }
      
      return result;
   }

   public ClazzSet withSuperClazzes(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withSuperClazz(value);
      }
      
      return this;
   }

   public ClazzSet withoutSuperClazzes(Clazz value)
   {
      for (Clazz obj : this)
      {
         obj.withoutSuperClazz(value);
      }
      
      return this;
   }

   public booleanList getInterfaze()
   {
      booleanList result = new booleanList();
      
      for (Clazz obj : this)
      {
         result.add(obj.isInterface());
      }
      
      return result;
   }

   public ClazzSet hasInterfaze(boolean value)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if (value == obj.isInterface())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public booleanList getExternal()
   {
      booleanList result = new booleanList();
      
      for (Clazz obj : this)
      {
         result.add(obj.isExternal());
      }
      
      return result;
   }

   public ClazzSet hasExternal(boolean value)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if (value == obj.isExternal())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AnnotationSet getAnnotations()
   {
      AnnotationSet result = new AnnotationSet();
      
      for (Clazz obj : this)
      {
         result.addAll(obj.getAnnotations());
      }
      
      return result;
   }

   public ClazzSet hasAnnotations(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ClazzSet answer = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getAnnotations()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ClazzSet withAnnotations(Annotation value)
   {
      for (Clazz obj : this)
      {
         obj.withAnnotation(value);
      }
      
      return this;
   }

   public ClazzSet withoutAnnotations(Annotation value)
   {
      for (Clazz obj : this)
      {
         obj.withoutAnnotation(value);
      }
      
      return this;
   }
   public ClazzSet hasName(String lower, String upper)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }
}
