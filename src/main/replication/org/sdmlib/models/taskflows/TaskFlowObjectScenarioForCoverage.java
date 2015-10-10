package org.sdmlib.models.taskflows;

import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

public class TaskFlowObjectScenarioForCoverage
{
   @Test
   public void testTaskFlowObjectScenarioForCoverage()
   {
      Storyboard story = new Storyboard();
      
      story.add("Create some objects just for coverage. This does not serve as an usage example.");
      
      FetchFileFlow fetchFileFlow = new FetchFileFlow().withFileServer(new PeerProxy());
      
      Logger logger = new Logger();
      
      fetchFileFlow.withSubFlow(logger);
      
      LogEntry logEntry1 = logger.createEntries();
      LogEntry logEntry2 = logger.createEntries();
      
      logEntry1.createChildren();
      
      SocketThread socketThread = new SocketThread();
      
      SDMTimer sdmTimer = new SDMTimer();
      
      story.addObjectDiagram(fetchFileFlow, socketThread, sdmTimer);
      
      story.dumpHTML();
   }
}
