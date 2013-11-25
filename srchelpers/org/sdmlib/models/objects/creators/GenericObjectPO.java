package org.sdmlib.models.objects.creators;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class GenericObjectPO extends PatternObject<GenericObjectPO, GenericObject>
{
   public GenericObjectPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
   
   public GenericLinkPO hasIncommingLinks()
   {
      GenericLinkPO result = new GenericLinkPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericObject.PROPERTY_INCOMMINGLINKS, result);
      
      return result;   }
   
   public GenericObjectPO hasIncommingLinks(GenericLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericObject.PROPERTY_INCOMMINGLINKS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericLinkSet getIncommingLinks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) this.getCurrentMatch()).getIncommingLinks();
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
   
}



