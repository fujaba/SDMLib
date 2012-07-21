package org.sdmlib.codegen.creators;

import java.util.LinkedHashSet;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;

public class SymTabEntrySet extends LinkedHashSet<SymTabEntry>
{
   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }

   public SymTabEntrySet withKind(String value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withKind(value);
      }
      
      return this;
   }

   public StringList getMemberName()
   {
      StringList result = new StringList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getMemberName());
      }
      
      return result;
   }

   public SymTabEntrySet withMemberName(String value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withMemberName(value);
      }
      
      return this;
   }

   public StringList getType()
   {
      StringList result = new StringList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getType());
      }
      
      return result;
   }

   public SymTabEntrySet withType(String value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withType(value);
      }
      
      return this;
   }

   public intList getStartPos()
   {
      intList result = new intList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getStartPos());
      }
      
      return result;
   }

   public SymTabEntrySet withStartPos(int value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withStartPos(value);
      }
      
      return this;
   }

   public intList getBodyStartPos()
   {
      intList result = new intList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getBodyStartPos());
      }
      
      return result;
   }

   public SymTabEntrySet withBodyStartPos(int value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withBodyStartPos(value);
      }
      
      return this;
   }

   public intList getEndPos()
   {
      intList result = new intList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getEndPos());
      }
      
      return result;
   }

   public SymTabEntrySet withEndPos(int value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withEndPos(value);
      }
      
      return this;
   }

   public StringList getModifiers()
   {
      StringList result = new StringList();
      
      for (SymTabEntry obj : this)
      {
         result.add(obj.getModifiers());
      }
      
      return result;
   }

   public SymTabEntrySet withModifiers(String value)
   {
      for (SymTabEntry obj : this)
      {
         obj.withModifiers(value);
      }
      
      return this;
   }

}

