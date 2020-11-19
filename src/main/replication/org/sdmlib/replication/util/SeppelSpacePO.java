package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ChangeEventList;
import org.sdmlib.replication.SeppelSpace;

public class SeppelSpacePO extends PatternObject<SeppelSpacePO, SeppelSpace>
{

    public SeppelSpaceSet allMatches()
   {
      this.setDoAllMatches(true);
      
      SeppelSpaceSet matches = new SeppelSpaceSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((SeppelSpace) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public SeppelSpacePO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public SeppelSpacePO(SeppelSpace... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public SeppelSpacePO hasSpaceId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpacePO hasSpaceId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpacePO createSpaceId(String value)
   {
      this.startCreate().hasSpaceId(value).endCreate();
      return this;
   }
   
   public String getSpaceId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpace) getCurrentMatch()).getSpaceId();
      }
      return null;
   }
   
   public SeppelSpacePO withSpaceId(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpace) getCurrentMatch()).setSpaceId(value);
      }
      return this;
   }
   
   public SeppelSpacePO hasLastChangeId(long value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpacePO hasLastChangeId(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpacePO createLastChangeId(long value)
   {
      this.startCreate().hasLastChangeId(value).endCreate();
      return this;
   }
   
   public long getLastChangeId()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpace) getCurrentMatch()).getLastChangeId();
      }
      return 0;
   }
   
   public SeppelSpacePO withLastChangeId(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpace) getCurrentMatch()).setLastChangeId(value);
      }
      return this;
   }
   
   public SeppelSpacePO hasJavaFXApplication(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public SeppelSpacePO createJavaFXApplication(boolean value)
   {
      this.startCreate().hasJavaFXApplication(value).endCreate();
      return this;
   }
   
   public boolean getJavaFXApplication()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpace) getCurrentMatch()).isJavaFXApplication();
      }
      return false;
   }
   
   public SeppelSpacePO withJavaFXApplication(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpace) getCurrentMatch()).setJavaFXApplication(value);
      }
      return this;
   }
   
   public SeppelSpacePO hasHistory(ChangeEventList value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_HISTORY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createHistory(ChangeEventList value)
   {
      this.startCreate().hasHistory(value).endCreate();
      return this;
   }
   
   public ChangeEventList getHistory()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((SeppelSpace) getCurrentMatch()).getHistory();
      }
      return null;
   }
   
   public SeppelSpacePO withHistory(ChangeEventList value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((SeppelSpace) getCurrentMatch()).setHistory(value);
      }
      return this;
   }
   
   public SeppelSpacePO filterSpaceId(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO filterSpaceId(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO filterHistory(ChangeEventList value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_HISTORY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO filterLastChangeId(long value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO filterLastChangeId(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO filterJavaFXApplication(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   

   public SeppelSpacePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public SeppelSpacePO createHistoryCondition(ChangeEventList value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_HISTORY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createHistoryAssignment(ChangeEventList value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_HISTORY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createJavaFXApplicationCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createJavaFXApplicationAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_JAVAFXAPPLICATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createLastChangeIdCondition(long value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createLastChangeIdCondition(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createLastChangeIdAssignment(long value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_LASTCHANGEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createSpaceIdCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createSpaceIdCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_SPACEID)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public SeppelSpacePO createSpaceIdAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(SeppelSpace.PROPERTY_SPACEID)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}
