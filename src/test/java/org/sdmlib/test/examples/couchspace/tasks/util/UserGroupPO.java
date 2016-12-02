package org.sdmlib.test.examples.couchspace.tasks.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import org.sdmlib.test.examples.couchspace.tasks.User;
import org.sdmlib.test.examples.couchspace.tasks.UserGroup;

public class UserGroupPO extends PatternObject<UserGroupPO, UserGroup>
{

    public UserGroupSet allMatches()
   {
      this.setDoAllMatches(true);
      
      UserGroupSet matches = new UserGroupSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((UserGroup) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public UserGroupPO(){
      newInstance(null);
   }

   public UserGroupPO(UserGroup... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public UserGroupPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(UserGroup.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UserGroupPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(UserGroup.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UserGroupPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UserGroup) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public UserGroupPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UserGroup) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TaskPO filterResponsible()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(UserGroup.PROPERTY_RESPONSIBLE, result);
      
      return result;
   }

   public TaskPO createResponsible()
   {
      return this.startCreate().filterResponsible().endCreate();
   }

   public UserGroupPO filterResponsible(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, UserGroup.PROPERTY_RESPONSIBLE);
   }

   public UserGroupPO createResponsible(TaskPO tgt)
   {
      return this.startCreate().filterResponsible(tgt).endCreate();
   }

   public TaskSet getResponsible()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UserGroup) this.getCurrentMatch()).getResponsible();
      }
      return null;
   }

   public UserPO filterMembers()
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(UserGroup.PROPERTY_MEMBERS, result);
      
      return result;
   }

   public UserPO createMembers()
   {
      return this.startCreate().filterMembers().endCreate();
   }

   public UserGroupPO filterMembers(UserPO tgt)
   {
      return hasLinkConstraint(tgt, UserGroup.PROPERTY_MEMBERS);
   }

   public UserGroupPO createMembers(UserPO tgt)
   {
      return this.startCreate().filterMembers(tgt).endCreate();
   }

   public UserSet getMembers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UserGroup) this.getCurrentMatch()).getMembers();
      }
      return null;
   }

}
