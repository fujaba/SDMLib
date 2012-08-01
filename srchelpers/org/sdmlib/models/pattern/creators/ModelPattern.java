package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.creators.PatternElementPO;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.creators.PatternPO;
import org.sdmlib.models.pattern.creators.NegativeApplicationConditionPO;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.creators.PatternObjectPO;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.creators.PatternLinkPO;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.AttributeConstraintPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.creators.LinkConstraintPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.MatchIsomorphicConstraintPO;
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;

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

   public PatternElementPO hasElementPatternElementPO()
   {
      PatternElementPO value = new PatternElementPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PatternElementPO hasElementPatternElementPO(PatternElement hostGraphObject)
   {
      PatternElementPO value = new PatternElementPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PatternPO hasElementPatternPO()
   {
      PatternPO value = new PatternPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PatternPO hasElementPatternPO(Pattern hostGraphObject)
   {
      PatternPO value = new PatternPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public NegativeApplicationConditionPO hasElementNegativeApplicationConditionPO()
   {
      NegativeApplicationConditionPO value = new NegativeApplicationConditionPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public NegativeApplicationConditionPO hasElementNegativeApplicationConditionPO(NegativeApplicationCondition hostGraphObject)
   {
      NegativeApplicationConditionPO value = new NegativeApplicationConditionPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PatternObjectPO hasElementPatternObjectPO()
   {
      PatternObjectPO value = new PatternObjectPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PatternObjectPO hasElementPatternObjectPO(PatternObject hostGraphObject)
   {
      PatternObjectPO value = new PatternObjectPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PatternLinkPO hasElementPatternLinkPO()
   {
      PatternLinkPO value = new PatternLinkPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PatternLinkPO hasElementPatternLinkPO(PatternLink hostGraphObject)
   {
      PatternLinkPO value = new PatternLinkPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public AttributeConstraintPO hasElementAttributeConstraintPO()
   {
      AttributeConstraintPO value = new AttributeConstraintPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public AttributeConstraintPO hasElementAttributeConstraintPO(AttributeConstraint hostGraphObject)
   {
      AttributeConstraintPO value = new AttributeConstraintPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public LinkConstraintPO hasElementLinkConstraintPO()
   {
      LinkConstraintPO value = new LinkConstraintPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LinkConstraintPO hasElementLinkConstraintPO(LinkConstraint hostGraphObject)
   {
      LinkConstraintPO value = new LinkConstraintPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public MatchIsomorphicConstraintPO hasElementMatchIsomorphicConstraintPO()
   {
      MatchIsomorphicConstraintPO value = new MatchIsomorphicConstraintPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public MatchIsomorphicConstraintPO hasElementMatchIsomorphicConstraintPO(MatchIsomorphicConstraint hostGraphObject)
   {
      MatchIsomorphicConstraintPO value = new MatchIsomorphicConstraintPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


