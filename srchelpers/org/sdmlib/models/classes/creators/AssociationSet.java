package org.sdmlib.models.classes.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Role;

public class AssociationSet extends LinkedHashSet<Association>
{
   public ClassModelSet getModel()
   {
      ClassModelSet result = new ClassModelSet();
      
      for (Association obj : this)
      {
         result.add(obj.getModel());
      }
      
      return result;
   }
   public AssociationSet withModel(ClassModel value)
   {
      for (Association obj : this)
      {
         obj.withModel(value);
      }
      
      return this;
   }

   public RoleSet getSource()
   {
      RoleSet result = new RoleSet();
      
      for (Association obj : this)
      {
         result.add(obj.getSource());
      }
      
      return result;
   }
   public AssociationSet withSource(Role value)
   {
      for (Association obj : this)
      {
         obj.withSource(value);
      }
      
      return this;
   }

   public RoleSet getTarget()
   {
      RoleSet result = new RoleSet();
      
      for (Association obj : this)
      {
         result.add(obj.getTarget());
      }
      
      return result;
   }
   public AssociationSet withTarget(Role value)
   {
      for (Association obj : this)
      {
         obj.withTarget(value);
      }
      
      return this;
   }

}
