package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.CSClientTask;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.examples.chats.CSVisitAllClientsFlow;

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

}

