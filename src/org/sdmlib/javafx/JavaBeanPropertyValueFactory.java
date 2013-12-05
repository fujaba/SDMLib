package org.sdmlib.javafx;

import org.omg.CORBA.TCKind;

import javafx.beans.property.adapter.JavaBeanDoublePropertyBuilder;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanObjectProperty;
import javafx.beans.property.adapter.JavaBeanProperty;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;

public class JavaBeanPropertyValueFactory<S, T> extends PropertyValueFactory<S, T>
{

   private Class tClass;

   public JavaBeanPropertyValueFactory(String property, Class tClass)
   {
      super(property);
      
      this.tClass = tClass;
   }

   @Override
   public ObservableValue<T> call(CellDataFeatures<S, T> param)
   {
      // construct a JavaBeanProperty
      JavaBeanProperty<T> beanProperty = null;
      try
      {
         if (tClass == Double.class)
         {
            beanProperty = (JavaBeanProperty<T>) JavaBeanDoublePropertyBuilder.create().bean(param.getValue()).name(getProperty()).build();
         }
         else if (tClass == Integer.class)
         {
            beanProperty = (JavaBeanProperty<T>) JavaBeanIntegerPropertyBuilder.create().bean(param.getValue()).name(getProperty()).build();
         }
         else if (tClass == String.class)
         {
            beanProperty = (JavaBeanProperty<T>) JavaBeanStringPropertyBuilder.create().bean(param.getValue()).name(getProperty()).build();
         }
         else
         {
            throw new RuntimeException("bean property type: " + tClass.getName() + " not yet supported");
         }
      }
      catch (NoSuchMethodException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return beanProperty;
   }

   
   
}
