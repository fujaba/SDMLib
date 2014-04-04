package org.sdmlib.examples.adamandeve.creators;

import org.sdmlib.examples.adamandeve.UpdateAdamFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.examples.adamandeve.creators.UpdateAdamFlowSet;

public class UpdateAdamFlowPO extends
      PatternObject<UpdateAdamFlowPO, UpdateAdamFlow>
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

   // ==========================================================================

   public void run()
   {
      if (this.getPattern().getHasMatch())
      {
         ((UpdateAdamFlow) getCurrentMatch()).run();
      }
   }

   public UpdateAdamFlowPO hasAdam(PeerProxy value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_ADAM).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

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

   public UpdateAdamFlowPO hasEve(PeerProxy value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_EVE).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

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

   public UpdateAdamFlowPO hasIdMap(JsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_IDMAP).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public JsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UpdateAdamFlow) getCurrentMatch()).getIdMap();
      }
      return null;
   }

   public UpdateAdamFlowPO hasAdamJarAtEveSiteLastModified(long value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_ADAMJARATEVESITELASTMODIFIED)
         .withTgtValue(value).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public long getAdamJarAtEveSiteLastModified()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UpdateAdamFlow) getCurrentMatch())
            .getAdamJarAtEveSiteLastModified();
      }
      return 0;
   }

   public UpdateAdamFlowPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_TASKNO).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

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

   public UpdateAdamFlowPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_IDMAP).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public UpdateAdamFlowPO hasAdam(PeerProxy lower, PeerProxy upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_ADAM).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public UpdateAdamFlowPO hasEve(PeerProxy lower, PeerProxy upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_EVE).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public UpdateAdamFlowPO hasIdMap(SDMLibJsonIdMap lower, SDMLibJsonIdMap upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_IDMAP).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public UpdateAdamFlowPO hasAdamJarAtEveSiteLastModified(long lower,
         long upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_ADAMJARATEVESITELASTMODIFIED)
         .withTgtValue(lower).withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public UpdateAdamFlowPO hasTaskNo(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(UpdateAdamFlow.PROPERTY_TASKNO).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
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

   public UpdateAdamFlowPO createEve(PeerProxy value)
   {
      this.startCreate().hasEve(value).endCreate();
      return this;
   }

   public UpdateAdamFlowPO createIdMap(SDMLibJsonIdMap value)
   {
      this.startCreate().hasIdMap(value).endCreate();
      return this;
   }

   public UpdateAdamFlowPO createAdamJarAtEveSiteLastModified(long value)
   {
      this.startCreate().hasAdamJarAtEveSiteLastModified(value).endCreate();
      return this;
   }

   public UpdateAdamFlowPO createTaskNo(int value)
   {
      this.startCreate().hasTaskNo(value).endCreate();
      return this;
   }

}
