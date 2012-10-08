package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.CSDrawPointFlow;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CSDrawPointFlowSet extends LinkedHashSet<CSDrawPointFlow>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CSDrawPointFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public CSDrawPointFlowSet with(CSDrawPointFlow value)
   {
      this.add(value);
      return this;
   }
   
   public CSDrawPointFlowSet without(CSDrawPointFlow value)
   {
      this.remove(value);
      return this;
   }
   
   //==========================================================================
   
   public CSDrawPointFlowSet run()
   {
      for (CSDrawPointFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public intList getX()
   {
      intList result = new intList();
      
      for (CSDrawPointFlow obj : this)
      {
         result.add(obj.getX());
      }
      
      return result;
   }

   public CSDrawPointFlowSet withX(int value)
   {
      for (CSDrawPointFlow obj : this)
      {
         obj.withX(value);
      }
      
      return this;
   }

   public intList getY()
   {
      intList result = new intList();
      
      for (CSDrawPointFlow obj : this)
      {
         result.add(obj.getY());
      }
      
      return result;
   }

   public CSDrawPointFlowSet withY(int value)
   {
      for (CSDrawPointFlow obj : this)
      {
         obj.withY(value);
      }
      
      return this;
   }

   public intList getR()
   {
      intList result = new intList();
      
      for (CSDrawPointFlow obj : this)
      {
         result.add(obj.getR());
      }
      
      return result;
   }

   public CSDrawPointFlowSet withR(int value)
   {
      for (CSDrawPointFlow obj : this)
      {
         obj.withR(value);
      }
      
      return this;
   }

   public intList getG()
   {
      intList result = new intList();
      
      for (CSDrawPointFlow obj : this)
      {
         result.add(obj.getG());
      }
      
      return result;
   }

   public CSDrawPointFlowSet withG(int value)
   {
      for (CSDrawPointFlow obj : this)
      {
         obj.withG(value);
      }
      
      return this;
   }

   public intList getB()
   {
      intList result = new intList();
      
      for (CSDrawPointFlow obj : this)
      {
         result.add(obj.getB());
      }
      
      return result;
   }

   public CSDrawPointFlowSet withB(int value)
   {
      for (CSDrawPointFlow obj : this)
      {
         obj.withB(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (CSDrawPointFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public CSDrawPointFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (CSDrawPointFlow obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (CSDrawPointFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public CSDrawPointFlowSet withTaskNo(int value)
   {
      for (CSDrawPointFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

}

