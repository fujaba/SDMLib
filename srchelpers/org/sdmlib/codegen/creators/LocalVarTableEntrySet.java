/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.codegen.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.models.modelsets.StringList;
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



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (LocalVarTableEntry elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.codegen.LocalVarTableEntry";
   }



   public LocalVarTableEntryPO startModelPattern()
   {
      org.sdmlib.models.classes.creators.ModelPattern pattern = new org.sdmlib.models.classes.creators.ModelPattern();
      
      LocalVarTableEntryPO patternObject = pattern.hasElementLocalVarTableEntryPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public LocalVarTableEntrySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<LocalVarTableEntry>)value);
      }
      else if (value != null)
      {
         this.add((LocalVarTableEntry) value);
      }
      
      return this;
   }
   
   public LocalVarTableEntrySet without(LocalVarTableEntry value)
   {
      this.remove(value);
      return this;
   }



   public LocalVarTableEntryPO hasLocalVarTableEntryPO()
   {
      return null;

   }
}




