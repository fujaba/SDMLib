package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.util.ClassModelSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.classes.util.ClazzPO;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.util.ClassModelPO;
import org.sdmlib.models.classes.util.ClazzSet;

public class ClassModelPO extends PatternObject<ClassModelPO, ClassModel>
{
   public ClassModelPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ClassModelPO(ClassModel... hostGraphObject) {
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   @Override
   public ClassModelPO startNAC()
   {
      return (ClassModelPO) super.startNAC();
   }
   
   @Override
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
      return hasLinkConstraint(tgt, ClassModel.PROPERTY_CLASSES);
   }
   
   public ClazzSet getClasses()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClassModel) this.getCurrentMatch()).getClasses();
      }
      return null;
   }
   
     public ClassModelPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ClassModel.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String geName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClassModel) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ClassModelPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ClassModel.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClassModelPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public ClazzPO createClasses()
   {
      return this.startCreate().hasClasses().endCreate();
   }

   public ClassModelPO createClasses(ClazzPO tgt)
   {
      return this.startCreate().hasClasses(tgt).endCreate();
   }
}
