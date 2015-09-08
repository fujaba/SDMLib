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
// VERSION: 2015.08.26 16:40 

//var uniId = 0;
//function generateId() { return uniId++; };
//Object.prototype.uniId = function () {
//	var newId = generateId();
//	this.uniId = function () { return newId; };
//	return newId;
//};
/*jslint forin:true, newcap:true, node: true, evil: true, nomen: true, continue: true, vars: true */
/*jshint forin:true, laxbreak: true, newcap: false, node: true, evil: true, nomen: true, onevar: true, -W089, -W079 */

/*global document: false, window: false, Options: false, navigator: false, unescape: false, Edge: false, Info: false, Loader: false, HTMLDrawer: false, DagreLayout: false, SVGDrawer: false */
/*global jsPDF: false, svgConverter: false, jsEPS: false, Image: false, Blob: false, dagre: false, SymbolLibary: false, InputNode: false */
/*global EditNode: false, CreateEdge: false, Selector: false, MoveNode: false, CreateNode: false, FileReader:false, java:false, ChoiceBox:false */
"use strict";
var Object_create = Object.create || function (o) {var F = function () {}; F.prototype = o; return new F(); };

/* Pos */
var Pos = function (x, y, id) {this.x = Math.round(x || 0); this.y = Math.round(y || 0); if (id) {this._id = id; } };
/* GraphUtil */
var GraphUtil = function (ns) {if (ns) {this.ns = ns; } };
GraphUtil.prototype.copy = function (ref, src, full, replace) {
	if (src) {
		var i;
		for (i in src) {
			if (!src.hasOwnProperty(i) || typeof (src[i]) === "function") {
				continue;
			}
			if (i.charAt(0) === "_") {
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
		if (src.width) {ref._startWidth = src.width; }
		if (src.height) {ref._startHeight = src.height; }
	}
	return ref;
};
GraphUtil.prototype.minJson = function (target, src, ref) {
	var i, temp, value;
	for (i in src) {
		if (!src.hasOwnProperty(i) || typeof (src[i]) === "function") {
			continue;
		}
		if (src[i] === null || src[i] === "" || src[i] === 0 || src[i] === false || i.charAt(0) === "_") {
			continue;
		}
		value = src[i];
		if (value instanceof Options || ref !== null) {
			if (typeof (value) === "object") {
				temp = (value instanceof Array) ? [] : {};
				if (ref) {
					value = this.minJson(temp, value, ref[i]);
				} else {
					value = this.minJson(temp, value, new Options());
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
				var newItem = this.minJson({}, value);
				if (JSON.stringify(newItem, null, "") === "{}") {
					continue;
				}
				target[i] = newItem;
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
	var item;
	if (document.createElementNS && (node.xmlns || this.ns)) {
		if (node.xmlns) {
			item = document.createElementNS(node.xmlns, node.tag);
		} else {
			item = document.createElementNS(this.ns, node.tag);
		}
	} else {
		item = document.createElement(node.tag);
	}
	var tag = node.tag.toLowerCase();
	var key;
	for (key in node) {
		if (!node.hasOwnProperty(key)) {
			continue;
		}
		var k = key.toLowerCase();
		if (node[key] === null) {
			continue;
		}
		if (k === 'tag' || k === 'content_src' || k.charAt(0) === '_' || k === 'model') {
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
				var style;
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
	if (node._font) {
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
	if (node._parent) {
		node._parent.appendChild(item);
	}
	if (tag === "image" && node.content_src) {
		item.setAttribute('xmlns:xlink', "http://www.w3.org/1999/xlink");
		item.setAttributeNS("http://www.w3.org/1999/xlink", 'href', node.content_src);
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
	var range;
	if (this.isIE()) {
		range = document.body.createTextRange();
		range.moveToElementText(text);
		range.select();
	} else if (this.isFireFox() || this.isOpera()) {
		var selection = window.getSelection();
		range = document.createRange();
		range.selectNodeContents(text);
		selection.removeAllRanges();
		selection.addRange(range);
	}
};
GraphUtil.prototype.sizeHTML = function (html, node) {
	if (!html) {return; }
	if (this._parent) {
		return this._parent.sizeHTML(html, node);
	}
	this.board.appendChild(html);
	var rect = html.getBoundingClientRect();
	this.board.removeChild(html);
	if (node) {
		if (!node._startWidth) {
			node.width = Math.round(rect.width);
		}
		if (!node._startHeight) {
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

/* Node */
var GraphNode = function (id) {
	this.typ = "node";
	this.id = id;
	this._edges = [];
	this.attributes = [];
	this.methods = [];
	this._parent = null;
	this.x = this.y = this.width = this.height = 0;
	this._isdraggable = true;
};
GraphNode.prototype = Object_create(GraphUtil.prototype);
GraphNode.prototype.getX = function () {if (this._parent) {return this._parent.getX() + this.x; } return this.x; };
GraphNode.prototype.getY = function () {if (this._parent) {return this._parent.getY() + this.y; } return this.y; };
GraphNode.prototype.removeFromBoard = function (board) {if (this._gui) {board.removeChild(this._gui); this._gui = null; } };

var GraphModel = function (json, options) {
	this.typ = "classdiagram";
	this._isdraggable = true;
	json = json || {};
	this.left = json.left || 0;
	this.top = json.top || 0;
	this.x = this.y = this.width = this.height = 0;
	this._nodeCount = 0;
	this.nodes = {};
	this.edges = [];
	json = json || {};
	this.typ = json.typ || "classdiagram";
	this.set("id", json.id);
	this.options = this.copy(this.copy(new Options(), json.options), options, true, true);
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
GraphModel.prototype = Object_create(GraphNode.prototype);
GraphModel.prototype.addEdgeModel = function (e) {
	var typ = e.typ.charAt(0).toUpperCase() + e.typ.substring(1).toLowerCase();
	var edge;
	if (typeof window[typ] === "function") {
		edge = eval("new " + typ + "()");
	} else {
		edge = new Edge();
	}
	edge.source = new Info(e.source, this);
	edge.target = new Info(e.target, this);
	edge._sNode = this.getNode(edge.source.id, true);
	edge._sNode._edges.push(edge);

	if (e.info) {
		if (typeof (e.info) === "string") {
			edge.info = {id: e.info};
		} else {
			edge.info = {id: e.info.id, property: e.info.property, cardinality: e.info.cardinality};
		}
	}
	edge._parent = this;
	edge.set("style", e.style);
	edge.set("counter", e.counter);

	edge._tNode = this.getNode(edge.target.id, true);
	edge._tNode._edges.push(edge);

	this.edges.push(edge);
	return edge;
};
GraphModel.prototype.addNode = function (node) {
	/* testing if node is already existing in the graph */
	if (typeof (node) === "string") {
		node = {id: node, typ: "node"};
	}
	node.typ = node.typ.toLowerCase();
	if (!(node.id)) {
		node.id = node.typ + "_" + (this._nodeCount + 1);
	}
	if (this.nodes[node.id] !== undefined) {
		return this.nodes[node.id];
	}
	if (node.typ.indexOf("diagram", node.typ.length - 7) !== -1) {
		node = new GraphModel(node, new Options(this));
	} else {
		node = this.copy(new GraphNode(), node);
	}
	this.nodes[node.id] = node;
	node._parent = this;
	this._nodeCount += 1;
	return this.nodes[node.id];
};
GraphModel.prototype.addEdge = function (source, target) {
	var edge = new Edge();
	edge._sNode = this.addNode(source);
	edge._sNode._edges.push(edge);

	edge._tNode = this.addNode(target);
	edge._tNode._edges.push(edge);

	this.edges.push(edge);
	return edge;
};
GraphModel.prototype.removeNode = function (id) {
	delete (this.nodes[id]);
	var i;
	for (i = 0; i < this.edges.length; i += 1) {
		if (this.edges[i]._sNode.id === id || this.edges[i]._tNode.id === id) {
			this.edges.splice(i, 1);
			i -= 1;
		}
	}
};
GraphModel.prototype.getNode = function (id, isSub, deep) {
	deep = deep || 0;
	if (this.nodes[id]) {
		return this.nodes[id];
	}
	if (!isSub) {
		return this.addNode(id);
	}
	var i;
	for (i in this.nodes) {
		if (!this.nodes.hasOwnProperty(i)) {
			continue;
		}
		var n = this.nodes[i];
		if (n instanceof GraphModel) {
			var r = n.getNode(id, isSub, deep + 1);
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
GraphModel.prototype.createElement = function (element, typ) {this._parent.createElement(element, typ); };
GraphModel.prototype.removeFromBoard = function (board) {
	if (this._gui) {
		board.removeChild(this._gui);
		this._gui = null;
	}
};
GraphModel.prototype.resize = function (mode) {};
GraphModel.prototype.calcLines = function (drawer) {
	var ownAssoc = [];
	var i;
	for (i in this.nodes) {
		if (!this.nodes.hasOwnProperty(i) || typeof (this.nodes[i]) === "function") {
			continue;
		}
		var n = this.nodes[i];
		n._RIGHT = n._LEFT = n._UP = n._DOWN = 0;
	}
	for (i = 0; i < this.edges.length; i += 1) {
		if (!this.edges[i].calculate(this._gui, drawer)) {
			ownAssoc.push(this.edges[i]);
		}
	}
	for (i = 0; i < ownAssoc.length; i += 1) {
		ownAssoc[i].calcOwnEdge();
		var sourcePos = ownAssoc[i].getCenterPosition(ownAssoc[i]._sNode, ownAssoc[i]._start);
		ownAssoc[i].calcInfoPos(sourcePos, ownAssoc[i]._sNode, ownAssoc[i].source);

		sourcePos = ownAssoc[i].getCenterPosition(ownAssoc[i]._tNode, ownAssoc[i]._end);
		ownAssoc[i].calcInfoPos(sourcePos, ownAssoc[i]._tNode, ownAssoc[i].target);
	}
};

/* Info */
var Info = function (info, parent) {
	if (typeof (info) === "string") {
		this.id = info;
	} else {
		if (info.property) {this.property = info.property; }
		if (info.cardinality) {this.cardinality = info.cardinality; }
		this.id = info.id;
	}
	this.x = this.y = this.width = this.height = 0;
	this._center = new Pos();
	this.custom = false;
	this._parent = parent;
	this._isdraggable = true;
};

var Line = function (source, target, line, style) {this.source = source; this.target = target; this.line = line; this.style = style; };
Line.Format = {SOLID: "SOLID", DOTTED: "DOTTED"};
/* Options */
var Options = function () {
	this.raster = false;
	this.display = "svg";
	this.font = {"font-size": "10px", "font-family": "Verdana"};
	this.layout = {name: "Dagre", rankDir: "TB", nodesep: 10};	// Dagre TB, LR
	this.CardinalityInfo = true;
	this.propertyinfo = true;
	this.rotatetext = true;
	this.linetyp = "center";
	this.buttons = ["HTML", "SVG"];	// ["HTML", "SVG", "PNG", "PDF"]
};
//				######################################################### Graph #########################################################
var Graph = function (json, options) {
	this.x = this.y = this.width = this.height = 0;
	json.top = json.top || 50;
	json.left = json.left || 10;
	this.model = new GraphModel(json, options);
	this.initLayouts();
	this.loader = new Loader(this);
	if (this.model.options.display.toLowerCase() === "html") {
		this.drawer = new HTMLDrawer();
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
	if (!this.root) {
		this.root = document.createElement("div");
		if (this.model.options.canvasid) {
			this.root.id = this.model.options.canvasid;
		}
		document.body.appendChild(this.root);
	}
};
Graph.prototype = Object_create(GraphNode.prototype);
Graph.prototype.initLayouts = function () { this.layouts = [{name: "dagre", value: new DagreLayout()}]; };
Graph.prototype.initInfo = function (edge, info) {
	if (!this.model.options.CardinalityInfo && !this.model.options.propertyinfo) {
		return null;
	}
	var infoTxt = edge.getInfo(info);
	if (infoTxt.length > 0) {
		this.sizeHTML(this.drawer.createInfo(info, infoTxt, 0), info);
	}
	return infoTxt;
};
Graph.prototype.clearBoard = function () {
	if (this.board) {
		var i;
		this.clearLines(this.model);
		for (i in this.model.nodes) {
			if (!this.model.nodes.hasOwnProperty(i)) {
				continue;
			}
			var n = this.model.nodes[i];
			n.removeFromBoard(this.board);
			n._RIGHT = n._LEFT = n._UP = n._DOWN = 0;
		}
		this.root.removeChild(this.board);
	}
	if (this.drawer) {
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
	var i;
	for (i = 0; i < model.edges.length; i += 1) {
		model.edges[i].draw(this.board, this.drawer);
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
	var min = new Pos();
	var max = new Pos(model.minSize.x, model.minSize.y);
	var i;
	var nodes = model.nodes;
	for (i in nodes) {
		if (!nodes.hasOwnProperty(i) || typeof (nodes[i]) === "function") {
			continue;
		}
		var n = nodes[i];
		this.moveToRaster(n);
		this.MinMax(n, min, max);
	}
	this.calcLines(model);
	for (i = 0; i < model.edges.length; i += 1) {
		var e = model.edges[i];
		this.MinMax(e.source, min, max);
		this.MinMax(e.target, min, max);
	}
	model.height = max.y;
	model.width = max.x;
	this.drawer.setSize(model._gui, max.x, max.y);
	if (model.options.raster) {
		this.drawRaster();
	}
	this.drawLines(model);
	return max;
};
Graph.prototype.drawRaster = function () {
	while (this.board.rasterElements.length > 0) {
		this.board.removeChild(this.board.rasterElements.pop());
	}
	var width = this.board.style.width.replace("px", "");
	var height = this.board.style.height.replace("px", "");
	var line, i;
	for (i = 10; i < width; i += 10) {
		line = this.drawer.createLine(i, 0, i, height, null, "#ccc");
		line.className = "lineRaster";
		this.board.rasterElements.push(line);
		this.board.appendChild(line);
	}
	for (i = 10; i < height; i += 10) {
		line = this.drawer.createLine(0, i, width, i, null, "#ccc");
		line.className = "lineRaster";
		this.board.rasterElements.push(line);
		this.board.appendChild(line);
	}
};
Graph.prototype.draw = function (model, width, height) {
	var i, n, nodes = model.nodes;
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
		n._gui = this.drawer.getNode(n, true);
		model._gui.appendChild(n._gui);
	}
};
Graph.prototype.moveToRaster = function (node) {
	if (this.model.options.raster) {
		node.x = parseInt(node.x / 10, 10) * 10;
		node.y = parseInt(node.y / 10, 10) * 10;
		if (node._gui) {
			this.drawer.setPos(node._gui, node.x, node.y);
		}
	}
};
Graph.prototype.initGraph = function (model) {
	var i;
	for (i in model.nodes) {
		if (typeof (model.nodes[i]) === "function") {
			continue;
		}
		var n = model.nodes[i];
		var isDiag = n.typ.indexOf("diagram", n.typ.length - 7) !== -1;
		if (isDiag) {
			this.initGraph(n);
		}
		var html = this.drawer.getNode(n);
		if (html) {
			this.sizeHTML(html, n);
			if (isDiag) {
				n._center = new Pos(n.x + (n.width / 2), n.y + (n.height / 2));
			}
		}
	}
	for (i = 0; i < model.edges.length; i += 1) {
		var e = model.edges[i];
		this.initInfo(e, e.source);
		this.initInfo(e, e.target);
	}
};
Graph.prototype.layout = function (minwidth, minHeight, model) {
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
		if (!node.model._isdraggable) {
			return null;
		}
		return node;
	}
	if (node.parentElement.model) {
		if (!node.parentElement.model._isdraggable) {
			return null;
		}
		return node.parentElement;
	}
	return null;
};
Graph.prototype.startDrag = function (event) {
	var n = this.getDragNode(event.currentTarget);
	if (!n) {
		return;
	}
	if (this.objDrag) {
		return;
	}
	this.objDrag = n;
	var graph = this.objDrag.parentElement;
	if (graph) {
		var i;
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
	this.mouse.x = this.isIE ? window.event.clientX : event.pageX;
	this.mouse.y = this.isIE ? window.event.clientY : event.pageY;

	if (this.objDrag !== null) {
		var x = (this.mouse.x - this.offset.x) + this.startObj.x;
		var y = (this.mouse.y - this.offset.y) + this.startObj.y;

		if (this.model.options.display === "svg") {
			x = x - this.startObj.x;
			y = y - this.startObj.y;
			this.objDrag.setAttribute('transform', "translate("  + x + " "  + y + ")");
		} else {
			this.drawer.setPos(this.objDrag, x, y);
			if (this.objDrag.model) {
				this.objDrag.model.x = x;
				this.objDrag.model.y = y;
				this.objDrag.model._parent.resize(this.model);
			}
		}
	}
};
Graph.prototype.stopDrag = function (event) {
	if (!this.objDrag) {
		return;
	}
	if (!(event.type === "mouseup" || event.type === "mouseout") && !event.currentTarget.isdraggable) {
		return;
	}
	if (event.type === "mouseout") {
		var x = this.isIE ? window.event.clientX : event.pageX;
		var y = this.isIE ? window.event.clientY : event.pageY;
		if (x < this.board.offsetWidth && y < this.board.offsetHeight) {
			return;
		}
	}
	var item = this.objDrag;
	this.objDrag = null;
	var graph = item.parentElement;
	var i;
	if (graph) {
		for (i = 0; i < graph.children.length; i += 1) {
			this.setSelectable(graph.children[i], null);
		}
	}
	var parent = item.parentElement;
	if (item.model) {
		if (this.model.options.display === "svg") {
			if (item.getAttributeNS(null, "transform")) {
				var pos = item.getAttributeNS(null, "transform").slice(10, -1).split(' ');
				item.model.x = item.model.x + Number(pos[0]);
				item.model.y = item.model.y + Number(pos[1]);
			}
			item.model._center = new Pos(item.model.x + (item.model.width / 2), item.model.y + (item.model.height / 2));
			parent.removeChild(item);
			if (item.model.board) {
				item.model.board = null;
			}

			if (item.model.typ === "Info") {
				item.model.custom = true;
				item.model.edge.removeElement(item);
				var infoTxt = item.model.edge.getInfo(item.model);
				item.model.edge.drawText(this.board, this.drawer, infoTxt, item.model);
			} else {
				item.model._gui = this.drawer.getNode(item.model, true);
				if (item.model._gui) {
					parent.appendChild(item.model._gui);
				}
				for (i = 0; i < item.model._edges.length; i += 1) {
					var e = item.model._edges[i];
					e.source.custom = false;
					e.target.custom = false;
				}
			}
		}
		var n = item.model._parent;
		var resize = n;
		while (resize) {
			this.resize(resize);
			resize = resize._parent;
		}
		if (n._parent) {
			this.redrawNode(n, true);
			this.resize(this.model);
		} else {
			this.resize(n);
		}
	}
};
Graph.prototype.redrawNode = function (node, draw) {
	var parent = node._gui.parentElement;
	parent.removeChild(node._gui);
	if (node.board) {
		node.board = null;
	}
	if (node.typ === "Info") {
		var infoTxt = node.edge.getInfo(node.node);
		node.edge.drawText(this.board, this.drawer, infoTxt, node.node);
	} else {
		node._gui = this.drawer.getNode(node, draw);
		if (node._gui) {
			parent.appendChild(node._gui);
		}
	}
	node._center = new Pos(node.x + (node.width / 2), node.y + (node.height / 2));
	this.resize(node._parent);
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
		this.drawer = new HTMLDrawer();
	} else if (typ === "svg") {
		this.drawer = new SVGDrawer();
	}
	this.board = this.drawer.createContainer(this);
	this.model._gui = this.board;
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
Graph.prototype.utf8_to_b64 = function (str) {
	return window.btoa(unescape(encodeURIComponent(str)));
};
Graph.prototype.ExportPDF = function () {
	var pdf = new jsPDF('l', 'px', 'a4');
	var converter = new svgConverter(this.board, pdf, {removeInvalid: false});
	pdf.save('Download.pdf');
};
Graph.prototype.ExportEPS = function () {
	var doc = new jsEPS({inverting: true});
	var converter = new svgConverter(this.board, doc);
	doc.save();
};
Graph.prototype.ExportPNG = function () {
	var image = new Image();
	image.src = 'data:image/svg+xml;base64,' + this.utf8_to_b64(this.serializeXmlNode(this.board));
	image.onload = function () {
		var canvas = document.createElement('canvas');
		canvas.width = image.width;
		canvas.height = image.height;
		var context = canvas.getContext('2d');
		context.drawImage(image, 0, 0);
		var a = document.createElement('a');
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
Graph.prototype.Save = function (typ, data, name) {
	var a = document.createElement("a");
	a.href = window.URL.createObjectURL(new Blob([data], {type: typ}));
	a.download = name;
	a.click();
};
Graph.prototype.ExportHTML = function () {
	var json = this.model.toJson();
	var data = "<html><head>" + document.head.innerHTML.trim() + "</head><body><script>"
		+ "new Graph("  + JSON.stringify(json, null, "\t") + ").layout();<" + "/script></body></html>";
	this.Save("text/json", data, "download.html");
};
//				######################################################### GraphLayout-Dagre #########################################################
var DagreLayout = function () {};
DagreLayout.prototype.layout = function (graph, node, width, height) {
	var graphOptions = node.copy({directed: false}, node.options.layout);
	var g = new dagre.graphlib.Graph(graphOptions);
	g.setGraph(graphOptions);
	g.setDefaultEdgeLabel(function () { return {}; });
	var i, n, nodes = node.nodes;
	for (i in nodes) {
		if (!nodes.hasOwnProperty(i) || typeof (nodes[i]) === "function") {
			continue;
		}
		n = nodes[i];
		g.setNode(n.id, {label: n.id, width: n.width, height: n.height, x: n.x, y: n.y});
	}
	for (i = 0; i < node.edges.length; i += 1) {
		var e = node.edges[i];
		g.setEdge(this.getRootNode(e._sNode).id, this.getRootNode(e._tNode).id);
	}
	dagre.layout(g);
	// Set the layouting back
	for (i in nodes) {
		if (!nodes.hasOwnProperty(i) || typeof (nodes[i]) === "function") {
			continue;
		}
		n = nodes[i];
		var layoutNode = g.node(n.id);
		n.x = Math.round(layoutNode.x - (n.width / 2));
		n.y = Math.round(layoutNode.y - (n.height / 2));
	}
	graph.draw(node, width, height);
};
DagreLayout.prototype.getRootNode = function (node, child) {
	if (node._parent) {
		return this.getRootNode(node._parent, node);
	}
	if (!child) {
		return node;
	}
	return child;
};
//				######################################################### Loader #########################################################
var Loader = function (graph) {this.images = []; this.graph = graph; this.abort = false; };
Loader.prototype.execute = function () {
	if (this.images.length === 0) {
		this.graph.layout(this.width, this.height);
	} else {
		var img = this.images[0];
		this.graph.root.appendChild(img);
	}
};
Loader.prototype.onLoad = function (e) {
	var img = e.target;
	var idx = this.images.indexOf(img);
	img.model.width = img.width;
	img.model.height = img.height;
	this.graph.root.removeChild(img);
	if (idx !== -1) {
		this.images.splice(idx, 1);
	}
	this.execute();
};
Loader.prototype.add = function (img) {
	//img.crossOrigin = 'anonymous';
	var that = this;
	var func = function (e) {that.onLoad(e); };
	this.graph.bind(img, "load", func);
	this.images.push(img);
	this.execute();
};
//				######################################################### LINES #########################################################
var Edge = function () {this.init(); this.typ = "EDGE"; };
Edge.prototype.init = function () {
	this._path = [];
	this._sNode = null;
	this._tNode = null;
	this._gui = [];
	this._m = 0;
	this._n = 0;
	this._lineStyle = Line.Format.SOLID;
};
Edge.Layout = { DIAG : 1, RECT : 0 };
Edge.Position = {UP: "UP", LEFT: "LEFT", RIGHT: "RIGHT", DOWN: "DOWN"};
Edge.prototype.set = function (id, value) {if (value) {this[id] = value; } };
Edge.prototype.addElement = function (board, element) {
	if (element) {this._gui.push(element); board.appendChild(element); }
};
Edge.prototype.removeElement = function (element) {
	if (element) {
		var i;
		for (i = 0; i < this._gui.length; i += 1) {
			if (this._gui[i] === element) {
				this._gui.splice(i, 1);
				i -= 1;
			}
		}
	}
};
Edge.prototype.removeFromBoard = function (board) {
	if (this._gui) {
		while (this._gui.length > 0) {
			board.removeChild(this._gui.pop());
		}
	}
};
Edge.prototype.calculate = function () {
	this._sNode._center = new Pos(this._sNode.getX() + (this._sNode.width / 2), this._sNode.getY() + (this._sNode.height / 2));
	this._tNode._center = new Pos(this._tNode.getX() + (this._tNode.width / 2), this._tNode.getY() + (this._tNode.height / 2));
	var divisor = (this._tNode._center.x - this._sNode._center.x);
	var sourcePos, targetPos;
	var edgePos = this.edgePosition() * 20;
	this._path = [];

	var source = this.getTarget(this._sNode, this._sNode), target = this.getTarget(this._tNode, this._tNode);
	if (divisor === 0) {
		if (this._sNode === this._tNode) {
			/* OwnAssoc */
			return false;
		}
		// Must be UP_DOWN or DOWN_UP
		if (this._sNode._center.y < this._tNode._center.y) {
			// UP_DOWN
			sourcePos = this.getCenterPosition(source, Edge.Position.DOWN, edgePos);
			targetPos = this.getCenterPosition(target, Edge.Position.UP, edgePos);
		} else {
			sourcePos = this.getCenterPosition(source, Edge.Position.UP, edgePos);
			targetPos = this.getCenterPosition(target, Edge.Position.DOWN, edgePos);
		}
	} else {
		// add switch from option or model
		var linetyp = this.linetyp;
		if (!linetyp) {
			var options = this._parent.options;
			if (options) {
				linetyp = options.linetyp;
			}
		}
		var result = false;
		if (linetyp === "Square") {
			result = this.calcSquareLine();
		}
		if (!result) {
			this._m = (target._center.y - source._center.y) / divisor;
			this._n = source._center.y - (source._center.x * this._m);
			sourcePos = this.getPosition(this._m, this._n, source, target._center, edgePos);
			targetPos = this.getPosition(this._m, this._n, target, sourcePos, edgePos);
		}
	}
	if (sourcePos && targetPos) {
		this.calcInfoPos(sourcePos, source, this.source, edgePos);
		this.calcInfoPos(targetPos, target, this.target, edgePos);
		source["_" + sourcePos.id] += 1;
		target["_" + targetPos.id] += 1;
		this._path.push(new Line(sourcePos, targetPos, this._lineStyle, this.style));
		if (this.info) {
			this.info.x = (sourcePos.x + targetPos.x) / 2;
			this.info.y = (sourcePos.y + targetPos.y) / 2;
		}
	}
	return true;
};
Edge.prototype.addLine = function (x1, y1, x2, y2) {
	var start, end;
	if (!x2 && !y2 && this._path.length > 0) {
		start = this._path[this._path.length - 1].target;
		end = new Pos(x1, y1);
	} else {
		start = new Pos(x1, y1);
		end = new Pos(x2, y2);
	}
	this._path.push(new Line(start, end, this._lineStyle, this.style));
};
Edge.prototype.addLineTo = function (x1, y1, x2, y2) {
	var start, end;
	if (!x2 && !y2 && this._path.length > 0) {
		start = this._path[this._path.length - 1].target;
		end = new Pos(start.x + x1, start.y + y1);
	} else {
		start = new Pos(x1, y1);
		end = new Pos(start.x + x2, start.y + y2);
	}
	this._path.push(new Line(start, end, this._lineStyle, this.style));
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
	if (this._sNode.y - 40 > this._tNode.y + this._tNode.height) { // oberseite von source and unterseite von target
		this.addLineTo(this._sNode.x + this._sNode.width / 2, this._sNode.y, 0, -20);
		this.addLine(this._tNode.x + this._tNode.width / 2, this._tNode.y + this._tNode.height + 20);
		this.addLineTo(0, -20);
		return true;
	}
	if (this._tNode.y - 40 > this._sNode.y + this._sNode.height) { // oberseite von source and unterseite von target
		// fall 1 nur andersherum
		this.addLineTo(this._sNode.x + this._sNode.width / 2, this._sNode.y + this._sNode.height, 0, +20);
		this.addLine(this._tNode.x + this._tNode.width / 2, this._tNode.y - 20);
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
	this.addLineTo(this._sNode.x + this._sNode.width / 2, this._sNode.y, 0, -20);
	this.addLine(this._tNode.x + this._tNode.width / 2, this._tNode.y - 20);
	this.addLineTo(0, 20);
	return true;
//return false;
};
Edge.prototype.draw = function (board, drawer) {
	var i;
	for (i = 0; i < this._path.length; i += 1) {
		var p = this._path[i];
		this.addElement(board, drawer.createLine(p.source.x, p.source.y, p.target.x, p.target.y, p.line, p.style));
	}
	var options = drawer.model.options;
	this.drawSourceText(board, drawer, options);
	if (this.info) {
		var angle = this.drawText(board, drawer, this.info, this.infoPos);
		this.addElement(board, new SymbolLibary().create({typ: "Arrow", x: this.infoPos.x, y: this.infoPos.y, rotate: angle}, drawer));
	}
	this.drawTargetText(board, drawer, options);
};
Edge.prototype.drawText = function (board, drawer, text, pos) {
	if (this._path.length < 1) {
		return;
	}
	var p = this._path[this._path.length - 1];
	var angle = 0;
	var options = drawer.model.model.options;
	if (options.rotatetext) {
		angle = Math.atan((p.source.y - p.target.y) / (p.source.x - p.target.x)) * 60;
	}
	this.addElement(board, drawer.createInfo(pos, text, angle));
	return angle;
};
Edge.prototype.drawSourceText = function (board, drawer) {
	var infoTxt = this.getInfo(this.source);
	if (infoTxt.length > 0) {
		this.drawText(board, drawer, infoTxt, this.source);
	}
};
Edge.prototype.drawTargetText = function (board, drawer) {
	var infoTxt = this.getInfo(this.target);
	if (infoTxt.length > 0) {
		this.drawText(board, drawer, infoTxt, this.target);
	}
};
Edge.prototype.endPos = function () {return this._path[this._path.length - 1]; };
Edge.prototype.edgePosition = function () {
	var pos = 0, i;
	for (i = 0; i < this._sNode._edges.length; i += 1) {
		if (this._sNode._edges[i] === this) {
			return pos;
		}
		if (this._sNode._edges[i].target === this._tNode) {
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
	return this.getTarget(node._parent, startNode);
};
Edge.prototype.getCenterPosition = function (node, pos, offset) {
	offset = offset || 0;
	if (pos === Edge.Position.DOWN) {
		return new Pos(node._center.x + offset, (node.y + node.height), Edge.Position.DOWN);
	}
	if (pos === Edge.Position.UP) {
		return new Pos(node._center.x + offset, node.y, Edge.Position.UP);
	}
	if (pos === Edge.Position.LEFT) {
		return new Pos(node.x, node._center.y + offset, Edge.Position.LEFT);
	}
	if (pos === Edge.Position.RIGHT) {
		return new Pos(node.x + node.width, node._center.y + offset, Edge.Position.RIGHT);
	}
};
Edge.prototype.getInfo = function (info) {
	var infoTxt = "";
	var isCardinality = this._parent.typ === "classdiagram" && this._parent.options.CardinalityInfo;
	var isProperty = this._parent.options.propertyinfo;

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
	var offset = 20;
	this._start = this.getFree(this._sNode);
	if (this._start.length > 0) {
		this._end = this.getFreeOwn(this._sNode, this._start);
	} else {
		this._start = Edge.Position.RIGHT;
		this._end = Edge.Position.DOWN;
	}

	var sPos = this.getCenterPosition(this._sNode, this._start);
	var tPos;
	if (this._start === Edge.Position.UP) {
		tPos = new Pos(sPos.x, sPos.y - offset);
	} else if (this._start === Edge.Position.DOWN) {
		tPos = new Pos(sPos.x, sPos.y + offset);
	} else if (this._start === Edge.Position.RIGHT) {
		tPos = new Pos(sPos.x + offset, sPos.y);
	} else if (this._start === Edge.Position.LEFT) {
		tPos = new Pos(sPos.x - offset, sPos.y);
	}
	this._path.push(new Line(sPos, tPos, this._lineStyle));
	if (this._end === Edge.Position.LEFT || this._end === Edge.Position.RIGHT) {
		if (this._start === Edge.Position.LEFT) {
			sPos = tPos;
			tPos = new Pos(sPos.x, this._sNode.y - offset);
			this._path.push(new Line(sPos, tPos, this._lineStyle));
		} else if (this._start === Edge.Position.RIGHT) {
			sPos = tPos;
			tPos = new Pos(sPos.x, this._sNode.y + offset);
			this._path.push(new Line(sPos, tPos, this._lineStyle));
		}
		sPos = tPos;
		if (this._end === Edge.Position.LEFT) {
			tPos = new Pos(this._sNode.x - offset, sPos.y);
		} else {
			tPos = new Pos(this._sNode.x + this._sNode.width + offset, sPos.y);
		}
		this._path.push(new Line(sPos, tPos, this._lineStyle));
		sPos = tPos;
		tPos = new Pos(sPos.x, this._sNode._center.y);
		this._path.push(new Line(sPos, tPos, this._lineStyle));
		if (this.info) {
			this.info.x = (sPos.x + tPos.x) / 2;
			this.info.y = sPos.y;
		}
	} else if (this._end === Edge.Position.UP || this._end === Edge.Position.DOWN) {
		if (this._start === Edge.Position.UP) {
			sPos = tPos;
			tPos = new Pos(this._sNode.x + this._sNode.width + offset, sPos.y);
			this._path.push(new Line(sPos, tPos, this._lineStyle));
		} else if (this._start === Edge.Position.DOWN) {
			sPos = tPos;
			tPos = new Pos(this._sNode.x - offset, sPos.y);
			this._path.push(new Line(sPos, tPos, this._lineStyle));
		}
		sPos = tPos;
		if (this._end === Edge.Position.UP) {
			tPos = new Pos(sPos.x, this._sNode.y - offset);
		} else {
			tPos = new Pos(sPos.x, this._sNode.y + this._sNode.height + offset);
		}
		this._path.push(new Line(sPos, tPos, this._lineStyle));
		sPos = tPos;
		tPos = new Pos(this._sNode._center.x, sPos.y);
		this._path.push(new Line(sPos, tPos, this._lineStyle));
		if (this.info) {
			this.info.x = sPos.x;
			this.info.y = (sPos.y + tPos.y) / 2;
		}
	}
	sPos = tPos;
	this._path.push(new Line(sPos, this.getCenterPosition(this._sNode, this._end), this._lineStyle));
};
Edge.prototype.getFree = function (node) {
	var i;
	for (i in Edge.Position) {
		if (node.hasOwnProperty("_" + i) && node["_" + i] === 0) {
			node["_" + i] = 1;
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
	if (node["_" + list[id + 1]] === 0 || node["_" + list[id + 1]] < node["_" + list[id + 3]]) {
		node["_" + list[id + 1]] += 1;
		return list[id + 1];
	}
	node["_" + list[id + 3]] += 1;
	return list[id + 3];
};
Edge.prototype.calcInfoPos = function (linePos, item, info, offset) {
	// Manuell move the InfoTag
	offset = offset || 0;
	var spaceA = 20, spaceB = 0;
	if (item._parent.options && !item._parent.options.rotatetext) {
		spaceA = 20;
		spaceB = 10;
	}
	if (info.custom) {
		return;
	}
	var newY = linePos.y;
	var newX = linePos.x;
	if (linePos._id === Edge.Position.UP) {
		newY = newY - info.height - offset - spaceA;
		if (this._m !== 0) {
			newX = (newY - this._n) / this._m + spaceB;
		}
	} else if (linePos._id === Edge.Position.DOWN) {
		newY = newY + offset + spaceA;
		if (this._m !== 0) {
			newX = (newY - this._n) / this._m + spaceB;
		}
	} else if (linePos._id === Edge.Position.LEFT) {
		newX = newX - info.width - offset - spaceA;
		if (this._m !== 0) {
			newY = (this._m * newX) + this._n;
		}
	} else if (linePos._id === Edge.Position.RIGHT) {
		newX += offset + spaceA;
		if (this._m !== 0) {
			newY = (this._m * newX) + this._n;
		}
	}
	info.x = Math.round(newX);
	info.y = Math.round(newY);
};
Edge.prototype.getPosition = function (m, n, entity, refCenter, offset) {
	if (!offset) {
		offset = 0;
	}
	var x, y;
	var pos = [];
	var distance = [];
	x = entity.getX() + entity.width;
	y = m * x + n;
	if (y >= entity.getY() && y <= (entity.getY() + entity.height)) {
		pos.push(new Pos(x, y + offset, Edge.Position.RIGHT));
		distance.push(Math.sqrt((refCenter.x - x) * (refCenter.x - x) + (refCenter.y - y) * (refCenter.y - y)));
	}
	y = entity.getY();
	x = (y - n) / m;
	if (x >= entity.getX() && x <= (entity.getX() + entity.width)) {
		pos.push(new Pos(x + offset, y, Edge.Position.UP));
		distance.push(Math.sqrt((refCenter.x - x) * (refCenter.x - x) + (refCenter.y - y) * (refCenter.y - y)));
	}
	x = entity.getX();
	y = m * x + n;
	if (y >= entity.getY() && y <= (entity.getY() + entity.height)) {
		pos.push(new Pos(x, y + offset, Edge.Position.LEFT));
		distance.push(Math.sqrt((refCenter.x - x) * (refCenter.x - x) + (refCenter.y - y) * (refCenter.y - y)));
	}
	y = entity.getY() + entity.height;
	x = (y - n) / m;
	if (x >= entity.getX() && x <= (entity.getX() + entity.width)) {
		pos.push(new Pos(x + offset, y, Edge.Position.DOWN));
		distance.push(Math.sqrt((refCenter.x - x) * (refCenter.x - x) + (refCenter.y - y) * (refCenter.y - y)));
	}
	var min = 999999999, position, i;
	for (i = 0; i < pos.length; i += 1) {
		if (distance[i] < min) {
			min = distance[i];
			position = pos[i];
		}
	}
	return position;
};
Edge.prototype.calcMoveLine = function (size, angle, move) {
	var startArrow	= this.endPos().source;
	this._end = this.endPos().target;
	// calculate the angle of the line
	var lineangle = Math.atan2(this._end.y - startArrow.y, this._end.x - startArrow.x);
	// h is the line length of a side of the arrow head
	var h = Math.abs(size / Math.cos(angle));
	var angle1 = lineangle + Math.PI + angle;
	var hCenter = Math.abs((size / 2) / Math.cos(angle));

	this._top = new Pos(this._end.x + Math.cos(angle1) * h, this._end.y + Math.sin(angle1) * h);
	this._topCenter = new Pos(this._end.x + Math.cos(angle1) * hCenter, this._end.y + Math.sin(angle1) * hCenter);
	var angle2 = lineangle + Math.PI - angle;
	this._bot = new Pos(this._end.x + Math.cos(angle2) * h, this._end.y + Math.sin(angle2) * h);
	this._botCenter = new Pos(this._end.x + Math.cos(angle2) * hCenter, this._end.y + Math.sin(angle2) * hCenter);
	if (move) {
		this.endPos().target = new Pos((this._top.x + this._bot.x) / 2, (this._top.y + this._bot.y) / 2);
	}
};
var Generalisation = function () { this.init(); this.typ = "Generalisation"; };
Generalisation.prototype = Object_create(Edge.prototype);
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
	this.addElement(board, drawer.createLine(this._top.x, this._top.y, this._end.x, this._end.y, this._lineStyle));
	this.addElement(board, drawer.createLine(this._bot.x, this._bot.y, this._end.x, this._end.y, this._lineStyle));
	this.addElement(board, drawer.createLine(this._top.x, this._top.y, this._bot.x, this._bot.y, this._lineStyle));
};
Generalisation.prototype.drawSourceText = function (board, drawer, options) {};
Generalisation.prototype.drawTargetText = function (board, drawer, options) {};

var Implements = function () { this.init(); this.typ = "Implements"; this._lineStyle = Line.Format.DOTTED; };
Implements.prototype = Object_create(Generalisation.prototype);

var Unidirectional = function () { this.init(); this.typ = "Unidirectional"; };
Unidirectional.prototype = Object_create(Generalisation.prototype);
Unidirectional.prototype.calculate = function (board, drawer) {
	if (!this.calculateEdge(board, drawer)) {
		return false;
	}
	this.calcMoveLine(16, 50, false);
	return true;
};
Unidirectional.prototype.draw = function (board, drawer) {
	this.drawSuper(board, drawer);
	this.addElement(board, drawer.createLine(this._top.x, this._top.y, this._end.x, this._end.y, this._lineStyle));
	this.addElement(board, drawer.createLine(this._bot.x, this._bot.y, this._end.x, this._end.y, this._lineStyle));
};
var Aggregation = function () { this.init(); this.typ = "Aggregation"; };
Aggregation.prototype = Object_create(Generalisation.prototype);
Aggregation.prototype.calculate = function (board, drawer) {
	if (!this.calculateEdge(board, drawer)) {
		return false;
	}
	this.calcMoveLine(16, 49.8, true);
	return true;
};
Aggregation.prototype.draw = function (board, drawer) {
	this.drawSuper(board, drawer);
	this.addElement(board, drawer.createPath(true, "none", [this.endPos().target, this._topCenter, this._end, this._botCenter]));
};
var Composition = function () { this.init(); this.typ = "Composition"; };
Composition.prototype = Object_create(Aggregation.prototype);
Composition.prototype.draw = function (board, drawer) {
	this.drawSuper(board, drawer);

	var start = this.endPos().source;
	var end = this.endPos().target;
	var a = (start.y - end.y) / (end.x - start.x);
	var h = Math.atan(a) * 100;

	this.addElement(board, drawer.createPath(true, "#000", [this.endPos().target, this._topCenter, this._end, this._botCenter], h));
};
function RGBColor(value) {
	this.ok = false;
	if (value === "none") {
		return;
	}
	var div = document.createElement("div");
	div.style.backgroundColor = value;
	document.body.appendChild(div);
	var computedColor = window.getComputedStyle(div).backgroundColor;
	// cleanup temporary div.
	document.body.removeChild(div);
	this.convert(computedColor);
}
RGBColor.prototype.convert = function (value) {
	var regex = /rgb *\( *([0-9]{1,3}) *, *([0-9]{1,3}) *, *([0-9]{1,3}) *\)/;
	var values = regex.exec(value);
	this.r = parseInt(values[1], 10);
	this.g = parseInt(values[2], 10);
	this.b = parseInt(values[3], 10);
	this.ok = true;
};
RGBColor.prototype.toRGB = function () {return 'rgb(' + this.r + ', ' + this.g + ', ' + this.b + ')'; };
RGBColor.prototype.toHex = function () {
	return "#" + (this.r + 0x10000).toString(16).substring(3).toUpperCase() + (this.g + 0x10000).toString(16).substring(3).toUpperCase() + (this.b + 0x10000).toString(16).substring(3).toUpperCase();
};

// TO_DO
// Validate input
// Create Assocs
// Edit Assocs
// Delete Assocs
// Edit Attribute and Methods
// ################################## ClassEditor ####################################################
var ClassEditor = function (element, diagramTyp) {
	this.isIE = document.all && !window.opera;
	this.drawer = new HTMLDrawer();
	this.inputEvent = true;
	this.nodes = {};
	this.model = new GraphModel(this, {buttons: [], typ: diagramTyp});
	if (element) {
		if (typeof (element) === "string") {
			this.board = document.getElementById(element);
			if (!this.board) {
				this.board = this.drawer.createContainer(this);
				this.board.className = "ClassEditor";
				document.body.appendChild(this.board);
			}
		} else {
			this.board = element;
		}
	} else {
		this.board = document.body;
	}
	this.inputNode = new InputNode(this);
	this.editNode = new EditNode(this);
	this.createEdge = new CreateEdge(this);
	this.actions = [ new Selector(this), new MoveNode(this), this.createEdge, new CreateNode(this)];

	var that = this;
	this.bind(this.board, "mousedown", function (e) {that.doAction(e, "startAction"); });
	this.bind(this.board, "mousemove", function (e) {that.doAction(e, "doAction"); });
	this.bind(this.board, "mouseup", function (e) {that.doAction(e, "stopAction"); });
	this.bind(this.board, "mouseout", function (e) {that.doAction(e, "outAction"); });
	this.bind(this.board, "dragover", function (e) {that.dragClass(e); });
	this.bind(this.board, "dragleave", function (e) {that.dragClass(e); });
	this.bind(this.board, "drop", function (e) {that.dropModel(e); });
	this.loadModel(this.model);
};
ClassEditor.prototype = Object_create(GraphUtil.prototype);
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
	var files = e.target.files || e.dataTransfer.files;
	// process all File objects
	if (!files || files.length < 1) {
		return;
	}
	var error = true, i, f;
	for (i = 0; i < files.length; i += 1) {
		f = files[i];
		if (f.type.indexOf("text") === 0) {
			error = false;
		} else if (f.type === "") {
			var n = f.name.toLowerCase();
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
	this.dragStyler(e, "dragleave");
	
	var data = event.dataTransfer.getData("Text");
	if(data) {
		var x = this.getEventX(e);
		var y = this.getEventY(e);
		this.getAction("CreateNode").setValue(x, y, x+100, y+100);
		return;
	}

	var i, f, files = e.target.files || e.dataTransfer.files;
	var that = this;
	var func = function (r) { that.loadModel(JSON.parse(r.target.result), e.ctrlKey, f); };
	for (i = 0; i < files.length; i += 1) {
		f = files[i];
		var load = f.type.indexOf("text") === 0;
		if (!load && f.type === "") {
			var n = f.name.toLowerCase();
			if (n.indexOf("json", n.length - 4) !== -1) {
				load = true;
			}
		}
		if (load) {
			e.stopPropagation();
			// file.name
			var reader = new FileReader();
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
	var result = {};
	this.copy(result, this.model);
	var data = JSON.stringify(result, null, "\t");
	if (java !== undefined) {
		java.save(data);
	} else {
		this.download("text/json", data, "model.json");
	}
};
ClassEditor.prototype.generate = function () {
	var result = this.minJson({}, this.model);
	var data = JSON.stringify(result, null, "\t");
	java.generate(data);
};
ClassEditor.prototype.close = function () {
	java.exit();
};
ClassEditor.prototype.loadModel = function (model, addFile, file) {
	var that = this;
	if (!addFile) {
		this.model = new GraphModel(that, {buttons: []});
		//this.model = that.copy(newModel, model);
	}
	this.getAction("Selector").setNode(null);
	var i;
	for (i = this.board.children.length - 1; i >= 0; i -= 1) {
		this.board.removeChild(this.board.children[i]);
	}
	for (i in this.model.nodes) {
		this.addNode(this.model.nodes[i]);
	}
	for (i in model.nodes) {
		this.addNode(model.nodes[i]);
	}
	this.toolbar = this.create({tag: "div", id: "toolbar", "class": "Toolbar", style: "width:6px;height:120px", onMouseOver: function () {that.maxToolbar(); }, onMouseOut: function (e) {that.minToolbar(e); }, _parent: this.board});

	this.itembar = this.create({tag: "div", id: "itembar", "class": "Itembar", style: "width:6px;height:200px", onMouseOver: function () {that.maxItembar(); }, onMouseOut: function (e) {that.minItembar(e); }, _parent: this.board});
	this.codeView = this.create({tag: "div", "class": "CodeView", _parent: this.board});
	this.create({tag: "div", "class": "pi", _parent: this.codeView, value: "&pi;", onMouseOver: function () {that.maxCodeView(); }, onMouseOut: function (e) {that.minCodeView(e); }});
};
ClassEditor.prototype.maxCodeView = function () {
	if (this.codeViewer) {return; }
	var result = this.minJson({}, this.model);
	var data = JSON.stringify(result, null, "    ");
	data = data.replace(new RegExp("\n", 'g'), "<br/>").replace(new RegExp(" ", 'g'), "&nbsp;");

	var html = this.create({tag: "div", style: "position:absolute;", value: data});
	this.board.appendChild(html);
	var rect = html.getBoundingClientRect();
	this.board.removeChild(html);
	this.codeViewer = this.create({tag: "div", "class": "code_box", style: {width: rect.width, height: rect.height}, _parent: this.board, value: data});
};
ClassEditor.prototype.minCodeView = function () {
	if (!this.codeViewer) {
		return;
	}
	this.board.removeChild(this.codeViewer);
	this.codeViewer = null;
};
ClassEditor.prototype.createCell = function (node, table) {
	var tr = this.create({tag: 'tr', _parent: table});
	node["_parent"] = tr;
	return this.create(node);
};
ClassEditor.prototype.maxToolbar = function () {
	if (this.toolbar.clientWidth > 100) {
		return;
	}
	var that = this;

	this.toolbar.minWidth = this.toolbar.clientWidth;
	this.toolbar.style.width = 300;
	var table = this.create({tag: "table", _parent: this.toolbar});
	this.createCell({"tag": "th", colspan: 2, value: "Properties"}, table);

	var tr = this.create({tag: 'tr', _parent: table});
	this.create({"tag": "td", value: "Workspace:", _parent: tr});
	var cell = this.create({"tag": "td", _parent: tr});
	this.createInputField({value: this.model["package"], _parent: cell, onChange: function (e) {that.savePackage(e); }});

	cell = this.createCell({"tag": "td", colspan: 2, style: "text-align:right;padding:10px 10px 0 0"}, table);
	this.create({tag: 'button', _parent: cell, style: "margin-left:10px;", value: "Save", onClick: function () {that.save(); }});
	if (typeof(java) != "undefined") {
		this.create({tag: 'button', _parent: cell, style: "margin-left:10px;", value: "Generate", onClick: function () {that.generate(); }});
		this.create({tag: 'button', _parent: cell, style: "margin-left:10px;", value: "Exit", onClick: function () {that.close(); }});
	}
};
ClassEditor.prototype.maxItembar = function () {
	if (this.itembar.clientWidth > 10) {
		return;
	}
	var that = this;

	this.itembar.minWidth = this.itembar.clientWidth;
	this.itembar.style.width = 80;

	var table = this.create({tag: "table", style: "padding-left:10px", _parent: this.itembar});
	this.createCell({"tag": "th", value: "Item"}, table);
	var th = this.createCell({"tag": "th"}, table);
	var item = this.create({"tag": "table", id: "node", draggable: "true", cellspacing: "0", ondragstart: function (e) {that.startDrag(e);}, style: "border:1px solid #000;width:30px;height:30px;cursor: pointer", _parent: th});
	this.createCell({"tag": "td", style: "height:10px;border-bottom:1px solid #000;"}, item);
	this.createCell({"tag": "td"}, item);
	var node = this.getAction("Selector").node

	if(node) {
		th = this.createCell({"tag": "th"}, table);
		this.create({tag: "button", id: "Attribute", value: "Attribute", onclick: function(e){that.executeClassAdd(e);}, "style": "margin-top:5px;", _parent: th});
		this.create({tag: "button", id: "Method", value: "Method", onclick: function(e){that.executeClassAdd(e);}, "style": "margin-top:5px;", _parent: th});
	}
};
ClassEditor.prototype.executeClassAdd = function (e) {
	var node = this.getAction("Selector").node
	if(e.target.id === "Attribute") {
		this.inputNode.accept("attribute:Object", node);
	} else if(e.target.id === "Method") {
		this.inputNode.accept("methods()", node);
	}

};
ClassEditor.prototype.startDrag = function (e) {e.dataTransfer.setData("Text", e.target.id);}
ClassEditor.prototype.createInputField = function (option) {
	var that = this;
	var node = this.copy({tag: "input", type: "text", width: "100%", onFocus: function () {that.inputEvent = false; }, onBlur: function () {that.inputEvent = true; }}, option);
	if (option._parent) {
		node._parent = option._parent;
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
	if(element == null) {
		return false;
	}
	if (element.id === id) {
		return true;
	}
	if (element.parentElement) {
		return this.getId(element.parentElement, id);
	}
	return false;
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
	var i, html = null;
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

	var size = this.drawer.getSize(html);
	node._minWidth = size.x;
	node._minHeight = size.y;
	this.drawer.setSize(html, Math.max(Number(node.width), Number(node._minWidth)), Math.max(Number(node.height), Number(node._minHeight)));

	var that = this;
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
			this.sizeHTML(this.drawer.createInfo(e.source, infoTxt, 0), e.source);
		}
		infoTxt = e.getInfo(e.target);
		if (infoTxt.length > 0) {
			this.sizeHTML(this.drawer.createInfo(e.target, infoTxt, 0), e.target);
		}
	}
	this.model.calcLines(this.drawer);
	for (i = 0; i < this.model.edges.length; i += 1) {
		e = this.model.edges[i];
		this.model.edges[i].draw(this.board, this.drawer);
	}
};

ClassEditor.prototype.removeCurrentNode = function () {
	var selector = this.getAction("Selector");
	var item = selector.node;
	if (item) {
		selector.removeAll();
		this.board.removeChild(item);
		var i, n = item.model;
		for (i = 0; i < this.model.nodes.length; i += 1) {
			if (this.model.nodes[i].id === n.id) {
				this.model.nodes.splice(i - 1, 1);
				i -= 1;
			}
		}
	}
};
// ################################## CREATE ####################################################
var CreateNode = function (parent) {
	this.name = "CreateNode";
	this._parent = parent;
	this.minSize = 20;
	this.offset = new Pos();
	this.mouse = new Pos();
	this.createClass = false;
};
CreateNode.prototype = Object_create(GraphUtil.prototype);
CreateNode.prototype.startAction = function (event) {
	if (event.button === 2) {return; }
	if (event.target !== this._parent.board) {return; }
	this.createClass = true;
	this.offset.x = this.mouse.x = this.getEventX(event);
	this.offset.y = this.mouse.y = this.getEventY(event);
	return true;
};
CreateNode.prototype.doAction = function (event) {
	if (!this.createClass) {return; }
	this.mouse.x = this.getEventX(event);
	this.mouse.y = this.getEventY(event);
	this.createNode();
};
CreateNode.prototype.setValue = function (x1, y1, x2, y2) {
	this.offset.x = x1;
	this.offset.y = y1;
	this.mouse.x = x2;
	this.mouse.y = y2;
	this.createNode();
};
CreateNode.prototype.createNode = function () {
	var width = Math.abs(this.mouse.x - this.offset.x);
	var height = Math.abs(this.mouse.y - this.offset.y);
	if (width > this.minSize && height > this.minSize) {
		if (!this.newClass) {
			this.newClass = this.create({tag: "div", style: "position:absolute;opacity: 0.2;background-color:#ccc;"});
			this._parent.board.appendChild(this.newClass);
		}
		this.newClass.style.width = width;
		this.newClass.style.height = height;
		this.newClass.style.left = Math.min(this.mouse.x, this.offset.x);
		this.newClass.style.top = Math.min(this.mouse.y, this.offset.y);
	} else {
		if (this.newClass) {
			this._parent.board.removeChild(this.newClass);
			this.newClass = null;
		}
	}
	return true;
};
CreateNode.prototype.outAction = function (event) {return this.stopAction(event); };
CreateNode.prototype.stopAction = function () {
	this.createClass = false;
	if (!this.newClass) {
		return false;
	}
	var node = {"typ": "node", "id": "Class" + (this._parent.model._nodeCount + 1)};
	node.x = this.getValue(this.newClass.style.left);
	node.y = this.getValue(this.newClass.style.top);
	node.width = this.getValue(this.newClass.style.width);
	node.height = this.getValue(this.newClass.style.height);

	this._parent.board.removeChild(this.newClass);
	this.newClass = null;
	this._parent.addNode(node);
	return true;
};

// ################################## SELECTOR ####################################################
var Selector = function (parent) {
	this.name = "Selector";
	this._parent = parent;
	this.size = 6;
	this.nodes = {};
	this.mouse = new Pos();
	this.offset = new Pos();
	this.resizeNode = null;
};
Selector.prototype = Object_create(GraphUtil.prototype);
Selector.prototype.start = function (e) {
	this.resizeNode = e.target.id;
	this.sizeNode = new Pos(this.node.model.width, this.node.model.height);
	this.offset.x = this.mouse.x = this.getEventX(e);
	this.offset.y = this.mouse.y = this.getEventY(e);
};
Selector.prototype.doit = function (e) {
	if (!this.resizeNode) {
		return;
	}
	this.mouse.x = this.getEventX(e);
	this.mouse.y = this.getEventY(e);

	var multiX = 1, multiY = 1;
	if (this.resizeNode.charAt(0) === "n") {
		multiY = -1;
	}
	if (this.resizeNode.indexOf("w") >= 0) {
		multiX = -1;
	}
	var n = this.node.model;
	var newWidth = Math.max(n._minWidth, this.sizeNode.x + (this.mouse.x - this.offset.x) * multiX);
	var newHeight = Math.max(n._minHeight, this.sizeNode.y + (this.mouse.y - this.offset.y) * multiY);

	var diffX = 0;
	var diffY = 0;
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
Selector.prototype.stop = function () {this.resizeNode = null; };
Selector.prototype.removeAll = function () {
	var i;
	for (i in this.nodes) {
		if (!this.nodes.hasOwnProperty(i)) {
			continue;
		}
		var select = this.nodes[i];
		this._parent.board.removeChild(select);
	}
	this.nodes = {};
};

Selector.prototype.setNode = function (node) {
	if (this.node) {
		this.removeAll();
	}
	this.node = node;
	this.refreshNode();
};
Selector.prototype.refreshNode = function () {
	if (!this.node) {
		return;
	}
	var x = this.getValue(this.node.style.left);
	var y = this.getValue(this.node.style.top);
	var width = this.getValue(this.node.clientWidth);
	var height = this.getValue(this.node.clientHeight);
	var s = this.size + 1;
	var sh = this.size / 2 + 1;
	this.selector("nw", x - s, y - s);
	this.selector("n", x + (width / 2) - sh, y - s);
	this.selector("ne", x + width + 1, y - s);
	this.selector("w", x - s, y + height / 2 - sh);
	this.selector("sw", x - s, y + height + 1);
	this.selector("s", x + (width / 2) - sh, y + height + 1);
	this.selector("se", x + width + 1, y + height + 1);
	this.selector("e", x + width + 1, y + height / 2 - sh);
};

Selector.prototype.selector = function (id, x, y) {
	var n = this.nodes[id];
	if (!n) {
		n = this.create({tag: "div", "id": id, style: "position:absolute;background:#00F;width:" + this.size + "px;height:" + this.size + "px;cursor:" + id + "-resize;"});
		this.nodes[id] = n;
		var that = this;
		this.bind(n, "mousedown", function (e) {that.start(e); });
		this.bind(n, "mousemove", function (e) {that.doit(e); });
		this.bind(n, "mouseup", function (e) {that.stop(e); });
		this._parent.board.appendChild(n);
	}
	n.style.left = x;
	n.style.top = y;
};
Selector.prototype.startAction = function () {
	if (!this.node) {
		return false;
	}
};
Selector.prototype.doAction = function (event) {
	if (!this.resizeNode) {
		return false;
	}
	this.doit(event);
	return true;
};

Selector.prototype.stopAction = function () {
	if (this.resizeNode) {
		this.resizeNode = false;
		return true;
	}
	return false;
};

// ################################## SELECTOR ####################################################
var MoveNode = function (parent) { this.name = "MoveNode"; this._parent = parent; this.mouse = new Pos(); this.offset = new Pos(); };
MoveNode.prototype = Object_create(GraphUtil.prototype);
MoveNode.prototype.callBack = function (typ, e) {
	if (typ === "id") {
		var th = e.target;
		var that = this;
		this.bind(th, "mousedown", function (e) {that.start(e); });
		this.bind(th, "mousemove", function (e) {that.doit(e); });
		this.bind(th, "mouseup", function (e) {that.stop(e); });
	}
};
MoveNode.prototype.start = function (e) {
	this.node = this.getModelNode(e.target).model;
	this.posNode = new Pos(this.node.x, this.node.y);
	// SAVE ID
	this.offset.x = this.mouse.x = this.getEventX(e);
	this.offset.y = this.mouse.y = this.getEventY(e);
};
MoveNode.prototype.doAction = function () {return this.node; };
MoveNode.prototype.doit = function (e) {
	if (!this.node) {
		return;
	}
	this.mouse.x = this.getEventX(e);
	this.mouse.y = this.getEventY(e);

	var newX = this.posNode.x + (this.mouse.x - this.offset.x);
	var newY = this.posNode.y + (this.mouse.y - this.offset.y);

	this.node.x = this.node._gui.style.left = newX;
	this.node.y = this.node._gui.style.top = newY;
	this._parent.getAction("Selector").refreshNode();
};
MoveNode.prototype.stop = function () {
	this.node = null;
	this._parent.drawlines();
};

// ################################## InputNode ####################################################
var InputNode = function (parent) {this.name = "InputNode"; this._parent = parent;
	var that = this;
	document.body.addEventListener("keyup", function (e) {
		that.keyup(e);
	});
	};
InputNode.prototype = Object_create(GraphUtil.prototype);
InputNode.prototype.keyup = function (e) {
	if (!this._parent.inputEvent) {
		return;
	}
	var x = e.keyCode;
	if (e.altKey || e.ctrlKey) {
		return;
	}
	if (x === 46) {
		this._parent.removeCurrentNode();
	}
	if ((x > 64 && x < 91) && !e.shiftKey) {
		x += 32;
	}
	if ((x > 64 && x < 91) || (x > 96 && x < 123) || (x > 127 && x < 155) || (x > 159 && x < 166)) {
		var selector = this._parent.getAction("Selector");
		var item = selector.node;
		if (item && !this.inputItem) {
			var m = item.model;
			this.inputItem = this._parent.create({tag: "input", type: "text", "#node": item, "value": String.fromCharCode(x), style: "position:absolute;left:" + m.x + "px;top:" + (m.y + m.height) + "px;width:" + m.width});
			this._parent.board.appendChild(this.inputItem);
			this.choiceBox = new ChoiceBox(this.inputItem, this._parent);
			var that = this;
			this.inputItem.addEventListener("keyup", function (e) {
				that.changeText(e);
			});
			this.inputItem.focus();
		}
	}
};
InputNode.prototype.accept = function (text, n) {
	var model = n.model;
	var id = n.model.id;
	if( this.addValue(text, model) ) {
		if (id !== n.model.id) {
			this._parent.removeNode(id);
			this._parent.addNode(n.model);
		} else {
			this._parent.board.removeChild(n);
			this._parent.addNode(n.model);
		}
		this._parent.getAction("Selector").refreshNode();
		return true;
	}
	return false;
};

InputNode.prototype.addValue = function (text, model) {
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
	if (model._parent.typ === "classdiagram") {
		model.id = this.fristUp(text);
	} else {
		model.id = text;
	}
	return true;
};
InputNode.prototype.fristUp = function (string) {
	return string.charAt(0).toUpperCase() + string.slice(1);
};
InputNode.prototype.changeText = function (e) {
	if (!this.inputItem) {
		return;
	}
	var close = false;
	if (e.keyCode === 27) {close = true; }
	if (e.keyCode === 13) {
		var n = this.inputItem.node;
		var id = n.model.id;
		var text = this.inputItem.value;
		if (this.accept(text, n)) {
			close = true;
		}
	}
	if (close) {
		this._parent.board.removeChild(this.inputItem);
		this.inputItem = null;
		if (this.choiceBox && this.choiceBox.div) {
			this.graph.board.removeChild(this.choiceBox.div);
			this.choiceBox.div = null;
			this.choiceBox = null;
		}
	}
};
// ################################## ChoiceBox ####################################################
var ChoiceBox = function (field, graph) {
	this.field = field;
	this.graph = graph;
	this.list = [];
	var that = this;
	this.bind(field, "keyup", function (e) {that.change(e); });
};
ChoiceBox.prototype = Object_create(GraphUtil.prototype);
ChoiceBox.prototype.initAttributes = function () {
	this.list = ["Boolean", "Byte", "Character", "Double", "Float", "Integer", "Long", "Number", "Object", "Short", "String"];
	this.addFromGraph(this.graph.model, "nodes.id");
	this.list.sort();
};
ChoiceBox.prototype.addFromGraph = function (item, filter) {
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
ChoiceBox.prototype.change = function (e) {
	if (this.div) {
		this.graph.board.removeChild(this.div);
		this.div = null;
	}
	if (e.keyCode === 27 || e.keyCode === 13) {
		return;
	}
	var t = e.target.value.toLowerCase();
	this.typ = "";
	if (t.indexOf(":") >= 0) {
		this.initAttributes();
		this.typ = ":";
	}
	if (this.typ === "") {
		return;
	}
	t = t.substring(t.lastIndexOf(this.typ) + 1);
	var that = this, i, div = this.create({tag: "div", "class": "ChoiceBox", style: "left:" + this.field.style.left + ";top:" + (this.getValue(this.field.style.top) + this.field.clientHeight + 4) + ";width:" + this.field.clientWidth});
	var func = function () {that.select(this); };
	for (i = 0; i < this.list.length; i += 1) {
		if (this.list[i].toLowerCase().indexOf(t) >= 0) {
			if (i % 2 === 0) {
				this.create({tag: "div", value: this.list[i], _parent: div, onMouseup: func});
			} else {
				this.create({tag: "div", value: this.list[i], "class": "alt", _parent: div, onMouseup: func});
			}
		}
	}
	if (div.children.length > 0) {
		this.div = div;
		this.graph.board.appendChild(div);
	}
};
ChoiceBox.prototype.select = function (input) {
	var pos = this.field.value.lastIndexOf(this.typ);
	this.field.value = this.field.value.substring(0, pos + 1) + input.innerHTML;
	if (this.div) {
		this.graph.board.removeChild(this.div);
		this.div = null;
	}
	this.field.focus();
};
// ################################## EditNode ####################################################
var EditNode = function (graph) {this.graph = graph; };
EditNode.prototype = Object_create(GraphUtil.prototype);
EditNode.prototype.addElement = function (element, typ) {
	var that = this;
	this.bind(element, "mouseup", function (e) {that.click(e, element, typ); });
};
EditNode.prototype.click = function (e, control, typ) {
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
EditNode.prototype.change = function (e, control) {
	if (e.keyCode !== 27 && e.keyCode !== 13) {
		return;
	}
	var node = this.getModelNode(control);
	control.contentEditable = false;
	this.graph.inputEvent = true;
	if (e.keyCode === 27) {
		control.innerHTML = control.oldValue;
		control.oldValue = null;
		return;
	}
	var value = control.innerHTML;
	control.oldValue = null;
	while (value.substring(value.length - 4) === "<br>") {
		value = value.substring(0, value.length - 4);
	}
	if (control.typ === "id") {
		node.model.id = value;
	} else if (control.typ === "attribute" || control.typ === "method") {
		var t = control.typ + "s", i;
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
var CreateEdge = function (graph) {this.graph = graph; };
CreateEdge.prototype = Object_create(GraphUtil.prototype);
CreateEdge.prototype.addElement = function (element, node) {
	var that = this;
	this.bind(element, "mouseup", function (e) {that.up(e, element, node); });
	this.bind(element, "mousedown", function (e) {that.down(e, element, node); });
};
CreateEdge.prototype.down = function (e, element, node) {
	this.fromElement = element;
	this.fromNode = node;
	this.x = e.x;
	this.y = e.y;
};
CreateEdge.prototype.up = function (e, element, node) {
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

	var i, width = 120;

	if (this.div) {
		return;
	}
	this.list = ["Generalisation", "Assoziation", "Abort"];

	var div = this.create({tag: "div", "class": "ChoiceBox", style: {left: e.x, top: e.y, "width": width, zIndex: 6000}});
	var that = this;
	var func = function () {that.select(this); };

	for (i = 0; i < this.list.length; i += 1) {
		if (i % 2 === 0) {
			this.create({tag: "div", value: this.list[i], _parent: div, onMouseup: func});
		} else {
			this.create({tag: "div", value: this.list[i], "class": "alt", _parent: div, onMouseup: func});
		}
	}
	this.div = div;
	this.graph.board.appendChild(div);
};
CreateEdge.prototype.startAction = function (e) {
	if (e.target === this.graph.board && this.div) {
		this.graph.board.removeChild(this.div);
		this.div = null;
	}
};
CreateEdge.prototype.select = function (e) {
	var t = e.innerHTML;
	var edge;
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
