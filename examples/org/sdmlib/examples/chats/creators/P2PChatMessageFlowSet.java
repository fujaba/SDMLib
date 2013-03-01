/*
   Copyright (c) 2013 zuendorf 
   
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

import org.sdmlib.examples.chats.P2PChatMessageFlow;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;

public class P2PChatMessageFlowSet extends LinkedHashSet<P2PChatMessageFlow> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (P2PChatMessageFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.chats.P2PChatMessageFlow";
   }


   public P2PChatMessageFlowSet with(P2PChatMessageFlow value)
   {
      this.add(value);
      return this;
   }
   
   public P2PChatMessageFlowSet without(P2PChatMessageFlow value)
   {
      this.remove(value);
      return this;
   }
   
   //==========================================================================
   
   public P2PChatMessageFlowSet run()
   {
      for (P2PChatMessageFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public StringList getMsg()
   {
      StringList result = new StringList();
      
      for (P2PChatMessageFlow obj : this)
      {
         result.add(obj.getMsg());
      }
      
      return result;
   }

   public P2PChatMessageFlowSet withMsg(String value)
   {
      for (P2PChatMessageFlow obj : this)
      {
         obj.withMsg(value);
      }
      
      return this;
   }

   public intList getPos()
   {
      intList result = new intList();
      
      for (P2PChatMessageFlow obj : this)
      {
         result.add(obj.getPos());
      }
      
      return result;
   }

   public P2PChatMessageFlowSet withPos(int value)
   {
      for (P2PChatMessageFlow obj : this)
      {
         obj.withPos(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (P2PChatMessageFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public P2PChatMessageFlowSet withTaskNo(int value)
   {
      for (P2PChatMessageFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (P2PChatMessageFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public P2PChatMessageFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (P2PChatMessageFlow obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public P2PChatMessageFlowSet withSubFlow(TaskFlow value)
   {
      for (P2PChatMessageFlow obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public TaskFlowSet getParent()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (P2PChatMessageFlow obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public P2PChatMessageFlowSet withParent(TaskFlow value)
   {
      for (P2PChatMessageFlow obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (P2PChatMessageFlow obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

}



