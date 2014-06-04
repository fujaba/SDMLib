package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.util.AssociationSet;
import org.sdmlib.models.classes.util.RolePO;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.util.AssociationPO;

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
}
