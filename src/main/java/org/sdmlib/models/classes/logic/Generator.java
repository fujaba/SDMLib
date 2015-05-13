package org.sdmlib.models.classes.logic;

import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;

public abstract class Generator<M>
{
   protected M model;

   public void setModel(M value)
   {
      if (this.model != value)
      {
         if (this.model != null)
         {
            this.model = null;
         }
         this.model = value;
      }
   }

   public Generator<M> withModel(M value)
   {
      if (this.model != value)
      {
         if (this.model != null)
         {
            this.model = null;
         }
         this.model = value;
      }
      return this;
   }

   public M getModel()
   {
      return model;
   }

   public GenAnnotation getGenerator(Annotation annotation)
   {
      if (annotation.getClazz() != null)
      {
         return annotation.getClazz().getClassModel().getGenerator().getOrCreate(annotation);
      }
      if (annotation.getMethod() != null)
      {
         return annotation.getMethod().getClazz().getClassModel().getGenerator().getOrCreate(annotation);
      }
      if (annotation.getAttribute() != null)
      {
         return annotation.getAttribute().getClazz().getClassModel().getGenerator().getOrCreate(annotation);
      }
      return null;
   }

   public GenClass getGenerator(Clazz clazz)
   {
      return clazz.getClassModel().getGenerator().getOrCreate(clazz);
   }

   public GenMethod getGenerator(Method method)
   {
      if (method.getClazz() != null)
      {
         return method.getClazz().getClassModel().getGenerator().getOrCreate(method);
      }
      if (method.getEnumeration() != null)
      {
         return method.getEnumeration().getClassModel().getGenerator().getOrCreate(method);
      }
      return null;
   }

   public GenAttribute getGenerator(Attribute attribute)
   {
      return attribute.getClazz().getClassModel().getGenerator().getOrCreate(attribute);
   }
}
