/*
   Copyright (c) 2012 zuendorf 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.test.examples.groupaccount;
 

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;
import java.util.Map;
import java.util.logging.Logger;

import org.junit.Test;
import org.sdmlib.models.SDMComponentListener;
import org.sdmlib.models.YamlIdMap;
import org.sdmlib.models.pattern.ModelIsomorphimOp;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.MikadoLog;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.groupaccount.model.Party;
import org.sdmlib.test.examples.groupaccount.model.Person;

public class GroupAccountTests implements PropertyChangeInterface 
{
   /**
    * 
    * @see <a href='../../../../../../../../doc/GroupAccountStoryboard1.html'>GroupAccountStoryboard1.html</a>
    * @see <a href='../../../../../../../../doc/GroupAccountMultiUserYaml.html'>GroupAccountMultiUserYaml.html</a>
 */
   @Test
   public void testGroupAccountMultiUserYaml()
   {
      Storyboard story = new Storyboard();

      projectPlan(story);


      story.addStep("create a party data structure and store it with YamlIdMap");

      Party victoryParty = new Party().withPartyName("Lectures Done");

      Person albert = victoryParty.createGuests().withName("Albert");

      Person nata = victoryParty.createGuests().withName("Nathalie");

      story.addObjectDiagram(victoryParty);


      story.addStep("add component listener and log changes in yaml format");

      String packageName = victoryParty.getClass().getPackage().getName();

      idMap = new YamlIdMap(packageName);

      new SDMComponentListener(victoryParty, e -> yamlLogPropertyChange(e));


      //----------------------------------------------------------------------------
      story.addStep("load changes into second model, continuously. ");

      YamlIdMap copyMap = new YamlIdMap(packageName);

      Object copyParty = copyMap.decode(buf.toString());

      story.addStep("check isomorphism");

      Map<Object, Object> match = ModelIsomorphimOp.match(victoryParty, copyParty);

      story.assertNotNull("match", match);


      //----------------------------------------------------------------------------
      story.addStep("deal with link removal");

      buf.setLength(0);

      victoryParty.withoutGuests(nata);

      Logger.getGlobal().info("\n" + buf.toString());

      story.addPreformatted(buf.toString());

      copyMap.decode(buf.toString());

      match = ModelIsomorphimOp.match(victoryParty, copyParty);

      story.assertNotNull("match", match);

      story.add("original model");
      story.addObjectDiagram(victoryParty);

      story.add("cloned model");
      story.addObjectDiagram(copyParty);


      //----------------------------------------------------------------------------
      story.addStep("deal with object removal");

      buf.setLength(0);

      nata.removeYou();

      Logger.getGlobal().info("\n" + buf.toString());

      story.addPreformatted(buf.toString());

      copyMap.decode(buf.toString());

      match = ModelIsomorphimOp.match(victoryParty, copyParty);

      story.assertNotNull("match", match);

      Object p3 = idMap.getObject("p3");

      story.assertNull("orig idmap has removed nata", p3);

      story.add("original model");
      story.addObjectDiagram(victoryParty);

      story.add("cloned model");
      story.addObjectDiagram(copyParty);


      //----------------------------------------------------------------------------
      story.addStep("do it with gui");

      story.dumpHTML();
   }

   private void projectPlan(Storyboard story)
   {
      story.addStep("Project plan: ");

      Goal multiUserGroupAccount = new Goal().withDescription("Multi User Group Account");

      Goal gui_integration = multiUserGroupAccount.createPreGoals().withDescription("Gui Integration");

      Goal yamlReplication = gui_integration.createPreGoals().withDescription("YamlReplication");

      multiUserGroupAccount.withPreGoals(yamlReplication);

      Goal simplePartyObjects = yamlReplication.createPreGoals().withDescription("Simple party objects")
              .withHoursTodo(1.5);

      Goal componentListener = yamlReplication.createPreGoals().withDescription("Component Listener");

      Goal yamlDeltas = yamlReplication.createPreGoals().withDescription("Yaml Deltas");

      yamlReplication.createPreGoals().withDescription("Persistence");

      Goal write = yamlDeltas.createPreGoals().withDescription("Write").withHoursTodo(2);

      Goal read = yamlDeltas.createPreGoals().withDescription("Read");

      Goal isomorphismCheck = yamlReplication.createPreGoals().withDescription("Isomorphism Check");

      Goal removeLink = yamlDeltas.createPreGoals().withDescription("Remove Link");

      Goal removeObject = yamlDeltas.createPreGoals().withDescription("Remove Object");

      Goal sessionIds = yamlDeltas.createPreGoals().withDescription("session ids");

      yamlDeltas.createPreGoals().withDescription("merge conflicts");

      yamlDeltas.createPreGoals().withDescription("timeStamps");


      MikadoLog mikadoLog = new MikadoLog().withMainGoal(multiUserGroupAccount);

      mikadoLog.createEntries()
              .withGoal(multiUserGroupAccount)
              .withDate("2018-02-28T12:00:00+01:00")
              .withHoursDone(0)
              .withHoursRemaining(4);

      mikadoLog.createEntries()
              .withGoal(simplePartyObjects)
              .withDate("2018-02-28T15:00:00+01:00")
              .withHoursDone(2)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(componentListener)
              .withDate("2018-02-28T16:00:00+01:00")
              .withHoursDone(2)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(write)
              .withDate("2018-03-01T21:30:00+01:00")
              .withHoursDone(1)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(read)
              .withDate("2018-03-01T22:30:00+01:00")
              .withHoursDone(1)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(isomorphismCheck)
              .withDate("2018-03-01T23:30:00+01:00")
              .withHoursDone(0)
              .withHoursRemaining(1);

      mikadoLog.createEntries()
              .withGoal(isomorphismCheck)
              .withDate("2018-03-02T00:30:00+01:00")
              .withHoursDone(1)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(removeLink)
              .withDate("2018-03-02T14:17:00+01:00")
              .withHoursDone(1)
              .withHoursRemaining(0);

      story.add(mikadoLog.burnDownChart());

      Goal done = multiUserGroupAccount.clipDone();

      story.addStep("open goals");

      story.addObjectDiagram(multiUserGroupAccount);

      story.addStep("closed goals");
      story.addObjectDiagram(done);

   }

   private YamlIdMap idMap;

   private StringBuilder buf = new StringBuilder();

   private void yamlLogPropertyChange(PropertyChangeEvent e)
   {
      String yaml = idMap.encode(e);

      buf.append(yaml);

      Logger.getGlobal().info("\n" + yaml);
   }

   //==========================================================================
   
   public Object get(String attrName)
   {
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
}

