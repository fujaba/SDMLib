package org.sdmlib.test.examples.patternrewriteops;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.patternrewriteops.model.Person;
import org.sdmlib.test.examples.patternrewriteops.model.Station;
import org.sdmlib.test.examples.patternrewriteops.model.Train;
import org.sdmlib.test.examples.patternrewriteops.model.util.PersonPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.SignalFlagPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.StationPO;
import org.sdmlib.test.examples.patternrewriteops.model.util.TrainPO;

public class TrainStoryboards
{
   protected Person p1;
   protected Person p2;
   protected Person p3;

     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/patternrewriteops/TrainStoryboards.java' type='text/x-java'>trainCollectPassengers</a></p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"P10 : Person",
    *          "attributes":[
    *             "train=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P11 : Person",
    *          "attributes":[
    *             "train=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P4 : Person",
    *          "attributes":[
    *             "train=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P5 : Person",
    *          "attributes":[
    *             "train=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P6 : Person",
    *          "attributes":[
    *             "train=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S12 : SignalFlag"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S2 : Station"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S3 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S7 : SignalFlag"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Station"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S9 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T1 : Train"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"flag",
    *             "id":"S7 : SignalFlag"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"station",
    *             "id":"S2 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"flag",
    *             "id":"S12 : SignalFlag"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S3 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S2 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S8 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S3 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S9 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"people",
    *             "id":"P4 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S2 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"people",
    *             "id":"P5 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S2 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"people",
    *             "id":"P6 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S2 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"people",
    *             "id":"P10 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"people",
    *             "id":"P11 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S2 : Station"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"trains",
    *             "id":"T1 : Train"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvastrainCollectPassengers1", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"P10 : Person",
    *          "attributes":[
    *             "train=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P11 : Person",
    *          "attributes":[
    *             "train=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P4 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P5 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P6 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S12 : SignalFlag"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S2 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S3 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Station"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S9 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T1 : Train"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"flag",
    *             "id":"S12 : SignalFlag"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S3 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S2 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S8 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S3 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S9 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P4 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P5 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P6 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"people",
    *             "id":"P10 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"people",
    *             "id":"P11 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S2 : Station"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"trains",
    *             "id":"T1 : Train"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvastrainCollectPassengers2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"P10 : Person",
    *          "attributes":[
    *             "train=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P11 : Person",
    *          "attributes":[
    *             "train=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P4 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P5 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P6 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S12 : SignalFlag"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S2 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S3 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Station"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S9 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T1 : Train"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"flag",
    *             "id":"S12 : SignalFlag"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S8 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S3 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S9 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P4 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P5 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P6 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"people",
    *             "id":"P10 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"people",
    *             "id":"P11 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S2 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S3 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S3 : Station"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"trains",
    *             "id":"T1 : Train"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvastrainCollectPassengers3", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"P10 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P11 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P4 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P5 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P6 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S2 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S3 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S9 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T1 : Train"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S9 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P4 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P5 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P6 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P10 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P11 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S3 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S2 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S3 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S8 : Station"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"trains",
    *             "id":"T1 : Train"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvastrainCollectPassengers4", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"P10 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P11 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P4 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P5 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P6 : Person",
    *          "attributes":[
    *             "station=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S2 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S3 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S9 : Station",
    *          "attributes":[
    *             "flag=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T1 : Train"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P4 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P5 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P6 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P10 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"passengers",
    *             "id":"P11 : Person"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"train",
    *             "id":"T1 : Train"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S8 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S9 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S3 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S8 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"prev",
    *             "id":"S2 : Station"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"next",
    *             "id":"S3 : Station"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"station",
    *             "id":"S9 : Station"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"trains",
    *             "id":"T1 : Train"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvastrainCollectPassengers5", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/trainCollectPassengers.html'>trainCollectPassengers.html</a>
*/
   @Test 
   public void trainCollectPassengers()
   {
      Storyboard storyboard = new Storyboard();

      Train train = new Train();

      Station stat1 = train.createStation();

      Station stat3 = stat1.createNext().createNext();

      stat3.createNext();

      p1 = stat1.createPeople();
      p2 = stat1.createPeople();
      p3 = stat1.createPeople();

      stat1.createFlag();

      stat3.createPeople();
      stat3.createPeople();

      stat3.createFlag();

//      storyboard.withMap(TrainCreator.createIdMap("Train"));
      
      storyboard.addObjectDiagram(train);
      
      int i = 1;

      do 
      {
         // collect people from first station
         TrainPO trainPO = new TrainPO(train);

         StationPO stationPO = trainPO.createStationPO();

         SignalFlagPO flagPO = stationPO.createFlagPO(Pattern.DESTROY);

         stationPO.startSubPattern();
         
         PersonPO personPO = stationPO.createPeoplePO();

         personPO.createStationLink(stationPO, Pattern.DESTROY);
         
         personPO.createTrainLink(trainPO, Pattern.CREATE);

         personPO.allMatches();
         
         stationPO.endSubPattern();

         storyboard.addObjectDiagram(train);
         
         train.setStation(train.getStation().getNext());
      }
      while (train.getStation() != null);
      
      storyboard.dumpHTML();

      storyboard.assertEquals("station should be vacant ",  0, stat1.getPeople().size());
      storyboard.assertEquals("train should have all people ",  5, train.getPassengers().size());
   }
}
