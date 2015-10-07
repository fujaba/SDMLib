/*
 NetworkParser
 Copyright (c) 2011 - 2014, Stefan Lindel
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
// VERSION: 2015.09.15 10:40

//var uniId = 0;
//function generateId() { return uniId++; };
//Object.prototype.uniId = function () {
//	var newId = generateId();
//	this.uniId = function () { return newId; };
//	return newId;
//};vars: true
/*jslint forin:true, newcap:true, node: true, continue: true */
/*jshint forin:true, laxbreak: true, newcap: false, node: true, nomen: true, -W089, -W079 */

/*global document: false, window: false, navigator: false, unescape: false, Edge: false, DagreLayout: false, Drawer: false */
/*global jsPDF: false, svgConverter: false, Image: false, Blob: false, dagre: false, SymbolLibary: false */
/*global FileReader:false, java:false, ChoiceBox:false */
"use strict";
var ObjectCreate = Object.create || function (o) {var F = function () {}; F.prototype = o; return new F(); };

/* Pos */
var Pos = function (x, y, id) {this.x = Math.round(x || 0); this.y = Math.round(y || 0); if (id) {this.$id = id; } };
/* GraphUtil */
var GraphUtil = function (ns) {if (ns) {this.ns = ns; } };
GraphUtil.prototype.copy = function (ref, src, full, replace) {
	if (src) {
		var i;
		for (i in src) {
			if (!src.hasOwnProperty(i) || typeof (src[i]) === "function") {
				continue;
			}
			if (i.charAt(0) === "$") {
				if (full) {ref[i] = src[i]; }
				continue;
			}
			if (typeof (src[i]) === "object") {
				if (replace) {
					ref[i] = src[i];
					continue;
				}
				if (!ref[i]) {
					if (src[i] instanceof Array) {
						ref[i] = [];
					} else {
						ref[i] = {};
					}
				}
				this.copy(ref[i], src[i], full);
			} else {
				if (src[i] === "") {
					continue;
				}
				ref[i] = src[i];
			}
		}
		if (src.width) {ref.$startWidth = src.width; }
		if (src.height) {ref.$startHeight = src.height; }
	}
	return ref;
};
GraphUtil.prototype.minJson = function (target, src, ref) {
	var i, temp, value;
	for (i in src) {
		if (!src.hasOwnProperty(i) || typeof (src[i]) === "function") {
			continue;
		}
		if (src[i] === null || src[i] === "" || src[i] === 0 || src[i] === false || i.charAt(0) === "$") {
			continue;
		}
		value = src[i];
		if (value instanceof GraphUtil.Options || ref !== null) {
			if (typeof (value) === "object") {
				temp = (value instanceof Array) ? [] : {};
				if (ref) {
					value = this.minJson(temp, value, ref[i]);
				} else {
					value = this.minJson(temp, value, new GraphUtil.Options());
				}
			}
			if (ref && value === ref[i]) {
				continue;
			}
		}
		if (typeof (value) === "object") {
			if (value instanceof Array && value.length < 1) {
				continue;
			}
			if (value instanceof Array) {
				target[i] = this.minJson([], value);
			} else {
				temp = this.minJson({}, value);
				if (JSON.stringify(temp, null, "") === "{}") {
					continue;
				}
				target[i] = temp;
			}
		} else {
			target[i] = value;
		}
	}
	return target;
};
GraphUtil.prototype.bind = function (el, eventName, eventHandler) {
	if (el.addEventListener) {
		el.addEventListener(eventName, eventHandler, false);
	} else if (el.attachEvent) {
		el.attachEvent('on' + eventName, eventHandler);
	}
};
GraphUtil.prototype.create = function (node) {
	var style, item, xmlns, key, tag, k;
	if (document.createElementNS && (node.xmlns || this.ns)) {
		if (node.xmlns) {
			xmlns = node.xmlns;
		} else {
			xmlns = this.ns;
		}
		if (node.tag === "img" && xmlns === "http://www.w3.org/2000/svg") {
			item = document.createElementNS(xmlns, "image");
			item.setAttribute('xmlns:xlink', "http://www.w3.org/1999/xlink");
			item.setAttributeNS("http://www.w3.org/1999/xlink", 'href', node.src);
		} else {
			item = document.createElementNS(xmlns, node.tag);
		}
	} else {
		item = document.createElement(node.tag);
	}

	tag = node.tag.toLowerCase();
	for (key in node) {
		if (!node.hasOwnProperty(key)) {
			continue;
		}
		k = key.toLowerCase();
		if (node[key] === null) {
			continue;
		}
		if (k === 'tag' || k.charAt(0) === '$' || k === 'model') {
			continue;
		}
		if (k.charAt(0) === '#') {
			item[k.substring(1)] = node[key];
			continue;
		}
		if (k === 'rotate') {
			item.setAttribute("transform", "rotate(" + node[key] + "," + node.model.x + "," + node.model.y + ")");
			continue;
		}
		if (k === 'value') {
			if (!node[key]) {
				continue;
			}
			if (tag !== "input") {
				if (tag === "text") {// SVG
					item.appendChild(document.createTextNode(node[key]));
				} else {
					item.innerHTML = node[key];
				}
			} else {
				item[key] = node[key];
			}
			continue;
		}
		if (k.indexOf("on") === 0) {
			this.bind(item, k.substring(2), node[key]);
			continue;
		}
		if (k.indexOf("-") >= 0) {
			item.style[key] = node[key];
		} else {
			if (k === "style" && typeof (node[key]) === "object") {
				for (style in node[key]) {
					if (!node[key].hasOwnProperty(style)) {
						continue;
					}
					if (node[key][style]) {
						if ("transform" === style) {
							item.style.transform = node[key][style];
							item.style.msTransform = item.style.MozTransform = item.style.WebkitTransform = item.style.OTransform = node[key][style];
						} else {
							item.style[style] = node[key][style];
						}
					}
				}
			} else {
				item.setAttribute(key, node[key]);
			}
		}
	}
	if (node.$font) {
		if (this.model && this.model.options && this.model.options.font) {
			for (key in this.model.options.font) {
				if (!this.model.options.font.hasOwnProperty(key)) {
					continue;
				}
				if (this.model.options.font[key]) {
					if (item.style) {
						item.style[key] = this.model.options.font[key];
					} else {
						item.setAttribute(key, this.model.options.font[key]);
					}
				}
			}
		}
	}
	if (node.$parent) {
		node.$parent.appendChild(item);
	}

	if (node.model) {
		item.model = node.model;
	}
	return item;
};
GraphUtil.prototype.getModelNode = function (element) {
	if (!element.model) {
		if (element.parentElement) {
			return this.getModelNode(element.parentElement);
		}
		return null;
	}
	return element;
};
GraphUtil.prototype.getValue = function (value) {return parseInt(("0" + value).replace("px", ""), 10); };
GraphUtil.prototype.isIE = function () {return document.all && !window.opera; };
GraphUtil.prototype.isFireFox = function () {return navigator.userAgent.toLowerCase().indexOf('firefox') > -1; };
GraphUtil.prototype.isOpera = function () {return navigator.userAgent.indexOf("Opera") > -1; };
GraphUtil.prototype.getEventX = function (event) {return (this.isIE) ? window.event.clientX : event.pageX; };
GraphUtil.prototype.getEventY = function (event) {return (this.isIE) ? window.event.clientY : event.pageY; };
GraphUtil.prototype.set = function (id, value) {if (value) {this[id] = value; } };
GraphUtil.prototype.selectText = function (text) {
	var selection, range;
	if (this.isIE()) {
		range = document.body.createTextRange();
		range.moveToElementText(text);
		range.select();
	} else if (this.isFireFox() || this.isOpera()) {
		selection = window.getSelection();
		range = document.createRange();
		range.selectNodeContents(text);
		selection.removeAllRanges();
		selection.addRange(range);
	}
};
GraphUtil.prototype.sizeHTML = function (html, node) {
	if (!html) {return; }
	if (this.$parent) {
		return this.$parent.sizeHTML(html, node);
	}
	this.board.appendChild(html);
	var rect = html.getBoundingClientRect();
	this.board.removeChild(html);
	if (node) {
		if (!node.$startWidth) {
			node.width = Math.round(rect.width);
		}
		if (!node.$startHeight) {
			node.height = Math.round(rect.height);
		}
	}
	return rect;
};
GraphUtil.prototype.hasClass = function (ele, cls) {return ele.className.indexOf(cls) > 0; };
GraphUtil.prototype.addClass = function (ele, cls) {if (!this.hasClass(ele, cls)) {
	ele.className = ele.className + " " + cls;
} };
GraphUtil.prototype.removeClass = function (ele, cls) {
	if (this.hasClass(ele, cls)) {
		var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
		ele.className = ele.className.replace(reg, ' ');
	}
};
/* Info */
GraphUtil.Info = function (info, parent, edge) {
	this.typ = "Info";
	if (typeof (info) === "string") {
		this.id = info;
	} else {
		if (info.property) {this.property = info.property; }
		if (info.cardinality) {this.cardinality = info.cardinality; }
		this.id = info.id;
	}
	this.x = this.y = this.width = this.height = 0;
	this.$center = new Pos();
	this.custom = false;
	this.$parent = parent;
	this.$edge = edge;
	this.$isdraggable = true;
};
GraphUtil.Info.prototype.getX = function () {return this.x; };
GraphUtil.Info.prototype.getY = function () {return this.y; };

