package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.Task;
import org.sdmlib.modelspace.TaskLane;

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
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TaskPO(Task... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public TaskLanePO hasLane()
   {
      TaskLanePO result = new TaskLanePO(new TaskLane[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_LANE, result);
      
      return result;
   }

   public TaskLanePO createLane()
   {
      return this.startCreate().hasLane().endCreate();
   }

   public TaskPO hasLane(TaskLanePO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_LANE);
   }

   public TaskPO createLane(TaskLanePO tgt)
   {
      return this.startCreate().hasLane(tgt).endCreate();
   }

   public TaskLane getLane()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getLane();
      }
      return null;
   }

   public TaskPO hasState(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_STATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO hasState(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_STATE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createState(String value)
   {
      this.startCreate().hasState(value).endCreate();
      return this;
   }
   
   public String getState()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) getCurrentMatch()).getState();
      }
      return null;
   }
   
   public TaskPO withState(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Task) getCurrentMatch()).setState(value);
      }
      return this;
   }
   
   public TaskPO hasSpaceName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_SPACENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO hasSpaceName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_SPACENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createSpaceName(String value)
   {
      this.startCreate().hasSpaceName(value).endCreate();
      return this;
   }
   
   public String getSpaceName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) getCurrentMatch()).getSpaceName();
      }
      return null;
   }
   
   public TaskPO withSpaceName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Task) getCurrentMatch()).setSpaceName(value);
      }
      return this;
   }
   
   public TaskPO hasFileName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_FILENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO hasFileName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_FILENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createFileName(String value)
   {
      this.startCreate().hasFileName(value).endCreate();
      return this;
   }
   
   public String getFileName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) getCurrentMatch()).getFileName();
      }
      return null;
   }
   
   public TaskPO withFileName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Task) getCurrentMatch()).setFileName(value);
      }
      return this;
   }
   
   public TaskPO hasLastModified(long value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_LASTMODIFIED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO hasLastModified(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_LASTMODIFIED)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createLastModified(long value)
   {
      this.startCreate().hasLastModified(value).endCreate();
      return this;
   }
   
   public long getLastModified()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) getCurrentMatch()).getLastModified();
      }
      return 0;
   }
   
   public TaskPO withLastModified(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Task) getCurrentMatch()).setLastModified(value);
      }
      return this;
   }
   
   public TaskLanePO hasFileTargetCloud()
   {
      TaskLanePO result = new TaskLanePO(new TaskLane[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_FILETARGETCLOUD, result);
      
      return result;
   }

   public TaskLanePO createFileTargetCloud()
   {
      return this.startCreate().hasFileTargetCloud().endCreate();
   }

   public TaskPO hasFileTargetCloud(TaskLanePO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_FILETARGETCLOUD);
   }

   public TaskPO createFileTargetCloud(TaskLanePO tgt)
   {
      return this.startCreate().hasFileTargetCloud(tgt).endCreate();
   }

   public TaskLane getFileTargetCloud()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getFileTargetCloud();
      }
      return null;
   }

   public TaskPO filterState(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_STATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterState(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_STATE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterSpaceName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_SPACENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterSpaceName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_SPACENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterFileName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_FILENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterFileName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_FILENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterLastModified(long value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_LASTMODIFIED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterLastModified(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_LASTMODIFIED)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO filterLane()
   {
      TaskLanePO result = new TaskLanePO(new TaskLane[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_LANE, result);
      
      return result;
   }

   public TaskPO filterLane(TaskLanePO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_LANE);
   }

   public TaskLanePO filterFileTargetCloud()
   {
      TaskLanePO result = new TaskLanePO(new TaskLane[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_FILETARGETCLOUD, result);
      
      return result;
   }

   public TaskPO filterFileTargetCloud(TaskLanePO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_FILETARGETCLOUD);
   }

}
