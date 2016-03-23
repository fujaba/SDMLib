package org.sdmlib.test.examples.replication.chat.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.replication.chat.ChatChannel;
import org.sdmlib.test.examples.replication.chat.ChatRoot;
import org.sdmlib.test.examples.replication.chat.ChatUser;
import org.sdmlib.test.examples.replication.chat.util.ChatRootPO;
import org.sdmlib.test.examples.replication.chat.util.ChatUserPO;
import org.sdmlib.test.examples.replication.chat.util.ChatChannelPO;

public class ChatUserPO extends PatternObject<ChatUserPO, ChatUser>
{

    public ChatUserSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChatUserSet matches = new ChatUserSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChatUser) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ChatUserPO(){
      newInstance(org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChatUserPO(ChatUser... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ChatRootPO hasChatRoot()
   {
      ChatRootPO result = new ChatRootPO(new ChatRoot[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatUser.PROPERTY_CHATROOT, result);
      
      return result;
   }

   public ChatRootPO createChatRoot()
   {
      return this.startCreate().hasChatRoot().endCreate();
   }

   public ChatUserPO hasChatRoot(ChatRootPO tgt)
   {
      return hasLinkConstraint(tgt, ChatUser.PROPERTY_CHATROOT);
   }

   public ChatUserPO createChatRoot(ChatRootPO tgt)
   {
      return this.startCreate().hasChatRoot(tgt).endCreate();
   }

   public ChatRoot getChatRoot()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatUser) this.getCurrentMatch()).getChatRoot();
      }
      return null;
   }

   public ChatChannelPO hasChannels()
   {
      ChatChannelPO result = new ChatChannelPO(new ChatChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatUser.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public ChatChannelPO createChannels()
   {
      return this.startCreate().hasChannels().endCreate();
   }

   public ChatUserPO hasChannels(ChatChannelPO tgt)
   {
      return hasLinkConstraint(tgt, ChatUser.PROPERTY_CHANNELS);
   }

   public ChatUserPO createChannels(ChatChannelPO tgt)
   {
      return this.startCreate().hasChannels(tgt).endCreate();
   }

   public ChatChannelSet getChannels()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatUser) this.getCurrentMatch()).getChannels();
      }
      return null;
   }

   public ChatUserPO hasUserName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChatUser.PROPERTY_USERNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChatUserPO hasUserName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatUser.PROPERTY_USERNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChatUserPO createUserName(String value)
   {
      this.startCreate().hasUserName(value).endCreate();
      return this;
   }
   
   public String getUserName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatUser) getCurrentMatch()).getUserName();
      }
      return null;
   }
   
   public ChatUserPO withUserName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChatUser) getCurrentMatch()).setUserName(value);
      }
      return this;
   }
   
   public ChatUserPO filterUserName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChatUser.PROPERTY_USERNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatUserPO filterUserName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatUser.PROPERTY_USERNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatRootPO filterChatRoot()
   {
      ChatRootPO result = new ChatRootPO(new ChatRoot[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatUser.PROPERTY_CHATROOT, result);
      
      return result;
   }

   public ChatUserPO filterChatRoot(ChatRootPO tgt)
   {
      return hasLinkConstraint(tgt, ChatUser.PROPERTY_CHATROOT);
   }

   public ChatChannelPO filterChannels()
   {
      ChatChannelPO result = new ChatChannelPO(new ChatChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatUser.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public ChatUserPO filterChannels(ChatChannelPO tgt)
   {
      return hasLinkConstraint(tgt, ChatUser.PROPERTY_CHANNELS);
   }

}
