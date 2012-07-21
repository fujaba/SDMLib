package org.sdmlib.codegen.creators;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;

public class StatementEntrySet extends LinkedHashSet<StatementEntry>
{
   public StringList getKind()
   {
      StringList result = new StringList();
      
      for (StatementEntry obj : this)
      {
         result.add(obj.getKind());
      }
      
      return result;
   }

   public StatementEntrySet withKind(String value)
   {
      for (StatementEntry obj : this)
      {
         obj.withKind(value);
      }
      
      return this;
   }

   public ArrayList<String> getTokenList()
   {
      ArrayList<String> result = new ArrayList<String>();
      
      for (StatementEntry obj : this)
      {
         result.addAll(obj.getTokenList());
      }
      
      return result;
   }

   public StatementEntrySet withTokenList(ArrayList<String> value)
   {
      for (StatementEntry obj : this)
      {
         obj.withTokenList(value);
      }
      
      return this;
   }

   public StringList getAssignTargetVarName()
   {
      StringList result = new StringList();
      
      for (StatementEntry obj : this)
      {
         result.add(obj.getAssignTargetVarName());
      }
      
      return result;
   }

   public StatementEntrySet withAssignTargetVarName(String value)
   {
      for (StatementEntry obj : this)
      {
         obj.withAssignTargetVarName(value);
      }
      
      return this;
   }

   public StatementEntrySet getBodyStats()
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         result.addAll(obj.getBodyStats());
      }
      
      return result;
   }
   public StatementEntrySet withBodyStats(StatementEntry value)
   {
      for (StatementEntry obj : this)
      {
         obj.withBodyStats(value);
      }
      
      return this;
   }

   public StatementEntrySet withoutBodyStats(StatementEntry value)
   {
      for (StatementEntry obj : this)
      {
         obj.withoutBodyStats(value);
      }
      
      return this;
   }

   public StatementEntrySet getParent()
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }
   public StatementEntrySet withParent(StatementEntry value)
   {
      for (StatementEntry obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}

