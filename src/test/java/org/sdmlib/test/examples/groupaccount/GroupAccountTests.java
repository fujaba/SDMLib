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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import io.moquette.server.Server;
import org.eclipse.paho.client.mqttv3.*;
import org.junit.Test;
import org.sdmlib.models.*;
import org.sdmlib.models.pattern.ModelIsomorphimOp;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.MikadoLog;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.groupaccount.model.Party;
import org.sdmlib.test.examples.groupaccount.model.Person;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;

public class GroupAccountTests implements PropertyChangeInterface, MqttCallback
{
   private YamlIdMap idMap;
   private YamlIdMap copyMap;
   private StringBuilder buf = new StringBuilder();
   private StringBuilder copyBuf = new StringBuilder();
   private YamlFileMap xiaFileMap;
   private YamlFileMap abuFileMap;
   private YamlMqttMap abuMqttMap;
   private YamlMqttMap xiaMqttMap;

   /**
    * 
    * <p>Storyboard PlainYaml</p>
    * <p>Start: plain yaml to be decoded to map</p>
    * <pre>joining: abu 
    * lastChanges: 2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia</pre>
    * <pre>{joining=abu, lastChanges=2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia}</pre>
    * <p>Check: value for joining abu actual abu</p>
    * <p><a name = 'step_1'>Step 1: Alternatively, use special object type map</a></p>
    * <pre>- m: .Map
    *   joining: abu
    *   lastChanges: 2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia</pre>
    * <pre>{joining=abu, lastChanges=2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia}</pre>
    * <p>Check: value for joining abu actual abu</p>
    * @see #testGroupAccountMultiUserYaml()
    * @see #testGroupAccountMultiUserYamlMerging()
 */
   @Test
   public void testPlainYaml()
   {
      Storyboard story = new Storyboard();
      story.addStep("plain yaml to be decoded to map");
      String yaml = "" +
              "joining: abu \n" +
              "lastChanges: 2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia";
      story.addPreformatted(yaml);
      Yamler yamler = new Yamler();
      LinkedHashMap<String, String> map = yamler.decode(yaml);
      story.addPreformatted(map.toString());
      story.assertEquals("value for joining", "abu", map.get("joining"));
      story.addStep("Alternatively, use special object type map");
      yaml = "" +
              "- m: .Map\n" +
              "  joining: abu\n" +
              "  lastChanges: 2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia";
      story.addPreformatted(yaml);
      idMap = new YamlIdMap("");
      map = (LinkedHashMap<String, String>) idMap.decode(yaml);
      story.addPreformatted(map.toString());
      story.assertEquals("value for joining", "abu", map.get("joining"));
      story.dumpHTML();
   }


   LinkedBlockingQueue<String> testInbox = new LinkedBlockingQueue<>();

   /**
    * 
    * <p>Storyboard GroupAccountYamlWithUserEncoding</p>
    * <p>Start: start mqtt broker</p>
    * <p><a name = 'step_1'>Step 1: test mqtt broker</a></p>
    * <p>Check: got mqtt message World actual World</p>
    * <p><a name = 'step_2'>Step 2: Abu creates a party and adds Albert and Nathalie</a></p>
    * <p><a name = 'step_3'>Step 3: xia creates a party and adds Eyshe</a></p>
    * <p><a name = 'step_4'>Step 4: Abu opens party with mqtt map</a></p>
    * <p><a name = 'step_5'>Step 5: now start abu with mqtt</a></p>
    * <p>Got message:</p>
    * <pre>msg: hello
    * user: abu
    * lastTimeStamps: 2018-08-13T16:15:06.054.abu 
    * </pre>
    * <p>Check: its a lobby message:  hello actual hello</p>
    * <p>Got message:</p>
    * <pre>msg: hello
    * user: xia
    * lastTimeStamps: 2018-08-13T16:15:06.407.xia 
    * </pre>
    * <p>Check: its a lobby message from:  xia actual xia</p>
    * <p>Got message:</p>
    * <pre>msg: welcome
    * newUser: xia
    * oldUser: abu
    * lastTimeStamps: 2018-08-13T16:15:06.054.abu 
    * </pre>
    * <p>Check: its a lobby message:  welcome actual welcome</p>
    * <pre>- xia.p2: 	Person
    *   saldo: 	0.0
    *   saldo.time: 	2018-08-13T16:15:06.685.xia
    * </pre>
    * <pre>- p1: 	Party
    *   guests: 	xia.p3
    *   guests.xia.p3.time: 	2018-08-13T16:15:06.740.xia
    * - xia.p3: 	PersonSet
    * </pre>
    */
   @Test
   public void testGroupAccountYamlWithUserEncoding() throws InterruptedException, IOException, MqttException
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.addStep("start mqtt broker");

