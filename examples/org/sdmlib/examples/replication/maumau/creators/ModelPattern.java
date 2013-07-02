package org.sdmlib.examples.replication.maumau.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.replication.maumau.creators.MauMauPO;
import org.sdmlib.examples.replication.maumau.MauMau;
import org.sdmlib.examples.replication.maumau.creators.CardPO;
import org.sdmlib.examples.replication.maumau.Card;
import org.sdmlib.examples.replication.maumau.creators.HolderPO;
import org.sdmlib.examples.replication.maumau.Holder;
import org.sdmlib.examples.replication.maumau.creators.PlayerPO;
import org.sdmlib.examples.replication.maumau.Player;

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

   public MauMauPO hasElementMauMauPO()
   {
      MauMauPO value = new MauMauPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public MauMauPO hasElementMauMauPO(MauMau hostGraphObject)
   {
      MauMauPO value = new MauMauPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public CardPO hasElementCardPO()
   {
      CardPO value = new CardPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public CardPO hasElementCardPO(Card hostGraphObject)
   {
      CardPO value = new CardPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public HolderPO hasElementHolderPO()
   {
      HolderPO value = new HolderPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public HolderPO hasElementHolderPO(Holder hostGraphObject)
   {
      HolderPO value = new HolderPO();
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

}


