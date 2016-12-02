package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationChange;

public class ChangeHistoryPO extends PatternObject<ChangeHistoryPO, ChangeHistory>
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


   public ChangeHistoryPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChangeHistoryPO(ChangeHistory... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ReplicationChangePO hasChanges()
   {
      ReplicationChangePO result = new ReplicationChangePO(new ReplicationChange[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChangeHistory.PROPERTY_CHANGES, result);
      
      return result;
   }

   public ReplicationChangePO createChanges()
   {
      return this.startCreate().hasChanges().endCreate();
   }

   public ChangeHistoryPO hasChanges(ReplicationChangePO tgt)
   {
      return hasLinkConstraint(tgt, ChangeHistory.PROPERTY_CHANGES);
   }

   public ChangeHistoryPO createChanges(ReplicationChangePO tgt)
   {
      return this.startCreate().hasChanges(tgt).endCreate();
   }

   public ReplicationChangeSet getChanges()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeHistory) this.getCurrentMatch()).getChanges();
      }
      return null;
   }

   public ReplicationChangePO filterChanges()
   {
      ReplicationChangePO result = new ReplicationChangePO(new ReplicationChange[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChangeHistory.PROPERTY_CHANGES, result);
      
      return result;
   }

   public ChangeHistoryPO filterChanges(ReplicationChangePO tgt)
   {
      return hasLinkConstraint(tgt, ChangeHistory.PROPERTY_CHANGES);
   }

}
