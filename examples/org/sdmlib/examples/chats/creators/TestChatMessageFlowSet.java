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
   
package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.chats.TestChatMessageFlow;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

public class TestChatMessageFlowSet extends LinkedHashSet<TestChatMessageFlow> implements ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (TestChatMessageFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.chats.TestChatMessageFlow";
   }


   public TestChatMessageFlowSet with(TestChatMessageFlow value)
   {
      this.add(value);
      return this;
   }
   
   public TestChatMessageFlowSet without(TestChatMessageFlow value)
   {
      this.remove(value);
      return this;
   }
   
   //==========================================================================
   
   public TestChatMessageFlowSet run()
   {
      for (TestChatMessageFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   
   //==========================================================================
   
   public LinkedHashSet<Object> getTaskNames()
   {
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      for (TestChatMessageFlow obj : this)
      {
         result.add(obj.getTaskNames());
      }
      return result;
   }

   public StringList getMsg()
   {
      StringList result = new StringList();
      
      for (TestChatMessageFlow obj : this)
      {
         result.add(obj.getMsg());
      }
      
      return result;
   }

   public TestChatMessageFlowSet withMsg(String value)
   {
      for (TestChatMessageFlow obj : this)
      {
         obj.withMsg(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (TestChatMessageFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public TestChatMessageFlowSet withTaskNo(int value)
   {
      for (TestChatMessageFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

}

