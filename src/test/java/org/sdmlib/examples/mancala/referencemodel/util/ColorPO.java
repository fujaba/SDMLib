package org.sdmlib.examples.mancala.referencemodel.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.mancala.referencemodel.Color;

public class ColorPO extends PatternObject<ColorPO, Color>
{

    public ColorSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ColorSet matches = new ColorSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Color) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ColorPO(){
      newInstance(org.sdmlib.examples.mancala.referencemodel.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ColorPO(Color... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.mancala.referencemodel.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
