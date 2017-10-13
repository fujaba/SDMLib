package org.sdmlib.test.examples.annotations.model.simple.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.annotations.model.simple.House;
import org.sdmlib.test.examples.annotations.model.simple.util.DoorPO;
import org.sdmlib.test.examples.annotations.model.simple.Door;
import org.sdmlib.test.examples.annotations.model.simple.util.HousePO;
import org.sdmlib.test.examples.annotations.model.simple.util.DoorSet;
import org.sdmlib.test.examples.annotations.model.simple.util.WindowPO;
import org.sdmlib.test.examples.annotations.model.simple.Window;
import org.sdmlib.test.examples.annotations.model.simple.util.WindowSet;

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

   public HousePO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void init()
   {
      if (this.getPattern().getHasMatch())
      {
          ((House) getCurrentMatch()).init();
      }
   }

   public DoorPO createDoorsPO()
   {
      DoorPO result = new DoorPO(new Door[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_DOORS, result);
      
      return result;
   }

   public DoorPO createDoorsPO(String modifier)
   {
      DoorPO result = new DoorPO(new Door[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_DOORS, result);
      
      return result;
   }

   public HousePO createDoorsLink(DoorPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_DOORS);
   }

   public HousePO createDoorsLink(DoorPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_DOORS, modifier);
   }

   public DoorSet getDoors()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((House) this.getCurrentMatch()).getDoors();
      }
      return null;
   }

   public WindowPO createWindowsPO()
   {
      WindowPO result = new WindowPO(new Window[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_WINDOWS, result);
      
      return result;
   }

   public WindowPO createWindowsPO(String modifier)
   {
      WindowPO result = new WindowPO(new Window[]{});
      
      result.setModifier(modifier);
      super.hasLink(House.PROPERTY_WINDOWS, result);
      
      return result;
   }

   public HousePO createWindowsLink(WindowPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_WINDOWS);
   }

   public HousePO createWindowsLink(WindowPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_WINDOWS, modifier);
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
