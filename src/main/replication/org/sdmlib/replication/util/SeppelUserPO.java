package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.SeppelUser;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.replication.util.SeppelSpaceProxyPO;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.replication.util.SeppelUserPO;
import org.sdmlib.replication.util.SeppelSpaceProxySet;
import org.sdmlib.replication.util.SeppelScopePO;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.util.SeppelScopeSet;

public class SeppelUserPO extends PatternObject<SeppelUserPO, SeppelUser>
{

    public SeppelUserSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SeppelUserSet matches = new SeppelUserSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SeppelUser) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SeppelUserPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SeppelUserPO(SeppelUser... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public SeppelUserPO hasLoginName(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelUser.PROPERTY_LOGINNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelUserPO hasLoginName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelUser.PROPERTY_LOGINNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelUserPO createLoginName(String value)
   {
      this.startCreate().hasLoginName(value).endCreate();
      return this;
   }
   
   public String getLoginName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelUser) getCurrentMatch()).getLoginName();
      }
      return null;
   }
   
   public SeppelUserPO withLoginName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelUser) getCurrentMatch()).setLoginName(value);
      }
      return this;
   }
   
   public SeppelUserPO hasPassword(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelUser.PROPERTY_PASSWORD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelUserPO hasPassword(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelUser.PROPERTY_PASSWORD)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelUserPO createPassword(String value)
   {
      this.startCreate().hasPassword(value).endCreate();
      return this;
   }
   
   public String getPassword()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelUser) getCurrentMatch()).getPassword();
      }
      return null;
   }
   
   public SeppelUserPO withPassword(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelUser) getCurrentMatch()).setPassword(value);
      }
      return this;
   }
   
   public SeppelSpaceProxyPO hasMasterSpace()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelUser.PROPERTY_MASTERSPACE, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createMasterSpace()
   {
      return this.startCreate().hasMasterSpace().endCreate();
   }

   public SeppelUserPO hasMasterSpace(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelUser.PROPERTY_MASTERSPACE);
   }

   public SeppelUserPO createMasterSpace(SeppelSpaceProxyPO tgt)
   {
      return this.startCreate().hasMasterSpace(tgt).endCreate();
   }

   public SeppelSpaceProxy getMasterSpace()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelUser) this.getCurrentMatch()).getMasterSpace();
      }
      return null;
   }

   public SeppelSpaceProxyPO hasSpaces()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelUser.PROPERTY_SPACES, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createSpaces()
   {
      return this.startCreate().hasSpaces().endCreate();
   }

   public SeppelUserPO hasSpaces(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, SeppelUser.PROPERTY_SPACES);
   }

   public SeppelUserPO createSpaces(SeppelSpaceProxyPO tgt)
   {
      return this.startCreate().hasSpaces(tgt).endCreate();
   }

   public SeppelSpaceProxySet getSpaces()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelUser) this.getCurrentMatch()).getSpaces();
      }
      return null;
   }

   public SeppelScopePO hasScopes()
   {
      SeppelScopePO result = new SeppelScopePO(new SeppelScope[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(SeppelUser.PROPERTY_SCOPES, result);
      
      return result;
   }

   public SeppelScopePO createScopes()
   {
      return this.startCreate().hasScopes().endCreate();
   }

   public SeppelUserPO hasScopes(SeppelScopePO tgt)
   {
      return hasLinkConstraint(tgt, SeppelUser.PROPERTY_SCOPES);
   }

   public SeppelUserPO createScopes(SeppelScopePO tgt)
   {
      return this.startCreate().hasScopes(tgt).endCreate();
   }

   public SeppelScopeSet getScopes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelUser) this.getCurrentMatch()).getScopes();
      }
      return null;
   }

}
