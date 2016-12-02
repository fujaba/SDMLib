package org.sdmlib.test.examples.groupaccount.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Locale;

import org.sdmlib.serialization.PropertyChangeInterface;

import javafx.scene.control.Label;

class LabelDouble0_00Binding implements PropertyChangeListener
{
   private Label label;
   private PropertyChangeInterface modelObject;
   private String propertyName;

   public LabelDouble0_00Binding(Label label, PropertyChangeInterface modelObject, String propertyName, Double initialValue)
   {
      this.label = label;
      this.modelObject = modelObject;
      this.propertyName = propertyName;

      modelObject.getPropertyChangeSupport().addPropertyChangeListener(propertyName, this);
      this.updateLabel(initialValue);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt.getNewValue() != null && evt.getNewValue() instanceof Double)
      {
         this.updateLabel((Double) evt.getNewValue());
      }
   }

   private void updateLabel(Double newValue)
   {
      String newText = String.format(Locale.ENGLISH, "%.2f", newValue);
      this.label.setText(newText);
   }
}