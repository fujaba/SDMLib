package org.sdmlib.test.examples.features.model.all.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.features.model.all.House;
import org.sdmlib.test.examples.features.model.all.Window;

public class WindowPO extends PatternObject<WindowPO, Window>
{

    public WindowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      WindowSet matches = new WindowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Window) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public WindowPO(){
      newInstance(org.sdmlib.test.examples.features.model.all.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public WindowPO(Window... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.features.model.all.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public HousePO hasHouse()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Window.PROPERTY_HOUSE, result);
      
      return result;
   }

   public HousePO createHouse()
   {
      return this.startCreate().hasHouse().endCreate();
   }

   public WindowPO hasHouse(HousePO tgt)
   {
      return hasLinkConstraint(tgt, Window.PROPERTY_HOUSE);
   }

   public WindowPO createHouse(HousePO tgt)
   {
      return this.startCreate().hasHouse(tgt).endCreate();
   }

   public House getHouse()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Window) this.getCurrentMatch()).getHouse();
      }
      return null;
   }

   public HousePO filterHouse()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Window.PROPERTY_HOUSE, result);
      
      return result;
   }

   public WindowPO filterHouse(HousePO tgt)
   {
      return hasLinkConstraint(tgt, Window.PROPERTY_HOUSE);
   }


   public WindowPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public HousePO createHousePO()
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Window.PROPERTY_HOUSE, result);
      
      return result;
   }

   public HousePO createHousePO(String modifier)
   {
      HousePO result = new HousePO(new House[]{});
      
      result.setModifier(modifier);
      super.hasLink(Window.PROPERTY_HOUSE, result);
      
      return result;
   }

   public WindowPO createHouseLink(HousePO tgt)
   {
      return hasLinkConstraint(tgt, Window.PROPERTY_HOUSE);
   }

   public WindowPO createHouseLink(HousePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Window.PROPERTY_HOUSE, modifier);
   }

}
