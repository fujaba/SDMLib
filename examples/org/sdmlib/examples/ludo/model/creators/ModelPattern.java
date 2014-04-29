package org.sdmlib.examples.ludo.model.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.ludo.model.creators.LudoPO;
import org.sdmlib.examples.ludo.model.Ludo;
import org.sdmlib.examples.ludo.model.creators.PlayerPO;
import org.sdmlib.examples.ludo.model.Player;
import org.sdmlib.examples.ludo.model.creators.DicePO;
import org.sdmlib.examples.ludo.model.Dice;
import org.sdmlib.examples.ludo.model.creators.FieldPO;
import org.sdmlib.examples.ludo.model.Field;
import org.sdmlib.examples.ludo.model.creators.PawnPO;
import org.sdmlib.examples.ludo.model.Pawn;

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

}


