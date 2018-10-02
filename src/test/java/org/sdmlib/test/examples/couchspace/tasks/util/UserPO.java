package org.sdmlib.test.examples.couchspace.tasks.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import org.sdmlib.test.examples.couchspace.tasks.User;
import org.sdmlib.test.examples.couchspace.tasks.UserGroup;

public class UserPO extends PatternObject<UserPO, User>
{

    public UserSet allMatches()
   {
      this.setDoAllMatches(true);
      
      UserSet matches = new UserSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((User) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public UserPO(){
      newInstance(null);
   }

   public UserPO(User... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public UserPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(User.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UserPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(User.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UserPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((User) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public UserPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((User) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TaskPO filterHandledTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(User.PROPERTY_HANDLEDTASKS, result);
      
      return result;
   }

   public TaskPO createHandledTasks()
   {
      return this.startCreate().filterHandledTasks().endCreate();
   }

   public UserPO filterHandledTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_HANDLEDTASKS);
   }

   public UserPO createHandledTasks(TaskPO tgt)
   {
      return this.startCreate().filterHandledTasks(tgt).endCreate();
   }

   public TaskSet getHandledTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((User) this.getCurrentMatch()).getHandledTasks();
      }
      return null;
   }

   public UserGroupPO filterGroups()
   {
      UserGroupPO result = new UserGroupPO(new UserGroup[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(User.PROPERTY_GROUPS, result);
      
      return result;
   }

   public UserGroupPO createGroups()
   {
      return this.startCreate().filterGroups().endCreate();
   }

   public UserPO filterGroups(UserGroupPO tgt)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_GROUPS);
   }

   public UserPO createGroups(UserGroupPO tgt)
   {
      return this.startCreate().filterGroups(tgt).endCreate();
   }

   public UserGroupSet getGroups()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((User) this.getCurrentMatch()).getGroups();
      }
      return null;
   }


   public UserPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public UserPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(User.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UserPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(User.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UserPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(User.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createHandledTasksPO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(User.PROPERTY_HANDLEDTASKS, result);
      
      return result;
   }

   public TaskPO createHandledTasksPO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(User.PROPERTY_HANDLEDTASKS, result);
      
      return result;
   }

   public UserPO createHandledTasksLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_HANDLEDTASKS);
   }

   public UserPO createHandledTasksLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_HANDLEDTASKS, modifier);
   }

   public UserGroupPO createGroupsPO()
   {
      UserGroupPO result = new UserGroupPO(new UserGroup[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(User.PROPERTY_GROUPS, result);
      
      return result;
   }

   public UserGroupPO createGroupsPO(String modifier)
   {
      UserGroupPO result = new UserGroupPO(new UserGroup[]{});
      
      result.setModifier(modifier);
      super.hasLink(User.PROPERTY_GROUPS, result);
      
      return result;
   }

   public UserPO createGroupsLink(UserGroupPO tgt)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_GROUPS);
   }

   public UserPO createGroupsLink(UserGroupPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, User.PROPERTY_GROUPS, modifier);
   }

}
