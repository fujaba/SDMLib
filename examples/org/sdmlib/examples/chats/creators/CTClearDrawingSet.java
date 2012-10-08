package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.CTClearDrawing;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CTClearDrawingSet extends LinkedHashSet<CTClearDrawing>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CTClearDrawing elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public CTClearDrawingSet with(CTClearDrawing value)
   {
      this.add(value);
      return this;
   }
   
   public CTClearDrawingSet without(CTClearDrawing value)
   {
      this.remove(value);
      return this;
   }
   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (CTClearDrawing obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public CTClearDrawingSet withTaskNo(int value)
   {
      for (CTClearDrawing obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (CTClearDrawing obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public CTClearDrawingSet withIdMap(SDMLibJsonIdMap value)
   {
      for (CTClearDrawing obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

}


