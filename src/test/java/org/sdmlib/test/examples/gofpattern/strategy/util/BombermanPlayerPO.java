package org.sdmlib.test.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanPlayer;
import org.sdmlib.models.pattern.Pattern;

public class BombermanPlayerPO extends PatternObject<BombermanPlayerPO, BombermanPlayer>
{

    public BombermanPlayerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BombermanPlayerSet matches = new BombermanPlayerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((BombermanPlayer) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public BombermanPlayerPO(){
      newInstance(org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public BombermanPlayerPO(BombermanPlayer... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   
   public BombermanPlayerPO hasXPosition(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_XPOSITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO hasXPosition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_XPOSITION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createXPosition(int value)
   {
      this.startCreate().hasXPosition(value).endCreate();
      return this;
   }
   
   public int getXPosition()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BombermanPlayer) getCurrentMatch()).getXPosition();
      }
      return 0;
   }
   
   public BombermanPlayerPO withXPosition(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((BombermanPlayer) getCurrentMatch()).setXPosition(value);
      }
      return this;
   }
   
   public BombermanPlayerPO hasYPosition(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_YPOSITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO hasYPosition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_YPOSITION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createYPosition(int value)
   {
      this.startCreate().hasYPosition(value).endCreate();
      return this;
   }
   
   public int getYPosition()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BombermanPlayer) getCurrentMatch()).getYPosition();
      }
      return 0;
   }
   
   public BombermanPlayerPO withYPosition(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((BombermanPlayer) getCurrentMatch()).setYPosition(value);
      }
      return this;
   }
   
   public BombermanPlayerPO hasNumberOfBombs(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_NUMBEROFBOMBS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO hasNumberOfBombs(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_NUMBEROFBOMBS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createNumberOfBombs(int value)
   {
      this.startCreate().hasNumberOfBombs(value).endCreate();
      return this;
   }
   
   public int getNumberOfBombs()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BombermanPlayer) getCurrentMatch()).getNumberOfBombs();
      }
      return 0;
   }
   
   public BombermanPlayerPO withNumberOfBombs(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((BombermanPlayer) getCurrentMatch()).setNumberOfBombs(value);
      }
      return this;
   }
   
   public BombermanPlayerPO hasLastKey(char value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_LASTKEY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createLastKey(char value)
   {
      this.startCreate().hasLastKey(value).endCreate();
      return this;
   }
   
   public char getLastKey()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BombermanPlayer) getCurrentMatch()).getLastKey();
      }
      return 0;
   }
   
   public BombermanPlayerPO withLastKey(char value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((BombermanPlayer) getCurrentMatch()).setLastKey(value);
      }
      return this;
   }
   
   public BombermanPlayerPO hasShortTest(short value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_SHORTTEST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createShortTest(short value)
   {
      this.startCreate().hasShortTest(value).endCreate();
      return this;
   }
   
   public short getShortTest()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BombermanPlayer) getCurrentMatch()).getShortTest();
      }
      return 0;
   }
   
   public BombermanPlayerPO withShortTest(short value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((BombermanPlayer) getCurrentMatch()).setShortTest(value);
      }
      return this;
   }
   
   public BombermanPlayerPO filterXPosition(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_XPOSITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO filterXPosition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_XPOSITION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO filterYPosition(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_YPOSITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO filterYPosition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_YPOSITION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO filterNumberOfBombs(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_NUMBEROFBOMBS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO filterNumberOfBombs(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_NUMBEROFBOMBS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO filterLastKey(char value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_LASTKEY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO filterShortTest(short value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_SHORTTEST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   

   public BombermanPlayerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   
   //==========================================================================
   
   public void keyPress(String key)
   {
      if (this.getPattern().getHasMatch())
      {
          ((BombermanPlayer) getCurrentMatch()).keyPress(key);
      }
   }

   public BombermanPlayerPO createLastKeyCondition(char value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_LASTKEY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createLastKeyAssignment(char value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_LASTKEY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createNumberOfBombsCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_NUMBEROFBOMBS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createNumberOfBombsCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_NUMBEROFBOMBS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createNumberOfBombsAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_NUMBEROFBOMBS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createShortTestCondition(short value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_SHORTTEST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createShortTestAssignment(short value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_SHORTTEST)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createXPositionCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_XPOSITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createXPositionCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_XPOSITION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createXPositionAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_XPOSITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createYPositionCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_YPOSITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createYPositionCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_YPOSITION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public BombermanPlayerPO createYPositionAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(BombermanPlayer.PROPERTY_YPOSITION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
}
