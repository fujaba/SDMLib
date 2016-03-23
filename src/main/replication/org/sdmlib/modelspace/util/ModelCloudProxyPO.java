package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.ModelCloudProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.modelspace.util.ModelCloudPO;
import org.sdmlib.modelspace.ModelCloud;
import org.sdmlib.modelspace.util.ModelCloudProxyPO;
import org.sdmlib.modelspace.util.ModelSpaceProxyPO;
import org.sdmlib.modelspace.ModelSpaceProxy;
import org.sdmlib.modelspace.util.ModelSpaceProxySet;

public class ModelCloudProxyPO extends PatternObject<ModelCloudProxyPO, ModelCloudProxy>
{

    public ModelCloudProxySet allMatches()
   {
      this.setDoAllMatches(true);
      
      ModelCloudProxySet matches = new ModelCloudProxySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ModelCloudProxy) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ModelCloudProxyPO(){
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ModelCloudProxyPO(ModelCloudProxy... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ModelCloudProxyPO hasHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloudProxy.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudProxyPO hasHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloudProxy.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudProxyPO createHostName(String value)
   {
      this.startCreate().hasHostName(value).endCreate();
      return this;
   }
   
   public String getHostName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCloudProxy) getCurrentMatch()).getHostName();
      }
      return null;
   }
   
   public ModelCloudProxyPO withHostName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ModelCloudProxy) getCurrentMatch()).setHostName(value);
      }
      return this;
   }
   
   public ModelCloudProxyPO hasPortNo(int value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloudProxy.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudProxyPO hasPortNo(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloudProxy.PROPERTY_PORTNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudProxyPO createPortNo(int value)
   {
      this.startCreate().hasPortNo(value).endCreate();
      return this;
   }
   
   public int getPortNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCloudProxy) getCurrentMatch()).getPortNo();
      }
      return 0;
   }
   
   public ModelCloudProxyPO withPortNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ModelCloudProxy) getCurrentMatch()).setPortNo(value);
      }
      return this;
   }
   
   public ModelCloudPO hasRoot()
   {
      ModelCloudPO result = new ModelCloudPO(new ModelCloud[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloudProxy.PROPERTY_ROOT, result);
      
      return result;
   }

   public ModelCloudPO createRoot()
   {
      return this.startCreate().hasRoot().endCreate();
   }

   public ModelCloudProxyPO hasRoot(ModelCloudPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloudProxy.PROPERTY_ROOT);
   }

   public ModelCloudProxyPO createRoot(ModelCloudPO tgt)
   {
      return this.startCreate().hasRoot(tgt).endCreate();
   }

   public ModelCloud getRoot()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCloudProxy) this.getCurrentMatch()).getRoot();
      }
      return null;
   }

   public ModelSpaceProxyPO hasProvidedSpaces()
   {
      ModelSpaceProxyPO result = new ModelSpaceProxyPO(new ModelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloudProxy.PROPERTY_PROVIDEDSPACES, result);
      
      return result;
   }

   public ModelSpaceProxyPO createProvidedSpaces()
   {
      return this.startCreate().hasProvidedSpaces().endCreate();
   }

   public ModelCloudProxyPO hasProvidedSpaces(ModelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloudProxy.PROPERTY_PROVIDEDSPACES);
   }

   public ModelCloudProxyPO createProvidedSpaces(ModelSpaceProxyPO tgt)
   {
      return this.startCreate().hasProvidedSpaces(tgt).endCreate();
   }

   public ModelSpaceProxySet getProvidedSpaces()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCloudProxy) this.getCurrentMatch()).getProvidedSpaces();
      }
      return null;
   }

   public ModelCloudProxyPO filterHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloudProxy.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudProxyPO filterHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloudProxy.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudProxyPO filterPortNo(int value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloudProxy.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudProxyPO filterPortNo(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloudProxy.PROPERTY_PORTNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO filterRoot()
   {
      ModelCloudPO result = new ModelCloudPO(new ModelCloud[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloudProxy.PROPERTY_ROOT, result);
      
      return result;
   }

   public ModelCloudProxyPO filterRoot(ModelCloudPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloudProxy.PROPERTY_ROOT);
   }

   public ModelSpaceProxyPO filterProvidedSpaces()
   {
      ModelSpaceProxyPO result = new ModelSpaceProxyPO(new ModelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloudProxy.PROPERTY_PROVIDEDSPACES, result);
      
      return result;
   }

   public ModelCloudProxyPO filterProvidedSpaces(ModelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloudProxy.PROPERTY_PROVIDEDSPACES);
   }

}
