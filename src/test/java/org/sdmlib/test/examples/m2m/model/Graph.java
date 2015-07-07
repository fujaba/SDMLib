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
   
package org.sdmlib.test.examples.m2m.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.LinkedHashSet;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.m2m.model.Person;
import org.sdmlib.test.examples.m2m.model.Relation;
import org.sdmlib.test.examples.m2m.model.util.GraphComponentSet;
import org.sdmlib.test.examples.m2m.model.util.PersonSet;
import org.sdmlib.test.examples.m2m.model.util.RelationSet;

public class Graph implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      removeAllFromGcs();
      removeAllFromPersons();
      removeAllFromRelations();
      withoutGcs(this.getGcs().toArray(new GraphComponent[this.getGcs().size()]));
      withoutPersons(this.getPersons().toArray(new Person[this.getPersons().size()]));
      withoutRelations(this.getRelations().toArray(new Relation[this.getRelations().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Graph ----------------------------------- GraphComponent
    *              parent                   gcs
    * </pre>
    */
   
   public static final String PROPERTY_GCS = "gcs";

   private GraphComponentSet gcs = null;
   
   public GraphComponentSet getGcs()
   {
      if (this.gcs == null)
      {
         return GraphComponent.EMPTY_SET;
      }
   
      return this.gcs;
   }

   public boolean addToGcs(GraphComponent value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.gcs == null)
         {
            this.gcs = new GraphComponentSet();
         }
         
         changed = this.gcs.add (value);
         
         if (changed)
         {
            value.withParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_GCS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromGcs(GraphComponent value)
   {
      boolean changed = false;
      
      if ((this.gcs != null) && (value != null))
      {
         changed = this.gcs.remove(value);
         
         if (changed)
         {
            value.setParent(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_GCS, value, null);
         }
      }
         
      return changed;   
   }

   public Graph withGcs(GraphComponent... value)
   {
      if(value==null){
         return this;
      }
      for (GraphComponent item : value)
      {
         addToGcs(item);
      }
      return this;
   } 

   public Graph withoutGcs(GraphComponent... value)
   {
      for (GraphComponent item : value)
      {
         removeFromGcs(item);
      }
      return this;
   }

   public void removeAllFromGcs()
   {
      LinkedHashSet<GraphComponent> tmpSet = new LinkedHashSet<GraphComponent>(this.getGcs());
   
      for (GraphComponent value : tmpSet)
      {
         this.removeFromGcs(value);
      }
   }

   public GraphComponent createGcs()
   {
      GraphComponent value = new GraphComponent();
      withGcs(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Graph ----------------------------------- Person
    *              graph                   persons
    * </pre>
    */
   
   public static final String PROPERTY_PERSONS = "persons";

   private PersonSet persons = null;
   
   public PersonSet getPersons()
   {
      if (this.persons == null)
      {
         return Person.EMPTY_SET;
      }
   
      return this.persons;
   }

   public boolean addToPersons(Person value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.persons == null)
         {
            this.persons = new PersonSet();
         }
         
         changed = this.persons.add (value);
         
         if (changed)
         {
            value.withGraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromPersons(Person value)
   {
      boolean changed = false;
      
      if ((this.persons != null) && (value != null))
      {
         changed = this.persons.remove(value);
         
         if (changed)
         {
            value.setGraph(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS, value, null);
         }
      }
         
      return changed;   
   }

   public Graph withPersons(Person... value)
   {
      if(value==null){
         return this;
      }
      for (Person item : value)
      {
         addToPersons(item);
      }
      return this;
   } 

   public Graph withoutPersons(Person... value)
   {
      for (Person item : value)
      {
         removeFromPersons(item);
      }
      return this;
   }

   public void removeAllFromPersons()
   {
      LinkedHashSet<Person> tmpSet = new LinkedHashSet<Person>(this.getPersons());
   
      for (Person value : tmpSet)
      {
         this.removeFromPersons(value);
      }
   }

   public Person createPersons()
   {
      Person value = new Person();
      withPersons(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Graph ----------------------------------- Relation
    *              graph                   relations
    * </pre>
    */
   
   public static final String PROPERTY_RELATIONS = "relations";

   private RelationSet relations = null;
   
   public RelationSet getRelations()
   {
      if (this.relations == null)
      {
         return Relation.EMPTY_SET;
      }
   
      return this.relations;
   }

   public boolean addToRelations(Relation value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.relations == null)
         {
            this.relations = new RelationSet();
         }
         
         changed = this.relations.add (value);
         
         if (changed)
         {
            value.withGraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RELATIONS, null, value);
         }
      }
         
      return changed;   
   }

   public boolean removeFromRelations(Relation value)
   {
      boolean changed = false;
      
      if ((this.relations != null) && (value != null))
      {
         changed = this.relations.remove(value);
         
         if (changed)
         {
            value.setGraph(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RELATIONS, value, null);
         }
      }
         
      return changed;   
   }

   public Graph withRelations(Relation... value)
   {
      if(value==null){
         return this;
      }
      for (Relation item : value)
      {
         addToRelations(item);
      }
      return this;
   } 

   public Graph withoutRelations(Relation... value)
   {
      for (Relation item : value)
      {
         removeFromRelations(item);
      }
      return this;
   }

   public void removeAllFromRelations()
   {
      LinkedHashSet<Relation> tmpSet = new LinkedHashSet<Relation>(this.getRelations());
   
      for (Relation value : tmpSet)
      {
         this.removeFromRelations(value);
      }
   }

   public Relation createRelations()
   {
      Relation value = new Relation();
      withRelations(value);
      return value;
   } 

   public GraphComponent createGcsPerson()
   {
      GraphComponent value = new Person();
      withGcs(value);
      return value;
   } 

   public GraphComponent createGcsRelation()
   {
      GraphComponent value = new Relation();
      withGcs(value);
      return value;
   } 

   public Person createPerson()
   {
      Person value = new Person();
      withGcs(value);
      return value;
   } 

   public Relation createRelation()
   {
      Relation value = new Relation();
      withGcs(value);
      return value;
   } 
}

