package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.model.taskflows.creators.SocketThreadSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

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
   
   public SocketThreadPO hasIp(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_PORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SDMLibJsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SocketThread) getCurrentMatch()).getIdMap();
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SocketThread.PROPERTY_DEFAULTTARGETTHREAD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
}

