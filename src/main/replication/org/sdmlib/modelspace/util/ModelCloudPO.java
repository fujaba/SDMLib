package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.ModelCloud;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.modelspace.util.ModelCloudProxyPO;
import org.sdmlib.modelspace.ModelCloudProxy;
import org.sdmlib.modelspace.util.ModelCloudPO;
import org.sdmlib.modelspace.util.ModelCloudProxySet;

public class ModelCloudPO extends PatternObject<ModelCloudPO, ModelCloud>
{

    public ModelCloudSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ModelCloudSet matches = new ModelCloudSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ModelCloud) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ModelCloudPO(){
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ModelCloudPO(ModelCloud... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ModelCloudPO hasAcceptPort(int value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_ACCEPTPORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ModelCloudPO hasAcceptPort(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_ACCEPTPORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ModelCloudPO createAcceptPort(int value)
   {
      this.startCreate().hasAcceptPort(value).endCreate();
      return this;
   }
   
   public int getAcceptPort()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCloud) getCurrentMatch()).getAcceptPort();
      }
      return 0;
   }
   
   public ModelCloudPO withAcceptPort(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ModelCloud) getCurrentMatch()).setAcceptPort(value);
      }
      return this;
   }
   
   public ModelCloudProxyPO hasServers()
   {
      ModelCloudProxyPO result = new ModelCloudProxyPO(new ModelCloudProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloud.PROPERTY_SERVERS, result);
      
      return result;
   }

   public ModelCloudProxyPO createServers()
   {
      return this.startCreate().hasServers().endCreate();
   }

   public ModelCloudPO hasServers(ModelCloudProxyPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloud.PROPERTY_SERVERS);
   }

   public ModelCloudPO createServers(ModelCloudProxyPO tgt)
   {
      return this.startCreate().hasServers(tgt).endCreate();
   }

   public ModelCloudProxySet getServers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCloud) this.getCurrentMatch()).getServers();
      }
      return null;
   }

}
