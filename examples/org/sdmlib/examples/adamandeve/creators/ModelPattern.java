package org.sdmlib.examples.adamandeve.creators;

import org.sdmlib.examples.adamandeve.Adam;
import org.sdmlib.examples.adamandeve.Eve;
import org.sdmlib.examples.adamandeve.UpdateAdamFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxyPO;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.JsonIdMapPO;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapPO;

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

   public JsonIdMapPO hasElementJsonIdMapPO()
   {
      JsonIdMapPO value = new JsonIdMapPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public JsonIdMapPO hasElementJsonIdMapPO(JsonIdMap hostGraphObject)
   {
      JsonIdMapPO value = new JsonIdMapPO();
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

}



