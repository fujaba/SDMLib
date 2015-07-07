package org.sdmlib.test.examples.annotations.model.simple.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.annotations.model.simple.Cube;

public class CubePO extends PatternObject<CubePO, Cube>
{

    public CubeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CubeSet matches = new CubeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Cube) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public CubePO(){
      newInstance(org.sdmlib.test.examples.annotations.model.simple.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public CubePO(Cube... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.annotations.model.simple.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void init()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Cube) getCurrentMatch()).init();
      }
   }

}
