package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.model.classes.ReverseClassModelTest;
import org.sdmlib.model.classes.creators.ReverseClassModelTestPO;
import org.sdmlib.model.test.superclasses.Continent;
import org.sdmlib.model.test.superclasses.State;
import org.sdmlib.model.test.superclasses.Town;
import org.sdmlib.models.pattern.Pattern;

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

   public ContinentPO hasElementContinentPO()
   {
      ContinentPO value = new ContinentPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ContinentPO hasElementContinentPO(Continent hostGraphObject)
   {
      ContinentPO value = new ContinentPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StatePO hasElementStatePO()
   {
      StatePO value = new StatePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StatePO hasElementStatePO(State hostGraphObject)
   {
      StatePO value = new StatePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public TownPO hasElementTownPO()
   {
      TownPO value = new TownPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TownPO hasElementTownPO(Town hostGraphObject)
   {
      TownPO value = new TownPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ReverseClassModelTestPO hasElementReverseClassModelTestPO()
   {
      ReverseClassModelTestPO value = new ReverseClassModelTestPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ReverseClassModelTestPO hasElementReverseClassModelTestPO(ReverseClassModelTest hostGraphObject)
   {
      ReverseClassModelTestPO value = new ReverseClassModelTestPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


