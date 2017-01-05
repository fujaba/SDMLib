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

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.test.examples.m2m.model.util.PersonSet;
import org.sdmlib.test.examples.m2m.model.util.RelationSet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/m2m/BanfM2MModelGen.java'>BanfM2MModelGen.java</a>
* @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/m2m/BanfM2MTransformations.java'>BanfM2MTransformations.java</a>
*/
   public class Person extends GraphComponent implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }
   
   //==========================================================================
   
   
   public void removeYou()
   {
      setGraph(null);
      removeAllFromOutEdges();
      removeAllFromInEdges();
      removeAllFromKnows();
      setParent(null);
      withoutOutEdges(this.getOutEdges().toArray(new Relation[this.getOutEdges().size()]));
      withoutInEdges(this.getInEdges().toArray(new Relation[this.getInEdges().size()]));
      withoutKnows(this.getKnows().toArray(new Person[this.getKnows().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_FIRSTNAME = "firstName";
   
   private String firstName;

   public String getFirstName()
   {
      return this.firstName;
   }
   
   public void setFirstName(String value)
   {
      if ( ! StrUtil.stringEquals(this.firstName, value))
      {
         String oldValue = this.firstName;
         this.firstName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FIRSTNAME, oldValue, value);
      }
   }
   
   public Person withFirstName(String value)
   {
      setFirstName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getFirstName());
      s.append(" ").append(this.getText());
      return s.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_TEXT = "text";
   
   private String text;

   public String getText()
   {
      return this.text;
   }
   
   public void setText(String value)
   {
      if ( ! StrUtil.stringEquals(this.text, value))
      {
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue, value);
      }
   }
   
   public Person withText(String value)
   {
      setText(value);
      return this;
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
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_GRAPH, oldValue, value);
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
         
         changed = this.outEdges.add (value);
         
         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTEDGES, null, value);
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
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTEDGES, value, null);
         }
      }
         
      return changed;   
   }

   public Person withOutEdges(Relation... value)
   {
      if(value==null){
         return this;
      }
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
      LinkedHashSet<Relation> tmpSet = new LinkedHashSet<Relation>(this.getOutEdges());
   
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
         
         changed = this.inEdges.add (value);
         
         if (changed)
         {
            value.withTgt(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INEDGES, null, value);
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
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INEDGES, value, null);
         }
      }
         
      return changed;   
   }

   public Person withInEdges(Relation... value)
   {
      if(value==null){
         return this;
      }
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
      LinkedHashSet<Relation> tmpSet = new LinkedHashSet<Relation>(this.getInEdges());
   
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
   public PersonSet getKnowsTransitive()
   {
      PersonSet result = new PersonSet().with(this);
      return result.getKnowsTransitive();
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
         
         changed = this.knows.add (value);
         
         if (changed)
         {
            value.withKnows(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KNOWS, null, value);
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
            getPropertyChangeSupport().firePropertyChange(PROPERTY_KNOWS, value, null);
         }
      }
         
      return changed;   
   }

   public Person withKnows(Person... value)
   {
      if(value==null){
         return this;
      }
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

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }

