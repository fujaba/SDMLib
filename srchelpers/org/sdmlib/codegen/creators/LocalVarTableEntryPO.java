package org.sdmlib.codegen.creators;

import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class LocalVarTableEntryPO extends PatternObject
{
   public LocalVarTableEntryPO startNAC()
   {
      return (LocalVarTableEntryPO) super.startNAC();
   }
   
   public LocalVarTableEntryPO endNAC()
   {
      return (LocalVarTableEntryPO) super.endNAC();
   }
   
   public LocalVarTableEntrySet allMatches()
   {
      LocalVarTableEntrySet matches = new LocalVarTableEntrySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LocalVarTableEntry) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public LocalVarTableEntryPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LocalVarTableEntry.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LocalVarTableEntry) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public LocalVarTableEntryPO hasType(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LocalVarTableEntry.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LocalVarTableEntry) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public LocalVarTableEntryPO hasStartPos(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LocalVarTableEntry.PROPERTY_STARTPOS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getStartPos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LocalVarTableEntry) getCurrentMatch()).getStartPos();
      }
      return 0;
   }
   
   public LocalVarTableEntryPO hasEndPos(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LocalVarTableEntry.PROPERTY_ENDPOS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getEndPos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LocalVarTableEntry) getCurrentMatch()).getEndPos();
      }
      return 0;
   }
   
}

