package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.LogEntry;
import org.sdmlib.storyboards.MikadoLog;

public class MikadoLogPO extends PatternObject<MikadoLogPO, MikadoLog>
{

    public MikadoLogSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MikadoLogSet matches = new MikadoLogSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MikadoLog) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MikadoLogPO(){
      newInstance(null);
   }

   public MikadoLogPO(MikadoLog... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public MikadoLogPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LogEntryPO createEntriesPO()
   {
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MikadoLog.PROPERTY_ENTRIES, result);
      
      return result;
   }

   public LogEntryPO createEntriesPO(String modifier)
   {
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(modifier);
      super.hasLink(MikadoLog.PROPERTY_ENTRIES, result);
      
      return result;
   }

   public MikadoLogPO createEntriesLink(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, MikadoLog.PROPERTY_ENTRIES);
   }

   public MikadoLogPO createEntriesLink(LogEntryPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MikadoLog.PROPERTY_ENTRIES, modifier);
   }

   public LogEntrySet getEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MikadoLog) this.getCurrentMatch()).getEntries();
      }
      return null;
   }

   public GoalPO createMainGoalPO()
   {
      GoalPO result = new GoalPO(new Goal[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MikadoLog.PROPERTY_MAINGOAL, result);
      
      return result;
   }

   public GoalPO createMainGoalPO(String modifier)
   {
      GoalPO result = new GoalPO(new Goal[]{});
      
      result.setModifier(modifier);
      super.hasLink(MikadoLog.PROPERTY_MAINGOAL, result);
      
      return result;
   }

   public MikadoLogPO createMainGoalLink(GoalPO tgt)
   {
      return hasLinkConstraint(tgt, MikadoLog.PROPERTY_MAINGOAL);
   }

   public MikadoLogPO createMainGoalLink(GoalPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MikadoLog.PROPERTY_MAINGOAL, modifier);
   }

   public Goal getMainGoal()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MikadoLog) this.getCurrentMatch()).getMainGoal();
      }
      return null;
   }

}
