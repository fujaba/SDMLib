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
   
package org.sdmlib.examples.clickcounter.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.clickcounter.Data;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.examples.clickcounter.creators.IntegerPropertySet;
import javafx.beans.property.IntegerProperty;

public class DataSet extends LinkedHashSet<Data> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Data elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.clickcounter.Data";
   }


   public DataSet with(Data value)
   {
      this.add(value);
      return this;
   }
   
   public DataSet without(Data value)
   {
      this.remove(value);
      return this;
   }
   public intList getNum()
   {
      intList result = new intList();
      
      for (Data obj : this)
      {
         result.add(obj.getNum());
      }
      
      return result;
   }

   public DataSet withNum(int value)
   {
      for (Data obj : this)
      {
         obj.setNum(value);
      }
      
      return this;
   }

   public IntegerPropertySet getFxnum()
   {
      IntegerPropertySet result = new IntegerPropertySet();
      
      for (Data obj : this)
      {
         result.add(obj.getFxnum());
      }
      
      return result;
   }

   public DataSet withFxnum(IntegerProperty value)
   {
      for (Data obj : this)
      {
         obj.setFxnum(value);
      }
      
      return this;
   }

}


