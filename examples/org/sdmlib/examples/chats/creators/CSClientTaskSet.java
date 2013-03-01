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
   
package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.chats.CSClientTask;
import org.sdmlib.examples.chats.CSVisitAllClientsFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;

public class CSClientTaskSet extends LinkedHashSet<CSClientTask>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CSClientTask elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public CSClientTaskSet with(CSClientTask value)
   {
      this.add(value);
      return this;
   }
   
   public CSClientTaskSet without(CSClientTask value)
   {
      this.remove(value);
      return this;
   }
   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (CSClientTask obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public CSClientTaskSet withTaskNo(int value)
   {
      for (CSClientTask obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

   public CSVisitAllClientsFlowSet getParent()
   {
      CSVisitAllClientsFlowSet result = new CSVisitAllClientsFlowSet();
      
      for (CSClientTask obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }
   public CSClientTaskSet withParent(CSVisitAllClientsFlow value)
   {
      for (CSClientTask obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }



   public String getEntryType()
   {
      return "org.sdmlib.examples.chats.CSClientTask";
   }
   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (CSClientTask obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public CSClientTaskSet withIdMap(SDMLibJsonIdMap value)
   {
      for (CSClientTask obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (CSClientTask obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

   public CSClientTaskSet withSubFlow(TaskFlow value)
   {
      for (CSClientTask obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public CSClientTaskSet withParent(TaskFlow value)
   {
      for (CSClientTask obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}



