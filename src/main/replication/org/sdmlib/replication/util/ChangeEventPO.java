package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ChangeEvent;
import org.sdmlib.models.pattern.AttributeConstraint;

public class ChangeEventPO extends PatternObject<ChangeEventPO, ChangeEvent>
{

    public ChangeEventSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChangeEventSet matches = new ChangeEventSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChangeEvent) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ChangeEventPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChangeEventPO(ChangeEvent... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ChangeEventPO hasObjectId(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_OBJECTID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO hasObjectId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_OBJECTID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO createObjectId(String value)
   {
      this.startCreate().hasObjectId(value).endCreate();
      return this;
   }
   
   public String getObjectId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeEvent) getCurrentMatch()).getObjectId();
      }
      return null;
   }
   
   public ChangeEventPO withObjectId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChangeEvent) getCurrentMatch()).setObjectId(value);
      }
      return this;
   }
   
   public ChangeEventPO hasObjectType(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_OBJECTTYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO hasObjectType(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_OBJECTTYPE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO createObjectType(String value)
   {
      this.startCreate().hasObjectType(value).endCreate();
      return this;
   }
   
   public String getObjectType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeEvent) getCurrentMatch()).getObjectType();
      }
      return null;
   }
   
   public ChangeEventPO withObjectType(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChangeEvent) getCurrentMatch()).setObjectType(value);
      }
      return this;
   }
   
   public ChangeEventPO hasProperty(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_PROPERTY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO hasProperty(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_PROPERTY)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO createProperty(String value)
   {
      this.startCreate().hasProperty(value).endCreate();
      return this;
   }
   
   public String getProperty()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeEvent) getCurrentMatch()).getProperty();
      }
      return null;
   }
   
   public ChangeEventPO withProperty(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChangeEvent) getCurrentMatch()).setProperty(value);
      }
      return this;
   }
   
   public ChangeEventPO hasNewValue(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_NEWVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO hasNewValue(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_NEWVALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO createNewValue(String value)
   {
      this.startCreate().hasNewValue(value).endCreate();
      return this;
   }
   
   public String getNewValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeEvent) getCurrentMatch()).getNewValue();
      }
      return null;
   }
   
   public ChangeEventPO withNewValue(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChangeEvent) getCurrentMatch()).setNewValue(value);
      }
      return this;
   }
   
   public ChangeEventPO hasOldValue(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_OLDVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO hasOldValue(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_OLDVALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO createOldValue(String value)
   {
      this.startCreate().hasOldValue(value).endCreate();
      return this;
   }
   
   public String getOldValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeEvent) getCurrentMatch()).getOldValue();
      }
      return null;
   }
   
   public ChangeEventPO withOldValue(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChangeEvent) getCurrentMatch()).setOldValue(value);
      }
      return this;
   }
   
   public ChangeEventPO hasValueType(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_VALUETYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO hasValueType(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_VALUETYPE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO createValueType(String value)
   {
      this.startCreate().hasValueType(value).endCreate();
      return this;
   }
   
   public String getValueType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeEvent) getCurrentMatch()).getValueType();
      }
      return null;
   }
   
   public ChangeEventPO withValueType(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChangeEvent) getCurrentMatch()).setValueType(value);
      }
      return this;
   }
   
   public ChangeEventPO hasChangeNo(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_CHANGENO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO hasChangeNo(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_CHANGENO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO createChangeNo(String value)
   {
      this.startCreate().hasChangeNo(value).endCreate();
      return this;
   }
   
   public String getChangeNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeEvent) getCurrentMatch()).getChangeNo();
      }
      return null;
   }
   
   public ChangeEventPO withChangeNo(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChangeEvent) getCurrentMatch()).setChangeNo(value);
      }
      return this;
   }
   
   public ChangeEventPO hasSessionId(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_SESSIONID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO hasSessionId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_SESSIONID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO createSessionId(String value)
   {
      this.startCreate().hasSessionId(value).endCreate();
      return this;
   }
   
   public String getSessionId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeEvent) getCurrentMatch()).getSessionId();
      }
      return null;
   }
   
   public ChangeEventPO withSessionId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChangeEvent) getCurrentMatch()).setSessionId(value);
      }
      return this;
   }
   
   public ChangeEventPO hasPropertyKind(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_PROPERTYKIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO hasPropertyKind(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChangeEvent.PROPERTY_PROPERTYKIND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public ChangeEventPO createPropertyKind(String value)
   {
      this.startCreate().hasPropertyKind(value).endCreate();
      return this;
   }
   
   public String getPropertyKind()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChangeEvent) getCurrentMatch()).getPropertyKind();
      }
      return null;
   }
   
   public ChangeEventPO withPropertyKind(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChangeEvent) getCurrentMatch()).setPropertyKind(value);
      }
      return this;
   }
   
}
