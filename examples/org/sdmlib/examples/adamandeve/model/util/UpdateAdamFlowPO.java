package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.adamandeve.model.UpdateAdamFlow;
import org.sdmlib.examples.adamandeve.model.util.UpdateAdamFlowSet;
import org.sdmlib.logger.PeerProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class UpdateAdamFlowPO extends PatternObject<UpdateAdamFlowPO, UpdateAdamFlow>
{

    public UpdateAdamFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      UpdateAdamFlowSet matches = new UpdateAdamFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((UpdateAdamFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   
   //==========================================================================
   
   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
          ((UpdateAdamFlow) getCurrentMatch()).run();
      }
   }

   
   //==========================================================================
   
   public Object getTaskNames()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UpdateAdamFlow) getCurrentMatch()).getTaskNames();
      }
      return null;
   }

   public UpdateAdamFlowPO hasAdam(PeerProxy value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UpdateAdamFlow.PROPERTY_ADAM)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UpdateAdamFlowPO createAdam(PeerProxy value)
   {
      this.startCreate().hasAdam(value).endCreate();
      return this;
   }
   
   public PeerProxy getAdam()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UpdateAdamFlow) getCurrentMatch()).getAdam();
      }
      return null;
   }
   
   public UpdateAdamFlowPO withAdam(PeerProxy value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UpdateAdamFlow) getCurrentMatch()).setAdam(value);
      }
      return this;
   }
   
   public UpdateAdamFlowPO hasEve(PeerProxy value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UpdateAdamFlow.PROPERTY_EVE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UpdateAdamFlowPO createEve(PeerProxy value)
   {
      this.startCreate().hasEve(value).endCreate();
      return this;
   }
   
   public PeerProxy getEve()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UpdateAdamFlow) getCurrentMatch()).getEve();
      }
      return null;
   }
   
   public UpdateAdamFlowPO withEve(PeerProxy value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UpdateAdamFlow) getCurrentMatch()).setEve(value);
      }
      return this;
   }
   
   public UpdateAdamFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UpdateAdamFlow.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UpdateAdamFlowPO createIdMap(SDMLibJsonIdMap value)
   {
      this.startCreate().hasIdMap(value).endCreate();
      return this;
   }
   
   public SDMLibJsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UpdateAdamFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public UpdateAdamFlowPO withIdMap(SDMLibJsonIdMap value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UpdateAdamFlow) getCurrentMatch()).setIdMap(value);
      }
      return this;
   }
   
   public UpdateAdamFlowPO hasAdamJarAtEveSiteLastModified(long value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UpdateAdamFlow.PROPERTY_ADAMJARATEVESITELASTMODIFIED)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UpdateAdamFlowPO hasAdamJarAtEveSiteLastModified(long lower, long upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UpdateAdamFlow.PROPERTY_ADAMJARATEVESITELASTMODIFIED)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UpdateAdamFlowPO createAdamJarAtEveSiteLastModified(long value)
   {
      this.startCreate().hasAdamJarAtEveSiteLastModified(value).endCreate();
      return this;
   }
   
   public long getAdamJarAtEveSiteLastModified()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UpdateAdamFlow) getCurrentMatch()).getAdamJarAtEveSiteLastModified();
      }
      return 0;
   }
   
   public UpdateAdamFlowPO withAdamJarAtEveSiteLastModified(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UpdateAdamFlow) getCurrentMatch()).setAdamJarAtEveSiteLastModified(value);
      }
      return this;
   }
   
   public UpdateAdamFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UpdateAdamFlow.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UpdateAdamFlowPO hasTaskNo(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(UpdateAdamFlow.PROPERTY_TASKNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public UpdateAdamFlowPO createTaskNo(int value)
   {
      this.startCreate().hasTaskNo(value).endCreate();
      return this;
   }
   
   public int getTaskNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UpdateAdamFlow) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public UpdateAdamFlowPO withTaskNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UpdateAdamFlow) getCurrentMatch()).setTaskNo(value);
      }
      return this;
   }
   
}

