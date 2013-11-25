package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxyPO;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.creators.SocketThreadPO;
import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.model.taskflows.creators.FetchFileFlowPO;
import org.sdmlib.model.taskflows.FetchFileFlow;
import org.sdmlib.model.taskflows.creators.LoggerPO;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.creators.LogEntryPO;
import org.sdmlib.model.taskflows.LogEntry;
import org.sdmlib.model.taskflows.creators.SDMTimerPO;
import org.sdmlib.model.taskflows.SDMTimer;
import org.sdmlib.model.taskflows.creators.SDMLibJsonIdMapPO;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.model.taskflows.creators.ObjectPO;
import org.sdmlib.model.taskflows.Object;

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

   public SocketThreadPO hasElementSocketThreadPO()
   {
      SocketThreadPO value = new SocketThreadPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public SocketThreadPO hasElementSocketThreadPO(SocketThread hostGraphObject)
   {
      SocketThreadPO value = new SocketThreadPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public FetchFileFlowPO hasElementFetchFileFlowPO()
   {
      FetchFileFlowPO value = new FetchFileFlowPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public FetchFileFlowPO hasElementFetchFileFlowPO(FetchFileFlow hostGraphObject)
   {
      FetchFileFlowPO value = new FetchFileFlowPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public LoggerPO hasElementLoggerPO()
   {
      LoggerPO value = new LoggerPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LoggerPO hasElementLoggerPO(Logger hostGraphObject)
   {
      LoggerPO value = new LoggerPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public LogEntryPO hasElementLogEntryPO()
   {
      LogEntryPO value = new LogEntryPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LogEntryPO hasElementLogEntryPO(LogEntry hostGraphObject)
   {
      LogEntryPO value = new LogEntryPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public SDMTimerPO hasElementSDMTimerPO()
   {
      SDMTimerPO value = new SDMTimerPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public SDMTimerPO hasElementSDMTimerPO(SDMTimer hostGraphObject)
   {
      SDMTimerPO value = new SDMTimerPO();
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

   public ObjectPO hasElementObjectPO()
   {
      ObjectPO value = new ObjectPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ObjectPO hasElementObjectPO(Object hostGraphObject)
   {
      ObjectPO value = new ObjectPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


