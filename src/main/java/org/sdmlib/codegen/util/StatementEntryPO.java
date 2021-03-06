package org.sdmlib.codegen.util;

import java.util.ArrayList;

import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class StatementEntryPO extends PatternObject<StatementEntryPO, StatementEntry>
{
   public StatementEntryPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public StatementEntryPO(StatementEntry... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   @Override
   public StatementEntryPO startNAC()
   {
      return (StatementEntryPO) super.startNAC();
   }
   
   @Override
   public StatementEntryPO endNAC()
   {
      return (StatementEntryPO) super.endNAC();
   }
   
   public StatementEntrySet allMatches()
   {
      StatementEntrySet matches = new StatementEntrySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((StatementEntry) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StatementEntryPO hasKind(String value)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_KIND)
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
         return ((StatementEntry) getCurrentMatch()).getKind();
      }
      return null;
   }
   
   public StatementEntryPO hasTokenList(ArrayList<String> value)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_TOKENLIST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ArrayList<String> getTokenList()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StatementEntry) getCurrentMatch()).getTokenList();
      }
      return null;
   }
   
   public StatementEntryPO hasAssignTargetVarName(String value)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_ASSIGNTARGETVARNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getAssignTargetVarName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StatementEntry) getCurrentMatch()).getAssignTargetVarName();
      }
      return null;
   }
   
   public StatementEntryPO hasBodyStats()
   {
      StatementEntryPO result = new StatementEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(StatementEntry.PROPERTY_BODYSTATS, result);
      
      return result;
   }
   
   public StatementEntryPO hasBodyStats(StatementEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(StatementEntry.PROPERTY_BODYSTATS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementEntrySet getBodyStats()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StatementEntry) this.getCurrentMatch()).getBodyStats();
      }
      return null;
   }
   
   public StatementEntryPO hasParent()
   {
      StatementEntryPO result = new StatementEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(StatementEntry.PROPERTY_PARENT, result);
      
      return result;
   }
   
   public StatementEntryPO hasParent(StatementEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(StatementEntry.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementEntry getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StatementEntry) this.getCurrentMatch()).getParent();
      }
      return null;
   }
   
   public StatementEntryPO hasStartPos(int value)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_STARTPOS)
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
         return ((StatementEntry) getCurrentMatch()).getStartPos();
      }
      return 0;
   }
   
   public StatementEntryPO withStartPos(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((StatementEntry) getCurrentMatch()).setStartPos(value);
      }
      return this;
   }
   
   public StatementEntryPO hasEndPos(int value)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_ENDPOS)
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
         return ((StatementEntry) getCurrentMatch()).getEndPos();
      }
      return 0;
   }
   
   public StatementEntryPO withEndPos(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((StatementEntry) getCurrentMatch()).setEndPos(value);
      }
      return this;
   }
   
   public StatementEntryPO hasKind(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_KIND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementEntryPO hasTokenList(ArrayList<String> lower, ArrayList<String> upper)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_TOKENLIST)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementEntryPO hasAssignTargetVarName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_ASSIGNTARGETVARNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementEntryPO hasStartPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_STARTPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementEntryPO hasEndPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_ENDPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StatementEntryPO createKind(String value)
   {
      this.startCreate().hasKind(value).endCreate();
      return this;
   }
   
   public StatementEntryPO createTokenList(ArrayList<String> value)
   {
      this.startCreate().hasTokenList(value).endCreate();
      return this;
   }
   
   public StatementEntryPO createAssignTargetVarName(String value)
   {
      this.startCreate().hasAssignTargetVarName(value).endCreate();
      return this;
   }
   
   public StatementEntryPO createStartPos(int value)
   {
      this.startCreate().hasStartPos(value).endCreate();
      return this;
   }
   
   public StatementEntryPO createEndPos(int value)
   {
      this.startCreate().hasEndPos(value).endCreate();
      return this;
   }
   
   public StatementEntryPO createBodyStats()
   {
      return this.startCreate().hasBodyStats().endCreate();
   }

   public StatementEntryPO createBodyStats(StatementEntryPO tgt)
   {
      return this.startCreate().hasBodyStats(tgt).endCreate();
   }

   public StatementEntryPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public StatementEntryPO createParent(StatementEntryPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

}




