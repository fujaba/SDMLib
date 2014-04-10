package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.replication.creators.ReplicationNodePO;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.creators.SharedSpacePO;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.creators.ReplicationChannelPO;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.creators.ReplicationServerPO;
import org.sdmlib.replication.ReplicationServer;
import org.sdmlib.replication.creators.ServerSocketAcceptThreadPO;
import org.sdmlib.replication.ServerSocketAcceptThread;
import org.sdmlib.replication.creators.TaskPO;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.creators.LogEntryPO;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.creators.ChangeHistoryPO;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.creators.ReplicationChangePO;
import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.replication.creators.RemoteTaskBoardPO;
import org.sdmlib.replication.RemoteTaskBoard;
import org.sdmlib.replication.creators.LanePO;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.creators.BoardTaskPO;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.creators.ReplicationRootPO;
import org.sdmlib.replication.ReplicationRoot;

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

   public ReplicationNodePO hasElementReplicationNodePO(
         ReplicationNode hostGraphObject)
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

   public ReplicationChannelPO hasElementReplicationChannelPO()
   {
      ReplicationChannelPO value = new ReplicationChannelPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());

      this.findMatch();

      return value;
   }

   public ReplicationChannelPO hasElementReplicationChannelPO(
         ReplicationChannel hostGraphObject)
   {
      ReplicationChannelPO value = new ReplicationChannelPO();
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

   public ReplicationServerPO hasElementReplicationServerPO(
         ReplicationServer hostGraphObject)
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

   public ServerSocketAcceptThreadPO hasElementServerSocketAcceptThreadPO(
         ServerSocketAcceptThread hostGraphObject)
   {
      ServerSocketAcceptThreadPO value = new ServerSocketAcceptThreadPO();
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

   public ChangeHistoryPO hasElementChangeHistoryPO()
   {
      ChangeHistoryPO value = new ChangeHistoryPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());

      this.findMatch();

      return value;
   }

   public ChangeHistoryPO hasElementChangeHistoryPO(
         ChangeHistory hostGraphObject)
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

   public ReplicationChangePO hasElementReplicationChangePO(
         ReplicationChange hostGraphObject)
   {
      ReplicationChangePO value = new ReplicationChangePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);

      value.setCurrentMatch(hostGraphObject);

      this.findMatch();

      return value;
   }

   public RemoteTaskBoardPO hasElementRemoteTaskBoardPO()
   {
      RemoteTaskBoardPO value = new RemoteTaskBoardPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());

      this.findMatch();

      return value;
   }

   public RemoteTaskBoardPO hasElementRemoteTaskBoardPO(
         RemoteTaskBoard hostGraphObject)
   {
      RemoteTaskBoardPO value = new RemoteTaskBoardPO();
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

   public ReplicationRootPO hasElementReplicationRootPO()
   {
      ReplicationRootPO value = new ReplicationRootPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ReplicationRootPO hasElementReplicationRootPO(ReplicationRoot hostGraphObject)
   {
      ReplicationRootPO value = new ReplicationRootPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}

