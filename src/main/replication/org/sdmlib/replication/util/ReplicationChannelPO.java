package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ReplicationChannel;
import java.net.Socket;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.replication.util.SharedSpacePO;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.util.ReplicationChannelPO;

public class ReplicationChannelPO extends PatternObject<ReplicationChannelPO, ReplicationChannel>
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


   public ReplicationChannelPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ReplicationChannelPO(ReplicationChannel... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ReplicationChannelPO hasSocket(Socket value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationChannel.PROPERTY_SOCKET)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationChannelPO createSocket(Socket value)
   {
      this.startCreate().hasSocket(value).endCreate();
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
   
   public ReplicationChannelPO hasTargetNodeId(String value)
   {
      new AttributeConstraint()
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
      new AttributeConstraint()
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
   
   public SharedSpacePO hasSharedSpace()
   {
      SharedSpacePO result = new SharedSpacePO(new SharedSpace[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationChannel.PROPERTY_SHAREDSPACE, result);
      
      return result;
   }

   public SharedSpacePO createSharedSpace()
   {
      return this.startCreate().hasSharedSpace().endCreate();
   }

   public ReplicationChannelPO hasSharedSpace(SharedSpacePO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationChannel.PROPERTY_SHAREDSPACE);
   }

   public ReplicationChannelPO createSharedSpace(SharedSpacePO tgt)
   {
      return this.startCreate().hasSharedSpace(tgt).endCreate();
   }

   public SharedSpace getSharedSpace()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChannel) this.getCurrentMatch()).getSharedSpace();
      }
      return null;
   }

}
