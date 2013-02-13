package org.sdmlib.examples.ludoreverse.model.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.ludoreverse.model.creators.LudoPO;
import org.sdmlib.examples.ludoreverse.model.Ludo;
import org.sdmlib.examples.ludoreverse.model.creators.PlayerPO;
import org.sdmlib.examples.ludoreverse.model.Player;
import org.sdmlib.examples.ludoreverse.model.creators.PointPO;
import java.awt.Point;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
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

}




