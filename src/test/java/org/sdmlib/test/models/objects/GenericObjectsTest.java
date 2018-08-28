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
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/models/objects/GenericObjectsTest.java' type='text/x-java'>GenericObjectDiagram</a></p>
    * <p>Start situation: we do not yet have a class diagram but want to start with some example object models</p>
    * <p>Step 1: We build a generic class model for object structures: </p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"GenericAttribute",
    *          "attributes":[
    *             "name : String",
    *             "value : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"GenericGraph"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"GenericLink",
    *          "attributes":[
    *             "srcLabel : String",
    *             "tgtLabel : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"GenericObject",
    *          "attributes":[
    *             "icon : String",
    *             "name : String",
    *             "type : String"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericAttribute",
    *             "cardinality":"many",
    *             "property":"attrs"
    *          },
    *          "target":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"owner"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericGraph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          },
    *          "target":{
    *             "id":"GenericObject",
    *             "cardinality":"many",
    *             "property":"objects"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericGraph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          },
    *          "target":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"links"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"incommingLinks"
    *          },
    *          "target":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"tgt"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"links"
    *          },
    *          "target":{
    *             "id":"GenericGraph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"outgoingLinks"
    *          },
    *          "target":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"src"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericObject",
    *             "cardinality":"many",
    *             "property":"objects"
    *          },
    *          "target":{
    *             "id":"GenericGraph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"owner"
    *          },
    *          "target":{
    *             "id":"GenericAttribute",
    *             "cardinality":"many",
    *             "property":"attrs"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"src"
    *          },
    *          "target":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"outgoingLinks"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GenericObject",
    *             "cardinality":"one",
    *             "property":"tgt"
    *          },
    *          "target":{
    *             "id":"GenericLink",
    *             "cardinality":"many",
    *             "property":"incommingLinks"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasGenericObjectDiagramClassDiagram2", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * <p>Step 2: We just build our example object structure with generic objects: </p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G1 : GenericGraph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G10 : GenericAttribute",
    *          "attributes":[
    *             "name=name",
    *             "value=WA03"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G11 : GenericAttribute",
    *          "attributes":[
    *             "name=level",
    *             "value=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G12 : GenericAttribute",
    *          "attributes":[
    *             "name=guest",
    *             "value=Ulrich"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G2 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=WilliAllee",
    *             "type=Building"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G3 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=seFloor",
    *             "type=Floor"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G4 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=digitalFloor",
    *             "type=Floor"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G5 : GenericLink",
    *          "attributes":[
    *             "srcLabel=null",
    *             "tgtLabel=has"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G6 : GenericLink",
    *          "attributes":[
    *             "srcLabel=null",
    *             "tgtLabel=has"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G7 : GenericAttribute",
    *          "attributes":[
    *             "name=name",
    *             "value=WA73"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G8 : GenericAttribute",
    *          "attributes":[
    *             "name=name",
    *             "value=WA13"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G9 : GenericAttribute",
    *          "attributes":[
    *             "name=level",
    *             "value=1"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G7 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G2 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G8 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G3 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G9 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G3 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G10 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G4 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G11 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G4 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G12 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G4 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G5 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G3 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G6 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G4 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"links",
    *             "id":"G5 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G1 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"links",
    *             "id":"G6 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G1 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"objects",
    *             "id":"G2 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G1 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"objects",
    *             "id":"G3 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G1 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"objects",
    *             "id":"G4 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G1 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G5 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G2 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G6 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G2 : GenericObject"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasGenericObjectDiagram5", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Step 3: Then we tune our diagram dumper to show it as a non-generic object diagram: </p>
    * <p></p>
    * <p>Step 4: now we try to learn a class diagram from the generic object structure: </p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Building",
    *          "attributes":[
    *             "name : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Floor",
    *          "attributes":[
    *             "guest : String",
    *             "level : int",
    *             "name : String"
    *          ]
    *       }
    *    ],
    *    "edges":[]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasGenericObjectDiagramClassDiagram8", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * <p>Step 5: generate model creation code to allow the developer to adjust e.g. attribute types and associoation cardinalities: </p>
    * <pre>            ClassModel model = new ClassModel(&quot;org.sdmlib.test.examples.roombook&quot;);
    * 
    *       Clazz buildingClass = model.createClazz(&quot;org.sdmlib.test.examples.roombook.Building&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING);
    * 
    *       Clazz floorClass = model.createClazz(&quot;org.sdmlib.test.examples.roombook.Floor&quot;)
    *             .withAttribute(&quot;level&quot;, DataType.INT)
    *             .withAttribute(&quot;name&quot;, DataType.STRING)
    *             &#x2F;*add attribut*&#x2F;
    *             .withAttribute(&quot;guest&quot;, DataType.STRING);    
    * 
    *       &#x2F;* add assoc *&#x2F;
    *       buildingClass.withBidirectional(floorClass, &quot;has&quot;, Cardinality.MANY, &quot;buildings&quot;, Cardinality.ONE);
    * 
    *       &#x2F;&#x2F; FIXME: Alex
    *       &#x2F;&#x2F; learnedModel.getGenerator().insertModelCreationCodeHere(&quot;examples&quot;);
    * </pre>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Building",
    *          "attributes":[
    *             "name : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Floor",
    *          "attributes":[
    *             "guest : String",
    *             "level : int",
    *             "name : String"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Building",
    *             "cardinality":"one",
    *             "property":"buildings"
    *          },
    *          "target":{
    *             "id":"Floor",
    *             "cardinality":"many",
    *             "property":"has"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Floor",
    *             "cardinality":"many",
    *             "property":"has"
    *          },
    *          "target":{
    *             "id":"Building",
    *             "cardinality":"one",
    *             "property":"buildings"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasGenericObjectDiagramClassDiagram11", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * <p>Step 6: generate code from the learned class diagram </p>
    * <p>Step 7: derive non-generic objects from the generic objects </p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"B13 : Building",
    *          "attributes":[
    *             "name=WA73"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F14 : Floor",
    *          "attributes":[
    *             "guest=null",
    *             "level=1",
    *             "name=WA13"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"F15 : Floor",
    *          "attributes":[
    *             "guest=Ulrich",
    *             "level=0",
    *             "name=WA03"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"has",
    *             "id":"F14 : Floor"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"buildings",
    *             "id":"B13 : Building"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"has",
    *             "id":"F15 : Floor"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"buildings",
    *             "id":"B13 : Building"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasGenericObjectDiagram15", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G16 : GenericGraph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G17 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=B527327461422143",
    *             "type=org.sdmlib.test.examples.roombook.Building"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G18 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=F527327461512224",
    *             "type=org.sdmlib.test.examples.roombook.Floor"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G19 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=F527327461625788",
    *             "type=org.sdmlib.test.examples.roombook.Floor"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G20 : GenericLink",
    *          "attributes":[
    *             "srcLabel=buildings",
    *             "tgtLabel=has"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G21 : GenericLink",
    *          "attributes":[
    *             "srcLabel=buildings",
    *             "tgtLabel=has"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G22 : GenericAttribute",
    *          "attributes":[
    *             "name=name",
    *             "value=WA73"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G23 : GenericAttribute",
    *          "attributes":[
    *             "name=level",
    *             "value=1"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G24 : GenericAttribute",
    *          "attributes":[
    *             "name=name",
    *             "value=WA13"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G25 : GenericAttribute",
    *          "attributes":[
    *             "name=name",
    *             "value=WA03"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G26 : GenericAttribute",
    *          "attributes":[
    *             "name=guest",
    *             "value=Ulrich"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G22 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G17 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G23 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G18 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G24 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G18 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G25 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G19 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G26 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G19 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G20 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G18 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G21 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G19 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"links",
    *             "id":"G20 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G16 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"links",
    *             "id":"G21 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G16 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"objects",
    *             "id":"G17 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G16 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"objects",
    *             "id":"G18 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G16 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"objects",
    *             "id":"G19 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G16 : GenericGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G20 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G17 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G21 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G17 : GenericObject"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasGenericObjectDiagram16", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/GenericObjectDiagram.html'>GenericObjectDiagram.html</a>
    * @see <a href='../../../../../../../../doc/genericgraphs/GenericObjectDiagram.html'>GenericObjectDiagram.html</a>
 */
   @Test
   public void testGenericObjectDiagram()
   {
      //====================================================================================================
      StoryboardImpl storyboard = new StoryboardImpl().withDocDirName("doc/genericgraphs");

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
      // storyboard.withIdMap(GenericGraphCreator.createIdMap("g"));
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

