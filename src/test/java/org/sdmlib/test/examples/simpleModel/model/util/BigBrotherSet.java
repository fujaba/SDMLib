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
   
package org.sdmlib.test.examples.simpleModel.model.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.simpleModel.model.BigBrother;
import org.sdmlib.test.examples.simpleModel.model.Person;
import org.sdmlib.test.examples.simpleModel.model.util.PersonSet;

public class BigBrotherSet extends SDMSet<BigBrother>
{

   public static final BigBrotherSet EMPTY_SET = new BigBrotherSet().withReadOnly(true);


   public BigBrotherPO hasBigBrotherPO()
   {
      return new BigBrotherPO(this.toArray(new BigBrother[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.test.examples.simpleModel.model.BigBrother";
   }


   @SuppressWarnings("unchecked")
   public BigBrotherSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<BigBrother>)value);
      }
      else if (value != null)
      {
         this.add((BigBrother) value);
      }
      
      return this;
   }
   
   public BigBrotherSet without(BigBrother value)
   {
      this.remove(value);
      return this;
   }

   public ObjectSet getKids()
   {
      ObjectSet result = new ObjectSet();
      
      for (BigBrother obj : this)
      {
         result.addAll(obj.getKids());
      }
      
      return result;
   }

   public BigBrotherSet hasKids(Object value)
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
      
      BigBrotherSet answer = new BigBrotherSet();
      
      for (BigBrother obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getKids()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public BigBrotherSet withKids(Object value)
   {
      for (BigBrother obj : this)
      {
         obj.withKids(value);
      }
      
      return this;
   }

   public BigBrotherSet withoutKids(Object value)
   {
      for (BigBrother obj : this)
      {
         obj.withoutKids(value);
      }
      
      return this;
   }

   public PersonSet getNoOne()
   {
      PersonSet result = new PersonSet();
      
      for (BigBrother obj : this)
      {
         result.add(obj.getNoOne());
      }
      
      return result;
   }

   public BigBrotherSet hasNoOne(Object value)
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
      
      BigBrotherSet answer = new BigBrotherSet();
      
      for (BigBrother obj : this)
      {
         if (neighbors.contains(obj.getNoOne()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public BigBrotherSet withNoOne(Person value)
   {
      for (BigBrother obj : this)
      {
         obj.withNoOne(value);
      }
      
      return this;
   }

   public PersonSet getSuspects()
   {
      PersonSet result = new PersonSet();
      
      for (BigBrother obj : this)
      {
         result.addAll(obj.getSuspects());
      }
      
      return result;
   }

   public BigBrotherSet hasSuspects(Object value)
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
      
      BigBrotherSet answer = new BigBrotherSet();
      
      for (BigBrother obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSuspects()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public BigBrotherSet withSuspects(Person value)
   {
      for (BigBrother obj : this)
      {
         obj.withSuspects(value);
      }
      
      return this;
   }

   public BigBrotherSet withoutSuspects(Person value)
   {
      for (BigBrother obj : this)
      {
         obj.withoutSuspects(value);
      }
      
      return this;
   }

}
