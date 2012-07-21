package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.pattern.PatternObject;

public class PatternLinkSet extends LinkedHashSet<PatternLink>
{
   public StringList getSrcRoleName()
   {
      StringList result = new StringList();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getSrcRoleName());
      }
      
      return result;
   }

   public StringList getTgtRoleName()
   {
      StringList result = new StringList();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getTgtRoleName());
      }
      
      return result;
   }

   public PatternObjectSet getTgt()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getTgt());
      }
      
      return result;
   }
   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getSrc());
      }
      
      return result;
   }
   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (PatternLink obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }

}


