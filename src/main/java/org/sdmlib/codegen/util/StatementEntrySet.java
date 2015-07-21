/*
   Copyright (c) 2012 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.codegen.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

public class StatementEntrySet extends SDMSet<StatementEntry>
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



   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (StatementEntry elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.codegen.StatementEntry";
   }


   public StatementEntrySet with(StatementEntry value)
   {
      this.add(value);
      return this;
   }
   
   public StatementEntrySet without(StatementEntry value)
   {
      this.remove(value);
      return this;
   }
   public intList getStartPos()
   {
      intList result = new intList();
      
      for (StatementEntry obj : this)
      {
         result.add(obj.getStartPos());
      }
      
      return result;
   }

   public StatementEntrySet withStartPos(int value)
   {
      for (StatementEntry obj : this)
      {
         obj.withStartPos(value);
      }
      
      return this;
   }

   public intList getEndPos()
   {
      intList result = new intList();
      
      for (StatementEntry obj : this)
      {
         result.add(obj.getEndPos());
      }
      
      return result;
   }

   public StatementEntrySet withEndPos(int value)
   {
      for (StatementEntry obj : this)
      {
         obj.withEndPos(value);
      }
      
      return this;
   }



   public StatementEntryPO startModelPattern()
   {
      return new StatementEntryPO(this.toArray(new StatementEntry[this.size()]));
   }

   public StatementEntrySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         for(Iterator<?> i = ((Collection<?>)value).iterator();i.hasNext();){
            this.add((StatementEntry) i.next());
         }
      }
      else if (value != null)
      {
         this.add((StatementEntry) value);
      }
      
      return this;
   }
   


   public StatementEntryPO hasStatementEntryPO()
   {
      return new StatementEntryPO(this.toArray(new StatementEntry[this.size()]));

   }

   public StatementEntrySet getBodyStatsTransitive()
   {
      StatementEntrySet todo = new StatementEntrySet().with(this);
      
      StatementEntrySet result = new StatementEntrySet();
      
      while ( ! todo.isEmpty())
      {
         StatementEntry current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.addAll(current.getBodyStats().minus(result));
         }
      }
      
      return result;
   }



   public StatementEntrySet getParentTransitive()
   {
      StatementEntrySet todo = new StatementEntrySet().with(this);
      
      StatementEntrySet result = new StatementEntrySet();
      
      while ( ! todo.isEmpty())
      {
         StatementEntry current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getParent()))
            {
               todo.with(current.getParent());
            }
         }
      }
      
      return result;
   }


   public static final StatementEntrySet EMPTY_SET = new StatementEntrySet().withReadOnly(true);
   public StatementEntrySet hasKind(String value)
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         if (value.equals(obj.getKind()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StatementEntrySet hasKind(String lower, String upper)
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         if (lower.compareTo(obj.getKind()) <= 0 && obj.getKind().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StatementEntrySet hasTokenList(ArrayList<String> value)
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         if (value == obj.getTokenList())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StatementEntrySet hasAssignTargetVarName(String value)
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         if (value.equals(obj.getAssignTargetVarName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StatementEntrySet hasAssignTargetVarName(String lower, String upper)
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         if (lower.compareTo(obj.getAssignTargetVarName()) <= 0 && obj.getAssignTargetVarName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StatementEntrySet hasStartPos(int value)
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         if (value == obj.getStartPos())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StatementEntrySet hasStartPos(int lower, int upper)
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         if (lower <= obj.getStartPos() && obj.getStartPos() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StatementEntrySet hasEndPos(int value)
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         if (value == obj.getEndPos())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public StatementEntrySet hasEndPos(int lower, int upper)
   {
      StatementEntrySet result = new StatementEntrySet();
      
      for (StatementEntry obj : this)
      {
         if (lower <= obj.getEndPos() && obj.getEndPos() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
