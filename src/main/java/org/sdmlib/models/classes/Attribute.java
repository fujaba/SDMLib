/*   Copyright (c) 2012 zuendorf 

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

package org.sdmlib.models.classes;

import org.sdmlib.models.classes.logic.ClassModelAdapter;
import org.sdmlib.models.classes.logic.GenAttribute;
import org.sdmlib.models.classes.util.AttributeSet;
import org.sdmlib.models.classes.util.AnnotationSet;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
*/
   public class Attribute extends Value implements AnnotationOwner
{
   public static final String PROPERTY_CLAZZ = "clazz";
   public static final AttributeSet EMPTY_SET = new AttributeSet().withReadOnly(true);

   private Clazz clazz = null;
   private Modifier visibility = Modifier.PRIVATE;
   
   public Attribute(String name, Type type)
   {
      this.name = name;
      
      this.type = DataType.ref(type);
   }

   public Clazz getClazz()
   {
      return this.clazz;
   }

   public boolean setClazz(Clazz value)
   {
      if (this.clazz != value)
      {
         Clazz oldValue = this.clazz;

         if (this.clazz != null)
         {
            this.clazz = null;
            oldValue.without(this);
         }

         this.clazz = value;

         if (value != null)
         {
            value.with(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLAZZ, oldValue, value);
         return true;
      }

      return false;
   }

   public Attribute with(Clazz value)
   {
      setClazz(value);
      return this;
   } 


   @Override
   public String toString()
   {
      //      StringBuilder _ = new StringBuilder();
      //      s.append(" ").append(this.getInitialization());
      //      s.append(" ").append(this.getName());
      return "" + name + " : " + type;
   }
   
   //==========================================================================
   @Override
   public void removeYou()
   {
      super.removeYou();
      setClazz(null);
      withoutAnnotation(this.getAnnotations().toArray(new Annotation[this.getAnnotations().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
   
   // OVERRIDE
   @Override
   public Attribute withName(String string)
   {
      setName(string);
      return this;
   }

   
   @Override
   public Attribute with(DataType value)
   {
      setType(value);
      return this;
   } 
   
   @Override
   public Attribute withInitialization(String value){
      setInitialization(value);
      return this;
   }

   public Modifier getVisibility()
   {
      return visibility;
   }

   public Attribute with(Modifier... visibility)
   {
      if(visibility==null){
         return this;
      }
      
      if(visibility.length==1){
         this.visibility = visibility[0];
      }
      this.visibility = Modifier.ref(visibility);
      return this;
   }

   Attribute withClazz(Clazz value)
   {
      setClazz(value);
      return this;
   } 

   Clazz createClazz()
   {
      Clazz value = new Clazz(null);
      withClazz(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Attribute ----------------------------------- Annotation
    *              attribute                   annotations
    * </pre>
    */
   
   public static final String PROPERTY_ANNOTATIONS = "annotations";

   private AnnotationSet annotations = null;
   
   public AnnotationSet getAnnotations()
   {
      if (this.annotations == null)
      {
         return AnnotationSet.EMPTY_SET;
      }
   
      return this.annotations;
   }

   public Attribute withAnnotation(Annotation... value)
   {
      if(value==null){
         return this;
      }
      for (Annotation item : value)
      {
         if (item != null)
         {
            if (this.annotations == null)
            {
               this.annotations = new AnnotationSet();
            }
            
            boolean changed = this.annotations.add (item);

            if (changed)
            {
               item.withOwner(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ANNOTATIONS, null, item);
            }
         }
      }
      return this;
   } 

   public Attribute withoutAnnotation(Annotation... value)
   {
      for (Annotation item : value)
      {
         if ((this.annotations != null) && (item != null))
         {
            if (this.annotations.remove(item))
            {
               item.setOwner(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_ANNOTATIONS, item, null);
            }
         }
      }
      return this;
   }

   public Annotation createAnnotations()
   {
      Annotation value = new Annotation();
      withAnnotation(value);
      return value;
   }

   /**
    * Removes this attribute from the current model and deletes
    * the generated code from the model and util classes.<br> 
    * This includes the set, creator and pattern object classes, that are associated with this attribute.
    * 
    * 
    * @param rootDir root directory, where the code of the attribute is located
    */
   public void removeFromModelAndCode(String rootDir) {

	   ClassModelAdapter generator = this.getClazz().getClassModel().getGenerator();
	   
	   GenAttribute genAttribute = generator.getOrCreate(this);

	   genAttribute.removeGeneratedCode(rootDir);
	   
	   this.removeYou();
	   
   } 
}
