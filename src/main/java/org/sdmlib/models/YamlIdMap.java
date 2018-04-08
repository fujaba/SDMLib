package org.sdmlib.models;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.list.AbstractArray;
import de.uniks.networkparser.list.SimpleKeyValueList;
import de.uniks.networkparser.list.SimpleList;
import org.sdmlib.CGUtil;
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
    * <p>Check: pojo albert exists org.sdmlib.test.examples.studyrightWithAssignments.model.Prof@16022d9d</p>
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
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/doc/TestJavaDocStories.java'>TestJavaDocStories.java</a>
 * @see org.sdmlib.test.doc.TestJavaDocStories#testJavaDocStoriesMikadoPlan
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testGroupAccountMultiUserYaml
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testPlainYaml
 */
   public class YamlIdMap
{
   private ArrayList<String> packageNames;
   private StringTokenizer tokenizer;
   private String yaml;
   private String userId = null;
   private boolean decodingPropertyChange;
   private SimpleKeyValueList<String, Object> objIdMap = new SimpleKeyValueList<String, Object>().withFlag(AbstractArray.BIDI);
   private int maxUsedIdNum = 0;
   private Yamler yamler = new Yamler();
   private HashMap<String, String> attrTimeStamps = new HashMap<>();

   public SimpleKeyValueList<String, Object> getObjIdMap()
   {
      return objIdMap;
   }
   public HashMap<String, String> getAttrTimeStamps()
   {
      return attrTimeStamps;
   }


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
    * <p>Check: pojo albert exists org.sdmlib.test.examples.studyrightWithAssignments.model.Prof@16022d9d</p>
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
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/doc/TestJavaDocStories.java'>TestJavaDocStories.java</a>
 * @see org.sdmlib.test.doc.TestJavaDocStories#testJavaDocStoriesMikadoPlan
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testGroupAccountMultiUserYaml
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testPlainYaml
 */
   private YamlIdMap()
   {
      // always pass package to constructor
   }

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
    * <p>Check: pojo albert exists org.sdmlib.test.examples.studyrightWithAssignments.model.Prof@16022d9d</p>
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
    * @see <a href='../../../../../../src/test/java/org/sdmlib/test/doc/TestJavaDocStories.java'>TestJavaDocStories.java</a>
 * @see org.sdmlib.test.doc.TestJavaDocStories#testJavaDocStoriesMikadoPlan
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testGroupAccountMultiUserYaml
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountTests#testPlainYaml
 */
   public YamlIdMap(String... packageNames)
   {
      Objects.requireNonNull(packageNames);
      List<String> list = Arrays.asList(packageNames);
      this.packageNames = new ArrayList<String>(list);
      creatorMap = new CreatorMap(this.packageNames);
   }


   public Object decodeCSV(String fileName)
   {
      try
      {
         byte[] bytes = Files.readAllBytes(Paths.get(fileName));

         String csvText = new String(bytes);

         String yamlText = convertCsv2Yaml(csvText);

         // System.out.println(yamlText);

         return this.decode(yamlText);

      } catch (IOException e)
      {
         Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
      }

      return null;
   }

   private String convertCsv2Yaml(String csvText)
   {
      String[] split = csvText.split(";");

      for (int i = 0; i < split.length; i++)
      {
         String token = split[i];


         if (token.startsWith("\"") && token.endsWith("\""))

         {
            // already done
            continue;
         }

         if (token.startsWith("\"") && !token.endsWith("\""))
         {
            // there is a semicolon within "   ;   " , recombine it
            int j = i;
            String nextToken;
            while (true)
            {
               j++;
               nextToken = split[j];
               split[j] = "";
               token = token + ";" + nextToken;
               if (nextToken.endsWith("\""))
               {
                  split[i] = token;
                  i = j;
                  break;
               }
            }
            continue;
         }

         if (token.trim().length() == 0)
         {
            continue;
         }

         Pattern pattern = Pattern.compile("\\s");
         Matcher matcher = pattern.matcher(token.trim());
         boolean found = matcher.find();

         if (found)
         {
            token = yamler.encapsulate(token);
            split[i] = token;
         }
      }

      StringBuilder buf = new StringBuilder();

      for (int i = 0; i < split.length; i++)
      {
         buf.append(split[i]).append(" ");
      }

      return buf.toString();
   }



