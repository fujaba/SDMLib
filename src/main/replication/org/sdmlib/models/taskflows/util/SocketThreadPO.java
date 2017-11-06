package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.taskflows.SocketThread;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.models.pattern.Pattern;

public class SocketThreadPO extends PatternObject<SocketThreadPO, SocketThread>
{

    public SocketThreadSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SocketThreadSet matches = new SocketThreadSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SocketThread) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SocketThreadPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SocketThreadPO(SocketThread... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public SocketThreadPO hasIp(String value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SocketThreadPO hasIp(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SocketThreadPO createIp(String value)
   {
      this.startCreate().hasIp(value).endCreate();
      return this;
   }
   
   public String getIp()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SocketThread) getCurrentMatch()).getIp();
      }
      return null;
   }
   
   public SocketThreadPO withIp(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SocketThread) getCurrentMatch()).setIp(value);
      }
      return this;
   }
   
   public SocketThreadPO hasPort(int value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SocketThreadPO hasPort(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_PORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SocketThreadPO createPort(int value)
   {
      this.startCreate().hasPort(value).endCreate();
      return this;
   }
   
   public int getPort()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SocketThread) getCurrentMatch()).getPort();
      }
      return 0;
   }
   
   public SocketThreadPO withPort(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SocketThread) getCurrentMatch()).setPort(value);
      }
      return this;
   }
   
   public SocketThreadPO hasIdMap(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SocketThreadPO createIdMap(SDMLibJsonIdMap value)
   {
      this.startCreate().hasIdMap(value).endCreate();
      return this;
   }
   
   public SDMLibJsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return (SDMLibJsonIdMap) ((SocketThread) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public SocketThreadPO withIdMap(SDMLibJsonIdMap value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SocketThread) getCurrentMatch()).setIdMap(value);
      }
      return this;
   }
   
   public SocketThreadPO hasDefaultTargetThread(Object value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_DEFAULTTARGETTHREAD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SocketThreadPO createDefaultTargetThread(Object value)
   {
      this.startCreate().hasDefaultTargetThread(value).endCreate();
      return this;
   }
   
   public Object getDefaultTargetThread()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SocketThread) getCurrentMatch()).getDefaultTargetThread();
      }
      return null;
   }
   
   public SocketThreadPO withDefaultTargetThread(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SocketThread) getCurrentMatch()).setDefaultTargetThread(value);
      }
      return this;
   }
   
   public SocketThreadPO filterIp(String value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO filterIp(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO filterPort(int value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO filterPort(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_PORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO filterIdMap(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO filterDefaultTargetThread(Object value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_DEFAULTTARGETTHREAD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   

   public SocketThreadPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public SocketThreadPO createDefaultTargetThreadCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_DEFAULTTARGETTHREAD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO createDefaultTargetThreadAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_DEFAULTTARGETTHREAD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO createIdMapCondition(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO createIdMapAssignment(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO createIpCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO createIpCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO createIpAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO createPortCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO createPortCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_PORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SocketThreadPO createPortAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}
