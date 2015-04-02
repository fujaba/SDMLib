package org.sdmlib.examples.annotations.model.simple.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.annotations.model.simple.Door;
import org.sdmlib.examples.annotations.model.simple.util.HousePO;
import org.sdmlib.examples.annotations.model.simple.House;
import org.sdmlib.examples.annotations.model.simple.util.DoorPO;

public class DoorPO extends PatternObject<DoorPO, Door>
{

    public DoorSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DoorSet matches = new DoorSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Door) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public DoorPO(){
      newInstance(org.sdmlib.examples.annotations.model.simple.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public DoorPO(Door... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.annotations.model.simple.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public HousePO hasHouse()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Door.PROPERTY_HOUSE, result);
      
      return result;
   }

   public HousePO createHouse()
   {
      return this.startCreate().hasHouse().endCreate();
   }

   public DoorPO hasHouse(HousePO tgt)
   {
      return hasLinkConstraint(tgt, Door.PROPERTY_HOUSE);
   }

   public DoorPO createHouse(HousePO tgt)
   {
      return this.startCreate().hasHouse(tgt).endCreate();
   }

   public House getHouse()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Door) this.getCurrentMatch()).getHouse();
      }
      return null;
   }

}
