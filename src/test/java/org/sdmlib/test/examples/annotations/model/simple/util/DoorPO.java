package org.sdmlib.test.examples.annotations.model.simple.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.annotations.model.simple.Door;
import org.sdmlib.test.examples.annotations.model.simple.util.HousePO;
import org.sdmlib.test.examples.annotations.model.simple.House;
import org.sdmlib.test.examples.annotations.model.simple.util.DoorPO;

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
      newInstance(null);
   }

   public DoorPO(Door... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public HousePO filterHouse()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Door.PROPERTY_HOUSE, result);
      
      return result;
   }

   public HousePO createHouse()
   {
      return this.startCreate().filterHouse().endCreate();
   }

   public DoorPO filterHouse(HousePO tgt)
   {
      return hasLinkConstraint(tgt, Door.PROPERTY_HOUSE);
   }

   public DoorPO createHouse(HousePO tgt)
   {
      return this.startCreate().filterHouse(tgt).endCreate();
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
