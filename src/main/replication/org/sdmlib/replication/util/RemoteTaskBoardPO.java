package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.RemoteTaskBoard;

public class RemoteTaskBoardPO extends PatternObject<RemoteTaskBoardPO, RemoteTaskBoard>
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


   public RemoteTaskBoardPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public RemoteTaskBoardPO(RemoteTaskBoard... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public LanePO hasLanes()
   {
      LanePO result = new LanePO(new Lane[]{});
      
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

   public LanePO filterLanes()
   {
      LanePO result = new LanePO(new Lane[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(RemoteTaskBoard.PROPERTY_LANES, result);
      
      return result;
   }

   public RemoteTaskBoardPO filterLanes(LanePO tgt)
   {
      return hasLinkConstraint(tgt, RemoteTaskBoard.PROPERTY_LANES);
   }


   public RemoteTaskBoardPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LanePO createLanesPO()
   {
      LanePO result = new LanePO(new Lane[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(RemoteTaskBoard.PROPERTY_LANES, result);
      
      return result;
   }

   public LanePO createLanesPO(String modifier)
   {
      LanePO result = new LanePO(new Lane[]{});
      
      result.setModifier(modifier);
      super.hasLink(RemoteTaskBoard.PROPERTY_LANES, result);
      
      return result;
   }

   public RemoteTaskBoardPO createLanesLink(LanePO tgt)
   {
      return hasLinkConstraint(tgt, RemoteTaskBoard.PROPERTY_LANES);
   }

   public RemoteTaskBoardPO createLanesLink(LanePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, RemoteTaskBoard.PROPERTY_LANES, modifier);
   }

}
