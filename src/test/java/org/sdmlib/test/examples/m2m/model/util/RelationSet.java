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
   
package org.sdmlib.test.examples.m2m.model.util;

import java.util.Collection;

import org.sdmlib.test.examples.m2m.model.Graph;
import org.sdmlib.test.examples.m2m.model.Person;
import org.sdmlib.test.examples.m2m.model.Relation;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.test.examples.m2m.model.util.GraphSet;
import org.sdmlib.test.examples.m2m.model.util.PersonSet;

public class RelationSet extends SimpleSet<Relation>
{


   public RelationPO hasRelationPO()
   {
      return new RelationPO(this.toArray(new Relation[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public RelationSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Relation>)value);
      }
      else if (value != null)
      {
         this.add((Relation) value);
      }
      
      return this;
   }
   
   public RelationSet without(Relation value)
   {
      this.remove(value);
      return this;
   }

   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (Relation obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }

   public RelationSet hasKind(String value)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (value.equals(obj.getKind()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public RelationSet withKind(String value)
   {
      for (Relation obj : this)
      {
         obj.setKind(value);
      }
      
      return this;
   }

   public GraphSet getGraph()
   {
      GraphSet result = new GraphSet();
      
      for (Relation obj : this)
      {
         result.add(obj.getGraph());
      }
      
      return result;
   }

   public RelationSet hasGraph(Object value)
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
      
      RelationSet answer = new RelationSet();
      
      for (Relation obj : this)
      {
         if (neighbors.contains(obj.getGraph()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RelationSet withGraph(Graph value)
   {
      for (Relation obj : this)
      {
         obj.withGraph(value);
      }
      
      return this;
   }

   public PersonSet getSrc()
   {
      PersonSet result = new PersonSet();
      
      for (Relation obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }

   public RelationSet hasSrc(Object value)
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
      
      RelationSet answer = new RelationSet();
      
      for (Relation obj : this)
      {
         if (neighbors.contains(obj.getSrc()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RelationSet withSrc(Person value)
   {
      for (Relation obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   public PersonSet getTgt()
   {
      PersonSet result = new PersonSet();
      
      for (Relation obj : this)
      {
         result.add(obj.getTgt());
      }
      
      return result;
   }

   public RelationSet hasTgt(Object value)
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
      
      RelationSet answer = new RelationSet();
      
      for (Relation obj : this)
      {
         if (neighbors.contains(obj.getTgt()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RelationSet withTgt(Person value)
   {
      for (Relation obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (Relation obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public RelationSet hasText(String value)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public RelationSet withText(String value)
   {
      for (Relation obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

   public GraphSet getParent()
   {
      GraphSet result = new GraphSet();
      
      for (Relation obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public RelationSet hasParent(Object value)
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
      
      RelationSet answer = new RelationSet();
      
      for (Relation obj : this)
      {
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RelationSet withParent(Graph value)
   {
      for (Relation obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }


   public static final RelationSet EMPTY_SET = new RelationSet().withFlag(RelationSet.READONLY);
   public RelationSet hasKind(String lower, String upper)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (lower.compareTo(obj.getKind()) <= 0 && obj.getKind().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public RelationSet hasText(String lower, String upper)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }



   public RelationPO filterRelationPO()
   {
      return new RelationPO(this.toArray(new Relation[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.m2m.model.Relation";
   }

   /**
    * Loop through the current set of Relation objects and collect those Relation objects where the kind attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Relation objects that match the parameter
    */
   public RelationSet filterKind(String value)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (value.equals(obj.getKind()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Relation objects and collect those Relation objects where the kind attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Relation objects that match the parameter
    */
   public RelationSet filterKind(String lower, String upper)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (lower.compareTo(obj.getKind()) <= 0 && obj.getKind().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Relation objects and collect those Relation objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Relation objects that match the parameter
    */
   public RelationSet filterText(String value)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Relation objects and collect those Relation objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Relation objects that match the parameter
    */
   public RelationSet filterText(String lower, String upper)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public RelationSet()
   {
      // empty
   }

   public RelationSet(Relation... objects)
   {
      for (Relation obj : objects)
      {
         this.add(obj);
      }
   }

   public RelationSet(Collection<Relation> objects)
   {
      this.addAll(objects);
   }


   public RelationPO createRelationPO()
   {
      return new RelationPO(this.toArray(new Relation[this.size()]));
   }


   @Override
   public RelationSet getNewList(boolean keyValue)
   {
      return new RelationSet();
   }

   /**
    * Loop through the current set of Relation objects and collect those Relation objects where the kind attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Relation objects that match the parameter
    */
   public RelationSet createKindCondition(String value)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (value.equals(obj.getKind()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Relation objects and collect those Relation objects where the kind attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Relation objects that match the parameter
    */
   public RelationSet createKindCondition(String lower, String upper)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (lower.compareTo(obj.getKind()) <= 0 && obj.getKind().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Relation objects and collect those Relation objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Relation objects that match the parameter
    */
   public RelationSet createTextCondition(String value)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Relation objects and collect those Relation objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Relation objects that match the parameter
    */
   public RelationSet createTextCondition(String lower, String upper)
   {
      RelationSet result = new RelationSet();
      
      for (Relation obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
