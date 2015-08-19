package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.ModelSpaceProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.modelspace.util.ModelCloudPO;
import org.sdmlib.modelspace.ModelCloud;
import org.sdmlib.modelspace.util.ModelSpaceProxyPO;
import org.sdmlib.modelspace.util.ModelCloudProxyPO;
import org.sdmlib.modelspace.ModelCloudProxy;
import org.sdmlib.modelspace.util.ModelCloudProxySet;

public class ModelSpaceProxyPO extends PatternObject<ModelSpaceProxyPO, ModelSpaceProxy>
{

    public ModelSpaceProxySet allMatches()
   {
      this.setDoAllMatches(true);
      
      ModelSpaceProxySet matches = new ModelSpaceProxySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ModelSpaceProxy) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ModelSpaceProxyPO(){
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ModelSpaceProxyPO(ModelSpaceProxy... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ModelSpaceProxyPO hasLocation(String value)
   {
      new AttributeConstraint()
      .withAttrName(ModelSpaceProxy.PROPERTY_LOCATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ModelSpaceProxyPO hasLocation(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelSpaceProxy.PROPERTY_LOCATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ModelSpaceProxyPO createLocation(String value)
   {
      this.startCreate().hasLocation(value).endCreate();
      return this;
   }
   
   public String getLocation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelSpaceProxy) getCurrentMatch()).getLocation();
      }
      return null;
   }
   
   public ModelSpaceProxyPO withLocation(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ModelSpaceProxy) getCurrentMatch()).setLocation(value);
      }
      return this;
   }
   
   public ModelCloudPO hasCloud()
   {
      ModelCloudPO result = new ModelCloudPO(new ModelCloud[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelSpaceProxy.PROPERTY_CLOUD, result);
      
      return result;
   }

   public ModelCloudPO createCloud()
   {
      return this.startCreate().hasCloud().endCreate();
   }

   public ModelSpaceProxyPO hasCloud(ModelCloudPO tgt)
   {
      return hasLinkConstraint(tgt, ModelSpaceProxy.PROPERTY_CLOUD);
   }

   public ModelSpaceProxyPO createCloud(ModelCloudPO tgt)
   {
      return this.startCreate().hasCloud(tgt).endCreate();
   }

   public ModelCloud getCloud()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelSpaceProxy) this.getCurrentMatch()).getCloud();
      }
      return null;
   }

   public ModelCloudProxyPO hasProvidingClouds()
   {
      ModelCloudProxyPO result = new ModelCloudProxyPO(new ModelCloudProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelSpaceProxy.PROPERTY_PROVIDINGCLOUDS, result);
      
      return result;
   }

   public ModelCloudProxyPO createProvidingClouds()
   {
      return this.startCreate().hasProvidingClouds().endCreate();
   }

   public ModelSpaceProxyPO hasProvidingClouds(ModelCloudProxyPO tgt)
   {
      return hasLinkConstraint(tgt, ModelSpaceProxy.PROPERTY_PROVIDINGCLOUDS);
   }

   public ModelSpaceProxyPO createProvidingClouds(ModelCloudProxyPO tgt)
   {
      return this.startCreate().hasProvidingClouds(tgt).endCreate();
   }

   public ModelCloudProxySet getProvidingClouds()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelSpaceProxy) this.getCurrentMatch()).getProvidingClouds();
      }
      return null;
   }

}
