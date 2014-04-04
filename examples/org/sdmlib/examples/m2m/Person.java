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
import org.sdmlib.utils.StrUtil;
import org.sdmlib.examples.m2m.creators.PersonSet;
import org.sdmlib.examples.m2m.creators.RelationSet;
import java.util.LinkedHashSet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.m2m.GraphComponent;

public class Person extends GraphComponent implements PropertyChangeInterface
{

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_FIRSTNAME.equalsIgnoreCase(attrName))
      {
         return getFirstName();
      }

      if (PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         return getGraph();
      }

      if (PROPERTY_OUTEDGES.equalsIgnoreCase(attrName))
      {
         return getOutEdges();
      }

      if (PROPERTY_INEDGES.equalsIgnoreCase(attrName))
      {
         return getInEdges();
      }

      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return getText();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }

      if (PROPERTY_KNOWS.equalsIgnoreCase(attrName))
      {
         return getKnows();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_FIRSTNAME.equalsIgnoreCase(attrName))
      {
         setFirstName((String) value);
         return true;
      }

      if (PROPERTY_GRAPH.equalsIgnoreCase(attrName))
      {
         setGraph((Graph) value);
         return true;
      }

      if (PROPERTY_OUTEDGES.equalsIgnoreCase(attrName))
      {
         addToOutEdges((Relation) value);
         return true;
      }

      if ((PROPERTY_OUTEDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromOutEdges((Relation) value);
         return true;
      }

      if (PROPERTY_INEDGES.equalsIgnoreCase(attrName))
      {
         addToInEdges((Relation) value);
         return true;
      }

      if ((PROPERTY_INEDGES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromInEdges((Relation) value);
         return true;
      }

      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         setText((String) value);
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((Graph) value);
         return true;
      }

      if (PROPERTY_KNOWS.equalsIgnoreCase(attrName))
      {
         addToKnows((Person) value);
         return true;
      }

      if ((PROPERTY_KNOWS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromKnows((Person) value);
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
      setGraph(null);
      removeAllFromOutEdges();
      removeAllFromInEdges();
      setParent(null);
      removeAllFromKnows();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   // ==========================================================================

   public static final String PROPERTY_FIRSTNAME = "firstName";

   private String firstName;

   public String getFirstName()
   {
      return this.firstName;
   }

   public void setFirstName(String value)
   {
      if (!StrUtil.stringEquals(this.firstName, value))
      {
         String oldValue = this.firstName;
         this.firstName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FIRSTNAME,
            oldValue, value);
      }
   }

   public Person withFirstName(String value)
   {
      setFirstName(value);
      return this;
   }

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getFirstName());
      _.append(" ").append(this.getText());
      return _.substring(1);
   }

   public static final PersonSet EMPTY_SET = new PersonSet();

   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- Graph
    *              persons                   graph
    * </pre>
    */

   public static final String PROPERTY_GRAPH = "graph";

   private Graph graph = null;

   public Graph getGraph()
   {
      return this.graph;
   }

   public boolean setGraph(Graph value)
   {
      boolean changed = false;

      if (this.graph != value)
      {
         Graph oldValue = this.graph;

         if (this.graph != null)
         {
            this.graph = null;
            oldValue.withoutPersons(this);
         }

         this.graph = value;

         if (value != null)
         {
            value.withPersons(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_GRAPH,
            oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Person withGraph(Graph value)
   {
      setGraph(value);
      return this;
   }

   public Graph createGraph()
   {
      Graph value = new Graph();
      withGraph(value);
      return value;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Person ----------------------------------- Relation
    *              src                   outEdges
    * </pre>
    */

   public static final String PROPERTY_OUTEDGES = "outEdges";

   private RelationSet outEdges = null;

   public RelationSet getOutEdges()
   {
      if (this.outEdges == null)
      {
         return Relation.EMPTY_SET;
      }

      return this.outEdges;
   }

   public boolean addToOutEdges(Relation value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.outEdges == null)
         {
            this.outEdges = new RelationSet();
         }

         changed = this.outEdges.add(value);

         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTEDGES,
               null, value);
         }
      }

      return changed;
   }

   public boolean removeFromOutEdges(Relation value)
   {
      boolean changed = false;

      if ((this.outEdges != null) && (value != null))
      {
         changed = this.outEdges.remove(value);

         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTEDGES,
               value, null);
         }
      }

      return changed;
   }

   public Person withOutEdges(Relation... value)
   {
      for (Relation item : value)
      {
         addToOutEdges(item);
      }
      return this;
   }

   public Person withoutOutEdges(Relation... value)
   {
      for (Relation item : value)
      {
         removeFromOutEdges(item);
      }
      return this;
   }

   public void removeAllFromOutEdges()
   {
      LinkedHashSet<Relation> tmpSet = new LinkedHashSet<Relation>(
            this.getOutEdges());

      for (Relation value : tmpSet)
      {
         this.removeFromOutEdges(value);
      }
   }

   public Relation createOutEdges()
   {
      Relation value = new Relation();
      withOutEdges(value);
      return value;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Person ----------------------------------- Relation
    *              tgt                   inEdges
    * </pre>
    */

   public static final String PROPERTY_INEDGES = "inEdges";

   private RelationSet inEdges = null;

   public RelationSet getInEdges()
   {
      if (this.inEdges == null)
      {
         return Relation.EMPTY_SET;
      }

      return this.inEdges;
   }

   public boolean addToInEdges(Relation value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.inEdges == null)
         {
            this.inEdges = new RelationSet();
         }

         changed = this.inEdges.add(value);

         if (changed)
         {
            value.withTgt(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INEDGES,
               null, value);
         }
      }

      return changed;
   }

   public boolean removeFromInEdges(Relation value)
   {
      boolean changed = false;

      if ((this.inEdges != null) && (value != null))
      {
         changed = this.inEdges.remove(value);

         if (changed)
         {
            value.setTgt(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INEDGES,
               value, null);
         }
      }

      return changed;
   }

   public Person withInEdges(Relation... value)
   {
      for (Relation item : value)
      {
         addToInEdges(item);
      }
      return this;
   }

   public Person withoutInEdges(Relation... value)
   {
      for (Relation item : value)
      {
         removeFromInEdges(item);
      }
      return this;
   }

   public void removeAllFromInEdges()
   {
      LinkedHashSet<Relation> tmpSet = new LinkedHashSet<Relation>(
            this.getInEdges());

      for (Relation value : tmpSet)
      {
         this.removeFromInEdges(value);
      }
   }

   public Relation createInEdges()
   {
      Relation value = new Relation();
      withInEdges(value);
      return value;
   }

   // ==========================================================================

   public static final String PROPERTY_TEXT = "text";

   private String text;

   public String getText()
   {
      return this.text;
   }

   public void setText(String value)
   {
      if (!StrUtil.stringEquals(this.text, value))
      {
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue,
            value);
      }
   }

   public Person withText(String value)
   {
      setText(value);
      return this;
   }

   /********************************************************************
    * <pre>
    *              many                       many
    * Person ----------------------------------- Person
    *              knows                   knows
    * </pre>
    */

   public static final String PROPERTY_KNOWS = "knows";

   private PersonSet knows = null;

   public PersonSet getKnows()
   {
      if (this.knows == null)
      {
         return Person.EMPTY_SET;
      }

      return this.knows;
   }

   public boolean addToKnows(Person value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.knows == null)
         {
            this.knows = new PersonSet();
         }

         changed = this.knows.add(value);

         if (changed)
         {
            value.withKnows(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KNOWS, null,
               value);
         }
      }

      return changed;
   }

   public boolean removeFromKnows(Person value)
   {
      boolean changed = false;

      if ((this.knows != null) && (value != null))
      {
         changed = this.knows.remove(value);

         if (changed)
         {
            value.withoutKnows(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KNOWS,
               value, null);
         }
      }

      return changed;
   }

   public Person withKnows(Person... value)
   {
      for (Person item : value)
      {
         addToKnows(item);
      }
      return this;
   }

   public Person withoutKnows(Person... value)
   {
      for (Person item : value)
      {
         removeFromKnows(item);
      }
      return this;
   }

   public void removeAllFromKnows()
   {
      LinkedHashSet<Person> tmpSet = new LinkedHashSet<Person>(this.getKnows());

      for (Person value : tmpSet)
      {
         this.removeFromKnows(value);
      }
   }

   public Person createKnows()
   {
      Person value = new Person();
      withKnows(value);
      return value;
   }

   public PersonSet getKnowsTransitive()
   {
      PersonSet result = new PersonSet().with(this);
      return result.getKnowsTransitive();
   }

}
