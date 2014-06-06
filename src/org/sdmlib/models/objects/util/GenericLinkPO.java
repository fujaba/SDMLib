package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.objects.util.GenericLinkSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.objects.util.GenericObjectPO;
import org.sdmlib.models.objects.util.GenericGraphPO;

public class GenericLinkPO extends PatternObject<GenericLinkPO, GenericLink>
{
   public GenericLinkPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public GenericLinkPO(GenericLink... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public GenericLinkPO hasTgtLabel(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericLink.PROPERTY_TGTLABEL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTgtLabel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) getCurrentMatch()).getTgtLabel();
      }
      return null;
   }
   
   public GenericLinkPO hasSrcLabel(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericLink.PROPERTY_SRCLABEL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getSrcLabel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) getCurrentMatch()).getSrcLabel();
      }
      return null;
   }
   
   public GenericObjectPO hasSrc()
   {
      GenericObjectPO result = new GenericObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericLink.PROPERTY_SRC, result);
      
      return result;   }
   
   public GenericLinkPO hasSrc(GenericObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericLink.PROPERTY_SRC)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericObject getSrc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) this.getCurrentMatch()).getSrc();
      }
      return null;
   }
   
   public GenericObjectPO hasTgt()
   {
      GenericObjectPO result = new GenericObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericLink.PROPERTY_TGT, result);
      
      return result;   }
   
   public GenericLinkPO hasTgt(GenericObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericLink.PROPERTY_TGT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericObject getTgt()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) this.getCurrentMatch()).getTgt();
      }
      return null;
   }
   
   public GenericGraphPO hasGraph()
   {
      GenericGraphPO result = new GenericGraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericLink.PROPERTY_GRAPH, result);
      
      return result;
   }
   
   public GenericLinkPO hasGraph(GenericGraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericLink.PROPERTY_GRAPH)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericGraph getGraph()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericLink) this.getCurrentMatch()).getGraph();
      }
      return null;
   }

   public GenericLinkSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GenericLinkSet result = new GenericLinkSet();
      
      while (this.getPattern().getHasMatch())
      {
         result.add(this.getCurrentMatch());
         
         this.getPattern().findNextMatch();
      }
      
      return result;
   }
   
   public GenericLinkPO hasTgtLabel(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericLink.PROPERTY_TGTLABEL)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericLinkPO hasSrcLabel(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericLink.PROPERTY_SRCLABEL)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericLinkPO createTgtLabel(String value)
   {
      this.startCreate().hasTgtLabel(value).endCreate();
      return this;
   }
   
   public GenericLinkPO createSrcLabel(String value)
   {
      this.startCreate().hasSrcLabel(value).endCreate();
      return this;
   }
   
   public GenericObjectPO createSrc()
   {
      return this.startCreate().hasSrc().endCreate();
   }

   public GenericLinkPO createSrc(GenericObjectPO tgt)
   {
      return this.startCreate().hasSrc(tgt).endCreate();
   }

   public GenericObjectPO createTgt()
   {
      return this.startCreate().hasTgt().endCreate();
   }

   public GenericLinkPO createTgt(GenericObjectPO tgt)
   {
      return this.startCreate().hasTgt(tgt).endCreate();
   }

   public GenericGraphPO createGraph()
   {
      return this.startCreate().hasGraph().endCreate();
   }

   public GenericLinkPO createGraph(GenericGraphPO tgt)
   {
      return this.startCreate().hasGraph(tgt).endCreate();
   }

}

