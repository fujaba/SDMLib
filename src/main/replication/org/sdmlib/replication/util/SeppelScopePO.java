package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSpaceProxy;

public class SeppelScopePO extends PatternObject<SeppelScopePO, SeppelScope>
{

    public SeppelScopeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SeppelScopeSet matches = new SeppelScopeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SeppelScope) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SeppelScopePO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SeppelScopePO(SeppelScope... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public SeppelScopePO hasScopeName(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelScope.PROPERTY_SCOPENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelScopePO hasScopeName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelScope.PROPERTY_SCOPENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelScopePO createScopeName(String value)
   {
      this.startCreate().hasScopeName(value).endCreate();
      return this;
   }
   
   public String getScopeName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelScope) getCurrentMatch()).getScopeName();
      }
      return null;
   }
   
   public SeppelScopePO withScopeName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelScope) getCurrentMatch()).setScopeName(value);
      }
      return this;
   }
   
   public SeppelScopePO hasSubScopes()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_SUBSCOPES, result);
      
      return result;
   }

   public SeppelScopePO createSubScopes()
   {
      return this.startCreate().hasSubScopes().endCreate();
   }

   public SeppelScopePO hasSubScopes(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SUBSCOPES);
   }

   public SeppelScopePO createSubScopes(SeppelScopePO tgt)
   {
      return this.startCreate().hasSubScopes(tgt).endCreate();
   }

   public SeppelScopeSet getSubScopes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelScope) this.getCurrentMatch()).getSubScopes();
      }
      return null;
   }

   public SeppelScopePO hasSuperScopes()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_SUPERSCOPES, result);
      
      return result;
   }

   public SeppelScopePO createSuperScopes()
   {
      return this.startCreate().hasSuperScopes().endCreate();
   }

   public SeppelScopePO hasSuperScopes(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SUPERSCOPES);
   }

   public SeppelScopePO createSuperScopes(SeppelScopePO tgt)
   {
      return this.startCreate().hasSuperScopes(tgt).endCreate();
   }

   public SeppelScopeSet getSuperScopes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelScope) this.getCurrentMatch()).getSuperScopes();
      }
      return null;
   }

   public SeppelSpaceProxyPO hasSpaces()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_SPACES, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createSpaces()
   {
      return this.startCreate().hasSpaces().endCreate();
   }

   public SeppelScopePO hasSpaces(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SPACES);
   }

   public SeppelScopePO createSpaces(SeppelSpaceProxyPO tgt)
   {
      return this.startCreate().hasSpaces(tgt).endCreate();
   }

   public SeppelSpaceProxySet getSpaces()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelScope) this.getCurrentMatch()).getSpaces();
      }
      return null;
   }

   public ObjectPO hasObservedObjects()
   {
      ObjectPO result = new ObjectPO(new java.lang.Object[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_OBSERVEDOBJECTS, result);
      
      return result;
   }

   public ObjectPO createObservedObjects()
   {
      return this.startCreate().hasObservedObjects().endCreate();
   }

   public SeppelScopePO hasObservedObjects(ObjectPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_OBSERVEDOBJECTS);
   }

   public SeppelScopePO createObservedObjects(ObjectPO tgt)
   {
      return this.startCreate().hasObservedObjects(tgt).endCreate();
   }

   public ObjectSet getObservedObjects()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelScope) this.getCurrentMatch()).getObservedObjects();
      }
      return null;
   }

   public SeppelScopePO filterScopeName(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelScope.PROPERTY_SCOPENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelScopePO filterScopeName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelScope.PROPERTY_SCOPENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelScopePO filterSuperScopes()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_SUPERSCOPES, result);
      
      return result;
   }

   public SeppelScopePO filterSuperScopes(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SUPERSCOPES);
   }

   public SeppelSpaceProxyPO filterSpaces()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_SPACES, result);
      
      return result;
   }

   public SeppelScopePO filterSpaces(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SPACES);
   }

   public ObjectPO filterObservedObjects()
   {
      ObjectPO result = new ObjectPO(new java.lang.Object[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_OBSERVEDOBJECTS, result);
      
      return result;
   }

   public SeppelScopePO filterObservedObjects(ObjectPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_OBSERVEDOBJECTS);
   }

   public SeppelScopePO filterSubScopes()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_SUBSCOPES, result);
      
      return result;
   }

   public SeppelScopePO filterSubScopes(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SUBSCOPES);
   }


   public SeppelScopePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public SeppelScopePO createScopeNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelScope.PROPERTY_SCOPENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelScopePO createScopeNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelScope.PROPERTY_SCOPENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelScopePO createScopeNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelScope.PROPERTY_SCOPENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ObjectPO createObservedObjectsPO()
   {
      ObjectPO result = new ObjectPO(new java.lang.Object[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_OBSERVEDOBJECTS, result);
      
      return result;
   }

   public ObjectPO createObservedObjectsPO(String modifier)
   {
      ObjectPO result = new ObjectPO(new java.lang.Object[]{});
      
      result.setModifier(modifier);
      super.hasLink(SeppelScope.PROPERTY_OBSERVEDOBJECTS, result);
      
      return result;
   }

   public SeppelScopePO createObservedObjectsLink(ObjectPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_OBSERVEDOBJECTS);
   }

   public SeppelScopePO createObservedObjectsLink(ObjectPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_OBSERVEDOBJECTS, modifier);
   }

   public SeppelSpaceProxyPO createSpacesPO()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_SPACES, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createSpacesPO(String modifier)
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(modifier);
      super.hasLink(SeppelScope.PROPERTY_SPACES, result);
      
      return result;
   }

   public SeppelScopePO createSpacesLink(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SPACES);
   }

   public SeppelScopePO createSpacesLink(SeppelSpaceProxyPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SPACES, modifier);
   }

   public SeppelScopePO createSuperScopesPO()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_SUPERSCOPES, result);
      
      return result;
   }

   public SeppelScopePO createSuperScopesPO(String modifier)
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(modifier);
      super.hasLink(SeppelScope.PROPERTY_SUPERSCOPES, result);
      
      return result;
   }

   public SeppelScopePO createSuperScopesLink(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SUPERSCOPES);
   }

   public SeppelScopePO createSuperScopesLink(SeppelScopePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SUPERSCOPES, modifier);
   }

   public SeppelScopePO createSubScopesPO()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelScope.PROPERTY_SUBSCOPES, result);
      
      return result;
   }

   public SeppelScopePO createSubScopesPO(String modifier)
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(modifier);
      super.hasLink(SeppelScope.PROPERTY_SUBSCOPES, result);
      
      return result;
   }

   public SeppelScopePO createSubScopesLink(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SUBSCOPES);
   }

   public SeppelScopePO createSubScopesLink(SeppelScopePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, SeppelScope.PROPERTY_SUBSCOPES, modifier);
   }

}
