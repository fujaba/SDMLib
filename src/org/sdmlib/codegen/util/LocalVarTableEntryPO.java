package org.sdmlib.codegen.util;

import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class LocalVarTableEntryPO extends PatternObject<LocalVarTableEntryPO, LocalVarTableEntry>
{
   public LocalVarTableEntryPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LocalVarTableEntryPO(LocalVarTableEntry... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   @Override
   public LocalVarTableEntryPO startNAC()
   {
      return (LocalVarTableEntryPO) super.startNAC();
   }
   
   @Override
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
   
   public LocalVarTableEntryPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LocalVarTableEntry.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LocalVarTableEntryPO hasType(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LocalVarTableEntry.PROPERTY_TYPE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LocalVarTableEntryPO hasStartPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(LocalVarTableEntry.PROPERTY_STARTPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LocalVarTableEntryPO hasEndPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(LocalVarTableEntry.PROPERTY_ENDPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LocalVarTableEntryPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public LocalVarTableEntryPO createType(String value)
   {
      this.startCreate().hasType(value).endCreate();
      return this;
   }
   
   public LocalVarTableEntryPO createStartPos(int value)
   {
      this.startCreate().hasStartPos(value).endCreate();
      return this;
   }
   
   public LocalVarTableEntryPO createEndPos(int value)
   {
      this.startCreate().hasEndPos(value).endCreate();
      return this;
   }
   
}



