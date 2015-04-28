package org.sdmlib.examples.features.model.all.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.features.model.all.Door;
import org.sdmlib.examples.features.model.all.util.HousePO;
import org.sdmlib.examples.features.model.all.House;
import org.sdmlib.examples.features.model.all.util.DoorPO;

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
      newInstance(org.sdmlib.examples.features.model.all.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public DoorPO(Door... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.features.model.all.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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
