package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.ChatServer;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class ChatServerPO extends PatternObject<ChatServerPO, ChatServer>
{
   public ChatServerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChatServerSet matches = new ChatServerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChatServer) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ChatServerPO hasAllMessages(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChatServer.PROPERTY_ALLMESSAGES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getAllMessages()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatServer) getCurrentMatch()).getAllMessages();
      }
      return null;
   }
   
   public ChatServerPO hasAllPeers(PeerProxySet value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChatServer.PROPERTY_ALLPEERS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxySet getAllPeers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatServer) getCurrentMatch()).getAllPeers();
      }
      return null;
   }
   
}

