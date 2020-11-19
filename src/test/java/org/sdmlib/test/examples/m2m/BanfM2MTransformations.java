package org.sdmlib.test.examples.m2m;

import org.junit.Test;
import org.sdmlib.models.SDMLibIdMap;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.objects.Generic2Specific;
import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.objects.Specific2Generic;
import org.sdmlib.models.objects.util.GenericGraphPO;
import org.sdmlib.models.objects.util.GenericLinkPO;
import org.sdmlib.models.objects.util.GenericLinkSet;
import org.sdmlib.models.objects.util.GenericObjectPO;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.util.PatternCreator;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.m2m.model.Graph;
import org.sdmlib.test.examples.m2m.model.GraphComponent;
import org.sdmlib.test.examples.m2m.model.Person;
import org.sdmlib.test.examples.m2m.model.Relation;
import org.sdmlib.test.examples.m2m.model.util.GraphComponentCreator;
import org.sdmlib.test.examples.m2m.model.util.GraphCreator;
import org.sdmlib.test.examples.m2m.model.util.PersonCreator;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.json.JsonArray;

public class BanfM2MTransformations
{
   private Clazz edgeClazz;
   private Clazz nodeClazz;
   private Clazz graphClazz;
   private GenericGraphPO renameKindAttrGraphPO;
   private GenericGraphPO renamePersonsLinkGraphPO;
   private GenericGraphPO renameRelationsLinkGraphPO;
   private GenericGraphPO renameFirstNameAttrGraphPO;

