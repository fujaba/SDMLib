package org.sdmlib.modelspace;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
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
      
      Label label = new Label("file transfer tasks:");
      tasksVBox.getChildren().add(label);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (evt == null)
      {
         myTaskLane.getTasks()
         .toString();
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

   private LinkedHashMap<Task, Label> labelMap = new LinkedHashMap<Task, Label>();
   
   private void handleTask(final Task task)
   {
      // add task to vbox anyway. 
      Label label = labelMap.get(task);
      
      if (label == null)
      {
         label = new Label();
         label.setPadding(new Insets(0, 0, 0, 24));
         tasksVBox.getChildren().add(label);
         
         labelMap.put(task, label);
      }
      
      label.setText(task.toString());
      
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
