/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.models.classes.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.DataTypeSet;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.list.SimpleSet;

public class AttributeSet extends SDMSet<Attribute>
{
   public StringList getValue()
   {
      StringList result = new StringList();
      
      for (Attribute obj : this)
      {
         result.add(obj.getValue());
      }
      
      return result;
   }

   public AttributeSet withValue(String value)
   {
      for (Attribute obj : this)
      {
         obj.withValue(value);
      }
      
      return this;
   }

   public ClazzSet getClazz()
   {
      ClazzSet result = new ClazzSet();
      
      for (Attribute obj : this)
      {
         result.add(obj.getClazz());
      }
      
      return result;
   }
   
   public AttributeSet withClazz(Clazz value)
   {
      for (Attribute obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }

   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Attribute elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Attribute";
   }


   public AttributeSet with(Attribute value)
   {
      this.add(value);
      return this;
   }
   
   public AttributeSet without(Attribute value)
   {
      this.remove(value);
      return this;
   }
   
   public DataTypeSet getType()
   {
      DataTypeSet result = new DataTypeSet();
      
      for (Attribute obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public AttributeSet withType(DataType value)
   {
      for (Attribute obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Attribute obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public AttributeSet withName(String value)
   {
      for (Attribute obj : this)
      {
         obj.with(value);
      }
      
      return this;
   }

   @SuppressWarnings("unchecked")
   public AttributeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Attribute>)value);
      }
      else if (value != null)
      {
         this.add((Attribute) value);
      }
      
      return this;
   }

   public static final AttributeSet EMPTY_SET = new AttributeSet().withReadOnly(true);
   public AnnotationSet getAnnotations()
   {
      AnnotationSet result = new AnnotationSet();
      
      for (Attribute obj : this)
      {
         result.addAll(obj.getAnnotations());
      }
      
      return result;
   }

   public AttributeSet hasAnnotations(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      AttributeSet answer = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getAnnotations()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public AttributeSet withAnnotations(Annotation value)
   {
      for (Attribute obj : this)
      {
         obj.withAnnotation(value);
      }
      
      return this;
   }

   public AttributeSet withoutAnnotations(Annotation value)
   {
      for (Attribute obj : this)
      {
         obj.withoutAnnotation(value);
      }
      
      return this;
   }
   public AttributeSet hasInitialization(String value)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (value.equals(obj.getInitialization()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AttributeSet hasInitialization(String lower, String upper)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (lower.compareTo(obj.getInitialization()) <= 0 && obj.getInitialization().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AttributeSet hasType(DataType value)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (value == obj.getType())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AttributeSet hasName(String value)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public AttributeSet hasName(String lower, String upper)
   {
      AttributeSet result = new AttributeSet();
      
      for (Attribute obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   /**
    * Removes every attribute from the current model and deletes
    * the generated code from the model and util classes.<br> 
    * This includes the set, creator and pattern object classes, that are associated with the attributes.
    * 
    * 
    * @param rootDir root directory, where the code of the attributes is located
    */
   
   public void removeFromModelAndCode(String rootDir) {

	   SimpleSet<Attribute> clone = this.clone();
	   
	   for (Attribute attribute : clone) {

		   attribute.removeFromModelAndCode(rootDir);

	   }

   }

}
