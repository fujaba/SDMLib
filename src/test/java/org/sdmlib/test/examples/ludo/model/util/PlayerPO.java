package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.ludo.LudoModel.LudoColor;
import org.sdmlib.test.examples.ludo.model.Dice;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Pawn;
import org.sdmlib.test.examples.ludo.model.Player;
import org.sdmlib.test.examples.ludo.model.util.LudoPO;
import org.sdmlib.test.examples.ludo.model.util.PlayerPO;
import org.sdmlib.test.examples.ludo.model.util.DicePO;
import org.sdmlib.test.examples.ludo.model.util.FieldPO;
import org.sdmlib.test.examples.ludo.model.util.PawnPO;
import org.sdmlib.models.pattern.Pattern;

public class PlayerPO extends PatternObject<PlayerPO, Player>
{

    public PlayerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PlayerSet matches = new PlayerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Player) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public PlayerPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PlayerPO(Player... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PlayerPO hasColor(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO hasColor(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO createColor(String value)
   {
      this.startCreate().hasColor(value).endCreate();
      return this;
   }
   
   public String getColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getColor();
      }
      return null;
   }
   
   public PlayerPO withColor(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setColor(value);
      }
      return this;
   }
   
   public PlayerPO hasEnumColor(LudoColor value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_ENUMCOLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO createEnumColor(LudoColor value)
   {
      this.startCreate().hasEnumColor(value).endCreate();
      return this;
   }
   
   public LudoColor getEnumColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getEnumColor();
      }
      return null;
   }
   
   public PlayerPO withEnumColor(LudoColor value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setEnumColor(value);
      }
      return this;
   }
   
   public PlayerPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public PlayerPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public PlayerPO hasX(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO hasX(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO createX(int value)
   {
      this.startCreate().hasX(value).endCreate();
      return this;
   }
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public PlayerPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setX(value);
      }
      return this;
   }
   
   public PlayerPO hasY(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO hasY(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlayerPO createY(int value)
   {
      this.startCreate().hasY(value).endCreate();
      return this;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public PlayerPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Player) getCurrentMatch()).setY(value);
      }
      return this;
   }
   
   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO(new Ludo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public LudoPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public PlayerPO hasGame(LudoPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public PlayerPO createGame(LudoPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

   public Ludo getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public PlayerPO hasNext()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_NEXT, result);
      
      return result;
   }

   public PlayerPO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public PlayerPO hasNext(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_NEXT);
   }

   public PlayerPO createNext(PlayerPO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
   }

   public Player getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public PlayerPO hasPrev()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PREV, result);
      
      return result;
   }

   public PlayerPO createPrev()
   {
      return this.startCreate().hasPrev().endCreate();
   }

   public PlayerPO hasPrev(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PREV);
   }

   public PlayerPO createPrev(PlayerPO tgt)
   {
      return this.startCreate().hasPrev(tgt).endCreate();
   }

   public Player getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java'>LudoStoryboard.java</a>
*/
   public DicePO hasDice()
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_DICE, result);
      
      return result;
   }

   public DicePO createDice()
   {
      return this.startCreate().hasDice().endCreate();
   }

   public PlayerPO hasDice(DicePO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_DICE);
   }

   public PlayerPO createDice(DicePO tgt)
   {
      return this.startCreate().hasDice(tgt).endCreate();
   }

   public Dice getDice()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getDice();
      }
      return null;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java'>LudoStoryboard.java</a>
