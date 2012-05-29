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
   
package org.sdmlib.models.objects;
   
import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.objects.creators.CreatorCreator;
import org.sdmlib.scenarios.LogEntry;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;
   
public class GenericObjectsTest 
{
   @Test
   public void testGenericObjectDiagram()
   {
      Scenario scenario = new Scenario("test", "GenericObjectDiagram");
      
      scenario.add("Start situation: we do not yet have a class diagram but want to start with some example object models",
         DONE, "zuendorf", "28.05.2012 23:51:42", 4, 0);
      
      scenario.add("Step 1: We build a generic class model for object structures: ");

      ClassModel model = new ClassModel();
      
      Clazz genericObjectClazz = new Clazz("org.sdmlib.models.objects.GenericObject")
      .withAttribute("name", "String")
      .withAttribute("type", "String");
      
      Clazz genericAttributeClazz = new Clazz("org.sdmlib.models.objects.GenericAttribute")
      .withAttribute("name", "String")
      .withAttribute("value", "String");
      
      new Association()
      .withSource("owner", genericObjectClazz, Role.ONE)
      .withTarget("attrs", genericAttributeClazz, Role.MANY);
      
      Clazz genericLinkClazz = new Clazz("org.sdmlib.models.objects.GenericLink")
      .withAttribute("tgtLabel", "String")
      .withAttribute("srcLabel", "String");
      
      new Association()
      .withSource("src", genericObjectClazz, Role.ONE)
      .withTarget("outgoingLinks", genericLinkClazz, Role.MANY);
      
      new Association()
      .withSource("tgt", genericObjectClazz, Role.ONE)
      .withTarget("incommingLinks", genericLinkClazz, Role.MANY);
      
      scenario.addImage(model.dumpClassDiag("GenericObjectStructureClasses"));
      
     
      model.generate("src", "srchelpers");
      
      scenario.add("Step 2: We just build our example object structure with generic objects: ");
      
      GenericObject building = new GenericObject()
      .with("id", "WA73")
      .withName("WilliAllee")
      .withType("Building");

      GenericObject wa13 = new GenericObject()
      .with("id", "WA13")
      .with("level", "first floor")
      .withName("seFloor")
      .withType("Floor");
      
      new GenericLink()
      .withSrc(building)
      .withTgt(wa13)
      .withTgtLabel("has");
      
      GenericObject wa03 = new GenericObject()
      .with("id", "WA03")
      .with("level", "ground floor")
      .withName("digitalFloor")
      .withType("Floor");
      
      new GenericLink()
      .withSrc(building)
      .withTgt(wa03)
      .withTgtLabel("has");
      
      JsonIdMap jsonIdMap = CreatorCreator.createIdMap("go");
      scenario.addObjectDiag(jsonIdMap, building);

      scenario.add("Step 3: Then we tune our diagram dumper to show it as a non-generic object diagram: ");
      
      scenario.addGenericObjectDiag("specificgenericobjectdiag", building);
      
      scenario.add("Step 4: now we try to learn a class diagram from the generic object structure: ");
      
      ClassModel newModel = new ClassModel().learnFromGenericObjects("de.kassel.roombook", building);
      
      scenario.addImage(newModel.dumpClassDiag("DerivedFromGenericObjectsClassDiag"));
      
      scenario.add("Step 5: generate model creation code to allow the developer to adjust e.g. attribute types and associoation cardinalities: ",
         IMPLEMENTATION, "zuendorf", "28.05.2012 23:54:42", 0, 1);
      
      scenario.add("Step 6: generate code from the learned class diagram ");
      
      scenario.add("Step 7: derive non-generic objects from the generic objects ",
         IMPLEMENTATION, "zuendorf", "28.05.2012 23:55:42", 0, 2);
      
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }

   private static final String MODELING = "modeling";
   private static final String ACTIVE = "active";
   private static final String DONE = "done";
   private static final String IMPLEMENTATION = "implementation";
   private static final String BACKLOG = "backlog";
   private static final String BUG = "bug";
}


