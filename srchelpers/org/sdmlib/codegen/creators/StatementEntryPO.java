package org.sdmlib.codegen.creators;

import java.util.ArrayList;

import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.codegen.creators.StatementEntrySet;
import org.sdmlib.codegen.creators.StatementEntryPO;

public class StatementEntryPO extends PatternObject
{
   public StatementEntryPO startNAC()
   {
      return (StatementEntryPO) super.startNAC();
   }
   
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(StatementEntry.PROPERTY_ENDPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
}



