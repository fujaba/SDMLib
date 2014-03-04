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
GraphLayout.Dagre = function(graph) {
    this.graph = graph;
    this.layouting();
};
GraphLayout.Dagre.prototype = {
    layouting: function() {
		this.g = new dagre.Digraph();
		for (var i in this.graph.nodes) {
			var node = this.graph.nodes[i];
			
			this.g.addNode(node.id, {"label": node.id, "width":node.width+10, "height":node.height+20});
		}
		for (var i in this.graph.edges) {
			var edges = this.graph.edges[i];
			edges.id = i;
			this.g.addEdge(i, edges.source.id, edges.target.id);
		}
    },
	layout: function(width, height) {
		var layout = dagre.layout().run(this.g);
		// Set the layouting back
		var maxx=0, maxy=0;
		for (var i in this.graph.nodes) {
			var node = this.graph.nodes[i];
			var layoutNode = layout._nodes[node.id];
			node.x = layoutNode.value.x;
			node.y = layoutNode.value.y;
			maxx=Math.max(maxx,node.x+node.width);
			maxy=Math.max(maxy,node.y+node.height);
		}
		width=maxx+20;
		height=maxy+50;
		this.graph.drawGraph(width, height);
    }
};
