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
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
   
public class PatternModelCodeGen 
{
   @Test
   public void testPatternModelCodegen()
   {
      Scenario scenario = new Scenario("test", "PatternModelCodegen");
      
      scenario.add("Start situation: ",
         DONE, "zuendorf", "19.08.2012 22:52:42", 80, 0);
      
      ClassModel model = new ClassModel();
      
      Clazz patternElement = new Clazz("org.sdmlib.models.pattern.PatternElement")
      .withAttribute("modifier", "String")
      .withAttribute("hasMatch", "boolean", "false")
      .withAttribute("patternObjectName", String.class.getSimpleName())
      .withAttribute("doAllMatches", boolean.class.getName());
      
      Clazz pattern = new Clazz("org.sdmlib.models.pattern.Pattern")
      .withSuperClass(patternElement)
      .withAttribute("currentSubPattern", "Pattern");
      
      Clazz NegativeApplicationCondition = new Clazz("org.sdmlib.models.pattern.NegativeApplicationCondition")
      .withSuperClass(pattern);
      
      Clazz OptionalSubPattern = new Clazz("org.sdmlib.models.pattern.OptionalSubPattern")
      .withSuperClass(pattern)
      .withAttribute("matchForward", boolean.class.getSimpleName());
      
      new Association()
      .withTarget(patternElement, "elements", R.MANY)
      .withSource(pattern, "pattern", R.ONE);
      
      Clazz patternObject = new Clazz("org.sdmlib.models.pattern.PatternObject")
      .withSuperClass(patternElement)
      .withAttribute("currentMatch", "Object")
      .withAttribute("candidates", "Object");
      
      Clazz patternLink = new Clazz("org.sdmlib.models.pattern.PatternLink")
      .withSuperClass(patternElement)
      .withAttribute("tgtRoleName", "String")
      .withAttribute("hostGraphSrcObject", "Object");
      
      new Association()
      .withTarget(patternObject, "tgt", R.ONE)
      .withSource(patternLink, "incomming", R.MANY);
      
      new Association()
      .withTarget(patternObject, "src", R.ONE)
      .withSource(patternLink, "outgoing", R.MANY);
      
      Clazz attrConstraint = new Clazz("org.sdmlib.models.pattern.AttributeConstraint")
      .withSuperClass(patternElement)
      .withAttribute("attrName", "String")
      .withAttribute("tgtValue", "Object")
      .withAttribute("hostGraphSrcObject", "Object");
      
      new Association()
      .withTarget(patternObject, "src", R.ONE)
      .withSource(attrConstraint, "attrConstraints", R.MANY);
      
      Clazz linkConstraint = new Clazz("org.sdmlib.models.pattern.LinkConstraint")
      .withSuperClass(patternLink);
      
      Clazz matchIsomorphicConstraint = new Clazz("org.sdmlib.models.pattern.MatchIsomorphicConstraint")
      .withSuperClass(patternElement);
      
      Clazz destroyObjectClazz = new Clazz("org.sdmlib.models.pattern.DestroyObjectElem")
      .withSuperClass(patternElement);
      
      new Association()
      .withTarget(patternObject, "patternObject", R.ONE)
      .withSource(destroyObjectClazz, "destroyElem", R.ONE);
      
      model.createClazz("org.sdmlib.serialization.json.JsonIdMap");
      
      model.createClazz(SDMLibJsonIdMap.class.getName());

      model.generate("src", "srchelpers");
      
      scenario.addImage(model.dumpClassDiag("src", "PatternModel01"));
           
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




