package org.sdmlib.models.classes.creators;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class ClazzPO extends PatternObject
{
   public ClazzPO startNAC()
   {
      return (ClazzPO) super.startNAC();
   }
   
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_INTERFAZE)
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
         return ((Clazz) getCurrentMatch()).isInterfaze();
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
   
   public ClazzPO hasKidClasses()
   {
      ClazzPO result = new ClazzPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_KIDCLASSES, result);
      
      return result;
   }
   
   public ClazzPO hasKidClasses(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_KIDCLASSES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzSet getKidClasses()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClazzSet) this.getCurrentMatch()).getKidClasses();
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
   
   public ClazzPO hasKindClassesAsInterface()
   {
      ClazzPO result = new ClazzPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_KIDCLASSESASINTERFACE, result);
      
      return result;
   }
   
   public ClazzPO hasKindClassesAsInterface(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_KIDCLASSESASINTERFACE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzSet getKindClassesAsInterface()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClazzSet) this.getCurrentMatch()).getKindClassesAsInterface();
      }
      return null;
   }
   
   public ClazzPO hasInterfaces()
   {
      ClazzPO result = new ClazzPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_INTERFACES, result);
      
      return result;
   }
   
   public ClazzPO hasInterfaces(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_INTERFACES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzSet getInterfaces()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClazzSet) this.getCurrentMatch()).getInterfaces();
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
   
   public RolePO hasSourceRoles()
   {
      RolePO result = new RolePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_SOURCEROLES, result);
      
      return result;
   }
   
   public ClazzPO hasSourceRoles(RolePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_SOURCEROLES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoleSet getSourceRoles()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) this.getCurrentMatch()).getSourceRoles();
      }
      return null;
   }
   
   public RolePO hasTargetRoles()
   {
      RolePO result = new RolePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_TARGETROLES, result);
      
      return result;
   }
   
   public ClazzPO hasTargetRoles(RolePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_TARGETROLES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RoleSet getTargetRoles()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) this.getCurrentMatch()).getTargetRoles();
      }
      return null;
   }
   
   public ClazzPO hasExternal(Boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_INTERFAZE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzPO hasExternal(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_EXTERNAL)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzPO hasWrapped(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_WRAPPED)
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
         return ((Clazz) getCurrentMatch()).getWrapped();
      }
      return false;
   }
   
   public ClazzPO hasFilePath(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Clazz.PROPERTY_FILEPATH)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getFilePath()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) getCurrentMatch()).getFilePath();
      }
      return null;
   }
   
   public ClazzPO withFilePath(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Clazz) getCurrentMatch()).setFilePath(value);
      }
      return this;
   }
   
   public ClazzPO hasKidClassesAsInterface()
   {
      ClazzPO result = new ClazzPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Clazz.PROPERTY_KIDCLASSESASINTERFACE, result);
      
      return result;
   }
   
   public ClazzPO hasKidClassesAsInterface(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_KIDCLASSESASINTERFACE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ClazzSet getKidClassesAsInterface()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ClazzSet) this.getCurrentMatch()).getKidClassesAsInterface();
      }
      return null;
   }
   
}





