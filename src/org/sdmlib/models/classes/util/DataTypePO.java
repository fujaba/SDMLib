package org.sdmlib.models.classes.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.util.DataTypeSet;

public class DataTypePO extends PatternObject<DataTypePO, DataType>
{

    public DataTypeSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DataTypeSet matches = new DataTypeSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((DataType) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public DataTypePO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public DataTypePO(DataType... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
}
