package org.sdmlib.codegen.creators;

import java.util.LinkedHashSet;
import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;

public class LocalVarTableEntrySet extends LinkedHashSet<LocalVarTableEntry>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (LocalVarTableEntry obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public LocalVarTableEntrySet withName(String value)
   {
      for (LocalVarTableEntry obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }

   public StringList getType()
   {
      StringList result = new StringList();
      
      for (LocalVarTableEntry obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public LocalVarTableEntrySet withType(String value)
   {
      for (LocalVarTableEntry obj : this)
      {
         obj.withType(value);
      }
      
      return this;
   }

   public intList getStartPos()
   {
      intList result = new intList();
      
      for (LocalVarTableEntry obj : this)
      {
         result.add(obj.getStartPos());
      }
      
      return result;
   }

   public LocalVarTableEntrySet withStartPos(int value)
   {
      for (LocalVarTableEntry obj : this)
      {
         obj.withStartPos(value);
      }
      
      return this;
   }

   public intList getEndPos()
   {
      intList result = new intList();
      
      for (LocalVarTableEntry obj : this)
      {
         result.add(obj.getEndPos());
      }
      
      return result;
   }

   public LocalVarTableEntrySet withEndPos(int value)
   {
      for (LocalVarTableEntry obj : this)
      {
         obj.withEndPos(value);
      }
      
      return this;
   }

}