GraphUtil.Line = function (source, target, line, style) {this.source = source; this.target = target; this.line = line; this.style = style; };
GraphUtil.Line.Format = {SOLID: "SOLID", DOTTED: "DOTTED"};
/* Options */
GraphUtil.Options = function () {
	this.raster = false;
	this.addBorder = true;
	this.display = "svg";
	this.font = {"font-size": "10px", "font-family": "Verdana"};
	this.layout = {name: "Dagre", rankDir: "TB", nodesep: 10};	// Dagre TB, LR
	this.CardinalityInfo = true;
	this.propertyinfo = true;
	this.rotatetext = true;
	this.linetyp = "center";
	this.buttons = ["HTML", "SVG"];	// ["HTML", "SVG", "PNG", "PDF"]
};
/* Node */
var GraphNode = function (id) {
	this.typ = "node";
	this.id = id;
	this.$edges = [];
	this.attributes = [];
	this.methods = [];
	this.$parent = null;
	this.x = this.y = this.width = this.height = 0;
	this.$isdraggable = true;
};
GraphNode.prototype = ObjectCreate(GraphUtil.prototype);
GraphNode.prototype.getX = function () {if (this.$parent) {return this.$parent.getX() + this.x; } return this.x; };
GraphNode.prototype.getY = function () {if (this.$parent) {return this.$parent.getY() + this.y; } return this.y; };
GraphNode.prototype.getEdges = function () {return this.$edges; };
GraphNode.prototype.removeFromBoard = function (board) {if (this.$gui) {board.removeChild(this.$gui); this.$gui = null; } };
GraphNode.prototype.isClosed = function () {
	if (this.status === "close") {
		return true;
	}
	if (this.$parent) {return this.$parent.isClosed(); }
	return false;
};
GraphNode.prototype.getShowed = function () {
	if (this.status === "close") {
		if (!this.$parent.isClosed()) {
			return this;
		}
	}
	if (this.isClosed()) {
		return this.$parent.getShowed();
	}
	return this;
};
var GraphModel = function (json, options) {
	this.typ = "classdiagram";
	this.$isdraggable = true;
	json = json || {};
	this.left = json.left || 0;
	this.top = json.top || 0;
	this.x = this.y = this.width = this.height = 0;
	if (json.minid) {
		this.minid = json.minid;
	}
	this.$nodeCount = 0;
	this.nodes = {};
	this.edges = [];
	json = json || {};
	this.typ = json.typ || "classdiagram";
	this.set("id", json.id);
	this.options = this.copy(this.copy(new GraphUtil.Options(), json.options), options, true, true);
	this["package"] = "";
	this.set("info", json.info);
	this.set("style", json.style);
	var i;
	if (json.nodes) {
		for (i = 0; i < json.nodes.length; i += 1) {
			this.addNode(json.nodes[i]);
		}
	}
	if (json.edges) {
		for (i = 0; i < json.edges.length; i += 1) {
			this.addEdgeModel(json.edges[i]);
		}
	}
};
GraphModel.prototype = ObjectCreate(GraphNode.prototype);
GraphModel.prototype.addEdgeModel = function (e) {
	var edge, typ = e.typ || "edge";
	typ = typ.charAt(0).toUpperCase() + typ.substring(1).toLowerCase();
	if (typeof window[typ] === "function") {
		edge = new window[typ]();
	} else {
		edge = new Edge();
	}
	edge.source = new GraphUtil.Info(e.source, this, edge);
	edge.target = new GraphUtil.Info(e.target, this, edge);
	edge.$sNode = this.getNode(edge.source.id, true);
	edge.$sNode.$edges.push(edge);

	if (e.info) {
		if (typeof (e.info) === "string") {
			edge.info = {id: e.info};
		} else {
			edge.info = {id: e.info.id, property: e.info.property, cardinality: e.info.cardinality};
		}
	}
	edge.$parent = this;
	edge.set("style", e.style);
	edge.set("counter", e.counter);

	edge.$tNode = this.getNode(edge.target.id, true);
	edge.$tNode.$edges.push(edge);

	this.edges.push(edge);
	return edge;
};
GraphModel.prototype.addEdge = function (source, target) {
	var edge = new Edge();
	edge.source = this.addNode(source);
	edge.target = this.addNode(target);
	return this.addEdgeModel(edge);
};
GraphModel.prototype.addNode = function (node) {
	/* testing if node is already existing in the graph */
	if (typeof (node) === "string") {
		node = {id: node, typ: "node"};
	}
	node.typ = node.typ || "node";
	node.typ = node.typ.toLowerCase();
	if (!(node.id)) {
		node.id = node.typ + "$" + (this.$nodeCount + 1);
	}
	if (this.nodes[node.id] !== undefined) {
		return this.nodes[node.id];
	}
	if (node.typ.indexOf("diagram", node.typ.length - 7) !== -1) {
		node = new GraphModel(node, new GraphUtil.Options());
	} else {
		node = this.copy(new GraphNode(), node);
	}
	this.nodes[node.id] = node;
	node.$parent = this;
	this.$nodeCount += 1;
	return this.nodes[node.id];
};
GraphModel.prototype.removeEdge = function (idSource, idTarget) {
	var z, e;
	for (z = 0; z < this.edges.length; z += 1) {
		e = this.edges[z];
		if (e.$sNode.id === idSource && e.$tNode.id === idTarget) {
			this.edges.splice(z, 1);
			z -= 1;
		} else if (e.$tNode.id === idSource && e.$sNode.id === idTarget) {
			this.edges.splice(z, 1);
			z -= 1;
		}
	}
};
GraphModel.prototype.removeNode = function (id) {
	delete (this.nodes[id]);
	var i;
	for (i = 0; i < this.edges.length; i += 1) {
		if (this.edges[i].$sNode.id === id || this.edges[i].$tNode.id === id) {
			this.edges.splice(i, 1);
			i -= 1;
		}
	}
};
GraphModel.prototype.getNode = function (id, isSub, deep) {
	var n, i, r;
	deep = deep || 0;
	if (this.nodes[id]) {
		return this.nodes[id];
	}
	if (!isSub) {
		return this.addNode(id);
	}
	for (i in this.nodes) {
		if (!this.nodes.hasOwnProperty(i)) {
			continue;
		}
		n = this.nodes[i];
		if (n instanceof GraphModel) {
			r = n.getNode(id, isSub, deep + 1);
			if (r) {
				return r;
			}
		}
	}
	if (deep === 0) {
		return this.addNode(id);
	}
	return null;
};
GraphModel.prototype.toJson = function () {return this.copy({}, this); };
GraphModel.prototype.createElement = function (element, typ) {this.$parent.createElement(element, typ); };
GraphModel.prototype.removeFromBoard = function (board) {
	if (this.$gui) {
		board.removeChild(this.$gui);
		this.$gui = null;
	}
};
GraphModel.prototype.resize = function (mode) {};
GraphModel.prototype.getEdges = function () {return this.edges; };
GraphModel.prototype.calcLines = function (drawer) {
	var i, n, sourcePos, e, ownAssoc = [];
	for (i in this.nodes) {
		if (!this.nodes.hasOwnProperty(i) || typeof (this.nodes[i]) === "function") {
			continue;
		}
		n = this.nodes[i];
		n.$RIGHT = n.$LEFT = n.$UP = n.$DOWN = 0;
	}
	for (i = 0; i < this.edges.length; i += 1) {
		e = this.edges[i];
		if (!e.calculate(this.$gui, drawer)) {
			ownAssoc.push(e);
		}
	}
	for (i = 0; i < ownAssoc.length; i += 1) {
		ownAssoc[i].calcOwnEdge();
		sourcePos = ownAssoc[i].getCenterPosition(ownAssoc[i].$sNode, ownAssoc[i].$start);
		ownAssoc[i].calcInfoPos(sourcePos, ownAssoc[i].$sNode, ownAssoc[i].source);

		sourcePos = ownAssoc[i].getCenterPosition(ownAssoc[i].$tNode, ownAssoc[i].$end);
		ownAssoc[i].calcInfoPos(sourcePos, ownAssoc[i].$tNode, ownAssoc[i].target);
	}
};
GraphModel.prototype.validateModel = function () {
	var e, z, n, id, node, list;
	if (this.typ === "classdiagram") {
		list = this.edges;
		for (e = 0; e < list.length; e += 1) {
			node = list[e].$sNode;
			z = node.id.indexOf(":");
			if (z > 0) {
				id = node.id.substring(z + 1);
				n = this.getNode(id, true, 1);
				delete (this.nodes[node.id]);
				this.edges[e].source.id = id;
				if (n) {
					this.edges[e].$sNode = n;
				} else {
					node.id = id;
					this.nodes[node.id] = node;
				}
			}
			node = list[e].$tNode;
			z = node.id.indexOf(":");
			if (z > 0) {
				id = node.id.substring(z + 1);
				n = this.getNode(id, true, 1);
				delete (this.nodes[node.id]);
				this.edges[e].target.id = id;
				if (n) {
					this.edges[e].$tNode = n;
				} else {
					node.id = id;
					this.nodes[node.id] = node;
				}
			}
			if (!list[e].source.cardinality) {
				list[e].source.cardinality = "one";
			}
			if (!list[e].target.cardinality) {
				list[e].target.cardinality = "one";
			}
			// Refactoring Edges for same property and typ set cardinality
			for (z = e + 1; z < list.length; z += 1) {
				id = typeof (java);
				if (!(id === typeof list[z])) {
					continue;
				}
				if (this.validateEdge(list[e], list[z])) {
					list[e].target.cardinality = "many";
					list.splice(z, 1);
					z -= 1;
				} else if (this.validateEdge(list[z], list[e])) {
					list[e].source.cardinality = "many";
					list.splice(z, 1);
					z -= 1;
				}
			}
		}
	}
};
GraphModel.prototype.validateEdge = function (sEdge, tEdge) {
	return (sEdge.source.id === tEdge.source.id && sEdge.target.id === tEdge.target.id) && (sEdge.source.property === tEdge.source.property && sEdge.target.property === tEdge.target.property);
};
//				######################################################### Graph #########################################################
var Graph = function (json, options) {
	this.x = this.y = this.width = this.height = 0;
	json = json || {};
	json.top = json.top || 50;
	json.left = json.left || 10;
	this.model = new GraphModel(json, options);
	this.initLayouts();
	this.loader = new GraphUtil.Loader(this);
	this.initOption();
};
Graph.prototype = ObjectCreate(GraphNode.prototype);
Graph.prototype.initOption = function (typ, value) {
	this.init = true;
	if (this.model.options.display.toLowerCase() === "html") {
		this.drawer = new Drawer.HTMLDrawer();
	} else {
		this.initDrawer("svg");
	}
	var i, layout = this.layouts[0];

	for (i = 0; i < this.layouts.length; i += 1) {
		if (this.layouts[i].name === this.model.options.layout.name.toLowerCase()) {
			layout = this.layouts[i];
			break;
		}
	}
	this.layouter = layout.value;
	if (this.model.options.canvasid) {
		this.root = document.getElementById(this.model.options.canvasid);
	}
	if (this.root) {
		if (this.model.options.clearCanvas) {
			for (i = this.root.children.length - 1; i >= 0; i -= 1) {
				this.root.removeChild(this.root.children[i]);
			}
		}
	} else {
		this.root = document.createElement("div");
		if (this.model.options.canvasid) {
			this.root.id = this.model.options.canvasid;
		}
		document.body.appendChild(this.root);
	}
};
Graph.prototype.addOption = function (typ, value) {
	this.model.options[typ] = value;
	this.init = false;
};
Graph.prototype.initLayouts = function () { this.layouts = [{name: "dagre", value: new DagreLayout()}]; };
Graph.prototype.initInfo = function (edge, info) {
	if (!this.model.options.CardinalityInfo && !this.model.options.propertyinfo) {
		return null;
	}
	var infoTxt = edge.getInfo(info);
	if (infoTxt.length > 0) {
		this.sizeHTML(this.drawer.getInfo(info, infoTxt, 0), info);
	}
	return infoTxt;
};
Graph.prototype.clearBoard = function (onlyElements) {
	var i, n;
	if (this.board) {
		this.clearLines(this.model);
		for (i in this.model.nodes) {
			if (!this.model.nodes.hasOwnProperty(i)) {
				continue;
			}
			n = this.model.nodes[i];
			if (this.board.children.length > 0) {
				n.removeFromBoard(this.board);
			}
			n.$RIGHT = n.$LEFT = n.$UP = n.$DOWN = 0;
		}
		if (!onlyElements) {
			this.root.removeChild(this.board);
		}
	}
	if (!onlyElements && this.drawer) {
		this.drawer.clearBoard();
	}
};
Graph.prototype.addNode = function (node) {return this.model.addNode(node); };
Graph.prototype.addEdge = function (source, target) {return this.model.addEdge(source, target); };
Graph.prototype.removeNode = function (id) {return this.model.removeNode(id); };
Graph.prototype.calcLines = function (model) {
	model = model || this.model;
	model.calcLines(this.drawer);
};
Graph.prototype.drawLines = function (model) {
	this.clearLines(model);
	var i, e, startShow, endShow, items = [], id;
	for (i = 0; i < model.edges.length; i += 1) {
		e = model.edges[i];
		startShow = !e.$sNode.isClosed();
		endShow = !e.$tNode.isClosed();
		if (startShow && endShow) {
			e.draw(this.board, this.drawer);
		} else if ((startShow && !endShow) || (!startShow && endShow)) {
			id = e.$sNode.getShowed().id + "-" + e.$tNode.getShowed().id;
			if (items.indexOf(id) < 0) {
				items.push(id);
				e.draw(this.board, this.drawer);
			}
		}
	}
};
Graph.prototype.clearLines = function (model) {
	var i;
	for (i = 0; i < model.edges.length; i += 1) {
		model.edges[i].removeFromBoard(this.board);
	}
};
Graph.prototype.MinMax = function (node, min, max) {
	max.x = Math.max(max.x, node.x + Number(node.width) + 10);
	max.y = Math.max(max.y, node.y + Number(node.height) + 10);
	min.x = Math.max(min.x, node.x);
	min.y = Math.max(min.y, node.y);
};
Graph.prototype.resize = function (model) {
	var nodes, n, max, i, min = new Pos();
	max = new Pos(model.minSize.x, model.minSize.y);
	nodes = model.nodes;
	for (i in nodes) {
		if (!nodes.hasOwnProperty(i) || typeof (nodes[i]) === "function") {
			continue;
		}
		n = nodes[i];
		this.moveToRaster(n);
		this.MinMax(n, min, max);
	}
	this.calcLines(model);
	for (i = 0; i < model.edges.length; i += 1) {
		n = model.edges[i];
		this.MinMax(n.source, min, max);
		this.MinMax(n.target, min, max);
	}
	model.height = max.y;
	model.width = max.x;
	this.drawer.setSize(model.$gui, max.x, max.y);
	if (model.options.raster) {
		this.drawRaster();
	}
	this.drawLines(model);
	return max;
};
Graph.prototype.drawRaster = function () {
	var width, height, line, i;
	while (this.board.rasterElements.length > 0) {
		this.board.removeChild(this.board.rasterElements.pop());
	}
	width = this.board.style.width.replace("px", "");
	height = this.board.style.height.replace("px", "");

	for (i = 10; i < width; i += 10) {
		line = this.drawer.getLine(i, 0, i, height, null, "#ccc");
		line.setAttribute("className", "lineRaster");
		this.board.rasterElements.push(line);
		this.board.appendChild(line);
	}
	for (i = 10; i < height; i += 10) {
		line = this.drawer.getLine(0, i, width, i, null, "#ccc");
		line.setAttribute("className", "lineRaster");
		this.board.rasterElements.push(line);
		this.board.appendChild(line);
	}
};
Graph.prototype.draw = function (model, width, height) {
	var i, n, nodes = model.nodes;
	if (model.options.addBorder) {
		for (i in nodes) {
			if (!nodes.hasOwnProperty(i) || typeof (nodes[i]) === "function") {
				continue;
			}
			n = nodes[i];
			if (model.left > 0 || model.top > 0) {
				n.x += model.left;
				n.y += model.top;
			}
		}
		model.options.addBorder = false;
	}
	model.minSize = new Pos(width || 0, height || 0);
	if (this.loader.abort && this.loader.images.length > 0) {
		return;
	}
	this.resize(model);
	for (i in nodes) {
		if (!nodes.hasOwnProperty(i) || typeof (nodes[i]) === "function") {
			continue;
		}
		n = nodes[i];
		n.$gui = this.drawer.getNode(n, true);
		model.$gui.appendChild(n.$gui);
	}
};
Graph.prototype.moveToRaster = function (node) {
	if (this.model.options.raster) {
		node.x = parseInt(node.x / 10, 10) * 10;
		node.y = parseInt(node.y / 10, 10) * 10;
		if (node.$gui) {
			this.drawer.setPos(node.$gui, node.x, node.y);
		}
	}
};
Graph.prototype.initGraph = function (model) {
	var i, n, isDiag, html, e;
	model.validateModel();
	for (i in model.nodes) {
		if (typeof (model.nodes[i]) === "function") {
			continue;
		}
		n = model.nodes[i];
		isDiag = n.typ.indexOf("diagram", n.typ.length - 7) !== -1;
		if (isDiag) {
			this.initGraph(n);
		}
		html = this.drawer.getNode(n);
		if (html) {
			this.sizeHTML(html, n);
			if (isDiag) {
				n.$center = new Pos(n.x + (n.width / 2), n.y + (n.height / 2));
			}
		}
	}
	for (i = 0; i < model.edges.length; i += 1) {
		e = model.edges[i];
		this.initInfo(e, e.source);
		this.initInfo(e, e.target);
	}
};
Graph.prototype.layout = function (minwidth, minHeight, model) {
	if (!this.init) {
		this.initOption();
	}
	if (model) {
		this.initGraph(model);
	} else {
		model = this.model;
		this.initDrawer();
		this.initGraph(model);
	}
	if (this.loader.images.length < 1) {
		this.layouter.layout(this, model, Math.max(minwidth || 0, 100), Math.max(minHeight || 0, 100));
	} else {
		this.loader.width = minwidth;
		this.loader.height = minHeight;
	}
};
Graph.prototype.createElement = function (element, typ, node) {
	var that = this;
	element.node = node;
	this.bind(element, "mousedown", function (e) {that.startDrag(e); });
};
Graph.prototype.appendImage = function (img) {
	this.loader.add(img);
};
//				######################################################### DRAG AND DROP #########################################################
Graph.prototype.initDragAndDrop = function () {
	this.objDrag = null;
	this.mouse = new Pos();
	this.offset = new Pos();
	this.startObj = new Pos();
	var that = this;
	this.bind(this.board, "mousemove", function (e) {that.doDrag(e); });
	this.bind(this.board, "mouseup", function (e) {that.stopDrag(e); });
	this.bind(this.board, "mouseout", function (e) {that.stopDrag(e); });
};
Graph.prototype.setSelectable = function (node, value) {
	if (node.nodeType === 1) {
		if (value) {
			node.setAttribute("unselectable", value);
		} else {
			node.removeAttribute("unselectable");
		}
	}
	var child = node.firstChild;
	while (child) {
		this.setSelectable(child, value);
		child = child.nextSibling;
	}
};
Graph.prototype.getDragNode = function (node) {
	if (node.model) {
		if (!node.model.$isdraggable) {
			return null;
		}
		return node;
	}
	if (node.parentElement.model) {
		if (!node.parentElement.model.$isdraggable) {
			return null;
		}
		return node.parentElement;
	}
	return null;
};
Graph.prototype.startDrag = function (event) {
	var graph, i, n = this.getDragNode(event.currentTarget);
	if (!n) {
		return;
	}
	if (this.objDrag) {
		return;
	}
	this.objDrag = n;
	graph = this.objDrag.parentElement;
	if (graph) {
		for (i = 0; i < graph.children.length; i += 1) {
			this.setSelectable(graph.children[i], "on");
		}
	}
	this.offset.x = this.isIE ? window.event.clientX : event.pageX;
	this.offset.y = this.isIE ? window.event.clientY : event.pageY;
	this.startObj.x = this.objDrag.model.x;
	this.startObj.y = this.objDrag.model.y;
};
Graph.prototype.doDrag = function (event) {
	var x, y;
	this.mouse.x = this.isIE ? window.event.clientX : event.pageX;
	this.mouse.y = this.isIE ? window.event.clientY : event.pageY;

	if (this.objDrag !== null) {
		x = (this.mouse.x - this.offset.x) + this.startObj.x;
		y = (this.mouse.y - this.offset.y) + this.startObj.y;

		if (this.model.options.display === "svg") {
			x = x - this.startObj.x;
			y = y - this.startObj.y;
			this.objDrag.setAttribute('transform', "translate("  + x + " "  + y + ")");
		} else {
			this.drawer.setPos(this.objDrag, x, y);
			if (this.objDrag.model) {
				this.objDrag.model.x = x;
				this.objDrag.model.y = y;
				this.objDrag.model.$parent.resize(this.model);
			}
		}
	}
};
Graph.prototype.stopDrag = function (event) {
	var x, y, z, item, entry, parent, pos;
	if (!this.objDrag) {
		return;
	}
	if (!(event.type === "mouseup" || event.type === "mouseout") && !event.currentTarget.isdraggable) {
		return;
	}
	if (event.type === "mouseout") {
		x = this.isIE ? window.event.clientX : event.pageX;
		y = this.isIE ? window.event.clientY : event.pageY;
		if (x < this.board.offsetWidth && y < this.board.offsetHeight) {
			return;
		}
	}
	item = this.objDrag;
	this.objDrag = null;
	entry = item.parentElement;
	if (entry) {
		for (z = 0; z < entry.children.length; z += 1) {
			this.setSelectable(entry.children[z], null);
		}
	}
	parent = item.parentElement;
	if (item.model) {
		if (this.model.options.display === "svg") {
			if (item.getAttributeNS(null, "transform")) {
				z = item.getAttributeNS(null, "transform");
				if (z.substring(0, 6) !== "rotate") {
					pos = z.slice(10, -1).split(' ');
					item.model.x = item.model.x + Number(pos[0]);
					item.model.y = item.model.y + Number(pos[1]);
				}
			}
			item.model.$center = new Pos(item.model.x + (item.model.width / 2), item.model.y + (item.model.height / 2));
			parent.removeChild(item);
			if (item.model.board) {
				item.model.board = null;
			}
		} else {
			this.board.removeChild(item);
		}

		if (item.model.typ === "Info") {
			item.model.custom = true;
			item.model.$edge.removeElement(item);
			entry = item.model.$edge.getInfo(item.model);
			item.model.$edge.drawText(this.board, this.drawer, entry, item.model);
		} else {
			item.model.$gui = this.drawer.getNode(item.model, true);
			if (item.model.$gui) {
				parent.appendChild(item.model.$gui);
			}
			entry = item.model.getEdges();
			for (z = 0; z < entry.length; z += 1) {
				entry[z].source.custom = false;
				entry[z].target.custom = false;
			}
		}
		parent = item.model.$parent;
		entry = parent;
		while (entry) {
			this.resize(entry);
			entry = entry.$parent;
		}
		if (parent.$parent) {
			this.redrawNode(parent, true);
			this.resize(this.model);
		} else {
			this.resize(parent);
		}
	}
};
Graph.prototype.redrawNode = function (node, draw) {
	var infoTxt, parent = node.$gui.parentElement;
	parent.removeChild(node.$gui);
	if (node.board) {
		node.board = null;
	}
	if (node.typ === "Info") {
		infoTxt = node.edge.getInfo(node.node);
		node.edge.drawText(this.board, this.drawer, infoTxt, node.node);
	} else {
		node.$gui = this.drawer.getNode(node, draw);
		if (node.$gui) {
			parent.appendChild(node.$gui);
		}
	}
	node.$center = new Pos(node.x + (node.width / 2), node.y + (node.height / 2));
	this.resize(node.$parent);
};
Graph.prototype.initDrawer = function (typ) {
	if (typ) {
		typ = typ.toLowerCase();
		if (this.model.options.display === typ) {
			return;
		}
		this.model.options.display = typ;
	} else {
		typ = this.model.options.display;
	}
	this.clearBoard();
	if (typ === "html") {
		this.drawer = new Drawer.HTMLDrawer();
	} else if (typ === "svg") {
		this.drawer = new Drawer.SVGDrawer();
	}
	this.board = this.drawer.getBoard(this);
	this.model.$gui = this.board;
	this.initDragAndDrop();
	this.root.appendChild(this.board);
};
Graph.prototype.serializeXmlNode = function (xmlNode) {
	if (window.XMLSerializer !== undefined) {
		return (new window.XMLSerializer()).serializeToString(xmlNode);
	}
	if (xmlNode.xml !== undefined) {
		return xmlNode.xml;
	}
	return xmlNode.outerHTML;
};
Graph.prototype.utf8$to$b64 = function (str) {
	return window.btoa(unescape(encodeURIComponent(str)));
};
Graph.prototype.ExportPDF = function () {
	var converter, pdf = new jsPDF('l', 'px', 'a4');
	converter = new svgConverter(this.board, pdf, {removeInvalid: false});
	pdf.save('Download.pdf');
};
Graph.prototype.ExportEPS = function () {
	var converter, doc = new svgConverter.jsEPS({inverting: true});
	converter = new svgConverter(this.board, doc, {removeInvalid: false});
	doc.save();
};
Graph.prototype.ExportPNG = function () {
	var canvas, context, a, image = new Image();
	image.src = 'data:image/svg+xml;base64,' + this.utf8$to$b64(this.serializeXmlNode(this.board));
	image.onload = function () {
		canvas = document.createElement('canvas');
		canvas.width = image.width;
		canvas.height = image.height;
		context = canvas.getContext('2d');
		context.drawImage(image, 0, 0);
		a = document.createElement('a');
		a.download = "download.png";
		a.href = canvas.toDataURL('image/png');
		a.click();
	};
};
Graph.prototype.SaveAs = function (typ) {
	typ = typ.toLowerCase();
	if (typ === "svg") {
		this.Save("image/svg+xml", this.serializeXmlNode(this.board), "download.svg");
	} else if (typ === "html") {
		this.ExportHTML();
	} else if (typ === "png") {
		this.ExportPNG();
	} else if (typ === "pdf") {
		this.ExportPDF();
	} else if (typ === "eps") {
		this.ExportEPS();
	}
};
Graph.prototype.SavePosition = function () {
	var data = [], node, id;
	for (id in this.model.nodes) {
		node = this.model.nodes[id];
		data.push({id: node.id, x: node.x, y: node.y});
	}
	if (window.localStorage && this.model.id) {
		window.localStorage.setItem(this.model.id, JSON.stringify(data));
	}
};
Graph.prototype.LoadPosition = function () {
	if (this.model.id && window.localStorage) {
		var node, id, data = window.localStorage.getItem(this.model.id);
		if (data) {
			data = JSON.parse(data);
			for (id in data) {
				node = data[id];
				if (this.model.nodes[node.id]) {
					this.model.nodes[node.id].x = node.x;
					this.model.nodes[node.id].y = node.y;
				}
			}
			this.clearBoard(true);
			this.draw(this.model);
		}
	}
};
Graph.prototype.Save = function (typ, data, name) {
	var a = document.createElement("a");
	a.href = window.URL.createObjectURL(new Blob([data], {type: typ}));
	a.download = name;
	a.click();
};
Graph.prototype.ExportHTML = function () {
	var data, json = this.model.toJson();
	data = "<html><head>" + document.head.innerHTML.trim() + "</head><body><script>"
		+ "new Graph("  + JSON.stringify(json, null, "\t") + ").layout();<" + "/script></body></html>";
	this.Save("text/json", data, "download.html");
};
//				######################################################### GraphLayout-Dagre #########################################################
var DagreLayout = function () {};
DagreLayout.prototype.layout = function (graph, node, width, height) {
	var layoutNode, i, n, nodes, g, graphOptions = node.copy({directed: false}, node.options.layout);
	g = new dagre.graphlib.Graph(graphOptions);
	g.setGraph(graphOptions);
	g.setDefaultEdgeLabel(function () { return {}; });
	nodes = node.nodes;
	for (i in nodes) {
		if (!nodes.hasOwnProperty(i) || typeof (nodes[i]) === "function") {
			continue;
		}
		n = nodes[i];
		g.setNode(n.id, {label: n.id, width: n.width, height: n.height, x: n.x, y: n.y});
	}
	for (i = 0; i < node.edges.length; i += 1) {
		n = node.edges[i];
		g.setEdge(this.getRootNode(n.$sNode).id, this.getRootNode(n.$tNode).id);
	}
	dagre.layout(g);
	// Set the layouting back
	for (i in nodes) {
		if (!nodes.hasOwnProperty(i) || typeof (nodes[i]) === "function") {
			continue;
		}
		n = nodes[i];
		layoutNode = g.node(n.id);
		if (n.x < 1 && n.y < 1) {
			n.x = Math.round(layoutNode.x - (n.width / 2));
			n.y = Math.round(layoutNode.y - (n.height / 2));
		}
	}
	graph.draw(node, width, height);
};
DagreLayout.prototype.getRootNode = function (node, child) {
	if (node.$parent) {
		return this.getRootNode(node.$parent, node);
	}
	if (!child) {
		return node;
	}
	return child;
};
//				######################################################### Loader #########################################################
GraphUtil.Loader = function (graph) {this.images = []; this.graph = graph; this.abort = false; };
GraphUtil.Loader.prototype.execute = function () {
	if (this.images.length === 0) {
		this.graph.layout(this.width, this.height);
	} else {
		var img = this.images[0];
		this.graph.root.appendChild(img);
	}
};
GraphUtil.Loader.prototype.onLoad = function (e) {
	var idx, img = e.target;
	idx = this.images.indexOf(img);
	img.model.width = img.width;
	img.model.height = img.height;
	this.graph.root.removeChild(img);
	if (idx !== -1) {
		this.images.splice(idx, 1);
	}
	this.execute();
};
GraphUtil.Loader.prototype.add = function (img) {
	//img.crossOrigin = 'anonymous';
	var that = this, func = function (e) {that.onLoad(e); };
	this.graph.bind(img, "load", func);
	this.images.push(img);
	this.execute();
};
//				######################################################### LINES #########################################################
var Edge = function () {this.init(); this.typ = "EDGE"; };
Edge.prototype.init = function () {
	this.$path = [];
	this.$sNode = null;
	this.$tNode = null;
	this.$gui = [];
	this.$m = 0;
	this.$n = 0;
	this.$lineStyle = GraphUtil.Line.Format.SOLID;
};
Edge.Layout = { DIAG : 1, RECT : 0 };
Edge.Position = {UP: "UP", LEFT: "LEFT", RIGHT: "RIGHT", DOWN: "DOWN"};
Edge.prototype.set = function (id, value) {if (value) {this[id] = value; } };
Edge.prototype.addElement = function (board, element) {
	if (element) {this.$gui.push(element); board.appendChild(element); }
};
Edge.prototype.removeElement = function (element) {
	if (element) {
		var i;
		for (i = 0; i < this.$gui.length; i += 1) {
			if (this.$gui[i] === element) {
				this.$gui.splice(i, 1);
				i -= 1;
			}
		}
	}
};
Edge.prototype.removeFromBoard = function (board) {
	if (this.$gui) {
		while (this.$gui.length > 0) {
			board.removeChild(this.$gui.pop());
		}
	}
};
// TODO
// many Edges SOME DOWN AND SOME RIGHT OR LEFT
// INFOTEXT DONT SHOW IF NO PLACE
// INFOTEXT CALCULATE POSITION
Edge.prototype.calculate = function () {
	var result, options, linetyp, source, target, sourcePos, targetPos, divisor, startNode, endNode;

	startNode = this.$sNode.getShowed();
	endNode = this.$tNode.getShowed();

	startNode.$center = new Pos(startNode.getX() + (startNode.width / 2), startNode.getY() + (startNode.height / 2));
	endNode.$center = new Pos(endNode.getX() + (endNode.width / 2), endNode.getY() + (endNode.height / 2));

	divisor = (endNode.$center.x - startNode.$center.x);
	this.$path = [];

	source = this.getTarget(startNode, startNode);
	target = this.getTarget(endNode, endNode);
	if (divisor === 0) {
		if (startNode === endNode) {
			/* OwnAssoc */
			return false;
		}
		// Must be UP_DOWN or DOWN_UP
		if (startNode.$center.y < endNode.$center.y) {
			// UP_DOWN
			sourcePos = this.getCenterPosition(source, Edge.Position.DOWN);
			targetPos = this.getCenterPosition(target, Edge.Position.UP);
		} else {
			sourcePos = this.getCenterPosition(source, Edge.Position.UP);
			targetPos = this.getCenterPosition(target, Edge.Position.DOWN);
		}
	} else {
		// add switch from option or model
		linetyp = this.linetyp;
		if (!linetyp) {
			options = this.$parent.options;
			if (options) {
				linetyp = options.linetyp;
			}
		}
		result = false;
		if (linetyp === "square") {
			result = this.calcSquareLine();
		}
		if (!result) {
			this.$m = (target.$center.y - source.$center.y) / divisor;
			this.$n = source.$center.y - (source.$center.x * this.$m);
			sourcePos = this.getPosition(this.$m, this.$n, source, target.$center);
			targetPos = this.getPosition(this.$m, this.$n, target, sourcePos);
		}
	}
	if (sourcePos && targetPos) {
		this.calcInfoPos(sourcePos, source, this.source);
		this.calcInfoPos(targetPos, target, this.target);
		source["$" + sourcePos.$id] += 1;
		target["$" + targetPos.$id] += 1;
		this.$path.push(new GraphUtil.Line(sourcePos, targetPos, this.$lineStyle, this.style));
		if (this.info) {
			this.info.x = (sourcePos.x + targetPos.x) / 2;
			this.info.y = (sourcePos.y + targetPos.y) / 2;
		}
	}
	return true;
};
Edge.prototype.addLine = function (x1, y1, x2, y2) {
	var start, end;
	if (!x2 && !y2 && this.$path.length > 0) {
		start = this.$path[this.$path.length - 1].target;
		end = new Pos(x1, y1);
	} else {
		start = new Pos(x1, y1);
		end = new Pos(x2, y2);
	}
	this.$path.push(new GraphUtil.Line(start, end, this.$lineStyle, this.style));
};
Edge.prototype.addLineTo = function (x1, y1, x2, y2) {
	var start, end;
	if (!x2 && !y2 && this.$path.length > 0) {
		start = this.$path[this.$path.length - 1].target;
		end = new Pos(start.x + x1, start.y + y1);
	} else {
		start = new Pos(x1, y1);
		end = new Pos(start.x + x2, start.y + y2);
	}
	this.$path.push(new GraphUtil.Line(start, end, this.$lineStyle, this.style));
};
Edge.prototype.calcSquareLine = function () {
	//	1. Case		/------\
	//				|    T  |
	//				\-------/
	//			|---------|
	//			|
	//		/-------\
	//		|   S   |
	//		\-------/
	if (this.$sNode.y - 40 > this.$tNode.y + this.$tNode.height) { // oberseite von source and unterseite von target
		this.addLineTo(this.$sNode.x + this.$sNode.width / 2, this.$sNode.y, 0, -20);
		this.addLine(this.$tNode.x + this.$tNode.width / 2, this.$tNode.y + this.$tNode.height + 20);
		this.addLineTo(0, -20);
		return true;
	}
	if (this.$tNode.y - 40 > this.$sNode.y + this.$sNode.height) { // oberseite von source and unterseite von target
		// fall 1 nur andersherum
		this.addLineTo(this.$sNode.x + this.$sNode.width / 2, this.$sNode.y + this.$sNode.height, 0, +20);
		this.addLine(this.$tNode.x + this.$tNode.width / 2, this.$tNode.y - 20);
		this.addLineTo(0, 20);
		return true;
	}
	//3. fall ,falls s (source) komplett unter t (target) ist
	// beide oberseiten
	//	3. Case
	//			  |--------
	//			/---\	  |
	//			| T |	/---\
	//			\---/	| S |
	//					-----
	// or
	//			---------|
	//			|      /---\
	//		/----\    | T |
	//		| S  |    \---/
	//		------
	//
	this.addLineTo(this.$sNode.x + this.$sNode.width / 2, this.$sNode.y, 0, -20);
	this.addLine(this.$tNode.x + this.$tNode.width / 2, this.$tNode.y - 20);
	this.addLineTo(0, 20);
	return true;
//return false;
};
Edge.prototype.draw = function (board, drawer) {
	var i, style, item, angle;
	for (i = 0; i < this.$path.length; i += 1) {
		item = this.$path[i];
		style = item.style;
		this.addElement(board, drawer.getLine(item.source.x, item.source.y, item.target.x, item.target.y, item.line, style));
	}
	item = drawer.model.options;
	this.drawSourceText(board, drawer, style);
	if (this.info) {
		angle = this.drawText(board, drawer, this.info, this.infoPos);
		this.addElement(board, new SymbolLibary().create({typ: "Arrow", x: this.infoPos.x, y: this.infoPos.y, rotate: angle}, drawer));
	}
	this.drawTargetText(board, drawer, style);
};
Edge.prototype.drawText = function (board, drawer, text, pos, style) {
	if (this.$path.length < 1) {
		return;
	}
	if (text.length < 1) {
		return;
	}
	var options, angle, p = this.$path[this.$path.length - 1];
	options = drawer.model.model.options;
	if (options.rotatetext) {
		angle = Math.atan((p.source.y - p.target.y) / (p.source.x - p.target.x)) * 60;
	}
	this.addElement(board, drawer.getInfo(pos, text, angle, style));
	return angle;
};
Edge.prototype.drawSourceText = function (board, drawer, style) {
	var infoTxt = this.getInfo(this.source);
	this.drawText(board, drawer, infoTxt, this.source, style);
};
Edge.prototype.drawTargetText = function (board, drawer, style) {
	var infoTxt = this.getInfo(this.target);
	this.drawText(board, drawer, infoTxt, this.target, style);
};
Edge.prototype.endPos = function () {return this.$path[this.$path.length - 1]; };
Edge.prototype.edgePosition = function () {
	var pos = 0, i;
	for (i = 0; i < this.$sNode.$edges.length; i += 1) {
		if (this.$sNode.$edges[i] === this) {
			return pos;
		}
		if (this.$sNode.$edges[i].$tNode === this.$tNode) {
			pos += 1;
		}
	}
	return pos;
};
Edge.prototype.getTarget = function (node, startNode) {
	if (node instanceof GraphModel) {
		if (node.status === "close") {
			return node;
		}
		return startNode;
	}
	return this.getTarget(node.$parent, startNode);
};
Edge.prototype.getCenterPosition = function (node, pos) {
	var offset = node["$" + pos];
	if (pos === Edge.Position.DOWN) {
		return new Pos(Math.min(node.$center.x + offset, node.x + node.width), (node.y + node.height), Edge.Position.DOWN);
	}
	if (pos === Edge.Position.UP) {
		return new Pos(Math.min(node.$center.x + offset, node.x + node.width), node.y, Edge.Position.UP);
	}
	if (pos === Edge.Position.LEFT) {
		return new Pos(node.x, Math.min(node.$center.y + offset, node.y + node.height), Edge.Position.LEFT);
	}
	if (pos === Edge.Position.RIGHT) {
		return new Pos(node.x + node.width, Math.min(node.$center.y + offset, node.y + node.height), Edge.Position.RIGHT);
	}
};
Edge.prototype.getInfo = function (info) {
	var isProperty, isCardinality, infoTxt = "";
	isCardinality = this.$parent.typ === "classdiagram" && this.$parent.options.CardinalityInfo;
	isProperty = this.$parent.options.propertyinfo;

	if (isProperty && info.property) {
		infoTxt = info.property;
	}
	if (isCardinality && info.cardinality) {
		if (infoTxt.length > 0) {
			infoTxt += "\n";
		}
		if (info.cardinality.toLowerCase() === "one") {
			infoTxt += "0..1";
		} else if (info.cardinality.toLowerCase() === "many") {
			infoTxt += "0..*";
		}
	}
	if (info.edge && info.edge.counter && info.edge.counter > 0) {
		infoTxt += " (" + info.edge.counter + ")";
	}
	return infoTxt;
};
Edge.prototype.calcOwnEdge = function () {
	//this.source
	var sPos, tPos, offset = 20;
	this.$start = this.getFree(this.$sNode);
	if (this.$start.length > 0) {
		this.$end = this.getFreeOwn(this.$sNode, this.$start);
	} else {
		this.$start = Edge.Position.RIGHT;
		this.$end = Edge.Position.DOWN;
	}

	sPos = this.getCenterPosition(this.$sNode, this.$start);
	if (this.$start === Edge.Position.UP) {
		tPos = new Pos(sPos.x, sPos.y - offset);
	} else if (this.$start === Edge.Position.DOWN) {
		tPos = new Pos(sPos.x, sPos.y + offset);
	} else if (this.$start === Edge.Position.RIGHT) {
		tPos = new Pos(sPos.x + offset, sPos.y);
	} else if (this.$start === Edge.Position.LEFT) {
		tPos = new Pos(sPos.x - offset, sPos.y);
	}
	this.$path.push(new GraphUtil.Line(sPos, tPos, this.$lineStyle));
	if (this.$end === Edge.Position.LEFT || this.$end === Edge.Position.RIGHT) {
		if (this.$start === Edge.Position.LEFT) {
			sPos = tPos;
			tPos = new Pos(sPos.x, this.$sNode.y - offset);
			this.$path.push(new GraphUtil.Line(sPos, tPos, this.$lineStyle));
		} else if (this.$start === Edge.Position.RIGHT) {
			sPos = tPos;
			tPos = new Pos(sPos.x, this.$sNode.y + offset);
			this.$path.push(new GraphUtil.Line(sPos, tPos, this.$lineStyle));
		}
		sPos = tPos;
		if (this.$end === Edge.Position.LEFT) {
			tPos = new Pos(this.$sNode.x - offset, sPos.y);
		} else {
			tPos = new Pos(this.$sNode.x + this.$sNode.width + offset, sPos.y);
		}
		this.$path.push(new GraphUtil.Line(sPos, tPos, this.$lineStyle));
		sPos = tPos;
		tPos = new Pos(sPos.x, this.$sNode.$center.y);
		this.$path.push(new GraphUtil.Line(sPos, tPos, this.$lineStyle));
		if (this.info) {
			this.info.x = (sPos.x + tPos.x) / 2;
			this.info.y = sPos.y;
		}
	} else if (this.$end === Edge.Position.UP || this.$end === Edge.Position.DOWN) {
		if (this.$start === Edge.Position.UP) {
			sPos = tPos;
			tPos = new Pos(this.$sNode.x + this.$sNode.width + offset, sPos.y);
			this.$path.push(new GraphUtil.Line(sPos, tPos, this.$lineStyle));
		} else if (this.$start === Edge.Position.DOWN) {
			sPos = tPos;
			tPos = new Pos(this.$sNode.x - offset, sPos.y);
			this.$path.push(new GraphUtil.Line(sPos, tPos, this.$lineStyle));
		}
		sPos = tPos;
		if (this.$end === Edge.Position.UP) {
			tPos = new Pos(sPos.x, this.$sNode.y - offset);
		} else {
			tPos = new Pos(sPos.x, this.$sNode.y + this.$sNode.height + offset);
		}
		this.$path.push(new GraphUtil.Line(sPos, tPos, this.$lineStyle));
		sPos = tPos;
		tPos = new Pos(this.$sNode.$center.x, sPos.y);
		this.$path.push(new GraphUtil.Line(sPos, tPos, this.$lineStyle));
		if (this.info) {
			this.info.x = sPos.x;
			this.info.y = (sPos.y + tPos.y) / 2;
		}
	}
	sPos = tPos;
	this.$path.push(new GraphUtil.Line(sPos, this.getCenterPosition(this.$sNode, this.$end), this.$lineStyle));
};
Edge.prototype.getFree = function (node) {
	var i;
	for (i in Edge.Position) {
		if (node.hasOwnProperty("$" + i) && node["$" + i] === 0) {
			node["$" + i] = 1;
			return i;
		}
	}
	return "";
};
Edge.prototype.getFreeOwn = function (node, start) {
	var id = 0, i, list = [Edge.Position.UP, Edge.Position.RIGHT, Edge.Position.DOWN, Edge.Position.LEFT, Edge.Position.UP, Edge.Position.RIGHT, Edge.Position.DOWN];
	for (i = 0; i < list.length; i += 1) {
		if (list[i] === start) {
			id = i;
			break;
		}
	}
	if (node["$" + list[id + 1]] === 0 || node["$" + list[id + 1]] < node["$" + list[id + 3]]) {
		node["$" + list[id + 1]] += 1;
		return list[id + 1];
	}
	node["$" + list[id + 3]] += 1;
	return list[id + 3];
};
Edge.prototype.calcInfoPos = function (linePos, item, info) {
	// Manuell move the InfoTag
	var newY, newX, spaceA = 20, spaceB = 0, step = 15;
	if (item.$parent.options && !item.$parent.options.rotatetext) {
		spaceA = 20;
		spaceB = 10;
	}
	if (info.custom) {
		return;
	}
	newY = linePos.y;
	newX = linePos.x;
	if (linePos.$id === Edge.Position.UP) {
		newY = newY - info.height - spaceA;
		if (this.$m !== 0) {
			newX = (newY - this.$n) / this.$m + spaceB + (item.$UP * step);
		}
	} else if (linePos.$id === Edge.Position.DOWN) {
		newY = newY + spaceA;
		if (this.$m !== 0) {
			newX = (newY - this.$n) / this.$m + spaceB + (item.$DOWN * step);
		}
	} else if (linePos.$id === Edge.Position.LEFT) {
		newX = newX - info.width - (item.$LEFT * step) - spaceA;
		if (this.$m !== 0) {
			newY = (this.$m * newX) + this.$n;
		}
	} else if (linePos.$id === Edge.Position.RIGHT) {
		newX += (item.$RIGHT * step) + spaceA;
		if (this.$m !== 0) {
			newY = (this.$m * newX) + this.$n;
		}
	}
	info.x = Math.round(newX);
	info.y = Math.round(newY);
};
Edge.prototype.getUDPosition = function (m, n, e, pos, step) {
	var x, y = e.getY();
	if (pos === Edge.Position.DOWN) {
		y += e.height;
	}
	x = (y - n) / m;
	if (step) {
		x += e["$" + pos] * step;
		if (x < e.getX()) {
			x = e.getX();
		} else if (x > (e.getX() + e.width)) {
			x = e.getX() + e.width;
		}
	}
	return new Pos(x, y, pos);
};
Edge.prototype.getLRPosition = function (m, n, e, pos, step) {
	var y, x = e.getX();
	if (pos === Edge.Position.RIGHT) {
		x += e.width;
	}
	y = m * x + n;
	if (step) {
		y += e["$" + pos] * step;
		if (y < e.getY()) {
			y = e.getY();
		} else if (y > (e.getY() + e.height)) {
			y = e.getY() + e.height;
		}
	}
	return new Pos(x, y, pos);
};
Edge.prototype.getPosition = function (m, n, entity, refCenter) {
	var t, pos = [], list, distance = [], min = 999999999, position, i, step = 15;
	list = [Edge.Position.LEFT, Edge.Position.RIGHT];
	for (i = 0; i < 2; i += 1) {
		t = this.getLRPosition(m, n, entity, list[i]);
		if (t.y >= entity.getY() && t.y <= (entity.getY() + entity.height)) {
			t.y += (entity["$" + list[i]] * step);
			if (t.y > (entity.getY() + entity.height)) {
				// Alternative
				t = this.getUDPosition(m, n, entity, Edge.Position.DOWN, step);
			}
			pos.push(t);
			distance.push(Math.sqrt((refCenter.x - t.x) * (refCenter.x - t.x) + (refCenter.y - t.y) * (refCenter.y - t.y)));
		}
	}
	list = [Edge.Position.UP, Edge.Position.DOWN];
	for (i = 0; i < 2; i += 1) {
		t = this.getUDPosition(m, n, entity, list[i]);
		if (t.x >= entity.getX() && t.x <= (entity.getX() + entity.width)) {
			t.x += (entity["$" + list[i]] * step);
			if (t.x > (entity.getX() + entity.width)) {
				// Alternative
				t = this.getLRPosition(m, n, entity, Edge.Position.RIGHT, step);
			}
			pos.push(t);
			distance.push(Math.sqrt((refCenter.x - t.x) * (refCenter.x - t.x) + (refCenter.y - t.y) * (refCenter.y - t.y)));
		}
	}
	for (i = 0; i < pos.length; i += 1) {
		if (distance[i] < min) {
			min = distance[i];
			position = pos[i];
		}
	}
	return position;
};
Edge.prototype.calcMoveLine = function (size, angle, move) {
	var lineangle, angle1, angle2, hCenter, startArrow, h;
	if (this.$path.length < 1) {
		return;
	}
	startArrow = this.endPos().source;
	this.$end = this.endPos().target;
	// calculate the angle of the line
	lineangle = Math.atan2(this.$end.y - startArrow.y, this.$end.x - startArrow.x);
	// h is the line length of a side of the arrow head
	h = Math.abs(size / Math.cos(angle));
	angle1 = lineangle + Math.PI + angle;
	hCenter = Math.abs((size / 2) / Math.cos(angle));

	this.$top = new Pos(this.$end.x + Math.cos(angle1) * h, this.$end.y + Math.sin(angle1) * h);
	this.$topCenter = new Pos(this.$end.x + Math.cos(angle1) * hCenter, this.$end.y + Math.sin(angle1) * hCenter);
	angle2 = lineangle + Math.PI - angle;
	this.$bot = new Pos(this.$end.x + Math.cos(angle2) * h, this.$end.y + Math.sin(angle2) * h);
	this.$botCenter = new Pos(this.$end.x + Math.cos(angle2) * hCenter, this.$end.y + Math.sin(angle2) * hCenter);
	if (move) {
		this.endPos().target = new Pos((this.$top.x + this.$bot.x) / 2, (this.$top.y + this.$bot.y) / 2);
	}
};
var Generalisation = function () { this.init(); this.typ = "Generalisation"; };
Generalisation.prototype = ObjectCreate(Edge.prototype);
Generalisation.prototype.calculateEdge = Generalisation.prototype.calculate;
Generalisation.prototype.calculate = function (board, drawer) {
	if (!this.calculateEdge(board, drawer)) {
		return false;
	}
	this.calcMoveLine(16, 50, true);
	return true;
};
Generalisation.prototype.drawSuper = Generalisation.prototype.draw;
Generalisation.prototype.draw = function (board, drawer) {
	this.drawSuper(board, drawer);
	if (this.$path.length > 0) {
		this.addElement(board, drawer.getLine(this.$top.x, this.$top.y, this.$end.x, this.$end.y, this.$lineStyle));
		this.addElement(board, drawer.getLine(this.$bot.x, this.$bot.y, this.$end.x, this.$end.y, this.$lineStyle));
		this.addElement(board, drawer.getLine(this.$top.x, this.$top.y, this.$bot.x, this.$bot.y, this.$lineStyle));
	}
};
Generalisation.prototype.drawSourceText = function (board, drawer, style) {};
Generalisation.prototype.drawTargetText = function (board, drawer, style) {};

