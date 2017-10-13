package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.ModelCloud;
import org.sdmlib.modelspace.ModelCloudProxy;
import org.sdmlib.modelspace.ModelSpaceProxy;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.modelspace.util.ModelSpaceProxyPO;
import org.sdmlib.modelspace.util.ModelCloudPO;
import org.sdmlib.modelspace.util.ModelCloudProxyPO;

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
      
      super.filterAttr();
      
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
      
      super.filterAttr();
      
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

   public ModelSpaceProxyPO hasModelSpaces()
   {
      ModelSpaceProxyPO result = new ModelSpaceProxyPO(new ModelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloud.PROPERTY_MODELSPACES, result);
      
      return result;
   }

   public ModelSpaceProxyPO createModelSpaces()
   {
      return this.startCreate().hasModelSpaces().endCreate();
   }

   public ModelCloudPO hasModelSpaces(ModelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloud.PROPERTY_MODELSPACES);
   }

   public ModelCloudPO createModelSpaces(ModelSpaceProxyPO tgt)
   {
      return this.startCreate().hasModelSpaces(tgt).endCreate();
   }

   public ModelSpaceProxySet getModelSpaces()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCloud) this.getCurrentMatch()).getModelSpaces();
      }
      return null;
   }

   public ModelCloudPO hasHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO hasHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO createHostName(String value)
   {
      this.startCreate().hasHostName(value).endCreate();
      return this;
   }
   
   public String getHostName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ModelCloud) getCurrentMatch()).getHostName();
      }
      return null;
   }
   
   public ModelCloudPO withHostName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ModelCloud) getCurrentMatch()).setHostName(value);
      }
      return this;
   }
   
   public ModelCloudPO filterHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO filterHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO filterAcceptPort(int value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_ACCEPTPORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO filterAcceptPort(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_ACCEPTPORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudProxyPO filterServers()
   {
      ModelCloudProxyPO result = new ModelCloudProxyPO(new ModelCloudProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloud.PROPERTY_SERVERS, result);
      
      return result;
   }

   public ModelCloudPO filterServers(ModelCloudProxyPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloud.PROPERTY_SERVERS);
   }

   public ModelSpaceProxyPO filterModelSpaces()
   {
      ModelSpaceProxyPO result = new ModelSpaceProxyPO(new ModelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloud.PROPERTY_MODELSPACES, result);
      
      return result;
   }

   public ModelCloudPO filterModelSpaces(ModelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloud.PROPERTY_MODELSPACES);
   }


   public ModelCloudPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ModelCloudPO createAcceptPortCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_ACCEPTPORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO createAcceptPortCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_ACCEPTPORT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO createAcceptPortAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_ACCEPTPORT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO createHostNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO createHostNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelCloudPO createHostNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ModelCloud.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ModelSpaceProxyPO createModelSpacesPO()
   {
      ModelSpaceProxyPO result = new ModelSpaceProxyPO(new ModelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloud.PROPERTY_MODELSPACES, result);
      
      return result;
   }

   public ModelSpaceProxyPO createModelSpacesPO(String modifier)
   {
      ModelSpaceProxyPO result = new ModelSpaceProxyPO(new ModelSpaceProxy[]{});
      
      result.setModifier(modifier);
      super.hasLink(ModelCloud.PROPERTY_MODELSPACES, result);
      
      return result;
   }

   public ModelCloudPO createModelSpacesLink(ModelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloud.PROPERTY_MODELSPACES);
   }

   public ModelCloudPO createModelSpacesLink(ModelSpaceProxyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ModelCloud.PROPERTY_MODELSPACES, modifier);
   }

   public ModelCloudProxyPO createServersPO()
   {
      ModelCloudProxyPO result = new ModelCloudProxyPO(new ModelCloudProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ModelCloud.PROPERTY_SERVERS, result);
      
      return result;
   }

   public ModelCloudProxyPO createServersPO(String modifier)
   {
      ModelCloudProxyPO result = new ModelCloudProxyPO(new ModelCloudProxy[]{});
      
      result.setModifier(modifier);
      super.hasLink(ModelCloud.PROPERTY_SERVERS, result);
      
      return result;
   }

   public ModelCloudPO createServersLink(ModelCloudProxyPO tgt)
   {
      return hasLinkConstraint(tgt, ModelCloud.PROPERTY_SERVERS);
   }

   public ModelCloudPO createServersLink(ModelCloudProxyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ModelCloud.PROPERTY_SERVERS, modifier);
   }

}
