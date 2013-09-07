package org.sdmlib.model.test.consistence.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.test.consistence.Border;
import org.sdmlib.model.test.consistence.creators.BorderSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.model.test.consistence.creators.FieldPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.model.test.consistence.creators.BorderPO;
import org.sdmlib.model.test.consistence.Field;

public class BorderPO extends PatternObject<BorderPO, Border>
{
   public BorderSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BorderSet matches = new BorderSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Border) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public BorderPO hasBorderLocation(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Border.PROPERTY_BORDERLOCATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getBorderLocation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Border) getCurrentMatch()).getBorderLocation();
      }
      return null;
   }
   
   public BorderPO withBorderLocation(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Border) getCurrentMatch()).setBorderLocation(value);
      }
      return this;
   }
   
   public BorderPO hasConnectedTo(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Border.PROPERTY_CONNECTEDTO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getConnectedTo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Border) getCurrentMatch()).getConnectedTo();
      }
      return null;
   }
   
   public BorderPO withConnectedTo(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Border) getCurrentMatch()).setConnectedTo(value);
      }
      return this;
   }
   
   public BorderPO hasConnectedToRev(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Border.PROPERTY_CONNECTEDTOREV)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getConnectedToRev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Border) getCurrentMatch()).getConnectedToRev();
      }
      return null;
   }
   
   public BorderPO withConnectedToRev(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Border) getCurrentMatch()).setConnectedToRev(value);
      }
      return this;
   }
   
   public BorderPO hasServerId(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Border.PROPERTY_SERVERID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getServerId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Border) getCurrentMatch()).getServerId();
      }
      return null;
   }
   
   public BorderPO withServerId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Border) getCurrentMatch()).setServerId(value);
      }
      return this;
   }
   
   public BorderPO hasRoad(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Border.PROPERTY_ROAD)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getRoad()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Border) getCurrentMatch()).getRoad();
      }
      return null;
   }
   
   public BorderPO withRoad(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Border) getCurrentMatch()).setRoad(value);
      }
      return this;
   }
   
   public FieldPO hasField()
   {
      FieldPO result = new FieldPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Border.PROPERTY_FIELD, result);
      
      return result;
   }

   public BorderPO hasField(FieldPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Border.PROPERTY_FIELD)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Field getField()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Border) this.getCurrentMatch()).getField();
      }
      return null;
   }

   public BorderPO hasConnectedTo()
   {
      BorderPO result = new BorderPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Border.PROPERTY_CONNECTEDTO, result);
      
      return result;
   }

   public BorderPO hasConnectedTo(BorderPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Border.PROPERTY_CONNECTEDTO)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public BorderPO hasConnectedToRev()
   {
      BorderPO result = new BorderPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Border.PROPERTY_CONNECTEDTOREV, result);
      
      return result;
   }

   public BorderPO hasConnectedToRev(BorderPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Border.PROPERTY_CONNECTEDTOREV)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

}

