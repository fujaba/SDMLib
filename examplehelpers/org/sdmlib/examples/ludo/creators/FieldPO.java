package org.sdmlib.examples.ludo.creators;

import org.sdmlib.examples.ludo.Dice;
import org.sdmlib.examples.ludo.Field;
import org.sdmlib.examples.ludo.Pawn;
import org.sdmlib.examples.ludo.Player;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;

public class FieldPO extends PatternObject
{

   public FieldPO hasKind(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Field.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;

   }

   public FieldPO startNAC()
   {
      NegativeApplicationCondition nac = new NegativeApplicationCondition();
      
      this.getPattern().addToElements(nac);

      return this;
   }

   public PawnPO hasPawns()
   {
      PawnPO result = new PawnPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(Field.PROPERTY_PAWNS)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }     
   
}
