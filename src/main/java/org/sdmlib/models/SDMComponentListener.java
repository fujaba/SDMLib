package org.sdmlib.models;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
   /**
    * 
    * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testGroupAccountMultiUserYaml
 */
   public class SDMComponentListener implements PropertyChangeListener
{
   private HashSet<SendableEntity> supervisedObjects = new HashSet<SendableEntity>();

   private PropertyChangeListener elementListener;
   
   private Set<SendableEntity> componentElements = new LinkedHashSet<SendableEntity>();

   private CreatorMap creatorMap;
   private boolean closed = false;

     /**
    * 
    * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testGroupAccountMultiUserYaml
 */
   public SDMComponentListener(SendableEntity root, PropertyChangeListener elementListener)
   {
      this.elementListener = elementListener;
      String packageName = root.getClass().getPackage().getName();
      ArrayList<String> packageNameList = new ArrayList<>();
      packageNameList.add(packageName);
      creatorMap = new CreatorMap(packageNameList);
      subscribeTo(root);
   }

   public void removeYou()
   {
      this.closed = true;

      for (SendableEntity obj : supervisedObjects)
      {
         obj.removePropertyChangeListener(this);
      }
   }


   private void subscribeTo(SendableEntity newObject)
   {
      if (supervisedObjects.contains(newObject)) return;

      newObject.addPropertyChangeListener(this);
      supervisedObjects.add(newObject);

      // run through elements and fire property changes and subscribe to neighbors
      SendableEntityCreator creator = creatorMap.getCreator(newObject);

      for (String prop : creator.getProperties())
      {
         Object newValue = creator.getValue(newObject, prop);

         if (newValue instanceof Collection)
         {
            Collection newCollection = (Collection) newValue;

            for (Object obj : newCollection)
            {
               SendableEntity newEntity = (SendableEntity) obj;

               PropertyChangeEvent event = new PropertyChangeEvent(newObject, prop, null, newEntity);

               propertyChange(event);
            }
         }
         else
         {
            PropertyChangeEvent event = new PropertyChangeEvent(newObject, prop, null, newValue);

            propertyChange(event);
         }
      }
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (closed) return;

      // just forward
      if (evt.getNewValue() != null && evt.getNewValue() instanceof SendableEntity)
      {
         SendableEntity newValue = (SendableEntity) evt.getNewValue();

         if ( ! supervisedObjects.contains(newValue))
         {
            subscribeTo(newValue);
         }
      }

      elementListener.propertyChange(evt);
   }

}
