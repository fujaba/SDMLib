package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.replication.creators.ReplicationChangeSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.ChangeHistoryPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.ReplicationChangePO;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.creators.LogEntryPO;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.creators.LogEntrySet;

public class ReplicationChangePO extends PatternObject<ReplicationChangePO, ReplicationChange>
{
   public ReplicationChangeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ReplicationChangeSet matches = new ReplicationChangeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ReplicationChange) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ReplicationChangePO hasTargetObjectId(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_TARGETOBJECTID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTargetObjectId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChange) getCurrentMatch()).getTargetObjectId();
      }
      return null;
   }
   
   public ReplicationChangePO withTargetObjectId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationChange) getCurrentMatch()).setTargetObjectId(value);
      }
      return this;
   }
   
   public ReplicationChangePO hasTargetProperty(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_TARGETPROPERTY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTargetProperty()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChange) getCurrentMatch()).getTargetProperty();
      }
      return null;
   }
   
   public ReplicationChangePO withTargetProperty(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationChange) getCurrentMatch()).setTargetProperty(value);
      }
      return this;
   }
   
   public ReplicationChangePO hasChangeMsg(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_CHANGEMSG)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getChangeMsg()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChange) getCurrentMatch()).getChangeMsg();
      }
      return null;
   }
   
   public ReplicationChangePO withChangeMsg(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationChange) getCurrentMatch()).setChangeMsg(value);
      }
      return this;
   }
   
   public ChangeHistoryPO hasHistory()
   {
      ChangeHistoryPO result = new ChangeHistoryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ReplicationChange.PROPERTY_HISTORY, result);
      
      return result;
   }

   public ReplicationChangePO hasHistory(ChangeHistoryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ReplicationChange.PROPERTY_HISTORY)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ChangeHistory getHistory()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChange) this.getCurrentMatch()).getHistory();
      }
      return null;
   }

   public ReplicationChangePO hasIsToManyProperty(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_ISTOMANYPROPERTY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getIsToManyProperty()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChange) getCurrentMatch()).getIsToManyProperty();
      }
      return false;
   }
   
   public ReplicationChangePO withIsToManyProperty(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationChange) getCurrentMatch()).setIsToManyProperty(value);
      }
      return this;
   }
   
   public ReplicationChangePO hasHistoryIdPrefix(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_HISTORYIDPREFIX)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getHistoryIdPrefix()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChange) getCurrentMatch()).getHistoryIdPrefix();
      }
      return null;
   }
   
   public ReplicationChangePO withHistoryIdPrefix(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationChange) getCurrentMatch()).setHistoryIdPrefix(value);
      }
      return this;
   }
   
   public ReplicationChangePO hasHistoryIdNumber(long value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_HISTORYIDNUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public long getHistoryIdNumber()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationChange) getCurrentMatch()).getHistoryIdNumber();
      }
      return 0;
   }
   
   public ReplicationChangePO withHistoryIdNumber(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationChange) getCurrentMatch()).setHistoryIdNumber(value);
      }
      return this;
   }
   
   public LogEntryPO hasLogEntries()
   {
      LogEntryPO result = new LogEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Task.PROPERTY_LOGENTRIES, result);
      
      return result;
   }

   public ReplicationChangePO hasLogEntries(LogEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Task.PROPERTY_LOGENTRIES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public LogEntrySet getLogEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getLogEntries();
      }
      return null;
   }

   public ReplicationChangePO hasHistoryIdPrefix(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_HISTORYIDPREFIX)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationChangePO createHistoryIdPrefix(String value)
   {
      this.startCreate().hasHistoryIdPrefix(value).endCreate();
      return this;
   }
   
   public ReplicationChangePO hasHistoryIdNumber(long lower, long upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_HISTORYIDNUMBER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationChangePO createHistoryIdNumber(long value)
   {
      this.startCreate().hasHistoryIdNumber(value).endCreate();
      return this;
   }
   
   public ReplicationChangePO hasTargetObjectId(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_TARGETOBJECTID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationChangePO createTargetObjectId(String value)
   {
      this.startCreate().hasTargetObjectId(value).endCreate();
      return this;
   }
   
   public ReplicationChangePO hasTargetProperty(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_TARGETPROPERTY)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationChangePO createTargetProperty(String value)
   {
      this.startCreate().hasTargetProperty(value).endCreate();
      return this;
   }
   
   public ReplicationChangePO createIsToManyProperty(boolean value)
   {
      this.startCreate().hasIsToManyProperty(value).endCreate();
      return this;
   }
   
   public ReplicationChangePO hasChangeMsg(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ReplicationChange.PROPERTY_CHANGEMSG)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationChangePO createChangeMsg(String value)
   {
      this.startCreate().hasChangeMsg(value).endCreate();
      return this;
   }
   
   public LogEntryPO createLogEntries()
   {
      return this.startCreate().hasLogEntries().endCreate();
   }

   public ReplicationChangePO createLogEntries(LogEntryPO tgt)
   {
      return this.startCreate().hasLogEntries(tgt).endCreate();
   }

   public ChangeHistoryPO createHistory()
   {
      return this.startCreate().hasHistory().endCreate();
   }

   public ReplicationChangePO createHistory(ChangeHistoryPO tgt)
   {
      return this.startCreate().hasHistory(tgt).endCreate();
   }

}





