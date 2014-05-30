package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;

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


   public TemplatePO hasElementTemplatePO()
   {
      TemplatePO value = new TemplatePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TemplatePO hasElementTemplatePO(Template hostGraphObject)
   {
      TemplatePO value = new TemplatePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PlaceHolderDescriptionPO hasElementPlaceHolderDescriptionPO()
   {
      PlaceHolderDescriptionPO value = new PlaceHolderDescriptionPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PlaceHolderDescriptionPO hasElementPlaceHolderDescriptionPO(PlaceHolderDescription hostGraphObject)
   {
      PlaceHolderDescriptionPO value = new PlaceHolderDescriptionPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   }

   public ChoiceTemplatePO hasElementChoiceTemplatePO()
   {
      ChoiceTemplatePO value = new ChoiceTemplatePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ChoiceTemplatePO hasElementChoiceTemplatePO(ChoiceTemplate hostGraphObject)
   {
      ChoiceTemplatePO value = new ChoiceTemplatePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public MatchPO hasElementMatchPO()
   {
      MatchPO value = new MatchPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public MatchPO hasElementMatchPO(Match hostGraphObject)
   {
      MatchPO value = new MatchPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}





