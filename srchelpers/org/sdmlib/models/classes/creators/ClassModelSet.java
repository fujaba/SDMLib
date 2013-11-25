package org.sdmlib.models.classes.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.modelsets.StringList;

public class ClassModelSet extends LinkedHashSet<ClassModel> implements org.sdmlib.models.modelsets.ModelSet
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

   public StringList getPackageName()
   {
      StringList result = new StringList();
      
      for (ClassModel obj : this)
      {
         result.add(obj.getPackageName());
      }
      
      return result;
   }

   public ClassModelSet withPackageName(String value)
   {
      for (ClassModel obj : this)
      {
         obj.withPackageName(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ClassModel elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.ClassModel";
   }


   public ClassModelSet with(ClassModel value)
   {
      this.add(value);
      return this;
   }
   
   public ClassModelSet without(ClassModel value)
   {
      this.remove(value);
      return this;
   }
}



