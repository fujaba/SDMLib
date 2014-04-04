package org.sdmlib.javafx;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

import com.sun.javafx.collections.NonIterableChange;
import com.sun.javafx.collections.NonIterableChange.SimpleAddChange;
import com.sun.javafx.collections.NonIterableChange.SimpleRemovedChange;

public class SDMObservableList<E> extends ArrayList<E> implements
      ObservableList<E>, PropertyChangeListener
{
   private PropertyChangeInterface parentObject;
   private String propertyName;

   private LinkedHashSet<InvalidationListener> invalidationListeners;
   private LinkedHashSet<ListChangeListener<? super E>> listChangeListeners;

   public SDMObservableList(PropertyChangeInterface parentObject,
         String propertyName)
   {
      this.parentObject = parentObject;
      this.propertyName = propertyName;

      invalidationListeners = new LinkedHashSet<>();
      listChangeListeners = new LinkedHashSet<>();

      // get the original collection
      try
      {
         Method method = parentObject.getClass().getMethod(
            "get" + StrUtil.upFirstChar(propertyName));
         Object invoke = method.invoke(parentObject);
         this.addAll((Collection) invoke);
      }
      catch (Exception e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      // subscribe as listener to the parentObject
      parentObject.getPropertyChangeSupport().addPropertyChangeListener(
         propertyName, this);
   }

   @Override
   public void addListener(InvalidationListener listener)
   {
      invalidationListeners.add(listener);
   }

   @Override
   public void removeListener(InvalidationListener listener)
   {
      invalidationListeners.remove(listener);
   }

   @Override
   public void addListener(ListChangeListener<? super E> listener)
   {
      listChangeListeners.add(listener);
   }

   @Override
   public void removeListener(ListChangeListener<? super E> listener)
   {
      listChangeListeners.remove(listener);
   }

   @Override
   public boolean addAll(E... elements)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean setAll(E... elements)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean setAll(Collection<? extends E> col)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean removeAll(E... elements)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean retainAll(E... elements)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void remove(int from, int to)
   {
      // TODO Auto-generated method stub

   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      // the parent object has fired a property change on the collection in
      // propertyName
      // add / remove element to this
      if (evt.getNewValue() != null)
      {
         this.add((E) evt.getNewValue());

         // inform change listeners
         SimpleAddChange<E> simpleAddChange = new NonIterableChange.SimpleAddChange<>(
               this.size() - 1, this.size() - 1, this);
         for (ListChangeListener<? super E> listChangeListener : this.listChangeListeners)
         {
            listChangeListener.onChanged(simpleAddChange);
         }
      }
      else
      {
         // find its index
         Object oldValue = evt.getOldValue();

         int i = 0;
         for (; i < this.size(); i++)
         {
            if (this.get(i) == oldValue)
            {
               break;
            }
         }
         this.remove(oldValue);

         SimpleRemovedChange<E> simpleRemovedChange = new NonIterableChange.SimpleRemovedChange<>(
               i, i, (E) oldValue, this);
         for (ListChangeListener<? super E> listChangeListener : this.listChangeListeners)
         {
            listChangeListener.onChanged(simpleRemovedChange);
         }
      }
   }

}
