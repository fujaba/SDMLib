package org.sdmlib.examples.studyright.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.studyright.model.Male;
import org.sdmlib.examples.studyright.model.util.MaleSet;
import org.sdmlib.models.pattern.Pattern;

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
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
   }

   public MalePO(Male... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
          return;
      }
      Pattern<Object> pattern = new Pattern<Object>(CreatorCreator.createIdMap("PatternObjectType"));
      pattern.addToElements(this);
      if(hostGraphObject.length>1){
           this.withCandidates(hostGraphObject);
      } else {
           this.withCandidates(hostGraphObject[0]);
      }
      pattern.findMatch();
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

