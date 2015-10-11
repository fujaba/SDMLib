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
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.objects.Generic2Specific;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.objects.Specific2Generic;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.storyboards.Storyboard;

import de.kassel.test.roombook.Building;
import de.uniks.networkparser.json.JsonIdMap;

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
      Storyboard storyboard = new Storyboard();

      storyboard.add("Start situation: we do not yet have a class diagram but want to start with some example object models");


      storyboard.add("Step 1: We build a generic class model for object structures: ");

      storyboard.add("<embed type=\"image/svg+xml\" src='GenericGraphModelClassDiagram1.svg'>");

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

      // JsonIdMap jsonIdMap = GenericGraphCreator.createIdMap("go");
      // storyboard.withJsonIdMap(GenericGraphCreator.createIdMap("g"));
      storyboard.addObjectDiagram(graph);


      //====================================================================================================
      storyboard.add("Step 3: Then we tune our diagram dumper to show it as a non-generic object diagram: ");

      storyboard.addGenericObjectDiag("specificgenericobjectdiag", graph);


      //====================================================================================================
      storyboard.add("Step 4: now we try to learn a class diagram from the generic object structure: ");


      ClassModel learnedModel = new ClassModel().getGenerator()
            .learnFromGenericObjects("de.kassel.test.roombook", building);

      storyboard.addClassDiagram(learnedModel);

      //====================================================================================================
      storyboard.add("Step 5: generate model creation code to allow the developer to adjust e.g. attribute types and associoation cardinalities: ");

      storyboard.markCodeStart();
      ClassModel model = new ClassModel();

      Clazz buildingClass = model.createClazz("de.kassel.test.roombook.Building")
            .withAttribute("name", DataType.STRING);

      Clazz floorClass = model.createClazz("de.kassel.test.roombook.Floor")
            .withAttribute("level", DataType.INT)
            .withAttribute("name", DataType.STRING)
            /*add attribut*/
            .withAttribute("guest", DataType.STRING);    
      
      /* add assoc */
      buildingClass.withAssoc(floorClass, "has", Card.MANY, "buildings", Card.ONE);

      // FIXME: Alex
      // learnedModel.getGenerator().insertModelCreationCodeHere("examples");
      storyboard.addCode("examples");

      storyboard.addClassDiagram(model);

      //====================================================================================================
      storyboard.add("Step 6: generate code from the learned class diagram ");
      model.generate("src/test/java");

      //====================================================================================================
      storyboard.add("Step 7: derive non-generic objects from the generic objects ");

      JsonIdMap createIdMap = de.kassel.test.roombook.util.BuildingCreator.createIdMap("gen2spec");

      Building specificBuilding = (Building) new Generic2Specific().convert(createIdMap, "de.kassel.test.roombook", graph);

      storyboard.addObjectDiagram(specificBuilding);

      GenericGraph gengraph = new Specific2Generic()
      .convert(de.kassel.test.roombook.util.BuildingCreator.createIdMap("spec2gen"), specificBuilding);

      storyboard.addObjectDiagram(gengraph);

      storyboard.dumpHTML();
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

