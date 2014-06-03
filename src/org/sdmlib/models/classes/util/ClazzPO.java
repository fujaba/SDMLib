package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.logic.GenClass;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class ClazzPO extends PatternObject<ClazzPO, Clazz>
{
   public ClazzPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ClazzPO(Clazz... hostGraphObject) {
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   @Override
   public ClazzPO startNAC()
   {
      return (ClazzPO) super.startNAC();
   }
   
   @Override
   public ClazzPO endNAC()
   {
      return (ClazzPO) super.endNAC();
   }
   
   public ClazzSet allMatches()
   {
      ClazzSet matches = new ClazzSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Clazz) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ClazzPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_NAME)
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
         return ((Clazz) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ClazzPO hasInterfaze(Boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_INTERFACE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Boolean getInterfaze()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) getCurrentMatch()).isInterface();
      }
      return null;
   }
   
   public ClassModelPO hasClassModel()
   {
      ClassModelPO result = new ClassModelPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_CLASSMODEL, result);
      
      return result;
   }
   
   public ClazzPO hasClassModel(ClassModelPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_CLASSMODEL)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClassModel getClassModel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) this.getCurrentMatch()).getClassModel();
      }
      return null;
   }
   
   public ClazzPO hasSuperClass()
   {
      ClazzPO result = new ClazzPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_SUPERCLASS, result);
      
      return result;
   }
   
   public ClazzPO hasSuperClass(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_SUPERCLASS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Clazz getSuperClass()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) this.getCurrentMatch()).getSuperClass();
      }
      return null;
   }
   
   public ClazzSet getInterfaces()
   {
      if (this.getPattern().getHasMatch())
      {
         return  this.getCurrentMatch().getInterfaces();
      }
      return null;
   }
   
   public AttributePO hasAttributes()
   {
      AttributePO result = new AttributePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_ATTRIBUTES, result);
      
      return result;
   }
   
   public ClazzPO hasAttributes(AttributePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_ATTRIBUTES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AttributeSet getAttributes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) this.getCurrentMatch()).getAttributes();
      }
      return null;
   }
   
   public MethodPO hasMethods()
   {
      MethodPO result = new MethodPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_METHODS, result);
      
      return result;
   }
   
   public ClazzPO hasMethods(MethodPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_METHODS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MethodSet getMethods()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) this.getCurrentMatch()).getMethods();
      }
      return null;
   }
   
   public RolePO hasRoles()
   {
      RolePO result = new RolePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_ROLES, result);
      
      return result;
   }
   
   public ClazzPO hasRoles(RolePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_ROLES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoleSet getRoles()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) this.getCurrentMatch()).getRoles();
      }
      return null;
   }
   
   public ClazzPO hasExternal(Boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_EXTERNAL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Boolean getExternal()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) getCurrentMatch()).isExternal();
      }
      return null;
   }
   
   public ClazzPO hasInterfaze(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_INTERFACE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzPO hasExternal(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_EXTERNAL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
      
   public boolean getWrapped()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) getCurrentMatch()).isExternal();
      }
      return false;
   }
   
   public ClazzPO hasFilePath(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenClass.PROPERTY_FILEPATH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzPO hasInterfaze(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_INTERFACE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzPO hasExternal(boolean lower, boolean upper)
   {
      new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_EXTERNAL)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzPO hasFilePath(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenClass.PROPERTY_FILEPATH)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public ClazzPO createInterfaze(boolean value)
   {
      this.startCreate().hasInterfaze(value).endCreate();
      return this;
   }
   
   public ClazzPO createExternal(boolean value)
   {
      this.startCreate().hasExternal(value).endCreate();
      return this;
   }
   
   public ClazzPO createFilePath(String value)
   {
      this.startCreate().hasFilePath(value).endCreate();
      return this;
   }
   
   public ClassModelPO createClassModel()
   {
      return this.startCreate().hasClassModel().endCreate();
   }

   public ClazzPO createClassModel(ClassModelPO tgt)
   {
      return this.startCreate().hasClassModel(tgt).endCreate();
   }

   public ClazzPO createSuperClass()
   {
      return this.startCreate().hasSuperClass().endCreate();
   }

   public ClazzPO createSuperClass(ClazzPO tgt)
   {
      return this.startCreate().hasSuperClass(tgt).endCreate();
   }

   public AttributePO createAttributes()
   {
      return this.startCreate().hasAttributes().endCreate();
   }

   public ClazzPO createAttributes(AttributePO tgt)
   {
      return this.startCreate().hasAttributes(tgt).endCreate();
   }

   public MethodPO createMethods()
   {
      return this.startCreate().hasMethods().endCreate();
   }

   public ClazzPO createMethods(MethodPO tgt)
   {
      return this.startCreate().hasMethods(tgt).endCreate();
   }

   public RolePO createRoles()
   {
      return this.startCreate().hasRoles().endCreate();
   }

   public ClazzPO createRoles(RolePO tgt)
   {
      return this.startCreate().hasRoles(tgt).endCreate();
   }
}
