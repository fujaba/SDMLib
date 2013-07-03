package org.sdmlib.models.classes.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.modelsets.StringList;

public class RoleSet extends LinkedHashSet<Role>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Role obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public RoleSet withName(String value)
   {
      for (Role obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public StringList getCard()
   {
      StringList result = new StringList();
      
      for (Role obj : this)
      {
         result.add(obj.getCard());
      }
      
      return result;
   }

   public RoleSet withCard(String value)
   {
      for (Role obj : this)
      {
         obj.withCard(value);
      }
      
      return this;
   }

   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (Role obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }

   public RoleSet withKind(String value)
   {
      for (Role obj : this)
      {
         obj.withKind(value);
      }
      
      return this;
   }

   public ClazzSet getClazz()
   {
      ClazzSet result = new ClazzSet();
      
      for (Role obj : this)
      {
         result.add(obj.getClazz());
      }
      
      return result;
   }
   public RoleSet withClazz(Clazz value)
   {
      for (Role obj : this)
      {
         obj.withClazz(value);
      }
      
      return this;
   }

   public AssociationSet getAssoc()
   {
      AssociationSet result = new AssociationSet();
      
      for (Role obj : this)
      {
         result.add(obj.getAssoc());
      }
      
      return result;
   }
   public RoleSet withAssoc(Association value)
   {
      for (Role obj : this)
      {
         obj.withAssoc(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Role elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Role";
   }


   public RoleSet with(Role value)
   {
      this.add(value);
      return this;
   }
   
   public RoleSet without(Role value)
   {
      this.remove(value);
      return this;
   }

   public RoleSet getOtherRoles()
   {
      RoleSet result = new RoleSet();
      
      for (Role role : this)
      {
         Association assoc = role.getAssoc();
         
         if (assoc.getSource() == role)
         {
            result.add(assoc.getTarget());
         }
         else
         {
            result.add(assoc.getSource());
         }
      }
      
      return result;
   }
}


