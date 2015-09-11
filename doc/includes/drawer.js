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
/*jslint node: true, vars: true, newcap:true, nomen: true, continue: true */

// VERSION: 2015.09.02 19:00
/*global GraphUtil: false, SymbolLibary: false, svgConverter: false, jsEPS: false, jsPDF: false, document:false */
"use strict";
var Object_create = Object.create || function (o) { var F = function () {}; F.prototype = o; return new F(); };

var Drawer = function () {this.init(); };
Drawer.prototype.init = function (typ) {this.util = new GraphUtil(typ); this.symbolLib = new SymbolLibary(); };
Drawer.prototype.clearBoard = function () {};
Drawer.prototype.setPos = function (item, x, y) {item.x = x; item.y = y; };
Drawer.prototype.setSize = function (item, x, y) {item.width = x; item.height = y; };
Drawer.prototype.getColor = function (style, defaultColor) {
	if (style) {
		if (style.toLowerCase() === "create") {
			return "#008000";
		}
		if (style.toLowerCase() === "nac") {
			return "#FE3E3E";
		}
		if (style.indexOf("#") === 0) {
			return style;
		}
	}
	if (defaultColor) {
		return defaultColor;
	}
	return "#000";
};
Drawer.prototype.removeToolItems = function (board) {
	var i;
	for (i = 0; i < this.toolitems.length; i += 1) {
		this.toolitems[i].close();
		if (this.toolitems[i].showed) {
			board.removeChild(this.toolitems[i]);
			this.toolitems[i].showed = false;
		}
	}
};
Drawer.prototype.createImage = function (node) {
	node.model = node;
	if (this.symbolLib.isSymbol(node)) {
		return this.symbolLib.draw(null, node);
	}
	var n = {tag: "img", model: node, src: node.src};
	if (node.width || node.height) {
		n.width = node.width;
		n.height = node.height;
	} else {
		n.xmlns = "http://www.w3.org/1999/xhtml";
	}
	var img = this.util.create(n);
	if (!node.width && !node.height) {
		this.model.appendImage(img);
		return null;
	}
	return img;
};
Drawer.prototype.showToolItems = function (board) {
	var i;
	for (i = 0; i < this.toolitems.length; i += 1) {
		board.appendChild(this.toolitems[i]);
		this.toolitems[i].showed = true;
	}
};
Drawer.prototype.isInTool = function (x, y, ox, oy) {
	var i;
	for (i = 0; i < this.toolitems.length; i += 1) {
		var g = this.toolitems[i];
		if (x >= (g.tool.x + ox) && x <= (g.tool.x + g.tool.width + ox) && y >= (g.tool.y + oy) && y <= (g.tool.y + g.tool.height + oy)) {
			return true;
		}
	}
	return false;
};
Drawer.prototype.createBoard = function (node, graph, listener) {
	var i, that = this;
	this.model = graph;
	this.toolitems = [];
	if (listener) {
		for (i = 0; i < listener.length; i += 1) {
			this.toolitems.push(listener[i]);
		}
	}
	var board = this.util.create(node);
	node.model = graph;
	board.setAttribute('class', "Board");
	board.rasterElements = [];
	board.saveShow = false;
	board.onmouseover = function () {
		that.showToolItems(board);
	};
	board.onmouseout = function (event) {
		var left = board.offsetLeft, top = board.offsetTop, x = Math.floor(event.pageX), y = Math.floor(event.pageY);
		if (!left) {
			if (board.parentNode) {
				left = board.parentNode.offsetLeft;
			} else {
				left = 0;
			}
		}
		if (!top) {
			if (board.parentNode) {
				top = board.parentNode.offsetTop;
			} else {
				top = 0;
			}
		}
		if (!that.isInTool(x, y, left, top)) {
			that.removeToolItems(board);
		}
	};
	return board;
};
Drawer.prototype.getButtons = function (graph, notTyp) {
	var i, buttons = [];
	var that = this;
	if (graph && graph.model.options) {
		var o = graph.model.options.buttons;
		var func = function (e) {
			var t = e.target.typ || e.target.parentElement.typ;
			that.model.initDrawer(t);
			that.model.layout();
		};
		for (i = 0; i < o.length; i += 1) {
			var typ = o[i];
			if (typ !== notTyp) {
				buttons.push(this.drawButton(typ, func));
			}
		}
	}
	return buttons;
};
//				###################################################### HTMLDrawer ####################################################################################
var HTMLDrawer = function () { this.init(); };
HTMLDrawer.prototype = Object_create(Drawer.prototype);
HTMLDrawer.prototype.setPos = function (item, x, y) {item.style.left = x + "px"; item.style.top = y + "px"; };
HTMLDrawer.prototype.setSize = function (item, x, y) {item.style.width = x + "px"; item.style.height = y + "px"; };
HTMLDrawer.prototype.getSize = function (item) {return {x: item.clientWidth, y: item.clientHeight}; };
HTMLDrawer.prototype.createContainer = function (graph) {
	return this.createBoard({tag: "div"}, graph, this.getButtons(graph, "HTML"));
};
HTMLDrawer.prototype.createCell = function (parent, tag, node, innerHTML, typ) {
	var tr = this.util.create({"tag": 'tr'});
	var cell = this.util.create({"tag": tag, _font: true, value: innerHTML});
	this.model.createElement(cell, typ, node);
	tr.appendChild(cell);
	parent.appendChild(tr);
	return cell;
};
HTMLDrawer.prototype.getNode = function (node, draw) {
	var htmlElement = this.util.create({tag: "div", model: node});
	var model = this.model.model;
	if (node.typ === "patternobject") {
		htmlElement.className = "patternElement";
	} else if (this.symbolLib.isSymbol(node)) {
		return this.symbolLib.draw(null, node);
	}
	if (node.typ === "classdiagram") {
		htmlElement.className = "classdiagram";
	} else if (node.typ === "objectdiagram") {
		htmlElement.className = "objectdiagram";
	} else if (model.typ.toLowerCase() === "objectdiagram") {
		htmlElement.className = "objectElement";
	} else {
		htmlElement.className = "classElement";
	}
	this.setPos(htmlElement, node.x, node.y);
	htmlElement.style.zIndex = 5000;

	if (node.typ === "objectdiagram" || node.typ === "classdiagram") {
		node.left = node.top = 30;
		node._gui = htmlElement;
		if (draw) {
			this.model.draw(node);
			htmlElement.style.borderColor = "red";
			if (node.style && node.style.toLowerCase() === "nac") {
				htmlElement.appendChild(this.symbolLib.draw(null, {typ: "stop", x: 0, y: 0}));
			}
		} else {
			this.model.layout(0, 0, node);
		}
		this.setSize(htmlElement, node._gui.style.width, node._gui.style.height);
		return htmlElement;
	}
	this.model.createElement(htmlElement, "class", node);
	var img;
	if (node.content) {
		node.content.width = node.content.width || 0;
		node.content.height = node.content.height || 0;
		if (node.content.src) {
			img = this.createImage(node.content);
			if (!img) {return null; }
			htmlElement.appendChild(img);
			return htmlElement;
		}
		if (node.content.html) {
			htmlElement.innerHTML = node.content.html;
			return htmlElement;
		}
	}
	var table = this.util.create({tag: 'table', border: "0"});
	table.style.width = "100%";
	table.style.height = "100%";
	htmlElement.appendChild(table);
	var cell;
	if (node.head_src) {
		cell = this.createCell(table, "td", node);
		cell.style.textAlign = "center";
		if (!node.head_img) {
			node.head_img = {};
			node.head_img.src = node.head_src;
			node.head_img.width = node.head_width;
			node.head_img.height = node.head_height;
		}
		img = this.createImage(node.head_img);
		if (img) {
			cell.appendChild(img);
		}
	}
	if (node.headinfo) {
		this.createCell(table, "td", node, node.headinfo).className = "head";
	}
	var info;
	if (model.typ.toLowerCase() === "objectdiagram") {
		info = node.id.charAt(0).toLowerCase() + node.id.slice(1);
	} else {
		info = node.id;
	}
	if (node.href) {
		info = "<a href=\"" + node.href + "\">" + info + "</a>";
	}
	cell  = this.createCell(table, "th", node, info, "id");
	if (model.typ.toLowerCase() === "objectdiagram") {
		cell.style.textDecorationLine = "underline";
	}
	cell = null;
	var a, first;
	if (node.attributes) {
		first = true;
		for (a = 0; a < node.attributes.length; a += 1) {
			cell = this.createCell(table, "td", node, node.attributes[a], "attribute");
			if (!first) {
				cell.className = 'attributes';
			} else {
				cell.className = 'attributes first';
				first = false;
			}
		}
	}
	if (node.methods) {
		first = true;
		var m;
		for (m = 0; m < node.methods.length; m += 1) {
			var method = node.methods[m];
			cell = this.createCell(table, "td", node, node.methods[m], "method");
			if (!first) {
				cell.className = 'methods';
			} else {
				cell.className = 'methods first';
				first = false;
			}
		}
	}
	if (!cell) {
		cell = this.createCell(table, "td", node, "&nbsp;");
		cell.className = 'first';
		this.model.createElement(cell, "empty", node);
	}
	htmlElement.appendChild(table);
	htmlElement.node = node;
	node._gui = htmlElement;
	return htmlElement;
};
HTMLDrawer.prototype.createInfo = function (item, text, angle) {
	var info = this.util.create({tag: "div", _font: true, model: item, "class": "EdgeInfo", value: text});

	if (angle !== 0) {
		info.style.transform = "rotate(" + angle + "deg)";
		info.style.msTransform = info.style.MozTransform = info.style.WebkitTransform = info.style.OTransform = "rotate(" + angle + "deg)";
	}
	this.setPos(info, item.x, item.y);
	this.model.createElement(info, "info", item);
	return info;
};
HTMLDrawer.prototype.createLine = function (x1, y1, x2, y2, lineStyle) {
	if (x2 < x1) {
		var temp = x1;
		x1 = x2;
		x2 = temp;
		temp = y1;
		y1 = y2;
		y2 = temp;
	}
	// Formula for the distance between two points
	// http://www.mathopenref.com/coorddist.html
	var length = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));

	var line = this.util.create({tag: "div", "class": "lineElement", style: {width: length + "px", position: "absolute", zIndex: 42}});
	line.style.borderBottomStyle = lineStyle;

	var angle = Math.atan((y1 - y2) / (x1 - x2));
	if (x1 === x2) {
		angle = Math.atan((y1 - y2) / (x1 - x2)) * -1;
	}
	line.style.top = y1 + 0.5 * length * Math.sin(angle) + "px";
	line.style.left = x1 - 0.5 * length * (1 - Math.cos(angle)) + "px";
	line.style.transform = "rotate(" + angle + "rad)";
	line.style.msTransform = line.style.MozTransform = line.style.WebkitTransform = line.style.OTransform = "rotate(" + angle + "rad)";
	return line;
};
HTMLDrawer.prototype.drawButton = function (text, action) {
	var btn = this.util.create({tag: "button", _font: true, width: 60, height: 28, style: "cursor: pointer;", value: text, "onMousedown": action});
	btn.tool = {x: 0, y: 8, height: 28, width: 60};
	btn.typ = text;
	btn.close = function () {};
	return btn;
};
HTMLDrawer.prototype.createPath = function (close, fill, path, angle) {
	var line;
	if (fill === "none") {
		line = this.util.create({tag: "div"});
		var i;
		for (i = 1; i < path.length; i += 1) {
			line.appendChild(this.createLine(path[i - 1].x, path[i - 1].y, path[i].x, path[i].y));
		}
		if (close) {
			line.appendChild(this.createLine(path[path.length - 1].x, path[path.length - 1].y, path[0].x, path[0].y));
		}
		return line;
	}
	line = this.util.create({tag: "div", style: {position: "absolute", left: path[0].x, top: path[0].y, transform: "rotate(" + angle + "rad)"}});
	line.appendChild(this.util.create({tag: "div", style: {background: "#000", width: 8, height: 8, transform: "rotate(45rad) skew(170deg, 170deg)"}}));
	return line;
};
//				###################################################### SVG ####################################################################################
var SVGDrawer = function () {this.init("http://www.w3.org/2000/svg"); this.showButton = true; };
SVGDrawer.prototype = Object_create(Drawer.prototype);
SVGDrawer.prototype.getWidth = function (label) {
	var text = this.util.create({tag: "text", _font: true, value: label});
	text.setAttribute("width", "5px");
	var board = this.model.board;
	board.appendChild(text);
	var width = text.getBoundingClientRect().width;
	board.removeChild(text);
	return width;
};
SVGDrawer.prototype.drawDef = function () {
	var def = this.util.create({tag: "defs"});

	var child = this.util.create({tag: "filter", id: "drop-shadow"});
	child.appendChild(this.util.create({tag: "feGaussianBlur", "in": "SourceAlpha", result: "blur-out", stdDeviation: 2}));
	child.appendChild(this.util.create({tag: "feOffset", "in": "blur-out", dx: 2, dy: 2}));
	child.appendChild(this.util.create({tag: "feBlend", "in": "SourceGraphic", mode: "normal"}));
	def.appendChild(child);
	child = this.util.create({tag: "linearGradient", id: "reflect", x1: "0%", x2: "0%", y1: "50%", y2: "0%", spreadMethod: "reflect"});
	child.appendChild(this.util.create({tag: "stop", "stop-color": "#aaa", offset: "0%"}));
	child.appendChild(this.util.create({tag: "stop", "stop-color": "#eee", offset: "100%"}));
	def.appendChild(child);

	child = this.util.create({tag: "linearGradient", id: "classelement", x1: "0%", x2: "100%", y1: "100%", y2: "0%"});
	child.appendChild(this.util.create({tag: "stop", "stop-color": "#ffffff", offset: "0"}));
	child.appendChild(this.util.create({tag: "stop", "stop-color": "#d3d3d3", offset: "1"}));
	def.appendChild(child);
	return def;

};
SVGDrawer.prototype.drawButton = function (text, action) {
	var btn = this.symbolLib.draw(this, {typ: "Button", value: text});
	btn.typ = text;
	btn.tool = {x: 0, y: 8, height: 28, width: 60};
	this.util.bind(btn, "mousedown", action);
	btn.close = function () {};
	return btn;
};
SVGDrawer.prototype.drawComboBox = function (elements, activText, action) {
	var g = this.util.create({tag: "g"});
	g.tool = {x: 66, y: 8, minheight: 28, maxheight: 28, width: 80};
	g.status = "close";
	g.appendChild(this.util.create({tag: "rect", rx: 0, x: g.tool.x, y: g.tool.y, width: 60, height: 28, stroke: "#000", fill: "none"}));
	g.appendChild(this.util.create({tag: "rect", rx: 2, x: g.tool.x + 60, y: g.tool.y, width: 20, height: 28, stroke: "#000", "class": "saveBtn"}));
	g.appendChild(this.util.create({tag: "path", style: "fill:#000000;stroke:#000000;stroke-width:1px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1;fill-opacity:1",
								d: "m " + (g.tool.x + 65) + "," + (g.tool.y + 13) + " 10,0 L " + (g.tool.x + 70) + "," + (g.tool.y + 20) + " z"}));
	if (elements) {
		var len = 0, i;
		for (i = 0; i < elements.length; i += 1) {
			if (elements[i] && elements[i].length > 0) {
				len += 1;
			}
		}
		var choicebox = this.util.create({tag: "g"});
		var h = len * 25 + 6;
		choicebox.appendChild(this.util.create({tag: "rect", rx: 0, x: g.tool.x, y: g.tool.y + 28, width: 60, height: h, stroke: "#000", fill: "#fff", opacity: "0.7"}));
		g.tool.maxheight = h + g.tool.minheight;

		g.elements = elements;
		g.activ = this.util.create({tag: "text", _font: true, "text-anchor": "left", "width": 60, "x": (g.tool.x + 10), "y": g.tool.y + 20, value: activText});
		g.appendChild(g.activ);
		var y = 46 + g.tool.y;
		var yr = 28 + g.tool.y;
		var e;
		var func = function (event) {
			g.activ.textContent = event.currentTarget.value;
		};
		for (e = 0; e < elements.length; e += 1) {
			if (!elements[e] || elements[e].length < 1) {
				continue;
			}
			var element = elements[e];
			choicebox.appendChild(this.util.create({tag: "text", _font: true, "text-anchor": "left", "width": 60, "x": (g.tool.x + 10), "y": y, value: element}));
			var item = choicebox.appendChild(this.util.create({tag: "rect", rx: 0, x: g.tool.x, y: yr, width: 60, height: 24, stroke: "none", "class": "selection"}));
			item.value = element;
			if (action) {
				item.onclick = action;
			} else {
				item.onclick = func;
			}
			y += 26;
			yr += 26;
		}
		g.choicebox = choicebox;
	}
	g.onclick = function () {
		if (g.status === "close") {
			g.open();
		} else {
			g.close();
		}
	};
	g.close = function () {
		if (g.status === "open") {
			this.removeChild(this.choicebox);
		}
		this.status = "close";
		this.tool.height = this.tool.minheight;
	};
	g.open = function () {
		if (g.status === "close") {
			this.appendChild(this.choicebox);
		}
		this.status = "open";
		this.tool.height = this.tool.maxheight;
	};
	g.close();
	return g;
};
SVGDrawer.prototype.createContainer = function (graph) {
	var that = this;
	var list = ["HTML", "SVG", "PNG"];
	var hasConverter = typeof (svgConverter);
	if (hasConverter !== "undefined") {
		var hasEPS = typeof (jsEPS);
		var hasPDF = typeof (jsPDF);
		list.push(hasEPS !== "undefined" ? "EPS" : "");
		list.push(hasPDF !== "undefined" ? "PDF" : "");
	}
	var buttons = [];
	if (this.showButton) {
		buttons = this.getButtons(graph, "SVG");
		buttons.push(this.drawComboBox(list, "Save", function (e) {that.removeToolItems(that.board); that.model.SaveAs(e.currentTarget.value); }));
	}

	var board = this.createBoard({tag: "svg", "xmlns:svg": "http://www.w3.org/2000/svg", "xmlns:xlink": "http://www.w3.org/1999/xlink"}, graph, buttons);
	board.appendChild(this.drawDef());
	return board;
};
SVGDrawer.prototype.setSize = function (item, x, y) {
	x = this.util.getValue(x);
	y = this.util.getValue(y);
	item.setAttribute("width", x);
	item.setAttribute("height", y);
	item.style.width = Math.ceil(x);
	item.style.height = Math.ceil(y);
};
SVGDrawer.prototype.getNode = function (node, draw) {
	var symbolLib = new SymbolLibary();
	if (symbolLib.isSymbol(node)) {
		return symbolLib.draw(this, node);
	}
	var g;
	if (node.content) {
		node.content.width = node.content.width || 0;
		node.content.height = node.content.height || 0;
		if (node.content.src) {
			var img = this.createImage(node.content);
			if (!img) {return null; }
			return img;
		}
		g = this.util.create({tag: "g", model: node});
		if (node.content.svg) {
			g.setAttribute('transform', "translate(" + node.x + " " + node.y + ")");
			g.innerHTML = node.content_svg;
			return g;
		}
		if (node.content.html) {
			g.setAttribute('transform', "translate(" + node.x + " " + node.y + ")");
			g.innerHTML = node.content_svg;
			return g;
		}
	}
	g = this.util.create({tag: "g", model: node});
	var width, height;
	if (node.typ === "objectdiagram" || node.typ === "classdiagram") {
		if (node.status === "close") {
			width = this.getWidth(node.id) + 30;
			height = 40;
			this.addChild(node, g, this.util.create({tag: "text", _font: true, "text-anchor": "left", "x": (node.x + 2), "y": node.y + 12, value: node.id}));
		} else {
			node.left = node.top = 30;
			node._gui = g;
			if (draw) {
				this.model.draw(node);
			} else {
				this.model.layout(0, 0, node);
			}

			width = this.util.getValue(node._gui.style.width);
			height = this.util.getValue(node._gui.style.height);
			if (node.style && node.style.toLowerCase() === "nac") {
				this.addChild(node, g, this.createGroup(node, symbolLib.drawStop(node)));
			}
		}
		this.setSize(g, width, height);
		this.addChild(node, g, this.util.create({tag: "rect", "width": width, "height": height, "fill": "none", "strokeWidth": "1px", "stroke": this.getColor(node.style, "#CCC"), "x": node.getX(), "y": node.getY(), "class": "draggable"}));
		if (width > 0 && width !== node.width) {node.width = width; }
		var btn;
		if (node.status === "close") {
			// Open Button
			btn = this.createGroup(node, symbolLib.drawMax(node));
		} else {
			btn = this.createGroup(node, symbolLib.drawMin(node));
		}
		var that = this;
		btn.setAttribute("class", "btn");

		this.util.bind(btn, "mousedown", function (e) {
			if (node.status === "close") {
				node.status = "open";
				that.model.redrawNode(node);
			} else {
				node.status = "close";
				that.model.redrawNode(node);
			}
			if (e.stopPropagation) {e.stopPropagation(); }
			if (e.cancelBubble !== null) {e.cancelBubble = true; }
		});
		g.appendChild(btn);
		this.model.createElement(g, "class", node);
		return g;
	}

	if (node.content_plain) {
		return this.util.create({tag: "text", _font: true, "text-anchor": "left", "x": (node.x + 10), value: node.content_plain});
	}

	width = 0;
	height = 40;
	var textWidth;

	var id;
	if (this.model.model.typ.toLowerCase() === "objectdiagram") {
		id = node.id.charAt(0).toLowerCase() + node.id.slice(1);
	} else {
		id = node.id;
		if (node.counter) {
			id += " (" + node.counter + ")";
		}
	}
	textWidth = this.getWidth(id);

	width = Math.max(width, textWidth);
	var i;
	if (node.attributes && node.attributes.length > 0) {
		height = height + node.attributes.length * 25;
		for (i = 0; i < node.attributes.length; i += 1) {
			var attribute = node.attributes[i];
			width = Math.max(width, this.getWidth(attribute));
		}
	} else {
		height += 20;
	}
	if (node.methods && node.methods.length > 0) {
		height = height + node.methods.length * 25;
		for (i = 0; i < node.methods.length; i += 1) {
			var method = node.methods[i];
			width = Math.max(width, this.getWidth(method));
		}
	}
	width += 20;

	var y = node.getY();
	var x = node.getX();

	this.model.createElement(g, "class", node);
	var rect = {tag: "rect", "width": width, "height": height, "x": x, "y": y, "fill": "#fff", "class": "draggable"};
	var typ = node.typ.toLowerCase();
	if (typ === "patternobject") {
		rect.fill = "lightblue";
	}
	var strokeColor = this.getColor(node.style);
	rect.stroke = strokeColor;
	g.appendChild(this.util.create(rect));

	if (typ !== "patternobject") {
		g.appendChild(this.util.create({tag: "rect", rx: 0, "x": x, "y": y, "width": width, height: 30, fill: "none", style: "fill:url(#classelement);"}));
	}

	var text = this.util.create({tag: "text", _font: true, "text-anchor": "right", "x": x + width / 2 - textWidth / 2, "y": y + 20, "width": textWidth});

	if (this.model.model.typ.toLowerCase() === "objectdiagram") {
		text.setAttribute("text-decoration", "underline");
	}
	text.appendChild(document.createTextNode(id));

	g.appendChild(text);
	g.appendChild(this.util.create({tag: "line", x1: x, y1: y + 30, x2: x + width, y2: y + 30, stroke: strokeColor}));
	y += 50;

	if (node.attributes) {
		var a;
		for (a = 0; a < node.attributes.length; a += 1) {
			g.appendChild(this.util.create({tag: "text", _font: true, "text-anchor": "left", "width": width, "x": (x + 10), "y": y, value: node.attributes[a]}));
			y += 20;
		}
		if (node.attributes.length > 0) {
			y -= 10;
		}
	}
	if (node.methods && node.methods.length > 0) {
		g.appendChild(this.util.create({tag: "line", x1: x, y1: y, x2: x + width, y2: y, stroke: "#000"}));
		y += 20;
		var m;
		for (m = 0; m < node.methods.length; m += 1) {
			g.appendChild(this.util.create({tag: "text", _font: true, "text-anchor": "left", "width": width, "x": x + 10, "y": y, value: node.methods[m]}));
			y += 20;
		}
	}
	return g;
};
SVGDrawer.prototype.addChild = function (node, parent, child) {
	child.setAttribute("class", "draggable");
	parent.appendChild(child);
	this.model.createElement(child, "class", node);
};
SVGDrawer.prototype.createInfo = function (item, text, angle) {
	var items = text.split("\n");
	var group, i;
	if (items.length > 1) {
		group = this.util.create({tag: "g", "class": "draggable", rotate: angle, model: item});
		for (i = 0; i < items.length; i += 1) {
			var child = this.util.create({tag: "text", _font: true, "text-anchor": "left", "x": item.x, "y": item.y + (item.height * i)});
			child.appendChild(document.createTextNode(items[i]));
			group.appendChild(child);
		}
		this.model.createElement(group, "info", item);
		return group;
	}
	group = this.util.create({tag: "text", "#_font": true, "text-anchor": "left", "x": item.x, "y": item.y, value: text, "id": item.id, "class": "draggable", rotate: angle, model: item});
	this.model.createElement(group, "info", item);
	return group;
};
SVGDrawer.prototype.createLine = function (x1, y1, x2, y2, lineStyle, style) {
	var line = this.util.create({tag: "line", 'x1': x1, 'y1': y1, 'x2': x2, 'y2': y2, "stroke": this.getColor(style)});
	if (lineStyle && lineStyle.toLowerCase() === "dotted") {
		line.setAttribute("stroke-miterlimit", "4");
		line.setAttribute("stroke-dasharray", "1,1");
	}
	return line;
};
SVGDrawer.prototype.createPath = function (close, fill, path) {
	var i, d = "M" + path[0].x + " " + path[0].y;
	for (i = 1; i < path.length; i += 1) {
		d = d + "L " + path[i].x + " " + path[i].y;
	}
	if (close) {
		d = d + " Z";
	}
	return this.util.create({tag: "path", "d": d, "fill": fill, stroke: "#000", "stroke-width": "1px"});
};
SVGDrawer.prototype.createGroup = function (node, group) {
	var i, entity = this.util.create({tag: "g"});
	var transform = "translate(" + group.x + " " + group.y + ")";
	if (group.scale) { transform += " scale(" + group.scale + ")"; }
	if (group.rotate) { transform += " rotate(" + group.rotate + ")"; }
	entity.setAttribute('transform', transform);
	entity.setAttribute("height", group.height);
	entity.setAttribute("width", group.width);
	for (i = 0; i < group.items.length; i += 1) {
		entity.appendChild(this.util.create(group.items[i]));
	}
	return entity;
};
// Example Items
// {tag: "path", d: ""}
// {tag: "rect", width:46, height:34}
// {tag: "ellipse", width:23, height:4}
// {tag: "line", x1:650, y1:-286, x2:650, y2:-252}
// {tag: "circle", r:5, x:12, y:0}
// {tag: "image", height: 30, width: 50, content_src: hallo}
// {tag: "text", "text-anchor": "left", x: "10"}
var SymbolLibary = function () {};
SymbolLibary.prototype.upFirstChar = function (txt) {return txt.charAt(0).toUpperCase() + txt.slice(1).toLowerCase(); };
SymbolLibary.prototype.create = function (node, drawer) {
	if (this.isSymbol(node)) {
		return this.draw(drawer, node, false);
	}
	return null;
};
SymbolLibary.prototype.isSymbol = function (node) {
	var fn = this[this.getName(node)];
	return typeof fn === "function";
};
SymbolLibary.prototype.getName = function (node) {
	if (node.typ) {
		return "draw" + this.upFirstChar(node.typ);
	}
	return "draw" + this.upFirstChar(node.src);
};
SymbolLibary.prototype.draw = function (drawer, node) {
	var fn = this[this.getName(node)];
	if (typeof fn === "function") {
		var group = fn.apply(this, [node]);
		if (!drawer) {
			drawer = new SVGDrawer();
			drawer.showButton = false;
			var board = drawer.createContainer(null);
			var element = drawer.createGroup(node, group);
			board.appendChild(element);
			return board;
		}
		return drawer.createGroup(node, group);
	}
};
SymbolLibary.prototype.drawSmily = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 100,
		height: 100,
		items: [
			{tag: "path", stroke: "black", fill: "none", d: "m49.01774,25.64542a24.5001,24.5 0 1 1 -49.0001,0a24.5001,24.5 0 1 1 49.0001,0z"},
			{tag: "path", d: "m8,31.5c16,20 32,0.3 32,0.3"},
			{tag: "path", d: "m19.15,20.32a1.74,2.52 0 1 1 -3.49,0a1.74,2.52 0 1 1 3.49,0z"},
			{tag: "path", d: "m33,20.32a1.74,2.52 0 1 1 -3.48,0a1.74,2.52 0 1 1 3.48,0z"},
			{tag: "path", d: "m5.57,31.65c3.39,0.91 4.03,-2.20 4.03,-2.20"},
			{tag: "path", d: "m43,32c-3,0.91 -4,-2.20 -4.04,-2.20"}
		]
	};
};
SymbolLibary.prototype.drawDatabase = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 100,
		height: 100,
		items: [
			{tag: "path", stroke: "black", fill: "none", d: "m0,6.26c0,-6.26 25.03,-6.26 25.03,0l0,25.82c0,6.26 -25.03,6.26 -25.03,0l0,-25.82z"},
			{tag: "path", stroke: "black", fill: "none", d: "m0,6.26c0,4.69 25.03,4.69 25.03,0m-25.03,2.35c0,4.69 25.03,4.69 25.03,0m-25.03,2.35c0,4.69 25.03,4.69 25.03,0"}
		]
	};
};
SymbolLibary.prototype.drawLetter = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 100,
		height: 50,
		items: [
			{tag: "path", stroke: "black", fill: "none", d: "m1,1l22,0l0,14l-22,0l0,-14z"},
			{tag: "path", stroke: "black", fill: "none", d: "m1.06,1.14l10.94,6.81l10.91,-6.91"}
		]
	};
};
SymbolLibary.prototype.drawMobilphone = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 25,
		height: 50,
		items: [
			{tag: "path", d: "m 4.2 0.5 15.61 0c 2 0 3.7 1.65 3.7 3.7l 0 41.6c 0 2-1.65 3.7-3.7 3.7l-15.6 0c-2 0-3.7-1.6-3.7-3.7l 0-41.6c 0-2 1.6-3.7 3.7-3.7z", fill: "none", stroke: "black"},
			{tag: "path", d: "m 12.5 2.73a 0.5 0.5 0 1 1-1 0 0.5 0.5 0 1 1 1 0z"},
			{tag: "path", d: "m 14 46a 2 2 0 1 1-4 0 2 2 0 1 1 4 0z"},
			{tag: "path", d: "m 8 5 7 0"},
			{tag: "path", d: "m 1.63 7.54 20.73 0 0 34-20.73 0z"}
		]
	};
};
SymbolLibary.prototype.drawWall = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 25,
		height: 50,
		items: [
			{tag: "path", d: "m26,45.44l-5,3.56l-21,-9l0,-36.41l5,-3.56l20.96,9l-0,36.4z"},
			{tag: "path", stroke: "white", d: "m2.21,11l18.34,7.91m-14.46,-12.57l0,6.3m8.2,21.74l0,6.35m-8.6,-10l0,6.351m4.1,-10.67l0,6.3m4.8,-10.2l0,6.3m-8.87,-10.23l0,6.35m4.78,-10.22l0,6.35m-8,14.5l18.34,7.91m-18.34,-13.91l18.34,7.91m-18.34,-13.91l18.34,7.91m-18.34,-13.91l18.34,7.91m0,-13l0,34m-18.23,-41.84l18.3,8m0,0.11l5,-3.57"}
		]
	};
};
SymbolLibary.prototype.drawActor = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 25,
		height: 50,
		items: [
			{tag: "line", stroke: "#000", x1: "12", y1: "10", x2: "12", y2: "30"},
			{tag: "circle", stroke: "#000", cy: "5", cx: "12", r: "5"},
			{tag: "line", stroke: "#000", y2: "18", x2: "25", y1: "18", x1: "0"},
			{tag: "line", stroke: "#000", y2: "39", x2: "5", y1: "30", x1: "12"},
			{tag: "line", stroke: "#000", y2: "39", x2: "20", y1: "30", x1: "12"}
		]
	};
};
SymbolLibary.prototype.drawLamp = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 25,
		height: 50,
		items: [
			{tag: "path", d: "m 22.47 10.58c-6.57 0-11.89 5.17-11.89 11.54 0 2.35 0.74 4.54 2 6.36 2 4 4.36 5.63 4.42 10.4l 11.15 0c 0.12-4.9 2.5-6.8 4.43-10.4 1.39-1.5 1.8-4.5 1.8-6.4 0-6.4-5.3-11.5-11.9-11.5z", fill: "white", stroke: "black"},
			{tag: "path", d: "m 18.4 40 8 0c 0.58 0 1 0.5 1 1 0 0.6-0.5 1-1 1l-8 0c-0.6 0-1-0.47-1-1 0-0.58 0.47-1 1-1z"},
			{tag: "path", d: "m 18.4 42.7 8 0c 0.58 0 1 0.47 1 1 0 0.58-0.47 1-1 1l-8 0c-0.58 0-1-0.47-1-1 0-0.58 0.46-1 1-1z"},
			{tag: "path", d: "m 18.4 45.3 8 0c 0.58 0 1 0.47 1 1 0 0.58-0.47 1-1 1l-8 0c-0.58 0-1-0.47-1-1 0-0.58 0.46-1 1-1z"},
			{tag: "path", d: "m 19.5 48c 0.37 0.8 1 1.3 1.9 1.7 0.6 0.3 1.5 0.3 2 0 0.8-0.3 1.4-0.8 1.9-1.8z"},
			{tag: "path", d: "m 6 37.5 4.2-4c 0.3-0.3 0.8-0.3 1 0 0.3 0.3 0.3 0.8 0 1.1l-4.2 4c-0.3 0.3-0.8 0.3-1.1 0-0.3-0.3-0.3-0.8 0-1z"},
			{tag: "path", d: "m 39 37.56-4.15-4c-0.3-0.3-0.8-0.3-1 0-0.3 0.3-0.3 0.8 0 1l 4.2 4c 0.3 0.3 0.8 0.3 1 0 0.3-0.3 0.3-0.8 0-1z"},
			{tag: "path", d: "m 38 23 5.8 0c 0.4 0 0.8-0.3 0.8-0.8 0-0.4-0.3-0.8-0.8-0.8l-5.8 0c-0.4 0-0.8 0.3-0.8 0.8 0 0.4 0.3 0.8 0.8 0.8z"},
			{tag: "path", d: "m 1.3 23 6 0c 0.4 0 0.8-0.3 0.8-0.8 0-0.4-0.3-0.8-0.8-0.8l-5.9 0c-0.4 0-0.8 0.3-0.8 0.8 0 0.4 0.3 0.8 0.8 0.8z"},
			{tag: "path", d: "m 34.75 11.2 4-4.1c 0.3-0.3 0.3-0.8 0-1-0.3-0.3-0.8-0.3-1 0l-4 4.1c-0.3 0.3-0.3 0.8 0 1 0.3 0.3 0.8 0.3 1 0z"},
			{tag: "path", d: "m 11.23 10-4-4c-0.3-0.3-0.8-0.3-1 0-0.3 0.3-0.3 0.8 0 1l 4.2 4c 0.3 0.3 0.8 0.3 1 0 0.3-0.3 0.3-0.8 0-1z"},
			{tag: "path", d: "m 21.64 1.3 0 5.8c 0 0.4 0.3 0.8 0.8 0.8 0.4 0 0.8-0.3 0.8-0.8l 0-5.8c 0-0.4-0.3-0.8-0.8-0.8-0.4 0-0.8 0.3-0.8 0.8z"},
			{tag: "path", d: "m 26.1 24.3c-0.5 0-1 0.2-1.3 0.4-1.1 0.6-2 3-2.27 3.5-0.26-0.69-1.14-2.9-2.2-3.5-0.7-0.4-2-0.7-2.5 0-0.6 0.8 0.2 2.2 0.9 2.9 1 0.9 3.9 0.9 3.9 0.9 0 0 0 0 0 0 0.54 0 2.8 0 3.7-0.9 0.7-0.7 1.5-2 0.9-2.9-0.2-0.3-0.7-0.4-1.2-0.4z"},
			{tag: "path", d: "m 22.5 28.57 0 10.7"}
		]
	};
};
SymbolLibary.prototype.drawStop = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 30,
		height: 30,
		items: [
			{tag: "path", fill: "#FFF", "stroke-width": "2", stroke: "#B00", d: "m 6,6 a 14,14 0 1 0 0.06,-0.06 z m 0,0 20,21"}
		]
	};
};
SymbolLibary.prototype.drawMin = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 20,
		height: 20,
		items: [
			{tag: "path", fill: "none", stroke: "#000", "stroke-width": 0.2, "stroke-linejoin": "round", d: "m 0,0 19,0 0,19 -19,0 z"},
			{tag: "path", fill: "none", stroke: "#000", "stroke-width": "1px", "stroke-linejoin": "miter", d: "m 4,10 13,-0.04"}
		]
	};
};
SymbolLibary.prototype.drawArrow = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 20,
		height: 20,
		rotate: node.rotate,
		items: [
			{tag: "path", fill: "#000", stroke: "#000", d: "M 0,0 10,4 0,9 z"}
		]
	};
};
SymbolLibary.prototype.drawMax = function (node) {
	return {
		x: node.x || 0,
		y: node.y || 0,
		width: 20,
		height: 20,
		items: [
			{tag: "path", fill: "none", stroke: "#000", "stroke-width": 0.2, "stroke-linejoin": "round", "stroke-dashoffset": 2, "stroke-dasharray": "4.8,4.8", d: "m 0,0 4.91187,0 5.44643,0 9.11886,0 0,19.47716 -19.47716,0 0,-15.88809 z"},
			{tag: "path", fill: "none", stroke: "#000", "stroke-width": "1px", "stroke-linejoin": "miter", d: "m 4,10 6,0.006 0.02,5 0.01,-11 -0.03,6.02 c 2,-0.01 4,-0.002 6,0.01"}
		]
	};
};
SymbolLibary.prototype.drawButton = function (node) {
	var btnX = node.x || 0;
	var btnY = node.y || 0;
	var btnWidth = node.width || 60;
	var btnHeight = node.height || 28;
	var btnValue = node.value || "";
	return {
		x: btnX,
		y: btnY,
		width: 60,
		height: 28,
		items: [
			{tag: "rect", rx: 8, x: 0, y: 0, width: btnWidth, height: btnHeight, stroke: "#000", filter: "url(#drop-shadow)", "class": "saveBtn"},
			{tag: "text", _font: true, x: 10, y: 18, fill: "black", value: btnValue, "class": "hand"}
		]
	};
};
SymbolLibary.prototype.drawDropdown = function (node) {
	return {
		x: node.x || 0;
		y: node.y || 0;
		width: 60,
		height: 28,
		items: [
			{tag: "rect", rx: 8, x: 0, y: 0, width: btnWidth, height: btnHeight, stroke: "#000", filter: "url(#drop-shadow)", "class": "saveBtn"},
			{tag: "text", _font: true, x: 10, y: 18, fill: "black", value: btnValue, "class": "hand"}
//	g.appendChild(this.util.create({tag: "rect", rx: 0, x: g.tool.x, y: g.tool.y, width: 60, height: 28, stroke: "#000", fill: "none"}));
//	g.appendChild(this.util.create({tag: "rect", rx: 2, x: g.tool.x + 60, y: g.tool.y, width: 20, height: 28, stroke: "#000", "class": "saveBtn"}));
//	g.appendChild(this.util.create({tag: "path", style: "fill:#000000;stroke:#000000;stroke-width:1px;stroke-linecap:butt;stroke-linejoin:miter;stroke-opacity:1;fill-opacity:1",
//								d: "m " + (g.tool.x + 65) + "," + (g.tool.y + 13) + " 10,0 L " + (g.tool.x + 70) + "," + (g.tool.y + 20) + " z"}));
		]
};