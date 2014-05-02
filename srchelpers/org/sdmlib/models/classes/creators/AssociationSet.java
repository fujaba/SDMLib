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
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.classes.creators.ClassModelSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.classes.creators.RoleSet;

public class AssociationSet extends LinkedHashSet<Association> implements
      org.sdmlib.models.modelsets.ModelSet
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

   public String toString()
   {
      StringList stringList = new StringList();

      for (Association elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Association";
   }

   public AssociationSet with(Association value)
   {
      this.add(value);
      return this;
   }

   public AssociationSet without(Association value)
   {
      this.remove(value);
      return this;
   }

   public AssociationPO startModelPattern()
   {
      org.sdmlib.models.classes.creators.ModelPattern pattern = new org.sdmlib.models.classes.creators.ModelPattern();

      AssociationPO patternObject = pattern.hasElementAssociationPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public AssociationSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Association>) value);
      }
      else if (value != null)
      {
         this.add((Association) value);
      }

      return this;
   }

   public AssociationPO hasAssociationPO()
   {
      org.sdmlib.models.classes.creators.ModelPattern pattern = new org.sdmlib.models.classes.creators.ModelPattern();

      AssociationPO patternObject = pattern.hasElementAssociationPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}




