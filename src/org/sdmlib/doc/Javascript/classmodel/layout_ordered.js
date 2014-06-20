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
OrderedLayout = function(graph, order) {
	this.graph = graph;
	this.order = order;
	this.radius = 40;
	this.layout();
};
OrderedLayout.prototype = {
	layout: function() {
		this.layoutPrepare();
		this.layoutCalcBounds();
	},

	layoutPrepare: function(order) {
		for (i in this.graph.nodes) {
			var node = this.graph.nodes[i];
			node.x = 0;
			node.y = 0;
		}
		var counter = 0;
		for (i in this.order) {
			var node = this.order[i];
			node.x = counter;
			node.y = Math.random();
			counter++;
		}
	},

	layoutCalcBounds: function() {
		var minx = Infinity, maxx = -Infinity, miny = Infinity, maxy = -Infinity;
		for (i in this.graph.nodes) {
			var x = this.graph.nodes[i].x;
			var y = this.graph.nodes[i].y;
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
	}
};
