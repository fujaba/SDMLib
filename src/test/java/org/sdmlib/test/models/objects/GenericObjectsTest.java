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

package org.sdmlib.test.models.objects;

import java.beans.PropertyChangeSupport;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.objects.Generic2Specific;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.Specific2Generic;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.StoryboardImpl;
import org.sdmlib.test.examples.roombook.Building;
import org.sdmlib.test.examples.roombook.util.BuildingCreator;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class GenericObjectsTest implements PropertyChangeInterface 
{
   /**
    * 
    * @see <a href='../../../../../../../../doc/GenericObjectDiagram.html'>GenericObjectDiagram.html</a>
    */
   @Test
   public void testGenericObjectDiagram()
   {
      //====================================================================================================
      StoryboardImpl storyboard = new StoryboardImpl();

      storyboard.add("Start situation: we do not yet have a class diagram but want to start with some example object models");


      storyboard.add("Step 1: We build a generic class model for object structures: ");

      new GenericGraphModel().testGenericGraphModel();
      storyboard.addClassDiagram(GenericGraphModel.genericModel);

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

      // IdMap jsonIdMap = GenericGraphCreator.createIdMap("go");
      // storyboard.withJsonIdMap(GenericGraphCreator.createIdMap("g"));
      storyboard.addObjectDiagram(graph);


      //====================================================================================================
      storyboard.add("Step 3: Then we tune our diagram dumper to show it as a non-generic object diagram: ");

      storyboard.addGenericObjectDiag("specificgenericobjectdiag", graph);


      //====================================================================================================
      storyboard.add("Step 4: now we try to learn a class diagram from the generic object structure: ");


      ClassModel learnedModel = new ClassModel().getGenerator()
            .learnFromGenericObjects("org.sdmlib.test.examples.roombook", building);

      storyboard.addClassDiagram(learnedModel);

      //====================================================================================================
      storyboard.add("Step 5: generate model creation code to allow the developer to adjust e.g. attribute types and associoation cardinalities: ");

      storyboard.markCodeStart();
      ClassModel model = new ClassModel("org.sdmlib.test.examples.roombook");

      Clazz buildingClass = model.createClazz("org.sdmlib.test.examples.roombook.Building")
            .withAttribute("name", DataType.STRING);

      Clazz floorClass = model.createClazz("org.sdmlib.test.examples.roombook.Floor")
            .withAttribute("level", DataType.INT)
            .withAttribute("name", DataType.STRING)
            /*add attribut*/
            .withAttribute("guest", DataType.STRING);    

      /* add assoc */
      buildingClass.withBidirectional(floorClass, "has", Cardinality.MANY, "buildings", Cardinality.ONE);

      // FIXME: Alex
      // learnedModel.getGenerator().insertModelCreationCodeHere("examples");
      storyboard.addCode();

      storyboard.addClassDiagram(model);

      //====================================================================================================
      storyboard.add("Step 6: generate code from the learned class diagram ");
      model.generate("src/test/java");

      //====================================================================================================
      storyboard.add("Step 7: derive non-generic objects from the generic objects ");

      IdMap createIdMap = BuildingCreator.createIdMap("gen2spec");

      Building specificBuilding = (Building) new Generic2Specific().convert(createIdMap, "org.sdmlib.test.examples.roombook", graph);

      storyboard.addObjectDiagram(specificBuilding);

      GenericGraph gengraph = new Specific2Generic()
            .convert(BuildingCreator.createIdMap("spec2gen"), specificBuilding);

      storyboard.addObjectDiagram(gengraph);

      storyboard.dumpHTML();
   }


   //==========================================================================

   protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }


}

