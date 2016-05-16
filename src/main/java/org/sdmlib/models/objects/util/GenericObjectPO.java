package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.objects.util.GenericGraphPO;
import org.sdmlib.models.objects.util.GenericObjectPO;
import org.sdmlib.models.objects.util.GenericAttributePO;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.util.GenericLinkPO;
import org.sdmlib.models.objects.GenericLink;

public class GenericObjectPO extends PatternObject<GenericObjectPO, GenericObject>
{
   public GenericObjectPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public GenericObjectPO(GenericObject... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public GenericObjectPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public GenericObjectPO hasType(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public GenericAttributePO hasAttrs()
   {
      GenericAttributePO result = new GenericAttributePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericObject.PROPERTY_ATTRS, result);
      
      return result;   }
   
   public GenericObjectPO hasAttrs(GenericAttributePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericObject.PROPERTY_ATTRS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericAttributeSet getAttrs()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) this.getCurrentMatch()).getAttrs();
      }
      return null;
   }
   
   public GenericLinkPO hasOutgoingLinks()
   {
      GenericLinkPO result = new GenericLinkPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericObject.PROPERTY_OUTGOINGLINKS, result);
      
      return result;   }
   
   public GenericObjectPO hasOutgoingLinks(GenericLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericObject.PROPERTY_OUTGOINGLINKS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericLinkSet getOutgoingLinks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) this.getCurrentMatch()).getOutgoingLinks();
      }
      return null;
   }
   
   public GenericLinkPO hasIncomingLinks()
   {
      GenericLinkPO result = new GenericLinkPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericObject.PROPERTY_INCOMINGLINKS, result);
      
      return result;   }
   
   public GenericObjectPO hasIncomingLinks(GenericLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericObject.PROPERTY_INCOMINGLINKS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericLinkSet getIncomingLinks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) this.getCurrentMatch()).getIncomingLinks();
      }
      return null;
   }
   
   public GenericGraphPO hasGraph()
   {
      GenericGraphPO result = new GenericGraphPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericObject.PROPERTY_GRAPH, result);
      
      return result;
   }
   
   public GenericObjectPO hasGraph(GenericGraphPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericObject.PROPERTY_GRAPH)
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
         return ((GenericObject) this.getCurrentMatch()).getGraph();
      }
      return null;
   }
   
   public GenericObjectSet allMatches()
   {
      this.setDoAllMatches(true); 
      
      GenericObjectSet result = new GenericObjectSet();
      
      while (this.getPattern().getHasMatch())
      {
         result.add(this.getCurrentMatch());
         
         this.getPattern().findNextMatch();
      }
      
      return result;
   }
   public GenericObjectPO hasIcon(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_ICON)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getIcon()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) getCurrentMatch()).getIcon();
      }
      return null;
   }
   
   public GenericObjectPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericObjectPO hasType(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_TYPE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericObjectPO hasIcon(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_ICON)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericObjectPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public GenericObjectPO createType(String value)
   {
      this.startCreate().hasType(value).endCreate();
      return this;
   }
   
   public GenericObjectPO createIcon(String value)
   {
      this.startCreate().hasIcon(value).endCreate();
      return this;
   }
   
   public GenericGraphPO createGraph()
   {
      return this.startCreate().hasGraph().endCreate();
   }

   public GenericObjectPO createGraph(GenericGraphPO tgt)
   {
      return this.startCreate().hasGraph(tgt).endCreate();
   }

   public GenericAttributePO createAttrs()
   {
      return (GenericAttributePO) this.startCreate().hasAttrs().endCreate();
   }

   public GenericObjectPO createAttrs(GenericAttributePO tgt)
   {
      return this.startCreate().hasAttrs(tgt).endCreate();
   }

   public GenericLinkPO createOutgoingLinks()
   {
      return this.startCreate().hasOutgoingLinks().endCreate();
   }

   public GenericObjectPO createOutgoingLinks(GenericLinkPO tgt)
   {
      return this.startCreate().hasOutgoingLinks(tgt).endCreate();
   }

   public GenericLinkPO createIncomingLinks()
   {
      return this.startCreate().hasIncomingLinks().endCreate();
   }

   public GenericObjectPO createIncomingLinks(GenericLinkPO tgt)
   {
      return this.startCreate().hasIncomingLinks(tgt).endCreate();
   }

   public GenericObjectPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericObjectPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericObjectPO filterType(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericObjectPO filterType(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_TYPE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericObjectPO filterIcon(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_ICON)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericObjectPO filterIcon(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_ICON)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GenericGraphPO filterGraph()
   {
      GenericGraphPO result = new GenericGraphPO(new GenericGraph[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GenericObject.PROPERTY_GRAPH, result);
      
      return result;
   }

   public GenericObjectPO filterGraph(GenericGraphPO tgt)
   {
      return hasLinkConstraint(tgt, GenericObject.PROPERTY_GRAPH);
   }

   public GenericAttributePO filterAttrs()
   {
      GenericAttributePO result = new GenericAttributePO(new GenericAttribute[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GenericObject.PROPERTY_ATTRS, result);
      
      return result;
   }

   public GenericObjectPO filterAttrs(GenericAttributePO tgt)
   {
      return hasLinkConstraint(tgt, GenericObject.PROPERTY_ATTRS);
   }

   public GenericLinkPO filterOutgoingLinks()
   {
      GenericLinkPO result = new GenericLinkPO(new GenericLink[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GenericObject.PROPERTY_OUTGOINGLINKS, result);
      
      return result;
   }

   public GenericObjectPO filterOutgoingLinks(GenericLinkPO tgt)
   {
      return hasLinkConstraint(tgt, GenericObject.PROPERTY_OUTGOINGLINKS);
   }

   public GenericLinkPO filterIncomingLinks()
   {
      GenericLinkPO result = new GenericLinkPO(new GenericLink[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GenericObject.PROPERTY_INCOMINGLINKS, result);
      
      return result;
   }

   public GenericObjectPO filterIncomingLinks(GenericLinkPO tgt)
   {
      return hasLinkConstraint(tgt, GenericObject.PROPERTY_INCOMINGLINKS);
   }

}






