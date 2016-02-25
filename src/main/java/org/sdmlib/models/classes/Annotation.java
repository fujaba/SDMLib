/*
   Copyright (c) 2016 zuendorf
   
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

import org.sdmlib.models.classes.SDMLibClass;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
 */
   public  class Annotation extends SDMLibClass
{

   
   //==========================================================================
   public Annotation createOverrideAnnotation(  )
   {
      return null;
   }

   
   //==========================================================================
   public Annotation createDeprecatedAnnotation(  )
   {
      return null;
   }

   
   //==========================================================================
   public Annotation createSuppressWarningsAnnotation(Object...  values )
   {
      return null;
   }

   
   //==========================================================================
   public Annotation createSafeVarargsAnnotation(  )
   {
      return null;
   }

   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
   
      super.removeYou();

      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_DEPRECATED = "DEPRECATED";
   
   public String DEPRECATED = "Deprecated";


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.DEPRECATED);
      result.append(" ").append(this.OVERRIDE);
      result.append(" ").append(this.SAFE_VARGARGS);
      result.append(" ").append(this.getName());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_OVERRIDE = "OVERRIDE";
   
   public String OVERRIDE = "Override";

   
   //==========================================================================
   
   public static final String PROPERTY_SAFE_VARGARGS = "SAFE_VARGARGS";
   
   public String SAFE_VARGARGS = "SafeVarargs";

   
   //==========================================================================
   
   public String SUPPRESS_WARNINGS = "SuppressWarnings";
}