   /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/m2m/BanfM2MTransformations.java' type='text/x-java'>BanfM2MTransformation</a></p>
    * <p>Class diagram for source model:</p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Graph"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"GraphComponent",
    *          "attributes":[
    *             "text : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Person",
    *          "attributes":[
    *             "firstName : String",
    *             "text : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Relation",
    *          "attributes":[
    *             "kind : String"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"persons"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          },
    *          "target":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"relations"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"parent"
    *          },
    *          "target":{
    *             "id":"GraphComponent",
    *             "cardinality":"many",
    *             "property":"gcs"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GraphComponent",
    *             "cardinality":"many",
    *             "property":"gcs"
    *          },
    *          "target":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"parent"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"knows"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"knows"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"persons"
    *          },
    *          "target":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"src"
    *          },
    *          "target":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"outEdges"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"tgt"
    *          },
    *          "target":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"inEdges"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"inEdges"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"tgt"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"outEdges"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"src"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"relations"
    *          },
    *          "target":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasBanfM2MTransformationClassDiagram1", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * <hr/><p>Class diagram for target model:</p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Graph"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"GraphComponent",
    *          "attributes":[
    *             "text : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Person"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Relation"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"parent"
    *          },
    *          "target":{
    *             "id":"GraphComponent",
    *             "cardinality":"many",
    *             "property":"gcs"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"GraphComponent",
    *             "cardinality":"many",
    *             "property":"gcs"
    *          },
    *          "target":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"parent"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"person"
    *          },
    *          "target":{
    *             "id":"GraphComponent",
    *             "cardinality":"one",
    *             "property":"graphcomponent"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Relation",
    *             "cardinality":"one",
    *             "property":"relation"
    *          },
    *          "target":{
    *             "id":"GraphComponent",
    *             "cardinality":"one",
    *             "property":"graphcomponent"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"src"
    *          },
    *          "target":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"outEdges"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"tgt"
    *          },
    *          "target":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"inEdges"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"person"
    *          },
    *          "target":{
    *             "id":"GraphComponent",
    *             "cardinality":"one",
    *             "property":"graphcomponent"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"inEdges"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"tgt"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Relation",
    *             "cardinality":"many",
    *             "property":"outEdges"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"src"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Relation",
    *             "cardinality":"one",
    *             "property":"relation"
    *          },
    *          "target":{
    *             "id":"GraphComponent",
    *             "cardinality":"one",
    *             "property":"graphcomponent"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasBanfM2MTransformationClassDiagram4", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * <hr/><p>Create example source graph: </p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G3 : Graph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P1 : Person",
    *          "attributes":[
    *             "firstName=Albert",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P10 : Person",
    *          "attributes":[
    *             "firstName=Nerdi",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P6 : Person",
    *          "attributes":[
    *             "firstName=Jens",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P8 : Person",
    *          "attributes":[
    *             "firstName=Gabi",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P9 : Person",
    *          "attributes":[
    *             "firstName=Nina",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R11 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R2 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R5 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R7 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P1 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R2 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R4 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R5 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P6 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R7 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P8 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P9 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P10 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G3 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R11 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R2 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P1 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R4 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P1 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R5 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P1 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R7 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P6 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R11 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P10 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P8 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R2 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P9 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R4 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P6 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R5 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P8 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R7 : Relation"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformation8", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <hr/><h2>Migrate using Generic Graph representation : </h2><p>did not find method simpleMigrationByGenericGraph(Graph,StoryPage) in class org.sdmlib.test.examples.m2m.BanfM2MTransformations</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G12 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=G526737215245205",
    *             "type=org.sdmlib.test.examples.m2m.model.Graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G13 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=persons"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G14 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G15 : GenericGraph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G16 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=persons"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G17 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=persons"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G18 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=persons"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G19 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=persons"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G20 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=relations"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G21 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=relations"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G22 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=relations"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G23 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=relations"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G24 : GenericLink",
    *          "attributes":[
    *             "srcLabel=graph",
    *             "tgtLabel=relations"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G25 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737215345295",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G26 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G27 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G28 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G29 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737215838432",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G30 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Jens"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G31 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G32 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G33 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737215728333",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G34 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Gabi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G35 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G36 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G37 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737216232249",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G38 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G39 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G40 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737216527130",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G41 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Nerdi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G42 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G43 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G44 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737215649801",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G45 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G46 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737216135238",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G47 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G48 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737215907341",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G49 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G50 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737215795316",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G51 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G52 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737216615672",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G53 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G14 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G30 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G29 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G34 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G33 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G38 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G37 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G41 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G40 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G45 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G44 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G47 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G46 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G49 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G48 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G51 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G50 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G53 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G52 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G13 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G16 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G17 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G18 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G19 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G20 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G21 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G22 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G23 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G24 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G26 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G27 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G28 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G29 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G31 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G32 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G33 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G35 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G36 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G37 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G39 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G40 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G42 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G43 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G44 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G46 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G48 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G50 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G52 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G26 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G27 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G28 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G31 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G29 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G32 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G29 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G35 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G33 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G36 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G33 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G39 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G37 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G42 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G40 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G43 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G40 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G13 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G16 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G17 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G18 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G19 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
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
    *             "id":"G12 : GenericObject"
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
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G22 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G23 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G24 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G44 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G26 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G46 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G27 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G48 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G28 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G48 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G31 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G50 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G32 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G44 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G35 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G50 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G36 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G46 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G39 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G52 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G42 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G52 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G43 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G25 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G13 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G29 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G16 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G33 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G17 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G37 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G18 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G40 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G19 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G44 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G20 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G46 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G21 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G48 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G22 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G50 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G23 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G52 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G24 : GenericLink"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformation12", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericObjectPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g3 : GenericAttributePO",
    *          "attributes":[
    *             "name == firstName",
    *             "name == text"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"objects",
    *             "id":"g2 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g2 : GenericObjectPO"
    *          },
    *          "target":{
    *             "property":"attrs",
    *             "id":"g3 : GenericAttributePO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram12", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericObjectPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g3 : GenericAttributePO",
    *          "attributes":[
    *             "name == kind",
    *             "name == text"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"objects",
    *             "id":"g2 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g2 : GenericObjectPO"
    *          },
    *          "target":{
    *             "property":"attrs",
    *             "id":"g3 : GenericAttributePO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram13", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericLinkPO",
    *          "attributes":[
    *             "tgtLabel == graph",
    *             "tgtLabel == parent",
    *             "srcLabel == gcs"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"links",
    *             "id":"g2 : GenericLinkPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram14", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericLinkPO",
    *          "attributes":[
    *             "srcLabel == graph",
    *             "srcLabel == parent",
    *             "tgtLabel == gcs"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"links",
    *             "id":"g2 : GenericLinkPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram15", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G12 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=G526737215245205",
    *             "type=org.sdmlib.test.examples.m2m.model.Graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G13 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G14 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G15 : GenericGraph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G16 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G17 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G18 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G19 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G20 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G21 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G22 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G23 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G24 : GenericLink",
    *          "attributes":[
    *             "srcLabel=parent",
    *             "tgtLabel=gcs"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G25 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737215345295",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G26 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G27 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G28 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G29 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737215838432",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G30 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Jens"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G31 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G32 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G33 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737215728333",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G34 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Gabi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G35 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G36 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G37 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737216232249",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G38 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G39 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G40 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526737216527130",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G41 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Nerdi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G42 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G43 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G44 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737215649801",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G45 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G46 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737216135238",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G47 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G48 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737215907341",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G49 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G50 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737215795316",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G51 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G52 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526737216615672",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G53 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G14 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G30 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G29 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G34 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G33 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G38 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G37 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G41 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G40 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G45 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G44 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G47 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G46 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G49 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G48 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G51 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G50 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G53 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G52 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G13 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G16 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G17 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G18 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G19 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G20 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G21 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G22 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G23 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G24 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G26 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G27 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G28 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G29 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G31 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G32 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G33 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G35 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G36 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G37 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G39 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G40 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G42 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G43 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G44 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G46 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G48 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G50 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G15 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G52 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G26 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G27 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G28 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G25 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G31 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G29 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G32 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G29 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G35 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G33 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G36 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G33 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G39 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G37 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G42 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G40 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G43 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G40 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G13 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G16 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G17 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G18 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G19 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
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
    *             "id":"G12 : GenericObject"
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
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G22 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G23 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G24 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G12 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G44 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G26 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G46 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G27 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G48 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G28 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G48 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G31 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G50 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G32 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G44 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G35 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G50 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G36 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G46 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G39 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G52 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G42 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G52 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G43 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G25 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G13 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G29 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G16 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G33 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G17 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G37 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G18 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G40 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G19 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G44 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G20 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G46 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G21 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G48 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G22 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G50 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G23 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G52 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G24 : GenericLink"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformation17", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Result graph: </p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G58 : Graph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P54 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "graph=null",
    *             "text=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P59 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "graph=null",
    *             "text=Jens"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P61 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "graph=null",
    *             "text=Gabi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P62 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "graph=null",
    *             "text=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P63 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "graph=null",
    *             "text=Nerdi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R55 : Relation",
    *          "attributes":[
    *             "graph=null",
    *             "kind=null",
    *             "text=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R56 : Relation",
    *          "attributes":[
    *             "graph=null",
    *             "kind=null",
    *             "text=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R57 : Relation",
    *          "attributes":[
    *             "graph=null",
    *             "kind=null",
    *             "text=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R60 : Relation",
    *          "attributes":[
    *             "graph=null",
    *             "kind=null",
    *             "text=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R64 : Relation",
    *          "attributes":[
    *             "graph=null",
    *             "kind=null",
    *             "text=knows"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R55 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P54 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R56 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P54 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R57 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P54 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R60 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P59 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R64 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P63 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P54 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R55 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R56 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R57 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P59 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R60 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P61 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P62 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P63 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"G58 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R64 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P61 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R55 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P62 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R56 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P59 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R57 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P61 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R60 : Relation"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformation19", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <hr/><h2>Even more evolved class diagram : </h2><script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Graph"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Person",
    *          "attributes":[
    *             "text : String"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"persons"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"knows"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"knows"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"persons"
    *          },
    *          "target":{
    *             "id":"Graph",
    *             "cardinality":"one",
    *             "property":"graph"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasBanfM2MTransformationClassDiagram21", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * <hr/><p>Again the input graph:</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G67 : Graph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P65 : Person",
    *          "attributes":[
    *             "firstName=Albert",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P70 : Person",
    *          "attributes":[
    *             "firstName=Jens",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P72 : Person",
    *          "attributes":[
    *             "firstName=Gabi",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P73 : Person",
    *          "attributes":[
    *             "firstName=Nina",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P74 : Person",
    *          "attributes":[
    *             "firstName=Nerdi",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R66 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R68 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R69 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R71 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R75 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P65 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R66 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R68 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R69 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P70 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R71 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P72 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P73 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P74 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G67 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R75 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R66 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P65 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R68 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P65 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R69 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P65 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R71 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P70 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R75 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P74 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P72 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R66 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P73 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R68 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P70 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R69 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P72 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R71 : Relation"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformation25", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>The transformation code:</p>
    * <p>did not find method simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph,StoryPage) in class org.sdmlib.test.examples.m2m.BanfM2MTransformations</p>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericObjectPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g3 : GenericAttributePO",
    *          "attributes":[
    *             "name == firstName",
    *             "name == text"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"objects",
    *             "id":"g2 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g2 : GenericObjectPO"
    *          },
    *          "target":{
    *             "property":"attrs",
    *             "id":"g3 : GenericAttributePO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram27", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericObjectPO",
    *          "attributes":[
    *             "type == org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g3 : GenericLinkPO",
    *          "attributes":[
    *             "tgtLabel == src"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g4 : GenericLinkPO",
    *          "attributes":[
    *             "tgtLabel == tgt",
    *             "srcLabel == knows",
    *             "tgtLabel == knows"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g5 : GenericObjectPO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g6 : GenericObjectPO",
    *          "attributes":[]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"objects",
    *             "id":"g2 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g2 : GenericObjectPO"
    *          },
    *          "target":{
    *             "property":"outgoingLinks",
    *             "id":"g3 : GenericLinkPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g2 : GenericObjectPO"
    *          },
    *          "target":{
    *             "property":"outgoingLinks",
    *             "id":"g4 : GenericLinkPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g3 : GenericLinkPO"
    *          },
    *          "target":{
    *             "property":"tgt",
    *             "id":"g5 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g4 : GenericLinkPO"
    *          },
    *          "target":{
    *             "property":"tgt",
    *             "id":"g6 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g4 : GenericLinkPO"
    *          },
    *          "target":{
    *             "property":"src",
    *             "id":"g5 : GenericObjectPO"
    *          },
    *          "style":"create"
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram28", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericObjectPO",
    *          "attributes":[
    *             "type == org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"objects",
    *             "id":"g2 : GenericObjectPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram29", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Result graph: </p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G77 : Graph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P76 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "parent=null",
    *             "text=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P78 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "parent=null",
    *             "text=Gabi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P79 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "parent=null",
    *             "text=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P80 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "parent=null",
    *             "text=Jens"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P81 : Person",
    *          "attributes":[
    *             "firstName=null",
    *             "parent=null",
    *             "text=Nerdi"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G77 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P76 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G77 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P78 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G77 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P79 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G77 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P80 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G77 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P81 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"knows",
    *             "id":"P78 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"knows",
    *             "id":"P76 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"knows",
    *             "id":"P79 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"knows",
    *             "id":"P76 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"knows",
    *             "id":"P80 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"knows",
    *             "id":"P76 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"knows",
    *             "id":"P80 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"knows",
    *             "id":"P78 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"knows",
    *             "id":"P81 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P81 : Person"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformation32", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <hr/><p>Let us derive the reverse transformation for the first model evolution:</p>
    * <p>_____ forward ______________ backward ___________</p>
    * <pre>            Pattern reverseRenameFirstNameAttrRule = revertRule(genericGraphPO.getPattern());
    * </pre>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericObjectPO",
    *          "attributes":[
    *             "type == org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g3 : GenericAttributePO",
    *          "attributes":[
    *             "name == firstName",
    *             "name == text"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"objects",
    *             "id":"g2 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g2 : GenericObjectPO"
    *          },
    *          "target":{
    *             "property":"attrs",
    *             "id":"g3 : GenericAttributePO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram36", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericObjectPO",
    *          "attributes":[
    *             "type == org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g3 : GenericAttributePO",
    *          "attributes":[
    *             "name == text",
    *             "name == firstName"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"p4 : PatternObject",
    *          "attributes":[]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"objects",
    *             "id":"g2 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g2 : GenericObjectPO"
    *          },
    *          "target":{
    *             "property":"attrs",
    *             "id":"g3 : GenericAttributePO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram37", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <hr/><pre>            Pattern reverseRenameKindAttrRule = revertRule(renameKindAttrGraphPO.getPattern());
    * </pre>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericObjectPO",
    *          "attributes":[
    *             "type == org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g3 : GenericAttributePO",
    *          "attributes":[
    *             "name == kind",
    *             "name == text"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"objects",
    *             "id":"g2 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g2 : GenericObjectPO"
    *          },
    *          "target":{
    *             "property":"attrs",
    *             "id":"g3 : GenericAttributePO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram40", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericObjectPO",
    *          "attributes":[
    *             "type == org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g3 : GenericAttributePO",
    *          "attributes":[
    *             "name == text",
    *             "name == kind"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"p4 : PatternObject",
    *          "attributes":[]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"objects",
    *             "id":"g2 : GenericObjectPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g2 : GenericObjectPO"
    *          },
    *          "target":{
    *             "property":"attrs",
    *             "id":"g3 : GenericAttributePO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram41", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <hr/><pre>            Pattern reverseRenamePersonsLinkRule = revertRule(renamePersonsLinkGraphPO.getPattern());
    * </pre>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericLinkPO",
    *          "attributes":[
    *             "tgtLabel == graph",
    *             "srcLabel == persons",
    *             "tgtLabel == parent",
    *             "srcLabel == gcs"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"links",
    *             "id":"g2 : GenericLinkPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram44", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericLinkPO",
    *          "attributes":[
    *             "tgtLabel == parent",
    *             "srcLabel == gcs",
    *             "tgtLabel == graph",
    *             "srcLabel == persons"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"p3 : PatternObject",
    *          "attributes":[]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"links",
    *             "id":"g2 : GenericLinkPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram45", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <hr/><pre>            Pattern reverseRenameRelationsLinkRule = revertRule(renameRelationsLinkGraphPO.getPattern());
    * </pre>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericLinkPO",
    *          "attributes":[
    *             "srcLabel == graph",
    *             "tgtLabel == relations",
    *             "srcLabel == parent",
    *             "tgtLabel == gcs"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"links",
    *             "id":"g2 : GenericLinkPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram48", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"g1 : GenericGraphPO",
    *          "attributes":[
    *             "<< bound>>"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"g2 : GenericLinkPO",
    *          "attributes":[
    *             "srcLabel == parent",
    *             "tgtLabel == gcs",
    *             "srcLabel == graph",
    *             "tgtLabel == relations"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"p3 : PatternObject",
    *          "attributes":[]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"g1 : GenericGraphPO"
    *          },
    *          "target":{
    *             "property":"links",
    *             "id":"g2 : GenericLinkPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformationPatternDiagram49", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <hr/><script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G100 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Jens"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G101 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G102 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G103 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743201391177",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G104 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Gabi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G105 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G106 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G107 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743202151863",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G108 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G109 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G110 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743202471381",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G111 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Nerdi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G112 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G113 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G114 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743201330738",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G115 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G116 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743202087959",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G117 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G118 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743201593282",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G119 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G120 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743201446226",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G121 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G122 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743202528356",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G123 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G82 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=G526743201193691",
    *             "type=org.sdmlib.test.examples.m2m.model.Graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G83 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G84 : GenericAttribute",
    *          "attributes":[
    *             "name=text",
    *             "value=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G85 : GenericGraph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G86 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G87 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G88 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G89 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G90 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G91 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G92 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G93 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G94 : GenericLink",
    *          "attributes":[
    *             "srcLabel=gcs",
    *             "tgtLabel=parent"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G95 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743201279153",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G96 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G97 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G98 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G99 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743201494732",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G84 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G100 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G99 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G104 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G103 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G108 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G107 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G111 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G110 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G115 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G114 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G117 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G116 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G119 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G118 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G121 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G120 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G123 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G122 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G83 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G86 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G87 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G88 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G89 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G90 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G91 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G92 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G93 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G94 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G96 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G97 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G98 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G99 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G101 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G102 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G103 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G105 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G106 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G107 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G109 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G110 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G112 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G113 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G114 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G116 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G118 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G120 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G122 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G83 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G86 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G87 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G88 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G89 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G90 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G91 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G92 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G93 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G94 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G96 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G97 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G98 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G101 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G99 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G102 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G99 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G105 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G103 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G106 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G103 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G109 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G107 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G112 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G110 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G113 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G110 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G95 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G83 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G99 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G86 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G103 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G87 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G107 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G88 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G110 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G89 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G114 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G90 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G116 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G91 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G118 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G92 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G120 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G93 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G122 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G94 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G114 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G96 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G116 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G97 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G118 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G98 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G118 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G101 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G120 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G102 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G114 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G105 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G120 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G106 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G116 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G109 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G122 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G112 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G122 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G113 : GenericLink"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformation52", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <hr/><script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G100 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Jens"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G101 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G102 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G103 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743201391177",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G104 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Gabi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G105 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G106 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G107 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743202151863",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G108 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G109 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G110 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743202471381",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G111 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Nerdi"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G112 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G113 : GenericLink",
    *          "attributes":[
    *             "srcLabel=inEdges",
    *             "tgtLabel=tgt"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G114 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743201330738",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G115 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G116 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743202087959",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G117 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G118 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743201593282",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G119 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G120 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743201446226",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G121 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G122 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=R526743202528356",
    *             "type=org.sdmlib.test.examples.m2m.model.Relation"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G123 : GenericAttribute",
    *          "attributes":[
    *             "name=kind",
    *             "value=knows"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G82 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=G526743201193691",
    *             "type=org.sdmlib.test.examples.m2m.model.Graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G83 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G84 : GenericAttribute",
    *          "attributes":[
    *             "name=firstName",
    *             "value=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G85 : GenericGraph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G86 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G87 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G88 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G89 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G90 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G91 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G92 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G93 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G94 : GenericLink",
    *          "attributes":[
    *             "srcLabel=persons",
    *             "tgtLabel=graph"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G95 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743201279153",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G96 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G97 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G98 : GenericLink",
    *          "attributes":[
    *             "srcLabel=outEdges",
    *             "tgtLabel=src"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G99 : GenericObject",
    *          "attributes":[
    *             "icon=null",
    *             "name=P526743201494732",
    *             "type=org.sdmlib.test.examples.m2m.model.Person"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G84 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G100 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G99 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G104 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G103 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G108 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G107 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G111 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G110 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G115 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G114 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G117 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G116 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G119 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G118 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G121 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G120 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"attrs",
    *             "id":"G123 : GenericAttribute"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"owner",
    *             "id":"G122 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G83 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G86 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G87 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G88 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G89 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G90 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G91 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G92 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G93 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G94 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G96 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G97 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G98 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G99 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G101 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G102 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G103 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G105 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G106 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G107 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G109 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G110 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G112 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericlink",
    *             "id":"G113 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G114 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G116 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G118 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G120 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G85 : GenericGraph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"genericobject",
    *             "id":"G122 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G83 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G86 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G87 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G88 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G89 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G90 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G91 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G92 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G93 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G94 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G82 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G96 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G97 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G98 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G95 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G101 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G99 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G102 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G99 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G105 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G103 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G106 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G103 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G109 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G107 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G112 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G110 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"incommingLinks",
    *             "id":"G113 : GenericLink"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"G110 : GenericObject"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G95 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G83 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G99 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G86 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G103 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G87 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G107 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G88 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G110 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G89 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G114 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G90 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G116 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G91 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G118 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G92 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G120 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G93 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G122 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G94 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G114 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G96 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G116 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G97 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G118 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G98 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G118 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G101 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G120 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G102 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G114 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G105 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G120 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G106 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G116 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G109 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G122 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G112 : GenericLink"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"G122 : GenericObject"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"outgoingLinks",
    *             "id":"G113 : GenericLink"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformation54", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <hr/><script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G126 : Graph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P124 : Person",
    *          "attributes":[
    *             "firstName=Albert",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P129 : Person",
    *          "attributes":[
    *             "firstName=Jens",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P131 : Person",
    *          "attributes":[
    *             "firstName=Gabi",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P132 : Person",
    *          "attributes":[
    *             "firstName=Nina",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P133 : Person",
    *          "attributes":[
    *             "firstName=Nerdi",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R125 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R127 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R128 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R130 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R134 : Relation",
    *          "attributes":[
    *             "kind=knows",
    *             "parent=null",
    *             "text=null"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P124 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R125 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R127 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R128 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P129 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R130 : Relation"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P131 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P132 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"person",
    *             "id":"P133 : Person"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graph",
    *             "id":"G126 : Graph"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"relation",
    *             "id":"R134 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R125 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P124 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R127 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P124 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R128 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P124 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R130 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P129 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"outEdges",
    *             "id":"R134 : Relation"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"P133 : Person"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P131 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R125 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P132 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R127 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P129 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R128 : Relation"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"P131 : Person"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"inEdges",
    *             "id":"R130 : Relation"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBanfM2MTransformation56", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href=
    *      '../../../../../../../../doc/BanfM2MTransformation.html'>BanfM2MTransformation.html</a>
    * @see <a href='../../../../../../../../doc/BanfM2MTransformation.html'>BanfM2MTransformation.html</a>
 */
   @Test
   public void testBanfM2MTransformation()
   {
      Storyboard storyboard = new Storyboard();

      storyboard.add("Class diagram for source model:");

      ClassModel model = BanfM2MModelGen.genModel();

      storyboard.addClassDiagram(model);

      // ==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("Class diagram for target model:");

      model = new ClassModel("org.sdmlib.test.examples.m2m.model");

      graphClazz = model.createClazz("Graph");

      Clazz graphComponentClazz = model.createClazz("GraphComponent")
         .withAttribute("text", DataType.STRING);

      Clazz nodeClazz = model.createClazz("Person")
         .withSuperClazz(graphComponentClazz);

      Clazz edgeClazz = model.createClazz("Relation")
         .withSuperClazz(graphComponentClazz);

      graphClazz.withBidirectional(graphComponentClazz, "gcs", Association.MANY, "parent", Association.ONE);

      edgeClazz.withBidirectional(nodeClazz, "src", Association.ONE, "outEdges", Association.MANY);

      edgeClazz.withBidirectional(nodeClazz, "tgt", Association.ONE, "inEdges", Association.MANY);

      // model.removeAllGeneratedCode("examples", "examples", "examples");

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);

      // storyboard.dumpHTML();

      // ==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("Create example source graph: ");

      Graph graph = createExampleGraph();

      storyboard.addObjectDiagramOnlyWith(graph.getPersons(), graph.getRelations());

      // ==========================================================================

      storyboard.add("<hr/>");
      storyboard.add("<h2>Migrate using Generic Graph representation : </h2>");

      graph = createExampleGraph();

      storyboard.add(storyboard.getMethodText("src/test/java", this.getClass().getName(), "simpleMigrationByGenericGraph(Graph,StoryPage)"));

      Graph tgtGraph = simpleMigrationByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramOnlyWith(tgtGraph.getGcs());

      // ==========================================================================
      storyboard.add("<hr/>");
      storyboard.add("<h2>Even more evolved class diagram : </h2>");

      model = new ClassModel("org.sdmlib.test.examples.m2m.model");

      graphClazz = model.createClazz("Graph");

      nodeClazz = model.createClazz("Person")
         .withAttribute("text", DataType.STRING);

      graphClazz.withBidirectional(nodeClazz, "persons", Association.MANY, "graph", Association.ONE);

      nodeClazz.withBidirectional(nodeClazz, "knows", Association.MANY, "knows", Association.MANY);

      model.generate("src/test/java");

      storyboard.addClassDiagram(model);

      storyboard.add("<hr/>");

      storyboard.add("Again the input graph:");

      graph = createExampleGraph();

      storyboard.addObjectDiagramOnlyWith(graph.getPersons(), graph.getRelations());

      storyboard.add("The transformation code:");

      storyboard.add(storyboard.getMethodText("src/test/java", this.getClass().getName(), "simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph,StoryPage)"));

      Graph tgtGraph2 = simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(graph, storyboard);

      storyboard.add("Result graph: ");

      storyboard.addObjectDiagramOnlyWith(tgtGraph2.getPersons());

      // ===============================================================================
      storyboard.add("<hr/>");
      storyboard.add("Let us derive the reverse transformation for the first model evolution:");

      Graph sourceGraphRevers = simpleReverseMigration(tgtGraph, storyboard);

      storyboard.addObjectDiagramOnlyWith(sourceGraphRevers.getPersons(), sourceGraphRevers.getRelations());

      storyboard.dumpHTML();
   }

   private Graph simpleReverseMigration(Graph tgtGraph, Storyboard storyboard)
   {
      // make the graph generic
      GenericGraph genGraph = new Specific2Generic().convert(GraphComponentCreator.createIdMap("s"), tgtGraph);

      // revert the transformations
      storyboard.add("_____ forward ______________ backward ___________");

      GenericGraphPO genericGraphPO = new GenericGraphPO().withModifier(Pattern.BOUND);
      genericGraphPO
         .hasObjects()
         .hasType(Person.class.getName())
         .hasAttrs()
         .hasName(Person.PROPERTY_FIRSTNAME)
         .startCreate()
         .hasName(GraphComponent.PROPERTY_TEXT)
         .allMatches();

      storyboard.markCodeStart();
      Pattern reverseRenameFirstNameAttrRule = revertRule(genericGraphPO.getPattern());
      storyboard.addCode();

      storyboard.addPattern(genericGraphPO, false);

      PatternObject reverseRenameFirstNameAttrRulePO = new PatternObject<>();

      reverseRenameFirstNameAttrRulePO.withPatternObjectName("reverseRenameFirstNameAttrRulePO");

      reverseRenameFirstNameAttrRulePO.withPattern(reverseRenameFirstNameAttrRule);

      storyboard.addPattern(reverseRenameFirstNameAttrRulePO, false);

      storyboard.add("<hr/>");

      renameKindAttrGraphPO = new GenericGraphPO().withModifier(Pattern.BOUND);
      renameKindAttrGraphPO
         .hasObjects()
         .hasType(Relation.class.getName())
         .hasAttrs()
         .hasName(Relation.PROPERTY_KIND)
         .startCreate()
         .hasName(GraphComponent.PROPERTY_TEXT)
         .allMatches();

      storyboard.markCodeStart();
      Pattern reverseRenameKindAttrRule = revertRule(renameKindAttrGraphPO.getPattern());
      storyboard.addCode();

      storyboard.addPattern(renameKindAttrGraphPO, false);

      PatternObject reverseRenameKindAttrRulePO = new PatternObject<>();

      reverseRenameKindAttrRulePO.withPatternObjectName("reverseRenameKindAttrRulePO");

      reverseRenameKindAttrRulePO.withPattern(reverseRenameKindAttrRule);

      storyboard.addPattern(reverseRenameKindAttrRulePO, false);

      storyboard.add("<hr/>");

      // rename graph--nodes links to parent--gcs links
      renamePersonsLinkGraphPO = new GenericGraphPO().withModifier(Pattern.BOUND);
      renamePersonsLinkGraphPO
         .hasLinks()
         .hasTgtLabel(Person.PROPERTY_GRAPH)
         .hasSrcLabel(Graph.PROPERTY_PERSONS)
         .startCreate()
         .hasTgtLabel(GraphComponent.PROPERTY_PARENT)
         .hasSrcLabel(Graph.PROPERTY_GCS)
         .allMatches();

      storyboard.markCodeStart();
      Pattern reverseRenamePersonsLinkRule = revertRule(renamePersonsLinkGraphPO.getPattern());
      storyboard.addCode();

      storyboard.addPattern(renamePersonsLinkGraphPO, false);

      PatternObject reverseRenamePersonsLinkRulePO = new PatternObject<>();

      reverseRenamePersonsLinkRulePO.withPatternObjectName("reverseRenamePersonsLinkRulePO");

      reverseRenamePersonsLinkRulePO.withPattern(reverseRenamePersonsLinkRule);

      storyboard.addPattern(reverseRenamePersonsLinkRulePO, false);

      storyboard.add("<hr/>");

      // rename graph--edges links to parent--gcs links
      renameRelationsLinkGraphPO = new GenericGraphPO().withModifier(Pattern.BOUND);
      renameRelationsLinkGraphPO
         .hasLinks()
         .hasSrcLabel(Relation.PROPERTY_GRAPH)
         .hasTgtLabel(Graph.PROPERTY_RELATIONS)
         .startCreate()
         .hasSrcLabel(GraphComponent.PROPERTY_PARENT)
         .hasTgtLabel(Graph.PROPERTY_GCS)
         .allMatches();

      storyboard.markCodeStart();
      Pattern reverseRenameRelationsLinkRule = revertRule(renameRelationsLinkGraphPO.getPattern());
      storyboard.addCode();

      storyboard.addPattern(renameRelationsLinkGraphPO, false);

      PatternObject reverseRenameRelationsLinkRulePO = new PatternObject<>();

      reverseRenameRelationsLinkRulePO.withPatternObjectName("reverseRenameRelationsLinkRulePO");

      reverseRenameRelationsLinkRulePO.withPattern(reverseRenameRelationsLinkRule);

      storyboard.addPattern(reverseRenameRelationsLinkRulePO, false);

      storyboard.add("<hr/>");

      storyboard.addObjectDiagramOnlyWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());

      storyboard.add("<hr/>");

      // apply the transformations
      PatternObject<?, ?> boundPO = (PatternObject<?, ?>) reverseRenameRelationsLinkRule.getElements().first();
      reverseRenameRelationsLinkRule.rebind(boundPO, genGraph);
      reverseRenameRelationsLinkRule.allMatches();

      boundPO = (PatternObject<?, ?>) reverseRenamePersonsLinkRule.getElements().first();
      reverseRenamePersonsLinkRule.rebind(boundPO, genGraph);
      reverseRenamePersonsLinkRule.allMatches();

      boundPO = (PatternObject<?, ?>) reverseRenameKindAttrRule.getElements().first();
      reverseRenameKindAttrRule.rebind(boundPO, genGraph);
      reverseRenameKindAttrRule.allMatches();

      boundPO = (PatternObject<?, ?>) reverseRenameFirstNameAttrRule.getElements().first();
      reverseRenameFirstNameAttrRule.rebind(boundPO, genGraph);
      reverseRenameFirstNameAttrRule.allMatches();

      storyboard.addObjectDiagramOnlyWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());

      storyboard.add("<hr/>");

      // make the graph specific again
      Graph srcGraph = (Graph) new Generic2Specific().convert(GraphCreator.createIdMap("s"), null, genGraph);

      return srcGraph;
   }

   private Pattern<?> revertRule(Pattern<?> forwardRule)
   {
      IdMap origMap = forwardRule.getIdMap();
      origMap.with(PatternCreator.createIdMap("x"));

      SDMLibIdMap fwdMap = new SDMLibIdMap("y");

      JsonArray jsonArray = fwdMap.toJsonArray(forwardRule);
      Object firstObject = jsonArray.get(0);
      jsonArray.remove(0);
      jsonArray.add(firstObject);

      SDMLibIdMap bwdMap = new SDMLibIdMap("z");

      PatternElement<?> decode = (PatternElement<?>) bwdMap.decode(jsonArray);
      Pattern<?> backwardRule = (Pattern<?>) decode.getPattern();
      backwardRule.setIdMap(origMap);

      // look for attribute constraint with modifer create
      for (PatternElement<?> elem : backwardRule.getElements())
      {
         if (elem instanceof AttributeConstraint)
         {
            AttributeConstraint attrConstraint = (AttributeConstraint) elem;

            if (Pattern.CREATE.equals(attrConstraint.getModifier()))
            {
               // find the corresponding search constraint
               AttributeConstraint otherConstraint = attrConstraint.getSrc().getAttrConstraints().createAttrNameCondition(attrConstraint.getAttrName()).first();

               // exchange values
               Object attrConstraintValue = attrConstraint.getTgtValue();
               attrConstraint.setTgtValue(otherConstraint.getTgtValue());
               otherConstraint.setTgtValue(attrConstraintValue);
            }
         }
      }

      return backwardRule;
   }

   private Graph simpleMigrationToEvenMoreEvolvedGraphByGenericGraph(Graph origGraph, Storyboard storyboard)
   {
      GenericGraph genGraph = new Specific2Generic()
         .convert(PersonCreator.createIdMap("g1"), origGraph);

      // rename name to text attributes
      GenericGraphPO po = new GenericGraphPO();
      po.withCandidates(genGraph);
      po.hasObjects()
         .hasAttrs()
         .hasName(Person.PROPERTY_FIRSTNAME)
         .startCreate()
         .hasName(Person.PROPERTY_TEXT)
         .allMatches();

      storyboard.addPattern(po, false);

      // replace n.l.e.l.n by n.l.n
      GenericObjectPO edgePO = new GenericGraphPO(genGraph)
         .hasObjects()
         .hasType(Relation.class.getName());

      GenericLinkPO srcLinkPO = edgePO.hasOutgoingLinks().hasTgtLabel(Relation.PROPERTY_SRC);

      GenericLinkPO tgtLinkPO = edgePO.hasOutgoingLinks().hasTgtLabel(Relation.PROPERTY_TGT);

      GenericObjectPO srcNodePO = srcLinkPO.hasTgt();

      GenericObjectPO tgtNodePO = tgtLinkPO.hasTgt();

      edgePO.destroy();

      srcLinkPO.destroy();

      GenericLinkSet allMatches = tgtLinkPO.startCreate()
         .hasSrc(srcNodePO)
         .hasSrcLabel(Person.PROPERTY_KNOWS)
         .hasTgtLabel(Person.PROPERTY_KNOWS)
         .allMatches();

      storyboard.addPattern(edgePO, false);

      // destroy dangling edges
      GenericObjectPO danglingPO = new GenericGraphPO(genGraph)
         .hasObjects()
         .hasType(Relation.class.getName());

      danglingPO.destroy();

      danglingPO.allMatches();

      storyboard.addPattern(danglingPO, false);

      Graph tgtGraph = (Graph) new Generic2Specific().convert(PersonCreator.createIdMap("tg"), null, genGraph);

      return tgtGraph;

   }

   private Graph simpleMigrationByGenericGraph(Graph origGraph, Storyboard storyboard)
   {
      GenericGraph genGraph = new Specific2Generic()
         .convert(GraphCreator.createIdMap("g"), origGraph);

      storyboard.addObjectDiagramOnlyWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());

      renameFirstNameAttrGraphPO = new GenericGraphPO(genGraph);
      renameFirstNameAttrGraphPO
         .hasObjects()
         .hasAttrs()
         .hasName(Person.PROPERTY_FIRSTNAME)
         .startCreate()
         .hasName(GraphComponent.PROPERTY_TEXT)
         .allMatches();

      storyboard.addPattern(renameFirstNameAttrGraphPO, false);

      // rename name to text attributes
      renameKindAttrGraphPO = new GenericGraphPO(genGraph);
      renameKindAttrGraphPO
         .hasObjects()
         .hasAttrs()
         .hasName(Relation.PROPERTY_KIND)
         .startCreate()
         .hasName(GraphComponent.PROPERTY_TEXT)
         .allMatches();

      storyboard.addPattern(renameKindAttrGraphPO, false);

      // rename graph--nodes links to parent--gcs links
      GenericGraphPO renameNodesEdgeGraphPO = new GenericGraphPO(genGraph);
      renameNodesEdgeGraphPO
         .hasLinks()
         .hasTgtLabel(Person.PROPERTY_GRAPH)
         .startCreate()
         .hasTgtLabel(GraphComponent.PROPERTY_PARENT)
         .hasSrcLabel(Graph.PROPERTY_GCS)
         .allMatches();

      storyboard.addPattern(renameNodesEdgeGraphPO, false);

      // storyboard.add("<hr/>");

      // rename graph--edges links to parent--gcs links
      GenericGraphPO genericGraphPO = new GenericGraphPO(genGraph);
      genericGraphPO
         .hasLinks()
         .hasSrcLabel(Relation.PROPERTY_GRAPH)
         .startCreate()
         .hasSrcLabel(GraphComponent.PROPERTY_PARENT)
         .hasTgtLabel(Graph.PROPERTY_GCS)
         .allMatches();

      storyboard.addPattern(genericGraphPO, false);

      storyboard.addObjectDiagramOnlyWith(genGraph.getObjects(), genGraph.getLinks(), genGraph.getObjects().getAttrs());

      Graph tgtGraph = (Graph) new Generic2Specific().convert(GraphComponentCreator.createIdMap("tg"), null, genGraph);

      return tgtGraph;
   }

   private Graph createExampleGraph()
   {
      Graph graph = new Graph();

      Person albert = graph.createPersons().withFirstName("Albert");
      Person jens = graph.createPersons().withFirstName("Jens");
      Person gabi = graph.createPersons().withFirstName("Gabi");
      Person nina = graph.createPersons().withFirstName("Nina");
      Person nerdi = graph.createPersons().withFirstName("Nerdi");

      graph.createRelations().withKind("knows").withSrc(albert).withTgt(gabi);
      graph.createRelations().withKind("knows").withSrc(albert).withTgt(nina);
      graph.createRelations().withKind("knows").withSrc(albert).withTgt(jens);
      graph.createRelations().withKind("knows").withSrc(jens).withTgt(gabi);
      graph.createRelations().withKind("knows").withSrc(nerdi).withTgt(nerdi);

      return graph;
   }

}
