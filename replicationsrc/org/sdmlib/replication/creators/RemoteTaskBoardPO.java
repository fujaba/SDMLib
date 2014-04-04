package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.RemoteTaskBoard;
import org.sdmlib.replication.creators.RemoteTaskBoardSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.LanePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.RemoteTaskBoardPO;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.creators.LaneSet;

public class RemoteTaskBoardPO extends
      PatternObject<RemoteTaskBoardPO, RemoteTaskBoard>
{
   public RemoteTaskBoardSet allMatches()
   {
      this.setDoAllMatches(true);

      RemoteTaskBoardSet matches = new RemoteTaskBoardSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((RemoteTaskBoard) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public LanePO hasLanes()
   {
      LanePO result = new LanePO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(RemoteTaskBoard.PROPERTY_LANES, result);

      return result;
   }

   public LanePO createLanes()
   {
      return this.startCreate().hasLanes().endCreate();
   }

   public RemoteTaskBoardPO hasLanes(LanePO tgt)
   {
      return hasLinkConstraint(tgt, RemoteTaskBoard.PROPERTY_LANES);
   }

   public RemoteTaskBoardPO createLanes(LanePO tgt)
   {
      return this.startCreate().hasLanes(tgt).endCreate();
   }

   public LaneSet getLanes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((RemoteTaskBoard) this.getCurrentMatch()).getLanes();
      }
      return null;
   }

}