      System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "ERROR");

      Server mqttBroker = new Server();
      mqttBroker.startServer();

      story.addStep("test mqtt broker");

      MqttClient mqttClient = new MqttClient("tcp://localhost:1883", "testGroupAccountYamlWithUserEncoding");
      mqttClient.setCallback(this);

      mqttClient.connect();
      mqttClient.subscribe("hello");

      mqttClient.publish("hello", new MqttMessage("World".getBytes()));

      String msg = testInbox.take();

      story.assertEquals("got mqtt message", "World", msg);

      story.addStep("Abu creates a party and adds Albert and Nathalie");

      ExecutorService modelThread = Executors.newSingleThreadExecutor();

      Files.deleteIfExists(Paths.get("aStore/LecturesParty.abu.yaml"));
      Files.deleteIfExists(Paths.get("aStore/LecturesParty.abu.yaml.log"));
      Files.deleteIfExists(Paths.get("aStore/LecturesParty.xia.yaml"));
      Files.deleteIfExists(Paths.get("aStore/LecturesParty.xia.yaml.log"));

      Party abuParty = new Party().withPartyName("Lectures Wrong");
      String packageName = abuParty.getClass().getPackage().getName();

      story.addStep("xia creates a party and adds Eyshe");
      Party xiaParty = new Party().withPartyName("Lectures Copy");
      xiaFileMap = new YamlFileMap("xia", "aStore/LecturesParty", xiaParty, modelThread);
      modelThread.execute(()-> xiaAddGuests(xiaParty));
      modelThread.execute(() -> xiaFileMap.compressLogFile());
      modelThread.execute(() -> xiaFileMap.removeYou());

      abuFileMap = new YamlFileMap("abu", "aStore/LecturesParty", abuParty, modelThread);
      modelThread.execute(()-> abuAddGuests(abuParty));
      modelThread.execute(() -> abuFileMap.compressLogFile());
      modelThread.execute(() -> abuFileMap.removeYou());
      story.addStep("Abu opens party with mqtt map");

      story.addStep("now start abu with mqtt");

      mqttClient.subscribe("aStore/LecturesParty/lobby");
      mqttClient.subscribe("aStore/LecturesParty/model");

      modelThread.execute(()->
              abuMqttMap = new YamlMqttMap("tcp://localhost:1883", "abu", "aStore/LecturesParty", abuParty, modelThread));

      msg = testInbox.take();

      LinkedHashMap<String, String> msgMap = new Yamler().decode(msg);

      String msgType = msgMap.get("msg");

      story.add("Got message:");
      story.addPreformatted(msg);

      story.assertEquals("its a lobby message: ", "hello", msgType);

      modelThread.execute(()->
              xiaMqttMap = new YamlMqttMap("tcp://localhost:1883", "xia", "aStore/LecturesParty", xiaParty, modelThread));

      msg = testInbox.take();
      msgMap = new Yamler().decode(msg);
      String userName = msgMap.get("user");
      story.add("Got message:");
      story.addPreformatted(msg);
      story.assertEquals("its a lobby message from: ", "xia", userName);

      msg = testInbox.take();
      msgMap = new Yamler().decode(msg);
      msgType = msgMap.get("msg");
      story.add("Got message:");
      story.addPreformatted(msg);
      story.assertEquals("its a lobby message: ", "welcome", msgType);

      String expected = "welcome.xia";

      while (! expected.trim().equals(""))
      {
         msg = testInbox.poll(5, TimeUnit.SECONDS);

         if (msg == null) break;

         if (msg.startsWith("-"))
         {
            // model message
            story.addPreformatted(msg);
         }
         else
         {
            msgMap = new Yamler().decode(msg);
            msgType = msgMap.get("msg");
            story.add("Got message:");
            story.addPreformatted(msg);
            story.assertEquals("its a lobby message: ", "welcome", msgType);
         }
      }


      modelThread.shutdown();
      modelThread.awaitTermination(1, TimeUnit.HOURS);

      story.dumpHTML();
   }

   private void xiaAddGuests(Party party)
   {
      Person eyshe = party.createGuests().withName("Eyshe");
   }


   private void abuAddGuests(Party victoryParty)
   {
      Person albert = victoryParty.createGuests().withName("Albert");
      Person nata = victoryParty.createGuests().withName("Nathalie");
   }

   /**
       *
       * <p>Storyboard GroupAccountMultiUserYaml</p>
    * <p>Start: create a party data structure and store it with YamlIdMap</p>
    * <img src="doc-files/GroupAccountMultiUserYamlStep1.png" alt="GroupAccountMultiUserYamlStep1.png">
    * <p><a name = 'step_1'>Step 1: add component listener and log changes in yaml format</a></p>
    * <pre>- p1: 	Party
    *   partyName: 	&quot;Lectures Done&quot;
    *   partyName.time: 	2018-08-13T16:15:24.101.albert
    * - p1: 	Party
    *   share: 	0.0
    *   share.time: 	2018-08-13T16:15:24.101.albert
    * - p1: 	Party
    *   total: 	0.0
    *   total.time: 	2018-08-13T16:15:24.101.albert
    * - albert.p2: 	Person
    *   name: 	Albert
    *   name.time: 	2018-08-13T16:15:24.103.albert
    * - albert.p2: 	Person
    *   saldo: 	0.0
    *   saldo.time: 	2018-08-13T16:15:24.105.albert
    * - albert.p2: 	Person
    *   total: 	0.0
    *   total.time: 	2018-08-13T16:15:24.105.albert
    * - albert.p2: 	Person
    *   party: 	p1
    *   party.time: 	2018-08-13T16:15:24.105.albert
    * - p1: 	Party
    * - p1: 	Party
    *   guests: 	albert.p2
    *   guests.albert.p2.time: 	2018-08-13T16:15:24.109.albert
    * - albert.p2: 	Person
    * - albert.p3: 	Person
    *   name: 	Nathalie
    *   name.time: 	2018-08-13T16:15:24.109.albert
    * - albert.p3: 	Person
    *   saldo: 	0.0
    *   saldo.time: 	2018-08-13T16:15:24.110.albert
    * - albert.p3: 	Person
    *   total: 	0.0
    *   total.time: 	2018-08-13T16:15:24.110.albert
    * - albert.p3: 	Person
    *   party: 	p1
    *   party.time: 	2018-08-13T16:15:24.116.albert
    * - p1: 	Party
    * - p1: 	Party
    *   guests: 	albert.p3
    *   guests.albert.p3.time: 	2018-08-13T16:15:24.116.albert
    * - albert.p3: 	Person
    * </pre>
    * <p><a name = 'step_2'>Step 2: load changes into second model, continuously. </a></p>
    * <p><a name = 'step_3'>Step 3: check isomorphism</a></p>
    * <p>Check: match {Lectures Done 0.0 0.0=Lectures Done 0.0 0.0, Albert 0.0 0.0=Albert 0.0 0.0, Nathalie 0.0 0.0=Nathalie 0.0 0.0}</p>
    * <p><a name = 'step_4'>Step 4: deal with link removal</a></p>
    * <pre>- albert.p3: 	Person
    *   party.remove: 	p1
    *   party.remove.time: 	2018-08-13T16:15:24.130.albert
    * - p1: 	Party
    *   guests.remove: 	albert.p3
    *   guests.remove.albert.p3.time: 	2018-08-13T16:15:24.130.albert
    * </pre>
    * <p>Check: match {Lectures Done 0.0 0.0=Lectures Done 0.0 0.0, Albert 0.0 0.0=Albert 0.0 0.0}</p>
    * <p>original model</p>
    * <img src="doc-files/GroupAccountMultiUserYamlStep11.png" alt="GroupAccountMultiUserYamlStep11.png">
    * <p>cloned model</p>
    * <img src="doc-files/GroupAccountMultiUserYamlStep13.png" alt="GroupAccountMultiUserYamlStep13.png">
    * <p><a name = 'step_5'>Step 5: deal with object removal</a></p>
    * <pre>- albert.p3: 	Person.remove
    *   Person.remove.time: 	2018-08-13T16:15:24.614.albert
    * </pre>
    * <p>Check: match {Lectures Done 0.0 0.0=Lectures Done 0.0 0.0, Albert 0.0 0.0=Albert 0.0 0.0}</p>
    * <p>Check: orig idmap has removed natanull</p>
    * <p>original model</p>
    * <img src="doc-files/GroupAccountMultiUserYamlStep19.png" alt="GroupAccountMultiUserYamlStep19.png">
    * <p>cloned model</p>
    * <img src="doc-files/GroupAccountMultiUserYamlStep21.png" alt="GroupAccountMultiUserYamlStep21.png">
    * <p><a name = 'step_6'>Step 6: add objects after removal</a></p>
    * <pre>- albert.p3: 	Person.remove
    *   Person.remove.time: 	2018-08-13T16:15:24.614.albert
    * - albert.p4: 	Person
    *   saldo: 	0.0
    *   saldo.time: 	2018-08-13T16:15:24.926.albert
    * - albert.p4: 	Person
    *   total: 	0.0
    *   total.time: 	2018-08-13T16:15:24.926.albert
    * - albert.p4: 	Person
    *   party: 	p1
    *   party.time: 	2018-08-13T16:15:24.926.albert
    * - p1: 	Party
    * - p1: 	Party
    *   guests: 	albert.p4
    *   guests.albert.p4.time: 	2018-08-13T16:15:24.926.albert
    * - albert.p4: 	Person
    * - albert.p4: 	Person
    *   name: 	Ann
    *   name.time: 	2018-08-13T16:15:24.926.albert
    * </pre>
    */
   @Test
   public void testGroupAccountMultiUserYamlMerging() throws InterruptedException
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.addStep("Create two parties");
      
      Party victoryParty = new Party().withPartyName("Lectures Wrong");
      Person albert = victoryParty.createGuests().withName("Albert");
      Person nata = victoryParty.createGuests().withName("Nathalie");

      Party copyParty = new Party().withPartyName("Lectures Right").withShare(23);
      Person ann = copyParty.createGuests().withName("Ann");


      story.addStep("record changes");

      String packageName = victoryParty.getClass().getPackage().getName();
      idMap = new YamlIdMap(packageName).withUserId("ann");
      new SDMComponentListener(victoryParty, e -> yamlLogPropertyChange(e, idMap, buf));

      Thread.sleep(10);

      copyMap = new YamlIdMap(packageName).withUserId("bob");
      new SDMComponentListener(copyParty, e -> yamlLogPropertyChange(e, copyMap, copyBuf));

      Thread.sleep(10);

      victoryParty.setShare(42);

      Logger.getGlobal().info("\norig buf: \n" + buf.toString());
      Logger.getGlobal().info("\ncopy buf: \n" + copyBuf.toString());

      story.addPreformatted("\norig buf: \n" + buf.toString());
      story.addPreformatted("\ncopy buf: \n" + copyBuf.toString());


      story.addStep("apply changes");

      idMap.decode(copyBuf.toString());

      Logger.getGlobal().info("\norig buf: \n" + buf.toString());
      story.addPreformatted("\norig buf: \n" + buf.toString());
      story.addObjectDiagram(victoryParty);

      copyMap.decode(buf.toString());

      story.addObjectDiagram(copyParty);

      story.assertEquals("Number of guests after merge:", 3, victoryParty.getGuests().size());

      //----------------------------------------------------------------------------
      story.addStep("change both with remove edit conflict");

      buf.setLength(0);
      copyBuf.setLength(0);

      nata.removeYou();

      Person copyNata = copyParty.getGuests().createNameCondition("Nathalie").first();

      copyNata.setSaldo(84);

      idMap.decode(copyBuf.toString());
      copyMap.decode(buf.toString());

      story.assertEquals("Number of objects in orig idMap", 3, idMap.getObjIdMap().size());
      story.assertEquals("Number of objects in copy idMap", 3, copyMap.getObjIdMap().size());


      //----------------------------------------------------------------------------
      story.addStep("do it with gui");

      story.dumpHTML();
   }



   @Test
   public void testGroupAccountMultiUserYaml()
   {
      Storyboard story = new Storyboard();

      story.addStep("create a party data structure and store it with YamlIdMap");

      Party victoryParty = new Party().withPartyName("Lectures Done");
      Person albert = victoryParty.createGuests().withName("Albert");
      Person nata = victoryParty.createGuests().withName("Nathalie");

      story.addObjectDiagramViaGraphViz(victoryParty);


      story.addStep("add component listener and log changes in yaml format");

      String packageName = victoryParty.getClass().getPackage().getName();

      idMap = new YamlIdMap(packageName).withUserId("albert");

      new SDMComponentListener(victoryParty, e -> yamlLogPropertyChange(e, idMap, buf));

      story.addPreformatted(buf.toString());

      //----------------------------------------------------------------------------
      story.addStep("load changes into second model, continuously. ");

      YamlIdMap copyMap = new YamlIdMap(packageName);

      Party copyParty = (Party) copyMap.decode(buf.toString());

      new SDMComponentListener(copyParty, e -> yamlLogPropertyChange(e, copyMap, copyBuf));

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
      story.addObjectDiagramViaGraphViz(victoryParty);

      story.add("cloned model");
      story.addObjectDiagramViaGraphViz(copyParty);


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
      story.addObjectDiagramViaGraphViz(victoryParty);

      story.add("cloned model");
      story.addObjectDiagramViaGraphViz(copyParty);

      //----------------------------------------------------------------------------
      story.addStep("add objects after removal");

      // buf.setLength(0);

      Person ann = victoryParty.createGuests().withName("Ann");

      Logger.getGlobal().info("\n" + buf.toString());

      story.addPreformatted(buf.toString());

      //----------------------------------------------------------------------------
     

      story.dumpHTML();
   }



   @Test
   public void testMultiUserGroupAccountProjectPlan()
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.addStep("Project plan: ");

      Goal multiUserGroupAccount = new Goal().withDescription("Multi User Group Account");

      Goal gui_integration = multiUserGroupAccount.createPreGoals().withDescription("Gui Integration");

      Goal yamlReplication = gui_integration.createPreGoals().withDescription("YamlReplication");

      multiUserGroupAccount.withPreGoals(yamlReplication);

      Goal simplePartyObjects = yamlReplication.createPreGoals().withDescription("Simple party objects")
              .withHoursTodo(1.5);

      Goal componentListener = yamlReplication.createPreGoals().withDescription("Component Listener");

      Goal yamlDeltas = yamlReplication.createPreGoals().withDescription("Yaml Deltas");

      Goal persistence = yamlReplication.createPreGoals().withDescription("Persistence");

      Goal write = yamlDeltas.createPreGoals().withDescription("Write").withHoursTodo(2);

      Goal read = yamlDeltas.createPreGoals().withDescription("Read");

      Goal isomorphismCheck = yamlReplication.createPreGoals().withDescription("Isomorphism Check");

      Goal removeLink = yamlDeltas.createPreGoals().withDescription("Remove Link");

      Goal removeObject = yamlDeltas.createPreGoals().withDescription("Remove Object");

      Goal sessionIds = yamlDeltas.createPreGoals().withDescription("session ids");

      Goal mergeConflicts = yamlDeltas.createPreGoals().withDescription("merge conflicts");

      Goal timeStamps = yamlDeltas.createPreGoals().withDescription("timeStamps");

      Goal plainYaml = multiUserGroupAccount.createPreGoals().withDescription("plain yaml");

      Goal yamler = plainYaml.createPreGoals().withDescription("yaml reader");

      Goal sessionProtocols = multiUserGroupAccount.createPreGoals().withDescription("session protocols");


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

      mikadoLog.createEntries()
              .withGoal(removeObject)
              .withDate("2018-03-09T14:00:00+01:00")
              .withHoursDone(0.5)
              .withHoursRemaining(2);

      mikadoLog.createEntries()
              .withGoal(sessionIds)
              .withDate("2018-03-11T22:00:00+01:00")
              .withHoursDone(2)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(timeStamps)
              .withDate("2018-03-11T23:57:00+01:00")
              .withHoursDone(2)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(mergeConflicts)
              .withDate("2018-03-12T18:30:00+01:00")
              .withHoursDone(2)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(removeObject)
              .withDate("2018-03-13T15:45:00+01:00")
              .withHoursDone(2)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(yamlDeltas)
              .withDate("2018-03-13T16:00:00+01:00")
              .withHoursDone(0.1)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(persistence)
              .withDate("2018-03-16T14:30:00+01:00")
              .withHoursDone(3)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(plainYaml)
              .withDate("2018-03-17T15:12:00+01:00")
              .withHoursDone(1)
              .withHoursRemaining(0);

      mikadoLog.createEntries()
              .withGoal(yamler)
              .withDate("2018-03-17T16:00:00+01:00")
              .withHoursDone(1)
              .withHoursRemaining(0);

      Goal refactorFileMap = multiUserGroupAccount.createPreGoals().withDescription("refactor yaml file map");



      story.add(mikadoLog.burnDownChartPng());

      Goal done = multiUserGroupAccount.clipDone();

      story.addStep("open goals");

      story.addObjectDiagram(multiUserGroupAccount);

      story.addStep("closed goals");
      story.addObjectDiagram(done);

      story.dumpHTML();
   }


   private void yamlLogPropertyChange(PropertyChangeEvent e, YamlIdMap tgtIdMap, StringBuilder tgtBuf)
   {
      if (tgtIdMap.isDecodingPropertyChange())
      {
         tgtBuf.append(tgtIdMap.getYamlChange());
      }
      else
      {
         String yaml = tgtIdMap.encode(e);
         tgtBuf.append(yaml);
      }
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

   @Override
   public void connectionLost(Throwable cause)
   {

   }

   @Override
   public void messageArrived(String topic, MqttMessage message) throws Exception
   {
      testInbox.put(message.toString());
   }

   @Override
   public void deliveryComplete(IMqttDeliveryToken token)
   {

   }
}

