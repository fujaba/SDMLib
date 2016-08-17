/*
   Copyright (c) 2016 deeptought
   
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
   
package org.sdmlib.modelcouch.util;

import java.util.Collection;

import org.sdmlib.modelcouch.ModelCouch;
import org.sdmlib.modelcouch.ModelDBListener;
import de.uniks.networkparser.list.ObjectSet;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.modelcouch.util.ModelCouchSet;

public class ModelDBListenerSet extends SimpleSet<ModelDBListener>
{

   public static final ModelDBListenerSet EMPTY_SET = new ModelDBListenerSet().withFlag(ModelDBListenerSet.READONLY);


   public ModelDBListenerPO hasModelDBListenerPO()
   {
      return new ModelDBListenerPO(this.toArray(new ModelDBListener[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.modelcouch.ModelDBListener";
   }


   @SuppressWarnings("unchecked")
   public ModelDBListenerSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ModelDBListener>)value);
      }
      else if (value != null)
      {
         this.add((ModelDBListener) value);
      }
      
      return this;
   }
   
   public ModelDBListenerSet without(ModelDBListener value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of ModelDBListener objects and collect a set of the ModelCouch objects reached via couch. 
    * 
    * @return Set of ModelCouch objects reachable via couch
    */
   public ModelCouchSet getCouch()
   {
      ModelCouchSet result = new ModelCouchSet();
      
      for (ModelDBListener obj : this)
      {
         result.add(obj.getCouch());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ModelDBListener objects and collect all contained objects with reference couch pointing to the object passed as parameter. 
    * 
    * @param value The object required as couch neighbor of the collected results. 
    * 
    * @return Set of ModelCouch objects referring to value via couch
    */
   public ModelDBListenerSet hasCouch(Object value)
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
      
      ModelDBListenerSet answer = new ModelDBListenerSet();
      
      for (ModelDBListener obj : this)
      {
         if (neighbors.contains(obj.getCouch()) || (neighbors.isEmpty() && obj.getCouch() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ModelDBListener object passed as parameter to the Couch attribute of each of it. 
    * 
    * @param value the couch
    * @return The original set of ModelType objects now with the new neighbor attached to their Couch attributes.
    */
   public ModelDBListenerSet withCouch(ModelCouch value)
   {
      for (ModelDBListener obj : this)
      {
         obj.withCouch(value);
      }
      
      return this;
   }



   public ModelDBListenerPO filterModelDBListenerPO()
   {
      return new ModelDBListenerPO(this.toArray(new ModelDBListener[this.size()]));
   }

   public ModelDBListenerSet()
   {
      // empty
   }

   public ModelDBListenerSet(ModelDBListener... objects)
   {
      for (ModelDBListener obj : objects)
      {
         this.add(obj);
      }
   }

   public ModelDBListenerSet(Collection<ModelDBListener> objects)
   {
      this.addAll(objects);
   }
}
