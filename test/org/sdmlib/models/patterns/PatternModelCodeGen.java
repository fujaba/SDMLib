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
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.creators.PatternSet;
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
      
      ClassModel model = new ClassModel("org.sdmlib.models.pattern");
      
      Clazz patternElement = new Clazz("PatternElement")
      .withAttribute("modifier", "String")
      .withAttribute("hasMatch", "boolean", "false")
      .withAttribute("patternObjectName", String.class.getSimpleName())
      .withAttribute("doAllMatches", boolean.class.getName());
      
      Clazz pattern = new Clazz("Pattern",
         "currentSubPattern", "Pattern",
         "debugMode", R.INT, 
         "trace", "StringBuilder",
         "name", R.STRING)
      .withSuperClass(patternElement);
      
      Clazz stringBuilderClazz = new Clazz(StringBuilder.class.getName()).withWrapped(true);
      
      Clazz NegativeApplicationCondition = new Clazz("NegativeApplicationCondition")
      .withSuperClass(pattern);
      
      Clazz OptionalSubPattern = new Clazz("OptionalSubPattern")
      .withSuperClass(pattern)
      .withAttribute("matchForward", boolean.class.getSimpleName());
      
      new Association()
      .withTarget(patternElement, "elements", R.MANY)
      .withSource(pattern, "pattern", R.ONE);
      
      Clazz patternObject = new Clazz("PatternObject")
      .withSuperClass(patternElement)
      .withAttributes("currentMatch", "Object", 
         "candidates", "Object");
      
      Clazz patternLink = new Clazz("PatternLink")
      .withSuperClass(patternElement)
      .withAttribute("tgtRoleName", "String")
      .withAttribute("hostGraphSrcObject", "Object");
      
//      new Association()
//      .withTarget(patternObject, "tgt", R.ONE)
//      .withSource(patternLink, "incomming", R.MANY);
//      
//      new Association()
//      .withTarget(patternObject, "src", R.ONE)
//      .withSource(patternLink, "outgoing", R.MANY);
      
      Clazz attrConstraint = new Clazz("AttributeConstraint")
      .withSuperClass(patternElement)
      .withAttribute("attrName", "String")
      .withAttribute("tgtValue", "Object")
      .withAttribute("cmpOp", R.STRING)
      .withAttribute("hostGraphSrcObject", "Object");
      
      new Association()
      .withTarget(patternObject, "src", R.ONE)
      .withSource(attrConstraint, "attrConstraints", R.MANY);
      
      Clazz linkConstraint = new Clazz("LinkConstraint")
      .withSuperClass(patternLink);
      
      Clazz matchIsomorphicConstraint = new Clazz("MatchIsomorphicConstraint")
      .withSuperClass(patternElement);
      
      Clazz cloneOp = new Clazz("CloneOp")
      .withSuperClass(patternElement);
      
      Clazz unifyGraphsOp = new Clazz("UnifyGraphsOp")
      .withSuperClass(patternElement);

      Clazz destroyObjectClazz = new Clazz("DestroyObjectElem")
      .withSuperClass(patternElement);
      
      new Association()
      .withTarget(patternObject, "patternObject", R.ONE)
      .withSource(destroyObjectClazz, "destroyElem", R.ONE);
      
      Clazz cardinalityConstraint = new Clazz("CardinalityConstraint")
      .withSuperClass(patternElement)
      .withAttributes("tgtRoleName", "String",
         "hostGraphSrcObject", "Object",
         "minCard", R.LONG,
         "maxCard", R.LONG)
      .withAssoc(patternObject, "src", R.ONE, "cardConstraints", R.MANY);
      
      Clazz matchOtherThen = new Clazz("MatchOtherThen")
      .withSuperClass(patternElement)
      .withAttributes("hostGraphSrcObject", "Object")
      .withAssoc(patternObject, "src", R.ONE, "matchOtherThen", R.MANY)
      .withAssoc(patternObject, "forbidden", R.ONE, "excluders", R.MANY);
      
      model.createClazz("org.sdmlib.serialization.json.JsonIdMap");
      
      model.createClazz(SDMLibJsonIdMap.class.getName());
      
      Clazz reachabilityGraph = model.createClazz("ReachabilityGraph");
      
      Clazz rState = reachabilityGraph.createClassAndAssoc("ReachableState", "states", R.MANY, "parent", R.ONE)
            .withAttributes("number", R.LONG, "graphRoot", "Object");
      
      Clazz ruleApplication = rState.createClassAndAssoc("RuleApplication", "ruleapplications", R.MANY, "src", R.ONE)
            .withAttributes("description", R.STRING);
      
      ruleApplication.withAssoc(rState, "tgt", R.ONE, "resultOf", R.MANY);
      
      reachabilityGraph.withAssoc(rState, "todo", R.MANY, "master", R.ONE);
      
      reachabilityGraph.withAssoc(pattern, "rules", R.MANY, "rgraph", R.ONE);
      
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




