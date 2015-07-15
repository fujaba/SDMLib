package org.sdmlib.models.classes.util;

import java.util.Set;

import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class AnnotationPO extends PatternObject<AnnotationPO, Annotation>
{

    public AnnotationSet allMatches()
   {
      this.setDoAllMatches(true);
      
      AnnotationSet matches = new AnnotationSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Annotation) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public AnnotationPO(){
      newInstance(org.sdmlib.models.classes.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public AnnotationPO(Annotation... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.models.classes.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public AnnotationPO hasValues(ArrayListSet value)
   {
      new AttributeConstraint()
      .withAttrName(Annotation.PROPERTY_VALUES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public AnnotationPO createValues(ArrayListSet value)
   {
      this.startCreate().hasValues(value).endCreate();
      return this;
   }
   
   public Set<String> getValues()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Annotation) getCurrentMatch()).getValues();
      }
      return null;
   }
   
   public AnnotationPO withValues(Set<String> value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Annotation) getCurrentMatch()).setValues(value);
      }
      return this;
   }
   
   public AnnotationPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Annotation.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public AnnotationPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Annotation.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public AnnotationPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Annotation) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public AnnotationPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Annotation) getCurrentMatch()).withName(value);
      }
      return this;
   }
}
