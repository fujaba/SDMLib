package org.sdmlib.test.examples.couchspace.tasks.util;

import org.sdmlib.models.pattern.AttributeConstraint;
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

}
