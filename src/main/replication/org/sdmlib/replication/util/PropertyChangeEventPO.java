package org.sdmlib.replication.util;

import java.beans.PropertyChangeEvent;

import org.sdmlib.models.pattern.PatternObject;

public class PropertyChangeEventPO extends PatternObject<PropertyChangeEventPO, PropertyChangeEvent>
{

    public PropertyChangeEventSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PropertyChangeEventSet matches = new PropertyChangeEventSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PropertyChangeEvent) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PropertyChangeEventPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PropertyChangeEventPO(PropertyChangeEvent... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }

   public PropertyChangeEventPO(String modifier)
   {
      this.setModifier(modifier);
   }
}
