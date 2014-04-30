package org.sdmlib.examples.ludo.model.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.ludo.model.util.LudoPO;
import org.sdmlib.examples.ludo.model.Ludo;
import org.sdmlib.examples.ludo.model.util.PointPO;
import java.awt.Point;
import org.sdmlib.examples.ludo.model.util.PlayerPO;
import org.sdmlib.examples.ludo.model.Player;
import org.sdmlib.examples.ludo.model.util.DicePO;
import org.sdmlib.examples.ludo.model.Dice;
import org.sdmlib.examples.ludo.model.util.FieldPO;
import org.sdmlib.examples.ludo.model.Field;
import org.sdmlib.examples.ludo.model.util.PawnPO;
import org.sdmlib.examples.ludo.model.Pawn;
import org.sdmlib.examples.ludo.model.util.LudoColorPO;
import org.sdmlib.examples.ludo.LudoModel.LudoColor;

public class ModelPattern extends Pattern
{
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public LudoPO hasElementLudoPO()
   {
      LudoPO value = new LudoPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LudoPO hasElementLudoPO(Ludo hostGraphObject)
   {
      LudoPO value = new LudoPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PointPO hasElementPointPO()
   {
      PointPO value = new PointPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PointPO hasElementPointPO(Point hostGraphObject)
   {
      PointPO value = new PointPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PlayerPO hasElementPlayerPO()
   {
      PlayerPO value = new PlayerPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PlayerPO hasElementPlayerPO(Player hostGraphObject)
   {
      PlayerPO value = new PlayerPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public DicePO hasElementDicePO()
   {
      DicePO value = new DicePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public DicePO hasElementDicePO(Dice hostGraphObject)
   {
      DicePO value = new DicePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public FieldPO hasElementFieldPO()
   {
      FieldPO value = new FieldPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public FieldPO hasElementFieldPO(Field hostGraphObject)
   {
      FieldPO value = new FieldPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PawnPO hasElementPawnPO()
   {
      PawnPO value = new PawnPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PawnPO hasElementPawnPO(Pawn hostGraphObject)
   {
      PawnPO value = new PawnPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public LudoColorPO hasElementLudoColorPO()
   {
      LudoColorPO value = new LudoColorPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LudoColorPO hasElementLudoColorPO(LudoColor hostGraphObject)
   {
      LudoColorPO value = new LudoColorPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


