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

import org.sdmlib.examples.chats.CSVisitAllClientsFlow;
import org.sdmlib.examples.chats.CTDrawPoint;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;

public class CTDrawPointSet extends LinkedHashSet<CTDrawPoint>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CTDrawPoint elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public CTDrawPointSet with(CTDrawPoint value)
   {
      this.add(value);
      return this;
   }
   
   public CTDrawPointSet without(CTDrawPoint value)
   {
      this.remove(value);
      return this;
   }
   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (CTDrawPoint obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public CTDrawPointSet withTaskNo(int value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (CTDrawPoint obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public CTDrawPointSet withX(int value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withX(value);
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (CTDrawPoint obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public CTDrawPointSet withY(int value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withY(value);
      }
      
      return this;
   }

   public intList getR()
   {
      intList result = new intList();
      
      for (CTDrawPoint obj : this)
      {
         result.add(obj.getR());
      }
      
      return result;
   }

   public CTDrawPointSet withR(int value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withR(value);
      }
      
      return this;
   }

   public intList getG()
   {
      intList result = new intList();
      
      for (CTDrawPoint obj : this)
      {
         result.add(obj.getG());
      }
      
      return result;
   }

   public CTDrawPointSet withG(int value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withG(value);
      }
      
      return this;
   }

   public intList getB()
   {
      intList result = new intList();
      
      for (CTDrawPoint obj : this)
      {
         result.add(obj.getB());
      }
      
      return result;
   }

   public CTDrawPointSet withB(int value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withB(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (CTDrawPoint obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public CTDrawPointSet withIdMap(SDMLibJsonIdMap value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }



   public String getEntryType()
   {
      return "org.sdmlib.examples.chats.CTDrawPoint";
   }
   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (CTDrawPoint obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

   public CTDrawPointSet withSubFlow(TaskFlow value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public TaskFlowSet getParent()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (CTDrawPoint obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public CTDrawPointSet withParent(TaskFlow value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public CTDrawPointSet withParent(CSVisitAllClientsFlow value)
   {
      for (CTDrawPoint obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}




