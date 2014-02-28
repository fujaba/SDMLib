package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.creators.SharedSpaceSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.ReplicationNodePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.SharedSpacePO;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.creators.ReplicationChannelPO;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.creators.ReplicationChannelSet;

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
   
   public SharedSpacePO hasSpaceId(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public ReplicationNodePO hasNode()
   {
      ReplicationNodePO result = new ReplicationNodePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(SharedSpace.PROPERTY_NODE, result);
      
      return result;
   }

   public SharedSpacePO hasNode(ReplicationNodePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(SharedSpace.PROPERTY_NODE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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
      ReplicationChannelPO result = new ReplicationChannelPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(SharedSpace.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public SharedSpacePO hasChannels(ReplicationChannelPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(SharedSpace.PROPERTY_CHANNELS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ReplicationChannelSet getChannels()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpace) this.getCurrentMatch()).getChannels();
      }
      return null;
   }

   public SharedSpacePO hasHistory(ChangeHistory value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_HISTORY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SharedSpace.PROPERTY_NODEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public SharedSpacePO hasSpaceId(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
   
   public SharedSpacePO createHistory(ChangeHistory value)
   {
      this.startCreate().hasHistory(value).endCreate();
      return this;
   }
   
   public SharedSpacePO hasLastChangeId(long lower, long upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
   
   public SharedSpacePO hasNodeId(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
   
   public ReplicationNodePO createNode()
   {
      return this.startCreate().hasNode().endCreate();
   }

   public SharedSpacePO createNode(ReplicationNodePO tgt)
   {
      return this.startCreate().hasNode(tgt).endCreate();
   }

   public ReplicationChannelPO createChannels()
   {
      return this.startCreate().hasChannels().endCreate();
   }

   public SharedSpacePO createChannels(ReplicationChannelPO tgt)
   {
      return this.startCreate().hasChannels(tgt).endCreate();
   }

}