   public Object decode(String yaml, Object root)
   {
      getOrCreateKey(root);
      decode(yaml);
      
      return root;
   }
   
   /**
    * Object decode(String yaml)
    * Decode string and create typed object structure.
    *
    * <pre>
    *
    * Example:
    *
    * String yaml = ""
    * + "- studyRight: University \n"
    * + "  name:       \"\\\"Study \\\" Right\\\"And\\\"Fast now\\\"\"\n"
    * + "  students:   karli\n"
    * + "  rooms:      mathRoom artsRoom sportsRoom examRoom softwareEngineering \n"
    * + "\n"
    * + "- karli: Student\n"
    * + "  id:    4242\n"
    * + "  name:  karli\n"
    * + "\n"
    * + "- albert: Prof\n"
    * + "  topic:  SE\n"
    * + "\n"
    * + "- Assignment   content:                      points: \n"
    * + "  matrixMult:  \"Matrix Multiplication\"     5\n"
    * + "  series:      \"Series\"                    6\n"
    * + "  a3:          Integrals                     8\n"
    * + "\n"
    * + "- Room                  topic:  credits: doors:                 students: assignments: \n"
    * + "  mathRoom:             math    17       null                   karli     [matrixMult series a3]\n"
    * + "  artsRoom:             arts    16       mathRoom               null      null\n"
    * + "  sportsRoom:           sports  25       [mathRoom artsRoom]\n"
    * + "  examRoom:             exam     0       [sportsRoom artsRoom]\n"
    * + "  softwareEngineering:  \"Software Engineering\" 42 [artsRoom examRoom]\n"
    * + "";
    *
    * YamlIdMap yamlIdMap = new YamlIdMap("org.sdmlib.test.examples.studyrightWithAssignments.model");
    *
    * University studyRight = (University) yamlIdMap.decode(yaml);
    * </pre>
    *
    * result:
    *
    * <img src="./doc-files/resultObjDiagram.png" />
    *
    *
    * @param yaml string describing object structure
    * @return first object
    */
   public Object decode(String yaml)
   {
      decodingPropertyChange = false;
      yamlChangeText = null;

      this.yaml = yaml;
      Object root = null;

      yamler = new Yamler(yaml);

      if ( ! yamler.getCurrentToken().equals("-"))
      {
         return yamler.decode(yaml);
      }

      root = parseObjectIds();

      yamler = new Yamler(yaml);

      parseObjectAttrs();

      // reset property change decoding
      this.setDecodingPropertyChange(false);

      yamlChangeText = null;

      return root;
   }


   private void parseObjectAttrs()
   {
      while ( ! "".equals(yamler.getCurrentToken()))
      {
         if ( ! "-".equals(yamler.getCurrentToken()) )
         {
            yamler.printError("'-' expected");
            yamler.nextToken();
            continue;
         }

         String key = yamler.nextToken();
         
         if ( key.endsWith(":"))
         {
            // usual
            parseUsualObjectAttrs();
            continue;
         }
         else
         {
            parseObjectTableAttrs();
            continue;
         }
      }

   }

   private void parseObjectTableAttrs()
   {
      // skip column names
      String className = yamler.getCurrentToken();
      
      SendableEntityCreator creator = getCreator(className);
      yamler.nextToken();

      ArrayList<String> colNameList = new ArrayList<String>();

      while ( ! "".equals(yamler.getCurrentToken()) && yamler.getLookAheadToken().endsWith(":"))
      {
         String colName = yamler.stripColon(yamler.getCurrentToken());
         colNameList.add(colName);
         yamler.nextToken();
      }

      while ( ! "".equals(yamler.getCurrentToken()) && ! "-".equals(yamler.getCurrentToken()))
      {
         String objectId = yamler.stripColon(yamler.getCurrentToken());
         yamler.nextToken();

         Object obj = objIdMap.get(objectId);
         
         // column values
         int colNum = 0;
         while ( ! "".equals(yamler.getCurrentToken()) && ! yamler.getCurrentToken().endsWith(":") && ! "-".equals(yamler.getCurrentToken()))
         {
            String attrName = colNameList.get(colNum);

            if (yamler.getCurrentToken().startsWith("["))
            {
               String value = yamler.getCurrentToken().substring(1);
               if (value.trim().equals(""))
               {
                  value = yamler.nextToken();
               }
               setValue(creator, obj, attrName, value);

               while (! "".equals(yamler.getCurrentToken()) && ! yamler.getCurrentToken().endsWith("]") )
               {
                  yamler.nextToken();
                  value = yamler.getCurrentToken();
                  if (yamler.getCurrentToken().endsWith("]"))
                  {
                     value = yamler.getCurrentToken().substring(0, yamler.getCurrentToken().length()-1);
                  }
                  if ( ! value.trim().equals(""))
                  {
                     setValue(creator, obj, attrName, value);
                  }
               }
            }
            else
            {
               setValue(creator, obj, attrName, yamler.getCurrentToken());
            }
            colNum++;
            yamler.nextToken();
         }
      }
   }

