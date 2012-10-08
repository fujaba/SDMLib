package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.ClearDrawingFlow;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.models.modelsets.StringList;

public class ClearDrawingFlowSet extends LinkedHashSet<ClearDrawingFlow>
{
   
   //==========================================================================
   
   public ClearDrawingFlowSet run()
   {
      for (ClearDrawingFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public PeerToPeerChatSet getGui()
   {
      PeerToPeerChatSet result = new PeerToPeerChatSet();
      
      for (ClearDrawingFlow obj : this)
      {
         result.add(obj.getGui());
      }
      
      return result;
   }

   public ClearDrawingFlowSet withGui(PeerToPeerChat value)
   {
      for (ClearDrawingFlow obj : this)
      {
         obj.withGui(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (ClearDrawingFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public ClearDrawingFlowSet withTaskNo(int value)
   {
      for (ClearDrawingFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ClearDrawingFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public ClearDrawingFlowSet with(ClearDrawingFlow value)
   {
      this.add(value);
      return this;
   }
   
   public ClearDrawingFlowSet without(ClearDrawingFlow value)
   {
      this.remove(value);
      return this;
   }
}



