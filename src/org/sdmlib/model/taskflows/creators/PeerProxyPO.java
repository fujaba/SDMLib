package org.sdmlib.model.taskflows.creators;

import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

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

   public PeerProxyPO hasIp(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(PeerProxy.PROPERTY_IP).withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

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

   public PeerProxyPO hasPort(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(PeerProxy.PROPERTY_PORT).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

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

   public PeerProxyPO hasIdMap(IdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(PeerProxy.PROPERTY_IDMAP).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public IdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PeerProxy) getCurrentMatch()).getIdMap();
      }
      return null;
   }

   public PeerProxyPO hasIdMap(JsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(PeerProxy.PROPERTY_IDMAP).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public PeerProxyPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(PeerProxy.PROPERTY_IDMAP).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

}
