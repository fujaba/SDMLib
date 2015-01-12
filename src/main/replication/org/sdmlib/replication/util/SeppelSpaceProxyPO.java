package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.replication.util.SeppelSpaceProxyPO;
import org.sdmlib.replication.util.SeppelSpaceProxySet;
import org.sdmlib.replication.util.SeppelUserPO;
import org.sdmlib.replication.SeppelUser;
import org.sdmlib.replication.util.SeppelUserSet;
import org.sdmlib.replication.util.SeppelScopePO;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.util.SeppelScopeSet;
import org.sdmlib.replication.util.SeppelChannelPO;
import org.sdmlib.replication.SeppelChannel;

public class SeppelSpaceProxyPO extends PatternObject<SeppelSpaceProxyPO, SeppelSpaceProxy>
{

    public SeppelSpaceProxySet allMatches()
   {
      this.setDoAllMatches(true);
      
      SeppelSpaceProxySet matches = new SeppelSpaceProxySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SeppelSpaceProxy) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SeppelSpaceProxyPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SeppelSpaceProxyPO(SeppelSpaceProxy... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public SeppelSpaceProxyPO hasSpaceId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO hasSpaceId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createSpaceId(String value)
   {
      this.startCreate().hasSpaceId(value).endCreate();
      return this;
   }
   
   public String getSpaceId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) getCurrentMatch()).getSpaceId();
      }
      return null;
   }
   
   public SeppelSpaceProxyPO withSpaceId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpaceProxy) getCurrentMatch()).setSpaceId(value);
      }
      return this;
   }
   
   public SeppelSpaceProxyPO hasAcceptsConnectionRequests(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_ACCEPTSCONNECTIONREQUESTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createAcceptsConnectionRequests(boolean value)
   {
      this.startCreate().hasAcceptsConnectionRequests(value).endCreate();
      return this;
   }
   
   public boolean getAcceptsConnectionRequests()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) getCurrentMatch()).isAcceptsConnectionRequests();
      }
      return false;
   }
   
   public SeppelSpaceProxyPO withAcceptsConnectionRequests(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpaceProxy) getCurrentMatch()).setAcceptsConnectionRequests(value);
      }
      return this;
   }
   
   public SeppelSpaceProxyPO hasHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO hasHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createHostName(String value)
   {
      this.startCreate().hasHostName(value).endCreate();
      return this;
   }
   
   public String getHostName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) getCurrentMatch()).getHostName();
      }
      return null;
   }
   
   public SeppelSpaceProxyPO withHostName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpaceProxy) getCurrentMatch()).setHostName(value);
      }
      return this;
   }
   
   public SeppelSpaceProxyPO hasPortNo(int value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO hasPortNo(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PORTNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createPortNo(int value)
   {
      this.startCreate().hasPortNo(value).endCreate();
      return this;
   }
   
   public int getPortNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) getCurrentMatch()).getPortNo();
      }
      return 0;
   }
   
   public SeppelSpaceProxyPO withPortNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpaceProxy) getCurrentMatch()).setPortNo(value);
      }
      return this;
   }
   
   public SeppelSpaceProxyPO hasPartners()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_PARTNERS, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createPartners()
   {
      return this.startCreate().hasPartners().endCreate();
   }

   public SeppelSpaceProxyPO hasPartners(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_PARTNERS);
   }

   public SeppelSpaceProxyPO createPartners(SeppelSpaceProxyPO tgt)
   {
      return this.startCreate().hasPartners(tgt).endCreate();
   }

   public SeppelSpaceProxySet getPartners()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) this.getCurrentMatch()).getPartners();
      }
      return null;
   }

   public SeppelUserPO hasKnownUsers()
   {
      SeppelUserPO result = new SeppelUserPO(new SeppelUser[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_KNOWNUSERS, result);
      
      return result;
   }

   public SeppelUserPO createKnownUsers()
   {
      return this.startCreate().hasKnownUsers().endCreate();
   }

   public SeppelSpaceProxyPO hasKnownUsers(SeppelUserPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_KNOWNUSERS);
   }

   public SeppelSpaceProxyPO createKnownUsers(SeppelUserPO tgt)
   {
      return this.startCreate().hasKnownUsers(tgt).endCreate();
   }

   public SeppelUserSet getKnownUsers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) this.getCurrentMatch()).getKnownUsers();
      }
      return null;
   }

   public SeppelUserPO hasUser()
   {
      SeppelUserPO result = new SeppelUserPO(new SeppelUser[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_USER, result);
      
      return result;
   }

   public SeppelUserPO createUser()
   {
      return this.startCreate().hasUser().endCreate();
   }

   public SeppelSpaceProxyPO hasUser(SeppelUserPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_USER);
   }

   public SeppelSpaceProxyPO createUser(SeppelUserPO tgt)
   {
      return this.startCreate().hasUser(tgt).endCreate();
   }

   public SeppelUser getUser()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) this.getCurrentMatch()).getUser();
      }
      return null;
   }

   public SeppelScopePO hasScopes()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_SCOPES, result);
      
      return result;
   }

   public SeppelScopePO createScopes()
   {
      return this.startCreate().hasScopes().endCreate();
   }

   public SeppelSpaceProxyPO hasScopes(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_SCOPES);
   }

   public SeppelSpaceProxyPO createScopes(SeppelScopePO tgt)
   {
      return this.startCreate().hasScopes(tgt).endCreate();
   }

   public SeppelScopeSet getScopes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) this.getCurrentMatch()).getScopes();
      }
      return null;
   }

   public SeppelChannelPO hasChannel()
   {
      SeppelChannelPO result = new SeppelChannelPO(new SeppelChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public SeppelChannelPO createChannel()
   {
      return this.startCreate().hasChannel().endCreate();
   }

   public SeppelSpaceProxyPO hasChannel(SeppelChannelPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_CHANNEL);
   }

   public SeppelSpaceProxyPO createChannel(SeppelChannelPO tgt)
   {
      return this.startCreate().hasChannel(tgt).endCreate();
   }

   public SeppelChannel getChannel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) this.getCurrentMatch()).getChannel();
      }
      return null;
   }

}
