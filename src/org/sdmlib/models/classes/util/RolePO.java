package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class RolePO extends PatternObject<RolePO, Role>
{
   @Override
   public RolePO startNAC()
   {
      return (RolePO) super.startNAC();
   }
   
   @Override
   public RolePO endNAC()
   {
      return (RolePO) super.endNAC();
   }
   
   public RoleSet allMatches()
   {
      RoleSet matches = new RoleSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Role) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public RolePO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Role.PROPERTY_NAME)
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
         return ((Role) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public RolePO hasCard(String value)
   {
      new AttributeConstraint()
      .withAttrName(Role.PROPERTY_CARD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getCard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Role) getCurrentMatch()).getCard();
      }
      return null;
   }
   
   public RolePO hasKind(String value)
   {
      new AttributeConstraint()
      .withAttrName(Role.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getKind()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Role) getCurrentMatch()).getKind();
      }
      return null;
   }
   
   public ClazzPO hasClazz()
   {
      ClazzPO result = new ClazzPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Role.PROPERTY_CLAZZ, result);
      
      return result;
   }
   
   public RolePO hasClazz(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Role.PROPERTY_CLAZZ)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Clazz getClazz()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Role) this.getCurrentMatch()).getClazz();
      }
      return null;
   }
   
   public AssociationPO hasAssoc()
   {
      AssociationPO result = new AssociationPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Role.PROPERTY_ASSOC, result);
      
      return result;
   }
   
   public RolePO hasAssoc(AssociationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Role.PROPERTY_ASSOC)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Association getAssoc()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Role) this.getCurrentMatch()).getAssoc();
      }
      return null;
   }
   
   public RolePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Role.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RolePO hasCard(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Role.PROPERTY_CARD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RolePO hasKind(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Role.PROPERTY_KIND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RolePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public RolePO createCard(String value)
   {
      this.startCreate().hasCard(value).endCreate();
      return this;
   }
   
   public RolePO createKind(String value)
   {
      this.startCreate().hasKind(value).endCreate();
      return this;
   }
   
   public ClazzPO createClazz()
   {
      return this.startCreate().hasClazz().endCreate();
   }

   public RolePO createClazz(ClazzPO tgt)
   {
      return this.startCreate().hasClazz(tgt).endCreate();
   }

   public AssociationPO createAssoc()
   {
      return this.startCreate().hasAssoc().endCreate();
   }

   public RolePO createAssoc(AssociationPO tgt)
   {
      return this.startCreate().hasAssoc(tgt).endCreate();
   }

}



