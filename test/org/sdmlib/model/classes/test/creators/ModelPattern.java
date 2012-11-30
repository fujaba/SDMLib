package org.sdmlib.model.classes.test.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.model.classes.test.creators.NoPropertiesPO;
import org.sdmlib.model.classes.test.NoProperties;

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

   public NoPropertiesPO hasElementNoPropertiesPO()
   {
      NoPropertiesPO value = new NoPropertiesPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public NoPropertiesPO hasElementNoPropertiesPO(NoProperties hostGraphObject)
   {
      NoPropertiesPO value = new NoPropertiesPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