   private void parseUsualObjectAttrs()
   {
      String objectId = yamler.stripColon(yamler.getCurrentToken());
      String className = yamler.nextToken();
      yamler.nextToken();

      if (className.endsWith(".remove"))
      {
         objIdMap.remove(objectId);

         // skip time stamp, if necessary
         while ( ! yamler.getCurrentToken().equals("")
                 && ! yamler.getCurrentToken().equals("-"))
         {
            yamler.nextToken();
         }
         return;
      }

      if (className.equals(".Map"))
      {
         LinkedHashMap<String, String> map = (LinkedHashMap<String, String>) objIdMap.get(objectId);

         while (yamler.getCurrentToken().endsWith(":"))
         {
            String attrName = yamler.stripColon(yamler.getCurrentToken());
            yamler.nextToken();
            String value = "";
            int valueStart = yamler.getCurrentPos();

            // many values
            while ( ! yamler.getCurrentToken().equals("")
                    && ! yamler.getCurrentToken().endsWith(":"))
            {
               value = yaml.substring(valueStart, yamler.getCurrentPos() + yamler.getCurrentToken().length());
               yamler.nextToken();
            }

            map.put(attrName, value);
         }
      }
      else
      {
         SendableEntityCreator creator = getCreator(className);

         Object obj = objIdMap.get(objectId);

         // read attributes
         while (!yamler.getCurrentToken().equals("") && !yamler.getCurrentToken().equals("-"))
         {
            String attrName = yamler.stripColon(yamler.getCurrentToken());
            yamler.nextToken();

            if (obj == null)
            {
               // no object created by parseObjectIds. Object has been removed.
               // ignore attr changes
               while (!yamler.getCurrentToken().equals("")
                       && !yamler.getCurrentToken().endsWith(":")
                       && !yamler.getCurrentToken().equals("-"))
               {
                  yamler.nextToken();
               }
               continue;
            }

            // many values
            while (!yamler.getCurrentToken().equals("")
                    && !yamler.getCurrentToken().endsWith(":")
                    && !yamler.getCurrentToken().equals("-"))
            {
               String attrValue = yamler.getCurrentToken();

               if (yamler.getLookAheadToken().endsWith(".time:"))
               {
                  String propWithTime = yamler.nextToken();
                  String newTimeStamp = yamler.nextToken();
                  String oldTimeStamp = attrTimeStamps.get(objectId + "." + attrName);

                  if (oldTimeStamp == null || oldTimeStamp.compareTo(newTimeStamp) <= 0)
                  {
                     this.setDecodingPropertyChange(true);

                     if (yamlChangeText == null)
                     {
                        yamlChangeText = yaml;
                     }

                     setValue(creator, obj, attrName, attrValue);
                     attrTimeStamps.put(objectId + "." + attrName, newTimeStamp);
                  }
               }
               else
               {
                  setValue(creator, obj, attrName, attrValue);
               }

               yamler.nextToken();
            }
         }
      }
   }

   private void setValue(SendableEntityCreator creator, Object obj, String attrName, String attrValue)
   {
      String type = "new";

      if (attrName.endsWith(".remove"))
      {
         attrName = attrName.substring(0, attrName.length() - ".remove".length());

         if (creator.getValue(obj, attrName) instanceof Collection)
         {
            type = SendableEntityCreator.REMOVE;
         }
         else
         {
            attrValue = null;
         }
      }

      try
      {
         creator.setValue(obj, attrName, attrValue, type);
      }
      catch (Exception e)
      {
         // maybe a node
         Object targetObj = objIdMap.get(attrValue);
         if (targetObj != null)
         {
            creator.setValue(obj, attrName, targetObj, type);
         }
      }
   }

