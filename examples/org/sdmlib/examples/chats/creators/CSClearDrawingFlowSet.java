package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.CSClearDrawingFlow;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.models.modelsets.intList;
import java.util.List;

public class CSClearDrawingFlowSet extends LinkedHashSet<CSClearDrawingFlow>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CSClearDrawingFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public CSClearDrawingFlowSet with(CSClearDrawingFlow value)
   {
      this.add(value);
      return this;
   }
   
   public CSClearDrawingFlowSet without(CSClearDrawingFlow value)
   {
      this.remove(value);
      return this;
   }
   
   //==========================================================================
   
   public CSClearDrawingFlowSet run()
   {
      for (CSClearDrawingFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (CSClearDrawingFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public CSClearDrawingFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (CSClearDrawingFlow obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (CSClearDrawingFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public CSClearDrawingFlowSet withTaskNo(int value)
   {
      for (CSClearDrawingFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

}

