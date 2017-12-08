package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UCargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UCargoSet;

public class UBankPO extends PatternObject<UBankPO, UBank>
{

    public UBankSet allMatches()
   {
      this.setDoAllMatches(true);
      
      UBankSet matches = new UBankSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((UBank) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public UBankPO(){
      newInstance(null);
   }

   public UBankPO(UBank... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public UBankPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public UBankPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(UBank.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UBankPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(UBank.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public UBankPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(UBank.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UBank) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public UBankPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((UBank) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public UCargoPO createCargosPO()
   {
      UCargoPO result = new UCargoPO(new UCargo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(UBank.PROPERTY_CARGOS, result);
      
      return result;
   }

   public UCargoPO createCargosPO(String modifier)
   {
      UCargoPO result = new UCargoPO(new UCargo[]{});
      
      result.setModifier(modifier);
      super.hasLink(UBank.PROPERTY_CARGOS, result);
      
      return result;
   }

   public UBankPO createCargosLink(UCargoPO tgt)
   {
      return hasLinkConstraint(tgt, UBank.PROPERTY_CARGOS);
   }

   public UBankPO createCargosLink(UCargoPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, UBank.PROPERTY_CARGOS, modifier);
   }

   public UCargoSet getCargos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((UBank) this.getCurrentMatch()).getCargos();
      }
      return null;
   }

}
