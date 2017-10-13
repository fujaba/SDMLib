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

   public DoorPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public HousePO createHousePO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Door.PROPERTY_HOUSE, result);
      
      return result;
   }

   public HousePO createHousePO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(Door.PROPERTY_HOUSE, result);
      
      return result;
   }

   public DoorPO createHouseLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, Door.PROPERTY_HOUSE);
   }

   public DoorPO createHouseLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Door.PROPERTY_HOUSE, modifier);
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
