package org.sdmlib.test.examples.replication.chat.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.replication.chat.ChatChannel;
import org.sdmlib.test.examples.replication.chat.ChatMsg;
import org.sdmlib.test.examples.replication.chat.ChatUser;

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
      newInstance(org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChatChannelPO(ChatChannel... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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

   public ChatUserPO filterUsers()
   {
      ChatUserPO result = new ChatUserPO(new ChatUser[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatChannel.PROPERTY_USERS, result);
      
      return result;
   }

   public ChatChannelPO filterUsers(ChatUserPO tgt)
   {
      return hasLinkConstraint(tgt, ChatChannel.PROPERTY_USERS);
   }

   public ChatMsgPO filterMsgs()
   {
      ChatMsgPO result = new ChatMsgPO(new ChatMsg[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatChannel.PROPERTY_MSGS, result);
      
      return result;
   }

   public ChatChannelPO filterMsgs(ChatMsgPO tgt)
   {
      return hasLinkConstraint(tgt, ChatChannel.PROPERTY_MSGS);
   }


   public ChatChannelPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ChatUserPO createUsersPO()
   {
      ChatUserPO result = new ChatUserPO(new ChatUser[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatChannel.PROPERTY_USERS, result);
      
      return result;
   }

   public ChatUserPO createUsersPO(String modifier)
   {
      ChatUserPO result = new ChatUserPO(new ChatUser[]{});
      
      result.setModifier(modifier);
      super.hasLink(ChatChannel.PROPERTY_USERS, result);
      
      return result;
   }

   public ChatChannelPO createUsersLink(ChatUserPO tgt)
   {
      return hasLinkConstraint(tgt, ChatChannel.PROPERTY_USERS);
   }

   public ChatChannelPO createUsersLink(ChatUserPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ChatChannel.PROPERTY_USERS, modifier);
   }

   public ChatMsgPO createMsgsPO()
   {
      ChatMsgPO result = new ChatMsgPO(new ChatMsg[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatChannel.PROPERTY_MSGS, result);
      
      return result;
   }

   public ChatMsgPO createMsgsPO(String modifier)
   {
      ChatMsgPO result = new ChatMsgPO(new ChatMsg[]{});
      
      result.setModifier(modifier);
      super.hasLink(ChatChannel.PROPERTY_MSGS, result);
      
      return result;
   }

   public ChatChannelPO createMsgsLink(ChatMsgPO tgt)
   {
      return hasLinkConstraint(tgt, ChatChannel.PROPERTY_MSGS);
   }

   public ChatChannelPO createMsgsLink(ChatMsgPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ChatChannel.PROPERTY_MSGS, modifier);
   }

}
