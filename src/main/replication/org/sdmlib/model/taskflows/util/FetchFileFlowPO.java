package org.sdmlib.model.taskflows.util;

import org.sdmlib.model.taskflows.FetchFileFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class FetchFileFlowPO extends PatternObject<FetchFileFlowPO, FetchFileFlow>
{

    public FetchFileFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FetchFileFlowSet matches = new FetchFileFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((FetchFileFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FetchFileFlowPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public FetchFileFlowPO(FetchFileFlow... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((FetchFileFlow) getCurrentMatch()).run();
      }
   }

   public FetchFileFlowPO hasFileServer(PeerProxy value)
   {
      new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_FILESERVER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FetchFileFlowPO createFileServer(PeerProxy value)
   {
      this.startCreate().hasFileServer(value).endCreate();
      return this;
   }
   
   public PeerProxy getFileServer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FetchFileFlow) getCurrentMatch()).getFileServer();
      }
      return null;
   }
   
   public FetchFileFlowPO withFileServer(PeerProxy value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((FetchFileFlow) getCurrentMatch()).setFileServer(value);
      }
      return this;
   }
   
   public FetchFileFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FetchFileFlowPO createIdMap(SDMLibJsonIdMap value)
   {
      this.startCreate().hasIdMap(value).endCreate();
      return this;
   }
   
   public SDMLibJsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FetchFileFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public FetchFileFlowPO withIdMap(SDMLibJsonIdMap value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((FetchFileFlow) getCurrentMatch()).setIdMap(value);
      }
      return this;
   }
   
   public FetchFileFlowPO hasFileName(String value)
   {
      new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_FILENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FetchFileFlowPO hasFileName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_FILENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FetchFileFlowPO createFileName(String value)
   {
      this.startCreate().hasFileName(value).endCreate();
      return this;
   }
   
   public String getFileName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FetchFileFlow) getCurrentMatch()).getFileName();
      }
      return null;
   }
   
   public FetchFileFlowPO withFileName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((FetchFileFlow) getCurrentMatch()).setFileName(value);
      }
      return this;
   }
   
   public FetchFileFlowPO hasTaskNo(int value)
   {
      new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FetchFileFlowPO hasTaskNo(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_TASKNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FetchFileFlowPO createTaskNo(int value)
   {
      this.startCreate().hasTaskNo(value).endCreate();
      return this;
   }
   
   public int getTaskNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((FetchFileFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public FetchFileFlowPO withTaskNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((FetchFileFlow) getCurrentMatch()).setTaskNo(value);
      }
      return this;
   }
   
   public TaskFlowPO hasSubFlow()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public TaskFlowPO createSubFlow()
   {
      return this.startCreate().hasSubFlow().endCreate();
   }

   public FetchFileFlowPO hasSubFlow(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_SUBFLOW);
   }

   public FetchFileFlowPO createSubFlow(TaskFlowPO tgt)
   {
      return this.startCreate().hasSubFlow(tgt).endCreate();
   }

   public TaskFlow getSubFlow()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getSubFlow();
      }
      return null;
   }

   public TaskFlowPO hasParent()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public TaskFlowPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public FetchFileFlowPO hasParent(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_PARENT);
   }

   public FetchFileFlowPO createParent(TaskFlowPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

   public TaskFlow getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getParent();
      }
      return null;
   }

}
