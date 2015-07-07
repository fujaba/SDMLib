package org.sdmlib.test.examples.studyright.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.studyright.model.Male;

public class MalePO extends PatternObject<MalePO, Male>
{

    public MaleSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MaleSet matches = new MaleSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Male) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MalePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MalePO(Male... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   //==========================================================================
   
   public void findMyPosition()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Male) getCurrentMatch()).findMyPosition();
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Male) getCurrentMatch()).findMyPosition(p0);
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0, int p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Male) getCurrentMatch()).findMyPosition(p0, p1);
      }
   }

}
