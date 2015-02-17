package org.sdmlib.examples.replication.chat.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.replication.chat.ChatChannel;
import org.sdmlib.examples.replication.chat.util.ChatUserPO;
import org.sdmlib.examples.replication.chat.ChatUser;
import org.sdmlib.examples.replication.chat.util.ChatChannelPO;
import org.sdmlib.examples.replication.chat.util.ChatUserSet;
import org.sdmlib.examples.replication.chat.util.ChatMsgPO;
import org.sdmlib.examples.replication.chat.ChatMsg;
import org.sdmlib.examples.replication.chat.util.ChatMsgSet;

public class ChatChannelPO extends PatternObject<ChatChannelPO, ChatChannel>
{

    public ChatChannelSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChatChannelSet matches = new ChatChannelSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChatChannel) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ChatChannelPO(){
      newInstance(org.sdmlib.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChatChannelPO(ChatChannel... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ChatUserPO hasUsers()
   {
      ChatUserPO result = new ChatUserPO(new ChatUser[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatChannel.PROPERTY_USERS, result);
      
      return result;
   }

   public ChatUserPO createUsers()
   {
      return this.startCreate().hasUsers().endCreate();
   }

   public ChatChannelPO hasUsers(ChatUserPO tgt)
   {
      return hasLinkConstraint(tgt, ChatChannel.PROPERTY_USERS);
   }

   public ChatChannelPO createUsers(ChatUserPO tgt)
   {
      return this.startCreate().hasUsers(tgt).endCreate();
   }

   public ChatUserSet getUsers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatChannel) this.getCurrentMatch()).getUsers();
      }
      return null;
   }

   public ChatMsgPO hasMsgs()
   {
      ChatMsgPO result = new ChatMsgPO(new ChatMsg[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatChannel.PROPERTY_MSGS, result);
      
      return result;
   }

   public ChatMsgPO createMsgs()
   {
      return this.startCreate().hasMsgs().endCreate();
   }

   public ChatChannelPO hasMsgs(ChatMsgPO tgt)
   {
      return hasLinkConstraint(tgt, ChatChannel.PROPERTY_MSGS);
   }

   public ChatChannelPO createMsgs(ChatMsgPO tgt)
   {
      return this.startCreate().hasMsgs(tgt).endCreate();
   }

   public ChatMsgSet getMsgs()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatChannel) this.getCurrentMatch()).getMsgs();
      }
      return null;
   }

}
