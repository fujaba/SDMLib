package org.sdmlib.models.debug;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.MapUpdateListener;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;

public class FlipBook implements MapUpdateListener,  PropertyChangeInterface

{
   public void step()
   {
      long stopHere = 0;
      if (changes.size() >= stopStep) 
      {
         stopHere = stopStep;
      }
      
   }
   
   public FlipBook back()
   {
      if (currentStep <= 0 )
      {
         // already at start
         return this;
      }
      
      StepInfo step = changes.get((int) (currentStep - 1));
      
      // undo step by swapping rem and upd
      JsonObject jo = step.change;

      JsonObject undo = new JsonObject();
      
      undo.put(JsonIdMap.ID, jo.getString(JsonIdMap.ID));
      
      Object update = jo.get(JsonIdMap.UPDATE);
      if (update != null)
      {
         // if the value is a JsonObject, just use the id
         JsonObject jsonUpdate = (JsonObject) update;
         String key = jsonUpdate.keyIterator().next();
         Object value = jsonUpdate.get(key);
         
         if (value instanceof JsonObject)
         {
            JsonObject jsonValue = (JsonObject) value;
            
            JsonObject newValue = new JsonObject();
            newValue.put(JsonIdMap.ID, jsonValue.getString(JsonIdMap.ID));
            JsonObject newUpdate = new JsonObject();
            newUpdate.put(key, newValue);
            undo.put(JsonIdMap.REMOVE, newUpdate);
         }
         else
         {
            undo.put(JsonIdMap.REMOVE, update);
         }
      }
      
      Object remove = jo.get(JsonIdMap.REMOVE);
      if (remove != null)
      {
         undo.put(JsonIdMap.UPDATE, remove);
      }
      
      setReading(true);
      map.executeUpdateMsg(undo);
      setReading(false);
      currentStep--;
      
      return this;
   }
   
   
   public FlipBook back(Object target, String property)
   {
      while (true)
      {
         back();
         
         if (currentStep <= 0)
         {
            return this;
         }
         
         // does current step operate on target?
         StepInfo stepInfo = changes.get((int) (currentStep - 1));
         
         JsonObject jo = stepInfo.change;
         
         String id = jo.getString(JsonIdMap.ID);
         
         Object obj = map.getObject(id);
         
         if (obj == target)
         {
            Object update = jo.get(JsonIdMap.UPDATE);
            
            if (update != null)
            {
               JsonObject jsonUpdate = (JsonObject) update;
               
               String key = jsonUpdate.keyIterator().next();
               
               if (key.equals(property))
               {
                  // print stacktrace
                  stepInfo.e.printStackTrace();
                  return this;
               }
            }
         }
      }
   }
   
   
   public FlipBook storeCurrentStepAsStopStep()
   {
      this.withStopStep(currentStep);
      return this;
   }
   
   
   public FlipBook back(Object target)
   {
      while (true)
      {
         back();
         
         if (currentStep <= 0)
         {
            return this;
         }
         
         // does current step operate on target?
         StepInfo stepInfo = changes.get((int) (currentStep - 1));
         
         JsonObject jo = stepInfo.change;
         
         String id = jo.getString(JsonIdMap.ID);
         
         Object obj = map.getObject(id);
         
         if (obj == target)
         {
            // print stacktrace
            stepInfo.e.printStackTrace();
            return this;
         }
         
      }
   }
   
   public FlipBook printCurrentStackTrace()
   {
      if (currentStep <= 0)
      {
         return this;
      }

      StepInfo stepInfo = changes.get((int) (currentStep -1));
      
      stepInfo.e.printStackTrace();
      return this;
      
   }
   
   public FlipBook back(long steps)
   {
      for (long l = 0; l < steps; l++)
      {
         back();
      }
      return this;
   }
   
   public FlipBook forward(long steps)
   {
      for (long l = 0; l < steps; l++)
      {
         forward();
      }
      return this;
   }
   
   public FlipBook forward()
   {
      if (currentStep >= changes.size() )
      {
         // already at start
         return this;
      }
      
      StepInfo step = changes.get((int) (currentStep));
      
      // redo step
      JsonObject jo = step.change;
      
      setReading(true);
      map.executeUpdateMsg(jo);
      setReading(false);
      currentStep++;
      
      return this;
   }
   
   private static JsonIdMap map = null;

   public long stopStep = Long.MAX_VALUE;
   
   public FlipBook withStopStep(long value)
   {
      if (value != stopStep)
      {
         stopStep = value;
         
         // store for reload
         JsonObject jsonObject = new JsonObject();
         jsonObject.put("stopStep", stopStep);
         
         File file = new File("doc");
         file.mkdirs();
         
         file = new File("doc/flibBookStopStep.json");
         
         try
         {
            FileWriter fileWriter = new FileWriter(file);
            
            fileWriter.write(jsonObject.toString() + "/n");
            fileWriter.close();
         }
         catch (IOException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      return this;
   }
   
   public FlipBook init(JsonIdMap theMap)
   {
      map = theMap;
      
      // read stopStep from file
      File file = new File("doc/flibBookStopStep.json");
      
      try
      {
         FileReader fileReader = new FileReader(file);
         BufferedReader in = new BufferedReader(fileReader);
         String line = in.readLine();
         in.close();
         
         JsonObject jsonObject = new JsonObject().withValue(line);
         
         long value = jsonObject.getInt("stopStep");
         
         stopStep = value;
         
      }
      catch (IOException e)
      {
         // no problem, work with default
      }
      return this;
   }
   
   public long currentStep = -1;
   
//   public void addModelRoot(Object root)
//   {
//      map.getId(root);
//   }
//   
//   private JsonIdMap map = null;
//   
//   public JsonIdMap getMap()
//   {
//      return map;
//   }
//   
//   public void setMap(JsonIdMap map)
//   {
//      this.map = map;
//   }
//   
//   public FlipBook withMap(JsonIdMap map)
//   {
//      this.setMap(map);
//      // map.addListener(this);
//      map.withUpdateMsgListener(this);
//      return this;
//   }
   
   private ArrayList<StepInfo> changes = new ArrayList<StepInfo>();
   
   @Override
   public boolean sendUpdateMsg(Object target, String property, Object oldObj,
         Object newObject, JsonObject jsonObject)
   {
      if (isReading)
      {
         // do nothing
         return true;
      }
      // store message in list
      StepInfo stepInfo = new StepInfo(jsonObject, new RuntimeException());
      changes.add(stepInfo);
      
      currentStep = changes.size();
      step();
      
      return true;
   }

   boolean isReading = false;
   
   public boolean isReading()
   {
      return isReading;
   }
   
   public void setReading(boolean isReading)
   {
      this.isReading = isReading;
   }
   
   @Override
   public boolean isReadMessages(String key, Object element, JsonObject props,
         String type)
   {
      // TODO Auto-generated method stub
      return isReading;
   }

   @Override
   public boolean readMessages(String key, Object element, Object value,
         JsonObject props, String type)
   {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public boolean skipUpdateCollision(Object masterObj, String key, Object value,
         JsonObject removeJson, JsonObject updateJson)
   {
      // TODO Auto-generated method stub
      return false;
   }
   
   class StepInfo
   {
      public StepInfo(JsonObject jsonObject, RuntimeException runtimeException)
      {
         this.change = jsonObject;
         this.e = runtimeException;
      }
      
      public JsonObject change;
      public Exception e;
   }
   
 //==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }


}
