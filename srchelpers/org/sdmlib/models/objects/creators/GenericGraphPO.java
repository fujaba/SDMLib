package org.sdmlib.models.objects.creators;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class GenericGraphPO extends PatternObject<GenericGraphPO, GenericGraph>
{
   public GenericGraphSet allMatches()
   {
      GenericGraphSet matches = new GenericGraphSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((GenericGraph) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public GenericObjectPO hasObjects()
   {
      GenericObjectPO result = new GenericObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericGraph.PROPERTY_OBJECTS, result);
      
      return result;
   }
   
   public GenericGraphPO hasObjects(GenericObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericGraph.PROPERTY_OBJECTS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericObjectSet getObjects()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericGraph) this.getCurrentMatch()).getObjects();
      }
      return null;
   }
      
   public GenericLinkPO hasLinks()
   {
      GenericLinkPO result = new GenericLinkPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericGraph.PROPERTY_LINKS, result);
      
      return result;
   }
   
   public GenericGraphPO hasLinks(GenericLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericGraph.PROPERTY_LINKS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericLinkSet getLinks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericGraph) this.getCurrentMatch()).getLinks();
      }
      return null;
   }
   
}


