package org.sdmlib.examples.replication.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.replication.creators.ChatRootPO;
import org.sdmlib.examples.replication.ChatRoot;
import org.sdmlib.examples.replication.creators.ChatMsgPO;
import org.sdmlib.examples.replication.ChatMsg;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public ChatRootPO hasElementChatRootPO()
   {
      ChatRootPO value = new ChatRootPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ChatRootPO hasElementChatRootPO(ChatRoot hostGraphObject)
   {
      ChatRootPO value = new ChatRootPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ChatMsgPO hasElementChatMsgPO()
   {
      ChatMsgPO value = new ChatMsgPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ChatMsgPO hasElementChatMsgPO(ChatMsg hostGraphObject)
   {
      ChatMsgPO value = new ChatMsgPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


