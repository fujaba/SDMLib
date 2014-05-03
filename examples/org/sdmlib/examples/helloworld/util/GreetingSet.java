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
   
package org.sdmlib.examples.helloworld.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.models.modelsets.StringList;
import java.util.Collection;
import java.util.List;

public class GreetingSet extends SDMSet<Greeting>
{


   public GreetingPO hasGreetingPO()
   {
      org.sdmlib.examples.helloworld.util.ModelPattern pattern = new org.sdmlib.examples.helloworld.util.ModelPattern();
      
      GreetingPO patternObject = pattern.hasElementGreetingPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.helloworld.Greeting";
   }


   public GreetingSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Greeting>)value);
      }
      else if (value != null)
      {
         this.add((Greeting) value);
      }
      
      return this;
   }
   
   public GreetingSet without(Greeting value)
   {
      this.remove(value);
      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (Greeting obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public GreetingSet hasText(String value)
   {
      GreetingSet result = new GreetingSet();
      
      for (Greeting obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GreetingSet withText(String value)
   {
      for (Greeting obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

}

