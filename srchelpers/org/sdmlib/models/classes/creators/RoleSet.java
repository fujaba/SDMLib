/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.models.classes.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.util.AssociationSet;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.modelsets.StringList;

public class RoleSet extends LinkedHashSet<Role> implements org.sdmlib.models.modelsets.ModelSet
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


   public RolePO startModelPattern()
   {
      org.sdmlib.models.classes.creators.ModelPattern pattern = new org.sdmlib.models.classes.creators.ModelPattern();
      
      RolePO patternObject = pattern.hasElementRolePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public RoleSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Role>)value);
      }
      else if (value != null)
      {
         this.add((Role) value);
      }
      
      return this;
   }
   
   public RoleSet without(Role value)
   {
      this.remove(value);
      return this;
   }



   public RolePO hasRolePO()
   {
      org.sdmlib.models.classes.creators.ModelPattern pattern = new org.sdmlib.models.classes.creators.ModelPattern();
      
      RolePO patternObject = pattern.hasElementRolePO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}









