package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.replication.creators.ReplicationNodePO;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.creators.SharedSpacePO;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.creators.ThreadPO;
import java.lang.Thread;
import org.sdmlib.replication.creators.ReplicationServerPO;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.creators.ServerSocketAcceptThreadPO;
import org.sdmlib.replication.ServerSocketAcceptThread;
import org.sdmlib.replication.creators.ReplicationChannelPO;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.creators.ChangeHistoryPO;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.creators.ReplicationChangePO;
import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.replication.creators.TaskPO;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.creators.LogEntryPO;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.creators.TaskFlowBoardPO;
import org.sdmlib.replication.TaskFlowBoard;
import org.sdmlib.replication.creators.LanePO;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.creators.StepPO;
import org.sdmlib.replication.Step;
import org.sdmlib.replication.creators.ExecutorPO;
import org.sdmlib.replication.Executor;
import org.sdmlib.replication.creators.BoardTaskPO;
import org.sdmlib.replication.BoardTask;

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

   public ReplicationNodePO hasElementReplicationNodePO()
   {
      ReplicationNodePO value = new ReplicationNodePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ReplicationNodePO hasElementReplicationNodePO(ReplicationNode hostGraphObject)
   {
      ReplicationNodePO value = new ReplicationNodePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public SharedSpacePO hasElementSharedSpacePO()
   {
      SharedSpacePO value = new SharedSpacePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public SharedSpacePO hasElementSharedSpacePO(SharedSpace hostGraphObject)
   {
      SharedSpacePO value = new SharedSpacePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ThreadPO hasElementThreadPO()
   {
      ThreadPO value = new ThreadPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ThreadPO hasElementThreadPO(Thread hostGraphObject)
   {
      ThreadPO value = new ThreadPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ReplicationServerPO hasElementReplicationServerPO()
   {
      ReplicationServerPO value = new ReplicationServerPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ReplicationServerPO hasElementReplicationServerPO(ReplicationServer hostGraphObject)
   {
      ReplicationServerPO value = new ReplicationServerPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ServerSocketAcceptThreadPO hasElementServerSocketAcceptThreadPO()
   {
      ServerSocketAcceptThreadPO value = new ServerSocketAcceptThreadPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ServerSocketAcceptThreadPO hasElementServerSocketAcceptThreadPO(ServerSocketAcceptThread hostGraphObject)
   {
      ServerSocketAcceptThreadPO value = new ServerSocketAcceptThreadPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ReplicationChannelPO hasElementReplicationChannelPO()
   {
      ReplicationChannelPO value = new ReplicationChannelPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ReplicationChannelPO hasElementReplicationChannelPO(ReplicationChannel hostGraphObject)
   {
      ReplicationChannelPO value = new ReplicationChannelPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ChangeHistoryPO hasElementChangeHistoryPO()
   {
      ChangeHistoryPO value = new ChangeHistoryPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ChangeHistoryPO hasElementChangeHistoryPO(ChangeHistory hostGraphObject)
   {
      ChangeHistoryPO value = new ChangeHistoryPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ReplicationChangePO hasElementReplicationChangePO()
   {
      ReplicationChangePO value = new ReplicationChangePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ReplicationChangePO hasElementReplicationChangePO(ReplicationChange hostGraphObject)
   {
      ReplicationChangePO value = new ReplicationChangePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public TaskPO hasElementTaskPO()
   {
      TaskPO value = new TaskPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TaskPO hasElementTaskPO(Task hostGraphObject)
   {
      TaskPO value = new TaskPO();
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

   public TaskFlowBoardPO hasElementTaskFlowBoardPO()
   {
      TaskFlowBoardPO value = new TaskFlowBoardPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public TaskFlowBoardPO hasElementTaskFlowBoardPO(TaskFlowBoard hostGraphObject)
   {
      TaskFlowBoardPO value = new TaskFlowBoardPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public LanePO hasElementLanePO()
   {
      LanePO value = new LanePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LanePO hasElementLanePO(Lane hostGraphObject)
   {
      LanePO value = new LanePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StepPO hasElementStepPO()
   {
      StepPO value = new StepPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StepPO hasElementStepPO(Step hostGraphObject)
   {
      StepPO value = new StepPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ExecutorPO hasElementExecutorPO()
   {
      ExecutorPO value = new ExecutorPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ExecutorPO hasElementExecutorPO(Executor hostGraphObject)
   {
      ExecutorPO value = new ExecutorPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public BoardTaskPO hasElementBoardTaskPO()
   {
      BoardTaskPO value = new BoardTaskPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public BoardTaskPO hasElementBoardTaskPO(BoardTask hostGraphObject)
   {
      BoardTaskPO value = new BoardTaskPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}







