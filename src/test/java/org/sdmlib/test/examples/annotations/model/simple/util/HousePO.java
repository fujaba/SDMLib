package org.sdmlib.test.examples.annotations.model.simple.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.annotations.model.simple.Door;
import org.sdmlib.test.examples.annotations.model.simple.House;
import org.sdmlib.test.examples.annotations.model.simple.Window;

public class HousePO extends PatternObject<HousePO, House>
{

    public HouseSet allMatches()
   {
      this.setDoAllMatches(true);
      
      HouseSet matches = new HouseSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((House) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public HousePO(){
      newInstance(null);
   }

   public HousePO(House... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   
   //==========================================================================
   
   public void init()
   {
      if (this.getPattern().getHasMatch())
      {
          ((House) getCurrentMatch()).init();
      }
   }

   public DoorPO filterDoors()
   {
      DoorPO result = new DoorPO(new Door[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_DOORS, result);
      
      return result;
   }

   public DoorPO createDoors()
   {
      return this.startCreate().filterDoors().endCreate();
   }

   public HousePO filterDoors(DoorPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_DOORS);
   }

   public HousePO createDoors(DoorPO tgt)
   {
      return this.startCreate().filterDoors(tgt).endCreate();
   }

   public DoorSet getDoors()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getDoors();
      }
      return null;
   }

   public WindowPO filterWindows()
   {
      WindowPO result = new WindowPO(new Window[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_WINDOWS, result);
      
      return result;
   }

   public WindowPO createWindows()
   {
      return this.startCreate().filterWindows().endCreate();
   }

   public HousePO filterWindows(WindowPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_WINDOWS);
   }

   public HousePO createWindows(WindowPO tgt)
   {
      return this.startCreate().filterWindows(tgt).endCreate();
   }

   public WindowSet getWindows()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getWindows();
      }
      return null;
   }

}
