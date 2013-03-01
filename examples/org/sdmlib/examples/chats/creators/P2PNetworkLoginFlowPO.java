package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.P2PNetworkLoginFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class P2PNetworkLoginFlowPO extends PatternObject<P2PNetworkLoginFlowPO, P2PNetworkLoginFlow>
{
   public P2PNetworkLoginFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      P2PNetworkLoginFlowSet matches = new P2PNetworkLoginFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((P2PNetworkLoginFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((P2PNetworkLoginFlow) getCurrentMatch()).run();
      }
   }

   
   //==========================================================================
   
   public Object getTaskNames()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((P2PNetworkLoginFlow) getCurrentMatch()).getTaskNames();
      }
      return null;
   }

   public P2PNetworkLoginFlowPO hasFirstPeer(PeerProxy value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PNetworkLoginFlow.PROPERTY_FIRSTPEER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxy getFirstPeer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((P2PNetworkLoginFlow) getCurrentMatch()).getFirstPeer();
      }
      return null;
   }
   
   public P2PNetworkLoginFlowPO withFirstPeer(PeerProxy value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PNetworkLoginFlow) getCurrentMatch()).setFirstPeer(value);
      }
      return this;
   }
   
   public P2PNetworkLoginFlowPO hasClientName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PNetworkLoginFlow.PROPERTY_CLIENTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getClientName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((P2PNetworkLoginFlow) getCurrentMatch()).getClientName();
      }
      return null;
   }
   
   public P2PNetworkLoginFlowPO withClientName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PNetworkLoginFlow) getCurrentMatch()).setClientName(value);
      }
      return this;
   }
   
   public P2PNetworkLoginFlowPO hasPeerList(PeerProxySet value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PNetworkLoginFlow.PROPERTY_PEERLIST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxySet getPeerList()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((P2PNetworkLoginFlow) getCurrentMatch()).getPeerList();
      }
      return null;
   }
   
   public P2PNetworkLoginFlowPO withPeerList(PeerProxySet value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PNetworkLoginFlow) getCurrentMatch()).setPeerList(value);
      }
      return this;
   }
   
   public P2PNetworkLoginFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PNetworkLoginFlow.PROPERTY_TASKNO)
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
         return ((P2PNetworkLoginFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public P2PNetworkLoginFlowPO withTaskNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PNetworkLoginFlow) getCurrentMatch()).setTaskNo(value);
      }
      return this;
   }
   
   public P2PNetworkLoginFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PNetworkLoginFlow.PROPERTY_IDMAP)
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
         return ((P2PNetworkLoginFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public P2PNetworkLoginFlowPO withIdMap(SDMLibJsonIdMap value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PNetworkLoginFlow) getCurrentMatch()).setIdMap(value);
      }
      return this;
   }
   
   public P2PNetworkLoginFlowPO hasClientPeer(PeerProxy value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PNetworkLoginFlow.PROPERTY_CLIENTPEER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxy getClientPeer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((P2PNetworkLoginFlow) getCurrentMatch()).getClientPeer();
      }
      return null;
   }
   
   public P2PNetworkLoginFlowPO withClientPeer(PeerProxy value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PNetworkLoginFlow) getCurrentMatch()).setClientPeer(value);
      }
      return this;
   }
   
   public P2PNetworkLoginFlowPO hasAllMessages(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(P2PNetworkLoginFlow.PROPERTY_ALLMESSAGES)
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
         return ((P2PNetworkLoginFlow) getCurrentMatch()).getAllMessages();
      }
      return null;
   }
   
   public P2PNetworkLoginFlowPO withAllMessages(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((P2PNetworkLoginFlow) getCurrentMatch()).setAllMessages(value);
      }
      return this;
   }
   
   public TaskFlowPO hasSubFlow()
   {
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public P2PNetworkLoginFlowPO hasSubFlow(TaskFlowPO tgt)
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

   public P2PNetworkLoginFlowPO hasParent(TaskFlowPO tgt)
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



