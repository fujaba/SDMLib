package org.sdmlib.examples.ludo.model.creators;

import org.sdmlib.models.pattern.PatternObject;
import java.awt.Point;
import org.sdmlib.examples.ludo.model.creators.PointSet;

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
   
}

