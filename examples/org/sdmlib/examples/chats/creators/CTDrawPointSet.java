package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.CTDrawPoint;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

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

}


