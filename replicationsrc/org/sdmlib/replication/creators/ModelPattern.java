package org.sdmlib.replication.creators;

import java.beans.PropertyChangeSupport;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.ServerSocketAcceptThread;
import org.sdmlib.replication.SharedModelRoot;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.TaskFlowBoard;

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

   public PropertyChangeSupportPO hasElementPropertyChangeSupportPO()
   {
      PropertyChangeSupportPO value = new PropertyChangeSupportPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PropertyChangeSupportPO hasElementPropertyChangeSupportPO(PropertyChangeSupport hostGraphObject)
   {
      PropertyChangeSupportPO value = new PropertyChangeSupportPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public SharedModelRootPO hasElementSharedModelRootPO()
   {
      SharedModelRootPO value = new SharedModelRootPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public SharedModelRootPO hasElementSharedModelRootPO(SharedModelRoot hostGraphObject)
   {
      SharedModelRootPO value = new SharedModelRootPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}











