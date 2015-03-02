package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class EnumerationPO extends PatternObject<EnumerationPO, Enumeration>
{

    public EnumerationSet allMatches()
   {
      this.setDoAllMatches(true);
      
      EnumerationSet matches = new EnumerationSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Enumeration) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public EnumerationPO(){
      newInstance(org.sdmlib.models.classes.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public EnumerationPO(Enumeration... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.models.classes.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public EnumerationPO hasValueNames(ArrayListSet value)
   {
      new AttributeConstraint()
      .withAttrName(Enumeration.PROPERTY_VALUENAMES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EnumerationPO createValueNames(ArrayListSet value)
   {
      this.startCreate().hasValueNames(value).endCreate();
      return this;
   }
   
   public ArrayListSet getValueNames()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Enumeration) getCurrentMatch()).getValueNames();
      }
      return null;
   }
   
   public EnumerationPO withValueNames(ArrayListSet value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Enumeration) getCurrentMatch()).setValueNames(value);
      }
      return this;
   }
   
   public EnumerationPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Enumeration.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EnumerationPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Enumeration.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public EnumerationPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Enumeration) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public EnumerationPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Enumeration) getCurrentMatch()).withName(value);
      }
      return this;
   }
   
   public ClassModelPO hasClassModel()
   {
      ClassModelPO result = new ClassModelPO(new ClassModel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Enumeration.PROPERTY_CLASSMODEL, result);
      
      return result;
   }

   public ClassModelPO createClassModel()
   {
      return this.startCreate().hasClassModel().endCreate();
   }

   public EnumerationPO hasClassModel(ClassModelPO tgt)
   {
      return hasLinkConstraint(tgt, Enumeration.PROPERTY_CLASSMODEL);
   }

   public EnumerationPO createClassModel(ClassModelPO tgt)
   {
      return this.startCreate().hasClassModel(tgt).endCreate();
   }

   public ClassModel getClassModel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Enumeration) this.getCurrentMatch()).getClassModel();
      }
      return null;
   }

   public MethodPO hasMethods()
   {
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Enumeration.PROPERTY_METHODS, result);
      
      return result;
   }

   public MethodPO createMethods()
   {
      return this.startCreate().hasMethods().endCreate();
   }

   public EnumerationPO hasMethods(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Enumeration.PROPERTY_METHODS);
   }

   public EnumerationPO createMethods(MethodPO tgt)
   {
      return this.startCreate().hasMethods(tgt).endCreate();
   }

   public MethodSet getMethods()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Enumeration) this.getCurrentMatch()).getMethods();
      }
      return null;
   }

}
