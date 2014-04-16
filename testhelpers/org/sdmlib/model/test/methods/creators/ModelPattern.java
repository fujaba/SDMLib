package org.sdmlib.model.test.methods.creators;

import org.sdmlib.model.test.methods.Place;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.utils.creators.PropertyChangeInterfacePO;
import org.sdmlib.serialization.util.PropertyChangeInterface;
import org.sdmlib.model.test.methods.creators.PlacePO;

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

   public PropertyChangeInterfacePO hasElementPropertyChangeInterfacePO()
   {
      PropertyChangeInterfacePO value = new PropertyChangeInterfacePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PropertyChangeInterfacePO hasElementPropertyChangeInterfacePO(PropertyChangeInterface hostGraphObject)
   {
      PropertyChangeInterfacePO value = new PropertyChangeInterfacePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}



