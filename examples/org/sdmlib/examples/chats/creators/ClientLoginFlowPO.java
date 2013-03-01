package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.ClientLoginFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

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
   
   public TaskFlowPO hasSubFlow()
   {
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public ClientLoginFlowPO hasSubFlow(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TaskFlow.PROPERTY_SUBFLOW)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public TaskFlow getSubFlow()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getSubFlow();
      }
      return null;
   }

   public TaskFlowPO hasParent()
   {
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public ClientLoginFlowPO hasParent(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TaskFlow.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public TaskFlow getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getParent();
      }
      return null;
   }

}




