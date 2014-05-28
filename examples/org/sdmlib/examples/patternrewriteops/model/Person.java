/*
   Copyright (c) 2014 Stefan 
   
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
   
package org.sdmlib.examples.patternrewriteops.model;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.examples.patternrewriteops.model.util.PersonSet;

public class Person implements PropertyChangeInterface
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
      setStation(null);
      setTrain(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- Station
    *              people                   station
    * </pre>
    */
   
   public static final String PROPERTY_STATION = "station";

   private Station station = null;

   public Station getStation()
   {
      return this.station;
   }

   public boolean setStation(Station value)
   {
      boolean changed = false;
      
      if (this.station != value)
      {
         Station oldValue = this.station;
         
         if (this.station != null)
         {
            this.station = null;
            oldValue.withoutPeople(this);
         }
         
         this.station = value;
         
         if (value != null)
         {
            value.withPeople(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STATION, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withStation(Station value)
   {
      setStation(value);
      return this;
   } 

   public Station createStation()
   {
      Station value = new Station();
      withStation(value);
      return value;
   } 

   
   public static final PersonSet EMPTY_SET = new PersonSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Person ----------------------------------- Train
    *              passengers                   train
    * </pre>
    */
   
   public static final String PROPERTY_TRAIN = "train";

   private Train train = null;

   public Train getTrain()
   {
      return this.train;
   }

   public boolean setTrain(Train value)
   {
      boolean changed = false;
      
      if (this.train != value)
      {
         Train oldValue = this.train;
         
         if (this.train != null)
         {
            this.train = null;
            oldValue.withoutPassengers(this);
         }
         
         this.train = value;
         
         if (value != null)
         {
            value.withPassengers(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TRAIN, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Person withTrain(Train value)
   {
      setTrain(value);
      return this;
   } 

   public Train createTrain()
   {
      Train value = new Train();
      withTrain(value);
      return value;
   } 
}

