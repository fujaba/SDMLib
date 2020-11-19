package org.sdmlib.replication.util;

import java.net.Socket;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.SharedSpace;

public class SharedSpacePO extends PatternObject<SharedSpacePO, SharedSpace>
{

    public SharedSpaceSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SharedSpaceSet matches = new SharedSpaceSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SharedSpace) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SharedSpacePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SharedSpacePO(SharedSpace... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public SharedSpacePO hasSocket(Socket value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_SOCKET)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO createSocket(Socket value)
   {
      this.startCreate().hasSocket(value).endCreate();
      return this;
   }
   
   public Socket getSocket()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) getCurrentMatch()).getSocket();
      }
      return null;
   }
   
   public SharedSpacePO withSocket(Socket value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpace) getCurrentMatch()).setSocket(value);
      }
      return this;
   }
   
   public SharedSpacePO hasTargetNodeId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_TARGETNODEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO hasTargetNodeId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_TARGETNODEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO createTargetNodeId(String value)
   {
      this.startCreate().hasTargetNodeId(value).endCreate();
      return this;
   }
   
   public String getTargetNodeId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) getCurrentMatch()).getTargetNodeId();
      }
      return null;
   }
   
   public SharedSpacePO withTargetNodeId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpace) getCurrentMatch()).setTargetNodeId(value);
      }
      return this;
   }
   
   public ReplicationNodePO hasNode()
   {
      ReplicationNodePO result = new ReplicationNodePO(new ReplicationNode[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpace.PROPERTY_NODE, result);
      
      return result;
   }

   public ReplicationNodePO createNode()
   {
      return this.startCreate().hasNode().endCreate();
   }

   public SharedSpacePO hasNode(ReplicationNodePO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_NODE);
   }

   public SharedSpacePO createNode(ReplicationNodePO tgt)
   {
      return this.startCreate().hasNode(tgt).endCreate();
   }

   public ReplicationNode getNode()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) this.getCurrentMatch()).getNode();
      }
      return null;
   }

   public ReplicationChannelPO hasChannels()
   {
      ReplicationChannelPO result = new ReplicationChannelPO(new ReplicationChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpace.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public ReplicationChannelPO createChannels()
   {
      return this.startCreate().hasChannels().endCreate();
   }

   public SharedSpacePO hasChannels(ReplicationChannelPO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_CHANNELS);
   }

   public SharedSpacePO createChannels(ReplicationChannelPO tgt)
   {
      return this.startCreate().hasChannels(tgt).endCreate();
   }

   public ReplicationChannelSet getChannels()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) this.getCurrentMatch()).getChannels();
      }
      return null;
   }

   public SharedSpacePO hasSpaceId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO hasSpaceId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO createSpaceId(String value)
   {
      this.startCreate().hasSpaceId(value).endCreate();
      return this;
   }
   
   public String getSpaceId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) getCurrentMatch()).getSpaceId();
      }
      return null;
   }
   
   public SharedSpacePO withSpaceId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpace) getCurrentMatch()).setSpaceId(value);
      }
      return this;
   }
   
   public SharedSpacePO hasHistory(ChangeHistory value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_HISTORY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO createHistory(ChangeHistory value)
   {
      this.startCreate().hasHistory(value).endCreate();
      return this;
   }
   
   public ChangeHistory getHistory()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) getCurrentMatch()).getHistory();
      }
      return null;
   }
   
   public SharedSpacePO withHistory(ChangeHistory value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpace) getCurrentMatch()).setHistory(value);
      }
      return this;
   }
   
   public SharedSpacePO hasLastChangeId(long value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO hasLastChangeId(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO createLastChangeId(long value)
   {
      this.startCreate().hasLastChangeId(value).endCreate();
      return this;
   }
   
   public long getLastChangeId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) getCurrentMatch()).getLastChangeId();
      }
      return 0;
   }
   
   public SharedSpacePO withLastChangeId(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpace) getCurrentMatch()).setLastChangeId(value);
      }
      return this;
   }
   
   public SharedSpacePO hasNodeId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_NODEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO hasNodeId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_NODEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO createNodeId(String value)
   {
      this.startCreate().hasNodeId(value).endCreate();
      return this;
   }
   
   public String getNodeId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) getCurrentMatch()).getNodeId();
      }
      return null;
   }
   
   public SharedSpacePO withNodeId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpace) getCurrentMatch()).setNodeId(value);
      }
      return this;
   }  
   public SharedSpacePO hasJavaFXApplication(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpacePO createJavaFXApplication(boolean value)
   {
      this.startCreate().hasJavaFXApplication(value).endCreate();
      return this;
   }
   
   public boolean getJavaFXApplication()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) getCurrentMatch()).isJavaFXApplication();
      }
      return false;
   }
   
   public SharedSpacePO withJavaFXApplication(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpace) getCurrentMatch()).setJavaFXApplication(value);
      }
      return this;
   }
   
   public SharedSpacePO filterSpaceId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO filterSpaceId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO filterHistory(ChangeHistory value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_HISTORY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO filterLastChangeId(long value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO filterLastChangeId(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO filterNodeId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_NODEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO filterNodeId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_NODEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO filterJavaFXApplication(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationNodePO filterNode()
   {
      ReplicationNodePO result = new ReplicationNodePO(new ReplicationNode[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpace.PROPERTY_NODE, result);
      
      return result;
   }

   public SharedSpacePO filterNode(ReplicationNodePO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_NODE);
   }

   public ReplicationChannelPO filterChannels()
   {
      ReplicationChannelPO result = new ReplicationChannelPO(new ReplicationChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpace.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public SharedSpacePO filterChannels(ReplicationChannelPO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_CHANNELS);
   }

   public ChangeHistoryPO filterHistory()
   {
      ChangeHistoryPO result = new ChangeHistoryPO(new ChangeHistory[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpace.PROPERTY_HISTORY, result);
      
      return result;
   }

   public ChangeHistoryPO createHistory()
   {
      return this.startCreate().filterHistory().endCreate();
   }

   public SharedSpacePO filterHistory(ChangeHistoryPO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_HISTORY);
   }

   public SharedSpacePO createHistory(ChangeHistoryPO tgt)
   {
      return this.startCreate().filterHistory(tgt).endCreate();
   }


   public SharedSpacePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public SharedSpacePO createJavaFXApplicationCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createJavaFXApplicationAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createLastChangeIdCondition(long value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createLastChangeIdCondition(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createLastChangeIdAssignment(long value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createNodeIdCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_NODEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createNodeIdCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_NODEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createNodeIdAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_NODEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createSpaceIdCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createSpaceIdCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SharedSpacePO createSpaceIdAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationChannelPO createChannelsPO()
   {
      ReplicationChannelPO result = new ReplicationChannelPO(new ReplicationChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpace.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public ReplicationChannelPO createChannelsPO(String modifier)
   {
      ReplicationChannelPO result = new ReplicationChannelPO(new ReplicationChannel[]{});
      
      result.setModifier(modifier);
      super.hasLink(SharedSpace.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public SharedSpacePO createChannelsLink(ReplicationChannelPO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_CHANNELS);
   }

   public SharedSpacePO createChannelsLink(ReplicationChannelPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_CHANNELS, modifier);
   }

   public ChangeHistoryPO createHistoryPO()
   {
      ChangeHistoryPO result = new ChangeHistoryPO(new ChangeHistory[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpace.PROPERTY_HISTORY, result);
      
      return result;
   }

   public ChangeHistoryPO createHistoryPO(String modifier)
   {
      ChangeHistoryPO result = new ChangeHistoryPO(new ChangeHistory[]{});
      
      result.setModifier(modifier);
      super.hasLink(SharedSpace.PROPERTY_HISTORY, result);
      
      return result;
   }

   public SharedSpacePO createHistoryLink(ChangeHistoryPO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_HISTORY);
   }

   public SharedSpacePO createHistoryLink(ChangeHistoryPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_HISTORY, modifier);
   }

   public ReplicationNodePO createNodePO()
   {
      ReplicationNodePO result = new ReplicationNodePO(new ReplicationNode[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpace.PROPERTY_NODE, result);
      
      return result;
   }

   public ReplicationNodePO createNodePO(String modifier)
   {
      ReplicationNodePO result = new ReplicationNodePO(new ReplicationNode[]{});
      
      result.setModifier(modifier);
      super.hasLink(SharedSpace.PROPERTY_NODE, result);
      
      return result;
   }

   public SharedSpacePO createNodeLink(ReplicationNodePO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_NODE);
   }

   public SharedSpacePO createNodeLink(ReplicationNodePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SharedSpace.PROPERTY_NODE, modifier);
   }

}
