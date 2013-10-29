/*
   Copyright (c) 2012 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.examples.adamandeve;

import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.sdmlib.examples.adamandeve.creators.CreatorCreator;
import org.sdmlib.model.taskflows.SDMThread;
import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

public class Adam implements PropertyChangeInterface
{

   /**
    * @param args
    */
   public static void main(String[] args)
   {
      JsonIdMap idMap = CreatorCreator.createIdMap("adam");
      
      idMap.put("json.idmap", idMap); // oh oh
      
      SDMThread sdmThread = new SDMThread("ModelThread");
      sdmThread.start();
      
      new SocketThread().withIdMap(idMap)
      .withDefaultTargetThread(sdmThread)
      .withPort(4242)
      .start();
      
      System.out.println("Adam has started.");
      
      // look for boot task
      try
      {
         BufferedReader in = new BufferedReader(new FileReader("bootTask.json"));
         
         StringBuilder buf = new StringBuilder();
         
         String line = "";
         while (line != null)
         {
            buf.append(line);
            line = in.readLine();
         }
         
         in.close();
         
         JsonArray jsonArray = new JsonArray().withValue(buf.toString());
         
         Object obj = idMap.decode(jsonArray);
         
         new File("bootTask.json").delete();
         
         ((TaskFlow) obj).run();
      }
      catch (Exception e)
      {
         // just fine, no reboot task available
      }
   }

   


   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
}

