package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.SharedSpaceProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.replication.util.SharedSpaceProxyPO;
import org.sdmlib.replication.util.SharedSpaceProxySet;
import org.sdmlib.replication.util.ReplicationChannelPO;
import org.sdmlib.replication.ReplicationChannel;

public class SharedSpaceProxyPO extends PatternObject<SharedSpaceProxyPO, SharedSpaceProxy>
{

    public SharedSpaceProxySet allMatches()
   {
      this.setDoAllMatches(true);
      
      SharedSpaceProxySet matches = new SharedSpaceProxySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SharedSpaceProxy) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SharedSpaceProxyPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SharedSpaceProxyPO(SharedSpaceProxy... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public SharedSpaceProxyPO hasSpaceId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpaceProxy.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpaceProxyPO hasSpaceId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpaceProxy.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpaceProxyPO createSpaceId(String value)
   {
      this.startCreate().hasSpaceId(value).endCreate();
      return this;
   }
   
   public String getSpaceId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpaceProxy) getCurrentMatch()).getSpaceId();
      }
      return null;
   }
   
   public SharedSpaceProxyPO withSpaceId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpaceProxy) getCurrentMatch()).setSpaceId(value);
      }
      return this;
   }
   
   public SharedSpaceProxyPO hasPassword(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpaceProxy.PROPERTY_PASSWORD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpaceProxyPO hasPassword(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpaceProxy.PROPERTY_PASSWORD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpaceProxyPO createPassword(String value)
   {
      this.startCreate().hasPassword(value).endCreate();
      return this;
   }
   
   public String getPassword()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpaceProxy) getCurrentMatch()).getPassword();
      }
      return null;
   }
   
   public SharedSpaceProxyPO withPassword(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpaceProxy) getCurrentMatch()).setPassword(value);
      }
      return this;
   }
   
   public SharedSpaceProxyPO hasAcceptsConnectionRequests(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpaceProxy.PROPERTY_ACCEPTSCONNECTIONREQUESTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpaceProxyPO createAcceptsConnectionRequests(boolean value)
   {
      this.startCreate().hasAcceptsConnectionRequests(value).endCreate();
      return this;
   }
   
   public boolean getAcceptsConnectionRequests()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpaceProxy) getCurrentMatch()).isAcceptsConnectionRequests();
      }
      return false;
   }
   
   public SharedSpaceProxyPO withAcceptsConnectionRequests(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpaceProxy) getCurrentMatch()).setAcceptsConnectionRequests(value);
      }
      return this;
   }
   
   public SharedSpaceProxyPO hasHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpaceProxy.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpaceProxyPO hasHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpaceProxy.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpaceProxyPO createHostName(String value)
   {
      this.startCreate().hasHostName(value).endCreate();
      return this;
   }
   
   public String getHostName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpaceProxy) getCurrentMatch()).getHostName();
      }
      return null;
   }
   
   public SharedSpaceProxyPO withHostName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpaceProxy) getCurrentMatch()).setHostName(value);
      }
      return this;
   }
   
   public SharedSpaceProxyPO hasPortNo(long value)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpaceProxy.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpaceProxyPO hasPortNo(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(SharedSpaceProxy.PROPERTY_PORTNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SharedSpaceProxyPO createPortNo(long value)
   {
      this.startCreate().hasPortNo(value).endCreate();
      return this;
   }
   
   public long getPortNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpaceProxy) getCurrentMatch()).getPortNo();
      }
      return 0;
   }
   
   public SharedSpaceProxyPO withPortNo(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SharedSpaceProxy) getCurrentMatch()).setPortNo(value);
      }
      return this;
   }
   
   public SharedSpaceProxyPO hasPartners()
   {
      SharedSpaceProxyPO result = new SharedSpaceProxyPO(new SharedSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpaceProxy.PROPERTY_PARTNERS, result);
      
      return result;
   }

   public SharedSpaceProxyPO createPartners()
   {
      return this.startCreate().hasPartners().endCreate();
   }

   public SharedSpaceProxyPO hasPartners(SharedSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpaceProxy.PROPERTY_PARTNERS);
   }

   public SharedSpaceProxyPO createPartners(SharedSpaceProxyPO tgt)
   {
      return this.startCreate().hasPartners(tgt).endCreate();
   }

   public SharedSpaceProxySet getPartners()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpaceProxy) this.getCurrentMatch()).getPartners();
      }
      return null;
   }

   public ReplicationChannelPO hasChannel()
   {
      ReplicationChannelPO result = new ReplicationChannelPO(new ReplicationChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SharedSpaceProxy.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public ReplicationChannelPO createChannel()
   {
      return this.startCreate().hasChannel().endCreate();
   }

   public SharedSpaceProxyPO hasChannel(ReplicationChannelPO tgt)
   {
      return hasLinkConstraint(tgt, SharedSpaceProxy.PROPERTY_CHANNEL);
   }

   public SharedSpaceProxyPO createChannel(ReplicationChannelPO tgt)
   {
      return this.startCreate().hasChannel(tgt).endCreate();
   }

   public ReplicationChannel getChannel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SharedSpaceProxy) this.getCurrentMatch()).getChannel();
      }
      return null;
   }

}
