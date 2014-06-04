package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.util.AssociationSet;
import org.sdmlib.models.classes.util.RolePO;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.util.AssociationPO;
import org.sdmlib.models.pattern.AttributeConstraint;

public class AssociationPO extends PatternObject<AssociationPO, Association>
{
   public AssociationPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public AssociationPO(Association... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   @Override
   public AssociationPO startNAC()
   {
      return (AssociationPO) super.startNAC();
   }
   
   @Override
   public AssociationPO endNAC()
   {
      return (AssociationPO) super.endNAC();
   }
   
   public AssociationSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AssociationSet matches = new AssociationSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Association) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }

   public RolePO hasSource()
   {
      RolePO result = new RolePO(new Role[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Association.PROPERTY_SOURCE, result);
      
      return result;
   }
   
   public AssociationPO hasSource(RolePO tgt)
   {
      return hasLinkConstraint(tgt, Association.PROPERTY_SOURCE);
   }
   
   public Role getSource()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Association) this.getCurrentMatch()).getSource();
      }
      return null;
   }
   
   public RolePO hasTarget()
   {
      RolePO result = new RolePO(new Role[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Association.PROPERTY_TARGET, result);
      
      return result;
   }
   
   public AssociationPO hasTarget(RolePO tgt)
   {
      return hasLinkConstraint(tgt, Association.PROPERTY_TARGET);
   }
   
   public Role getTarget()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Association) this.getCurrentMatch()).getTarget();
      }
      return null;
   }
   
   public RolePO createSource()
   {
      return this.startCreate().hasSource().endCreate();
   }

   public AssociationPO createSource(RolePO tgt)
   {
      return this.startCreate().hasSource(tgt).endCreate();
   }
   public AssociationPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Association.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssociationPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Association.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssociationPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Association) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public AssociationPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Association) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public RolePO createTarget()
   {
      return this.startCreate().hasTarget().endCreate();
   }

   public AssociationPO createTarget(RolePO tgt)
   {
      return this.startCreate().hasTarget(tgt).endCreate();
   }

}
