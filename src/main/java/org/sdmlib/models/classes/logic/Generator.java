package org.sdmlib.models.classes.logic;

import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.classes.AnnotationOwner;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
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
	   AnnotationOwner owner = annotation.getOwner();
	   if(owner instanceof Clazz) {
         return ((Clazz)owner).getClassModel().getGenerator().getOrCreate(annotation);
      }
      if (owner instanceof Method) {
         return ((Method)owner).getClazz().getClassModel().getGenerator().getOrCreate(annotation);
      }
      if (owner instanceof Attribute) {
         return ((Attribute)owner).getClazz().getClassModel().getGenerator().getOrCreate(annotation);
      }
      return null;
   }
   abstract ClassModel getClazz();
   
   public GenClass getGenerator(Clazz clazz)
   {
      return clazz.getClassModel().getGenerator().getOrCreate(clazz);
   }
   
   public GenClass getGenerator(Generator<?> generator, String name)
   {
	   return generator.getClazz().getGenerator().getClazz(name);
   }

   public GenMethod getGenerator(Method method)
   {
      if (method.getClazz() != null)
      {
         return method.getClazz().getClassModel().getGenerator().getOrCreate(method);
      }
      return null;
   }

   public GenAttribute getGenerator(Attribute attribute)
   {
      return attribute.getClazz().getClassModel().getGenerator().getOrCreate(attribute);
   }
}