   private Object parseObjectIds()
   {
      Object root = null;
      while ( ! "".equals(yamler.getCurrentToken()))
      {
         if ( ! "-".equals(yamler.getCurrentToken()) )
         {
            yamler.printError("'-' expected");
            yamler.nextToken();
            continue;
         }

         String key = yamler.nextToken();
         
         if ( key.endsWith(":"))
         {
            // usual
            Object now = parseUsualObjectId();
            if (root == null) root = now;
            continue;
         }
         else
         {
            Object now = parseObjectTableIds();
            if (root == null) root = now;
            continue;
         }
      }

      return root;
   }
   

   private Object parseUsualObjectId()
   {
      String objectId = yamler.stripColon(yamler.getCurrentToken());
      int pos = objectId.lastIndexOf('.');
      String numPart = objectId.substring(pos + 2);
      int objectNum = 0;

      try
      {
         objectNum = Integer.parseInt(numPart);
      }
      catch (NumberFormatException e)
      {
         objectNum = objIdMap.size() + 1;
      }

      if (objectNum > maxUsedIdNum)
      {
         maxUsedIdNum = objectNum;
      }

      String className = yamler.nextToken();
      
      Object obj = objIdMap.get(objectId);

      String userId = null;

      // skip attributes
      while ( ! yamler.getCurrentToken().equals("") && ! yamler.getCurrentToken().equals("-"))
      {
         String token = yamler.nextToken();
         if (token.endsWith(".time:"))
         {
            token = yamler.nextToken();

            userId = token.substring(token.lastIndexOf('.') + 1);
         }
      }

      boolean foreignChange = false;

      if (userId != null)
      {
         int dotIndex = objectId.indexOf('.');

         if (dotIndex > 0)
         {
            String ownerId = objectId.substring(0, dotIndex);
            foreignChange = ! userId.equals(ownerId);
         }
      }

      if (obj == null && ! className.endsWith(".remove") && ! foreignChange)
      {
         if (className.equals(".Map"))
         {
            obj = new LinkedHashMap<String, String>();
         }
         else
         {
            SendableEntityCreator creator = getCreator(className);
            obj = creator.getSendableInstance(false);
         }

         objIdMap.put(objectId, obj);
      }

      return obj;
   }

   private Object parseObjectTableIds()
   {
      Object root = null;
      
      // skip column names
      String className = yamler.getCurrentToken();
      
      SendableEntityCreator creator = getCreator(className);

      while ( ! "".equals(yamler.getCurrentToken()) && yamler.getLookAheadToken().endsWith(":"))
      {
         yamler.nextToken();
      }

      while ( ! "".equals(yamler.getCurrentToken()) && ! "-".equals(yamler.getCurrentToken()))
      {
         String objectId = yamler.stripColon(yamler.getCurrentToken());
         yamler.nextToken();

         Object obj = creator.getSendableInstance(false);
         
         objIdMap.put(objectId, obj);
         
         if (root == null) root = obj;
         
         // skip column values
         while (! "".equals(yamler.getCurrentToken()) && ! yamler.getCurrentToken().endsWith(":") && ! "-".equals(yamler.getCurrentToken()))
         {
            yamler.nextToken();
         }
      }
      
      return root;
   }


   private Object parseObjList(String key, String second)
   {
      
      return null;
   }


   CreatorMap creatorMap;

   public SendableEntityCreator getCreator(String clazzName)
   {
      return creatorMap.getCreator(clazzName);
   }



   public Object getObject(String objId)
   {
      return objIdMap.get(objId);
   }

