package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.DrawPointFlow;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class DrawPointFlowSet extends LinkedHashSet<DrawPointFlow>
{
   public intList getX()
   {
      intList result = new intList();
      
      for (DrawPointFlow obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public DrawPointFlowSet withX(int value)
   {
      for (DrawPointFlow obj : this)
      {
         obj.withX(value);
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (DrawPointFlow obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public DrawPointFlowSet withY(int value)
   {
      for (DrawPointFlow obj : this)
      {
         obj.withY(value);
      }
      
      return this;
   }

   public PeerToPeerChatSet getGui()
   {
      PeerToPeerChatSet result = new PeerToPeerChatSet();
      
      for (DrawPointFlow obj : this)
      {
         result.add(obj.getGui());
      }
      
      return result;
   }

   //==========================================================================
   
   public DrawPointFlowSet run()
   {
      for (DrawPointFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (DrawPointFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public DrawPointFlowSet withTaskNo(int value)
   {
      for (DrawPointFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

   public DrawPointFlowSet withGui(PeerToPeerChat value)
   {
      for (DrawPointFlow obj : this)
      {
         obj.withGui(value);
      }
      
      return this;
   }

   public intList getR()
   {
      intList result = new intList();
      
      for (DrawPointFlow obj : this)
      {
         result.add(obj.getR());
      }
      
      return result;
   }

   public DrawPointFlowSet withR(int value)
   {
      for (DrawPointFlow obj : this)
      {
         obj.withR(value);
      }
      
      return this;
   }

   public intList getG()
   {
      intList result = new intList();
      
      for (DrawPointFlow obj : this)
      {
         result.add(obj.getG());
      }
      
      return result;
   }

   public DrawPointFlowSet withG(int value)
   {
      for (DrawPointFlow obj : this)
      {
         obj.withG(value);
      }
      
      return this;
   }

   public intList getB()
   {
      intList result = new intList();
      
      for (DrawPointFlow obj : this)
      {
         result.add(obj.getB());
      }
      
      return result;
   }

   public DrawPointFlowSet withB(int value)
   {
      for (DrawPointFlow obj : this)
      {
         obj.withB(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (DrawPointFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public DrawPointFlowSet with(DrawPointFlow value)
   {
      this.add(value);
      return this;
   }
   
   public DrawPointFlowSet without(DrawPointFlow value)
   {
      this.remove(value);
      return this;
   }
   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (DrawPointFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public DrawPointFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (DrawPointFlow obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

}








