package org.sdmlib.examples.studyrightWithAssignments.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyrightWithAssignments.model.Room;

public class RoomPO extends PatternObject<RoomPO, Room>
{

    public RoomSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RoomSet matches = new RoomSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Room) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public RoomPO(){
      newInstance(org.sdmlib.examples.studyrightWithAssignments.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public RoomPO(Room... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.studyrightWithAssignments.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void findPath(int String)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Room) getCurrentMatch()).findPath(String);
      }
   }

}
