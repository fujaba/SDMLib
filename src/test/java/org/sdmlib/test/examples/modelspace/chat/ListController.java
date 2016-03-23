package org.sdmlib.test.examples.modelspace.chat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.list.SimpleSet;
import javafx.application.Application;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class ListController implements PropertyChangeListener 
{

   private VBox vBox;
   
   private IdMap idMap;
   
   private PropertyChangeInterface parent;

   private String property;

   private Class subControllerClass;

   private SendableEntityCreator creator;

   private Application app;

   public VBox init(IdMap idMap, Application app, PropertyChangeInterface parent, String property, Class subControllerClass)
   {
      this.idMap = idMap;
      this.app = app;
      this.parent = parent;
      this.property = property;
      this.subControllerClass = subControllerClass;
      creator = idMap.getCreatorClass(parent);
      
      vBox = new VBox();
      
      parent.getPropertyChangeSupport().addPropertyChangeListener(property, this);
      
      this.propertyChange(null);
      
      return vBox;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      // build whole list, anew
      vBox.getChildren().clear();
      
      SimpleSet<?> elements = (SimpleSet<?>) creator.getValue(parent, property);
      
      for (Object elem : elements)
      {
         try
         {
            ChannelDescriptionController newController = (ChannelDescriptionController) subControllerClass.newInstance();
            HBox hBox = newController.init(app, elem);
            vBox.getChildren().add(hBox);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }

}