var Implements = function () { this.init(); this.typ = "Implements"; this.$lineStyle = GraphUtil.Line.Format.DOTTED; };
Implements.prototype = ObjectCreate(Generalisation.prototype);

var Unidirectional = function () { this.init(); this.typ = "Unidirectional"; };
Unidirectional.prototype = ObjectCreate(Generalisation.prototype);
Unidirectional.prototype.calculate = function (board, drawer) {
	if (!this.calculateEdge(board, drawer)) {
		return false;
	}
	this.calcMoveLine(16, 50, false);
	return true;
};
Unidirectional.prototype.draw = function (board, drawer) {
	this.drawSuper(board, drawer);
	this.addElement(board, drawer.getLine(this.$top.x, this.$top.y, this.$end.x, this.$end.y, this.$lineStyle));
	this.addElement(board, drawer.getLine(this.$bot.x, this.$bot.y, this.$end.x, this.$end.y, this.$lineStyle));
};
var Aggregation = function () { this.init(); this.typ = "Aggregation"; };
Aggregation.prototype = ObjectCreate(Generalisation.prototype);
Aggregation.prototype.calculate = function (board, drawer) {
	if (!this.calculateEdge(board, drawer)) {
		return false;
	}
	this.calcMoveLine(16, 49.8, true);
	return true;
};
Aggregation.prototype.draw = function (board, drawer) {
	this.drawSuper(board, drawer);
	this.addElement(board, drawer.createPath(true, "none", [this.endPos().target, this.$topCenter, this.$end, this.$botCenter]));
};
var Composition = function () { this.init(); this.typ = "Composition"; };
Composition.prototype = ObjectCreate(Aggregation.prototype);
Composition.prototype.draw = function (board, drawer) {
	this.drawSuper(board, drawer);
	var lineangle, start = this.$path[0].source;
	lineangle = Math.atan2(this.$end.y - start.y, this.$end.x - start.x);
	this.addElement(board, drawer.createPath(true, "#000", [this.endPos().target, this.$topCenter, this.$end, this.$botCenter], lineangle));
};
// TODO
// Validate input
// Create Assocs
// Edit Assocs
// Delete Assocs
// Edit Attribute and Methods
// ################################## ClassEditor ####################################################
var ClassEditor = function (element, diagramTyp) {
	var parent, that = this, i;
	this.isIE = document.all && !window.opera;
	this.drawer = new Drawer.HTMLDrawer();
	this.inputEvent = true;
	this.nodes = {};
	this.noButtons = true;
	this.model = new GraphModel(this, {buttons: [], typ: diagramTyp});
	if (element) {
		if (typeof (element) === "string") {
			this.board = this.drawer.getBoard(this);
			this.board.className = "ClassEditor";
			parent = document.getElementById(element);
			if (!parent) {
				document.body.appendChild(this.board);
			} else {
				for (i = parent.children.length - 1; i >= 0; i -= 1) {
					parent.removeChild(parent.children[i]);
				}
				parent.appendChild(this.board);
				parent.style.height = "100%";
				parent.style["min-height"] = "";
				parent.style["min-width"] = "";
				parent.style.width = "100%";
			}
		} else {
			this.board = element;
		}
	} else {
		this.board = document.body;
	}
	this.inputNode = new ClassEditor.InputNode(this);
	this.editNode = new ClassEditor.EditNode(this);
	this.createEdge = new ClassEditor.CreateEdge(this);
	this.actions = [ new ClassEditor.Selector(this), new ClassEditor.MoveNode(this), this.createEdge, new ClassEditor.CreateNode(this)];

	this.bind(this.board, "mousedown", function (e) {that.doAction(e, "startAction"); });
	this.bind(this.board, "mousemove", function (e) {that.doAction(e, "doAction"); });
	this.bind(this.board, "mouseup", function (e) {that.doAction(e, "stopAction"); });
	this.bind(this.board, "mouseout", function (e) {that.doAction(e, "outAction"); });
	this.bind(this.board, "dragover", function (e) {that.dragClass(e); });
	this.bind(this.board, "dragleave", function (e) {that.dragClass(e); });
	this.bind(this.board, "drop", function (e) {that.dropModel(e); });
	this.loadModel(this.model);
};
ClassEditor.prototype = ObjectCreate(GraphUtil.prototype);
ClassEditor.prototype.dragStyler = function (e, typ) {
	e.stopPropagation();
	e.preventDefault();
	this.setBoardStyle(typ);
};
ClassEditor.prototype.setBoardStyle = function (typ) {
	var b = this.board;
	this.removeClass(b, "Error");
	this.removeClass(b, "Ok");
	this.removeClass(b, "Add");
	if (typ === "dragleave") {
		if (b.errorText) {
			b.removeChild(b.errorText);
			b.errorText = null;
		}
		return true;
	}
	this.addClass(b, typ);
	if (typ === "Error") {
		if (!b.errorText) {
			b.errorText = this.create({tag: "div", style: "margin-top: 30%", value: "NO TEXTFILE"});
			b.appendChild(b.errorText);
		}
		return true;
	}
	return false;
};
ClassEditor.prototype.dragClass = function (e) {
	if (this.dragStyler(e, e.type)) {
		return;
	}
	if (e.target !== this.board) {
		return;
	}
	var error = true, n, i, f, files = e.target.files || e.dataTransfer.files;
	// process all File objects
	if (!files || files.length < 1) {
		return;
	}
	for (i = 0; i < files.length; i += 1) {
		f = files[i];
		if (f.type.indexOf("text") === 0) {
			error = false;
		} else if (f.type === "") {
			n = f.name.toLowerCase();
			if (n.indexOf("json", n.length - 4) !== -1) {
				error = false;
			}
		}
	}
	if (error) {
		this.dragStyler(e, "Error");
	} else if (e.ctrlKey) {
		this.dragStyler(e, "Add");
	} else {
		this.dragStyler(e, "Ok");
	}
};
ClassEditor.prototype.dropFile =  function (content, file) {
	this.loadModel(JSON.parse(content), false, file);
};
ClassEditor.prototype.dropModel = function (e) {
	var i, n, f, files, x, y, that = this, func, data, load, reader;
	this.dragStyler(e, "dragleave");

	data = e.dataTransfer.getData("Text");
	if (data) {
		x = this.getEventX(e);
		y = this.getEventY(e);
		this.getAction("CreateNode").setValue(x, y, x + 100, y + 100);
		return;
	}

	files = e.target.files || e.dataTransfer.files;
	func = function (r) { that.loadModel(JSON.parse(r.target.result), e.ctrlKey, f); };
	for (i = 0; i < files.length; i += 1) {
		f = files[i];
		load = f.type.indexOf("text") === 0;
		if (!load && f.type === "") {
			n = f.name.toLowerCase();
			if (n.indexOf("json", n.length - 4) !== -1) {
				load = true;
			}
		}
		if (load) {
			e.stopPropagation();
			// file.name
			reader = new FileReader();
			reader.onload = func;
			reader.readAsText(f);
			break;
		}
	}
};
ClassEditor.prototype.download = function (typ, data, name) {
	var a = document.createElement("a");
	a.href = window.URL.createObjectURL(new Blob([data], {type: typ}));
	a.download = name;
	a.click();
};
ClassEditor.prototype.save = function () {
	var data, hasJava, result = {};
	this.copy(result, this.model);
	data = JSON.stringify(result, null, "\t");
	hasJava = typeof (java);
	if (hasJava !== 'undefined') {
		java.save(data);
	} else {
		this.download("text/json", data, "model.json");
	}
};
ClassEditor.prototype.generate = function () {
	var data, result = this.minJson({}, this.model);
	data = JSON.stringify(result, null, "\t");
	java.generate(data);
};
ClassEditor.prototype.close = function () {
	java.exit();
};
ClassEditor.prototype.loadModel = function (model, addFile, file) {
	var i, that = this;
	if (!addFile) {
		this.model = new GraphModel(that, {buttons: []});
		//this.model = that.copy(newModel, model);
	}
	this.getAction("Selector").setNode(null);
	for (i = this.board.children.length - 1; i >= 0; i -= 1) {
		this.board.removeChild(this.board.children[i]);
	}
	for (i in this.model.nodes) {
		this.addNode(this.model.nodes[i]);
	}
	for (i in model.nodes) {
		this.addNode(model.nodes[i]);
	}
	this.toolbar = this.create({tag: "div", id: "toolbar", "class": "Toolbar", style: "width:6px;height:120px", onMouseOver: function () {that.maxToolbar(); }, onMouseOut: function (e) {that.minToolbar(e); }, $parent: this.board});

	this.itembar = this.create({tag: "div", id: "itembar", "class": "Itembar", style: "width:6px;height:200px", onMouseOver: function () {that.maxItembar(); }, onMouseOut: function (e) {that.minItembar(e); }, $parent: this.board});
	this.codeView = this.create({tag: "div", "class": "CodeView", $parent: this.board});
	this.create({tag: "div", "class": "pi", $parent: this.codeView, value: "&pi;", onMouseOver: function () {that.maxCodeView(); }, onMouseOut: function (e) {that.minCodeView(e); }});
};
ClassEditor.prototype.maxCodeView = function () {
	if (this.codeViewer) {return; }
	var html, rect, data, result = this.minJson({}, this.model);
	data = JSON.stringify(result, null, "    ");
	data = data.replace(new RegExp("\n", 'g'), "<br/>").replace(new RegExp(" ", 'g'), "&nbsp;");

	html = this.create({tag: "div", style: "position:absolute;", value: data});
	this.board.appendChild(html);
	rect = html.getBoundingClientRect();
	this.board.removeChild(html);
	this.codeViewer = this.create({tag: "div", "class": "code_box", style: {width: rect.width, height: rect.height}, $parent: this.board, value: data});
};
ClassEditor.prototype.minCodeView = function () {
	if (!this.codeViewer) {
		return;
	}
	this.board.removeChild(this.codeViewer);
	this.codeViewer = null;
};
ClassEditor.prototype.createCell = function (node, table) {
	var tr = this.create({tag: 'tr', $parent: table});
	node.$parent = tr;
	return this.create(node);
};
ClassEditor.prototype.maxToolbar = function () {
	if (this.toolbar.clientWidth > 100) {
		return;
	}
	var that = this, table, tr, cell, hasJava;

	this.toolbar.minWidth = this.toolbar.clientWidth;
	this.toolbar.style.width = 300;
	table = this.create({tag: "table", $parent: this.toolbar});
	this.createCell({"tag": "th", colspan: 2, value: "Properties"}, table);

	tr = this.create({tag: 'tr', $parent: table});
	this.create({"tag": "td", value: "Workspace:", $parent: tr});
	cell = this.create({"tag": "td", $parent: tr});
	this.createInputField({value: this.model["package"], $parent: cell, onChange: function (e) {that.savePackage(e); }});

	cell = this.createCell({"tag": "td", colspan: 2, style: "text-align:right;padding:10px 10px 0 0"}, table);
	this.create({tag: 'button', $parent: cell, style: "margin-left:10px;", value: "Save", onClick: function () {that.save(); }});
	hasJava = typeof (java);
	if (hasJava !== 'undefined') {
		this.create({tag: 'button', $parent: cell, style: "margin-left:10px;", value: "Generate", onClick: function () {that.generate(); }});
		this.create({tag: 'button', $parent: cell, style: "margin-left:10px;", value: "Exit", onClick: function () {that.close(); }});
	}
};
ClassEditor.prototype.maxItembar = function () {
	if (this.itembar.clientWidth > 10) {
		return;
	}
	var that = this, table, th, item, node;

	this.itembar.minWidth = this.itembar.clientWidth;
	this.itembar.style.width = 80;

	table = this.create({tag: "table", style: "padding-left:10px", $parent: this.itembar});
	this.createCell({"tag": "th", value: "Item"}, table);
	th = this.createCell({"tag": "th"}, table);
	item = this.create({"tag": "table", id: "node", draggable: "true", cellspacing: "0", ondragstart: function (e) {that.startDrag(e); }, style: "border:1px solid #000;width:30px;height:30px;cursor: pointer", $parent: th});
	this.createCell({"tag": "td", style: "height:10px;border-bottom:1px solid #000;"}, item);
	this.createCell({"tag": "td"}, item);
	node = this.getAction("Selector").node;

	if (node) {
		th = this.createCell({"tag": "th"}, table);
		this.create({tag: "button", id: "Attribute", value: "Attribute", onclick: function (e) {that.executeClassAdd(e); }, "style": "margin-top:5px;", $parent: th});
		this.create({tag: "button", id: "Method", value: "Method", onclick: function (e) {that.executeClassAdd(e); }, "style": "margin-top:5px;", $parent: th});
	}
};
ClassEditor.prototype.executeClassAdd = function (e) {
	var node = this.getAction("Selector").node;
	if (e.target.id === "Attribute") {
		this.inputNode.accept("attribute:Object", node);
	} else if (e.target.id === "Method") {
		this.inputNode.accept("methods()", node);
	}

};
ClassEditor.prototype.startDrag = function (e) {e.dataTransfer.setData("Text", e.target.id); };
ClassEditor.prototype.createInputField = function (option) {
	var that = this, node;
	node = this.copy({tag: "input", type: "text", width: "100%", onFocus: function () {that.inputEvent = false; }, onBlur: function () {that.inputEvent = true; }}, option);
	if (option.$parent) {
		node.$parent = option.$parent;
	}
	if (option.onChange) {
		node.onChange = option.onChange;
	}
	return this.create(node);
};
ClassEditor.prototype.savePackage = function (e) {
	this.model["package"] = e.target.value;
};
ClassEditor.prototype.minToolbar = function (e) {
	if (this.toolbar.clientWidth < 100 || this.getId(e.toElement, "toolbar")) {
		return;
	}
	var i;
	for (i = this.toolbar.children.length - 1; i >= 0; i -= 1) {
		this.toolbar.removeChild(this.toolbar.children[i]);
	}
	this.toolbar.style.width = this.toolbar.minWidth;
	this.inputEvent = true;
};
ClassEditor.prototype.minItembar = function (e) {
	if (this.itembar.clientWidth < 50 || this.getId(e.toElement, "itembar")) {
		return;
	}
	var i;
	for (i = this.itembar.children.length - 1; i >= 0; i -= 1) {
		this.itembar.removeChild(this.itembar.children[i]);
	}
	this.itembar.style.width = this.itembar.minWidth;
	this.inputEvent = true;
};
ClassEditor.prototype.getId = function (element, id) {
	if (element === null) {
		return false;
	}
	if (element.id === id) {
		return true;
	}
	return this.getId(element.parentElement, id);
};
ClassEditor.prototype.doAction = function (event, functionName) {
	var i;
	for (i = 0; i < this.actions.length; i += 1) {
		if (typeof this.actions[i][functionName] === "function" && this.actions[i][functionName](event)) {
			return;
		}
	}
	if (functionName === "stopAction" && event.target === this.board) {
		this.getAction("Selector").setNode(null);
	}
};
ClassEditor.prototype.selectNode = function (event) {
	var n = this.getModelNode(event.target);
	if (n) {
		this.getAction("Selector").setNode(n);
	}
};
ClassEditor.prototype.getAction = function (name) {
	var i;
	for (i = 0; i < this.actions.length; i += 1) {
		if (name === this.actions[i].name) {
			return this.actions[i];
		}
	}
	return null;
};
ClassEditor.prototype.createElement = function (element, typ, node) {
	if (typ) {
		if (typ === "empty" || typ === "attribute" || typ === "method") {
			this.createEdge.addElement(element, node);
		} else {
			if (typ !== "info") {
				var that = this;
				this.bind(element, "mousedown", function (e) {that.callBack(typ, e); });
			}
			this.editNode.addElement(element, typ);
		}
	}
};
ClassEditor.prototype.callBack = function (typ, e) {
	this.getAction("MoveNode").callBack(typ, e);
};
ClassEditor.prototype.addNode = function (node) {
	var i, html = null, size, that = this;
	for (i = 0; i < this.model.nodes.length; i += 1) {
		if (this.model.nodes[i].id === node.id) {
			html = this.drawer.getNode(this.model.nodes[i], false);
			break;
		}
	}
	if (!html) {
		node = this.model.addNode(node);
		html = this.drawer.getNode(node, false);
	}
	if (this.getAction("Selector").node) {
		this.getAction("Selector").node = html;
	}
	this.board.appendChild(html);

	size = this.drawer.getSize(html);
	node.$minWidth = size.x;
	node.$minHeight = size.y;
	this.drawer.setSize(html, Math.max(Number(node.width), Number(node.$minWidth)), Math.max(Number(node.height), Number(node.$minHeight)));

	this.bind(html, "mouseup", function (e) {that.selectNode(e); });
};
ClassEditor.prototype.removeNode = function (id) {
	this.model.removeNode(id);
};
ClassEditor.prototype.clearLines = function () {
	var i;
	for (i = 0; i < this.model.edges.length; i += 1) {
		this.model.edges[i].removeFromBoard(this.board);
	}
};
ClassEditor.prototype.drawlines = function () {
	this.clearLines();
	var infoTxt, e, i;
	for (i = 0; i < this.model.edges.length; i += 1) {
		e = this.model.edges[i];
		infoTxt = e.getInfo(e.source);
		if (infoTxt.length > 0) {
			this.sizeHTML(this.drawer.getInfo(e.source, infoTxt, 0), e.source);
		}
		infoTxt = e.getInfo(e.target);
		if (infoTxt.length > 0) {
			this.sizeHTML(this.drawer.getInfo(e.target, infoTxt, 0), e.target);
		}
	}
	this.model.calcLines(this.drawer);
	for (i = 0; i < this.model.edges.length; i += 1) {
		this.model.edges[i].draw(this.board, this.drawer);
	}
};
ClassEditor.prototype.removeCurrentNode = function () {
	var i, n, item, selector = this.getAction("Selector");
	item = selector.node;
	if (item) {
		selector.removeAll();
		this.board.removeChild(item);
		n = item.model;
		for (i = 0; i < this.model.nodes.length; i += 1) {
			if (this.model.nodes[i].id === n.id) {
				this.model.nodes.splice(i - 1, 1);
				i -= 1;
			}
		}
	}
};
// ################################## CREATE ####################################################
ClassEditor.CreateNode = function (parent) {
	this.name = "CreateNode";
	this.$parent = parent;
	this.minSize = 20;
	this.offset = new Pos();
	this.mouse = new Pos();
	this.createClass = false;
};
ClassEditor.CreateNode.prototype = ObjectCreate(GraphUtil.prototype);
ClassEditor.CreateNode.prototype.startAction = function (event) {
	if (event.button === 2) {return; }
	if (event.target !== this.$parent.board) {return; }
	this.createClass = true;
	this.offset.x = this.mouse.x = this.getX(event);
	this.offset.y = this.mouse.y = this.getY(event);
	return true;
};
ClassEditor.CreateNode.prototype.doAction = function (event) {
	if (!this.createClass) {return; }
	this.mouse.x = this.getX(event);
	this.mouse.y = this.getY(event);
	this.createNode();
};
ClassEditor.CreateNode.prototype.setValue = function (x1, y1, x2, y2) {
	this.offset.x = x1;
	this.offset.y = y1;
	this.mouse.x = x2;
	this.mouse.y = y2;
	this.createNode();
};
ClassEditor.CreateNode.prototype.createNode = function () {
	var height, width = Math.abs(this.mouse.x - this.offset.x);
	height = Math.abs(this.mouse.y - this.offset.y);
	if (width > this.minSize && height > this.minSize) {
		if (!this.newClass) {
			this.newClass = this.create({tag: "div", style: "position:absolute;opacity: 0.2;background-color:#ccc;"});
			this.$parent.board.appendChild(this.newClass);
		}
		this.newClass.style.width = width;
		this.newClass.style.height = height;
		this.newClass.style.left = Math.min(this.mouse.x, this.offset.x);
		this.newClass.style.top = Math.min(this.mouse.y, this.offset.y);
	} else {
		if (this.newClass) {
			this.$parent.board.removeChild(this.newClass);
			this.newClass = null;
		}
	}
	return true;
};
ClassEditor.CreateNode.prototype.getX = function (event) {
	return this.getEventX(event) - this.$parent.board.offsetLeft;
};
ClassEditor.CreateNode.prototype.getY = function (event) {
	return this.getEventY(event) - this.$parent.board.offsetTop;
};
ClassEditor.CreateNode.prototype.outAction = function (event) {return this.stopAction(event); };
ClassEditor.CreateNode.prototype.stopAction = function () {
	this.createClass = false;
	if (!this.newClass) {
		return false;
	}
	var node = {"typ": "node", "id": "Class" + (this.$parent.model.$nodeCount + 1)};
	node.x = this.getValue(this.newClass.style.left);
	node.y = this.getValue(this.newClass.style.top);
	node.width = this.getValue(this.newClass.style.width);
	node.height = this.getValue(this.newClass.style.height);

	this.$parent.board.removeChild(this.newClass);
	this.newClass = null;
	this.$parent.addNode(node);
	return true;
};
// ################################## SELECTOR ####################################################
ClassEditor.Selector = function (parent) {
	this.name = "Selector";
	this.$parent = parent;
	this.size = 6;
	this.nodes = {};
	this.mouse = new Pos();
	this.offset = new Pos();
	this.resizeNode = null;
};
ClassEditor.Selector.prototype = ObjectCreate(GraphUtil.prototype);
ClassEditor.Selector.prototype.start = function (e) {
	this.resizeNode = e.target.id;
	this.sizeNode = new Pos(this.node.model.width, this.node.model.height);
	this.offset.x = this.mouse.x = this.getEventX(e);
	this.offset.y = this.mouse.y = this.getEventY(e);
};
ClassEditor.Selector.prototype.doit = function (e) {
	if (!this.resizeNode) {
		return;
	}
	this.mouse.x = this.getEventX(e);
	this.mouse.y = this.getEventY(e);

	var n, multiX = 1, multiY = 1, diffX = 0, diffY = 0, newWidth, newHeight;
	if (this.resizeNode.charAt(0) === "n") {
		multiY = -1;
	}
	if (this.resizeNode.indexOf("w") >= 0) {
		multiX = -1;
	}
	n = this.node.model;

	newWidth = Math.max(n.$minWidth, this.sizeNode.x + (this.mouse.x - this.offset.x) * multiX);
	newHeight = Math.max(n.$minHeight, this.sizeNode.y + (this.mouse.y - this.offset.y) * multiY);

	if (this.resizeNode === "n") {
		diffY = n.height - newHeight;
		n.height = this.node.style.height = newHeight;
	} else if (this.resizeNode === "nw") {
		diffY = n.height - newHeight;
		n.height = this.node.style.height = newHeight;
		diffX = n.width - newWidth;
		n.width = this.node.style.width = newWidth;
	} else if (this.resizeNode === "ne") {
		diffY = n.height - newHeight;
		n.height = this.node.style.height = newHeight;
		n.width = this.node.style.width = newWidth;
	} else if (this.resizeNode === "sw") {
		diffX = n.width - newWidth;
		n.height = this.node.style.height = newHeight;
		n.width = this.node.style.width = newWidth;
	} else if (this.resizeNode === "s") {
		n.height = this.node.style.height = newHeight;
	} else if (this.resizeNode === "w") {
		diffX = n.width - newWidth;
		n.width = this.node.style.width = newWidth;
	} else if (this.resizeNode === "e") {
		n.width = this.node.style.width = newWidth;
	} else {
		n.width = this.node.style.width = newWidth;
		n.height = this.node.style.height = newHeight;
	}
	if (diffY !== 0) {
		n.y += diffY;
		this.node.style.top = n.y;
	}
	if (diffX !== 0) {
		n.x += diffX;
		this.node.style.left = n.x;
	}
	this.refreshNode();
};
ClassEditor.Selector.prototype.stop = function () {this.resizeNode = null; };
ClassEditor.Selector.prototype.removeAll = function () {
	var i, select;
	for (i in this.nodes) {
		if (!this.nodes.hasOwnProperty(i)) {
			continue;
		}
		select = this.nodes[i];
		this.$parent.board.removeChild(select);
	}
	this.nodes = {};
};
ClassEditor.Selector.prototype.setNode = function (node) {
	if (this.node) {
		this.removeAll();
	}
	this.node = node;
	this.refreshNode();
};
ClassEditor.Selector.prototype.refreshNode = function () {
	if (!this.node) {
		return;
	}
	var x, y, width, height, s, sh;
	x = this.getValue(this.node.style.left);
	y = this.getValue(this.node.style.top);
	width = this.getValue(this.node.clientWidth);
	height = this.getValue(this.node.clientHeight);
	s = this.size + 1;
	sh = this.size / 2 + 1;
	this.selector("nw", x - s, y - s);
	this.selector("n", x + (width / 2) - sh, y - s);
	this.selector("ne", x + width + 1, y - s);
	this.selector("w", x - s, y + height / 2 - sh);
	this.selector("sw", x - s, y + height + 1);
	this.selector("s", x + (width / 2) - sh, y + height + 1);
	this.selector("se", x + width + 1, y + height + 1);
	this.selector("e", x + width + 1, y + height / 2 - sh);
	this.addCreateAssoc(x + width, y);
};
ClassEditor.Selector.prototype.addCreateAssoc = function (x, y) {
	var n = this.nodes.assoc, symbolLib, that = this;
	if (!n) {
		n = {typ: "EdgeIcon", transform: "scale(0.2)", style: "cursor:pointer;top: " + x + "px;left:" + y + "px;" };
		symbolLib = new SymbolLibary();
		n = symbolLib.draw(null, n);
		n.style.left = x + 10;
		n.style.width = 40;
		n.style.height = 30;
		n.style.position = "absolute";
		n.style.top = y - 10;
		this.nodes.assoc = n;
		this.$parent.board.appendChild(n);
	}
};
ClassEditor.Selector.prototype.selector = function (id, x, y) {
	var n = this.nodes[id], that = this;
	if (!n) {
		n = this.create({tag: "div", "id": id, style: "position:absolute;background:#00F;width:" + this.size + "px;height:" + this.size + "px;cursor:" + id + "-resize;"});
		this.nodes[id] = n;
		this.bind(n, "mousedown", function (e) {that.start(e); });
		this.bind(n, "mousemove", function (e) {that.doit(e); });
		this.bind(n, "mouseup", function (e) {that.stop(e); });
		this.$parent.board.appendChild(n);
	}
	n.style.left = x;
	n.style.top = y;
};
ClassEditor.Selector.prototype.startAction = function () {
	if (!this.node) {
		return false;
	}
};
ClassEditor.Selector.prototype.doAction = function (event) {
	if (!this.resizeNode) {
		return false;
	}
	this.doit(event);
	return true;
};
ClassEditor.Selector.prototype.stopAction = function () {
	if (this.resizeNode) {
		this.resizeNode = false;
		return true;
	}
	return false;
};
// ################################## SELECTOR ####################################################
ClassEditor.MoveNode = function (parent) { this.name = "MoveNode"; this.$parent = parent; this.mouse = new Pos(); this.offset = new Pos(); };
ClassEditor.MoveNode.prototype = ObjectCreate(GraphUtil.prototype);
ClassEditor.MoveNode.prototype.callBack = function (typ, e) {
	if (typ === "id") {
		var th = e.target, that = this;
		this.bind(th, "mousedown", function (e) {that.start(e); });
		this.bind(th, "mousemove", function (e) {that.doit(e); });
		this.bind(th, "mouseup", function (e) {that.stop(e); });
	}
};
ClassEditor.MoveNode.prototype.start = function (e) {
	this.node = this.getModelNode(e.target).model;
	this.posNode = new Pos(this.node.x, this.node.y);
	// SAVE ID
	this.offset.x = this.mouse.x = this.getEventX(e);
	this.offset.y = this.mouse.y = this.getEventY(e);
};
ClassEditor.MoveNode.prototype.doAction = function () {return this.node; };
ClassEditor.MoveNode.prototype.doit = function (e) {
	if (!this.node) {
		return;
	}
	this.mouse.x = this.getEventX(e);
	this.mouse.y = this.getEventY(e);
	var newX, newY;
	newX = this.posNode.x + (this.mouse.x - this.offset.x);
	newY = this.posNode.y + (this.mouse.y - this.offset.y);

	this.node.x = this.node.$gui.style.left = newX;
	this.node.y = this.node.$gui.style.top = newY;
	this.$parent.getAction("Selector").refreshNode();
};
ClassEditor.MoveNode.prototype.stop = function () {
	this.node = null;
	this.$parent.drawlines();
};
// ################################## InputNode ####################################################
ClassEditor.InputNode = function (parent) {this.name = "InputNode"; this.$parent = parent;
	var that = this;
	document.body.addEventListener("keyup", function (e) {
		that.keyup(e);
	});
	};
