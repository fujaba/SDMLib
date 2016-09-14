package org.sdmlib.simple.model.association_c.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_c.Room;

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
      newInstance(null);
   }

   public RoomPO(Room... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public RoomPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
