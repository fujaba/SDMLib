package org.sdmlib.test.examples.couchspace.tasks.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import org.sdmlib.test.examples.couchspace.tasks.TaskFlow;
import org.sdmlib.test.examples.couchspace.tasks.User;
import org.sdmlib.test.examples.couchspace.tasks.UserGroup;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.couchspace.tasks.util.TaskFlowPO;
import org.sdmlib.test.examples.couchspace.tasks.util.TaskPO;
import org.sdmlib.test.examples.couchspace.tasks.util.UserPO;
import org.sdmlib.test.examples.couchspace.tasks.util.UserGroupPO;

public class TaskPO extends PatternObject<TaskPO, Task>
{

    public TaskSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TaskSet matches = new TaskSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Task) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TaskPO(){
      newInstance(null);
   }

   public TaskPO(Task... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public TaskPO filterTitle(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterTitle(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TITLE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createTitle(String value)
   {
      this.startCreate().filterTitle(value).endCreate();
      return this;
   }
   
   public String getTitle()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) getCurrentMatch()).getTitle();
      }
      return null;
   }
   
   public TaskPO withTitle(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Task) getCurrentMatch()).setTitle(value);
      }
      return this;
   }
   
   public TaskPO filterTransitionCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TRANSITIONCONDITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterTransitionCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TRANSITIONCONDITION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createTransitionCondition(String value)
   {
      this.startCreate().filterTransitionCondition(value).endCreate();
      return this;
   }
   
   public String getTransitionCondition()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) getCurrentMatch()).getTransitionCondition();
      }
      return null;
   }
   
   public TaskPO withTransitionCondition(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Task) getCurrentMatch()).setTransitionCondition(value);
      }
      return this;
   }
   
   public TaskPO filterCopySdmLibId(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_COPYSDMLIBID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterCopySdmLibId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_COPYSDMLIBID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createCopySdmLibId(String value)
   {
      this.startCreate().filterCopySdmLibId(value).endCreate();
      return this;
   }
   
   public String getCopySdmLibId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) getCurrentMatch()).getCopySdmLibId();
      }
      return null;
   }
   
   public TaskPO withCopySdmLibId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Task) getCurrentMatch()).setCopySdmLibId(value);
      }
      return this;
   }
   
   public TaskFlowPO filterTaskFlow()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_TASKFLOW, result);
      
      return result;
   }

   public TaskFlowPO createTaskFlow()
   {
      return this.startCreate().filterTaskFlow().endCreate();
   }

   public TaskPO filterTaskFlow(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TASKFLOW);
   }

   public TaskPO createTaskFlow(TaskFlowPO tgt)
   {
      return this.startCreate().filterTaskFlow(tgt).endCreate();
   }

   public TaskFlow getTaskFlow()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getTaskFlow();
      }
      return null;
   }

   public TaskFlowPO filterTaskFlowFirst()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_TASKFLOWFIRST, result);
      
      return result;
   }

   public TaskFlowPO createTaskFlowFirst()
   {
      return this.startCreate().filterTaskFlowFirst().endCreate();
   }

   public TaskPO filterTaskFlowFirst(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TASKFLOWFIRST);
   }

   public TaskPO createTaskFlowFirst(TaskFlowPO tgt)
   {
      return this.startCreate().filterTaskFlowFirst(tgt).endCreate();
   }

   public TaskFlow getTaskFlowFirst()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getTaskFlowFirst();
      }
      return null;
   }

   public TaskPO filterTransitionSource()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_TRANSITIONSOURCE, result);
      
      return result;
   }

   public TaskPO createTransitionSource()
   {
      return this.startCreate().filterTransitionSource().endCreate();
   }

   public TaskPO filterTransitionSource(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TRANSITIONSOURCE);
   }

   public TaskPO createTransitionSource(TaskPO tgt)
   {
      return this.startCreate().filterTransitionSource(tgt).endCreate();
   }

   public Task getTransitionSource()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getTransitionSource();
      }
      return null;
   }

   public UserGroupPO filterResponsibles()
   {
      UserGroupPO result = new UserGroupPO(new UserGroup[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_RESPONSIBLES, result);
      
      return result;
   }

   public UserGroupPO createResponsibles()
   {
      return this.startCreate().filterResponsibles().endCreate();
   }

   public TaskPO filterResponsibles(UserGroupPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_RESPONSIBLES);
   }

   public TaskPO createResponsibles(UserGroupPO tgt)
   {
      return this.startCreate().filterResponsibles(tgt).endCreate();
   }

   public UserGroupSet getResponsibles()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getResponsibles();
      }
      return null;
   }

   public UserPO filterHandledBy()
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_HANDLEDBY, result);
      
      return result;
   }

   public UserPO createHandledBy()
   {
      return this.startCreate().filterHandledBy().endCreate();
   }

   public TaskPO filterHandledBy(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_HANDLEDBY);
   }

   public TaskPO createHandledBy(UserPO tgt)
   {
      return this.startCreate().filterHandledBy(tgt).endCreate();
   }

   public User getHandledBy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getHandledBy();
      }
      return null;
   }

   public TaskPO filterTransitionTargets()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_TRANSITIONTARGETS, result);
      
      return result;
   }

   public TaskPO createTransitionTargets()
   {
      return this.startCreate().filterTransitionTargets().endCreate();
   }

   public TaskPO filterTransitionTargets(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TRANSITIONTARGETS);
   }

   public TaskPO createTransitionTargets(TaskPO tgt)
   {
      return this.startCreate().filterTransitionTargets(tgt).endCreate();
   }

   public TaskSet getTransitionTargets()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getTransitionTargets();
      }
      return null;
   }


   public TaskPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TaskPO createCopySdmLibIdCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_COPYSDMLIBID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createCopySdmLibIdCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_COPYSDMLIBID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createCopySdmLibIdAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_COPYSDMLIBID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createTitleCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createTitleCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TITLE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createTitleAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createTransitionConditionCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TRANSITIONCONDITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createTransitionConditionCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TRANSITIONCONDITION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createTransitionConditionAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_TRANSITIONCONDITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO createTaskFlowFirstPO()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_TASKFLOWFIRST, result);
      
      return result;
   }

   public TaskFlowPO createTaskFlowFirstPO(String modifier)
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(modifier);
      super.hasLink(Task.PROPERTY_TASKFLOWFIRST, result);
      
      return result;
   }

   public TaskPO createTaskFlowFirstLink(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TASKFLOWFIRST);
   }

   public TaskPO createTaskFlowFirstLink(TaskFlowPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TASKFLOWFIRST, modifier);
   }

   public UserPO createHandledByPO()
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_HANDLEDBY, result);
      
      return result;
   }

   public UserPO createHandledByPO(String modifier)
   {
      UserPO result = new UserPO(new User[]{});
      
      result.setModifier(modifier);
      super.hasLink(Task.PROPERTY_HANDLEDBY, result);
      
      return result;
   }

   public TaskPO createHandledByLink(UserPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_HANDLEDBY);
   }

   public TaskPO createHandledByLink(UserPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_HANDLEDBY, modifier);
   }

   public UserGroupPO createResponsiblesPO()
   {
      UserGroupPO result = new UserGroupPO(new UserGroup[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_RESPONSIBLES, result);
      
      return result;
   }

   public UserGroupPO createResponsiblesPO(String modifier)
   {
      UserGroupPO result = new UserGroupPO(new UserGroup[]{});
      
      result.setModifier(modifier);
      super.hasLink(Task.PROPERTY_RESPONSIBLES, result);
      
      return result;
   }

   public TaskPO createResponsiblesLink(UserGroupPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_RESPONSIBLES);
   }

   public TaskPO createResponsiblesLink(UserGroupPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_RESPONSIBLES, modifier);
   }

   public TaskFlowPO createTaskFlowPO()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_TASKFLOW, result);
      
      return result;
   }

   public TaskFlowPO createTaskFlowPO(String modifier)
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(modifier);
      super.hasLink(Task.PROPERTY_TASKFLOW, result);
      
      return result;
   }

   public TaskPO createTaskFlowLink(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TASKFLOW);
   }

   public TaskPO createTaskFlowLink(TaskFlowPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TASKFLOW, modifier);
   }

   public TaskPO createTransitionTargetsPO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_TRANSITIONTARGETS, result);
      
      return result;
   }

   public TaskPO createTransitionTargetsPO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(Task.PROPERTY_TRANSITIONTARGETS, result);
      
      return result;
   }

   public TaskPO createTransitionTargetsLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TRANSITIONTARGETS);
   }

   public TaskPO createTransitionTargetsLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TRANSITIONTARGETS, modifier);
   }

   public TaskPO createTransitionSourcePO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_TRANSITIONSOURCE, result);
      
      return result;
   }

   public TaskPO createTransitionSourcePO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(Task.PROPERTY_TRANSITIONSOURCE, result);
      
      return result;
   }

   public TaskPO createTransitionSourceLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TRANSITIONSOURCE);
   }

   public TaskPO createTransitionSourceLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TRANSITIONSOURCE, modifier);
   }

}
