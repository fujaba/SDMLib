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
      newInstance(null);
   }

   public CubePO(Cube... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public CubePO(String modifier)
   {
      this.setModifier(modifier);
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
