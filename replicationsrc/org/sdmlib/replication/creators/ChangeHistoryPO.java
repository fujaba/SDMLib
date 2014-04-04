package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.creators.ChangeHistorySet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.ReplicationChangePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.ChangeHistoryPO;
import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.replication.creators.ReplicationChangeSet;

public class ChangeHistoryPO extends
      PatternObject<ChangeHistoryPO, ChangeHistory>
{
   public ChangeHistorySet allMatches()
   {
      this.setDoAllMatches(true);

      ChangeHistorySet matches = new ChangeHistorySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChangeHistory) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public ReplicationChangePO hasChanges()
   {
      ReplicationChangePO result = new ReplicationChangePO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(ChangeHistory.PROPERTY_CHANGES, result);

      return result;
   }

   public ChangeHistoryPO hasChanges(ReplicationChangePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(ChangeHistory.PROPERTY_CHANGES)
         .withSrc(this).withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public ReplicationChangeSet getChanges()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeHistory) this.getCurrentMatch()).getChanges();
      }
      return null;
   }

   public ReplicationChangePO createChanges()
   {
      return this.startCreate().hasChanges().endCreate();
   }

   public ChangeHistoryPO createChanges(ReplicationChangePO tgt)
   {
      return this.startCreate().hasChanges(tgt).endCreate();
   }

}
