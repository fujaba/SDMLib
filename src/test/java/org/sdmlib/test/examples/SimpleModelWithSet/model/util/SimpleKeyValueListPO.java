package org.sdmlib.test.examples.SimpleModelWithSet.model.util;

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
      newInstance(org.sdmlib.test.examples.SimpleModelWithSet.model.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SimpleKeyValueListPO(SimpleKeyValueList... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.SimpleModelWithSet.model.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
