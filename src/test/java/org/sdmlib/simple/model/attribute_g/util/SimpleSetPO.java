package org.sdmlib.simple.model.attribute_g.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.networkparser.list.SimpleSet;

public class SimpleSetPO extends PatternObject<SimpleSetPO, SimpleSet>
{

    public SimpleSetSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SimpleSetSet matches = new SimpleSetSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SimpleSet) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SimpleSetPO(){
      newInstance(null);
   }

   public SimpleSetPO(SimpleSet... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public SimpleSetPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
