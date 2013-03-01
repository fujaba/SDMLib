package org.sdmlib.codegen.creators;

import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class SymTabEntryPO extends PatternObject
{
   public SymTabEntryPO startNAC()
   {
      return (SymTabEntryPO) super.startNAC();
   }
   
   public SymTabEntryPO endNAC()
   {
      return (SymTabEntryPO) super.endNAC();
   }
   
   public SymTabEntrySet allMatches()
   {
      SymTabEntrySet matches = new SymTabEntrySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SymTabEntry) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public SymTabEntryPO hasKind(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getKind()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SymTabEntry) getCurrentMatch()).getKind();
      }
      return null;
   }
   
   public SymTabEntryPO hasMemberName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_MEMBERNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getMemberName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SymTabEntry) getCurrentMatch()).getMemberName();
      }
      return null;
   }
   
   public SymTabEntryPO hasType(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_TYPE)
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
         return ((SymTabEntry) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public SymTabEntryPO hasStartPos(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_STARTPOS)
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
         return ((SymTabEntry) getCurrentMatch()).getStartPos();
      }
      return 0;
   }
   
   public SymTabEntryPO hasBodyStartPos(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_BODYSTARTPOS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getBodyStartPos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SymTabEntry) getCurrentMatch()).getBodyStartPos();
      }
      return 0;
   }
   
   public SymTabEntryPO hasEndPos(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_ENDPOS)
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
         return ((SymTabEntry) getCurrentMatch()).getEndPos();
      }
      return 0;
   }
   
   public SymTabEntryPO hasModifiers(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_MODIFIERS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getModifiers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SymTabEntry) getCurrentMatch()).getModifiers();
      }
      return null;
   }
   
}

