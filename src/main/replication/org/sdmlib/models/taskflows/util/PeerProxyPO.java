package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.taskflows.PeerProxy;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class PeerProxyPO extends PatternObject<PeerProxyPO, PeerProxy>
{

    public PeerProxySet allMatches()
   {
      this.setDoAllMatches(true);
      
      PeerProxySet matches = new PeerProxySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PeerProxy) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PeerProxyPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PeerProxyPO(PeerProxy... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PeerProxyPO hasIp(String value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxyPO hasIp(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxyPO createIp(String value)
   {
      this.startCreate().hasIp(value).endCreate();
      return this;
   }
   
   public String getIp()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PeerProxy) getCurrentMatch()).getIp();
      }
      return null;
   }
   
   public PeerProxyPO withIp(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PeerProxy) getCurrentMatch()).setIp(value);
      }
      return this;
   }
   
   public PeerProxyPO hasPort(int value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxyPO hasPort(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_PORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxyPO createPort(int value)
   {
      this.startCreate().hasPort(value).endCreate();
      return this;
   }
   
   public int getPort()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PeerProxy) getCurrentMatch()).getPort();
      }
      return 0;
   }
   
   public PeerProxyPO withPort(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PeerProxy) getCurrentMatch()).setPort(value);
      }
      return this;
   }
   
   public PeerProxyPO hasIdMap(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PeerProxyPO createIdMap(SDMLibJsonIdMap value)
   {
      this.startCreate().hasIdMap(value).endCreate();
      return this;
   }
   
   public SDMLibJsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PeerProxy) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public PeerProxyPO withIdMap(SDMLibJsonIdMap value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PeerProxy) getCurrentMatch()).setIdMap(value);
      }
      return this;
   }
   
   public PeerProxyPO filterIp(String value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO filterIp(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO filterPort(int value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO filterPort(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_PORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO filterIdMap(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   

   public PeerProxyPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PeerProxyPO createIdMapCondition(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO createIdMapAssignment(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO createIpCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO createIpCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO createIpAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_IP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO createPortCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO createPortCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_PORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PeerProxyPO createPortAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(PeerProxy.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}
