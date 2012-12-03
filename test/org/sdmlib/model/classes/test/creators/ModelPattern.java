package org.sdmlib.model.classes.test.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.model.classes.test.creators.NoPropertiesPO;
import org.sdmlib.model.classes.test.NoProperties;
import org.sdmlib.model.classes.test.creators.ParentPO;
import org.sdmlib.model.classes.test.Parent;
import org.sdmlib.model.classes.test.creators.UnclePO;
import org.sdmlib.model.classes.test.Uncle;
import org.sdmlib.model.classes.test.creators.KidPO;
import org.sdmlib.model.classes.test.Kid;

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

   public ParentPO hasElementParentPO()
   {
      ParentPO value = new ParentPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ParentPO hasElementParentPO(Parent hostGraphObject)
   {
      ParentPO value = new ParentPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public UnclePO hasElementUnclePO()
   {
      UnclePO value = new UnclePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public UnclePO hasElementUnclePO(Uncle hostGraphObject)
   {
      UnclePO value = new UnclePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public KidPO hasElementKidPO()
   {
      KidPO value = new KidPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public KidPO hasElementKidPO(Kid hostGraphObject)
   {
      KidPO value = new KidPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


