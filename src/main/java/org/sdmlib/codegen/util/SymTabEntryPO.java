package org.sdmlib.codegen.util;

import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class SymTabEntryPO extends PatternObject<SymTabEntryPO, SymTabEntry>
{
   public SymTabEntryPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SymTabEntryPO(SymTabEntry... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   @Override
   public SymTabEntryPO startNAC()
   {
      return (SymTabEntryPO) super.startNAC();
   }
   
   @Override
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
   
   public SymTabEntryPO hasKind(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_KIND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SymTabEntryPO hasMemberName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_MEMBERNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SymTabEntryPO hasType(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_TYPE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SymTabEntryPO hasStartPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_STARTPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SymTabEntryPO hasBodyStartPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_BODYSTARTPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SymTabEntryPO hasEndPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_ENDPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SymTabEntryPO hasModifiers(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SymTabEntry.PROPERTY_MODIFIERS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SymTabEntryPO createKind(String value)
   {
      this.startCreate().hasKind(value).endCreate();
      return this;
   }
   
   public SymTabEntryPO createMemberName(String value)
   {
      this.startCreate().hasMemberName(value).endCreate();
      return this;
   }
   
   public SymTabEntryPO createType(String value)
   {
      this.startCreate().hasType(value).endCreate();
      return this;
   }
   
   public SymTabEntryPO createStartPos(int value)
   {
      this.startCreate().hasStartPos(value).endCreate();
      return this;
   }
   
   public SymTabEntryPO createBodyStartPos(int value)
   {
      this.startCreate().hasBodyStartPos(value).endCreate();
      return this;
   }
   
   public SymTabEntryPO createEndPos(int value)
   {
      this.startCreate().hasEndPos(value).endCreate();
      return this;
   }
   
   public SymTabEntryPO createModifiers(String value)
   {
      this.startCreate().hasModifiers(value).endCreate();
      return this;
   }
   
}



