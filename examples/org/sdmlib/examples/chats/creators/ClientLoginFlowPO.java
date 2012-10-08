package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.chats.ClientLoginFlow;
import org.sdmlib.examples.chats.creators.ClientLoginFlowSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.model.taskflows.PeerProxy;

public class ClientLoginFlowPO extends PatternObject<ClientLoginFlowPO, ClientLoginFlow>
{
   public ClientLoginFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ClientLoginFlowSet matches = new ClientLoginFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ClientLoginFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((ClientLoginFlow) getCurrentMatch()).run();
      }
   }

   public ClientLoginFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ClientLoginFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SDMLibJsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClientLoginFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public ClientLoginFlowPO hasServer(PeerProxy value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ClientLoginFlow.PROPERTY_SERVER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxy getServer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClientLoginFlow) getCurrentMatch()).getServer();
      }
      return null;
   }
   
   public ClientLoginFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ClientLoginFlow.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getTaskNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClientLoginFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public ClientLoginFlowPO hasClientIP(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ClientLoginFlow.PROPERTY_CLIENTIP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getClientIP()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClientLoginFlow) getCurrentMatch()).getClientIP();
      }
      return null;
   }
   
   public ClientLoginFlowPO hasClientPort(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ClientLoginFlow.PROPERTY_CLIENTPORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getClientPort()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClientLoginFlow) getCurrentMatch()).getClientPort();
      }
      return 0;
   }
   
   public ClientLoginFlowPO hasAllMessagesText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ClientLoginFlow.PROPERTY_ALLMESSAGESTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getAllMessagesText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClientLoginFlow) getCurrentMatch()).getAllMessagesText();
      }
      return null;
   }
   
}



