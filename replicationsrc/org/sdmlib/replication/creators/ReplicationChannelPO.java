package org.sdmlib.replication.creators;

import java.net.Socket;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.creators.ReplicationChannelSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.SharedSpacePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.ReplicationChannelPO;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.models.pattern.AttributeConstraint;

public class ReplicationChannelPO extends
      PatternObject<ReplicationChannelPO, ReplicationChannel>
{
   public ReplicationChannelSet allMatches()
   {
      this.setDoAllMatches(true);

      ReplicationChannelSet matches = new ReplicationChannelSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ReplicationChannel) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public SharedSpacePO hasSharedSpace()
   {
      SharedSpacePO result = new SharedSpacePO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(ReplicationChannel.PROPERTY_SHAREDSPACE, result);

      return result;
   }

   public ReplicationChannelPO hasSharedSpace(SharedSpacePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(ReplicationChannel.PROPERTY_SHAREDSPACE)
         .withSrc(this).withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public SharedSpace getSharedSpace()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChannel) this.getCurrentMatch()).getSharedSpace();
      }
      return null;
   }

   public ReplicationChannelPO hasSocket(Socket value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(ReplicationChannel.PROPERTY_SOCKET).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public Socket getSocket()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChannel) getCurrentMatch()).getSocket();
      }
      return null;
   }

   public ReplicationChannelPO withSocket(Socket value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationChannel) getCurrentMatch()).setSocket(value);
      }
      return this;
   }

   public ReplicationChannelPO createSocket(Socket value)
   {
      this.startCreate().hasSocket(value).endCreate();
      return this;
   }

   public SharedSpacePO createSharedSpace()
   {
      return this.startCreate().hasSharedSpace().endCreate();
   }

   public ReplicationChannelPO createSharedSpace(SharedSpacePO tgt)
   {
      return this.startCreate().hasSharedSpace(tgt).endCreate();
   }

   public ReplicationChannelPO hasTargetNodeId(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChannel.PROPERTY_TARGETNODEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationChannelPO hasTargetNodeId(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChannel.PROPERTY_TARGETNODEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationChannelPO createTargetNodeId(String value)
   {
      this.startCreate().hasTargetNodeId(value).endCreate();
      return this;
   }
   
   public String getTargetNodeId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChannel) getCurrentMatch()).getTargetNodeId();
      }
      return null;
   }
   
   public ReplicationChannelPO withTargetNodeId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationChannel) getCurrentMatch()).setTargetNodeId(value);
      }
      return this;
   }
   
}

