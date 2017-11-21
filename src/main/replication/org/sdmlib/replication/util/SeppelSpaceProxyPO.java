package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.replication.util.SeppelChannelPO;
import org.sdmlib.replication.util.SeppelSpaceProxyPO;
import org.sdmlib.replication.util.SeppelScopePO;
import org.sdmlib.replication.util.BoardTaskPO;

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
   
   public SeppelSpaceProxyPO hasLoginName(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_LOGINNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO hasLoginName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_LOGINNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createLoginName(String value)
   {
      this.startCreate().hasLoginName(value).endCreate();
      return this;
   }
   
   public String getLoginName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) getCurrentMatch()).getLoginName();
      }
      return null;
   }
   
   public SeppelSpaceProxyPO withLoginName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpaceProxy) getCurrentMatch()).setLoginName(value);
      }
      return this;
   }
   
   public SeppelSpaceProxyPO hasPassword(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PASSWORD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO hasPassword(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PASSWORD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createPassword(String value)
   {
      this.startCreate().hasPassword(value).endCreate();
      return this;
   }
   
   public String getPassword()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) getCurrentMatch()).getPassword();
      }
      return null;
   }
   
   public SeppelSpaceProxyPO withPassword(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpaceProxy) getCurrentMatch()).setPassword(value);
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

   public BoardTaskPO hasTasks()
   {
      BoardTaskPO result = new BoardTaskPO(new BoardTask[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_TASKS, result);
      
      return result;
   }

   public BoardTaskPO createTasks()
   {
      return this.startCreate().hasTasks().endCreate();
   }

   public SeppelSpaceProxyPO hasTasks(BoardTaskPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_TASKS);
   }

   public SeppelSpaceProxyPO createTasks(BoardTaskPO tgt)
   {
      return this.startCreate().hasTasks(tgt).endCreate();
   }

   public BoardTaskSet getTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpaceProxy) this.getCurrentMatch()).getTasks();
      }
      return null;
   }

   public SeppelSpaceProxyPO filterSpaceId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterSpaceId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterAcceptsConnectionRequests(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_ACCEPTSCONNECTIONREQUESTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterPortNo(int value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterPortNo(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PORTNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterLoginName(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_LOGINNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterLoginName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_LOGINNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterPassword(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PASSWORD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterPassword(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PASSWORD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterPartners()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_PARTNERS, result);
      
      return result;
   }

   public SeppelSpaceProxyPO filterPartners(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_PARTNERS);
   }

   public SeppelScopePO filterScopes()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_SCOPES, result);
      
      return result;
   }

   public SeppelSpaceProxyPO filterScopes(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_SCOPES);
   }

   public SeppelChannelPO filterChannel()
   {
      SeppelChannelPO result = new SeppelChannelPO(new SeppelChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public SeppelSpaceProxyPO filterChannel(SeppelChannelPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_CHANNEL);
   }

   public BoardTaskPO filterTasks()
   {
      BoardTaskPO result = new BoardTaskPO(new BoardTask[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_TASKS, result);
      
      return result;
   }

   public SeppelSpaceProxyPO filterTasks(BoardTaskPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_TASKS);
   }


   public SeppelSpaceProxyPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public SeppelSpaceProxyPO createAcceptsConnectionRequestsCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_ACCEPTSCONNECTIONREQUESTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createAcceptsConnectionRequestsAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_ACCEPTSCONNECTIONREQUESTS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createHostNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createHostNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createHostNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createLoginNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_LOGINNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createLoginNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_LOGINNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createLoginNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_LOGINNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createPasswordCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PASSWORD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createPasswordCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PASSWORD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createPasswordAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PASSWORD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createPortNoCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createPortNoCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PORTNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createPortNoAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createSpaceIdCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createSpaceIdCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createSpaceIdAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpaceProxy.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelChannelPO createChannelPO()
   {
      SeppelChannelPO result = new SeppelChannelPO(new SeppelChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public SeppelChannelPO createChannelPO(String modifier)
   {
      SeppelChannelPO result = new SeppelChannelPO(new SeppelChannel[]{});
      
      result.setModifier(modifier);
      super.hasLink(SeppelSpaceProxy.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createChannelLink(SeppelChannelPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_CHANNEL);
   }

   public SeppelSpaceProxyPO createChannelLink(SeppelChannelPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_CHANNEL, modifier);
   }

   public SeppelSpaceProxyPO createPartnersPO()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_PARTNERS, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createPartnersPO(String modifier)
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(modifier);
      super.hasLink(SeppelSpaceProxy.PROPERTY_PARTNERS, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createPartnersLink(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_PARTNERS);
   }

   public SeppelSpaceProxyPO createPartnersLink(SeppelSpaceProxyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_PARTNERS, modifier);
   }

   public SeppelScopePO createScopesPO()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_SCOPES, result);
      
      return result;
   }

   public SeppelScopePO createScopesPO(String modifier)
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(modifier);
      super.hasLink(SeppelSpaceProxy.PROPERTY_SCOPES, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createScopesLink(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_SCOPES);
   }

   public SeppelSpaceProxyPO createScopesLink(SeppelScopePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_SCOPES, modifier);
   }

   public BoardTaskPO createTasksPO()
   {
      BoardTaskPO result = new BoardTaskPO(new BoardTask[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelSpaceProxy.PROPERTY_TASKS, result);
      
      return result;
   }

   public BoardTaskPO createTasksPO(String modifier)
   {
      BoardTaskPO result = new BoardTaskPO(new BoardTask[]{});
      
      result.setModifier(modifier);
      super.hasLink(SeppelSpaceProxy.PROPERTY_TASKS, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createTasksLink(BoardTaskPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_TASKS);
   }

   public SeppelSpaceProxyPO createTasksLink(BoardTaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SeppelSpaceProxy.PROPERTY_TASKS, modifier);
   }

}
