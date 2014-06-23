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
SpringLayout = function(graph) {
	this.graph = graph;
	this.iterations = 500;
	this.maxRepulsiveForceDistance = 6;
	this.k = 2;
	this.c = 0.01;
	this.maxVertexMovement = 0.5;
	this.radius = 40;
	this.layouting();
};
SpringLayout.prototype = {
	layouting: function() {
		forceNodes = {}
		for (var i in this.graph.nodes) {
			var node = this.getNode(i);
			forceNodes[node.id] = {"id": node.id,"x":0,"y":0};
		}
		
		for (var i = 0; i < this.iterations; i++) {
			this.layoutIteration(forceNodes);
		}
		this.layoutCalcBounds();
	},
	layout: function(width, height) {
		this.width = width;
		this.height = height;
		this.factorX = (width - 2 * this.radius) / (g.layoutMaxX - g.layoutMinX);
		this.factorY = (height - 2 * this.radius) / (g.layoutMaxY - g.layoutMinY);
		var list = this.graph.nodes;
		for (var i in list) {
			var node = list[i];
			node.x = (node.x - g.layoutMinX) * this.factorX + this.radius;
			node.y = (node.y - g.layoutMinY) * this.factorY + this.radius;
		}
		this.graph.drawGraph(width, height);
	},
	getNode: function(pos) {
		return this.graph.nodes[pos];
	},
	getEdge: function(pos) {
		return this.graph.edges[pos];
	},
	layoutCalcBounds: function() {
		var minx = Infinity, maxx = -Infinity, miny = Infinity, maxy = -Infinity;
		for (i in this.graph.nodes) {
			var x = this.getNode(i).x;
			var y = this.getNode(i).y;
			if(x > maxx) maxx = x;
			if(x < minx) minx = x;
			if(y > maxy) maxy = y;
			if(y < miny) miny = y;
		}
		this.graph.layoutMinX = minx;
		this.graph.layoutMaxX = maxx;
		this.graph.layoutMinY = miny;
		this.graph.layoutMaxY = maxy;
	},
	layoutIteration: function(forceNodes) {
		// Forces on nodes due to node-node repulsions
		var prev = new Array();
		var forceList = new Array();
		for(var pos1 in this.graph.nodes) {
			var node1 = this.graph.nodes[pos1];
			var forceNode1 = forceNodes[pos1];
			for (var pos2 in prev) {
				var node2 = prev[pos2];
				var forceNode2 = forceList[pos2];
				this.layoutRepulsive(node1, node2, forceNode1, forceNode2);
			}
			prev.push(node1);
			forceList.push(forceNode1);
		}
		// Forces on nodes due to edge attractions
		for (var i in this.graph.edges){
			this.layoutAttractive(this.getEdge(i), forceNodes);
		}

		// Move by the given force
		for (i in this.graph.nodes) {
			var node = this.graph.nodes[i];
			var force = forceNodes[i];
			var xmove = this.c * force.x;
			var ymove = this.c * force.y;

			var max = this.maxVertexMovement;
			if(xmove > max) xmove = max;
			if(xmove < -max) xmove = -max;
			if(ymove > max) ymove = max;
			if(ymove < -max) ymove = -max;

			node.x += xmove;
			node.y += ymove;
		}
	},

	layoutRepulsive: function(node1, node2, forceNode1, forceNode2) {
		if (typeof node1 == 'undefined' || typeof node2 == 'undefined')
			return;
		var dx = node2.x - node1.x;
		var dy = node2.y - node1.y;
		var d2 = dx * dx + dy * dy;
		if(d2 < 0.01) {
			dx = 0.1 * Math.random() + 0.1;
			dy = 0.1 * Math.random() + 0.1;
			var d2 = dx * dx + dy * dy;
		}
		var d = Math.sqrt(d2);
		if(d < this.maxRepulsiveForceDistance) {
			var repulsiveForce = this.k * this.k / d;
			forceNode2.x += repulsiveForce * dx / d;
			forceNode2.y += repulsiveForce * dy / d;
			forceNode1.x -= repulsiveForce * dx / d;
			forceNode1.y -= repulsiveForce * dy / d;
		}
	},

	layoutAttractive: function(edge, forceNodes) {
		var node1 = edge.source;
		var node2 = edge.target;
		var force1 = forceNodes[node1.id];
		var force2 = forceNodes[node2.id];

		var dx = node2.x - node1.x;
		var dy = node2.y - node1.y;
		var d2 = dx * dx + dy * dy;
		if(d2 < 0.01) {
			dx = 0.1 * Math.random() + 0.1;
			dy = 0.1 * Math.random() + 0.1;
			var d2 = dx * dx + dy * dy;
		}
		var d = Math.sqrt(d2);
		if(d > this.maxRepulsiveForceDistance) {
			d = this.maxRepulsiveForceDistance;
			d2 = d * d;
		}
		var attractiveForce = (d2 - this.k * this.k) / this.k;
		if(edge.attraction == undefined) edge.attraction = 1;
		attractiveForce *= Math.log(edge.attraction) * 0.5 + 1;

		force2.x -= attractiveForce * dx / d;
		force2.y -= attractiveForce * dy / d;
		force1.x += attractiveForce * dx / d;
		force1.y += attractiveForce * dy / d;
	}
};
