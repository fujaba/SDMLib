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
   
package org.sdmlib.models.patterns;
   
import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.scenarios.LogEntry;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
   
public class PatternModelCodeGen 
{
   @Test
   public void testTransformationsCodegen()
   {
      Scenario scenario = new Scenario("test", "TransformationsCodegen");
      
      scenario.add("Start situation: ",
         BACKLOG, "zuendorf", "27.07.2012 13:27:59", 0, 0);
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }

   @Test
   public void testPatternModelCodegen()
   {
      Scenario scenario = new Scenario("test", "PatternModelCodegen");
      
      scenario.add("Start situation: ",
         MODELING, "zuendorf", "18.07.2012 15:30:28", 4, 4);
      
      ClassModel model = new ClassModel();
      
      Clazz patternElement = new Clazz("org.sdmlib.models.pattern.PatternElement")
      .withAttribute("modifier", "String")
      .withAttribute("hasMatch", "boolean", "false");
      
      Clazz pattern = new Clazz("org.sdmlib.models.pattern.Pattern")
      .withSuperClass(patternElement)
      .withAttribute("currentNAC", "NegativeApplicationCondition");
      
      Clazz NegativeApplicationCondition = new Clazz("org.sdmlib.models.pattern.NegativeApplicationCondition")
      .withSuperClass(pattern);
      
      new Association()
      .withTarget(patternElement, "elements", Role.MANY)
      .withSource(pattern, "pattern", Role.ONE);
      
      Clazz patternObject = new Clazz("org.sdmlib.models.pattern.PatternObject")
      .withSuperClass(patternElement)
      .withAttribute("currentMatch", "Object")
      .withAttribute("candidates", "Object");
      
      Clazz patternLink = new Clazz("org.sdmlib.models.pattern.PatternLink")
      .withSuperClass(patternElement)
      .withAttribute("tgtRoleName", "String")
      .withAttribute("hostGraphSrcObject", "Object");
      
      new Association()
      .withTarget(patternObject, "tgt", Role.ONE)
      .withSource(patternLink, "incomming", Role.MANY);
      
      new Association()
      .withTarget(patternObject, "src", Role.ONE)
      .withSource(patternLink, "outgoing", Role.MANY);
      
      Clazz attrConstraint = new Clazz("org.sdmlib.models.pattern.AttributeConstraint")
      .withSuperClass(patternElement)
      .withAttribute("attrName", "String")
      .withAttribute("tgtValue", "Object")
      .withAttribute("hostGraphSrcObject", "Object");
      
      new Association()
      .withTarget(patternObject, "src", Role.ONE)
      .withSource(attrConstraint, "attrConstraints", Role.MANY);
      
      Clazz linkConstraint = new Clazz("org.sdmlib.models.pattern.LinkConstraint")
      .withSuperClass(patternLink);
      
      
      
      model.generate("src", "srchelpers");
      
      scenario.addImage(model.dumpClassDiag("PatternModel01"));
           
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




