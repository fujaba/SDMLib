package org.sdmlib.simple.model.attribute_h.util;

import org.sdmlib.models.pattern.PatternObject;
import de.uniks.networkparser.list.SimpleKeyValueList;

public class SimpleKeyValueListPO extends PatternObject<SimpleKeyValueListPO, SimpleKeyValueList>
{

    public SimpleKeyValueListSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SimpleKeyValueListSet matches = new SimpleKeyValueListSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SimpleKeyValueList) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SimpleKeyValueListPO(){
      newInstance(null);
   }

   public SimpleKeyValueListPO(SimpleKeyValueList... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
}
