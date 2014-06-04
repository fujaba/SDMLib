package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.logic.GenClass;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.classes.util.ClassModelPO;
import org.sdmlib.models.classes.util.AttributePO;
import org.sdmlib.models.classes.util.MethodPO;
import org.sdmlib.models.classes.util.RolePO;
import org.sdmlib.models.classes.util.ClazzPO;

public class ClazzPO extends PatternObject<ClazzPO, Clazz>
{
   public ClazzPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ClazzPO(Clazz... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
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
      this.setDoAllMatches(true);
      
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
      ClassModelPO result = new ClassModelPO(new ClassModel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Clazz.PROPERTY_CLASSMODEL, result);
      
      return result;
   }
   
   public ClazzPO hasClassModel(ClassModelPO tgt)
   {
      return hasLinkConstraint(tgt, Clazz.PROPERTY_CLASSMODEL);
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
      
      super.hasLink(Clazz.PROPERTY_SUPERCLAZZES, result);
      
      return result;
   }
   
   public ClazzPO hasSuperClass(ClazzPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Clazz.PROPERTY_SUPERCLAZZES)
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
      AttributePO result = new AttributePO(new Attribute[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Clazz.PROPERTY_ATTRIBUTES, result);
      
      return result;
   }
   
   public ClazzPO hasAttributes(AttributePO tgt)
   {
      return hasLinkConstraint(tgt, Clazz.PROPERTY_ATTRIBUTES);
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
      MethodPO result = new MethodPO(new Method[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Clazz.PROPERTY_METHODS, result);
      
      return result;
   }
   
   public ClazzPO hasMethods(MethodPO tgt)
   {
      return hasLinkConstraint(tgt, Clazz.PROPERTY_METHODS);
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
      RolePO result = new RolePO(new Role[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Clazz.PROPERTY_ROLES, result);
      
      return result;
   }
   
   public ClazzPO hasRoles(RolePO tgt)
   {
      return hasLinkConstraint(tgt, Clazz.PROPERTY_ROLES);
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
   
   public boolean isExternal()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) getCurrentMatch()).isExternal();
      }
      return false;
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
   public ClazzPO hasInterface(boolean value)
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
   
   public ClazzPO createInterface(boolean value)
   {
      this.startCreate().hasInterface(value).endCreate();
      return this;
   }
   
   
   public ClazzPO withInterface(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Clazz) getCurrentMatch()).setInterface(value);
      }
      return this;
   }
   
   public ClazzPO hasKidClazzes()
   {
      ClazzPO result = new ClazzPO(new Clazz[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Clazz.PROPERTY_KIDCLAZZES, result);
      
      return result;
   }

   public ClazzPO createKidClazzes()
   {
      return this.startCreate().hasKidClazzes().endCreate();
   }

   public ClazzPO hasKidClazzes(ClazzPO tgt)
   {
      return hasLinkConstraint(tgt, Clazz.PROPERTY_KIDCLAZZES);
   }

   public ClazzPO createKidClazzes(ClazzPO tgt)
   {
      return this.startCreate().hasKidClazzes(tgt).endCreate();
   }

   public ClazzSet getKidClazzes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) this.getCurrentMatch()).getKidClazzes();
      }
      return null;
   }

   public ClazzPO hasSuperClazzes()
   {
      ClazzPO result = new ClazzPO(new Clazz[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Clazz.PROPERTY_SUPERCLAZZES, result);
      
      return result;
   }

   public ClazzPO createSuperClazzes()
   {
      return this.startCreate().hasSuperClazzes().endCreate();
   }

   public ClazzPO hasSuperClazzes(ClazzPO tgt)
   {
      return hasLinkConstraint(tgt, Clazz.PROPERTY_SUPERCLAZZES);
   }

   public ClazzPO createSuperClazzes(ClazzPO tgt)
   {
      return this.startCreate().hasSuperClazzes(tgt).endCreate();
   }

   public ClazzSet getSuperClazzes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) this.getCurrentMatch()).getSuperClazzes();
      }
      return null;
   }

   public boolean getExternal()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Clazz) getCurrentMatch()).isExternal();
      }
      return false;
   }
   
   public ClazzPO withExternal(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Clazz) getCurrentMatch()).setExternal(value);
      }
      return this;
   }
   
}
