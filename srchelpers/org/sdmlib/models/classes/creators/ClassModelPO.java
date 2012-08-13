package org.sdmlib.models.classes.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.creators.ClassModelSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.classes.creators.ClazzPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.classes.creators.ClassModelPO;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.creators.ClazzSet;
import org.sdmlib.models.classes.creators.AssociationPO;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.creators.AssociationSet;

public class ClassModelPO extends PatternObject
{
   public ClassModelPO startNAC()
   {
      return (ClassModelPO) super.startNAC();
   }
   
   public ClassModelPO endNAC()
   {
      return (ClassModelPO) super.endNAC();
   }
   
   public ClassModelSet allMatches()
   {
      ClassModelSet matches = new ClassModelSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ClassModel) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ClazzPO hasClasses()
   {
      ClazzPO result = new ClazzPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ClassModel.PROPERTY_CLASSES, result);
      
      return result;
   }
   
   public ClassModelPO hasClasses(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ClassModel.PROPERTY_CLASSES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzSet getClasses()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClassModel) this.getCurrentMatch()).getClasses();
      }
      return null;
   }
   
   public AssociationPO hasAssociations()
   {
      AssociationPO result = new AssociationPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ClassModel.PROPERTY_ASSOCIATIONS, result);
      
      return result;
   }
   
   public ClassModelPO hasAssociations(AssociationPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ClassModel.PROPERTY_ASSOCIATIONS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AssociationSet getAssociations()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClassModel) this.getCurrentMatch()).getAssociations();
      }
      return null;
   }
   
}

