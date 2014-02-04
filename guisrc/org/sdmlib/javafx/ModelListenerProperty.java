package org.sdmlib.javafx;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Licensed under the EUPL, Version 1.1 or as soon they
 will be approved by the European Commission - subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
 */
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;

import javafx.beans.InvalidationListener;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public abstract class ModelListenerProperty<T> implements javafx.beans.property.Property<T>, PropertyChangeListener, ObservableValue<T>, InvalidationListener
{
   protected Object item;
   protected String property;
   protected SendableEntityCreator creator;
   private LinkedHashSet<ChangeListener<? super T>> listeners=new LinkedHashSet<ChangeListener<? super T>>();
   private LinkedHashSet<InvalidationListener> invalidationListeners=new LinkedHashSet<InvalidationListener>();
   protected ObservableValue<? extends T> observable = null;

   public ModelListenerProperty(SendableEntityCreator creator, Object item, String property) 
   {
      this.item = item;
      this.creator = creator;
      this.property = property;
      try 
      {
         Method method = item.getClass().getMethod("addPropertyChangeListener", String.class, java.beans.PropertyChangeListener.class );
         method.invoke(item, property, this);
      } 
      catch (Exception e) 
      {
         if (item instanceof SendableEntity) 
         {
            ((SendableEntity) item).addPropertyChangeListener(property, this);
         }
         else if(item instanceof PropertyChangeSupport)
         {
            ((PropertyChangeSupport) item).addPropertyChangeListener(property, this);
         }
      }
   }

   @Override
   public Object getBean() {
      return item;
   }

   @Override
   public String getName() {
      return property;
   }

   @Override
   public void addListener(ChangeListener<? super T> listener) {
      listeners.add(listener);
   }

   @Override
   public void removeListener(ChangeListener<? super T> listener) {
      listeners.remove(listener);
   }

   @Override
   public void addListener(InvalidationListener listener) {
      this.invalidationListeners.add(listener);
   }

   @Override
   public void removeListener(InvalidationListener listener) {
      this.invalidationListeners.remove(listener);
   }

   @Override
   public void setValue(T value) {
      creator.setValue(item, property, value, IdMap.NEW);
   }

   @Override
   public void bind(ObservableValue<? extends T> newObservable) {
      if (newObservable == null) {
         throw new NullPointerException("Cannot bind to null");
      }
      if (!newObservable.equals(observable)) {
         unbind();
         observable = newObservable;
         observable.addListener(this);
      }
   }

   @Override
   public void bindBidirectional(Property<T> other) {
      Bindings.bindBidirectional(this, other);
   }

   @Override
   public boolean isBound() {
      return observable != null;
   }

   @Override
   public void unbind() {
      if (observable != null) {
         observable.removeListener(this);
         observable = null;
      }
   }

   @Override
   public void unbindBidirectional(Property<T> other) {
      Bindings.unbindBidirectional(this, other);
   } 

   @Override
   @SuppressWarnings("unchecked")
   public void propertyChange(PropertyChangeEvent evt) 
   {
      for(ChangeListener<? super T> listener: listeners) 
      {
         SimpleObjectProperty<T> objectProperty = new SimpleObjectProperty<T>();
         objectProperty.setValue((T)evt.getSource());

         listener.changed(objectProperty, (T)evt.getOldValue(), (T)evt.getNewValue());
      }
      
      for(InvalidationListener listener : invalidationListeners) 
      {
         listener.invalidated(this);
      }
   }
}
