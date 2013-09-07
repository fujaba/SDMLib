package org.sdmlib.model.test.consistence.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.model.test.consistence.creators.FieldPO;
import org.sdmlib.model.test.consistence.Field;
import org.sdmlib.model.test.consistence.creators.BorderPO;
import org.sdmlib.model.test.consistence.Border;

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

   public FieldPO hasElementFieldPO()
   {
      FieldPO value = new FieldPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public FieldPO hasElementFieldPO(Field hostGraphObject)
   {
      FieldPO value = new FieldPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public BorderPO hasElementBorderPO()
   {
      BorderPO value = new BorderPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public BorderPO hasElementBorderPO(Border hostGraphObject)
   {
      BorderPO value = new BorderPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


