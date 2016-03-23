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
   
package org.sdmlib.test.examples.studyright.model.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.test.examples.studyright.model.Female;

import de.uniks.networkparser.list.SimpleSet;

public class FemaleSet extends SimpleSet<Female>
{
   public FemalePO hasFemalePO()
   {
      return new FemalePO(this.toArray(new Female[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public FemaleSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Female>)value);
      }
      else if (value != null)
      {
         this.add((Female) value);
      }
      
      return this;
   }
   
   public FemaleSet without(Female value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public FemaleSet findMyPosition()
   {
      for (Female obj : this)
      {
         obj.findMyPosition();
      }
      return this;
   }

   
   //==========================================================================
   
   public FemaleSet findMyPosition(String p0)
   {
      for (Female obj : this)
      {
         obj.findMyPosition(p0);
      }
      return this;
   }

   
   //==========================================================================
   
   public FemaleSet findMyPosition(String p0, int p1)
   {
      for (Female obj : this)
      {
         obj.findMyPosition(p0, p1);
      }
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Female obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public FemaleSet hasName(String value)
   {
      FemaleSet result = new FemaleSet();
      
      for (Female obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FemaleSet withName(String value)
   {
      for (Female obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   public static final FemaleSet EMPTY_SET = new FemaleSet().withFlag(FemaleSet.READONLY);
   public FemaleSet hasName(String lower, String upper)
   {
      FemaleSet result = new FemaleSet();
      
      for (Female obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public FemalePO filterFemalePO()
   {
      return new FemalePO(this.toArray(new Female[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.studyright.model.Female";
   }

   /**
    * Loop through the current set of Female objects and collect those Female objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Female objects that match the parameter
    */
   public FemaleSet filterName(String value)
   {
      FemaleSet result = new FemaleSet();
      
      for (Female obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Female objects and collect those Female objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Female objects that match the parameter
    */
   public FemaleSet filterName(String lower, String upper)
   {
      FemaleSet result = new FemaleSet();
      
      for (Female obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
