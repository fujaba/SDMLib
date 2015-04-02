/*
   Copyright (c) 2015 zuendorf 
   
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
   
package org.sdmlib.examples.annotations.model.simple.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.annotations.model.simple.Cube;
import java.util.Collection;

public class CubeSet extends SDMSet<Cube>
{

   public static final CubeSet EMPTY_SET = new CubeSet().withReadOnly(true);


   public CubePO hasCubePO()
   {
      return new CubePO(this.toArray(new Cube[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.annotations.model.simple.Cube";
   }


   @SuppressWarnings("unchecked")
   public CubeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Cube>)value);
      }
      else if (value != null)
      {
         this.add((Cube) value);
      }
      
      return this;
   }
   
   public CubeSet without(Cube value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public CubeSet init()
   {
      for (Cube obj : this)
      {
         obj.init();
      }
      return this;
   }

}
