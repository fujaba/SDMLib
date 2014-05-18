package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.adamandeve.model.util.EvePO;
import org.sdmlib.examples.adamandeve.model.Eve;
import org.sdmlib.examples.adamandeve.model.util.AdamPO;
import org.sdmlib.examples.adamandeve.model.Adam;
import org.sdmlib.examples.adamandeve.model.util.TaskFlowPO;
import org.sdmlib.logger.TaskFlow;
import org.sdmlib.examples.adamandeve.model.util.SDMLibJsonIdMapPO;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.examples.adamandeve.model.util.PeerProxyPO;
import org.sdmlib.logger.PeerProxy;
import org.sdmlib.examples.adamandeve.model.util.UpdateAdamFlowPO;
import org.sdmlib.examples.adamandeve.model.UpdateAdamFlow;

public class ModelPattern extends Pattern
{
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public EvePO hasElementEvePO()
   {
      EvePO value = new EvePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public EvePO hasElementEvePO(Eve hostGraphObject)
   {
      EvePO value = new EvePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public AdamPO hasElementAdamPO()
   {
      AdamPO value = new AdamPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public AdamPO hasElementAdamPO(Adam hostGraphObject)
   {
      AdamPO value = new AdamPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public TaskFlowPO hasElementTaskFlowPO()
   {
      TaskFlowPO value = new TaskFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TaskFlowPO hasElementTaskFlowPO(TaskFlow hostGraphObject)
   {
      TaskFlowPO value = new TaskFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public SDMLibJsonIdMapPO hasElementSDMLibJsonIdMapPO()
   {
      SDMLibJsonIdMapPO value = new SDMLibJsonIdMapPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public SDMLibJsonIdMapPO hasElementSDMLibJsonIdMapPO(SDMLibJsonIdMap hostGraphObject)
   {
      SDMLibJsonIdMapPO value = new SDMLibJsonIdMapPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PeerProxyPO hasElementPeerProxyPO()
   {
      PeerProxyPO value = new PeerProxyPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PeerProxyPO hasElementPeerProxyPO(PeerProxy hostGraphObject)
   {
      PeerProxyPO value = new PeerProxyPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public UpdateAdamFlowPO hasElementUpdateAdamFlowPO()
   {
      UpdateAdamFlowPO value = new UpdateAdamFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public UpdateAdamFlowPO hasElementUpdateAdamFlowPO(UpdateAdamFlow hostGraphObject)
   {
      UpdateAdamFlowPO value = new UpdateAdamFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


