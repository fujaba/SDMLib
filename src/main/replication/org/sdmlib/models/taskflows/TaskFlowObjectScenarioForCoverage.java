package org.sdmlib.models.taskflows;

import org.junit.Test;
import org.sdmlib.storyboards.StoryboardImpl;

public class TaskFlowObjectScenarioForCoverage
{
     /**
    * 
    * @see <a href='../../../../../../../doc/TaskFlowObjectScenarioForCoverage.html'>TaskFlowObjectScenarioForCoverage.html</a>
* @see <a href='../../../../../../../doc/TaskFlowObjectScenarioForCoverage.html'>TaskFlowObjectScenarioForCoverage.html</a>
*/
   @Test
   public void testTaskFlowObjectScenarioForCoverage()
   {
      StoryboardImpl story = new StoryboardImpl();
      
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
