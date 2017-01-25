package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.PatternObject;
import java.lang.Object;

public class ObjectPO extends PatternObject<ObjectPO, Object>
{

    public ObjectSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ObjectSet matches = new ObjectSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Object) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ObjectPO(){
      newInstance(null);
   }

   public ObjectPO(Object... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public ObjectPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
