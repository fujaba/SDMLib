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
   
import java.beans.PropertyChangeSupport;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.objects.creators.CreatorCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;
import org.sdmlib.utils.PropertyChangeInterface;

import de.kassel.roombook.Building;
   
public class GenericObjectsTest implements PropertyChangeInterface 
{
   @Test
   public void testGenericObjectDiagram()
   {
      //====================================================================================================
      Storyboard storyboard = new Storyboard("test", "GenericObjectDiagram");
      
      storyboard.add("Start situation: we do not yet have a class diagram but want to start with some example object models",
         DONE, "zuendorf", "28.05.2012 23:51:42", 4, 0);
      
      
      storyboard.add("Step 1: We build a generic class model for object structures: ");

      ClassModel genericModel = new ClassModel("org.sdmlib.models.objects");
      
      Clazz genericGraph = new Clazz("org.sdmlib.models.objects.GenericGraph");
      
      Clazz genericObjectClazz = new Clazz("org.sdmlib.models.objects.GenericObject")
      .withAttribute("name", String.class.getSimpleName())
      .withAttribute("type", String.class.getSimpleName())
      .withAttribute("icon", String.class.getSimpleName());
      
      new Association()
      .withTarget(genericObjectClazz, "objects", R.MANY)
      .withSource(genericGraph, "graph", R.ONE);
      
      Clazz genericAttributeClazz = new Clazz("org.sdmlib.models.objects.GenericAttribute")
      .withAttribute("name", String.class.getSimpleName())
      .withAttribute("value", String.class.getSimpleName());
      
      new Association()
      .withSource("owner", genericObjectClazz, R.ONE)
      .withTarget("attrs", genericAttributeClazz, R.MANY);
      
      Clazz genericLinkClazz = new Clazz("org.sdmlib.models.objects.GenericLink")
      .withAttribute("tgtLabel", "String")
      .withAttribute("srcLabel", "String");
      
      new Association()
      .withSource("src", genericObjectClazz, R.ONE)
      .withTarget("outgoingLinks", genericLinkClazz, R.MANY);
      
      new Association()
      .withSource("tgt", genericObjectClazz, R.ONE)
      .withTarget("incommingLinks", genericLinkClazz, R.MANY);
      
      new Association()
      .withTarget(genericLinkClazz, "links", R.MANY)
      .withSource(genericGraph, "graph", R.ONE);
      
      storyboard.addSVGImage(genericModel.dumpClassDiagram("src", "GenericObjectStructureClasses"));
     
      // genericModel.removeAllGeneratedCode("test", "src", "srchelpers");
     
      genericModel.generate("src", "srchelpers");
      
      
      //====================================================================================================
      storyboard.add("Step 2: We just build our example object structure with generic objects: ");
      
      GenericGraph graph = new GenericGraph();
      
      GenericObject building = graph.createObjects()
      .with("name", "WA73")
      .withName("WilliAllee")
      .withType("Building");

      GenericObject wa13 = graph.createObjects()
      .with("name", "WA13")
      .with("level", "1")
      .withName("seFloor")
      .withType("Floor");
      
      graph.createLinks()
      .withSrc(building)
      .withTgt(wa13)
      .withTgtLabel("has");
      
      GenericObject wa03 = graph.createObjects()
      .with("name", "WA03")
      .with("level", "0")
      .with("guest","Ulrich")
      .withName("digitalFloor")
      .withType("Floor");
      
      graph.createLinks()
      .withSrc(building)
      .withTgt(wa03)
      .withTgtLabel("has");
      
      JsonIdMap jsonIdMap = CreatorCreator.createIdMap("go");
      storyboard.addObjectDiagram(jsonIdMap, graph);

      
      //====================================================================================================
      storyboard.add("Step 3: Then we tune our diagram dumper to show it as a non-generic object diagram: ");
      
      storyboard.addGenericObjectDiag("specificgenericobjectdiag", graph);
      
      
      //====================================================================================================
      storyboard.add("Step 4: now we try to learn a class diagram from the generic object structure: ");
      
      ClassModel learnedModel = new ClassModel().learnFromGenericObjects("de.kassel.roombook", building);
      
      storyboard.addSVGImage(learnedModel.dumpClassDiagram("test", "DerivedFromGenericObjectsClassDiag"));
      
      //====================================================================================================
      storyboard.add("Step 5: generate model creation code to allow the developer to adjust e.g. attribute types and associoation cardinalities: ",
         DONE, "zuendorf", "31.05.2012 13:51:42", 1, 0);
      
      storyboard.markCodeStart();
      ClassModel model = new ClassModel();

      Clazz buildingClass = new Clazz("de.kassel.roombook.Building")
      .withAttribute("name", "String");

      Clazz floorClass = new Clazz("de.kassel.roombook.Floor")
      .withAttribute("level", "int")
		.withAttribute("name", "String")
			/*add attribut*/
      .withAttribute("guest", "String"); // changed attr type to int
		
      new Association()
			.withSource("buildings", buildingClass, R.ONE) // changed cardinality to ONE
			.withTarget("has", floorClass, R.MANY);

      learnedModel.insertModelCreationCodeHere("test");
      storyboard.addCode("test");
      
      storyboard.addSVGImage(model.dumpClassDiagram("test", "ManuallyImprovedLearnedClassDiag"));
      
      //====================================================================================================
      storyboard.add("Step 6: generate code from the learned class diagram ");
      model.generate("test", "test");
      
      //====================================================================================================
      storyboard.add("Step 7: derive non-generic objects from the generic objects ",
         DONE, "zuendorf", "31.05.2012 15:15:42", 1, 0);
      
      JsonIdMap createIdMap = de.kassel.roombook.creators.CreatorCreator.createIdMap("gen2spec");
      
      Building specificBuilding = (Building) new Generic2Specific().convert(createIdMap, "de.kassel.roombook", graph);
      
      storyboard.addObjectDiagram(createIdMap, specificBuilding);
      
      storyboard.add("BUG REPORT: if an object has a (String) attribute with name 'id', this attribute is not shown in the object diagram ",
         BUG, "zuendorf", "31.05.2012 15:22:42", 0, 0);
      
      storyboard.add("New Feature: just for completeness and for later model migration provide a conversion from a specific model to a generic model ",
         IMPLEMENTATION, "zuendorf", "05.08.2012 15:43:42", 4, 0);
      
      GenericGraph gengraph = new Specific2Generic()
      .convert(de.kassel.roombook.creators.CreatorCreator.createIdMap("spec2gen"), specificBuilding);
      
      storyboard.addObjectDiagram(CreatorCreator.createIdMap("go"), gengraph);

      StoryboardManager.get()
      .add(storyboard)
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

