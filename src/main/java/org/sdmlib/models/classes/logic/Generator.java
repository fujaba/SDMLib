package org.sdmlib.models.classes.logic;

import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.GraphEntity;
import de.uniks.networkparser.graph.GraphMember;
import de.uniks.networkparser.graph.Method;

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
	   GraphMember owner = annotation.getParent();
	   Clazz clazz = null;
	   if(owner instanceof Clazz) {
		   clazz = ((Clazz)owner);
      }
      if (owner instanceof Method) {
    	  clazz = ((Method)owner).getClazz();
      }
      if (owner instanceof Attribute) {
    	  clazz = ((Attribute)owner).getClazz();
      }
      if(clazz != null) {
    	  ClassModel model = (ClassModel) clazz.getClassModel();
    	  return model.getGenerator().getOrCreate(annotation);
      }
      return null;
   }
   abstract ClassModel getClazz();
   
   public GenClass getGenerator(Clazz clazz)
   {
	   ClassModel model = (ClassModel) clazz.getClassModel();
      return model.getGenerator().getOrCreateClazz(clazz);
   }
   
   public GenClass getGenerator(Generator<?> generator, String name)
   {
	   return generator.getClazz().getGenerator().getClazz(name);
   }

   public GenMethod getGenerator(Method method)
   {
      if (method.getClazz() != null)
      {
			ClassModel model = (ClassModel) method.getClazz().getClassModel();
			return model.getGenerator().getOrCreate(method);
	      }
      return null;
   }

   public GenAttribute getGenerator(Attribute attribute)
   {
 	  ClassModel model = (ClassModel) attribute.getClazz().getClassModel();
      return model.getGenerator().getOrCreate(attribute);
   }
   
   public GenAssociation getGenerator(Association assoc)
   {
	   Clazz clazz = (Clazz) assoc.getClazz();
 	  ClassModel model = (ClassModel)clazz.getClassModel();
      return model.getGenerator().getOrCreate(assoc);
   }
}