ClassEditor.InputNode.prototype = ObjectCreate(GraphUtil.prototype);
ClassEditor.InputNode.prototype.keyup = function (e) {
	if (!this.$parent.inputEvent) {
		return;
	}
	var x = e.keyCode, selector, item, m, that = this;
	if (e.altKey || e.ctrlKey) {
		return;
	}
	if (x === 46) {
		this.$parent.removeCurrentNode();
	}
	if ((x > 64 && x < 91) && !e.shiftKey) {
		x += 32;
	}
	if ((x > 64 && x < 91) || (x > 96 && x < 123) || (x > 127 && x < 155) || (x > 159 && x < 166)) {
		selector = this.$parent.getAction("Selector");
		item = selector.node;
		if (item && !this.inputItem) {
			m = item.model;
			this.inputItem = this.$parent.create({tag: "input", type: "text", "#node": item, "value": String.fromCharCode(x), style: "position:absolute;left:" + m.x + "px;top:" + (m.y + m.height) + "px;width:" + m.width});
			this.$parent.board.appendChild(this.inputItem);
			this.choiceBox = new ClassEditor.ChoiceBox(this.inputItem, this.$parent);
			this.inputItem.addEventListener("keyup", function (e) {
				that.changeText(e);
			});
			this.inputItem.focus();
		}
	}
};
ClassEditor.InputNode.prototype.accept = function (text, n) {
	var id, model = n.model;
	id = n.model.id;
	if (this.addValue(text, model)) {
		if (id !== n.model.id) {
			this.$parent.removeNode(id);
			this.$parent.addNode(n.model);
		} else {
			this.$parent.board.removeChild(n);
			this.$parent.addNode(n.model);
		}
		this.$parent.getAction("Selector").refreshNode();
		return true;
	}
	return false;
};
ClassEditor.InputNode.prototype.addValue = function (text, model) {
	if (text.length < 1) {
		return false;
	}
	if (text.indexOf(":") >= 0) {
		if (!model.attributes) {
			model.attributes = [];
		}
		model.attributes.push(text);
		return true;
	}
	if (text.indexOf("(") > 0) {
		if (!model.methods) {
			model.methods = [];
		}
		model.methods.push(text);
		return true;
	}
	//typ ClassEditor
	if (model.$parent.typ === "classdiagram") {
		model.id = this.fristUp(text);
	} else {
		model.id = text;
	}
	return true;
};
ClassEditor.InputNode.prototype.fristUp = function (string) {
	return string.charAt(0).toUpperCase() + string.slice(1);
};
ClassEditor.InputNode.prototype.changeText = function (e) {
	if (!this.inputItem) {
		return;
	}
	var close = false, n, text;
	if (e.keyCode === 27) {close = true; }
	if (e.keyCode === 13) {
		n = this.inputItem.node;
		text = this.inputItem.value;
		if (this.accept(text, n)) {
			close = true;
		}
	}
	if (close) {
		this.$parent.board.removeChild(this.inputItem);
		this.inputItem = null;
		if (this.choiceBox && this.choiceBox.div) {
			this.graph.board.removeChild(this.choiceBox.div);
			this.choiceBox.div = null;
			this.choiceBox = null;
		}
	}
};
// ################################## ChoiceBox ####################################################
ClassEditor.ChoiceBox = function (field, graph) {
	this.field = field;
	this.graph = graph;
	this.list = [];
	var that = this;
	this.bind(field, "keyup", function (e) {that.change(e); });
};
ClassEditor.ChoiceBox.prototype = ObjectCreate(GraphUtil.prototype);
ClassEditor.ChoiceBox.prototype.initAttributes = function () {
	this.list = ["Boolean", "Byte", "Character", "Double", "Float", "Integer", "Long", "Number", "Object", "Short", "String"];
	this.addFromGraph(this.graph.model, "nodes.id");
	this.list.sort();
};
ClassEditor.ChoiceBox.prototype.addFromGraph = function (item, filter) {
	var i, z;
	for (i in item) {
		if (!item.hasOwnProperty(i)) {
			continue;
		}
		if (item[i] instanceof Array) {
			for (z = 0; z < item[i].length; z += 1) {
				this.addFromGraph(item[i][z], filter.substring(filter.indexOf(".") + 1));
			}
		}
		if (filter.indexOf(".") < 0 && i === filter) {
			this.list.push(item[i]);
		}
	}
};
ClassEditor.ChoiceBox.prototype.change = function (e) {
	if (this.div) {
		this.graph.board.removeChild(this.div);
		this.div = null;
	}
	if (e.keyCode === 27 || e.keyCode === 13) {
		return;
	}
	var t = e.target.value.toLowerCase(), that = this, i, div, func;
	this.typ = "";
	if (t.indexOf(":") >= 0) {
		this.initAttributes();
		this.typ = ":";
	}
	if (this.typ === "") {
		return;
	}
	t = t.substring(t.lastIndexOf(this.typ) + 1);
	div = this.create({tag: "div", "class": "ChoiceBox", style: "left:" + this.field.style.left + ";top:" + (this.getValue(this.field.style.top) + this.field.clientHeight + 4) + ";width:" + this.field.clientWidth});
	func = function () {that.select(this); };
	for (i = 0; i < this.list.length; i += 1) {
		if (this.list[i].toLowerCase().indexOf(t) >= 0) {
			if (i % 2 === 0) {
				this.create({tag: "div", value: this.list[i], $parent: div, onMouseup: func});
			} else {
				this.create({tag: "div", value: this.list[i], "class": "alt", $parent: div, onMouseup: func});
			}
		}
	}
	if (div.children.length > 0) {
		this.div = div;
		this.graph.board.appendChild(div);
	}
};
ClassEditor.ChoiceBox.prototype.select = function (input) {
	var pos = this.field.value.lastIndexOf(this.typ);
	this.field.value = this.field.value.substring(0, pos + 1) + input.innerHTML;
	if (this.div) {
		this.graph.board.removeChild(this.div);
		this.div = null;
	}
	this.field.focus();
};
// ################################## EditNode ####################################################
ClassEditor.EditNode = function (graph) {this.graph = graph; };
ClassEditor.EditNode.prototype = ObjectCreate(GraphUtil.prototype);
ClassEditor.EditNode.prototype.addElement = function (element, typ) {
	var that = this;
	this.bind(element, "mouseup", function (e) {that.click(e, element, typ); });
};
ClassEditor.EditNode.prototype.click = function (e, control, typ) {
	if (!control.lastClick || control.lastClick < new Date().getTime() - 1000 || control.oldValue) {
		control.lastClick = new Date().getTime();
		return;
	}
	var that = this;
	control.oldValue = control.innerHTML;
	control.contentEditable = true;
	control.typ = typ;
	this.graph.inputEvent = false;
	this.selectText(control);
	control.onkeydown = function (e) {that.change(e, control); };
};
ClassEditor.EditNode.prototype.change = function (e, control) {
	if (e.keyCode !== 27 && e.keyCode !== 13) {
		return;
	}
	var value, t, i, node = this.getModelNode(control);
	control.contentEditable = false;
	this.graph.inputEvent = true;
	if (e.keyCode === 27) {
		control.innerHTML = control.oldValue;
		control.oldValue = null;
		return;
	}
	value = control.innerHTML;
	control.oldValue = null;
	while (value.substring(value.length - 4) === "<br>") {
		value = value.substring(0, value.length - 4);
	}
	if (control.typ === "id") {
		node.model.id = value;
	} else if (control.typ === "attribute" || control.typ === "method") {
		t = control.typ + "s";
		for (i = 0; i < node.model[t].length; i += 1) {
			if (node.model[t][i] === control.oldValue) {
				if (value.length > 0) {
					node.model[t][i] = value;
				} else {
					node.model[t].splice(i, 1);
				}
				break;
			}
		}
	} else if (control.typ === "info") {
		node.model.property = value;
	}
	control.innerHTML = value;
};
// ################################## CreateEdge ####################################################
ClassEditor.CreateEdge = function (graph) {this.graph = graph; };
ClassEditor.CreateEdge.prototype = ObjectCreate(GraphUtil.prototype);
ClassEditor.CreateEdge.prototype.addElement = function (element, node) {
	var that = this;
	this.bind(element, "mouseup", function (e) {that.up(e, element, node); });
	this.bind(element, "mousedown", function (e) {that.down(e, element, node); });
};
ClassEditor.CreateEdge.prototype.down = function (e, element, node) {
	this.fromElement = element;
	this.fromNode = node;
	this.x = e.x;
	this.y = e.y;
};
ClassEditor.CreateEdge.prototype.up = function (e, element, node) {
	if (!this.fromElement) {
		return;
	}
	if (this.graph.getAction("Selector").node || Math.abs(e.x - this.x) + Math.abs(e.y - this.y) < 10) {
		this.fromElement = null;
		this.fromNode = null;
		return;
	}
	//this.getAction("Selector").setNode(null);
	e.stopPropagation();
	this.toElement = element;
	this.toNode = node;

	var i, div, width = 120, that = this, func;

	if (this.div) {
		return;
	}
	this.list = ["Generalisation", "Assoziation", "Abort"];

	div = this.create({tag: "div", "class": "ChoiceBox", style: {left: e.x, top: e.y, "width": width, zIndex: 6000}});
	func = function () {that.select(this); };

	for (i = 0; i < this.list.length; i += 1) {
		if (i % 2 === 0) {
			this.create({tag: "div", value: this.list[i], $parent: div, onMouseup: func});
		} else {
			this.create({tag: "div", value: this.list[i], "class": "alt", $parent: div, onMouseup: func});
		}
	}
	this.div = div;
	this.graph.board.appendChild(div);
};
ClassEditor.CreateEdge.prototype.startAction = function (e) {
	if (e.target === this.graph.board && this.div) {
		this.graph.board.removeChild(this.div);
		this.div = null;
	}
};
ClassEditor.CreateEdge.prototype.select = function (e) {
	var edge, t = e.innerHTML;
	if (t === this.list[0]) {
		edge = this.graph.model.addEdgeModel({"typ": "Generalisation", "source": {id: this.fromNode.id}, target: {id: this.toNode.id}});
		this.graph.drawlines();
	}
	if (t === this.list[1]) {
		edge = this.graph.model.addEdgeModel({"typ": "edge", "source": {id: this.fromNode.id, property: "from"}, target: {id: this.toNode.id, property: "to"}});
		this.graph.drawlines();
	}
	this.graph.board.removeChild(this.div);
	this.div = null;
	this.fromElement = null;
	this.fromNode = null;
	this.toNode = null;
	this.toElement = null;
};