*/
   public FieldPO hasStart()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_START, result);
      
      return result;
   }

   public FieldPO createStart()
   {
      return this.startCreate().hasStart().endCreate();
   }

   public PlayerPO hasStart(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_START);
   }

   public PlayerPO createStart(FieldPO tgt)
   {
      return this.startCreate().hasStart(tgt).endCreate();
   }

   public Field getStart()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getStart();
      }
      return null;
   }

   public FieldPO hasBase()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_BASE, result);
      
      return result;
   }

   public FieldPO createBase()
   {
      return this.startCreate().hasBase().endCreate();
   }

   public PlayerPO hasBase(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_BASE);
   }

   public PlayerPO createBase(FieldPO tgt)
   {
      return this.startCreate().hasBase(tgt).endCreate();
   }

   public Field getBase()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getBase();
      }
      return null;
   }

   public FieldPO hasLanding()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_LANDING, result);
      
      return result;
   }

   public FieldPO createLanding()
   {
      return this.startCreate().hasLanding().endCreate();
   }

   public PlayerPO hasLanding(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_LANDING);
   }

   public PlayerPO createLanding(FieldPO tgt)
   {
      return this.startCreate().hasLanding(tgt).endCreate();
   }

   public Field getLanding()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getLanding();
      }
      return null;
   }

   public PawnPO hasPawns()
   {
      PawnPO result = new PawnPO(new Pawn[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PAWNS, result);
      
      return result;
   }

   public PawnPO createPawns()
   {
      return this.startCreate().hasPawns().endCreate();
   }

   public PlayerPO hasPawns(PawnPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PAWNS);
   }

   public PlayerPO createPawns(PawnPO tgt)
   {
      return this.startCreate().hasPawns(tgt).endCreate();
   }

   public PawnSet getPawns()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Player) this.getCurrentMatch()).getPawns();
      }
      return null;
   }

   public PlayerPO filterColor(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterColor(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterEnumColor(LudoColor value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_ENUMCOLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterX(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterX(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterY(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO filterY(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LudoPO filterGame()
   {
      LudoPO result = new LudoPO(new Ludo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public PlayerPO filterGame(LudoPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public PlayerPO filterPrev()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PREV, result);
      
      return result;
   }

   public PlayerPO filterPrev(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PREV);
   }

   public DicePO filterDice()
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_DICE, result);
      
      return result;
   }

   public PlayerPO filterDice(DicePO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_DICE);
   }

   public FieldPO filterStart()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_START, result);
      
      return result;
   }

   public PlayerPO filterStart(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_START);
   }

   public FieldPO filterBase()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_BASE, result);
      
      return result;
   }

   public PlayerPO filterBase(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_BASE);
   }

   public FieldPO filterLanding()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_LANDING, result);
      
      return result;
   }

   public PlayerPO filterLanding(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_LANDING);
   }

   public PawnPO filterPawns()
   {
      PawnPO result = new PawnPO(new Pawn[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PAWNS, result);
      
      return result;
   }

   public PlayerPO filterPawns(PawnPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PAWNS);
   }

   public PlayerPO filterNext()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_NEXT, result);
      
      return result;
   }

   public PlayerPO filterNext(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_NEXT);
   }


   public PlayerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PlayerPO createColorCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createColorCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createColorAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createEnumColorCondition(LudoColor value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_ENUMCOLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createEnumColorAssignment(LudoColor value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_ENUMCOLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createXCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createXCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createXAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createYCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createYCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlayerPO createYAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Player.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LudoPO createGamePO()
   {
      LudoPO result = new LudoPO(new Ludo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public LudoPO createGamePO(String modifier)
   {
      LudoPO result = new LudoPO(new Ludo[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_GAME, result);
      
      return result;
   }

   public PlayerPO createGameLink(LudoPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME);
   }

   public PlayerPO createGameLink(LudoPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_GAME, modifier);
   }

   public PlayerPO createPrevPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PREV, result);
      
      return result;
   }

   public PlayerPO createPrevPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_PREV, result);
      
      return result;
   }

   public PlayerPO createPrevLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PREV);
   }

   public PlayerPO createPrevLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PREV, modifier);
   }

   public PlayerPO createNextPO()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_NEXT, result);
      
      return result;
   }

   public PlayerPO createNextPO(String modifier)
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_NEXT, result);
      
      return result;
   }

   public PlayerPO createNextLink(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_NEXT);
   }

   public PlayerPO createNextLink(PlayerPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_NEXT, modifier);
   }

   public DicePO createDicePO()
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_DICE, result);
      
      return result;
   }

   public DicePO createDicePO(String modifier)
   {
      DicePO result = new DicePO(new Dice[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_DICE, result);
      
      return result;
   }

   public PlayerPO createDiceLink(DicePO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_DICE);
   }

   public PlayerPO createDiceLink(DicePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_DICE, modifier);
   }

   public FieldPO createStartPO()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_START, result);
      
      return result;
   }

   public FieldPO createStartPO(String modifier)
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_START, result);
      
      return result;
   }

   public PlayerPO createStartLink(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_START);
   }

   public PlayerPO createStartLink(FieldPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_START, modifier);
   }

   public FieldPO createBasePO()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_BASE, result);
      
      return result;
   }

   public FieldPO createBasePO(String modifier)
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_BASE, result);
      
      return result;
   }

   public PlayerPO createBaseLink(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_BASE);
   }

   public PlayerPO createBaseLink(FieldPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_BASE, modifier);
   }

   public FieldPO createLandingPO()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_LANDING, result);
      
      return result;
   }

   public FieldPO createLandingPO(String modifier)
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_LANDING, result);
      
      return result;
   }

   public PlayerPO createLandingLink(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_LANDING);
   }

   public PlayerPO createLandingLink(FieldPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_LANDING, modifier);
   }

   public PawnPO createPawnsPO()
   {
      PawnPO result = new PawnPO(new Pawn[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Player.PROPERTY_PAWNS, result);
      
      return result;
   }

   public PawnPO createPawnsPO(String modifier)
   {
      PawnPO result = new PawnPO(new Pawn[]{});
      
      result.setModifier(modifier);
      super.hasLink(Player.PROPERTY_PAWNS, result);
      
      return result;
   }

   public PlayerPO createPawnsLink(PawnPO tgt)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PAWNS);
   }

   public PlayerPO createPawnsLink(PawnPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Player.PROPERTY_PAWNS, modifier);
   }

}