   public String encode(Object... rootObjList)
   {
      
      SimpleList<Object> simpleList = new SimpleList<Object>();
      simpleList.add(rootObjList);
      
      StringBuilder buf = new StringBuilder();
      
      // collect objects
      while ( ! simpleList.isEmpty())
      {
         Object obj = simpleList.first();
         simpleList.remove(0);

         if (obj instanceof PropertyChangeEvent)
         {
            encodePropertyChange(buf, obj);

            return buf.toString();
         }

         // already known? 
         String key = objIdMap.getKey(obj);
         
         if (key == null)
         {
            // add to map
            key = addToObjIdMap(obj);

            String className = obj.getClass().getSimpleName();
            
            // find neighbors
            SendableEntityCreator creator = getCreator(className);
            
            for (String prop : creator.getProperties())
            {
               Object value = creator.getValue(obj, prop);
               
               if (value == null)
               {
                  continue;
               }
               
               Class valueClass = value.getClass();
               
               if (value instanceof Collection)
               {
                  for (Object valueObj : (Collection) value)
                  {
                     simpleList.add(valueObj);
                  }
               }
               else if (  valueClass.getName().startsWith("java.lang.") || valueClass == String.class)
               {
                  continue;
               }
               else
               {
                  simpleList.add(value);
               }
            }
         }
         
      } // collect objects
      
      for ( Entry<String, Object> entry : objIdMap.entrySet())
      {
         String key = entry.getKey();
         Object obj = entry.getValue();
         String className = obj.getClass().getSimpleName();
         
         
         buf.append("- ").append(key).append(": \t").append(className).append("\n");
         
         // attrs
         SendableEntityCreator creator = getCreator(className);
         
         for (String prop : creator.getProperties())
         {
            Object value = creator.getValue(obj, prop);
            
            if (value == null)
            {
               continue;
            }
            
            if (value instanceof Collection)
            {
               if (((Collection) value).isEmpty())
               {
                  continue;
               }
               
               buf.append("  ").append(prop).append(": \t");
               for (Object valueObj : (Collection) value)
               {
                  String valueKey = objIdMap.getKey(valueObj);
                  buf.append(valueKey).append(" \t");
               }
               buf.append("\n");
            }
            else
            {
               String valueKey = objIdMap.getKey(value);
               
               if (valueKey != null)
               {
                  buf.append("  ").append(prop).append(": \t").append(valueKey).append("\n");
               }
               else
               {
                  if (value instanceof String)
                  {
                     value = yamler.encapsulate((String) value);
                  }
                  buf.append("  ").append(prop).append(": \t").append(value).append("\n");
               }

               // add time stamp?
               if (userId != null)
               {
                  String timeKey =  key + "." + prop;
                  String timeStamp = attrTimeStamps.get(timeKey);

                  if (timeStamp != null)
                  {
                     buf.append("  ").append(prop).append(".time: \t").append(timeStamp).append("\n");
                  }
               }
            }
         }
         buf.append("\n");
      }
      
      return buf.toString();
   }

   private void encodePropertyChange(StringBuilder buf, Object obj)
   {
      PropertyChangeEvent event = (PropertyChangeEvent) obj;
      obj = event.getSource();
      String propertyName = event.getPropertyName();
      Object value = event.getNewValue();
      String className = obj.getClass().getSimpleName();

      if (propertyName.equals(SendableEntityCreator.REMOVE_YOU))
      {
         // send - o42: C1.remove
         //        remove.time: 2018-03-11T22:11:02.123+01:00
         value = event.getOldValue();
         String valueKey = getOrCreateKey(value);
         buf.append("- ").append(valueKey).append(": \t").append(className).append(".remove\n");

         if (userId != null)
         {
            String now = "" + LocalDateTime.now() + "." + userId;
            buf.append("  ").append(className).append(".remove.time: \t").append(now).append("\n");
         }

         // remove it from our id map
         this.objIdMap.remove(valueKey);

         return;
      }

      if (value == null)
      {
         value = event.getOldValue();
         propertyName = propertyName + ".remove";

         if (value == null)
         {
            // no old nor new value, do nothing
            return;
         }
      }

      encodeAttrValue(buf, obj, propertyName, value);
   }

