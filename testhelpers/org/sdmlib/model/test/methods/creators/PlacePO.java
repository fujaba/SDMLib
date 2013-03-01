package org.sdmlib.model.test.methods.creators;

import org.sdmlib.model.test.methods.Place;
import org.sdmlib.models.pattern.PatternObject;

public class PlacePO extends PatternObject
{
   
   //==========================================================================
   
   public void findMyPosition()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Place) getCurrentMatch()).findMyPosition();
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Place) getCurrentMatch()).findMyPosition( p0);
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0, int p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Place) getCurrentMatch()).findMyPosition( p0,  p1);
      }
   }

}

