package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.taskflows.FetchFileFlow;
import org.sdmlib.model.taskflows.creators.FetchFileFlowSet;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.model.taskflows.creators.FetchFileFlowPO;

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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_FILESERVER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_FILENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(FetchFileFlow.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public FetchFileFlowPO hasSubFlow(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TaskFlow.PROPERTY_SUBFLOW)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public FetchFileFlowPO hasParent(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TaskFlow.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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

