package org.sdmlib.examples.features.model.all.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.features.model.all.House;
import org.sdmlib.examples.features.model.all.util.DoorPO;
import org.sdmlib.examples.features.model.all.Door;
import org.sdmlib.examples.features.model.all.util.HousePO;
import org.sdmlib.examples.features.model.all.util.DoorSet;
import org.sdmlib.examples.features.model.all.util.WindowPO;
import org.sdmlib.examples.features.model.all.Window;
import org.sdmlib.examples.features.model.all.util.WindowSet;

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
      newInstance(org.sdmlib.examples.features.model.all.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public HousePO(House... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.features.model.all.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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
