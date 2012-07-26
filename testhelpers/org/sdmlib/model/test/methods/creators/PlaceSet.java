package org.sdmlib.model.test.methods.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.test.methods.Place;

public class PlaceSet extends LinkedHashSet<Place>
{
   
   //==========================================================================
   
   public PlaceSet findMyPosition()
   {
      for (Place obj : this)
      {
         obj.findMyPosition();
      }
      return this;
   }

   
   //==========================================================================
   
   public PlaceSet findMyPosition(String p0)
   {
      for (Place obj : this)
      {
         obj.findMyPosition( p0);
      }
      return this;
   }

   
   //==========================================================================
   
   public PlaceSet findMyPosition(String p0, int p1)
   {
      for (Place obj : this)
      {
         obj.findMyPosition( p0,  p1);
      }
      return this;
   }

}


