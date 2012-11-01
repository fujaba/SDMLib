package org.sdmlib.models.classes.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.classes.Clazz;

public class MethodSet extends LinkedHashSet<Method>
{
   public StringList getSignature()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getSignature());
      }
      
      return result;
   }

   public MethodSet withSignature(String value)
   {
      for (Method obj : this)
      {
         obj.withSignature(value);
      }
      
      return this;
   }

   public StringList getReturnType()
   {
      StringList result = new StringList();
      
      for (Method obj : this)
      {
         result.add(obj.getReturnType());
      }
      
      return result;
   }

   public MethodSet withReturnType(String value)
   {
      for (Method obj : this)
      {
         obj.withReturnType(value);
      }
      
      return this;
   }

   public ClazzSet getClazz()
   {
      ClazzSet result = new ClazzSet();
      
      for (Method obj : this)
      {
         result.add(obj.getClazz());
      }
      
      return result;
   }
   public MethodSet withClazz(Clazz value)
   {
      for (Method obj : this)
      {
         obj.withClazz(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Method elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.classes.Method";
   }


   public MethodSet with(Method value)
   {
      this.add(value);
      return this;
   }
   
   public MethodSet without(Method value)
   {
      this.remove(value);
      return this;
   }
}


