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

package org.sdmlib.examples.m2m;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.examples.m2m.creators.PersonSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.m2m.creators.RelationSet;
import org.sdmlib.examples.m2m.creators.GraphComponentSet;

public class Graph implements PropertyChangeInterface
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_PERSONS.equalsIgnoreCase(attrName))
      {
         return getPersons();
      }

      if (PROPERTY_RELATIONS.equalsIgnoreCase(attrName))
      {
         return getRelations();
      }

      if (PROPERTY_GCS.equalsIgnoreCase(attrName))
      {
         return getGcs();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_PERSONS.equalsIgnoreCase(attrName))
      {
         addToPersons((Person) value);
         return true;
      }

      if ((PROPERTY_PERSONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromPersons((Person) value);
         return true;
      }

      if (PROPERTY_RELATIONS.equalsIgnoreCase(attrName))
      {
         addToRelations((Relation) value);
         return true;
      }

      if ((PROPERTY_RELATIONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromRelations((Relation) value);
         return true;
      }

      if (PROPERTY_GCS.equalsIgnoreCase(attrName))
      {
         addToGcs((GraphComponent) value);
         return true;
      }

      if ((PROPERTY_GCS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromGcs((GraphComponent) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   // ==========================================================================

   public void removeYou()
   {
      removeAllFromPersons();
      removeAllFromRelations();
      removeAllFromGcs();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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

         changed = this.persons.add(value);

         if (changed)
         {
            value.withGraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS,
               null, value);
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
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS,
               value, null);
         }
      }

      return changed;
   }

   public Graph withPersons(Person... value)
   {
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
      LinkedHashSet<Person> tmpSet = new LinkedHashSet<Person>(
            this.getPersons());

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

         changed = this.relations.add(value);

         if (changed)
         {
            value.withGraph(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RELATIONS,
               null, value);
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
            getPropertyChangeSupport().firePropertyChange(PROPERTY_RELATIONS,
               value, null);
         }
      }

      return changed;
   }

   public Graph withRelations(Relation... value)
   {
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
      LinkedHashSet<Relation> tmpSet = new LinkedHashSet<Relation>(
            this.getRelations());

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

         changed = this.gcs.add(value);

         if (changed)
         {
            value.withParent(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_GCS, null,
               value);
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
            getPropertyChangeSupport().firePropertyChange(PROPERTY_GCS, value,
               null);
         }
      }

      return changed;
   }

   public Graph withGcs(GraphComponent... value)
   {
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
      LinkedHashSet<GraphComponent> tmpSet = new LinkedHashSet<GraphComponent>(
            this.getGcs());

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
}
