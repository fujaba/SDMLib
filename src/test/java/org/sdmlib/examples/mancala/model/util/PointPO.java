package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.models.pattern.PatternObject;
import java.awt.Point;

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
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PointPO(Point... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
