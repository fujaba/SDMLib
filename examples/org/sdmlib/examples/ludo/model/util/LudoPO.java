package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.ludo.model.Ludo;
import org.sdmlib.examples.ludo.model.util.LudoSet;
import java.util.Date;
import org.sdmlib.models.pattern.AttributeConstraint;

public class LudoPO extends PatternObject<LudoPO, Ludo>
{

    public LudoSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LudoSet matches = new LudoSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Ludo) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public LudoPO hasDate(Date value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Ludo.PROPERTY_DATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LudoPO createDate(Date value)
   {
      this.startCreate().hasDate(value).endCreate();
      return this;
   }
   
   public Date getDate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Ludo) getCurrentMatch()).getDate();
      }
      return null;
   }
   
   public LudoPO withDate(Date value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Ludo) getCurrentMatch()).setDate(value);
      }
      return this;
   }
   
}

