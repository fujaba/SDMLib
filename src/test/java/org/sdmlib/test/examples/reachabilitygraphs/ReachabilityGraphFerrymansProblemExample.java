package org.sdmlib.test.examples.reachabilitygraphs;

import org.junit.Test;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.models.pattern.util.ReachabilityGraphCreator;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Bank;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.River;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BankSet;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.BoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.CargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverCreator;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverPO;
import org.sdmlib.test.examples.reachabilitygraphs.ferrymansproblem.util.RiverSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBank;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBoat;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UCargo;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.URiver;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBankPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBoatPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UCargoPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.URiverCreator;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.URiverPO;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.URiverSet;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class ReachabilityGraphFerrymansProblemExample {
  /**
   * 
   * <p>
   * Storyboard <a href=
   * './src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'
   * type='text/x-java'>FerrymansProblemExample</a>
   * </p>
   * <p>
   * initial situation:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B2 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B3 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B4 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"C5 : Cargo", "attributes":[ "boat=null", "name=cabbage"
   * ] }, { "type":"clazz", "id":"C6 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C7 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz",
   * "id":"R1 : River" } ], "edges":[ { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B2 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B4 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B2 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C5 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B3 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C6 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B3 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C7 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B3 : Bank" } } ] }
   * ; json["options"]={"canvasid":"canvasFerrymansProblemExample2", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 8*1
   * 9*1
   * 10*1
   * 11*1
   * 12*1
   * 13*1
   * 14*1
   * 8: 1:    boat: 3
   *    river: 7
   *    cargos: 4 5 6 
   * 9: 2:    river: 7
   * 10: 3:    river: 7
   *    bank: 1
   * 11: 4:    bank: 1
   * 12: 5:    bank: 1
   * 13: 6:    bank: 1
   * 14: 7:    boat: 3
   *    banks: 1 2 
   * 1: Bank
   *    name: left
   *    age: 0
   * 2: Bank
   *    name: right
   *    age: 0
   * 3: Boat
   * 4: Cargo
   *    name: cabbage
   * 5: Cargo
   *    name: goat
   * 6: Cargo
   *    name: wolf
   * 7: River
   * </pre>
   * 
   * <script> var json = { "type":"object", "nodes":[ { "type":"patternObject", "id":"r1 : RiverPO",
   * "attributes":[] }, { "type":"patternObject", "id":"b2 : BoatPO", "attributes":[] }, {
   * "type":"objectdiagram", "style":"nac", "info":"NegativeApplicationCondition", "nodes":[ {
   * "type":"patternObject", "id":"c3 : CargoPO", "attributes":[] } ] }, { "type":"patternObject",
   * "id":"b4 : BankPO", "attributes":[] }, { "type":"patternObject", "id":"c5 : CargoPO",
   * "attributes":[] }, { "type":"objectdiagram", "style":"nac",
   * "info":"NegativeApplicationCondition", "nodes":[ { "type":"patternObject", "id":"c6 : CargoPO",
   * "attributes":[ "name == goat" ] }, { "type":"patternObject", "id":"c7 : CargoPO", "attributes":[]
   * } ] } ], "edges":[ { "typ":"EDGE", "source":{ "property":" ", "id":"r1 : RiverPO" }, "target":{
   * "property":"boat", "id":"b2 : BoatPO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"b2 :
   * BoatPO" }, "target":{ "property":"cargo", "id":"c3 : CargoPO" } }, { "typ":"EDGE", "source":{
   * "property":" ", "id":"b2 : BoatPO" }, "target":{ "property":"bank", "id":"b4 : BankPO" } }, {
   * "typ":"EDGE", "source":{ "property":" ", "id":"b4 : BankPO" }, "target":{ "property":"cargos",
   * "id":"c5 : CargoPO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"b4 : BankPO" },
   * "target":{ "property":"cargos", "id":"c6 : CargoPO" } }, { "typ":"EDGE", "source":{ "property":"
   * ", "id":"b4 : BankPO" }, "target":{ "property":"cargos", "id":"c7 : CargoPO" } }, { "typ":"EDGE",
   * "source":{ "property":" ", "id":"b4 : BankPO" }, "target":{ "property":"cargos", "id":"c5 :
   * CargoPO" }, "style":"destroy" }, { "typ":"EDGE", "source":{ "property":" ", "id":"b2 : BoatPO" },
   * "target":{ "property":"cargo", "id":"c5 : CargoPO" }, "style":"create" } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExamplePatternDiagram3", "display":"html",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script> <script> var
   * json = { "type":"object", "nodes":[ { "type":"patternObject", "id":"r1 : RiverPO",
   * "attributes":[] }, { "type":"patternObject", "id":"b2 : BoatPO", "attributes":[] }, {
   * "type":"patternObject", "id":"b3 : BankPO", "attributes":[] }, { "type":"patternObject", "id":"b4
   * : BankPO", "attributes":[] }, { "type":"objectdiagram", "style":"nac",
   * "info":"NegativeApplicationCondition", "nodes":[ { "type":"patternObject", "id":"c5 : CargoPO",
   * "attributes":[ "name == goat" ] }, { "type":"patternObject", "id":"c6 : CargoPO", "attributes":[]
   * } ] }, { "type":"objectdiagram", "style":"nac", "info":"OptionalSubPattern", "nodes":[ {
   * "type":"patternObject", "id":"c7 : CargoPO", "attributes":[] } ] } ], "edges":[ { "typ":"EDGE",
   * "source":{ "property":" ", "id":"r1 : RiverPO" }, "target":{ "property":"boat", "id":"b2 :
   * BoatPO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"b2 : BoatPO" }, "target":{
   * "property":"bank", "id":"b3 : BankPO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"r1 :
   * RiverPO" }, "target":{ "property":"banks", "id":"b4 : BankPO" } }, { "typ":"EDGE", "source":{
   * "property":" ", "id":"b3 : BankPO" }, "target":{ "property":"cargos", "id":"c5 : CargoPO" } }, {
   * "typ":"EDGE", "source":{ "property":" ", "id":"b3 : BankPO" }, "target":{ "property":"cargos",
   * "id":"c6 : CargoPO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"b2 : BoatPO" },
   * "target":{ "property":"bank", "id":"b3 : BankPO" }, "style":"destroy" }, { "typ":"EDGE",
   * "source":{ "property":" ", "id":"b2 : BoatPO" }, "target":{ "property":"bank", "id":"b4 : BankPO"
   * }, "style":"create" }, { "typ":"EDGE", "source":{ "property":" ", "id":"b2 : BoatPO" },
   * "target":{ "property":"cargo", "id":"c7 : CargoPO" } }, { "typ":"EDGE", "source":{ "property":"
   * ", "id":"c7 : CargoPO" }, "target":{ "property":"boat", "id":"b2 : BoatPO" }, "style":"destroy"
   * }, { "typ":"EDGE", "source":{ "property":" ", "id":"c7 : CargoPO" }, "target":{
   * "property":"bank", "id":"b4 : BankPO" }, "style":"create" } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExamplePatternDiagram4", "display":"html",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 1
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B2 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B3 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B4 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"C5 : Cargo", "attributes":[ "boat=null", "name=cabbage"
   * ] }, { "type":"clazz", "id":"C6 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C7 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz",
   * "id":"R1 : River" } ], "edges":[ { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B2 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B4 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B2 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C5 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B3 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C6 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B3 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C7 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B3 : Bank" } } ] }
   * ; json["options"]={"canvasid":"canvasFerrymansProblemExample7", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 2
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B10 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B12 : Bank", "attributes":[
   * "age=0", "boat=null", "name=right" ] }, { "type":"clazz", "id":"B9 : Boat" }, { "type":"clazz",
   * "id":"C11 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C13 :
   * Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C14 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R8 : River" } ], "edges":[ {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B10 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B9 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B10 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R8 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B12 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B9 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C13 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B9 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C11 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B10 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C14 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B10 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample9", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 3
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B16 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B17 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B19 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C18 : Cargo", "attributes":[ "boat=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C20 : Cargo", "attributes":[ "boat=null", "name=wolf"
   * ] }, { "type":"clazz", "id":"C21 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"R15 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B19 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B16 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B17 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R15 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B19 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B16 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C18 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B17 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C20 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B17 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C21 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B19 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample11",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 4
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B23 : Boat" }, {
   * "type":"clazz", "id":"B24 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B26 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"C25 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C27 :
   * Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C28 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R22 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B24 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B23 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B24 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R22 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B26 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R22 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B23 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R22 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C27 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B23 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C25 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B26 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C28 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B26 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample13", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 5
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B30 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B31 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=right" ] }, { "type":"clazz", "id":"B33 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"C32 : Cargo", "attributes":[ "boat=null", "name=goat" ]
   * }, { "type":"clazz", "id":"C34 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C35 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"R29 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B33 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B30 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B31 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R29 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B33 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R29 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B30 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R29 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C32 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B31 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C34 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B33 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C35 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B33 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample15",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 6
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B37 : Boat" }, {
   * "type":"clazz", "id":"B38 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B40 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C39 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C41 :
   * Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, { "type":"clazz", "id":"C42 : Cargo",
   * "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"R36 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B38 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B37 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B38 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R36 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B40 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R36 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B37 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R36 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C41 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B37 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C39 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B38 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C42 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B40 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample17", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 7
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B44 : Boat" }, {
   * "type":"clazz", "id":"B45 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B47 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C46 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C48 :
   * Cargo", "attributes":[ "bank=null", "name=wolf" ] }, { "type":"clazz", "id":"C49 : Cargo",
   * "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"R43 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B45 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B44 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B45 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R43 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B47 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B44 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C48 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B44 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C46 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B45 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C49 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B47 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample19", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 8
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B51 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B52 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B54 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C53 : Cargo", "attributes":[ "boat=null", "name=wolf" ]
   * }, { "type":"clazz", "id":"C55 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C56 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"R50 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B54 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B51 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B52 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R50 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B54 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B51 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C53 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B52 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C55 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B54 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C56 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B54 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample21",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 9
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B58 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B59 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B61 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C60 : Cargo", "attributes":[ "boat=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C62 : Cargo", "attributes":[ "boat=null", "name=goat"
   * ] }, { "type":"clazz", "id":"C63 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"R57 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B61 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B58 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B59 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R57 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B61 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R57 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B58 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R57 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C60 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B59 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C62 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B61 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C63 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B61 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample23",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 10
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B65 : Boat" }, {
   * "type":"clazz", "id":"B66 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B68 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"C67 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C69 :
   * Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C70 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R64 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B66 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B65 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B66 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R64 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B68 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R64 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B65 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R64 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C69 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B65 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C67 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B66 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C70 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B68 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample25", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 11
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B72 : Boat" }, {
   * "type":"clazz", "id":"B73 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B75 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"C74 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C76 :
   * Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, { "type":"clazz", "id":"C77 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R71 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B73 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B72 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B73 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R71 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B75 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R71 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B72 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R71 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C76 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B72 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C74 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B73 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C77 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B75 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample27", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 12
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B79 : Boat" }, {
   * "type":"clazz", "id":"B80 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B82 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"C81 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C83 :
   * Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C84 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R78 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B80 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B79 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B80 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R78 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B82 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B79 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C83 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B79 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C81 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B80 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C84 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B82 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample29", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 13
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B86 : Boat" }, {
   * "type":"clazz", "id":"B87 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B89 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"C88 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C90 :
   * Cargo", "attributes":[ "bank=null", "name=wolf" ] }, { "type":"clazz", "id":"C91 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R85 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B87 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B86 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B87 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R85 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B89 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R85 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B86 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R85 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C90 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B86 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C88 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B87 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C91 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B89 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample31", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 14
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B93 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B94 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=right" ] }, { "type":"clazz", "id":"B96 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"C95 : Cargo", "attributes":[ "boat=null", "name=cabbage"
   * ] }, { "type":"clazz", "id":"C97 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C98 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"R92 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B96 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B93 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B94 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R92 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B96 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R92 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B93 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R92 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C95 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B94 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C97 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B96 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C98 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B96 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample33",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 15
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B100 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B101 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=right" ] }, { "type":"clazz", "id":"B103 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"C102 : Cargo", "attributes":[ "boat=null", "name=wolf" ]
   * }, { "type":"clazz", "id":"C104 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C105 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"R99 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B103 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B100 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B101 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R99 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B103 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R99 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B100 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R99 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C102 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B101 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C104 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B103 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C105 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B103 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample35",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 16
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B107 : Boat" }, {
   * "type":"clazz", "id":"B108 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B110 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C109 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C111 :
   * Cargo", "attributes":[ "bank=null", "name=wolf" ] }, { "type":"clazz", "id":"C112 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R106 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B108 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B107 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B108 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R106 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B110 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R106 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B107 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R106 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C111 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B107 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C109 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B108 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C112 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B110 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample37", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 17
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B114 : Boat" }, {
   * "type":"clazz", "id":"B115 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B117 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C116 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C118 :
   * Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C119 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R113 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B115 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B114 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B115 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R113 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B117 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B114 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C118 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B114 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C116 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B115 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C119 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B117 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample39", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 18
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B121 : Boat" }, {
   * "type":"clazz", "id":"B122 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B124 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C123 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C125 :
   * Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, { "type":"clazz", "id":"C126 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R120 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B122 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B121 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B122 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R120 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B124 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R120 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B121 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R120 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C125 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B121 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C123 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B122 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C126 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B124 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample41", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 19
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B128 : Boat" }, {
   * "type":"clazz", "id":"B129 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B131 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C130 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C132
   * : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C133 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R127 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B129 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B128 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B129 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R127 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B131 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R127 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B128 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R127 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C132 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B128 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C130 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B129 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C133 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B131 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample43", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 20
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B135 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B136 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B138 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C137 : Cargo", "attributes":[ "boat=null", "name=goat"
   * ] }, { "type":"clazz", "id":"C139 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C140 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"R134 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B138 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B135 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B136 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R134 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B138 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R134 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B135 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R134 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C137 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B136 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C139 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B138 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C140 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B138 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample45",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 21
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B142 : Boat" }, {
   * "type":"clazz", "id":"B143 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B145 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"C144 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C146 :
   * Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, { "type":"clazz", "id":"C147 : Cargo",
   * "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"R141 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B143 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B142 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B143 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R141 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B145 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R141 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B142 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R141 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C146 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B142 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C144 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B143 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C147 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B145 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample47", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 22
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B149 : Boat" }, {
   * "type":"clazz", "id":"B150 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B152 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"C151 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C153
   * : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, { "type":"clazz", "id":"C154 : Cargo",
   * "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"R148 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B150 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B149 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B150 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R148 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B152 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B149 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C153 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B149 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C151 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B150 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C154 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B152 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample49", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 23
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B156 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B157 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=right" ] }, { "type":"clazz", "id":"B159 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"C158 : Cargo", "attributes":[ "boat=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C160 : Cargo", "attributes":[ "boat=null",
   * "name=wolf" ] }, { "type":"clazz", "id":"C161 : Cargo", "attributes":[ "boat=null", "name=goat" ]
   * }, { "type":"clazz", "id":"R155 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B159 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B156 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B157 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R155 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B159 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B156 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C158 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B157 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C160 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B157 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C161 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B159 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample51",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 24
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B163 : Boat" }, {
   * "type":"clazz", "id":"B164 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B166 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C165 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C167
   * : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C168 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R162 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B164 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B163 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B164 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R162 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B166 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R162 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B163 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R162 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C167 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B163 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C165 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B166 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C168 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B166 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample53", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 25
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B170 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B171 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B173 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C172 : Cargo", "attributes":[ "boat=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C174 : Cargo", "attributes":[ "boat=null",
   * "name=wolf" ] }, { "type":"clazz", "id":"C175 : Cargo", "attributes":[ "boat=null", "name=goat" ]
   * }, { "type":"clazz", "id":"R169 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B173 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B170 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B171 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R169 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B173 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R169 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B170 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R169 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C172 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B173 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C174 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B173 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C175 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B173 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample55",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 26
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B177 : Boat" }, {
   * "type":"clazz", "id":"B178 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B180 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"C179 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C181
   * : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C182 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R176 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B178 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B177 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B178 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R176 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B180 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R176 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B177 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R176 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C181 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B177 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C179 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B178 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C182 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B178 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemExample57", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Check: Number of Reachable States expected: 27 actual 27
   * </p>
   * <p>
   * Small reachbility graph with hyperlinks to states:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":null, "edges":null } ;
   * json["options"]={"canvasid":"canvasferrymansproblemRG", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * large reachbility graph with embedded states:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B10 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B100 : Boat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"B101 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"B103 : Bank", "attributes":[ "age=0", "name=left" ] },
   * { "type":"clazz", "id":"B107 : Boat" }, { "type":"clazz", "id":"B108 : Bank", "attributes":[
   * "age=0", "name=left" ] }, { "type":"clazz", "id":"B110 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=right" ] }, { "type":"clazz", "id":"B114 : Boat" }, { "type":"clazz",
   * "id":"B115 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B117 :
   * Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz", "id":"B12 :
   * Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz", "id":"B121 :
   * Boat" }, { "type":"clazz", "id":"B122 : Bank", "attributes":[ "age=0", "name=left" ] }, {
   * "type":"clazz", "id":"B124 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, {
   * "type":"clazz", "id":"B128 : Boat" }, { "type":"clazz", "id":"B129 : Bank", "attributes":[
   * "age=0", "name=left" ] }, { "type":"clazz", "id":"B131 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=right" ] }, { "type":"clazz", "id":"B135 : Boat", "attributes":[ "cargo=null"
   * ] }, { "type":"clazz", "id":"B136 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] },
   * { "type":"clazz", "id":"B138 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B142 : Boat" }, { "type":"clazz", "id":"B143 : Bank", "attributes":[ "age=0", "name=right"
   * ] }, { "type":"clazz", "id":"B145 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] },
   * { "type":"clazz", "id":"B149 : Boat" }, { "type":"clazz", "id":"B150 : Bank", "attributes":[
   * "age=0", "name=right" ] }, { "type":"clazz", "id":"B152 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B156 : Boat", "attributes":[ "cargo=null" ]
   * }, { "type":"clazz", "id":"B157 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, {
   * "type":"clazz", "id":"B159 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B16 : Boat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B163 : Boat" }, {
   * "type":"clazz", "id":"B164 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B166 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"B17 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"B170 : Boat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B171 : Bank",
   * "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz", "id":"B173 : Bank",
   * "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B177 : Boat" }, {
   * "type":"clazz", "id":"B178 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B180 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"B19 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B2 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B23 : Boat" }, { "type":"clazz",
   * "id":"B24 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B26 : Bank",
   * "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz", "id":"B3 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B30 : Boat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"B31 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"B33 : Bank", "attributes":[ "age=0", "name=left" ] }, {
   * "type":"clazz", "id":"B37 : Boat" }, { "type":"clazz", "id":"B38 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B4 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"B40 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"B44 : Boat" }, { "type":"clazz", "id":"B45 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B47 : Bank", "attributes":[
   * "age=0", "boat=null", "name=right" ] }, { "type":"clazz", "id":"B51 : Boat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"B52 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=left" ] }, { "type":"clazz", "id":"B54 : Bank", "attributes":[ "age=0", "name=right" ] }, {
   * "type":"clazz", "id":"B58 : Boat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B59 :
   * Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz", "id":"B61 : Bank",
   * "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B65 : Boat" }, {
   * "type":"clazz", "id":"B66 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B68 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"B72 : Boat" }, { "type":"clazz", "id":"B73 : Bank", "attributes":[ "age=0", "name=right" ]
   * }, { "type":"clazz", "id":"B75 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B79 : Boat" }, { "type":"clazz", "id":"B80 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"B82 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=left" ] }, { "type":"clazz", "id":"B86 : Boat" }, { "type":"clazz", "id":"B87 : Bank",
   * "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B89 : Bank", "attributes":[
   * "age=0", "boat=null", "name=left" ] }, { "type":"clazz", "id":"B9 : Boat" }, { "type":"clazz",
   * "id":"B93 : Boat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B94 : Bank",
   * "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz", "id":"B96 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"C102 : Cargo", "attributes":[
   * "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C104 : Cargo", "attributes":[ "boat=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C105 : Cargo", "attributes":[ "boat=null",
   * "name=goat" ] }, { "type":"clazz", "id":"C109 : Cargo", "attributes":[ "boat=null", "name=goat" ]
   * }, { "type":"clazz", "id":"C11 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C111 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C112 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C116 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C118 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C119 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C123 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C125 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C126 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C13 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C130 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C132 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C133 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C137 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C139 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C14 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C140 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C144 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C146 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C147 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C151 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C153 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C154 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C158 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C160 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C161 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C165 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C167 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C168 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C172 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C174 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C175 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C179 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C18 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C181 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C182 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C20 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C21 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C25 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C27 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C28 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C32 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C34 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C35 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C39 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C41 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C42 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C46 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C48 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C49 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C5 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C53 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C55 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C56 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C6 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz",
   * "id":"C60 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C62 :
   * Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C63 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C67 : Cargo", "attributes":[
   * "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C69 : Cargo", "attributes":[
   * "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C7 : Cargo", "attributes":[ "boat=null",
   * "name=wolf" ] }, { "type":"clazz", "id":"C70 : Cargo", "attributes":[ "boat=null", "name=wolf" ]
   * }, { "type":"clazz", "id":"C74 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C76 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C77 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C81 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C83 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C84 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C88 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C90 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C91 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C95 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C97 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C98 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"R1 : River" }, { "type":"clazz", "id":"R106 : River" }, { "type":"clazz",
   * "id":"R113 : River" }, { "type":"clazz", "id":"R120 : River" }, { "type":"clazz", "id":"R127 :
   * River" }, { "type":"clazz", "id":"R134 : River" }, { "type":"clazz", "id":"R141 : River" }, {
   * "type":"clazz", "id":"R148 : River" }, { "type":"clazz", "id":"R15 : River" }, { "type":"clazz",
   * "id":"R155 : River" }, { "type":"clazz", "id":"R162 : River" }, { "type":"clazz", "id":"R169 :
   * River" }, { "type":"clazz", "id":"R176 : River" }, { "type":"clazz", "id":"R183 :
   * ReachabilityGraph" }, { "type":"clazz", "id":"R184 : ReachableState", "attributes":[ "descr=1
   * 0.0\u000aleft cabbage goat wolf boat \\___/\u000aright ", "failureState=false",
   * "metricValue=0.0", "number=1" ] }, { "type":"clazz", "id":"R185 : ReachableState", "attributes":[
   * "descr=2 0.0\u000aleft cabbage wolf boat \\goat/\u000aright ", "failureState=false",
   * "metricValue=0.0", "number=2" ] }, { "type":"clazz", "id":"R186 : ReachableState", "attributes":[
   * "descr=3 0.0\u000aleft cabbage wolf\u000aright goat boat \\___/", "failureState=false",
   * "metricValue=0.0", "number=3" ] }, { "type":"clazz", "id":"R187 : ReachableState", "attributes":[
   * "descr=4 0.0\u000aright boat \\goat/\u000aleft cabbage wolf", "failureState=false",
   * "metricValue=0.0", "number=4" ] }, { "type":"clazz", "id":"R188 : ReachableState", "attributes":[
   * "descr=5 0.0\u000aright goat\u000aleft cabbage wolf boat \\___/", "failureState=false",
   * "metricValue=0.0", "number=5" ] }, { "type":"clazz", "id":"R189 : ReachableState", "attributes":[
   * "descr=6 0.0\u000aleft wolf boat \\cabbage/\u000aright goat", "failureState=false",
   * "metricValue=0.0", "number=6" ] }, { "type":"clazz", "id":"R190 : ReachableState", "attributes":[
   * "descr=7 0.0\u000aleft cabbage boat \\wolf/\u000aright goat", "failureState=false",
   * "metricValue=0.0", "number=7" ] }, { "type":"clazz", "id":"R191 : ReachableState", "attributes":[
   * "descr=8 0.0\u000aleft wolf\u000aright goat cabbage boat \\___/", "failureState=false",
   * "metricValue=0.0", "number=8" ] }, { "type":"clazz", "id":"R192 : ReachableState", "attributes":[
   * "descr=9 0.0\u000aleft cabbage\u000aright goat wolf boat \\___/", "failureState=false",
   * "metricValue=0.0", "number=9" ] }, { "type":"clazz", "id":"R193 : ReachableState", "attributes":[
   * "descr=10 0.0\u000aright cabbage boat \\goat/\u000aleft wolf", "failureState=false",
   * "metricValue=0.0", "number=10" ] }, { "type":"clazz", "id":"R194 : ReachableState",
   * "attributes":[ "descr=11 0.0\u000aright goat boat \\cabbage/\u000aleft wolf",
   * "failureState=false", "metricValue=0.0", "number=11" ] }, { "type":"clazz", "id":"R195 :
   * ReachableState", "attributes":[ "descr=12 0.0\u000aright wolf boat \\goat/\u000aleft cabbage",
   * "failureState=false", "metricValue=0.0", "number=12" ] }, { "type":"clazz", "id":"R196 :
   * ReachableState", "attributes":[ "descr=13 0.0\u000aright goat boat \\wolf/\u000aleft cabbage",
   * "failureState=false", "metricValue=0.0", "number=13" ] }, { "type":"clazz", "id":"R197 :
   * ReachableState", "attributes":[ "descr=14 0.0\u000aright cabbage\u000aleft wolf goat boat
   * \\___/", "failureState=false", "metricValue=0.0", "number=14" ] }, { "type":"clazz", "id":"R198 :
   * ReachableState", "attributes":[ "descr=15 0.0\u000aright wolf\u000aleft cabbage goat boat
   * \\___/", "failureState=false", "metricValue=0.0", "number=15" ] }, { "type":"clazz", "id":"R199 :
   * ReachableState", "attributes":[ "descr=16 0.0\u000aleft goat boat \\wolf/\u000aright cabbage",
   * "failureState=false", "metricValue=0.0", "number=16" ] }, { "type":"clazz", "id":"R200 :
   * ReachableState", "attributes":[ "descr=17 0.0\u000aleft wolf boat \\goat/\u000aright cabbage",
   * "failureState=false", "metricValue=0.0", "number=17" ] }, { "type":"clazz", "id":"R201 :
   * ReachableState", "attributes":[ "descr=18 0.0\u000aleft goat boat \\cabbage/\u000aright wolf",
   * "failureState=false", "metricValue=0.0", "number=18" ] }, { "type":"clazz", "id":"R202 :
   * ReachableState", "attributes":[ "descr=19 0.0\u000aleft cabbage boat \\goat/\u000aright wolf",
   * "failureState=false", "metricValue=0.0", "number=19" ] }, { "type":"clazz", "id":"R203 :
   * ReachableState", "attributes":[ "descr=20 0.0\u000aleft goat\u000aright cabbage wolf boat
   * \\___/", "failureState=false", "metricValue=0.0", "number=20" ] }, { "type":"clazz", "id":"R204 :
   * ReachableState", "attributes":[ "descr=21 0.0\u000aright wolf boat \\cabbage/\u000aleft goat",
   * "failureState=false", "metricValue=0.0", "number=21" ] }, { "type":"clazz", "id":"R205 :
   * ReachableState", "attributes":[ "descr=22 0.0\u000aright cabbage boat \\wolf/\u000aleft goat",
   * "failureState=false", "metricValue=0.0", "number=22" ] }, { "type":"clazz", "id":"R206 :
   * ReachableState", "attributes":[ "descr=23 0.0\u000aright cabbage wolf\u000aleft goat boat
   * \\___/", "failureState=false", "metricValue=0.0", "number=23" ] }, { "type":"clazz", "id":"R207 :
   * ReachableState", "attributes":[ "descr=24 0.0\u000aleft boat \\goat/\u000aright cabbage wolf",
   * "failureState=false", "metricValue=0.0", "number=24" ] }, { "type":"clazz", "id":"R208 :
   * ReachableState", "attributes":[ "descr=25 0.0\u000aleft \u000aright cabbage wolf goat boat
   * \\___/", "failureState=false", "metricValue=0.0", "number=25" ] }, { "type":"clazz", "id":"R209 :
   * ReachableState", "attributes":[ "descr=26 0.0\u000aright cabbage wolf boat \\goat/\u000aleft ",
   * "failureState=false", "metricValue=0.0", "number=26" ] }, { "type":"clazz", "id":"R210 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R211 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R212 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R213 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R214 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R215 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R216 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R217 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R218 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R219 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R22 : River" },
   * { "type":"clazz", "id":"R220 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R221 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R222 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R223 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R224 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R225 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R226 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R227 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R228 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R229 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R230 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R231 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R232 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R233 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R234 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R235 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R236 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R237 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R238 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R239 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R240 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R241 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R242 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R243 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R244 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R245 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R29 : River" }, { "type":"clazz", "id":"R36 : River" }, { "type":"clazz",
   * "id":"R43 : River" }, { "type":"clazz", "id":"R50 : River" }, { "type":"clazz", "id":"R57 :
   * River" }, { "type":"clazz", "id":"R64 : River" }, { "type":"clazz", "id":"R71 : River" }, {
   * "type":"clazz", "id":"R78 : River" }, { "type":"clazz", "id":"R8 : River" }, { "type":"clazz",
   * "id":"R85 : River" }, { "type":"clazz", "id":"R92 : River" }, { "type":"clazz", "id":"R99 :
   * River" } ], "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B2 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B10 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B9 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B19 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B16 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B24 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B23 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B33 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B30 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B38 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B37 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B45 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B44 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B54 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B51 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B61 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B58 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B66 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B65 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B73 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B72 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B80 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B79 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B87 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B86 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B96 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B93 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B103 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B100 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B108 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B107 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B115 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B114 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B122 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B121 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B129 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B128 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B138 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B135 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B143 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B142 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B150 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B149 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B159 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B156 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B164 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B163 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B173 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B170 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B178 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B177 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B3 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B4 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B10 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B12 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B17 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B19 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B24 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R22 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B26 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R22 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B31 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R29 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B33 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R29 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B38 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R36 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B40 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R36 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B45 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B47 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B52 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B54 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B59 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R57 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B61 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R57 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B66 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R64 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B68 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R64 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B73 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R71 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B75 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R71 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B80 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B82 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B87 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R85 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B89 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R85 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B94 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R92 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B96 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R92 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B101 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R99 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B103 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R99 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B108 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R106 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B110 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R106 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B115 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B117 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B122 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R120 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B124 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R120 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B129 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R127 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B131 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R127 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B136 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R134 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B138 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R134 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B143 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R141 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B145 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R141 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B150 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B152 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B157 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B159 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B164 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R162 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B166 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R162 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B171 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R169 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B173 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R169 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B178 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R176 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B180 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R176 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B2 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R1 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B9 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B16 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B23 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R22 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B30 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R29 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B37 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R36 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B44 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B51 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B58 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R57 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B65 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R64 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B72 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R71 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B79 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B86 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R85 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B93 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R92 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B100 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R99 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B107 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R106 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B114 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B121 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R120 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B128 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R127 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B135 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R134 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B142 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R141 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B149 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B156 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B163 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R162 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B170 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R169 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B177 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R176 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"C13 : Cargo" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B9 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C27 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B23 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"cargo", "id":"C41 : Cargo" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B37 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"C48 : Cargo" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B44 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"cargo", "id":"C69 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B65 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"C76 : Cargo" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B72 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C83 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B79 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"cargo", "id":"C90 : Cargo" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B86 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"C111 : Cargo" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B107 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"cargo", "id":"C118 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B114 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"C125 : Cargo" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B121 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C132 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B128 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo", "id":"C146 : Cargo" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B142 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"C153 : Cargo" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B149 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"cargo", "id":"C167 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B163 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"C181 : Cargo" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B177 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C5 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B3 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C6 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B3 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C7 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B3 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C11 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B10 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C14 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B10 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C18 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B17 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C20 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B17 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C21 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B19 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C25 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B26 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C28 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B26 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C32 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B31 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C34 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B33 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C35 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B33 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C39 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B38 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C42 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B40 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C46 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B45 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C49 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B47 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C53 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B52 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C55 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B54 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C56 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B54 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C60 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B59 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C62 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B61 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C63 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B61 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C67 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B66 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C70 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B68 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C74 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B73 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C77 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B75 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C81 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B80 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C84 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B82 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C88 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B87 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C91 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B89 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C95 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B94 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C97 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B96 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C98 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B96 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C102 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B101 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C104 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B103 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C105 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B103 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C109 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B108 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C112 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B110 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C116 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B115 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C119 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B117 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C123 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B122 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C126 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B124 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C130 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B129 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C133 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B131 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C137 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B136 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C139 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B138 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C140 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B138 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C144 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B143 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C147 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B145 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C151 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B150 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C154 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B152 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C158 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B157 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C160 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B157 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C161 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B159 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C165 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B166 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C168 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B166 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C172 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B173 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C174 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B173 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C175 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B173 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C179 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B178 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C182 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B178 : Bank" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"graphRoot", "id":"R1 : River" }, "target":{
   * "cardinality":"one", "property":"reachablestate", "id":"R184 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R8 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R185 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R15 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R186 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R22 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R187 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R29 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R188 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R36 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R189 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R43 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R190 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R50 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R191 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R57 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R192 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R64 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R193 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R71 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R194 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R78 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R195 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R85 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R196 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R92 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R197 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R99 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R198 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R106 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R199 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R113 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R200 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R120 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R201 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R127 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R202 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R134 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R203 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R141 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R204 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R148 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R205 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R155 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R206 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R162 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R207 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R169 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R208 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R176 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R209 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R211 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R184 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R210 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R185 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R212 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R186 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R215 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R186 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R213 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R187 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R214 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R188 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R218 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R188 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R219 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R188 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R216 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R189 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R217 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R190 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R220 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R191 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R224 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R191 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R221 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R192 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R227 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R192 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R222 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R193 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R223 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R194 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R225 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R195 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R226 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R196 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R228 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R197 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R232 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R197 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R229 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R198 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R235 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R198 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R230 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R199 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R231 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R200 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R233 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R201 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R234 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R202 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R236 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R203 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R237 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R203 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R241 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R203 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R238 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R204 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R239 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R205 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R240 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R206 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R243 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R206 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R242 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R207 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R244 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R208 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R245 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R209 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R210 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R184 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R212 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R185 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R213 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R186 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R214 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R186 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R211 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R187 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R216 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R188 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R217 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R188 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R215 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R188 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R220 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R189 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R221 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R190 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R222 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R191 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R223 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R191 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R225 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R192 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R226 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R192 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R228 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R193 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R218 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R194 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R229 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R195 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R219 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R196 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R230 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R197 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R231 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R197 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R233 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R198 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R234 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R198 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R236 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R199 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R224 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R200 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R237 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R201 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R227 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R202 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R238 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R203 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R239 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R203 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R240 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R203 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R235 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R204 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R232 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R205 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R242 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R206 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R241 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R206 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R244 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R207 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R245 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R208 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R243 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R209 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R184 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R185 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R186 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R187 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R188 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R189 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R190 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R191 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R192 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R193 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R194 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R195 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R196 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R197 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R198 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R199 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R200 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R201 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R202 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R203 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R204 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R205 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R206 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R207 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R208 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R209 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemExample62",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Found a solution.
   * </p>
   * 
   * @see <a href=
   *      '../../../../../../../../doc/FerrymansProblemExample.html'>FerrymansProblemExample.html</a>
   */
  @Test //
  public void FerrymansProblemExample() {
    Storyboard storyboard = new Storyboard();

    // ================================================
    storyboard.add("initial situation:");

    River river = new River();

    Boat boat = river.createBoat();

    Bank left = river.createBanks().withName("left").withBoat(boat);

    left.createCargos().withName("cabbage");
    left.createCargos().withName("goat");
    left.createCargos().withName("wolf");

    river.createBanks().withName("right");

    storyboard.addObjectDiagram(river);


    ReachableState rs1 = new ReachableState().withGraphRoot(river);

    RiverCreator cc = new RiverCreator();

    IdMap map = cc.createIdMap("s");

    ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
        .withMasterMap(map).withStates(rs1).withTodo(rs1);

    Object s1cert = rs1.dynComputeCertificate();

    storyboard.addPreformatted(s1cert.toString());


    // ================================================
    // map.with(new ModelPatternCreator());
    // FlipBook flipBook = new FlipBook().withMap(map);
    // String id = map.getId(reachabilityGraph);
    //
    // ================================================
    // load boat rule

    RiverPO riverPO = new RiverPO();

    Pattern loadPattern = (Pattern) riverPO.getPattern().withName("load").withDebugMode(Kanban.DEBUG_ON);
    // map.getId(loadPattern);

    BoatPO boatPO = riverPO.hasBoat();

    boatPO.startNAC().hasCargo().endNAC();

    BankPO bankPO = boatPO.hasBank();

    CargoPO cargoPO = bankPO.hasCargos();

    CargoPO goatPO = bankPO.startNAC().hasCargos().hasName("goat").hasMatchOtherThen(cargoPO);
    bankPO.hasCargos().hasMatchOtherThen(cargoPO).hasMatchOtherThen(goatPO);
    bankPO.endNAC();

    loadPattern.createCloneOp();

    bankPO.startDestroy().hasCargos(cargoPO).endDestroy();

    boatPO.createCargo(cargoPO);

    storyboard.addPattern(loadPattern, false);

    reachabilityGraph.addToRules(loadPattern);

    // ================================================
    // move boat rule

    riverPO = new RiverPO();

    Pattern movePattern = (Pattern) riverPO.getPattern().withName("move").withDebugMode(Kanban.DEBUG_ON);

    boatPO = riverPO.hasBoat();

    BankPO oldBankPO = boatPO.hasBank();

    BankPO newBankPO = riverPO.hasBanks().hasMatchOtherThen(oldBankPO);

    // do not leave the goat alone with some other cargo
    goatPO = oldBankPO.startNAC().hasCargos().hasName("goat");
    oldBankPO.hasCargos().hasMatchOtherThen(goatPO).endNAC();

    movePattern.createCloneOp();

    boatPO.startDestroy().hasBank(oldBankPO).endDestroy();

    boatPO.startCreate().hasBank(newBankPO).endCreate();

    cargoPO = boatPO.startSubPattern().hasCargo();

    cargoPO.createBoatLink(boatPO, Pattern.DESTROY);

    cargoPO.startCreate().hasBank(newBankPO).endCreate().endSubPattern();

    storyboard.addPattern(movePattern, false);

    reachabilityGraph.addToRules(movePattern);

    // ================================================

    long size = reachabilityGraph.explore();

    // ================================================

    for (ReachableState s : reachabilityGraph.getStates()) {
      storyboard.add("Reachable State " + s.getNumber());
      River r = (River) s.getGraphRoot();
      storyboard.addObjectDiagram(r, r.getBoat(), r.getBanks(), r.getBanks().getCargos());
    }


    storyboard.assertEquals("Number of Reachable States expected: ", 27L, size);

    storyboard.add("Small reachbility graph with hyperlinks to states: ");
    storyboard.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));

    storyboard.add("large reachbility graph with embedded states: ");
    storyboard.addObjectDiagram(reachabilityGraph);

    RiverSet rivers = new RiverSet().with(reachabilityGraph.getStates().getGraphRoot());
    BankSet banks = rivers.getBanks().hasName("right");

    for (Bank bank : banks) {
      if (bank.getCargos().size() == 3) {
        storyboard.add("Found a solution.");
        break;
      }
    }

    storyboard.dumpHTML();
  }

  /**
   * 
   * <p>
   * Storyboard <a href=
   * './src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'
   * type='text/x-java'>FerrymansProblemManuel</a>
   * </p>
   * <p>
   * initial situation:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B2 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B3 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B4 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"C5 : Cargo", "attributes":[ "boat=null", "name=cabbage"
   * ] }, { "type":"clazz", "id":"C6 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C7 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz",
   * "id":"R1 : River" } ], "edges":[ { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B2 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B4 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B2 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C5 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B3 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C6 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B3 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C7 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B3 : Bank" } } ] }
   * ; json["options"]={"canvasid":"canvasFerrymansProblemManuel2", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 1
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B2 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B3 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B4 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"C5 : Cargo", "attributes":[ "boat=null", "name=cabbage"
   * ] }, { "type":"clazz", "id":"C6 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C7 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz",
   * "id":"R1 : River" } ], "edges":[ { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B2 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B4 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B2 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C5 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B3 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C7 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B3 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C6 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B3 : Bank" } } ] }
   * ; json["options"]={"canvasid":"canvasFerrymansProblemManuel4", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 2
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B10 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B11 : Bank", "attributes":[
   * "age=0", "boat=null", "name=right" ] }, { "type":"clazz", "id":"B9 : Boat" }, { "type":"clazz",
   * "id":"C12 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C13 :
   * Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C14 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R8 : River" } ], "edges":[ {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B10 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B9 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B10 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R8 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B11 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B9 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C12 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B9 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C13 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B10 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C14 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B10 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel6", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 3
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B16 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B17 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B18 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C19 : Cargo", "attributes":[ "boat=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C20 : Cargo", "attributes":[ "boat=null", "name=wolf"
   * ] }, { "type":"clazz", "id":"C21 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"R15 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B18 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B16 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B17 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R15 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B18 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B16 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C19 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B17 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C20 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B17 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C21 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B18 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel8",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 4
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B23 : Boat" }, {
   * "type":"clazz", "id":"B24 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B25 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"C26 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C27 :
   * Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C28 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R22 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B25 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B23 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B24 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R22 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B25 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R22 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B23 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R22 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C26 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B23 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C27 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B24 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C28 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B24 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel10", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 5
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B30 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B31 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B32 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"C33 : Cargo", "attributes":[ "boat=null", "name=wolf" ]
   * }, { "type":"clazz", "id":"C34 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C35 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"R29 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B31 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B30 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B31 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R29 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B32 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R29 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B30 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R29 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C33 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B31 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C34 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B31 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C35 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B32 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel12",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 6
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B37 : Boat" }, {
   * "type":"clazz", "id":"B38 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B39 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C40 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, { "type":"clazz", "id":"C41 :
   * Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C42 : Cargo",
   * "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"R36 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B38 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B37 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B38 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R36 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B39 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R36 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B37 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R36 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C40 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B37 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C41 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B38 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C42 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B39 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel14", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 7
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B44 : Boat" }, {
   * "type":"clazz", "id":"B45 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B46 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C47 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, { "type":"clazz", "id":"C48 :
   * Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C49 : Cargo",
   * "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"R43 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B45 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B44 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B45 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R43 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B46 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B44 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C47 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B44 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C48 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B45 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C49 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B46 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel16", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 8
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B51 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B52 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B53 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C54 : Cargo", "attributes":[ "boat=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C55 : Cargo", "attributes":[ "boat=null", "name=wolf"
   * ] }, { "type":"clazz", "id":"C56 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"R50 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B53 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B51 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B52 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R50 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B53 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B51 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C54 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B52 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C55 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B53 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C56 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B53 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel18",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 9
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B58 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B59 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B60 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C61 : Cargo", "attributes":[ "boat=null", "name=wolf" ]
   * }, { "type":"clazz", "id":"C62 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C63 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"R57 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B60 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B58 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B59 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R57 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B60 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R57 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B58 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R57 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C61 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B59 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C62 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B60 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C63 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B60 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel20",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 10
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B65 : Boat" }, {
   * "type":"clazz", "id":"B66 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B67 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"C68 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, { "type":"clazz", "id":"C69 :
   * Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C70 : Cargo",
   * "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"R64 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B67 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B65 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B66 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R64 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B67 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R64 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B65 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R64 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C68 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B65 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C69 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B66 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C70 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B67 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel22", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 11
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B72 : Boat" }, {
   * "type":"clazz", "id":"B73 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B74 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"C75 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C76 :
   * Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C77 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R71 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B74 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B72 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B73 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R71 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B74 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R71 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B72 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R71 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C75 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B72 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C76 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B73 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C77 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B74 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel24", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 12
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B79 : Boat" }, {
   * "type":"clazz", "id":"B80 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B81 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"C82 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C83 :
   * Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C84 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R78 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B81 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B79 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B80 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R78 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B81 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B79 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C82 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B79 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C83 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B80 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C84 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B81 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel26", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 13
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B86 : Boat" }, {
   * "type":"clazz", "id":"B87 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B88 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"C89 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, { "type":"clazz", "id":"C90 :
   * Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C91 : Cargo",
   * "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"R85 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B88 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B86 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B87 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R85 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B88 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R85 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B86 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R85 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C89 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B86 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C90 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B87 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C91 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B88 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel28", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 14
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B93 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B94 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B95 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"C96 : Cargo", "attributes":[ "boat=null", "name=goat" ]
   * }, { "type":"clazz", "id":"C97 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C98 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"R92 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B94 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B93 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B94 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R92 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B95 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R92 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B93 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R92 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C96 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B94 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C97 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B94 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C98 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B95 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel30",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 15
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B100 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B101 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B102 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"C103 : Cargo", "attributes":[ "boat=null", "name=wolf"
   * ] }, { "type":"clazz", "id":"C104 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C105 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"R99 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B101 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B100 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B101 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R99 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B102 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R99 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B100 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R99 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C103 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B101 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C104 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B101 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C105 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B102 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel32",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 16
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B107 : Boat" }, {
   * "type":"clazz", "id":"B108 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B109 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C110 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C111 :
   * Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C112 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R106 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B108 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B107 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B108 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R106 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B109 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R106 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B107 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R106 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C110 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B107 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C111 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B108 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C112 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B109 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel34", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 17
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B114 : Boat" }, {
   * "type":"clazz", "id":"B115 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B116 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C117 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, { "type":"clazz", "id":"C118
   * : Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C119 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R113 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B115 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B114 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B115 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R113 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B116 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B114 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C117 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B114 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C118 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B115 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C119 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B116 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel36", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 18
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B121 : Boat" }, {
   * "type":"clazz", "id":"B122 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B123 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C124 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, { "type":"clazz", "id":"C125 :
   * Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C126 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R120 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B122 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B121 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B122 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R120 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B123 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R120 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B121 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R120 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C124 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B121 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C125 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B122 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C126 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B123 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel38", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 19
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B128 : Boat" }, {
   * "type":"clazz", "id":"B129 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B130 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C131 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C132 :
   * Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C133 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R127 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B129 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B128 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B129 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R127 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B130 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R127 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B128 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R127 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C131 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B128 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C132 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B129 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C133 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B130 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel40", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 20
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B135 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B136 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B137 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C138 : Cargo", "attributes":[ "boat=null", "name=goat"
   * ] }, { "type":"clazz", "id":"C139 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C140 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"R134 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B137 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B135 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B136 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R134 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B137 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R134 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B135 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R134 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C138 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B136 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C139 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B137 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C140 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B137 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel42",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 21
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B142 : Boat" }, {
   * "type":"clazz", "id":"B143 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B144 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"C145 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, { "type":"clazz", "id":"C146 :
   * Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C147 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R141 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B144 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B142 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B143 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R141 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B144 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R141 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B142 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R141 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C145 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B142 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C146 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B143 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C147 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B144 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel44", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 22
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B149 : Boat" }, {
   * "type":"clazz", "id":"B150 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B151 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"C152 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, { "type":"clazz", "id":"C153
   * : Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C154 : Cargo",
   * "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"R148 : River" } ], "edges":[
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B151 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B149 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B150 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R148 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B151 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B149 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C152 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B149 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C153 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B150 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C154 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B151 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel46", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 23
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B156 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B157 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B158 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"C159 : Cargo", "attributes":[ "boat=null", "name=goat"
   * ] }, { "type":"clazz", "id":"C160 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C161 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"R155 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B157 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B156 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B157 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R155 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B158 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B156 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C159 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B157 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C160 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B158 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C161 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B158 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel48",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 24
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B163 : Boat" }, {
   * "type":"clazz", "id":"B164 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B165 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"C166 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C167 :
   * Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C168 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R162 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B164 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B163 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B164 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R162 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B165 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R162 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B163 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R162 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C166 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B163 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C167 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B165 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C168 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B165 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel50", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 25
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B170 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B171 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B172 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"C173 : Cargo", "attributes":[ "boat=null", "name=wolf"
   * ] }, { "type":"clazz", "id":"C174 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C175 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"R169 : River" } ], "edges":[ { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B172 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B170 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B171 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R169 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B172 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R169 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B170 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R169 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C173 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B172 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C174 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B172 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C175 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B172 : Bank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel52",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 26
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B177 : Boat" }, {
   * "type":"clazz", "id":"B178 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B179 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"C180 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, { "type":"clazz", "id":"C181 :
   * Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C182 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"R176 : River" } ],
   * "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B179 : Bank"
   * }, "target":{ "cardinality":"one", "property":"boat", "id":"B177 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B178 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R176 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B179 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R176 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B177 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R176 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C180 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B177 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C181 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B179 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C182 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B179 : Bank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemManuel54", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Check: Number of Reachable States expected: 27 actual 27
   * </p>
   * <p>
   * large reachbility graph with embedded states:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"B10 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B100 : Boat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"B101 : Bank", "attributes":[ "age=0", "name=left" ] },
   * { "type":"clazz", "id":"B102 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, {
   * "type":"clazz", "id":"B107 : Boat" }, { "type":"clazz", "id":"B108 : Bank", "attributes":[
   * "age=0", "name=left" ] }, { "type":"clazz", "id":"B109 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=right" ] }, { "type":"clazz", "id":"B11 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=right" ] }, { "type":"clazz", "id":"B114 : Boat" }, { "type":"clazz",
   * "id":"B115 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B116 :
   * Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz", "id":"B121 :
   * Boat" }, { "type":"clazz", "id":"B122 : Bank", "attributes":[ "age=0", "name=left" ] }, {
   * "type":"clazz", "id":"B123 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, {
   * "type":"clazz", "id":"B128 : Boat" }, { "type":"clazz", "id":"B129 : Bank", "attributes":[
   * "age=0", "name=left" ] }, { "type":"clazz", "id":"B130 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=right" ] }, { "type":"clazz", "id":"B135 : Boat", "attributes":[ "cargo=null"
   * ] }, { "type":"clazz", "id":"B136 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] },
   * { "type":"clazz", "id":"B137 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B142 : Boat" }, { "type":"clazz", "id":"B143 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=left" ] }, { "type":"clazz", "id":"B144 : Bank", "attributes":[ "age=0", "name=right" ] },
   * { "type":"clazz", "id":"B149 : Boat" }, { "type":"clazz", "id":"B150 : Bank", "attributes":[
   * "age=0", "boat=null", "name=left" ] }, { "type":"clazz", "id":"B151 : Bank", "attributes":[
   * "age=0", "name=right" ] }, { "type":"clazz", "id":"B156 : Boat", "attributes":[ "cargo=null" ] },
   * { "type":"clazz", "id":"B157 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B158 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"B16 : Boat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B163 : Boat" }, {
   * "type":"clazz", "id":"B164 : Bank", "attributes":[ "age=0", "name=left" ] }, { "type":"clazz",
   * "id":"B165 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, { "type":"clazz",
   * "id":"B17 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"B170 : Boat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B171 : Bank",
   * "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz", "id":"B172 : Bank",
   * "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B177 : Boat" }, {
   * "type":"clazz", "id":"B178 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B179 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B18 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B2 : Boat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B23 : Boat" }, { "type":"clazz",
   * "id":"B24 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz",
   * "id":"B25 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B3 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B30 : Boat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"B31 : Bank", "attributes":[ "age=0", "name=left" ] }, {
   * "type":"clazz", "id":"B32 : Bank", "attributes":[ "age=0", "boat=null", "name=right" ] }, {
   * "type":"clazz", "id":"B37 : Boat" }, { "type":"clazz", "id":"B38 : Bank", "attributes":[ "age=0",
   * "name=left" ] }, { "type":"clazz", "id":"B39 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"B4 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=right" ] }, { "type":"clazz", "id":"B44 : Boat" }, { "type":"clazz", "id":"B45 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B46 : Bank", "attributes":[
   * "age=0", "boat=null", "name=right" ] }, { "type":"clazz", "id":"B51 : Boat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"B52 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=left" ] }, { "type":"clazz", "id":"B53 : Bank", "attributes":[ "age=0", "name=right" ] }, {
   * "type":"clazz", "id":"B58 : Boat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B59 :
   * Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz", "id":"B60 : Bank",
   * "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B65 : Boat" }, {
   * "type":"clazz", "id":"B66 : Bank", "attributes":[ "age=0", "boat=null", "name=left" ] }, {
   * "type":"clazz", "id":"B67 : Bank", "attributes":[ "age=0", "name=right" ] }, { "type":"clazz",
   * "id":"B72 : Boat" }, { "type":"clazz", "id":"B73 : Bank", "attributes":[ "age=0", "boat=null",
   * "name=left" ] }, { "type":"clazz", "id":"B74 : Bank", "attributes":[ "age=0", "name=right" ] }, {
   * "type":"clazz", "id":"B79 : Boat" }, { "type":"clazz", "id":"B80 : Bank", "attributes":[ "age=0",
   * "boat=null", "name=left" ] }, { "type":"clazz", "id":"B81 : Bank", "attributes":[ "age=0",
   * "name=right" ] }, { "type":"clazz", "id":"B86 : Boat" }, { "type":"clazz", "id":"B87 : Bank",
   * "attributes":[ "age=0", "boat=null", "name=left" ] }, { "type":"clazz", "id":"B88 : Bank",
   * "attributes":[ "age=0", "name=right" ] }, { "type":"clazz", "id":"B9 : Boat" }, { "type":"clazz",
   * "id":"B93 : Boat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"B94 : Bank",
   * "attributes":[ "age=0", "name=left" ] }, { "type":"clazz", "id":"B95 : Bank", "attributes":[
   * "age=0", "boat=null", "name=right" ] }, { "type":"clazz", "id":"C103 : Cargo", "attributes":[
   * "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C104 : Cargo", "attributes":[ "boat=null",
   * "name=goat" ] }, { "type":"clazz", "id":"C105 : Cargo", "attributes":[ "boat=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C110 : Cargo", "attributes":[ "bank=null",
   * "name=goat" ] }, { "type":"clazz", "id":"C111 : Cargo", "attributes":[ "boat=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C112 : Cargo", "attributes":[ "boat=null",
   * "name=wolf" ] }, { "type":"clazz", "id":"C117 : Cargo", "attributes":[ "bank=null",
   * "name=cabbage" ] }, { "type":"clazz", "id":"C118 : Cargo", "attributes":[ "boat=null",
   * "name=goat" ] }, { "type":"clazz", "id":"C119 : Cargo", "attributes":[ "boat=null", "name=wolf" ]
   * }, { "type":"clazz", "id":"C12 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C124 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C125 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C126 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C13 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C131 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C132 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C133 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C138 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C139 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C14 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C140 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C145 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C146 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C147 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C152 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C153 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C154 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C159 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C160 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C161 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C166 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C167 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C168 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C173 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C174 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C175 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C180 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C181 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C182 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C19 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C20 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C21 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C26 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C27 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C28 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C33 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C34 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C35 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C40 : Cargo", "attributes":[ "bank=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C41 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C42 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C47 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C48 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C49 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C5 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C54 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C55 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C56 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C6 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz",
   * "id":"C61 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, { "type":"clazz", "id":"C62 :
   * Cargo", "attributes":[ "boat=null", "name=goat" ] }, { "type":"clazz", "id":"C63 : Cargo",
   * "attributes":[ "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C68 : Cargo",
   * "attributes":[ "bank=null", "name=wolf" ] }, { "type":"clazz", "id":"C69 : Cargo", "attributes":[
   * "boat=null", "name=cabbage" ] }, { "type":"clazz", "id":"C7 : Cargo", "attributes":[ "boat=null",
   * "name=wolf" ] }, { "type":"clazz", "id":"C70 : Cargo", "attributes":[ "boat=null", "name=goat" ]
   * }, { "type":"clazz", "id":"C75 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C76 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C77 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C82 : Cargo", "attributes":[ "bank=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C83 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C84 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C89 : Cargo", "attributes":[ "bank=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C90 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"C91 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C96 : Cargo", "attributes":[ "boat=null", "name=goat" ] }, {
   * "type":"clazz", "id":"C97 : Cargo", "attributes":[ "boat=null", "name=cabbage" ] }, {
   * "type":"clazz", "id":"C98 : Cargo", "attributes":[ "boat=null", "name=wolf" ] }, {
   * "type":"clazz", "id":"R1 : River" }, { "type":"clazz", "id":"R106 : River" }, { "type":"clazz",
   * "id":"R113 : River" }, { "type":"clazz", "id":"R120 : River" }, { "type":"clazz", "id":"R127 :
   * River" }, { "type":"clazz", "id":"R134 : River" }, { "type":"clazz", "id":"R141 : River" }, {
   * "type":"clazz", "id":"R148 : River" }, { "type":"clazz", "id":"R15 : River" }, { "type":"clazz",
   * "id":"R155 : River" }, { "type":"clazz", "id":"R162 : River" }, { "type":"clazz", "id":"R169 :
   * River" }, { "type":"clazz", "id":"R176 : River" }, { "type":"clazz", "id":"R183 :
   * ReachabilityGraph" }, { "type":"clazz", "id":"R184 : ReachableState", "attributes":[ "descr=1
   * 0.0\u000aleft cabbage wolf goat boat \\___/\u000aright ", "failureState=false",
   * "metricValue=0.0", "number=1" ] }, { "type":"clazz", "id":"R185 : ReachableState", "attributes":[
   * "descr=2 0.0\u000aleft cabbage wolf boat \\goat/\u000aright ", "failureState=false",
   * "metricValue=0.0", "number=2" ] }, { "type":"clazz", "id":"R186 : ReachableState", "attributes":[
   * "descr=3 0.0\u000aleft cabbage wolf\u000aright goat boat \\___/", "failureState=false",
   * "metricValue=0.0", "number=3" ] }, { "type":"clazz", "id":"R187 : ReachableState", "attributes":[
   * "descr=4 0.0\u000aleft cabbage wolf\u000aright boat \\goat/", "failureState=false",
   * "metricValue=0.0", "number=4" ] }, { "type":"clazz", "id":"R188 : ReachableState", "attributes":[
   * "descr=5 0.0\u000aleft wolf cabbage boat \\___/\u000aright goat", "failureState=false",
   * "metricValue=0.0", "number=5" ] }, { "type":"clazz", "id":"R189 : ReachableState", "attributes":[
   * "descr=6 0.0\u000aleft cabbage boat \\wolf/\u000aright goat", "failureState=false",
   * "metricValue=0.0", "number=6" ] }, { "type":"clazz", "id":"R190 : ReachableState", "attributes":[
   * "descr=7 0.0\u000aleft wolf boat \\cabbage/\u000aright goat", "failureState=false",
   * "metricValue=0.0", "number=7" ] }, { "type":"clazz", "id":"R191 : ReachableState", "attributes":[
   * "descr=8 0.0\u000aleft cabbage\u000aright wolf goat boat \\___/", "failureState=false",
   * "metricValue=0.0", "number=8" ] }, { "type":"clazz", "id":"R192 : ReachableState", "attributes":[
   * "descr=9 0.0\u000aleft wolf\u000aright goat cabbage boat \\___/", "failureState=false",
   * "metricValue=0.0", "number=9" ] }, { "type":"clazz", "id":"R193 : ReachableState", "attributes":[
   * "descr=10 0.0\u000aleft cabbage\u000aright goat boat \\wolf/", "failureState=false",
   * "metricValue=0.0", "number=10" ] }, { "type":"clazz", "id":"R194 : ReachableState",
   * "attributes":[ "descr=11 0.0\u000aleft cabbage\u000aright wolf boat \\goat/",
   * "failureState=false", "metricValue=0.0", "number=11" ] }, { "type":"clazz", "id":"R195 :
   * ReachableState", "attributes":[ "descr=12 0.0\u000aleft wolf\u000aright cabbage boat \\goat/",
   * "failureState=false", "metricValue=0.0", "number=12" ] }, { "type":"clazz", "id":"R196 :
   * ReachableState", "attributes":[ "descr=13 0.0\u000aleft wolf\u000aright goat boat \\cabbage/",
   * "failureState=false", "metricValue=0.0", "number=13" ] }, { "type":"clazz", "id":"R197 :
   * ReachableState", "attributes":[ "descr=14 0.0\u000aleft goat cabbage boat \\___/\u000aright
   * wolf", "failureState=false", "metricValue=0.0", "number=14" ] }, { "type":"clazz", "id":"R198 :
   * ReachableState", "attributes":[ "descr=15 0.0\u000aleft wolf goat boat \\___/\u000aright
   * cabbage", "failureState=false", "metricValue=0.0", "number=15" ] }, { "type":"clazz", "id":"R199
   * : ReachableState", "attributes":[ "descr=16 0.0\u000aleft cabbage boat \\goat/\u000aright wolf",
   * "failureState=false", "metricValue=0.0", "number=16" ] }, { "type":"clazz", "id":"R200 :
   * ReachableState", "attributes":[ "descr=17 0.0\u000aleft goat boat \\cabbage/\u000aright wolf",
   * "failureState=false", "metricValue=0.0", "number=17" ] }, { "type":"clazz", "id":"R201 :
   * ReachableState", "attributes":[ "descr=18 0.0\u000aleft goat boat \\wolf/\u000aright cabbage",
   * "failureState=false", "metricValue=0.0", "number=18" ] }, { "type":"clazz", "id":"R202 :
   * ReachableState", "attributes":[ "descr=19 0.0\u000aleft wolf boat \\goat/\u000aright cabbage",
   * "failureState=false", "metricValue=0.0", "number=19" ] }, { "type":"clazz", "id":"R203 :
   * ReachableState", "attributes":[ "descr=20 0.0\u000aleft goat\u000aright wolf cabbage boat
   * \\___/", "failureState=false", "metricValue=0.0", "number=20" ] }, { "type":"clazz", "id":"R204 :
   * ReachableState", "attributes":[ "descr=21 0.0\u000aleft goat\u000aright cabbage boat \\wolf/",
   * "failureState=false", "metricValue=0.0", "number=21" ] }, { "type":"clazz", "id":"R205 :
   * ReachableState", "attributes":[ "descr=22 0.0\u000aleft goat\u000aright wolf boat \\cabbage/",
   * "failureState=false", "metricValue=0.0", "number=22" ] }, { "type":"clazz", "id":"R206 :
   * ReachableState", "attributes":[ "descr=23 0.0\u000aleft goat boat \\___/\u000aright wolf
   * cabbage", "failureState=false", "metricValue=0.0", "number=23" ] }, { "type":"clazz", "id":"R207
   * : ReachableState", "attributes":[ "descr=24 0.0\u000aleft boat \\goat/\u000aright wolf cabbage",
   * "failureState=false", "metricValue=0.0", "number=24" ] }, { "type":"clazz", "id":"R208 :
   * ReachableState", "attributes":[ "descr=25 0.0\u000aleft \u000aright wolf cabbage goat boat
   * \\___/", "failureState=false", "metricValue=0.0", "number=25" ] }, { "type":"clazz", "id":"R209 :
   * ReachableState", "attributes":[ "descr=26 0.0\u000aleft \u000aright wolf cabbage boat \\goat/",
   * "failureState=false", "metricValue=0.0", "number=26" ] }, { "type":"clazz", "id":"R210 :
   * RuleApplication", "attributes":[ "description=load goat" ] }, { "type":"clazz", "id":"R211 :
   * RuleApplication", "attributes":[ "description=move boat" ] }, { "type":"clazz", "id":"R212 :
   * RuleApplication", "attributes":[ "description=move boat" ] }, { "type":"clazz", "id":"R213 :
   * RuleApplication", "attributes":[ "description=load goat" ] }, { "type":"clazz", "id":"R214 :
   * RuleApplication", "attributes":[ "description=move boat" ] }, { "type":"clazz", "id":"R215 :
   * RuleApplication", "attributes":[ "description=move boat" ] }, { "type":"clazz", "id":"R216 :
   * RuleApplication", "attributes":[ "description=load wolf" ] }, { "type":"clazz", "id":"R217 :
   * RuleApplication", "attributes":[ "description=load cabbage" ] }, { "type":"clazz", "id":"R218 :
   * RuleApplication", "attributes":[ "description=move boat" ] }, { "type":"clazz", "id":"R219 :
   * RuleApplication", "attributes":[ "description=move boat" ] }, { "type":"clazz", "id":"R22 :
   * River" }, { "type":"clazz", "id":"R220 : RuleApplication", "attributes":[ "description=move boat"
   * ] }, { "type":"clazz", "id":"R221 : RuleApplication", "attributes":[ "description=move boat" ] },
   * { "type":"clazz", "id":"R222 : RuleApplication", "attributes":[ "description=load wolf" ] }, {
   * "type":"clazz", "id":"R223 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R224 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R225 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R226 : RuleApplication", "attributes":[ "description=load cabbage" ] }, {
   * "type":"clazz", "id":"R227 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R228 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R229 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R230 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R231 : RuleApplication", "attributes":[ "description=load cabbage" ] }, {
   * "type":"clazz", "id":"R232 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R233 : RuleApplication", "attributes":[ "description=load wolf" ] }, {
   * "type":"clazz", "id":"R234 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R235 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R236 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R237 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R238 : RuleApplication", "attributes":[ "description=load wolf" ] }, {
   * "type":"clazz", "id":"R239 : RuleApplication", "attributes":[ "description=load cabbage" ] }, {
   * "type":"clazz", "id":"R240 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R241 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R242 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R243 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R244 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R245 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R29 : River" }, { "type":"clazz", "id":"R36 : River" }, { "type":"clazz",
   * "id":"R43 : River" }, { "type":"clazz", "id":"R50 : River" }, { "type":"clazz", "id":"R57 :
   * River" }, { "type":"clazz", "id":"R64 : River" }, { "type":"clazz", "id":"R71 : River" }, {
   * "type":"clazz", "id":"R78 : River" }, { "type":"clazz", "id":"R8 : River" }, { "type":"clazz",
   * "id":"R85 : River" }, { "type":"clazz", "id":"R92 : River" }, { "type":"clazz", "id":"R99 :
   * River" } ], "edges":[ { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B3 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B2 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B10 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B9 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B18 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B16 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B25 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B23 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B31 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B30 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B38 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B37 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B45 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B44 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B53 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B51 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B60 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B58 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B67 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B65 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B74 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B72 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B81 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B79 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B88 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B86 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B94 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B93 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B101 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B100 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B108 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B107 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B115 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B114 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B122 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B121 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B129 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B128 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B137 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B135 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B144 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B142 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"bank", "id":"B151 : Bank" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B149 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"bank", "id":"B157 : Bank" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B156 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"bank", "id":"B164 : Bank" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B163 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"bank", "id":"B172 : Bank" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B170 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"bank",
   * "id":"B179 : Bank" }, "target":{ "cardinality":"one", "property":"boat", "id":"B177 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B3 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B4 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R1 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B10 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B11 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B17 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B18 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B24 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R22 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B25 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R22 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B31 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R29 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B32 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R29 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B38 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R36 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B39 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R36 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B45 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B46 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B52 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B53 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B59 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R57 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B60 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R57 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B66 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R64 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B67 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R64 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B73 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R71 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B74 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R71 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B80 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B81 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B87 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R85 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B88 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R85 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B94 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R92 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B95 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R92 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B101 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R99 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B102 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R99 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B108 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R106 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B109 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R106 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B115 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B116 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B122 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R120 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B123 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R120 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B129 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R127 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B130 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R127 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B136 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R134 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B137 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R134 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B143 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R141 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B144 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R141 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B150 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B151 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B157 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B158 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B164 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R162 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"banks", "id":"B165 : Bank" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R162 : River" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"banks", "id":"B171 : Bank" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R169 : River" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks",
   * "id":"B172 : Bank" }, "target":{ "cardinality":"one", "property":"river", "id":"R169 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"many", "property":"banks", "id":"B178 : Bank" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R176 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"banks", "id":"B179 : Bank" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R176 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B2 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R1 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B9 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R8 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B16 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R15 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B23 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R22 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B30 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R29 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B37 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R36 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B44 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R43 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B51 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R50 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B58 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R57 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B65 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R64 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B72 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R71 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B79 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R78 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B86 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R85 : River" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B93 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R92 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B100 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R99 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B107 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R106 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B114 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R113 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B121 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R120 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B128 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R127 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B135 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R134 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B142 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R141 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"boat", "id":"B149 : Boat" }, "target":{ "cardinality":"one", "property":"river",
   * "id":"R148 : River" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat",
   * "id":"B156 : Boat" }, "target":{ "cardinality":"one", "property":"river", "id":"R155 : River" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"boat", "id":"B163 : Boat" },
   * "target":{ "cardinality":"one", "property":"river", "id":"R162 : River" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"boat", "id":"B170 : Boat" }, "target":{
   * "cardinality":"one", "property":"river", "id":"R169 : River" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"boat", "id":"B177 : Boat" }, "target":{ "cardinality":"one",
   * "property":"river", "id":"R176 : River" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"C12 : Cargo" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B9 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C26 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B23 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"cargo", "id":"C40 : Cargo" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B37 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"C47 : Cargo" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B44 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"cargo", "id":"C68 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B65 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"C75 : Cargo" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B72 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C82 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B79 : Boat" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"cargo", "id":"C89 : Cargo" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B86 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"C110 : Cargo" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B107 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"cargo", "id":"C117 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B114 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"C124 : Cargo" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B121 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"C131 : Cargo" }, "target":{ "cardinality":"one", "property":"boat", "id":"B128 : Boat" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"cargo", "id":"C145 : Cargo" },
   * "target":{ "cardinality":"one", "property":"boat", "id":"B142 : Boat" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"C152 : Cargo" }, "target":{
   * "cardinality":"one", "property":"boat", "id":"B149 : Boat" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"cargo", "id":"C166 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"boat", "id":"B163 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"C180 : Cargo" }, "target":{ "cardinality":"one", "property":"boat",
   * "id":"B177 : Boat" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C5 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B3 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C7 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B3 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C6 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B3 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C13 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B10 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C14 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B10 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C19 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B17 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C20 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B17 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C21 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B18 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C27 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B24 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C28 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B24 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C33 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B31 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C34 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B31 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C35 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B32 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C41 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B38 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C42 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B39 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C48 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B45 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C49 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B46 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C54 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B52 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C55 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B53 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C56 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B53 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C61 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B59 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C62 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B60 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C63 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B60 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C69 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B66 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C70 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B67 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C76 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B73 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C77 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B74 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C83 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B80 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C84 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B81 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C90 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B87 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C91 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B88 : Bank" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C96 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B94 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C97 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B94 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C98 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B95 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C103 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B101 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C104 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B101 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C105 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B102 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C111 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B108 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C112 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B109 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C118 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B115 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C119 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B116 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C125 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B122 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C126 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B123 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C132 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B129 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C133 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B130 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C138 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B136 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C139 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B137 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C140 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B137 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C146 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B143 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C147 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B144 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C153 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B150 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C154 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B151 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C159 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B157 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C160 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B158 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C161 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B158 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C167 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B165 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C168 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B165 : Bank" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"C173 : Cargo" }, "target":{
   * "cardinality":"one", "property":"bank", "id":"B172 : Bank" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"cargos", "id":"C174 : Cargo" }, "target":{ "cardinality":"one",
   * "property":"bank", "id":"B172 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"C175 : Cargo" }, "target":{ "cardinality":"one", "property":"bank",
   * "id":"B172 : Bank" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"C181 : Cargo" }, "target":{ "cardinality":"one", "property":"bank", "id":"B179 : Bank" } },
   * { "type":"assoc", "source":{ "cardinality":"many", "property":"cargos", "id":"C182 : Cargo" },
   * "target":{ "cardinality":"one", "property":"bank", "id":"B179 : Bank" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"graphRoot", "id":"R1 : River" }, "target":{
   * "cardinality":"one", "property":"reachablestate", "id":"R184 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R8 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R185 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R15 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R186 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R22 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R187 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R29 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R188 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R36 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R189 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R43 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R190 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R50 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R191 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R57 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R192 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R64 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R193 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R71 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R194 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R78 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R195 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R85 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R196 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R92 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R197 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R99 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R198 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R106 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R199 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R113 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R200 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R120 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R201 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R127 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R202 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R134 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R203 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R141 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R204 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R148 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R205 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R155 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R206 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R162 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R207 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R169 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R208 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"R176 : River" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R209 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R211 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R184 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R210 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R185 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R212 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R186 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R215 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R186 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R213 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R187 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R214 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R188 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R218 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R188 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R219 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R188 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R216 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R189 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R217 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R190 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R220 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R191 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R224 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R191 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R221 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R192 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R227 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R192 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R222 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R193 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R223 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R194 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R225 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R195 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R226 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R196 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R228 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R197 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R232 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R197 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R229 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R198 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R235 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R198 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R230 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R199 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R231 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R200 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R233 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R201 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R234 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R202 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R236 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R203 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R237 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R203 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R241 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R203 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R238 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R204 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R239 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R205 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R240 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R206 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R243 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R206 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R242 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R207 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R244 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R208 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R245 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R209 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R210 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R184 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R212 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R185 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R213 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R186 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R214 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R186 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R211 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R187 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R216 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R188 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R217 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R188 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R215 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R188 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R220 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R189 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R221 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R190 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R222 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R191 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R223 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R191 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R225 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R192 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R226 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R192 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R218 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R193 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R228 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R194 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R229 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R195 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R219 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R196 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R230 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R197 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R231 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R197 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R233 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R198 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R234 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R198 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R224 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R199 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R236 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R200 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R237 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R201 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R227 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R202 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R238 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R203 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R239 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R203 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R240 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R203 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R235 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R204 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R232 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R205 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R242 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R206 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R241 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R206 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R244 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R207 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R245 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R208 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R243 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R209 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R184 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R185 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R186 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R187 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R188 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R189 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R190 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R191 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R192 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R193 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R194 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R195 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R196 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R197 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R198 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R199 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R200 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R201 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R202 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R203 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R204 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R205 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R206 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R207 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R208 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R209 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R183 :
   * ReachabilityGraph" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemManuel57",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Check: found a solution true
   * </p>
   * 
   * @see <a href=
   *      '../../../../../../../../doc/FerrymansProblemManuel.html'>FerrymansProblemManuel.html</a>
   */
  @Test
  public void FerrymansProblemManuel() {
    Storyboard storyboard = new Storyboard();

    // ================================================
    storyboard.add("initial situation:");

    River river = new River();

    Boat boat = river.createBoat();

    Bank left = river.createBanks().withName("left").withBoat(boat);

    left.createCargos().withName("cabbage");
    left.createCargos().withName("goat");
    left.createCargos().withName("wolf");

    river.createBanks().withName("right");

    storyboard.addObjectDiagram(river);


    ReachableState rs1 = new ReachableState().withGraphRoot(river);

    RiverCreator cc = new RiverCreator();

    IdMap map = cc.createIdMap("s");
    map.with(ReachabilityGraphCreator.createIdMap("rg"));

    ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
        .withMasterMap(map)
        .withStart(rs1)
        .withLazyCloning();

    // ================================================
    // load boat operations

    reachabilityGraph.withTrafo("load wolf", g -> load(g, "wolf"));
    reachabilityGraph.withTrafo("load goat", g -> load(g, "goat"));
    reachabilityGraph.withTrafo("load cabbage", g -> load(g, "cabbage"));

    // ================================================
    // move boat rule
    reachabilityGraph.withTrafo("move boat", g -> move(g));


    // ================================================
    long size = reachabilityGraph.explore();

    for (ReachableState s : reachabilityGraph.getStates()) {
      storyboard.add("Reachable State " + s.getNumber());
      River r = (River) s.getGraphRoot();
      storyboard.addObjectDiagram(r);
    }


    storyboard.assertEquals("Number of Reachable States expected: ", 27L, size);

    storyboard.add("large reachbility graph with embedded states: ");
    storyboard.addObjectDiagram(reachabilityGraph);

    RiverSet rivers = new RiverSet().with(reachabilityGraph.getStates().getGraphRoot());
    SimpleSet<Bank> banks = rivers.getBanks()
        .hasName("right")
        .filter(bank -> ((Bank) bank).getCargos().size() == 3);

    storyboard.assertTrue("found a solution ", !banks.isEmpty());

    storyboard.dumpHTML();
  }


  /**
   * 
   * <p>
   * Storyboard <a href=
   * './src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'
   * type='text/x-java'>FerrymansProblemLazyBackup</a>
   * </p>
   * <p>
   * initial situation:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U1 : URiver" }, {
   * "type":"clazz", "id":"U2 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U3 :
   * UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U4 : UBoat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, {
   * "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 :
   * UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U2 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U4 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U2 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U1 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U3 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U4 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U2 : UBank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup2",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 4 --move boat-&gt;
   * Reachable State 1
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U1 : URiver" }, {
   * "type":"clazz", "id":"U2 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U3 :
   * UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U4 : UBoat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, {
   * "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 :
   * UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U2 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U4 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U2 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U1 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U3 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U4 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U2 : UBank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup4",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 1 --load goat-&gt;
   * Reachable State 2
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U10 : UBoat" }, {
   * "type":"clazz", "id":"U3 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U5 :
   * UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U8 : URiver" }, { "type":"clazz", "id":"U9 : UBank", "attributes":[
   * "name=left" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U9 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U10 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U3 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U10 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U10 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup6", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 2 --move boat-&gt;
   * 5 --move boat-&gt;
   * Reachable State 3
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U11 : URiver" },
   * { "type":"clazz", "id":"U12 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz",
   * "id":"U13 : UBoat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U9 : UBank", "attributes":[ "name=left" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U12 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U13 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U13 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U11 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U12 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup8", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 3 --load goat-&gt;
   * Reachable State 4
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U14 : URiver" },
   * { "type":"clazz", "id":"U15 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz",
   * "id":"U16 : UBoat" }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, {
   * "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 :
   * UCargo", "attributes":[ "name=wolf" ] }, { "type":"clazz", "id":"U9 : UBank", "attributes":[
   * "name=left" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U15 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U16 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U9 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U14 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U15 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U14 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U16 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U14 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U16 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup10", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 3 --move boat-&gt;
   * 10 --move boat-&gt;
   * 13 --move boat-&gt;
   * Reachable State 5
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U12 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U17 : URiver" }, { "type":"clazz",
   * "id":"U18 : UBoat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U9 : UBank", "attributes":[ "name=left" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U18 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U17 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U17 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U18 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U17 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U12 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup12", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 5 --load wolf-&gt;
   * Reachable State 6
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U12 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U19 : URiver" }, { "type":"clazz",
   * "id":"U20 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U21 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U20 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U21 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U12 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U19 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U20 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U19 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U21 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U19 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U21 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U12 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U20 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup14", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 5 --load cabbage-&gt;
   * Reachable State 7
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U12 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U22 : URiver" }, { "type":"clazz",
   * "id":"U23 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U24 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U23 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U24 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U12 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U24 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U24 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U12 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup16", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 6 --move boat-&gt;
   * 16 --move boat-&gt;
   * Reachable State 8
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U20 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U25 : URiver" }, { "type":"clazz",
   * "id":"U26 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U27 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U26 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U27 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U20 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U26 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U27 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U25 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U20 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U26 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U26 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup18", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 7 --move boat-&gt;
   * 19 --move boat-&gt;
   * Reachable State 9
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U23 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U28 : URiver" }, { "type":"clazz",
   * "id":"U29 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U30 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U29 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U30 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U23 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U28 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U29 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U28 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U30 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U28 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U29 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U29 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup20", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 8 --load wolf-&gt;
   * Reachable State 10
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U20 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U31 : URiver" }, { "type":"clazz",
   * "id":"U32 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U33 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U32 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U33 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U20 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U32 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U33 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U33 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U20 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U32 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup22", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 8 --load goat-&gt;
   * Reachable State 11
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U20 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U34 : URiver" }, { "type":"clazz",
   * "id":"U35 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U36 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U35 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U36 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U20 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U34 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U35 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U34 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U36 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U34 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U36 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U20 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U35 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup24", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 9 --load goat-&gt;
   * Reachable State 12
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U23 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U37 : URiver" }, { "type":"clazz",
   * "id":"U38 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U39 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U39 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U39 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U39 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U38 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup26", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 9 --load cabbage-&gt;
   * Reachable State 13
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U23 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U40 : URiver" }, { "type":"clazz",
   * "id":"U41 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U42 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U41 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U42 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U41 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U42 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U42 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U41 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup28", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 11 --move boat-&gt;
   * 22 --move boat-&gt;
   * Reachable State 14
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U35 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U43 : URiver" }, { "type":"clazz",
   * "id":"U44 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U45 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U44 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U45 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U35 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U43 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U44 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U43 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U45 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U43 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U35 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U44 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U44 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup30", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 12 --move boat-&gt;
   * 21 --move boat-&gt;
   * Reachable State 15
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U38 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U46 : URiver" }, { "type":"clazz",
   * "id":"U47 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U48 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U47 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U48 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U46 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U47 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U46 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U48 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U46 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U38 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U47 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U47 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup32", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 14 --load goat-&gt;
   * Reachable State 16
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U35 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U49 : URiver" }, { "type":"clazz",
   * "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U50 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U51 : UBoat" }, { "type":"clazz",
   * "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U50 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U51 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U35 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U49 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U50 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U49 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U51 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U49 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U51 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U35 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U50 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup34", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 14 --load cabbage-&gt;
   * Reachable State 17
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U35 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U52 : URiver" }, { "type":"clazz", "id":"U53 :
   * UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U54 : UBoat" }, { "type":"clazz",
   * "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U53 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U54 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U35 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U52 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U53 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U54 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U54 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U35 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U53 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup36", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 15 --load wolf-&gt;
   * Reachable State 18
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U38 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U55 : URiver" }, { "type":"clazz", "id":"U56 :
   * UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U57 : UBoat" }, { "type":"clazz",
   * "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U56 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U57 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U55 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U56 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U55 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U57 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U55 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U57 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U38 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U56 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup38", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 15 --load goat-&gt;
   * Reachable State 19
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U38 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U58 : URiver" }, { "type":"clazz", "id":"U59 :
   * UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U60 : UBoat" }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U59 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U60 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U58 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U59 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U58 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U60 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U58 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U60 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U38 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U59 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup40", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 17 --move boat-&gt;
   * 18 --move boat-&gt;
   * 23 --move boat-&gt;
   * Reachable State 20
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U53 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U61 : URiver" }, { "type":"clazz", "id":"U62 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U63 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U62 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U63 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U53 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U61 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U61 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U63 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U61 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U53 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U62 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup42", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 20 --load wolf-&gt;
   * Reachable State 21
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U53 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U64 : URiver" }, { "type":"clazz", "id":"U65 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U66 : UBoat" }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U65 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U66 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U53 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U64 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U65 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U64 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U66 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U64 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U66 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U53 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U65 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup44", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 20 --load cabbage-&gt;
   * Reachable State 22
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U53 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U67 : URiver" }, { "type":"clazz", "id":"U68 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U69 : UBoat" }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U68 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U69 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U53 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U67 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U68 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U69 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U69 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U53 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U68 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup46", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 20 --move boat-&gt;
   * 26 --move boat-&gt;
   * Reachable State 23
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U53 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U62 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U7 :
   * UCargo", "attributes":[ "name=wolf" ] }, { "type":"clazz", "id":"U70 : URiver" }, {
   * "type":"clazz", "id":"U71 : UBoat", "attributes":[ "cargo=null" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U53 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U71 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U53 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U71 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U70 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U53 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U62 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup48", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 23 --load goat-&gt;
   * Reachable State 24
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U62 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, { "type":"clazz", "id":"U72 :
   * URiver" }, { "type":"clazz", "id":"U73 : UBank", "attributes":[ "name=left" ] }, {
   * "type":"clazz", "id":"U74 : UBoat" } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U73 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U74 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U72 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U73 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U72 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U74 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U72 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U74 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U62 : UBank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup50",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 24 --move boat-&gt;
   * Reachable State 25
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U73 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U75 :
   * URiver" }, { "type":"clazz", "id":"U76 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U77 : UBoat", "attributes":[ "cargo=null" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U76 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U77 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U73 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U75 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U76 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U75 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U77 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U75 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U76 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup52", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * 
   * <pre>
   * 25 --load goat-&gt;
   * Reachable State 26
   * </pre>
   * 
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U73 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U78 :
   * URiver" }, { "type":"clazz", "id":"U79 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U80 : UBoat" } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U79 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U80 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U73 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U78 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U79 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U78 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U80 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U78 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U80 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U79 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U79 : UBank" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup54",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Check: Number of Reachable States expected: 27 actual 27
   * </p>
   * <p>
   * large reachbility graph with embedded states:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"R100 :
   * ReachableState", "attributes":[ "descr=19 0.0\u000aleft: (wolf)\u000aboat: at: (left) with:
   * (goat)\u000aright: (cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=19" ] }, {
   * "type":"clazz", "id":"R101 : ReachableState", "attributes":[ "descr=20 0.0\u000aleft:
   * (goat)\u000aboat: at: (right) with: ()\u000aright: (wolf, cabbage)\u000a", "failureState=false",
   * "metricValue=0.0", "number=20" ] }, { "type":"clazz", "id":"R102 : ReachableState",
   * "attributes":[ "descr=21 0.0\u000aleft: (goat)\u000aboat: at: (right) with: (wolf)\u000aright:
   * (cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=21" ] }, { "type":"clazz",
   * "id":"R103 : ReachableState", "attributes":[ "descr=22 0.0\u000aleft: (goat)\u000aboat: at:
   * (right) with: (cabbage)\u000aright: (wolf)\u000a", "failureState=false", "metricValue=0.0",
   * "number=22" ] }, { "type":"clazz", "id":"R104 : ReachableState", "attributes":[ "descr=23
   * 0.0\u000aleft: (goat)\u000aboat: at: (left) with: ()\u000aright: (wolf, cabbage)\u000a",
   * "failureState=false", "metricValue=0.0", "number=23" ] }, { "type":"clazz", "id":"R105 :
   * ReachableState", "attributes":[ "descr=24 0.0\u000aleft: ()\u000aboat: at: (left) with:
   * (goat)\u000aright: (wolf, cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=24" ]
   * }, { "type":"clazz", "id":"R106 : ReachableState", "attributes":[ "descr=25 0.0\u000aleft:
   * ()\u000aboat: at: (right) with: ()\u000aright: (wolf, cabbage, goat)\u000a",
   * "failureState=false", "metricValue=0.0", "number=25" ] }, { "type":"clazz", "id":"R107 :
   * ReachableState", "attributes":[ "descr=26 0.0\u000aleft: ()\u000aboat: at: (right) with:
   * (goat)\u000aright: (wolf, cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=26" ]
   * }, { "type":"clazz", "id":"R108 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R109 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R110 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R111 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R112 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R113 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R114 : RuleApplication", "attributes":[ "description=load wolf" ] }, {
   * "type":"clazz", "id":"R115 : RuleApplication", "attributes":[ "description=load cabbage" ] }, {
   * "type":"clazz", "id":"R116 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R117 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R118 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R119 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R120 : RuleApplication", "attributes":[ "description=load wolf" ] }, {
   * "type":"clazz", "id":"R121 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R122 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R123 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R124 : RuleApplication", "attributes":[ "description=load cabbage" ] }, {
   * "type":"clazz", "id":"R125 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R126 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R127 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R128 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R129 : RuleApplication", "attributes":[ "description=load cabbage" ] }, {
   * "type":"clazz", "id":"R130 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R131 : RuleApplication", "attributes":[ "description=load wolf" ] }, {
   * "type":"clazz", "id":"R132 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R133 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R134 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R135 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R136 : RuleApplication", "attributes":[ "description=load wolf" ] }, {
   * "type":"clazz", "id":"R137 : RuleApplication", "attributes":[ "description=load cabbage" ] }, {
   * "type":"clazz", "id":"R138 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R139 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R140 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R141 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R142 : RuleApplication", "attributes":[ "description=move boat" ] }, {
   * "type":"clazz", "id":"R143 : RuleApplication", "attributes":[ "description=load goat" ] }, {
   * "type":"clazz", "id":"R81 : ReachabilityGraph" }, { "type":"clazz", "id":"R82 : ReachableState",
   * "attributes":[ "descr=1 0.0\u000aleft: (cabbage, wolf, goat)\u000aboat: at: (left) with:
   * ()\u000aright: ()\u000a", "failureState=false", "metricValue=0.0", "number=1" ] }, {
   * "type":"clazz", "id":"R83 : ReachableState", "attributes":[ "descr=2 0.0\u000aleft: (wolf,
   * cabbage)\u000aboat: at: (left) with: (goat)\u000aright: ()\u000a", "failureState=false",
   * "metricValue=0.0", "number=2" ] }, { "type":"clazz", "id":"R84 : ReachableState", "attributes":[
   * "descr=3 0.0\u000aleft: (wolf, cabbage)\u000aboat: at: (right) with: ()\u000aright:
   * (goat)\u000a", "failureState=false", "metricValue=0.0", "number=3" ] }, { "type":"clazz",
   * "id":"R85 : ReachableState", "attributes":[ "descr=4 0.0\u000aleft: (wolf, cabbage)\u000aboat:
   * at: (right) with: (goat)\u000aright: ()\u000a", "failureState=false", "metricValue=0.0",
   * "number=4" ] }, { "type":"clazz", "id":"R86 : ReachableState", "attributes":[ "descr=5
   * 0.0\u000aleft: (wolf, cabbage)\u000aboat: at: (left) with: ()\u000aright: (goat)\u000a",
   * "failureState=false", "metricValue=0.0", "number=5" ] }, { "type":"clazz", "id":"R87 :
   * ReachableState", "attributes":[ "descr=6 0.0\u000aleft: (cabbage)\u000aboat: at: (left) with:
   * (wolf)\u000aright: (goat)\u000a", "failureState=false", "metricValue=0.0", "number=6" ] }, {
   * "type":"clazz", "id":"R88 : ReachableState", "attributes":[ "descr=7 0.0\u000aleft:
   * (wolf)\u000aboat: at: (left) with: (cabbage)\u000aright: (goat)\u000a", "failureState=false",
   * "metricValue=0.0", "number=7" ] }, { "type":"clazz", "id":"R89 : ReachableState", "attributes":[
   * "descr=8 0.0\u000aleft: (cabbage)\u000aboat: at: (right) with: ()\u000aright: (wolf,
   * goat)\u000a", "failureState=false", "metricValue=0.0", "number=8" ] }, { "type":"clazz",
   * "id":"R90 : ReachableState", "attributes":[ "descr=9 0.0\u000aleft: (wolf)\u000aboat: at: (right)
   * with: ()\u000aright: (goat, cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=9"
   * ] }, { "type":"clazz", "id":"R91 : ReachableState", "attributes":[ "descr=10 0.0\u000aleft:
   * (cabbage)\u000aboat: at: (right) with: (wolf)\u000aright: (goat)\u000a", "failureState=false",
   * "metricValue=0.0", "number=10" ] }, { "type":"clazz", "id":"R92 : ReachableState", "attributes":[
   * "descr=11 0.0\u000aleft: (cabbage)\u000aboat: at: (right) with: (goat)\u000aright: (wolf)\u000a",
   * "failureState=false", "metricValue=0.0", "number=11" ] }, { "type":"clazz", "id":"R93 :
   * ReachableState", "attributes":[ "descr=12 0.0\u000aleft: (wolf)\u000aboat: at: (right) with:
   * (goat)\u000aright: (cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=12" ] }, {
   * "type":"clazz", "id":"R94 : ReachableState", "attributes":[ "descr=13 0.0\u000aleft:
   * (wolf)\u000aboat: at: (right) with: (cabbage)\u000aright: (goat)\u000a", "failureState=false",
   * "metricValue=0.0", "number=13" ] }, { "type":"clazz", "id":"R95 : ReachableState", "attributes":[
   * "descr=14 0.0\u000aleft: (goat, cabbage)\u000aboat: at: (left) with: ()\u000aright:
   * (wolf)\u000a", "failureState=false", "metricValue=0.0", "number=14" ] }, { "type":"clazz",
   * "id":"R96 : ReachableState", "attributes":[ "descr=15 0.0\u000aleft: (wolf, goat)\u000aboat: at:
   * (left) with: ()\u000aright: (cabbage)\u000a", "failureState=false", "metricValue=0.0",
   * "number=15" ] }, { "type":"clazz", "id":"R97 : ReachableState", "attributes":[ "descr=16
   * 0.0\u000aleft: (cabbage)\u000aboat: at: (left) with: (goat)\u000aright: (wolf)\u000a",
   * "failureState=false", "metricValue=0.0", "number=16" ] }, { "type":"clazz", "id":"R98 :
   * ReachableState", "attributes":[ "descr=17 0.0\u000aleft: (goat)\u000aboat: at: (left) with:
   * (cabbage)\u000aright: (wolf)\u000a", "failureState=false", "metricValue=0.0", "number=17" ] }, {
   * "type":"clazz", "id":"R99 : ReachableState", "attributes":[ "descr=18 0.0\u000aleft:
   * (goat)\u000aboat: at: (left) with: (wolf)\u000aright: (cabbage)\u000a", "failureState=false",
   * "metricValue=0.0", "number=18" ] }, { "type":"clazz", "id":"U1 : URiver" }, { "type":"clazz",
   * "id":"U10 : UBoat" }, { "type":"clazz", "id":"U11 : URiver" }, { "type":"clazz", "id":"U12 :
   * UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U13 : UBoat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"U14 : URiver" }, { "type":"clazz", "id":"U15 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U16 : UBoat" }, { "type":"clazz",
   * "id":"U17 : URiver" }, { "type":"clazz", "id":"U18 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U19 : URiver" }, { "type":"clazz", "id":"U2 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U20 : UBank", "attributes":[ "name=left" ] }, {
   * "type":"clazz", "id":"U21 : UBoat" }, { "type":"clazz", "id":"U22 : URiver" }, { "type":"clazz",
   * "id":"U23 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U24 : UBoat" }, {
   * "type":"clazz", "id":"U25 : URiver" }, { "type":"clazz", "id":"U26 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U27 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U28 : URiver" }, { "type":"clazz", "id":"U29 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U3 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U30 : UBoat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U31
   * : URiver" }, { "type":"clazz", "id":"U32 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U33 : UBoat" }, { "type":"clazz", "id":"U34 : URiver" }, { "type":"clazz",
   * "id":"U35 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U36 : UBoat" }, {
   * "type":"clazz", "id":"U37 : URiver" }, { "type":"clazz", "id":"U38 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U39 : UBoat" }, { "type":"clazz", "id":"U4 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U40 : URiver" }, { "type":"clazz",
   * "id":"U41 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U42 : UBoat" }, {
   * "type":"clazz", "id":"U43 : URiver" }, { "type":"clazz", "id":"U44 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U45 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U46 : URiver" }, { "type":"clazz", "id":"U47 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U48 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U49 : URiver" }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U50 : UBank", "attributes":[ "name=left" ] }, {
   * "type":"clazz", "id":"U51 : UBoat" }, { "type":"clazz", "id":"U52 : URiver" }, { "type":"clazz",
   * "id":"U53 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U54 : UBoat" }, {
   * "type":"clazz", "id":"U55 : URiver" }, { "type":"clazz", "id":"U56 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U57 : UBoat" }, { "type":"clazz", "id":"U58 : URiver" },
   * { "type":"clazz", "id":"U59 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U60 : UBoat" }, {
   * "type":"clazz", "id":"U61 : URiver" }, { "type":"clazz", "id":"U62 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U63 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U64 : URiver" }, { "type":"clazz", "id":"U65 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U66 : UBoat" }, { "type":"clazz", "id":"U67 : URiver"
   * }, { "type":"clazz", "id":"U68 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz",
   * "id":"U69 : UBoat" }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U70 : URiver" }, { "type":"clazz", "id":"U71 : UBoat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"U72 : URiver" }, { "type":"clazz", "id":"U73 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U74 : UBoat" }, { "type":"clazz",
   * "id":"U75 : URiver" }, { "type":"clazz", "id":"U76 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U77 : UBoat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U78
   * : URiver" }, { "type":"clazz", "id":"U79 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U8 : URiver" }, { "type":"clazz", "id":"U80 : UBoat" }, { "type":"clazz",
   * "id":"U9 : UBank", "attributes":[ "name=left" ] } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U2 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U4 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U9 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U10 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U13 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U15 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U16 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U18 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U20 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U21 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U23 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U24 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U26 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U27 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U29 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U30 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U32 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U33 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U35 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U36 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U39 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U41 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U42 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U44 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U45 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U47 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U48 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U50 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U51 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U53 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U54 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U56 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U57 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U59 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U60 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U62 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U63 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U65 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U66 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U68 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U69 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U53 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U71 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U73 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U74 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U76 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U77 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U79 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U80 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U2 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U1 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U3 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U3 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U9 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U14 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U15 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U14 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U17 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U17 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U19 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U20 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U19 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U12 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U20 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U26 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U23 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U28 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U29 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U28 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U20 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U32 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U20 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U34 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U35 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U34 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U23 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U41 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U40 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U35 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U43 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U44 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U43 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U46 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U47 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U46 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U35 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U49 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U50 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U49 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U35 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U53 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U55 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U56 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U55 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U58 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U59 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U58 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U53 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U61 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U61 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U53 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U64 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U65 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U64 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U53 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U68 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U53 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U72 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U73 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U72 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U73 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U75 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U76 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U75 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U73 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U78 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U79 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U78 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U4 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U10 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U13 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U16 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U14 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U18 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U17 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U21 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U19 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U24 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U27 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U30 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U28 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U33 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U31 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U36 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U34 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U39 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U42 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U45 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U43 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U48 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U46 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U51 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U49 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U54 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U57 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U55 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U60 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U58 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U63 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U61 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U66 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U64 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U69 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U71 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U74 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U72 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U77 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U75 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U80 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U78 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U10 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U16 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U21 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U24 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"cargo", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U33 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U36 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U39 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U42 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U51 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"cargo", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U54 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U57 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U60 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U66 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U69 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U74 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U80 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U2 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U2 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U12 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U20 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U26 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U26 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U29 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U29 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U32 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U35 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U38 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U41 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U44 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U44 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U47 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U47 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U50 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U53 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U56 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U59 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U65 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U68 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U79 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U79 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U1 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R82 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U8 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R83 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U11 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R84 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U14 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R85 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U17 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R86 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U19 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R87 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U22 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R88 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U25 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R89 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U28 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R90 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U31 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R91 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U34 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R92 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U37 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R93 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U40 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R94 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U43 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R95 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U46 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R96 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U49 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R97 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U52 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R98 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U55 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R99 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U58 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R100 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U61 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R101 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U64 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R102 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U67 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R103 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U70 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R104 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U72 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R105 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U75 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R106 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U78 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R107 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R109 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R82 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R108 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R83 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R110 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R84 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R113 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R84 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R111 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R85 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R112 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R86 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R116 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R86 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R117 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R86 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R114 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R87 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R115 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R88 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R118 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R89 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R122 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R89 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R119 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R90 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R125 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R90 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R120 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R91 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R121 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R92 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R123 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R93 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R124 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R94 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R126 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R95 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R130 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R95 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R127 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R96 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R133 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R96 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R128 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R97 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R129 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R98 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R131 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R99 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R132 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R100 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R134 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R101 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R135 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R101 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R139 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R101 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R136 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R102 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R137 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R103 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R138 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R104 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R141 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R104 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R140 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R105 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R142 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R106 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R143 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R107 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R108 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R82 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R110 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R83 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R111 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R84 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R112 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R84 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R109
   * : RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R85 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R114 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R86 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R115 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R86 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R113 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R86 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R118 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R87 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R119
   * : RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R88 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R120 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R89 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R121 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R89 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R123 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R90 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R124 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R90 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R116
   * : RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R91 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R126 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R92 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R127 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R93 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R117 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R94 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R128 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R95 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R129
   * : RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R95 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R131 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R96 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R132 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R96 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R122 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R97 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R134 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R98 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R135
   * : RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R99 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R125 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R100 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R136 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R101 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R137 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R101 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R138 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R101 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R133 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R102 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R130 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R103 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R140 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R104 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R139 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R104 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R142 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R105 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R143 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R106 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R141 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R107 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R82 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R83 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R84 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R85 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R86 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R87 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R88 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R89 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R90 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R91 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R92 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R93 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R94 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R95 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R96 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R97 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R98 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R99 : ReachableState"
   * }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 : ReachabilityGraph" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"states", "id":"R100 :
   * ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R101 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R102 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R103 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R104 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R105 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R106 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 :
   * ReachabilityGraph" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"states",
   * "id":"R107 : ReachableState" }, "target":{ "cardinality":"one", "property":"parent", "id":"R81 :
   * ReachabilityGraph" } } ] } ; json["options"]={"canvasid":"canvasFerrymansProblemLazyBackup57",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Check: found a solution true
   * </p>
   * 
   * @see <a href=
   *      '../../../../../../../../doc/FerrymansProblemLazyBackup.html'>FerrymansProblemLazyBackup.html</a>
   */
  @Test
  public void FerrymansProblemLazyBackup() {
    Storyboard storyboard = new Storyboard();

    // ================================================
    storyboard.add("initial situation:");

    URiver river = new URiver();

    UBoat boat = river.createBoat();

    UBank left = river.createBanks().withName("left");

    boat.withBank(left);

    left.createCargos().withName("cabbage");
    left.createCargos().withName("goat");
    left.createCargos().withName("wolf");

    river.createBanks().withName("right");

    storyboard.addObjectDiagram(river);


    ReachableState rs1 = new ReachableState().withGraphRoot(river);

    URiverCreator cc = new URiverCreator();

    IdMap map = cc.createIdMap("s");
    map.with(ReachabilityGraphCreator.createIdMap("rg"));

    ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
        .withMasterMap(map)
        .withStart(rs1)
        .withLazyCloning();

    // ================================================
    // load boat operations

    reachabilityGraph.withTrafo("load wolf", g -> uLoad(g, "wolf"));
    reachabilityGraph.withTrafo("load goat", g -> uLoad(g, "goat"));
    reachabilityGraph.withTrafo("load cabbage", g -> uLoad(g, "cabbage"));

    // ================================================
    // move boat rule
    reachabilityGraph.withTrafo("move boat", g -> uMove(g));

    reachabilityGraph.withLazyBackup();

    // ================================================
    long size = reachabilityGraph.explore();

    for (ReachableState s : reachabilityGraph.getStates()) {
      StringBuilder buf = new StringBuilder("\n");

      for (RuleApplication t : s.getResultOf()) {
        ReachableState src = t.getSrc();

        buf.append(src.getNumber()).append(" --").append(t.getDescription()).append("->\n");
      }

      buf.append("Reachable State " + s.getNumber());
      storyboard.addPreformatted(buf.toString());
      URiver r = (URiver) s.getGraphRoot();
      storyboard.addObjectDiagram(r);
    }


    storyboard.assertEquals("Number of Reachable States expected: ", 27L, size);

    storyboard.add("large reachbility graph with embedded states: ");
    storyboard.addObjectDiagram(reachabilityGraph);

    URiverSet rivers = new URiverSet().with(reachabilityGraph.getStates().getGraphRoot());
    SimpleSet<UBank> banks = rivers.getBanks()
        .createNameCondition("right")
        .filter(bank -> ((UBank) bank).getCargos().size() == 3);

    storyboard.assertTrue("found a solution ", !banks.isEmpty());

    storyboard.dumpHTML();
  }



  private void load(Object g, String cargoName) {
    River river = (River) g;

    Boat boat = river.getBoat();

    if (boat.getCargo() == null) {
      Bank bank = boat.getBank();

      if (bank.getCargos().size() == 3 && !cargoName.equals("goat")) {
        // reject
        return;
      }

      for (Cargo cargo : bank.getCargos()) {
        if (cargo.getName().equals(cargoName)) {
          bank.withoutCargos(cargo);
          boat.withCargo(cargo);
          return;
        }
      }
    }
  }


  private void uLoad(Object g, String cargoName) {
    URiver river = (URiver) g;

    UBoat boat = river.getBoat();

    if (boat.getCargo() == null) {
      UBank bank = boat.getBank();

      if (bank.getCargos().size() == 3 && !cargoName.equals("goat")) {
        // reject
        return;
      }

      for (UCargo cargo : bank.getCargos()) {
        if (cargo.getName().equals(cargoName)) {
          bank.withoutCargos(cargo);
          boat.withCargo(cargo);
          return;
        }
      }
    }
  }


  private void move(Object g) {
    River river = (River) g;

    Boat boat = river.getBoat();

    Bank oldBank = boat.getBank();

    if (oldBank.getCargos().size() >= 2) {
      // would i leave alone the goat with some other cargo?
      for (Cargo cargo : oldBank.getCargos()) {
        if (cargo.getName().equals("goat")) {
          // reject
          return;
        }
      }
    }

    for (Bank newBank : river.getBanks()) {
      if (newBank != oldBank) {
        boat.setBank(newBank);

        Cargo cargo = boat.getCargo();
        if (cargo != null) {
          boat.setCargo(null);
          newBank.withCargos(cargo);
        }
        return;
      }
    }
  }


  private void uMove(Object g) {
    URiver river = (URiver) g;

    UBoat boat = river.getBoat();

    UBank oldBank = boat.getBank();

    if (oldBank.getCargos().size() >= 2) {
      // would i leave alone the goat with some other cargo?
      for (UCargo cargo : oldBank.getCargos()) {
        if (cargo.getName().equals("goat")) {
          // reject
          return;
        }
      }
    }

    for (UBank newBank : river.getBanks()) {
      if (newBank != oldBank) {
        boat.setBank(newBank);

        UCargo cargo = boat.getCargo();
        if (cargo != null) {
          boat.setCargo(null);
          newBank.withCargos(cargo);
        }
        return;
      }
    }
  }


  /**
   * 
   * <p>
   * Storyboard <a href=
   * './src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'
   * type='text/x-java'>UniDirFerrymansProblemRules</a>
   * </p>
   * <p>
   * initial situation:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U1 : URiver" }, {
   * "type":"clazz", "id":"U2 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U3 :
   * UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U4 : UBoat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, {
   * "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 :
   * UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U2 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U4 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U2 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U1 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U3 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U4 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U2 : UBank" } } ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules2",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <script> var json = { "type":"object", "nodes":[ { "type":"patternObject", "id":"u1 : URiverPO",
   * "attributes":[] }, { "type":"patternObject", "id":"u2 : UBoatPO", "attributes":[] }, {
   * "type":"objectdiagram", "style":"nac", "info":"NegativeApplicationCondition", "nodes":[ {
   * "type":"patternObject", "id":"u3 : UCargoPO", "attributes":[] } ] }, { "type":"patternObject",
   * "id":"u4 : UBankPO", "attributes":[] }, { "type":"patternObject", "id":"u5 : UCargoPO",
   * "attributes":[] }, { "type":"objectdiagram", "style":"nac",
   * "info":"NegativeApplicationCondition", "nodes":[ { "type":"patternObject", "id":"u6 : UCargoPO",
   * "attributes":[ "name == goat" ] }, { "type":"patternObject", "id":"u7 : UCargoPO",
   * "attributes":[] } ] } ], "edges":[ { "typ":"EDGE", "source":{ "property":" ", "id":"u1 :
   * URiverPO" }, "target":{ "property":"boat", "id":"u2 : UBoatPO" } }, { "typ":"EDGE", "source":{
   * "property":" ", "id":"u2 : UBoatPO" }, "target":{ "property":"cargo", "id":"u3 : UCargoPO" } }, {
   * "typ":"EDGE", "source":{ "property":" ", "id":"u2 : UBoatPO" }, "target":{ "property":"bank",
   * "id":"u4 : UBankPO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"u4 : UBankPO" },
   * "target":{ "property":"cargos", "id":"u5 : UCargoPO" } }, { "typ":"EDGE", "source":{ "property":"
   * ", "id":"u4 : UBankPO" }, "target":{ "property":"cargos", "id":"u6 : UCargoPO" } }, {
   * "typ":"EDGE", "source":{ "property":" ", "id":"u4 : UBankPO" }, "target":{ "property":"cargos",
   * "id":"u7 : UCargoPO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"u4 : UBankPO" },
   * "target":{ "property":"cargos", "id":"u5 : UCargoPO" }, "style":"destroy" }, { "typ":"EDGE",
   * "source":{ "property":" ", "id":"u2 : UBoatPO" }, "target":{ "property":"cargo", "id":"u5 :
   * UCargoPO" }, "style":"create" } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRulesPatternDiagram2", "display":"html",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script> <script> var
   * json = { "type":"object", "nodes":[ { "type":"patternObject", "id":"u1 : URiverPO",
   * "attributes":[] }, { "type":"patternObject", "id":"u2 : UBoatPO", "attributes":[] }, {
   * "type":"patternObject", "id":"u3 : UBankPO", "attributes":[] }, { "type":"patternObject",
   * "id":"u4 : UBankPO", "attributes":[] }, { "type":"objectdiagram", "style":"nac",
   * "info":"NegativeApplicationCondition", "nodes":[ { "type":"patternObject", "id":"u5 : UCargoPO",
   * "attributes":[ "name == goat" ] }, { "type":"patternObject", "id":"u6 : UCargoPO",
   * "attributes":[] } ] }, { "type":"objectdiagram", "style":"nac", "info":"OptionalSubPattern",
   * "nodes":[ { "type":"patternObject", "id":"u7 : UCargoPO", "attributes":[] } ] } ], "edges":[ {
   * "typ":"EDGE", "source":{ "property":" ", "id":"u1 : URiverPO" }, "target":{ "property":"boat",
   * "id":"u2 : UBoatPO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"u2 : UBoatPO" },
   * "target":{ "property":"bank", "id":"u3 : UBankPO" } }, { "typ":"EDGE", "source":{ "property":" ",
   * "id":"u1 : URiverPO" }, "target":{ "property":"banks", "id":"u4 : UBankPO" } }, { "typ":"EDGE",
   * "source":{ "property":" ", "id":"u3 : UBankPO" }, "target":{ "property":"cargos", "id":"u5 :
   * UCargoPO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"u3 : UBankPO" }, "target":{
   * "property":"cargos", "id":"u6 : UCargoPO" } }, { "typ":"EDGE", "source":{ "property":" ",
   * "id":"u2 : UBoatPO" }, "target":{ "property":"bank", "id":"u3 : UBankPO" }, "style":"destroy" },
   * { "typ":"EDGE", "source":{ "property":" ", "id":"u2 : UBoatPO" }, "target":{ "property":"bank",
   * "id":"u4 : UBankPO" }, "style":"create" }, { "typ":"EDGE", "source":{ "property":" ", "id":"u2 :
   * UBoatPO" }, "target":{ "property":"cargo", "id":"u7 : UCargoPO" } }, { "typ":"EDGE", "source":{
   * "property":" ", "id":"u2 : UBoatPO" }, "target":{ "property":"cargo", "id":"u7 : UCargoPO" },
   * "style":"destroy" }, { "typ":"EDGE", "source":{ "property":" ", "id":"u4 : UBankPO" }, "target":{
   * "property":"cargos", "id":"u7 : UCargoPO" }, "style":"create" } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRulesPatternDiagram3", "display":"html",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 1
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U1 : URiver" }, {
   * "type":"clazz", "id":"U2 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U3 :
   * UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U4 : UBoat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, {
   * "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 :
   * UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U2 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U4 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U2 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U1 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U3 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U4 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U2 : UBank" } } ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules6",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 2
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U10 : UBoat" }, {
   * "type":"clazz", "id":"U3 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U5 :
   * UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U8 : URiver" }, { "type":"clazz", "id":"U9 : UBank", "attributes":[
   * "name=left" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U9 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U10 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U3 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U10 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U10 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules8", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 3
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U11 : URiver" },
   * { "type":"clazz", "id":"U12 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz",
   * "id":"U13 : UBoat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U9 : UBank", "attributes":[ "name=left" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U12 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U13 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U13 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U11 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U12 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules10", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 4
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U14 : URiver" },
   * { "type":"clazz", "id":"U15 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz",
   * "id":"U16 : UBoat" }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, {
   * "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 :
   * UCargo", "attributes":[ "name=wolf" ] }, { "type":"clazz", "id":"U9 : UBank", "attributes":[
   * "name=left" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U15 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U16 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U9 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U14 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U15 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U14 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U16 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U14 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U16 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules12", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 5
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U12 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U17 : URiver" }, { "type":"clazz",
   * "id":"U18 : UBoat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U9 : UBank", "attributes":[ "name=left" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U18 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U17 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U17 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U18 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U17 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U12 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules14", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 6
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U12 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U19 : URiver" }, { "type":"clazz",
   * "id":"U20 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U21 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U20 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U21 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U12 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U19 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U20 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U19 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U21 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U19 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U21 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U12 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U20 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules16", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 7
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U12 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U22 : URiver" }, { "type":"clazz",
   * "id":"U23 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U24 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U23 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U24 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U12 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U24 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U24 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U12 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules18", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 8
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U20 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U25 : URiver" }, { "type":"clazz",
   * "id":"U26 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U27 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U26 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U27 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U20 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U26 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U27 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U25 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U20 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U26 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U26 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules20", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 9
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U23 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U28 : URiver" }, { "type":"clazz",
   * "id":"U29 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U30 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U29 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U30 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U23 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U28 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U29 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U28 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U30 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U28 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U29 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U29 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules22", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 10
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U20 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U31 : URiver" }, { "type":"clazz",
   * "id":"U32 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U33 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U32 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U33 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U20 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U32 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U33 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U33 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U20 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U32 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules24", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 11
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U20 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U34 : URiver" }, { "type":"clazz",
   * "id":"U35 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U36 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U35 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U36 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U20 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U34 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U35 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U34 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U36 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U34 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U36 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U20 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U35 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules26", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 12
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U23 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U37 : URiver" }, { "type":"clazz",
   * "id":"U38 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U39 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U39 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U39 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U39 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U38 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules28", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 13
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U23 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U40 : URiver" }, { "type":"clazz",
   * "id":"U41 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U42 : UBoat" }, {
   * "type":"clazz", "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[
   * "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U41 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U42 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U41 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U42 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U42 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U41 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules30", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 14
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U32 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U43 : URiver" }, { "type":"clazz",
   * "id":"U44 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U45 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U44 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U45 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U32 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U43 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U44 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U43 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U45 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U43 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U32 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U44 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U44 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules32", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 15
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U38 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U46 : URiver" }, { "type":"clazz",
   * "id":"U47 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U48 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U47 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U48 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U46 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U47 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U46 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U48 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U46 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U38 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U47 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U47 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules34", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 16
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U32 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U49 : URiver" }, { "type":"clazz",
   * "id":"U5 : UCargo", "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U50 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U51 : UBoat" }, { "type":"clazz",
   * "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U50 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U51 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U32 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U49 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U50 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U49 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U51 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U49 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U51 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U32 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U50 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules36", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 17
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U32 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U52 : URiver" }, { "type":"clazz", "id":"U53 :
   * UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U54 : UBoat" }, { "type":"clazz",
   * "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U53 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U54 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U32 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U52 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U53 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U54 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U54 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U32 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U53 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules38", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 18
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U38 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U55 : URiver" }, { "type":"clazz", "id":"U56 :
   * UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U57 : UBoat" }, { "type":"clazz",
   * "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U56 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U57 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U55 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U56 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U55 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U57 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U55 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U57 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U38 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U56 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules40", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 19
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U38 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U58 : URiver" }, { "type":"clazz", "id":"U59 :
   * UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U60 : UBoat" }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U59 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U60 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U58 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U59 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U58 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U60 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U58 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U60 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U38 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U59 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules42", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 20
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U50 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U61 : URiver" }, { "type":"clazz", "id":"U62 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U63 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U62 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U63 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U50 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U61 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U61 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U63 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U61 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U50 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U62 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules44", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 21
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U50 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U64 : URiver" }, { "type":"clazz", "id":"U65 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U66 : UBoat" }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U65 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U66 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U50 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U64 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U65 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U64 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U66 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U64 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U66 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U50 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U65 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules46", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 22
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U50 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U67 : URiver" }, { "type":"clazz", "id":"U68 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U69 : UBoat" }, { "type":"clazz", "id":"U7 : UCargo",
   * "attributes":[ "name=wolf" ] } ], "edges":[ { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U68 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U69 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U50 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U67 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U68 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U69 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U69 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U50 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U68 : UBank" } }
   * ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules48", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 23
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U50 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[ "name=goat" ] }, {
   * "type":"clazz", "id":"U62 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U7 :
   * UCargo", "attributes":[ "name=wolf" ] }, { "type":"clazz", "id":"U70 : URiver" }, {
   * "type":"clazz", "id":"U71 : UBoat", "attributes":[ "cargo=null" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U50 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U71 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U50 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U71 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U70 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U50 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U62 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules50", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 24
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U62 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, { "type":"clazz", "id":"U72 :
   * URiver" }, { "type":"clazz", "id":"U73 : UBank", "attributes":[ "name=left" ] }, {
   * "type":"clazz", "id":"U74 : UBoat" } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U73 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U74 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U72 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U73 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U72 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U74 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U72 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U74 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U62 : UBank" } } ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules52",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 25
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U73 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U75 :
   * URiver" }, { "type":"clazz", "id":"U76 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U77 : UBoat", "attributes":[ "cargo=null" ] } ], "edges":[ { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U76 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U77 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U73 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U75 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U76 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U75 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U77 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U75 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U76 : UBank" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules54", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Reachable State 26
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"U5 : UCargo",
   * "attributes":[ "name=cabbage" ] }, { "type":"clazz", "id":"U6 : UCargo", "attributes":[
   * "name=goat" ] }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U73 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U78 :
   * URiver" }, { "type":"clazz", "id":"U79 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U80 : UBoat" } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U79 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U80 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U73 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U78 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U79 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U78 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U80 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U78 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U80 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U79 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U79 : UBank" } } ] } ; json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules56",
   * "display":"svg", "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Check: Number of Reachable States expected: 26 actual 26
   * </p>
   * <p>
   * Small reachbility graph with hyperlinks to states:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"R100 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R101 :
   * ReachableState", "attributes":[ "descr=8 0.0\u000aleft: (wolf)\u000aboat: at: (right) with:
   * ()\u000aright: (goat, cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=8" ] }, {
   * "type":"clazz", "id":"R102 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R103 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R104 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R105 : ReachableState", "attributes":[ "descr=9 0.0\u000aleft:
   * (cabbage)\u000aboat: at: (right) with: ()\u000aright: (goat, wolf)\u000a", "failureState=false",
   * "metricValue=0.0", "number=9" ] }, { "type":"clazz", "id":"R106 : RuleApplication",
   * "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R107 : RuleApplication",
   * "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R108 : RuleApplication",
   * "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R109 : ReachableState",
   * "attributes":[ "descr=10 0.0\u000aleft: (wolf)\u000aboat: at: (right) with: (goat)\u000aright:
   * (cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=10" ] }, { "type":"clazz",
   * "id":"R110 : RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz",
   * "id":"R111 : ReachableState", "attributes":[ "descr=11 0.0\u000aleft: (wolf)\u000aboat: at:
   * (right) with: (cabbage)\u000aright: (goat)\u000a", "failureState=false", "metricValue=0.0",
   * "number=11" ] }, { "type":"clazz", "id":"R112 : ReachableState", "attributes":[ "descr=12
   * 0.0\u000aleft: (cabbage)\u000aboat: at: (right) with: (goat)\u000aright: (wolf)\u000a",
   * "failureState=false", "metricValue=0.0", "number=12" ] }, { "type":"clazz", "id":"R113 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R114 :
   * ReachableState", "attributes":[ "descr=13 0.0\u000aleft: (cabbage)\u000aboat: at: (right) with:
   * (wolf)\u000aright: (goat)\u000a", "failureState=false", "metricValue=0.0", "number=13" ] }, {
   * "type":"clazz", "id":"R115 : ReachableState", "attributes":[ "descr=14 0.0\u000aleft: (wolf,
   * goat)\u000aboat: at: (left) with: ()\u000aright: (cabbage)\u000a", "failureState=false",
   * "metricValue=0.0", "number=14" ] }, { "type":"clazz", "id":"R116 : RuleApplication",
   * "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R117 : RuleApplication",
   * "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R118 : RuleApplication",
   * "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R119 : ReachableState",
   * "attributes":[ "descr=15 0.0\u000aleft: (cabbage, goat)\u000aboat: at: (left) with:
   * ()\u000aright: (wolf)\u000a", "failureState=false", "metricValue=0.0", "number=15" ] }, {
   * "type":"clazz", "id":"R120 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R121 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R122 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R123 : ReachableState", "attributes":[ "descr=16 0.0\u000aleft:
   * (goat)\u000aboat: at: (left) with: (wolf)\u000aright: (cabbage)\u000a", "failureState=false",
   * "metricValue=0.0", "number=16" ] }, { "type":"clazz", "id":"R124 : RuleApplication",
   * "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R125 : ReachableState",
   * "attributes":[ "descr=17 0.0\u000aleft: (wolf)\u000aboat: at: (left) with: (goat)\u000aright:
   * (cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=17" ] }, { "type":"clazz",
   * "id":"R126 : ReachableState", "attributes":[ "descr=18 0.0\u000aleft: (goat)\u000aboat: at:
   * (left) with: (cabbage)\u000aright: (wolf)\u000a", "failureState=false", "metricValue=0.0",
   * "number=18" ] }, { "type":"clazz", "id":"R127 : RuleApplication", "attributes":[
   * "description=move" ] }, { "type":"clazz", "id":"R128 : ReachableState", "attributes":[ "descr=19
   * 0.0\u000aleft: (cabbage)\u000aboat: at: (left) with: (goat)\u000aright: (wolf)\u000a",
   * "failureState=false", "metricValue=0.0", "number=19" ] }, { "type":"clazz", "id":"R129 :
   * ReachableState", "attributes":[ "descr=20 0.0\u000aleft: (goat)\u000aboat: at: (right) with:
   * ()\u000aright: (cabbage, wolf)\u000a", "failureState=false", "metricValue=0.0", "number=20" ] },
   * { "type":"clazz", "id":"R130 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R131 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R132 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R133 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R134 : ReachableState", "attributes":[ "descr=21 0.0\u000aleft:
   * (goat)\u000aboat: at: (right) with: (cabbage)\u000aright: (wolf)\u000a", "failureState=false",
   * "metricValue=0.0", "number=21" ] }, { "type":"clazz", "id":"R135 : ReachableState",
   * "attributes":[ "descr=22 0.0\u000aleft: (goat)\u000aboat: at: (right) with: (wolf)\u000aright:
   * (cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=22" ] }, { "type":"clazz",
   * "id":"R136 : ReachableState", "attributes":[ "descr=23 0.0\u000aleft: (goat)\u000aboat: at:
   * (left) with: ()\u000aright: (cabbage, wolf)\u000a", "failureState=false", "metricValue=0.0",
   * "number=23" ] }, { "type":"clazz", "id":"R137 : RuleApplication", "attributes":[
   * "description=load" ] }, { "type":"clazz", "id":"R138 : RuleApplication", "attributes":[
   * "description=move" ] }, { "type":"clazz", "id":"R139 : ReachableState", "attributes":[ "descr=24
   * 0.0\u000aleft: ()\u000aboat: at: (left) with: (goat)\u000aright: (cabbage, wolf)\u000a",
   * "failureState=false", "metricValue=0.0", "number=24" ] }, { "type":"clazz", "id":"R140 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R141 :
   * ReachableState", "attributes":[ "descr=25 0.0\u000aleft: ()\u000aboat: at: (right) with:
   * ()\u000aright: (cabbage, wolf, goat)\u000a", "failureState=false", "metricValue=0.0", "number=25"
   * ] }, { "type":"clazz", "id":"R142 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R143 : ReachableState", "attributes":[ "descr=26 0.0\u000aleft:
   * ()\u000aboat: at: (right) with: (goat)\u000aright: (cabbage, wolf)\u000a", "failureState=false",
   * "metricValue=0.0", "number=26" ] }, { "type":"clazz", "id":"R81 : ReachableState", "attributes":[
   * "descr=1 0.0\u000aleft: (cabbage, goat, wolf)\u000aboat: at: (left) with: ()\u000aright:
   * ()\u000a", "failureState=false", "metricValue=0.0", "number=1" ] }, { "type":"clazz", "id":"R82 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R83 :
   * ReachabilityGraph" }, { "type":"clazz", "id":"R84 : RuleApplication", "attributes":[
   * "description=move" ] }, { "type":"clazz", "id":"R85 : ReachableState", "attributes":[ "descr=2
   * 0.0\u000aleft: (cabbage, wolf)\u000aboat: at: (left) with: (goat)\u000aright: ()\u000a",
   * "failureState=false", "metricValue=0.0", "number=2" ] }, { "type":"clazz", "id":"R86 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R87 :
   * ReachableState", "attributes":[ "descr=3 0.0\u000aleft: (cabbage, wolf)\u000aboat: at: (right)
   * with: ()\u000aright: (goat)\u000a", "failureState=false", "metricValue=0.0", "number=3" ] }, {
   * "type":"clazz", "id":"R88 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R89 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R90 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R91 : ReachableState", "attributes":[ "descr=4 0.0\u000aleft: (cabbage,
   * wolf)\u000aboat: at: (right) with: (goat)\u000aright: ()\u000a", "failureState=false",
   * "metricValue=0.0", "number=4" ] }, { "type":"clazz", "id":"R92 : ReachableState", "attributes":[
   * "descr=5 0.0\u000aleft: (cabbage, wolf)\u000aboat: at: (left) with: ()\u000aright: (goat)\u000a",
   * "failureState=false", "metricValue=0.0", "number=5" ] }, { "type":"clazz", "id":"R93 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R94 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R95 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R96 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R97 :
   * ReachableState", "attributes":[ "descr=6 0.0\u000aleft: (wolf)\u000aboat: at: (left) with:
   * (cabbage)\u000aright: (goat)\u000a", "failureState=false", "metricValue=0.0", "number=6" ] }, {
   * "type":"clazz", "id":"R98 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R99 : ReachableState", "attributes":[ "descr=7 0.0\u000aleft:
   * (cabbage)\u000aboat: at: (left) with: (wolf)\u000aright: (goat)\u000a", "failureState=false",
   * "metricValue=0.0", "number=7" ] }, { "type":"clazz", "id":"U1 : URiver" }, { "type":"clazz",
   * "id":"U11 : URiver" }, { "type":"clazz", "id":"U14 : URiver" }, { "type":"clazz", "id":"U17 :
   * URiver" }, { "type":"clazz", "id":"U19 : URiver" }, { "type":"clazz", "id":"U22 : URiver" }, {
   * "type":"clazz", "id":"U25 : URiver" }, { "type":"clazz", "id":"U28 : URiver" }, { "type":"clazz",
   * "id":"U31 : URiver" }, { "type":"clazz", "id":"U34 : URiver" }, { "type":"clazz", "id":"U37 :
   * URiver" }, { "type":"clazz", "id":"U40 : URiver" }, { "type":"clazz", "id":"U43 : URiver" }, {
   * "type":"clazz", "id":"U46 : URiver" }, { "type":"clazz", "id":"U49 : URiver" }, { "type":"clazz",
   * "id":"U52 : URiver" }, { "type":"clazz", "id":"U55 : URiver" }, { "type":"clazz", "id":"U58 :
   * URiver" }, { "type":"clazz", "id":"U61 : URiver" }, { "type":"clazz", "id":"U64 : URiver" }, {
   * "type":"clazz", "id":"U67 : URiver" }, { "type":"clazz", "id":"U70 : URiver" }, { "type":"clazz",
   * "id":"U72 : URiver" }, { "type":"clazz", "id":"U75 : URiver" }, { "type":"clazz", "id":"U78 :
   * URiver" }, { "type":"clazz", "id":"U8 : URiver" } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"graphRoot", "id":"U1 : URiver" }, "target":{
   * "cardinality":"one", "property":"reachablestate", "id":"R81 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U8 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R85 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U11 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R87 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U14 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R91 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U17 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R92 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U19 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R97 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U22 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R99 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U25 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R101 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U28 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R105 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U31 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R109 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U34 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R111 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U37 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R112 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U40 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R114 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U43 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R115 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U46 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R119 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U49 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R123 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U52 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R125 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U55 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R126 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U58 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R128 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U61 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R129 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U64 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R134 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U67 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R135 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U70 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R136 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U72 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R139 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U75 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R141 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U78 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R143 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"parent", "id":"R83 :
   * ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate", "id":"R81 :
   * ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"parent",
   * "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate",
   * "id":"R85 : ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one",
   * "property":"reachablestate", "id":"R87 : ReachableState" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{
   * "cardinality":"one", "property":"reachablestate", "id":"R91 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"parent", "id":"R83 :
   * ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate", "id":"R92 :
   * ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"parent",
   * "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate",
   * "id":"R97 : ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one",
   * "property":"reachablestate", "id":"R99 : ReachableState" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{
   * "cardinality":"one", "property":"reachablestate", "id":"R101 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"parent", "id":"R83 :
   * ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate", "id":"R105 :
   * ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"parent",
   * "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate",
   * "id":"R109 : ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one",
   * "property":"reachablestate", "id":"R111 : ReachableState" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{
   * "cardinality":"one", "property":"reachablestate", "id":"R112 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"parent", "id":"R83 :
   * ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate", "id":"R114 :
   * ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"parent",
   * "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate",
   * "id":"R115 : ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one",
   * "property":"reachablestate", "id":"R119 : ReachableState" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{
   * "cardinality":"one", "property":"reachablestate", "id":"R123 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"parent", "id":"R83 :
   * ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate", "id":"R125 :
   * ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"parent",
   * "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate",
   * "id":"R126 : ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one",
   * "property":"reachablestate", "id":"R128 : ReachableState" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{
   * "cardinality":"one", "property":"reachablestate", "id":"R129 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"parent", "id":"R83 :
   * ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate", "id":"R134 :
   * ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"parent",
   * "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate",
   * "id":"R135 : ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one",
   * "property":"reachablestate", "id":"R136 : ReachableState" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" }, "target":{
   * "cardinality":"one", "property":"reachablestate", "id":"R139 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"parent", "id":"R83 :
   * ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate", "id":"R141 :
   * ReachableState" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"parent",
   * "id":"R83 : ReachabilityGraph" }, "target":{ "cardinality":"one", "property":"reachablestate",
   * "id":"R143 : ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"resultOf", "id":"R84 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"tgt", "id":"R81 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"resultOf", "id":"R90 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"tgt", "id":"R87 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"resultOf", "id":"R95 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"tgt", "id":"R92 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R96 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R92 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R104 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R101 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R108 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R105 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R118 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R115 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R122 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R119 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R133 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R129 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R138 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R136 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R82 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R81 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R86 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R85 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R88 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R87 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R89 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R87 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R93
   * : RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R92 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R94 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R92 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R98 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R97 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R100 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R99 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R102 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R101 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R103 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R101 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R106 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R105 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R107 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R105 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R110 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R109 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R113 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R112 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R116 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R115 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R117 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R115 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R120 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R119 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R121 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R119 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R124 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R123 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R127 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R126 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R130 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R129 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R131 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R129 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R132 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R129 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R137 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R136 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R140 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R139 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R142 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R141 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"src", "id":"R91 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R84 : RuleApplication" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"src", "id":"R92 : ReachableState" },
   * "target":{ "cardinality":"many", "property":"ruleapplications", "id":"R90 : RuleApplication" } },
   * { "type":"assoc", "source":{ "cardinality":"one", "property":"src", "id":"R111 : ReachableState"
   * }, "target":{ "cardinality":"many", "property":"ruleapplications", "id":"R95 : RuleApplication" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"src", "id":"R114 :
   * ReachableState" }, "target":{ "cardinality":"many", "property":"ruleapplications", "id":"R96 :
   * RuleApplication" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"src",
   * "id":"R125 : ReachableState" }, "target":{ "cardinality":"many", "property":"ruleapplications",
   * "id":"R104 : RuleApplication" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"src", "id":"R128 : ReachableState" }, "target":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R108 : RuleApplication" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"src", "id":"R135 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R118 : RuleApplication" } }, {
   * "type":"assoc", "source":{ "cardinality":"one", "property":"src", "id":"R134 : ReachableState" },
   * "target":{ "cardinality":"many", "property":"ruleapplications", "id":"R122 : RuleApplication" }
   * }, { "type":"assoc", "source":{ "cardinality":"one", "property":"src", "id":"R136 :
   * ReachableState" }, "target":{ "cardinality":"many", "property":"ruleapplications", "id":"R133 :
   * RuleApplication" } }, { "type":"assoc", "source":{ "cardinality":"one", "property":"src",
   * "id":"R143 : ReachableState" }, "target":{ "cardinality":"many", "property":"ruleapplications",
   * "id":"R138 : RuleApplication" } }, { "type":"assoc", "source":{ "cardinality":"one",
   * "property":"tgt", "id":"R85 : ReachableState" }, "target":{ "cardinality":"many",
   * "property":"resultOf", "id":"R82 : RuleApplication" } }, { "type":"assoc", "source":{
   * "cardinality":"one", "property":"tgt", "id":"R87 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R86 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R91 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R88 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R92 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R89 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R97 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R93 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R99 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R94 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R101 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R98 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R105 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R100 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R109 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R102 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R111 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R103 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R112 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R106 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R114 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R107 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R115 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R110 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R119 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R113 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R123 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R116 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R125 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R117 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R126 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R120 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R128 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R121 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R129 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R124 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R129 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R127 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R134 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R130 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R135 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R131 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R136 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R132 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R139 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R137 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R141 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R140 : RuleApplication" } }, { "type":"assoc",
   * "source":{ "cardinality":"one", "property":"tgt", "id":"R143 : ReachableState" }, "target":{
   * "cardinality":"many", "property":"resultOf", "id":"R142 : RuleApplication" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules59", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * large reachbility graph with embedded states:
   * </p>
   * <script> var json = { "type":"objectdiagram", "nodes":[ { "type":"clazz", "id":"R100 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R101 :
   * ReachableState", "attributes":[ "descr=8 0.0\u000aleft: (wolf)\u000aboat: at: (right) with:
   * ()\u000aright: (goat, cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=8" ] }, {
   * "type":"clazz", "id":"R102 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R103 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R104 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R105 : ReachableState", "attributes":[ "descr=9 0.0\u000aleft:
   * (cabbage)\u000aboat: at: (right) with: ()\u000aright: (goat, wolf)\u000a", "failureState=false",
   * "metricValue=0.0", "number=9" ] }, { "type":"clazz", "id":"R106 : RuleApplication",
   * "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R107 : RuleApplication",
   * "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R108 : RuleApplication",
   * "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R109 : ReachableState",
   * "attributes":[ "descr=10 0.0\u000aleft: (wolf)\u000aboat: at: (right) with: (goat)\u000aright:
   * (cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=10" ] }, { "type":"clazz",
   * "id":"R110 : RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz",
   * "id":"R111 : ReachableState", "attributes":[ "descr=11 0.0\u000aleft: (wolf)\u000aboat: at:
   * (right) with: (cabbage)\u000aright: (goat)\u000a", "failureState=false", "metricValue=0.0",
   * "number=11" ] }, { "type":"clazz", "id":"R112 : ReachableState", "attributes":[ "descr=12
   * 0.0\u000aleft: (cabbage)\u000aboat: at: (right) with: (goat)\u000aright: (wolf)\u000a",
   * "failureState=false", "metricValue=0.0", "number=12" ] }, { "type":"clazz", "id":"R113 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R114 :
   * ReachableState", "attributes":[ "descr=13 0.0\u000aleft: (cabbage)\u000aboat: at: (right) with:
   * (wolf)\u000aright: (goat)\u000a", "failureState=false", "metricValue=0.0", "number=13" ] }, {
   * "type":"clazz", "id":"R115 : ReachableState", "attributes":[ "descr=14 0.0\u000aleft: (wolf,
   * goat)\u000aboat: at: (left) with: ()\u000aright: (cabbage)\u000a", "failureState=false",
   * "metricValue=0.0", "number=14" ] }, { "type":"clazz", "id":"R116 : RuleApplication",
   * "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R117 : RuleApplication",
   * "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R118 : RuleApplication",
   * "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R119 : ReachableState",
   * "attributes":[ "descr=15 0.0\u000aleft: (cabbage, goat)\u000aboat: at: (left) with:
   * ()\u000aright: (wolf)\u000a", "failureState=false", "metricValue=0.0", "number=15" ] }, {
   * "type":"clazz", "id":"R120 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R121 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R122 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R123 : ReachableState", "attributes":[ "descr=16 0.0\u000aleft:
   * (goat)\u000aboat: at: (left) with: (wolf)\u000aright: (cabbage)\u000a", "failureState=false",
   * "metricValue=0.0", "number=16" ] }, { "type":"clazz", "id":"R124 : RuleApplication",
   * "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R125 : ReachableState",
   * "attributes":[ "descr=17 0.0\u000aleft: (wolf)\u000aboat: at: (left) with: (goat)\u000aright:
   * (cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=17" ] }, { "type":"clazz",
   * "id":"R126 : ReachableState", "attributes":[ "descr=18 0.0\u000aleft: (goat)\u000aboat: at:
   * (left) with: (cabbage)\u000aright: (wolf)\u000a", "failureState=false", "metricValue=0.0",
   * "number=18" ] }, { "type":"clazz", "id":"R127 : RuleApplication", "attributes":[
   * "description=move" ] }, { "type":"clazz", "id":"R128 : ReachableState", "attributes":[ "descr=19
   * 0.0\u000aleft: (cabbage)\u000aboat: at: (left) with: (goat)\u000aright: (wolf)\u000a",
   * "failureState=false", "metricValue=0.0", "number=19" ] }, { "type":"clazz", "id":"R129 :
   * ReachableState", "attributes":[ "descr=20 0.0\u000aleft: (goat)\u000aboat: at: (right) with:
   * ()\u000aright: (cabbage, wolf)\u000a", "failureState=false", "metricValue=0.0", "number=20" ] },
   * { "type":"clazz", "id":"R130 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R131 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R132 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R133 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R134 : ReachableState", "attributes":[ "descr=21 0.0\u000aleft:
   * (goat)\u000aboat: at: (right) with: (cabbage)\u000aright: (wolf)\u000a", "failureState=false",
   * "metricValue=0.0", "number=21" ] }, { "type":"clazz", "id":"R135 : ReachableState",
   * "attributes":[ "descr=22 0.0\u000aleft: (goat)\u000aboat: at: (right) with: (wolf)\u000aright:
   * (cabbage)\u000a", "failureState=false", "metricValue=0.0", "number=22" ] }, { "type":"clazz",
   * "id":"R136 : ReachableState", "attributes":[ "descr=23 0.0\u000aleft: (goat)\u000aboat: at:
   * (left) with: ()\u000aright: (cabbage, wolf)\u000a", "failureState=false", "metricValue=0.0",
   * "number=23" ] }, { "type":"clazz", "id":"R137 : RuleApplication", "attributes":[
   * "description=load" ] }, { "type":"clazz", "id":"R138 : RuleApplication", "attributes":[
   * "description=move" ] }, { "type":"clazz", "id":"R139 : ReachableState", "attributes":[ "descr=24
   * 0.0\u000aleft: ()\u000aboat: at: (left) with: (goat)\u000aright: (cabbage, wolf)\u000a",
   * "failureState=false", "metricValue=0.0", "number=24" ] }, { "type":"clazz", "id":"R140 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R141 :
   * ReachableState", "attributes":[ "descr=25 0.0\u000aleft: ()\u000aboat: at: (right) with:
   * ()\u000aright: (cabbage, wolf, goat)\u000a", "failureState=false", "metricValue=0.0", "number=25"
   * ] }, { "type":"clazz", "id":"R142 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R143 : ReachableState", "attributes":[ "descr=26 0.0\u000aleft:
   * ()\u000aboat: at: (right) with: (goat)\u000aright: (cabbage, wolf)\u000a", "failureState=false",
   * "metricValue=0.0", "number=26" ] }, { "type":"clazz", "id":"R81 : ReachableState", "attributes":[
   * "descr=1 0.0\u000aleft: (cabbage, goat, wolf)\u000aboat: at: (left) with: ()\u000aright:
   * ()\u000a", "failureState=false", "metricValue=0.0", "number=1" ] }, { "type":"clazz", "id":"R82 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R83 :
   * ReachabilityGraph" }, { "type":"clazz", "id":"R84 : RuleApplication", "attributes":[
   * "description=move" ] }, { "type":"clazz", "id":"R85 : ReachableState", "attributes":[ "descr=2
   * 0.0\u000aleft: (cabbage, wolf)\u000aboat: at: (left) with: (goat)\u000aright: ()\u000a",
   * "failureState=false", "metricValue=0.0", "number=2" ] }, { "type":"clazz", "id":"R86 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R87 :
   * ReachableState", "attributes":[ "descr=3 0.0\u000aleft: (cabbage, wolf)\u000aboat: at: (right)
   * with: ()\u000aright: (goat)\u000a", "failureState=false", "metricValue=0.0", "number=3" ] }, {
   * "type":"clazz", "id":"R88 : RuleApplication", "attributes":[ "description=load" ] }, {
   * "type":"clazz", "id":"R89 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R90 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R91 : ReachableState", "attributes":[ "descr=4 0.0\u000aleft: (cabbage,
   * wolf)\u000aboat: at: (right) with: (goat)\u000aright: ()\u000a", "failureState=false",
   * "metricValue=0.0", "number=4" ] }, { "type":"clazz", "id":"R92 : ReachableState", "attributes":[
   * "descr=5 0.0\u000aleft: (cabbage, wolf)\u000aboat: at: (left) with: ()\u000aright: (goat)\u000a",
   * "failureState=false", "metricValue=0.0", "number=5" ] }, { "type":"clazz", "id":"R93 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R94 :
   * RuleApplication", "attributes":[ "description=load" ] }, { "type":"clazz", "id":"R95 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R96 :
   * RuleApplication", "attributes":[ "description=move" ] }, { "type":"clazz", "id":"R97 :
   * ReachableState", "attributes":[ "descr=6 0.0\u000aleft: (wolf)\u000aboat: at: (left) with:
   * (cabbage)\u000aright: (goat)\u000a", "failureState=false", "metricValue=0.0", "number=6" ] }, {
   * "type":"clazz", "id":"R98 : RuleApplication", "attributes":[ "description=move" ] }, {
   * "type":"clazz", "id":"R99 : ReachableState", "attributes":[ "descr=7 0.0\u000aleft:
   * (cabbage)\u000aboat: at: (left) with: (wolf)\u000aright: (goat)\u000a", "failureState=false",
   * "metricValue=0.0", "number=7" ] }, { "type":"clazz", "id":"U1 : URiver" }, { "type":"clazz",
   * "id":"U10 : UBoat" }, { "type":"clazz", "id":"U11 : URiver" }, { "type":"clazz", "id":"U12 :
   * UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U13 : UBoat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"U14 : URiver" }, { "type":"clazz", "id":"U15 : UBank",
   * "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U16 : UBoat" }, { "type":"clazz",
   * "id":"U17 : URiver" }, { "type":"clazz", "id":"U18 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U19 : URiver" }, { "type":"clazz", "id":"U2 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U20 : UBank", "attributes":[ "name=left" ] }, {
   * "type":"clazz", "id":"U21 : UBoat" }, { "type":"clazz", "id":"U22 : URiver" }, { "type":"clazz",
   * "id":"U23 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U24 : UBoat" }, {
   * "type":"clazz", "id":"U25 : URiver" }, { "type":"clazz", "id":"U26 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U27 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U28 : URiver" }, { "type":"clazz", "id":"U29 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U3 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U30 : UBoat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U31
   * : URiver" }, { "type":"clazz", "id":"U32 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U33 : UBoat" }, { "type":"clazz", "id":"U34 : URiver" }, { "type":"clazz",
   * "id":"U35 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U36 : UBoat" }, {
   * "type":"clazz", "id":"U37 : URiver" }, { "type":"clazz", "id":"U38 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U39 : UBoat" }, { "type":"clazz", "id":"U4 : UBoat",
   * "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U40 : URiver" }, { "type":"clazz",
   * "id":"U41 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz", "id":"U42 : UBoat" }, {
   * "type":"clazz", "id":"U43 : URiver" }, { "type":"clazz", "id":"U44 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U45 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U46 : URiver" }, { "type":"clazz", "id":"U47 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U48 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U49 : URiver" }, { "type":"clazz", "id":"U5 : UCargo", "attributes":[
   * "name=cabbage" ] }, { "type":"clazz", "id":"U50 : UBank", "attributes":[ "name=left" ] }, {
   * "type":"clazz", "id":"U51 : UBoat" }, { "type":"clazz", "id":"U52 : URiver" }, { "type":"clazz",
   * "id":"U53 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U54 : UBoat" }, {
   * "type":"clazz", "id":"U55 : URiver" }, { "type":"clazz", "id":"U56 : UBank", "attributes":[
   * "name=left" ] }, { "type":"clazz", "id":"U57 : UBoat" }, { "type":"clazz", "id":"U58 : URiver" },
   * { "type":"clazz", "id":"U59 : UBank", "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U6
   * : UCargo", "attributes":[ "name=goat" ] }, { "type":"clazz", "id":"U60 : UBoat" }, {
   * "type":"clazz", "id":"U61 : URiver" }, { "type":"clazz", "id":"U62 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U63 : UBoat", "attributes":[ "cargo=null" ] }, {
   * "type":"clazz", "id":"U64 : URiver" }, { "type":"clazz", "id":"U65 : UBank", "attributes":[
   * "name=right" ] }, { "type":"clazz", "id":"U66 : UBoat" }, { "type":"clazz", "id":"U67 : URiver"
   * }, { "type":"clazz", "id":"U68 : UBank", "attributes":[ "name=right" ] }, { "type":"clazz",
   * "id":"U69 : UBoat" }, { "type":"clazz", "id":"U7 : UCargo", "attributes":[ "name=wolf" ] }, {
   * "type":"clazz", "id":"U70 : URiver" }, { "type":"clazz", "id":"U71 : UBoat", "attributes":[
   * "cargo=null" ] }, { "type":"clazz", "id":"U72 : URiver" }, { "type":"clazz", "id":"U73 : UBank",
   * "attributes":[ "name=left" ] }, { "type":"clazz", "id":"U74 : UBoat" }, { "type":"clazz",
   * "id":"U75 : URiver" }, { "type":"clazz", "id":"U76 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U77 : UBoat", "attributes":[ "cargo=null" ] }, { "type":"clazz", "id":"U78
   * : URiver" }, { "type":"clazz", "id":"U79 : UBank", "attributes":[ "name=right" ] }, {
   * "type":"clazz", "id":"U8 : URiver" }, { "type":"clazz", "id":"U80 : UBoat" }, { "type":"clazz",
   * "id":"U9 : UBank", "attributes":[ "name=left" ] } ], "edges":[ { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U2 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U4 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U9 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U10 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U13 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U15 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U16 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U18 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U20 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U21 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U23 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U24 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U26 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U27 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U29 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U30 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U32 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U33 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U35 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U36 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U39 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U41 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U42 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U44 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U45 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U47 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U48 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U50 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U51 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U53 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U54 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U56 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U57 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U59 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U60 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U62 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U63 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U65 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U66 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"bank", "id":"U68 : UBank" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U69 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"bank",
   * "id":"U50 : UBank" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U71 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"bank", "id":"U73 : UBank" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U74 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"bank", "id":"U76 : UBank" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U77 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"bank", "id":"U79 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U80 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U2 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U1 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U3 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U3 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U9 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U14 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U15 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U14 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U9 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U17 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U17 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U12 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U19 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U20 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U19 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U12 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U20 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U26 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U23 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U28 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U29 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U28 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U20 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U32 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U31 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U20 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U34 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U35 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U34 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U23 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U23 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U41 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U40 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U32 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U43 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U44 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U43 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U46 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U47 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U46 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U32 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U49 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U50 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U49 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U32 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U53 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U38 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U55 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U56 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U55 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U38 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U58 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U59 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U58 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U50 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U61 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U61 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U50 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U64 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U65 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U64 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U50 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U68 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U50 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U62 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U72 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks", "id":"U73 : UBank" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U72 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"banks", "id":"U73 : UBank" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U75 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"banks", "id":"U76 : UBank" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U75 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"banks", "id":"U73 : UBank" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U78 : URiver" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"banks",
   * "id":"U79 : UBank" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U78 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U4 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U1 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U10 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U8 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U13 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U11 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U16 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U14 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U18 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U17 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U21 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U19 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U24 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U22 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U27 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U25 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U30 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U28 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U33 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U31 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U36 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U34 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U39 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U37 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U42 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U40 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U45 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U43 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U48 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U46 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U51 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U49 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U54 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U52 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U57 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U55 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U60 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U58 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U63 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U61 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U66 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U64 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"boat", "id":"U69 : UBoat" }, "target":{
   * "cardinality":"one", "property":"uriver", "id":"U67 : URiver" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"boat", "id":"U71 : UBoat" }, "target":{ "cardinality":"one",
   * "property":"uriver", "id":"U70 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"boat", "id":"U74 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver",
   * "id":"U72 : URiver" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat",
   * "id":"U77 : UBoat" }, "target":{ "cardinality":"one", "property":"uriver", "id":"U75 : URiver" }
   * }, { "type":"edge", "source":{ "cardinality":"one", "property":"boat", "id":"U80 : UBoat" },
   * "target":{ "cardinality":"one", "property":"uriver", "id":"U78 : URiver" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U10 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U16 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U21 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U24 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U33 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U36 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U39 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U42 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U51 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U54 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U57 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"uboat", "id":"U60 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one",
   * "property":"cargo", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat",
   * "id":"U66 : UBoat" } }, { "type":"edge", "source":{ "cardinality":"one", "property":"cargo",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"uboat", "id":"U69 : UBoat" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"uboat", "id":"U74 : UBoat" } }, { "type":"edge",
   * "source":{ "cardinality":"one", "property":"cargo", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"uboat", "id":"U80 : UBoat" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U2 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U2 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U2 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U9 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U12 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U20 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U23 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U26 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U26 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U29 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U29 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U32 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U35 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U38 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U41 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U44 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U44 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U47 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U47 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U50 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U53 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U6 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U56 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U59 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U62 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U65 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U68 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"many", "property":"cargos", "id":"U5 : UCargo" },
   * "target":{ "cardinality":"one", "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge",
   * "source":{ "cardinality":"many", "property":"cargos", "id":"U7 : UCargo" }, "target":{
   * "cardinality":"one", "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge", "source":{
   * "cardinality":"many", "property":"cargos", "id":"U6 : UCargo" }, "target":{ "cardinality":"one",
   * "property":"ubank", "id":"U76 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many",
   * "property":"cargos", "id":"U5 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank",
   * "id":"U79 : UBank" } }, { "type":"edge", "source":{ "cardinality":"many", "property":"cargos",
   * "id":"U7 : UCargo" }, "target":{ "cardinality":"one", "property":"ubank", "id":"U79 : UBank" } },
   * { "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U1 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R81 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U8 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R85 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U11 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R87 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U14 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R91 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U17 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R92 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U19 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R97 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U22 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R99 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U25 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R101 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U28 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R105 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U31 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R109 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U34 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R111 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U37 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R112 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U40 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R114 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U43 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R115 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U46 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R119 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U49 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R123 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U52 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R125 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U55 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R126 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U58 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R128 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U61 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R129 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U64 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R134 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U67 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R135 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U70 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R136 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U72 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R139 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U75 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R141 : ReachableState" } }, {
   * "type":"edge", "source":{ "cardinality":"one", "property":"graphRoot", "id":"U78 : URiver" },
   * "target":{ "cardinality":"one", "property":"reachablestate", "id":"R143 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R84 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R81 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R82 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R85 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R86 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R87 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R90 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R87 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R88 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R91 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R89 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R92 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R95 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R92 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R96 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R92 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R93 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R97 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R94 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R99 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf", "id":"R98 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R101 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R104 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R101 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R100 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R105 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R108 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R105 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R102 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R109 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R103 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R111 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R106 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R112 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R107 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R114 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R110 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R115 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R118 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R115 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R113 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R119 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R122 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R119 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R116 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R123 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R117 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R125 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R120 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R126 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R121 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R128 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R124 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R129 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R127 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R129 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R133 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R129 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R130 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R134 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R131 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R135 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R132 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R136 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R138 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R136 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R137 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R139 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R140 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R141 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"resultOf",
   * "id":"R142 : RuleApplication" }, "target":{ "cardinality":"one", "property":"tgt", "id":"R143 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R82 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R81 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R86 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R85 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R88 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R87 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R89 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R87 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R84
   * : RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R91 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R93 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R92 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R94 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R92 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R90 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R92 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R98 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R97 : ReachableState"
   * } }, { "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R100
   * : RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R99 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R102 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R101 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R103 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R101 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R106 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R105 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R107 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R105 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R110 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R109 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R95 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R111 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R113 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R112 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R96 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R114 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R116 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R115 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R117 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R115 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R120 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R119 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R121 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R119 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R124 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R123 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R104 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R125 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R127 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R126 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R108 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R128 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R130 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R129 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R131 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R129 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R132 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R129 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R122 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R134 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R118 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R135 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R137 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R136 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R133 : RuleApplication" },
   * "target":{ "cardinality":"one", "property":"src", "id":"R136 : ReachableState" } }, {
   * "type":"assoc", "source":{ "cardinality":"many", "property":"ruleapplications", "id":"R140 :
   * RuleApplication" }, "target":{ "cardinality":"one", "property":"src", "id":"R139 :
   * ReachableState" } }, { "type":"assoc", "source":{ "cardinality":"many",
   * "property":"ruleapplications", "id":"R142 : RuleApplication" }, "target":{ "cardinality":"one",
   * "property":"src", "id":"R141 : ReachableState" } }, { "type":"assoc", "source":{
   * "cardinality":"many", "property":"ruleapplications", "id":"R138 : RuleApplication" }, "target":{
   * "cardinality":"one", "property":"src", "id":"R143 : ReachableState" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R81 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R85 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R87 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R91 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R92 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R97 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R99 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R101 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R105 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R109 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R111 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R112 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R114 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R115 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R119 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R123 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R125 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R126 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R128 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R129 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R134 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R135 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R136 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R139 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R141 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } }, { "type":"assoc",
   * "source":{ "cardinality":"many", "property":"states", "id":"R143 : ReachableState" }, "target":{
   * "cardinality":"one", "property":"parent", "id":"R83 : ReachabilityGraph" } } ] } ;
   * json["options"]={"canvasid":"canvasUniDirFerrymansProblemRules61", "display":"svg",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * Found a solution.
   * </p>
   * 
   * @see <a href=
   *      '../../../../../../../../doc/UniDirFerrymansProblemRules.html'>UniDirFerrymansProblemRules.html</a>
   */
  @Test
  public void UniDirFerrymansProblemRules() {
    Storyboard storyboard = new Storyboard();

    // ================================================
    storyboard.add("initial situation:");

    URiver river = new URiver();

    UBoat boat = river.createBoat();

    UBank left = river.createBanks().withName("left");

    boat.withBank(left);

    UCargo cabbage = left.createCargos().withName("cabbage");
    UCargo goat = left.createCargos().withName("goat");
    UCargo wolf = left.createCargos().withName("wolf");

    UBank right = river.createBanks().withName("right");

    storyboard.addObjectDiagram(river);

    IdMap map = URiverCreator.createIdMap("rg");
    map.with(ReachabilityGraphCreator.createIdMap("rg"));

    ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
        .withMasterMap(map);
    reachabilityGraph.withLazyCloning();

    ReachableState rs1 = reachabilityGraph.createStates().withGraphRoot(river);
    reachabilityGraph.withTodo(rs1);


    // load boat rule
    URiverPO riverPO = new URiverPO();

    Pattern loadPattern = (Pattern) riverPO.getPattern().withName("load").withDebugMode(Kanban.DEBUG_ON);
    map.getId(loadPattern);

    UBoatPO boatPO = riverPO.createBoatPO();

    boatPO.startNAC().createCargoPO().endNAC();

    UBankPO bankPO = boatPO.createBankPO();

    UCargoPO cargoPO = bankPO.createCargosPO();

    UCargoPO goatPO = bankPO.startNAC()
        .createCargosPO().createNameCondition("goat").hasMatchOtherThen(cargoPO);
    bankPO.createCargosPO().hasMatchOtherThen(cargoPO).hasMatchOtherThen(goatPO);
    bankPO.endNAC();

    bankPO.createCargosLink(cargoPO, Pattern.DESTROY);

    boatPO.createCargoLink(cargoPO, Pattern.CREATE);

    reachabilityGraph.addToRules(loadPattern);

    storyboard.addPattern(loadPattern, false);


    // ================================================
    // move boat rule

    riverPO = new URiverPO();

    Pattern movePattern = (Pattern) riverPO.getPattern().withName("move").withDebugMode(Kanban.DEBUG_ON);

    boatPO = riverPO.createBoatPO();

    UBankPO oldBankPO = boatPO.createBankPO();

    UBankPO newBankPO = riverPO.createBanksPO().hasMatchOtherThen(oldBankPO);

    // do not leave the goat alone with some other cargo
    goatPO = oldBankPO.startNAC().createCargosPO().createNameCondition("goat");
    oldBankPO.createCargosPO().hasMatchOtherThen(goatPO).endNAC();

    boatPO.createBankLink(oldBankPO, Pattern.DESTROY);

    boatPO.createBankLink(newBankPO, Pattern.CREATE);

    cargoPO = boatPO.startSubPattern().createCargoPO();

    boatPO.createCargoLink(cargoPO, Pattern.DESTROY);

    newBankPO.createCargosLink(cargoPO, Pattern.CREATE)
        .endSubPattern();

    storyboard.addPattern(movePattern, false);

    reachabilityGraph.addToRules(movePattern);


    // ======================================================================================

    ObjectSet staticNodes = new ObjectSet();
    staticNodes.with(goat, cabbage, wolf);
    reachabilityGraph.setStaticNodes(staticNodes);

    reachabilityGraph.explore();

    // ======================================================================================

    long size = reachabilityGraph.getStates().size();



    for (ReachableState s : reachabilityGraph.getStates()) {
      storyboard.add("Reachable State " + s.getNumber());
      storyboard.addObjectDiagramOnlyWith(s.getDynNodes(), reachabilityGraph.getStaticNodes());
    }


    storyboard.assertEquals("Number of Reachable States expected: ", 26L, size);

    storyboard.add("Small reachbility graph with hyperlinks to states: ");
    storyboard.addObjectDiagramOnlyWith(reachabilityGraph.getStates(), reachabilityGraph.getStates().getRuleapplications());

    // storyboard.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));

    storyboard.add("large reachbility graph with embedded states: ");
    storyboard.addObjectDiagram(reachabilityGraph);

    URiverSet rivers = new URiverSet().with(reachabilityGraph.getStates().getGraphRoot());
    UBankSet banks = rivers.getBanks().createNameCondition("right");

    for (UBank bank : banks) {
      if (bank.getCargos().size() == 3) {
        storyboard.add("Found a solution.");
        break;
      }
    }

    storyboard.dumpHTML();
  }


  // /**
  // *
  // * @see <a
  // href='../../../../../../../../doc/LayzFerrymansProblemIsomorphim.html'>LayzFerrymansProblemIsomorphim.html</a>
  // */
  //
  // @Test
  // public void LayzFerrymansProblemIsomorphim()
  // {
  // Storyboard storyboard = new Storyboard();
  //
  // // ================================================
  // storyboard.add("initial situation:");
  //
  // LRiver river = new LRiver();
  //
  // LBoat boat = river.createBoat();
  //
  // LBank left = river.createBanks().withName("left").withBoat(boat);
  //
  // left.createCargos().withName("cabbage");
  // LCargo goat = left.createCargos().withName("goat");
  // LCargo wolf = left.createCargos().withName("wolf");
  //
  // LBank right = river.createBanks().withName("right");
  //
  // storyboard.addObjectDiagram(river);
  //
  // ObjectSet graphElems = new ObjectSet();
  //
  // // try aggregation
  // LazyCloneOp lazyCloneOp = new LazyCloneOp().setMap(LRiverCreator.createIdMap("lazy"));
  //
  // LRiver river2 = (LRiver) lazyCloneOp.clone(river);
  //
  // storyboard.add("compute certificates");
  //
  // ReachableState rs1 = new ReachableState().withGraphRoot(river);
  //
  // LRiverCreator cc = new LRiverCreator();
  //
  // IdMap map = LRiverCreator.createIdMap("s");
  // map.with(ReachabilityGraphCreator.createIdMap("rg"));
  //
  // ReachabilityGraph reachabilityGraph = new ReachabilityGraph()
  // .withMasterMap(map).withStates(rs1).withTodo(rs1).setLazyCloneOp(lazyCloneOp);
  //
  // // add dummy rule computing a lazy clone.
  // // explore should recognize it as equal and terminate immediately
  //
  // LRiverPO riverPO = new LRiverPO();
  //
  // riverPO.createCondition(r -> lazyClone(riverPO, r));
  //
  // reachabilityGraph.withRules(riverPO);
  //
  // long size = reachabilityGraph.explore();

  // // ================================================
  // // map.with(new ModelPatternCreator());
  // // FlipBook flipBook = new FlipBook().withMap(map);
  // // String id = map.getId(reachabilityGraph);
  // //
  // // ================================================
  // // load boat rule
  //
  // LRiverPO riverPO = new LRiverPO();
  //
  // Pattern loadPattern = (Pattern)
  // riverPO.getPattern().withName("load").withDebugMode(Kanban.DEBUG_ON);
  // map.getId(loadPattern);
  //
  // LBoatPO boatPO = riverPO.createBoatPO();
  //
  // boatPO.startNAC().createCargoPO().endNAC();
  //
  // LBankPO bankPO = boatPO.createBankPO();
  //
  // LCargoPO cargoPO = bankPO.createCargosPO();
  //
  // LCargoPO goatPO = bankPO.startNAC()
  // .createCargosPO().createNameCondition("goat").hasMatchOtherThen(cargoPO);
  // bankPO.createCargosPO().hasMatchOtherThen(cargoPO).hasMatchOtherThen(goatPO);
  // bankPO.endNAC();
  //
  // loadPattern.createCloneOp();
  //
  // bankPO.createCargosLink(cargoPO, Pattern.DESTROY);
  //
  // boatPO.createCargoLink(cargoPO, Pattern.CREATE);
  //
  // storyboard.addPattern(loadPattern, false);
  //
  // reachabilityGraph.addToRules(loadPattern);
  //
  // // ================================================
  // // move boat rule
  //
  // riverPO = new LRiverPO();
  //
  // Pattern movePattern = (Pattern)
  // riverPO.getPattern().withName("move").withDebugMode(Kanban.DEBUG_ON);
  //
  // boatPO = riverPO.createBoatPO();
  //
  // LBankPO oldBankPO = boatPO.createBankPO();
  //
  // LBankPO newBankPO = riverPO.createBanksPO().hasMatchOtherThen(oldBankPO);
  //
  // // do not leave the goat allone with some other cargo
  // goatPO = oldBankPO.startNAC().createCargosPO().createNameCondition("goat");
  // oldBankPO.createCargosPO().hasMatchOtherThen(goatPO).endNAC();
  //
  // movePattern.createCloneOp();
  //
  // boatPO.createBankLink(oldBankPO, Pattern.DESTROY);
  //
  // boatPO.createBankLink(newBankPO, Pattern.CREATE);
  //
  // cargoPO = boatPO.startSubPattern().createCargoPO();
  //
  // cargoPO.createBoatLink(boatPO, Pattern.DESTROY);
  //
  // cargoPO.createBankLink(newBankPO, Pattern.CREATE).endSubPattern();
  //
  // storyboard.addPattern(movePattern, false);
  //
  // reachabilityGraph.addToRules(movePattern);
  //
  // // ================================================
  // long size = reachabilityGraph.explore();
  //
  // for (ReachableState s : reachabilityGraph.getStates())
  // {
  // storyboard.add("Reachable State " + s.getNumber());
  // LRiver r = (LRiver) s.getGraphRoot();
  // storyboard.addObjectDiagram(r, r.getBoat(), r.getBanks(), r.getBanks().getCargos());
  // }
  //
  //
  // storyboard.assertEquals("Number of Reachable States expected: ", 27L, size);
  //
  // storyboard.add("Small reachbility graph with hyperlinks to states: ");
  // storyboard.add(reachabilityGraph.dumpDiagram("ferrymansproblemRG"));
  //
  // storyboard.add("large reachbility graph with embedded states: ");
  // storyboard.addObjectDiagram(map, reachabilityGraph, true);
  //
  // LRiverSet rivers = new LRiverSet().with(reachabilityGraph.getStates().getGraphRoot());
  // LBankSet banks = rivers.getBanks().createNameCondition("right");
  //
  // for (LBank bank : banks)
  // {
  // if (bank.getCargos().size() == 3)
  // {
  // storyboard.add("Found a solution.");
  // break;
  // }
  // }
  //
  // storyboard.dumpHTML();
  // }


}
