package org.sdmlib.test.examples.reachabilitygraphs;

import org.junit.Test;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodePO;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.NodeSet;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStateCreator;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStatePO;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStateSet;

public class ReachbilityGraphSimpleExamples
{
   
   /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java' type='text/x-java'>LazyReachabilityGraphAttrsAndNodes</a></p>
    * <p>Start graph: </p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"N2 : Node",
    *          "attributes":[
    *             "num=42"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N3 : Node",
    *          "attributes":[
    *             "num=4"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S1 : SimpleState"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N2 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N2 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasLazyReachabilityGraphAttrsAndNodes2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Rewrite rule: </p>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"s1 : SimpleStatePO",
    *          "attributes":[]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"n2 : NodePO",
    *          "attributes":[
    *             "num == 23",
    *             "num == 42"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"s1 : SimpleStatePO"
    *          },
    *          "target":{
    *             "property":"nodes",
    *             "id":"n2 : NodePO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasLazyReachabilityGraphAttrsAndNodesPatternDiagram3", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"N12 : Node",
    *          "attributes":[
    *             "num=42"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N13 : Node",
    *          "attributes":[
    *             "num=23"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N14 : Node",
    *          "attributes":[
    *             "num=42"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N15 : Node",
    *          "attributes":[
    *             "num=42"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N2 : Node",
    *          "attributes":[
    *             "num=42"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N3 : Node",
    *          "attributes":[
    *             "num=4"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R10 : RuleApplication",
    *          "attributes":[
    *             "description=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : ReachabilityGraph"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R5 : ReachableState",
    *          "attributes":[
    *             "descr=1 0.0\u000aorg.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState@71023677",
    *             "failureState=false",
    *             "metricValue=0.0",
    *             "number=1"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R6 : ReachableState",
    *          "attributes":[
    *             "descr=2 0.0\u000aorg.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState@14e68ce5",
    *             "failureState=false",
    *             "metricValue=0.0",
    *             "number=2"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R7 : ReachableState",
    *          "attributes":[
    *             "descr=3 0.0\u000aorg.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState@79f63a19",
    *             "failureState=false",
    *             "metricValue=0.0",
    *             "number=3"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R8 : RuleApplication",
    *          "attributes":[
    *             "description=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S1 : SimpleState"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S11 : SimpleState"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S9 : SimpleState"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graphRoot",
    *             "id":"S1 : SimpleState"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"reachablestate",
    *             "id":"R5 : ReachableState"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graphRoot",
    *             "id":"S9 : SimpleState"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"reachablestate",
    *             "id":"R6 : ReachableState"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"graphRoot",
    *             "id":"S11 : SimpleState"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"reachablestate",
    *             "id":"R7 : ReachableState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N2 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N2 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N12 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S9 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N13 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S9 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N14 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S11 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N15 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S11 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"resultOf",
    *             "id":"R8 : RuleApplication"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"R6 : ReachableState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"resultOf",
    *             "id":"R10 : RuleApplication"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"tgt",
    *             "id":"R7 : ReachableState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"ruleapplications",
    *             "id":"R8 : RuleApplication"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"R5 : ReachableState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"ruleapplications",
    *             "id":"R10 : RuleApplication"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"src",
    *             "id":"R6 : ReachableState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"states",
    *             "id":"R5 : ReachableState"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"R4 : ReachabilityGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"states",
    *             "id":"R6 : ReachableState"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"R4 : ReachabilityGraph"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"states",
    *             "id":"R7 : ReachableState"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"R4 : ReachabilityGraph"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasLazyReachabilityGraphAttrsAndNodes5", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Check: number of reachable states 3 actual 3</p>
    */
   @Test
   public void LazyReachabilityGraphAttrsAndNodes() throws Exception
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");
      
      // TODO AZ: change to uni dir assocs
      SimpleState root = new SimpleState();
      
      Node kid1 = root.createNodes().withNum(42);

      Node kid2 = root.createNodes().withNum(4);

      kid1.withNext(kid2);
      
      story.add("Start graph: ");
      story.addObjectDiagram(root);
      
      // nodes rule
      SimpleStatePO rootPO1 = new SimpleStatePO();
      
      NodePO kidPO = rootPO1.createNodesPO()
            .createNumCondition(4);
      
      kidPO.destroy();
      
      NodePO newKid = rootPO1.createNodesPO(Pattern.CREATE)
            .createNumAssignment(23);
      
      
      // attr rule
      SimpleStatePO rootPO2 = new SimpleStatePO();
      
      kidPO = rootPO2.createNodesPO()
            .createNumCondition(23)
            .createNumAssignment(42);
      
      story.add("Rewrite rule: ");
      story.addPattern(rootPO2, true);
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
            .withMasterMap(SimpleStateCreator.createIdMap("s"))
            .withLazyCloning()
            .withRules(rootPO1, rootPO2);
      
      ReachableState startState = new ReachableState().withGraphRoot(root);
      
      reachabilityGraph.withStart(startState);
      
      reachabilityGraph.explore();
      
      story.addObjectDiagram(reachabilityGraph);
      
      story.assertEquals("number of reachable states", 3, reachabilityGraph.getStates().size());
      
      NodeSet nodes = reachabilityGraph.getStates().getGraphRoot().instanceOf(new SimpleStateSet()).getNodes();
      // story.assertEquals("number of nodes building the two graphs", 4, nodes.size());
      
      story.dumpHTML();
   }
   /**
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java' type='text/x-java'>ReachabilityGraphSimpleIsomorphismTest</a></p>
    * <p>Create two rings of three nodes with a mark at one node.</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"N2 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N3 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N4 : Node",
    *          "attributes":[
    *             "num=42"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S1 : SimpleState"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N2 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N4 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N3 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N2 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N4 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N4 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N2 : Node"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasReachabilityGraphSimpleIsomorphismTest2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"N6 : Node",
    *          "attributes":[
    *             "num=42"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N7 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N8 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S5 : SimpleState"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N7 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N6 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N8 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N7 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N6 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S5 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N7 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S5 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N8 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S5 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N8 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N6 : Node"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasReachabilityGraphSimpleIsomorphismTest3", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>compute certificates</p>
    * <p>Check: Both certificates are equal.  true</p>
    * <p>4*1
    * 5*1
    * 6*1
    * 7*1
    * 4: 1:    prev: 1 
    *    next: 2 
    *    graph: 3 
    * 5: 1:    prev: 2 
    *    next: 1 
    *    graph: 3 
    * 6: 2:    prev: 1 
    *    next: 1 
    *    graph: 3 
    * 7: 3:    nodes: 1 1 2 
    * 1: Node
    *    num: 0
    * 2: Node
    *    num: 42
    * 3: SimpleState
    * </p>
    * <p>Check: Graphs are isomorphic: true</p>
    * <p>true</p>
    * <p>removing the num</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"N2 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N3 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N4 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S1 : SimpleState"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N2 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N4 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N3 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N2 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N4 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N4 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N2 : Node"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasReachabilityGraphSimpleIsomorphismTest10", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"N6 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N7 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N8 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S5 : SimpleState"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N7 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N6 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N8 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N7 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N6 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S5 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N7 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S5 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N8 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S5 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N8 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N6 : Node"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasReachabilityGraphSimpleIsomorphismTest11", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Check: Both certificates are again equal.  true</p>
    * <p>5*3
    * 6*1
    * 3: 1:    prev: 1 
    *    next: 1 
    *    graph: 2 
    * 4: 2:    nodes: 1 1 1 
    * 5: 3:    prev: 3 
    *    next: 3 
    *    graph: 4 
    * 6: 4:    nodes: 3 3 3 
    * 1: Node
    *    num: 0
    * 2: SimpleState
    * </p>
    * <p>Check: Graphs are isomorphic: true</p>
    * @see <a href='../../../../../../../../doc/internal/ReachabilityGraphSimpleIsomorphismTest.html'>ReachabilityGraphSimpleIsomorphismTest.html</a>
    */
   @Test
   public void ReachabilityGraphSimpleIsomorphismTest()
   {
      Storyboard storyboard = new Storyboard().withDocDirName("doc/internal");
      
      storyboard.add("Create two rings of three nodes with a mark at one node.");
      
      // graph 1
      SimpleState s11 = new SimpleState();
      Node n11 = s11.createNodes();
      Node n12 = s11.createNodes().withPrev(n11);
      Node n13 = s11.createNodes().withPrev(n12).withNext(n11);
      
      // graph2
      SimpleState s21 = new SimpleState();
      Node n21 = s21.createNodes();
      Node n22 = s21.createNodes().withPrev(n21);
      Node n23 = s21.createNodes().withPrev(n22).withNext(n21);
      
      // mark them at different places
      n21.withNum(42);
      
      n13.withNum(42);
      
      storyboard.addObjectDiagram(s11);
      
      storyboard.addObjectDiagram(s21);
      
      storyboard.add("compute certificates");
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.setMasterMap(SimpleStateCreator.createIdMap("s"));
      
      ReachableState rs1 = new ReachableState().withGraphRoot(s11).withParent(reachabilityGraph);
      ReachableState rs2 = new ReachableState().withGraphRoot(s21).withParent(reachabilityGraph);
      
      Object s1cert = rs1.dynComputeCertificate();
      Object s2cert = rs2.dynComputeCertificate();
      
      storyboard.assertTrue("Both certificates are equal. ", s1cert.equals(s2cert));
      
      storyboard.add(s1cert.toString());
      
      Object match = reachabilityGraph.lazyMatch(rs1, rs2);
      
      storyboard.assertNotNull("Graphs are isomorphic:", match);
      storyboard.add(match.toString());
      
      
      storyboard.add("removing the num");
      
      n21.withNum(0);
      
      n13.withNum(0);
      
      storyboard.addObjectDiagram(s11);
      
      storyboard.addObjectDiagram(s21);
      
      s1cert = rs1.dynComputeCertificate();
      s2cert = rs2.dynComputeCertificate();
      
      storyboard.assertTrue("Both certificates are again equal. ", s1cert.equals(s2cert));
      
      storyboard.add(s1cert.toString());

      match = reachabilityGraph.lazyMatch(rs1, rs2);
      
      storyboard.assertNotNull("Graphs are isomorphic:", match);
      
      storyboard.dumpHTML();
   }

    /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachbilityGraphSimpleExamples.java' type='text/x-java'>ReachabilitGraphSameCertificatesNonIsomorphic</a></p>
    * <p>graph 1 two rings of two nodes</p>
    * <p>graph 2 one ring of four nodes</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"N2 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N3 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N4 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N5 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S1 : SimpleState"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N2 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N4 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N5 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S1 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N3 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N2 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N5 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N4 : Node"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasReachabilitGraphSameCertificatesNonIsomorphic3", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"N10 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N7 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N8 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"N9 : Node",
    *          "attributes":[
    *             "num=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S6 : SimpleState"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N8 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N7 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N9 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N8 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N10 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N9 : Node"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N7 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S6 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N8 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S6 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N9 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S6 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"nodes",
    *             "id":"N10 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"graph",
    *             "id":"S6 : SimpleState"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"N10 : Node"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"N7 : Node"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasReachabilitGraphSameCertificatesNonIsomorphic4", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>compute certificates</p>
    * <p>Both certificates are equal: true</p>
    * <p>5*4
    * 6*1
    * 3: 1:    prev: 1 
    *    next: 1 
    *    graph: 2 
    * 4: 2:    nodes: 1 1 1 1 
    * 5: 3:    prev: 3 
    *    next: 3 
    *    graph: 4 
    * 6: 4:    nodes: 3 3 3 3 
    * 1: Node
    *    num: 0
    * 2: SimpleState
    * </p>
    * <p>Check: Graphs are not isomorphic: true</p>
    * @see <a href='../../../../../../../../doc/internal/ReachabilitGraphSameCertificatesNonIsomorphic.html'>ReachabilitGraphSameCertificatesNonIsomorphic.html</a>
 */
   @Test
   public void ReachabilitGraphSameCertificatesNonIsomorphic()
   {
      Storyboard storyboard = new Storyboard().withDocDirName("doc/internal");
      
      storyboard.add("graph 1 two rings of two nodes");
      storyboard.add("graph 2 one ring of four nodes");
      
      // graph 1
      SimpleState s11 = new SimpleState();
      Node n11 = s11.createNodes();
      Node n12 = s11.createNodes().withPrev(n11).withNext(n11);
      Node n13 = s11.createNodes();
      Node n14 = s11.createNodes().withPrev(n13).withNext(n13);
      
      
      // graph2
      SimpleState s21 = new SimpleState();
      Node n21 = s21.createNodes();
      Node n22 = s21.createNodes().withPrev(n21);
      Node n23 = s21.createNodes().withPrev(n22);
      Node n24 = s21.createNodes().withPrev(n23).withNext(n21);
      
      
      storyboard.addObjectDiagram(s11);
      
      storyboard.addObjectDiagram(s21);
      
      storyboard.add("compute certificates");
      
      ReachabilityGraph reachabilityGraph = new ReachabilityGraph();
      reachabilityGraph.setMasterMap(SimpleStateCreator.createIdMap("s"));
      
      ReachableState rs1 = new ReachableState().withGraphRoot(s11).withParent(reachabilityGraph);
      ReachableState rs2 = new ReachableState().withGraphRoot(s21).withParent(reachabilityGraph);
      
      Object s1cert = rs1.dynComputeCertificate();
      Object s2cert = rs2.dynComputeCertificate();
      
      storyboard.add("Both certificates are equal: " + (s1cert.equals(s2cert)));
      
      storyboard.add(s1cert.toString());
      
      Object match = reachabilityGraph.lazyMatch(rs1, rs2);
      
      storyboard.assertTrue("Graphs are not isomorphic:", match == null);
            
      storyboard.dumpHTML();
   }

}
