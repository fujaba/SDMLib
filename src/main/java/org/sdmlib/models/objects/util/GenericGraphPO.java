package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.objects.util.GenericObjectPO;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.util.GenericGraphPO;
import org.sdmlib.models.objects.util.GenericLinkPO;
import org.sdmlib.models.objects.GenericLink;

public class GenericGraphPO extends PatternObject<GenericGraphPO, GenericGraph>
{
   public GenericGraphPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public GenericGraphPO(GenericGraph... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
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
   
   public GenericObjectPO createObjects()
   {
      return this.startCreate().hasObjects().endCreate();
   }

   public GenericGraphPO createObjects(GenericObjectPO tgt)
   {
      return this.startCreate().hasObjects(tgt).endCreate();
   }

   public GenericLinkPO createLinks()
   {
      return this.startCreate().hasLinks().endCreate();
   }

   public GenericGraphPO createLinks(GenericLinkPO tgt)
   {
      return this.startCreate().hasLinks(tgt).endCreate();
   }

   public GenericObjectPO filterObjects()
   {
      GenericObjectPO result = new GenericObjectPO(new GenericObject[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GenericGraph.PROPERTY_OBJECTS, result);
      
      return result;
   }

   public GenericGraphPO filterObjects(GenericObjectPO tgt)
   {
      return hasLinkConstraint(tgt, GenericGraph.PROPERTY_OBJECTS);
   }

   public GenericLinkPO filterLinks()
   {
      GenericLinkPO result = new GenericLinkPO(new GenericLink[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GenericGraph.PROPERTY_LINKS, result);
      
      return result;
   }

   public GenericGraphPO filterLinks(GenericLinkPO tgt)
   {
      return hasLinkConstraint(tgt, GenericGraph.PROPERTY_LINKS);
   }


   public GenericGraphPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public GenericObjectPO createObjectsPO()
   {
      GenericObjectPO result = new GenericObjectPO(new GenericObject[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GenericGraph.PROPERTY_OBJECTS, result);
      
      return result;
   }

   public GenericObjectPO createObjectsPO(String modifier)
   {
      GenericObjectPO result = new GenericObjectPO(new GenericObject[]{});
      
      result.setModifier(modifier);
      super.hasLink(GenericGraph.PROPERTY_OBJECTS, result);
      
      return result;
   }

   public GenericGraphPO createObjectsLink(GenericObjectPO tgt)
   {
      return hasLinkConstraint(tgt, GenericGraph.PROPERTY_OBJECTS);
   }

   public GenericGraphPO createObjectsLink(GenericObjectPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, GenericGraph.PROPERTY_OBJECTS, modifier);
   }

   public GenericLinkPO createLinksPO()
   {
      GenericLinkPO result = new GenericLinkPO(new GenericLink[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(GenericGraph.PROPERTY_LINKS, result);
      
      return result;
   }

   public GenericLinkPO createLinksPO(String modifier)
   {
      GenericLinkPO result = new GenericLinkPO(new GenericLink[]{});
      
      result.setModifier(modifier);
      super.hasLink(GenericGraph.PROPERTY_LINKS, result);
      
      return result;
   }

   public GenericGraphPO createLinksLink(GenericLinkPO tgt)
   {
      return hasLinkConstraint(tgt, GenericGraph.PROPERTY_LINKS);
   }

   public GenericGraphPO createLinksLink(GenericLinkPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, GenericGraph.PROPERTY_LINKS, modifier);
   }

}

