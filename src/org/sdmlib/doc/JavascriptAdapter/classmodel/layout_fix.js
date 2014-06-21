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
FixedLayout = function(graph) {
    this.graph = graph;
	this.items = new Array();
};
FixedLayout.prototype = {
    addNode: function(nodeid, left, top) {
		this.items.push(new Array(nodeid, left, top));
	},
	layout: function(width, height) {
		var newNode = {}
        for (i in this.items) {
            var nodeid = this.items[i][0];
			var node = this.graph.nodes[nodeid];
			if(node){
				node.x = this.items[i][1];
				node.y = this.items[i][2];
				newNode[nodeid] = node;
			}
		}
		this.graph.nodes = newNode;
		var newEdges = [];
		for (i in this.graph.edges) {
            var edge = this.graph.edges[i];
			if(newNode[edge.source.id] && newNode[edge.target.id]){
				newEdges.push(edge);
			}
		}
		this.graph.edges = newEdges;
		
		this.width = width;
		this.height = height;
		this.factorX = (width - 2 * this.radius) / (g.layoutMaxX - g.layoutMinX);
		this.factorY = (height - 2 * this.radius) / (g.layoutMaxY - g.layoutMinY);
		this.graph.drawGraph(width, height);
	}
};
