package org.sdmlib.javafx;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;

public class ModelListController implements PropertyChangeListener
{

   private JsonIdMap idMap;
   private VBox vBox;
   private PropertyChangeInterface listRoot;
   private String property;
   private Class<? extends ModelObjectController> elementControllerClass;
   
   private LinkedHashMap<PropertyChangeInterface, ModelObjectController> objectControllers = new LinkedHashMap<PropertyChangeInterface, ModelObjectController>();
   private SendableEntityCreator creator;

   public ModelListController(JsonIdMap idMap, VBox vBox, PropertyChangeInterface listRoot, String property,
         Class<? extends ModelObjectController> elementControllerClass2)
   {
      this.idMap = idMap;
      this.vBox = vBox;
      this.listRoot = listRoot;
      this.property = property;
      this.elementControllerClass = elementControllerClass2;
      creator = idMap.getCreatorClass(listRoot);
      
      // subscribe as listener to the list
      listRoot.getPropertyChangeSupport().addPropertyChangeListener(property, this);
      
      this.propertyChange(null);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      // run through all list elements, remove obsolete element controllers, add missing element controllers
      SDMSet<PropertyChangeInterface> elems = (SDMSet<PropertyChangeInterface>) creator.getValue(listRoot, property);
      for (PropertyChangeInterface listElem : new LinkedHashSet<PropertyChangeInterface>(objectControllers.keySet()))
      {
         if ( ! elems.contains(listElem))
         {
            // it has been removed
            ModelObjectController elemControl = objectControllers.get(listElem);
            objectControllers.remove(listElem);
            vBox.getChildren().remove(elemControl.getNode());
         }
      }
      
      for (PropertyChangeInterface listElem : elems)
      {
         if (objectControllers.get(listElem) == null)
         {
            // need new one
            try
            {
               ModelObjectController elemController = elementControllerClass.newInstance();
               Node listNode = elemController.init(listElem);
               vBox.getChildren().add(listNode);
               objectControllers.put(listElem, elemController);
            }
            catch (InstantiationException | IllegalAccessException e)
            {
               e.printStackTrace();
            }
         }
      }
   }

}
