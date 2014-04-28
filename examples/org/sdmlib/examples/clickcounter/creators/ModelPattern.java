package org.sdmlib.examples.clickcounter.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.clickcounter.creators.DataPO;
import org.sdmlib.examples.clickcounter.Data;
import org.sdmlib.examples.clickcounter.creators.IntegerPropertyPO;
import javafx.beans.property.IntegerProperty;

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

   public DataPO hasElementDataPO()
   {
      DataPO value = new DataPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());

      this.findMatch();

      return value;
   }

   public DataPO hasElementDataPO(Data hostGraphObject)
   {
      DataPO value = new DataPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);

      value.setCurrentMatch(hostGraphObject);

      this.findMatch();

      return value;
   }

   public IntegerPropertyPO hasElementIntegerPropertyPO()
   {
      IntegerPropertyPO value = new IntegerPropertyPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());

      this.findMatch();

      return value;
   }

   public IntegerPropertyPO hasElementIntegerPropertyPO(
         IntegerProperty hostGraphObject)
   {
      IntegerPropertyPO value = new IntegerPropertyPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);

      value.setCurrentMatch(hostGraphObject);

      this.findMatch();

      return value;
   }

}
