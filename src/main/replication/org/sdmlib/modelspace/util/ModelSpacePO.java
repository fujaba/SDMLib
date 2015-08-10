package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.ModelSpace;

public class ModelSpacePO extends PatternObject<ModelSpacePO, ModelSpace>
{

    public ModelSpaceSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ModelSpaceSet matches = new ModelSpaceSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ModelSpace) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ModelSpacePO(){
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ModelSpacePO(ModelSpace... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
