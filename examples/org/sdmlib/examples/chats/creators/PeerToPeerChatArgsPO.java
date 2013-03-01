package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.PeerToPeerChatArgs;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class PeerToPeerChatArgsPO extends PatternObject<PeerToPeerChatArgsPO, PeerToPeerChatArgs>
{
   public PeerToPeerChatArgsSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PeerToPeerChatArgsSet matches = new PeerToPeerChatArgsSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PeerToPeerChatArgs) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public PeerToPeerChatArgsPO hasUserName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PeerToPeerChatArgs.PROPERTY_USERNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getUserName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PeerToPeerChatArgs) getCurrentMatch()).getUserName();
      }
      return null;
   }
   
   public PeerToPeerChatArgsPO hasLocalPort(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PeerToPeerChatArgs.PROPERTY_LOCALPORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getLocalPort()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PeerToPeerChatArgs) getCurrentMatch()).getLocalPort();
      }
      return 0;
   }
   
   public PeerToPeerChatArgsPO hasPeerIp(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PeerToPeerChatArgs.PROPERTY_PEERIP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getPeerIp()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PeerToPeerChatArgs) getCurrentMatch()).getPeerIp();
      }
      return null;
   }
   
   public PeerToPeerChatArgsPO hasPeerPort(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PeerToPeerChatArgs.PROPERTY_PEERPORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getPeerPort()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PeerToPeerChatArgs) getCurrentMatch()).getPeerPort();
      }
      return 0;
   }
   
}