   public void encodeAttrValue(StringBuilder buf, Object obj, String propertyName, Object value)
   {
      // already known?
      String key = getOrCreateKey(obj);
      String className = obj.getClass().getSimpleName();
      buf.append("- ").append(key).append(": \t").append(className).append("\n");
      Class valueClass = value.getClass();

      if (  valueClass.getName().startsWith("java.lang.") || valueClass == String.class)
      {
         buf.append("  ").append(propertyName).append(": \t").append(yamler.encapsulate(value.toString())).append("\n");
         if (userId != null)
         {
            String now = "" + LocalDateTime.now() + "." + userId;
            buf.append("  ").append(propertyName).append(".time: \t").append(now).append("\n");
            attrTimeStamps.put(key + "." + propertyName, now);
         }
      }
      else
      {
         // value is an object
         String valueKey = getOrCreateKey(value);

         buf.append("  ").append(propertyName).append(": \t").append(valueKey).append("\n");
         if (userId != null)
         {
            // add timestamp only for to-one assocs
            SendableEntityCreator creator = creatorMap.getCreator(obj);
            String fieldName = propertyName;
            if (propertyName.endsWith(".remove"))
            {
               fieldName = propertyName.substring(0, propertyName.lastIndexOf('.'));
            }
            Object fieldValue = creator.getValue(obj, fieldName);
            if (fieldValue == null || ! (fieldValue instanceof Collection))
            {
               String now = "" + LocalDateTime.now() + "." + userId;
               buf.append("  ").append(propertyName).append(".time: \t").append(now).append("\n");
               attrTimeStamps.put(key + "." + propertyName, now);
            }
            else if (fieldValue != null && fieldValue instanceof Collection)
            {
               String now = "" + LocalDateTime.now() + "." + userId;
               buf.append("  ").append(propertyName).append('.').append(valueKey).append(".time: \t").append(now).append("\n");
               attrTimeStamps.put(key + "." + propertyName + "." + valueKey, now);
            }
         }

         if (value != null && ! propertyName.endsWith(".remove"))
         {
            buf.append("- ").append(valueKey).append(": \t").append(valueClass.getSimpleName()).append("\n");
         }
      }
   }

   private String getOrCreateKey(Object obj)
   {
      String key = objIdMap.getKey(obj);

      if (key == null)
      {
         key = addToObjIdMap(obj);
      }
      return key;
   }

   private String addToObjIdMap(Object obj)
   {
      String className = obj.getClass().getSimpleName();

      String key = className.substring(0, 1).toLowerCase();

      maxUsedIdNum++;

      key += maxUsedIdNum;

      if (maxUsedIdNum > 1 && userId != null)
      {
         // all but the first get a userId prefix
         key = userId + "." + key;
      }

      objIdMap.put(key, obj);

      return key;
   }

   public YamlIdMap withUserId(String userId)
   {
      this.userId = userId;
      return this;
   }

   public boolean isDecodingPropertyChange()
   {
      return decodingPropertyChange;
   }

   public void setDecodingPropertyChange(boolean decodingPropertyChange)
   {
      this.decodingPropertyChange = decodingPropertyChange;
   }

   private String yamlChangeText = null;

   public String getYamlChange()
   {
      String result = yamlChangeText;
      yamlChangeText = "";
      return result;
   }

   public String getLastTimeStamps()
   {
      LinkedHashMap<String, String> user2TimeStampMap = getLastTimeStampMap();

      StringBuilder buf = new StringBuilder();
      for ( Entry<String, String> e  : user2TimeStampMap.entrySet())
      {
         buf.append(e.getValue()).append(" ");
      }

      return buf.toString();
   }

   public LinkedHashMap<String, String> getLastTimeStampMap(String lastTimeStamps)
   {
      LinkedHashMap<String, String> user2TimeStampMap = new LinkedHashMap<String, String>();

      String[] split = lastTimeStamps.split("\\s+");

      for (String s : split)
      {
         int pos = s.lastIndexOf('.');
         String user = s.substring(pos + 1);
         user2TimeStampMap.put(user, s);
      }

      return user2TimeStampMap;
   }

      public LinkedHashMap<String, String> getLastTimeStampMap()
   {
      LinkedHashMap<String, String> user2TimeStampMap = new LinkedHashMap<String, String>();

      for ( Entry<String, String> e  : attrTimeStamps.entrySet())
      {
         String timeStamp = e.getValue();
         int pos = timeStamp.lastIndexOf('.');
         String userName = timeStamp.substring(pos+1);
         String oldTimeStamp = user2TimeStampMap.get(userName);

         if (oldTimeStamp == null || oldTimeStamp.compareTo(timeStamp) < 0)
         {
            user2TimeStampMap.put(userName, timeStamp);
         }
      }
      return user2TimeStampMap;
   }

}
