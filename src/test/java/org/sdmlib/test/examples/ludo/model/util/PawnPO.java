package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.ludo.model.Field;
import org.sdmlib.test.examples.ludo.model.Pawn;
import org.sdmlib.test.examples.ludo.model.Player;
   /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java'>LudoStoryboard.java</a>
*/
   public class PawnPO extends PatternObject<PawnPO, Pawn>
{

    public PawnSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PawnSet matches = new PawnSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Pawn) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java'>LudoStoryboard.java</a>
*/
   public PawnPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PawnPO(Pawn... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public PawnPO hasColor(String value)
   {
      new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_COLOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO hasColor(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_COLOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO createColor(String value)
   {
      this.startCreate().hasColor(value).endCreate();
      return this;
   }
   
   public String getColor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) getCurrentMatch()).getColor();
      }
      return null;
   }
   
   public PawnPO withColor(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) getCurrentMatch()).setColor(value);
      }
      return this;
   }
   
   public PawnPO hasX(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_X)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO hasX(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_X)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO createX(int value)
   {
      this.startCreate().hasX(value).endCreate();
      return this;
   }
   
   public int getX()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) getCurrentMatch()).getX();
      }
      return 0;
   }
   
   public PawnPO withX(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) getCurrentMatch()).setX(value);
      }
      return this;
   }
   
   public PawnPO hasY(int value)
   {
      new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_Y)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO hasY(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Pawn.PROPERTY_Y)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PawnPO createY(int value)
   {
      this.startCreate().hasY(value).endCreate();
      return this;
   }
   
   public int getY()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) getCurrentMatch()).getY();
      }
      return 0;
   }
   
   public PawnPO withY(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Pawn) getCurrentMatch()).setY(value);
      }
      return this;
   }
   
     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java'>LudoStoryboard.java</a>
*/
   public PlayerPO hasPlayer()
   {
      PlayerPO result = new PlayerPO(new Player[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pawn.PROPERTY_PLAYER, result);
      
      return result;
   }

   public PlayerPO createPlayer()
   {
      return this.startCreate().hasPlayer().endCreate();
   }

   public PawnPO hasPlayer(PlayerPO tgt)
   {
      return hasLinkConstraint(tgt, Pawn.PROPERTY_PLAYER);
   }

   public PawnPO createPlayer(PlayerPO tgt)
   {
      return this.startCreate().hasPlayer(tgt).endCreate();
   }

   public Player getPlayer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) this.getCurrentMatch()).getPlayer();
      }
      return null;
   }

     /**
    * 
    * @see <a href='../../../../../../../../../../src/test/java/org/sdmlib/test/examples/ludo/LudoStoryboard.java'>LudoStoryboard.java</a>
*/
   public FieldPO hasPos()
   {
      FieldPO result = new FieldPO(new Field[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Pawn.PROPERTY_POS, result);
      
      return result;
   }

   public FieldPO createPos()
   {
      return this.startCreate().hasPos().endCreate();
   }

   public PawnPO hasPos(FieldPO tgt)
   {
      return hasLinkConstraint(tgt, Pawn.PROPERTY_POS);
   }

   public PawnPO createPos(FieldPO tgt)
   {
      return this.startCreate().hasPos(tgt).endCreate();
   }

   public Field getPos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Pawn) this.getCurrentMatch()).getPos();
      }
      return null;
   }

}
