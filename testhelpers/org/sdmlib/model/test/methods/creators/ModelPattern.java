package org.sdmlib.model.test.methods.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.model.test.methods.creators.PlacePO;
import org.sdmlib.model.test.methods.Place;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public PlacePO hasElementPlacePO()
   {
      PlacePO value = new PlacePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PlacePO hasElementPlacePO(Place hostGraphObject)
   {
      PlacePO value = new PlacePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


