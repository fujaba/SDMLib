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

import org.sdmlib.examples.chats.PeerToPeerChatArgs;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;

public class PeerToPeerChatArgsSet extends LinkedHashSet<PeerToPeerChatArgs>
{
   public StringList getUserName()
   {
      StringList result = new StringList();
      
      for (PeerToPeerChatArgs obj : this)
      {
         result.add(obj.getUserName());
      }
      
      return result;
   }

   public PeerToPeerChatArgsSet withUserName(String value)
   {
      for (PeerToPeerChatArgs obj : this)
      {
         obj.withUserName(value);
      }
      
      return this;
   }

   public intList getLocalPort()
   {
      intList result = new intList();
      
      for (PeerToPeerChatArgs obj : this)
      {
         result.add(obj.getLocalPort());
      }
      
      return result;
   }

   public PeerToPeerChatArgsSet withLocalPort(int value)
   {
      for (PeerToPeerChatArgs obj : this)
      {
         obj.withLocalPort(value);
      }
      
      return this;
   }

   public StringList getPeerIp()
   {
      StringList result = new StringList();
      
      for (PeerToPeerChatArgs obj : this)
      {
         result.add(obj.getPeerIp());
      }
      
      return result;
   }

   public PeerToPeerChatArgsSet withPeerIp(String value)
   {
      for (PeerToPeerChatArgs obj : this)
      {
         obj.withPeerIp(value);
      }
      
      return this;
   }

   public intList getPeerPort()
   {
      intList result = new intList();
      
      for (PeerToPeerChatArgs obj : this)
      {
         result.add(obj.getPeerPort());
      }
      
      return result;
   }

   public PeerToPeerChatArgsSet withPeerPort(int value)
   {
      for (PeerToPeerChatArgs obj : this)
      {
         obj.withPeerPort(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (PeerToPeerChatArgs elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public PeerToPeerChatArgsSet with(PeerToPeerChatArgs value)
   {
      this.add(value);
      return this;
   }
   
   public PeerToPeerChatArgsSet without(PeerToPeerChatArgs value)
   {
      this.remove(value);
      return this;
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.chats.PeerToPeerChatArgs";
   }
}




