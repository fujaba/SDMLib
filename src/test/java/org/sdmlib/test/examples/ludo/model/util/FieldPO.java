package org.sdmlib.test.examples.ludo.model.util;

import java.awt.Point;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Ludo;
import org.sdmlib.test.examples.ludo.model.Pawn;
import org.sdmlib.test.examples.ludo.model.Player;

public class FieldPO extends PatternObject<FieldPO, Field>
{

    public FieldSet allMatches()
   {
      this.setDoAllMatches(true);
      
      FieldSet matches = new FieldSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Field) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public FieldPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public FieldPO(Field... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public FieldPO hasColor(String value)
   {
      new AttributeConstraint()
      .withAttrName(Field.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO hasColor(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Field.PROPERTY_COLOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO createColor(String value)
   {
      this.startCreate().hasColor(value).endCreate();
      return this;
   }
   
   public String getColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) getCurrentMatch()).getColor();
      }
      return null;
   }
   
   public FieldPO withColor(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) getCurrentMatch()).setColor(value);
      }
      return this;
   }
   
   public FieldPO hasKind(String value)
   {
      new AttributeConstraint()
      .withAttrName(Field.PROPERTY_KIND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO hasKind(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Field.PROPERTY_KIND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO createKind(String value)
   {
      this.startCreate().hasKind(value).endCreate();
      return this;
   }
   
   public String getKind()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) getCurrentMatch()).getKind();
      }
      return null;
   }
   
   public FieldPO withKind(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) getCurrentMatch()).setKind(value);
      }
      return this;
   }
   
   public FieldPO hasX(int value)
   {
      new AttributeConstraint()
      .withAttrName(Field.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO hasX(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Field.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO createX(int value)
   {
      this.startCreate().hasX(value).endCreate();
      return this;
   }
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public FieldPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) getCurrentMatch()).setX(value);
      }
      return this;
   }
   
   public FieldPO hasY(int value)
   {
      new AttributeConstraint()
      .withAttrName(Field.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO hasY(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Field.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO createY(int value)
   {
      this.startCreate().hasY(value).endCreate();
      return this;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public FieldPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) getCurrentMatch()).setY(value);
      }
      return this;
   }
   
   public FieldPO hasPoint(Point value)
   {
      new AttributeConstraint()
      .withAttrName(Field.PROPERTY_POINT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public FieldPO createPoint(Point value)
   {
      this.startCreate().hasPoint(value).endCreate();
      return this;
   }
   
   public Point getPoint()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) getCurrentMatch()).getPoint();
      }
      return null;
   }
   
   public FieldPO withPoint(Point value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Field) getCurrentMatch()).setPoint(value);
      }
      return this;
   }
   
   public LudoPO hasGame()
   {
      LudoPO result = new LudoPO(new Ludo[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Field.PROPERTY_GAME, result);
      
      return result;
   }

   public LudoPO createGame()
   {
      return this.startCreate().hasGame().endCreate();
   }

   public FieldPO hasGame(LudoPO tgt)
   {
      return hasLinkConstraint(tgt, Field.PROPERTY_GAME);
   }

   public FieldPO createGame(LudoPO tgt)
   {
      return this.startCreate().hasGame(tgt).endCreate();
   }

   public Ludo getGame()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getGame();
      }
      return null;
   }

   public FieldPO hasNext()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Field.PROPERTY_NEXT, result);
      
      return result;
   }

   public FieldPO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public FieldPO hasNext(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Field.PROPERTY_NEXT);
   }

   public FieldPO createNext(FieldPO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
   }

   public Field getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public FieldPO hasPrev()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Field.PROPERTY_PREV, result);
      
      return result;
   }

   public FieldPO createPrev()
   {
      return this.startCreate().hasPrev().endCreate();
   }

   public FieldPO hasPrev(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Field.PROPERTY_PREV);
   }

   public FieldPO createPrev(FieldPO tgt)
   {
      return this.startCreate().hasPrev(tgt).endCreate();
   }

   public Field getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public FieldPO hasLanding()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Field.PROPERTY_LANDING, result);
      
      return result;
   }

   public FieldPO createLanding()
   {
      return this.startCreate().hasLanding().endCreate();
   }

   public FieldPO hasLanding(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Field.PROPERTY_LANDING);
   }

   public FieldPO createLanding(FieldPO tgt)
   {
      return this.startCreate().hasLanding(tgt).endCreate();
   }

   public Field getLanding()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getLanding();
      }
      return null;
   }

   public FieldPO hasEntry()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Field.PROPERTY_ENTRY, result);
      
      return result;
   }

   public FieldPO createEntry()
   {
      return this.startCreate().hasEntry().endCreate();
   }

   public FieldPO hasEntry(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Field.PROPERTY_ENTRY);
   }

   public FieldPO createEntry(FieldPO tgt)
   {
      return this.startCreate().hasEntry(tgt).endCreate();
   }

   public Field getEntry()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getEntry();
      }
      return null;
   }

   public PlayerPO hasStarter()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Field.PROPERTY_STARTER, result);
      
      return result;
   }

   public PlayerPO createStarter()
   {
      return this.startCreate().hasStarter().endCreate();
   }

   public FieldPO hasStarter(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Field.PROPERTY_STARTER);
   }

   public FieldPO createStarter(PlayerPO tgt)
   {
      return this.startCreate().hasStarter(tgt).endCreate();
   }

   public Player getStarter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getStarter();
      }
      return null;
   }

   public PlayerPO hasBaseowner()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Field.PROPERTY_BASEOWNER, result);
      
      return result;
   }

   public PlayerPO createBaseowner()
   {
      return this.startCreate().hasBaseowner().endCreate();
   }

   public FieldPO hasBaseowner(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Field.PROPERTY_BASEOWNER);
   }

   public FieldPO createBaseowner(PlayerPO tgt)
   {
      return this.startCreate().hasBaseowner(tgt).endCreate();
   }

   public Player getBaseowner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getBaseowner();
      }
      return null;
   }

   public PlayerPO hasLander()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Field.PROPERTY_LANDER, result);
      
      return result;
   }

   public PlayerPO createLander()
   {
      return this.startCreate().hasLander().endCreate();
   }

   public FieldPO hasLander(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Field.PROPERTY_LANDER);
   }

   public FieldPO createLander(PlayerPO tgt)
   {
      return this.startCreate().hasLander(tgt).endCreate();
   }

   public Player getLander()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getLander();
      }
      return null;
   }

   public PawnPO hasPawns()
   {
      PawnPO result = new PawnPO(new Pawn[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Field.PROPERTY_PAWNS, result);
      
      return result;
   }

   public PawnPO createPawns()
   {
      return this.startCreate().hasPawns().endCreate();
   }

   public FieldPO hasPawns(PawnPO tgt)
   {
      return hasLinkConstraint(tgt, Field.PROPERTY_PAWNS);
   }

   public FieldPO createPawns(PawnPO tgt)
   {
      return this.startCreate().hasPawns(tgt).endCreate();
   }

   public PawnSet getPawns()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Field) this.getCurrentMatch()).getPawns();
      }
      return null;
   }

}
