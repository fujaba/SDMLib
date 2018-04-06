/*
   Copyright (c) 2013 ulno (http://contact.ulno.net) 

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
package org.sdmlib.test.examples.studyrightWithAssignments;

import static org.sdmlib.models.pattern.Pattern.CREATE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.CGUtil;
import org.sdmlib.models.SDMLibIdMap;
import org.sdmlib.models.YamlIdMap;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.pattern.Match;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.models.pattern.util.ReachabilityGraphCreator;
import org.sdmlib.models.pattern.util.ReachabilityGraphPO;
import org.sdmlib.models.pattern.util.ReachableStatePO;
import org.sdmlib.models.pattern.util.ReachableStateSet;
import org.sdmlib.models.pattern.util.RuleApplicationSet;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.tables.Table;
import org.sdmlib.models.tables.util.CellPO;
import org.sdmlib.models.tables.util.ColumnPO;
import org.sdmlib.models.tables.util.RowPO;
import org.sdmlib.models.tables.util.TablePO;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.test.examples.studyrightWithAssignments.model.President;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Prof;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.AssignmentPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.AssignmentSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.RoomSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.TeachingAssistantPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.TeachingAssistantSet;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversityCreator;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversityPO;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversitySet;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.list.SimpleSet;

public class StudyRightWithAssignmentsStoryboards
{
     /**
    * 
    * <p>Start: Read graph from yaml text:</p>
    * <pre>- studyRight: University 
    *   name:       &quot;\&quot;Study \&quot; Right\&quot;And\&quot;Fast now\&quot;&quot;
    *   students:   karli
    *   rooms:      mathRoom artsRoom sportsRoom examRoom softwareEngineering 
    * 
    * - karli: Student
    *   id:    4242
    *   name:  karli
    * 
    * - albert: Prof
    *   topic:  SE
    * 
    * - Assignment   content:                      points: 
    *   matrixMult:  &quot;Matrix Multiplication&quot;     5
    *   series:      &quot;Series&quot;                    6
    *   a3:          Integrals                     8
    * 
    * - Room                  topic:  credits: doors:                 students: assignments: 
    *   mathRoom:             math    17       null                   karli     [matrixMult series a3]
    *   artsRoom:             arts    16       mathRoom               null      null
    *   sportsRoom:           sports  25       [mathRoom artsRoom]
    *   examRoom:             exam     0       [sportsRoom artsRoom]
    *   softwareEngineering:  &quot;Software Engineering&quot; 42 [artsRoom examRoom]
    * </pre>
    * <p><a name = 'step_1'>Step 1: Call YamlIdMap.decode:</a></p><pre>            YamlIdMap yamlIdMap = new YamlIdMap(&quot;org.sdmlib.test.examples.studyrightWithAssignments.model&quot;);
    *       
    *       University studyRight = (University) yamlIdMap.decode(yaml);
    * </pre>
    * <p><a name = 'step_2'>Step 2: Decoded object structure:</a></p><script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"A10 : Assignment",
    *          "attributes":[
    *             "content=Integrals",
    *             "points=8"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"A8 : Assignment",
    *          "attributes":[
    *             "content=Matrix Multiplication",
    *             "points=5"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"A9 : Assignment",
    *          "attributes":[
    *             "content=Series",
    *             "points=6"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R3 : Room",
    *          "attributes":[
    *             "credits=17",
    *             "name=null",
    *             "topic=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Room",
    *          "attributes":[
    *             "credits=16",
    *             "name=null",
    *             "topic=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R5 : Room",
    *          "attributes":[
    *             "credits=25",
    *             "name=null",
    *             "topic=sports"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R6 : Room",
    *          "attributes":[
    *             "credits=0",
    *             "name=null",
    *             "topic=exam"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R7 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "name=null",
    *             "topic=Software Engineering"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S2 : Student",
    *          "attributes":[
    *             "assignmentPoints=0",
    *             "credits=0",
    *             "id=4242",
    *             "motivation=0",
    *             "name=karli"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=\"Study \" Right\"And\"Fast now\"",
    *             "president=null"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"assignments",
    *             "id":"A8 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"room",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"assignments",
    *             "id":"A9 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"room",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"assignments",
    *             "id":"A10 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"room",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R4 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R4 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R7 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R4 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R5 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R7 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R6 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S2 : Student"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R7 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S2 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U1 : University"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasYaml6", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Check: root object exists "Study " Right"And"Fast now"</p>
    * <p>Check: pojo albert exists org.sdmlib.test.examples.studyrightWithAssignments.model.Prof@1cab0bfb</p>
    * <p>Check: pojo attr SE actual SE</p>
    * <p><a name = 'step_3'>Step 3: Generate Yaml from model:</a></p><pre>- u1: 	University
    *   name: 	&quot;\&quot;Study \&quot; Right\&quot;And\&quot;Fast now\&quot;&quot;
    *   students: 	s2 	
    *   rooms: 	r3 	r4 	r5 	r6 	r7 	
    * 
    * - s2: 	Student
    *   name: 	karli
    *   id: 	4242
    *   assignmentPoints: 	0
    *   motivation: 	0
    *   credits: 	0
    *   university: 	u1
    *   in: 	r3
    * 
    * - r3: 	Room
    *   topic: 	math
    *   credits: 	17
    *   university: 	u1
    *   students: 	s2 	
    *   doors: 	r4 	r5 	
    *   assignments: 	a8 	a9 	a10 	
    * 
    * - r4: 	Room
    *   topic: 	arts
    *   credits: 	16
    *   university: 	u1
    *   doors: 	r3 	r5 	r6 	r7 	
    * 
    * - r5: 	Room
    *   topic: 	sports
    *   credits: 	25
    *   university: 	u1
    *   doors: 	r3 	r4 	r6 	
    * 
    * - r6: 	Room
    *   topic: 	exam
    *   credits: 	0
    *   university: 	u1
    *   doors: 	r5 	r4 	r7 	
    * 
    * - r7: 	Room
    *   topic: 	&quot;Software Engineering&quot;
    *   credits: 	42
    *   university: 	u1
    *   doors: 	r4 	r6 	
    * 
    * - a8: 	Assignment
    *   content: 	&quot;Matrix Multiplication&quot;
    *   points: 	5
    *   room: 	r3
    * 
    * - a9: 	Assignment
    *   content: 	Series
    *   points: 	6
    *   room: 	r3
    * 
    * - a10: 	Assignment
    *   content: 	Integrals
    *   points: 	8
    *   room: 	r3
    * 
    * </pre>
    * <p>Check: yaml starts with - u... true</p>
    * <p><a name = 'step_4'>Step 4: decoded again:</a></p><script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"A18 : Assignment",
    *          "attributes":[
    *             "content=Matrix Multiplication",
    *             "points=5"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"A19 : Assignment",
    *          "attributes":[
    *             "content=Series",
    *             "points=6"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"A20 : Assignment",
    *          "attributes":[
    *             "content=Integrals",
    *             "points=8"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R13 : Room",
    *          "attributes":[
    *             "credits=17",
    *             "name=null",
    *             "topic=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R14 : Room",
    *          "attributes":[
    *             "credits=16",
    *             "name=null",
    *             "topic=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R15 : Room",
    *          "attributes":[
    *             "credits=25",
    *             "name=null",
    *             "topic=sports"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R16 : Room",
    *          "attributes":[
    *             "credits=0",
    *             "name=null",
    *             "topic=exam"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R17 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "name=null",
    *             "topic=Software Engineering"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S12 : Student",
    *          "attributes":[
    *             "assignmentPoints=0",
    *             "credits=0",
    *             "id=4242",
    *             "motivation=0",
    *             "name=karli"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U11 : University",
    *          "attributes":[
    *             "name=\"Study \" Right\"And\"Fast now\"",
    *             "president=null"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"assignments",
    *             "id":"A18 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"room",
    *             "id":"R13 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"assignments",
    *             "id":"A19 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"room",
    *             "id":"R13 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"assignments",
    *             "id":"A20 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"room",
    *             "id":"R13 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R14 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R13 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R15 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R13 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R15 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R14 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R16 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R14 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R17 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R14 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R16 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R15 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R17 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R16 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R13 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S12 : Student"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R13 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U11 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R14 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U11 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R15 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U11 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R16 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U11 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R17 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U11 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S12 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U11 : University"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasYaml14", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p><a name = 'step_5'>Step 5: now read from excel file</a></p><pre>      
    *       story.addPattern(roomPO, false);
    * 
    *       story.addObjectDiagramOnlyWith(mathRoom, mathRoom.getDoors(), mathRoom.getStudents());
    * 
    *       story.assertEquals(&quot;New students in math room: &quot;, 3, mathRoom.getStudents().size());
    * </pre>
    * <p>doc/StudyRightStartSituation.txt</p>
    * <pre>-	studyRight:	University				
    * 	name: 	&quot;&quot;&quot;Study Right&quot;&quot;&quot;				
    * 	students:	karli				
    * 	rooms: 	mathRoom	artsRoom	sportsRoom	examRoom	softwareEngineering
    * 						
    * -	karli:	Student				
    * 	id:	4242				
    * 	name:	karli				
    * 						
    * -	albert: 	Prof				
    * 	topic:	SE				
    * 						
    * -	Assignment	content:	points:			
    * 	matrixMult:	&quot;&quot;&quot;Matrix Multiplication&quot;&quot;&quot;	5			
    * 	series:	Series	6			
    * 	a3:	Integrals	8			
    * 						
    * -	Room	topic:	credits:	doors:	students:	assignments:
    * 	mathRoom:	math	17	null	karli	[matricMult series a3]
    * 	artsRoom:	arts	25	mathRoom		
    * 	sportsRoom:	sports	25	[mathRoom artsRoom]		
    * 	examRoom:	exam	0	[sportsRoom artsRoom]		
    * 	softwareEngineering:	&quot;&quot;&quot;Software Engineering&quot;&quot;&quot;	42	[artsRoom examRoom]		
    * </pre>
    * <p>result:</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"A28 : Assignment",
    *          "attributes":[
    *             "content=Series",
    *             "points=6"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"A29 : Assignment",
    *          "attributes":[
    *             "content=Integrals",
    *             "points=8"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R23 : Room",
    *          "attributes":[
    *             "credits=17",
    *             "name=null",
    *             "topic=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R24 : Room",
    *          "attributes":[
    *             "credits=25",
    *             "name=null",
    *             "topic=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R25 : Room",
    *          "attributes":[
    *             "credits=25",
    *             "name=null",
    *             "topic=sports"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R26 : Room",
    *          "attributes":[
    *             "credits=0",
    *             "name=null",
    *             "topic=exam"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R27 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "name=null",
    *             "topic=\"\"Software Engineering\"\""
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S22 : Student",
    *          "attributes":[
    *             "assignmentPoints=0",
    *             "credits=0",
    *             "id=4242",
    *             "motivation=0",
    *             "name=karli"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U21 : University",
    *          "attributes":[
    *             "name=\"\"Study Right\"\"",
    *             "president=null"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"assignments",
    *             "id":"A28 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"room",
    *             "id":"R23 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"assignments",
    *             "id":"A29 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"room",
    *             "id":"R23 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R24 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R23 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R25 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R23 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R25 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R24 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R26 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R24 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R27 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R24 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R26 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R25 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R27 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"doors",
    *             "id":"R26 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R23 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S22 : Student"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R23 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U21 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R24 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U21 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R25 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U21 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R26 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U21 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R27 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U21 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S22 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U21 : University"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasYaml20", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @throws IOException 
    * @see <a href='../../../../../../../../doc/Yaml.html'>Yaml.html</a>
 */
   @Test
   public void testYaml() throws IOException
   {
      
      System.out.println(" (StudyRightWithAssignmentsStoryboards.java:85)");
      
      Storyboard story = new Storyboard();

      story.addStep("Read graph from yaml text:");

      String yaml = ""
         + "- studyRight: University \n"
         + "  name:       \"\\\"Study \\\" Right\\\"And\\\"Fast now\\\"\"\n"
         + "  students:   karli\n"
         + "  rooms:      mathRoom artsRoom sportsRoom examRoom softwareEngineering \n"
         + "\n"
         + "- karli: Student\n"
         + "  id:    4242\n"
         + "  name:  karli\n"
         + "\n"
         + "- albert: Prof\n"
         + "  topic:  SE\n"
         + "\n"
         + "- Assignment   content:                      points: \n"
         + "  matrixMult:  \"Matrix Multiplication\"     5\n"
         + "  series:      \"Series\"                    6\n"
         + "  a3:          Integrals                     8\n"
         + "\n"
         + "- Room                  topic:  credits: doors:                 students: assignments: \n"
         + "  mathRoom:             math    17       null                   karli     [matrixMult series a3]\n"
         + "  artsRoom:             arts    16       mathRoom               null      null\n"
         + "  sportsRoom:           sports  25       [mathRoom artsRoom]\n"
         + "  examRoom:             exam     0       [sportsRoom artsRoom]\n"
         + "  softwareEngineering:  \"Software Engineering\" 42 [artsRoom examRoom]\n"
         + "";

      story.addPreformatted(yaml);
      
      story.addStep("Call YamlIdMap.decode:");
      
      story.markCodeStart();
      YamlIdMap yamlIdMap = new YamlIdMap("org.sdmlib.test.examples.studyrightWithAssignments.model");
      
      University studyRight = (University) yamlIdMap.decode(yaml);
      story.addCode();
      
      story.addStep("Decoded object structure:");
      
      story.addObjectDiagram(studyRight);
      
      story.assertNotNull("root object exists", studyRight);
      
      Object albert = yamlIdMap.getObject("albert");
      
      story.assertNotNull("pojo albert exists", albert);
      
      story.assertEquals("pojo attr", "SE", ((Prof)albert).getTopic());
      
      story.addStep("Generate Yaml from model:");
      
      YamlIdMap yamlEncodeMap = new YamlIdMap("org.sdmlib.test.examples.studyrightWithAssignments.model");
      
      String newYaml = yamlEncodeMap.encode(studyRight);
      
      story.addPreformatted(newYaml);
      
      story.assertTrue("yaml starts with - u...", newYaml.startsWith("- u"));
      
      YamlIdMap yamlDecodeMap = new YamlIdMap("org.sdmlib.test.examples.studyrightWithAssignments.model");
      
      University newStudyRight = (University) yamlIdMap.decode(newYaml);
      
      story.addStep("decoded again:");
      
      story.addObjectDiagram(newStudyRight);
      
      story.addStep("now read from excel file");
      
      story.markCodeStart();
      byte[] readAllBytes = Files.readAllBytes(Paths.get("doc/StudyRightStartSituation.txt"));
      String excelText = new String(readAllBytes);
      
      YamlIdMap excelIdMap = new YamlIdMap("org.sdmlib.test.examples.studyrightWithAssignments.model");
      
      studyRight = (University) excelIdMap.decode(excelText);
      story.addCode();
      
      story.add("doc/StudyRightStartSituation.txt");
      story.addPreformatted(excelText);
      story.add("result:");
      story.addObjectDiagram(studyRight);
      story.dumpJavaDoc(YamlIdMap.class.getName());
      story.dumpHTML();
   }

   /**
    * @see <a href='../../../../../../../../doc/StudyRightWithAssignmentsStoryboard.html'>StudyRightWithAssignmentsStoryboard.html</a>
    */
   @Test
   public void testStudyRightWithAssignmentsStoryboard()
   {
      Storyboard storyboard = new Storyboard();

      // =============================================================
      storyboard.add("1. (start situation/pre-condition) Karli enters the Study-Right University \n"
         + "in the math room. Karli has no credits yet and still a motivation of 214. ");

      storyboard.markCodeStart();
      University university = new University()
         .withName("StudyRight");

      Student karli = university.createStudents()
         .withId("4242")
         .withName("Karli");

      Assignment matrixMult = new Assignment()
         .withContent("Matrix Multiplication")
         .withPoints(5);

      Assignment series = new Assignment()
         .withContent("Series")
         .withPoints(6);

      Assignment a3 = new Assignment()
         .withContent("Integrals")
         .withPoints(8);

      Room mathRoom = university.createRooms()
         .withName("senate")
         .withTopic("math")
         .withCredits(17)
         .withStudents(karli)
         .withAssignments(matrixMult, series, a3);

      Room artsRoom = university.createRooms()
         .withName("7522")
         .withTopic("arts")
         .withCredits(16)
         .withDoors(mathRoom);

      Room sportsRoom = university.createRooms()
         .withName("gymnasium")
         .withTopic("sports")
         .withCredits(25)
         .withDoors(mathRoom, artsRoom);

      Room examRoom = university.createRooms()
         .withName("The End")
         .withTopic("exam")
         .withCredits(0)
         .withDoors(sportsRoom, artsRoom);

      Room softwareEngineering = university.createRooms()
         .withName("7422")
         .withTopic("Software Engineering")
         .withCredits(42)
         .withDoors(artsRoom, examRoom);
      storyboard.addCode();

      storyboard.addObjectDiagram(
         "studyRight", university,
         "karli", "icons/karli.png", karli,
         "mathRoom", "icons/mathroom.png", mathRoom,
         "artsRoom", artsRoom,
         "sportsRoom", sportsRoom,
         "examRoom", examRoom,
         "placeToBe", softwareEngineering,
         "icons/matrix.png", matrixMult,
         "icons/limes.png", series, "icons/integralAssignment.png", a3);

      // ===============================================================================================
      storyboard.add("2. Karli does assignment a1 on Matrix Multiplication and earns 5 points <br>\n"
         + "(general rule: the student earns always full points for doing an assignment). <br>\n"
         + "Karli's motivation is reduced by 5 points to now 209.\n");

      storyboard.markCodeStart();
      karli.setAssignmentPoints(karli.getAssignmentPoints() + matrixMult.getPoints());
      karli.withDone(matrixMult);
      storyboard.addCode();

      storyboard.addObjectDiagramOnlyWith(karli, mathRoom, mathRoom.getAssignments());

      // ===============================================================================================
      storyboard.add("3. Karli does assignment a2 on Series and earns another 6 points. <br>\n"
         + "Thus Karli has 11 points now. Motivation is reduced to 203.\n");

      storyboard.markCodeStart();
      karli.setAssignmentPoints(karli.getAssignmentPoints() + series.getPoints());
      karli.withDone(series);
      storyboard.addCode();

      storyboard.addObjectDiagramOnlyWith(karli, mathRoom, mathRoom.getAssignments());

      // ===============================================================================================
      storyboard.add("4. Karli does the third assignment on Integrals, earns <br>\n"
         + "another 8 points and thus Karli has now 19 points and a motivation of 195.\n");

      storyboard.markCodeStart();
      karli.setAssignmentPoints(karli.getAssignmentPoints() + a3.getPoints());
      karli.withDone(a3);
      storyboard.addCode();

      storyboard.addObjectDiagramOnlyWith(karli, mathRoom, mathRoom.getAssignments());

      // ===============================================================================================
      storyboard.add("5. Since 19 points are more than the 17 points required \n"
         + "for the 17 math credits, Karli hands the points in and earns the credits \n"
         + "and has his assignmnet points reset to 0. <br>\n"
         + "(General rule: if the points earned by the assignments are higher or equal than \n"
         + "the credit points, the credit points will be awarded to the student.)");

      storyboard.markCodeStart();
      if (karli.getAssignmentPoints() >= mathRoom.getCredits())
      {
         karli.setCredits(karli.getCredits() + mathRoom.getCredits());
         karli.setAssignmentPoints(0);
      }
      storyboard.addCode();

      storyboard.addObjectDiagramOnlyWith(karli, mathRoom, mathRoom.getAssignments());

      // ===============================================================================================
      storyboard.add("6. (end situation/post-condition) Karli has completed the math topic and moves to sports.");

      // ===============================================================================================
      storyboard.assertEquals("Karli's credits: ", 17, karli.getCredits());
      storyboard.assertEquals("Karli's assignment points: ", 0, karli.getAssignmentPoints());
      storyboard.assertEquals("Number of students: ", 1, university.getStudents().size());

      // ================ Create HTML
      storyboard.dumpHTML();
   }


   @Test
   public void testStudyRightWithAssignmentsAggregation()
   {
      University university = new University()
            .withName("StudyRight");

         Student abu = university.createStudents()
            .withId("1337")
            .withName("Abu");

         Student karli = new TeachingAssistant().withCertified(true);
         university.withStudents(karli
            .withId("4242")
            .withName("Karli"));

         Student alice = university.createStudents()
            .withId("2323")
            .withName("Alice");

         abu.withFriends(alice);

         Assignment a1 = new Assignment()
            .withContent("Matrix Multiplication")
            .withPoints(5)
            .withStudents(abu);

         Assignment a2 = new Assignment()
            .withContent("Series")
            .withPoints(6);

         Assignment a3 = new Assignment()
            .withContent("Integrals")
            .withPoints(8);

         karli.withDone(a1, a2);

         Room mathRoom = university.createRooms()
            .withName("senate")
            .withTopic("math")
            .withCredits(17)
            .withStudents(karli)
            .withAssignments(a1, a2, a3);

         Room artsRoom = university.createRooms()
            .withName("7522")
            .withTopic("arts")
            .withCredits(16)
            .withDoors(mathRoom);

         Room sportsRoom = university.createRooms()
            .withName("gymnasium")
            .withTopic("sports")
            .withCredits(25)
            .withDoors(mathRoom, artsRoom)
            .withStudents(abu, alice);

         Assignment a4 = sportsRoom.createAssignments().withContent("Pushups").withPoints(4).withStudents(abu);

         Room examRoom = university.createRooms()
            .withName("The End")
            .withTopic("exam")
            .withCredits(0)
            .withDoors(sportsRoom, artsRoom);

         Room softwareEngineering = university.createRooms()
            .withName("7422")
            .withTopic("Software Engineering")
            .withCredits(42)
            .withDoors(artsRoom, examRoom);
         
         President president = university.createPresident();
         
         Assert.assertEquals("presidents lives", true, president.alive);
         
         university.removeYou();
         
         Assert.assertEquals("studyright has no more rooms", 0, university.getRooms().size());
         Assert.assertEquals("karli still has assignments", 2, karli.getDone().size());
         Assert.assertEquals("presidents is dead", false, president.alive);
         
   }

   /**
    * @see <a href='../../../../../../../../doc/JsonPersistency.html'>JsonPersistency.html</a>
    */
   @Test
   public void testJsonPersistency()
   {
      Storyboard storyboard = new Storyboard();

      storyboard.add("How to serialize an object model to json and how to read json into an object model");

      storyboard.addStep("Example object structure:");

      University university = new University()
         .withName("StudyRight");

      Student karli = university.createStudents()
         .withId("4242")
         .withName("Karli");

      Assignment a1 = new Assignment()
         .withContent("Matrix Multiplication")
         .withPoints(5);

      Assignment a2 = new Assignment()
         .withContent("Series")
         .withPoints(6);

      Assignment integrals = new Assignment()
         .withContent("Integrals")
         .withPoints(8);

//      Room mathRoom = university.createRooms()
//         .withName("senate")
//         .withTopic("math")
//         .withCredits(17)
//         .withStudents(karli)
//         .withAssignments(a1, a2, integrals);

//      Room artsRoom = university.createRooms()
//         .withName("7522")
//         .withTopic("arts")
//         .withCredits(16)
//         .withDoors(mathRoom);
//
//      Room sportsRoom = university.createRooms()
//         .withName("gymnasium")
//         .withTopic("sports")
//         .withCredits(25)
//         .withDoors(mathRoom, artsRoom);

//      Room examRoom = university.createRooms()
//         .withName("The End")
//         .withTopic("exam")
//         .withCredits(0)
//         .withDoors(sportsRoom, artsRoom);
//
//      Room softwareEngineering = university.createRooms()
//         .withName("7422")
//         .withTopic("Software Engineering")
//         .withCredits(42)
//         .withDoors(artsRoom, examRoom);

//      storyboard.addObjectDiagram(
//         "studyRight", university,
//         "karli", "icons/karli.png", karli,
//         "mathRoom", "icons/mathroom.png", mathRoom,
//         "artsRoom", artsRoom,
//         "sportsRoom", sportsRoom,
//         "examRoom", examRoom,
//         "placeToBe", softwareEngineering,
//         "icons/matrix.png", a1,
//         "icons/limes.png", a2, "icons/integralAssignment.png", integrals);

      // =====================================================
      storyboard.addStep("Serialize to json:");

      storyboard.markCodeStart();

      IdMap idMap = UniversityCreator.createIdMap("demo");

      JsonArray jsonArray = idMap.toJsonArray(university);

      String jsonText = jsonArray.toString(3);

      // you might write jsonText into a file

      storyboard.addCode();

      storyboard.add("Results in:");

      storyboard.add("<pre>" + jsonText + "</pre>");

      // =====================================================
      storyboard.addStep("Now read it back again");

      storyboard.markCodeStart();

      // read jsonText from file
      IdMap readerMap = UniversityCreator.createIdMap("demo");

      Object rootObject = readerMap.decode(jsonText);

      University readUniversity = (University) rootObject;
      storyboard.addCode();

      storyboard.addObjectDiagram(rootObject);

      storyboard.dumpHTML();
   }

   /**
    * @see <a href=
    *      '../../../../../../../../doc/StudyRightObjectModelNavigationAndQueries.html'>
    *      StudyRightObjectModelNavigationAndQueries.html</a>
    * @see <a href=
    *      '../../../../../../../../doc/StudyRightObjectModelNavigationAndQueries.html'>StudyRightObjectModelNavigationAndQueries.html</a>
    * @see <a href='../../../../../../../../doc/StudyRightObjectModelNavigationAndQueries.html'>StudyRightObjectModelNavigationAndQueries.html</a>
 */
   @Test
   public void testStudyRightObjectModelNavigationAndQueries()
   {
      Storyboard story = new Storyboard();

      story.add("Extend the class model:");

      ClassModel model = new ClassModel();

      Clazz studentClass = model.createClazz(Student.class.getName());

      studentClass.withBidirectional(studentClass, "friends", Cardinality.MANY, "friends", Cardinality.MANY);

      Clazz roomClass = model.createClazz(Room.class.getName());

      Clazz taClass = model.createClazz("TeachingAssistant");

      roomClass.withBidirectional(taClass, "tas", Cardinality.MANY, "room", Cardinality.ONE);

      story.addClassDiagram(model);

      // model.generate("examples");

      model.getGenerator().updateFromCode("src/test/java", "org.sdmlib.test.examples.studyrightWithAssignments.model");

      story.add("Full class model from code:");

      story.addClassDiagram(model);

      story.add("How to navigate and query an object model.");

      story.addStep("Example object structure:");

      University university = new University()
         .withName("StudyRight");

      Student abu = university.createStudents()
         .withId("1337")
         .withName("Abu");

      Student karli = new TeachingAssistant().withCertified(true);
      university.withStudents(karli
         .withId("4242")
         .withName("Karli"));

      Student alice = university.createStudents()
         .withId("2323")
         .withName("Alice");

      abu.withFriends(alice);

      Assignment a1 = new Assignment()
         .withContent("Matrix Multiplication")
         .withPoints(5)
         .withStudents(abu);

      Assignment a2 = new Assignment()
         .withContent("Series")
         .withPoints(6);

      Assignment a3 = new Assignment()
         .withContent("Integrals")
         .withPoints(8);

      karli.withDone(a1, a2);

      Room mathRoom = university.createRooms()
         .withName("senate")
         .withTopic("math")
         .withCredits(17)
         .withStudents(karli)
         .withAssignments(a1, a2, a3);

      Room artsRoom = university.createRooms()
         .withName("7522")
         .withTopic("arts")
         .withCredits(16)
         .withDoors(mathRoom);

      Room sportsRoom = university.createRooms()
         .withName("gymnasium")
         .withTopic("sports")
         .withCredits(25)
         .withDoors(mathRoom, artsRoom)
         .withStudents(abu, alice);

      Assignment a4 = sportsRoom.createAssignments().withContent("Pushups").withPoints(4).withStudents(abu);

      Room examRoom = university.createRooms()
         .withName("The End")
         .withTopic("exam")
         .withCredits(0)
         .withDoors(sportsRoom, artsRoom);

      Room softwareEngineering = university.createRooms()
         .withName("7422")
         .withTopic("Software Engineering")
         .withCredits(42)
         .withDoors(artsRoom, examRoom);

      story.addObjectDiagram(university);

      // =====================================================
      story.addStep("Simple set based navigation:");

      story.markCodeStart();

      double assignmentPoints = university.getRooms().getAssignments().getPoints().sum();

      double donePoints = university.getStudents().getDone().getPoints().sum();

      story.addCode();

      story.add("Results in:");

      String text = CGUtil.replaceAll(
         "      Sum of assignment points: 42. \n" +
            "      Sum of points of assignments that have been done by at least one students: 84.",
         "42", assignmentPoints,
         "84", donePoints);

      story.addPreformatted(text);

      story.assertEquals("Assignment points: ", 23.0, assignmentPoints);
      story.assertEquals("donePoints: ", 15.0, donePoints);

      // =====================================================
      story.addStep("Rooms with assignments not yet done by Karli:");

      story.markCodeStart();

      AssignmentSet availableAssignments = university.getRooms().getAssignments().minus(karli.getDone());

      RoomSet rooms = availableAssignments.getRoom();

      story.addCode();

      story.add("Results in:");

      story.addPreformatted("      " + rooms.toString());

      story.assertEquals("rooms.size(): ", 2, rooms.size());

      story.addStep("Filter for attribute:");

      story.markCodeStart();

      RoomSet rooms17 = university.getRooms().createCreditsCondition(17);
      RoomSet roomsGE20 = university.getRooms().createCreditsCondition(20, Integer.MAX_VALUE);

      story.addCode();

      story.add("Results in:");

      story.addPreformatted("      rooms17: " + rooms17.toString()
         + "\n      roomsGE20: " + roomsGE20);

      story.addStep("Filter for even values:");

      story.markCodeStart();

      SimpleSet<Room> roomsEven = university.getRooms().filter(r -> r.getCredits() % 2 == 0);

      story.addCode();

      story.add("Results in:");

      story.addPreformatted("      " + roomsEven);

      
      // ====================================================
      story.addStep("Filter for type: ");

      story.markCodeStart();

      TeachingAssistantSet taStudents = university.getRooms().getStudents().instanceOfTeachingAssistant();

      story.addCode();

      story.addPreformatted("" + taStudents);

      
      // ====================================================
      story.addStep("Write operations on sets: ");

      story.markCodeStart();

      university.getStudents().withMotivation(42);

      story.addCode();

      story.addObjectDiagramOnlyWith(university.getStudents());

      
      // =====================================================
      story.addStep("Rooms with two students that are friends (and need supervision): ");

      story.markCodeStart();

      RoomPO roomPO = university.getRooms().createRoomPO();

      StudentPO stud1PO = roomPO.createStudentsPO();

      roomPO.createStudentsPO().createMotivationCondition(42).createFriendsLink(stud1PO);

      rooms = roomPO.allMatches();

      story.addCode();

      story.addPattern(roomPO, false);

      story.add("Results in:");

      story.addPreformatted("      " + rooms.toString());

      
      // =====================================================
      story.addStep("Rooms with two students with low motivation that are friends (and need supervision): ");

      story.markCodeStart();

      roomPO = university.getRooms().createRoomPO();

      stud1PO = roomPO.createStudentsPO();

      final StudentPO stud2PO = roomPO.createStudentsPO().createMotivationCondition(0, 50);

      stud2PO.createFriendsLink(stud1PO);

      rooms = roomPO.allMatches();

      story.addCode();

      story.addPattern(roomPO, false);

      story.add("Results in:");

      story.addPreformatted("      " + rooms.toString());

      // =====================================================
      story.addStep("Rooms with two students without supervision that are friends and add teaching assistance: ");

      story.markCodeStart();

      UniversityPO uniPO = new UniversityPO(university);

      roomPO = uniPO.createRoomsPO();

      stud1PO = roomPO.createStudentsPO().createMotivationCondition(0, 42);

      roomPO.createStudentsPO().createFriendsLink(stud1PO);

      roomPO.createTasLink(null);

      roomPO.createTasPO(CREATE);

      rooms = roomPO.allMatches();

      story.addCode();

      story.addPattern(uniPO, false);

      // story.add("Internal pattern structure for debugging.");
      //
      // story.addObjectDiagramWith(roomPO.getPattern(),
      // roomPO.getPattern().getElementsTransitive(null));

      story.add("Results in:");

      story.addObjectDiagramOnlyWith(rooms, rooms.getStudents(), rooms.getTas());

      story.addPreformatted("      " + rooms.toString());

      // =====================================================
      story.addStep("TAs as students in a room: ");

      story.markCodeStart();

      roomPO = university.getRooms().createRoomPO();

      stud1PO = roomPO.createStudentsPO();

      TeachingAssistantPO taPO = stud1PO.instanceOf(new TeachingAssistantPO());

      TeachingAssistantSet taSet = taPO.allMatches();

      story.addCode();

      story.addPattern(roomPO, false);

      story.addObjectDiagramOnlyWith(taSet, taSet.getRoom());

      // =====================================================
      story.addStep("Double motivation of all students: ");

      story.markCodeStart();

      roomPO = university.getRooms().createRoomPO();

      stud1PO = roomPO.createStudentsPO();

      for (Match match : (Iterable<Match>) roomPO.getPattern())
      {
         Student currentMatch = stud1PO.getCurrentMatch();

         currentMatch.withMotivation(currentMatch.getMotivation() * 2);

         // or more simple:
         stud1PO.withMotivation(stud1PO.getMotivation() * 2);

         Room assertMatch = roomPO.getCurrentMatch();

         if (match.number == 1)
         {
            Assert.assertEquals("Karli", currentMatch.getName());
            Assert.assertEquals("senate", assertMatch.getName());
            Assert.assertEquals("math", assertMatch.getTopic());
            Assert.assertEquals(17, assertMatch.getCredits());
         }
         else if (match.number == 2)
         {
            Assert.assertEquals("Abu", currentMatch.getName());
            Assert.assertEquals("gymnasium", assertMatch.getName());
            Assert.assertEquals("sports", assertMatch.getTopic());
            Assert.assertEquals(25, assertMatch.getCredits());
         }
         else if (match.number == 3)
         {
            Assert.assertEquals("Alice", currentMatch.getName());
            Assert.assertEquals("gymnasium", assertMatch.getName());
            Assert.assertEquals("sports", assertMatch.getTopic());
            Assert.assertEquals(25, assertMatch.getCredits());
         }

         // System.out.println("match " + match.number + ": " + currentMatch + "
         // in room " + roomPO.getCurrentMatch());
      }

      story.addCode();

      story.addPattern(roomPO, false);

      story.addObjectDiagramOnlyWith(university.getStudents(), university.getStudents().getIn());

      // =====================================================
      story.addStep("lure students from other rooms into math room: ");

      story.markCodeStart();

      roomPO = new RoomPO(mathRoom);

      stud1PO = roomPO.createPath(r -> ((Room) r).getDoors().getStudents(), new StudentPO());

      stud1PO.startCreate();

      stud1PO.createInLink(roomPO);

      stud1PO.allMatches();

      story.addCode();

      story.addPattern(roomPO, false);

      story.addObjectDiagramOnlyWith(mathRoom, mathRoom.getDoors(), mathRoom.getStudents());

      story.assertEquals("New students in math room: ", 3, mathRoom.getStudents().size());

      story.dumpHTML();
   }

   
   /**
    * @see <a href='../../../../../../../../doc/StudyRightTablesAndReports.html'>StudyRightTablesAndReports.html</a>
    */
   @Test
   public void testReports()
   {
      University university = new University()
         .withName("StudyRight");

      Student abu = university.createStudents()
         .withId("1337")
         .withName("Abu");

      Student alice = university.createStudents()
         .withId("2323")
         .withName("Alice");

      abu.withFriends(alice);

      IdMap map=new IdMap();
      map.with(new org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversityCreator());
      map.with(new org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentCreator());
      
      System.out.println(map.toJsonArray(university).toString(2));
   }
   
   /**
    * @see <a href='../../../../../../../../doc/StudyRightTablesAndReports.html'>StudyRightTablesAndReports.html</a>
    */
   @Test
   public void testStudyRightTablesAndReports()
   {
      Storyboard story = new Storyboard();

      story.add("How to generate table reports from a model.");

      story.addStep("Example object structure:");

      University university = new University()
         .withName("StudyRight");

      Student abu = university.createStudents()
         .withId("1337")
         .withName("Abu");

      Student karli = new TeachingAssistant().withCertified(true);
      university.withStudents(karli
         .withId("4242")
         .withName("Karli"));

      Student alice = university.createStudents()
         .withId("2323")
         .withName("Alice");

      abu.withFriends(alice);

      Assignment a1 = new Assignment()
         .withContent("Matrix Multiplication")
         .withPoints(5)
         .withStudents(abu);

      Assignment a2 = new Assignment()
         .withContent("Series")
         .withPoints(6);
//
      Assignment a3 = new Assignment()
         .withContent("Integrals")
         .withPoints(8);

      karli.withDone(a1, a2);

      Room mathRoom = university.createRooms()
         .withName("senate")
         .withTopic("math")
         .withCredits(17)
         .withStudents(karli)
         .withAssignments(a1, a2, a3);

      Room artsRoom = university.createRooms()
         .withName("7522")
         .withTopic("arts")
         .withCredits(16)
         .withDoors(mathRoom);

      Room sportsRoom = university.createRooms()
         .withName("gymnasium")
         .withTopic("sports")
         .withCredits(25)
         .withDoors(mathRoom, artsRoom)
         .withStudents(abu, alice);

//      Assignment a4 = sportsRoom.createAssignments().withContent("Pushups").withPoints(4).withStudents(abu);

//      Room examRoom = university.createRooms()
//         .withName("The End")
//         .withTopic("exam")
//         .withCredits(0)
//         .withDoors(sportsRoom, artsRoom);

//      Room softwareEngineering = university.createRooms()
//         .withName("7422")
//         .withTopic("Software Engineering")
//         .withCredits(42)
//         .withDoors(artsRoom, examRoom);

      story.addObjectDiagram(university);

      story.addStep("Query for table");

      {
         story.markCodeStart();

         UniversityPO universityPO = new UniversityPO(university);

         RoomPO createRoomsPO = universityPO.createRoomsPO();

         Table table = universityPO.createResultTable();

         story.addCode();
         
         story.addPattern(universityPO, false);

         story.add("Results in:");

         story.addTable(table);

         // filter row rule
         TablePO tablePO = new TablePO(table);
         RowPO rowPO = tablePO.createRowsPO();
         CellPO cellPO = rowPO.createCellsPO();
         RoomPO roomPO = new RoomPO();
         cellPO.hasLink("value", roomPO);
         roomPO.startNAC();
         roomPO.createStudentsPO();
         roomPO.endNAC();
         rowPO.startSubPattern();
         CellPO otherCellPO = rowPO.createCellsPO();
         otherCellPO.destroy();
         otherCellPO.doAllMatches();
         rowPO.endSubPattern();
         rowPO.destroy();
         rowPO.doAllMatches();
         
         story.addPattern(tablePO, false);
         
         story.markCodeStart();

         table.createColumns("Topic", row -> {
            Room r = row.getCellValue("B");
            return r.getTopic();
         });
         table.createColumns("Credits", row -> ((Room) row.getCellValue("B")).getCredits())
            .withTdCssClass("text-right");
         table.createColumns("Students", row -> ((Room) row.getCellValue("B")).getStudents().size())
            .withTdCssClass("text-right");
         table.withoutColumns("A", "B");

         story.addCode();
         
         story.addTable(table);
         
         double creditsSum = table.getColumn("Credits").getValueSum();
         
         String csv = table.getCSV();
         
         try
         {
            Files.write(Paths.get("doc/StudyRight.csv"), csv.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
         }
         catch (IOException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
         
         tablePO = new TablePO(table);
         ColumnPO columnsPO = tablePO.createColumnsPO(CREATE);
         columnsPO.createNameAssignment("Topic");
         tablePO.startSubPattern();
         rowPO = tablePO.createRowsPO();
         cellPO = rowPO.createCellsPO();
         roomPO = new RoomPO();
         cellPO.hasLink("value", roomPO);
         cellPO.startCreate();
         CellPO cellPO2 = columnsPO.createCellsPO(CREATE);
         cellPO2.createValueAssignment("r5.topic");
         cellPO.endCreate();
         tablePO.endSubPattern();
         
         
         story.addPattern(tablePO, false);
         
      }

      // =====================================================
      story.addStep("List all topics:");

      {
         story.markCodeStart();

         UniversityPO universityPO = new UniversityPO(university);

         TablePO tablePO = new TablePO(CREATE);

         universityPO.addToPattern(tablePO);

         tablePO.createNameAssignment("University");

         ColumnPO col1PO = tablePO.createColumnsPO(CREATE).createNameAssignment("Topic");

         ColumnPO col2PO = tablePO.createColumnsPO(CREATE)
            .createNameAssignment("Credits")
            .createTdCssClassAssignment("text-right");

         ColumnPO col3PO = tablePO.createColumnsPO(CREATE)
            .createNameAssignment("Students")
            .createTdCssClassAssignment("text-right");

         RoomPO roomsPO = universityPO.createRoomsPO();

         RowPO rowPO = tablePO.createRowsPO(CREATE);

         CellPO cell1PO = rowPO.createCellsPO(CREATE).createColumnLink(col1PO, CREATE);
         cell1PO.createCondition(cell -> cell.withValue(roomsPO.getTopic()) != null);

         CellPO cell2PO = rowPO.createCellsPO(CREATE).createColumnLink(col2PO, CREATE);
         cell2PO.createCondition(cell -> cell.withValue(roomsPO.getCredits()) != null);

         CellPO cell3PO = rowPO.createCellsPO(CREATE).createColumnLink(col3PO, CREATE);
         cell3PO.createCondition(cell -> cell.withValue(roomsPO.getStudents().size()) != null);
         
         universityPO.doAllMatches();

         story.addCode();

         story.add("Results in:");

         story.addTable(tablePO.getCurrentMatch());

         story.addObjectDiagram(tablePO.getCurrentMatch());
      }

      story.addStep("Do a nested table");
      {
         story.markCodeStart();

         UniversityPO universityPO = new UniversityPO(university);

         RoomPO createRoomsPO = universityPO.createRoomsPO();

         Table table = universityPO.createResultTable();

         table.createColumns("Topic", row -> ((Room) row.getCellValue("B")).getTopic());
         table.createColumns("Assignments", row -> addAssignments(row));
         table.createColumns("Students", row -> ((Room) row.getCellValue("B")).getStudents().size())
            .withTdCssClass("text-right");
         table.withoutColumns("A", "B");

         story.addCode();

         story.addTable(table);
      }
      story.dumpHTML();
   }

   public Table addAssignments(Row row)
   {
      Room room = (Room) row.getCellValue("B");

      RoomPO roomPO = new RoomPO(room);

      AssignmentPO assignmentPO = roomPO.createAssignmentsPO();

      Table table = roomPO.createResultTable();

      table.createColumns("Content", r -> ((Assignment) r.getCellValue("B")).getContent());
      table.createColumns("Points", r -> ((Assignment) r.getCellValue("B")).getPoints())
         .withTdCssClass("text-right");
      table.withoutColumns("A", "B");

      return table;
   }

   /**
    * 
    * @see <a href=
    *      '../../../../../../../../doc/StudyRightReachabilityGraph.html'>StudyRightReachabilityGraph.html</a>
    * @see <a href='../../../../../../../../doc/StudyRightReachabilityGraph.html'>StudyRightReachabilityGraph.html</a>
 */
   @Test
   public void testStudyRightReachabilityGraph()
   {
      Storyboard story = new Storyboard();

      story.addStep("Build a start graph");

      University university = new University()
         .withName("StudyRight");

      Student karli = university.createStudents()
         .withId("1337")
         .withName("Karli")
         .withMotivation(100);

      Room mathRoom = university.createRooms()
         .withName("senate")
         .withTopic("math")
         .withCredits(17)
         .withStudents(karli);

      karli.withCredits(17).withMotivation(100 - 17);

      Room artsRoom = university.createRooms()
         .withName("7522")
         .withTopic("arts")
         .withCredits(16)
         .withDoors(mathRoom);

      Room sportsRoom = university.createRooms()
         .withName("gymnasium")
         .withTopic("sports")
         .withCredits(25)
         .withDoors(mathRoom, artsRoom);

      Room examRoom = university.createRooms()
         .withName("The End")
         .withTopic("exam")
         .withCredits(0)
         .withDoors(sportsRoom, artsRoom);

      Room softwareEngineering = university.createRooms()
         .withName("7422")
         .withTopic("Software Engineering")
         .withCredits(42)
         .withDoors(artsRoom, examRoom);

      story.addObjectDiagramOnlyWith(university, karli, university.getRooms());

      // =====================================================
      IdMap idMap = UniversityCreator.createIdMap("s");
      idMap.with(ReachabilityGraphCreator.createIdMap("rg"));

      ReachableState startState = new ReachableState().withGraphRoot(university);
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph().withMasterMap(idMap)
         .withStates(startState).withTodo(startState);

      UniversityPO uniPO = new UniversityPO();

      StudentPO studPO = uniPO.createStudentsPO();

      RoomPO currentRoomPO = studPO.createInPO();

      RoomPO nextRoomPO = currentRoomPO.createDoorsPO();

      studPO.createCondition(s -> studPO.getMotivation() >= nextRoomPO.getCredits());

      uniPO.getPattern().clone(reachabilityGraph);

      studPO.startCreate().createInLink(nextRoomPO).endCreate();

      studPO.createCondition(s -> {
         studPO.withMotivation(studPO.getMotivation() - nextRoomPO.getCredits());
         studPO.withCredits(studPO.getCredits() + nextRoomPO.getCredits());
         return true;
      });

      story.addPattern(uniPO, false);

      reachabilityGraph.addToRules(uniPO.getPattern().withName("next"));

      reachabilityGraph.explore();

      ReachableStateSet allStates = reachabilityGraph.getStates();
      // interestingStates.addAll(reachabilityGraph.getStates().filterNumber(60,
      // Integer.MAX_VALUE));

      ReachableStateSet interestingStates = new ReachableStateSet();

      for (ReachableState state : allStates)
      {
         University uni = (University) state.getGraphRoot();
         Student student = uni.getStudents().first();
         Room room = student.getIn();
         if (student.getMotivation() == 0 && room.getTopic().equals("exam"))
         {
            interestingStates.add(state);
            break;
         }
      }

      RuleApplicationSet ruleApplications = new RuleApplicationSet();

      ReachableState endState = interestingStates.first();

      for (int i = 1; i <= 10; i++)
      {
         RuleApplication ruleApplication = endState.getResultOf().first();

         if (ruleApplication != null)
         {
            ruleApplications.add(ruleApplication);
            endState = ruleApplication.getSrc();
            interestingStates.add(endState);
         }
         else
         {
            break;
         }
      }

      UniversitySet interestingUniversities = CGUtil.instanceOf(interestingStates.getGraphRoot(), new UniversitySet());

      StudentSet studentsSet = CGUtil.instanceOf(allStates.getGraphRoot(), new UniversitySet()).getStudents().filterMotivation(0);

      story.addObjectDiagramOnlyWith(
         interestingStates,
         ruleApplications,
         interestingUniversities,
         interestingUniversities.getStudents(),
         interestingUniversities.getStudents().getIn());

      // ok do it with search pattern
      ReachabilityGraphPO reachabilityGraphPO = new ReachabilityGraphPO(reachabilityGraph);
      ReachableStatePO statePO = reachabilityGraphPO.createStatesPO();
      UniversityPO universityPO =
            statePO.createGraphRootPO().instanceOf(new UniversityPO());
      StudentPO studentPO =
            universityPO.createStudentsPO().createMotivationCondition(0);
      RoomPO roomPO = studentPO.createInPO().createTopicCondition("exam");

      ReachableState currentMatch = statePO.getCurrentMatch();

      story.add("Total number of states " + reachabilityGraph.getStates().size());

      story.add("Success state " + currentMatch.getNumber());

      story.addPattern(reachabilityGraphPO, true);

      story.dumpHTML();
   }
}
