/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.
 
 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
*/
CircularLayout = function(graph){this.graph = graph;};
/**
 * Spreads the vertices evenly in a circle. No cross reduction.
 *
 * @param graph A valid graph instance
 */
CircularLayout.prototype = {
 postlayout: function() {
  /* Radius. */
  var r = Math.min(this.width, this.height) / 2;
  /* Where to start the circle. */
  var dx = this.width / 2;
  var dy = this.height / 2;
  /* Calculate the step so that the vertices are equally apart. */
  var step = 2*Math.PI / this.graph.nodeCount; 
  var t = 0; // Start at "angle" 0.
  for (var i in this.graph.nodes) {
    var v = this.graph.nodes[i];
    v.x = Math.round(r*Math.cos(t) + dx);
    v.y = Math.round(r*Math.sin(t) + dy);
    t = t + step;
  }
},
  layout : function(width, height){
    this.width = width;
    this.height = height;
    this.postlayout();
    this.graph.drawGraph(width, height);
}
};