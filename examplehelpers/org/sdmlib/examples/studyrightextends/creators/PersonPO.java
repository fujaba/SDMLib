package org.sdmlib.examples.studyrightextends.creators;

import org.sdmlib.examples.studyrightextends.Person;
import org.sdmlib.models.pattern.PatternObject;

public class PersonPO extends PatternObject
{
   
   //==========================================================================
   
   public void findMyPosition()
   {
      if (this.getPattern().getHasMatch())
      {
          ((Person) getCurrentMatch()).findMyPosition();
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Person) getCurrentMatch()).findMyPosition( p0);
      }
   }

   
   //==========================================================================
   
   public void findMyPosition(String p0, int p1)
   {
      if (this.getPattern().getHasMatch())
      {
          ((Person) getCurrentMatch()).findMyPosition( p0,  p1);
      }
   }

}


