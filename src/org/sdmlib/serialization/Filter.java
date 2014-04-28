package org.sdmlib.serialization;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
 */
import java.util.LinkedHashSet;
import org.sdmlib.serialization.logic.Condition;
import org.sdmlib.serialization.logic.ValuesMap;

public class Filter
{
   private Condition idFilter;
   private Condition convertable;
   private Condition property;

   // Temporary variables
   private LinkedHashSet<Object> visitedObjects;
   private Boolean full;

   public Condition getIdFilter()
   {
      return idFilter;
   }

   public Filter withIdFilter(Condition idFilter)
   {
      this.idFilter = idFilter;
      return this;
   }

   public boolean isId(IdMapEncoder map, Object entity, String className)
   {
      if (idFilter != null)
      {
         return idFilter.matches(ValuesMap.with(map, entity, className));
      }
      return true;
   }

   /**
    * Serialization the Full object inclusive null value
    * 
    * @return boolean for serialization the full object
    */
   public Boolean isFullSeriation()
   {
      return full;
   }

   public Filter withFull(boolean value)
   {
      this.full = value;
      return this;
   }

   public Condition getConvertable()
   {
      return convertable;
   }

   public Filter withConvertable(Condition convertable)
   {
      this.convertable = convertable;
      return this;
   }

   public Condition getPropertyRegard()
   {
      return property;
   }

   public Filter withPropertyRegard(Condition property)
   {
      this.property = property;
      return this;
   }

   public Filter withStandard(Filter referenceFilter)
   {
      if (idFilter == null)
      {
         idFilter = referenceFilter.getIdFilter();
      }
      if (convertable == null)
      {
         convertable = referenceFilter.getConvertable();
      }
      if (property == null)
      {
         property = referenceFilter.getPropertyRegard();
      }
      visitedObjects = new LinkedHashSet<Object>();
      if (full == null)
      {
         full = referenceFilter.isFullSeriation();
         if (full == null)
         {
            full = false;
         }
      }

      return this;
   }

   public Filter cloneObj()
   {
      Filter reference = new Filter();
      if (reference.getClass().getName().equals(this.getClass().getName()))
      {
         return clone(new Filter());
      }
      return this;
   }

   public Filter clone(Filter newInstance)
   {
      return newInstance.withConvertable(convertable).withIdFilter(idFilter)
         .withPropertyRegard(property);
   }

   public boolean hasVisitedObjects(Object element)
   {
      return visitedObjects.contains(element);
   }

   public void addToVisitedObjects(Object visitedObjects)
   {
      this.visitedObjects.add(visitedObjects);
   }

   public boolean isPropertyRegard(IdMapEncoder map, Object entity,
         String property, Object value, boolean isMany, int deep)
   {
      if (this.property != null)
      {
         return this.property.matches(ValuesMap.with(map, entity, property,
            value, isMany, deep));
      }
      return true;
   }

   public boolean isConvertable(IdMapEncoder map, Object entity,
         String property, Object value, boolean isMany, int deep)
   {
      if (this.convertable != null)
      {
         return this.convertable.matches(ValuesMap.with(map, entity, property,
            value, isMany, deep));
      }
      return true;
   }
}
