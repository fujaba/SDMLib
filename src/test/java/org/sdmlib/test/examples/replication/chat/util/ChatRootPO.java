package org.sdmlib.test.examples.replication.chat.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.replication.chat.ChatRoot;
import org.sdmlib.test.examples.replication.chat.ChatUser;

public class ChatRootPO extends PatternObject<ChatRootPO, ChatRoot>
{

    public ChatRootSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChatRootSet matches = new ChatRootSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChatRoot) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ChatRootPO(){
      newInstance(org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChatRootPO(ChatRoot... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ChatUserPO hasUsers()
   {
      ChatUserPO result = new ChatUserPO(new ChatUser[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatRoot.PROPERTY_USERS, result);
      
      return result;
   }

   public ChatUserPO createUsers()
   {
      return this.startCreate().hasUsers().endCreate();
   }

   public ChatRootPO hasUsers(ChatUserPO tgt)
   {
      return hasLinkConstraint(tgt, ChatRoot.PROPERTY_USERS);
   }

   public ChatRootPO createUsers(ChatUserPO tgt)
   {
      return this.startCreate().hasUsers(tgt).endCreate();
   }

   public ChatUserSet getUsers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatRoot) this.getCurrentMatch()).getUsers();
      }
      return null;
   }

   public ChatUserPO filterUsers()
   {
      ChatUserPO result = new ChatUserPO(new ChatUser[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatRoot.PROPERTY_USERS, result);
      
      return result;
   }

   public ChatRootPO filterUsers(ChatUserPO tgt)
   {
      return hasLinkConstraint(tgt, ChatRoot.PROPERTY_USERS);
   }


   public ChatRootPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ChatUserPO createUsersPO()
   {
      ChatUserPO result = new ChatUserPO(new ChatUser[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatRoot.PROPERTY_USERS, result);
      
      return result;
   }

   public ChatUserPO createUsersPO(String modifier)
   {
      ChatUserPO result = new ChatUserPO(new ChatUser[]{});
      
      result.setModifier(modifier);
      super.hasLink(ChatRoot.PROPERTY_USERS, result);
      
      return result;
   }

   public ChatRootPO createUsersLink(ChatUserPO tgt)
   {
      return hasLinkConstraint(tgt, ChatRoot.PROPERTY_USERS);
   }

   public ChatRootPO createUsersLink(ChatUserPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ChatRoot.PROPERTY_USERS, modifier);
   }

}
