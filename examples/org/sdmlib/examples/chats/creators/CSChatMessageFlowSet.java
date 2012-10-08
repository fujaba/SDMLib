package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.chats.CSChatMessageFlow;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;

public class CSChatMessageFlowSet extends LinkedHashSet<CSChatMessageFlow>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CSChatMessageFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   //==========================================================================
   
   public CSChatMessageFlowSet run()
   {
      for (CSChatMessageFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (CSChatMessageFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public CSChatMessageFlowSet withTaskNo(int value)
   {
      for (CSChatMessageFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }



   public CSChatMessageFlowSet with(CSChatMessageFlow value)
   {
      this.add(value);
      return this;
   }
   
   public CSChatMessageFlowSet without(CSChatMessageFlow value)
   {
      this.remove(value);
      return this;
   }
   public StringList getMsg()
   {
      StringList result = new StringList();
      
      for (CSChatMessageFlow obj : this)
      {
         result.add(obj.getMsg());
      }
      
      return result;
   }

   public CSChatMessageFlowSet withMsg(String value)
   {
      for (CSChatMessageFlow obj : this)
      {
         obj.withMsg(value);
      }
      
      return this;
   }

   public CSChatMessageFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (CSChatMessageFlow obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (CSChatMessageFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

}




