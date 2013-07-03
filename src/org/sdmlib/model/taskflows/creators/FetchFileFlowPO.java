package org.sdmlib.model.taskflows.creators;

import org.sdmlib.model.taskflows.FetchFileFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

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
   
}


