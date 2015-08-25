package org.sdmlib.modelspace;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javafx.application.Platform;
import javafx.scene.layout.VBox;

public class TaskLaneListener implements PropertyChangeListener
{

   private ModelCloud modelCloud;
   private TaskLane myTaskLane;
   private VBox tasksVBox;

   public TaskLaneListener(ModelCloud modelCloud, TaskLane myTaskLane, VBox tasksVBox)
   {
      this.modelCloud = modelCloud;
      this.myTaskLane = myTaskLane;
      this.tasksVBox = tasksVBox;
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt == null)
      {
         for (Task task : myTaskLane.getTasks())
         {
            task.getPropertyChangeSupport().addPropertyChangeListener(this);
            handleTask(task);
         }
      }
      else if (evt.getSource() == myTaskLane && evt.getNewValue() != null)
      {
         Task task = (Task) evt.getNewValue();
         task.getPropertyChangeSupport().addPropertyChangeListener(this);
         handleTask(task);
      }
      else if (evt.getSource() instanceof Task)
      {
         handleTask((Task) evt.getSource());
      }
   }

   private void handleTask(final Task task)
   {
      // is the task complete? 
      if (task.getLane() != myTaskLane 
            || task.getSpaceName() == null
            || task.getFileName() == null
            || task.getLastModified() == 0
            || task.getFileTargetCloud() == null
            || task.getState() == null)
      {
         return;
      }
         
      Platform.runLater(new Runnable()
      {
         @Override
         public void run()
         {
            modelCloud.handleTask(task);
         }
      });
   }

}
