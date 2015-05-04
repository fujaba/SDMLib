package org.sdmlib.examples.simpleModel.model.util;

import org.sdmlib.examples.simpleModel.model.Item;
import org.sdmlib.models.pattern.PatternObject;

public class ItemPO extends PatternObject<ItemPO, Item>
{

    public ItemSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ItemSet matches = new ItemSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Item) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ItemPO(){
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ItemPO(Item... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================   
   public void init()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Item) getCurrentMatch()).init();
      }
   }

}
