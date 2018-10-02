package org.sdmlib.test.examples.features.model.all.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.features.model.all.Door;
import org.sdmlib.test.examples.features.model.all.House;
import org.sdmlib.test.examples.features.model.all.Window;

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
      newInstance(org.sdmlib.test.examples.features.model.all.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public HousePO(House... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.features.model.all.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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

   public DoorPO filterDoors()
   {
      DoorPO result = new DoorPO(new Door[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_DOORS, result);
      
      return result;
   }

   public HousePO filterDoors(DoorPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_DOORS);
   }

   public WindowPO filterWindows()
   {
      WindowPO result = new WindowPO(new Window[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(House.PROPERTY_WINDOWS, result);
      
      return result;
   }

   public HousePO filterWindows(WindowPO tgt)
   {
      return hasLinkConstraint(tgt, House.PROPERTY_WINDOWS);
   }


   public HousePO(String modifier)
   {
      this.setModifier(modifier);
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

}
