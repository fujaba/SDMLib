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
import java.util.Iterator;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.booleanSet;

public class ClazzSet extends SDMSet<Clazz> 
{
   private static final long serialVersionUID = 1L;


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
   
   public ClazzSet hasName(String value)
   {
      ClazzSet result = new ClazzSet();
      
      for (Clazz clazz : this)
      {
         if (value.indexOf(clazz.getName()) >= 0)
         {
            result.add(clazz);
         }
      }
      
      return result;
   }

   public booleanSet getInterface()
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
         obj.withClassModel(value);
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
         result.addAll(obj.getRoles());
      }
      
      return result;
   }
   public ClazzSet withSourceRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.withRoles(value);
      }
      
      return this;
   }

   public ClazzSet withoutSourceRoles(Role value)
   {
      for (Clazz obj : this)
      {
         obj.withoutRoles(value);
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

   public ClazzPO startModelPattern()
   {
      org.sdmlib.models.classes.util.ModelPattern pattern = new org.sdmlib.models.classes.util.ModelPattern();
      
      ClazzPO patternObject = pattern.hasElementClazzPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public ClazzSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         for(Iterator<?> i = ((Collection<?>)value).iterator();i.hasNext();){
            this.add((Clazz) i.next());
         }
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



   public ClazzPO hasClazzPO()
   {
      org.sdmlib.models.classes.util.ModelPattern pattern = new org.sdmlib.models.classes.util.ModelPattern();
      
      ClazzPO patternObject = pattern.hasElementClazzPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
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
}












