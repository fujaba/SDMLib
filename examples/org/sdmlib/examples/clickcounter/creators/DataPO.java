package org.sdmlib.examples.clickcounter.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.clickcounter.Data;
import org.sdmlib.examples.clickcounter.creators.DataSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import javafx.beans.property.IntegerProperty;

public class DataPO extends PatternObject<DataPO, Data>
{
   public DataSet allMatches()
   {
      this.setDoAllMatches(true);
      
      DataSet matches = new DataSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Data) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public DataPO hasNum(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Data.PROPERTY_NUM)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getNum()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Data) getCurrentMatch()).getNum();
      }
      return 0;
   }
   
   public DataPO withNum(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Data) getCurrentMatch()).setNum(value);
      }
      return this;
   }
   
   public DataPO hasFxnum(IntegerProperty value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Data.PROPERTY_FXNUM)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public IntegerProperty getFxnum()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Data) getCurrentMatch()).getFxnum();
      }
      return null;
   }
   
   public DataPO withFxnum(IntegerProperty value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Data) getCurrentMatch()).setFxnum(value);
      }
      return this;
   }
   
}


