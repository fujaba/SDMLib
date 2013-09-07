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
   
package org.sdmlib.model.test.consistence.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.test.consistence.Border;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.model.test.consistence.creators.FieldSet;
import org.sdmlib.model.test.consistence.Field;

public class BorderSet extends LinkedHashSet<Border> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Border elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.test.consistence.Border";
   }


   public BorderSet with(Border value)
   {
      this.add(value);
      return this;
   }
   
   public BorderSet without(Border value)
   {
      this.remove(value);
      return this;
   }
   public StringList getBorderLocation()
   {
      StringList result = new StringList();
      
      for (Border obj : this)
      {
         result.add(obj.getBorderLocation());
      }
      
      return result;
   }

   public BorderSet withBorderLocation(String value)
   {
      for (Border obj : this)
      {
         obj.setBorderLocation(value);
      }
      
      return this;
   }

   public StringList getConnectedTo()
   {
      StringList result = new StringList();
      
      for (Border obj : this)
      {
         result.add(obj.getConnectedTo());
      }
      
      return result;
   }

   public BorderSet withConnectedTo(String value)
   {
      for (Border obj : this)
      {
         obj.setConnectedTo(value);
      }
      
      return this;
   }

   public StringList getConnectedToRev()
   {
      StringList result = new StringList();
      
      for (Border obj : this)
      {
         result.add(obj.getConnectedToRev());
      }
      
      return result;
   }

   public BorderSet withConnectedToRev(String value)
   {
      for (Border obj : this)
      {
         obj.setConnectedToRev(value);
      }
      
      return this;
   }

   public StringList getServerId()
   {
      StringList result = new StringList();
      
      for (Border obj : this)
      {
         result.add(obj.getServerId());
      }
      
      return result;
   }

   public BorderSet withServerId(String value)
   {
      for (Border obj : this)
      {
         obj.setServerId(value);
      }
      
      return this;
   }

   public StringList getRoad()
   {
      StringList result = new StringList();
      
      for (Border obj : this)
      {
         result.add(obj.getRoad());
      }
      
      return result;
   }

   public BorderSet withRoad(String value)
   {
      for (Border obj : this)
      {
         obj.setRoad(value);
      }
      
      return this;
   }

   public FieldSet getField()
   {
      FieldSet result = new FieldSet();
      
      for (Border obj : this)
      {
         result.add(obj.getField());
      }
      
      return result;
   }

   public BorderSet withField(Field value)
   {
      for (Border obj : this)
      {
         obj.withField(value);
      }
      
      return this;
   }

   public BorderSet withConnectedTo(Border value)
   {
      for (Border obj : this)
      {
         // obj.withConnectedTo(value);
      }
      
      return this;
   }

   public BorderSet withConnectedToRev(Border value)
   {
      for (Border obj : this)
      {
         // obj.withConnectedToRev(value);
      }
      
      return this;
   }

}


