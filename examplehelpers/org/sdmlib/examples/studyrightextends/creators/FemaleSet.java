package org.sdmlib.examples.studyrightextends.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.studyrightextends.Female;
import org.sdmlib.examples.studyrightextends.Person;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;

public class FemaleSet extends LinkedHashSet<Female>
{
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Female obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   
   //==========================================================================
   
   public FemaleSet findMyPosition()
   {
      for (Person obj : this)
      {
         obj.findMyPosition();
      }
      return this;
   }

   
   //==========================================================================
   
   public FemaleSet findMyPosition(String p0)
   {
      for (Person obj : this)
      {
         obj.findMyPosition( p0);
      }
      return this;
   }

   
   //==========================================================================
   
   public FemaleSet findMyPosition(String p0, int p1)
   {
      for (Person obj : this)
      {
         obj.findMyPosition( p0,  p1);
      }
      return this;
   }

   public FemaleSet withName(String value)
   {
      for (Female obj : this)
      {
         obj.withName(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Female elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public FemaleSet with(Female value)
   {
      this.add(value);
      return this;
   }
   
   public FemaleSet without(Female value)
   {
      this.remove(value);
      return this;
   }
}



