package org.sdmlib.test.examples.mancala.model.util;

import java.awt.Point;

import org.sdmlib.models.pattern.PatternObject;

public class PointPO extends PatternObject<PointPO, Point>
{

    public PointSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PointSet matches = new PointSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Point) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PointPO(){
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PointPO(Point... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
