package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.model.taskflows.creators.SocketThreadSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.serialization.json.JsonIdMap;

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
   
   public SocketThreadPO hasIdMap(JsonIdMap value)
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
   
   public JsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SocketThread) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
}

