package org.sdmlib.models.classes.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Association;

public class ClassModelSet extends LinkedHashSet<ClassModel>
{
   public ClazzSet getClasses()
   {
      ClazzSet result = new ClazzSet();
      
      for (ClassModel obj : this)
      {
         result.addAll(obj.getClasses());
      }
      
      return result;
   }
   public ClassModelSet withClasses(Clazz value)
   {
      for (ClassModel obj : this)
      {
         obj.withClasses(value);
      }
      
      return this;
   }

   public ClassModelSet withoutClasses(Clazz value)
   {
      for (ClassModel obj : this)
      {
         obj.withoutClasses(value);
      }
      
      return this;
   }

   public AssociationSet getAssociations()
   {
      AssociationSet result = new AssociationSet();
      
      for (ClassModel obj : this)
      {
         result.addAll(obj.getAssociations());
      }
      
      return result;
   }
   public ClassModelSet withAssociations(Association value)
   {
      for (ClassModel obj : this)
      {
         obj.withAssociations(value);
      }
      
      return this;
   }

   public ClassModelSet withoutAssociations(Association value)
   {
      for (ClassModel obj : this)
      {
         obj.withoutAssociations(value);
      }
      
      return this;
   }

}

