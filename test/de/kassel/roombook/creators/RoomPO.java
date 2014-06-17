package de.kassel.roombook.creators;

import org.sdmlib.models.pattern.PatternObject;
import de.kassel.roombook.Room;
import de.kassel.roombook.creators.RoomSet;
import org.sdmlib.models.pattern.PatternLink;
import de.kassel.roombook.creators.FloorPO;
import org.sdmlib.models.pattern.LinkConstraint;
import de.kassel.roombook.creators.RoomPO;
import de.kassel.roombook.Floor;

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
   
   public FloorPO hasFloor()
   {
      FloorPO result = new FloorPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Room.PROPERTY_FLOOR, result);
      
      return result;
   }

   public FloorPO createFloor()
   {
      return this.startCreate().hasFloor().endCreate();
   }

   public RoomPO hasFloor(FloorPO tgt)
   {
      return hasLinkConstraint(tgt, Room.PROPERTY_FLOOR);
   }

   public RoomPO createFloor(FloorPO tgt)
   {
      return this.startCreate().hasFloor(tgt).endCreate();
   }

   public Floor getFloor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Room) this.getCurrentMatch()).getFloor();
      }
      return null;
   }

}

