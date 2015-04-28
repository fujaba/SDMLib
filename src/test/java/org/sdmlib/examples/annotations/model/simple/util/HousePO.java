package org.sdmlib.examples.annotations.model.simple.util;

import org.sdmlib.examples.annotations.model.simple.Door;
import org.sdmlib.examples.annotations.model.simple.House;
import org.sdmlib.examples.annotations.model.simple.Window;
import org.sdmlib.models.pattern.PatternObject;

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
      newInstance(org.sdmlib.examples.annotations.model.simple.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public HousePO(House... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.annotations.model.simple.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void init()
   {
      if (this.getPattern().getHasMatch())
      {
          ((House) getCurrentMatch()).init();
      }
   }

   public DoorPO hasDoors()
   {
      DoorPO result = new DoorPO(new Door[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_DOORS, result);
      
      return result;
   }

   public DoorPO createDoors()
   {
      return this.startCreate().hasDoors().endCreate();
   }

   public HousePO hasDoors(DoorPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_DOORS);
   }

   public HousePO createDoors(DoorPO tgt)
   {
      return this.startCreate().hasDoors(tgt).endCreate();
   }

   public DoorSet getDoors()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getDoors();
      }
      return null;
   }

   public WindowPO hasWindows()
   {
      WindowPO result = new WindowPO(new Window[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_WINDOWS, result);
      
      return result;
   }

   public WindowPO createWindows()
   {
      return this.startCreate().hasWindows().endCreate();
   }

   public HousePO hasWindows(WindowPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_WINDOWS);
   }

   public HousePO createWindows(WindowPO tgt)
   {
      return this.startCreate().hasWindows(tgt).endCreate();
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
