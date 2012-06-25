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
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;

import de.kassel.roombook.Building;

import java.beans.PropertyChangeSupport;
   
public class GenericObjectsTest implements PropertyChangeInterface 
{
   @Test
   public void testGenericObjectDiagram()
   {
      //====================================================================================================
      Scenario scenario = new Scenario("test", "GenericObjectDiagram");
      
      scenario.add("Start situation: we do not yet have a class diagram but want to start with some example object models",
         DONE, "zuendorf", "28.05.2012 23:51:42", 4, 0);
      
      scenario.add("Step 1: We build a generic class model for object structures: ");

      ClassModel genericModel = new ClassModel();
      
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
      
      scenario.addImage(genericModel.dumpClassDiag("GenericObjectStructureClasses"));
      
     
      genericModel.generate("src", "srchelpers");
      
      //====================================================================================================
      scenario.add("Step 2: We just build our example object structure with generic objects: ");
      
      GenericObject building = new GenericObject()
      .with("name", "WA73")
      .withName("WilliAllee")
      .withType("Building");

      GenericObject wa13 = new GenericObject()
      .with("name", "WA13")
      .with("level", "1")
      .withName("seFloor")
      .withType("Floor");
      
      new GenericLink()
      .withSrc(building)
      .withTgt(wa13)
      .withTgtLabel("has");
      
      GenericObject wa03 = new GenericObject()
      .with("name", "WA03")
      .with("level", "0")
      .withName("digitalFloor")
      .withType("Floor");
      
      new GenericLink()
      .withSrc(building)
      .withTgt(wa03)
      .withTgtLabel("has");
      
      JsonIdMap jsonIdMap = CreatorCreator.createIdMap("go");
      scenario.addObjectDiag(jsonIdMap, building);

      //====================================================================================================
      scenario.add("Step 3: Then we tune our diagram dumper to show it as a non-generic object diagram: ");
      
      scenario.addGenericObjectDiag("specificgenericobjectdiag", building);
      
      //====================================================================================================
      scenario.add("Step 4: now we try to learn a class diagram from the generic object structure: ");
      
      ClassModel learnedModel = new ClassModel().learnFromGenericObjects("de.kassel.roombook", building);
      
      scenario.addImage(learnedModel.dumpClassDiag("DerivedFromGenericObjectsClassDiag"));
      
      //====================================================================================================
      scenario.add("Step 5: generate model creation code to allow the developer to adjust e.g. attribute types and associoation cardinalities: ",
         DONE, "zuendorf", "31.05.2012 13:51:42", 1, 0);
      
      scenario.markCodeStart();
      ClassModel model = new ClassModel();

      Clazz buildingClass = new Clazz("de.kassel.roombook.Building")
      .withAttribute("name", "String");

      Clazz floorClass = new Clazz("de.kassel.roombook.Floor")
      .withAttribute("level", "int")
		.withAttribute("name", "String"); // changed attr type to int
		
      new Association()
			.withSource("buildings", buildingClass, Role.ONE) // changed cardinality to ONE
			.withTarget("has", floorClass, "many");

      learnedModel.insertModelCreationCodeHere("test");
      scenario.addCode("test");
      
      scenario.addImage(model.dumpClassDiag("ManuallyImprovedLearnedClassDiag"));
      
      //====================================================================================================
      scenario.add("Step 6: generate code from the learned class diagram ");
      model.generate("test", "test");
      
      //====================================================================================================
      scenario.add("Step 7: derive non-generic objects from the generic objects ",
         DONE, "zuendorf", "31.05.2012 15:15:42", 1, 0);
      
      JsonIdMap createIdMap = de.kassel.roombook.creators.CreatorCreator.createIdMap("gen2spec");
      
      Building specificBuilding = (Building) new Generic2Specific().convert(createIdMap, "de.kassel.roombook", building);
      
      scenario.addObjectDiag(createIdMap, specificBuilding);
      scenario.add("BUG REPORT: if an object has a (String) attribute with name 'id', this attribute is not shown in the object diagram ",
         BUG, "zuendorf", "31.05.2012 15:22:42", 0, 0);
      
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

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      return false;
   }

   
   //==========================================================================
   
   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
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

