/*
   Copyright (c) 2013 zuendorf 
   
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

package org.sdmlib.models.patterns.example.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.patterns.example.Node;
import org.sdmlib.models.patterns.example.SimpleState;
import org.sdmlib.models.patterns.example.creators.NodeSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class SimpleStateSet extends LinkedHashSet<SimpleState> implements
      org.sdmlib.models.modelsets.ModelSet
{

   public String toString()
   {
      StringList stringList = new StringList();

      for (SimpleState elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.models.patterns.example.SimpleState";
   }

   public SimpleStateSet with(SimpleState value)
   {
      this.add(value);
      return this;
   }

   public SimpleStateSet without(SimpleState value)
   {
      this.remove(value);
      return this;
   }

   public NodeSet getNodes()
   {
      NodeSet result = new NodeSet();

      for (SimpleState obj : this)
      {
         result.addAll(obj.getNodes());
      }

      return result;
   }

   public SimpleStateSet withNodes(Node value)
   {
      for (SimpleState obj : this)
      {
         obj.withNodes(value);
      }

      return this;
   }

   public SimpleStateSet withoutNodes(Node value)
   {
      for (SimpleState obj : this)
      {
         obj.withoutNodes(value);
      }

      return this;
   }

   public SimpleStatePO startModelPattern()
   {
      org.sdmlib.models.patterns.example.creators.ModelPattern pattern = new org.sdmlib.models.patterns.example.creators.ModelPattern();

      SimpleStatePO patternObject = pattern.hasElementSimpleStatePO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public SimpleStateSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SimpleState>) value);
      }
      else if (value != null)
      {
         this.add((SimpleState) value);
      }

      return this;
   }

   public SimpleStatePO hasSimpleStatePO()
   {
      org.sdmlib.models.patterns.example.creators.ModelPattern pattern = new org.sdmlib.models.patterns.example.creators.ModelPattern();

      SimpleStatePO patternObject = pattern.hasElementSimpleStatePO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}




