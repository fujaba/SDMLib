package org.sdmlib.replication.util;

import java.net.Socket;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.SeppelSpaceProxy;

public class SeppelChannelPO extends PatternObject<SeppelChannelPO, SeppelChannel>
{

    public SeppelChannelSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SeppelChannelSet matches = new SeppelChannelSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SeppelChannel) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SeppelChannelPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SeppelChannelPO(SeppelChannel... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public SeppelChannelPO hasSocket(Socket value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelChannel.PROPERTY_SOCKET)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelChannelPO createSocket(Socket value)
   {
      this.startCreate().hasSocket(value).endCreate();
      return this;
   }
   
   public Socket getSocket()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelChannel) getCurrentMatch()).getSocket();
      }
      return null;
   }
   
   public SeppelChannelPO withSocket(Socket value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelChannel) getCurrentMatch()).setSocket(value);
      }
      return this;
   }
   
   public SeppelChannelPO hasLoginValidated(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelChannel.PROPERTY_LOGINVALIDATED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelChannelPO createLoginValidated(boolean value)
   {
      this.startCreate().hasLoginValidated(value).endCreate();
      return this;
   }
   
   public boolean getLoginValidated()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelChannel) getCurrentMatch()).isLoginValidated();
      }
      return false;
   }
   
   public SeppelChannelPO withLoginValidated(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelChannel) getCurrentMatch()).setLoginValidated(value);
      }
      return this;
   }
   
   public SeppelSpaceProxyPO hasSeppelSpaceProxy()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelChannel.PROPERTY_SEPPELSPACEPROXY, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createSeppelSpaceProxy()
   {
      return this.startCreate().hasSeppelSpaceProxy().endCreate();
   }

   public SeppelChannelPO hasSeppelSpaceProxy(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelChannel.PROPERTY_SEPPELSPACEPROXY);
   }

   public SeppelChannelPO createSeppelSpaceProxy(SeppelSpaceProxyPO tgt)
   {
      return this.startCreate().hasSeppelSpaceProxy(tgt).endCreate();
   }

   public SeppelSpaceProxy getSeppelSpaceProxy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelChannel) this.getCurrentMatch()).getSeppelSpaceProxy();
      }
      return null;
   }

   public SeppelChannelPO filterSocket(Socket value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelChannel.PROPERTY_SOCKET)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelChannelPO filterLoginValidated(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelChannel.PROPERTY_LOGINVALIDATED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO filterSeppelSpaceProxy()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelChannel.PROPERTY_SEPPELSPACEPROXY, result);
      
      return result;
   }

   public SeppelChannelPO filterSeppelSpaceProxy(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelChannel.PROPERTY_SEPPELSPACEPROXY);
   }


   public SeppelChannelPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public SeppelChannelPO createLoginValidatedCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelChannel.PROPERTY_LOGINVALIDATED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelChannelPO createLoginValidatedAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelChannel.PROPERTY_LOGINVALIDATED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelChannelPO createSocketCondition(Socket value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelChannel.PROPERTY_SOCKET)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelChannelPO createSocketAssignment(Socket value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelChannel.PROPERTY_SOCKET)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpaceProxyPO createSeppelSpaceProxyPO()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelChannel.PROPERTY_SEPPELSPACEPROXY, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createSeppelSpaceProxyPO(String modifier)
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(modifier);
      super.hasLink(SeppelChannel.PROPERTY_SEPPELSPACEPROXY, result);
      
      return result;
   }

   public SeppelChannelPO createSeppelSpaceProxyLink(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelChannel.PROPERTY_SEPPELSPACEPROXY);
   }

   public SeppelChannelPO createSeppelSpaceProxyLink(SeppelSpaceProxyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SeppelChannel.PROPERTY_SEPPELSPACEPROXY, modifier);
   }

}
